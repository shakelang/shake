package com.shakelang.util.shason.processing

import com.shakelang.util.parseutils.CompilerError
import com.shakelang.util.parseutils.ParserErrorFactory
import com.shakelang.util.parseutils.characters.Characters
import com.shakelang.util.parseutils.characters.position.Position
import com.shakelang.util.shason.elements.*
import com.shakelang.util.shason.processing.JsonTokenType.*

/**
 * A [JsonParser] creates a [JsonElement] from a [JsonTokenInputStream]
 * @since 0.1.0
 * @version 0.1.0
 */
@Suppress("unused")
class JsonParser(

    /**
     * The [JsonToken]s to parse ([JsonTokenInputStream])
     * @since 0.1.0
     * @version 0.1.0
     */
    val tokens: JsonTokenInputStream,

) {

    fun parse(): JsonElement {
        val ret = parseValue()
        if (tokens.hasNext()) throw errorFactory.createErrorAtCurrent("Input not finished")
        return ret
    }

    /**
     * Parse a [JsonElement]
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun parseValue(): JsonElement {
        val next = tokens.next()
        return when (next.type) {
            LCURL -> parseMap()
            LSQUARE -> parseArray()
            STRING -> JsonElement.from(Characters.parseString(next.value))
            INT -> JsonElement.from(next.value.toLong())
            DOUBLE -> JsonElement.from(next.value.toDouble())
            TRUE -> JsonBooleanElement.TRUE
            FALSE -> JsonBooleanElement.FALSE
            NULL -> JsonNullElement.INSTANCE

            else -> throw errorFactory.createErrorAtCurrent("Could not parse token $next")
        }
    }

    /**
     * Parse a [JsonObject]
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun parseMap(): JsonObject {
        val map = mutableJsonObjectOf()
        var next = true

        while (tokens.hasNext() && next) {
            val key = if (tokens.peek().type == STRING) Characters.parseString(tokens.next().value) else break
            if (tokens.next().type != COLON) throw errorFactory.createErrorAtCurrent("Expecting ':'")
            map[key] = parseValue()

            next = tokens.peek().type == COMMA
            if (next) tokens.skip()
        }

        if (tokens.next().type != RCURL) throw errorFactory.createErrorAtCurrent("Expecting '}'")

        return map
    }

    /**
     * Parse a [JsonArray]
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun parseArray(): JsonArray {
        val arr = mutableJsonArrayOf()
        var next = true

        while (tokens.hasNext() && next) {
            if (tokens.peek().type == RSQUARE) break
            arr.add(parseValue())
            next = tokens.peek().type == COMMA
            if (next) tokens.skip()
        }

        if (tokens.next().type != RSQUARE) throw errorFactory.createErrorAtCurrent("Expecting ']")
        return arr
    }

    // ****************************************************************************
    // Errors

    private inner class JsonParserErrorFactory : ParserErrorFactory<ParserError>(
        { message, start, end -> ParserError(message, start, end) },
        tokens,
    )
    private val errorFactory = JsonParserErrorFactory()

    /**
     * A [CompilerError] thrown by the [JsonParser]
     * @since 0.1.0
     * @version 0.3.0
     */
    private class ParserError(message: String, start: Position, end: Position) : CompilerError(message, start, end)
}
