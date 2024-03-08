package com.shakelang.shake.lexer.token.stream

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.lexer.token.ShakeTokenContext
import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.util.parseutils.characters.position.PositionMap
import com.shakelang.util.parseutils.lexer.token.TokenFactory
import com.shakelang.util.parseutils.lexer.token.stream.FactoryTokenInputStream
import com.shakelang.util.parseutils.lexer.token.stream.TokenInputStream

/**
 * A [ShakeTokenInputStream] provides the [ShakeToken]s for a Parser. It is
 * created by the [com.shakelang.shake.lexer.ShakeLexer]
 */
@Suppress("unused")
interface ShakeTokenInputStream : TokenInputStream<
    ShakeTokenInputStream,
    ShakeTokenType,
    ShakeToken,
    ShakeTokenContext,
    > {
    fun skipIgnorable(): ShakeTokenInputStream {
        while (hasNext() && peek().type == ShakeTokenType.LINE_SEPARATOR) {
            skip()
        }
        return this
    }
}

class ShakeTokenInputStreamImpl(
    tokens: TokenFactory<ShakeToken>,
    map: PositionMap,
) : ShakeTokenInputStream, FactoryTokenInputStream<
    ShakeTokenInputStream,
    ShakeTokenType,
    ShakeToken,
    ShakeTokenContext,
    >(tokens, map, ShakeTokenType.EOF)
