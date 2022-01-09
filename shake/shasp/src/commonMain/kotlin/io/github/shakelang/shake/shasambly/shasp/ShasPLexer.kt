package io.github.shakelang.shake.lexer

import io.github.shakelang.parseutils.characters.streaming.CharacterInputStream
import io.github.shakelang.shake.lexer.token.ShasPToken
import io.github.shakelang.shake.lexer.token.stream.OnDemandLexingShasPTokenInputStream
import io.github.shakelang.shake.lexer.token.stream.TokenBasedShasPTokenInputStream

class ShasPLexer(
    input: CharacterInputStream
): ShasPLexingBase(input) {

    fun makeTokens(): TokenBasedShasPTokenInputStream {
        val tokens = mutableListOf<ShasPToken>()
        while (input.hasNext()) tokens.add(makeToken())
        return TokenBasedShasPTokenInputStream(input.source.location, tokens.toTypedArray(), input.positionMaker)
    }

    fun stream(): OnDemandLexingShasPTokenInputStream {
        return OnDemandLexingShasPTokenInputStream(input)
    }
}