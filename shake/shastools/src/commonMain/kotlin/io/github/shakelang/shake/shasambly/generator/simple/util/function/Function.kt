@file:Suppress("unused")
package io.github.shakelang.shake.shasambly.generator.simple.util.function

import io.github.shakelang.parseutils.bytes.removeLastInt
import io.github.shakelang.parseutils.bytes.removeLastLong
import io.github.shakelang.parseutils.bytes.removeLastShort
import io.github.shakelang.shake.shasambly.generator.basic.ShasamblyOpcode
import io.github.shakelang.shake.shasambly.generator.basic.ShasamblyOpcodeJumpStaticToIndex
import io.github.shakelang.shake.shasambly.generator.simple.SimpleShasambly
import io.github.shakelang.shake.shasambly.generator.simple.SimpleShasamblyGeneratorFunction

open class CallableRoutine(
    val shasambly: SimpleShasambly,
    val argumentCount: Int,
    val stackSize: Int,
    var exec: SimpleShasamblyGeneratorFunction? = null
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
            while(args.size > 7) {
                val pos = list.size + argumentCount
                lpush(list.removeLastLong())
                storeLocalLong(pos)
            }
            if(list.size > 3) {
                val pos = list.size + argumentCount
                ipush(list.removeLastInt())
                storeLocalLong(pos)
            }
            if(list.size > 1) {
                val pos = list.size + argumentCount
                spush(list.removeLastShort())
                storeLocalShort(pos)
            }
            if(list.size > 0) {
                spush(list.removeLastShort())
                storeLocalShort(argumentCount)
            }
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

    fun create(exec: SimpleShasamblyGeneratorFunction) {
        shasambly {
            if (isCreated) throw Error("Already created")
            isCreated = true
            location = size
            toInitCalls.forEach {
                it(ShasamblyOpcodeJumpStaticToIndex(location))
            }
            exec()
            i_get_local(realStackSize)
            jumpDynamic()
        }
    }

    fun executor(exec: SimpleShasamblyGeneratorFunction) {
        if(this.exec != null || this.isCreated) throw Error("Executor is already defined")
        this.exec = exec
    }
}

fun SimpleShasambly.declareRoutine(argumentCount: Int, stackSize: Int = 100, exec: SimpleShasamblyGeneratorFunction): CallableRoutine {
    return CallableRoutine(this, argumentCount = argumentCount, stackSize = stackSize, exec = exec)
}

fun SimpleShasambly.declareRoutine(argumentCount: Int, stackSize: Int = 100): CallableRoutine {
    return CallableRoutine(this, argumentCount = argumentCount, stackSize = stackSize)
}

fun SimpleShasambly.createRoutine(argumentCount: Int, stackSize: Int = 100, exec: SimpleShasamblyGeneratorFunction): CallableRoutine {
    val ret = CallableRoutine(this, argumentCount = argumentCount, stackSize = stackSize, exec = exec)
    ret.create()
    return ret
}
