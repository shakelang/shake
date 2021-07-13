package com.github.shakelang.shake.util.json

import com.github.shakelang.shake.util.Characters
import com.github.shakelang.shake.util.CompilerError
import com.github.shakelang.shake.util.characterinput.position.Position
import com.github.shakelang.shake.util.json.JsonTokenType.*
import com.github.shakelang.shake.util.json.elements.*


/**
 * A [JsonParser] creates a [JsonElement] from a [JsonTokenInputStream]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
class JsonParser(

    /**
     * The [JsonToken]s to parse ([JsonTokenInputStream])
     */
    val tokens: JsonTokenInputStream

) {

    fun parse(): JsonElement {

        val ret = parseValue()
        if(tokens.hasNext()) throw ParserError("Input not finished")
        return ret

    }

    /**
     * Parse a [JsonElement]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    private fun parseValue(): JsonElement {

        return when (val next = tokens.next().type) {

            LCURL -> parseMap()
            LSQUARE -> parseArray()
            STRING -> JsonElement.from(Characters.parseString(tokens.actual().value!!))
            INT -> JsonElement.from(tokens.actual().value!!.toLong())
            DOUBLE -> JsonElement.from(tokens.actual().value!!.toDouble())

            else -> throw ParserError("Could not parse token $next")

        }

    }

    /**
     * Parse a [JsonObject]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    private fun parseMap(): JsonObject {
        if(tokens.actual().type != LCURL) throw ParserError("Expecting '{'")

        val map = mutableJsonObjectOf()
        var next = true

        while(tokens.hasNext() && next) {
            val key = if(tokens.peek().type == STRING) Characters.parseString(tokens.next().value!!) else break
            if(tokens.next().type != COLON) throw ParserError("Expecting ':'")
            map[key] = parseValue()

            next = tokens.peek().type == COMMA
            if(next) tokens.skip()

        }

        if(tokens.next().type != RCURL) throw ParserError("Expecting '}'")

        return map
    }

    /**
     * Parse a [JsonArray]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    private fun parseArray(): JsonArray {
        if(tokens.actual().type != LSQUARE) throw ParserError("Expecting '['")

        val arr = mutableJsonArrayOf()
        var next = true

        while(tokens.hasNext() && next) {

            if(tokens.peek().type == RSQUARE) break
            arr.add(parseValue())
            next = tokens.peek().type == COMMA
            if(next) tokens.skip()

        }

        if(tokens.next().type != RSQUARE) throw ParserError("Expecting ']")
        return arr

    }

    // ****************************************************************************
    // Errors

    /**
     * An [CompilerError] thrown by the [JsonParser]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    inner class ParserError(message: String, name: String, details: String, start: Position, end: Position) :
        CompilerError(message, name, details, start, end) {

        init {
            println(start)
            println(end)
        }

        /**
         * Constructor for [ParserError]
         *
         * @param name the name of the [ParserError]
         * @param details the details of the [ParserError]
         * @param start the start position of the [ParserError]
         * @param end the end position of the [ParserError]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        constructor(
            name: String,
            details: String,
            start: Position,
            end: Position
        ) : this(
            "Error occurred in parser: " + name + ", " + details + " in " + start.source + ":" + start.line + ":" + start.column,
            name,
            details,
            start,
            end
        )


        /**
         * Constructor for [ParserError]
         *
         * @param details the details of the [ParserError]
         * @param start the start position of the [ParserError]
         * @param end the end position of the [ParserError]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        constructor(details: String, start: Position, end: Position) : this("ParserError", details, start, end)


        /**
         * Constructor for [ParserError]
         *
         * @param details the details of the [ParserError]
         * @param start the start position of the [ParserError]
         * @param end the end position of the [ParserError]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        constructor(details: String, start: Int, end: Int) : this(
            "ParserError",
            details,
            tokens.map.resolve(start),
            tokens.map.resolve(end)
        )


        /**
         * Constructor for [ParserError]
         *
         * @param error the [ParserError] message
         * @param position the position of the [ParserError]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        constructor(error: String, position: Int) : this(
            error,
            tokens.map.resolve(tokens[position].start),
            tokens.map.resolve(tokens[position].end)
        )


        /**
         * Constructor for [ParserError]
         *
         * @param error the error message
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        constructor(error: String) : this(
            error,
            tokens.map.resolve(tokens.peek().start),
            tokens.map.resolve(tokens.peek().end)
        )
    }

}