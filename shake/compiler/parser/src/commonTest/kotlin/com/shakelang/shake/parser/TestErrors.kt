package com.shakelang.shake.parser

import com.shakelang.shake.parser.node.statements.ShakeIfNode
import com.shakelang.util.parseutils.parser.AbstractParser
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class TestErrors : FreeSpec(
    {
        "test LPAREN" {
            val error = shouldThrow<AbstractParser.ParserError> {
                ParserTestUtil.parseStatement("<TestLPAREN>", "if test", ShakeIfNode::class)
            }

            error.start.index shouldBe 3
            error.end.index shouldBe 6
            error.message shouldBe "Expecting '('"
        }

        "test RPAREN" {
            val error = shouldThrow<AbstractParser.ParserError> {
                ParserTestUtil.parseStatement("<TestRPAREN>", "if(test{", ShakeIfNode::class)
            }
            error.start.index shouldBe 7
            error.end.index shouldBe 7
            error.message shouldBe "Expecting ')'"
        }

        "test LCURL" {
            val error = shouldThrow<AbstractParser.ParserError> {
                ParserTestUtil.parseStatement("<TestLCURL>", "if(test) {", ShakeIfNode::class)
            }
            error.start.index shouldBe 9
            error.end.index shouldBe 9
            error.message shouldBe "Expecting '}'"
        }

        "test RCURL" {
            val error = shouldThrow<AbstractParser.ParserError> {
                ParserTestUtil.parseStatement("<TestAwaitSemicolonError>", "for(var i = 0 i<10) {", ShakeIfNode::class)
            }

            error.start.index shouldBe 14
            error.end.index shouldBe 14
            error.message shouldBe "Expecting ';'"
        }
    },
)
