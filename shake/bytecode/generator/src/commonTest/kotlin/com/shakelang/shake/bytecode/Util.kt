package com.shakelang.shake.bytecode

import com.shakelang.shake.bytecode.generator.ShakeBytecodeGenerator
import com.shakelang.shake.bytecode.interpreter.ShakeInterpreter
import com.shakelang.shake.bytecode.interpreter.wrapper.ShakeClasspath
import com.shakelang.shake.processor.ShakePackageBasedProcessor
import com.shakelang.shake.stdlib.CoreFiles
import com.shakelang.util.io.streaming.output.ByteArrayOutputStream

fun createBaseProcessor(): ShakePackageBasedProcessor {
    val processor = ShakePackageBasedProcessor()
    processor.loadSynthetic("shake/lang/Object.shake", CoreFiles.OBJECT_SHAKE)
    processor.loadSynthetic("shake/lang/String.shake", CoreFiles.STRING_SHAKE)
    processor.loadSynthetic("shake/lang/Numbers.shake", CoreFiles.NUMBERS_SHAKE)
    processor.loadSynthetic("shake/lang/Print.shake", CoreFiles.PRINT_SHAKE)

    return processor
}

interface CodeSpec {
    val processor: ShakePackageBasedProcessor
    val stdout: ByteArrayOutputStream
    val classpath: ShakeClasspath
    val interpreter: ShakeInterpreter
    val ticks: Int

    val consoleOut: String
        get() = stdout.toByteArray().joinToString("") { it.toInt().toChar().toString() }

    fun putFunctionOnStack(name: String) =
        interpreter.putFunctionOnStack(
            "test/main()V",
            byteArrayOf(),
        )

    fun run(limit: Int = -1)
    fun execute(name: String, limit: Int = -1) {
        putFunctionOnStack(name)
        run(limit)
    }
}

fun codeSpec(code: String, then: CodeSpec.() -> Unit) {
    val processor = createBaseProcessor()

    processor.loadSynthetic("<Unit Tests>", code)

    processor.finish()

    val generator = ShakeBytecodeGenerator()
    val out = generator.generateProject(processor.project)

    val stdout = ByteArrayOutputStream()

    val interpreter = ShakeInterpreter()
    interpreter.classPath.load(out)
    interpreter.process.setOut(stdout)

    (
        object : CodeSpec {

            override var ticks: Int = -99
                get() = if (field == -99) throw IllegalStateException("Code not executed") else field
                private set

            override val processor: ShakePackageBasedProcessor
                get() = processor
            override val stdout: ByteArrayOutputStream
                get() = stdout
            override val classpath: ShakeClasspath
                get() = interpreter.classPath
            override val interpreter: ShakeInterpreter
                get() = interpreter

            override fun run(limit: Int) {
                this.ticks = interpreter.run(limit)
            }
        }
        )
}
