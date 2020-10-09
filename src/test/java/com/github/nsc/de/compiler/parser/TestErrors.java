package com.github.nsc.de.compiler.parser;

import com.github.nsc.de.compiler.util.Formatting;
import com.github.nsc.de.compiler.parser.node.IfNode;
import com.github.nsc.de.compiler.parser.parser.Parser;
import org.junit.jupiter.api.Test;

import static com.github.nsc.de.compiler.parser.ParserTestUtil.parseSingle;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestErrors {

    @Test
    public void testLPAREN() {
        Parser.ParserError error = assertThrows(Parser.ParserError.class, () ->
                parseSingle("<TestLPAREN>", "if test", IfNode.class)
        );

        System.out.println(error.toString());

        assertSame(3, error.getStart().getIndex());
        assertSame(6, error.getEnd().getIndex());
        assertEquals("Expecting '('", error.getDetails());
        assertEquals("<TestLPAREN>:1:4", error.getMarker().getSource());
        assertEquals("1  if test", error.getMarker().getPreview());
        assertEquals("      ^^^^", error.getMarker().getMarker());

        System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }

    @Test
    public void testRPAREN() {
        Parser.ParserError error = assertThrows(Parser.ParserError.class, () ->
            parseSingle("<TestRPAREN>", "if(test{", IfNode.class)
        );

        System.out.println(error.toString());

        assertSame(7, error.getStart().getIndex());
        assertSame(7, error.getEnd().getIndex());
        assertEquals("Expecting ')'", error.getDetails());
        assertEquals("<TestRPAREN>:1:8", error.getMarker().getSource());
        assertEquals("1  if(test{", error.getMarker().getPreview());
        assertEquals("          ^", error.getMarker().getMarker());

        System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }

    @Test
    public void testLCURL() {
        Parser.ParserError error = assertThrows(Parser.ParserError.class, () ->
            parseSingle("<TestLCURL>", "if(test) {", IfNode.class)
        );

        System.out.println(error.toString());

        assertSame(9, error.getStart().getIndex());
        assertSame(9, error.getEnd().getIndex());
        assertEquals("Expecting '}'", error.getDetails());
        assertEquals("<TestLCURL>:1:10", error.getMarker().getSource());
        assertEquals("1  if(test) {", error.getMarker().getPreview());
        assertEquals("            ^", error.getMarker().getMarker());

        System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }

    @Test
    public void testExpectingSemicolon() {
        Parser.ParserError error = assertThrows(Parser.ParserError.class, () ->
            parseSingle("<TestAwaitSemicolonError>", "for(var i = 0 i<10) {", IfNode.class)
        );

        System.out.println(error.toString());

        assertSame(14, error.getStart().getIndex());
        assertSame(14, error.getEnd().getIndex());
        assertEquals("Expecting semicolon at this point", error.getDetails());
        assertEquals("<TestAwaitSemicolonError>:1:15", error.getMarker().getSource());
        assertEquals("1  for(var i = 0 i<10) {", error.getMarker().getPreview());
        assertEquals("                 ^", error.getMarker().getMarker());

        System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }



}
