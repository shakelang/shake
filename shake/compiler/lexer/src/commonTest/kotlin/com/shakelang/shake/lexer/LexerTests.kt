package com.shakelang.shake.lexer

import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.util.parseutils.characters.source.CharacterSource
import com.shakelang.shake.util.parseutils.characters.streaming.SourceCharacterInputStream
import kotlin.test.Test
import kotlin.test.assertEquals

class LexerTests {

    @Test
    fun testMakeTokens() {
        val tokens = createLexer("10+7*3", "LexerTests#testMakeTokens()").makeTokens()
        var token = tokens.next()
        assertEquals(ShakeTokenType.INTEGER, token.type)
        assertEquals("10", token.value)
        assertEquals(0, token.start)
        assertEquals(1, token.end)
        token = tokens.next()
        assertEquals(ShakeTokenType.ADD, token.type)
        assertEquals(null, token.value)
        assertEquals(2, token.start)
        assertEquals(2, token.end)
        token = tokens.next()
        assertEquals(ShakeTokenType.INTEGER, token.type)
        assertEquals("7", token.value)
        assertEquals(3, token.start)
        assertEquals(3, token.end)
        token = tokens.next()
        assertEquals(ShakeTokenType.MUL, token.type)
        assertEquals(null, token.value)
        assertEquals(4, token.start)
        assertEquals(4, token.end)
        token = tokens.next()
        assertEquals(ShakeTokenType.INTEGER, token.type)
        assertEquals("3", token.value)
        assertEquals(5, token.start)
        assertEquals(5, token.end)
    }

    @Test
    fun testStream() {
        val tokens = createLexer("10+7*3", "LexerTests#testStream()").stream()
        var token = tokens.next()
        assertEquals(ShakeTokenType.INTEGER, token.type)
        assertEquals("10", token.value)
        assertEquals(0, token.start)
        assertEquals(1, token.end)
        token = tokens.next()
        assertEquals(ShakeTokenType.ADD, token.type)
        assertEquals(null, token.value)
        assertEquals(2, token.start)
        assertEquals(2, token.end)
        token = tokens.next()
        assertEquals(ShakeTokenType.INTEGER, token.type)
        assertEquals("7", token.value)
        assertEquals(3, token.start)
        assertEquals(3, token.end)
        token = tokens.next()
        assertEquals(ShakeTokenType.MUL, token.type)
        assertEquals(null, token.value)
        assertEquals(4, token.start)
        assertEquals(4, token.end)
        token = tokens.next()
        assertEquals(ShakeTokenType.INTEGER, token.type)
        assertEquals("3", token.value)
        assertEquals(5, token.start)
        assertEquals(5, token.end)
    }

    fun createLexer(constents: String, source: String): ShakeLexer {
        val stream = SourceCharacterInputStream(CharacterSource.from(constents, source))
        return ShakeLexer(stream)
    }
}
