package com.shakelang.shake.bytecode.interpreter

import com.shakelang.shake.util.primitives.bytes.*
import com.shakelang.shake.util.primitives.calc.shl
import com.shakelang.shake.util.primitives.calc.shr
import com.shakelang.shake.util.primitives.calc.ushr
import kotlin.experimental.and
import kotlin.experimental.inv
import kotlin.experimental.or
import kotlin.experimental.xor

class ShakeInterpreter {

    val stack = ByteStack(1024 * 1024 * 10) // 10 MB stack size

    fun tick() {

    }

    inner class ShakeCodeInterpreter(
        val code: ByteArray,
        localsSize: Int
    ) {

        val locals = ByteArray(localsSize)

        var pc = 0

        fun readByte(): Byte {
            val byte = code[pc]
            pc++
            return byte
        }

        fun readShort(): Short {
            val v = code.getShort(pc)
            pc += 2
            return v
        }
        fun readInt(): Int {
            val v = code.getInt(pc)
            pc += 4
            return v
        }
        fun readLong(): Long {
            val v = code.getLong(pc)
            pc += 8
            return v
        }

        fun readUByte(): UByte {
            val v = code.getUnsignedByte(pc)
            pc++
            return v
        }

        fun readUShort(): UShort {
            val v = code.getUnsignedShort(pc)
            pc += 2
            return v
        }

        fun readUInt(): UInt {
            val v = code.getUnsignedInt(pc)
            pc += 4
            return v
        }

        fun readULong(): ULong {
            val v = code.getUnsignedLong(pc)
            pc += 8
            return v
        }



        fun tick() {
            val opcode = readByte()
            when (opcode) {
                Opcodes.NOP -> {
                    // do nothing
                }

                // Pushing values to the stack
                Opcodes.BPUSH -> stack.push(readByte())
                Opcodes.SPUSH -> stack.push(readShort())
                Opcodes.IPUSH -> stack.push(readInt())
                Opcodes.LPUSH -> stack.push(readLong())

                Opcodes.BLOAD -> stack.push(locals[readUShort().toInt()])
                Opcodes.SLOAD -> {
                    stack.push(locals[readUShort().toInt()])
                    stack.push(locals[readUShort().toInt() + 1])
                }
                Opcodes.ILOAD -> {
                    for (i in 0 until 4) stack.push(locals[readUShort().toInt() + i])
                }
                Opcodes.LLOAD -> {
                    for (i in 0 until 8) stack.push(locals[readUShort().toInt() + i])
                }

                Opcodes.BSTORE -> locals[readUShort().toInt()] = stack.pop()
                Opcodes.SSTORE -> {
                    locals[readUShort().toInt()] = stack.pop()
                    locals[readUShort().toInt() + 1] = stack.pop()
                }
                Opcodes.ISTORE -> {
                    for (i in 0 until 4) locals[readUShort().toInt() + i] = stack.pop()
                }
                Opcodes.LSTORE -> {
                    for (i in 0 until 8) locals[readUShort().toInt() + i] = stack.pop()
                }

                Opcodes.BADD -> stack.push(stack.pop() + stack.pop())
                Opcodes.SADD -> stack.push(stack.popShort() + stack.popShort())
                Opcodes.IADD -> stack.push(stack.popInt() + stack.popInt())
                Opcodes.LADD -> stack.push(stack.popLong() + stack.popLong())
                Opcodes.FADD -> stack.push(stack.popFloat() + stack.popFloat())
                Opcodes.DADD -> stack.push(stack.popDouble() + stack.popDouble())

                Opcodes.BSUB -> stack.push(stack.pop() - stack.pop())
                Opcodes.SSUB -> stack.push(stack.popShort() - stack.popShort())
                Opcodes.ISUB -> stack.push(stack.popInt() - stack.popInt())
                Opcodes.LSUB -> stack.push(stack.popLong() - stack.popLong())
                Opcodes.UBSUB -> stack.push((stack.popUByte() - stack.popUByte()).toByte())
                Opcodes.USSUB -> stack.push((stack.popUShort() - stack.popUShort()).toShort())
                Opcodes.UISUB -> stack.push((stack.popUInt() - stack.popUInt()).toInt())
                Opcodes.ULSUB -> stack.push((stack.popULong() - stack.popULong()).toLong())
                Opcodes.FSUB -> stack.push(stack.popFloat() - stack.popFloat())
                Opcodes.DSUB -> stack.push(stack.popDouble() - stack.popDouble())

                Opcodes.BMUL -> stack.push(stack.pop() * stack.pop())
                Opcodes.SMUL -> stack.push(stack.popShort() * stack.popShort())
                Opcodes.IMUL -> stack.push(stack.popInt() * stack.popInt())
                Opcodes.LMUL -> stack.push(stack.popLong() * stack.popLong())
                Opcodes.FMUL -> stack.push(stack.popFloat() * stack.popFloat())
                Opcodes.DMUL -> stack.push(stack.popDouble() * stack.popDouble())
                Opcodes.UBMUL -> stack.push((stack.popUByte() * stack.popUByte()).toByte())
                Opcodes.USMUL -> stack.push((stack.popUShort() * stack.popUShort()).toShort())
                Opcodes.UIMUL -> stack.push((stack.popUInt() * stack.popUInt()).toInt())
                Opcodes.ULMUL -> stack.push((stack.popULong() * stack.popULong()).toLong())

                Opcodes.BDIV -> stack.push(stack.pop() / stack.pop())
                Opcodes.SDIV -> stack.push(stack.popShort() / stack.popShort())
                Opcodes.IDIV -> stack.push(stack.popInt() / stack.popInt())
                Opcodes.LDIV -> stack.push(stack.popLong() / stack.popLong())
                Opcodes.FDIV -> stack.push(stack.popFloat() / stack.popFloat())
                Opcodes.DDIV -> stack.push(stack.popDouble() / stack.popDouble())
                Opcodes.UBDIV -> stack.push((stack.popUByte() / stack.popUByte()).toByte())
                Opcodes.USDIV -> stack.push((stack.popUShort() / stack.popUShort()).toShort())
                Opcodes.UIDIV -> stack.push((stack.popUInt() / stack.popUInt()).toInt())
                Opcodes.ULDIV -> stack.push((stack.popULong() / stack.popULong()).toLong())

                Opcodes.BMOD -> stack.push(stack.pop() % stack.pop())
                Opcodes.SMOD -> stack.push(stack.popShort() % stack.popShort())
                Opcodes.IMOD -> stack.push(stack.popInt() % stack.popInt())
                Opcodes.LMOD -> stack.push(stack.popLong() % stack.popLong())
                Opcodes.UBMOD -> stack.push((stack.popUByte() % stack.popUByte()).toByte())
                Opcodes.USMOD -> stack.push((stack.popUShort() % stack.popUShort()).toShort())
                Opcodes.UIMOD -> stack.push((stack.popUInt() % stack.popUInt()).toInt())
                Opcodes.ULMOD -> stack.push((stack.popULong() % stack.popULong()).toLong())

                Opcodes.BAND -> stack.push(stack.pop() and stack.pop())
                Opcodes.SAND -> stack.push(stack.popShort() and stack.popShort())
                Opcodes.IAND -> stack.push(stack.popInt() and stack.popInt())
                Opcodes.LAND -> stack.push(stack.popLong() and stack.popLong())

                Opcodes.BOR -> stack.push(stack.pop() or stack.pop())
                Opcodes.SOR -> stack.push(stack.popShort() or stack.popShort())
                Opcodes.IOR -> stack.push(stack.popInt() or stack.popInt())
                Opcodes.LOR -> stack.push(stack.popLong() or stack.popLong())

                Opcodes.BXOR -> stack.push(stack.pop() xor stack.pop())
                Opcodes.SXOR -> stack.push(stack.popShort() xor stack.popShort())
                Opcodes.IXOR -> stack.push(stack.popInt() xor stack.popInt())
                Opcodes.LXOR -> stack.push(stack.popLong() xor stack.popLong())

                Opcodes.BNOT -> stack.push(stack.pop().inv())
                Opcodes.SNOT -> stack.push(stack.popShort().inv())
                Opcodes.INOT -> stack.push(stack.popInt().inv())
                Opcodes.LNOT -> stack.push(stack.popLong().inv())

                Opcodes.BSHL -> stack.push(stack.pop() shl stack.pop())
                Opcodes.SSHL -> stack.push(stack.popShort() shl stack.pop())
                Opcodes.ISHL -> stack.push(stack.popInt() shl stack.pop())
                Opcodes.LSHL -> stack.push(stack.popLong() shl stack.pop())

                Opcodes.BSHR -> stack.push(stack.pop() shr stack.pop())
                Opcodes.SSHR -> stack.push(stack.popShort() shr stack.pop())
                Opcodes.ISHR -> stack.push(stack.popInt() shr stack.pop())
                Opcodes.LSHR -> stack.push(stack.popLong() shr stack.pop())

                Opcodes.BSHRU -> stack.push(stack.popUByte() ushr stack.popUByte())
                Opcodes.SSHRU -> stack.push(stack.popUShort() ushr stack.popUShort())
                Opcodes.ISHRU -> stack.push(stack.popUInt() ushr stack.popUInt())
                Opcodes.LSHRU -> stack.push(stack.popULong() ushr stack.popULong())

                Opcodes.PCAST -> {
                    val type = readByte()
                    // First 4 bits are the "from" type, last 4 bits are the "to" type
                }

            }
        }

    }

}


object ClassRegister {

    private val classes = mutableMapOf<String, ShakeInterpreterClass>()

    fun registerClass(name: String, clazz: ShakeInterpreterClass) {
        classes[name] = clazz
    }
    fun getClass(name: String): ShakeInterpreterClass? = classes[name]

}

class ShakeInterpreterClass (
    val methods : List<ShakeInterpreterMethod>,
    val fields : List<ShakeInterpreterField>,
) {

}

class ShakeInterpreterMethod (
    val qualifiedName: String,
    val isStatic: Boolean
) {

}

class ShakeInterpreterField (val name: String) {
}

open class ShakeInterpreterType (
    val name: String,
    val type: Type,
    val subType : ShakeInterpreterType? = null,
    val classType: ShakeInterpreterClass? = null
) {

    enum class Type {
        INT,
        FLOAT,
        DOUBLE,
        LONG,
        SHORT,
        BYTE,
        CHAR,
        BOOLEAN,
        STRING,
        OBJECT,
        ARRAY,
        VOID
    }

}