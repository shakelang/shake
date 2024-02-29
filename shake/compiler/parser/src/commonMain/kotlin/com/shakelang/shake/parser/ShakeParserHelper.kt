package com.shakelang.shake.parser

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.lexer.token.stream.ShakeTokenInputStream
import com.shakelang.util.parseutils.CompilerError
import com.shakelang.util.parseutils.characters.position.Position
import com.shakelang.util.shason.json

/**
 * A helper class for the [ShakeParser] class.
 * This class provides some useful functions for parsing.
 * The default implementation of this class is [ShakeParserImpl].
 * Create a ShakeParser using [ShakeParser.from]
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
abstract class ShakeParserHelper(

    /**
     * The [PositionMap] of the [input]. It is directly taken from the [input], because [ShakeTokenInputStream]
     * already provides a [PositionMap] implementation.
     */
    override val input: ShakeTokenInputStream,
) : ShakeParser() {

    /**
     * Assert a value to not be null, if it is null a [ParserError] is thrown
     * @param value The value to be checked
     * @return The value if it is not null
     * @throws ParserError if the value is null
     */
    protected fun <T> expectNotNull(value: T?): T = value ?: throw ParserError("Value is null")

    // ****************************************************************************

    /**
     * Consumes the next token and returns it if it is of the given type, throws a [ParserError] otherwise
     * @param type The type of the token to be consumed
     * @param message The message of the error if the token is not of the given type
     * @param skipIgnorable If ignorable tokens should be skipped
     * @return The consumed token
     * @throws ParserError If the next token is not of the given type
     */
    protected fun expectToken(type: ShakeTokenType, message: String, skipIgnorable: Boolean = true): ShakeToken {
        if (skipIgnorable) input.skipIgnorable()
        if (!input.hasNext()) throw ParserError(message)
        val next = input.next()
        if (next.type != type) throw ParserError(message)
        return next
    }

    /**
     * Consumes the next token and returns it if it is of the given type, throws a [ParserError] otherwise
     * @param type The type of the token to be consumed
     * @param skipIgnorable If ignorable tokens should be skipped
     * @return The consumed token
     * @throws ParserError If the next token is not of the given type
     */
    protected fun expectToken(type: ShakeTokenType, skipIgnorable: Boolean = true): ShakeToken {
        return expectToken(type, "Expecting ${type.simpleValue}", skipIgnorable)
    }

    /**
     * Consumes the next token and returns it if it is of one of the given types, throws a [ParserError] otherwise
     * @param types The types of the token to be consumed
     * @param message The message of the error if the token is not of the given type
     * @param skipIgnorable If ignorable tokens should be skipped
     * @return The consumed token
     * @throws ParserError If the next token is not of the given type
     */
    protected fun expectToken(types: List<ShakeTokenType>, message: String, skipIgnorable: Boolean = true): ShakeToken {
        if (skipIgnorable) input.skipIgnorable()
        if (!input.hasNext() || input.peekType() !in types) throw ParserError(message)
        return input.next()
    }

    /**
     * Consumes the next token and returns it if it is of one of the given types, throws a [ParserError] otherwise
     * @param types The types of the token to be consumed
     * @param skipIgnorable If ignorable tokens should be skipped
     * @return The consumed token
     * @throws ParserError If the next token is not of the given type
     */
    protected fun expectToken(types: List<ShakeTokenType>, skipIgnorable: Boolean = true): ShakeToken {
        return expectToken(types, "Expecting one of [${types.joinToString(", "){ json.stringify(it) }}]", skipIgnorable)
    }

    /**
     * Skips ignorable tokens and checks whether the next token is of the given type
     * @param type The type of the token to be checked
     * @param skipIgnorable If ignorable tokens should be skipped
     * @return Whether the next token is of the given type
     */
    protected fun nextToken(type: ShakeTokenType, skipIgnorable: Boolean = true): Boolean {
        if (skipIgnorable) input.skipIgnorable()
        return input.hasNext() && input.peekType() == type
    }

    /**
     * Skips ignorable tokens and checks whether the next token is of one of the given types
     * @param types The types of the token to be checked
     * @param skipIgnorable If ignorable tokens should be skipped
     * @return Whether the next token is of one of the given types
     */
    protected fun nextToken(types: List<ShakeTokenType>, skipIgnorable: Boolean = true): Boolean {
        if (skipIgnorable) input.skipIgnorable()
        return input.hasNext() && input.peekType() in types
    }

    // ****************************************************************************
    // Errors

    /**
     * A class representing a parser error
     * @param message The message of the error
     * @param name The name of the error
     * @param details The details of the error
     * @param start The start position of the error
     * @param end The end position of the error
     */
    inner class ParserError(message: String?, name: String?, details: String?, start: Position?, end: Position?) :
        CompilerError(
            message!!,
            name!!,
            details!!,
            start!!,
            end!!,
        ) {

        /**
         * A class representing a parser error
         * @param name The name of the error
         * @param details The details of the error
         * @param start The start position of the error
         * @param end The end position of the error
         */
        constructor(
            name: String,
            details: String,
            start: Position,
            end: Position?,
        ) : this(
            "Error occurred in parser: $name, $details in <${start.source.location}>:${start.line}:${start.column}",
            name,
            details,
            start,
            end,
        )

        /**
         * A class representing a parser error
         * @param details The details of the error
         * @param start The start position of the error
         * @param end The end position of the error
         */
        constructor(details: String, start: Position, end: Position?) : this("ParserError", details, start, end)

        /**
         * A class representing a parser error
         * @param details The details of the error
         */
        constructor(details: String, start: Int, end: Int) : this(
            "ParserError",
            details,
            input.map.resolve(start),
            input.map.resolve(end),
        )

        constructor(error: String) : this(
            error,
            input.map.resolve(input.actualStart),
            input.map.resolve(input.actualEnd),
        )
    }
}
