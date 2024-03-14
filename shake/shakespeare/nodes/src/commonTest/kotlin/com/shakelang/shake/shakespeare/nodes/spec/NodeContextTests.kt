package com.shakelang.shake.shakespeare.nodes.spec

import com.shakelang.shake.lexer.token.ShakeTokenType
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class NodeContextTests : FreeSpec({

    "output stream should pipe into string builder" {
        val context = NodeContext()
        context.stream.write("Hello, World!".encodeToByteArray())
        context.built.toString() shouldBe "Hello, World!"
    }

    "character source should source from string builder" {
        val context = NodeContext()
        context.built.append("Hello, World!")
        context.characterSource.all shouldBe "Hello, World!".toCharArray()
        context.characterSource.length shouldBe 13
        context.characterSource[0, 5] shouldBe "Hello".toCharArray()
    }

    "position maker should track position" {
        val context = NodeContext()
        context.print("Hello, World!")
        context.positionMaker.line shouldBe 1
        context.positionMaker.column shouldBe 13
        context.positionMaker.index shouldBe 12
        context.print("\n")
        context.positionMaker.line shouldBe 2
        context.positionMaker.column shouldBe 1
        context.positionMaker.index shouldBe 13
        context.print("Hello, World!")
        context.positionMaker.line shouldBe 2
        context.positionMaker.column shouldBe 14
        context.positionMaker.index shouldBe 26
    }

    "create token should create tokens" {
        val context = NodeContext()
        val token = context.createToken(ShakeTokenType.COLON)
        token.type shouldBe ShakeTokenType.COLON
        token.value shouldBe ":"
        token.start shouldBe 0
        token.end shouldBe 0
        context.built.toString() shouldBe ":"
    }

    "create token with value should create tokens" {
        val context = NodeContext()
        val token = context.createToken(ShakeTokenType.IDENTIFIER, "TEST")
        token.type shouldBe ShakeTokenType.IDENTIFIER
        token.value shouldBe "TEST"
        token.start shouldBe 0
        token.end shouldBe 3
        context.built.toString() shouldBe "TEST"
    }
})
