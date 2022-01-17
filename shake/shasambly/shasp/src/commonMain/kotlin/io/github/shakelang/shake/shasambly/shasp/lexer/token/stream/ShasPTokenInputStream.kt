package io.github.shakelang.shake.lexer.token.stream

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.lexer.token.ShasPToken
import io.github.shakelang.shake.shasambly.shasp.lexer.token.ShasPTokenType

/**
 * A [ShasPTokenInputStream] provides the [ShasPToken]s for a Parser. It is
 * created by the [io.github.shakelang.shake.lexer.ShasPLexer]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
interface ShasPTokenInputStream {

    /**
     * The source (mostly filename) of the [ShasPTokenInputStream]
     */
    val source: String

    /**
     * The map for the token-positions
     * We have this map to resolve the column / line of an index. This is useful for error-generation.
     */
    val map: PositionMap

    /**
     * The position that the TokenInputStream is actually at
     */
    val position: Int

    /**
     * Checks if the [ShasPTokenInputStream] has left a given number of tokens
     *
     * @param num the number of tokens to check
     * @return has the [ShasPTokenInputStream] left the given amount of [ShasPToken]s?
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun has(num: Int): Boolean

    /**
     * Checks if the [ShasPTokenInputStream] has a token left
     *
     * @return has the [ShasPTokenInputStream] another [ShasPToken] left?
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    operator fun hasNext(): Boolean = has(1)

    /**
     * Returns the next token of the [ShasPTokenInputStream] (and skips)
     *
     * @return the next token
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    operator fun next(): ShasPToken {
        // skip to next token and then return the actual token
        skip()
        return actual
    }

    /**
     * Returns the type of the next token of the [ShasPTokenInputStream] (and skips)
     *
     * @return the next token
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun nextType(): ShasPTokenType {
        // skip to next token and then return the actual token
        skip()
        return actualType
    }

    /**
     * Returns the next token of the [ShasPTokenInputStream]
     *
     * @return the next token
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun nextValue(): String? {
        // skip to next token and then return the actual token
        skip()
        return actualValue
    }

    /**
     * Skips the next token
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun skip()

    /**
     * Skips a number of tokens
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun skip(amount: Int)

    /**
     * Returns the actual [ShasPToken]
     *
     * @return The actual [ShasPToken]
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    val actual: ShasPToken

    /**
     * Returns the type of the actual token
     *
     * @return The actual token-type
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    val actualType: ShasPTokenType
        get() {
        return actual.type
    }

    /**
     * Returns the start of the actual token
     *
     * @return The actual token-start
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    val actualStart: Int get() {
        return actual.start
    }

    /**
     * Returns the end of the actual token
     *
     * @return The actual token-end
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    val actualEnd: Int get() {
        return actual.end
    }

    /**
     * Returns the value of the actual
     *
     * @return The actual token-value
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    val actualValue: String? get() {
        return actual.value
    }

    /**
     * Checks if the actual token without changing the actual token
     *
     * @return Has the actual token a value?
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    val actualHasValue: Boolean get() {
        return actualValue != null
    }

    /**
     * Returns the next [ShasPToken]
     *
     * @return The next [ShasPToken]
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun peek(): ShasPToken {
        return peek(1)
    }

    /**
     * Returns the type of the next token without changing the actual token
     *
     * @return The next token-type
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun peekType(): ShasPTokenType {
        return peek().type
    }

    /**
     * Returns the start of the next token without changing the actual token
     *
     * @return The next token-start
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun peekStart(): Int {
        return peek().start
    }

    /**
     * Returns the end of the next token without changing the actual token
     *
     * @return The next token-end
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun peekEnd(): Int {
        return peek().end
    }

    /**
     * Returns the value of the next token without changing the actual token
     *
     * @return The next token-value
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun peekValue(): String? {
        return peek().value
    }

    /**
     * Checks if the next token of the [ShasPTokenInputStream] has a value without skipping
     *
     * @return The next [ShasPToken]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun peekHasValue(): Boolean {
        return peekValue() != null
    }

    /**
     * Peek the token at the given index
     */
    fun peek(offset: Int): ShasPToken

    /**
     * Peek the token type at the given index
     */
    fun peekType(offset: Int): ShasPTokenType {
        return peek(offset).type
    }

    /**
     * Peek the token start at the given index
     */
    fun peekStart(offset: Int): Int {
        return peek(offset).start
    }

    /**
     * Peek the token end at the given index
     */
    fun peekEnd(offset: Int): Int {
        return peek(offset).end
    }

    /**
     * Peek the token value at the given index
     */
    fun peekValue(offset: Int): String? {
        return peek(offset).value
    }

    /**
     * Checks if the token at the given offset of the [ShasPTokenInputStream] has a value without changing the actual token
     */
    fun peekHasValue(offset: Int): Boolean {
        return peekValue(offset) != null
    }
}