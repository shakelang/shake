package com.shakelang.shake.lexer.token

import com.shakelang.shake.lexer.token.stream.ShakeTokenInputStream
import com.shakelang.util.parseutils.lexer.token.Token

/**
 * The input of the [com.shakelang.shake.lexer.ShakeLexer] gets converted into [ShakeToken]s. These get parsed
 * by the parser
 */
class ShakeToken(
    type: ShakeTokenType,
    value: String,
    start: Int,
    end: Int,
    context: ShakeTokenContext,
) : Token<ShakeToken, ShakeTokenType, ShakeTokenInputStream, ShakeTokenContext>(
    type,
    value,
    start,
    end,
    context,
) {
    override fun toString(): String {
        return "ShakeToken{type=$type, value=$value, start=$start, end=$end}"
    }
}
