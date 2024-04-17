package com.shakelang.shake.bytecode

import com.shakelang.shake.bytecode.tools.BytecodeStringGenerator
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class BytecodeGeneratorTest : FreeSpec(
    {

        "call native with params" {
            codeSpec(
                """
                package test
                
                fun main() {
                    print(1 + 2)
                }
                
                """.trimIndent(),
            ) {
                execute("test/main()V")
                consoleOut shouldBe "3"
            }
        }

        "call a method" {
            codeSpec(
                """
                package test
                
                fun main() {
                    test();
                }
                
                fun test() {
                    print(1)
                }
                
                """.trimIndent(),
            ) {
                execute("test/main()V")
                consoleOut shouldBe "1"
            }
        }

        "call a method (with return value)" {
            codeSpec(
                """
                package test
                
                fun main() {
                    print(test())
                }
                
                fun test(): int {
                    return 1
                }
                
                """.trimIndent(),
            ) {
                execute("test/main()V")
                consoleOut shouldBe "1"
            }
        }

        "call a method (with params)" {
            codeSpec(
                """
                package test
                
                fun main() {
                    test(3)
                }
                
                fun test(i: int) {
                    print(i)
                }
                
                """.trimIndent(),
            ) {
                execute("test/main()V")
                consoleOut shouldBe "3"
            }
        }

        "call a method (with parameters and return value)" {
            codeSpec(
                """
                package test
                
                fun main() {
                    print(add(1, 2))
                }
                
                fun add(a: int, b: int): int {
                    return a + b
                }
                
                """.trimIndent(),
            ) {
                execute("test/main()V")
                consoleOut shouldBe "3"
            }
        }

        "call a static method" {

            codeSpec(
                """
                package test
                
                class Test {
                    static fun main() {
                        print(1 + 2)
                    }
                }
                                
                """.trimIndent(),
            ) {
                execute("test/Test:main()V")
                consoleOut shouldBe "3"
            }
        }

        "call a static method (with parameters)" {

            codeSpec(
                """
                package test
                
                class Test {
                    static fun main() {
                        print(add(1, 2))
                    }
                    
                    static fun add(a: int, b: int): int {
                        return a + b
                    }
                }
                                
                """.trimIndent(),
            ) {
                execute("test/Test:main()V")
                consoleOut shouldBe "3"
            }
        }

        "call a static method (with parameters and return value)" {

            codeSpec(
                """
                package test
                
                class Test {
                    static fun main() {
                        print(add(1, 2))
                    }
                    
                    static fun add(a: int, b: int): int {
                        return a + b
                    }
                }
                                
                """.trimIndent(),
            ) {
                execute("test/Test:main()V")
                consoleOut shouldBe "3"
            }
        }

        // TODO

        "create a new object".config(enabled = false) {

            codeSpec(
                """
                package test
                
                class Test {
                    
                    constructor() {
                        print(10)
                    }
                
                    static fun main() {
                        val test : Test = Test()
                        print(test)
                    }
                }
                                
                """.trimIndent(),
            ) {
                execute("test/Test:main()V")
                consoleOut shouldBe "10test/Test@0000000000000000"
            }
        }

        "set / get field of object".config(enabled = false) {

            codeSpec(
                """
                package test
                
                class Test {
                
                    var i
                    
                    constructor() {
                        print(10)
                    }
                
                    static fun main() {
                        Test test = new Test()
                        test.i = 42
                        print(test.i)
                    }
                }
                                
                """.trimIndent(),
            ) {
                println(BytecodeStringGenerator(format).generate().joinToString("\n"))

                execute("test/Test:main()V")
                consoleOut shouldBe "1042"
            }
        }
    },
)
