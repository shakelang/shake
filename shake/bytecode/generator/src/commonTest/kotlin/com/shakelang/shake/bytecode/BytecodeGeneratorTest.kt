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
    },
)
