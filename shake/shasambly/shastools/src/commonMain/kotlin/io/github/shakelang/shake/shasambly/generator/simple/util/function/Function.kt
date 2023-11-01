@file:Suppress("unused")
package io.github.shakelang.shake.shasambly.generator.simple.util.function

import io.github.shakelang.shake.util.primitives.bytes.removeLastByte
import io.github.shakelang.shake.util.primitives.bytes.removeLastInt
import io.github.shakelang.shake.util.primitives.bytes.removeLastLong
import io.github.shakelang.shake.util.primitives.bytes.removeLastShort
import io.github.shakelang.shake.shasambly.generator.basic.ShasamblyOpcode
import io.github.shakelang.shake.shasambly.generator.basic.ShasamblyOpcodeJumpStaticToIndex
import io.github.shakelang.shake.shasambly.generator.simple.RelativeShasamblyGeneratorPart
import io.github.shakelang.shake.shasambly.generator.simple.SimpleShasambly
import io.github.shakelang.shake.shasambly.generator.simple.SimpleShasamblyGenerator
import io.github.shakelang.shake.shasambly.generator.simple.SimpleShasamblyGeneratorFunction

typealias SimpleRoutineShasamblyGenerator = SimpleRoutineShasambly.() -> Unit

open class SimpleRoutineShasambly(
    val routine: CallableRoutine,
    base: SimpleShasamblyGenerator,
    parent: SimpleShasambly,
    generator: SimpleRoutineShasamblyGenerator,
) : RelativeShasamblyGeneratorPart(base, parent, {}) {

    init {
        generator(this)
    }

    fun getByteArgument(position: Int) {
        if(position < 0 || position >= routine.argumentCount)
            throw Error("Position must be bigger than 0 and smaller than ${routine.argumentCount}")
        b_get_local(position + routine.stackSize)
    }

    fun getShortArgument(position: Int) {
        if(position < 0 || position >= routine.argumentCount - 1)
            throw Error("Position must be bigger than 0 and smaller than ${routine.argumentCount - 1}")
        if(position < 0) throw Error("Position must be bigger than 0 and smaller than ${routine.argumentCount}")
        s_get_local(position + routine.stackSize)
    }

    fun getIntArgument(position: Int) {
        if(position < 0 || position >= routine.argumentCount - 3)
            throw Error("Position must be bigger than 0 and smaller than ${routine.argumentCount - 3}")
        if(position < 0) throw Error("Position must be bigger than 0 and smaller than ${routine.argumentCount}")
        i_get_local(position + routine.stackSize)
    }

    fun getLongArgument(position: Int) {
        if(position < 0 || position >= routine.argumentCount - 7)
            throw Error("Position must be bigger than 0 and smaller than ${routine.argumentCount - 3}")
        if(position < 0) throw Error("Position must be bigger than 0 and smaller than ${routine.argumentCount}")
        l_get_local(position + routine.stackSize)
    }

    fun getFloatArgument(position: Int) = getIntArgument(position)
    fun getDoubleArgument(position: Int) = getLongArgument(position)

}

open class CallableRoutine(
    val shasambly: SimpleShasambly,
    val argumentCount: Int,
    val stackSize: Int,
    var exec: SimpleRoutineShasamblyGenerator? = null
) {
    val toInitCalls = mutableListOf<(ShasamblyOpcode) -> Unit>()
    val realStackSize: Int = stackSize + argumentCount + 4

    var isCreated = false
    var location = 0

    fun call(args: ByteArray) {
        if(args.size != this.argumentCount) throw Error("Expecting $argumentCount arguments for function")
        shasambly {
            incrStack(realStackSize)
            val list = args.toMutableList()
            while(list.size > 7) {
                lpush(list.removeLastLong())
                l_store_local(list.size + stackSize)
            }
            if(list.size > 3) {
                ipush(list.removeLastInt())
                i_store_local(list.size + stackSize)
            }
            if(list.size > 1) {
                spush(list.removeLastShort())
                s_store_local(list.size + stackSize)
            }
            if(list.size > 0) {
                bpush(list.removeLastByte())
                b_store_local(stackSize)
            }
            push_rel_addr(3)
            i_store_local(realStackSize - 4)
            jumpToCallableRoutineStart()
        }
    }

    fun call() {
        shasambly {
            incrStack(realStackSize)
            var size = argumentCount
            while(size > 7) {
                size -= 8
                l_store_local(size + stackSize)
            }
            if(size > 3) {
                size -= 4
                i_store_local(size + stackSize)
            }
            if(size > 1) {
                size -= 2
                s_store_local(size + stackSize)
            }
            if(size > 0) {
                size -= 1
                b_store_local(stackSize)
            }
            push_rel_addr(3)
            i_store_local(realStackSize - 4)
            jumpToCallableRoutineStart()
        }
    }

    fun jumpToCallableRoutineStart() {
        shasambly {
            if (isCreated) jumpStaticTo(location)
            else toInitCalls.add(lateinit(5))
        }
    }
    fun create() {
        if (isCreated) throw Error("Already created")
        if (this.exec == null) throw Error("No executor defined")
        create(this.exec!!)
        this.exec = null
    }

    fun create(exec: SimpleRoutineShasamblyGenerator) {
        shasambly {
            if (isCreated) throw Error("Already created")
            isCreated = true
            val jumpEnd = lateinit(5)
            location = size
            val jump = ShasamblyOpcodeJumpStaticToIndex(location)
            toInitCalls.forEach { it(jump) }
            SimpleRoutineShasambly(this@CallableRoutine, base, this@shasambly, exec)
            i_get_local(realStackSize - 4)
            decrStack()
            jumpDynamic()
            jumpEnd(ShasamblyOpcodeJumpStaticToIndex(size))
        }
    }

    fun executor(exec: SimpleShasamblyGeneratorFunction) {
        if(this.exec != null || this.isCreated) throw Error("Executor is already defined")
        this.exec = exec
    }
}

fun SimpleShasambly.declareRoutine(argumentCount: Int, stackSize: Int = 100, exec: SimpleRoutineShasamblyGenerator): CallableRoutine {
    return CallableRoutine(this, argumentCount = argumentCount, stackSize = stackSize, exec = exec)
}

fun SimpleShasambly.declareRoutine(argumentCount: Int, stackSize: Int = 100): CallableRoutine {
    return CallableRoutine(this, argumentCount = argumentCount, stackSize = stackSize)
}

fun SimpleShasambly.createRoutine(argumentCount: Int, stackSize: Int = 100, exec: SimpleRoutineShasamblyGenerator): CallableRoutine {
    val ret = CallableRoutine(this, argumentCount = argumentCount, stackSize = stackSize, exec = exec)
    ret.create()
    return ret
}
