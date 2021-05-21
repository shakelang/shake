package com.github.nsc.de.shake.lexer.token

import java.util.*

/**
 * The input of the [com.github.nsc.de.shake.lexer.Lexer] gets converted into [Token]s. These get parsed
 * by the parser
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
class Token
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
    val type: Byte,
    /**
     * The value of the [Token] (This is for identifiers, strings or numbers. If not necessary this is null)
     *
     * @see Token
     */
    val value: String?,
    /**
     * The starting [com.github.nsc.de.shake.lexer.characterinput.position.Position] of the [Token]
     *
     * @see Token
     */
    val start: Int,
    /**
     * The ending [com.github.nsc.de.shake.lexer.characterinput.position.Position] of the [Token]
     *
     * @see Token
     */
    val end: Int
) {

    /**
     * Constructor for [Token]
     *
     * @param type the [type] of the [Token]
     * @param value the [value] of the [Token]
     * @param position the [start] and [end] and of the [Token]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     *
     * @see Token
     * @see type
     * @see value
     * @see start
     * @see end
     */
    constructor(type: Byte, value: String?, position: Int) : this(type, value, position, position) {}

    /**
     * Constructor for [Token]
     *
     * @param type the [type] of the [Token]
     * @param start the [start] of the [Token]
     * @param end the [end] of the [Token]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     *
     * @see Token
     * @see type
     * @see start
     * @see end
     */
    constructor(type: Byte, start: Int, end: Int) : this(type, null, start, end) {}

    /**
     * Constructor for [Token]
     *
     * @param type the [type] of the [Token]
     * @param position the [start] and [end] and of the [Token]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     *
     * @see Token
     * @see type
     * @see start
     * @see end
     */
    constructor(type: Byte, position: Int) : this(type, null, position, position) {}

    override fun toString(): String {
        return if (start == end) if (value != null) "" +
                "Token{" + "type=" + type + ", value=" + value + ", position=" + start + '}' else "Token{type=$type, position=$start}" else if (value != null) "" +
                "Token{" + "type=" + type + ", value=" + value + ", start=" + start + ", end=" + end + '}' else "Token{type=$type, position=$start, end=$end}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val token = other as Token
        return type == token.type &&
                value == token.value
    }

    override fun hashCode(): Int {
        return Objects.hash(type, value)
    }
}