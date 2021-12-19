package io.github.shakelang.shake.lexer.token

import io.github.shakelang.parseutils.characters.position.PositionMap

/**
 * A [TokenInputStream] provides the [Token]s for a Parser. It is
 * created by the [io.github.shakelang.shake.lexer.Lexer]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
abstract class TokenInputStream


/**
 * A stream of [Token]s that is created by a shake lexer
 *
 * @param source value for field [TokenInputStream.source] (The source (mostly file) of the tokens)
 * (We just store the end-indexes of each token. We can calculate the start-index out of the token type &amp; value)
 * @param map  value for field [TokenInputStream.map] (The position map of the [TokenInputStream])
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
(
    /**
     * The source (mostly filename) of the [TokenInputStream]
     */
    open val source: String,

    /**
     * The map for the token-positions
     * We have this map to resolve the column / line of an index. This is useful for error-generation.
     */
    open val map: PositionMap
) {

    /**
     * The position that the TokenInputStream is actually at
     */
    abstract val position: Int

    /**
     * Checks if the [TokenInputStream] has left a given number of tokens
     *
     * @param num the number of tokens to check
     * @return has the [TokenInputStream] left the given amount of [Token]s?
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    abstract fun has(num: Int): Boolean

    /**
     * Checks if the [TokenInputStream] has a token left
     *
     * @return has the [TokenInputStream] another [Token] left?
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open operator fun hasNext(): Boolean = has(1)

    /**
     * Returns the next token of the [TokenInputStream] (and skips)
     *
     * @return the next token
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open operator fun next(): Token {
        // skip to next token and then return the actual token
        skip()
        return actual
    }

    /**
     * Returns the type of the next token of the [TokenInputStream] (and skips)
     *
     * @return the next token
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open fun nextType(): Byte {
        // skip to next token and then return the actual token
        skip()
        return actualType
    }

    /**
     * Returns the next token of the [TokenInputStream]
     *
     * @return the next token
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open fun nextValue(): String? {
        // skip to next token and then return the actual token
        skip()
        return actualValue
    }

    /**
     * Skips the next token
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    abstract fun skip()

    /**
     * Skips a number of tokens
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    abstract fun skip(amount: Int)

    /**
     * Skips all ignorable tokens
     * _(ignorable tokens are tokens that can have a function in the parser, but can also be ignored
     * ([TokenType.LINE_SEPARATOR]))
     *
     * @return The [TokenInputStream] itself so you can do an operation directly after the call
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open fun skipIgnorable(): TokenInputStream {
        // As long as the next token is a line-separator execute skip
        while (hasNext() && peekType() == TokenType.LINE_SEPARATOR) skip()
        return this
    }

    /**
     * Returns the actual [Token]
     *
     * @return The actual [Token]
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    abstract val actual: Token

    /**
     * Returns the type of the actual token
     *
     * @return The actual token-type
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open val actualType: Byte get() {
        return actual.type
    }

    /**
     * Returns the start of the actual token
     *
     * @return The actual token-start
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open val actualStart: Int get() {
        return actual.start
    }

    /**
     * Returns the end of the actual token
     *
     * @return The actual token-end
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open val actualEnd: Int get() {
        return actual.end
    }

    /**
     * Returns the value of the actual
     *
     * @return The actual token-value
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open val actualValue: String? get() {
        return actual.value
    }

    /**
     * Checks if the actual token without changing the actual token
     *
     * @return Has the actual token a value?
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open val actualHasValue: Boolean get() {
        return actualValue != null
    }

    /**
     * Returns the next [Token]
     *
     * @return The next [Token]
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open fun peek(): Token {
        return peek(1)
    }

    /**
     * Returns the type of the next token without changing the actual token
     *
     * @return The next token-type
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open fun peekType(): Byte {
        return peek().type
    }

    /**
     * Returns the start of the next token without changing the actual token
     *
     * @return The next token-start
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open fun peekStart(): Int {
        return peek().start
    }

    /**
     * Returns the end of the next token without changing the actual token
     *
     * @return The next token-end
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open fun peekEnd(): Int {
        return peek().end
    }

    /**
     * Returns the value of the next token without changing the actual token
     *
     * @return The next token-value
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open fun peekValue(): String? {
        return peek().value
    }

    /**
     * Checks if the next token of the [TokenInputStream] has a value without skipping
     *
     * @return The next [Token]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open fun peekHasValue(): Boolean {
        return peekValue() != null
    }

    /**
     * Peek the token at the given index
     */
    abstract fun peek(offset: Int): Token

    /**
     * Peek the token type at the given index
     */
    open fun peekType(offset: Int): Byte {
        return peek(offset).type
    }

    /**
     * Peek the token start at the given index
     */
    open fun peekStart(offset: Int): Int {
        return peek(offset).start
    }

    /**
     * Peek the token end at the given index
     */
    open fun peekEnd(offset: Int): Int {
        return peek(offset).end
    }

    /**
     * Peek the token value at the given index
     */
    open fun peekValue(offset: Int): String? {
        return peek(offset).value
    }

    /**
     * Checks if the token at the given offset of the [TokenInputStream] has a value without changing the actual token
     */
    open fun peekHasValue(offset: Int): Boolean {
        return peekValue(offset) != null
    }
}