package com.github.shakelang.shake.util.json

import com.github.shakelang.shake.util.characterinput.characterinputstream.SourceCharacterInputStream
import com.github.shakelang.shake.util.characterinput.charactersource.CharacterSource
import com.github.shakelang.shake.util.json.processing.JsonGenerator
import com.github.shakelang.shake.util.json.processing.JsonLexer
import com.github.shakelang.shake.util.json.processing.JsonParser

@Suppress("unused")
object JSON {
    fun parse(code: String): Any {
        val source = CharacterSource.from(code, "JSOn.parse")
        val chars = SourceCharacterInputStream(source)
        val lexer = JsonLexer(chars)
        val tokens = lexer.makeTokens()
        val parser = JsonParser(tokens)
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