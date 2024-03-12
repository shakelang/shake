package com.shakelang.util.parseutils

import com.shakelang.util.colorlib.functional.invert
import com.shakelang.util.colorlib.functional.red
import com.shakelang.util.parseutils.characters.position.Position
import com.shakelang.util.parseutils.characters.position.PositionMap
import com.shakelang.util.parseutils.characters.source.CharacterSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class CompilerErrorTests {

    @Test
    fun testCompilerError() {
        val source = CharacterSource.from(genLengthString(30), "<source>")
        val map = PositionMap.PositionMapImplementation(source, intArrayOf())
        val error = CompilerError(
            "message",
            map.resolve(10),
            map.resolve(10),
        )

        assertEquals(10, error.start.index)
        assertEquals(error.start.line, 1)
        assertEquals(error.start.column, 11)

        assertEquals("<source>:1:11", error.marker.source)
        assertEquals("1  012345678901234567890123456789", error.marker.preview)
        assertEquals("             ^", error.marker.marker)
        assertEquals(
            "1  0123456789${"0".red().invert()}1234567890123456789",
            error.marker.coloredPreview,
        )
    }

    @Test
    fun testCausedCompilerError() {
        val source = CharacterSource.from(genLengthString(30), "<source>")
        val map = PositionMap.PositionMapImplementation(source, intArrayOf())
        val error = Error("Test")
        val caused = CompilerError(
            "message",
            map.resolve(10),
            map.resolve(10),
            error,
        )

        assertEquals(10, caused.start.index)
        assertEquals(caused.start.line, 1)
        assertEquals(caused.start.column, 11)

        assertEquals("<source>:1:11", caused.marker.source)
        assertEquals("1  012345678901234567890123456789", caused.marker.preview)
        assertEquals("             ^", caused.marker.marker)
        assertEquals(
            "1  0123456789${"0".red().invert()}1234567890123456789",
            caused.marker.coloredPreview,
        )

        assertEquals(error, caused.cause)
    }

    @Test
    fun testCompilerErrorOverflowAfter() {
        val source = CharacterSource.from(genLengthString(100), "<source>")
        val map = PositionMap.PositionMapImplementation(source, intArrayOf())
        val error = CompilerError(
            "message",
            Position(map, 10, 11, 1),
            Position(map, 10, 11, 1),
        )

        assertEquals("<source>:1:11", error.marker.source)
        assertEquals("1  012345678901234567890123456789012345678901234567890123456789012345678...(+31)", error.marker.preview)
        assertEquals("             ^", error.marker.marker)
        assertEquals(
            "1  0123456789${"0".red().invert()}1234567890123456789012345678901234567890123456789012345678...(+31)",
            error.marker.coloredPreview,
        )
    }

    @Test
    fun testCompilerErrorOverflowBefore() {
        val source = CharacterSource.from(genLengthString(100), "<source>")
        val map = PositionMap.PositionMapImplementation(source, intArrayOf())
        val error = CompilerError(
            "message",
            map.resolve(69),
            map.resolve(69),
        )

        assertEquals("<source>:1:70", error.marker.source)
        assertEquals("1  (+32)...23456789012345678901234567890123456789012345678901234567890123456789", error.marker.preview)
        assertEquals("                                                ^", error.marker.marker)
        assertEquals(
            "1  (+32)...2345678901234567890123456789012345678${"9".red().invert()}012345678901234567890123456789",
            error.marker.coloredPreview,
        )
    }

    @Test
    fun testCompilerErrorOverflow() {
        val source = CharacterSource.from(genLengthString(100), "<source>")
        val map = PositionMap.PositionMapImplementation(source, intArrayOf())
        val error = CompilerError(
            "message",
            map.resolve(49),
            map.resolve(49),
        )

        assertEquals("<source>:1:50", error.marker.source)
        assertEquals("1  (+19)...9012345678901234567890123456789012345678901234567890123456789...(+20)", error.marker.preview)
        assertEquals("                                         ^", error.marker.marker)
        assertEquals(
            "1  (+19)...901234567890123456789012345678${"9".red().invert()}012345678901234567890123456789...(+20)",
            error.marker.coloredPreview,
        )
    }

    @Test
    fun testCompilerErrorLongMark() {
        val source = CharacterSource.from(genLengthString(40), "<source>")
        val map = PositionMap.PositionMapImplementation(source, intArrayOf())
        val error = CompilerError(
            "message",
            map.resolve(9),
            map.resolve(14),
        )

        assertEquals(9, error.start.index)
        assertEquals(error.start.line, 1)
        assertEquals(error.start.column, 10)

        assertEquals("<source>:1:10", error.marker.source)
        assertEquals("1  0123456789012345678901234567890123456789", error.marker.preview)
        assertEquals("            ^^^^^^", error.marker.marker)
        assertEquals(
            "1  012345678${"901234".red().invert()}5678901234567890123456789",
            error.marker.coloredPreview,
        )
    }

    @Test
    fun testCompilerErrorMultiLine() {
        val str = """
            
            ${genLengthString(38)}
            ${genLengthString(40)}
            ${genLengthString(39)}
            
        """.trimIndent()
        val source = CharacterSource.from(str, "<source>")
        val map = PositionMap.PositionMapImplementation(source, intArrayOf(0, 39, 80, 120))
        val pos = map.resolve(50)
        assertSame(50, pos.index)
        assertSame(11, pos.column)
        assertSame(3, pos.line)
        val error = CompilerError(
            "message",
            pos,
            pos,
        )

        assertEquals("<source>:3:11", error.marker.source)
        assertEquals("3  0123456789012345678901234567890123456789", error.marker.preview)
        assertEquals("             ^", error.marker.marker)
        assertEquals(
            "3  0123456789${"0".red().invert()}12345678901234567890123456789",
            error.marker.coloredPreview,
        )
    }

    companion object {
        private fun genLengthString(length: Int): String {
            val string = StringBuilder()
            for (i in 0 until length) string.append(i % 10)
            return string.toString()
        }
    }
}
