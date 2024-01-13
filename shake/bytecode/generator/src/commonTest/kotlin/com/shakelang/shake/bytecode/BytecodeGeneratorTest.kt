package com.shakelang.shake.bytecode

import com.shakelang.shake.bytecode.generator.ShakeBytecodeGenerator
import com.shakelang.shake.bytecode.interpreter.ShakeInterpreter
import com.shakelang.shake.bytecode.interpreter.wrapper.ShakeClasspath
import com.shakelang.util.io.streaming.output.ByteArrayOutputStream
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class BytecodeGeneratorTest : FreeSpec(
    {

        "call native with params" {
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
        }

        "call a method" {
            val processor = createBaseProcessor()

            processor.loadSynthetic(
                "test.shake",
                """
                package test
                
                void main() {
                    test();
                }
                
                void test() {
                    print(1)
                }
                
                """.trimIndent(),
            )
            processor.finish()

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

            val output = stdout.toByteArray().joinToString("") { it.toInt().toChar().toString() }
            output shouldBe "1"
        }

        "call a method (with return value)" {
            val processor = createBaseProcessor()

            processor.loadSynthetic(
                "test.shake",
                """
                package test
                
                void main() {
                    print(test())
                }
                
                int test() {
                    return 1
                }
                
                """.trimIndent(),
            )
            processor.finish()

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

            val output = stdout.toByteArray().joinToString("") { it.toInt().toChar().toString() }
            output shouldBe "1"
        }

        "call a method (with params)" {
            val processor = createBaseProcessor()

            processor.loadSynthetic(
                "test.shake",
                """
                package test
                
                void main() {
                    test(3)
                }
                
                void test(int i) {
                    print(i)
                }
                
                """.trimIndent(),
            )
            processor.finish()

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

            val output = stdout.toByteArray().joinToString("") { it.toInt().toChar().toString() }
            output shouldBe "3"
        }

        "call a method (with parameters and return value)" {
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

            val output = stdout.toByteArray().joinToString("") { it.toInt().toChar().toString() }
            output shouldBe "3"
        }
    },
)
