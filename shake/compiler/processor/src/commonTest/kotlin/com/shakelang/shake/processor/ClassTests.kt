package com.shakelang.shake.processor

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ClassTests :
    FreeSpec(
        {

            // Superclass tests

            "default supertype" {

                val processor = createBaseProcessor()

                processor.loadSynthetic(
                    "test.shake",
                    """
                package test;
                
                class Test {}
                    """.trimIndent(),
                )

                processor.finish()

                val project = processor.project
                val testClass = project.getClass("test.Test")!!

                testClass.superClass.qualifiedName shouldBe "Lshake/lang/Object;"
            }

            "custom supertype" {

                val processor = createBaseProcessor()

                processor.loadSynthetic(
                    "test.shake",
                    """
                package test;
                
                class TestSuper {}
                class Test : TestSuper {}
                    """.trimIndent(),
                )

                processor.finish()

                val project = processor.project
                val testClass = project.getClass("test.Test")!!

                testClass.superClass.qualifiedName shouldBe "Ltest/TestSuper;"
            }

            "custom supertype from other file within same package" {

                val processor = createBaseProcessor()

                processor.loadSynthetic(
                    "test.shake",
                    """
                    package test
                    
                    class TestSuper {}
                    """.trimIndent(),
                )

                processor.loadSynthetic(
                    "test2.shake",
                    """
                    package test
                    
                    class Test : TestSuper {}
                    """.trimIndent(),
                )

                processor.finish()

                val project = processor.project
                val testClass = project.getClass("test.Test")!!

                testClass.superClass.qualifiedName shouldBe "Ltest/TestSuper;"
            }

            "custom supertype from imported package" {

                val processor = createBaseProcessor()

                processor.loadSynthetic(
                    "test.shake",
                    """
                package abc
                
                class TestSuper {}
                    """.trimIndent(),
                )

                processor.loadSynthetic(
                    "test.shake",
                    """
                package test
                
                import abc.TestSuper
                
                class Test : TestSuper {}
                    """.trimIndent(),
                )

                processor.finish()

                val project = processor.project
                val testClass = project.getClass("test.Test")!!

                testClass.superClass.qualifiedName shouldBe "Labc/TestSuper;"
            }

            "custom supertype from imported package (wildcard)" {

                val processor = createBaseProcessor()

                processor.loadSynthetic(
                    "test.shake",
                    """
                package abc
                
                class TestSuper {}
                    """.trimIndent(),
                )

                processor.loadSynthetic(
                    "test.shake",
                    """
                package test
                
                import abc.*
                
                class Test : TestSuper {}
                    """.trimIndent(),
                )

                processor.finish()

                val project = processor.project
                val testClass = project.getClass("test.Test")!!

                testClass.superClass.qualifiedName shouldBe "Labc/TestSuper;"
            }

            // TODO Not implemented in parser
            "custom supertype with inline package".config(enabled = false) {

                val processor = createBaseProcessor()

                processor.loadSynthetic(
                    "test.shake",
                    """
                package test
                
                class TestSuper {}
                class Test : test.TestSuper {}
                    """.trimIndent(),
                )

                processor.finish()

                val project = processor.project
                val testClass = project.getClass("test.Test")!!

                testClass.superClass.qualifiedName shouldBe "test/TestSuper"
            }

            // Interface tests

            "no interfaces" {

                val processor = createBaseProcessor()

                processor.loadSynthetic(
                    "test.shake",
                    """
                package test
                
                class Test {}
                    """.trimIndent(),
                )

                processor.finish()

                val project = processor.project
                val testClass = project.getClass("test.Test")!!

                testClass.interfaces.size shouldBe 0
            }

            "one interface" {

                val processor = createBaseProcessor()

                processor.loadSynthetic(
                    "test.shake",
                    """
                package test
                
                interface TestInterface {}
                class Test : TestInterface {}
                    """.trimIndent(),
                )

                processor.finish()

                val project = processor.project
                val testClass = project.getClass("test.Test")!!

                testClass.interfaces.size shouldBe 1
                testClass.interfaces[0].qualifiedName shouldBe "Ltest/TestInterface;"
            }

            "multiple interfaces" {

                val processor = createBaseProcessor()

                processor.loadSynthetic(
                    "test.shake",
                    """
                package test
                
                interface TestInterface1 {}
                interface TestInterface2 {}
                class Test : TestInterface1, TestInterface2 {}
                    """.trimIndent(),
                )

                processor.finish()

                val project = processor.project
                val testClass = project.getClass("test.Test")!!

                val testInterface1 = project.getClass("test.TestInterface1")!!
                val testInterface2 = project.getClass("test.TestInterface2")!!

                testInterface1.isInterface shouldBe true
                testInterface2.isInterface shouldBe true

                testClass.interfaces.size shouldBe 2
                testClass.interfaces[0].qualifiedName shouldBe "Ltest/TestInterface1;"
                testClass.interfaces[1].qualifiedName shouldBe "Ltest/TestInterface2;"
            }

            "interface from other file within same package" {

                val processor = createBaseProcessor()

                processor.loadSynthetic(
                    "test.shake",
                    """
                package test
                
                interface TestInterface {}
                    """.trimIndent(),
                )

                processor.loadSynthetic(
                    "test2.shake",
                    """
                package test
                
                class Test : TestInterface {}
                    """.trimIndent(),
                )

                processor.finish()

                val project = processor.project
                val testClass = project.getClass("test.Test")!!

                testClass.interfaces.size shouldBe 1
                testClass.interfaces[0].qualifiedName shouldBe "Ltest/TestInterface;"
            }

            "interface from imported package" {

                val processor = createBaseProcessor()

                processor.loadSynthetic(
                    "test.shake",
                    """
                package abc
                
                interface TestInterface {}
                    """.trimIndent(),
                )

                processor.loadSynthetic(
                    "test.shake",
                    """
                package test
                
                import abc.TestInterface
                
                class Test : TestInterface {}
                    """.trimIndent(),
                )

                processor.finish()

                val project = processor.project
                val testClass = project.getClass("test.Test")!!

                testClass.interfaces.size shouldBe 1
                testClass.interfaces[0].qualifiedName shouldBe "Labc/TestInterface;"
            }

            "interface from imported package (wildcard)" {

                val processor = createBaseProcessor()

                processor.loadSynthetic(
                    "test.shake",
                    """
                package abc
                
                interface TestInterface {}
                    """.trimIndent(),
                )

                processor.loadSynthetic(
                    "test.shake",
                    """
                package test
                
                import abc.*
                
                class Test : TestInterface {}
                    """.trimIndent(),
                )

                processor.finish()

                val project = processor.project
                val testClass = project.getClass("test.Test")!!

                testClass.interfaces.size shouldBe 1
                testClass.interfaces[0].qualifiedName shouldBe "Labc/TestInterface;"
            }

            // TODO Not implemented in parser

            "interface with inline package".config(enabled = false) {

                val processor = createBaseProcessor()

                processor.loadSynthetic(
                    "test.shake",
                    """
                package test
                
                interface TestInterface {}
                class Test : test.TestInterface {}
                    """.trimIndent(),
                )

                processor.finish()

                val project = processor.project
                val testClass = project.getClass("test.Test")!!

                testClass.interfaces.size shouldBe 1
                testClass.interfaces[0].qualifiedName shouldBe "test/TestInterface"
            }
        },
    )
