package io.github.shakelang.shake.shasambly.shasp

import io.github.shakelang.parseutils.characters.source.CharacterSource
import io.github.shakelang.parseutils.characters.streaming.SourceCharacterInputStream
import io.github.shakelang.shake.shasambly.shasp.lexer.ShasPLexer
import io.github.shakelang.shake.shasambly.shasp.parser.ShasPParser
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

fun main(args: Array<String>) {
    if(args.isEmpty()) throw Error("Expecting at least one argument")
    if(args.size > 2) throw Error("Expecting maximum 2 arguments")
    if(!args[0].endsWith(".shasp")) throw Error("Input file has to end with .shas")
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
    println(prog)
    val output = if(args.size == 1) "${args[0]}x" else args[1]
    println("Generating file \"${File(output).absolutePath}\"...")
    val out = FileOutputStream(output).buffered()
    out.flush()
    out.close()
}