package com.shakelang.shake.util.shason.processing

import com.shakelang.util.parseutils.characters.streaming.SourceCharacterInputStream
import com.shakelang.shake.util.shason.elements.JsonElement

object TestUtilities {
    fun makeTokens(source: String, contents: String): JsonTokenInputStream {
        val input = SourceCharacterInputStream(source, contents)
        val lexer = JsonLexer(input)
        return lexer.makeTokens()
    }

    fun parse(source: String, contents: String): JsonElement {
        val tokens = makeTokens(source, contents)
        val parser = JsonParser(tokens)
        return parser.parse()
    }
}
