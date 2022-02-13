package io.github.shakelang.shake.lexer.token


/**
 * The input of the [io.github.shakelang.shake.lexer.ShakeLexer] gets converted into [ShakeToken]s. These get parsed
 * by the parser
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
class ShakeToken
/**
 * Constructor for [ShakeToken]
 *
 * @param type the [ShakeToken.type] of the [ShakeToken]
 * @param value the [ShakeToken.value] of the [ShakeToken]
 * @param start the [ShakeToken.start] of the [ShakeToken]
 * @param end the [ShakeToken.end] of the [ShakeToken]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 *
 * @see ShakeToken
 * @see ShakeToken.type
 * @see ShakeToken.value
 * @see ShakeToken.start
 * @see ShakeToken.end
 *
 */(
    type: ShakeTokenType,
    value: String?,
    start: Int,
    end: Int = start
) : Token<ShakeTokenType>(type, value, start, end) {

    /**
     * Constructor for [ShakeToken]
     *
     * @param type the [type] of the [ShakeToken]
     * @param value the [value] of the [ShakeToken]
     * @param end the [end] position of the [ShakeToken]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     *
     * @see ShakeToken
     * @see type
     * @see value
     * @see start
     * @see end
     */
    constructor(type: ShakeTokenType, value: String?, end: Int) : this(type, value, end - type.length(value) + 1, end)

    /**
     * Constructor for [ShakeToken]
     *
     * @param type the [type] of the [ShakeToken]
     * @param start the [start] of the [ShakeToken]
     * @param end the [end] of the [ShakeToken]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     *
     * @see ShakeToken
     * @see type
     * @see start
     * @see end
     */
    constructor(type: ShakeTokenType, start: Int, end: Int) : this(type, null, start, end)

    /**
     * Constructor for [ShakeToken]
     *
     * @param type the [type] of the [ShakeToken]
     * @param end the [end] position of the [ShakeToken]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     *
     * @see ShakeToken
     * @see type
     * @see start
     * @see end
     */
    constructor(type: ShakeTokenType, end: Int) : this(type, null, end)
}