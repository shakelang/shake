package com.shakelang.util.parseutils.lexer.token

import com.shakelang.util.parseutils.lexer.token.stream.TokenInputStream

/**
 * The input of a lexer gets converted into [Token]s. These get parsed
 * by the parser
 * @param type the [Token.type] of the [Token]
 * @param value the [Token.value] of the [Token]
 * @param start the [Token.start] of the [Token]
 * @param end the [Token.end] of the [Token]
 *
 * @since 0.1.0
 * @version 0.2.1
 */
open class Token<
    Self : Token<Self, TT, ST, CTX>,
    TT : TokenType,
    ST : TokenInputStream<ST, TT, Self, CTX>,
    CTX : TokenContext<CTX, TT, Self, ST>,
    >(
    /**
     * The type of the [Token]
     * @see Token
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    open val type: TT,

    /**
     * The value of the [Token] (This is for identifiers, strings or numbers. If not necessary this is null)
     * @see Token
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    open val value: String,

    /**
     * The starting Position of the [Token]
     * @see Token
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    open val start: Int,

    /**
     * The ending Position of the [Token]
     * @see Token
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    open val end: Int,

    /**
     * The context of the [Token]
     * @see Token
     *
     * @since 0.5.0
     * @version 0.5.0
     */
    open val context: CTX,
) {
    /**
     * String representation of the [Token]
     * @return the string representation of the [Token]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override fun toString(): String {
        return "Token{type=$type, value=$value, start=$start, end=$end}"
    }

    /**
     * Check if the [Token] is equal to another [Token]
     * @param other the other [Token]
     * @return if the [Token] is equal to the other [Token]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is Token<*, *, *, *>) return false
        return type == other.type &&
            value == other.value
    }

    /**
     * Get the hash code of the [Token]
     * @return the hash code of the [Token]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override fun hashCode(): Int = hashAll(type, value)

    /**
     * Get the hash code of all given values
     * @param values the values to hash
     * @return the hash code of all given values
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    private fun hashAll(vararg values: Any?): Int {
        var res = 0
        for (v in values) {
            res += v.hashCode()
            res *= 31
        }
        return res
    }
}
