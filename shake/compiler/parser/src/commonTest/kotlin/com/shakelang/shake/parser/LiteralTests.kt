package com.shakelang.shake.parser

import com.shakelang.shake.parser.node.values.factor.*
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class LiteralTests : FreeSpec({

    "basic string" {
        val node = ParserTestUtil.parseValue("<BasicStringTest>", "\"test\"", ShakeStringLiteralNode::class)
        node.value shouldBe "test"
    }

    "basic int" {
        val node = ParserTestUtil.parseValue("<BasicIntTest>", "123", ShakeIntegerLiteralNode::class)
        node.value shouldBe 123
    }

    "basic doubles" {
        val node = ParserTestUtil.parseValue("<BasicDoubleTest>", "123.123", ShakeFloatLiteralNode::class)
        node.value shouldBe 123.123
    }

    "basic char" {
        val node = ParserTestUtil.parseValue("<BasicCharTest>", "'a'", ShakeCharacterLiteralNode::class)
        node.value shouldBe 'a'
    }

    "basic true literal" {
        ParserTestUtil.parseValue("<BasicTrueTest>", "true", ShakeTrueLiteralNode::class)
    }

    "basic false literal" {
        ParserTestUtil.parseValue("<BasicFalseTest>", "false", ShakeFalseLiteralNode::class)
    }

    "basic null literal" {
        ParserTestUtil.parseValue("<BasicNullTest>", "null", ShakeNullLiteralNode::class)
    }

    "this literal" {
        ParserTestUtil.parseValue("<ThisTest>", "this", ShakeThisNode::class)
    }

    "super literal" {
        ParserTestUtil.parseValue("<SuperTest>", "super", ShakeSuperNode::class)
    }
})
