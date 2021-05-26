package com.github.nsc.de.shake.util.json

import com.github.nsc.de.shake.util.characterinput.position.PositionMap

@Suppress("unused")
class JSONToken(
    val type: JSONTokenType,
    val start: Int,
    val end: Int = start,
    val value: String? = null
) {
    constructor(type: JSONTokenType, start: Int, value: String? = null) : this(type, start, start, value)

    /**
     * Has the [JSONToken] a value?
     */
    val hasValue: Boolean
        get() = this.value != null

    override fun toString() = "JSONToken{type=$type,start=$start,end=$end,value=$value}"

}

enum class JSONTokenType {

    LCURL,              // '{'
    RCURL,              // '}'
    LSQUARE,            // '['
    RSQUARE,            // ']'
    COMMA,              // ','
    COLON,              // ':'
    TRUE,
    FALSE,
    STRING,
    DOUBLE,
    INT,

}

/**
 * A [JSONTokenInputStream] provides the [JSONToken]s for a Parser. It is
 * created by the [com.github.nsc.de.shake.util.json.JsonLexer]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
class JSONTokenInputStream(

    /**
     * The source (mostly filename) of the [JSONTokenInputStream]
     */
    val source: String,

    /**
     * The tokens that are contained in the [JSONTokenInputStream]
     */
    private val tokens: Array<JSONToken>,

    /**
     * The PositionMap to resolve the [com.github.nsc.de.shake.util.characterinput.position.Position]s
     * of the [JSONToken]s
     */
    val map: PositionMap,

    /**
     * The position that the TokenInputStream is actually at
     */
    private var position: Int = -1

) {

    /**
     * Get the size of the [JSONTokenInputStream]
     *
     * @return the length of the [JSONTokenInputStream.tokens] array
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    val size: Int
        get() = this.tokens.size

    /**
     * Get a specific token from the [JSONTokenInputStream]
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    operator fun get(position: Int): JSONToken {
        testPosition(position)
        return this.tokens[position]
    }

    /**
     * Checks if the [JSONTokenInputStream] has left a given number of tokens
     *
     * @param num the number of tokens to check
     * @return has the [JSONTokenInputStream] left the given amount of [JSONToken]s?
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
     * Checks if the [JSONTokenInputStream] has a token left
     *
     * @return has the [JSONTokenInputStream] another [JSONToken] left?
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    operator fun hasNext(): Boolean {
        // We could also use has(1) here, but for performance-reasons
        // that here should be better
        return position + 1 < tokens.size
    }

    /**
     * Returns the next token of the [JSONTokenInputStream] (and skips)
     *
     * @return the next token
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    operator fun next(): JSONToken {
        // skip to next token and then return the actual token
        skip()
        return actual()
    }

    /**
     * Skips the next token of the [JSONTokenInputStream]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun skip() {
        // Check if the input has a next token.
        // If so then increase the position. If not throw an error
        if (hasNext()) position++ else throw Error("Input already finished")
    }

    /**
     * Returns the actual [JSONToken] of the [JSONTokenInputStream]
     *
     * @return The actual [JSONToken]
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun actual(): JSONToken {
        // Just return the actual token
        // That is possible, because the position should never get
        // bigger than the token length.
        return this[this.position]
    }

    /**
     * Returns the next [JSONToken] of the [JSONTokenInputStream] without skipping
     *
     * @return The next [JSONToken]
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun peek(): JSONToken {
        return if (position + 1 < tokens.size) this[position + 1] else throw Error("Not enough tokens left")
    }

    /**
     * Returns a string-representation of the [JSONTokenInputStream]
     *
     * @return the string-representation of the [JSONTokenInputStream]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun toString(): String {
        // Return a string-representation of the input just showing all the sub-elements
        return "TokenInputStream{" +
                "source='" + source + '\'' +
                ", tokens=" + this.tokens.contentToString() +
                ", position=" + position +
                '}'
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
        if (position >= this.size) throw Error(
            "The given position is to high. The maximum value is %${this.size - 1}, but given was $position",
        )
    }
}