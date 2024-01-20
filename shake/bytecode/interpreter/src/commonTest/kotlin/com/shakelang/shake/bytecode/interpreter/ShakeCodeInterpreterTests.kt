@file:Suppress("unused")

package com.shakelang.shake.bytecode.interpreter

import com.shakelang.shake.bytecode.interpreter.generator.bytecode
import com.shakelang.shake.bytecode.interpreter.generator.generatePackage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ShakeCodeInterpreterTests : FreeSpec(
    {

        val dummyInterpreter = ShakeInterpreter()
        val classpath = dummyInterpreter.classPath
        classpath.load(
            generatePackage {
                name = "test"
                Method {
                    name = "main()V"
                    code {
                        maxLocals = 100
                        maxStack = 100
                        bytecode {}
                    }
                }

                Method {
                    name = "btest()B"
                    code {
                        maxLocals = 100
                        maxStack = 100
                        bytecode {}
                    }
                }

                Method {
                    name = "stest()S"
                    code {
                        maxLocals = 100
                        maxStack = 100
                        bytecode {}
                    }
                }

                Method {
                    name = "itest()I"
                    code {
                        maxLocals = 100
                        maxStack = 100
                        bytecode {}
                    }
                }

                Method {
                    name = "ltest()J"
                    code {
                        maxLocals = 100
                        maxStack = 100
                        bytecode {}
                    }
                }
            },
        )

        val method = classpath.getMethod("test/main()V")!!
        val bmethod = classpath.getMethod("test/btest()B")!!
        val smethod = classpath.getMethod("test/stest()S")!!
        val imethod = classpath.getMethod("test/itest()I")!!
        val lmethod = classpath.getMethod("test/ltest()J")!!

        "bpush" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(1)
                    bpush(2)
                    bpush(3)
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 3
            stack.pop() shouldBe 3
            stack.pop() shouldBe 2
            stack.pop() shouldBe 1
        }

        "spush" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(1)
                    spush(2)
                    spush(3)
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 6
            stack.popShort() shouldBe 3
            stack.popShort() shouldBe 2
            stack.popShort() shouldBe 1
        }

        "ipush" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1)
                    ipush(2)
                    ipush(3)
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 12
            stack.popInt() shouldBe 3
            stack.popInt() shouldBe 2
            stack.popInt() shouldBe 1
        }

        "lpush" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(1)
                    lpush(2)
                    lpush(3)
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 24
            stack.popLong() shouldBe 3
            stack.popLong() shouldBe 2
            stack.popLong() shouldBe 1
        }

        "bstore/bload" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(1)
                    bstore(0u)
                    bload(0u)
                    bload(0u)
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 2
            stack.pop() shouldBe 1
            stack.pop() shouldBe 1
        }

        "sstore/sload" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(1)
                    sstore(0u)
                    sload(0u)
                    sload(0u)
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popShort() shouldBe 1
            stack.popShort() shouldBe 1
        }

        "istore/iload" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1)
                    istore(0u)
                    iload(0u)
                    iload(0u)
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 8
            stack.popInt() shouldBe 1
            stack.popInt() shouldBe 1
        }

        "lstore/lload" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(1)
                    lstore(0u)
                    lload(0u)
                    lload(0u)
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 16
            stack.popLong() shouldBe 1
            stack.popLong() shouldBe 1
        }

        "badd" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(1)
                    bpush(2)
                    badd()
                },
                method,

            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe 3
        }

        "sadd" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(1)
                    spush(2)
                    sadd()
                },
                method,

            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe 3
        }

        "iadd" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1)
                    ipush(2)
                    iadd()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe 3
        }

        "ladd" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(1)
                    lpush(2)
                    ladd()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 8
            stack.popLong() shouldBe 3
        }

        "fadd" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1f.toBits())
                    ipush(2f.toBits())
                    fadd()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popFloat() shouldBe 3f
        }

        "dadd" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(1.0.toBits())
                    lpush(2.0.toBits())
                    dadd()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 8
            stack.popDouble() shouldBe 3.0
        }

        "bsub" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(1)
                    bpush(2)
                    bsub()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe -1
        }

        "ssub" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(1)
                    spush(2)
                    ssub()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe -1
        }

        "isub" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1)
                    ipush(2)
                    isub()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe -1
        }

        "lsub" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(1)
                    lpush(2)
                    lsub()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 8
            stack.popLong() shouldBe -1
        }

        "fsub" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1f.toBits())
                    ipush(2f.toBits())
                    fsub()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popFloat() shouldBe -1f
        }

        "dsub" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(1.0.toBits())
                    lpush(2.0.toBits())
                    dsub()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 8
            stack.popDouble() shouldBe -1.0
        }

        "bmul" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(2)
                    bpush(3)
                    bmul()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe 6
        }

        "smul" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(2)
                    spush(3)
                    smul()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe 6
        }

        "imul" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(2)
                    ipush(3)
                    imul()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe 6
        }

        "lmul" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(2)
                    lpush(3)
                    lmul()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 8
            stack.popLong() shouldBe 6
        }

        "fmul" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(2f.toBits())
                    ipush(3f.toBits())
                    fmul()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popFloat() shouldBe 6f
        }

        "dmul" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(2.0.toBits())
                    lpush(3.0.toBits())
                    dmul()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 8
            stack.popDouble() shouldBe 6.0
        }

        "bdiv" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(6)
                    bpush(3)
                    bdiv()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe 2
        }

        "sdiv" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(6)
                    spush(3)
                    sdiv()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe 2
        }

        "idiv" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(6)
                    ipush(3)
                    idiv()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe 2
        }

        "ldiv" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(6)
                    lpush(3)
                    ldiv()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 8
            stack.popLong() shouldBe 2
        }

        "fdiv" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(6f.toBits())
                    ipush(3f.toBits())
                    fdiv()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popFloat() shouldBe 2f
        }

        "ddiv" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(6.0.toBits())
                    lpush(3.0.toBits())
                    ddiv()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 8
            stack.popDouble() shouldBe 2.0
        }

        "bmod" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(6)
                    bpush(3)
                    bmod()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe 0
        }

        "smod" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(6)
                    spush(3)
                    smod()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe 0
        }

        "imod" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(6)
                    ipush(3)
                    imod()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe 0
        }

        "lmod" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(6)
                    lpush(3)
                    lmod()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 8
            stack.popLong() shouldBe 0
        }

        "fmod" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(6f.toBits())
                    ipush(3f.toBits())
                    fmod()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popFloat() shouldBe 0f
        }

        "dmod" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(6.0.toBits())
                    lpush(3.0.toBits())
                    dmod()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 8
            stack.popDouble() shouldBe 0.0
        }

        "band" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(5)
                    bpush(3)
                    band()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 1
        }

        "sand" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(5)
                    spush(3)
                    sand()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 2
            stack.popShort() shouldBe 1
        }

        "iand" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(5)
                    ipush(3)
                    iand()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe 1
        }

        "land" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(5)
                    lpush(3)
                    land()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 8
            stack.popLong() shouldBe 1
        }

        "bor" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(5)
                    bpush(3)
                    bor()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 7
        }

        "sor" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(5)
                    spush(3)
                    sor()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 2
            stack.popShort() shouldBe 7
        }

        "ior" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(5)
                    ipush(3)
                    ior()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe 7
        }

        "lor" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(5)
                    lpush(3)
                    lor()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 8
            stack.popLong() shouldBe 7
        }

        "bxor" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(5)
                    bpush(3)
                    bxor()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 6
        }

        "sxor" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(5)
                    spush(3)
                    sxor()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 2
            stack.popShort() shouldBe 6
        }

        "ixor" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(5)
                    ipush(3)
                    ixor()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe 6
        }

        "lxor" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(5)
                    lpush(3)
                    lxor()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 8
            stack.popLong() shouldBe 6
        }

        "bshl" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(5)
                    bpush(3)
                    bshl()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 40
        }

        "sshl" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(5)
                    spush(3)
                    sshl()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 2
            stack.popShort() shouldBe 40
        }

        "ishl" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(5)
                    ipush(3)
                    ishl()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe 40
        }

        "lshl" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(5)
                    lpush(3)
                    lshl()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 8
            stack.popLong() shouldBe 40
        }

        "bshr" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(40)
                    bpush(3)
                    bshr()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 5
        }

        "sshr" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(40)
                    spush(3)
                    sshr()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 2
            stack.popShort() shouldBe 5
        }

        "ishr" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(40)
                    ipush(3)
                    ishr()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe 5
        }

        "lshr" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(40)
                    lpush(3)
                    lshr()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 8
            stack.popLong() shouldBe 5
        }

        "bshru" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(-40)
                    bpush(3)
                    bshru()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe (-5).toByte()
        }

        "sshru" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(-40)
                    spush(3)
                    sshru()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 2
            stack.popShort() shouldBe (-5).toShort()
        }

        "ishru" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(-40)
                    ipush(3)
                    ishru()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe 536870907
        }

        "lshru" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(-40)
                    lpush(3)
                    lshru()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 8
            stack.popLong() shouldBe 2305843009213693947L
        }

        "bnot" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(5)
                    bnot()
                },
                method,
            )

            code.tick(2)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe -6
        }

        "snot" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(5)
                    snot()
                },
                method,
            )

            code.tick(2)

            val stack = code.stack
            stack.size shouldBe 2
            stack.popShort() shouldBe -6
        }

        "inot" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(5)
                    inot()
                },
                method,
            )

            code.tick(2)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe -6
        }

        "lnot" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(5)
                    lnot()
                },
                method,
            )

            code.tick(2)

            val stack = code.stack
            stack.size shouldBe 8
            stack.popLong() shouldBe -6
        }

        "binc" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(5)
                    binc()
                },
                method,
            )

            code.tick(2)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 6
        }

        "sinc" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(5)
                    sinc()
                },
                method,
            )

            code.tick(2)

            val stack = code.stack
            stack.size shouldBe 2
            stack.popShort() shouldBe 6
        }

        "iinc" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(5)
                    iinc()
                },
                method,
            )

            code.tick(2)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe 6
        }

        "linc" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(5)
                    linc()
                },
                method,
            )

            code.tick(2)

            val stack = code.stack
            stack.size shouldBe 8
            stack.popLong() shouldBe 6
        }

        "bdec" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(5)
                    bdec()
                },
                method,
            )

            code.tick(2)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 4
        }

        "sdec" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(5)
                    sdec()
                },
                method,
            )

            code.tick(2)

            val stack = code.stack
            stack.size shouldBe 2
            stack.popShort() shouldBe 4
        }

        "idec" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(5)
                    idec()
                },
                method,
            )

            code.tick(2)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe 4
        }

        "ldec" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(5)
                    ldec()
                },
                method,
            )

            code.tick(2)

            val stack = code.stack
            stack.size shouldBe 8
            stack.popLong() shouldBe 4
        }
        "byte compare (less)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(0)
                    bpush(1)
                    bcmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 2
        }
        "byte compare (equal)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(1)
                    bpush(1)
                    bcmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 1
        }
        "byte compare (greater)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(1)
                    bpush(0)
                    bcmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 0
        }

        "short compare (less)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(0)
                    spush(1)
                    scmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 2
        }
        "short compare (equal)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(1)
                    spush(1)
                    scmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 1
        }
        "short compare (greater)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(1)
                    spush(0)
                    scmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 0
        }

        "int compare (less)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(0)
                    ipush(1)
                    icmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 2
        }
        "int compare (equal)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1)
                    ipush(1)
                    icmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 1
        }
        "int compare (greater)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1)
                    ipush(0)
                    icmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 0
        }

        "long compare (less)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(0)
                    lpush(1)
                    lcmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 2
        }
        "long compare (equal)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(1)
                    lpush(1)
                    lcmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 1
        }
        "long compare (greater)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(1)
                    lpush(0)
                    lcmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 0
        }

        "float compare (less)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(0f.toBits())
                    ipush(1f.toBits())
                    fcmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 2
        }
        "float compare (equal)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1f.toBits())
                    ipush(1f.toBits())
                    fcmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 1
        }
        "float compare (greater)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1f.toBits())
                    ipush(0f.toBits())
                    fcmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 0
        }

        "double compare (less)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(0.0.toBits())
                    lpush(1.0.toBits())
                    dcmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 2
        }
        "double compare (equal)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(1.0.toBits())
                    lpush(1.0.toBits())
                    dcmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 1
        }
        "double compare (greater)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(1.0.toBits())
                    lpush(0.0.toBits())
                    dcmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 0
        }

        "unsigned byte compare (less)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(0)
                    bpush(1)
                    ubcmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 2
        }

        "unsigned byte compare (equal)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(1)
                    bpush(1)
                    ubcmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 1
        }

        "unsigned byte compare (greater)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(1)
                    bpush(0)
                    ubcmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 0
        }

        "unsigned short compare (less)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(0)
                    spush(1)
                    uscmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 2
        }

        "unsigned short compare (equal)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(1)
                    spush(1)
                    uscmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 1
        }

        "unsigned short compare (greater)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(1)
                    spush(0)
                    uscmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 0
        }

        "unsigned int compare (less)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(0)
                    ipush(1)
                    uicmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 2
        }

        "unsigned int compare (equal)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1)
                    ipush(1)
                    uicmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 1
        }

        "unsigned int compare (greater)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1)
                    ipush(0)
                    uicmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 0
        }

        "unsigned long compare (less)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(0)
                    lpush(1)
                    ulcmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 2
        }

        "unsigned long compare (equal)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(1)
                    lpush(1)
                    ulcmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 1
        }

        "unsigned long compare (greater)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(1)
                    lpush(0)
                    ulcmp()
                },
                method,
            )

            code.tick(3)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 0
        }

        "cgt (greater)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1)
                    ipush(0)
                    icmp()
                    cgt()
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 1
        }

        "cgt (equal)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1)
                    ipush(1)
                    icmp()
                    cgt()
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 0
        }

        "cgt (less)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(0)
                    ipush(1)
                    icmp()
                    cgt()
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 0
        }

        "cge (greater)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1)
                    ipush(0)
                    icmp()
                    cge()
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 1
        }

        "cge (equal)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1)
                    ipush(1)
                    icmp()
                    cge()
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 1
        }

        "cge (less)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(0)
                    ipush(1)
                    icmp()
                    cge()
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 0
        }

        "clt (greater)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1)
                    ipush(0)
                    icmp()
                    clt()
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 0
        }

        "clt (equal)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1)
                    ipush(1)
                    icmp()
                    clt()
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 0
        }

        "clt (less)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(0)
                    ipush(1)
                    icmp()
                    clt()
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 1
        }

        "cle (greater)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1)
                    ipush(0)
                    icmp()
                    cle()
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 0
        }

        "cle (equal)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1)
                    ipush(1)
                    icmp()
                    cle()
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 1
        }

        "cle (less)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(0)
                    ipush(1)
                    icmp()
                    cle()
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 1
        }

        "ceq (greater)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1)
                    ipush(0)
                    icmp()
                    ceq()
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 0
        }

        "ceq (equal)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1)
                    ipush(1)
                    icmp()
                    ceq()
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 1
        }

        "ceq (less)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(0)
                    ipush(1)
                    icmp()
                    ceq()
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 0
        }

        "cne (greater)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1)
                    ipush(0)
                    icmp()
                    cne()
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 1
        }

        "cne (equal)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1)
                    ipush(1)
                    icmp()
                    cne()
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 0
        }

        "cne (less)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(0)
                    ipush(1)
                    icmp()
                    cne()
                },
                method,
            )

            code.tick(4)

            val stack = code.stack
            stack.size shouldBe 1
            stack.pop() shouldBe 1
        }

        "jmp" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(1)
                    bpush(2)
                    jmp(0)
                },
                method,
            )

            code.tick(6)

            code.pc shouldBe 0
            val stack = code.stack
            stack.size shouldBe 4
        }

        "jz" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(1)
                    bpush(0)
                    bcmp()
                    jz(0)
                },
                method,
            )

            code.tick(4)

            code.pc shouldBe 0
            val stack = code.stack
            stack.size shouldBe 0
        }

        "jz (false)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(0)
                    bpush(1)
                    bcmp()
                    jz(0)
                },
                method,
            )

            code.tick(4)

            code.pc shouldBe 10
            val stack = code.stack
            stack.size shouldBe 0
        }

        "jnz" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(1)
                    bpush(0)
                    bcmp()
                    jnz(0)
                },
                method,
            )

            code.tick(4)

            code.pc shouldBe 10
            val stack = code.stack
            stack.size shouldBe 0
        }

        "jnz (false)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(0)
                    bpush(1)
                    bcmp()
                    jnz(0)
                },
                method,
            )

            code.tick(4)

            code.pc shouldBe 0
            val stack = code.stack
            stack.size shouldBe 0
        }

        "jl" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(0)
                    bpush(1)
                    bcmp()
                    jl(0)
                },
                method,
            )

            code.tick(4)

            code.pc shouldBe 0
            val stack = code.stack
            stack.size shouldBe 0
        }

        "jl (false)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(1)
                    bpush(0)
                    bcmp()
                    jl(0)
                },
                method,
            )

            code.tick(4)

            code.pc shouldBe 10
            val stack = code.stack
            stack.size shouldBe 0
        }

        "jle" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(0)
                    bpush(1)
                    bcmp()
                    jle(0)
                },
                method,
            )

            code.tick(4)

            code.pc shouldBe 0
            val stack = code.stack
            stack.size shouldBe 0
        }

        "jle (equal)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(1)
                    bpush(1)
                    bcmp()
                    jle(0)
                },
                method,
            )

            code.tick(4)

            code.pc shouldBe 0
            val stack = code.stack
            stack.size shouldBe 0
        }

        "jle (false)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(1)
                    bpush(0)
                    bcmp()
                    jle(0)
                },
                method,
            )

            code.tick(4)

            code.pc shouldBe 10
            val stack = code.stack
            stack.size shouldBe 0
        }

        "jg" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(1)
                    bpush(0)
                    bcmp()
                    jg(0)
                },
                method,
            )

            code.tick(4)

            code.pc shouldBe 0
            val stack = code.stack
            stack.size shouldBe 0
        }

        "jg (false)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(0)
                    bpush(1)
                    bcmp()
                    jg(0)
                },
                method,
            )

            code.tick(4)

            code.pc shouldBe 10
            val stack = code.stack
            stack.size shouldBe 0
        }

        "jge" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(1)
                    bpush(0)
                    bcmp()
                    jge(0)
                },
                method,
            )

            code.tick(4)

            code.pc shouldBe 0
            val stack = code.stack
            stack.size shouldBe 0
        }

        "jge (equal)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(1)
                    bpush(1)
                    bcmp()
                    jge(0)
                },
                method,
            )

            code.tick(4)

            code.pc shouldBe 0
            val stack = code.stack
            stack.size shouldBe 0
        }

        "jge (false)" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(0)
                    bpush(1)
                    bcmp()
                    jge(0)
                },
                method,
            )

            code.tick(4)

            code.pc shouldBe 10
            val stack = code.stack
            stack.size shouldBe 0
        }

        "ret" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(1)
                    ret()
                },
                method,
            )

            code.tick(2)

            code.pc shouldBe 3
            code.finished shouldBe true
            val stack = code.stack
            stack.size shouldBe 1
        }

        "bret" {

            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(1)
                    bret()
                },
                bmethod,
            )

            code.tick(2)

            code.pc shouldBe 3
            code.finished shouldBe false
            code.returnData shouldBe byteArrayOf(1)
            val stack = code.stack
            stack.size shouldBe 0
        }

        "sret" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    spush(1)
                    sret()
                },
                smethod,
            )

            code.tick(2)

            code.pc shouldBe 4
            code.finished shouldBe false
            code.returnData shouldBe byteArrayOf(0, 1)
            val stack = code.stack
            stack.size shouldBe 0
        }

        "iret" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    ipush(1)
                    iret()
                },
                imethod,
            )

            code.tick(2)

            code.pc shouldBe 6
            code.finished shouldBe false
            code.returnData shouldBe byteArrayOf(0, 0, 0, 1)
            val stack = code.stack
            stack.size shouldBe 0
        }

        "lret" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    lpush(1)
                    lret()
                },
                lmethod,
            )

            code.tick(2)

            code.pc shouldBe 10
            code.finished shouldBe false
            code.returnData shouldBe byteArrayOf(0, 0, 0, 0, 0, 0, 0, 1)
            val stack = code.stack
            stack.size shouldBe 0
        }

        "pcast" {
            val interpreter = ShakeInterpreter()
            val code = interpreter.createCodeInterpreter(
                bytecode {
                    bpush(1)
                    pcast(PCast.BYTE, PCast.INT)
                },
                method,
            )

            code.tick(2)

            val stack = code.stack
            stack.size shouldBe 4
            stack.popInt() shouldBe 1
        }

        "call" {
            val interpreter = ShakeInterpreter()
            interpreter.classPath.load(
                generatePackage {
                    name = "test"
                    Method {
                        name = "and(B,B)B"
                        isPublic = true

                        code {
                            maxStack = 100
                            maxLocals = 100

                            this.bytecode {
                                band()
                                bret()
                                ret()
                            }
                        }
                    }
                    Method {
                        name = "main()V"
                        isPublic = true

                        code {
                            maxStack = 100
                            maxLocals = 100

                            this.bytecode {
                                bpush(1)
                                bpush(1)
                                call("test/and(B,B)B")
                                ret()
                            }
                        }
                    }
                },
            )

            val method = interpreter.classPath.getMethod("test/main()V")!!

            val code = interpreter.putFunctionOnStack(method) as ShakeInterpreter.ShakeCodeInterpreter
            interpreter.tick(2)

            val stack = code.stack

            code.pc shouldBe 4
            stack.size shouldBe 2
            interpreter.callStack.size shouldBe 1

            interpreter.tick(2)

            code.pc shouldBe 9
            stack.size shouldBe 0
            interpreter.callStack.size shouldBe 2

            interpreter.tick(2)

            code.pc shouldBe 9
            stack.size shouldBe 1
            interpreter.callStack.size shouldBe 1

            interpreter.tick()
        }
    },
)
