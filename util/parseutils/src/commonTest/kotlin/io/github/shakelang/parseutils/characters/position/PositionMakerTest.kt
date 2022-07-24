package io.github.shakelang.parseutils.characters.position

import io.github.shakelang.parseutils.characters.source.CharacterSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame


/**
 * Tests for [PositionMaker]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)w
 */
class PositionMakerTest {

    @Test
    fun testPositionMakerNext() {
        val src = CharacterSource.from(
            "he\nllo",
            "PositionMakerTest#testPositionMakerNext()"
        )

        val maker = PositionMaker(src)

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
        val src = CharacterSource.from("abc\nabc\nabc", "PositionMapTests#testImplementationWithNewline")
        val map = PositionMaker(11, 4, 3, intArrayOf(3, 7), src)

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
    fun testImplementationWithoutNewline() {
        val src = CharacterSource.from("abc", "PositionMapTests#testImplementationWithoutNewline")
        val map = PositionMaker(3, 4, 1, intArrayOf(), src)

        assertEquals(Position(map, 0, 1, 1), map.resolve(0))
        assertEquals(Position(map, 1, 2, 1), map.resolve(1))
        assertEquals(Position(map, 2, 3, 1), map.resolve(2))
    }

    @Test
    fun testToString() {
        val src = CharacterSource.from("abc\nabc\nabc", "PositionMapTests#testToString")
        val map = PositionMaker(11, 4, 3, intArrayOf(3, 7), src)
        assertEquals("PositionMaker(index=11, column=4, line=3, lineSeparators=[3, 7])", map.toString())
    }

    @Test
    fun testCreatePositionMap() {
        val src = CharacterSource.from("abc\nabc\nabc", "PositionMapTests#testCreatePositionMap")
        val maker = PositionMaker(11, 4, 3, intArrayOf(3, 7), src)
        val map = maker.createPositionMap()

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
            "PositionMakerTest#testPositionMakerNext()"
        )

        val maker = PositionMaker(src)

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



}