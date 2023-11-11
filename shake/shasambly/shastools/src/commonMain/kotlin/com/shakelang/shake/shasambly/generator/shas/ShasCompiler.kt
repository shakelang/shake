@file:Suppress("unused")

package com.shakelang.shake.shasambly.generator.shas

import io.github.shakelang.shake.shasambly.interpreter.Opcodes
import io.github.shakelang.shake.shasambly.interpreter.natives.Natives
import io.github.shakelang.shake.shasambly.interpreter.natives.nativeFunctions
import io.github.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import io.github.shakelang.shake.util.io.streaming.output.DataOutputStream
import io.github.shakelang.shake.util.io.streaming.output.OutputStream
import io.github.shakelang.shake.util.parseutils.characters.Characters.isHexCharacter
import io.github.shakelang.shake.util.parseutils.characters.Characters.isIdentifierCharacter
import io.github.shakelang.shake.util.parseutils.characters.Characters.isIdentifierStartCharacter
import io.github.shakelang.shake.util.parseutils.characters.Characters.isNumberCharacter
import io.github.shakelang.shake.util.parseutils.characters.Characters.isNumberOrDotCharacter
import io.github.shakelang.shake.util.parseutils.characters.streaming.CharacterInputStream

class ShasCompiler(private val input: CharacterInputStream) {

    private lateinit var out: DataOutputStream

    fun parse(): ByteArray {
        val out = ByteArrayOutputStream()
        this.out = DataOutputStream(out)

        doParse()

        return out.toByteArray()
    }

    fun dumpParse(out: OutputStream) {
        this.out = DataOutputStream(out)
        doParse()
    }

    private fun doParse() {
        Natives.initNativeFunctions()
        while (input.hasNext()) {
            expectCommand()
            skipIgnored()
        }
    }

    private fun skipIgnored() {
        while (input.hasNext()) {
            val next = input.peek()
            if (next.isWhitespace()) {
                input.skip()
            } else if (next == '[') {
                do input.skip() while (input.actual() != ']')
            } else {
                break
            }
        }
    }

    private fun isHex() = input.has(2) && input.peek() == '0' && input.peek(2) == 'x'

    private fun expectHexNumber(): String {
        skipIgnored()
        if (input.next() != '0' || !input.hasNext() || input.next() != 'x') throw IllegalStateException("Hex numbers have to start with '0x'")
        val number = StringBuilder()
        while (input.hasNext() && isHexCharacter(input.peek())) {
            number.append(input.next())
        }
        if (number.isEmpty()) throw IllegalStateException("Hex number to short")
        return number.toString()
    }

    private fun expectNumber(): String {
        skipIgnored()
        val number = StringBuilder()
        while (input.hasNext() && isNumberCharacter(input.peek())) {
            number.append(input.next())
        }
        if (number.isEmpty()) throw IllegalStateException("Hex number to short")
        return number.toString()
    }

    private fun expectFloatingPointNumber(): String {
        skipIgnored()
        val number = StringBuilder()
        var dotCount = 0
        while (input.hasNext() && isNumberOrDotCharacter(input.next())) {
            if (input.next() == '.') dotCount++
            if (dotCount > 1) throw IllegalStateException("There must not be more then one dots in a floating point number")
            number.append(input.next())
        }
        if (number.isEmpty() || (number.length == 1 && number[0] == '.')) throw IllegalStateException("Hex number to short")
        return number.toString()
    }

    private fun expectByte(): Byte {
        skipIgnored()
        val neg = input.peek() == '-'
        if (neg) input.skip()
        return if (isHex()) {
            if (neg) "-${expectHexNumber()}".toByte() else expectHexNumber().toUByte(16).toByte()
        } else if (neg) "-${expectNumber()}".toByte() else expectNumber().toUByte().toByte()
    }

    private fun expectShort(): Short {
        skipIgnored()
        val neg = input.peek() == '-'
        if (neg) input.skip()
        return if (isHex()) {
            if (neg) "-${expectHexNumber()}".toShort() else expectHexNumber().toUShort(16).toShort()
        } else if (neg) "-${expectNumber()}".toShort() else expectNumber().toUShort().toShort()
    }

    private fun expectInt(): Int {
        skipIgnored()
        val neg = input.peek() == '-'
        if (neg) input.skip()
        return if (isHex()) {
            if (neg) "-${expectHexNumber()}".toInt() else expectHexNumber().toUInt(16).toInt()
        } else if (neg) "-${expectNumber()}".toInt() else expectNumber().toUInt().toInt()
    }

    private fun expectLong(): Long {
        skipIgnored()
        val neg = input.peek() == '-'
        if (neg) input.skip()
        return if (isHex()) {
            if (neg) "-${expectHexNumber()}".toLong() else expectHexNumber().toULong(16).toLong()
        } else if (neg) "-${expectNumber()}".toLong() else expectNumber().toULong().toLong()
    }

    private fun expectUByte(): UByte {
        skipIgnored()
        return if (isHex()) {
            expectHexNumber().toUByte(16)
        } else {
            expectNumber().toUByte()
        }
    }

    private fun expectUShort(): UShort {
        skipIgnored()
        return if (isHex()) {
            expectHexNumber().toUShort(16)
        } else {
            expectNumber().toUShort()
        }
    }

    private fun expectUInt(): UInt {
        skipIgnored()
        return if (isHex()) {
            expectHexNumber().toUInt(16)
        } else {
            expectNumber().toUInt()
        }
    }

    private fun expectULong(): ULong {
        skipIgnored()
        return if (isHex()) {
            expectHexNumber().toULong(16)
        } else {
            expectNumber().toULong()
        }
    }

    private fun expectFloat(): Float {
        skipIgnored()
        val neg = input.peek() == '-'
        if (neg) input.skip()
        return if (isHex()) {
            Float.fromBits(if (neg) "-${expectHexNumber()}".toInt() else expectHexNumber().toUInt(16).toInt())
        } else {
            (if (neg) "-" else "" + expectFloatingPointNumber()).toFloat()
        }
    }

    private fun expectDouble(): Double {
        skipIgnored()
        val neg = input.peek() == '-'
        if (neg) input.skip()
        return if (isHex()) {
            Double.fromBits(if (neg) "-${expectHexNumber()}".toLong() else expectHexNumber().toULong(16).toLong())
        } else {
            (if (neg) "-" else "" + expectFloatingPointNumber()).toDouble()
        }
    }

    private fun expectBytes(byteArgumentAmount: Int): ByteArray {
        skipIgnored()
        if (byteArgumentAmount <= 0) return byteArrayOf()
        val number = StringBuilder()
        while (input.hasNext() && isHexCharacter(input.peek())) {
            number.append(input.next())
        }
        val byteString = number.toString()
        if (number.length % 2 != 0) throw IllegalStateException("Expecting an even number of hex chars in byte array")
        if (byteString.length / 2 != byteArgumentAmount) {
            throw IllegalStateException("Wrong number of bytes given, expect $byteArgumentAmount, but got ${byteString.length / 2}")
        }
        return ByteArray(byteString.length / 2) {
            byteString.substring(it * 2, it * 2 + 1).toUByte(16).toByte()
        }
    }

    private fun makeIdentifier(): String {
        val cmd = StringBuilder()
        while (isIdentifierCharacter(input.peek())) {
            cmd.append(input.next())
        }
        return cmd.toString()
    }

    private fun expectNativeFunction(): Short {
        skipIgnored()
        if (isIdentifierStartCharacter(input.peek())) {
            val identifier = makeIdentifier()
            for (i in nativeFunctions.indices) {
                val f = nativeFunctions[i]
                if (f != null && f.name.equals(identifier, ignoreCase = true)) return i.toUShort().toShort()
            }
            throw IllegalArgumentException("Unknown native function $identifier")
        } else {
            return expectUShort().toShort()
        }
    }

    private fun expectCommand() {
        skipIgnored()
        val cmd = makeIdentifier()
        if (cmd.isEmpty()) throw Error("Expecting Command")
        when (cmd.uppercase()) {
            "INCR_STACK" -> {
                out.writeByte(Opcodes.INCR_STACK)
                out.writeUnsignedShort(expectUShort())
            }

            "DECR_STACK" -> out.writeByte(Opcodes.DECR_STACK)
            "JUMP_STATIC" -> {
                out.writeByte(Opcodes.JUMP_STATIC)
                out.writeUnsignedInt(expectUInt())
            }

            "JUMP_DYNAMIC" -> out.writeByte(Opcodes.JUMP_DYNAMIC)
            "JUMP_IF" -> {
                out.writeByte(Opcodes.JUMP_IF)
                out.writeUnsignedInt(expectUInt())
            }

            "NATIVE", "INVOKE", "CALL", "INVOKE_NATIVE" -> {
                out.writeByte(Opcodes.INVOKE_NATIVE)
                val function = expectNativeFunction()
                out.writeShort(function)
                out.writeByteArray(expectBytes(nativeFunctions[function.toInt()]!!.byteArgumentAmount))
            }

            "GLOB_ADDR" -> {
                out.writeByte(Opcodes.GLOB_ADDR)
                out.writeUnsignedShort(expectUShort())
            }

            "B_GET_LOCAL" -> {
                out.writeByte(Opcodes.B_GET_LOCAL)
                out.writeUnsignedShort(expectUShort())
            }

            "S_GET_LOCAL" -> {
                out.writeByte(Opcodes.S_GET_LOCAL)
                out.writeUnsignedShort(expectUShort())
            }

            "I_GET_LOCAL" -> {
                out.writeByte(Opcodes.I_GET_LOCAL)
                out.writeUnsignedShort(expectUShort())
            }

            "L_GET_LOCAL" -> {
                out.writeByte(Opcodes.L_GET_LOCAL)
                out.writeUnsignedShort(expectUShort())
            }

            "B_STORE_LOCAL" -> {
                out.writeByte(Opcodes.B_STORE_LOCAL)
                out.writeUnsignedShort(expectUShort())
            }

            "S_STORE_LOCAL" -> {
                out.writeByte(Opcodes.S_STORE_LOCAL)
                out.writeUnsignedShort(expectUShort())
            }

            "I_STORE_LOCAL" -> {
                out.writeByte(Opcodes.I_STORE_LOCAL)
                out.writeUnsignedShort(expectUShort())
            }

            "L_STORE_LOCAL" -> {
                out.writeByte(Opcodes.L_STORE_LOCAL)
                out.writeUnsignedShort(expectUShort())
            }

            "BPUSH", "B_PUSH" -> {
                out.writeByte(Opcodes.B_PUSH)
                out.writeByte(expectByte())
            }

            "SPUSH", "S_PUSH" -> {
                out.writeByte(Opcodes.S_PUSH)
                out.writeShort(expectShort())
            }

            "IPUSH", "I_PUSH" -> {
                out.writeByte(Opcodes.I_PUSH)
                out.writeInt(expectInt())
            }

            "LPUSH", "L_PUSH" -> {
                out.writeByte(Opcodes.L_PUSH)
                out.writeLong(expectLong())
            }

            "FPUSH", "F_PUSH" -> { // Synthetic pregenerated opcode to make your life a bit easier ;)
                out.writeByte(Opcodes.I_PUSH)
                out.writeFloat(expectFloat())
            }

            "DPUSH", "D_PUSH" -> { // Synthetic pregenerated opcode to make your life a bit easier ;)
                out.writeByte(Opcodes.L_PUSH)
                out.writeDouble(expectDouble())
            }

            "BADD", "B_ADD" -> out.writeByte(Opcodes.B_ADD)
            "BSUB", "B_SUB" -> out.writeByte(Opcodes.B_SUB)
            "BMUL", "B_MUL" -> out.writeByte(Opcodes.B_MUL)
            "BDIV", "B_DIV" -> out.writeByte(Opcodes.B_DIV)
            "BMOD", "B_MOD" -> out.writeByte(Opcodes.B_MOD)
            "SADD", "S_ADD" -> out.writeByte(Opcodes.S_ADD)
            "SSUB", "S_SUB" -> out.writeByte(Opcodes.S_SUB)
            "SMUL", "S_MUL" -> out.writeByte(Opcodes.S_MUL)
            "SDIV", "S_DIV" -> out.writeByte(Opcodes.S_DIV)
            "SMOD", "S_MOD" -> out.writeByte(Opcodes.S_MOD)
            "IADD", "I_ADD" -> out.writeByte(Opcodes.I_ADD)
            "ISUB", "I_SUB" -> out.writeByte(Opcodes.I_SUB)
            "IMUL", "I_MUL" -> out.writeByte(Opcodes.I_MUL)
            "IDIV", "I_DIV" -> out.writeByte(Opcodes.I_DIV)
            "IMOD", "I_MOD" -> out.writeByte(Opcodes.I_MOD)
            "LADD", "L_ADD" -> out.writeByte(Opcodes.L_ADD)
            "LSUB", "L_SUB" -> out.writeByte(Opcodes.L_SUB)
            "LMUL", "L_MUL" -> out.writeByte(Opcodes.L_MUL)
            "LDIV", "L_DIV" -> out.writeByte(Opcodes.L_DIV)
            "LMOD", "L_MOD" -> out.writeByte(Opcodes.L_MOD)
            "FADD", "F_ADD" -> out.writeByte(Opcodes.F_ADD)
            "FSUB", "F_SUB" -> out.writeByte(Opcodes.F_SUB)
            "FMUL", "F_MUL" -> out.writeByte(Opcodes.F_MUL)
            "FDIV", "F_DIV" -> out.writeByte(Opcodes.F_DIV)
            "FMOD", "F_MOD" -> out.writeByte(Opcodes.F_MOD)
            "DADD", "D_ADD" -> out.writeByte(Opcodes.D_ADD)
            "DSUB", "D_SUB" -> out.writeByte(Opcodes.D_SUB)
            "DMUL", "D_MUL" -> out.writeByte(Opcodes.D_MUL)
            "DDIV", "D_DIV" -> out.writeByte(Opcodes.D_DIV)
            "DMOD", "D_MOD" -> out.writeByte(Opcodes.D_MOD)
            "BEQ", "B_EQ" -> out.writeByte(Opcodes.B_EQ)
            "SEQ", "S_EQ" -> out.writeByte(Opcodes.S_EQ)
            "IEQ", "I_EQ" -> out.writeByte(Opcodes.I_EQ)
            "LEQ", "L_EQ" -> out.writeByte(Opcodes.L_EQ)
            "FEQ", "F_EQ" -> out.writeByte(Opcodes.F_EQ)
            "DEQ", "D_EQ" -> out.writeByte(Opcodes.D_EQ)
            "BBIGGER", "B_BIGGER" -> out.writeByte(Opcodes.B_BIGGER)
            "SBIGGER", "S_BIGGER" -> out.writeByte(Opcodes.S_BIGGER)
            "IBIGGER", "I_BIGGER" -> out.writeByte(Opcodes.I_BIGGER)
            "LBIGGER", "L_BIGGER" -> out.writeByte(Opcodes.L_BIGGER)
            "FBIGGER", "F_BIGGER" -> out.writeByte(Opcodes.F_BIGGER)
            "DBIGGER", "D_BIGGER" -> out.writeByte(Opcodes.D_BIGGER)
            "BSMALLER", "B_SMALLER" -> out.writeByte(Opcodes.B_SMALLER)
            "SSMALLER", "S_SMALLER" -> out.writeByte(Opcodes.S_SMALLER)
            "ISMALLER", "I_SMALLER" -> out.writeByte(Opcodes.I_SMALLER)
            "LSMALLER", "L_SMALLER" -> out.writeByte(Opcodes.L_SMALLER)
            "FSMALLER", "F_SMALLER" -> out.writeByte(Opcodes.F_SMALLER)
            "DSMALLER", "D_SMALLER" -> out.writeByte(Opcodes.D_SMALLER)
            "BBIGGEREQ", "B_BIGGER_EQ" -> out.writeByte(Opcodes.B_BIGGER_EQ)
            "SBIGGEREQ", "S_BIGGER_EQ" -> out.writeByte(Opcodes.S_BIGGER_EQ)
            "IBIGGEREQ", "I_BIGGER_EQ" -> out.writeByte(Opcodes.I_BIGGER_EQ)
            "LBIGGEREQ", "L_BIGGER_EQ" -> out.writeByte(Opcodes.L_BIGGER_EQ)
            "FBIGGEREQ", "F_BIGGER_EQ" -> out.writeByte(Opcodes.F_BIGGER_EQ)
            "DBIGGEREQ", "D_BIGGER_EQ" -> out.writeByte(Opcodes.D_BIGGER_EQ)
            "BSMALLEREQ", "B_SMALLER_EQ" -> out.writeByte(Opcodes.B_SMALLER_EQ)
            "SSMALLEREQ", "S_SMALLER_EQ" -> out.writeByte(Opcodes.S_SMALLER_EQ)
            "ISMALLEREQ", "I_SMALLER_EQ" -> out.writeByte(Opcodes.I_SMALLER_EQ)
            "LSMALLEREQ", "L_SMALLER_EQ" -> out.writeByte(Opcodes.L_SMALLER_EQ)
            "FSMALLEREQ", "F_SMALLER_EQ" -> out.writeByte(Opcodes.F_SMALLER_EQ)
            "DSMALLEREQ", "D_SMALLER_EQ" -> out.writeByte(Opcodes.D_SMALLER_EQ)
            "NOT", "BOOL_NOT" -> out.writeByte(Opcodes.BOOL_NOT)
            "B_GET_GLOBAL" -> {
                out.writeByte(Opcodes.B_GET_GLOBAL)
                out.writeUnsignedInt(expectUInt())
            }

            "S_GET_GLOBAL" -> {
                out.writeByte(Opcodes.S_GET_GLOBAL)
                out.writeUnsignedInt(expectUInt())
            }

            "I_GET_GLOBAL" -> {
                out.writeByte(Opcodes.I_GET_GLOBAL)
                out.writeUnsignedInt(expectUInt())
            }

            "L_GET_GLOBAL" -> {
                out.writeByte(Opcodes.L_GET_GLOBAL)
                out.writeUnsignedInt(expectUInt())
            }

            "B_GET_GLOBAL_DYNAMIC" -> out.writeByte(Opcodes.B_GET_GLOBAL_DYNAMIC)
            "S_GET_GLOBAL_DYNAMIC" -> out.writeByte(Opcodes.S_GET_GLOBAL_DYNAMIC)
            "I_GET_GLOBAL_DYNAMIC" -> out.writeByte(Opcodes.I_GET_GLOBAL_DYNAMIC)
            "L_GET_GLOBAL_DYNAMIC" -> out.writeByte(Opcodes.L_GET_GLOBAL_DYNAMIC)
            "B_STORE_GLOBAL" -> {
                out.writeByte(Opcodes.B_STORE_GLOBAL)
                out.writeUnsignedInt(expectUInt())
            }

            "S_STORE_GLOBAL" -> {
                out.writeByte(Opcodes.S_STORE_GLOBAL)
                out.writeUnsignedInt(expectUInt())
            }

            "I_STORE_GLOBAL" -> {
                out.writeByte(Opcodes.I_STORE_GLOBAL)
                out.writeUnsignedInt(expectUInt())
            }

            "L_STORE_GLOBAL" -> {
                out.writeByte(Opcodes.L_STORE_GLOBAL)
                out.writeUnsignedInt(expectUInt())
            }

            "B_STORE_GLOBAL_DYNAMIC" -> out.writeByte(Opcodes.B_STORE_GLOBAL_DYNAMIC)
            "S_STORE_GLOBAL_DYNAMIC" -> out.writeByte(Opcodes.S_STORE_GLOBAL_DYNAMIC)
            "I_STORE_GLOBAL_DYNAMIC" -> out.writeByte(Opcodes.I_STORE_GLOBAL_DYNAMIC)
            "L_STORE_GLOBAL_DYNAMIC" -> out.writeByte(Opcodes.L_STORE_GLOBAL_DYNAMIC)
            "BNEG", "B_NEG" -> out.writeByte(Opcodes.B_NEG)
            "SNEG", "S_NEG" -> out.writeByte(Opcodes.S_NEG)
            "INEG", "I_NEG" -> out.writeByte(Opcodes.I_NEG)
            "LNEG", "L_NEG" -> out.writeByte(Opcodes.L_NEG)
            "FNEG", "F_NEG" -> out.writeByte(Opcodes.F_NEG)
            "DNEG", "D_NEG" -> out.writeByte(Opcodes.D_NEG)
            "BABS", "B_ABS" -> out.writeByte(Opcodes.B_ABS)
            "SABS", "S_ABS" -> out.writeByte(Opcodes.S_ABS)
            "IABS", "I_ABS" -> out.writeByte(Opcodes.I_ABS)
            "LABS", "L_ABS" -> out.writeByte(Opcodes.L_ABS)
            "FABS", "F_ABS" -> out.writeByte(Opcodes.F_ABS)
            "DABS", "D_ABS" -> out.writeByte(Opcodes.D_ABS)
            else -> throw Error("Unknown opcode")
        }
    }
}
