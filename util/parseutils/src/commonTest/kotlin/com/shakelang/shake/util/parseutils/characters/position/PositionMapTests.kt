package com.shakelang.shake.util.parseutils.characters.position

import io.github.shakelang.shake.util.parseutils.characters.source.CharacterSource
import kotlin.test.Test
import kotlin.test.assertEquals

class PositionMapTests {

    @Test
    fun testImplementationWithNewline() {
        val src = CharacterSource.from("abc\nabc\nabc", "PositionMapTests#testImplementationWithNewline")
        val map = PositionMap.PositionMapImpl(src, intArrayOf(3, 7))

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
        val map = PositionMap.PositionMapImpl(src, intArrayOf())

        assertEquals(Position(map, 0, 1, 1), map.resolve(0))
        assertEquals(Position(map, 1, 2, 1), map.resolve(1))
        assertEquals(Position(map, 2, 3, 1), map.resolve(2))
    }

    @Test
    fun testToString() {
        val src = CharacterSource.from("abc\nabc\nabc", "PositionMapTests#testToString")
        val map = PositionMap.PositionMapImpl(src, intArrayOf(3, 7))
        assertEquals("PositionMapTests#testToString", map.toString())
    }
}
