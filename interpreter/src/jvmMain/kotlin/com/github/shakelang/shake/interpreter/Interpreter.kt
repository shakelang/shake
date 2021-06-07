package com.github.shakelang.shake.interpreter

import com.github.shakelang.shake.interpreter.values.Java
import com.github.shakelang.shake.lexer.Lexer
import com.github.shakelang.shake.parser.Parser
import com.github.shakelang.shake.util.characterinput.characterinputstream.CharacterInputStream
import com.github.shakelang.shake.util.characterinput.characterinputstream.SourceCharacterInputStream
import com.github.shakelang.shake.util.characterinput.charactersource.CharacterSource.Companion.from
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
    val lexer = Lexer(inputStream)
    val tokens = lexer.makeTokens()
    val parser = Parser(tokens)
    val tree = parser.parse()
    interpreter.visit(tree)
}