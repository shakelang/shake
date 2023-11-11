package com.shakelang.shake.shasambly

import com.shakelang.shake.shasambly.shasp.generator.ShasPShasamblyGenerator
import com.shakelang.shake.shasambly.shasp.lexer.ShasPLexer
import com.shakelang.shake.shasambly.shasp.parser.ShasPParser
import com.shakelang.shake.util.parseutils.characters.source.CharacterSource
import com.shakelang.shake.util.parseutils.characters.streaming.SourceCharacterInputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

fun main(args: Array<String>) {
    if (args.isEmpty()) throw Error("Expecting at least one argument")
    if (args.size > 2) throw Error("Expecting maximum 2 arguments")
    if (!args[0].endsWith(".shasp")) throw Error("Input file has to end with .shas")
    val f = File(args[0])
    val inputStream = FileInputStream(f).buffered()
    val contents = inputStream.readAllBytes().map { it.toInt().toChar() }.toCharArray()
    inputStream.close()
    val source = CharacterSource.from(contents, args[0])
    val chars = SourceCharacterInputStream(source)
    val lexer = ShasPLexer(chars)
    val tokens = lexer.makeTokens()
    val parser = ShasPParser(tokens)
    val prog = parser.parse()

    val output = if (args.size == 1) "${args[0].substring(0 until args[0].length - 1)}x" else args[1]
    println("Generating file \"${File(output).absolutePath}\"...")
    val out = FileOutputStream(output).buffered()
    out.write(ShasPShasamblyGenerator(prog).generate().generate())
    out.flush()
    out.close()
}
