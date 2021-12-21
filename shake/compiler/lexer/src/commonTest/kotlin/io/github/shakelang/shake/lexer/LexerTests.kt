package io.github.shakelang.shake.lexer

import io.github.shakelang.parseutils.characters.source.CharacterSource
import io.github.shakelang.parseutils.characters.streaming.SourceCharacterInputStream
import io.github.shakelang.shake.lexer.token.TokenType
import kotlin.test.Test
import kotlin.test.assertEquals

class LexerTests {

    @Test
    fun testMakeTokens() {

        val tokens = createLexer("10+7*3", "LexerTests#testMakeTokens()").makeTokens()
        var token = tokens.next()
        assertEquals(TokenType.INTEGER, token.type)
        assertEquals("10", token.value)
        assertEquals(0, token.start)
        assertEquals(1, token.end)
        token = tokens.next()
        assertEquals(TokenType.ADD, token.type)
        assertEquals(null, token.value)
        assertEquals(2, token.start)
        assertEquals(2, token.end)
        token = tokens.next()
        assertEquals(TokenType.INTEGER, token.type)
        assertEquals("7", token.value)
        assertEquals(3, token.start)
        assertEquals(3, token.end)
        token = tokens.next()
        assertEquals(TokenType.MUL, token.type)
        assertEquals(null, token.value)
        assertEquals(4, token.start)
        assertEquals(4, token.end)
        token = tokens.next()
        assertEquals(TokenType.INTEGER, token.type)
        assertEquals("3", token.value)
        assertEquals(5, token.start)
        assertEquals(5, token.end)

    }

    @Test
    fun testMakeTokens2() {

        val tokens = createLexer("10+7*3", "LexerTests#testMakeTokens2()").makeTokens2()
        var token = tokens.next()
        assertEquals(TokenType.INTEGER, token.type)
        assertEquals("10", token.value)
        assertEquals(0, token.start)
        assertEquals(1, token.end)
        token = tokens.next()
        assertEquals(TokenType.ADD, token.type)
        assertEquals(null, token.value)
        assertEquals(2, token.start)
        assertEquals(2, token.end)
        token = tokens.next()
        assertEquals(TokenType.INTEGER, token.type)
        assertEquals("7", token.value)
        assertEquals(3, token.start)
        assertEquals(3, token.end)
        token = tokens.next()
        assertEquals(TokenType.MUL, token.type)
        assertEquals(null, token.value)
        assertEquals(4, token.start)
        assertEquals(4, token.end)
        token = tokens.next()
        assertEquals(TokenType.INTEGER, token.type)
        assertEquals("3", token.value)
        assertEquals(5, token.start)
        assertEquals(5, token.end)

    }

    @Test
    fun testStream() {

        val tokens = createLexer("10+7*3", "LexerTests#testStream()").stream()
        var token = tokens.next()
        assertEquals(TokenType.INTEGER, token.type)
        assertEquals("10", token.value)
        assertEquals(0, token.start)
        assertEquals(1, token.end)
        token = tokens.next()
        assertEquals(TokenType.ADD, token.type)
        assertEquals(null, token.value)
        assertEquals(2, token.start)
        assertEquals(2, token.end)
        token = tokens.next()
        assertEquals(TokenType.INTEGER, token.type)
        assertEquals("7", token.value)
        assertEquals(3, token.start)
        assertEquals(3, token.end)
        token = tokens.next()
        assertEquals(TokenType.MUL, token.type)
        assertEquals(null, token.value)
        assertEquals(4, token.start)
        assertEquals(4, token.end)
        token = tokens.next()
        assertEquals(TokenType.INTEGER, token.type)
        assertEquals("3", token.value)
        assertEquals(5, token.start)
        assertEquals(5, token.end)

    }

    fun createLexer(constents: String, source: String): Lexer {
        val stream = SourceCharacterInputStream(CharacterSource.from(constents, source))
        return Lexer(stream)
    }

}