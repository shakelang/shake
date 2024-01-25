package com.shakelang.shake.bytecode

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class BytecodeGeneratorTest : FreeSpec(
    {

        "call native with params" {
            codeSpec(
                """
                package test
                
                void main() {
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
                
                void main() {
                    test();
                }
                
                void test() {
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
                
                void main() {
                    print(test())
                }
                
                int test() {
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
                
                void main() {
                    test(3)
                }
                
                void test(int i) {
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
                
                void main() {
                    print(add(1, 2))
                }
                
                int add(int a, int b) {
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
                    static void main() {
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
                    static void main() {
                        print(add(1, 2))
                    }
                    
                    static int add(int a, int b) {
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
                    static void main() {
                        print(add(1, 2))
                    }
                    
                    static int add(int a, int b) {
                        return a + b
                    }
                }
                                
                """.trimIndent(),
            ) {
                execute("test/Test:main()V")
                consoleOut shouldBe "3"
            }
        }

        "create a new object" {

            codeSpec(
                """
                package test
                
                class Test {
                    
                    constructor() {
                        print(10)
                    }
                
                    static void main() {
                        Test test = new Test()
                        print(test)
                    }
                }
                                
                """.trimIndent(),
            ) {
                execute("test/Test:main()V")
                consoleOut shouldBe "10test/Test@0000000000000000"
            }
        }
    },
)
