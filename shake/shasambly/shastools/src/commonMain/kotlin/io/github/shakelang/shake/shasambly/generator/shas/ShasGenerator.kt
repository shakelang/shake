@file:Suppress("unused")

package io.github.shakelang.shake.shasambly.generator.shas

import io.github.shakelang.shake.shasambly.interpreter.Opcodes
import io.github.shakelang.shake.shasambly.interpreter.natives.Natives
import io.github.shakelang.shake.shasambly.interpreter.natives.nativeFunctions
import io.github.shakelang.shake.util.io.streaming.input.InputStream
import io.github.shakelang.shake.util.io.streaming.input.asCountingInputStream
import io.github.shakelang.shake.util.io.streaming.input.asDataInputStream
import io.github.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import io.github.shakelang.shake.util.io.streaming.output.OutputStream
import com.shakelang.shake.util.primitives.bytes.toBytes
import com.shakelang.shake.util.primitives.bytes.toHexString

class ShasGenerator(input: InputStream) {

    private val counter = input.asCountingInputStream()
    private val input = counter.asDataInputStream()

    private lateinit var out: OutputStream

    fun parse(): ByteArray {
        val out = ByteArrayOutputStream()

        doParse()

        return out.toByteArray()
    }

    fun dumpParse(out: OutputStream) {
        this.out = out
        doParse()
    }

    private fun doParse() {
        Natives.initNativeFunctions()
        while (input.available() > 0) {
            print_opcode()
        }
    }

    private fun oprint(contents: String) {
        out.write(contents.toCharArray().map { it.code.toUByte().toByte() }.toByteArray())
    }

    private fun oprintln(contents: String = "") {
        oprint("${contents}\n")
    }

    fun print_opcode() {
        oprint("[0x${(counter.count + Util.START_BYTES).toUInt().toBytes().toHexString()}] ")
        when (val opcode = input.readByte()) {
            Opcodes.INCR_STACK -> oprintln("incr_stack ${input.readUnsignedShort()}")
            Opcodes.DECR_STACK -> oprintln("decr_stack")
            Opcodes.JUMP_STATIC -> input.readUnsignedInt().let {
                oprintln("jump_static 0x${it.toBytes().toHexString()} [numeric: $it]")
            }

            Opcodes.JUMP_DYNAMIC -> oprintln("jump_dynamic")
            Opcodes.JUMP_IF -> oprintln("jump_if 0x${input.readUnsignedInt().toBytes().toHexString()}")
            Opcodes.INVOKE_NATIVE -> {
                val f = input.readUnsignedShort().toInt()
                oprint("invoke_native ${nativeFunctions[f]?.name ?: throw Error("Unknown native")}")
                val args = nativeFunctions[f]!!.byteArgumentAmount
                if (args > 0) oprint(" ${input.readNBytes(args).toHexString()}")
                oprintln()
            }

            Opcodes.GLOB_ADDR -> oprintln("glob_addr ${input.readUnsignedShort()}")

            Opcodes.B_GET_LOCAL -> input.readUnsignedShort().let {
                oprintln("b_get_local 0x${it.toBytes().toHexString()} [numeric: $it]")
            }

            Opcodes.S_GET_LOCAL -> input.readUnsignedShort().let {
                oprintln("s_get_local 0x${it.toBytes().toHexString()} [numeric: $it]")
            }

            Opcodes.I_GET_LOCAL -> input.readUnsignedShort().let {
                oprintln("i_get_local 0x${it.toBytes().toHexString()} [numeric: $it]")
            }

            Opcodes.L_GET_LOCAL -> input.readUnsignedShort().let {
                oprintln("l_get_local 0x${it.toBytes().toHexString()} [numeric: $it]")
            }

            Opcodes.B_STORE_LOCAL -> input.readUnsignedShort().let {
                oprintln("b_store_local 0x${it.toBytes().toHexString()} [numeric: $it]")
            }

            Opcodes.S_STORE_LOCAL -> input.readUnsignedShort().let {
                oprintln("s_store_local 0x${it.toBytes().toHexString()} [numeric: $it]")
            }

            Opcodes.I_STORE_LOCAL -> input.readUnsignedShort().let {
                oprintln("i_store_local 0x${it.toBytes().toHexString()} [numeric: $it]")
            }

            Opcodes.L_STORE_LOCAL -> input.readUnsignedShort().let {
                oprintln("l_store_local 0x${it.toBytes().toHexString()} [numeric: $it]")
            }

            Opcodes.B_PUSH -> input.readByte().let {
                oprintln("b_push 0x${it.toBytes().toHexString()} [numeric: $it]")
            }

            Opcodes.S_PUSH -> input.readShort().let {
                oprintln("s_push 0x${it.toBytes().toHexString()} [numeric: $it]")
            }

            Opcodes.I_PUSH -> input.readInt().let {
                oprintln("i_push 0x${it.toBytes().toHexString()} [numeric: $it]")
            }

            Opcodes.L_PUSH -> input.readLong().let {
                oprintln("l_push 0x${it.toBytes().toHexString()} [numeric: $it]")
            }

            Opcodes.B_ADD -> oprintln("badd")
            Opcodes.B_SUB -> oprintln("bsub")
            Opcodes.B_MUL -> oprintln("bmul")
            Opcodes.B_DIV -> oprintln("bdiv")
            Opcodes.B_MOD -> oprintln("bmod")
            Opcodes.S_ADD -> oprintln("sadd")
            Opcodes.S_SUB -> oprintln("ssub")
            Opcodes.S_MUL -> oprintln("smul")
            Opcodes.S_DIV -> oprintln("sdiv")
            Opcodes.S_MOD -> oprintln("smod")
            Opcodes.I_ADD -> oprintln("iadd")
            Opcodes.I_SUB -> oprintln("isub")
            Opcodes.I_MUL -> oprintln("imul")
            Opcodes.I_DIV -> oprintln("idiv")
            Opcodes.I_MOD -> oprintln("imod")
            Opcodes.L_ADD -> oprintln("ladd")
            Opcodes.L_SUB -> oprintln("lsub")
            Opcodes.L_MUL -> oprintln("lmul")
            Opcodes.L_DIV -> oprintln("ldiv")
            Opcodes.L_MOD -> oprintln("lmod")
            Opcodes.F_ADD -> oprintln("fadd")
            Opcodes.F_SUB -> oprintln("fsub")
            Opcodes.F_MUL -> oprintln("fmul")
            Opcodes.F_DIV -> oprintln("fdiv")
            Opcodes.F_MOD -> oprintln("fmod")
            Opcodes.D_ADD -> oprintln("dadd")
            Opcodes.D_SUB -> oprintln("dsub")
            Opcodes.D_MUL -> oprintln("dmul")
            Opcodes.D_DIV -> oprintln("ddiv")
            Opcodes.D_MOD -> oprintln("dmod")
            Opcodes.B_EQ -> oprintln("b_eq")
            Opcodes.S_EQ -> oprintln("s_eq")
            Opcodes.I_EQ -> oprintln("i_eq")
            Opcodes.L_EQ -> oprintln("l_eq")
            Opcodes.F_EQ -> oprintln("f_eq")
            Opcodes.D_EQ -> oprintln("d_eq")

            Opcodes.B_BIGGER -> oprintln("b_bigger")
            Opcodes.S_BIGGER -> oprintln("s_bigger")
            Opcodes.I_BIGGER -> oprintln("i_bigger")
            Opcodes.L_BIGGER -> oprintln("l_bigger")
            Opcodes.F_BIGGER -> oprintln("f_bigger")
            Opcodes.D_BIGGER -> oprintln("d_bigger")
            Opcodes.B_SMALLER -> oprintln("b_smaller")
            Opcodes.S_SMALLER -> oprintln("s_smaller")
            Opcodes.I_SMALLER -> oprintln("i_smaller")
            Opcodes.L_SMALLER -> oprintln("l_smaller")
            Opcodes.F_SMALLER -> oprintln("f_smaller")
            Opcodes.D_SMALLER -> oprintln("d_smaller")
            Opcodes.B_BIGGER_EQ -> oprintln("b_bigger_eq")
            Opcodes.S_BIGGER_EQ -> oprintln("s_bigger_eq")
            Opcodes.I_BIGGER_EQ -> oprintln("i_bigger_eq")
            Opcodes.L_BIGGER_EQ -> oprintln("l_bigger_eq")
            Opcodes.F_BIGGER_EQ -> oprintln("f_bigger_eq")
            Opcodes.D_BIGGER_EQ -> oprintln("d_bigger_eq")
            Opcodes.B_SMALLER_EQ -> oprintln("b_smaller_eq")
            Opcodes.S_SMALLER_EQ -> oprintln("s_smaller_eq")
            Opcodes.I_SMALLER_EQ -> oprintln("i_smaller_eq")
            Opcodes.L_SMALLER_EQ -> oprintln("l_smaller_eq")
            Opcodes.F_SMALLER_EQ -> oprintln("f_smaller_eq")
            Opcodes.D_SMALLER_EQ -> oprintln("d_smaller_eq")

            Opcodes.BOOL_NOT -> oprintln("not")

            Opcodes.B_GET_GLOBAL -> oprintln("b_get_global ${input.readUnsignedInt().toString(16)}")
            Opcodes.S_GET_GLOBAL -> oprintln("s_get_global ${input.readUnsignedInt().toString(16)}")
            Opcodes.I_GET_GLOBAL -> oprintln("i_get_global ${input.readUnsignedInt().toString(16)}")
            Opcodes.L_GET_GLOBAL -> oprintln("l_get_global ${input.readUnsignedInt().toString(16)}")

            Opcodes.B_GET_GLOBAL_DYNAMIC -> oprintln("b_get_global_dynamic")
            Opcodes.S_GET_GLOBAL_DYNAMIC -> oprintln("s_get_global_dynamic")
            Opcodes.I_GET_GLOBAL_DYNAMIC -> oprintln("i_get_global_dynamic")
            Opcodes.L_GET_GLOBAL_DYNAMIC -> oprintln("l_get_global_dynamic")

            Opcodes.B_STORE_GLOBAL -> oprintln("b_store_global ${input.readUnsignedInt().toString(16)}")
            Opcodes.S_STORE_GLOBAL -> oprintln("s_store_global ${input.readUnsignedInt().toString(16)}")
            Opcodes.I_STORE_GLOBAL -> oprintln("i_store_global ${input.readUnsignedInt().toString(16)}")
            Opcodes.L_STORE_GLOBAL -> oprintln("l_store_global ${input.readUnsignedInt().toString(16)}")

            Opcodes.B_STORE_GLOBAL_DYNAMIC -> oprintln("b_store_global_dynamic")
            Opcodes.S_STORE_GLOBAL_DYNAMIC -> oprintln("s_store_global_dynamic")
            Opcodes.I_STORE_GLOBAL_DYNAMIC -> oprintln("i_store_global_dynamic")
            Opcodes.L_STORE_GLOBAL_DYNAMIC -> oprintln("l_store_global_dynamic")

            Opcodes.B_NEG -> oprintln("bneg")
            Opcodes.S_NEG -> oprintln("sneg")
            Opcodes.I_NEG -> oprintln("ineg")
            Opcodes.L_NEG -> oprintln("lneg")
            Opcodes.F_NEG -> oprintln("fneg")
            Opcodes.D_NEG -> oprintln("dneg")

            Opcodes.B_ABS -> oprintln("babs")
            Opcodes.S_ABS -> oprintln("sabs")
            Opcodes.I_ABS -> oprintln("iabs")
            Opcodes.L_ABS -> oprintln("labs")
            Opcodes.F_ABS -> oprintln("fabs")
            Opcodes.D_ABS -> oprintln("dabs")
            else -> throw Error(
                "Wrong opcode 0x${opcode.toBytes().toHexString()} at position " +
                    "0x${(counter.count - 1).toUInt().toBytes().toHexString()}"
            )
        }
    }
}
