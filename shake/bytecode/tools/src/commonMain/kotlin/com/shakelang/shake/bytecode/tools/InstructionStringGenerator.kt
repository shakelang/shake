package com.shakelang.shake.bytecode.tools

import com.shakelang.shake.bytecode.interpreter.Opcodes
import com.shakelang.shake.bytecode.interpreter.format.StorageFormat
import com.shakelang.util.io.streaming.input.bytes.InputStream
import com.shakelang.util.io.streaming.input.bytes.countingStream
import com.shakelang.util.io.streaming.input.bytes.dataStream
import com.shakelang.util.primitives.bytes.toBytes
import com.shakelang.util.primitives.bytes.toHexString

class InstructionStringGenerator(
    bytes: InputStream,
    val storage: StorageFormat,
) {

    val counter = bytes.countingStream
    val bytes = counter.dataStream

    fun hasNext() = bytes.available() > 0
    fun generateInstruction(): String {
        // Read the Opcode
        val opcode = bytes.readByte()

        when (opcode) {
            // NOP
            Opcodes.NOP -> return "nop"

            // Push
            Opcodes.BPUSH -> return "bpush ${bytes.readByte()}"
            Opcodes.SPUSH -> return "spush ${bytes.readShort()}"
            Opcodes.IPUSH -> return "ipush ${bytes.readInt()}"
            Opcodes.LPUSH -> return "lpush ${bytes.readLong()}"

            Opcodes.BLOAD -> return "bload ${bytes.readUnsignedShort()}"
            Opcodes.SLOAD -> return "sload ${bytes.readUnsignedShort()}"
            Opcodes.ILOAD -> return "iload ${bytes.readUnsignedShort()}"
            Opcodes.LLOAD -> return "lload ${bytes.readUnsignedShort()}"

            Opcodes.BSTORE -> return "bstore ${bytes.readUnsignedShort()}"
            Opcodes.SSTORE -> return "sstore ${bytes.readUnsignedShort()}"
            Opcodes.ISTORE -> return "istore ${bytes.readUnsignedShort()}"
            Opcodes.LSTORE -> return "lstore ${bytes.readUnsignedShort()}"

            Opcodes.BADD -> return "badd"
            Opcodes.SADD -> return "sadd"
            Opcodes.IADD -> return "iadd"
            Opcodes.LADD -> return "ladd"
            Opcodes.FADD -> return "fadd"
            Opcodes.DADD -> return "dadd"

            Opcodes.BSUB -> return "bsub"
            Opcodes.SSUB -> return "ssub"
            Opcodes.ISUB -> return "isub"
            Opcodes.LSUB -> return "lsub"
            Opcodes.UBSUB -> return "ubsub"
            Opcodes.USSUB -> return "ussub"
            Opcodes.UISUB -> return "uisub"
            Opcodes.ULSUB -> return "ulsub"
            Opcodes.FSUB -> return "fsub"
            Opcodes.DSUB -> return "dsub"

            Opcodes.BMUL -> return "bmul"
            Opcodes.SMUL -> return "smul"
            Opcodes.IMUL -> return "imul"
            Opcodes.LMUL -> return "lmul"
            Opcodes.UBMUL -> return "ubmul"
            Opcodes.USMUL -> return "usmul"
            Opcodes.UIMUL -> return "uimul"
            Opcodes.ULMUL -> return "ulmul"
            Opcodes.FMUL -> return "fmul"
            Opcodes.DMUL -> return "dmul"

            Opcodes.BDIV -> return "bdiv"
            Opcodes.SDIV -> return "sdiv"
            Opcodes.IDIV -> return "idiv"
            Opcodes.LDIV -> return "ldiv"
            Opcodes.UBDIV -> return "ubdiv"
            Opcodes.USDIV -> return "usdiv"
            Opcodes.UIDIV -> return "uidiv"
            Opcodes.ULDIV -> return "uldiv"
            Opcodes.FDIV -> return "fdiv"
            Opcodes.DDIV -> return "ddiv"

            Opcodes.BMOD -> return "bmod"
            Opcodes.SMOD -> return "smod"
            Opcodes.IMOD -> return "imod"
            Opcodes.LMOD -> return "lmod"
            Opcodes.UBMOD -> return "ubmod"
            Opcodes.USMOD -> return "usmod"
            Opcodes.UIMOD -> return "uimod"
            Opcodes.ULMOD -> return "ulmod"
            Opcodes.FMOD -> return "fmod"
            Opcodes.DMOD -> return "dmod"

            Opcodes.BNEG -> return "bneg"
            Opcodes.SNEG -> return "sneg"
            Opcodes.INEG -> return "ineg"
            Opcodes.LNEG -> return "lneg"
            Opcodes.FNEG -> return "fneg"
            Opcodes.DNEG -> return "dneg"

            Opcodes.BINC -> return "binc"
            Opcodes.SINC -> return "sinc"
            Opcodes.IINC -> return "iinc"
            Opcodes.LINC -> return "linc"
            Opcodes.FINC -> return "finc"
            Opcodes.DINC -> return "dinc"

            Opcodes.BDEC -> return "bdec"
            Opcodes.SDEC -> return "sdec"
            Opcodes.IDEC -> return "idec"
            Opcodes.LDEC -> return "ldec"
            Opcodes.FDEC -> return "fdec"
            Opcodes.DDEC -> return "ddec"

            Opcodes.BSHL -> return "bshl"
            Opcodes.SSHL -> return "sshl"
            Opcodes.ISHL -> return "ishl"
            Opcodes.LSHL -> return "lshl"

            Opcodes.BSHR -> return "bshr"
            Opcodes.SSHR -> return "sshr"
            Opcodes.ISHR -> return "ishr"
            Opcodes.LSHR -> return "lshr"

            Opcodes.BSHRU -> return "bshru"
            Opcodes.SSHRU -> return "sshru"
            Opcodes.ISHRU -> return "ishru"
            Opcodes.LSHRU -> return "lshru"

            Opcodes.BAND -> return "band"
            Opcodes.SAND -> return "sand"
            Opcodes.IAND -> return "iand"
            Opcodes.LAND -> return "land"

            Opcodes.BOR -> return "bor"
            Opcodes.SOR -> return "sor"
            Opcodes.IOR -> return "ior"
            Opcodes.LOR -> return "lor"

            Opcodes.BXOR -> return "bxor"
            Opcodes.SXOR -> return "sxor"
            Opcodes.IXOR -> return "ixor"
            Opcodes.LXOR -> return "lxor"

            Opcodes.BCMP -> return "bcmp"
            Opcodes.SCMP -> return "scmp"
            Opcodes.ICMP -> return "icmp"
            Opcodes.LCMP -> return "lcmp"

            Opcodes.CLT -> return "clt"
            Opcodes.CLE -> return "cle"
            Opcodes.CGT -> return "cgt"
            Opcodes.CGE -> return "cge"
            Opcodes.CEQ -> return "ceq"
            Opcodes.CNE -> return "cne"

            Opcodes.JMP -> return "jmp 0x${bytes.readUnsignedInt().toBytes().toHexString()}"
            Opcodes.JZ -> return "jz 0x${bytes.readUnsignedInt().toBytes().toHexString()}"
            Opcodes.JNZ -> return "jnz 0x${bytes.readUnsignedInt().toBytes().toHexString()}"
            Opcodes.JL -> return "jl 0x${bytes.readUnsignedInt().toBytes().toHexString()}"
            Opcodes.JLE -> return "jle 0x${bytes.readUnsignedInt().toBytes().toHexString()}"
            Opcodes.JGE -> return "jge 0x${bytes.readUnsignedInt().toBytes().toHexString()}"
            Opcodes.JE -> return "je 0x${bytes.readUnsignedInt().toBytes().toHexString()}"
            Opcodes.JNE -> return "jne 0x${bytes.readUnsignedInt().toBytes().toHexString()}"

            Opcodes.RET -> return "ret"
            Opcodes.BRET -> return "bret"
            Opcodes.SRET -> return "sret"
            Opcodes.IRET -> return "iret"
            Opcodes.LRET -> return "lret"

            Opcodes.BPOP -> return "pop"
            Opcodes.SPOP -> return "spop"
            Opcodes.IPOP -> return "ipop"
            Opcodes.LPOP -> return "lpop"

            Opcodes.BDUP -> return "bdup"
            Opcodes.SDUP -> return "sdup"
            Opcodes.IDUP -> return "idup"
            Opcodes.LDUP -> return "ldup"

            Opcodes.PCAST -> return "pcast 0x${bytes.readUnsignedByte().toBytes().toHexString()}"
            Opcodes.INVOKE_STATIC -> {
                val addr = bytes.readUnsignedInt()
                return "invoke_static `${storage.constantPool.getUtf8(addr.toInt()).value}`"
            }

            Opcodes.INVOKE_VIRTUAL -> {
                val addr = bytes.readUnsignedInt()
                return "invoke_virtual `${storage.constantPool.getUtf8(addr.toInt()).value}`"
            }

            Opcodes.LOAD_STATIC -> {
                val addr = bytes.readUnsignedInt()
                return "load_static `${storage.constantPool.getUtf8(addr.toInt()).value}`"
            }

            Opcodes.LOAD_VIRTUAL -> {
                val addr = bytes.readUnsignedInt()
                return "load_virtual `${storage.constantPool.getUtf8(addr.toInt()).value}`"
            }

            Opcodes.STORE_STATIC -> {
                val addr = bytes.readUnsignedInt()
                return "store_static `${storage.constantPool.getUtf8(addr.toInt()).value}`"
            }

            Opcodes.STORE_VIRTUAL -> {
                val addr = bytes.readUnsignedInt()
                return "store_virtual `${storage.constantPool.getUtf8(addr.toInt()).value}`"
            }

            Opcodes.NEW_OBJ -> {
                val addr = bytes.readUnsignedInt()
                return "new_obj `${storage.constantPool.getUtf8(addr.toInt()).value}`"
            }

            Opcodes.NEW_ARR -> {
                val addr = bytes.readUnsignedInt()
                return "new_arr `${storage.constantPool.getUtf8(addr.toInt()).value}`"
            }

            else -> throw IllegalStateException("Unknown opcode: 0x${opcode.toBytes().toHexString()}")
        }
    }

    fun generate(): List<String> {
        val instructions = mutableListOf<String>()
        while (hasNext()) instructions.add(generateInstruction())
        return instructions
    }
}
