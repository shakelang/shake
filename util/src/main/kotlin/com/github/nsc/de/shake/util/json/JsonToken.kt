package com.github.nsc.de.shake.util.json

import com.github.nsc.de.shake.util.characterinput.position.PositionMap

@Suppress("unused")
class NBTToken(
    val type: NBTTokenType,
    val start: Int,
    val end: Int = start,
    val value: String? = null
) {
    constructor(type: NBTTokenType, start: Int, value: String? = null) : this(type, start, start, value)

    /**
     * Has the [NBTToken] a value?
     */
    val hasValue: Boolean
        get() = this.value != null

    override fun toString() = "NBTToken{type=$type,start=$start,end=$end,value=$value}"

}

enum class NBTTokenType {

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
 * A [NBTTokenInputStream] provides the [NBTToken]s for a Parser. It is
 * created by the [com.github.nsc.de.shake.util.json.JsonLexer]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
class NBTTokenInputStream(

    /**
     * The source (mostly filename) of the [NBTTokenInputStream]
     */
    val source: String,

    /**
     * The tokens that are contained in the [NBTTokenInputStream]
     */
    private val tokens: Array<NBTToken>,

    /**
     * The PositionMap to resolve the [com.github.nsc.de.shake.util.characterinput.position.Position]s
     * of the [NBTToken]s
     */
    val map: PositionMap,

    /**
     * The position that the TokenInputStream is actually at
     */
    private var position: Int = -1

) {

    /**
     * Get the size of the [NBTTokenInputStream]
     *
     * @return the length of the [NBTTokenInputStream.tokens] array
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    val size: Int
        get() = this.tokens.size

    /**
     * Get a specific token from the [NBTTokenInputStream]
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    operator fun get(position: Int): NBTToken {
        testPosition(position)
        return this.tokens[position]
    }

    /**
     * Checks if the [NBTTokenInputStream] has left a given number of tokens
     *
     * @param num the number of tokens to check
     * @return has the [NBTTokenInputStream] left the given amount of [NBTToken]s?
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
     * Checks if the [NBTTokenInputStream] has a token left
     *
     * @return has the [NBTTokenInputStream] another [NBTToken] left?
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    operator fun hasNext(): Boolean {
        // We could also use has(1) here, but for performance-reasons
        // that here should be better
        return position + 1 < tokens.size
    }

    /**
     * Returns the next token of the [NBTTokenInputStream] (and skips)
     *
     * @return the next token
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    operator fun next(): NBTToken {
        // skip to next token and then return the actual token
        skip()
        return actual()
    }

    /**
     * Skips the next token of the [NBTTokenInputStream]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun skip() {
        // Check if the input has a next token.
        // If so then increase the position. If not throw an error
        if (hasNext()) position++ else throw Error("Input already finished")
    }

    /**
     * Returns the actual [NBTToken] of the [NBTTokenInputStream]
     *
     * @return The actual [NBTToken]
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun actual(): NBTToken {
        // Just return the actual token
        // That is possible, because the position should never get
        // bigger than the token length.
        return this[this.position]
    }

    /**
     * Returns the next [NBTToken] of the [NBTTokenInputStream] without skipping
     *
     * @return The next [NBTToken]
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun peek(): NBTToken {
        return if (position + 1 < tokens.size) this[position + 1] else throw Error("Not enough tokens left")
    }

    /**
     * Returns a string-representation of the [NBTTokenInputStream]
     *
     * @return the string-representation of the [NBTTokenInputStream]
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
            String.format(
                "The given position is to high. The maximum value is %d, but given was %d",
                this.size - 1,
                position
            )
        )
    }
}