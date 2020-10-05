package com.github.nsc.de.compiler.lexer;

import com.github.nsc.de.compiler.lexer.characterinputstream.CharacterInputStream;
import com.github.nsc.de.compiler.lexer.characterinputstream.StringCharacterInputStream;
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

        System.err.println(error.toString());

        assertSame(4, error.getStart().getIndex());
        assertSame(4, error.getEnd().getIndex());
        assertEquals("String must end with a '\"'", error.getDetails());

        System.out.println("\u2705 Correct error was thrown");

    }

    @Test
    public void testUnknownEscapeSequenceError() {

        // Unknown escape sequence (using \a here)
        Lexer.LexerError error = assertThrows(Lexer.LexerError.class, () -> {

            CharacterInputStream chars = new StringCharacterInputStream("<tests>", "\"\\a\"");
            Lexer lexer = new Lexer(chars);
            lexer.makeTokens();

        });

        System.err.println(error.toString());

        assertSame(2, error.getStart().getIndex());
        assertSame(2, error.getEnd().getIndex());
        assertEquals("Unknown escape sequence '\\a'", error.getDetails());

        System.out.println("\u2705 Correct error was thrown");

    }

    @Test
    public void testExpectHexCharacterError() {

        // Wrong input to unicode character
        Lexer.LexerError error = assertThrows(Lexer.LexerError.class, () -> {

            CharacterInputStream chars = new StringCharacterInputStream("<tests>", "\"\\uatea\"");
            Lexer lexer = new Lexer(chars);
            lexer.makeTokens();

        });

        System.err.println(error.toString());

        assertSame(4, error.getStart().getIndex());
        assertSame(4, error.getEnd().getIndex());
        assertEquals("Expecting hex char", error.getDetails());

        System.out.println("\u2705 Correct error was thrown");

    }

    @Test
    public void testUnexpectedTokenError() {

        // Unexpected Token
        Lexer.LexerError error = assertThrows(Lexer.LexerError.class, () -> {

            CharacterInputStream chars = new StringCharacterInputStream("<tests>", "ü");
            Lexer lexer = new Lexer(chars);
            lexer.makeTokens();

        });

        System.err.println(error.toString());

        assertSame(0, error.getStart().getIndex());
        assertSame(0, error.getEnd().getIndex());
        assertEquals("Unrecognised Token: 'ü'", error.getDetails());
        assertEquals("UnexpectedTokenError", error.getName());

        System.out.println("\u2705 Correct error was thrown");

    }

}
