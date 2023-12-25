package com.shakelang.shake.lexer

import com.shakelang.util.parseutils.characters.streaming.CharacterInputStream
import com.shakelang.util.parseutils.characters.streaming.SourceCharacterInputStream
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

@Suppress("unused")
class TestErrors : FreeSpec({

    "string must end error" {
        // String type 1 not finished
        val error = shouldThrow<ShakeLexingBase.LexerError> {
            val chars: CharacterInputStream = SourceCharacterInputStream("<tests>", "\"test")
            val lexer = ShakeLexer(chars)
            lexer.makeTokens()
        }

        error.start.index shouldBe 4
        error.end.index shouldBe 4
        error.details shouldBe "String must end with a '\"'"
        error.marker.source shouldBe "<tests>:1:5"
        error.marker.preview shouldBe "1  \"test"
        error.marker.marker shouldBe "       ^"
    }

    "unknown escape sequence error" {
        // Unknown escape sequence (using \a here)
        val error = shouldThrow<ShakeLexingBase.LexerError> {
            val chars: CharacterInputStream = SourceCharacterInputStream("<tests>", "\"\\a\"")
            val lexer = ShakeLexer(chars)
            lexer.makeTokens()
        }

        error.start.index shouldBe 2
        error.end.index shouldBe 2
        error.details shouldBe "Unknown escape sequence '\\a'"
        error.marker.source shouldBe "<tests>:1:3"
        error.marker.preview shouldBe "1  \"\\a\""
        error.marker.marker shouldBe "     ^"
    }

    "expect hex character error" {
        // Wrong input to unicode character
        val error = shouldThrow<ShakeLexingBase.LexerError> {
            val chars: CharacterInputStream = SourceCharacterInputStream("<tests>", "\"\\uatea\"")
            val lexer = ShakeLexer(chars)
            lexer.makeTokens()
        }

        error.start.index shouldBe 4
        error.end.index shouldBe 4
        error.details shouldBe "Expecting hex char"
        error.marker.source shouldBe "<tests>:1:5"
        error.marker.preview shouldBe "1  \"\\uatea\""
        error.marker.marker shouldBe "       ^"
    }

    "unexpected token error" {
        // Unexpected Token
        val error = shouldThrow<ShakeLexingBase.LexerError> {
            val chars: CharacterInputStream = SourceCharacterInputStream("<tests>", "\u00dc")
            val lexer = ShakeLexer(chars)
            lexer.makeTokens()
        }

        error.start.index shouldBe 0
        error.end.index shouldBe 0
        error.details shouldBe "Unrecognised Token: '\u00dc'"
        error.exceptionName shouldBe "UnexpectedTokenError"
        error.marker.source shouldBe "<tests>:1:1"
        error.marker.preview shouldBe "1  \u00dc"
        error.marker.marker shouldBe "   ^"
    }
})
