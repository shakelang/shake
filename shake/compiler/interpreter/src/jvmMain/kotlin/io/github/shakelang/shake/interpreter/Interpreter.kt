package io.github.shakelang.shake.interpreter

import io.github.shakelang.shake.interpreter.values.Java
import io.github.shakelang.shake.lexer.ShakeLexer
import io.github.shakelang.shake.parser.Parser
import io.github.shakelang.parseutils.characters.streaming.CharacterInputStream
import io.github.shakelang.parseutils.characters.streaming.SourceCharacterInputStream
import io.github.shakelang.parseutils.characters.source.CharacterSource.Companion.from
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

@Throws(Error::class)
actual fun applyDefaults(interpreter: Interpreter) {
    interpreter.global.getVariables().declare(Variable.finalOf("java", Java()))
    load(interpreter, "/shake/java/system.shake", "shake/system.shake")
    load(interpreter, "/shake/java/io.shake", "shake/io.shake")
}


@Throws(IOException::class)
private fun load(interpreter: Interpreter, src: String, src_name: String) {
    val s = interpreter::class.java.getResourceAsStream(src)!!
    val reader = BufferedReader(InputStreamReader(s))
    val chars = CharArray(s.available())
    for (i in chars.indices) chars[i] = reader.read().toChar()
    val source = from(chars, src_name)
    val inputStream: CharacterInputStream = SourceCharacterInputStream(source)
    val lexer = ShakeLexer(inputStream)
    val tokens = lexer.makeTokens()
    val parser = Parser(tokens)
    val tree = parser.parse()
    interpreter.visit(tree)
}