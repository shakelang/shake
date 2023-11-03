package io.github.shakelang.shake.util.parseutils.lexer.token.streaming

import io.github.shakelang.shake.lexer.token.Token
import io.github.shakelang.shake.util.parseutils.characters.source.CharacterSource
import io.github.shakelang.shake.util.parseutils.characters.streaming.SourceCharacterInputStream
import io.github.shakelang.shake.util.parseutils.lexer.LexingBase
import io.github.shakelang.shake.util.parseutils.lexer.token.stream.OnDemandLexingTokenInputStream
import kotlin.test.Test
import kotlin.test.assertEquals

class OnDemandLexingTokenInputStreamTests {
    enum class TokenType : io.github.shakelang.shake.util.parseutils.lexer.token.TokenType {
        IDENTIFIER, NUMBER, STRING;

        override val hasValue: Boolean
            get() = this == IDENTIFIER

        override fun length(value: String?): Int {
            return value?.length ?: 0
        }
    }

    class TestLexer(
        tokens: List<Token<TokenType>>,
    ) : LexingBase<TokenType, Token<TokenType>>(SourceCharacterInputStream(CharacterSource.from("qwgg".toCharArray(), "a"))) {

        val tokens = tokens.toMutableList()
        override fun makeToken(): Token<TokenType> {
            return tokens.removeAt(0)
        }
    }

    @Test
    fun testConstruct() {
        val tokens = mutableListOf<Token<TokenType>>()
        tokens.add(Token(TokenType.IDENTIFIER, "test", 0, 0))
        tokens.add(Token(TokenType.NUMBER, "123", 0, 0))
        tokens.add(Token(TokenType.STRING, "\"test\"", 0, 0))
        val lexer = TestLexer(tokens)
        OnDemandLexingTokenInputStream(lexer)
    }

    @Test
    fun testNext() {
        val tokens = mutableListOf<Token<TokenType>>()
        tokens.add(Token(TokenType.IDENTIFIER, "test", 0, 0))
        tokens.add(Token(TokenType.NUMBER, "123", 0, 0))
        tokens.add(Token(TokenType.STRING, "\"test\"", 0, 0))
        val lexer = TestLexer(tokens)
        val stream = OnDemandLexingTokenInputStream(lexer)
        assertEquals(tokens[0], stream.next())
        assertEquals(tokens[1], stream.next())
        assertEquals(tokens[2], stream.next())
    }



}