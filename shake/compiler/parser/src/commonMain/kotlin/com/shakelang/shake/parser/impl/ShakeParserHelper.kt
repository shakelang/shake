package com.shakelang.shake.parser.impl

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.lexer.token.stream.ShakeTokenInputStream
import com.shakelang.shake.parser.ShakeParser
import com.shakelang.util.parseutils.characters.position.PositionMap
import com.shakelang.util.shason.json

/**
 * A helper class for the [ShakeParser] class.
 * This class provides some useful functions for parsing.
 * The default implementation of this class is [ShakeParserImpl].
 * Create a ShakeParser using [ShakeParser.from]
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
abstract class ShakeParserHelper(
    input: ShakeTokenInputStream,
) : ShakeParser(input) {

    /**
     * The [PositionMap] of the [input]. It is directly taken from the [input], because [ShakeTokenInputStream]
     * already provides a [PositionMap] implementation.
     */
    override val map: PositionMap get() = input.map

    /**
     * Assert a value to not be null, if it is null an error is thrown
     * @param value The value to be checked
     * @return The value if it is not null
     * @throws com.shakelang.util.parseutils.parser.AbstractParser.ParserError if the value is null
     */
    protected fun <T> expectNotNull(value: T?): T = value ?: throw errorFactory.createErrorAtCurrent("Value is null")

    // ****************************************************************************

    /**
     * Consumes the next token and returns it if it is of the given type, throws an error otherwise
     * @param type The type of the token to be consumed
     * @param message The message of the error if the token is not of the given type
     * @param skipIgnorable If ignorable tokens should be skipped
     * @return The consumed token
     * @throws com.shakelang.util.parseutils.parser.AbstractParser.ParserError If the next token is not of the given type
     */
    protected fun expectToken(type: ShakeTokenType, message: String, skipIgnorable: Boolean = true): ShakeToken {
        if (skipIgnorable) input.skipIgnorable()
        if (!input.hasNext()) throw errorFactory.createErrorAtCurrent(message)
        val next = input.next()
        if (next.type != type) throw errorFactory.createErrorAtCurrent(message)
        return next
    }

    /**
     * Consumes the next token and returns it if it is of the given type, throws an error otherwise
     * @param type The type of the token to be consumed
     * @param skipIgnorable If ignorable tokens should be skipped
     * @return The consumed token
     * @throws com.shakelang.util.parseutils.parser.AbstractParser.ParserError If the next token is not of the given type
     */
    protected fun expectToken(type: ShakeTokenType, skipIgnorable: Boolean = true): ShakeToken {
        return expectToken(type, "Expecting ${type.simpleValue}", skipIgnorable)
    }

    /**
     * Consumes the next token and returns it if it is of one of the given types, throws an error otherwise
     * @param types The types of the token to be consumed
     * @param message The message of the error if the token is not of the given type
     * @param skipIgnorable If ignorable tokens should be skipped
     * @return The consumed token
     * @throws com.shakelang.util.parseutils.parser.AbstractParser.ParserError If the next token is not of the given type
     */
    protected fun expectToken(types: List<ShakeTokenType>, message: String, skipIgnorable: Boolean = true): ShakeToken {
        if (skipIgnorable) input.skipIgnorable()
        if (!input.hasNext() || input.peek().type !in types) throw errorFactory.createErrorAtCurrent(message)
        return input.next()
    }

    /**
     * Consumes the next token and returns it if it is of one of the given types, throws an error otherwise
     * @param types The types of the token to be consumed
     * @param skipIgnorable If ignorable tokens should be skipped
     * @return The consumed token
     * @throws com.shakelang.util.parseutils.parser.AbstractParser.ParserError If the next token is not of the given type
     */
    protected fun expectToken(types: List<ShakeTokenType>, skipIgnorable: Boolean = true): ShakeToken {
        return expectToken(types, "Expecting one of [${types.joinToString(", ") { json.stringify(it) }}]", skipIgnorable)
    }

    /**
     * Skips ignorable tokens and checks whether the next token is of the given type
     * @param type The type of the token to be checked
     * @param skipIgnorable If ignorable tokens should be skipped
     * @return Whether the next token is of the given type
     */
    protected fun nextToken(type: ShakeTokenType, skipIgnorable: Boolean = true): Boolean {
        if (skipIgnorable) input.skipIgnorable()
        return input.hasNext() && input.peek().type == type
    }

    /**
     * Skips ignorable tokens and checks whether the next token is of one of the given types
     * @param types The types of the token to be checked
     * @param skipIgnorable If ignorable tokens should be skipped
     * @return Whether the next token is of one of the given types
     */
    protected fun nextToken(types: List<ShakeTokenType>, skipIgnorable: Boolean = true): Boolean {
        if (skipIgnorable) input.skipIgnorable()
        return input.hasNext() && input.peek().type in types
    }
}
