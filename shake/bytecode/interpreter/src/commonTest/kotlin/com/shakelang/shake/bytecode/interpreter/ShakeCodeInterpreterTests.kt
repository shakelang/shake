@file:Suppress("unused")

package com.shakelang.shake.bytecode.interpreter

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ShakeCodeInterpreterTests : FreeSpec({

    "bpush" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(1)
                bpush(2)
                bpush(3)
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 3
        stack.pop() shouldBe 3
        stack.pop() shouldBe 2
        stack.pop() shouldBe 1
    }

    "spush" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                spush(1)
                spush(2)
                spush(3)
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 6
        stack.popShort() shouldBe 3
        stack.popShort() shouldBe 2
        stack.popShort() shouldBe 1
    }

    "ipush" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(1)
                ipush(2)
                ipush(3)
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 12
        stack.popInt() shouldBe 3
        stack.popInt() shouldBe 2
        stack.popInt() shouldBe 1
    }

    "lpush" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(1)
                lpush(2)
                lpush(3)
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 24
        stack.popLong() shouldBe 3
        stack.popLong() shouldBe 2
        stack.popLong() shouldBe 1
    }

    "bstore/bload" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(1)
                bstore(0u)
                bload(0u)
                bload(0u)
            },
            100
        )

        code.tick(4)

        stack.size shouldBe 2
        stack.pop() shouldBe 1
        stack.pop() shouldBe 1
    }

    "sstore/sload" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                spush(1)
                sstore(0u)
                sload(0u)
                sload(0u)
            },
            100
        )

        code.tick(4)

        stack.size shouldBe 4
        stack.popShort() shouldBe 1
        stack.popShort() shouldBe 1
    }

    "istore/iload" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(1)
                istore(0u)
                iload(0u)
                iload(0u)
            },
            100
        )

        code.tick(4)

        stack.size shouldBe 8
        stack.popInt() shouldBe 1
        stack.popInt() shouldBe 1
    }

    "lstore/lload" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(1)
                lstore(0u)
                lload(0u)
                lload(0u)
            },
            100
        )

        code.tick(4)

        stack.size shouldBe 16
        stack.popLong() shouldBe 1
        stack.popLong() shouldBe 1
    }

    "badd" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(1)
                bpush(2)
                badd()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popInt() shouldBe 3
    }

    "sadd" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                spush(1)
                spush(2)
                sadd()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popInt() shouldBe 3
    }

    "iadd" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(1)
                ipush(2)
                iadd()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popInt() shouldBe 3
    }

    "ladd" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(1)
                lpush(2)
                ladd()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 8
        stack.popLong() shouldBe 3
    }

    "fadd" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(1f.toBits())
                ipush(2f.toBits())
                fadd()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popFloat() shouldBe 3f
    }

    "dadd" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(1.0.toBits())
                lpush(2.0.toBits())
                dadd()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 8
        stack.popDouble() shouldBe 3.0
    }

    "bsub" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(1)
                bpush(2)
                bsub()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popInt() shouldBe -1
    }

    "ssub" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                spush(1)
                spush(2)
                ssub()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popInt() shouldBe -1
    }

    "isub" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(1)
                ipush(2)
                isub()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popInt() shouldBe -1
    }

    "lsub" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(1)
                lpush(2)
                lsub()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 8
        stack.popLong() shouldBe -1
    }

    "fsub" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(1f.toBits())
                ipush(2f.toBits())
                fsub()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popFloat() shouldBe -1f
    }

    "dsub" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(1.0.toBits())
                lpush(2.0.toBits())
                dsub()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 8
        stack.popDouble() shouldBe -1.0
    }

    "bmul" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(2)
                bpush(3)
                bmul()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popInt() shouldBe 6
    }

    "smul" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                spush(2)
                spush(3)
                smul()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popInt() shouldBe 6
    }

    "imul" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(2)
                ipush(3)
                imul()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popInt() shouldBe 6
    }

    "lmul" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(2)
                lpush(3)
                lmul()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 8
        stack.popLong() shouldBe 6
    }

    "fmul" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(2f.toBits())
                ipush(3f.toBits())
                fmul()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popFloat() shouldBe 6f
    }

    "dmul" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(2.0.toBits())
                lpush(3.0.toBits())
                dmul()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 8
        stack.popDouble() shouldBe 6.0
    }

    "bdiv" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(6)
                bpush(3)
                bdiv()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popInt() shouldBe 2
    }

    "sdiv" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                spush(6)
                spush(3)
                sdiv()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popInt() shouldBe 2
    }

    "idiv" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(6)
                ipush(3)
                idiv()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popInt() shouldBe 2
    }

    "ldiv" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(6)
                lpush(3)
                ldiv()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 8
        stack.popLong() shouldBe 2
    }

    "fdiv" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(6f.toBits())
                ipush(3f.toBits())
                fdiv()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popFloat() shouldBe 2f
    }

    "ddiv" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(6.0.toBits())
                lpush(3.0.toBits())
                ddiv()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 8
        stack.popDouble() shouldBe 2.0
    }

    "bmod" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(6)
                bpush(3)
                bmod()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popInt() shouldBe 0
    }

    "smod" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                spush(6)
                spush(3)
                smod()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popInt() shouldBe 0
    }

    "imod" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(6)
                ipush(3)
                imod()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popInt() shouldBe 0
    }

    "lmod" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(6)
                lpush(3)
                lmod()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 8
        stack.popLong() shouldBe 0
    }

    "fmod" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(6f.toBits())
                ipush(3f.toBits())
                fmod()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popFloat() shouldBe 0f
    }

    "dmod" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(6.0.toBits())
                lpush(3.0.toBits())
                dmod()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 8
        stack.popDouble() shouldBe 0.0
    }

    "band" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(5)
                bpush(3)
                band()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 1
    }

    "sand" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                spush(5)
                spush(3)
                sand()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 2
        stack.popShort() shouldBe 1
    }

    "iand" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(5)
                ipush(3)
                iand()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popInt() shouldBe 1
    }

    "land" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(5)
                lpush(3)
                land()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 8
        stack.popLong() shouldBe 1
    }

    "bor" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(5)
                bpush(3)
                bor()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 7
    }

    "sor" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                spush(5)
                spush(3)
                sor()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 2
        stack.popShort() shouldBe 7
    }

    "ior" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(5)
                ipush(3)
                ior()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popInt() shouldBe 7
    }

    "lor" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(5)
                lpush(3)
                lor()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 8
        stack.popLong() shouldBe 7
    }

    "bxor" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(5)
                bpush(3)
                bxor()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 6
    }

    "sxor" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                spush(5)
                spush(3)
                sxor()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 2
        stack.popShort() shouldBe 6
    }

    "ixor" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(5)
                ipush(3)
                ixor()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popInt() shouldBe 6
    }

    "lxor" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(5)
                lpush(3)
                lxor()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 8
        stack.popLong() shouldBe 6
    }

    "bshl" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(5)
                bpush(3)
                bshl()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 40
    }

    "sshl" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                spush(5)
                spush(3)
                sshl()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 2
        stack.popShort() shouldBe 40
    }

    "ishl" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(5)
                ipush(3)
                ishl()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popInt() shouldBe 40
    }

    "lshl" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(5)
                lpush(3)
                lshl()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 8
        stack.popLong() shouldBe 40
    }

    "bshr" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(40)
                bpush(3)
                bshr()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 5
    }

    "sshr" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                spush(40)
                spush(3)
                sshr()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 2
        stack.popShort() shouldBe 5
    }

    "ishr" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(40)
                ipush(3)
                ishr()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popInt() shouldBe 5
    }

    "lshr" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(40)
                lpush(3)
                lshr()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 8
        stack.popLong() shouldBe 5
    }

    "bshru" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(-40)
                bpush(3)
                bshru()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe (-5).toByte()
    }

    "sshru" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                spush(-40)
                spush(3)
                sshru()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 2
        stack.popShort() shouldBe (-5).toShort()
    }

    "ishru" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(-40)
                ipush(3)
                ishru()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 4
        stack.popInt() shouldBe 536870907
    }

    "lshru" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(-40)
                lpush(3)
                lshru()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 8
        stack.popLong() shouldBe 2305843009213693947L
    }

    "bnot" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(5)
                bnot()
            },
            0
        )

        code.tick(2)

        stack.size shouldBe 1
        stack.pop() shouldBe -6
    }

    "snot" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                spush(5)
                snot()
            },
            0
        )

        code.tick(2)

        stack.size shouldBe 2
        stack.popShort() shouldBe -6
    }

    "inot" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(5)
                inot()
            },
            0
        )

        code.tick(2)

        stack.size shouldBe 4
        stack.popInt() shouldBe -6
    }

    "lnot" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(5)
                lnot()
            },
            0
        )

        code.tick(2)

        stack.size shouldBe 8
        stack.popLong() shouldBe -6
    }

    "binc" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(5)
                binc()
            },
            0
        )

        code.tick(2)

        stack.size shouldBe 1
        stack.pop() shouldBe 6
    }

    "sinc" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                spush(5)
                sinc()
            },
            0
        )

        code.tick(2)

        stack.size shouldBe 2
        stack.popShort() shouldBe 6
    }

    "iinc" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(5)
                iinc()
            },
            0
        )

        code.tick(2)

        stack.size shouldBe 4
        stack.popInt() shouldBe 6
    }

    "linc" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(5)
                linc()
            },
            0
        )

        code.tick(2)

        stack.size shouldBe 8
        stack.popLong() shouldBe 6
    }

    "bdec" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(5)
                bdec()
            },
            0
        )

        code.tick(2)

        stack.size shouldBe 1
        stack.pop() shouldBe 4
    }

    "sdec" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                spush(5)
                sdec()
            },
            0
        )

        code.tick(2)

        stack.size shouldBe 2
        stack.popShort() shouldBe 4
    }

    "idec" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(5)
                idec()
            },
            0
        )

        code.tick(2)

        stack.size shouldBe 4
        stack.popInt() shouldBe 4
    }

    "ldec" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(5)
                ldec()
            },
            0
        )

        code.tick(2)

        stack.size shouldBe 8
        stack.popLong() shouldBe 4
    }
    "byte compare (less)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(0)
                bpush(1)
                bcmp()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 2
    }
    "byte compare (equal)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(1)
                bpush(1)
                bcmp()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 1
    }
    "byte compare (greater)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(1)
                bpush(0)
                bcmp()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 0
    }

    "short compare (less)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                spush(0)
                spush(1)
                scmp()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 2
    }
    "short compare (equal)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                spush(1)
                spush(1)
                scmp()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 1
    }
    "short compare (greater)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                spush(1)
                spush(0)
                scmp()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 0
    }

    "int compare (less)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(0)
                ipush(1)
                icmp()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 2
    }
    "int compare (equal)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(1)
                ipush(1)
                icmp()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 1
    }
    "int compare (greater)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(1)
                ipush(0)
                icmp()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 0
    }

    "long compare (less)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(0)
                lpush(1)
                lcmp()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 2
    }
    "long compare (equal)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(1)
                lpush(1)
                lcmp()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 1
    }
    "long compare (greater)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(1)
                lpush(0)
                lcmp()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 0
    }

    "float compare (less)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(0f.toBits())
                ipush(1f.toBits())
                fcmp()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 2
    }
    "float compare (equal)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(1f.toBits())
                ipush(1f.toBits())
                fcmp()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 1
    }
    "float compare (greater)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                ipush(1f.toBits())
                ipush(0f.toBits())
                fcmp()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 0
    }

    "double compare (less)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(0.0.toBits())
                lpush(1.0.toBits())
                dcmp()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 2
    }
    "double compare (equal)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(1.0.toBits())
                lpush(1.0.toBits())
                dcmp()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 1
    }
    "double compare (greater)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                lpush(1.0.toBits())
                lpush(0.0.toBits())
                dcmp()
            },
            0
        )

        code.tick(3)

        stack.size shouldBe 1
        stack.pop() shouldBe 0
    }

    "jmp" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(1)
                bpush(2)
                jmp(0)
            },
            0
        )

        code.tick(6)

        code.pc shouldBe 0
        stack.size shouldBe 4
    }

    "jz" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(1)
                bpush(0)
                bcmp()
                jz(0)
            },
            0
        )

        code.tick(4)

        code.pc shouldBe 0
        stack.size shouldBe 0
    }

    "jz (false)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(0)
                bpush(1)
                bcmp()
                jz(0)
            },
            0
        )

        code.tick(4)

        code.pc shouldBe 10
        stack.size shouldBe 0
    }

    "jnz" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(1)
                bpush(0)
                bcmp()
                jnz(0)
            },
            0
        )

        code.tick(4)

        code.pc shouldBe 10
        stack.size shouldBe 0
    }

    "jnz (false)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(0)
                bpush(1)
                bcmp()
                jnz(0)
            },
            0
        )

        code.tick(4)

        code.pc shouldBe 0
        stack.size shouldBe 0
    }

    "jl" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(0)
                bpush(1)
                bcmp()
                jl(0)
            },
            0
        )

        code.tick(4)

        code.pc shouldBe 0
        stack.size shouldBe 0
    }

    "jl (false)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(1)
                bpush(0)
                bcmp()
                jl(0)
            },
            0
        )

        code.tick(4)

        code.pc shouldBe 10
        stack.size shouldBe 0
    }

    "jle" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(0)
                bpush(1)
                bcmp()
                jle(0)
            },
            0
        )

        code.tick(4)

        code.pc shouldBe 0
        stack.size shouldBe 0
    }

    "jle (equal)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(1)
                bpush(1)
                bcmp()
                jle(0)
            },
            0
        )

        code.tick(4)

        code.pc shouldBe 0
        stack.size shouldBe 0
    }

    "jle (false)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(1)
                bpush(0)
                bcmp()
                jle(0)
            },
            0
        )

        code.tick(4)

        code.pc shouldBe 10
        stack.size shouldBe 0
    }

    "jg" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(1)
                bpush(0)
                bcmp()
                jg(0)
            },
            0
        )

        code.tick(4)

        code.pc shouldBe 0
        stack.size shouldBe 0
    }

    "jg (false)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(0)
                bpush(1)
                bcmp()
                jg(0)
            },
            0
        )

        code.tick(4)

        code.pc shouldBe 10
        stack.size shouldBe 0
    }

    "jge" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(1)
                bpush(0)
                bcmp()
                jge(0)
            },
            0
        )

        code.tick(4)

        code.pc shouldBe 0
        stack.size shouldBe 0
    }

    "jge (equal)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(1)
                bpush(1)
                bcmp()
                jge(0)
            },
            0
        )

        code.tick(4)

        code.pc shouldBe 0
        stack.size shouldBe 0
    }

    "jge (false)" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(0)
                bpush(1)
                bcmp()
                jge(0)
            },
            0
        )

        code.tick(4)

        code.pc shouldBe 10
        stack.size shouldBe 0
    }

    "cast" {
        val interpreter = ShakeInterpreter()
        val stack = interpreter.stack
        val code = interpreter.createCodeInterpreter(
            bytecode {
                bpush(1)
                pcast(PCast.BYTE, PCast.INT)
            },
            0
        )

        code.tick(2)

        stack.size shouldBe 4
        stack.popInt() shouldBe 1
    }
})
