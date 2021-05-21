package com.github.nsc.de.shake.parser

import com.github.nsc.de.shake.parser.Parser.ParserError
import com.github.nsc.de.shake.parser.node.IfNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestErrors {
    @Test
    fun testLPAREN() {
        val error = Assertions.assertThrows(
            ParserError::class.java
        ) { ParserTestUtil.parseSingle("<TestLPAREN>", "if test", IfNode::class.java) }

        // System.out.println(error.toString());
        Assertions.assertSame(3, error.start.index)
        Assertions.assertSame(6, error.end.index)
        Assertions.assertEquals("Expecting '('", error.details)
        Assertions.assertEquals("<TestLPAREN>:1:4", error.marker.source)
        Assertions.assertEquals("1  if test", error.marker.preview)
        Assertions.assertEquals("      ^^^^", error.marker.marker)

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }

    @Test
    fun testRPAREN() {
        val error = Assertions.assertThrows(
            ParserError::class.java
        ) { ParserTestUtil.parseSingle("<TestRPAREN>", "if(test{", IfNode::class.java) }

        // System.out.println(error.toString());
        Assertions.assertSame(7, error.start.index)
        Assertions.assertSame(7, error.end.index)
        Assertions.assertEquals("Expecting ')'", error.details)
        Assertions.assertEquals("<TestRPAREN>:1:8", error.marker.source)
        Assertions.assertEquals("1  if(test{", error.marker.preview)
        Assertions.assertEquals("          ^", error.marker.marker)

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }

    @Test
    fun testLCURL() {
        val error = Assertions.assertThrows(
            ParserError::class.java
        ) { ParserTestUtil.parseSingle("<TestLCURL>", "if(test) {", IfNode::class.java) }

        // System.out.println(error.toString());
        Assertions.assertSame(9, error.start.index)
        Assertions.assertSame(9, error.end.index)
        Assertions.assertEquals("Expecting '}'", error.details)
        Assertions.assertEquals("<TestLCURL>:1:10", error.marker.source)
        Assertions.assertEquals("1  if(test) {", error.marker.preview)
        Assertions.assertEquals("            ^", error.marker.marker)

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }

    @Test
    fun testExpectingSemicolon() {
        val error = Assertions.assertThrows(
            ParserError::class.java
        ) { ParserTestUtil.parseSingle("<TestAwaitSemicolonError>", "for(var i = 0 i<10) {", IfNode::class.java) }

        // System.out.println(error.toString());
        Assertions.assertSame(14, error.start.index)
        Assertions.assertSame(14, error.end.index)
        Assertions.assertEquals("Expecting semicolon at this point", error.details)
        Assertions.assertEquals("<TestAwaitSemicolonError>:1:15", error.marker.source)
        Assertions.assertEquals("1  for(var i = 0 i<10) {", error.marker.preview)
        Assertions.assertEquals("                 ^", error.marker.marker)

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }
}