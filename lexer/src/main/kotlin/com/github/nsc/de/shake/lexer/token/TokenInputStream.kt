package com.github.nsc.de.shake.lexer.token

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap
import com.github.nsc.de.shake.util.ArrayUtil
import java.lang.Error
import java.util.Arrays

/**
 * A [TokenInputStream] provides the [Token]s for a [com.github.nsc.de.shake.parser.Parser]. It is
 * created by the [com.github.nsc.de.shake.lexer.Lexer]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
class TokenInputStream


/**
 * Create a [TokenInputStream] giving the [TokenInputStream.source] and [TokenInputStream.tokens]
 *
 * @param source value for field [TokenInputStream.source] (The source (mostly file) of the tokens)
 * @param tokens value for field [TokenInputStream.tokens] (The tokens that the [TokenInputStream] should give)
 * @param values The values for the tokens that have values in the [TokenInputStream]
 * @param positions The positions of the tokens of the [TokenInputStream]
 * (We just store the end-indexes of each token. We can calculate the start-index out of the token type &amp; value)
 * @param map  value for field [TokenInputStream.map] (The position map of the [TokenInputStream])
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
(
    /**
     * The source (mostly filename) of the [TokenInputStream]
     */
    val source: String,
    /**
     * The tokens that are contained in the [TokenInputStream]
     */
    private val tokens: ByteArray,
    /**
     * The values for the tokens that have values in the [TokenInputStream]
     */
    private val values: Array<String>,
    /**
     * The positions of the tokens of the [TokenInputStream]
     * (We just store the end-indexes of each token. We can calculate the start-index out of the token type &amp; value)
     */
    private val positions: IntArray,
    /**
     * The map for the token-positions
     * We have this map to resolve the column / line of an index. This is useful for error-generation.
     */
    val map: PositionMap
) {// just return the source
    /**
     * Getter for [source] (The source (mostly file) of the tokens)
     *
     * @return the source (mostly file) of the [TokenInputStream]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */

    /**
     * The position that the TokenInputStream is actually at
     */
    private var position: Int = -1

    /**
     * The value-position of the [TokenInputStream]
     * (This variable we just need for performance-reasons, because don't have to search all the tokens again to get the
     * value-index of the actual position)
     */
    private var valuePosition = -1// just return the position-map
    /**
     * Getter for [map] (Gives back the map for the positions)
     *
     * @return The [PositionMap] of the [TokenInputStream] (for resolving the token-positions
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */

    /**
     * Getter for [TokenInputStream.position] (The actual position of the [TokenInputStream])
     *
     * @return the actual position of the [TokenInputStream]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun getPosition(): Int {
        // just return the position
        return position
    }

    /**
     * Getter for [tokens] (Gives back an array of [Token]s)
     *
     * @return The [Token]s of the [TokenInputStream]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun getTokens(): Array<Token?> {

        // Create an array of tokens for all the tokens
        val tokens = arrayOfNulls<Token>(tokens.size)

        // The positions
        var valuePosition = -1

        // loop over the tokens array and fill it.
        for (i in tokens.indices) {
            if (TokenType.hasValue(this.tokens[i])) {
                tokens[i] = Token(
                    this.tokens[i],
                    values[++valuePosition],
                    positions[i] - TokenType.getTokenLength(this.tokens[i], values[valuePosition]),
                    positions[i]
                )
            } else tokens[i] = Token(
                this.tokens[i],
                positions[i] - TokenType.getTokenLength(this.tokens[i]),
                positions[i]
            )
        }
        return tokens
    }

    /**
     * Set the position of the [TokenInputStream]
     *
     * @param position the new position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun setPosition(position: Int) {
        // test the position (throw error if a wrong position is provided)
        // and set the position if no error is thrown
        testPosition(position)
        this.position = position

        // resolve value-position
        valuePosition = -1
        for (i in 0..position) if (TokenType.hasValue(tokens[i])) valuePosition++
    }

    /**
     * Get a specific token from the [TokenInputStream]
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
     * Get the type of specific token from the [TokenInputStream] by it's position
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
        return tokens[position]
    }

    /**
     * Get the start of specific token from the [TokenInputStream] by it's position
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
                if (getHasValue(position)) TokenType.getTokenLength(getType(position),getValue(position))
                else TokenType.getTokenLength(getType(position)).toInt()
    }

    /**
     * Get the end of specific token from the [TokenInputStream] by it's position
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
     * Get the value of specific token from the [TokenInputStream] by it's position
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
    fun getValue(position: Int): String {

        // We start the valueIndex at 0
        var valueIndex = 0

        /// loop up to the given position and count the valued tokens before (increase the valueIndex)
        for (i in 0 until position) if (TokenType.hasValue(tokens[i])) valueIndex++

        // return the value at the given position
        return values[valueIndex]
    }

    /**
     * Check if specific token from the [TokenInputStream] has a value (by it's position)
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun getHasValue(position: Int): Boolean {

        // Check, if the token at the given position has a value
        return TokenType.hasValue(tokens[position])
    }

    /**
     * Checks if the [TokenInputStream] has left a given number of tokens
     *
     * @param num the number of tokens to check
     * @return has the [TokenInputStream] left the given amount of [Token]s?
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun has(num: Int): Boolean {
        // When the number to check is smaller than 0 throw an error
        // in other case just check if the required tokens are left
        if (num < 1) throw Error("You should only give positive numbers to this function")
        return position + num < tokens.size
    }

    /**
     * Checks if the [TokenInputStream] has a token left
     *
     * @return has the [TokenInputStream] another [Token] left?
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    operator fun hasNext(): Boolean {
        // We could also use has(1) here, but for performance-reasons
        // that here should be better
        return position + 1 < tokens.size
    }

    /**
     * Returns the next token of the [TokenInputStream] (and skips)
     *
     * @return the next token
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    @Deprecated(
        """If you don't need a {@link Token}, it is advised to use {@link #nextType()} &amp; {@link #nextValue()}
     
      """
    )
    @Suppress("deprecation")
    operator fun next(): Token {
        // skip to next token and then return the actual token
        skip()
        return actual()
    }

    /**
     * Returns the type of the next token of the [TokenInputStream] (and skips)
     *
     * @return the next token
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun nextType(): Byte {
        // skip to next token and then return the actual token
        skip()
        return actualType()
    }

    /**
     * Returns the next token of the [TokenInputStream]
     *
     * @return the next token
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun nextValue(): String? {
        // skip to next token and then return the actual token
        skip()
        return actualValue()
    }

    /**
     * Skips the next token of the [TokenInputStream]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun skip() {
        // Check if the input has a next token. If so then increase the position. If not throw an error
        // also increase the valuePosition if the next token has a value
        if (hasNext()) {
            if (TokenType.hasValue(tokens[++position])) valuePosition++
        } else throw Error("Input already finished")
    }

    /**
     * Skips all ignorable tokens of the [TokenInputStream]
     * _(ignorable tokens are tokens that can have a function in the parser, but can also be ignored
     * ([TokenType.LINE_SEPARATOR]))
     *
     * @return The [TokenInputStream] itself so you can do an operation directly after the call
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun skipIgnorable(): TokenInputStream {
        // As long as the next token is a line-separator execute skip
        while (hasNext() && peekType() == TokenType.LINE_SEPARATOR) {

            // We could also use skip here, but for performance-reasons
            // that here should be better
            // This is possible because i already checked if there is a next
            // token before in the while statement and a line-separator token
            // has no value.
            position++
        }
        return this
    }

    /**
     * Returns the actual [Token] of the [TokenInputStream]
     *
     * @return The actual [Token]
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    @Deprecated(
        """Only use, if you need a complete {@link Token}. Use {@link #actualType()}, {@link #actualStart()},
      {@link #actualEnd()}, {@link #actualValue()} &amp; {@link #actualHasValue()} instead, if you just need one of these!
     
      """
    )
    fun actual(): Token {
        // Just return the actual token
        // That is possible, because the position should never get
        // bigger than the token length.
        return Token(actualType(), actualValue(), actualStart(), actualEnd())
    }

    /**
     * Returns the type of the actual token of the [TokenInputStream]
     *
     * @return The actual token-type
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun actualType(): Byte {
        // Just return the actual token-type
        // That is possible, because the position should never get
        // bigger than the token length.
        return tokens[position]
    }

    /**
     * Returns the start of the actual token of the [TokenInputStream]
     *
     * @return The actual token-start
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun actualStart(): Int {
        // Just return the actual token-start
        // That is possible, because the position should never get
        // bigger than the token length.
        return actualEnd() + 1 -
                if (actualHasValue()) TokenType.getTokenLength(actualType(), actualValue())
                else TokenType.getTokenLength(actualType()).toInt()
    }

    /**
     * Returns the end of the actual token of the [TokenInputStream]
     *
     * @return The actual token-end
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun actualEnd(): Int {
        // Just return the actual token-end
        // That is possible, because the position should never get
        // bigger than the token length.
        return positions[position]
    }

    /**
     * Returns the value of the actual token of the [TokenInputStream]
     *
     * @return The actual token-value
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun actualValue(): String? {
        // Just return the actual token-value
        // That is possible, because the position should never get
        // bigger than the token length.
        return if (TokenType.hasValue(tokens[position])) values[valuePosition] else null
    }

    /**
     * Checks if the actual token of the [TokenInputStream] has a value
     *
     * @return Has the actual token a value?
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun actualHasValue(): Boolean {
        // just return if the actual token-type
        return TokenType.hasValue(tokens[position])
    }

    /**
     * Returns the next [Token] of the [TokenInputStream] without skipping
     *
     * @return The next [Token]
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    @Deprecated(
        """Only use, if you need a complete {@link Token}. Use {@link #peekType()}, {@link #peekStart()},
      {@link #peekEnd()}, {@link #peekValue()} &amp; {@link #peekHasValue()} instead, if you just need one of these!
     
      """
    )
    fun peek(): Token {
        return if (position + 1 < tokens.size) Token(
            peekType(),
            peekValue(),
            peekStart(),
            peekEnd()
        ) else throw Error("Not enough tokens left")
    }

    /**
     * Returns the type of the next token of the [TokenInputStream] without skipping
     *
     * @return The next token-type
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun peekType(): Byte {
        return if (position + 1 < tokens.size) tokens[position + 1] else throw Error("Not enough tokens left")
    }

    /**
     * Returns the start of the next token of the [TokenInputStream] without skipping
     *
     * @return The next token-start
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun peekStart(): Int {
        return if (position + 1 < tokens.size) peekEnd() + 1 -
                (if (peekHasValue()) TokenType.getTokenLength(peekType(),peekValue())
                else TokenType.getTokenLength(peekType()).toInt())
            else throw Error("Not enough tokens left")
    }

    /**
     * Returns the end of the next token of the [TokenInputStream] without skipping
     *
     * @return The next token-end
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun peekEnd(): Int {
        return if (position + 1 < tokens.size) positions[position + 1] else throw Error("Not enough tokens left")
    }

    /**
     * Returns the value of the next token of the [TokenInputStream] without skipping
     *
     * @return The next token-value
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun peekValue(): String? {
        return if (peekHasValue()) values[if (TokenType.hasValue(actualType())) valuePosition + 2 else valuePosition + 1] else null
    }

    /**
     * Checks if the next token of the [TokenInputStream] has a value without skipping
     *
     * @return The next [Token]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun peekHasValue(): Boolean {
        return if (position + 1 < tokens.size) TokenType.hasValue(peekType()) else throw Error("Not enough tokens left")
    }

    /**
     * Returns a string-representation of the [TokenInputStream]
     *
     * @return the string-representation of the [TokenInputStream]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun toString(): String {
        // Return a string-representation of the input just showing all the sub-elements
        return "TokenInputStream{source='$source', tokens=${Arrays.toString(
                    ArrayUtil.map(tokens, arrayOfNulls<String>(tokens.size)) {
                        TokenType.getName(it)
                    })}, position=$position}"
    }

    /**
     * This function checks if the given position is a valid position for the [tokens]-array
     * throws an error if the position is a wrong one
     *
     * @param position the position to check
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    private fun testPosition(position: Int) {
        // If the position is out of range of the tokens array throw an error
        if (position < 0) throw Error("Position mustn't be smaller than 0.")
        if (position >= getTokens().size) throw Error(
            String.format(
                "The given position is to high. The maximum value is %d, but given was %d",
                getTokens().size - 1,
                position
            )
        )
    }
}