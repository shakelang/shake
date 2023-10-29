package io.github.shakelang.parseutils.lexer.token

import io.github.shakelang.shake.lexer.token.Token
import kotlin.test.Test
import kotlin.test.assertEquals

class TokenTests {

    enum class TokenType : io.github.shakelang.parseutils.lexer.token.TokenType {
        IDENTIFIER ;

        override val hasValue: Boolean
            get() = this == IDENTIFIER
        override fun length(value: String?): Int {
            return value?.length ?: 0
        }
    }

    @Test
    fun testWithPositionAndValue() {
        val token = Token(TokenType.IDENTIFIER, "test", 1, 4)
        assertEquals(TokenType.IDENTIFIER, token.type)
        assertEquals("test", token.value)
        assertEquals(1, token.start)
        assertEquals(4, token.end)
    }

    @Test
    fun testToString() {
        val token = Token(TokenType.IDENTIFIER, "test", 0, 4)
        assertEquals("Token{type=IDENTIFIER, value=test, start=0, end=4}", token.toString())
    }

}