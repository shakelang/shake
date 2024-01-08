package com.shakelang.util.parseutils.characters.streaming

import com.shakelang.util.parseutils.characters.source.CharacterSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class SourceCharacterInputStreamTests {

    @Test
    fun testConstructors() {
        run {
            val source = CharacterSource.from("Hello World!", "SourceCharacterInputStreamTests#testConstructors")
            val stream = SourceCharacterInputStream(source)

            assertEquals(source, stream.source)
            assertEquals(-1, stream.position)
            assertEquals("Hello World!", stream.content.concatToString())
        }

        run {
            val stream = SourceCharacterInputStream("SourceCharacterInputStreamTests#testConstructors", "Hello World!")

            assertEquals(-1, stream.position)
            assertEquals("Hello World!", stream.content.concatToString())
        }

        run {
            val stream = SourceCharacterInputStream(
                "SourceCharacterInputStreamTests#testConstructors",
                "Hello World!".toCharArray(),
            )

            assertEquals(-1, stream.position)
            assertEquals("Hello World!", stream.content.concatToString())
        }
    }

    @Test
    fun testSkip() {
        val stream = SourceCharacterInputStream("SourceCharacterInputStreamTests#testSkip", "Hello \nWorld!")

        assertEquals(-1, stream.position)
        stream.skip()
        assertEquals(0, stream.position)
        stream.skip()
        assertEquals(1, stream.position)
        stream.skip()
        assertEquals(2, stream.position)
        stream.skip()
        assertEquals(3, stream.position)
        stream.skip(3)
        assertEquals(6, stream.position)
    }

    @Test
    fun testActual() {
        val stream = SourceCharacterInputStream("SourceCharacterInputStreamTests#testActual", "Hello World!")

        assertEquals(-1, stream.position)
        stream.skip()
        assertEquals('H', stream.actual())
        stream.skip()
        assertEquals('e', stream.actual())
        stream.skip()
        assertEquals('l', stream.actual())
        stream.skip()
        assertEquals('l', stream.actual())
        stream.skip()
        assertEquals('o', stream.actual())
        stream.skip()
        assertEquals(' ', stream.actual())
        stream.skip()
        assertEquals('W', stream.actual())
        stream.skip()
        assertEquals('o', stream.actual())
        stream.skip()
        assertEquals('r', stream.actual())
        stream.skip()
        assertEquals('l', stream.actual())
        stream.skip()
        assertEquals('d', stream.actual())
        stream.skip()
        assertEquals('!', stream.actual())
    }

    @Test
    fun testNext() {
        val stream = SourceCharacterInputStream("SourceCharacterInputStreamTests#testNext", "Hello World!")

        assertEquals(-1, stream.position)

        assertEquals('H', stream.next())
        assertEquals('e', stream.next())
        assertEquals('l', stream.next())
        assertEquals('l', stream.next())
        assertEquals('o', stream.next())
        assertEquals(' ', stream.next())
        assertEquals('W', stream.next())
        assertEquals('o', stream.next())
        assertEquals('r', stream.next())
        assertEquals('l', stream.next())
        assertEquals('d', stream.next())
        assertEquals('!', stream.next())
    }

    @Test
    fun checkHasNext() {
        val stream = SourceCharacterInputStream("SourceCharacterInputStreamTests#checkHasNext", "Hello World!")

        assertEquals(-1, stream.position)

        for (i in 0..11) {
            assertEquals(true, stream.hasNext())
            stream.skip()
        }

        assertEquals(false, stream.hasNext())
    }

    @Test
    fun checkHas() {
        val stream = SourceCharacterInputStream("SourceCharacterInputStreamTests#checkHas", "Hello World!")

        assertEquals(-1, stream.position)

        assertFailsWith<Error>("The given number must be 1 or bigger") {
            stream.has(0)
        }

        for (i in 0..11) {
            for (j in 1..30) {
                assertEquals(j < 13 - i, stream.has(j))
            }
            stream.skip()
        }
    }

    @Test
    fun testPeek() {
        val stream = SourceCharacterInputStream("SourceCharacterInputStreamTests#checkHas", "Hello World!")

        assertEquals(-1, stream.position)

        assertEquals('H', stream.peek())
        assertEquals('e', stream.peek(2))
        assertEquals('l', stream.peek(3))
        assertEquals(' ', stream.peek(6))
        assertEquals('W', stream.peek(7))
        assertEquals('!', stream.peek(12))

        assertFailsWith<Error>("Not enough characters left") {
            stream.peek(13)
        }

        assertEquals("Hello", stream.peek(1, 5))

        assertFailsWith<Error>("Peek argument must not be smaller than 0") {
            stream.peek(-1, 6)
        }

        assertFailsWith<Error>("To-argument must be bigger than from-argument") {
            stream.peek(2, 1)
        }

        assertFailsWith<Error>("Not enough characters left") {
            stream.peek(2, 13)
        }

        stream.skip(1)

        assertEquals('e', stream.peek())
        assertEquals('l', stream.peek(2))
        assertEquals('l', stream.peek(3))

        assertEquals("ello ", stream.peek(1, 5))
    }
}
