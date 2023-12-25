package com.shakelang.shake.processor

import com.shakelang.shake.stdlib.CoreFiles
import com.shakelang.util.logger.GlobalDebugConfiguration
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

/**
 * Create a processor with the basic apis
 */
fun createBaseProcessor(): ShakePackageBasedProcessor {
    val processor = ShakePackageBasedProcessor()
    processor.loadSynthetic("shake/lang/Object.shake", CoreFiles.OBJECT_SHAKE)
    processor.loadSynthetic("shake/lang/String.shake", CoreFiles.STRING_SHAKE)
    processor.loadSynthetic("shake/lang/Numbers.shake", CoreFiles.NUMBERS_SHAKE)
    return processor
}

class ClassTests : FreeSpec({

    // Output all logs
    GlobalDebugConfiguration.paths.add("*")

    "default supertype" {

        val processor = createBaseProcessor()

        processor.loadSynthetic(
            "test.shake",
            """
                package test;
                
                class Test {}
            """.trimIndent()
        )

        processor.finish()

        val project = processor.project
        val testClass = project.getClass("test.Test")!!

        testClass.superClass.qualifiedName shouldBe "shake/lang/Object"
    }

    "custom supertype".config(false) {

        val processor = createBaseProcessor()

        processor.loadSynthetic(
            "test.shake",
            """
                package test;
                
                class TestSuper {}
                class Test extends TestSuper {}
            """.trimIndent()
        )

        processor.finish()

        val project = processor.project
        val testClass = project.getClass("test.Test")!!

        testClass.superClass.qualifiedName shouldBe "test/TestSuper"
    }
})
