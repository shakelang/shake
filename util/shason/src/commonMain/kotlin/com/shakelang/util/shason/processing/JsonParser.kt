package com.shakelang.util.shason.processing

import com.shakelang.util.parseutils.CompilerError
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
        if (tokens.hasNext()) throw ParserError("Input not finished")
        return ret
    }

    /**
     * Parse a [JsonElement]
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun parseValue(): JsonElement {
        return when (val next = tokens.next().type) {
            LCURL -> parseMap()
            LSQUARE -> parseArray()
            STRING -> JsonElement.from(Characters.parseString(tokens.actual.value!!))
            INT -> JsonElement.from(tokens.actual.value!!.toLong())
            DOUBLE -> JsonElement.from(tokens.actual.value!!.toDouble())
            TRUE -> JsonBooleanElement.TRUE
            FALSE -> JsonBooleanElement.FALSE

            else -> throw ParserError("Could not parse token $next")
        }
    }

    /**
     * Parse a [JsonObject]
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun parseMap(): JsonObject {
        if (tokens.actual.type != LCURL) throw ParserError("Expecting '{'")

        val map = mutableJsonObjectOf()
        var next = true

        while (tokens.hasNext() && next) {
            val key = if (tokens.peek().type == STRING) Characters.parseString(tokens.next().value!!) else break
            if (tokens.nextType() != COLON) throw ParserError("Expecting ':'")
            map[key] = parseValue()

            next = tokens.peek().type == COMMA
            if (next) tokens.skip()
        }

        if (tokens.next().type != RCURL) throw ParserError("Expecting '}'")

        return map
    }

    /**
     * Parse a [JsonArray]
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun parseArray(): JsonArray {
        if (tokens.actual.type != LSQUARE) throw ParserError("Expecting '['")

        val arr = mutableJsonArrayOf()
        var next = true

        while (tokens.hasNext() && next) {
            if (tokens.peek().type == RSQUARE) break
            arr.add(parseValue())
            next = tokens.peek().type == COMMA
            if (next) tokens.skip()
        }

        if (tokens.next().type != RSQUARE) throw ParserError("Expecting ']")
        return arr
    }

    // ****************************************************************************
    // Errors

    /**
     * An [CompilerError] thrown by the [JsonParser]
     * @since 0.1.0
     * @version 0.3.0
     */
    inner class ParserError(message: String, name: String, details: String, start: Position, end: Position) :
        CompilerError(message, name, details, start, end) {

        /**
         * Constructor for [ParserError]
         *
         * @param name the name of the [ParserError]
         * @param details the details of the [ParserError]
         * @param start the start position of the [ParserError]
         * @param end the end position of the [ParserError]
         * @since 0.1.0
         * @version 0.3.0
         */
        constructor(
            name: String,
            details: String,
            start: Position,
            end: Position,
        ) : this(
            "Error occurred in parser: $name, $details in ${start.source}:${start.line}:${start.column}",
            name,
            details,
            start,
            end,
        )

        /**
         * Constructor for [ParserError]
         *
         * @param details the details of the [ParserError]
         * @param start the start position of the [ParserError]
         * @param end the end position of the [ParserError]
         * @since 0.1.0
         * @version 0.1.0
         */
        constructor(details: String, start: Position, end: Position) : this("ParserError", details, start, end)

        /**
         * Constructor for [ParserError]
         * @param details the details of the [ParserError]
         * @param start the start position of the [ParserError]
         * @param end the end position of the [ParserError]
         * @since 0.1.0
         * @version 0.1.0
         */
        constructor(details: String, start: Int, end: Int) : this(
            "ParserError",
            details,
            tokens.map.resolve(start),
            tokens.map.resolve(end),
        )

        /**
         * Constructor for [ParserError]
         * @param error the error message
         * @since 0.1.0
         * @version 0.1.0
         */
        constructor(error: String) : this(
            error,
            tokens.map.resolve(tokens.peek().start),
            tokens.map.resolve(tokens.peek().end),
        )
    }
}
