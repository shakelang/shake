import com.github.shakelang.shake.interpreter.Interpreter
import com.github.shakelang.shake.lexer.Lexer
import com.github.shakelang.shake.parser.Parser
import com.github.shakelang.shake.parser.node.Tree
import com.github.shakelang.shake.util.characterinput.characterinputstream.CharacterInputStream
import com.github.shakelang.shake.util.characterinput.characterinputstream.SourceCharacterInputStream
import com.github.shakelang.shake.util.characterinput.charactersource.CharacterSource
import com.github.shakelang.shake.util.characterinput.position.PositionMap


/**
 * The core api files for the shake code execution
 */
private val core_files = mutableMapOf<String, String>()

/**
 * Add an api file to the interpreter code execution
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun addInterpreterFile(filename: String, contents: String): Boolean {
    val existing = core_files.containsKey(filename)
    core_files[filename] = contents
    return existing
}

/**
 * Execute the given code
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@JsName("execute")
@Suppress("unused")
fun execute(source: String, code: String) {

    val interpreter = Interpreter()

    // add code files
    core_files.forEach {
        val result = parse(it.key, it.value)
        interpreter.visit(result.tree)
    }

    val result = parse(source, code)
    interpreter.visit(result.tree)

}

/**
 * Parse the given code
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
private fun parse(source: String, input: String)
    = parse(SourceCharacterInputStream(CharacterSource.Companion.from(input, source)))


/**
 * This function parses a [CharacterInputStream] into a Tree
 *
 * @param input the [CharacterInputStream] to parse
 * @return the parsed [Tree]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
private fun parse(input: CharacterInputStream): ParseResult {

    // Create a new Lexer from the CharacterInputStream
    val lexer = Lexer(input)

    // Generate the tokens using the lexer
    val tokens = lexer.makeTokens()

    // if debug is enabled we print out the lexer-tokens
    debug("[DEBUG] Lexer-Tokens: $tokens")

    // Create a new Parser from the tokens
    val parser = Parser(tokens)

    // Let the parser parse the tokens into a Tree
    val tree = parser.parse()

    // If debug is enabled we print out the tree
    debug("[DEBUG] Parsed Tree: $tree", )

    // return the Tree
    return ParseResult(tree, tokens.map)

}


/**
 * The result of a parsing operation.
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
private class ParseResult(val tree: Tree, val map: PositionMap)


/**
 * Log a message to console.debug
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused_parameter")
private fun debug(message: String) {
    js("console.debug(message)")
}

@JsName("main")
@Suppress("unused_expression")
fun main() {
    // Keep these functions
    ::execute
    ::addInterpreterFile
}