package com.shakelang.shake.bytecode.interpreter.generator

import com.shakelang.shake.bytecode.interpreter.Opcodes
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.util.primitives.bytes.setBytes
import com.shakelang.shake.util.primitives.bytes.toBytes
import com.shakelang.shake.util.primitives.calc.shl
import kotlin.jvm.JvmName

open class ShakeBytecodeInstructionGenerator(
    val bytes: MutableList<Byte> = mutableListOf()
) {

    fun pointer() = bytes.size

    fun addByte(byte: Byte) = bytes.add(byte)
    fun addBytes(bytes: List<Byte>) = this.bytes.addAll(bytes)
    fun addBytes(vararg bytes: Byte) = this.bytes.addAll(bytes.toList())

    fun nop() = addByte(Opcodes.NOP)

    fun bpush(byte: Byte) = addBytes(listOf(Opcodes.BPUSH, byte))
    fun spush(short: Short) = addBytes(listOf(Opcodes.SPUSH, *short.toBytes().toTypedArray()))
    fun ipush(int: Int) = addBytes(listOf(Opcodes.IPUSH, *int.toBytes().toTypedArray()))
    fun lpush(long: Long) = addBytes(listOf(Opcodes.LPUSH, *long.toBytes().toTypedArray()))

    fun bpush(value: Boolean) = addBytes(listOf(Opcodes.BPUSH, if (value) 1 else 0))
    fun spush(value: Char) = addBytes(listOf(Opcodes.SPUSH, *value.code.toShort().toBytes().toTypedArray()))
    fun ipush(value: Float) = addBytes(listOf(Opcodes.IPUSH, *value.toRawBits().toBytes().toTypedArray()))
    fun lpush(value: Double) = addBytes(listOf(Opcodes.LPUSH, *value.toRawBits().toBytes().toTypedArray()))

    @JvmName("bpushUByte")
    fun bpush(value: UByte) = addBytes(listOf(Opcodes.BPUSH, value.toByte()))

    @JvmName("spushUShort")
    fun spush(value: UShort) = addBytes(listOf(Opcodes.SPUSH, *value.toBytes().toTypedArray()))

    @JvmName("ipushUInt")
    fun ipush(value: UInt) = addBytes(listOf(Opcodes.IPUSH, *value.toBytes().toTypedArray()))

    @JvmName("lpushULong")
    fun lpush(value: ULong) = addBytes(listOf(Opcodes.LPUSH, *value.toBytes().toTypedArray()))

    fun bload(variable: UShort) = addBytes(listOf(Opcodes.BLOAD, *variable.toBytes().toTypedArray()))
    fun sload(variable: UShort) = addBytes(listOf(Opcodes.SLOAD, *variable.toBytes().toTypedArray()))
    fun iload(variable: UShort) = addBytes(listOf(Opcodes.ILOAD, *variable.toBytes().toTypedArray()))
    fun lload(variable: UShort) = addBytes(listOf(Opcodes.LLOAD, *variable.toBytes().toTypedArray()))

    fun load(type: String, variable: UShort) {
        when(type) {
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

    fun bstore(variable: UShort) = addBytes(listOf(Opcodes.BSTORE, *variable.toBytes().toTypedArray()))
    fun sstore(variable: UShort) = addBytes(listOf(Opcodes.SSTORE, *variable.toBytes().toTypedArray()))
    fun istore(variable: UShort) = addBytes(listOf(Opcodes.ISTORE, *variable.toBytes().toTypedArray()))
    fun lstore(variable: UShort) = addBytes(listOf(Opcodes.LSTORE, *variable.toBytes().toTypedArray()))

    fun store(type: String, variable: UShort) {
        when(type) {
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

    fun badd() = addByte(Opcodes.BADD)
    fun sadd() = addByte(Opcodes.SADD)
    fun iadd() = addByte(Opcodes.IADD)
    fun ladd() = addByte(Opcodes.LADD)
    fun fadd() = addByte(Opcodes.FADD)
    fun dadd() = addByte(Opcodes.DADD)

    fun add(type: String) {
        when(type) {
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

    fun sub(type: String) {
        when(type) {
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

    fun mul(type: String) {
        when(type) {
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

    fun div(type: String) {
        when(type) {
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

    fun mod(type: String) {
        when(type) {
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

    fun bneg() = addByte(Opcodes.BNEG)
    fun sneg() = addByte(Opcodes.SNEG)
    fun ineg() = addByte(Opcodes.INEG)
    fun lneg() = addByte(Opcodes.LNEG)
    fun fneg() = addByte(Opcodes.FNEG)
    fun dneg() = addByte(Opcodes.DNEG)

    fun neg(type: String) {
        when(type) {
            "B" -> bneg()
            "S" -> sneg()
            "I" -> ineg()
            "J" -> lneg()
            "F" -> fneg()
            "D" -> dneg()
        }
    }

    fun band() = addByte(Opcodes.BAND)
    fun sand() = addByte(Opcodes.SAND)
    fun iand() = addByte(Opcodes.IAND)
    fun land() = addByte(Opcodes.LAND)

    fun band(type: String) {
        when(type) {
            "B" -> band()
            "S" -> sand()
            "I" -> iand()
            "J" -> land()
        }
    }

    fun bor() = addByte(Opcodes.BOR)
    fun sor() = addByte(Opcodes.SOR)
    fun ior() = addByte(Opcodes.IOR)
    fun lor() = addByte(Opcodes.LOR)

    fun bor(type: String) {
        when(type) {
            "B" -> bor()
            "S" -> sor()
            "I" -> ior()
            "J" -> lor()
        }
    }

    fun bxor() = addByte(Opcodes.BXOR)
    fun sxor() = addByte(Opcodes.SXOR)
    fun ixor() = addByte(Opcodes.IXOR)
    fun lxor() = addByte(Opcodes.LXOR)

    fun bxor(type: String) {
        when(type) {
            "B" -> bxor()
            "S" -> sxor()
            "I" -> ixor()
            "J" -> lxor()
        }
    }

    fun bnot() = addByte(Opcodes.BNOT)
    fun snot() = addByte(Opcodes.SNOT)
    fun inot() = addByte(Opcodes.INOT)
    fun lnot() = addByte(Opcodes.LNOT)

    fun bnot(type: String) {
        when(type) {
            "B" -> bnot()
            "S" -> snot()
            "I" -> inot()
            "J" -> lnot()
        }
    }

    fun bshl() = addByte(Opcodes.BSHL)
    fun sshl() = addByte(Opcodes.SSHL)
    fun ishl() = addByte(Opcodes.ISHL)
    fun lshl() = addByte(Opcodes.LSHL)

    fun bshl(type: String) {
        when(type) {
            "B" -> bshl()
            "S" -> sshl()
            "I" -> ishl()
            "J" -> lshl()
        }
    }

    fun bshr() = addByte(Opcodes.BSHR)
    fun sshr() = addByte(Opcodes.SSHR)
    fun ishr() = addByte(Opcodes.ISHR)
    fun lshr() = addByte(Opcodes.LSHR)

    fun bshr(type: String) {
        when(type) {
            "B" -> bshr()
            "S" -> sshr()
            "I" -> ishr()
            "J" -> lshr()
        }
    }

    fun bshru() = addByte(Opcodes.BSHRU)
    fun sshru() = addByte(Opcodes.SSHRU)
    fun ishru() = addByte(Opcodes.ISHRU)
    fun lshru() = addByte(Opcodes.LSHRU)

    fun bshru(type: String) {
        when(type) {
            "B" -> bshru()
            "S" -> sshru()
            "I" -> ishru()
            "J" -> lshru()
        }
    }

    fun binc() = addByte(Opcodes.BINC)
    fun sinc() = addByte(Opcodes.SINC)
    fun iinc() = addByte(Opcodes.IINC)
    fun linc() = addByte(Opcodes.LINC)
    fun finc() = addByte(Opcodes.FINC)
    fun dinc() = addByte(Opcodes.DINC)

    fun binc(type: String) {
        when(type) {
            "B" -> binc()
            "S" -> sinc()
            "I" -> iinc()
            "J" -> linc()
            "F" -> finc()
            "D" -> dinc()
        }
    }

    fun bdec() = addByte(Opcodes.BDEC)
    fun sdec() = addByte(Opcodes.SDEC)
    fun idec() = addByte(Opcodes.IDEC)
    fun ldec() = addByte(Opcodes.LDEC)
    fun fdec() = addByte(Opcodes.FDEC)
    fun ddec() = addByte(Opcodes.DDEC)

    fun bdec(type: String) {
        when(type) {
            "B" -> bdec()
            "S" -> sdec()
            "I" -> idec()
            "J" -> ldec()
            "F" -> fdec()
            "D" -> ddec()
        }
    }

    fun bcmp() = addByte(Opcodes.BCMP)
    fun scmp() = addByte(Opcodes.SCMP)
    fun icmp() = addByte(Opcodes.ICMP)
    fun lcmp() = addByte(Opcodes.LCMP)
    fun fcmp() = addByte(Opcodes.FCMP)
    fun dcmp() = addByte(Opcodes.DCMP)
    fun ubcmp() = addByte(Opcodes.UBCMP)
    fun uscmp() = addByte(Opcodes.USCMP)
    fun uicmp() = addByte(Opcodes.UICMP)
    fun ulcmp() = addByte(Opcodes.ULCMP)

    fun cmp(type: String) {
        when(type) {
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

    fun cgt() = addByte(Opcodes.CGT)
    fun clt() = addByte(Opcodes.CLT)
    fun cge() = addByte(Opcodes.CGE)
    fun cle() = addByte(Opcodes.CLE)
    fun ceq() = addByte(Opcodes.CEQ)
    fun cne() = addByte(Opcodes.CNE)

    fun jmp(address: Int) = addBytes(listOf(Opcodes.JMP, *address.toBytes().toTypedArray()))
    fun jz(address: Int) = addBytes(listOf(Opcodes.JZ, *address.toBytes().toTypedArray()))
    fun jnz(address: Int) = addBytes(listOf(Opcodes.JNZ, *address.toBytes().toTypedArray()))
    fun je(address: Int) = addBytes(listOf(Opcodes.JE, *address.toBytes().toTypedArray()))
    fun jne(address: Int) = addBytes(listOf(Opcodes.JNE, *address.toBytes().toTypedArray()))
    fun jl(address: Int) = addBytes(listOf(Opcodes.JL, *address.toBytes().toTypedArray()))
    fun jle(address: Int) = addBytes(listOf(Opcodes.JLE, *address.toBytes().toTypedArray()))
    fun jg(address: Int) = addBytes(listOf(Opcodes.JG, *address.toBytes().toTypedArray()))
    fun jge(address: Int) = addBytes(listOf(Opcodes.JGE, *address.toBytes().toTypedArray()))

    fun jmp(): IntPlaceholder {
        addByte(Opcodes.JMP)
        return iPlaceholder()
    }

    fun jz(): IntPlaceholder {
        addByte(Opcodes.JZ)
        return iPlaceholder()
    }

    fun jnz(): IntPlaceholder {
        addByte(Opcodes.JNZ)
        return iPlaceholder()
    }

    fun je(): IntPlaceholder {
        addByte(Opcodes.JE)
        return iPlaceholder()
    }

    fun jne(): IntPlaceholder {
        addByte(Opcodes.JNE)
        return iPlaceholder()
    }

    fun jl(): IntPlaceholder {
        addByte(Opcodes.JL)
        return iPlaceholder()
    }

    fun jle(): IntPlaceholder {
        addByte(Opcodes.JLE)
        return iPlaceholder()
    }

    fun jg(): IntPlaceholder {
        addByte(Opcodes.JG)
        return iPlaceholder()
    }

    fun jge(): IntPlaceholder {
        addByte(Opcodes.JGE)
        return iPlaceholder()
    }

    fun ret() = addByte(Opcodes.RET)
    fun bret() = addByte(Opcodes.BRET)
    fun sret() = addByte(Opcodes.SRET)
    fun iret() = addByte(Opcodes.IRET)
    fun lret() = addByte(Opcodes.LRET)
    fun ret(type: String) {
        when(type) {
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

    fun pop() = addByte(Opcodes.POP)
    fun bpop() = addByte(Opcodes.POP)
    fun spop() = addByte(Opcodes.SPOP)
    fun ipop() = addByte(Opcodes.IPOP)
    fun lpop() = addByte(Opcodes.LPOP)

    fun pop(type: String) {
        when(type) {
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

    fun dup() = addByte(Opcodes.DUP)
    fun bdup() = addByte(Opcodes.DUP)
    fun sdup() = addByte(Opcodes.SDUP)
    fun idup() = addByte(Opcodes.IDUP)
    fun ldup() = addByte(Opcodes.LDUP)

    fun pcast(from: UByte, to: UByte) = addBytes(Opcodes.PCAST, ((from shl 4) or to).toByte())

    fun call(address: Int) = addBytes(listOf(Opcodes.CALL, *address.toBytes().toTypedArray()))

    fun call(): IntPlaceholder {
        addByte(Opcodes.CALL)
        return iPlaceholder()
    }

    fun toByteArray() = bytes.toByteArray()

    fun bPlaceholder(): BytePlaceholder {
        val index = bytes.size
        addByte(0)
        return BytePlaceholder(index)
    }

    fun sPlaceholder(): ShortPlaceholder {
        val index = bytes.size
        addBytes(0, 0)
        return ShortPlaceholder(index)
    }

    fun iPlaceholder(): IntPlaceholder {
        val index = bytes.size
        addBytes(0, 0, 0, 0)
        return IntPlaceholder(index)
    }

    fun lPlaceholder(): LongPlaceholder {
        val index = bytes.size
        addBytes(0, 0, 0, 0, 0, 0, 0, 0)
        return LongPlaceholder(index)
    }

    inner class BytePlaceholder(val index: Int) {
        fun set(byte: Byte) {
            bytes[index] = byte
        }

        fun set(boolean: Boolean) {
            bytes[index] = if (boolean) 1 else 0
        }

        fun set(unsignedByte: UByte) {
            bytes[index] = unsignedByte.toByte()
        }
    }

    inner class ShortPlaceholder(val index: Int) {
        fun set(short: Short) {
            bytes.setBytes(index, short.toBytes())
        }

        fun set(unsignedShort: UShort) {
            bytes.setBytes(index, unsignedShort.toBytes())
        }

        fun set(char: Char) {
            bytes.setBytes(index, char.code.toShort().toBytes())
        }
    }

    inner class IntPlaceholder(val index: Int) {
        fun set(int: Int) {
            bytes.setBytes(index, int.toBytes())
        }

        fun set(unsignedInt: UInt) {
            bytes.setBytes(index, unsignedInt.toBytes())
        }

        fun set(float: Float) {
            bytes.setBytes(index, float.toRawBits().toBytes())
        }
    }

    inner class LongPlaceholder(val index: Int) {
        fun set(long: Long) {
            bytes.setBytes(index, long.toBytes())
        }

        fun set(unsignedLong: ULong) {
            bytes.setBytes(index, unsignedLong.toBytes())
        }

        fun set(double: Double) {
            bytes.setBytes(index, double.toRawBits().toBytes())
        }
    }
}

open class PooledShakeBytecodeInstructionGenerator(
    val constantPool: MutableConstantPool,
    bytes: MutableList<Byte> = mutableListOf<Byte>()
) : ShakeBytecodeInstructionGenerator(
    bytes
) {

    fun utf8Ref(value: String) = addBytes(*constantPool.resolveUtf8(value).toBytes())
    fun byteRef(value: Byte) = addBytes(*constantPool.resolveByte(value).toBytes())
    fun shortRef(value: Short) = addBytes(*constantPool.resolveShort(value).toBytes())
    fun intRef(value: Int) = addBytes(*constantPool.resolveInt(value).toBytes())
    fun longRef(value: Long) = addBytes(*constantPool.resolveLong(value).toBytes())
    fun floatRef(value: Float) = addBytes(*constantPool.resolveFloat(value).toBytes())
    fun doubleRef(value: Double) = addBytes(*constantPool.resolveDouble(value).toBytes())
    fun classRef(value: String) = addBytes(*constantPool.resolveClass(value).toBytes())

    fun call(descriptor: String) {
        addByte(Opcodes.CALL)
        utf8Ref(descriptor)
    }
}

fun bytecode(init: ShakeBytecodeInstructionGenerator.() -> Unit): ByteArray {
    val generator = ShakeBytecodeInstructionGenerator()
    generator.init()
    return generator.toByteArray()
}

fun bytecode(
    constantPool: MutableConstantPool,
    init: PooledShakeBytecodeInstructionGenerator.() -> Unit
): ByteArray {
    val generator = PooledShakeBytecodeInstructionGenerator(constantPool)
    generator.init()
    return generator.toByteArray()
}