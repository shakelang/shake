/**
 * The main command-line CLI for Shake
 */

@file:JvmName("ShakeCli")

package io.github.shakelang.shake.cli

import io.github.shakelang.parseutils.File
import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.parseutils.characters.streaming.CharacterInputStream
import io.github.shakelang.parseutils.characters.streaming.SourceCharacterInputStream
import io.github.shakelang.shake.interpreter.Interpreter
import io.github.shakelang.shake.js.ShakeJsGenerator
import io.github.shakelang.shake.lexer.ShakeLexer
import io.github.shakelang.shake.parser.Parser
import io.github.shakelang.shake.parser.node.Tree
import io.github.shakelang.shason.json
import kotlin.jvm.JvmName

/**
 * The interpreter for interpreting the code
 */
val interpreter = Interpreter()

val jsGenerator = ShakeJsGenerator()

// ----------------------------------------------------------------
//  Java Generator is not implemented yet
//
// /**
//  * The java-generator for generating java form the input code
//  */
// val java = JavaGenerator()

/**
 * Is the program in debug mode?
 */
var DEBUG = false

/**
 * The version of the program
 */
const val VERSION = "0.1.0"

/**
 * The Main-Method for the ShakeCli
 *
 * @param args The arguments for the main cli
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun main(args: Array<String>) {

    // Create a parser for the arguments
    val argumentParser = io.github.shakelang.shake.cli.CliArgumentParser()

    // Define the options for the argumentParser
    argumentParser
        .option("generator", "g", 1, arrayOf("interpreter"))
        .option("debug", "d")
        .option("target", "t", 1, arrayOf(null))


    // Parse the arguments given to the main-method
    val arguments = argumentParser.parse(args)

    // Detect debug mode (check if debug option is set ("--debug"))
    DEBUG = arguments.getOption("debug")!!.isGiven

    // Get the generator ("--generator")
    val generator = arguments.getOption("generator")!!.values!![0]

    // If no normal argument is given, then we will open a prompt for entering code
    when(arguments.arguments.size) {

        0 -> {
            // Info message for Shake console
            println(
                "# Shake version $VERSION ${if (DEBUG) "in debug mode " else ""}\n" +
                "# Enter Shake code below to execute!\n" +
                "# Using $generator to execute code"
            )

            // Just create a infinite loop for reading from the console
            mainLoop {

                // request the input from the console and create a StringCharacterInputStream from it
                val chars: CharacterInputStream = SourceCharacterInputStream("<Console>", it)

                // parse the CharacterInputStream into a Tree
                val pr = parse(chars)

                // execute the tree using the specified generator
                execute(pr, generator, null, arguments.getOption("target")!!.values!![0])
            }
        }

        1 -> {
            val src = arguments.arguments[0]

            // read the contents of the file
            val file = File(src).contents

            // Create a new StringCharacterInputStream from the file's contents
            val chars: CharacterInputStream = SourceCharacterInputStream(
                "<File: " + arguments.arguments[0] + ">", file
            )

            // Parse the CharacterInputStream
            val pr = parse(chars)

            // Execute the Tree using the specified generator
            execute(pr, generator, src, arguments.getOption("target")!!.values!![0])
        }

        else -> throw Error("There are only 0-1 arguments allowed")
    }
}

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
    val lexer = ShakeLexer(input)

    // Generate the tokens using the lexer
    val tokens = lexer.makeTokens()

    // if debug is enabled we print out the lexer-tokens
    if (DEBUG) println("[DEBUG] Lexer-Tokens: $tokens")

    // Create a new Parser from the tokens
    val parser = Parser(tokens)

    // Let the parser parse the tokens into a Tree
    val tree = parser.parse()

    // If debug is enabled we print out the tree
    if (DEBUG) println("[DEBUG] Parsed Tree: $tree", )

    // return the Tree
    return ParseResult(tree, tokens.map)
}

/**
 * This function executes a [Tree] using a specified generator
 *
 * @param pr the [ParseResult] to execute
 * @param generator the generator to use (just give the name of it)
 * @param src the source file of the program
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
private fun execute(pr: ParseResult, generator: String?, src: String?, target: String?) {
    if (src != null && !src.endsWith(".shake")) throw Error("Shake file names have to end with extension \".shake\"")
    val targetFile = src?.substring(0, src.length - 6)
    // val baseName = if (src != null) targetFile!!.split("[\\\\/](?=[^\\\\/]+$)").toTypedArray()[1] else null
    when (generator) {
        "interpreter" -> println(">> ${interpreter.visit(pr.tree)}")
        "json" ->
            if (src == null) println(">> ${pr.tree}")
            else writeFile(File(target ?: "$targetFile.json"), pr.tree.toString())
        "beauty-json", "bjson" ->
            if (src == null) println(">> ${json.stringify(pr.tree, indent = 2)}")
            else writeFile(File(target ?: "$targetFile.json"), json.stringify(pr.tree, indent = 2))
        "java" -> throw Error("Java is not available")
        "js", "javascript" -> println(jsGenerator.visit(pr.tree).generate())
        //     if (src == null) println(">> ${java.visitProgram(pr.tree, "CliInput").toString("", "  ")}%n")
        //     else writeFile(File(target ?: targetFile + java.extension), java.visitProgram(pr.tree, baseName).toString("", "  "))
        else -> throw Error("Unknown generator!")
    }
}

private fun writeFile(f: File, content: String) {
    println("Generating file \"${f.absolutePath}\"...")
    f.parent.mkdirs()
    f.write(content)
}

private class ParseResult(val tree: Tree, val map: PositionMap)