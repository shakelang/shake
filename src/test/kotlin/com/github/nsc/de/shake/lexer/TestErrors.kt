package com.github.shakelang.shake.lexer

import com.github.shakelang.shake.lexer.Lexer.LexerError
import com.github.shakelang.shake.util.characterinput.characterinputstream.CharacterInputStream
import com.github.shakelang.shake.util.characterinput.characterinputstream.SourceCharacterInputStream
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestErrors {
    @Test
    fun testStringMustEndError() {

        // String type 1 not finished
        val error = Assertions.assertThrows(LexerError::class.java) {
            val chars: CharacterInputStream = SourceCharacterInputStream("<tests>", "\"test")
            val lexer = Lexer(chars)
            lexer.makeTokens()
        }

        // System.out.println(error.toString());
        Assertions.assertSame(4, error.start.index)
        Assertions.assertSame(4, error.end.index)
        Assertions.assertEquals("String must end with a '\"'", error.details)
        Assertions.assertEquals("<tests>:1:5", error.marker.source)
        Assertions.assertEquals("1  \"test", error.marker.preview)
        Assertions.assertEquals("       ^", error.marker.marker)

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }

    @Test
    fun testUnknownEscapeSequenceError() {

        // Unknown escape sequence (using \a here)
        val error = Assertions.assertThrows(LexerError::class.java) {
            val chars: CharacterInputStream = SourceCharacterInputStream("<tests>", "\"\\a\"")
            val lexer = Lexer(chars)
            lexer.makeTokens()
        }

        // System.out.println(error.toString());
        Assertions.assertSame(2, error.start.index)
        Assertions.assertSame(2, error.end.index)
        Assertions.assertEquals("Unknown escape sequence '\\a'", error.details)
        Assertions.assertEquals("<tests>:1:3", error.marker.source)
        Assertions.assertEquals("1  \"\\a\"", error.marker.preview)
        Assertions.assertEquals("     ^", error.marker.marker)


        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }

    @Test
    fun testExpectHexCharacterError() {

        // Wrong input to unicode character
        val error = Assertions.assertThrows(LexerError::class.java) {
            val chars: CharacterInputStream = SourceCharacterInputStream("<tests>", "\"\\uatea\"")
            val lexer = Lexer(chars)
            lexer.makeTokens()
        }

        // System.out.println(error.toString());
        Assertions.assertSame(4, error.start.index)
        Assertions.assertSame(4, error.end.index)
        Assertions.assertEquals("Expecting hex char", error.details)
        Assertions.assertEquals("<tests>:1:5", error.marker.source)
        Assertions.assertEquals("1  \"\\uatea\"", error.marker.preview)
        Assertions.assertEquals("       ^", error.marker.marker)

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }

    @Test
    fun testUnexpectedTokenError() {

        // Unexpected Token
        val error = Assertions.assertThrows(LexerError::class.java) {
            val chars: CharacterInputStream = SourceCharacterInputStream("<tests>", "\u00dc")
            val lexer = Lexer(chars)
            lexer.makeTokens()
        }

        // System.out.println(error.toString());
        Assertions.assertSame(0, error.start.index)
        Assertions.assertSame(0, error.end.index)
        Assertions.assertEquals("Unrecognised Token: '\u00dc'", error.details)
        Assertions.assertEquals("UnexpectedTokenError", error.name)
        Assertions.assertEquals("<tests>:1:1", error.marker.source)
        Assertions.assertEquals("1  \u00dc", error.marker.preview)
        Assertions.assertEquals("   ^", error.marker.marker)

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }
}