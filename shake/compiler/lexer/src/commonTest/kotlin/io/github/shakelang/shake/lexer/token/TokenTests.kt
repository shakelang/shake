package io.github.shakelang.shake.lexer.token

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class TokenTests {

    @Test
    fun testTokenOneArgument() {
        val token = Token(TokenType.IDENTIFIER, "test", 10)
        assertEquals(TokenType.IDENTIFIER, token.type)
        assertEquals("test", token.value)
        assertEquals(7, token.start)
        assertEquals(10, token.end)
    }

    @Test
    fun testTokenTwoArguments() {
        val token = Token(TokenType.IDENTIFIER, "test", 7, 10)
        assertEquals(TokenType.IDENTIFIER, token.type)
        assertEquals("test", token.value)
        assertEquals(7, token.start)
        assertEquals(10, token.end)
    }

    @Test
    fun testTokenEquals() {
        val token0 = Token(TokenType.IDENTIFIER, "test", 7, 10)
        val token1 = Token(TokenType.IDENTIFIER, "test", 7, 10)
        val token2 = Token(TokenType.IDENTIFIER, "test", 7, 11)
        val token3 = Token(TokenType.IDENTIFIER, "test2", 8, 10)
        val token4 = Token(TokenType.ASSIGN, 8, 10)

        assertEquals(token0, token0)
        assertEquals(token0, token1)
        assertEquals(token0, token2)
        assertNotEquals(token0, token3)
        assertNotEquals(token0, token4)

        assertEquals(token1,  token0)
        assertEquals(token2,  token0)
        assertNotEquals(token3,  token0)
        assertNotEquals(token4,  token0)
    }

}