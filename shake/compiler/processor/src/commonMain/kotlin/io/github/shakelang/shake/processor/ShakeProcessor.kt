package io.github.shakelang.shake.processor

import io.github.shakelang.parseutils.File
import io.github.shakelang.parseutils.characters.streaming.CharacterInputStream
import io.github.shakelang.parseutils.characters.streaming.SourceCharacterInputStream
import io.github.shakelang.shake.lexer.ShakeLexer
import io.github.shakelang.shake.parser.Parser
import io.github.shakelang.shake.parser.node.Tree
import io.github.shakelang.shake.parser.node.functions.FunctionDeclarationNode
import io.github.shakelang.shake.parser.node.objects.ClassDeclarationNode
import io.github.shakelang.shake.parser.node.variables.VariableDeclarationNode

abstract class ShakeProcessor <T> {

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

class ShakePackageBasedProcessor : ShakeProcessor<ShakePackage>() {

    val packages = ShakePackage("src")
    override val src get() = packages

    fun loadSynthetic(src: String, contents: Tree) {
        val reformatted = src.replace("\\", "/").replace(".", "/")
        packages.putFile(reformatted.split("/").toTypedArray(), contents)
    }

    override fun loadFile(directory: String, src: String) {

        val reformatted = src.replace("\\", "/").replace(".", "/")
        val contents = parseFile("$directory/$src")
        packages.putFile(reformatted.split("/").toTypedArray(), contents)

    }

}

open class ShakePackage (
    open val name: String,
    open val subpackages: MutableList<ShakePackage> = mutableListOf(),
    open val classes: MutableMap<String, ClassDeclarationNode> = mutableMapOf(),
    open val functions: MutableMap<String, FunctionDeclarationNode> = mutableMapOf(),
    open val fields: MutableMap<String, VariableDeclarationNode> = mutableMapOf()
) {

    open fun getPackage(name: String): ShakePackage {
        return subpackages.find { it.name == name } ?: ShakePackage(name).let {
            subpackages.add(it)
            it
        }
    }

    open fun getPackage(name: Array<String>): ShakePackage {
        return name.fold(this) { acc, pkgName -> acc.getPackage(pkgName) }
    }

    open fun putFile(name: String, contents: Tree) {
        contents.children.forEach {
            when (it) {
                is ClassDeclarationNode -> {
                    if(classes.containsKey(it.name)) {
                        throw Exception("Class ${it.name} already exists")
                    }
                    classes[it.name] = it
                }
                is FunctionDeclarationNode -> {
                    if(functions.containsKey(it.name)) {
                        throw Exception("Function ${it.name} already exists")
                    }
                    functions[it.name] = it
                }
                is VariableDeclarationNode -> {
                    if(fields.containsKey(it.name)) {
                        throw Exception("Field ${it.name} already exists")
                    }
                    fields[it.name] = it
                }
            }
        }
    }

    open fun putFile(name: Array<String>, contents: Tree) {
        val pkg = name.sliceArray(0 until name.size - 1)
        val file = name.last()
        getPackage(pkg).putFile(file, contents)
    }
}

open class ShakeFile (
    open val name: String,
    open val contents: Tree,
)
