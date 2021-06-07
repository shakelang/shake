package com.github.shakelang.shake

import com.github.shakelang.shake.util.CompilerError
import com.github.shakelang.shake.util.Formatting
import com.github.shakelang.shake.util.characterinput.charactersource.CharacterSource
import com.github.shakelang.shake.util.characterinput.position.Position
import com.github.shakelang.shake.util.characterinput.position.PositionMap
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CompilerError {
    @Test
    fun testCompilerError() {
        val source = CharacterSource.from(genLengthString(30), "<source>")
        val map = PositionMap(source, intArrayOf())
        val error = CompilerError(
            "message", "TestingError", "Some details",
            map.resolve(10), map.resolve(10)
        )
        Assertions.assertEquals("<source>:1:11", error.marker.source)
        Assertions.assertEquals("1  012345678901234567890123456789", error.marker.preview)
        Assertions.assertEquals("             ^", error.marker.marker)
        Assertions.assertEquals(
            "1  0123456789" + Formatting.INVERT + Formatting.FGColor.RED + "0" +
                    Formatting.RESET + "1234567890123456789", error.marker.colorPreview
        )

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }

    @Test
    fun testCompilerErrorOverflowAfter() {
        val source = CharacterSource.from(genLengthString(60), "<source>")
        val map = PositionMap(source, intArrayOf())
        val error = CompilerError(
            "message", "TestingError", "Some details",
            Position(map, 10, 11, 1),
            Position(map, 10, 11, 1)
        )

        // System.out.println(error.toString());
        Assertions.assertEquals("<source>:1:11", error.marker.source)
        Assertions.assertEquals("1  0123456789012345678901234567890...+28", error.marker.preview)
        Assertions.assertEquals("             ^", error.marker.marker)
        Assertions.assertEquals(
            "1  0123456789" + Formatting.INVERT + Formatting.FGColor.RED + "0" +
                    Formatting.RESET + "12345678901234567890...+28", error.marker.colorPreview
        )

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }

    @Test
    fun testCompilerErrorOverflowBefore() {
        val source = CharacterSource.from(genLengthString(60), "<source>")
        val map = PositionMap(source, intArrayOf())
        val error = CompilerError(
            "message", "TestingError", "Some details",
            map.resolve(39), map.resolve(39)
        )

        // System.out.println(error.toString());
        Assertions.assertEquals("<source>:1:40", error.marker.source)
        Assertions.assertEquals("1  +18...890123456789012345678901234567890123456789", error.marker.preview)
        Assertions.assertEquals("                              ^", error.marker.marker)
        Assertions.assertEquals(
            "1  +18...890123456789012345678" + Formatting.INVERT + Formatting.FGColor.RED + "9" +
                    Formatting.RESET + "01234567890123456789", error.marker.colorPreview
        )

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }

    @Test
    fun testCompilerErrorOverflow() {
        val source = CharacterSource.from(genLengthString(100), "<source>")
        val map = PositionMap(source, intArrayOf())
        val error = CompilerError(
            "message", "TestingError", "Some details",
            map.resolve(49), map.resolve(49)
        )

        // System.out.println(error.toString());
        Assertions.assertEquals("<source>:1:50", error.marker.source)
        Assertions.assertEquals("1  +28...890123456789012345678901234567890123456789...+29", error.marker.preview)
        Assertions.assertEquals("                              ^", error.marker.marker)
        Assertions.assertEquals(
            "1  +28...890123456789012345678" + Formatting.INVERT + Formatting.FGColor.RED + "9" +
                    Formatting.RESET + "01234567890123456789...+29", error.marker.colorPreview
        )

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }

    @Test
    fun testCompilerErrorLongMark() {
        val source = CharacterSource.from(genLengthString(40), "<source>")
        val map = PositionMap(source, intArrayOf())
        val error = CompilerError(
            "message", "TestingError", "Some details",
            map.resolve(9),
            map.resolve(14)
        )

        // System.out.println(error.toString());
        Assertions.assertEquals("<source>:1:10", error.marker.source)
        Assertions.assertEquals("1  012345678901234567890123456789012345678", error.marker.preview)
        Assertions.assertEquals("            ^^^^^^", error.marker.marker)
        Assertions.assertEquals(
            "1  012345678" + Formatting.INVERT + Formatting.FGColor.RED + "901234" +
                    Formatting.RESET + "567890123456789012345678", error.marker.colorPreview
        )

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }

    @Test
    fun testCompilerErrorMultiLine() {
        val str = """
            
            ${genLengthString(38)}
            ${genLengthString(40)}
            ${genLengthString(39)}
            
            """.trimIndent()
        val source = CharacterSource.from(str, "<source>")
        val map = PositionMap(source, intArrayOf(0, 39, 80, 120))
        val pos = map.resolve(50)
        Assertions.assertSame(50, pos.index)
        Assertions.assertSame(11, pos.column)
        Assertions.assertSame(3, pos.line)
        val error = CompilerError(
            "message", "TestingError", "Some details", pos, pos
        )

        // System.out.println(error.toString());
        Assertions.assertEquals("<source>:3:11", error.marker.source)
        Assertions.assertEquals("3  012345678901234567890123456789012...+7", error.marker.preview)
        Assertions.assertEquals("             ^", error.marker.marker)
        Assertions.assertEquals(
            "3  0123456789" + Formatting.INVERT + Formatting.FGColor.RED + "0" +
                    Formatting.RESET + "1234567890123456789012...+7", error.marker.colorPreview
        )

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }

    companion object {
        private fun genLengthString(length: Int): String {
            val string = StringBuilder()
            for (i in 0 until length) string.append(i % 10)
            return string.toString()
        }
    }
}