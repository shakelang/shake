package io.github.shakelang.shake.lexer

import io.github.shakelang.shake.lexer.token.TokenInputStream
import io.github.shakelang.shake.lexer.token.TokenType
import io.github.shakelang.parseutils.characters.Characters
import io.github.shakelang.parseutils.CompilerError
import io.github.shakelang.parseutils.characters.streaming.CharacterInputStream
import io.github.shakelang.parseutils.characters.position.Position
import kotlin.jvm.JvmOverloads

class Lexer(
    input: CharacterInputStream
): LexingBase(input) {


    fun makeTokens(): TokenInputStream {

        val tokens = mutableListOf<Byte>()
        val positions = mutableListOf<Int>()
        val values =  mutableListOf<String>()

        while (input.hasNext()) {
            val token = makeToken()
            tokens.add(token.type)
            positions.add(token.end)
            if(token.value != null) values.add(token.value)
        }

        return TokenInputStream(
            input.source.location,
            createPrimitiveByteArray(tokens),
            values.toTypedArray(),
            createPrimitiveIntArray(positions),
            input.positionMaker.createPositionMap()
        )
    }
}