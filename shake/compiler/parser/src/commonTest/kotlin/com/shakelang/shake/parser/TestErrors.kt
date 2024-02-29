package com.shakelang.shake.parser

import com.shakelang.shake.parser.impl.ShakeParserHelper
import com.shakelang.shake.parser.node.statements.ShakeIfNode
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class TestErrors : FreeSpec(
    {
        "test LPAREN" {
            val error = shouldThrow<ShakeParserHelper.ParserError> {
                ParserTestUtil.parseStatement("<TestLPAREN>", "if test", ShakeIfNode::class)
            }

            error.start.index shouldBe 3
            error.end.index shouldBe 6
            error.details shouldBe "Expecting '('"
            error.marker.source shouldBe "<TestLPAREN>:1:4"
            error.marker.preview shouldBe "1  if test"
            error.marker.marker shouldBe "      ^^^^"
        }

        "test RPAREN" {
            val error = shouldThrow<ShakeParserHelper.ParserError> {
                ParserTestUtil.parseStatement("<TestRPAREN>", "if(test{", ShakeIfNode::class)
            }
            error.start.index shouldBe 7
            error.end.index shouldBe 7
            error.details shouldBe "Expecting ')'"
            error.marker.source shouldBe "<TestRPAREN>:1:8"
            error.marker.preview shouldBe "1  if(test{"
            error.marker.marker shouldBe "          ^"
        }

        "test LCURL" {
            val error = shouldThrow<ShakeParserHelper.ParserError> {
                ParserTestUtil.parseStatement("<TestLCURL>", "if(test) {", ShakeIfNode::class)
            }
            error.start.index shouldBe 9
            error.end.index shouldBe 9
            error.details shouldBe "Expecting '}'"
            error.marker.source shouldBe "<TestLCURL>:1:10"
            error.marker.preview shouldBe "1  if(test) {"
            error.marker.marker shouldBe "            ^"
        }

        "test RCURL" {
            val error = shouldThrow<ShakeParserHelper.ParserError> {
                ParserTestUtil.parseStatement("<TestAwaitSemicolonError>", "for(var i = 0 i<10) {", ShakeIfNode::class)
            }

            error.start.index shouldBe 14
            error.end.index shouldBe 14
            error.details shouldBe "Expecting ';'"
            error.marker.source shouldBe "<TestAwaitSemicolonError>:1:15"
            error.marker.preview shouldBe "1  for(var i = 0 i<10) {"
            error.marker.marker shouldBe "                 ^"
        }
    },
)
