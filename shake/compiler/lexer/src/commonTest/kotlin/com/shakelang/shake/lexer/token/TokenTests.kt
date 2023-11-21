package com.shakelang.shake.lexer.token

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

@Suppress("unused")
class TokenTests : FreeSpec({

    "token one argument" {
        val token = ShakeToken(ShakeTokenType.IDENTIFIER, "test", 7, 10)
        token.type shouldBe ShakeTokenType.IDENTIFIER
        token.value shouldBe "test"
        token.start shouldBe 7
        token.end shouldBe 10
    }

    "token equals" {
        val token0 = ShakeToken(ShakeTokenType.IDENTIFIER, "test", 7, 10)
        val token1 = ShakeToken(ShakeTokenType.IDENTIFIER, "test", 7, 10)
        val token2 = ShakeToken(ShakeTokenType.IDENTIFIER, "test", 7, 11)
        val token3 = ShakeToken(ShakeTokenType.IDENTIFIER, "test2", 8, 10)
        val token4 = ShakeToken(ShakeTokenType.ASSIGN, 8, 10)

        token0 shouldBe token0
        token0 shouldBe token1
        token0 shouldBe token2
        token0 shouldNotBe token3
        token0 shouldNotBe token4

        token1 shouldBe token0
        token2 shouldBe token0
        token3 shouldNotBe token0
        token4 shouldNotBe token0
    }
})
