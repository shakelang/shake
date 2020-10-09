package com.github.nsc.de.compiler;

import com.github.nsc.de.compiler.lexer.Position;
import com.github.nsc.de.compiler.util.Formatting;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompilerError {

    @Test
    public void testCompilerError() {
        String str = genLengthString(30);

        String source = "<source>";
        com.github.nsc.de.compiler.util.CompilerError error =
                new com.github.nsc.de.compiler.util.CompilerError(
                        "message", "TestingError", "Some details",
                        new Position(source, str, 10, 11, 1),
                        new Position(source, str, 10, 11, 1));

        System.out.println(error.toString());

        assertEquals("<source>:1:11", error.getMarker().getSource());
        assertEquals("1  012345678901234567890123456789", error.getMarker().getPreview());
        assertEquals("             ^", error.getMarker().getMarker());

        assertEquals("1  0123456789" + Formatting.INVERT + Formatting.FGColor.RED + "0" +
                Formatting.RESET + "1234567890123456789", error.getMarker().getColorPreview());

        System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);

    }

    @Test
    public void testCompilerErrorOverflowAfter() {
        String str = genLengthString(60);

        String source = "<source>";
        com.github.nsc.de.compiler.util.CompilerError error =
                new com.github.nsc.de.compiler.util.CompilerError(
                        "message", "TestingError", "Some details",
                        new Position(source, str, 10, 11, 1),
                        new Position(source, str, 10, 11, 1));

        System.out.println(error.toString());

        assertEquals("<source>:1:11", error.getMarker().getSource());
        assertEquals("1  0123456789012345678901234567890...+28", error.getMarker().getPreview());
        assertEquals("             ^", error.getMarker().getMarker());

        assertEquals("1  0123456789" + Formatting.INVERT + Formatting.FGColor.RED + "0" +
                Formatting.RESET + "12345678901234567890...+28", error.getMarker().getColorPreview());

        System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);

    }

    @Test
    public void testCompilerErrorOverflowBefore() {
        String str = genLengthString(60);

        String source = "<source>";
        com.github.nsc.de.compiler.util.CompilerError error =
                new com.github.nsc.de.compiler.util.CompilerError(
                        "message", "TestingError", "Some details",
                        new Position(source, str, 39, 40, 1),
                        new Position(source, str, 39, 40, 1));

        System.out.println(error.toString());

        assertEquals("<source>:1:40", error.getMarker().getSource());
        assertEquals("1  +18...890123456789012345678901234567890123456789", error.getMarker().getPreview());
        assertEquals("                              ^", error.getMarker().getMarker());

        assertEquals("1  +18...890123456789012345678" + Formatting.INVERT + Formatting.FGColor.RED + "9" +
                Formatting.RESET + "01234567890123456789", error.getMarker().getColorPreview());

        System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);

    }

    @Test
    public void testCompilerErrorOverflow() {
        String str = genLengthString(100);

        String source = "<source>";
        com.github.nsc.de.compiler.util.CompilerError error =
                new com.github.nsc.de.compiler.util.CompilerError(
                        "message", "TestingError", "Some details",
                        new Position(source, str, 49, 50, 1),
                        new Position(source, str, 49, 50, 1));

        System.out.println(error.toString());

        assertEquals("<source>:1:50", error.getMarker().getSource());
        assertEquals("1  +28...890123456789012345678901234567890123456789...+29", error.getMarker().getPreview());
        assertEquals("                              ^", error.getMarker().getMarker());

        assertEquals("1  +28...890123456789012345678" + Formatting.INVERT + Formatting.FGColor.RED + "9" +
                Formatting.RESET + "01234567890123456789...+29", error.getMarker().getColorPreview());

        System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);

    }

    @Test
    public void testCompilerErrorLongMark() {
        String str = genLengthString(40);

        String source = "<source>";
        com.github.nsc.de.compiler.util.CompilerError error =
                new com.github.nsc.de.compiler.util.CompilerError(
                        "message", "TestingError", "Some details",
                        new Position(source, str, 9, 10, 1),
                        new Position(source, str, 14, 15, 1));

        System.out.println(error.toString());

        assertEquals("<source>:1:10", error.getMarker().getSource());
        assertEquals("1  012345678901234567890123456789012345678", error.getMarker().getPreview());
        assertEquals("            ^^^^^^", error.getMarker().getMarker());

        assertEquals("1  012345678" + Formatting.INVERT + Formatting.FGColor.RED + "901234" +
                Formatting.RESET + "567890123456789012345678", error.getMarker().getColorPreview());

        System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);

    }

    @Test
    public void testCompilerErrorMultiLine() {
        String str = '\n' + genLengthString(38) + '\n' + genLengthString(40) + '\n' + genLengthString(39) + '\n';

        String source = "<source>";
        com.github.nsc.de.compiler.util.CompilerError error =
                new com.github.nsc.de.compiler.util.CompilerError(
                        "message", "TestingError", "Some details",
                        new Position(source, str, 50, 11, 3),
                        new Position(source, str, 50, 11, 3));

        System.out.println(error.toString());

        assertEquals("<source>:3:11", error.getMarker().getSource());
        assertEquals("3  012345678901234567890123456789012...+7", error.getMarker().getPreview());
        assertEquals("             ^", error.getMarker().getMarker());

        assertEquals("3  0123456789" + Formatting.INVERT + Formatting.FGColor.RED + "0" +
                Formatting.RESET + "1234567890123456789012...+7", error.getMarker().getColorPreview());

        System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);

    }

    private static String genLengthString(int length) {
        StringBuilder string = new StringBuilder();
        for(int i = 0; i < length; i++) {
            String num = String.valueOf(i);
            string.append(num.charAt(num.length() - 1));
        }
        return string.toString();
    }

}
