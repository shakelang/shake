package io.github.shakelang.shake.lexer.token.stream

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.lexer.token.ShasPToken
import io.github.shakelang.shake.shasambly.shasp.token.ShasPTokenType

/**
 * A [TokenBasedShasPTokenInputStream] provides the [ShasPToken]s for a Parser. It is
 * created by the [io.github.shakelang.shake.lexer.ShasPLexer]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
class TokenBasedShasPTokenInputStream
(
    override val source: String,

    /**
     * The tokenTypes that are contained in the [TokenBasedShasPTokenInputStream]
     */
    val tokens: Array<ShasPToken>,
    override val map: PositionMap

) : ShasPTokenInputStream {

    /**
     * Get a specific token from the [TokenBasedShasPTokenInputStream]
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    operator fun get(position: Int): ShasPToken {
        return tokens[position]
    }

    /**
     * Get the type of specific token from the [TokenBasedShasPTokenInputStream] by its position
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun getType(position: Int): ShasPTokenType {
        return this[position].type
    }

    /**
     * Get the start of specific token from the [TokenBasedShasPTokenInputStream] by its position
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    @Suppress("deprecation")
    fun getStart(position: Int): Int {
        return this[position].start
    }

    /**
     * Get the end of specific token from the [TokenBasedShasPTokenInputStream] by it's position
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun getEnd(position: Int): Int {
        return this[position].end
    }

    /**
     * Get the value of specific token from the [TokenBasedShasPTokenInputStream] by it's position
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun getValue(position: Int): String? {
        return this[position].value
    }

    /**
     * Check if specific token from the [TokenBasedShasPTokenInputStream] has a value (by it's position)
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun getHasValue(position: Int): Boolean {
        return this[position].value != null
    }

    override var position: Int = -1

    override fun has(num: Int): Boolean {
        return position + num < tokens.size
    }

    override fun next(): ShasPToken {
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

    override fun peek(): ShasPToken {
        if(position + 1 >= tokens.size) throw Error("Not enough tokens left")
        return tokens[position + 1]
    }

    override fun peek(offset: Int): ShasPToken {
        if(position + offset >= tokens.size) throw Error("Not enough tokens left")
        return tokens[position + offset]
    }

    override val actual: ShasPToken
        get() = tokens[position]

    fun reset() {
        position = 0
    }

    override fun toString(): String {
        return "TokenBasedTokenInputStream(source='$source', tokens=${tokens.size}, position=$position)"
    }

}