package com.shakelang.shake.lexer

import com.shakelang.util.parseutils.characters.streaming.CharacterInputStream
import com.shakelang.util.parseutils.characters.streaming.SourceCharacterInputStream
import com.shakelang.util.parseutils.lexer.AbstractLexer
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

@Suppress("unused")
class TestErrors : FreeSpec(
    {

        "string must end error" {
            // String type 1 not finished
            val error = shouldThrow<AbstractLexer.LexerError> {
                val chars: CharacterInputStream = SourceCharacterInputStream("<tests>", "\"test")
                val lexer = ShakeLexer(chars)
                lexer.stream().next()
            }

            error.start.index shouldBe 4
            error.end.index shouldBe 4
            error.message shouldBe "String must end with a '\"'"
        }

        "unknown escape sequence error" {
            // Unknown escape sequence (using \a here)
            val error = shouldThrow<AbstractLexer.LexerError> {
                val chars: CharacterInputStream = SourceCharacterInputStream("<tests>", "\"\\a\"")
                val lexer = ShakeLexer(chars)
                lexer.stream().next()
            }

            error.start.index shouldBe 2
            error.end.index shouldBe 2
            error.message shouldBe "Unknown escape sequence '\\a'"
        }

        "expect hex character error" {
            // Wrong input to unicode character
            val error = shouldThrow<AbstractLexer.LexerError> {
                val chars: CharacterInputStream = SourceCharacterInputStream("<tests>", "\"\\uatea\"")
                val lexer = ShakeLexer(chars)
                lexer.stream().next()
            }

            error.start.index shouldBe 4
            error.end.index shouldBe 4
            error.message shouldBe "Expecting hex char"
        }

        "unexpected token error" {
            // Unexpected Token
            val error = shouldThrow<AbstractLexer.LexerError> {
                val chars: CharacterInputStream = SourceCharacterInputStream("<tests>", "\u00dc")
                val lexer = ShakeLexer(chars)
                lexer.stream().next()
            }

            error.start.index shouldBe 0
            error.end.index shouldBe 0
            error.message shouldBe "Unrecognised Token: '\u00dc'"
        }
    },
)
