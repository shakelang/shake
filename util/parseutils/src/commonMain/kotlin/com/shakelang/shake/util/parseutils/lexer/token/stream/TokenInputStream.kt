package com.shakelang.shake.util.parseutils.lexer.token.stream

import com.shakelang.shake.util.parseutils.characters.position.PositionMap
import com.shakelang.shake.util.parseutils.lexer.token.Token
import com.shakelang.shake.util.parseutils.lexer.token.TokenType

/**
 * A [TokenInputStream] provides the [Token]s for a Parser. It is
 * created by a lexer
 *
 * @since 0.1.0
 * @version 0.2.1
 */
@Suppress("unused")
interface TokenInputStream<TT : TokenType, T : Token<TT>> {

    /**
     * The source (mostly filename) of the [TokenInputStream]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val source: String

    /**
     * The map for the token-positions
     * We have this map to resolve the column / line of an index. This is useful for error-generation.
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val map: PositionMap

    /**
     * The position that the TokenInputStream is actually at
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val position: Int

    /**
     * The size of the TokenInputStream
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val size: Int

    /**
     * Checks if the [TokenInputStream] has left a given number of tokens
     * @param num the number of tokens to check
     * @return has the [TokenInputStream] left the given amount of [Token]s?
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun has(num: Int): Boolean

    /**
     * Checks if the [TokenInputStream] has a token left
     * @return has the [TokenInputStream] another [Token] left?
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    operator fun hasNext(): Boolean = has(1)

    /**
     * Returns the next token of the [TokenInputStream] (and skips)
     * @return the next token
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    operator fun next(): T {
        // skip to next token and then return the actual token
        skip()
        return actual
    }

    /**
     * Returns the type of the next token of the [TokenInputStream] (and skips)
     * @return the next token
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun nextType(): TT {
        // skip to next token and then return the actual token
        skip()
        return actualType
    }

    /**
     * Returns the next token of the [TokenInputStream]
     * @return the next token
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun nextValue(): String? {
        // skip to next token and then return the actual token
        skip()
        return actualValue
    }

    /**
     * Skips the next token
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun skip()

    /**
     * Skips a number of tokens
     * @param amount the number of tokens to skip
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun skip(amount: Int)

    /**
     * Returns the actual [Token]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val actual: T

    /**
     * Returns the type of the actual token
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val actualType
        get() = actual.type

    /**
     * Returns the start of the actual token
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val actualStart
        get() = actual.start

    /**
     * Returns the end of the actual token
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val actualEnd
        get() = actual.end

    /**
     * Returns the value of the actual
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val actualValue
        get() = actual.value

    /**
     * Checks if the actual token without changing the actual token
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val actualHasValue
        get() = actualValue != null

    /**
     * Returns the next [Token]
     * @return The next [Token]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun peek() = peek(1)

    /**
     * Returns the type of the next token without changing the actual token
     * @return The next token-type
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun peekType() = peek().type

    /**
     * Returns the start of the next token without changing the actual token
     * @return The next token-start
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun peekStart() = peek().start

    /**
     * Returns the end of the next token without changing the actual token
     * @return The next token-end
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun peekEnd() = peek().end

    /**
     * Returns the value of the next token without changing the actual token
     * @return The next token-value
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun peekValue() = peek().value

    /**
     * Checks if the next token of the [TokenInputStream] has a value without skipping
     * @return The next [Token]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun peekHasValue() = peekValue() != null

    /**
     * Peek the token at the given index
     * @param offset the offset of the token
     * @return the token at the given index
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun peek(offset: Int): T

    /**
     * Peek the token type at the given index
     * @param offset the offset of the token
     * @return the token-type at the given index
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun peekType(offset: Int) = peek(offset).type

    /**
     * Peek the token start at the given index
     * @param offset the offset of the token
     * @return the token-start at the given index
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun peekStart(offset: Int) = peek(offset).start

    /**
     * Peek the token end at the given index
     * @param offset the offset of the token
     * @return the token-end at the given index
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun peekEnd(offset: Int) = peek(offset).end

    /**
     * Peek the token value at the given index
     * @param offset the offset of the token
     * @return the token-value at the given index
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun peekValue(offset: Int) = peek(offset).value

    /**
     * Checks if the token at the given offset of the [TokenInputStream] has a value without changing the actual token
     * @param offset the offset of the token
     * @return The next [Token]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun peekHasValue(offset: Int) = peekValue(offset) != null
}
