package io.github.shakelang.shake.lexer

import io.github.shakelang.shake.lexer.token.ShakeToken
import io.github.shakelang.shake.lexer.token.stream.ShakeOnDemandLexingTokenInputStream
import io.github.shakelang.shake.lexer.token.stream.ShakeTokenBasedTokenInputStream
import io.github.shakelang.shake.util.parseutils.characters.streaming.CharacterInputStream

class ShakeLexer(
    input: CharacterInputStream
) : ShakeLexingBase(input) {

    fun makeTokens(): ShakeTokenBasedTokenInputStream {
        val tokens = mutableListOf<ShakeToken>()
        while (input.hasNext()) tokens.add(makeToken())
        return ShakeTokenBasedTokenInputStream(input.source.location, tokens.toTypedArray(), input.positionMaker)
    }

    fun stream(): ShakeOnDemandLexingTokenInputStream {
        return ShakeOnDemandLexingTokenInputStream(input)
    }
}