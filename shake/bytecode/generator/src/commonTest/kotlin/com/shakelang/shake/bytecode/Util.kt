package com.shakelang.shake.bytecode

import com.shakelang.shake.bytecode.generator.ShakeBytecodeGenerator
import com.shakelang.shake.bytecode.interpreter.ShakeInterpreter
import com.shakelang.shake.bytecode.interpreter.format.StorageFormat
import com.shakelang.shake.bytecode.interpreter.wrapper.ShakeInterpreterClasspath
import com.shakelang.shake.processor.ShakePackageBasedProcessor
import com.shakelang.shake.shakelib.ShakeLib
import com.shakelang.util.io.streaming.output.bytes.ByteArrayOutputStream
import com.shakelang.util.parseutils.CompilerError

fun createBaseProcessor(): ShakePackageBasedProcessor {
    val processor = ShakePackageBasedProcessor()
    ShakeLib.forEachFile {
        processor.loadSynthetic(it.path, it.contentsAsString())
    }

    return processor
}

interface CodeSpec {
    val processor: ShakePackageBasedProcessor
    val stdout: ByteArrayOutputStream
    val classpath: ShakeInterpreterClasspath
    val interpreter: ShakeInterpreter
    val ticks: Int
    val format: StorageFormat

    val consoleOut: String
        get() = stdout.toByteArray().joinToString("") { it.toInt().toChar().toString() }

    fun putFunctionOnStack(name: String) =
        interpreter.putFunctionOnStack(
            name,
            byteArrayOf(),
        )

    fun run(limit: Int = -1)
    fun execute(name: String, limit: Int = -1) {
        putFunctionOnStack(name)
        run(limit)
    }
}

fun codeSpec(code: String, then: CodeSpec.() -> Unit) {
    try {
        val processor = createBaseProcessor()

        processor.loadSynthetic("<Unit Tests>", code)

        processor.finish()

        val generator = ShakeBytecodeGenerator()
        val out = generator.generateProject(processor.project)

        val stdout = ByteArrayOutputStream()

        val interpreter = ShakeInterpreter()
        interpreter.classPath.load(out)
        interpreter.process.setOut(stdout)

        val codeSpec = (
            object : CodeSpec {

                override var ticks: Int = -99
                    get() = if (field == -99) throw IllegalStateException("Code not executed") else field
                    private set

                override val processor: ShakePackageBasedProcessor
                    get() = processor
                override val stdout: ByteArrayOutputStream
                    get() = stdout
                override val classpath: ShakeInterpreterClasspath
                    get() = interpreter.classPath
                override val interpreter: ShakeInterpreter
                    get() = interpreter
                override val format: StorageFormat
                    get() = out.find { it.packageName == "test" } ?: throw IllegalStateException("No test package found")

                override fun run(limit: Int) {
                    this.ticks = interpreter.run(limit)
                }
            }
            )

        then(codeSpec)
    } catch (e: CompilerError) {
        println(e.message + " at " + e.marker.source)
        println(e.marker.preview)
        println(e.marker.marker)
        e.printStackTrace()
        throw e
    }
}
