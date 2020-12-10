package com.github.nsc.de.shake.lexer;

import com.github.nsc.de.shake.lexer.characterinputstream.CharacterInputStream;
import com.github.nsc.de.shake.lexer.characterinputstream.StringCharacterInputStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestErrors {

    @Test
    public void testStringMustEndError() {

        // String type 1 not finished
        Lexer.LexerError error = assertThrows(Lexer.LexerError.class, () -> {

            CharacterInputStream chars = new StringCharacterInputStream("<tests>", "\"test");
            Lexer lexer = new Lexer(chars);
            lexer.makeTokens();

        });

        // System.out.println(error.toString());

        assertSame(4, error.getStart().getIndex());
        assertSame(4, error.getEnd().getIndex());
        assertEquals("String must end with a '\"'", error.getDetails());
        assertEquals("<tests>:1:5", error.getMarker().getSource());
        assertEquals("1  \"test", error.getMarker().getPreview());
        assertEquals("       ^", error.getMarker().getMarker());

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);

    }

    @Test
    public void testUnknownEscapeSequenceError() {

        // Unknown escape sequence (using \a here)
        Lexer.LexerError error = assertThrows(Lexer.LexerError.class, () -> {

            CharacterInputStream chars = new StringCharacterInputStream("<tests>", "\"\\a\"");
            Lexer lexer = new Lexer(chars);
            lexer.makeTokens();

        });

        // System.out.println(error.toString());

        assertSame(2, error.getStart().getIndex());
        assertSame(2, error.getEnd().getIndex());
        assertEquals("Unknown escape sequence '\\a'", error.getDetails());
        assertEquals("<tests>:1:3", error.getMarker().getSource());
        assertEquals("1  \"\\a\"", error.getMarker().getPreview());
        assertEquals("     ^", error.getMarker().getMarker());


        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);

    }

    @Test
    public void testExpectHexCharacterError() {

        // Wrong input to unicode character
        Lexer.LexerError error = assertThrows(Lexer.LexerError.class, () -> {

            CharacterInputStream chars = new StringCharacterInputStream("<tests>", "\"\\uatea\"");
            Lexer lexer = new Lexer(chars);
            lexer.makeTokens();

        });

        // System.out.println(error.toString());

        assertSame(4, error.getStart().getIndex());
        assertSame(4, error.getEnd().getIndex());
        assertEquals("Expecting hex char", error.getDetails());
        assertEquals("<tests>:1:5", error.getMarker().getSource());
        assertEquals("1  \"\\uatea\"", error.getMarker().getPreview());
        assertEquals("       ^", error.getMarker().getMarker());

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);

    }

    @Test
    public void testUnexpectedTokenError() {

        // Unexpected Token
        Lexer.LexerError error = assertThrows(Lexer.LexerError.class, () -> {

            CharacterInputStream chars = new StringCharacterInputStream("<tests>", "\u00dc");
            Lexer lexer = new Lexer(chars);
            lexer.makeTokens();

        });

        // System.out.println(error.toString());

        assertSame(0, error.getStart().getIndex());
        assertSame(0, error.getEnd().getIndex());
        assertEquals("Unrecognised Token: '\u00dc'", error.getDetails());
        assertEquals("UnexpectedTokenError", error.getName());
        assertEquals("<tests>:1:1", error.getMarker().getSource());
        assertEquals("1  \u00dc", error.getMarker().getPreview());
        assertEquals("   ^", error.getMarker().getMarker());

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);

    }

}
