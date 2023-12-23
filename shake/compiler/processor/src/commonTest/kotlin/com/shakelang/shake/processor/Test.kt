package com.shakelang.shake.processor

import com.shakelang.shake.stdlib.CoreFiles
import com.shakelang.shake.util.shason.json
import io.kotest.core.spec.style.FreeSpec

class Test : FreeSpec({

    "test" {
        val processor = ShakePackageBasedProcessor()
        processor.loadSynthetic("shake/lang/Object.shake", CoreFiles.OBJECT_SHAKE)
        processor.loadSynthetic("shake/lang/String.shake", CoreFiles.STRING_SHAKE)
        processor.loadSynthetic("shake/lang/Numbers.shake", CoreFiles.NUMBERS_SHAKE)
        processor.loadSynthetic("test2.shake", """
            package test2
            String test = "Hello World"
        """.trimIndent())
        processor.loadSynthetic("test.shake", """
            package test
            
            import test2.test
            
            class Test {
                static void main() {
                    int a = 1
                    int b = 2
                    int c = a + b
                    String d = "Hello World"
                    int e = d + "!"
                    int f = e + c
                    // println(f)
                }
            }
        """.trimIndent())
        processor.finish()

        println(json.stringify(processor.project.toJson()))
    }
})