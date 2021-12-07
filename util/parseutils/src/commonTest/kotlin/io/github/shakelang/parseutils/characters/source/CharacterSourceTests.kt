package io.github.shakelang.parseutils.characters.source

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class CharacterSourceTests {

    @Test
    fun testCharacterSourceGet() {

        val src = CharArraySource("abc".toCharArray(), "CharacterSourceTests#testCharacterSourceGet()")
        assertEquals('a', src[0, 1][0])
        assertEquals('b', src[1, 2][0])
        assertEquals('c', src[2, 3][0])

        assertEquals("ab", src[0, 2].concatToString())
        assertEquals("bc", src[1, 3].concatToString())
        assertEquals("abc", src[0, 3].concatToString())

    }

    @Test
    fun testCharacterSourceLength() {

        val src = CharArraySource("abc".toCharArray(), "CharacterSourceTests#testCharacterSourceLength()")
        assertSame(3, src.length)

    }

    @Test
    fun testCharacterSourceAll() {

        val src = CharArraySource("abc".toCharArray(), "CharacterSourceTests#testCharacterSourceAll()")
        assertEquals("abc", src.all.concatToString())

    }

}