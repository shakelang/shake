package io.github.shakelang.shake.lexer.token.stream

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.lexer.token.Token
import io.github.shakelang.shake.lexer.token.TokenType
import io.github.shakelang.shake.lexer.token.tokenLength

/**
 * A [DataBasedTokenInputStream] provides the [Token]s for a Parser. It is
 * created by the [io.github.shakelang.shake.lexer.Lexer]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
class DataBasedTokenInputStream

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
    val tokenTypes: ByteArray,
    /**
     * The values for the tokens that have values in the [DataBasedTokenInputStream]
     */
    private val values: Array<String>,
    /**
     * The positions of the tokens of the [DataBasedTokenInputStream]
     * (We just store the end-indexes of each token. We can calculate the start-index out of the token type &amp; value)
     */
    private val positions: IntArray,
    /**
     * The map for the token-positions
     * We have this map to resolve the column / line of an index. This is useful for error-generation.
     */
    override val map: PositionMap

) : TokenInputStream {

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
            for (i in 0..position) if (TokenType.hasValue(tokenTypes[i])) valuePos++
        }

    /**
     * Getter for [tokenTypes] (Gives back an array of [Token]s)
     *
     * @return The [Token]s of the [DataBasedTokenInputStream]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    val tokens: Array<Token?> get() {

        // Create an array of tokens for all the tokens
        val tokens = arrayOfNulls<Token>(tokenTypes.size)

        // The positions
        var valuePosition = -1

        // loop over the tokens array and fill it.
        for (i in tokens.indices) {
            if (TokenType.hasValue(this.tokenTypes[i])) {
                tokens[i] = Token(
                    this.tokenTypes[i],
                    values[++valuePosition],
                    positions[i] - TokenType.getTokenLength(this.tokenTypes[i], values[valuePosition]),
                    positions[i]
                )
            } else tokens[i] = Token(
                this.tokenTypes[i],
                positions[i] - TokenType.getTokenLength(this.tokenTypes[i]),
                positions[i]
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
    operator fun get(position: Int): Token {
        // test the position (throw error if a wrong position is provided)
        // and return the token at the position if no error is thrown
        testPosition(position)
        return Token(getType(position), getValue(position), getStart(position), getEnd(position))
    }

    /**
     * Get the type of specific token from the [DataBasedTokenInputStream] by its position
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun getType(position: Int): Byte {
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
    fun getStart(position: Int): Int {
        // test the position (throw error if a wrong position is provided)
        // and return the token-start at the position if no error is thrown
        testPosition(position)
        return getEnd(position) + 1 -
                if (getHasValue(position)) TokenType.getTokenLength(getType(position), getValue(position))
                else TokenType.getTokenLength(getType(position)).toInt()
    }

    /**
     * Get the end of specific token from the [DataBasedTokenInputStream] by it's position
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun getEnd(position: Int): Int {
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
    fun getValue(position: Int): String? {
        if(!getHasValue(position)) return null

        // We start the valueIndex at 0
        var valueIndex = 0

        /// loop up to the given position and count the valued tokens before (increase the valueIndex)
        for (i in 0 until position) if (TokenType.hasValue(tokenTypes[i])) valueIndex++

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
    fun getHasValue(position: Int): Boolean {
        // Check, if the token at the given position has a value
        return TokenType.hasValue(tokenTypes[position])
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

    override fun nextType(): Byte {
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
            if (TokenType.hasValue(tokenTypes[++pos])) valuePos++
        } else throw Error("Input already finished")
    }

    override fun skip(amount: Int) {
        for(i in 0 until amount) skip()
    }

    override val actual: Token
        get() {
        // Just return the actual token
        // That is possible, because the position should never get
        // bigger than the token length.
        return Token(actualType, actualValue, actualStart, actualEnd)
    }

    override val actualType: Byte get() {
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
                if (actualHasValue) TokenType.getTokenLength(actualType, actualValue)
                else TokenType.getTokenLength(actualType).toInt()
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
        return if (TokenType.hasValue(tokenTypes[pos])) values[valuePos] else null
    }

    override val actualHasValue: Boolean get() {
        // just return if the actual token-type
        return TokenType.hasValue(tokenTypes[pos])
    }

    override fun peek(): Token {
        return if (pos + 1 < tokenTypes.size) Token(
            peekType(),
            peekValue(),
            peekStart(),
            peekEnd()
        ) else throw Error("Not enough tokens left")
    }

    override fun peekType(): Byte {
        return if (pos + 1 < tokenTypes.size) tokenTypes[pos + 1] else throw Error("Not enough tokens left")
    }

    override fun peekStart(): Int {
        return if (pos + 1 < tokenTypes.size) peekEnd() + 1 -
                (if (peekHasValue()) TokenType.getTokenLength(peekType(), peekValue())
                else TokenType.getTokenLength(peekType()).toInt())
            else throw Error("Not enough tokens left")
    }

    override fun peekEnd(): Int {
        return if (pos + 1 < tokenTypes.size) positions[pos + 1] else throw Error("Not enough tokens left")
    }

    override fun peekValue(): String? {
        return if (peekHasValue()) values[if (TokenType.hasValue(actualType)) valuePos + 2 else valuePos + 1] else null
    }

    override fun peekHasValue(): Boolean {
        return if (pos + 1 < tokenTypes.size) TokenType.hasValue(peekType()) else throw Error("Not enough tokens left")
    }

    override fun peek(offset: Int): Token {
        return if (pos + offset < tokenTypes.size) Token(
            peekType(offset),
            peekValue(offset),
            peekStart(offset),
            peekEnd(offset)
        ) else throw Error("Not enough tokens left")
    }

    override fun peekType(offset: Int): Byte {
        return if (pos + offset < tokenTypes.size) tokenTypes[pos + offset] else throw Error("Not enough tokens left")
    }

    override fun peekStart(offset: Int): Int {
        return if (pos + offset < tokenTypes.size)
            peekEnd(offset) + 1 - peekType(offset).tokenLength(peekValue(offset))
        else throw Error("Not enough tokens left")
    }

    override fun peekEnd(offset: Int): Int {
        return if (pos + offset < tokenTypes.size) positions[pos + offset] else throw Error("Not enough tokens left")
    }

    override fun peekValue(offset: Int): String? {
        if (!has(offset)) throw Error("Not enough tokens left")
        var valuePos = valuePos
        for (i in 1 until offset + 1) {
            if (TokenType.hasValue(tokenTypes[pos + i])) valuePos++
        }
        return if (peekHasValue(offset)) values[valuePos] else null
    }

    override fun peekHasValue(offset: Int): Boolean {
        if (!has(offset)) throw Error("Not enough tokens left")
        var valuePos = valuePos
        for (i in 0 until valuePos) {
            if (pos + i >= 0 && TokenType.hasValue(tokenTypes[pos + i + 1])) valuePos++
        }
        return if (pos + offset < tokenTypes.size) TokenType.hasValue(peekType(offset)) else throw Error("Not enough tokens left")
    }

    override fun toString(): String {
        // Return a string-representation of the input just showing all the sub-elements
        return "TokenInputStream{source='$source', tokens=${tokenTypes.map { TokenType.getName(it) }}, position=$pos}"
    }

    /**
     * This function checks if the given position is a valid position for the [tokenTypes]-array
     * throws an error if the position is a wrong one
     *
     * @param position the position to check
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    private fun testPosition(position: Int) {
        // If the position is out of range of the tokens array throw an error
        if (position < 0) throw Error("Position mustn't be smaller than 0.")
        if (position >= tokenTypes.size) throw Error(
                "The given position is to high. The maximum value is ${tokenTypes.size - 1}, but given was $position"
        )
    }
}