package com.shakelang.shake.bytecode

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.string.shouldMatch

class OperationTests : FreeSpec(
    {
        val types = listOf(
            "byte", "shorts", "int", "long", "float", "doubles",
            "ubyte", "ushort", "uint", "ulong",
        )

        for (type1 in types) {
            for (type2 in types) {
                "add $type1 and $type2" {
                    codeSpec(
                        """
                        package test
                        
                        fun main() {
                            val a : $type1 = 1
                            val b : $type2 = 2
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
                        
                        fun main() {
                            val a : $type1 = 3
                            val b : $type2 = 2
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
                        
                        fun main() {
                            val a : $type1 = 3
                            val b : $type2 = 2
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
                        
                        fun main() {
                            val a : $type1 = 6
                            val b : $type2 = 2
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
                        
                        fun main() {
                            val a : $type1 = 7
                            val b : $type2 = 2
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
                        
                        fun main() {
                            val a : $type1 = 7
                            val b : $type2 = 2
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
