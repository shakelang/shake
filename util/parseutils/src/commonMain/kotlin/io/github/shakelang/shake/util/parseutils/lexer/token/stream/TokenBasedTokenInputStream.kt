package io.github.shakelang.shake.util.parseutils.lexer.token.stream

import io.github.shakelang.shake.lexer.token.Token
import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.util.parseutils.lexer.token.TokenType

/**
 * A [TokenBasedTokenInputStream] provides the [Token]s for a Parser. It is
 * created by a lexer
 */
@Suppress("unused")
open class TokenBasedTokenInputStream<TT : TokenType, T : Token<TT>>
    (

    /**
     * The tokenTypes that are contained in the [TokenBasedTokenInputStream]
     */
    open val tokens: Array<T>,
    override val map: PositionMap

) : TokenInputStream<TT, T> {

    override val source get() = map.source.location
    override val size: Int get() = tokens.size

    /**
     * Get a specific token from the [DataBasedTokenInputStream]
     *
     * @param position the position to get
     * @return the token at the given position
     */
    open operator fun get(position: Int): T {
        if (position < 0 || position >= tokens.size) throw Error("Invalid position")
        return tokens[position]
    }

    /**
     * Get the type of specific token from the [DataBasedTokenInputStream] by its position
     *
     * @param position the position to get
     * @return the token at the given position
     */
    open fun getType(position: Int): TT {
        return this[position].type
    }

    /**
     * Get the start of specific token from the [DataBasedTokenInputStream] by its position
     *
     * @param position the position to get
     * @return the token at the given position
     */
    open fun getStart(position: Int): Int {
        return this[position].start
    }

    /**
     * Get the end of specific token from the [DataBasedTokenInputStream] by it's position
     *
     * @param position the position to get
     * @return the token at the given position
     */
    open fun getEnd(position: Int): Int {
        return this[position].end
    }

    /**
     * Get the value of specific token from the [DataBasedTokenInputStream] by it's position
     *
     * @param position the position to get
     * @return the token at the given position
     */
    open fun getValue(position: Int): String? {
        return this[position].value
    }

    /**
     * Check if specific token from the [DataBasedTokenInputStream] has a value (by it's position)
     *
     * @param position the position to get
     * @return the token at the given position
     */
    open fun getHasValue(position: Int): Boolean {
        return this[position].value != null
    }

    override var position: Int = -1

    override fun has(num: Int): Boolean {
        return position + num < tokens.size
    }

    override fun next(): T {
        if (!hasNext()) throw Error("Not enough tokens left")
        return tokens[++position]
    }

    override fun skip() {
        if (!hasNext()) throw Error("Not enough tokens left")
        position++
    }

    override fun skip(amount: Int) {
        if (amount < 1) throw Error("Amount must be greater than 0")
        if (!has(amount)) throw Error("Not enough tokens left")
        position += amount
    }

    override fun hasNext(): Boolean {
        return position + 1 < tokens.size
    }

    override fun peek(): T {
        if (position + 1 >= tokens.size) throw Error("Not enough tokens left")
        return tokens[position + 1]
    }

    override fun peek(offset: Int): T {
        if (offset < 1) throw Error("Offset must be greater than 0")
        if (position + offset >= tokens.size) throw Error("Not enough tokens left")
        return tokens[position + offset]
    }

    override val actual: T
        get() = tokens[position]

    fun reset() {
        position = 0
    }

    override fun toString(): String {
        return "TokenBasedTokenInputStream(source='$source', tokens=${tokens.size}, position=$position)"
    }

}