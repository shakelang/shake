package com.shakelang.shake.interpreter

import com.shakelang.shake.util.parseutils.characters.source.CharacterSource.Companion.from
import com.shakelang.shake.util.parseutils.characters.streaming.CharacterInputStream
import com.shakelang.shake.util.parseutils.characters.streaming.SourceCharacterInputStream
import com.shakelang.shake.interpreter.values.Java
import com.shakelang.shake.lexer.ShakeLexer
import com.shakelang.shake.parser.ShakeParser
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
    val parser = ShakeParser(tokens)
    val tree = parser.parse()
    interpreter.visit(tree)
}