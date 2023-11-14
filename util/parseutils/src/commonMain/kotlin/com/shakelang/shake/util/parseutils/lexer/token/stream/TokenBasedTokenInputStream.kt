package com.shakelang.shake.util.parseutils.lexer.token.stream

import com.shakelang.shake.util.parseutils.lexer.token.Token
import com.shakelang.shake.util.parseutils.characters.position.PositionMap
import com.shakelang.shake.util.parseutils.lexer.token.TokenType

/**
 * A [TokenBasedTokenInputStream] provides the [Token]s for a Parser. It is
 * created by a lexer
 * @param TT the [TokenType] of the [Token]s
 * @param T the [Token]s
 * 
 * @param tokens the [Token]s that are contained in the [TokenBasedTokenInputStream]
 * @param map the [PositionMap] of the [Token]s
 * 
 * @since 0.1.0
 * @version 0.2.1
 */
@Suppress("unused")
open class TokenBasedTokenInputStream<TT : TokenType, T : Token<TT>>(

    /**
     * The tokenTypes that are contained in the [TokenBasedTokenInputStream]
     * 
     * @since 0.1.0
     * @version 0.2.1
     */
    open val tokens: Array<T>,
    
    /**
     * The [PositionMap] of the [Token]s
     * 
     * @since 0.1.0
     * @version 0.2.1
     */
    override val map: PositionMap

) : TokenInputStream<TT, T> {

    /**
     * The source of the [TokenBasedTokenInputStream]
     * 
     * @since 0.1.0
     * @version 0.2.1
     */
    override val source get() = map.source.location
    
    /**
     * The size of the [TokenBasedTokenInputStream]
     * 
     * @since 0.1.0
     * @version 0.2.1
     */
    override val size: Int get() = tokens.size

    /**
     * Get a specific token from the [TokenBasedTokenInputStream]
     * @param position the position to get
     * @return the token at the given position
     *
     * @throws Error if the position is invalid
     * @since 0.1.0
     * @version 0.2.1
     */
    open operator fun get(position: Int): T {
        if (position < 0 || position >= tokens.size) throw Error("Invalid position")
        return tokens[position]
    }

    /**
     * Get the type of specific token from the [TokenBasedTokenInputStream] by its position
     * @param position the position to get
     * @return the token at the given position
     *
     * @throws Error if the position is invalid
     * @since 0.1.0
     * @version 0.2.1
     */
    open fun getType(position: Int): TT {
        return this[position].type
    }

    /**
     * Get the start of specific token from the [TokenBasedTokenInputStream] by its position
     * @param position the position to get
     * @return the token at the given position
     *
     * @throws Error if the position is invalid
     * @since 0.1.0
     * @version 0.2.1
     */
    open fun getStart(position: Int): Int {
        return this[position].start
    }

    /**
     * Get the end of specific token from the [TokenBasedTokenInputStream] by it's position
     * @param position the position to get
     * @return the token at the given position
     *
     * @throws Error if the position is invalid
     * @since 0.1.0
     * @version 0.2.1
     */
    open fun getEnd(position: Int): Int {
        return this[position].end
    }

    /**
     * Get the value of specific token from the [TokenBasedTokenInputStream] by it's position
     * @param position the position to get
     * @return the token at the given position
     *
     * @throws Error if the position is invalid
     * @since 0.1.0
     * @version 0.2.1
     */
    open fun getValue(position: Int): String? {
        return this[position].value
    }

    /**
     * Check if specific token from the [TokenBasedTokenInputStream] has a value (by it's position)
     * @param position the position to get
     * @return the token at the given position
     *
     * @throws Error if the position is invalid
     * @since 0.1.0
     * @version 0.2.1
     */
    open fun getHasValue(position: Int): Boolean {
        return this[position].value != null
    }

    /**
     * The position of the [TokenBasedTokenInputStream]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override var position: Int = -1

    /**
     * Check if the [TokenBasedTokenInputStream] has a specific amount of [Token]s left
     * @param num the amount of [Token]s to check
     * @return if the [TokenBasedTokenInputStream] has a specific amount of [Token]s left
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override fun has(num: Int): Boolean {
        return position + num < tokens.size
    }

    /**
     * Get the next [Token] from the [TokenBasedTokenInputStream]
     * @return the next [Token]
     *
     * @throws Error if there are no [Token]s left
     * @since 0.1.0
     * @version 0.2.1
     */
    override fun next(): T {
        if (!hasNext()) throw Error("Not enough tokens left")
        return tokens[++position]
    }

    /**
     * Skip the next [Token] from the [TokenBasedTokenInputStream]
     * @throws Error if there are no [Token]s left
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override fun skip() {
        if (!hasNext()) throw Error("Not enough tokens left")
        position++
    }

    /**
     * Skip a specific amount of [Token]s from the [TokenBasedTokenInputStream]
     * @param amount the amount of [Token]s to skip
     *
     * @throws Error if the amount is smaller than 1
     * @throws Error if there are not enough [Token]s left
     * @since 0.1.0
     * @version 0.2.1
     */
    override fun skip(amount: Int) {
        if (amount < 1) throw Error("Amount must be greater than 0")
        if (!has(amount)) throw Error("Not enough tokens left")
        position += amount
    }

    /**
     * Check if the [TokenBasedTokenInputStream] has a next [Token]
     * @return if the [TokenBasedTokenInputStream] has a next [Token]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override fun hasNext(): Boolean {
        return position + 1 < tokens.size
    }

    /**
     * Get the next [Token] from the [TokenBasedTokenInputStream] without increasing the position
     * @return the next [Token]
     *
     * @throws Error if there are no [Token]s left
     * @since 0.1.0
     * @version 0.2.1
     */
    override fun peek(): T {
        if (position + 1 >= tokens.size) throw Error("Not enough tokens left")
        return tokens[position + 1]
    }

    /**
     * Get a specific [Token] from the [TokenBasedTokenInputStream] without increasing the position
     * @param offset the offset of the [Token] to get
     * @return the [Token] at the given offset
     *
     * @throws Error if the offset is smaller than 1
     * @throws Error if there are not enough [Token]s left
     * @since 0.1.0
     * @version 0.2.1
     */
    override fun peek(offset: Int): T {
        if (offset < 1) throw Error("Offset must be greater than 0")
        if (position + offset >= tokens.size) throw Error("Not enough tokens left")
        return tokens[position + offset]
    }

    /**
     * Get the actual [Token] from the [TokenBasedTokenInputStream] without increasing the position
     * @return the next [Token]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override val actual: T
        get() = tokens[position]

    /**
     * Reset the [TokenBasedTokenInputStream] to the start
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun reset() {
        position = 0
    }

    /**
     * Return the [TokenBasedTokenInputStream] as a [String]
     * @return the [TokenBasedTokenInputStream] as a [String]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override fun toString(): String {
        return "TokenBasedTokenInputStream(source='$source', tokens=${tokens.size}, position=$position)"
    }
}
