package io.github.shakelang.shake.util.shason

import io.github.shakelang.shake.util.parseutils.characters.source.CharacterSource
import io.github.shakelang.shake.util.parseutils.characters.streaming.SourceCharacterInputStream
import io.github.shakelang.shake.util.shason.processing.JsonGenerator
import io.github.shakelang.shake.util.shason.processing.JsonLexer
import io.github.shakelang.shake.util.shason.processing.JsonParser

/**
 * Api for processing json
 */
@Suppress("unused")
object JSON {

    /**
     * Parse json code
     *
     * @param code the json to parse
     */
    fun parse(code: String): Any {
        val source = CharacterSource.from(code, "JSON.parse")
        val chars = SourceCharacterInputStream(source)
        val lexer = JsonLexer(chars)
        val tokens = lexer.makeTokens()
        val parser = JsonParser(tokens)
        return parser.parse()
    }

    /**
     * Stringify json
     *
     * @param input the objects to stringify
     * @param indent the indent for the json (or null for no indent). Can be an Int (number of spaces) or a string
     * to indent with
     */
    fun stringify(input: Any?, indent: Any? = null): String {
        return JsonGenerator.generate(
            input, indent = when (indent) {
                null -> null
                is String -> indent
                is Int -> " ".repeat(indent)
                is Boolean -> if (indent) "  " else null
                else -> throw Error("Wrong indent input: \"$indent\"")
            }
        )
    }
}

/**
 * Api for processing json
 */
typealias json = io.github.shakelang.shake.util.shason.JSON
