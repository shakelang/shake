package com.github.nsc.de.shake.util.json

import com.github.nsc.de.shake.util.characterinput.characterinputstream.SourceCharacterInputStream
import com.github.nsc.de.shake.util.characterinput.charactersource.CharacterSource

@Suppress("unused")
object JSON {
    fun parse(code: String, createSets: Boolean = false): Any {
        val source = CharacterSource.from(code, "JSOn.parse")
        val chars = SourceCharacterInputStream(source)
        val lexer = JsonLexer(chars)
        val tokens = lexer.makeTokens()
        val parser = JsonParser(tokens, createSets = createSets)
        return parser.parse()
    }
    fun stringify(input: Any?, indent: Any? = null): String {
        return JsonGenerator.generate(
            input, indent = when (indent) {
                null -> null
                is String -> indent
                is Int -> " ".repeat(indent)
                else -> throw Error("Wrong indent input: \"$indent\"")
            }
        )
    }
}
typealias json = JSON