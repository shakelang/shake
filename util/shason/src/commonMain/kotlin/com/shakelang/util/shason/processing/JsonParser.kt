package com.shakelang.util.shason.processing

import com.shakelang.util.parseutils.characters.Characters
import com.shakelang.util.parseutils.parser.AbstractParser
import com.shakelang.util.shason.elements.*
import com.shakelang.util.shason.processing.JsonTokenType.*

/**
 * A [JsonParser] creates a [JsonElement] from a [JsonTokenInputStream]
 * @since 0.1.0
 * @version 0.1.0
 */
@Suppress("unused")
class JsonParser(

    tokens: JsonTokenInputStream,

) : AbstractParser<JsonTokenInputStream, JsonElement>(tokens) {

    override fun parse(): JsonElement {
        val ret = parseValue()
        if (input.hasNext()) throw errorFactory.createErrorAtCurrent("Input not finished")
        return ret
    }

    /**
     * Parse a [JsonElement]
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun parseValue(): JsonElement {
        val next = input.next()
        return when (next.type) {
            LCURL -> parseMap()
            LSQUARE -> parseArray()
            STRING -> JsonElement.from(Characters.decodeStringContents(next.value))
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

        while (input.hasNext() && next) {
            val key = if (input.peek().type == STRING) Characters.decodeStringContents(input.next().value) else break
            if (input.next().type != COLON) throw errorFactory.createErrorAtCurrent("Expecting ':'")
            map[key] = parseValue()

            next = input.peek().type == COMMA
            if (next) input.skip()
        }

        if (input.next().type != RCURL) throw errorFactory.createErrorAtCurrent("Expecting '}'")

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

        while (input.hasNext() && next) {
            if (input.peek().type == RSQUARE) break
            arr.add(parseValue())
            next = input.peek().type == COMMA
            if (next) input.skip()
        }

        if (input.next().type != RSQUARE) throw errorFactory.createErrorAtCurrent("Expecting ']")
        return arr
    }
}
