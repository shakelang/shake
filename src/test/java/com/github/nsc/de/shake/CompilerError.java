package com.github.nsc.de.shake;

import com.github.nsc.de.shake.lexer.characterinputstream.charactersource.CharacterSource;
import com.github.nsc.de.shake.lexer.characterinputstream.position.Position;
import com.github.nsc.de.shake.lexer.characterinputstream.position.PositionMap;
import com.github.nsc.de.shake.util.Formatting;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompilerError {

    @Test
    public void testCompilerError() {
        CharacterSource source = CharacterSource.from(genLengthString(30), "<source>");
        PositionMap map = new PositionMap(source, new int[] {});

        com.github.nsc.de.shake.util.CompilerError error =
                new com.github.nsc.de.shake.util.CompilerError(
                        "message", "TestingError", "Some details",
                        map.resolve(10), map.resolve(10));

        assertEquals("<source>:1:11", error.getMarker().getSource());
        assertEquals("1  012345678901234567890123456789", error.getMarker().getPreview());
        assertEquals("             ^", error.getMarker().getMarker());

        assertEquals("1  0123456789" + Formatting.INVERT + Formatting.FGColor.RED + "0" +
                Formatting.RESET + "1234567890123456789", error.getMarker().getColorPreview());

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);

    }

    @Test
    public void testCompilerErrorOverflowAfter() {
        CharacterSource source = CharacterSource.from(genLengthString(60), "<source>");
        PositionMap map = new PositionMap(source, new int[] {});

        com.github.nsc.de.shake.util.CompilerError error =
                new com.github.nsc.de.shake.util.CompilerError(
                        "message", "TestingError", "Some details",
                        new Position(map, 10, 11, 1),
                        new Position(map, 10, 11, 1));

        // System.out.println(error.toString());

        assertEquals("<source>:1:11", error.getMarker().getSource());
        assertEquals("1  0123456789012345678901234567890...+28", error.getMarker().getPreview());
        assertEquals("             ^", error.getMarker().getMarker());

        assertEquals("1  0123456789" + Formatting.INVERT + Formatting.FGColor.RED + "0" +
                Formatting.RESET + "12345678901234567890...+28", error.getMarker().getColorPreview());

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);

    }

    @Test
    public void testCompilerErrorOverflowBefore() {
        CharacterSource source = CharacterSource.from(genLengthString(60), "<source>");
        PositionMap map = new PositionMap(source, new int[] {});

        com.github.nsc.de.shake.util.CompilerError error =
                new com.github.nsc.de.shake.util.CompilerError(
                        "message", "TestingError", "Some details",
                        map.resolve(39), map.resolve(39));

        // System.out.println(error.toString());

        assertEquals("<source>:1:40", error.getMarker().getSource());
        assertEquals("1  +18...890123456789012345678901234567890123456789", error.getMarker().getPreview());
        assertEquals("                              ^", error.getMarker().getMarker());

        assertEquals("1  +18...890123456789012345678" + Formatting.INVERT + Formatting.FGColor.RED + "9" +
                Formatting.RESET + "01234567890123456789", error.getMarker().getColorPreview());

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);

    }

    @Test
    public void testCompilerErrorOverflow() {
        CharacterSource source = CharacterSource.from(genLengthString(100), "<source>");
        PositionMap map = new PositionMap(source, new int[] {});

        com.github.nsc.de.shake.util.CompilerError error =
                new com.github.nsc.de.shake.util.CompilerError(
                        "message", "TestingError", "Some details",
                        map.resolve(49), map.resolve(49));

        // System.out.println(error.toString());

        assertEquals("<source>:1:50", error.getMarker().getSource());
        assertEquals("1  +28...890123456789012345678901234567890123456789...+29", error.getMarker().getPreview());
        assertEquals("                              ^", error.getMarker().getMarker());

        assertEquals("1  +28...890123456789012345678" + Formatting.INVERT + Formatting.FGColor.RED + "9" +
                Formatting.RESET + "01234567890123456789...+29", error.getMarker().getColorPreview());

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);

    }

    @Test
    public void testCompilerErrorLongMark() {
        CharacterSource source = CharacterSource.from(genLengthString(40), "<source>");
        PositionMap map = new PositionMap(source, new int[] {});

        com.github.nsc.de.shake.util.CompilerError error =
                new com.github.nsc.de.shake.util.CompilerError(
                        "message", "TestingError", "Some details",
                        map.resolve(9),
                        map.resolve(14));

        // System.out.println(error.toString());

        assertEquals("<source>:1:10", error.getMarker().getSource());
        assertEquals("1  012345678901234567890123456789012345678", error.getMarker().getPreview());
        assertEquals("            ^^^^^^", error.getMarker().getMarker());

        assertEquals("1  012345678" + Formatting.INVERT + Formatting.FGColor.RED + "901234" +
                Formatting.RESET + "567890123456789012345678", error.getMarker().getColorPreview());

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);

    }

    @Test
    public void testCompilerErrorMultiLine() {
        String str = '\n' + genLengthString(38) + '\n' + genLengthString(40) + '\n' + genLengthString(39) + '\n';
        CharacterSource source = CharacterSource.from(str, "<source>");
        PositionMap map = new PositionMap(source, new int[] {0, 39, 80, 120});

        Position pos = map.resolve(50);

        assertSame(50, pos.getIndex());
        assertSame(11, pos.getColumn());
        assertSame(3, pos.getLine());

        com.github.nsc.de.shake.util.CompilerError error =
                new com.github.nsc.de.shake.util.CompilerError(
                        "message", "TestingError", "Some details", pos, pos);

        // System.out.println(error.toString());

        assertEquals("<source>:3:11", error.getMarker().getSource());
        assertEquals("3  012345678901234567890123456789012...+7", error.getMarker().getPreview());
        assertEquals("             ^", error.getMarker().getMarker());

        assertEquals("3  0123456789" + Formatting.INVERT + Formatting.FGColor.RED + "0" +
                Formatting.RESET + "1234567890123456789012...+7", error.getMarker().getColorPreview());

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);

    }

    private static String genLengthString(int length) {
        StringBuilder string = new StringBuilder();
        for(int i = 0; i < length; i++) string.append(i % 10);
        return string.toString();
    }

}
