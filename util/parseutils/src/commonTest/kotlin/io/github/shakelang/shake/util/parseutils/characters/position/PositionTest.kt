package io.github.shakelang.shake.util.parseutils.characters.position

import io.github.shakelang.shake.util.parseutils.characters.source.CharacterSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class PositionTest {

    @Test
    fun testConstructor() {
        val src = CharacterSource.from("abc", "PositionTest#testConstructor")
        val map = PositionMap.PositionMapImpl(src, intArrayOf(3, 7))
        val position = Position(map, 1, 2, 1)

        assertEquals(1, position.line)
        assertEquals(2, position.column)
        assertEquals(1, position.index)
    }

    @Test
    fun testCopy() {
        val src = CharacterSource.from("abc", "PositionTest#testCopy")
        val map = PositionMap.PositionMapImpl(src, intArrayOf(3, 7))
        val position = Position(map, 1, 2, 1)

        assertEquals(1, position.line)
        assertEquals(2, position.column)
        assertEquals(1, position.index)

        val copy = position.copy()

        assertEquals(position.line, copy.line)
        assertEquals(position.column, copy.column)
        assertEquals(position.index, copy.index)
    }

    @Test
    fun testEquals() {
        val src = CharacterSource.from("abc", "PositionTest#testEquals")
        val map = PositionMap.PositionMapImpl(src, intArrayOf(3, 7))
        val map2 = PositionMap.PositionMapImpl(src, intArrayOf(3, 7))

        run {
            val position = Position(map, 1, 2, 1)
            assertEquals(position, position)
        }

        run {
            val position = Position(map, 1, 2, 1)
            val position2 = Position(map, 1, 2, 1)
            assertEquals(position, position2)
        }

        run {
            val position1 = Position(map, 1, 2, 1)
            val position2 = Position(map2, 1, 2, 1)
            assertNotEquals(position1, position2)
        }

        run {
            val position1 = Position(map, 1, 2, 1)
            val position2 = Position(map, 1, 3, 1)
            assertNotEquals(position1, position2)
        }

        run {
            val position1 = Position(map, 1, 2, 1)
            val position2 = Position(map, 2, 2, 1)
            assertNotEquals(position1, position2)
        }

        run {
            val position1 = Position(map, 1, 2, 1)
            val position2 = Position(map, 1, 2, 2)
            assertNotEquals(position1, position2)
        }

        run {
            val position1 = Position(map, 1, 2, 1)
            val position2 = Position(map2, 4, 3, 3)
            assertNotEquals(position1, position2)
        }

        run {
            val position1 = Position(map, 1, 2, 1)
            val position2 = 10
            assertNotEquals<Any>(position1, position2)
        }
    }

    @Test
    fun testToString() {
        val src = CharacterSource.from("abc", "PositionTest#testToString")
        val map = PositionMap.PositionMapImpl(src, intArrayOf(3, 7))
        val position = Position(map, 1, 2, 1)
        assertEquals("PositionTest#testToString:1:2", position.toString())
    }

}