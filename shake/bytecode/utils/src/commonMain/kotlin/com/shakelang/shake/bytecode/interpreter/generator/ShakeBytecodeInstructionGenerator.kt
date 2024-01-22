package com.shakelang.shake.bytecode.interpreter.generator

import com.shakelang.shake.bytecode.interpreter.Opcodes
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.util.primitives.bytes.setBytes
import com.shakelang.util.primitives.bytes.toBytes
import com.shakelang.util.primitives.calc.shl
import kotlin.jvm.JvmName

/**
 * Utility class to generate bytecode instructions
 * Normally you should use the [bytecode] function to generate bytecode
 *
 * @property bytes The bytes of the generator (default: empty)
 * @constructor Creates a new [ShakeBytecodeInstructionGenerator]
 * @param bytes The bytes of the generator (default: empty)
 *
 * @since 0.1.0
 * @version 0.1.0
 */

@Suppress("ktlint:standard:function-naming")
open class ShakeBytecodeInstructionGenerator(

    /**
     * The bytes of the generator
     * @since 0.1.0
     * @version 0.1.0
     */
    val bytes: MutableList<Byte> = mutableListOf(),
) {

    /**
     * Get a pointer to the current position in the bytecode
     * @return The pointer to the current position in the bytecode
     * @since 0.1.0
     * @version 0.1.0
     */
    fun pointer() = size

    /**
     * Get the current size of the bytecode
     * @return The current size of the bytecode
     * @since 0.1.0
     * @version 0.1.0
     */
    val size: Int get() = bytes.size

    /**
     * Add a byte to the bytecode
     * @param byte The byte to add
     * @since 0.1.0
     * @version 0.1.0
     */
    fun addByte(byte: Byte) = bytes.add(byte)

    /**
     * Add a list of bytes to the bytecode
     * @param bytes The bytes to add
     * @since 0.1.0
     * @version 0.1.0
     */
    fun addBytes(bytes: List<Byte>) = this.bytes.addAll(bytes)

    /**
     * Add a list of bytes to the bytecode
     * @param bytes The bytes to add
     * @since 0.1.0
     * @version 0.1.0
     */
    fun addBytes(vararg bytes: Byte) = this.bytes.addAll(bytes.toList())

    /**
     * Add a nop instruction to the bytecode
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-nop)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.NOP
     */
    fun nop() = addByte(Opcodes.NOP)

    /**
     * Push a byte to the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bpush)
     *
     * @param byte The byte to push
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BPUSH
     */
    fun bpush(byte: Byte) = addBytes(listOf(Opcodes.BPUSH, byte))

    /**
     * Push a short to the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-spush)
     *
     * @param short The short to push
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SPUSH
     */
    fun spush(short: Short) = addBytes(listOf(Opcodes.SPUSH, *short.toBytes().toTypedArray()))

    /**
     * Push an int to the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ipush)
     *
     * @param int The int to push
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.IPUSH
     */
    fun ipush(int: Int) = addBytes(listOf(Opcodes.IPUSH, *int.toBytes().toTypedArray()))

    /**
     * Push a long to the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lpush)
     *
     * @param long The long to push
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LPUSH
     */
    fun lpush(long: Long) = addBytes(listOf(Opcodes.LPUSH, *long.toBytes().toTypedArray()))

    /**
     * Push a boolean to the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bpush)
     *
     * @param value The boolean to push
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BPUSH
     */
    fun bpush(value: Boolean) = addBytes(listOf(Opcodes.BPUSH, if (value) 1 else 0))

    /**
     * Push a char to the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-spush)
     *
     * @param value The char to push
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SPUSH
     */
    fun spush(value: Char) = addBytes(listOf(Opcodes.SPUSH, *value.code.toShort().toBytes().toTypedArray()))

    /**
     * Push a float to the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ipush)
     *
     * @param value The float to push
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.IPUSH
     */
    fun ipush(value: Float) = addBytes(listOf(Opcodes.IPUSH, *value.toRawBits().toBytes().toTypedArray()))

    /**
     * Push a double to the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lpush)
     *
     * @param value The double to push
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LPUSH
     */
    fun lpush(value: Double) = addBytes(listOf(Opcodes.LPUSH, *value.toRawBits().toBytes().toTypedArray()))

    /**
     * Push an unsigned byte to the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bpush)
     *
     * @param value The unsigned byte to push
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BPUSH
     */
    @JvmName("bpushUByte")
    fun bpush(value: UByte) = addBytes(listOf(Opcodes.BPUSH, value.toByte()))

    /**
     * Push an unsigned short to the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-spush)
     *
     * @param value The unsigned short to push
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SPUSH
     */
    @JvmName("spushUShort")
    fun spush(value: UShort) = addBytes(listOf(Opcodes.SPUSH, *value.toBytes().toTypedArray()))

    /**
     * Push an unsigned int to the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ipush)
     *
     * @param value The unsigned int to push
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.IPUSH
     */
    @JvmName("ipushUInt")
    fun ipush(value: UInt) = addBytes(listOf(Opcodes.IPUSH, *value.toBytes().toTypedArray()))

    /**
     * Push an unsigned long to the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lpush)
     *
     * @param value The unsigned long to push
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LPUSH
     */
    @JvmName("lpushULong")
    fun lpush(value: ULong) = addBytes(listOf(Opcodes.LPUSH, *value.toBytes().toTypedArray()))

    /**
     * Load a byte from the local variable table onto the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bload)
     *
     * @param variable The variable to load
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BLOAD
     */
    fun bload(variable: UShort) = addBytes(listOf(Opcodes.BLOAD, *variable.toBytes().toTypedArray()))

    /**
     * Load a short from the local variable table onto the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sload)
     *
     * @param variable The variable to load
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SLOAD
     */
    fun sload(variable: UShort) = addBytes(listOf(Opcodes.SLOAD, *variable.toBytes().toTypedArray()))

    /**
     * Load an int from the local variable table onto the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-iload)
     *
     * @param variable The variable to load
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.ILOAD
     */
    fun iload(variable: UShort) = addBytes(listOf(Opcodes.ILOAD, *variable.toBytes().toTypedArray()))

    /**
     * Load a long from the local variable table onto the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lload)
     *
     * @param variable The variable to load
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LLOAD
     */
    fun lload(variable: UShort) = addBytes(listOf(Opcodes.LLOAD, *variable.toBytes().toTypedArray()))

    /**
     * Load a variable from the local variable table onto the stack
     * Takes the type of the variable as a string
     *
     * | Identifier  | Type   | Byte Size |
     * |------------|--------|-----------|
     * | B          | Byte   | 1         |
     * | S          | Short  | 2         |
     * | I          | Int    | 4         |
     * | J          | Long   | 8         |
     * | F          | Float  | 4         |
     * | D          | Double | 8         |
     * | b          | UByte  | 1         |
     * | s          | UShort | 2         |
     * | i          | UInt   | 4         |
     * | j          | ULong  | 8         |
     *
     * @param type The type of the variable to load
     * @param variable The variable to load
     * @since 0.1.0
     * @version 0.1.0
     *
     * @see bload
     * @see sload
     * @see iload
     * @see lload
     * @see Opcodes.BLOAD
     * @see Opcodes.SLOAD
     * @see Opcodes.ILOAD
     * @see Opcodes.LLOAD
     */
    fun load(type: String, variable: UShort) {
        when (type) {
            "B" -> bload(variable)
            "S" -> sload(variable)
            "I" -> iload(variable)
            "J" -> lload(variable)
            "F" -> iload(variable)
            "D" -> lload(variable)
            "b" -> bload(variable)
            "s" -> sload(variable)
            "i" -> iload(variable)
            "j" -> lload(variable)
        }
    }

    /**
     * Store a byte from the stack into the local variable table
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bstore)
     *
     * @param variable The variable to store
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BSTORE
     */
    fun bstore(variable: UShort) = addBytes(listOf(Opcodes.BSTORE, *variable.toBytes().toTypedArray()))

    /**
     * Store a short from the stack into the local variable table
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sstore)
     *
     * @param variable The variable to store
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SSTORE
     */
    fun sstore(variable: UShort) = addBytes(listOf(Opcodes.SSTORE, *variable.toBytes().toTypedArray()))

    /**
     * Store an int from the stack into the local variable table
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-istore)
     *
     * @param variable The variable to store
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.ISTORE
     */
    fun istore(variable: UShort) = addBytes(listOf(Opcodes.ISTORE, *variable.toBytes().toTypedArray()))

    /**
     * Store a long from the stack into the local variable table
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lstore)
     *
     * @param variable The variable to store
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LSTORE
     */
    fun lstore(variable: UShort) = addBytes(listOf(Opcodes.LSTORE, *variable.toBytes().toTypedArray()))

    /**
     * Store a variable from the stack into the local variable table
     * Takes the type of the variable as a string
     *
     * | Identifier  | Type   | Byte Size |
     * |------------|--------|-----------|
     * | B          | Byte   | 1         |
     * | S          | Short  | 2         |
     * | I          | Int    | 4         |
     * | J          | Long   | 8         |
     * | F          | Float  | 4         |
     * | D          | Double | 8         |
     * | b          | UByte  | 1         |
     * | s          | UShort | 2         |
     * | i          | UInt   | 4         |
     * | j          | ULong  | 8         |
     *
     * @param type The type of the variable to store
     * @param variable The variable to store
     * @since 0.1.0
     * @version 0.1.0
     *
     * @see bstore
     * @see sstore
     * @see istore
     * @see lstore
     * @see Opcodes.BSTORE
     * @see Opcodes.SSTORE
     * @see Opcodes.ISTORE
     * @see Opcodes.LSTORE
     */
    fun store(type: String, variable: UShort) {
        when (type) {
            "B" -> bstore(variable)
            "S" -> sstore(variable)
            "I" -> istore(variable)
            "J" -> lstore(variable)
            "F" -> istore(variable)
            "D" -> lstore(variable)
            "b" -> bstore(variable)
            "s" -> sstore(variable)
            "i" -> istore(variable)
            "j" -> lstore(variable)
        }
    }

    /**
     * Add the top two bytes on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-badd)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BADD
     */
    fun badd() = addByte(Opcodes.BADD)

    /**
     * Add the top two shorts on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sadd)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SADD
     */
    fun sadd() = addByte(Opcodes.SADD)

    /**
     * Add the top two ints on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-iadd)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.IADD
     */
    fun iadd() = addByte(Opcodes.IADD)

    /**
     * Add the top two longs on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ladd)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LADD
     */
    fun ladd() = addByte(Opcodes.LADD)

    /**
     * Add the top two floats on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-fadd)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.FADD
     */
    fun fadd() = addByte(Opcodes.FADD)

    /**
     * Add the top two doubles on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-dadd)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.DADD
     */
    fun dadd() = addByte(Opcodes.DADD)

    /**
     * Add the top two variables on the stack
     * Takes the type of the variables as a string
     *
     * | Identifier  | Type   | Byte Size |
     * |------------|--------|-----------|
     * | B          | Byte   | 1         |
     * | S          | Short  | 2         |
     * | I          | Int    | 4         |
     * | J          | Long   | 8         |
     * | F          | Float  | 4         |
     * | D          | Double | 8         |
     * | b          | UByte  | 1         |
     * | s          | UShort | 2         |
     * | i          | UInt   | 4         |
     * | j          | ULong  | 8         |
     *
     * @param type The type of the variables to add
     * @since 0.1.0
     * @version 0.1.0
     *
     * @see badd
     * @see sadd
     * @see iadd
     * @see ladd
     * @see fadd
     * @see dadd
     * @see Opcodes.BADD
     * @see Opcodes.SADD
     * @see Opcodes.IADD
     * @see Opcodes.LADD
     * @see Opcodes.FADD
     * @see Opcodes.DADD
     */
    fun add(type: String) {
        when (type) {
            "B" -> badd()
            "S" -> sadd()
            "I" -> iadd()
            "J" -> ladd()
            "F" -> fadd()
            "D" -> dadd()
            "b" -> badd()
            "s" -> sadd()
            "i" -> iadd()
            "j" -> ladd()
        }
    }

    /**
     * Subtract the top two bytes on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bsub)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BSUB
     */
    fun bsub() = addByte(Opcodes.BSUB)

    /**
     * Subtract the top two shorts on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ssub)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SSUB
     */
    fun ssub() = addByte(Opcodes.SSUB)

    /**
     * Subtract the top two ints on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-isub)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.ISUB
     */
    fun isub() = addByte(Opcodes.ISUB)

    /**
     * Subtract the top two longs on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lsub)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LSUB
     */
    fun lsub() = addByte(Opcodes.LSUB)

    /**
     * Subtract the top two unsigned bytes on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ubsub)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.UBSUB
     */
    fun ubsub() = addByte(Opcodes.UBSUB)

    /**
     * Subtract the top two unsigned shorts on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ussub)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.USSUB
     */
    fun ussub() = addByte(Opcodes.USSUB)

    /**
     * Subtract the top two unsigned ints on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-uisub)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.UISUB
     */
    fun uisub() = addByte(Opcodes.UISUB)

    /**
     * Subtract the top two unsigned longs on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ulsub)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.ULSUB
     */
    fun ulsub() = addByte(Opcodes.ULSUB)

    /**
     * Subtract the top two floats on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-fsub)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.FSUB
     */
    fun fsub() = addByte(Opcodes.FSUB)

    /**
     * Subtract the top two doubles on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-dsub)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.DSUB
     */
    fun dsub() = addByte(Opcodes.DSUB)

    /**
     * Subtract the top two variables on the stack
     * Takes the type of the variables as a string
     *
     * | Identifier  | Type   | Byte Size |
     * |------------|--------|-----------|
     * | B          | Byte   | 1         |
     * | S          | Short  | 2         |
     * | I          | Int    | 4         |
     * | J          | Long   | 8         |
     * | F          | Float  | 4         |
     * | D          | Double | 8         |
     * | b          | UByte  | 1         |
     * | s          | UShort | 2         |
     * | i          | UInt   | 4         |
     * | j          | ULong  | 8         |
     *
     * @param type The type of the variables to subtract
     * @since 0.1.0
     * @version 0.1.0
     *
     * @see bsub
     * @see ssub
     * @see isub
     * @see lsub
     * @see fsub
     * @see dsub
     * @see Opcodes.BSUB
     * @see Opcodes.SSUB
     * @see Opcodes.ISUB
     * @see Opcodes.LSUB
     * @see Opcodes.FSUB
     * @see Opcodes.DSUB
     */
    fun sub(type: String) {
        when (type) {
            "B" -> bsub()
            "S" -> ssub()
            "I" -> isub()
            "J" -> lsub()
            "F" -> fsub()
            "D" -> dsub()
            "b" -> ubsub()
            "s" -> ussub()
            "i" -> uisub()
            "j" -> ulsub()
        }
    }

    /**
     * Multiply the top two bytes on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bmul)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BMUL
     */
    fun bmul() = addByte(Opcodes.BMUL)

    /**
     * Multiply the top two shorts on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-smul)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SMUL
     */
    fun smul() = addByte(Opcodes.SMUL)

    /**
     * Multiply the top two ints on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-imul)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.IMUL
     */
    fun imul() = addByte(Opcodes.IMUL)

    /**
     * Multiply the top two longs on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lmul)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LMUL
     */
    fun lmul() = addByte(Opcodes.LMUL)

    /**
     * Multiply the top two unsigned bytes on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ubmul)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.UBMUL
     */
    fun ubmul() = addByte(Opcodes.UBMUL)

    /**
     * Multiply the top two unsigned shorts on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-usmul)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.USMUL
     */
    fun usmul() = addByte(Opcodes.USMUL)

    /**
     * Multiply the top two unsigned ints on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-uimul)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.UIMUL
     */
    fun uimul() = addByte(Opcodes.UIMUL)

    /**
     * Multiply the top two unsigned longs on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ulmul)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.ULMUL
     */
    fun ulmul() = addByte(Opcodes.ULMUL)

    /**
     * Multiply the top two floats on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-fmul)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.FMUL
     */
    fun fmul() = addByte(Opcodes.FMUL)

    /**
     * Multiply the top two doubles on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-dmul)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.DMUL
     */
    fun dmul() = addByte(Opcodes.DMUL)

    /**
     * Multiply the top two variables on the stack
     * Takes the type of the variables as a string
     *
     * | Identifier  | Type   | Byte Size |
     * |------------|--------|-----------|
     * | B          | Byte   | 1         |
     * | S          | Short  | 2         |
     * | I          | Int    | 4         |
     * | J          | Long   | 8         |
     * | F          | Float  | 4         |
     * | D          | Double | 8         |
     * | b          | UByte  | 1         |
     * | s          | UShort | 2         |
     * | i          | UInt   | 4         |
     * | j          | ULong  | 8         |
     *
     * @param type The type of the variables to multiply
     * @since 0.1.0
     * @version 0.1.0
     *
     * @see bmul
     * @see smul
     * @see imul
     * @see lmul
     * @see fmul
     * @see dmul
     * @see Opcodes.BMUL
     * @see Opcodes.SMUL
     * @see Opcodes.IMUL
     * @see Opcodes.LMUL
     * @see Opcodes.FMUL
     * @see Opcodes.DMUL
     */
    fun mul(type: String) {
        when (type) {
            "B" -> bmul()
            "S" -> smul()
            "I" -> imul()
            "J" -> lmul()
            "F" -> fmul()
            "D" -> dmul()
            "b" -> ubmul()
            "s" -> usmul()
            "i" -> uimul()
            "j" -> ulmul()
        }
    }

    /**
     * Divide the top two bytes on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bdiv)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BDIV
     */
    fun bdiv() = addByte(Opcodes.BDIV)

    /**
     * Divide the top two shorts on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sdiv)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SDIV
     */
    fun sdiv() = addByte(Opcodes.SDIV)

    /**
     * Divide the top two ints on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-idiv)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.IDIV
     */
    fun idiv() = addByte(Opcodes.IDIV)

    /**
     * Divide the top two longs on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ldiv)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LDIV
     */
    fun ldiv() = addByte(Opcodes.LDIV)

    /**
     * Divide the top two unsigned bytes on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ubdiv)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.UBDIV
     */
    fun ubdiv() = addByte(Opcodes.UBDIV)

    /**
     * Divide the top two unsigned shorts on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-usdiv)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.USDIV
     */
    fun usdiv() = addByte(Opcodes.USDIV)

    /**
     * Divide the top two unsigned ints on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-uidiv)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.UIDIV
     */
    fun uidiv() = addByte(Opcodes.UIDIV)

    /**
     * Divide the top two unsigned longs on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-uldiv)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.ULDIV
     */
    fun uldiv() = addByte(Opcodes.ULDIV)

    /**
     * Divide the top two floats on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-fdiv)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.FDIV
     */
    fun fdiv() = addByte(Opcodes.FDIV)

    /**
     * Divide the top two doubles on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ddiv)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.DDIV
     */
    fun ddiv() = addByte(Opcodes.DDIV)

    /**
     * Divide the top two variables on the stack
     * Takes the type of the variables as a string
     *
     * | Identifier  | Type   | Byte Size |
     * |------------|--------|-----------|
     * | B          | Byte   | 1         |
     * | S          | Short  | 2         |
     * | I          | Int    | 4         |
     * | J          | Long   | 8         |
     * | F          | Float  | 4         |
     * | D          | Double | 8         |
     * | b          | UByte  | 1         |
     * | s          | UShort | 2         |
     * | i          | UInt   | 4         |
     * | j          | ULong  | 8         |
     *
     * @param type The type of the variables to divide
     * @since 0.1.0
     * @version 0.1.0
     *
     * @see bdiv
     * @see sdiv
     * @see idiv
     * @see ldiv
     * @see fdiv
     * @see ddiv
     * @see Opcodes.BDIV
     * @see Opcodes.SDIV
     * @see Opcodes.IDIV
     * @see Opcodes.LDIV
     * @see Opcodes.FDIV
     * @see Opcodes.DDIV
     */
    fun div(type: String) {
        when (type) {
            "B" -> bdiv()
            "S" -> sdiv()
            "I" -> idiv()
            "J" -> ldiv()
            "F" -> fdiv()
            "D" -> ddiv()
            "b" -> ubdiv()
            "s" -> usdiv()
            "i" -> uidiv()
            "j" -> uldiv()
        }
    }

    /**
     * Modulo the top two bytes on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bmod)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BMOD
     */
    fun bmod() = addByte(Opcodes.BMOD)

    /**
     * Modulo the top two shorts on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-smod)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SMOD
     */
    fun smod() = addByte(Opcodes.SMOD)

    /**
     * Modulo the top two ints on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-imod)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.IMOD
     */
    fun imod() = addByte(Opcodes.IMOD)

    /**
     * Modulo the top two longs on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lmod)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LMOD
     */
    fun lmod() = addByte(Opcodes.LMOD)

    /**
     * Modulo the top two unsigned bytes on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ubmod)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.UBMOD
     */
    fun ubmod() = addByte(Opcodes.UBMOD)

    /**
     * Modulo the top two unsigned shorts on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-usmod)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.USMOD
     */
    fun usmod() = addByte(Opcodes.USMOD)

    /**
     * Modulo the top two unsigned ints on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-uimod)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.UIMOD
     */
    fun uimod() = addByte(Opcodes.UIMOD)

    /**
     * Modulo the top two unsigned longs on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ulmod)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.ULMOD
     */
    fun ulmod() = addByte(Opcodes.ULMOD)

    /**
     * Modulo the top two floats on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-fmod)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.FMOD
     */
    fun fmod() = addByte(Opcodes.FMOD)

    /**
     * Modulo the top two doubles on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-dmod)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.DMOD
     */
    fun dmod() = addByte(Opcodes.DMOD)

    /**
     * Modulo the top two variables on the stack
     * Takes the type of the variables as a string
     *
     * | Identifier  | Type   | Byte Size |
     * |------------|--------|-----------|
     * | B          | Byte   | 1         |
     * | S          | Short  | 2         |
     * | I          | Int    | 4         |
     * | J          | Long   | 8         |
     * | F          | Float  | 4         |
     * | D          | Double | 8         |
     * | b          | UByte  | 1         |
     * | s          | UShort | 2         |
     * | i          | UInt   | 4         |
     * | j          | ULong  | 8         |
     *
     * @param type The type of the variables to modulo
     * @since 0.1.0
     * @version 0.1.0
     *
     * @see bmod
     * @see smod
     * @see imod
     * @see lmod
     * @see fmod
     * @see dmod
     * @see Opcodes.BMOD
     * @see Opcodes.SMOD
     * @see Opcodes.IMOD
     * @see Opcodes.LMOD
     * @see Opcodes.FMOD
     * @see Opcodes.DMOD
     */
    fun mod(type: String) {
        when (type) {
            "B" -> bmod()
            "S" -> smod()
            "I" -> imod()
            "J" -> lmod()
            "F" -> fmod()
            "D" -> dmod()
            "b" -> ubmod()
            "s" -> usmod()
            "i" -> uimod()
            "j" -> ulmod()
        }
    }

    /**
     * Negate the top byte on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bneg)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BNEG
     */
    fun bneg() = addByte(Opcodes.BNEG)

    /**
     * Negate the top short on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sneg)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SNEG
     */
    fun sneg() = addByte(Opcodes.SNEG)

    /**
     * Negate the top int on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ineg)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.INEG
     */
    fun ineg() = addByte(Opcodes.INEG)

    /**
     * Negate the top long on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lneg)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LNEG
     */
    fun lneg() = addByte(Opcodes.LNEG)

    /**
     * Negate the top float on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-fneg)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.FNEG
     */
    fun fneg() = addByte(Opcodes.FNEG)

    /**
     * Negate the top double on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-dneg)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.DNEG
     */
    fun dneg() = addByte(Opcodes.DNEG)

    /**
     * Negate the top variable on the stack
     * Takes the type of the variable as a string
     *
     * | Identifier  | Type   | Byte Size |
     * |------------|--------|-----------|
     * | B          | Byte   | 1         |
     * | S          | Short  | 2         |
     * | I          | Int    | 4         |
     * | J          | Long   | 8         |
     * | F          | Float  | 4         |
     * | D          | Double | 8         |
     *
     * @param type The type of the variable to negate
     * @since 0.1.0
     * @version 0.1.0
     *
     * @see bneg
     * @see sneg
     * @see ineg
     * @see lneg
     * @see fneg
     * @see dneg
     * @see Opcodes.BNEG
     * @see Opcodes.SNEG
     * @see Opcodes.INEG
     * @see Opcodes.LNEG
     * @see Opcodes.FNEG
     * @see Opcodes.DNEG
     */
    fun neg(type: String) {
        when (type) {
            "B" -> bneg()
            "S" -> sneg()
            "I" -> ineg()
            "J" -> lneg()
            "F" -> fneg()
            "D" -> dneg()
        }
    }

    /**
     * Bitwise and the top two bytes on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-band)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BAND
     */
    fun band() = addByte(Opcodes.BAND)

    /**
     * Bitwise and the top two shorts on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sand)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SAND
     */
    fun sand() = addByte(Opcodes.SAND)

    /**
     * Bitwise and the top two ints on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-iand)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.IAND
     */
    fun iand() = addByte(Opcodes.IAND)

    /**
     * Bitwise and the top two longs on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-land)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LAND
     */
    fun land() = addByte(Opcodes.LAND)

    /**
     * Bitwise and the top two variables on the stack
     * Takes the type of the variables as a string
     *
     * | Identifier  | Type   | Byte Size |
     * |------------|--------|-----------|
     * | B          | Byte   | 1         |
     * | S          | Short  | 2         |
     * | I          | Int    | 4         |
     * | J          | Long   | 8         |
     * | b          | UByte  | 1         |
     * | s          | UShort | 2         |
     * | i          | UInt   | 4         |
     * | j          | ULong  | 8         |
     * | Z          | Bool   | 1         |
     * | C          | Char   | 2         |
     * | F          | Float  | 4         |
     * | D          | Double | 8         |
     *
     * @param type The type of the variables to bitwise and
     * @since 0.1.0
     * @version 0.1.0
     *
     * @see band
     * @see sand
     * @see iand
     * @see land
     * @see Opcodes.BAND
     * @see Opcodes.SAND
     * @see Opcodes.IAND
     * @see Opcodes.LAND
     */
    fun and(type: String) {
        when (type) {
            "B" -> band()
            "S" -> sand()
            "I" -> iand()
            "J" -> land()
            "b" -> band()
            "s" -> sand()
            "i" -> iand()
            "j" -> land()
            "Z" -> band()
            "C" -> sand()
            "F" -> iand()
            "D" -> land()
        }
    }

    /**
     * Bitwise or the top two bytes on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bor)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BOR
     */
    fun bor() = addByte(Opcodes.BOR)

    /**
     * Bitwise or the top two shorts on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sor)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SOR
     */
    fun sor() = addByte(Opcodes.SOR)

    /**
     * Bitwise or the top two ints on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ior)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.IOR
     */
    fun ior() = addByte(Opcodes.IOR)

    /**
     * Bitwise or the top two longs on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lor)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LOR
     */
    fun lor() = addByte(Opcodes.LOR)

    /**
     * Bitwise or the top two variables on the stack
     * Takes the type of the variables as a string
     *
     * | Identifier  | Type   | Byte Size |
     * |------------|--------|-----------|
     * | B          | Byte   | 1         |
     * | S          | Short  | 2         |
     * | I          | Int    | 4         |
     * | J          | Long   | 8         |
     * | b          | UByte  | 1         |
     * | s          | UShort | 2         |
     * | i          | UInt   | 4         |
     * | j          | ULong  | 8         |
     * | Z          | Bool   | 1         |
     * | C          | Char   | 2         |
     * | F          | Float  | 4         |
     * | D          | Double | 8         |
     *
     * @param type The type of the variables to bitwise or
     * @since 0.1.0
     * @version 0.1.0
     *
     * @see bor
     * @see sor
     * @see ior
     * @see lor
     * @see Opcodes.BOR
     * @see Opcodes.SOR
     * @see Opcodes.IOR
     * @see Opcodes.LOR
     */
    fun or(type: String) {
        when (type) {
            "B" -> bor()
            "S" -> sor()
            "I" -> ior()
            "J" -> lor()
            "b" -> bor()
            "s" -> sor()
            "i" -> ior()
            "j" -> lor()
            "Z" -> bor()
            "C" -> sor()
            "F" -> ior()
            "D" -> lor()
        }
    }

    /**
     * Bitwise xor the top two bytes on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bxor)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BXOR
     */
    fun bxor() = addByte(Opcodes.BXOR)

    /**
     * Bitwise xor the top two shorts on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sxor)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SXOR
     */
    fun sxor() = addByte(Opcodes.SXOR)

    /**
     * Bitwise xor the top two ints on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ixor)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.IXOR
     */
    fun ixor() = addByte(Opcodes.IXOR)

    /**
     * Bitwise xor the top two longs on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lxor)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LXOR
     */
    fun lxor() = addByte(Opcodes.LXOR)

    /**
     * Bitwise xor the top two variables on the stack
     * Takes the type of the variables as a string
     *
     * | Identifier  | Type   | Byte Size |
     * |------------|--------|-----------|
     * | B          | Byte   | 1         |
     * | S          | Short  | 2         |
     * | I          | Int    | 4         |
     * | J          | Long   | 8         |
     * | b          | UByte  | 1         |
     * | s          | UShort | 2         |
     * | i          | UInt   | 4         |
     * | j          | ULong  | 8         |
     * | Z          | Bool   | 1         |
     * | C          | Char   | 2         |
     * | F          | Float  | 4         |
     * | D          | Double | 8         |
     *
     * @param type The type of the variables to bitwise xor
     * @since 0.1.0
     * @version 0.1.0
     *
     * @see bxor
     * @see sxor
     * @see ixor
     * @see lxor
     * @see Opcodes.BXOR
     * @see Opcodes.SXOR
     * @see Opcodes.IXOR
     * @see Opcodes.LXOR
     */
    fun xor(type: String) {
        when (type) {
            "B" -> bxor()
            "S" -> sxor()
            "I" -> ixor()
            "J" -> lxor()
            "b" -> bxor()
            "s" -> sxor()
            "i" -> ixor()
            "j" -> lxor()
            "Z" -> bxor()
            "C" -> sxor()
            "F" -> ixor()
            "D" -> lxor()
        }
    }

    /**
     * Bitwise not the top byte on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bnot)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BNOT
     */
    fun bnot() = addByte(Opcodes.BNOT)

    /**
     * Bitwise not the top short on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-snot)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SNOT
     */
    fun snot() = addByte(Opcodes.SNOT)

    /**
     * Bitwise not the top int on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-inot)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.INOT
     */
    fun inot() = addByte(Opcodes.INOT)

    /**
     * Bitwise not the top long on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lnot)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LNOT
     */
    fun lnot() = addByte(Opcodes.LNOT)

    /**
     * Bitwise not the top variable on the stack
     * Takes the type of the variable as a string
     *
     * | Identifier  | Type   | Byte Size |
     * |------------|--------|-----------|
     * | B          | Byte   | 1         |
     * | S          | Short  | 2         |
     * | I          | Int    | 4         |
     * | J          | Long   | 8         |
     * | F          | Float  | 4         |
     * | D          | Double | 8         |
     * | b          | UByte  | 1         |
     * | s          | UShort | 2         |
     * | i          | UInt   | 4         |
     * | j          | ULong  | 8         |
     * | Z          | Bool   | 1         |
     * | C          | Char   | 2         |
     *
     * @param type The type of the variable to bitwise not
     * @since 0.1.0
     * @version 0.1.0
     *
     * @see bnot
     * @see snot
     * @see inot
     * @see lnot
     * @see Opcodes.BNOT
     * @see Opcodes.SNOT
     * @see Opcodes.INOT
     * @see Opcodes.LNOT
     */
    fun not(type: String) {
        when (type) {
            "B" -> bnot()
            "S" -> snot()
            "I" -> inot()
            "J" -> lnot()
            "b" -> bnot()
            "s" -> snot()
            "i" -> inot()
            "j" -> lnot()
            "f" -> inot()
            "d" -> lnot()
            "Z" -> bnot()
            "C" -> snot()
        }
    }

    /**
     * Shift the top byte on the stack left by the second byte on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bshl)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BSHL
     */
    fun bshl() = addByte(Opcodes.BSHL)

    /**
     * Shift the top short on the stack left by the second short on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sshl)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SSHL
     */
    fun sshl() = addByte(Opcodes.SSHL)

    /**
     * Shift the top int on the stack left by the second int on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ishl)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.ISHL
     */
    fun ishl() = addByte(Opcodes.ISHL)

    /**
     * Shift the top long on the stack left by the second long on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lshl)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LSHL
     */
    fun lshl() = addByte(Opcodes.LSHL)

    /**
     * Shift the top variable on the stack left by the second variable on the stack
     * Takes the type of the variables as a string
     *
     * | Identifier  | Type   | Byte Size |
     * |------------|--------|-----------|
     * | B          | Byte   | 1         |
     * | S          | Short  | 2         |
     * | I          | Int    | 4         |
     * | J          | Long   | 8         |
     * | b          | UByte  | 1         |
     * | s          | UShort | 2         |
     * | i          | UInt   | 4         |
     * | j          | ULong  | 8         |
     * | Z          | Bool   | 1         |
     * | C          | Char   | 2         |
     * | F          | Float  | 4         |
     * | D          | Double | 8         |
     *
     * @param type The type of the variables to shift left
     * @since 0.1.0
     * @version 0.1.0
     *
     * @see bshl
     * @see sshl
     * @see ishl
     * @see lshl
     * @see Opcodes.BSHL
     * @see Opcodes.SSHL
     * @see Opcodes.ISHL
     * @see Opcodes.LSHL
     */
    fun shl(type: String) {
        when (type) {
            "B" -> bshl()
            "S" -> sshl()
            "I" -> ishl()
            "J" -> lshl()
            "b" -> bshl()
            "s" -> sshl()
            "i" -> ishl()
            "j" -> lshl()
            "Z" -> bshl()
            "C" -> sshl()
            "F" -> ishl()
            "D" -> lshl()
        }
    }

    /**
     * Shift the top byte on the stack left by the second byte on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bshlu)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BSHLU
     */
    fun bshr() = addByte(Opcodes.BSHR)

    /**
     * Shift the top short on the stack left by the second short on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sshr)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SSHR
     */
    fun sshr() = addByte(Opcodes.SSHR)

    /**
     * Shift the top int on the stack left by the second int on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ishr)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.ISHR
     */
    fun ishr() = addByte(Opcodes.ISHR)

    /**
     * Shift the top long on the stack left by the second long on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lshr)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LSHR
     */
    fun lshr() = addByte(Opcodes.LSHR)

    /**
     * Shift the top variable on the stack left by the second variable on the stack
     * Takes the type of the variables as a string
     *
     * | Identifier  | Type   | Byte Size |
     * |------------|--------|-----------|
     * | B          | Byte   | 1         |
     * | S          | Short  | 2         |
     * | I          | Int    | 4         |
     * | J          | Long   | 8         |
     * | b          | UByte  | 1         |
     * | s          | UShort | 2         |
     * | i          | UInt   | 4         |
     * | j          | ULong  | 8         |
     * | Z          | Bool   | 1         |
     * | C          | Char   | 2         |
     * | F          | Float  | 4         |
     * | D          | Double | 8         |
     *
     * @param type The type of the variables to shift left
     * @since 0.1.0
     * @version 0.1.0
     *
     * @see bshr
     * @see sshr
     * @see ishr
     * @see lshr
     * @see Opcodes.BSHR
     * @see Opcodes.SSHR
     * @see Opcodes.ISHR
     * @see Opcodes.LSHR
     */
    fun shr(type: String) {
        when (type) {
            "B" -> bshr()
            "S" -> sshr()
            "I" -> ishr()
            "J" -> lshr()
            "b" -> bshru()
            "s" -> sshru()
            "i" -> ishru()
            "j" -> lshru()
            "Z" -> bshru()
            "C" -> sshru()
            "F" -> ishru()
            "D" -> lshru()
        }
    }

    /**
     * Shift the top byte on the stack left by the second byte on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bshru)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BSHRU
     */
    fun bshru() = addByte(Opcodes.BSHRU)

    /**
     * Shift the top short on the stack left by the second short on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sshr)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SSHRU
     */
    fun sshru() = addByte(Opcodes.SSHRU)

    /**
     * Shift the top int on the stack left by the second int on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ishru)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.ISHRU
     */
    fun ishru() = addByte(Opcodes.ISHRU)

    /**
     * Shift the top long on the stack left by the second long on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lshru)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LSHRU
     */
    fun lshru() = addByte(Opcodes.LSHRU)

    /**
     * Shift the top variable on the stack left by the second variable on the stack
     * Takes the type of the variables as a string
     *
     * | Identifier  | Type   | Byte Size |
     * |------------|--------|-----------|
     * | B          | Byte   | 1         |
     * | S          | Short  | 2         |
     * | I          | Int    | 4         |
     * | J          | Long   | 8         |
     * | b          | UByte  | 1         |
     * | s          | UShort | 2         |
     * | i          | UInt   | 4         |
     * | j          | ULong  | 8         |
     * | Z          | Bool   | 1         |
     * | C          | Char   | 2         |
     * | F          | Float  | 4         |
     * | D          | Double | 8         |
     *
     * @param type The type of the variables to shift left
     * @since 0.1.0
     * @version 0.1.0
     *
     * @see bshru
     * @see sshru
     * @see ishru
     * @see lshru
     * @see Opcodes.BSHRU
     * @see Opcodes.SSHRU
     * @see Opcodes.ISHRU
     * @see Opcodes.LSHRU
     */
    fun shru(type: String) {
        when (type) {
            "B" -> bshru()
            "S" -> sshru()
            "I" -> ishru()
            "J" -> lshru()
        }
    }

    /**
     * Add the top two bytes on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-badd)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BADD
     */
    fun binc() = addByte(Opcodes.BINC)

    /**
     * Add the top two shorts on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sadd)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SADD
     */
    fun sinc() = addByte(Opcodes.SINC)

    /**
     * Add the top two ints on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-iadd)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.IADD
     */
    fun iinc() = addByte(Opcodes.IINC)

    /**
     * Add the top two longs on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ladd)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LADD
     */
    fun linc() = addByte(Opcodes.LINC)

    /**
     * Add the top two floats on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-fadd)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.FADD
     */
    fun finc() = addByte(Opcodes.FINC)

    /**
     * Add the top two doubles on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-dadd)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.DADD
     */
    fun dinc() = addByte(Opcodes.DINC)

    /**
     * Add the top two variables on the stack
     * Takes the type of the variables as a string
     *
     * | Identifier  | Type   | Byte Size |
     * |------------|--------|-----------|
     * | B          | Byte   | 1         |
     * | S          | Short  | 2         |
     * | I          | Int    | 4         |
     * | J          | Long   | 8         |
     * | F          | Float  | 4         |
     * | D          | Double | 8         |
     * | b          | UByte  | 1         |
     * | s          | UShort | 2         |
     * | i          | UInt   | 4         |
     * | j          | ULong  | 8         |
     *
     * @param type The type of the variables to add
     * @since 0.1.0
     * @version 0.1.0
     *
     * @see binc
     * @see sinc
     * @see iinc
     * @see linc
     * @see finc
     * @see dinc
     * @see Opcodes.BINC
     * @see Opcodes.SINC
     * @see Opcodes.IINC
     * @see Opcodes.LINC
     * @see Opcodes.FINC
     * @see Opcodes.DINC
     */
    fun inc(type: String) {
        when (type) {
            "B" -> binc()
            "S" -> sinc()
            "I" -> iinc()
            "J" -> linc()
            "F" -> finc()
            "D" -> dinc()
            "b" -> binc()
            "s" -> sinc()
            "i" -> iinc()
            "j" -> linc()
        }
    }

    /**
     * Subtract the top two bytes on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bsub)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BSUB
     */
    fun bdec() = addByte(Opcodes.BDEC)

    /**
     * Subtract the top two shorts on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ssub)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SSUB
     */
    fun sdec() = addByte(Opcodes.SDEC)

    /**
     * Subtract the top two ints on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-isub)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.ISUB
     */
    fun idec() = addByte(Opcodes.IDEC)

    /**
     * Subtract the top two longs on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lsub)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LSUB
     */
    fun ldec() = addByte(Opcodes.LDEC)

    /**
     * Subtract the top two floats on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-fsub)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.FSUB
     */
    fun fdec() = addByte(Opcodes.FDEC)

    /**
     * Subtract the top two doubles on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-dsub)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.DSUB
     */
    fun ddec() = addByte(Opcodes.DDEC)

    /**
     * Subtract the top two variables on the stack
     * Takes the type of the variables as a string
     *
     * | Identifier  | Type   | Byte Size |
     * |------------|--------|-----------|
     * | B          | Byte   | 1         |
     * | S          | Short  | 2         |
     * | I          | Int    | 4         |
     * | J          | Long   | 8         |
     * | F          | Float  | 4         |
     * | D          | Double | 8         |
     * | b          | UByte  | 1         |
     * | s          | UShort | 2         |
     * | i          | UInt   | 4         |
     * | j          | ULong  | 8         |
     *
     * @param type The type of the variables to subtract
     * @since 0.1.0
     * @version 0.1.0
     *
     * @see bdec
     * @see sdec
     * @see idec
     * @see ldec
     * @see fdec
     * @see ddec
     * @see Opcodes.BDEC
     * @see Opcodes.SDEC
     * @see Opcodes.IDEC
     * @see Opcodes.LDEC
     * @see Opcodes.FDEC
     * @see Opcodes.DDEC
     */
    fun dec(type: String) {
        when (type) {
            "B" -> bdec()
            "S" -> sdec()
            "I" -> idec()
            "J" -> ldec()
            "F" -> fdec()
            "D" -> ddec()
            "b" -> bdec()
            "s" -> sdec()
            "i" -> idec()
            "j" -> ldec()
        }
    }

    /**
     * Compare the top two bytes on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bcmp)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BCMP
     */
    fun bcmp() = addByte(Opcodes.BCMP)

    /**
     * Compare the top two shorts on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-scmp)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SCMP
     */
    fun scmp() = addByte(Opcodes.SCMP)

    /**
     * Compare the top two ints on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-icmp)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.ICMP
     */
    fun icmp() = addByte(Opcodes.ICMP)

    /**
     * Compare the top two longs on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lcmp)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LCMP
     */
    fun lcmp() = addByte(Opcodes.LCMP)

    /**
     * Compare the top two floats on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-fcmp)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.FCMP
     */
    fun fcmp() = addByte(Opcodes.FCMP)

    /**
     * Compare the top two doubles on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-dcmp)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.DCMP
     */
    fun dcmp() = addByte(Opcodes.DCMP)

    /**
     * Compare the top two unsigned bytes on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ubcmp)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.UBCMP
     */
    fun ubcmp() = addByte(Opcodes.UBCMP)

    /**
     * Compare the top two unsigned shorts on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-uscmp)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.USCMP
     */
    fun uscmp() = addByte(Opcodes.USCMP)

    /**
     * Compare the top two unsigned ints on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-uicmp)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.UICMP
     */
    fun uicmp() = addByte(Opcodes.UICMP)

    /**
     * Compare the top two unsigned longs on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ulcmp)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.ULCMP
     */
    fun ulcmp() = addByte(Opcodes.ULCMP)

    /**
     * Compare the top two variables on the stack
     * Takes the type of the variables as a string
     *
     * | Identifier  | Type   | Byte Size |
     * |------------|--------|-----------|
     * | B          | Byte   | 1         |
     * | S          | Short  | 2         |
     * | I          | Int    | 4         |
     * | J          | Long   | 8         |
     * | F          | Float  | 4         |
     * | D          | Double | 8         |
     * | b          | UByte  | 1         |
     * | s          | UShort | 2         |
     * | i          | UInt   | 4         |
     * | j          | ULong  | 8         |
     *
     * @param type The type of the variables to compare
     * @since 0.1.0
     * @version 0.1.0
     *
     * @see bcmp
     * @see scmp
     * @see icmp
     * @see lcmp
     * @see fcmp
     * @see dcmp
     * @see ubcmp
     * @see uscmp
     * @see uicmp
     * @see ulcmp
     * @see Opcodes.BCMP
     * @see Opcodes.SCMP
     * @see Opcodes.ICMP
     * @see Opcodes.LCMP
     * @see Opcodes.FCMP
     * @see Opcodes.DCMP
     * @see Opcodes.UBCMP
     * @see Opcodes.USCMP
     * @see Opcodes.UICMP
     * @see Opcodes.ULCMP
     */
    fun cmp(type: String) {
        when (type) {
            "B" -> bcmp()
            "S" -> scmp()
            "I" -> icmp()
            "J" -> lcmp()
            "F" -> fcmp()
            "D" -> dcmp()
            "b" -> ubcmp()
            "s" -> uscmp()
            "i" -> uicmp()
            "j" -> ulcmp()
        }
    }

    /**
     * Adds true to the stack if the comparison result was greater than
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bcmp)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BCMP
     */
    fun cgt() = addByte(Opcodes.CGT)

    /**
     * Adds true to the stack if the comparison result was less than
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bcmp)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BCMP
     */
    fun clt() = addByte(Opcodes.CLT)

    /**
     * Adds true to the stack if the comparison result was greater than or equal to
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bcmp)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BCMP
     */
    fun cge() = addByte(Opcodes.CGE)

    /**
     * Adds true to the stack if the comparison result was less than or equal to
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bcmp)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BCMP
     */
    fun cle() = addByte(Opcodes.CLE)

    /**
     * Adds true to the stack if the comparison result was equal to
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bcmp)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BCMP
     */
    fun ceq() = addByte(Opcodes.CEQ)

    /**
     * Adds true to the stack if the comparison result was not equal to
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bcmp)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BCMP
     */
    fun cne() = addByte(Opcodes.CNE)

    /**
     * Jump to the given address
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jmp)
     *
     * @param address The address to jump to
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.JMP
     */
    fun jmp(address: Int) = addBytes(listOf(Opcodes.JMP, *address.toBytes().toTypedArray()))

    /**
     * Jump to the given address if the top variable byte on the stack is 0
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jz)
     *
     * @param address The address to jump to
     * @since 0.1.0
     * @version 0.1.0
     */
    fun jz(address: Int) = addBytes(listOf(Opcodes.JZ, *address.toBytes().toTypedArray()))

    /**
     * Jump to the given address if the top variable byte on the stack is not 0
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jnz)
     *
     * @param address The address to jump to
     * @since 0.1.0
     * @version 0.1.0
     */
    fun jnz(address: Int) = addBytes(listOf(Opcodes.JNZ, *address.toBytes().toTypedArray()))

    /**
     * Jump to the given address if the comparison result was equal to
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-je)
     *
     * @param address The address to jump to
     * @since 0.1.0
     * @version 0.1.0
     */
    fun je(address: Int) = addBytes(listOf(Opcodes.JE, *address.toBytes().toTypedArray()))

    /**
     * Jump to the given address if the comparison result was not equal to
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jne)
     *
     * @param address The address to jump to
     * @since 0.1.0
     * @version 0.1.0
     */
    fun jne(address: Int) = addBytes(listOf(Opcodes.JNE, *address.toBytes().toTypedArray()))

    /**
     * Jump to the given address if the comparison result was less than
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jl)
     *
     * @param address The address to jump to
     * @since 0.1.0
     * @version 0.1.0
     */
    fun jlt(address: Int) = addBytes(listOf(Opcodes.JL, *address.toBytes().toTypedArray()))

    /**
     * Jump to the given address if the comparison result was less than or equal to
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jle)
     *
     * @param address The address to jump to
     * @since 0.1.0
     * @version 0.1.0
     */
    fun jle(address: Int) = addBytes(listOf(Opcodes.JLE, *address.toBytes().toTypedArray()))

    /**
     * Jump to the given address if the comparison result was greater than
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jg)
     *
     * @param address The address to jump to
     * @since 0.1.0
     * @version 0.1.0
     */
    fun jgt(address: Int) = addBytes(listOf(Opcodes.JG, *address.toBytes().toTypedArray()))

    /**
     * Jump to the given address if the comparison result was greater than or equal to
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jge)
     *
     * @param address The address to jump to
     * @since 0.1.0
     * @version 0.1.0
     */
    fun jge(address: Int) = addBytes(listOf(Opcodes.JGE, *address.toBytes().toTypedArray()))

    /**
     * Jump to the given address
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jmp)
     *
     * @return A placeholder for the address to jump to
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.JMP
     */
    fun jmp(): IntPlaceholder {
        addByte(Opcodes.JMP)
        return iPlaceholder()
    }

    /**
     * Jump to the given address if the top variable byte on the stack is 0
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jz)
     *
     * @return A placeholder for the address to jump to
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun jz(): IntPlaceholder {
        addByte(Opcodes.JZ)
        return iPlaceholder()
    }

    /**
     * Jump to the given address if the top variable byte on the stack is not 0
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jnz)
     *
     * @return A placeholder for the address to jump to
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun jnz(): IntPlaceholder {
        addByte(Opcodes.JNZ)
        return iPlaceholder()
    }

    /**
     * Jump to the given address if the comparison result was equal to
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-je)
     *
     * @return A placeholder for the address to jump to
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun je(): IntPlaceholder {
        addByte(Opcodes.JE)
        return iPlaceholder()
    }

    /**
     * Jump to the given address if the comparison result was not equal to
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jne)
     *
     * @return A placeholder for the address to jump to
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun jne(): IntPlaceholder {
        addByte(Opcodes.JNE)
        return iPlaceholder()
    }

    /**
     * Jump to the given address if the comparison result was less than
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jl)
     *
     * @return A placeholder for the address to jump to
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun jlt(): IntPlaceholder {
        addByte(Opcodes.JL)
        return iPlaceholder()
    }

    /**
     * Jump to the given address if the comparison result was less than or equal to
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jle)
     *
     * @return A placeholder for the address to jump to
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun jle(): IntPlaceholder {
        addByte(Opcodes.JLE)
        return iPlaceholder()
    }

    /**
     * Jump to the given address if the comparison result was greater than
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jg)
     *
     * @return A placeholder for the address to jump to
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun jgt(): IntPlaceholder {
        addByte(Opcodes.JG)
        return iPlaceholder()
    }

    /**
     * Jump to the given address if the comparison result was greater than or equal to
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jge)
     *
     * @return A placeholder for the address to jump to
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun jge(): IntPlaceholder {
        addByte(Opcodes.JGE)
        return iPlaceholder()
    }

    /**
     * Return from the current function
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ret)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BPUSH
     */
    fun ret() = addByte(Opcodes.RET)

    /**
     * Set the top byte on the stack as return value of the function
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bret)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BRET
     */
    fun bret() = addByte(Opcodes.BRET)

    /**
     * Set the top short on the stack as return value of the function
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sret)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SRET
     */
    fun sret() = addByte(Opcodes.SRET)

    /**
     * Set the top int on the stack as return value of the function
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-iret)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.IRET
     */
    fun iret() = addByte(Opcodes.IRET)

    /**
     * Set the top long on the stack as return value of the function
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lret)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LRET
     */
    fun lret() = addByte(Opcodes.LRET)

    /**
     * Return the given type from the current function
     * Takes the type of the return value as a string
     *
     * | Identifier  | Type   | Byte Size |
     * |------------|--------|-----------|
     * | B          | Byte   | 1         |
     * | S          | Short  | 2         |
     * | I          | Int    | 4         |
     * | J          | Long   | 8         |
     * | F          | Float  | 4         |
     * | D          | Double | 8         |
     * | b          | UByte  | 1         |
     * | s          | UShort | 2         |
     * | i          | UInt   | 4         |
     * | j          | ULong  | 8         |
     * | C          | Char   | 2         |
     * | Z          | Bool   | 1         |
     */
    fun ret(type: String) {
        when (type) {
            "B" -> bret()
            "S" -> sret()
            "I" -> iret()
            "J" -> lret()
            "F" -> iret()
            "D" -> lret()
            "b" -> bret()
            "s" -> sret()
            "i" -> iret()
            "j" -> lret()
        }
    }

    /**
     * Pop the top byte on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bpop)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BPOP
     */
    fun pop() = addByte(Opcodes.BPOP)

    /**
     * Pop the top short on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-spop)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SPOP
     */
    fun bpop() = addByte(Opcodes.BPOP)

    /**
     * Pop the top int on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ipop)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.IPOP
     */
    fun spop() = addByte(Opcodes.SPOP)

    /**
     * Pop the top long on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lpop)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LPOP
     */
    fun ipop() = addByte(Opcodes.IPOP)

    /**
     * Pop the top float on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-fpop)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.FPOP
     */
    fun lpop() = addByte(Opcodes.LPOP)

    /**
     * Pop the top variable on the stack
     * Takes the type of the variable as a string
     *
     * | Identifier  | Type   | Byte Size |
     * |------------|--------|-----------|
     * | B          | Byte   | 1         |
     * | S          | Short  | 2         |
     * | I          | Int    | 4         |
     * | J          | Long   | 8         |
     * | F          | Float  | 4         |
     * | D          | Double | 8         |
     * | b          | UByte  | 1         |
     * | s          | UShort | 2         |
     * | i          | UInt   | 4         |
     * | j          | ULong  | 8         |
     *
     * @param type The type of the variable to pop
     * @since 0.1.0
     * @version 0.1.0
     */
    fun pop(type: String) {
        when (type) {
            "B" -> bpop()
            "S" -> spop()
            "I" -> ipop()
            "J" -> lpop()
            "F" -> ipop()
            "D" -> lpop()
            "b" -> bpop()
            "s" -> spop()
            "i" -> ipop()
            "j" -> lpop()
        }
    }

    /**
     * Duplicate the top byte on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bdup)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.BDUP
     */
    fun dup() = addByte(Opcodes.BDUP)

    /**
     * Duplicate the top short on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sdup)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.SDUP
     */
    fun bdup() = addByte(Opcodes.BDUP)

    /**
     * Duplicate the top int on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-idup)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.IDUP
     */
    fun sdup() = addByte(Opcodes.SDUP)

    /**
     * Duplicate the top long on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ldup)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.LDUP
     */
    fun idup() = addByte(Opcodes.IDUP)

    /**
     * Duplicate the top float on the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-fdup)
     *
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.FDUP
     */
    fun ldup() = addByte(Opcodes.LDUP)

    /**
     * Duplicate the top variable on the stack
     * Takes the type of the variable as a string
     *
     * | Identifier  | Type   | Byte Size |
     * |------------|--------|-----------|
     * | B          | Byte   | 1         |
     * | S          | Short  | 2         |
     * | I          | Int    | 4         |
     * | J          | Long   | 8         |
     * | F          | Float  | 4         |
     * | D          | Double | 8         |
     * | b          | UByte  | 1         |
     * | s          | UShort | 2         |
     * | i          | UInt   | 4         |
     * | j          | ULong  | 8         |
     *
     * @param type The type of the variable to duplicate
     * @since 0.1.0
     * @version 0.1.0
     */
    fun dup(type: String) {
        when (type) {
            "B" -> bdup()
            "S" -> sdup()
            "I" -> idup()
            "J" -> ldup()
            "F" -> idup()
            "D" -> ldup()
            "b" -> bdup()
            "s" -> sdup()
            "i" -> idup()
            "j" -> ldup()
        }
    }

    /**
     * Perform a cast operation on the top of the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-pcast)
     *
     * @param from The type to cast from
     * @param to The type to cast to
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.PCAST
     */
    fun pcast(from: UByte, to: UByte) = addBytes(Opcodes.PCAST, ((from shl 4) or to).toByte())

    /**
     * Call a function
     * The address int should point to an utf8 constant pool entry
     * in the constant pool
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-invoke-static)
     *
     * @param address The address of the function to call
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.INVOKE_STATIC
     */
    fun invoke_static(address: Int) = addBytes(listOf(Opcodes.INVOKE_STATIC, *address.toBytes().toTypedArray()))

    /**
     * Call a function
     * The address int should point to an utf8 constant pool entry
     * in the constant pool
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-invoke-static)
     *
     * @return A placeholder for the address of the function to call
     * @since 0.1.0
     * @version 0.1.0
     * @see Opcodes.INVOKE_STATIC
     */
    fun invoke_static(): IntPlaceholder {
        addByte(Opcodes.INVOKE_STATIC)
        return iPlaceholder()
    }

    /**
     * Output a [ByteArray] of the bytecode
     *
     * @return The bytecode as a [ByteArray]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toByteArray() = bytes.toByteArray()

    /**
     * Create a byte placeholder
     *
     * @return A [BytePlaceholder] for the next byte
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun bPlaceholder(): BytePlaceholder {
        val index = bytes.size
        addByte(0)
        return BytePlaceholder(index)
    }

    /**
     * Create a short placeholder
     *
     * @return A [ShortPlaceholder] for the next short
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun sPlaceholder(): ShortPlaceholder {
        val index = bytes.size
        addBytes(0, 0)
        return ShortPlaceholder(index)
    }

    /**
     * Create an int placeholder
     *
     * @return An [IntPlaceholder] for the next int
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun iPlaceholder(): IntPlaceholder {
        val index = bytes.size
        addBytes(0, 0, 0, 0)
        return IntPlaceholder(index)
    }

    /**
     * Create a long placeholder
     *
     * @return A [LongPlaceholder] for the next long
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun lPlaceholder(): LongPlaceholder {
        val index = bytes.size
        addBytes(0, 0, 0, 0, 0, 0, 0, 0)
        return LongPlaceholder(index)
    }

    /**
     * Byte placeholder
     *
     * @param index The index of the byte in the bytecode
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    inner class BytePlaceholder(

        /**
         * The index of the byte in the bytecode
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val index: Int,

    ) {

        /**
         * Set the byte at the index
         *
         * @param byte The byte to set
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun set(byte: Byte) {
            bytes[index] = byte
        }

        /**
         * Set the byte at the index
         *
         * @param unsignedByte The unsigned byte to set
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun set(boolean: Boolean) {
            bytes[index] = if (boolean) 1 else 0
        }

        /**
         * Set the byte at the index
         *
         * @param unsignedByte The unsigned byte to set
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun set(unsignedByte: UByte) {
            bytes[index] = unsignedByte.toByte()
        }
    }

    /**
     * Short placeholder
     *
     * @param index The index of the short in the bytecode
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    inner class ShortPlaceholder(

        /**
         * The index of the short in the bytecode
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val index: Int,
    ) {

        /**
         * Set the short at the index
         *
         * @param short The short to set
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun set(short: Short) {
            bytes.setBytes(index, short.toBytes())
        }

        /**
         * Set the short at the index
         *
         * @param unsignedShort The unsigned short to set
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun set(unsignedShort: UShort) {
            bytes.setBytes(index, unsignedShort.toBytes())
        }

        /**
         * Set the short at the index
         *
         * @param char The char to set
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun set(char: Char) {
            bytes.setBytes(index, char.code.toShort().toBytes())
        }
    }

    /**
     * Int placeholder
     *
     * @param index The index of the int in the bytecode
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    inner class IntPlaceholder(

        /**
         * The index of the int in the bytecode
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val index: Int,
    ) {

        /**
         * Set the int at the index
         *
         * @param int The int to set
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun set(int: Int) {
            bytes.setBytes(index, int.toBytes())
        }

        /**
         * Set the int at the index
         *
         * @param unsignedInt The unsigned int to set
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun set(unsignedInt: UInt) {
            bytes.setBytes(index, unsignedInt.toBytes())
        }

        /**
         * Set the int at the index
         *
         * @param float The float to set
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun set(float: Float) {
            bytes.setBytes(index, float.toRawBits().toBytes())
        }
    }

    /**
     * Long placeholder
     *
     * @param index The index of the long in the bytecode
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    inner class LongPlaceholder(

        /**
         * The index of the long in the bytecode
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val index: Int,
    ) {

        /**
         * Set the long at the index
         *
         * @param long The long to set
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun set(long: Long) {
            bytes.setBytes(index, long.toBytes())
        }

        /**
         * Set the long at the index
         *
         * @param unsignedLong The unsigned long to set
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun set(unsignedLong: ULong) {
            bytes.setBytes(index, unsignedLong.toBytes())
        }

        /**
         * Set the long at the index
         *
         * @param double The double to set
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun set(double: Double) {
            bytes.setBytes(index, double.toRawBits().toBytes())
        }
    }
}

/**
 * A [ShakeBytecodeInstructionGenerator] that uses a [MutableConstantPool]
 *
 * @param constantPool The constant pool to use
 * @param bytes The bytes to use
 *
 * @since 0.1.0
 * @version 0.1.0
 */
@Suppress("ktlint:standard:function-naming")
open class PooledShakeBytecodeInstructionGenerator(
    val constantPool: MutableConstantPool,
    bytes: MutableList<Byte> = mutableListOf<Byte>(),
) : ShakeBytecodeInstructionGenerator(
    bytes,
) {

    /**
     * Add a reference to a constant pool entry
     *
     * @param value The value to add
     * @since 0.1.0
     * @version 0.1.0
     */
    fun utf8Ref(value: String) = addBytes(*constantPool.resolveUtf8(value).toBytes())

    /**
     * Add a reference to a constant pool entry
     *
     * @param value The value to add
     * @since 0.1.0
     * @version 0.1.0
     */
    fun byteRef(value: Byte) = addBytes(*constantPool.resolveByte(value).toBytes())

    /**
     * Add a reference to a constant pool entry
     *
     * @param value The value to add
     * @since 0.1.0
     * @version 0.1.0
     */
    fun shortRef(value: Short) = addBytes(*constantPool.resolveShort(value).toBytes())

    /**
     * Add a reference to a constant pool entry
     *
     * @param value The value to add
     * @since 0.1.0
     * @version 0.1.0
     */
    fun intRef(value: Int) = addBytes(*constantPool.resolveInt(value).toBytes())

    /**
     * Add a reference to a constant pool entry
     *
     * @param value The value to add
     * @since 0.1.0
     * @version 0.1.0
     */
    fun longRef(value: Long) = addBytes(*constantPool.resolveLong(value).toBytes())

    /**
     * Add a reference to a constant pool entry
     *
     * @param value The value to add
     * @since 0.1.0
     * @version 0.1.0
     */
    fun floatRef(value: Float) = addBytes(*constantPool.resolveFloat(value).toBytes())

    /**
     * Add a reference to a constant pool entry
     *
     * @param value The value to add
     * @since 0.1.0
     * @version 0.1.0
     */
    fun doubleRef(value: Double) = addBytes(*constantPool.resolveDouble(value).toBytes())

    /**
     * Add a reference to a constant pool entry
     *
     * @param value The value to add
     * @since 0.1.0
     * @version 0.1.0
     */
    fun classRef(value: String) = addBytes(*constantPool.resolveClass(value).toBytes())

    fun invoke_static(descriptor: String) {
        addByte(Opcodes.INVOKE_STATIC)
        utf8Ref(descriptor)
    }
}

/**
 * Generate bytecode instructions using a [ShakeBytecodeInstructionGenerator]
 *
 * @param init The init function
 * @return The bytecode as a [ByteArray]
 *
 * @since 0.1.0
 * @version 0.1.0
 * @see ShakeBytecodeInstructionGenerator
 */
fun bytecode(init: ShakeBytecodeInstructionGenerator.() -> Unit): ByteArray {
    val generator = ShakeBytecodeInstructionGenerator()
    generator.init()
    return generator.toByteArray()
}

/**
 * Generate bytecode instructions using a [PooledShakeBytecodeInstructionGenerator]
 *
 * @param constantPool The constant pool to use
 * @param init The init function
 * @return The bytecode as a [ByteArray]
 *
 * @since 0.1.0
 * @version 0.1.0
 * @see PooledShakeBytecodeInstructionGenerator
 */
fun bytecode(
    constantPool: MutableConstantPool,
    init: PooledShakeBytecodeInstructionGenerator.() -> Unit,
): ByteArray {
    val generator = PooledShakeBytecodeInstructionGenerator(constantPool)
    generator.init()
    return generator.toByteArray()
}
