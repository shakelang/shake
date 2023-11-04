package io.github.shakelang.shake.shasambly.shasp.lexer

import io.github.shakelang.shake.lexer.token.ShasPToken
import io.github.shakelang.shake.shasambly.shasp.lexer.token.OnDemandLexingShasPTokenInputStream
import io.github.shakelang.shake.shasambly.shasp.lexer.token.ShasPTokenBasedInputStream
import io.github.shakelang.shake.util.parseutils.characters.streaming.CharacterInputStream

class ShasPLexer(
    input: CharacterInputStream
): ShasPLexingBase(input) {

    fun makeTokens(): ShasPTokenBasedInputStream {
        val tokens = mutableListOf<ShasPToken>()
        while (input.hasNext()) tokens.add(makeToken())
        return ShasPTokenBasedInputStream(tokens.toTypedArray(), input.positionMaker.createPositionMap())
    }

    fun stream(): OnDemandLexingShasPTokenInputStream {
        return OnDemandLexingShasPTokenInputStream(input)
    }
}