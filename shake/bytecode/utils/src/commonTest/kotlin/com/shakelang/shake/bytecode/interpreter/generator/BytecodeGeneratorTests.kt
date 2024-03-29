@file:Suppress("unused")

package com.shakelang.shake.bytecode.interpreter.generator

import com.shakelang.shake.bytecode.interpreter.Opcodes
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class BytecodeGeneratorTests : FreeSpec(
    {

        "nop" {
            val bytes = bytecode {
                nop()
            }
            bytes shouldBe arrayOf(Opcodes.NOP)
        }

        "bpush" {
            val bytes = bytecode {
                bpush(0x01)
            }
            bytes shouldBe arrayOf(Opcodes.BPUSH, 0x01)
        }

        "spush" {
            val bytes = bytecode {
                spush(0x0102)
            }
            bytes shouldBe arrayOf(Opcodes.SPUSH, 0x01, 0x02)
        }

        "ipush" {
            val bytes = bytecode {
                ipush(0x01020304)
            }
            bytes shouldBe arrayOf(Opcodes.IPUSH, 0x01, 0x02, 0x03, 0x04)
        }

        "lpush" {
            val bytes = bytecode {
                lpush(0x0102030405060708)
            }
            bytes shouldBe arrayOf(Opcodes.LPUSH, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07.toByte(), 0x08.toByte())
        }

        "bload" {
            val bytes = bytecode {
                bload(0x0102u)
            }
            bytes shouldBe arrayOf(Opcodes.BLOAD, 0x01, 0x02)
        }

        "sload" {
            val bytes = bytecode {
                sload(0x0102u)
            }
            bytes shouldBe arrayOf(Opcodes.SLOAD, 0x01, 0x02)
        }

        "iload" {
            val bytes = bytecode {
                iload(0x0102u)
            }
            bytes shouldBe arrayOf(Opcodes.ILOAD, 0x01, 0x02)
        }

        "lload" {
            val bytes = bytecode {
                lload(0x0102u)
            }
            bytes shouldBe arrayOf(Opcodes.LLOAD, 0x01, 0x02)
        }

        "bstore" {
            val bytes = bytecode {
                bstore(0x0102u)
            }
            bytes shouldBe arrayOf(Opcodes.BSTORE, 0x01, 0x02)
        }

        "sstore" {
            val bytes = bytecode {
                sstore(0x0102u)
            }
            bytes shouldBe arrayOf(Opcodes.SSTORE, 0x01, 0x02)
        }

        "istore" {
            val bytes = bytecode {
                istore(0x0102u)
            }
            bytes shouldBe arrayOf(Opcodes.ISTORE, 0x01, 0x02)
        }

        "lstore" {
            val bytes = bytecode {
                lstore(0x0102u)
            }
            bytes shouldBe arrayOf(Opcodes.LSTORE, 0x01, 0x02)
        }

        "badd" {
            val bytes = bytecode {
                badd()
            }
            bytes shouldBe arrayOf(Opcodes.BADD)
        }

        "sadd" {
            val bytes = bytecode {
                sadd()
            }
            bytes shouldBe arrayOf(Opcodes.SADD)
        }

        "iadd" {
            val bytes = bytecode {
                iadd()
            }
            bytes shouldBe arrayOf(Opcodes.IADD)
        }

        "ladd" {
            val bytes = bytecode {
                ladd()
            }
            bytes shouldBe arrayOf(Opcodes.LADD)
        }

        "fadd" {
            val bytes = bytecode {
                fadd()
            }
            bytes shouldBe arrayOf(Opcodes.FADD)
        }

        "dadd" {
            val bytes = bytecode {
                dadd()
            }
            bytes shouldBe arrayOf(Opcodes.DADD)
        }

        "bsub" {
            val bytes = bytecode {
                bsub()
            }
            bytes shouldBe arrayOf(Opcodes.BSUB)
        }

        "ssub" {
            val bytes = bytecode {
                ssub()
            }
            bytes shouldBe arrayOf(Opcodes.SSUB)
        }

        "isub" {
            val bytes = bytecode {
                isub()
            }
            bytes shouldBe arrayOf(Opcodes.ISUB)
        }

        "lsub" {
            val bytes = bytecode {
                lsub()
            }
            bytes shouldBe arrayOf(Opcodes.LSUB)
        }

        "ubsub" {
            val bytes = bytecode {
                ubsub()
            }
            bytes shouldBe arrayOf(Opcodes.UBSUB)
        }

        "ussub" {
            val bytes = bytecode {
                ussub()
            }
            bytes shouldBe arrayOf(Opcodes.USSUB)
        }

        "uisub" {
            val bytes = bytecode {
                uisub()
            }
            bytes shouldBe arrayOf(Opcodes.UISUB)
        }

        "ulsub" {
            val bytes = bytecode {
                ulsub()
            }
            bytes shouldBe arrayOf(Opcodes.ULSUB)
        }

        "fsub" {
            val bytes = bytecode {
                fsub()
            }
            bytes shouldBe arrayOf(Opcodes.FSUB)
        }

        "dsub" {
            val bytes = bytecode {
                dsub()
            }
            bytes shouldBe arrayOf(Opcodes.DSUB)
        }

        "bmul" {
            val bytes = bytecode {
                bmul()
            }
            bytes shouldBe arrayOf(Opcodes.BMUL)
        }

        "smul" {
            val bytes = bytecode {
                smul()
            }
            bytes shouldBe arrayOf(Opcodes.SMUL)
        }

        "imul" {
            val bytes = bytecode {
                imul()
            }
            bytes shouldBe arrayOf(Opcodes.IMUL)
        }

        "lmul" {
            val bytes = bytecode {
                lmul()
            }
            bytes shouldBe arrayOf(Opcodes.LMUL)
        }

        "ubmul" {
            val bytes = bytecode {
                ubmul()
            }
            bytes shouldBe arrayOf(Opcodes.UBMUL)
        }

        "usmul" {
            val bytes = bytecode {
                usmul()
            }
            bytes shouldBe arrayOf(Opcodes.USMUL)
        }

        "uimul" {
            val bytes = bytecode {
                uimul()
            }
            bytes shouldBe arrayOf(Opcodes.UIMUL)
        }

        "ulmul" {
            val bytes = bytecode {
                ulmul()
            }
            bytes shouldBe arrayOf(Opcodes.ULMUL)
        }

        "fmul" {
            val bytes = bytecode {
                fmul()
            }
            bytes shouldBe arrayOf(Opcodes.FMUL)
        }

        "dmul" {
            val bytes = bytecode {
                dmul()
            }
            bytes shouldBe arrayOf(Opcodes.DMUL)
        }

        "bdiv" {
            val bytes = bytecode {
                bdiv()
            }
            bytes shouldBe arrayOf(Opcodes.BDIV)
        }

        "sdiv" {
            val bytes = bytecode {
                sdiv()
            }
            bytes shouldBe arrayOf(Opcodes.SDIV)
        }

        "idiv" {
            val bytes = bytecode {
                idiv()
            }
            bytes shouldBe arrayOf(Opcodes.IDIV)
        }

        "ldiv" {
            val bytes = bytecode {
                ldiv()
            }
            bytes shouldBe arrayOf(Opcodes.LDIV)
        }

        "ubdiv" {
            val bytes = bytecode {
                ubdiv()
            }
            bytes shouldBe arrayOf(Opcodes.UBDIV)
        }

        "usdiv" {
            val bytes = bytecode {
                usdiv()
            }
            bytes shouldBe arrayOf(Opcodes.USDIV)
        }

        "uidiv" {
            val bytes = bytecode {
                uidiv()
            }
            bytes shouldBe arrayOf(Opcodes.UIDIV)
        }

        "uldiv" {
            val bytes = bytecode {
                uldiv()
            }
            bytes shouldBe arrayOf(Opcodes.ULDIV)
        }

        "fdiv" {
            val bytes = bytecode {
                fdiv()
            }
            bytes shouldBe arrayOf(Opcodes.FDIV)
        }

        "ddiv" {
            val bytes = bytecode {
                ddiv()
            }
            bytes shouldBe arrayOf(Opcodes.DDIV)
        }

        "bmod" {
            val bytes = bytecode {
                bmod()
            }
            bytes shouldBe arrayOf(Opcodes.BMOD)
        }

        "smod" {
            val bytes = bytecode {
                smod()
            }
            bytes shouldBe arrayOf(Opcodes.SMOD)
        }

        "imod" {
            val bytes = bytecode {
                imod()
            }
            bytes shouldBe arrayOf(Opcodes.IMOD)
        }

        "lmod" {
            val bytes = bytecode {
                lmod()
            }
            bytes shouldBe arrayOf(Opcodes.LMOD)
        }

        "ubmod" {
            val bytes = bytecode {
                ubmod()
            }
            bytes shouldBe arrayOf(Opcodes.UBMOD)
        }

        "usmod" {
            val bytes = bytecode {
                usmod()
            }
            bytes shouldBe arrayOf(Opcodes.USMOD)
        }

        "uimod" {
            val bytes = bytecode {
                uimod()
            }
            bytes shouldBe arrayOf(Opcodes.UIMOD)
        }

        "ulmod" {
            val bytes = bytecode {
                ulmod()
            }
            bytes shouldBe arrayOf(Opcodes.ULMOD)
        }

        "fmod" {
            val bytes = bytecode {
                fmod()
            }
            bytes shouldBe arrayOf(Opcodes.FMOD)
        }

        "dmod" {
            val bytes = bytecode {
                dmod()
            }
            bytes shouldBe arrayOf(Opcodes.DMOD)
        }

        "bneg" {
            val bytes = bytecode {
                bneg()
            }
            bytes shouldBe arrayOf(Opcodes.BNEG)
        }

        "sneg" {
            val bytes = bytecode {
                sneg()
            }
            bytes shouldBe arrayOf(Opcodes.SNEG)
        }

        "ineg" {
            val bytes = bytecode {
                ineg()
            }
            bytes shouldBe arrayOf(Opcodes.INEG)
        }

        "lneg" {
            val bytes = bytecode {
                lneg()
            }
            bytes shouldBe arrayOf(Opcodes.LNEG)
        }

        "fneg" {
            val bytes = bytecode {
                fneg()
            }
            bytes shouldBe arrayOf(Opcodes.FNEG)
        }

        "dneg" {
            val bytes = bytecode {
                dneg()
            }
            bytes shouldBe arrayOf(Opcodes.DNEG)
        }

        "band" {
            val bytes = bytecode {
                band()
            }
            bytes shouldBe arrayOf(Opcodes.BAND)
        }

        "sand" {
            val bytes = bytecode {
                sand()
            }
            bytes shouldBe arrayOf(Opcodes.SAND)
        }

        "iand" {
            val bytes = bytecode {
                iand()
            }
            bytes shouldBe arrayOf(Opcodes.IAND)
        }

        "land" {
            val bytes = bytecode {
                land()
            }
            bytes shouldBe arrayOf(Opcodes.LAND)
        }

        "bor" {
            val bytes = bytecode {
                bor()
            }
            bytes shouldBe arrayOf(Opcodes.BOR)
        }

        "sor" {
            val bytes = bytecode {
                sor()
            }
            bytes shouldBe arrayOf(Opcodes.SOR)
        }

        "ior" {
            val bytes = bytecode {
                ior()
            }
            bytes shouldBe arrayOf(Opcodes.IOR)
        }

        "lor" {
            val bytes = bytecode {
                lor()
            }
            bytes shouldBe arrayOf(Opcodes.LOR)
        }

        "bxor" {
            val bytes = bytecode {
                bxor()
            }
            bytes shouldBe arrayOf(Opcodes.BXOR)
        }

        "sxor" {
            val bytes = bytecode {
                sxor()
            }
            bytes shouldBe arrayOf(Opcodes.SXOR)
        }

        "ixor" {
            val bytes = bytecode {
                ixor()
            }
            bytes shouldBe arrayOf(Opcodes.IXOR)
        }

        "lxor" {
            val bytes = bytecode {
                lxor()
            }
            bytes shouldBe arrayOf(Opcodes.LXOR)
        }

        "bnot" {
            val bytes = bytecode {
                bnot()
            }
            bytes shouldBe arrayOf(Opcodes.BNOT)
        }

        "snot" {
            val bytes = bytecode {
                snot()
            }
            bytes shouldBe arrayOf(Opcodes.SNOT)
        }

        "inot" {
            val bytes = bytecode {
                inot()
            }
            bytes shouldBe arrayOf(Opcodes.INOT)
        }

        "lnot" {
            val bytes = bytecode {
                lnot()
            }
            bytes shouldBe arrayOf(Opcodes.LNOT)
        }

        "bshl" {
            val bytes = bytecode {
                bshl()
            }
            bytes shouldBe arrayOf(Opcodes.BSHL)
        }

        "sshl" {
            val bytes = bytecode {
                sshl()
            }
            bytes shouldBe arrayOf(Opcodes.SSHL)
        }

        "ishl" {
            val bytes = bytecode {
                ishl()
            }
            bytes shouldBe arrayOf(Opcodes.ISHL)
        }

        "lshl" {
            val bytes = bytecode {
                lshl()
            }
            bytes shouldBe arrayOf(Opcodes.LSHL)
        }

        "bshr" {
            val bytes = bytecode {
                bshr()
            }
            bytes shouldBe arrayOf(Opcodes.BSHR)
        }

        "sshr" {
            val bytes = bytecode {
                sshr()
            }
            bytes shouldBe arrayOf(Opcodes.SSHR)
        }

        "ishr" {
            val bytes = bytecode {
                ishr()
            }
            bytes shouldBe arrayOf(Opcodes.ISHR)
        }

        "lshr" {
            val bytes = bytecode {
                lshr()
            }
            bytes shouldBe arrayOf(Opcodes.LSHR)
        }

        "bshru" {
            val bytes = bytecode {
                bshru()
            }
            bytes shouldBe arrayOf(Opcodes.BSHRU)
        }

        "sshru" {
            val bytes = bytecode {
                sshru()
            }
            bytes shouldBe arrayOf(Opcodes.SSHRU)
        }

        "ishru" {
            val bytes = bytecode {
                ishru()
            }
            bytes shouldBe arrayOf(Opcodes.ISHRU)
        }

        "lshru" {
            val bytes = bytecode {
                lshru()
            }
            bytes shouldBe arrayOf(Opcodes.LSHRU)
        }

        "binc" {
            val bytes = bytecode {
                binc()
            }
            bytes shouldBe arrayOf(Opcodes.BINC)
        }

        "sinc" {
            val bytes = bytecode {
                sinc()
            }
            bytes shouldBe arrayOf(Opcodes.SINC)
        }

        "iinc" {
            val bytes = bytecode {
                iinc()
            }
            bytes shouldBe arrayOf(Opcodes.IINC)
        }

        "linc" {
            val bytes = bytecode {
                linc()
            }
            bytes shouldBe arrayOf(Opcodes.LINC)
        }

        "finc" {
            val bytes = bytecode {
                finc()
            }
            bytes shouldBe arrayOf(Opcodes.FINC)
        }

        "dinc" {
            val bytes = bytecode {
                dinc()
            }
            bytes shouldBe arrayOf(Opcodes.DINC)
        }

        "bdec" {
            val bytes = bytecode {
                bdec()
            }
            bytes shouldBe arrayOf(Opcodes.BDEC)
        }

        "sdec" {
            val bytes = bytecode {
                sdec()
            }
            bytes shouldBe arrayOf(Opcodes.SDEC)
        }

        "idec" {
            val bytes = bytecode {
                idec()
            }
            bytes shouldBe arrayOf(Opcodes.IDEC)
        }

        "ldec" {
            val bytes = bytecode {
                ldec()
            }
            bytes shouldBe arrayOf(Opcodes.LDEC)
        }

        "fdec" {
            val bytes = bytecode {
                fdec()
            }
            bytes shouldBe arrayOf(Opcodes.FDEC)
        }

        "ddec" {
            val bytes = bytecode {
                ddec()
            }
            bytes shouldBe arrayOf(Opcodes.DDEC)
        }

        "bcmp" {
            val bytes = bytecode {
                bcmp()
            }
            bytes shouldBe arrayOf(Opcodes.BCMP)
        }

        "scmp" {
            val bytes = bytecode {
                scmp()
            }
            bytes shouldBe arrayOf(Opcodes.SCMP)
        }

        "icmp" {
            val bytes = bytecode {
                icmp()
            }
            bytes shouldBe arrayOf(Opcodes.ICMP)
        }

        "lcmp" {
            val bytes = bytecode {
                lcmp()
            }
            bytes shouldBe arrayOf(Opcodes.LCMP)
        }

        "fcmp" {
            val bytes = bytecode {
                fcmp()
            }
            bytes shouldBe arrayOf(Opcodes.FCMP)
        }

        "dcmp" {
            val bytes = bytecode {
                dcmp()
            }
            bytes shouldBe arrayOf(Opcodes.DCMP)
        }

        "ubcmp" {
            val bytes = bytecode {
                ubcmp()
            }
            bytes shouldBe arrayOf(Opcodes.UBCMP)
        }

        "uscmp" {
            val bytes = bytecode {
                uscmp()
            }
            bytes shouldBe arrayOf(Opcodes.USCMP)
        }

        "uicmp" {
            val bytes = bytecode {
                uicmp()
            }
            bytes shouldBe arrayOf(Opcodes.UICMP)
        }

        "ulcmp" {
            val bytes = bytecode {
                ulcmp()
            }
            bytes shouldBe arrayOf(Opcodes.ULCMP)
        }

        "cmp" {
            val bytes = bytecode {
                cmp("B")
                cmp("S")
                cmp("I")
                cmp("J")
                cmp("F")
                cmp("D")
                cmp("b")
                cmp("s")
                cmp("i")
                cmp("j")
            }
            bytes shouldBe arrayOf(
                Opcodes.BCMP,
                Opcodes.SCMP,
                Opcodes.ICMP,
                Opcodes.LCMP,
                Opcodes.FCMP,
                Opcodes.DCMP,
                Opcodes.UBCMP,
                Opcodes.USCMP,
                Opcodes.UICMP,
                Opcodes.ULCMP,
            )
        }

        "cgt" {
            val bytes = bytecode {
                cgt()
            }
            bytes shouldBe arrayOf(Opcodes.CGT)
        }

        "clt" {
            val bytes = bytecode {
                clt()
            }
            bytes shouldBe arrayOf(Opcodes.CLT)
        }

        "cge" {
            val bytes = bytecode {
                cge()
            }
            bytes shouldBe arrayOf(Opcodes.CGE)
        }

        "cle" {
            val bytes = bytecode {
                cle()
            }
            bytes shouldBe arrayOf(Opcodes.CLE)
        }

        "ceq" {
            val bytes = bytecode {
                ceq()
            }
            bytes shouldBe arrayOf(Opcodes.CEQ)
        }

        "cne" {
            val bytes = bytecode {
                cne()
            }
            bytes shouldBe arrayOf(Opcodes.CNE)
        }

        "jmp" {
            val bytes = bytecode {
                jmp(0x01020304)
            }
            bytes shouldBe arrayOf(Opcodes.JMP, 0x01, 0x02, 0x03, 0x04)
        }

        "jz" {
            val bytes = bytecode {
                jz(0x01020304)
            }
            bytes shouldBe arrayOf(Opcodes.JZ, 0x01, 0x02, 0x03, 0x04)
        }

        "jnz" {
            val bytes = bytecode {
                jnz(0x01020304)
            }
            bytes shouldBe arrayOf(Opcodes.JNZ, 0x01, 0x02, 0x03, 0x04)
        }

        "je" {
            val bytes = bytecode {
                je(0x01020304)
            }
            bytes shouldBe arrayOf(Opcodes.JE, 0x01, 0x02, 0x03, 0x04)
        }

        "jne" {
            val bytes = bytecode {
                jne(0x01020304)
            }
            bytes shouldBe arrayOf(Opcodes.JNE, 0x01, 0x02, 0x03, 0x04)
        }

        "jl" {
            val bytes = bytecode {
                jlt(0x01020304)
            }
            bytes shouldBe arrayOf(Opcodes.JL, 0x01, 0x02, 0x03, 0x04)
        }

        "jle" {
            val bytes = bytecode {
                jle(0x01020304)
            }
            bytes shouldBe arrayOf(Opcodes.JLE, 0x01, 0x02, 0x03, 0x04)
        }

        "jg" {
            val bytes = bytecode {
                jgt(0x01020304)
            }
            bytes shouldBe arrayOf(Opcodes.JG, 0x01, 0x02, 0x03, 0x04)
        }

        "jge" {
            val bytes = bytecode {
                jge(0x01020304)
            }
            bytes shouldBe arrayOf(Opcodes.JGE, 0x01, 0x02, 0x03, 0x04)
        }

        "ret" {
            val bytes = bytecode {
                ret()
            }
            bytes shouldBe arrayOf(Opcodes.RET)
        }

        "bret" {
            val bytes = bytecode {
                bret()
            }
            bytes shouldBe arrayOf(Opcodes.BRET)
        }

        "sret" {
            val bytes = bytecode {
                sret()
            }
            bytes shouldBe arrayOf(Opcodes.SRET)
        }

        "iret" {
            val bytes = bytecode {
                iret()
            }
            bytes shouldBe arrayOf(Opcodes.IRET)
        }

        "lret" {
            val bytes = bytecode {
                lret()
            }
            bytes shouldBe arrayOf(Opcodes.LRET)
        }

        "pop" {
            val bytes = bytecode {
                pop()
            }
            bytes shouldBe arrayOf(Opcodes.BPOP)
        }

        "bpop" {
            val bytes = bytecode {
                bpop()
            }
            bytes shouldBe arrayOf(Opcodes.BPOP)
        }

        "spop" {
            val bytes = bytecode {
                spop()
            }
            bytes shouldBe arrayOf(Opcodes.SPOP)
        }

        "ipop" {
            val bytes = bytecode {
                ipop()
            }
            bytes shouldBe arrayOf(Opcodes.IPOP)
        }

        "lpop" {
            val bytes = bytecode {
                lpop()
            }
            bytes shouldBe arrayOf(Opcodes.LPOP)
        }

        "dup" {
            val bytes = bytecode {
                dup()
            }
            bytes shouldBe arrayOf(Opcodes.BDUP)
        }

        "bdup" {
            val bytes = bytecode {
                bdup()
            }
            bytes shouldBe arrayOf(Opcodes.BDUP)
        }

        "sdup" {
            val bytes = bytecode {
                sdup()
            }
            bytes shouldBe arrayOf(Opcodes.SDUP)
        }

        "idup" {
            val bytes = bytecode {
                idup()
            }
            bytes shouldBe arrayOf(Opcodes.IDUP)
        }

        "ldup" {
            val bytes = bytecode {
                ldup()
            }
            bytes shouldBe arrayOf(Opcodes.LDUP)
        }

        "pcast" {
            for (from in 0u..0xFu) {
                for (to in 0u..0xFu) {
                    val bytes = bytecode {
                        pcast(from.toUByte(), to.toUByte())
                    }
                    bytes shouldBe arrayOf(Opcodes.PCAST, ((from shl 4) or to).toByte())
                }
            }
        }

        "invoke_static" {
            val bytes = bytecode {
                invoke_static(0x01020304)
            }
            bytes shouldBe arrayOf(Opcodes.INVOKE_STATIC, 0x01, 0x02, 0x03, 0x04)
        }

        "invoke_virtual" {
            val bytes = bytecode {
                invoke_virtual(0x01020304)
            }
            bytes shouldBe arrayOf(Opcodes.INVOKE_VIRTUAL, 0x01, 0x02, 0x03, 0x04)
        }

        "load_static" {
            val bytes = bytecode {
                load_static(0x01020304)
            }
            bytes shouldBe arrayOf(Opcodes.LOAD_STATIC, 0x01, 0x02, 0x03, 0x04)
        }

        "store_static" {
            val bytes = bytecode {
                store_static(0x01020304)
            }
            bytes shouldBe arrayOf(Opcodes.STORE_STATIC, 0x01, 0x02, 0x03, 0x04)
        }

        "baload" {
            val bytes = bytecode {
                baload()
            }
            bytes shouldBe arrayOf(Opcodes.BALOAD)
        }

        "saload" {
            val bytes = bytecode {
                saload()
            }
            bytes shouldBe arrayOf(Opcodes.SALOAD)
        }

        "iaload" {
            val bytes = bytecode {
                iaload()
            }
            bytes shouldBe arrayOf(Opcodes.IALOAD)
        }

        "laload" {
            val bytes = bytecode {
                laload()
            }
            bytes shouldBe arrayOf(Opcodes.LALOAD)
        }

        "bastore" {
            val bytes = bytecode {
                bastore()
            }
            bytes shouldBe arrayOf(Opcodes.BASTORE)
        }

        "sastore" {
            val bytes = bytecode {
                sastore()
            }
            bytes shouldBe arrayOf(Opcodes.SASTORE)
        }

        "iastore" {
            val bytes = bytecode {
                iastore()
            }
            bytes shouldBe arrayOf(Opcodes.IASTORE)
        }

        "lastore" {
            val bytes = bytecode {
                lastore()
            }
            bytes shouldBe arrayOf(Opcodes.LASTORE)
        }

        "new_array" {
            val bytes = bytecode {
                new_array(0x01)
            }
            bytes shouldBe arrayOf(Opcodes.NEW_ARR, 0x00, 0x00, 0x00, 0x01)
        }

        "toByteArray" {
            val bytes = bytecode {
                bshru()
                sshru()
                ishru()
                lshru()
            }
            bytes shouldBe arrayOf(Opcodes.BSHRU, Opcodes.SSHRU, Opcodes.ISHRU, Opcodes.LSHRU)
        }
    },
)
