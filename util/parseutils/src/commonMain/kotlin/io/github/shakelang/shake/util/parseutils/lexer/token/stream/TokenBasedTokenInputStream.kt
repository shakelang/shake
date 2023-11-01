package io.github.shakelang.shake.util.parseutils.lexer.token.stream

import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.util.parseutils.lexer.token.TokenType
import io.github.shakelang.shake.lexer.token.Token

/**
 * A [TokenBasedTokenInputStream] provides the [Token]s for a Parser. It is
 * created by a lexer
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open operator fun get(position: Int): T {
        if(position < 0 || position >= tokens.size) throw Error("Invalid position")
        return tokens[position]
    }

    /**
     * Get the type of specific token from the [DataBasedTokenInputStream] by its position
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open fun getType(position: Int): TT {
        return this[position].type
    }

    /**
     * Get the start of specific token from the [DataBasedTokenInputStream] by its position
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open fun getStart(position: Int): Int {
        return this[position].start
    }

    /**
     * Get the end of specific token from the [DataBasedTokenInputStream] by it's position
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open fun getEnd(position: Int): Int {
        return this[position].end
    }

    /**
     * Get the value of specific token from the [DataBasedTokenInputStream] by it's position
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open fun getValue(position: Int): String? {
        return this[position].value
    }

    /**
     * Check if specific token from the [DataBasedTokenInputStream] has a value (by it's position)
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open fun getHasValue(position: Int): Boolean {
        return this[position].value != null
    }

    override var position: Int = -1

    override fun has(num: Int): Boolean {
        return position + num < tokens.size
    }

    override fun next(): T {
        if(!hasNext()) throw Error("Not enough tokens left")
        return tokens[++position]
    }

    override fun skip() {
        if(!hasNext()) throw Error("Input already finished")
        position++
    }

    override fun skip(amount: Int) {
        position += amount
    }

    override fun hasNext(): Boolean {
        return position + 1 < tokens.size
    }

    override fun peek(): T {
        if(position + 1 >= tokens.size) throw Error("Not enough tokens left")
        return tokens[position + 1]
    }

    override fun peek(offset: Int): T {
        if(position + offset >= tokens.size) throw Error("Not enough tokens left")
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