/**
 * The main command-line CLI for Shake
 */

@file:JvmName("ShakeCli")

package com.shakelang.shake.cli

import com.shakelang.shake.js.ShakeJsGenerator
import com.shakelang.shake.lexer.ShakeLexer
import com.shakelang.shake.parser.ShakeParser
import com.shakelang.shake.parser.node.ShakeBlockNode
import com.shakelang.shake.parser.node.ShakeFileNode
import com.shakelang.shake.processor.ShakePackageBasedProcessor
import com.shakelang.shake.stdlib.CoreFiles
import com.shakelang.util.parseutils.File
import com.shakelang.util.parseutils.characters.position.PositionMap
import com.shakelang.util.parseutils.characters.streaming.CharacterInputStream
import com.shakelang.util.parseutils.characters.streaming.SourceCharacterInputStream
import com.shakelang.util.shason.json
import kotlin.jvm.JvmName

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

val baseProcessor: ShakePackageBasedProcessor get() {
    val processor = ShakePackageBasedProcessor()

    processor.loadSynthetic("shake/lang/Object.shake", CoreFiles.OBJECT_SHAKE)
    processor.loadSynthetic("shake/lang/String.shake", CoreFiles.STRING_SHAKE)
    processor.loadSynthetic("shake/lang/Numbers.shake", CoreFiles.NUMBERS_SHAKE)

    return processor
}

/**
 * The Main-Method for the ShakeCli
 *
 * @param args The arguments for the main cli
 */
fun main(args: Array<String>) {
    // Create a parser for the arguments
    val argumentParser = CliArgumentParser()

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
    when (arguments.arguments.size) {
        0 -> {
            // Info message for Shake console
            println(
                "# Shake version $VERSION ${if (DEBUG) "in debug mode " else ""}\n" +
                    "# Enter Shake code below to execute!\n" +
                    "# Using $generator to execute code"
            )

            // Create an infinite loop for reading from the console
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
                "<File: " + arguments.arguments[0] + ">",
                file
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
 * @return the parsed [ShakeBlockNode]
 */
private fun parse(input: CharacterInputStream): ParseResult {
    // Create a new Lexer from the CharacterInputStream
    val lexer = ShakeLexer(input)

    // Generate the tokens using the lexer
    val tokens = lexer.makeTokens()

    // if debug is enabled, we print out the lexer-tokens
    if (DEBUG) println("[DEBUG] Lexer-Tokens: $tokens")

    // Create a new Parser from the tokens
    val parser = ShakeParser.from(tokens)

    // Let the parser parse the tokens into a Tree
    val tree = parser.parse()

    // If debug is enabled, we print out the tree
    if (DEBUG) println("[DEBUG] Parsed Tree: $tree")

    // return the Tree
    return ParseResult(tree, tokens.map)
}

/**
 * This function executes a [ShakeBlockNode] using a specified generator
 *
 * @param pr the [ParseResult] to execute
 * @param generator the generator to use (just give the name of it)
 * @param src the source file of the program
 */
private fun execute(pr: ParseResult, generator: String?, src: String?, target: String?) {
    if (src != null && !src.endsWith(".shake")) throw Error("Shake file names have to end with extension \".shake\"")
    val targetFile = src?.substring(0, src.length - 6)
    // val baseName = if (src != null) targetFile!!.split("[\\\\/](?=[^\\\\/]+$)").toTypedArray()[1] else null
    when (generator) {
        "interpreter" -> {
            val processor = baseProcessor
            processor.loadSynthetic("stdin.ConsoleInput", pr.tree)
        }
        "json" ->
            if (src == null) {
                println(">> ${pr.tree}")
            } else {
                writeFile(File(target ?: "$targetFile.json"), pr.tree.toString())
            }

        "beauty-json", "bjson" ->
            if (src == null) {
                println(">> ${json.stringify(pr.tree, indent = 2)}")
            } else {
                writeFile(File(target ?: "$targetFile.json"), json.stringify(pr.tree, indent = 2))
            }

        "java" -> TODO()
        "js", "javascript" -> {
            val processor = baseProcessor
            processor.loadSynthetic("stdin.ConsoleInput", pr.tree)
            TODO()
            //  println(processor.generate(jsGenerator::generateSingleFile).generate())
        }
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

private class ParseResult(val tree: ShakeFileNode, val map: PositionMap)
