package io.github.shakelang.shason

import io.github.shakelang.shason.processing.JsonGenerator
import io.github.shakelang.shason.processing.JsonLexer
import io.github.shakelang.shason.processing.JsonParser
import io.github.shakelang.parseutils.characters.source.CharacterSource
import io.github.shakelang.parseutils.characters.streaming.SourceCharacterInputStream

/**
 * Api for processing json
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
object JSON {

    /**
     * Parse json code
     *
     * @param code the json to parse
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
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

/**
 * Api for processing json
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
typealias json = JSON
