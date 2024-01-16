package com.shakelang.shake.bytecode

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class OperationTests : FreeSpec(
    {
        for (type1 in listOf("byte", "short", "int", "long", "unsigned byte", "unsigned short", "unsigned int", "unsigned long")) {
            for (type2 in listOf("byte", "short", "int", "long", "unsigned byte", "unsigned short", "unsigned int", "unsigned long")) {
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
                        execute("test/main()V")
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
            }
        }
    },
)
