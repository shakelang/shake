package com.shakelang.util.shason

import com.shakelang.util.parseutils.characters.source.CharacterSource
import com.shakelang.util.parseutils.characters.streaming.SourceCharacterInputStream
import com.shakelang.util.shason.elements.JsonElement
import com.shakelang.util.shason.processing.JsonGenerator
import com.shakelang.util.shason.processing.JsonLexer
import com.shakelang.util.shason.processing.JsonParser

/**
 * Api for processing json
 * @since 0.1.0
 * @version 0.1.0
 */
object JSON {

    /**
     * Parse json code
     * @param code the json to parse
     * @since 0.1.0
     * @version 0.1.0
     */
    fun parse(code: String): JsonElement {
        val source = CharacterSource.from(code, "JSON.parse")
        val chars = SourceCharacterInputStream(source)
        val lexer = JsonLexer(chars)
        val tokens = lexer.stream()
        val parser = JsonParser(tokens)
        return parser.parse()
    }

    /**
     * Stringify json
     * @param input the objects to stringify
     * @param indent the indent for the json (or null for no indent). Can be an Int (number of spaces) or a string
     * to indent with
     * @since 0.1.0
     * @version 0.1.0
     */
    fun stringify(input: Any?, indent: Any? = null): String {
        return JsonGenerator.generate(
            input,
            indent = when (indent) {
                null -> null
                is String -> indent
                is Int -> " ".repeat(indent)
                is Boolean -> if (indent) "  " else null
                else -> throw Error("Wrong indent input: \"$indent\"")
            },
        )
    }
}

/**
 * Api for processing json
 * @since 0.1.0
 * @version 0.1.0
 */
val json = JSON
