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

    fun createCodeInterpreter(code: ByteArray, localsSize: Int): ShakeCodeInterpreter {
        return ShakeCodeInterpreter(code, localsSize)
    }

    inner class ShakeCodeInterpreter(
        val code: ByteArray,
        localsSize: Int
    ) {

        val locals = ByteArray(localsSize)

        var pc = 0

        private fun readByte(): Byte {
            val byte = code[pc]
            pc++
            return byte
        }

        private fun readShort(): Short {
            val v = code.getShort(pc)
            pc += 2
            return v
        }
        private fun readInt(): Int {
            val v = code.getInt(pc)
            pc += 4
            return v
        }
        private fun readLong(): Long {
            val v = code.getLong(pc)
            pc += 8
            return v
        }

        private fun readUByte(): UByte {
            val v = code.getUnsignedByte(pc)
            pc++
            return v
        }

        private fun readUShort(): UShort {
            val v = code.getUnsignedShort(pc)
            pc += 2
            return v
        }

        private fun readUInt(): UInt {
            val v = code.getUnsignedInt(pc)
            pc += 4
            return v
        }

        private fun readULong(): ULong {
            val v = code.getUnsignedLong(pc)
            pc += 8
            return v
        }

        fun tick(times: Int) {
            for (i in 0 until times) tick()
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
                    val pos = readUShort().toInt()
                    stack.push(locals[pos])
                    stack.push(locals[pos + 1])
                }
                Opcodes.ILOAD -> {
                    val pos = readUShort().toInt()
                    for (i in 0 until 4) stack.push(locals[pos + i])
                }
                Opcodes.LLOAD -> {
                    val pos = readUShort().toInt()
                    for (i in 0 until 8) stack.push(locals[pos + i])
                }

                Opcodes.BSTORE -> locals[readUShort().toInt()] = stack.pop()
                Opcodes.SSTORE -> {
                    val pos = readUShort().toInt()
                    locals[pos+1] = stack.pop()
                    locals[pos] = stack.pop()
                }
                Opcodes.ISTORE -> {
                    val pos = readUShort().toInt()
                    for (i in 3 downTo  0) locals[pos + i] = stack.pop()
                }
                Opcodes.LSTORE -> {
                    val pos = readUShort().toInt()
                    for (i in 7 downTo 0) locals[pos + i] = stack.pop()
                }

                Opcodes.BADD -> stack.push(stack.pop() + stack.pop())
                Opcodes.SADD -> stack.push(stack.popShort() + stack.popShort())
                Opcodes.IADD -> stack.push(stack.popInt() + stack.popInt())
                Opcodes.LADD -> stack.push(stack.popLong() + stack.popLong())
                Opcodes.FADD -> stack.push(stack.popFloat() + stack.popFloat())
                Opcodes.DADD -> stack.push(stack.popDouble() + stack.popDouble())

                Opcodes.BSUB -> {
                    val a = stack.pop()
                    val b = stack.pop()
                    stack.push(b - a)
                }
                Opcodes.SSUB -> {
                    val a = stack.popShort()
                    val b = stack.popShort()
                    stack.push(b - a)
                }
                Opcodes.ISUB -> {
                    val a = stack.popInt()
                    val b = stack.popInt()
                    stack.push(b - a)
                }
                Opcodes.LSUB -> {
                    val a = stack.popLong()
                    val b = stack.popLong()
                    stack.push(b - a)
                }
                Opcodes.UBSUB -> {
                    val a = stack.popUByte()
                    val b = stack.popUByte()
                    stack.push((b - a).toByte())
                }
                Opcodes.USSUB -> {
                    val a = stack.popUShort()
                    val b = stack.popUShort()
                    stack.push((b - a).toShort())
                }
                Opcodes.UISUB -> {
                    val a = stack.popUInt()
                    val b = stack.popUInt()
                    stack.push((b - a).toInt())
                }
                Opcodes.ULSUB -> {
                    val a = stack.popULong()
                    val b = stack.popULong()
                    stack.push((b - a).toLong())
                }
                Opcodes.FSUB -> {
                    val a = stack.popFloat()
                    val b = stack.popFloat()
                    stack.push(b - a)
                }
                Opcodes.DSUB -> {
                    val a = stack.popDouble()
                    val b = stack.popDouble()
                    stack.push(b - a)
                }

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

                Opcodes.BDIV -> {
                    val a = stack.pop()
                    val b = stack.pop()
                    stack.push(b / a)
                }
                Opcodes.SDIV -> {
                    val a = stack.popShort()
                    val b = stack.popShort()
                    stack.push(b / a)
                }
                Opcodes.IDIV -> {
                    val a = stack.popInt()
                    val b = stack.popInt()
                    stack.push(b / a)
                }
                Opcodes.LDIV -> {
                    val a = stack.popLong()
                    val b = stack.popLong()
                    stack.push(b / a)
                }
                Opcodes.FDIV -> {
                    val a = stack.popFloat()
                    val b = stack.popFloat()
                    stack.push(b / a)
                }
                Opcodes.DDIV -> {
                    val a = stack.popDouble()
                    val b = stack.popDouble()
                    stack.push(b / a)
                }
                Opcodes.UBDIV -> {
                    val a = stack.popUByte()
                    val b = stack.popUByte()
                    stack.push((b / a).toByte())
                }
                Opcodes.USDIV -> {
                    val a = stack.popUShort()
                    val b = stack.popUShort()
                    stack.push((b / a).toShort())
                }
                Opcodes.UIDIV -> {
                    val a = stack.popUInt()
                    val b = stack.popUInt()
                    stack.push((b / a).toInt())
                }
                Opcodes.ULDIV -> {
                    val a = stack.popULong()
                    val b = stack.popULong()
                    stack.push((b / a).toLong())
                }

                Opcodes.BMOD -> {
                    val a = stack.pop()
                    val b = stack.pop()
                    stack.push(b % a)
                }
                Opcodes.SMOD -> {
                    val a = stack.popShort()
                    val b = stack.popShort()
                    stack.push(b % a)
                }
                Opcodes.IMOD -> {
                    val a = stack.popInt()
                    val b = stack.popInt()
                    stack.push(b % a)
                }
                Opcodes.LMOD -> {
                    val a = stack.popLong()
                    val b = stack.popLong()
                    stack.push(b % a)
                }
                Opcodes.UBMOD -> {
                    val a = stack.popUByte()
                    val b = stack.popUByte()
                    stack.push((b % a).toByte())
                }
                Opcodes.USMOD -> {
                    val a = stack.popUShort()
                    val b = stack.popUShort()
                    stack.push((b % a).toShort())
                }
                Opcodes.UIMOD -> {
                    val a = stack.popUInt()
                    val b = stack.popUInt()
                    stack.push((b % a).toInt())
                }
                Opcodes.ULMOD -> {
                    val a = stack.popULong()
                    val b = stack.popULong()
                    stack.push((b % a).toLong())
                }
                Opcodes.FMOD -> {
                    val a = stack.popFloat()
                    val b = stack.popFloat()
                    stack.push(b % a)
                }
                Opcodes.DMOD -> {
                    val a = stack.popDouble()
                    val b = stack.popDouble()
                    stack.push(b % a)
                }

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

                Opcodes.BSHL -> {
                    val a = stack.pop()
                    val b = stack.pop()
                    stack.push(b shl a)
                }
                Opcodes.SSHL -> {
                    val a = stack.popShort()
                    val b = stack.popShort()
                    stack.push(b shl a)
                }
                Opcodes.ISHL -> {
                    val a = stack.popInt()
                    val b = stack.popInt()
                    stack.push(b shl a)
                }
                Opcodes.LSHL -> {
                    val a = stack.popLong()
                    val b = stack.popLong()
                    stack.push(b shl a)
                }

                Opcodes.BSHR -> {
                    val a = stack.pop()
                    val b = stack.pop()
                    stack.push(b shr a)
                }
                Opcodes.SSHR -> {
                    val a = stack.popShort()
                    val b = stack.popShort()
                    stack.push(b shr a)
                }
                Opcodes.ISHR -> {
                    val a = stack.popInt()
                    val b = stack.popInt()
                    stack.push(b shr a)
                }
                Opcodes.LSHR -> {
                    val a = stack.popLong()
                    val b = stack.popLong()
                    stack.push(b shr a)
                }

                Opcodes.BSHRU -> {
                    val a = stack.pop()
                    val b = stack.pop()
                    stack.push(b ushr a)
                }
                Opcodes.SSHRU -> {
                    val a = stack.popShort()
                    val b = stack.popShort()
                    stack.push(b ushr a)
                }
                Opcodes.ISHRU -> {
                    val a = stack.popInt()
                    val b = stack.popInt()
                    stack.push(b ushr a)
                }
                Opcodes.LSHRU -> {
                    val a = stack.popLong()
                    val b = stack.popLong()
                    stack.push(b ushr a)
                }

                Opcodes.PCAST -> {
                    // First 4 bits are the "from" type, last 4 bits are the "to" type
                    // See CastUtil.kt
                    val type = readUByte()
                    CastUtil.performCast(stack, type)
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