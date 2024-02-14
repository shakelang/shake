package com.shakelang.shake.lexer

import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.util.parseutils.characters.streaming.CharacterInputStream
import com.shakelang.util.parseutils.characters.streaming.SourceCharacterInputStream
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

@Suppress("unused")
class TokenPosition : FreeSpec(
    {

        "get basic position" {
            val chars: CharacterInputStream = SourceCharacterInputStream("<tests>", "val test = 10;")
            val lexer = ShakeLexer(chars)
            val input = lexer.makeTokens()
            input.peek().type shouldBe ShakeTokenType.KEYWORD_VAL
            input.peek().value shouldBe null
            var start = input.map.resolve(input.peek().start)
            var end = input.map.resolve(input.peek().end)
            start.index shouldBe 0
            start.column shouldBe 1
            start.line shouldBe 1
            end.index shouldBe 2
            end.column shouldBe 3
            end.line shouldBe 1
            input.skip()
            input.peek().type shouldBe ShakeTokenType.IDENTIFIER
            input.peek().value shouldBe "test"
            start = input.map.resolve(input.peek().start)
            end = input.map.resolve(input.peek().end)
            start.index shouldBe 4
            start.column shouldBe 5
            start.line shouldBe 1
            end.index shouldBe 7
            end.column shouldBe 8
            end.line shouldBe 1
            input.skip()
            input.peek().type shouldBe ShakeTokenType.ASSIGN
            input.peek().value shouldBe null
            start = input.map.resolve(input.peek().start)
            end = input.map.resolve(input.peek().end)
            start.index shouldBe 9
            start.column shouldBe 10
            start.line shouldBe 1
            end.index shouldBe 9
            end.column shouldBe 10
            end.line shouldBe 1
            input.skip()
            input.peek().type shouldBe ShakeTokenType.INTEGER
            input.peek().value shouldBe "10"
            start = input.map.resolve(input.peek().start)
            end = input.map.resolve(input.peek().end)
            start.index shouldBe 11
            start.column shouldBe 12
            start.line shouldBe 1
            end.index shouldBe 12
            end.column shouldBe 13
            end.line shouldBe 1
            input.skip()
            input.peek().type shouldBe ShakeTokenType.SEMICOLON
            input.peek().value shouldBe null
            start = input.map.resolve(input.peek().start)
            end = input.map.resolve(input.peek().end)
            start.index shouldBe 13
            start.column shouldBe 14
            start.line shouldBe 1
            end.index shouldBe 13
            end.column shouldBe 14
            end.line shouldBe 1
        }

        "get multi line position" {
            val chars: CharacterInputStream = SourceCharacterInputStream("<tests>", "val test\n  = \n10;")
            val lexer = ShakeLexer(chars)
            val input = lexer.makeTokens()
            input.peek().type shouldBe ShakeTokenType.KEYWORD_VAL
            input.peek().value shouldBe null
            var start = input.map.resolve(input.peek().start)
            var end = input.map.resolve(input.peek().end)
            start.index shouldBe 0
            start.column shouldBe 1
            start.line shouldBe 1
            end.index shouldBe 2
            end.column shouldBe 3
            end.line shouldBe 1
            input.skip()
            input.peek().type shouldBe ShakeTokenType.IDENTIFIER
            input.peek().value shouldBe "test"
            start = input.map.resolve(input.peek().start)
            end = input.map.resolve(input.peek().end)
            start.index shouldBe 4
            start.column shouldBe 5
            start.line shouldBe 1
            end.index shouldBe 7
            end.column shouldBe 8
            end.line shouldBe 1
            input.skip()
            input.peek().type shouldBe ShakeTokenType.LINE_SEPARATOR
            input.peek().value shouldBe null
            start = input.map.resolve(input.peek().start)
            end = input.map.resolve(input.peek().end)
            start.index shouldBe 8
            start.column shouldBe 9
            start.line shouldBe 1
            end.index shouldBe 8
            end.column shouldBe 9
            end.line shouldBe 1
            input.skip()
            input.peek().type shouldBe ShakeTokenType.ASSIGN
            input.peek().value shouldBe null
            start = input.map.resolve(input.peek().start)
            end = input.map.resolve(input.peek().end)
            start.index shouldBe 11
            start.column shouldBe 3
            start.line shouldBe 2
            end.index shouldBe 11
            end.column shouldBe 3
            end.line shouldBe 2
            input.skip()
            input.peek().type shouldBe ShakeTokenType.LINE_SEPARATOR
            input.peek().value shouldBe null
            start = input.map.resolve(input.peek().start)
            end = input.map.resolve(input.peek().end)
            start.index shouldBe 13
            start.column shouldBe 5
            start.line shouldBe 2
            end.index shouldBe 13
            end.column shouldBe 5
            end.line shouldBe 2
            input.skip()
            input.peek().type shouldBe ShakeTokenType.INTEGER
            input.peek().value shouldBe "10"
            start = input.map.resolve(input.peek().start)
            end = input.map.resolve(input.peek().end)
            start.index shouldBe 14
            start.column shouldBe 1
            start.line shouldBe 3
            end.index shouldBe 15
            end.column shouldBe 2
            end.line shouldBe 3
            input.skip()
            input.peek().type shouldBe ShakeTokenType.SEMICOLON
            input.peek().value shouldBe null
            start = input.map.resolve(input.peek().start)
            end = input.map.resolve(input.peek().end)
            start.index shouldBe 16
            start.column shouldBe 3
            start.line shouldBe 3
            end.index shouldBe 16
            end.column shouldBe 3
            end.line shouldBe 3
        }
    },
)
