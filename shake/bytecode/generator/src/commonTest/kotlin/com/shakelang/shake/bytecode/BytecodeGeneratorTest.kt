package com.shakelang.shake.bytecode

import com.shakelang.shake.bytecode.generator.ShakeBytecodeGenerator
import com.shakelang.shake.bytecode.interpreter.ShakeInterpreter
import com.shakelang.shake.bytecode.interpreter.wrapper.ShakeClasspath
import com.shakelang.shake.bytecode.tools.BytecodeStringGenerator
import com.shakelang.util.io.streaming.output.ByteArrayOutputStream
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class BytecodeGeneratorTest : FreeSpec(
    {

        "execute a method" {
            val processor = createBaseProcessor()

            processor.loadSynthetic(
                "test.shake",
                """
                package test
                
                void main() {
                    print(1 + 2)
                }
                
                """.trimIndent(),
            )
            processor.finish()

            // println(json.stringify(processor.project.toJson()))

            val generator = ShakeBytecodeGenerator()
            val out = generator.generateProject(processor.project)

            val stdout = ByteArrayOutputStream()

            // Execute test code
            val classPath = ShakeClasspath.create(out)

            val interpreter = ShakeInterpreter(classPath)
            interpreter.process.setOut(stdout)

            interpreter.putFunctionOnStack(
                "test/main()V",
                byteArrayOf(),
            )
            val ticks = interpreter.run()

            val output = stdout.toByteArray().joinToString { it.toInt().toChar().toString() }
            output shouldBe "3"
            ticks shouldBe 6
        }

        "call a method" {
            val processor = createBaseProcessor()

            processor.loadSynthetic(
                "test.shake",
                """
                package test
                
                void main() {
                    print(add(1, 2))
                }
                
                int add(int a, int b) {
                    return a + b
                }
                
                """.trimIndent(),
            )
            processor.finish()

            // println(json.stringify(processor.project.toJson()))

            val generator = ShakeBytecodeGenerator()
            val out = generator.generateProject(processor.project)

            val stdout = ByteArrayOutputStream()

            // Execute test code
            val classPath = ShakeClasspath.create(out)

            // Get test package
            val testPackage = classPath.packages.find { it.name == "test" }!!.storages[0]
            println(BytecodeStringGenerator.generate(testPackage).joinToString("\n"))

            val interpreter = ShakeInterpreter(classPath)
            interpreter.process.setOut(stdout)

            interpreter.putFunctionOnStack(
                "test/main()V",
                byteArrayOf(),
            )
            val ticks = interpreter.run()

            val output = stdout.toByteArray().joinToString { it.toInt().toChar().toString() }
            output shouldBe "3"
            ticks shouldBe 8
        }
    },
)
