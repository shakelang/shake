package com.shakelang.shake.util.parseutils.characters

import com.shakelang.shake.util.parseutils.characters.source.CharacterSource
import com.shakelang.shake.util.parseutils.characters.streaming.SourceCharacterInputStream
import kotlin.test.*

/**
 * Tests for [SourceCharacterInputStream]
 */
class SourceCharacterInputStreamTests {

    @Test
    fun testSourceCharacterInputStreamContent() {
        val stream = createSourceCharacterInputStream(
            "hello",
            "SourceCharacterInputStreamTests#testSourceCharacterInputStreamContent()"
        )

        assertEquals("hello", stream.content.concatToString())
    }

    @Test
    fun testSourceCharacterInputStreamNext() {
        val stream = createSourceCharacterInputStream(
            "hello",
            "SourceCharacterInputStreamTests#testSourceCharacterInputStreamNext()"
        )

        assertEquals('h', stream.next())
        assertEquals('e', stream.next())
        assertEquals('l', stream.next())
        assertEquals('l', stream.next())
        assertEquals('o', stream.next())

        assertFailsWith<Error>("Error: Not enough characters left") {
            stream.next()
        }
    }

    @Test
    fun testSourceCharacterInputStreamPeek() {
        val stream = createSourceCharacterInputStream(
            "hello",
            "SourceCharacterInputStreamTests#testSourceCharacterInputStreamPeek()"
        )

        assertEquals('h', stream.peek())
        stream.skip()
        assertEquals('e', stream.peek())
        stream.skip()
        assertEquals('l', stream.peek())
        stream.skip()
        assertEquals('l', stream.peek())
        stream.skip()
        assertEquals('o', stream.peek())
        stream.skip()

        assertFailsWith<Error>("Error: Not enough characters left") {
            stream.peek()
        }
    }

    @Test
    fun testSourceCharacterInputStreamSkip() {
        val stream = createSourceCharacterInputStream(
            "hello",
            "SourceCharacterInputStreamTests#testSourceCharacterInputStreamSkip()"
        )

        stream.skip()
        assertEquals('h', stream.actual())
        stream.skip()
        assertEquals('e', stream.actual())
        stream.skip(3)

        assertFailsWith<Error>("Error: Not enough characters left") {
            stream.skip()
        }
    }

    @Test
    fun testSourceCharacterInputStreamActual() {
        val stream = createSourceCharacterInputStream(
            "hello",
            "SourceCharacterInputStreamTests#testSourceCharacterInputStreamActual()"
        )

        stream.skip()
        assertEquals('h', stream.actual())
    }

    @Test
    fun testSourceCharacterInputStreamPosition() {
        val stream = createSourceCharacterInputStream(
            "hello",
            "SourceCharacterInputStreamTests#testSourceCharacterInputStreamPosition()"
        )

        assertSame(-1, stream.position)
        stream.skip()
        assertSame(0, stream.position)
        stream.skip()
        assertSame(1, stream.position)
    }

    @Test
    fun testSourceCharacterInputStreamHas() {
        val stream = createSourceCharacterInputStream(
            "hello",
            "SourceCharacterInputStreamTests#testSourceCharacterInputStreamHas()"
        )

        assertFailsWith<Error>("The given number must be 1 or bigger") {
            stream.has(-10)
        }
        assertFailsWith<Error>("The given number must be 1 or bigger") {
            stream.has(-1)
        }
        assertFailsWith<Error>("The given number must be 1 or bigger") {
            stream.has(0)
        }

        assertTrue(stream.has(1))
        assertTrue(stream.has(5))
        assertFalse(stream.has(6))
    }

    @Test
    fun testSourceCharacterInputStreamHasNext() {
        val stream = createSourceCharacterInputStream(
            "hello",
            "SourceCharacterInputStreamTests#testSourceCharacterInputStreamHasNext()"
        )

        assertTrue(stream.hasNext())
        stream.skip(4)
        assertTrue(stream.hasNext())
        stream.skip()
        assertFalse(stream.hasNext())
    }

    fun createSourceCharacterInputStream(content: String, source: String) =
        SourceCharacterInputStream(CharacterSource.from(content, source))
}
