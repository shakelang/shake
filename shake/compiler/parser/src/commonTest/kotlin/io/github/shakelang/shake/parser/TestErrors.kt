package io.github.shakelang.shake.parser

import io.github.shakelang.shake.parser.ShakeParserImpl.ParserError
import io.github.shakelang.shake.parser.node.ShakeIfNode
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertSame

class TestErrors {
    @Test
    fun testLPAREN() {
        val error = assertFailsWith(ParserError::class) {
            ParserTestUtil.parseStatement("<TestLPAREN>", "if test", ShakeIfNode::class)
        }

        // System.out.println(error.toString());
        assertSame(3, error.start.index)
        assertSame(6, error.end.index)
        assertEquals("Expecting '('", error.details)
        assertEquals("<TestLPAREN>:1:4", error.marker.source)
        assertEquals("1  if test", error.marker.preview)
        assertEquals("      ^^^^", error.marker.marker)

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }

    @Test
    fun testRPAREN() {
        val error = assertFailsWith(ParserError::class) {
            ParserTestUtil.parseStatement("<TestRPAREN>", "if(test{", ShakeIfNode::class)
        }

        // System.out.println(error.toString());
        assertSame(7, error.start.index)
        assertSame(7, error.end.index)
        assertEquals("Expecting ')'", error.details)
        assertEquals("<TestRPAREN>:1:8", error.marker.source)
        assertEquals("1  if(test{", error.marker.preview)
        assertEquals("          ^", error.marker.marker)

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }

    @Test
    fun testLCURL() {
        val error = assertFailsWith(ParserError::class) {
            ParserTestUtil.parseStatement("<TestLCURL>", "if(test) {", ShakeIfNode::class)
        }

        // System.out.println(error.toString());
        assertSame(9, error.start.index)
        assertSame(9, error.end.index)
        assertEquals("Expecting '}'", error.details)
        assertEquals("<TestLCURL>:1:10", error.marker.source)
        assertEquals("1  if(test) {", error.marker.preview)
        assertEquals("            ^", error.marker.marker)

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }

    @Test
    fun testExpectingSemicolon() {
        val error = assertFailsWith(ParserError::class) {
            ParserTestUtil.parseStatement("<TestAwaitSemicolonError>", "for(var i = 0 i<10) {", ShakeIfNode::class)
        }

        // System.out.println(error.toString());
        assertSame(14, error.start.index)
        assertSame(14, error.end.index)
        assertEquals("Expecting semicolon at this point", error.details)
        assertEquals("<TestAwaitSemicolonError>:1:15", error.marker.source)
        assertEquals("1  for(var i = 0 i<10) {", error.marker.preview)
        assertEquals("                 ^", error.marker.marker)

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }
}