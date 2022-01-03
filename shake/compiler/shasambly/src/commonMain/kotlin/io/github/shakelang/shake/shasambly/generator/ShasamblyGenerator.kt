@file: Suppress("unused")
package io.github.shakelang.shake.shasambly.generator

import io.github.shakelang.parseutils.bytes.toBytes
import io.github.shakelang.parseutils.bytes.toHexString
import io.github.shakelang.shake.shasambly.interpreter.Opcodes

interface ShasamblyOpcode {

    val size: Int
    fun generate(gen: ShasamblyGenerator): ByteArray

}

open class ShasamblyGenerator(contents: MutableList<ShasamblyOpcode>) : MutableList<ShasamblyOpcode> by contents {

    constructor(contents: Array<ShasamblyOpcode>) : this(contents.toMutableList())

    private var sizesCache: List<Int>? = null
    val sizes: List<Int> get() = sizesCache ?: map { it.size }

    fun generate(): ByteArray {
        if(sizesCache != null) throw IllegalStateException("Generate already running")
        sizesCache = sizes

        val bytes = flatMap { it.generate(this).toList() }.toByteArray()

        sizesCache = null
        return bytes
    }

    fun positionOfIndex(index: Int): Int {
        val sizes = sizes
        var sum = 0
        if(index > sizes.size) throw IllegalArgumentException("Index to big, must be smaller than ${sizes.size} (is $index)")
        for(i in 0 until index) sum += sizes[i]
        return sum
    }

}

open class ShasamblyOpcodeIncrStack(val stackSize: Int) : ShasamblyOpcode {
    init {
        if(stackSize !in 0..UShort.MAX_VALUE.toInt()) throw IllegalArgumentException("Stack size must not be bigger then ${UShort.MAX_VALUE} and not below 0, but is $stackSize")
    }

    override val size get() = 3
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(Opcodes.INCR_STACK, *stackSize.toUShort().toBytes())
    }
}

open class ShasamblyOpcodeDecrStack : ShasamblyOpcode {
    override val size get() = 1
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(Opcodes.DECR_STACK)
    }
}

open class ShasamblyOpcodeJumpStatic(val address: Int) : ShasamblyOpcode {
    init {
        if(address < 0) throw IllegalArgumentException("Jump address must not be smaller than 0 (is $address)")
    }

    override val size: Int get() = 5
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(Opcodes.JUMP_STATIC, *address.toBytes())
    }
}

open class ShasamblyOpcodeJumpStaticToIndex(val index: Int): ShasamblyOpcodeJumpStatic(0) {
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        val address = gen.positionOfIndex(index)
        return byteArrayOf(Opcodes.JUMP_STATIC, *address.toBytes())
    }
}

open class ShasamblyOpcodeJumpStaticToRelativeIndex(val relativeIndex: Int): ShasamblyOpcodeJumpStatic(0) {
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        val index = gen.indexOf(this) + relativeIndex
        val address = gen.positionOfIndex(index)
        return byteArrayOf(Opcodes.JUMP_STATIC, *address.toBytes())
    }
}

open class ShasamblyOpcodeJumpDynamic : ShasamblyOpcode {
    override val size: Int get() = 1
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(Opcodes.JUMP_DYNAMIC)
    }
}

open class ShasamblyOpcodeJumpIf(val address: Int) : ShasamblyOpcode {
    init {
        if(address < 0) throw IllegalArgumentException("Jump address must not be smaller than 0 (is $address)")
    }

    override val size: Int get() = 5
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(Opcodes.JUMP_IF, *address.toBytes())
    }
}

open class ShasamblyOpcodeJumpIfToIndex(val index: Int): ShasamblyOpcodeJumpIf(0) {
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        val address = gen.positionOfIndex(index)
        return byteArrayOf(Opcodes.JUMP_IF, *address.toBytes())
    }
}

open class ShasamblyOpcodeJumpIfToRelativeIndex(val relativeIndex: Int): ShasamblyOpcodeJumpIf(0) {
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        val index = gen.indexOf(this) + relativeIndex
        val address = gen.positionOfIndex(index)
        return byteArrayOf(Opcodes.JUMP_IF, *address.toBytes())
    }
}

open class ShasamblyLateInitOpcode(override val size: Int) : ShasamblyOpcode {
    lateinit var opcode: ShasamblyOpcode
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return opcode.generate(gen)
    }
    fun init(opcode: ShasamblyOpcode) {
        this.opcode = opcode
    }
}

open class ShasamblyOpcodeInvokeNative(val address: Short, val args: ByteArray = byteArrayOf()) : ShasamblyOpcode {

    init {
        if(address < 0) throw IllegalArgumentException("Jump address must not be smaller than 0x0 (is 0x${address.toBytes().toHexString()})")
    }

    override val size: Int = 3 + args.size
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(Opcodes.INVOKE_NATIVE, *address.toBytes(), *args)
    }
}

abstract class ShasamblyOpcodeGetLocal(val opcode: Byte, val address: Int): ShasamblyOpcode {

    init {
        if(address !in 0..UShort.MAX_VALUE.toInt())
            throw IllegalArgumentException("Address must not be bigger then 0x${UShort.MAX_VALUE.toBytes().toHexString()}" +
                    " and not below 0x0, but is 0x${address.toBytes().toHexString()}")
    }

    override val size: Int get() = 3
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(opcode, *address.toUShort().toBytes())
    }

}

open class ShasamblyOpcodeBGetLocal(address: Int): ShasamblyOpcodeGetLocal(Opcodes.B_GET_LOCAL, address)
open class ShasamblyOpcodeSGetLocal(address: Int): ShasamblyOpcodeGetLocal(Opcodes.S_GET_LOCAL, address)
open class ShasamblyOpcodeIGetLocal(address: Int): ShasamblyOpcodeGetLocal(Opcodes.I_GET_LOCAL, address)
open class ShasamblyOpcodeLGetLocal(address: Int): ShasamblyOpcodeGetLocal(Opcodes.L_GET_LOCAL, address)

@Deprecated("Replace with ShasamblyOpcodeIGetLocal")
typealias ShasamblyOpcodeFGetLocal = ShasamblyOpcodeIGetLocal

@Deprecated("Replace with ShasamblyOpcodeLGetLocal")
typealias ShasamblyOpcodeDGetLocal = ShasamblyOpcodeLGetLocal

abstract class ShasamblyOpcodeStoreLocal(val opcode: Byte, val address: Int): ShasamblyOpcode {

    init {
        if(address !in 0..UShort.MAX_VALUE.toInt())
            throw IllegalArgumentException("Address must not be bigger then 0x${UShort.MAX_VALUE.toBytes().toHexString()}" +
                    " and not below 0x0, but is 0x${address.toBytes().toHexString()}")
    }

    override val size: Int get() = 3
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(opcode, *address.toUShort().toBytes())
    }

}

open class ShasamblyOpcodeBStoreLocal(address: Int): ShasamblyOpcodeStoreLocal(Opcodes.B_STORE_LOCAL, address)
open class ShasamblyOpcodeSStoreLocal(address: Int): ShasamblyOpcodeStoreLocal(Opcodes.S_STORE_LOCAL, address)
open class ShasamblyOpcodeIStoreLocal(address: Int): ShasamblyOpcodeStoreLocal(Opcodes.I_STORE_LOCAL, address)
open class ShasamblyOpcodeLStoreLocal(address: Int): ShasamblyOpcodeStoreLocal(Opcodes.L_STORE_LOCAL, address)

@Deprecated("Replace with ShasamblyOpcodeIStoreLocal")
typealias ShasamblyOpcodeFStoreLocal = ShasamblyOpcodeIStoreLocal

@Deprecated("Replace with ShasamblyOpcodeLStoresLocal")
typealias ShasamblyOpcodeDStoreLocal = ShasamblyOpcodeLStoreLocal

open class ShasamblyOpcodeBPush(val byte: Byte) : ShasamblyOpcode {

    constructor(bool: Boolean) : this(if(bool) 0x01 else 0x00)

    override val size: Int
        get() = 2
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(Opcodes.B_PUSH, byte)
    }

}

open class ShasamblyOpcodeSPush(val short: Short) : ShasamblyOpcode {

    override val size: Int
        get() = 3
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(Opcodes.S_PUSH, *short.toBytes())
    }

}

open class ShasamblyOpcodeIPush(val int: Int) : ShasamblyOpcode {

    constructor(float: Float) : this(float.toBits())

    override val size: Int
        get() = 5
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(Opcodes.I_PUSH, *int.toBytes())
    }

}

open class ShasamblyOpcodeLPush(val long: Long) : ShasamblyOpcode {

    constructor(double: Double) : this(double.toBits())

    override val size: Int
        get() = 9
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(Opcodes.L_PUSH, *long.toBytes())
    }
}

@Deprecated("Use ShasamblyOpcodeIPush instead")
typealias ShasamblyOpcodeFPush = ShasamblyOpcodeIPush

@Deprecated("Use ShasamblyOpcodeLPush instead")
typealias ShasamblyOpcodeDPush = ShasamblyOpcodeLPush

abstract class ShasamblyOperationOpcode(val opcode: Byte) : ShasamblyOpcode {
    override val size: Int get() = 1
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(opcode)
    }
}

class ShasamblyOpcodeBAdd : ShasamblyOperationOpcode(Opcodes.B_ADD)
class ShasamblyOpcodeBSub : ShasamblyOperationOpcode(Opcodes.B_SUB)
class ShasamblyOpcodeBMul : ShasamblyOperationOpcode(Opcodes.B_MUL)
class ShasamblyOpcodeBDiv : ShasamblyOperationOpcode(Opcodes.B_DIV)
class ShasamblyOpcodeBMod : ShasamblyOperationOpcode(Opcodes.B_MOD)

class ShasamblyOpcodeSAdd : ShasamblyOperationOpcode(Opcodes.S_ADD)
class ShasamblyOpcodeSSub : ShasamblyOperationOpcode(Opcodes.S_SUB)
class ShasamblyOpcodeSMul : ShasamblyOperationOpcode(Opcodes.S_MUL)
class ShasamblyOpcodeSDiv : ShasamblyOperationOpcode(Opcodes.S_DIV)
class ShasamblyOpcodeSMod : ShasamblyOperationOpcode(Opcodes.S_MOD)

class ShasamblyOpcodeIAdd : ShasamblyOperationOpcode(Opcodes.I_ADD)
class ShasamblyOpcodeISub : ShasamblyOperationOpcode(Opcodes.I_SUB)
class ShasamblyOpcodeIMul : ShasamblyOperationOpcode(Opcodes.I_MUL)
class ShasamblyOpcodeIDiv : ShasamblyOperationOpcode(Opcodes.I_DIV)
class ShasamblyOpcodeIMod : ShasamblyOperationOpcode(Opcodes.I_MOD)

class ShasamblyOpcodeLAdd : ShasamblyOperationOpcode(Opcodes.L_ADD)
class ShasamblyOpcodeLSub : ShasamblyOperationOpcode(Opcodes.L_SUB)
class ShasamblyOpcodeLMul : ShasamblyOperationOpcode(Opcodes.L_MUL)
class ShasamblyOpcodeLDiv : ShasamblyOperationOpcode(Opcodes.L_DIV)
class ShasamblyOpcodeLMod : ShasamblyOperationOpcode(Opcodes.L_MOD)

class ShasamblyOpcodeFAdd : ShasamblyOperationOpcode(Opcodes.F_ADD)
class ShasamblyOpcodeFSub : ShasamblyOperationOpcode(Opcodes.F_SUB)
class ShasamblyOpcodeFMul : ShasamblyOperationOpcode(Opcodes.F_MUL)
class ShasamblyOpcodeFDiv : ShasamblyOperationOpcode(Opcodes.F_DIV)
class ShasamblyOpcodeFMod : ShasamblyOperationOpcode(Opcodes.F_MOD)

class ShasamblyOpcodeDAdd : ShasamblyOperationOpcode(Opcodes.D_ADD)
class ShasamblyOpcodeDSub : ShasamblyOperationOpcode(Opcodes.D_SUB)
class ShasamblyOpcodeDMul : ShasamblyOperationOpcode(Opcodes.D_MUL)
class ShasamblyOpcodeDDiv : ShasamblyOperationOpcode(Opcodes.D_DIV)
class ShasamblyOpcodeDMod : ShasamblyOperationOpcode(Opcodes.D_MOD)

class ShasamblyOpcodeBEq : ShasamblyOperationOpcode(Opcodes.B_EQ)
class ShasamblyOpcodeSEq : ShasamblyOperationOpcode(Opcodes.S_EQ)
class ShasamblyOpcodeIEq : ShasamblyOperationOpcode(Opcodes.I_EQ)
class ShasamblyOpcodeLEq : ShasamblyOperationOpcode(Opcodes.L_EQ)
class ShasamblyOpcodeFEq : ShasamblyOperationOpcode(Opcodes.F_EQ)
class ShasamblyOpcodeDEq : ShasamblyOperationOpcode(Opcodes.D_EQ)

class ShasamblyOpcodeBBigger : ShasamblyOperationOpcode(Opcodes.B_BIGGER)
class ShasamblyOpcodeSBigger : ShasamblyOperationOpcode(Opcodes.S_BIGGER)
class ShasamblyOpcodeIBigger : ShasamblyOperationOpcode(Opcodes.I_BIGGER)
class ShasamblyOpcodeLBigger : ShasamblyOperationOpcode(Opcodes.L_BIGGER)
class ShasamblyOpcodeFBigger : ShasamblyOperationOpcode(Opcodes.F_BIGGER)
class ShasamblyOpcodeDBigger : ShasamblyOperationOpcode(Opcodes.D_BIGGER)

class ShasamblyOpcodeBSmaller : ShasamblyOperationOpcode(Opcodes.B_SMALLER)
class ShasamblyOpcodeSSmaller : ShasamblyOperationOpcode(Opcodes.S_SMALLER)
class ShasamblyOpcodeISmaller : ShasamblyOperationOpcode(Opcodes.I_SMALLER)
class ShasamblyOpcodeLSmaller : ShasamblyOperationOpcode(Opcodes.L_SMALLER)
class ShasamblyOpcodeFSmaller : ShasamblyOperationOpcode(Opcodes.F_SMALLER)
class ShasamblyOpcodeDSmaller : ShasamblyOperationOpcode(Opcodes.D_SMALLER)

class ShasamblyOpcodeBBiggerEq : ShasamblyOperationOpcode(Opcodes.B_BIGGER_EQ)
class ShasamblyOpcodeSBiggerEq : ShasamblyOperationOpcode(Opcodes.S_BIGGER_EQ)
class ShasamblyOpcodeIBiggerEq : ShasamblyOperationOpcode(Opcodes.I_BIGGER_EQ)
class ShasamblyOpcodeLBiggerEq : ShasamblyOperationOpcode(Opcodes.L_BIGGER_EQ)
class ShasamblyOpcodeFBiggerEq : ShasamblyOperationOpcode(Opcodes.F_BIGGER_EQ)
class ShasamblyOpcodeDBiggerEq : ShasamblyOperationOpcode(Opcodes.D_BIGGER_EQ)

class ShasamblyOpcodeBSmallerEq : ShasamblyOperationOpcode(Opcodes.B_SMALLER_EQ)
class ShasamblyOpcodeSSmallerEq : ShasamblyOperationOpcode(Opcodes.S_SMALLER_EQ)
class ShasamblyOpcodeISmallerEq : ShasamblyOperationOpcode(Opcodes.I_SMALLER_EQ)
class ShasamblyOpcodeLSmallerEq : ShasamblyOperationOpcode(Opcodes.L_SMALLER_EQ)
class ShasamblyOpcodeFSmallerEq : ShasamblyOperationOpcode(Opcodes.F_SMALLER_EQ)
class ShasamblyOpcodeDSmallerEq : ShasamblyOperationOpcode(Opcodes.D_SMALLER_EQ)
class ShasamblyOpcodeBoolNot : ShasamblyOperationOpcode(Opcodes.BOOL_NOT)

abstract class ShasamblyGetGlobalOpcode(val opcode: Byte, val address: Int) : ShasamblyOpcode {
    override val size: Int get() = 5
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(opcode, *address.toBytes())
    }
}

class ShasasamblyOpcodeBGetGlobal(address: Int) : ShasamblyGetGlobalOpcode(Opcodes.B_GET_GLOBAL, address)
class ShasasamblyOpcodeSGetGlobal(address: Int) : ShasamblyGetGlobalOpcode(Opcodes.S_GET_GLOBAL, address)
class ShasasamblyOpcodeIGetGlobal(address: Int) : ShasamblyGetGlobalOpcode(Opcodes.I_GET_GLOBAL, address)
class ShasasamblyOpcodeLGetGlobal(address: Int) : ShasamblyGetGlobalOpcode(Opcodes.L_GET_GLOBAL, address)

abstract class ShasamblyGetGlobalDynamicOpcode(val opcode: Byte) : ShasamblyOpcode {
    override val size: Int get() = 1
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(opcode)
    }
}

class ShasasamblyOpcodeBGetGlobalDynamic : ShasamblyGetGlobalDynamicOpcode(Opcodes.B_GET_GLOBAL_DYNAMIC)
class ShasasamblyOpcodeSGetGlobalDynamic : ShasamblyGetGlobalDynamicOpcode(Opcodes.S_GET_GLOBAL_DYNAMIC)
class ShasasamblyOpcodeIGetGlobalDynamic : ShasamblyGetGlobalDynamicOpcode(Opcodes.I_GET_GLOBAL_DYNAMIC)
class ShasasamblyOpcodeLGetGlobalDynamic : ShasamblyGetGlobalDynamicOpcode(Opcodes.L_GET_GLOBAL_DYNAMIC)