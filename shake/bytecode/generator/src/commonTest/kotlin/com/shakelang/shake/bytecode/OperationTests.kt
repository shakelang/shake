package com.shakelang.shake.bytecode

import com.shakelang.shake.bytecode.tools.BytecodeStringGenerator
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class OperationTests : FreeSpec(
    {
        val types = listOf(
            "byte", "short", "int", "long", "float", "double",
            "unsigned byte", "unsigned short", "unsigned int", "unsigned long",
        )

        for (type1 in types) {
            for (type2 in types) {
                "add $type1 and $type2" {
                    codeSpec(
                        """
                        package test
                        
                        void main() {
                            $type1 a = 1
                            $type2 b = 2
                            print(a + b)
                        }
                    
                        """.trimIndent(),
                    ) {
                        println(BytecodeStringGenerator(format).generate().joinToString("\n"))

                        execute("test/main()V")
//                        interpreter.putFunctionOnStack(interpreter.classPath.getMethod("test/main()V")!!)
//                        interpreter.tick(2)
//                        interpreter.callStack[0].stack.size shouldBe 1
//
//                        interpreter.tick(4)
//                        interpreter.callStack[0].stack.size shouldBe 0
//
//                        interpreter.tick(2)
//                        interpreter.callStack[0].stack.size shouldBe 2
//
//                        println(interpreter.callStack[0].stack.toByteArray().toHexString())
//
//                        interpreter.tick()
//
//                        println(interpreter.callStack[0].stack.toByteArray().toHexString())

                        consoleOut shouldBe "3"
                    }
                }

                "subtract $type1 and $type2" {
                    codeSpec(
                        """
                        package test
                        
                        void main() {
                            $type1 a = 3
                            $type2 b = 2
                            print(a - b)
                        }
                   
                        """.trimIndent(),
                    ) {
                        execute("test/main()V")
                        consoleOut shouldBe "1"
                    }
                }

                "multiply $type1 and $type2" {
                    codeSpec(
                        """
                        package test
                        
                        void main() {
                            $type1 a = 3
                            $type2 b = 2
                            print(a * b)
                        }
                    
                        """.trimIndent(),
                    ) {
                        execute("test/main()V")
                        consoleOut shouldBe "6"
                    }
                }

                "divide $type1 and $type2" {
                    codeSpec(
                        """
                        package test
                        
                        void main() {
                            $type1 a = 6
                            $type2 b = 2
                            print(a / b)
                        }
                    
                        """.trimIndent(),
                    ) {
                        execute("test/main()V")
                        consoleOut shouldBe "3"
                    }
                }

                "modulo $type1 and $type2" {
                    codeSpec(
                        """
                        package test
                        
                        void main() {
                            $type1 a = 7
                            $type2 b = 2
                            print(a % b)
                        }
                    
                        """.trimIndent(),
                    ) {
                        execute("test/main()V")
                        consoleOut shouldBe "1"
                    }
                }

                "power $type1 and $type2" {
                    codeSpec(
                        """
                        package test
                        
                        void main() {
                            $type1 a = 7
                            $type2 b = 2
                            print(a ** b)
                        }
                    
                        """.trimIndent(),
                    ) {
                        execute("test/main()V")
                        consoleOut shouldBe "49"
                    }
                }
            }
        }
    },
)
