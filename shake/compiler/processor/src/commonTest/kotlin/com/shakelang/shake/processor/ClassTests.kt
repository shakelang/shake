package com.shakelang.shake.processor

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ClassTests : FreeSpec({

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

        testClass.superClass.qualifiedName shouldBe "shake/lang/Object"
    }

    "custom supertype" {

        val processor = createBaseProcessor()

        processor.loadSynthetic(
            "test.shake",
            """
                package test;
                
                class TestSuper {}
                class Test extends TestSuper {}
            """.trimIndent(),
        )

        processor.finish()

        val project = processor.project
        val testClass = project.getClass("test.Test")!!

        testClass.superClass.qualifiedName shouldBe "test/TestSuper"
    }

    "custom supertype from other file within same package" {

        val processor = createBaseProcessor()

        processor.loadSynthetic(
            "test.shake",
            """
                    package test;
                    
                    class TestSuper {}
            """.trimIndent(),
        )

        processor.loadSynthetic(
            "test2.shake",
            """
                    package test;
                    
                    class Test extends TestSuper {}
            """.trimIndent(),
        )

        processor.finish()

        val project = processor.project
        val testClass = project.getClass("test.Test")!!

        testClass.superClass.qualifiedName shouldBe "test/TestSuper"
    }

    "custom supertype from imported package" {

        val processor = createBaseProcessor()

        processor.loadSynthetic(
            "test.shake",
            """
                package abc;
                
                class TestSuper {}
            """.trimIndent(),
        )

        processor.loadSynthetic(
            "test.shake",
            """
                package test;
                
                import abc.TestSuper;
                
                class Test extends TestSuper {}
            """.trimIndent(),
        )

        processor.finish()

        val project = processor.project
        val testClass = project.getClass("test.Test")!!

        testClass.superClass.qualifiedName shouldBe "abc/TestSuper"
    }

    "custom supertype from imported package (wildcard)" {

        val processor = createBaseProcessor()

        processor.loadSynthetic(
            "test.shake",
            """
                package abc;
                
                class TestSuper {}
            """.trimIndent(),
        )

        processor.loadSynthetic(
            "test.shake",
            """
                package test;
                
                import abc.*;
                
                class Test extends TestSuper {}
            """.trimIndent(),
        )

        processor.finish()

        val project = processor.project
        val testClass = project.getClass("test.Test")!!

        testClass.superClass.qualifiedName shouldBe "abc/TestSuper"
    }

    // TODO Not implemented in parser
    "custom supertype with inline package".config(enabled = false) {

        val processor = createBaseProcessor()

        processor.loadSynthetic(
            "test.shake",
            """
                package test;
                
                class TestSuper {}
                class Test extends test.TestSuper {}
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
                package test;
                
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
                package test;
                
                interface TestInterface {}
                class Test implements TestInterface {}
            """.trimIndent(),
        )

        processor.finish()

        val project = processor.project
        val testClass = project.getClass("test.Test")!!

        testClass.interfaces.size shouldBe 1
        testClass.interfaces[0].qualifiedName shouldBe "test/TestInterface"
    }

    "multiple interfaces" {

        val processor = createBaseProcessor()

        processor.loadSynthetic(
            "test.shake",
            """
                package test;
                
                interface TestInterface1 {}
                interface TestInterface2 {}
                class Test implements TestInterface1, TestInterface2 {}
            """.trimIndent(),
        )

        processor.finish()

        val project = processor.project
        val testClass = project.getClass("test.Test")!!

        testClass.interfaces.size shouldBe 2
        testClass.interfaces[0].qualifiedName shouldBe "test/TestInterface1"
        testClass.interfaces[1].qualifiedName shouldBe "test/TestInterface2"
    }

    "interface from other file within same package" {

        val processor = createBaseProcessor()

        processor.loadSynthetic(
            "test.shake",
            """
                package test;
                
                interface TestInterface {}
            """.trimIndent(),
        )

        processor.loadSynthetic(
            "test2.shake",
            """
                package test;
                
                class Test implements TestInterface {}
            """.trimIndent(),
        )

        processor.finish()

        val project = processor.project
        val testClass = project.getClass("test.Test")!!

        testClass.interfaces.size shouldBe 1
        testClass.interfaces[0].qualifiedName shouldBe "test/TestInterface"
    }

    "interface from imported package" {

        val processor = createBaseProcessor()

        processor.loadSynthetic(
            "test.shake",
            """
                package abc;
                
                interface TestInterface {}
            """.trimIndent(),
        )

        processor.loadSynthetic(
            "test.shake",
            """
                package test;
                
                import abc.TestInterface;
                
                class Test implements TestInterface {}
            """.trimIndent(),
        )

        processor.finish()

        val project = processor.project
        val testClass = project.getClass("test.Test")!!

        testClass.interfaces.size shouldBe 1
        testClass.interfaces[0].qualifiedName shouldBe "abc/TestInterface"
    }

    "interface from imported package (wildcard)" {

        val processor = createBaseProcessor()

        processor.loadSynthetic(
            "test.shake",
            """
                package abc;
                
                interface TestInterface {}
            """.trimIndent(),
        )

        processor.loadSynthetic(
            "test.shake",
            """
                package test;
                
                import abc.*;
                
                class Test implements TestInterface {}
            """.trimIndent(),
        )

        processor.finish()

        val project = processor.project
        val testClass = project.getClass("test.Test")!!

        testClass.interfaces.size shouldBe 1
        testClass.interfaces[0].qualifiedName shouldBe "abc/TestInterface"
    }

    // TODO Not implemented in parser

    "interface with inline package".config(enabled = false) {

        val processor = createBaseProcessor()

        processor.loadSynthetic(
            "test.shake",
            """
                package test;
                
                interface TestInterface {}
                class Test implements test.TestInterface {}
            """.trimIndent(),
        )

        processor.finish()

        val project = processor.project
        val testClass = project.getClass("test.Test")!!

        testClass.interfaces.size shouldBe 1
        testClass.interfaces[0].qualifiedName shouldBe "test/TestInterface"
    }
})
