package io.github.shakelang.shake.lexer.token.stream

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.parseutils.lexer.token.stream.TokenBasedTokenInputStream
import io.github.shakelang.shake.lexer.token.ShakeToken
import io.github.shakelang.shake.lexer.token.ShakeTokenType

/**
 * A [ShakeTokenBasedTokenInputStream] provides the [ShakeToken]s for a Parser. It is
 * created by the [io.github.shakelang.shake.lexer.ShakeLexer]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
class ShakeTokenBasedTokenInputStream
(
    override val source: String,

    /**
     * The tokenTypes that are contained in the [ShakeTokenBasedTokenInputStream]
     */
    tokens: Array<ShakeToken>,
    map: PositionMap

) : ShakeTokenInputStream, TokenBasedTokenInputStream<ShakeTokenType, ShakeToken>(tokens, map)