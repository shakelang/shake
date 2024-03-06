package com.shakelang.util.parseutils.lexer.token.stream

import com.shakelang.util.io.streaming.general.PeekableStream
import com.shakelang.util.parseutils.characters.position.PositionMap
import com.shakelang.util.parseutils.lexer.token.Token
import com.shakelang.util.parseutils.lexer.token.TokenType

/**
 * A [TokenInputStream] provides the [Token]s for a Parser. It is
 * created by a lexer
 *
 * @since 0.1.0
 * @version 0.2.1
 */
@Suppress("unused")
interface TokenInputStream<Self : TokenInputStream<Self, TT, T>, TT : TokenType, T : Token<*, TT, *>> : PeekableStream<T> {

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
}
