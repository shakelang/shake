package com.shakelang.util.parseutils.lexer.token.stream

import com.shakelang.util.parseutils.characters.position.PositionMap
import com.shakelang.util.parseutils.lexer.LexingBase
import com.shakelang.util.parseutils.lexer.token.Token
import com.shakelang.util.parseutils.lexer.token.TokenType

/**
 * A [TokenInputStream] that generates tokens on demand (from a [LexingBase])
 *
 * @since 0.1.0
 * @version 0.2.1
 */
open class OnDemandLexingTokenInputStream<TT : TokenType, T : Token<TT>>(

    /**
     * The [LexingBase] that should be used to generate the tokens
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    private val lexingBase: LexingBase<TT, T>,
) : LexingBase<TT, T>(lexingBase.input), TokenInputStream<TT, T> {

    /**
     * The size of the [TokenInputStream]
     *
     * @since 0.1.0
     * @version 0.2.1
     *
     * @throws UnsupportedOperationException always
     * @deprecated this is not supported (because the size is not known)
     */
    override val size: Int get() = throw UnsupportedOperationException()

    /**
     * The buffer of the [OnDemandLexingTokenInputStream]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val buffer: MutableList<T> = mutableListOf()

    /**
     * The input of the [OnDemandLexingTokenInputStream]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override lateinit var actual: T

    /**
     * The input of the [OnDemandLexingTokenInputStream]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override val source: String
        get() = input.source.location

    /**
     * The [PositionMap] of the [OnDemandLexingTokenInputStream]
     * (In this case it's a position maker, because the position map will be generated on demand)
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override val map: PositionMap
        get() = input.positionMaker

    /**
     * The position of the [OnDemandLexingTokenInputStream]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override var position: Int = -1

    /**
     * Check if the [TokenInputStream] has a requested amount of tokens
     * (This will try to generate the requested amount of tokens and fill the buffer, as
     * this is the only way to check if the [TokenInputStream] has the requested amount of tokens)
     * @param num The amount of tokens that should be checked
     * @return If the [TokenInputStream] has the requested amount of tokens
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override fun has(num: Int): Boolean {
        return try {
            fillBuffer(num)
            true
        } catch (e: IndexOutOfBoundsException) {
            false
        } catch (e: IllegalStateException) {
            false
        }
    }

    /**
     * Skip one token
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override fun skip() {
        try {
            fillBuffer(1)
        } catch (e: IndexOutOfBoundsException) {
            throw Error("Input already finished", e)
        } catch (e: IllegalStateException) {
            throw Error("Input already finished", e)
        }

        position++
        actual = buffer.removeAt(0)
    }

    /**
     * Skip a requested amount of tokens
     * @param amount The amount of tokens that should be skipped
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override fun skip(amount: Int) {
        for (i in 0 until amount) skip()
    }

    /**
     * Peek the next token
     * @return The next token
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override fun peek(offset: Int): T {
        try {
            fillBuffer(offset)
        } catch (e: IndexOutOfBoundsException) {
            throw Error("Not enough tokens left", e)
        } catch (e: IllegalStateException) {
            throw Error("Not enough tokens left", e)
        }
        return buffer[offset - 1]
    }

    /**
     * Generate one token and add it to the buffer
     * @return If the token was generated
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    private fun generateToken(): Boolean {
        if (!this.input.hasNext()) return false
        buffer.add(this.makeToken())
        return true
    }

    /**
     * Fill the buffer with a requested amount of tokens
     * @param minAmount The amount of tokens that should be in the buffer
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    private fun fillBuffer(minAmount: Int) {
        while (buffer.size < minAmount) {
            if (!generateToken()) {
                throw IllegalStateException("Not enough tokens left (${buffer.size}/$minAmount)")
            }
        }
    }

    /**
     * Make a token
     * @return The generated token
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override fun makeToken(): T {
        return lexingBase.makeToken()
    }

    /**
     * Get string representation of the [OnDemandLexingTokenInputStream]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override fun toString(): String = "OnDemandLexingTokenInputStream(lexer=$lexingBase)"
}
