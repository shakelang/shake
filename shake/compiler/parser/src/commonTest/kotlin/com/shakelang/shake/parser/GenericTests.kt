package com.shakelang.shake.parser

import com.shakelang.shake.parser.node.outer.ShakeFieldDeclarationNode
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class GenericTests :
    FreeSpec({

        "test variable with generic type" {
            val code = "val a: List<Int>"
            val field = ParserTestUtil.parseSingle("<GenericTest>", code, ShakeFieldDeclarationNode::class)
            field.name shouldBe "a"
        }
    })
