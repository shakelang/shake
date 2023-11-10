package io.github.shakelang.shake.util.parseutils.lexer.token.stream

import io.github.shakelang.shake.lexer.token.Token
import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.util.parseutils.lexer.token.TokenType

/**
 * A [TokenInputStream] provides the [Token]s for a Parser. It is
 * created by a lexer
 */
@Suppress("unused")
interface TokenInputStream<TT : TokenType, T : Token<TT>> {

    /**
     * The source (mostly filename) of the [TokenInputStream]
     */
    val source: String

    /**
     * The map for the token-positions
     * We have this map to resolve the column / line of an index. This is useful for error-generation.
     */
    val map: PositionMap

    /**
     * The position that the TokenInputStream is actually at
     */
    val position: Int

    /**
     * The size of the TokenInputStream
     */
    val size: Int

    /**
     * Checks if the [TokenInputStream] has left a given number of tokens
     *
     * @param num the number of tokens to check
     * @return has the [TokenInputStream] left the given amount of [Token]s?
     */
    fun has(num: Int): Boolean

    /**
     * Checks if the [TokenInputStream] has a token left
     *
     * @return has the [TokenInputStream] another [Token] left?
     */
    operator fun hasNext(): Boolean = has(1)

    /**
     * Returns the next token of the [TokenInputStream] (and skips)
     *
     * @return the next token
     */
    operator fun next(): T {
        // skip to next token and then return the actual token
        skip()
        return actual
    }

    /**
     * Returns the type of the next token of the [TokenInputStream] (and skips)
     *
     * @return the next token
     */
    fun nextType(): TT {
        // skip to next token and then return the actual token
        skip()
        return actualType
    }

    /**
     * Returns the next token of the [TokenInputStream]
     *
     * @return the next token
     */
    fun nextValue(): String? {
        // skip to next token and then return the actual token
        skip()
        return actualValue
    }

    /**
     * Skips the next token
     */
    fun skip()

    /**
     * Skips a number of tokens
     */
    fun skip(amount: Int)

    /**
     * Returns the actual [Token]
     *
     * @return The actual [Token]
     */
    val actual: T

    /**
     * Returns the type of the actual token
     *
     * @return The actual token-type
     */
    val actualType: TT
        get() {
            return actual.type
        }

    /**
     * Returns the start of the actual token
     *
     * @return The actual token-start
     */
    val actualStart: Int
        get() {
            return actual.start
        }

    /**
     * Returns the end of the actual token
     *
     * @return The actual token-end
     */
    val actualEnd: Int
        get() {
            return actual.end
        }

    /**
     * Returns the value of the actual
     *
     * @return The actual token-value
     */
    val actualValue: String?
        get() {
            return actual.value
        }

    /**
     * Checks if the actual token without changing the actual token
     *
     * @return Has the actual token a value?
     */
    val actualHasValue: Boolean
        get() {
            return actualValue != null
        }

    /**
     * Returns the next [Token]
     *
     * @return The next [Token]
     */
    fun peek(): T {
        return peek(1)
    }

    /**
     * Returns the type of the next token without changing the actual token
     *
     * @return The next token-type
     */
    fun peekType(): TT {
        return peek().type
    }

    /**
     * Returns the start of the next token without changing the actual token
     *
     * @return The next token-start
     */
    fun peekStart(): Int {
        return peek().start
    }

    /**
     * Returns the end of the next token without changing the actual token
     *
     * @return The next token-end
     */
    fun peekEnd(): Int {
        return peek().end
    }

    /**
     * Returns the value of the next token without changing the actual token
     *
     * @return The next token-value
     */
    fun peekValue(): String? {
        return peek().value
    }

    /**
     * Checks if the next token of the [TokenInputStream] has a value without skipping
     *
     * @return The next [Token]
     */
    fun peekHasValue(): Boolean {
        return peekValue() != null
    }

    /**
     * Peek the token at the given index
     */
    fun peek(offset: Int): T

    /**
     * Peek the token type at the given index
     */
    fun peekType(offset: Int): TT {
        return peek(offset).type
    }

    /**
     * Peek the token start at the given index
     */
    fun peekStart(offset: Int): Int {
        return peek(offset).start
    }

    /**
     * Peek the token end at the given index
     */
    fun peekEnd(offset: Int): Int {
        return peek(offset).end
    }

    /**
     * Peek the token value at the given index
     */
    fun peekValue(offset: Int): String? {
        return peek(offset).value
    }

    /**
     * Checks if the token at the given offset of the [TokenInputStream] has a value without changing the actual token
     */
    fun peekHasValue(offset: Int): Boolean {
        return peekValue(offset) != null
    }
}
