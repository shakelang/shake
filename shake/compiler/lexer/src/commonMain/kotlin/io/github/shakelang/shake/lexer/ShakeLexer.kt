package io.github.shakelang.shake.lexer

import io.github.shakelang.parseutils.characters.streaming.CharacterInputStream
import io.github.shakelang.shake.lexer.token.ShakeToken
import io.github.shakelang.shake.lexer.token.ShakeTokenType
import io.github.shakelang.shake.lexer.token.stream.ShakeDataBasedTokenInputStream
import io.github.shakelang.shake.lexer.token.stream.ShakeOnDemandLexingTokenInputStream
import io.github.shakelang.shake.lexer.token.stream.ShakeTokenBasedTokenInputStream

class ShakeLexer(
    input: CharacterInputStream
): ShakeLexingBase(input) {

    fun makeTokens(): ShakeTokenBasedTokenInputStream {
        val tokens = mutableListOf<ShakeToken>()
        while (input.hasNext()) tokens.add(makeToken())
        return ShakeTokenBasedTokenInputStream(input.source.location, tokens.toTypedArray(), input.positionMaker)
    }


    fun makeTokens2(): ShakeDataBasedTokenInputStream {

        val tokens = mutableListOf<ShakeTokenType>()
        val positions = mutableListOf<Int>()
        val values =  mutableListOf<String>()

        while (input.hasNext()) {
            val token = makeToken()
            tokens.add(token.type)
            positions.add(token.end)
            token.value?.let { values.add(it) }
        }

        return ShakeDataBasedTokenInputStream(
            input.source.location,
            tokens.toTypedArray(),
            values.toTypedArray(),
            positions.toIntArray(),
            input.positionMaker.createPositionMap()
        )
    }

    fun stream(): ShakeOnDemandLexingTokenInputStream {
        return ShakeOnDemandLexingTokenInputStream(input)
    }
}