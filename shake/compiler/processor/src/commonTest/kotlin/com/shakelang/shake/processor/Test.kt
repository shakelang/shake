package com.shakelang.shake.processor

import com.shakelang.shake.stdlib.CoreFiles
import io.kotest.core.spec.style.FreeSpec

class Test : FreeSpec({

    "test" {
        val processor = ShakePackageBasedProcessor()
        processor.loadSynthetic("shake/lang/Object.shake", CoreFiles.OBJECT_SHAKE)
        processor.loadSynthetic("shake/lang/String.shake", CoreFiles.STRING_SHAKE)
        processor.loadSynthetic("shake/lang/Numbers.shake", CoreFiles.NUMBERS_SHAKE)
        processor.loadSynthetic("test.shake", """
            package test
            import shake.lang.Object
            import shake.lang.String
            import shake.lang.Numbers
            class Test {
                void main() {
                    val a = 1
                    val b = 2
                    val c = a + b
                    val d = "Hello World"
                    val e = d + "!"
                    val f = e + c
                    // println(f)
                }
            }
        """.trimIndent())
        processor.finish()

        println(processor.project.cores.String)
    }
})