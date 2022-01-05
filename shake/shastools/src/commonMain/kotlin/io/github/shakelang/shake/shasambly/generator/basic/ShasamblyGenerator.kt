@file: Suppress("unused")
package io.github.shakelang.shake.shasambly.generator.basic

import io.github.shakelang.parseutils.bytes.toBytes
import io.github.shakelang.parseutils.bytes.toHexString
import io.github.shakelang.shake.shasambly.interpreter.Opcodes

/**
 * This represents one shasambly opcode in the generation. It will be converted to it's bytes by
 * the [ShasamblyGenerator] using the #[generate] function
 */
interface ShasamblyOpcode {

    /**
     * Returns the size of the [ShasamblyOpcode] in bytes
     * This is required by by the generate function of some
     * opcodes because they have to resolve opcode indexes to
     * byte indexes
     * This value should be constant and not depend on other
     * opcodes!
     */
    val size: Int

    /**
     * Generates the ShasamblyOpcode
     * Return's an array of it's bytes
     */
    fun generate(gen: ShasamblyGenerator): ByteArray

}

/**
 * A generator for Shasambly. It has a list of all it's [ShasamblyOpcode]s and will
 * convert all of them into a single byte array using #[generate]
 */
open class ShasamblyGenerator(contents: MutableList<ShasamblyOpcode>) : MutableList<ShasamblyOpcode> by contents {

    constructor(contents: Array<ShasamblyOpcode>) : this(contents.toMutableList())

    /**
     * A cache variable for the sizes of each opcode
     * This is initialized once at the beginning of the
     * #[generate] method, so the sizes are not calculated
     * again every time they are used.
     */
    private var sizesCache: List<Int>? = null

    /**
     * The sizes of all the opcodes
     * (If a #[sizesCache] is given return it, if not
     * generate all the sizes using [map])
     */
    val sizes: List<Int> get() = sizesCache ?: map { it.size }

    /**
     * Generates all content [Opcodes] into a single [ByteArray]
     */
    fun generate(): ByteArray {
        if(sizesCache != null) throw IllegalStateException("Generate already running")
        sizesCache = sizes

        val bytes = flatMap { it.generate(this).toList() }.toByteArray()

        sizesCache = null
        return bytes
    }

    /**
     * Resolve the position of a given index
     */
    fun positionOfIndex(index: Int): Int {
        val sizes = sizes
        var sum = 0
        if(index > sizes.size) throw IllegalArgumentException("Index to big, must be smaller than ${sizes.size} (is $index)")
        for(i in 0 until index) sum += sizes[i]
        return sum
    }

}

/**
 * Generator for opcode [Opcodes.INCR_STACK]
 */
open class ShasamblyOpcodeIncrStack(val stackSize: Int) : ShasamblyOpcode {
    init {
        if(stackSize !in 0..UShort.MAX_VALUE.toInt()) throw IllegalArgumentException("Stack size must not be bigger then ${UShort.MAX_VALUE} and not below 0, but is $stackSize")
    }

    override val size get() = 3
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(Opcodes.INCR_STACK, *stackSize.toUShort().toBytes())
    }
}

/**
 * Generator for opcode [Opcodes.DECR_STACK]
 */
open class ShasamblyOpcodeDecrStack : ShasamblyOpcode {
    override val size get() = 1
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(Opcodes.DECR_STACK)
    }
}

/**
 * Generator for opcode [Opcodes.JUMP_STATIC]
 *
 * @see ShasamblyOpcodeJumpStaticToIndex
 * @see ShasamblyOpcodeJumpStaticToRelativeIndex
 */
open class ShasamblyOpcodeJumpStatic(val address: Int) : ShasamblyOpcode {
    init {
        if(address < 0) throw IllegalArgumentException("Jump address must not be smaller than 0 (is $address)")
    }

    override val size: Int get() = 5
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(Opcodes.JUMP_STATIC, *address.toBytes())
    }
}

/**
 * Generator for opcode [Opcodes.JUMP_STATIC], but the argument is the index of an opcode
 * and is recalculated in the #[generate] function
 *
 * @see ShasamblyOpcodeJumpStatic
 * @see ShasamblyOpcodeJumpStaticToRelativeIndex
 */
open class ShasamblyOpcodeJumpStaticToIndex(val index: Int): ShasamblyOpcodeJumpStatic(0) {
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        val address = gen.positionOfIndex(index)
        return byteArrayOf(Opcodes.JUMP_STATIC, *address.toBytes())
    }
}

/**
 * Generator for opcode [Opcodes.JUMP_STATIC], but the argument is the index relative index of
 * an opcode and is recalculated in the #[generate] function
 *
 * You can use for Example
 * ```kotlin
 * ShasamblyOpcodeJumpStaticToRelativeIndex(-2)
 * ```
 * to jump to the previous index or
 * ```kotlin
 * ShasamblyOpcodeJumpStaticToRelativeIndex(1)
 * ```
 * to skip the next opcode
 *
 * @see ShasamblyOpcodeJumpStatic
 * @see ShasamblyOpcodeJumpStaticToIndex
 */
open class ShasamblyOpcodeJumpStaticToRelativeIndex(val relativeIndex: Int): ShasamblyOpcodeJumpStatic(0) {
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        val index = gen.indexOf(this) + relativeIndex
        val address = gen.positionOfIndex(index)
        return byteArrayOf(Opcodes.JUMP_STATIC, *address.toBytes())
    }
}

/**
 * Generator for opcode [Opcodes.JUMP_DYNAMIC]. Jumps dynamically to a byte index (not opcode-index!!!)
 * The jump target is taken from the top element on the stack
 */
open class ShasamblyOpcodeJumpDynamic : ShasamblyOpcode {
    override val size: Int get() = 1
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(Opcodes.JUMP_DYNAMIC)
    }
}

/**
 * Generator for opcode [Opcodes.JUMP_IF]. Jumps to a byte index (not opcode-index!!!) if
 * the byte (or boolean) on top of the stack is true (so it must not be 0x00)
 *
 * @see ShasamblyOpcodeJumpIfToIndex
 * @see ShasamblyOpcodeJumpIfToRelativeIndex
 */
open class ShasamblyOpcodeJumpIf(val address: Int) : ShasamblyOpcode {
    init {
        if(address < 0) throw IllegalArgumentException("Jump address must not be smaller than 0 (is $address)")
    }

    override val size: Int get() = 5
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(Opcodes.JUMP_IF, *address.toBytes())
    }
}

/**
 * Generator for opcode [Opcodes.JUMP_IF]. Jumps to an opcode index if
 * the byte (or boolean) on top of the stack is true (so it must not be 0x00)
 *
 * @see ShasamblyOpcodeJumpIf
 * @see ShasamblyOpcodeJumpIfToRelativeIndex
 */
open class ShasamblyOpcodeJumpIfToIndex(val index: Int): ShasamblyOpcodeJumpIf(0) {
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        val address = gen.positionOfIndex(index)
        return byteArrayOf(Opcodes.JUMP_IF, *address.toBytes())
    }
}

/**
 * Generator for opcode [Opcodes.JUMP_IF]. Jumps to a relative opcode index if
 * the byte (or boolean) on top of the stack is true (so it must not be 0x00)
 *
 * You can use for Example
 * ```kotlin
 * ShasamblyOpcodeJumpIfToRelativeIndex(-2)
 * ```
 * to jump to the previous index or
 * ```kotlin
 * ShasamblyOpcodeJumpIfToRelativeIndex(1)
 * ```
 * to skip the next opcode
 *
 * @see ShasamblyOpcodeJumpIfToIndex
 * @see ShasamblyOpcodeJumpIfToRelativeIndex
 */
open class ShasamblyOpcodeJumpIfToRelativeIndex(val relativeIndex: Int): ShasamblyOpcodeJumpIf(0) {
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        val index = gen.indexOf(this) + relativeIndex
        val address = gen.positionOfIndex(index)
        return byteArrayOf(Opcodes.JUMP_IF, *address.toBytes())
    }
}

/**
 * Generator for opcode [Opcodes.GLOB_ADDR]
 * Converts a local address into a global address.
 * Leaves the global address on top of the stack
 */
open class ShasamblyOpcodeGlobAddr(val address: Int) : ShasamblyOpcode {
    init {
        if(address !in 0..UShort.MAX_VALUE.toInt())
            throw IllegalArgumentException("Address must not be bigger then 0x${UShort.MAX_VALUE.toBytes().toHexString()}" +
                    " and not below 0x0, but is 0x${address.toBytes().toHexString()}")
    }

    override val size: Int get() = 3
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(Opcodes.GLOB_ADDR, *address.toUShort().toBytes())
    }

}

/**
 * Just a tool to help you generate stuff
 * This opcode can be replaced later by a working one and
 * for the time just works as a dummy opcode
 * This is NOT an opcode that can be generated, it just redirects the
 * generation to a given opcode and will throw an error if it did not
 * receive an opcode to generate until generation (when the generation
 * function is called)
 * Only use if it is needed, it is much better to provide the opcodes
 * directly than to use a placeholder opcode
 */
open class ShasamblyLateinitOpcode(override val size: Int) : ShasamblyOpcode {
    var opcode: ShasamblyOpcode? = null
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        if(opcode == null) throw Error("Lateinit opcode is not initialized")
        return opcode!!.generate(gen)
    }
    fun init(opcode: ShasamblyOpcode) {
        this.opcode = opcode
    }
}

/**
 * Generator for opcode [Opcodes.INVOKE_NATIVE]
 * Invokes a shasambly native function
 * The ids are provided by [io.github.shakelang.shake.shasambly.interpreter.natives.Natives]
 *
 * @see io.github.shakelang.shake.shasambly.interpreter.natives.Natives
 */
open class ShasamblyOpcodeInvokeNative(val address: Short, val args: ByteArray = byteArrayOf()) : ShasamblyOpcode {

    init {
        if(address < 0) throw IllegalArgumentException("Jump address must not be smaller than 0x0 (is 0x${address.toBytes().toHexString()})")
    }

    override val size: Int = 3 + args.size
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(Opcodes.INVOKE_NATIVE, *address.toBytes(), *args)
    }
}

/**
 * An abstract opcode for getting locals
 * This is not a final opcode. It is just the shared code between all
 * get local opcodes
 *
 * @see ShasamblyOpcodeBGetLocal
 * @see ShasamblyOpcodeSGetLocal
 * @see ShasamblyOpcodeIGetLocal
 * @see ShasamblyOpcodeLGetLocal
 */
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

/**
 * Generator for opcode [Opcodes.B_GET_LOCAL]
 * Gets a byte from the local variables at a given local address and puts it on top of the stack
 */
open class ShasamblyOpcodeBGetLocal(address: Int): ShasamblyOpcodeGetLocal(Opcodes.B_GET_LOCAL, address)

/**
 * Generator for opcode [Opcodes.S_GET_LOCAL]
 * Gets a short from the local variables at a given local address and puts it on top of the stack
 */
open class ShasamblyOpcodeSGetLocal(address: Int): ShasamblyOpcodeGetLocal(Opcodes.S_GET_LOCAL, address)

/**
 * Generator for opcode [Opcodes.I_GET_LOCAL]
 * Gets a short from the local variables at a given local address and puts it on top of the stack
 */
open class ShasamblyOpcodeIGetLocal(address: Int): ShasamblyOpcodeGetLocal(Opcodes.I_GET_LOCAL, address)

/**
 * Generator for opcode [Opcodes.L_GET_LOCAL]
 * Gets a long from the local variables at a given local address and puts it on top of the stack
 */
open class ShasamblyOpcodeLGetLocal(address: Int): ShasamblyOpcodeGetLocal(Opcodes.L_GET_LOCAL, address)

/**
 * This is a typealias to [ShasamblyOpcodeIGetLocal]
 * Integers and floats both have 4 Bytes, so it is not necessary to have an own bytecode
 * for getting a float, you can just use the integer one
 *
 * @see ShasamblyOpcodeIGetLocal
 */
@Deprecated("Replace with ShasamblyOpcodeIGetLocal")
typealias ShasamblyOpcodeFGetLocal = ShasamblyOpcodeIGetLocal

/**
 * This is a typealias to [ShasamblyOpcodeLGetLocal]
 * Doubles and longs both have 8 Bytes, so it is not necessary to have an own bytecode
 * for getting a double, you can just use the long one
 *
 * @see ShasamblyOpcodeLGetLocal
 */
@Deprecated("Replace with ShasamblyOpcodeLGetLocal")
typealias ShasamblyOpcodeDGetLocal = ShasamblyOpcodeLGetLocal

/**
 * An abstract opcode for storing locals
 * This is not a final opcode. It is just the shared code between all
 * store-local-opcodes
 *
 * @see ShasamblyOpcodeBStoreLocal
 * @see ShasamblyOpcodeSStoreLocal
 * @see ShasamblyOpcodeIStoreLocal
 * @see ShasamblyOpcodeLStoreLocal
 */
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

/**
 * Generator for opcode [Opcodes.B_STORE_LOCAL]
 * Takes the top byte from the stack and puts it into the given local address
 */
open class ShasamblyOpcodeBStoreLocal(address: Int): ShasamblyOpcodeStoreLocal(Opcodes.B_STORE_LOCAL, address)

/**
 * Generator for opcode [Opcodes.S_STORE_LOCAL]
 * Takes the top short from the stack and puts it into the given local address
 */
open class ShasamblyOpcodeSStoreLocal(address: Int): ShasamblyOpcodeStoreLocal(Opcodes.S_STORE_LOCAL, address)

/**
 * Generator for opcode [Opcodes.I_STORE_LOCAL]
 * Takes the top integer from the stack and puts it into the given local address
 */
open class ShasamblyOpcodeIStoreLocal(address: Int): ShasamblyOpcodeStoreLocal(Opcodes.I_STORE_LOCAL, address)

/**
 * Generator for opcode [Opcodes.L_STORE_LOCAL]
 * Takes the top long from the stack and puts it into the given local address
 */
open class ShasamblyOpcodeLStoreLocal(address: Int): ShasamblyOpcodeStoreLocal(Opcodes.L_STORE_LOCAL, address)

/**
 * This is a typealias to [ShasamblyOpcodeIStoreLocal]
 * Integers and floats both have 4 Bytes, so it is not necessary to have an own bytecode
 * for storing a float, you can just use the integer one
 *
 * @see ShasamblyOpcodeIStoreLocal
 */
@Deprecated("Replace with ShasamblyOpcodeIStoreLocal")
typealias ShasamblyOpcodeFStoreLocal = ShasamblyOpcodeIStoreLocal

/**
 * This is a typealias to [ShasamblyOpcodeLStoreLocal]
 * Longs and doubles both have 8 Bytes, so it is not necessary to have an own bytecode
 * for storing a double, you can just use the long one
 *
 * @see ShasamblyOpcodeLStoreLocal
 */
@Deprecated("Replace with ShasamblyOpcodeLStoresLocal")
typealias ShasamblyOpcodeDStoreLocal = ShasamblyOpcodeLStoreLocal

/**
 * Generator for opcode [Opcodes.B_PUSH]
 * Pushes a byte on top of the stack
 */
open class ShasamblyOpcodeBPush(val byte: Byte) : ShasamblyOpcode {

    constructor(bool: Boolean) : this(if(bool) 0x01 else 0x00)

    override val size: Int
        get() = 2
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(Opcodes.B_PUSH, byte)
    }

}

/**
 * Generator for opcode [Opcodes.S_PUSH]
 * Pushes a short on top of the stack
 */
open class ShasamblyOpcodeSPush(val short: Short) : ShasamblyOpcode {

    override val size: Int
        get() = 3
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(Opcodes.S_PUSH, *short.toBytes())
    }

}

/**
 * Generator for opcode [Opcodes.I_PUSH]
 * Pushes an integer on top of the stack
 */
open class ShasamblyOpcodeIPush(val int: Int) : ShasamblyOpcode {

    constructor(float: Float) : this(float.toBits())

    override val size: Int
        get() = 5

    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(Opcodes.I_PUSH, *int.toBytes())
    }

}

/**
 * Generator for opcode [Opcodes.L_PUSH]
 * Pushes a long on top of the stack
 */
open class ShasamblyOpcodeLPush(val long: Long) : ShasamblyOpcode {

    constructor(double: Double) : this(double.toBits())

    override val size: Int
        get() = 9
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(Opcodes.L_PUSH, *long.toBytes())
    }
}

/**
 * This is a typealias to [ShasamblyOpcodeIPush]
 * Integers and floats both have 8 Bytes, so it is not necessary to have an own bytecode
 * for pushing a float, you can just use the integer one
 *
 * @see ShasamblyOpcodeLPush
 */
@Deprecated("Use ShasamblyOpcodeIPush instead")
typealias ShasamblyOpcodeFPush = ShasamblyOpcodeIPush

/**
 * This is a typealias to [ShasamblyOpcodeLPush]
 * Longs and doubles both have 8 Bytes, so it is not necessary to have an own bytecode
 * for pushing a double, you can just use the long one
 *
 * @see ShasamblyOpcodeLPush
 */
@Deprecated("Use ShasamblyOpcodeLPush instead")
typealias ShasamblyOpcodeDPush = ShasamblyOpcodeLPush

/**
 * An abstract opcode for operations
 * This is not a final opcode. It is just the shared code between all
 * operation opcodes
 */
abstract class ShasamblyOperationOpcode(val opcode: Byte) : ShasamblyOpcode {
    override val size: Int get() = 1
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(opcode)
    }
}

/**
 * Generator for opcode [Opcodes.B_ADD]
 * Adds the top two bytes from the stack
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeBAdd : ShasamblyOperationOpcode(Opcodes.B_ADD)

/**
 * Generator for opcode [Opcodes.B_SUB]
 * Subtracts the top two bytes from the stack (it subtracts the top one from the bottom one)
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeBSub : ShasamblyOperationOpcode(Opcodes.B_SUB)

/**
 * Generator for opcode [Opcodes.B_MUL]
 * Multiplies the top two bytes from the stack
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeBMul : ShasamblyOperationOpcode(Opcodes.B_MUL)

/**
 * Generator for opcode [Opcodes.B_DIV]
 * Divides the top two bytes from the stack (it divides the top one by the bottom one)
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeBDiv : ShasamblyOperationOpcode(Opcodes.B_DIV)

/**
 * Generator for opcode [Opcodes.B_MOD]
 * Divides the top two bytes from the stack (it divides the top one by the bottom one)
 * and puts the modulo result on top of the stack
 */
class ShasamblyOpcodeBMod : ShasamblyOperationOpcode(Opcodes.B_MOD)


/**
 * Generator for opcode [Opcodes.S_ADD]
 * Adds the top two shorts from the stack
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeSAdd : ShasamblyOperationOpcode(Opcodes.S_ADD)

/**
 * Generator for opcode [Opcodes.S_SUB]
 * Subtracts the top two shorts from the stack (it subtracts the top one from the bottom one)
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeSSub : ShasamblyOperationOpcode(Opcodes.S_SUB)

/**
 * Generator for opcode [Opcodes.S_MUL]
 * Multiplies the top two shorts from the stack
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeSMul : ShasamblyOperationOpcode(Opcodes.S_MUL)

/**
 * Generator for opcode [Opcodes.S_DIV]
 * Divides the top two shorts from the stack (it divides the top one by the bottom one)
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeSDiv : ShasamblyOperationOpcode(Opcodes.S_DIV)

/**
 * Generator for opcode [Opcodes.S_MOD]
 * Divides the top two shorts from the stack (it divides the top one by the bottom one)
 * and puts the modulo result on top of the stack
 */
class ShasamblyOpcodeSMod : ShasamblyOperationOpcode(Opcodes.S_MOD)


/**
 * Generator for opcode [Opcodes.I_ADD]
 * Adds the top two integers from the stack
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeIAdd : ShasamblyOperationOpcode(Opcodes.I_ADD)

/**
 * Generator for opcode [Opcodes.I_SUB]
 * Subtracts the top two integers from the stack (it subtracts the top one from the bottom one)
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeISub : ShasamblyOperationOpcode(Opcodes.I_SUB)

/**
 * Generator for opcode [Opcodes.I_MUL]
 * Multiplies the top two integers from the stack
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeIMul : ShasamblyOperationOpcode(Opcodes.I_MUL)

/**
 * Generator for opcode [Opcodes.I_DIV]
 * Divides the top two integers from the stack (it divides the top one by the bottom one)
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeIDiv : ShasamblyOperationOpcode(Opcodes.I_DIV)

/**
 * Generator for opcode [Opcodes.I_MOD]
 * Divides the top two integers from the stack (it divides the top one by the bottom one)
 * and puts the modulo result on top of the stack
 */
class ShasamblyOpcodeIMod : ShasamblyOperationOpcode(Opcodes.I_MOD)


/**
 * Generator for opcode [Opcodes.L_ADD]
 * Adds the top two longs from the stack
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeLAdd : ShasamblyOperationOpcode(Opcodes.L_ADD)

/**
 * Generator for opcode [Opcodes.L_SUB]
 * Subtracts the top two longs from the stack (it subtracts the top one from the bottom one)
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeLSub : ShasamblyOperationOpcode(Opcodes.L_SUB)

/**
 * Generator for opcode [Opcodes.L_MUL]
 * Multiplies the top two longs from the stack
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeLMul : ShasamblyOperationOpcode(Opcodes.L_MUL)

/**
 * Generator for opcode [Opcodes.L_DIV]
 * Divides the top two longs from the stack (it divides the top one by the bottom one)
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeLDiv : ShasamblyOperationOpcode(Opcodes.L_DIV)

/**
 * Generator for opcode [Opcodes.L_MOD]
 * Divides the top two longs from the stack (it divides the top one by the bottom one)
 * and puts the modulo result on top of the stack
 */
class ShasamblyOpcodeLMod : ShasamblyOperationOpcode(Opcodes.L_MOD)


/**
 * Generator for opcode [Opcodes.F_ADD]
 * Adds the top two floats from the stack
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeFAdd : ShasamblyOperationOpcode(Opcodes.F_ADD)

/**
 * Generator for opcode [Opcodes.F_SUB]
 * Subtracts the top two floats from the stack (it subtracts the top one from the bottom one)
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeFSub : ShasamblyOperationOpcode(Opcodes.F_SUB)

/**
 * Generator for opcode [Opcodes.F_MUL]
 * Multiplies the top two floats from the stack
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeFMul : ShasamblyOperationOpcode(Opcodes.F_MUL)

/**
 * Generator for opcode [Opcodes.F_DIV]
 * Divides the top two floats from the stack (it divides the top one by the bottom one)
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeFDiv : ShasamblyOperationOpcode(Opcodes.F_DIV)

/**
 * Generator for opcode [Opcodes.F_MOD]
 * Divides the top two floats from the stack (it divides the top one by the bottom one)
 * and puts the modulo result on top of the stack
 */
class ShasamblyOpcodeFMod : ShasamblyOperationOpcode(Opcodes.F_MOD)


/**
 * Generator for opcode [Opcodes.D_ADD]
 * Adds the top two doubles from the stack
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeDAdd : ShasamblyOperationOpcode(Opcodes.D_ADD)

/**
 * Generator for opcode [Opcodes.D_SUB]
 * Subtracts the top two doubles from the stack (it subtracts the top one from the bottom one)
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeDSub : ShasamblyOperationOpcode(Opcodes.D_SUB)

/**
 * Generator for opcode [Opcodes.D_MUL]
 * Multiplies the top two doubles from the stack
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeDMul : ShasamblyOperationOpcode(Opcodes.D_MUL)

/**
 * Generator for opcode [Opcodes.D_DIV]
 * Divides the top two doubles from the stack (it divides the top one by the bottom one)
 * and puts the result on top of the stack
 */
class ShasamblyOpcodeDDiv : ShasamblyOperationOpcode(Opcodes.D_DIV)

/**
 * Generator for opcode [Opcodes.D_MOD]
 * Divides the top two doubles from the stack (it divides the top one by the bottom one)
 * and puts the modulo result on top of the stack
 */
class ShasamblyOpcodeDMod : ShasamblyOperationOpcode(Opcodes.D_MOD)


/**
 * Generator for opcode [Opcodes.B_EQ]
 * Checks if the top two bytes are equal and puts the result on top of the stack
 */
class ShasamblyOpcodeBEq : ShasamblyOperationOpcode(Opcodes.B_EQ)

/**
 * Generator for opcode [Opcodes.S_EQ]
 * Checks if the top two shorts are equal and puts the result on top of the stack
 */
class ShasamblyOpcodeSEq : ShasamblyOperationOpcode(Opcodes.S_EQ)

/**
 * Generator for opcode [Opcodes.I_EQ]
 * Checks if the top two integers are equal and puts the result on top of the stack
 */
class ShasamblyOpcodeIEq : ShasamblyOperationOpcode(Opcodes.I_EQ)

/**
 * Generator for opcode [Opcodes.L_EQ]
 * Checks if the top two longs are equal and puts the result on top of the stack
 */
class ShasamblyOpcodeLEq : ShasamblyOperationOpcode(Opcodes.L_EQ)

/**
 * Generator for opcode [Opcodes.F_EQ]
 * Checks if the top two floats are equal and puts the result on top of the stack
 */
class ShasamblyOpcodeFEq : ShasamblyOperationOpcode(Opcodes.F_EQ)

/**
 * Generator for opcode [Opcodes.D_EQ]
 * Checks if the top two doubles are equal and puts the result on top of the stack
 */
class ShasamblyOpcodeDEq : ShasamblyOperationOpcode(Opcodes.D_EQ)


/**
 * Generator for opcode [Opcodes.B_BIGGER]
 * Checks if the second but top byte is bigger than the top byte and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeBBigger : ShasamblyOperationOpcode(Opcodes.B_BIGGER)

/**
 * Generator for opcode [Opcodes.S_BIGGER]
 * Checks if the second but top short is bigger than the top short and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeSBigger : ShasamblyOperationOpcode(Opcodes.S_BIGGER)

/**
 * Generator for opcode [Opcodes.I_BIGGER]
 * Checks if the second but top integer is bigger than the top integer and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeIBigger : ShasamblyOperationOpcode(Opcodes.I_BIGGER)

/**
 * Generator for opcode [Opcodes.L_BIGGER]
 * Checks if the second but top long is bigger than the top long and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeLBigger : ShasamblyOperationOpcode(Opcodes.L_BIGGER)

/**
 * Generator for opcode [Opcodes.F_BIGGER]
 * Checks if the second but top float is bigger than the top float and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeFBigger : ShasamblyOperationOpcode(Opcodes.F_BIGGER)

/**
 * Generator for opcode [Opcodes.D_BIGGER]
 * Checks if the second but top double is bigger than the top double and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeDBigger : ShasamblyOperationOpcode(Opcodes.D_BIGGER)


/**
 * Generator for opcode [Opcodes.B_SMALLER]
 * Checks if the second but top byte is smaller than the top byte and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeBSmaller : ShasamblyOperationOpcode(Opcodes.B_SMALLER)

/**
 * Generator for opcode [Opcodes.S_SMALLER]
 * Checks if the second but top short is smaller than the top short and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeSSmaller : ShasamblyOperationOpcode(Opcodes.S_SMALLER)

/**
 * Generator for opcode [Opcodes.I_SMALLER]
 * Checks if the second but top is smaller than the top integer and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeISmaller : ShasamblyOperationOpcode(Opcodes.I_SMALLER)

/**
 * Generator for opcode [Opcodes.L_SMALLER]
 * Checks if the second but top long is smaller than the top long and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeLSmaller : ShasamblyOperationOpcode(Opcodes.L_SMALLER)

/**
 * Generator for opcode [Opcodes.F_SMALLER]
 * Checks if the second but top float is smaller than the top float and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeFSmaller : ShasamblyOperationOpcode(Opcodes.F_SMALLER)

/**
 * Generator for opcode [Opcodes.D_SMALLER]
 * Checks if the second but top double is smaller than the top double and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeDSmaller : ShasamblyOperationOpcode(Opcodes.D_SMALLER)


/**
 * Generator for opcode [Opcodes.B_BIGGER_EQ]
 * Checks if the second but top byte is bigger or equal to the top byte and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeBBiggerEq : ShasamblyOperationOpcode(Opcodes.B_BIGGER_EQ)

/**
 * Generator for opcode [Opcodes.S_BIGGER_EQ]
 * Checks if the second but top short is bigger or equal to the top short and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeSBiggerEq : ShasamblyOperationOpcode(Opcodes.S_BIGGER_EQ)

/**
 * Generator for opcode [Opcodes.I_BIGGER_EQ]
 * Checks if the second but top integer is bigger or equal to the top integer and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeIBiggerEq : ShasamblyOperationOpcode(Opcodes.I_BIGGER_EQ)

/**
 * Generator for opcode [Opcodes.L_BIGGER_EQ]
 * Checks if the second but top long is bigger or equal to the top long and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeLBiggerEq : ShasamblyOperationOpcode(Opcodes.L_BIGGER_EQ)

/**
 * Generator for opcode [Opcodes.F_BIGGER_EQ]
 * Checks if the second but top float is bigger or equal to the top float and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeFBiggerEq : ShasamblyOperationOpcode(Opcodes.F_BIGGER_EQ)

/**
 * Generator for opcode [Opcodes.D_BIGGER_EQ]
 * Checks if the second but top double is bigger or equal to the top double and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeDBiggerEq : ShasamblyOperationOpcode(Opcodes.D_BIGGER_EQ)


/**
 * Generator for opcode [Opcodes.B_SMALLER_EQ]
 * Checks if the second but top byte is smaller or equal to the top byte and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeBSmallerEq : ShasamblyOperationOpcode(Opcodes.B_SMALLER_EQ)

/**
 * Generator for opcode [Opcodes.S_SMALLER_EQ]
 * Checks if the second but top short is smaller or equal to the top short and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeSSmallerEq : ShasamblyOperationOpcode(Opcodes.S_SMALLER_EQ)

/**
 * Generator for opcode [Opcodes.I_SMALLER_EQ]
 * Checks if the second but top integer is smaller or equal to the top integer and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeISmallerEq : ShasamblyOperationOpcode(Opcodes.I_SMALLER_EQ)

/**
 * Generator for opcode [Opcodes.L_SMALLER_EQ]
 * Checks if the second but top long is smaller or equal to the top long and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeLSmallerEq : ShasamblyOperationOpcode(Opcodes.L_SMALLER_EQ)

/**
 * Generator for opcode [Opcodes.F_SMALLER_EQ]
 * Checks if the second but top float is smaller or equal to the top float and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeFSmallerEq : ShasamblyOperationOpcode(Opcodes.F_SMALLER_EQ)

/**
 * Generator for opcode [Opcodes.D_SMALLER_EQ]
 * Checks if the second but top double is smaller or equal to the top double and puts the result
 * on top of the stack
 */
class ShasamblyOpcodeDSmallerEq : ShasamblyOperationOpcode(Opcodes.D_SMALLER_EQ)

/**
 * Generator for opcode [Opcodes.BOOL_NOT]
 * Takes the boolean value on top of the stack and puts the opposite onto it
 */
class ShasamblyOpcodeBoolNot : ShasamblyOperationOpcode(Opcodes.BOOL_NOT)


/**
 * An abstract opcode for getting globals with a static address
 * This is not a final opcode. It is just the shared code between all
 * get global opcodes
 *
 * @see ShasamblyOpcodeBGetGlobal
 * @see ShasamblyOpcodeSGetGlobal
 * @see ShasamblyOpcodeIGetGlobal
 * @see ShasamblyOpcodeLGetGlobal
 */
abstract class ShasamblyGetGlobalOpcode(val opcode: Byte, val address: Int) : ShasamblyOpcode {
    override val size: Int get() = 5
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(opcode, *address.toBytes())
    }
}

/**
 * Generator for opcode [Opcodes.B_GET_GLOBAL]
 * Put the byte at the given address into the stack
 */
class ShasamblyOpcodeBGetGlobal(address: Int) : ShasamblyGetGlobalOpcode(Opcodes.B_GET_GLOBAL, address)

/**
 * Generator for opcode [Opcodes.S_GET_GLOBAL]
 * Put the short at the given address into the stack
 */
class ShasamblyOpcodeSGetGlobal(address: Int) : ShasamblyGetGlobalOpcode(Opcodes.S_GET_GLOBAL, address)

/**
 * Generator for opcode [Opcodes.I_GET_GLOBAL]
 * Put the integer at the given address into the stack
 */
class ShasamblyOpcodeIGetGlobal(address: Int) : ShasamblyGetGlobalOpcode(Opcodes.I_GET_GLOBAL, address)

/**
 * Generator for opcode [Opcodes.L_GET_GLOBAL]
 * Put the long at the given address into the stack
 */
class ShasamblyOpcodeLGetGlobal(address: Int) : ShasamblyGetGlobalOpcode(Opcodes.L_GET_GLOBAL, address)


/**
 * An abstract opcode for getting globals with a dynamic address
 * This is not a final opcode. It is just the shared code between all
 * get-global-dynamic-opcodes
 *
 * @see ShasamblyOpcodeBGetGlobalDynamic
 * @see ShasamblyOpcodeSGetGlobalDynamic
 * @see ShasamblyOpcodeIGetGlobalDynamic
 * @see ShasamblyOpcodeLGetGlobalDynamic
 */
abstract class ShasamblyGetGlobalDynamicOpcode(val opcode: Byte) : ShasamblyOpcode {
    override val size: Int get() = 1
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(opcode)
    }
}

/**
 * Generator for opcode [Opcodes.B_GET_GLOBAL_DYNAMIC]
 * Put the byte at the given position on top of the stack. The position is given by
 * the top integer on the stack.
 */
class ShasamblyOpcodeBGetGlobalDynamic : ShasamblyGetGlobalDynamicOpcode(Opcodes.B_GET_GLOBAL_DYNAMIC)

/**
 * Generator for opcode [Opcodes.S_GET_GLOBAL_DYNAMIC]
 * Put the short at the given position on top of the stack. The position is given by
 * the top integer on the stack.
 */
class ShasamblyOpcodeSGetGlobalDynamic : ShasamblyGetGlobalDynamicOpcode(Opcodes.S_GET_GLOBAL_DYNAMIC)

/**
 * Generator for opcode [Opcodes.I_GET_GLOBAL_DYNAMIC]
 * Put the integer at the given position on top of the stack. The position is given by
 * the top integer on the stack.
 */
class ShasamblyOpcodeIGetGlobalDynamic : ShasamblyGetGlobalDynamicOpcode(Opcodes.I_GET_GLOBAL_DYNAMIC)

/**
 * Generator for opcode [Opcodes.L_GET_GLOBAL_DYNAMIC]
 * Put the long at the given position on top of the stack. The position is given by
 * the top integer on the stack.
 */
class ShasamblyOpcodeLGetGlobalDynamic : ShasamblyGetGlobalDynamicOpcode(Opcodes.L_GET_GLOBAL_DYNAMIC)


/**
 * An abstract opcode for setting globals with a static address
 * This is not a final opcode. It is just the shared code between all
 * store-global opcodes
 *
 * @see ShasamblyOpcodeBStoreGlobal
 * @see ShasamblyOpcodeSStoreGlobal
 * @see ShasamblyOpcodeIStoreGlobal
 * @see ShasamblyOpcodeLStoreGlobal
 */
abstract class ShasamblyStoreGlobalOpcode(val opcode: Byte, val address: Int) : ShasamblyOpcode {
    override val size: Int get() = 5
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(opcode, *address.toBytes())
    }
}

/**
 * Generator for opcode [Opcodes.B_STORE_GLOBAL]
 * Put the top byte on the stack into the given address
 */
class ShasamblyOpcodeBStoreGlobal(address: Int) : ShasamblyStoreGlobalOpcode(Opcodes.B_STORE_GLOBAL, address)

/**
 * Generator for opcode [Opcodes.S_STORE_GLOBAL]
 * Put the top short on the stack into the given address
 */
class ShasamblyOpcodeSStoreGlobal(address: Int) : ShasamblyStoreGlobalOpcode(Opcodes.S_STORE_GLOBAL, address)

/**
 * Generator for opcode [Opcodes.I_STORE_GLOBAL]
 * Put the top integer on the stack into the given address
 */
class ShasamblyOpcodeIStoreGlobal(address: Int) : ShasamblyStoreGlobalOpcode(Opcodes.I_STORE_GLOBAL, address)

/**
 * Generator for opcode [Opcodes.L_STORE_GLOBAL]
 * Put the top long on the stack into the given address
 */
class ShasamblyOpcodeLStoreGlobal(address: Int) : ShasamblyStoreGlobalOpcode(Opcodes.L_STORE_GLOBAL, address)


/**
 * An abstract opcode for setting globals with a dynamic address
 * This is not a final opcode. It is just the shared code between all
 * store-global-dynamic opcodes
 *
 * @see ShasamblyOpcodeBStoreGlobalDynamic
 * @see ShasamblyOpcodeSStoreGlobalDynamic
 * @see ShasamblyOpcodeIStoreGlobalDynamic
 * @see ShasamblyOpcodeLStoreGlobalDynamic
 */
abstract class ShasamblyStoreGlobalDynamicOpcode(val opcode: Byte) : ShasamblyOpcode {
    override val size: Int get() = 1
    override fun generate(gen: ShasamblyGenerator): ByteArray {
        return byteArrayOf(opcode)
    }
}

/**
 * Generator for opcode [Opcodes.B_STORE_GLOBAL_DYNAMIC]
 * Put the top byte under the top integer (so the byte at position 5) on the stack into the given
 * address. The address is given by the top integer on the stack.
 */
class ShasamblyOpcodeBStoreGlobalDynamic : ShasamblyStoreGlobalDynamicOpcode(Opcodes.B_STORE_GLOBAL_DYNAMIC)

/**
 * Generator for opcode [Opcodes.S_STORE_GLOBAL_DYNAMIC]
 * Put the top short under the top integer (so the short at position 5) on the stack into the given
 * address. The address is given by the top integer on the stack.
 */
class ShasamblyOpcodeSStoreGlobalDynamic : ShasamblyStoreGlobalDynamicOpcode(Opcodes.S_STORE_GLOBAL_DYNAMIC)

/**
 * Generator for opcode [Opcodes.I_STORE_GLOBAL_DYNAMIC]
 * Put the top integer under the top integer (so the integer at position 5) on the stack into the given
 * address. The address is given by the top integer on the stack.
 */
class ShasamblyOpcodeIStoreGlobalDynamic : ShasamblyStoreGlobalDynamicOpcode(Opcodes.I_STORE_GLOBAL_DYNAMIC)

/**
 * Generator for opcode [Opcodes.L_STORE_GLOBAL_DYNAMIC]
 * Put the top long under the top integer (so the long at position 5) on the stack into the given
 * address. The address is given by the top integer on the stack.
 */
class ShasamblyOpcodeLStoreGlobalDynamic : ShasamblyStoreGlobalDynamicOpcode(Opcodes.L_STORE_GLOBAL_DYNAMIC)


/**
 * Generator for opcode [Opcodes.B_NEG]
 * Negate the top byte of the stack
 */
class ShasamblyOpcodeBNeg : ShasamblyOperationOpcode(Opcodes.B_NEG)

/**
 * Generator for opcode [Opcodes.S_NEG]
 * Negate the top short of the stack
 */
class ShasamblyOpcodeSNeg : ShasamblyOperationOpcode(Opcodes.S_NEG)

/**
 * Generator for opcode [Opcodes.I_NEG]
 * Negate the top integer of the stack
 */
class ShasamblyOpcodeINeg : ShasamblyOperationOpcode(Opcodes.I_NEG)

/**
 * Generator for opcode [Opcodes.L_NEG]
 * Negate the top long of the stack
 */
class ShasamblyOpcodeLNeg : ShasamblyOperationOpcode(Opcodes.L_NEG)

/**
 * Generator for opcode [Opcodes.F_NEG]
 * Negate the top float of the stack
 */
class ShasamblyOpcodeFNeg : ShasamblyOperationOpcode(Opcodes.F_NEG)

/**
 * Generator for opcode [Opcodes.D_NEG]
 * Negate the top double of the stack
 */
class ShasamblyOpcodeDNeg : ShasamblyOperationOpcode(Opcodes.D_NEG)


/**
 * Generator for opcode [Opcodes.B_ABS]
 * Put the absolute value of the top byte on top of the stack
 */
class ShasamblyOpcodeBAbs : ShasamblyOperationOpcode(Opcodes.B_ABS)

/**
 * Generator for opcode [Opcodes.S_ABS]
 * Put the absolute value of the top short on top of the stack
 */
class ShasamblyOpcodeSAbs : ShasamblyOperationOpcode(Opcodes.S_ABS)

/**
 * Generator for opcode [Opcodes.I_ABS]
 * Put the absolute value of the top integer on top of the stack
 */
class ShasamblyOpcodeIAbs : ShasamblyOperationOpcode(Opcodes.I_ABS)

/**
 * Generator for opcode [Opcodes.L_ABS]
 * Put the absolute value of the top long on top of the stack
 */
class ShasamblyOpcodeLAbs : ShasamblyOperationOpcode(Opcodes.L_ABS)

/**
 * Generator for opcode [Opcodes.F_ABS]
 * Put the absolute value of the top float on top of the stack
 */
class ShasamblyOpcodeFAbs : ShasamblyOperationOpcode(Opcodes.F_ABS)

/**
 * Generator for opcode [Opcodes.D_ABS]
 * Put the absolute value of the top double on top of the stack
 */
class ShasamblyOpcodeDAbs : ShasamblyOperationOpcode(Opcodes.D_ABS)