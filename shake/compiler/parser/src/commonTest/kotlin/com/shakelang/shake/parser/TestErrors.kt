package com.shakelang.shake.parser

import com.shakelang.shake.parser.ShakeParserImpl.ParserError
import com.shakelang.shake.parser.node.ShakeIfNode
import io.kotest.core.spec.style.FreeSpec
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertSame

class TestErrors : FreeSpec({
    "test LPAREN" {
        val error = assertFailsWith(ParserError::class) {
            ParserTestUtil.parseStatement("<TestLPAREN>", "if test", ShakeIfNode::class)
        }

        assertSame(3, error.start.index)
        assertSame(6, error.end.index)
        assertEquals("Expecting '('", error.details)
        assertEquals("<TestLPAREN>:1:4", error.marker.source)
        assertEquals("1  if test", error.marker.preview)
        assertEquals("      ^^^^", error.marker.marker)
    }

    "test RPAREN" {
        val error = assertFailsWith(ParserError::class) {
            ParserTestUtil.parseStatement("<TestRPAREN>", "if(test{", ShakeIfNode::class)
        }
        assertSame(7, error.start.index)
        assertSame(7, error.end.index)
        assertEquals("Expecting ')'", error.details)
        assertEquals("<TestRPAREN>:1:8", error.marker.source)
        assertEquals("1  if(test{", error.marker.preview)
        assertEquals("          ^", error.marker.marker)

    }

    "test LCURL" {
        val error = assertFailsWith(ParserError::class) {
            ParserTestUtil.parseStatement("<TestLCURL>", "if(test) {", ShakeIfNode::class)
        }
        assertSame(9, error.start.index)
        assertSame(9, error.end.index)
        assertEquals("Expecting '}'", error.details)
        assertEquals("<TestLCURL>:1:10", error.marker.source)
        assertEquals("1  if(test) {", error.marker.preview)
        assertEquals("            ^", error.marker.marker)
    }


    "test RCURL" {
        val error = assertFailsWith(ParserError::class) {
            ParserTestUtil.parseStatement("<TestAwaitSemicolonError>", "for(var i = 0 i<10) {", ShakeIfNode::class)
        }

        assertSame(14, error.start.index)
        assertSame(14, error.end.index)
        assertEquals("Expecting semicolon at this point", error.details)
        assertEquals("<TestAwaitSemicolonError>:1:15", error.marker.source)
        assertEquals("1  for(var i = 0 i<10) {", error.marker.preview)
        assertEquals("                 ^", error.marker.marker)
    }
})