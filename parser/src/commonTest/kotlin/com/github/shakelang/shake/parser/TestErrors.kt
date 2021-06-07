package com.github.shakelang.shake.parser

import com.github.shakelang.shake.parser.Parser.ParserError
import com.github.shakelang.shake.parser.node.IfNode
import kotlin.test.*

class TestErrors {
    @Test
    fun testLPAREN() {
        val error = assertFailsWith(ParserError::class) {
            ParserTestUtil.parseSingle("<TestLPAREN>", "if test", IfNode::class)
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
            ParserTestUtil.parseSingle("<TestRPAREN>", "if(test{", IfNode::class)
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
            ParserTestUtil.parseSingle("<TestLCURL>", "if(test) {", IfNode::class)
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
            ParserTestUtil.parseSingle("<TestAwaitSemicolonError>", "for(var i = 0 i<10) {", IfNode::class)
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