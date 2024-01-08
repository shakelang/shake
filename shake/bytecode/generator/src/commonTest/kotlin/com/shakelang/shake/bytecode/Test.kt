package com.shakelang.shake.bytecode

import com.shakelang.shake.bytecode.generator.ShakeBytecodeGenerator
import com.shakelang.shake.bytecode.interpreter.ShakeInterpreter
import com.shakelang.shake.bytecode.interpreter.wrapper.ShakeClasspath
import com.shakelang.shake.processor.ShakePackageBasedProcessor
import com.shakelang.shake.stdlib.CoreFiles
import com.shakelang.util.primitives.bytes.toInt
import io.kotest.core.spec.style.FreeSpec

class Test : FreeSpec(
    {

        "test" {
            val processor = ShakePackageBasedProcessor()
            processor.loadSynthetic("shake/lang/Object.shake", CoreFiles.OBJECT_SHAKE)
            processor.loadSynthetic("shake/lang/String.shake", CoreFiles.STRING_SHAKE)
            processor.loadSynthetic("shake/lang/Numbers.shake", CoreFiles.NUMBERS_SHAKE)
            processor.loadSynthetic(
                "test.shake",
                """
                package test
                
                import test2.test
                
                class Test {
                    static int main() {
                        int a = 1
                        int b = 2
                        int c = a + b
                        // println(f)
                        return c
                    }
                }
                """.trimIndent(),
            )
            processor.finish()

            // println(json.stringify(processor.project.toJson()))

            val generator = ShakeBytecodeGenerator()
            val out = generator.generateProject(processor.project)

            // Execute test code
            val classPath = ShakeClasspath.create()
            out.forEach {
                classPath.load(it)
            }

            val interpreter = ShakeInterpreter(classPath)
            interpreter.putFunctionOnStack(
                "test/Test:main()I",
                byteArrayOf(),
            )
            val ticks = interpreter.tick(1000)

            println("Finished in $ticks ticks")
            println("Result: ${interpreter.latestReturnData.toInt()}")
        }
    },
)
