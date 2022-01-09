package io.github.shakelang.shake.lexer.token

import io.github.shakelang.shake.shasambly.shasp.token.ShasPTokenType


/**
 * The input of the [io.github.shakelang.shake.lexer.ShasPLexer] gets converted into [ShasPToken]s. These get parsed
 * by the parser
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
class ShasPToken
/**
 * Constructor for [ShasPToken]
 *
 * @param type the [ShasPToken.type] of the [ShasPToken]
 * @param value the [ShasPToken.value] of the [ShasPToken]
 * @param start the [ShasPToken.start] of the [ShasPToken]
 * @param end the [ShasPToken.end] of the [ShasPToken]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 *
 * @see ShasPToken
 * @see ShasPToken.type
 * @see ShasPToken.value
 * @see ShasPToken.start
 * @see ShasPToken.end
 *
 */(
    /**
     * The type of the [ShasPToken]
     *
     * @see ShasPToken
     */
    val type: ShasPTokenType,

    /**
     * The value of the [ShasPToken] (This is for identifiers, strings or numbers. If not necessary this is null)
     *
     * @see ShasPToken
     */
    val value: String?,

    /**
     * The starting Position of the [ShasPToken]
     *
     * @see ShasPToken
     */
    val start: Int,

    /**
     * The ending Position of the [ShasPToken]
     *
     * @see ShasPToken
     */
    val end: Int
) {

    /**
     * Constructor for [ShasPToken]
     *
     * @param type the [type] of the [ShasPToken]
     * @param value the [value] of the [ShasPToken]
     * @param end the [end] position of the [ShasPToken]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     *
     * @see ShasPToken
     * @see type
     * @see value
     * @see start
     * @see end
     */
    constructor(type: ShasPTokenType, value: String?, end: Int) : this(type, value, end - type.tokenLength(value) + 1, end)

    /**
     * Constructor for [ShasPToken]
     *
     * @param type the [type] of the [ShasPToken]
     * @param start the [start] of the [ShasPToken]
     * @param end the [end] of the [ShasPToken]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     *
     * @see ShasPToken
     * @see type
     * @see start
     * @see end
     */
    constructor(type: ShasPTokenType, start: Int, end: Int) : this(type, null, start, end)

    /**
     * Constructor for [ShasPToken]
     *
     * @param type the [type] of the [ShasPToken]
     * @param end the [end] position of the [ShasPToken]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     *
     * @see ShasPToken
     * @see type
     * @see start
     * @see end
     */
    constructor(type: ShasPTokenType, end: Int) : this(type, null, end)

    override fun toString(): String {
        return if (start == end) if (value != null) "" +
                "Token{" + "type=" + type + ", value=" + value + ", position=" + start + '}' else "Token{type=$type, position=$start}" else if (value != null) "" +
                "Token{" + "type=" + type + ", value=" + value + ", start=" + start + ", end=" + end + '}' else "Token{type=$type, position=$start, end=$end}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if(other is Byte) return other == this.type
        if (other == null || other !is ShasPToken) return false
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