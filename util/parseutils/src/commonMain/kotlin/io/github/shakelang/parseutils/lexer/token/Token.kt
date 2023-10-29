package io.github.shakelang.shake.lexer.token

import io.github.shakelang.parseutils.lexer.token.TokenType


/**
 * The input of the [io.github.shakelang.shake.lexer.Lexer] gets converted into [Token]s. These get parsed
 * by the parser
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
open class Token<T : TokenType>
/**
 * Constructor for [Token]
 *
 * @param type the [Token.type] of the [Token]
 * @param value the [Token.value] of the [Token]
 * @param start the [Token.start] of the [Token]
 * @param end the [Token.end] of the [Token]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 *
 * @see Token
 * @see Token.type
 * @see Token.value
 * @see Token.start
 * @see Token.end
 *
 */(
    /**
     * The type of the [Token]
     *
     * @see Token
     */
    open val type: T,

    /**
     * The value of the [Token] (This is for identifiers, strings or numbers. If not necessary this is null)
     *
     * @see Token
     */
    open val value: String?,

    /**
     * The starting Position of the [Token]
     *
     * @see Token
     */
    open val start: Int,

    /**
     * The ending Position of the [Token]
     *
     * @see Token
     */
    open val end: Int
) {

    /**
     * Constructor for [Token]
     *
     * @param type the [Token.type] of the [Token]
     * @param start the [Token.start] of the [Token]
     * @param end the [Token.end] of the [Token]
     *
     * @see Token
     * @see Token.type
     * @see Token.start
     * @see Token.end
     *
     */
    constructor(
        type: T,
        start: Int,
        end: Int
    ) : this(type, null, start, end)

    override fun toString(): String {
        return "Token{type=$type, value=$value, start=$start, end=$end}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if(other is Byte) return other == this.type
        if (other == null || other !is Token<*>) return false
        return type == other.type &&
                value == other.value
    }

    override fun hashCode(): Int = hashAll(type, value)

    private fun hashAll(vararg vals: Any?): Int {
        var res = 0
        for (v in vals) {
            res += v.hashCode()
            res *= 31
        }
        return res
    }

}