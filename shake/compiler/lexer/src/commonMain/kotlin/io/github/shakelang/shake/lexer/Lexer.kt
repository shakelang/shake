package io.github.shakelang.shake.lexer

import io.github.shakelang.parseutils.characters.streaming.CharacterInputStream
import io.github.shakelang.shake.lexer.token.stream.DataBasedTokenInputStream

class Lexer(
    input: CharacterInputStream
): LexingBase(input) {


    fun makeTokens(): DataBasedTokenInputStream {

        val tokens = mutableListOf<Byte>()
        val positions = mutableListOf<Int>()
        val values =  mutableListOf<String>()

        while (input.hasNext()) {
            val token = makeToken()
            tokens.add(token.type)
            positions.add(token.end)
            if(token.value != null) values.add(token.value)
        }

        return DataBasedTokenInputStream(
            input.source.location,
            createPrimitiveByteArray(tokens),
            values.toTypedArray(),
            createPrimitiveIntArray(positions),
            input.positionMaker.createPositionMap()
        )
    }
}