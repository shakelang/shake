package io.github.shakelang.parseutils.lexer.token.stream

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.parseutils.lexer.token.TokenType
import io.github.shakelang.shake.lexer.token.Token

/**
 * A [DataBasedTokenInputStream] provides the [Token]s for a Parser. It is
 * created by a lexer
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
open class DataBasedTokenInputStream<TT: TokenType, T: Token<TT>>

/**
 * Create a [DataBasedTokenInputStream] giving the [DataBasedTokenInputStream.source] and [DataBasedTokenInputStream.tokenTypes]
 *
 * @param source value for field [DataBasedTokenInputStream.source] (The source (mostly file) of the tokens)
 * @param tokenTypes value for field [DataBasedTokenInputStream.tokenTypes] (The tokens that the [DataBasedTokenInputStream] should give)
 * @param values The values for the tokens that have values in the [DataBasedTokenInputStream]
 * @param positions The positions of the tokens of the [DataBasedTokenInputStream]
 * (We just store the end-indexes of each token. We can calculate the start-index out of the token type &amp; value)
 * @param map  value for field [DataBasedTokenInputStream.map] (The position map of the [DataBasedTokenInputStream])
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
(

    /**
     * The source (mostly filename) of the [DataBasedTokenInputStream]
     */
    override val source: String,

    /**
     * The tokenTypes that are contained in the [DataBasedTokenInputStream]
     */
    open val tokenTypes: Array<TT>,

    /**
     * The values for the tokens that have values in the [DataBasedTokenInputStream]
     */
    protected open val values: Array<String>,

    /**
     * The positions of the tokens of the [DataBasedTokenInputStream]
     * (We just store the end-indexes of each token. We can calculate the start-index out of the token type &amp; value)
     */
    protected open val positions: IntArray,

    /**
     * The map for the token-positions
     * We have this map to resolve the column / line of an index. This is useful for error-generation.
     */
    override val map: PositionMap,

    /**
     * Create a [Token] from its contents
     */
    val createToken: (TT, String?, Int, Int) -> T,

) : TokenInputStream<TT, T> {

    private var pos: Int = -1
    private var valuePos = -1

    override var position: Int
        get() {
            return pos
        }
        set(position) {
            pos = position

            // test the position (throw error if a wrong position is provided)
            // and set the position if no error is thrown
            testPosition(position)
            this.pos = position

            // resolve value-position
            valuePos = -1
            for (i in 0..position) if (tokenTypes[i].hasValue) valuePos++
        }

    /**
     * Get the amount of contained tokens
     */
    override val size: Int get() = tokenTypes.size

    /**
     * Getter for [tokenTypes] (Gives back an array of [Token]s)
     *
     * @return The [Token]s of the [DataBasedTokenInputStream]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open val tokens: List<T?> get() {

        // Create an array of tokens for all the tokens
        var valuePosition = -1
        val tokens = List(tokenTypes.size) {
            if (this.tokenTypes[it].hasValue) {
                createToken(
                    this.tokenTypes[it],
                    values[++valuePosition],
                    positions[it] - this.tokenTypes[it].length(values[valuePosition]),
                    positions[it]
                )
            } else createToken(
                this.tokenTypes[it],
                null,
                positions[it] - this.tokenTypes[it].length(null),
                positions[it]
            )

        }
        return tokens
    }

    /**
     * Get a specific token from the [DataBasedTokenInputStream]
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    @Deprecated("Try to avoid using this method, it will be a performance bottleneck")
    @Suppress("deprecation")
    open operator fun get(position: Int): T {
        // test the position (throw error if a wrong position is provided)
        // and return the token at the position if no error is thrown
        testPosition(position)
        return createToken(getType(position), getValue(position), getStart(position), getEnd(position))
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
        // test the position (throw error if a wrong position is provided)
        // and return the token-type at the position if no error is thrown
        testPosition(position)
        return tokenTypes[position]
    }

    /**
     * Get the start of specific token from the [DataBasedTokenInputStream] by its position
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    @Deprecated(
        """Avoid using this method and use #peekStart(), #actualStart() instead. This method
                  is fully functional, but it uses #getValue(int), which has to loop over the complete
                  #tokens array again up to the given token position
     
      """
    )
    @Suppress("deprecation")
    open fun getStart(position: Int): Int {
        // test the position (throw error if a wrong position is provided)
        // and return the token-start at the position if no error is thrown
        testPosition(position)
        return getEnd(position) + 1 -
                if (getHasValue(position)) getType(position).length(getValue(position))
                else getType(position).length(null)
    }

    /**
     * Get the end of specific token from the [DataBasedTokenInputStream] by its position
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open fun getEnd(position: Int): Int {
        // test the position (throw error if a wrong position is provided)
        // and return the token-end at the position if no error is thrown
        testPosition(position)
        return positions[position]
    }

    /**
     * Get the value of specific token from the [DataBasedTokenInputStream] by it's position
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    @Deprecated(
        """This method is fully working, but it will decrease the performance of the program, because it has to
                  loop over all the {@link #tokens} again to find the value's index that should be returned
     
      """
    )
    open fun getValue(position: Int): String? {
        if(!getHasValue(position)) return null

        // We start the valueIndex at 0
        var valueIndex = 0

        /// loop up to the given position and count the valued tokens before (increase the valueIndex)
        for (i in 0 until position) if (tokenTypes[i].hasValue) valueIndex++

        // return the value at the given position
        return values[valueIndex]
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
        // Check, if the token at the given position has a value
        return tokenTypes[position].hasValue
    }

    override fun has(num: Int): Boolean {
        // When the number to check is smaller than 0 throw an error
        // in other case just check if the required tokens are left
        if (num < 1) throw Error("You should only give positive numbers to this function")
        return pos + num < tokenTypes.size
    }

    override operator fun hasNext(): Boolean {
        // We could also use has(1) here, but for performance-reasons
        // that here should be better
        return pos + 1 < tokenTypes.size
    }

    override fun nextType(): TT {
        // skip to next token and then return the actual token
        skip()
        return actualType
    }

    override fun nextValue(): String? {
        // skip to next token and then return the actual token
        skip()
        return actualValue
    }

    override fun skip() {
        // Check if the input has a next token. If so then increase the position. If not throw an error
        // also increase the valuePosition if the next token has a value
        if (hasNext()) {
            if (tokenTypes[++pos].hasValue) valuePos++
        } else throw Error("Input already finished")
    }

    override fun skip(amount: Int) {
        for(i in 0 until amount) skip()
    }

    override val actual: T
        get() {
        // Just return the actual token
        // That is possible, because the position should never get
        // bigger than the token length.
        return createToken(actualType, actualValue, actualStart, actualEnd)
    }

    override val actualType: TT get() {
        // Just return the actual token-type
        // That is possible, because the position should never get
        // bigger than the token length.
        return tokenTypes[pos]
    }

    override val actualStart: Int get() {
        // Just return the actual token-start
        // That is possible, because the position should never get
        // bigger than the token length.
        return actualEnd + 1 -
                if (actualHasValue) actualType.length(actualValue)
                else actualType.length(null)
    }

    override val actualEnd: Int get() {
        // Just return the actual token-end
        // That is possible, because the position should never get
        // bigger than the token length.
        return positions[pos]
    }

    override val actualValue: String? get() {
        // Just return the actual token-value
        // That is possible, because the position should never get
        // bigger than the token length.
        return if (actualHasValue) values[valuePos] else null
    }

    override val actualHasValue: Boolean get() {
        // just return if the actual token-type
        return tokenTypes[pos].hasValue
    }

    override fun peek(): T {
        return if (pos + 1 < tokenTypes.size) createToken(
            peekType(),
            peekValue(),
            peekStart(),
            peekEnd()
        ) else throw Error("Not enough tokens left")
    }

    override fun peekType(): TT {
        return if (pos + 1 < tokenTypes.size) tokenTypes[pos + 1] else throw Error("Not enough tokens left")
    }

    override fun peekStart(): Int {
        return if (pos + 1 < tokenTypes.size) peekEnd() + 1 -
                (if (peekHasValue()) peekType().length(peekValue())
                else peekType().length(null))
            else throw Error("Not enough tokens left")
    }

    override fun peekEnd(): Int {
        return if (pos + 1 < tokenTypes.size) positions[pos + 1] else throw Error("Not enough tokens left")
    }

    override fun peekValue(): String? {
        return if (peekHasValue()) values[if (actualHasValue) valuePos + 2 else valuePos + 1] else null
    }

    override fun peekHasValue(): Boolean {
        return if (pos + 1 < tokenTypes.size) peekType().hasValue else throw Error("Not enough tokens left")
    }

    override fun peek(offset: Int): T {
        return if (pos + offset < tokenTypes.size) createToken(
            peekType(offset),
            peekValue(offset),
            peekStart(offset),
            peekEnd(offset)
        ) else throw Error("Not enough tokens left")
    }

    override fun peekType(offset: Int): TT {
        return if (pos + offset < tokenTypes.size) tokenTypes[pos + offset] else throw Error("Not enough tokens left")
    }

    override fun peekStart(offset: Int): Int {
        return if (pos + offset < tokenTypes.size) peekEnd(offset) + 1 -
                (if(peekHasValue(offset)) peekType(offset).length(peekValue(offset))
                else peekType(offset).length(null))
        else throw Error("Not enough tokens left")
    }

    override fun peekEnd(offset: Int): Int {
        return if (pos + offset < tokenTypes.size) positions[pos + offset] else throw Error("Not enough tokens left")
    }

    override fun peekValue(offset: Int): String? {
        if (!has(offset)) throw Error("Not enough tokens left")
        var valuePos = valuePos
        for (i in 1 until offset + 1) {
            if (tokenTypes[pos + i].hasValue) valuePos++
        }
        return if (peekHasValue(offset)) values[valuePos] else null
    }

    override fun peekHasValue(offset: Int): Boolean {
        if (!has(offset)) throw Error("Not enough tokens left")
        var valuePos = valuePos
        for (i in 0 until valuePos) {
            if (pos + i >= 0 && tokenTypes[pos + i + 1].hasValue) valuePos++
        }
        return if (pos + offset < tokenTypes.size) peekType(offset).hasValue else throw Error("Not enough tokens left")
    }

    override fun toString(): String {
        // Return a string-representation of the input just showing all the sub-elements
        return "TokenInputStream{source='$source', tokens=${tokenTypes.map { it.name }}, position=$pos}"
    }

    /**
     * This function checks if the given position is a valid position for the [tokenTypes]-array
     * throws an error if the position is a wrong one
     *
     * @param position the position to check
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    protected open fun testPosition(position: Int) {
        // If the position is out of range of the tokens array throw an error
        if (position < 0) throw Error("Position mustn't be smaller than 0.")
        if (position >= tokenTypes.size) throw Error(
                "The given position is to high. The maximum value is ${tokenTypes.size - 1}, but given was $position"
        )
    }
}