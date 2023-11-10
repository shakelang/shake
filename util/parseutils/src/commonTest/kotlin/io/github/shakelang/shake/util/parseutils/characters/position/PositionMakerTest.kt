package io.github.shakelang.shake.util.parseutils.characters.position

import io.github.shakelang.shake.util.parseutils.characters.source.CharacterSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

/**
 * Tests for [PositionMaker]
 *w
 */
class PositionMakerTest {

    @Test
    fun testConstructors() {
        val src = CharacterSource.from(
            "he\nllo",
            "PositionMakerTest#testPositionMakerNext()"
        )

        run {
            val pos = PositionMaker(src)
            assertEquals(1, pos.line)
            assertEquals(0, pos.column)
            assertEquals(-1, pos.index)
            assertEquals(0, pos.lineSeparators.size)
            assertSame(src, pos.source)
        }

        run {
            val pos = PositionMaker(1, 2, 3, src)
            assertEquals(3, pos.line)
            assertEquals(2, pos.column)
            assertEquals(1, pos.index)
            assertEquals(0, pos.lineSeparators.size)
            assertSame(src, pos.source)
        }

        run {
            val pos = PositionMaker(1, 2, 3, intArrayOf(1, 2, 3), src)
            assertEquals(3, pos.line)
            assertEquals(2, pos.column)
            assertEquals(1, pos.index)
            assertEquals(3, pos.lineSeparators.size)
            assertSame(src, pos.source)
        }
    }

    @Test
    fun testPositionMakerNext() {
        val src = CharacterSource.from(
            "he\nllo",
            "PositionMakerTest#testPositionMakerNext()"
        )

        val maker = PositionMaker(src)

        assertEquals("PositionMakerTest#testPositionMakerNext()", maker.location)

        assertSame(-1, maker.index)
        assertSame(0, maker.column)
        assertSame(1, maker.line)

        maker.nextColumn()

        assertSame(0, maker.index)
        assertSame(1, maker.column)
        assertSame(1, maker.line)

        maker.nextColumn()

        assertSame(1, maker.index)
        assertSame(2, maker.column)
        assertSame(1, maker.line)

        maker.nextColumn()

        assertSame(2, maker.index)
        assertSame(3, maker.column)
        assertSame(1, maker.line)

        maker.nextLine()

        assertSame(3, maker.index)
        assertSame(1, maker.column)
        assertSame(2, maker.line)

        maker.nextColumn()

        assertSame(4, maker.index)
        assertSame(2, maker.column)
        assertSame(2, maker.line)
    }

    @Test
    fun testPositionMakerLineSeparators() {
        val src = CharacterSource.from(
            "he\nllo",
            "PositionMakerTest#testPositionMakerNext()"
        )

        val maker = PositionMaker(src)

        assertEquals("PositionMakerTest#testPositionMakerNext()", maker.location)

        maker.nextColumn()
        maker.nextColumn()
        maker.nextColumn()
        maker.nextLine()
        maker.nextColumn()

        assertSame(1, maker.lineSeparators.size)
        assertSame(3, maker.lineSeparators[0])
    }

    @Test
    fun testImplementationWithNewline() {
        val src = CharacterSource.from("abc\nabc\nabc", "PositionMakerTest#testImplementationWithNewline()")
        val maker = PositionMaker(11, 4, 3, intArrayOf(3, 7), src)

        assertEquals("PositionMakerTest#testImplementationWithNewline()", maker.location)

        assertEquals(Position(maker, 0, 1, 1), maker.resolve(0))
        assertEquals(Position(maker, 1, 2, 1), maker.resolve(1))
        assertEquals(Position(maker, 2, 3, 1), maker.resolve(2))
        assertEquals(Position(maker, 3, 4, 1), maker.resolve(3))
        assertEquals(Position(maker, 4, 1, 2), maker.resolve(4))
        assertEquals(Position(maker, 5, 2, 2), maker.resolve(5))
        assertEquals(Position(maker, 6, 3, 2), maker.resolve(6))
        assertEquals(Position(maker, 7, 4, 2), maker.resolve(7))
        assertEquals(Position(maker, 8, 1, 3), maker.resolve(8))
        assertEquals(Position(maker, 9, 2, 3), maker.resolve(9))
        assertEquals(Position(maker, 10, 3, 3), maker.resolve(10))
    }

    @Test
    fun testImplementationWithoutNewline() {
        val src = CharacterSource.from("abc", "PositionMakerTest#testImplementationWithoutNewline()")
        val maker = PositionMaker(3, 4, 1, intArrayOf(), src)

        assertEquals("PositionMakerTest#testImplementationWithoutNewline()", maker.location)

        assertEquals(Position(maker, 0, 1, 1), maker.resolve(0))
        assertEquals(Position(maker, 1, 2, 1), maker.resolve(1))
        assertEquals(Position(maker, 2, 3, 1), maker.resolve(2))
    }

    @Test
    fun testToString() {
        val src = CharacterSource.from("abc\nabc\nabc", "PositionMakerTest#testToString()")
        val maker = PositionMaker(11, 4, 3, intArrayOf(3, 7), src)

        assertEquals("PositionMakerTest#testToString()", maker.location)
        assertEquals("PositionMaker(index=11, column=4, line=3, lineSeparators=[3, 7])", maker.toString())
    }

    @Test
    fun testCreatePositionMap() {
        val src = CharacterSource.from("abc\nabc\nabc", "PositionMakerTest#testCreatePositionMap()")
        val maker = PositionMaker(11, 4, 3, intArrayOf(3, 7), src)
        val map = maker.createPositionMap()

        assertEquals("PositionMakerTest#testCreatePositionMap()", maker.location)

        assertEquals(Position(map, 0, 1, 1), map.resolve(0))
        assertEquals(Position(map, 1, 2, 1), map.resolve(1))
        assertEquals(Position(map, 2, 3, 1), map.resolve(2))
        assertEquals(Position(map, 3, 4, 1), map.resolve(3))
        assertEquals(Position(map, 4, 1, 2), map.resolve(4))
        assertEquals(Position(map, 5, 2, 2), map.resolve(5))
        assertEquals(Position(map, 6, 3, 2), map.resolve(6))
        assertEquals(Position(map, 7, 4, 2), map.resolve(7))
        assertEquals(Position(map, 8, 1, 3), map.resolve(8))
        assertEquals(Position(map, 9, 2, 3), map.resolve(9))
        assertEquals(Position(map, 10, 3, 3), map.resolve(10))
    }

    @Test
    fun testCreatePositionAtLocation() {
        val src = CharacterSource.from(
            "he\nllo",
            "PositionMakerTest#testCreatePositionAtLocation()"
        )

        val maker = PositionMaker(src)

        assertEquals("PositionMakerTest#testCreatePositionAtLocation()", maker.location)

        assertSame(-1, maker.index)
        assertSame(0, maker.column)
        assertSame(1, maker.line)

        assertEquals(Position(maker, -1, 0, 1), maker.createPositionAtLocation())

        maker.nextColumn()

        assertSame(0, maker.index)
        assertSame(1, maker.column)
        assertSame(1, maker.line)

        assertEquals(Position(maker, 0, 1, 1), maker.createPositionAtLocation())

        maker.nextColumn()

        assertSame(1, maker.index)
        assertSame(2, maker.column)
        assertSame(1, maker.line)

        assertEquals(Position(maker, 1, 2, 1), maker.createPositionAtLocation())

        maker.nextColumn()

        assertSame(2, maker.index)
        assertSame(3, maker.column)
        assertSame(1, maker.line)

        assertEquals(Position(maker, 2, 3, 1), maker.createPositionAtLocation())

        maker.nextLine()

        assertSame(3, maker.index)
        assertSame(1, maker.column)
        assertSame(2, maker.line)

        assertEquals(Position(maker, 3, 1, 2), maker.createPositionAtLocation())

        maker.nextColumn()

        assertSame(4, maker.index)
        assertSame(2, maker.column)
        assertSame(2, maker.line)

        assertEquals(Position(maker, 4, 2, 2), maker.createPositionAtLocation())
    }

    @Test
    fun getAfterInLine() {
        run {
            val src = CharacterSource.from(
                "he\nllo",
                "PositionMakerTest#getAfterInLine()"
            )

            val maker = PositionMaker(src)

            assertEquals("PositionMakerTest#getAfterInLine()", maker.location)

            assertSame(-1, maker.index)
            assertSame(0, maker.column)
            assertSame(1, maker.line)

            assertEquals(Position(maker, -1, 0, 1), maker.createPositionAtLocation())

            maker.nextColumn()

            assertSame(0, maker.index)
            assertSame(1, maker.column)
            assertSame(1, maker.line)

            assertEquals(Position(maker, 0, 1, 1), maker.createPositionAtLocation())

            maker.nextColumn()

            assertSame(1, maker.index)
            assertSame(2, maker.column)
            assertSame(1, maker.line)

            assertEquals(Position(maker, 1, 2, 1), maker.createPositionAtLocation())

            maker.nextColumn()

            assertSame(2, maker.index)
            assertSame(3, maker.column)
            assertSame(1, maker.line)

            assertEquals(Position(maker, 2, 3, 1), maker.createPositionAtLocation())

            maker.nextLine()

            assertSame(3, maker.index)
            assertSame(1, maker.column)
            assertSame(2, maker.line)

            assertEquals(Position(maker, 3, 1, 2), maker.createPositionAtLocation())

            maker.nextColumn()

            assertSame(4, maker.index)
            assertSame(2, maker.column)
            assertSame(2, maker.line)

            assertEquals(Position(maker, 4, 2, 2), maker.createPositionAtLocation())

            assertEquals(3, maker.getAfterInLine(0))
            assertEquals(2, maker.getAfterInLine(1))
            assertEquals(1, maker.getAfterInLine(2))
            assertEquals(0, maker.getAfterInLine(3))
            assertEquals(5, maker.getAfterInLine(4))
            assertEquals(4, maker.getAfterInLine(5))
            assertEquals(3, maker.getAfterInLine(6))
            assertEquals(2, maker.getAfterInLine(7))
            assertEquals(1, maker.getAfterInLine(8))
            assertEquals(0, maker.getAfterInLine(9))
        }
    }
}
