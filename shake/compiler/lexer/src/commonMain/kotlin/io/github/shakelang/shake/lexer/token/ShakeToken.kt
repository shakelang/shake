package io.github.shakelang.shake.lexer.token

/**
 * The input of the [io.github.shakelang.shake.lexer.ShakeLexer] gets converted into [ShakeToken]s. These get parsed
 * by the parser
 */
class ShakeToken : Token<ShakeTokenType> {

    constructor(
        type: ShakeTokenType,
        value: String?,
        start: Int,
        end: Int
    ) : super(type, value, start, end)

    constructor(
        type: ShakeTokenType,
        start: Int,
        end: Int,
    ) : super(type, start, end)

    override fun toString(): String {
        return "ShakeToken{type=$type, value=$value, start=$start, end=$end}"
    }

}