package com.shakelang.shake.parser

import com.shakelang.shake.parser.node.factor.*
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class LiteralTests: FreeSpec ({

    "basic string" {
        val node = ParserTestUtil.parseValue("<BasicStringTest>", "\"test\"", ShakeStringNode::class)
        node.value shouldBe "test"
    }

    "basic int" {
        val node = ParserTestUtil.parseValue("<BasicIntTest>", "123", ShakeIntegerNode::class)
        node.number shouldBe 123
    }

    "basic double" {
        val node = ParserTestUtil.parseValue("<BasicDoubleTest>", "123.123", ShakeDoubleNode::class)
        node.number shouldBe 123.123
    }

    "basic char" {
        val node = ParserTestUtil.parseValue("<BasicCharTest>", "'a'", ShakeCharacterNode::class)
        node.value shouldBe 'a'
    }

    "basic true literal" {
        ParserTestUtil.parseValue("<BasicTrueTest>", "true", ShakeLogicalTrueNode::class)
    }

    "basic false literal" {
        ParserTestUtil.parseValue("<BasicFalseTest>", "false", ShakeLogicalFalseNode::class)
    }

    "basic null literal" {
        ParserTestUtil.parseValue("<BasicNullTest>", "null", ShakeNullNode::class)
    }

    "this literal" {
        ParserTestUtil.parseValue("<ThisTest>", "this", ShakeThisNode::class)
    }

    "super literal" {
        ParserTestUtil.parseValue("<SuperTest>", "super", ShakeSuperNode::class)
    }

})