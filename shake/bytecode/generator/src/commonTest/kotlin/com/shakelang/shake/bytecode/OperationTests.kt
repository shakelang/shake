package com.shakelang.shake.bytecode

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.string.shouldMatch

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
                        execute("test/main()V")
                        consoleOut shouldMatch Regex("3(\\.0)?")
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
                        consoleOut shouldMatch Regex("1(\\.0)?")
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
                        consoleOut shouldMatch Regex("6(\\.0)?")
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
                        consoleOut shouldMatch Regex("3(\\.0)?")
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
                        consoleOut shouldMatch Regex("1(\\.0)?")
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
                        consoleOut shouldMatch Regex("49(\\.0)?")
                    }
                }
            }
        }
    },
)
