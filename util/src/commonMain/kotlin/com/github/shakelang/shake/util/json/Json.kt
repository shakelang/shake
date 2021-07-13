package com.github.shakelang.shake.util.json

import com.github.shakelang.shake.util.characterinput.characterinputstream.SourceCharacterInputStream
import com.github.shakelang.shake.util.characterinput.charactersource.CharacterSource
import com.github.shakelang.shake.util.json.processing.JsonGenerator
import com.github.shakelang.shake.util.json.processing.JsonLexer
import com.github.shakelang.shake.util.json.processing.JsonParser

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
        val source = CharacterSource.from(code, "JSOn.parse")
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
