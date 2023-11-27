package com.shakelang.shake.bytecode.interpreter

import com.shakelang.shake.util.primitives.bytes.toBytes
import com.shakelang.shake.util.primitives.calc.shl

class ShakeBytecodeGenerator(
    val bytes: MutableList<Byte> = mutableListOf <Byte>(),
) {

    fun addByte(byte: Byte) = bytes.add(byte)
    fun addBytes(bytes: List<Byte>) = this.bytes.addAll(bytes)
    fun addBytes(vararg bytes: Byte) = this.bytes.addAll(bytes.toList())

    fun nop() = addByte(Opcodes.NOP)

    fun bpush(byte: Byte) = addBytes(listOf(Opcodes.BPUSH, byte))
    fun spush(short: Short) = addBytes(listOf(Opcodes.SPUSH, *short.toBytes().toTypedArray()))
    fun ipush(int: Int) = addBytes(listOf(Opcodes.IPUSH, *int.toBytes().toTypedArray()))
    fun lpush(long: Long) = addBytes(listOf(Opcodes.LPUSH, *long.toBytes().toTypedArray()))

    fun bload(variable: UShort) = addBytes(listOf(Opcodes.BLOAD, *variable.toBytes().toTypedArray()))
    fun sload(variable: UShort) = addBytes(listOf(Opcodes.SLOAD, *variable.toBytes().toTypedArray()))
    fun iload(variable: UShort) = addBytes(listOf(Opcodes.ILOAD, *variable.toBytes().toTypedArray()))
    fun lload(variable: UShort) = addBytes(listOf(Opcodes.LLOAD, *variable.toBytes().toTypedArray()))

    fun bstore(variable: UShort) = addBytes(listOf(Opcodes.BSTORE, *variable.toBytes().toTypedArray()))
    fun sstore(variable: UShort) = addBytes(listOf(Opcodes.SSTORE, *variable.toBytes().toTypedArray()))
    fun istore(variable: UShort) = addBytes(listOf(Opcodes.ISTORE, *variable.toBytes().toTypedArray()))
    fun lstore(variable: UShort) = addBytes(listOf(Opcodes.LSTORE, *variable.toBytes().toTypedArray()))

    fun badd() = addByte(Opcodes.BADD)
    fun sadd() = addByte(Opcodes.SADD)
    fun iadd() = addByte(Opcodes.IADD)
    fun ladd() = addByte(Opcodes.LADD)
    fun fadd() = addByte(Opcodes.FADD)
    fun dadd() = addByte(Opcodes.DADD)

    fun bsub() = addByte(Opcodes.BSUB)
    fun ssub() = addByte(Opcodes.SSUB)
    fun isub() = addByte(Opcodes.ISUB)
    fun lsub() = addByte(Opcodes.LSUB)
    fun ubsub() = addByte(Opcodes.UBSUB)
    fun ussub() = addByte(Opcodes.USSUB)
    fun uisub() = addByte(Opcodes.UISUB)
    fun ulsub() = addByte(Opcodes.ULSUB)
    fun fsub() = addByte(Opcodes.FSUB)
    fun dsub() = addByte(Opcodes.DSUB)

    fun bmul() = addByte(Opcodes.BMUL)
    fun smul() = addByte(Opcodes.SMUL)
    fun imul() = addByte(Opcodes.IMUL)
    fun lmul() = addByte(Opcodes.LMUL)
    fun ubmul() = addByte(Opcodes.UBMUL)
    fun usmul() = addByte(Opcodes.USMUL)
    fun uimul() = addByte(Opcodes.UIMUL)
    fun ulmul() = addByte(Opcodes.ULMUL)
    fun fmul() = addByte(Opcodes.FMUL)
    fun dmul() = addByte(Opcodes.DMUL)

    fun bdiv() = addByte(Opcodes.BDIV)
    fun sdiv() = addByte(Opcodes.SDIV)
    fun idiv() = addByte(Opcodes.IDIV)
    fun ldiv() = addByte(Opcodes.LDIV)
    fun ubdiv() = addByte(Opcodes.UBDIV)
    fun usdiv() = addByte(Opcodes.USDIV)
    fun uidiv() = addByte(Opcodes.UIDIV)
    fun uldiv() = addByte(Opcodes.ULDIV)
    fun fdiv() = addByte(Opcodes.FDIV)
    fun ddiv() = addByte(Opcodes.DDIV)

    fun bmod() = addByte(Opcodes.BMOD)
    fun smod() = addByte(Opcodes.SMOD)
    fun imod() = addByte(Opcodes.IMOD)
    fun lmod() = addByte(Opcodes.LMOD)
    fun ubmod() = addByte(Opcodes.UBMOD)
    fun usmod() = addByte(Opcodes.USMOD)
    fun uimod() = addByte(Opcodes.UIMOD)
    fun ulmod() = addByte(Opcodes.ULMOD)
    fun fmod() = addByte(Opcodes.FMOD)
    fun dmod() = addByte(Opcodes.DMOD)

    fun bneg() = addByte(Opcodes.BNEG)
    fun sneg() = addByte(Opcodes.SNEG)
    fun ineg() = addByte(Opcodes.INEG)
    fun lneg() = addByte(Opcodes.LNEG)
    fun fneg() = addByte(Opcodes.FNEG)
    fun dneg() = addByte(Opcodes.DNEG)

    fun band() = addByte(Opcodes.BAND)
    fun sand() = addByte(Opcodes.SAND)
    fun iand() = addByte(Opcodes.IAND)
    fun land() = addByte(Opcodes.LAND)

    fun bor() = addByte(Opcodes.BOR)
    fun sor() = addByte(Opcodes.SOR)
    fun ior() = addByte(Opcodes.IOR)
    fun lor() = addByte(Opcodes.LOR)

    fun bxor() = addByte(Opcodes.BXOR)
    fun sxor() = addByte(Opcodes.SXOR)
    fun ixor() = addByte(Opcodes.IXOR)
    fun lxor() = addByte(Opcodes.LXOR)

    fun bnot() = addByte(Opcodes.BNOT)
    fun snot() = addByte(Opcodes.SNOT)
    fun inot() = addByte(Opcodes.INOT)
    fun lnot() = addByte(Opcodes.LNOT)

    fun bshl() = addByte(Opcodes.BSHL)
    fun sshl() = addByte(Opcodes.SSHL)
    fun ishl() = addByte(Opcodes.ISHL)
    fun lshl() = addByte(Opcodes.LSHL)

    fun bshr() = addByte(Opcodes.BSHR)
    fun sshr() = addByte(Opcodes.SSHR)
    fun ishr() = addByte(Opcodes.ISHR)
    fun lshr() = addByte(Opcodes.LSHR)

    fun bshru() = addByte(Opcodes.BSHRU)
    fun sshru() = addByte(Opcodes.SSHRU)
    fun ishru() = addByte(Opcodes.ISHRU)
    fun lshru() = addByte(Opcodes.LSHRU)

    fun binc() = addByte(Opcodes.BINC)
    fun sinc() = addByte(Opcodes.SINC)
    fun iinc() = addByte(Opcodes.IINC)
    fun linc() = addByte(Opcodes.LINC)

    fun bdec() = addByte(Opcodes.BDEC)
    fun sdec() = addByte(Opcodes.SDEC)
    fun idec() = addByte(Opcodes.IDEC)
    fun ldec() = addByte(Opcodes.LDEC)
    fun fdec() = addByte(Opcodes.FDEC)
    fun ddec() = addByte(Opcodes.DDEC)

    fun bcmp() = addByte(Opcodes.BCMP)
    fun scmp() = addByte(Opcodes.SCMP)
    fun icmp() = addByte(Opcodes.ICMP)
    fun lcmp() = addByte(Opcodes.LCMP)
    fun fcmp() = addByte(Opcodes.FCMP)
    fun dcmp() = addByte(Opcodes.DCMP)

    fun pcast(from: UByte, to: UByte) = addBytes(Opcodes.PCAST, ((from shl 4) or to).toByte())


    fun toByteArray() = bytes.toByteArray()
}

fun bytecode(init: ShakeBytecodeGenerator.() -> Unit): ByteArray {
    val generator = ShakeBytecodeGenerator()
    generator.init()
    return generator.toByteArray()
}