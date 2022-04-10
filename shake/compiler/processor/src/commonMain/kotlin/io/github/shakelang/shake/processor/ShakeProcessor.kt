package io.github.shakelang.shake.processor

import io.github.shakelang.parseutils.File
import io.github.shakelang.parseutils.characters.streaming.CharacterInputStream
import io.github.shakelang.parseutils.characters.streaming.SourceCharacterInputStream
import io.github.shakelang.shake.lexer.ShakeLexer
import io.github.shakelang.shake.parser.Parser
import io.github.shakelang.shake.parser.node.*

class ShakeProcessorOptions {
    var precalculate: Boolean = true
}

abstract class ShakeProcessor <T> {

    val options: ShakeProcessorOptions = ShakeProcessorOptions()

    abstract val src: T

    open fun parseFile(src: String): Tree {

        val file = File(src).contents
        val chars: CharacterInputStream = SourceCharacterInputStream(src, file)

        val lexer = ShakeLexer(chars)
        val tokens = lexer.makeTokens()
        val parser = Parser(tokens)
        return parser.parse()

    }

    abstract fun loadFile(directory: String, src: String)
    fun <O> generate(f: (T) -> O): O {
        return f(src)
    }


}
open class ShakePackageBasedProcessor : ShakeProcessor<ShakeProject>() {

    open val project = ShakeProject()
    override val src: ShakeProject
        get() = project

    open fun loadSynthetic(src: String, contents: Tree) {
        val reformatted = src.replace("\\", "/").replace(".", "/")
        project.putFile(reformatted.split("/").toTypedArray(), contents)
    }

    override fun loadFile(directory: String, src: String) {

        val reformatted = src.replace("\\", "/").replace(".", "/")
        val contents = parseFile("$directory/$src")
        project.putFile(reformatted.split("/").toTypedArray(), contents)

    }
}