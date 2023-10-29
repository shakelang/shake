package io.github.shakelang.parseutils.lexer.token

import io.github.shakelang.shake.lexer.token.Token
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class TokenTests {

    enum class TokenType : io.github.shakelang.parseutils.lexer.token.TokenType {
        IDENTIFIER, NUMBER;

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
    fun testWithPositionAndNoValue() {
        val token = Token(TokenType.NUMBER, 1, 4)
        assertEquals(TokenType.NUMBER, token.type)
        assertEquals(null, token.value)
        assertEquals(1, token.start)
        assertEquals(4, token.end)
    }

    @Test
    fun testToString() {
        val token = Token(TokenType.IDENTIFIER, "test", 0, 4)
        assertEquals("Token{type=IDENTIFIER, value=test, start=0, end=4}", token.toString())
    }

    @Test
    fun testHashCode() {
        val token = Token(TokenType.IDENTIFIER, "test", 0, 4)
        val vals = arrayOf(token.type, token.value)
        var res = 0
        for (v in vals) {
            res += v.hashCode()
            res *= 31
        }

        assertEquals(res, token.hashCode())
    }

    @Test
    fun testEquals() {
        val token = Token(TokenType.IDENTIFIER, "test", 0, 4)
        val token2 = Token(TokenType.IDENTIFIER, "test", 0, 4)
        val token3 = Token(TokenType.IDENTIFIER, "test", 0, 5)
        val token4 = Token(TokenType.IDENTIFIER, "test2", 0, 4)
        val token5 = Token(TokenType.IDENTIFIER, "test", 1, 4)
        val token6 = Token(TokenType.IDENTIFIER, "test", 0, 4)
        val token7 = Token(TokenType.NUMBER, "test", 0, 4)

        assertEquals(token, token)
        assertEquals(token, token2)
        assertEquals(token, token3)
        assertNotEquals(token, token4)
        assertEquals(token, token5)
        assertEquals(token, token6)
        assertNotEquals(token, token7)

        assertNotEquals<Token<*>?>(token, null)
        assertNotEquals<Any>(token, "test")

    }

}