package com.shakelang.shake.lexer

import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.util.parseutils.characters.source.CharacterSource
import com.shakelang.shake.util.parseutils.characters.streaming.SourceCharacterInputStream
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

@Suppress("unused")
class LexerTests : FreeSpec({

    fun createLexer(contents: String, source: String): ShakeLexer {
        val stream = SourceCharacterInputStream(CharacterSource.from(contents, source))
        return ShakeLexer(stream)
    }

    "make tokens" {
        val tokens = createLexer("10+7*3", "LexerTests#testMakeTokens()").makeTokens()
        var token = tokens.next()
        token.type shouldBe ShakeTokenType.INTEGER
        token.value shouldBe "10"
        token.start shouldBe 0
        token.end shouldBe 1
        token = tokens.next()
        token.type shouldBe ShakeTokenType.ADD
        token.value shouldBe null
        token.start shouldBe 2
        token.end shouldBe 2
        token = tokens.next()
        token.type shouldBe ShakeTokenType.INTEGER
        token.value shouldBe "7"
        token.start shouldBe 3
        token.end shouldBe 3
        token = tokens.next()
        token.type shouldBe ShakeTokenType.MUL
        token.value shouldBe null
        token.start shouldBe 4
        token.end shouldBe 4
        token = tokens.next()
        token.type shouldBe ShakeTokenType.INTEGER
        token.value shouldBe "3"
        token.start shouldBe 5
        token.end shouldBe 5
    }

    "stream" {
        val tokens = createLexer("10+7*3", "LexerTests#testStream()").stream()
        var token = tokens.next()
        token.type shouldBe ShakeTokenType.INTEGER
        token.value shouldBe "10"
        token.start shouldBe 0
        token.end shouldBe 1
        token = tokens.next()
        token.type shouldBe ShakeTokenType.ADD
        token.value shouldBe null
        token.start shouldBe 2
        token.end shouldBe 2
        token = tokens.next()
        token.type shouldBe ShakeTokenType.INTEGER
        token.value shouldBe "7"
        token.start shouldBe 3
        token.end shouldBe 3
        token = tokens.next()
        token.type shouldBe ShakeTokenType.MUL
        token.value shouldBe null
        token.start shouldBe 4
        token.end shouldBe 4
        token = tokens.next()
        token.type shouldBe ShakeTokenType.INTEGER
        token.value shouldBe "3"
        token.start shouldBe 5
        token.end shouldBe 5
    }
})
