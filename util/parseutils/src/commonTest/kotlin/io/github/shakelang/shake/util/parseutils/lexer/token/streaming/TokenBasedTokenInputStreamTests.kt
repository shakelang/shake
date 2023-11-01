package io.github.shakelang.shake.util.parseutils.lexer.token.streaming

import io.github.shakelang.shake.util.parseutils.characters.position.Position
import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.util.parseutils.characters.source.CharacterSource
import io.github.shakelang.shake.util.parseutils.lexer.token.stream.TokenBasedTokenInputStream
import io.github.shakelang.shake.lexer.token.Token
import kotlin.test.*

class TokenBasedTokenInputStreamTests {

    enum class TokenType : io.github.shakelang.shake.util.parseutils.lexer.token.TokenType {
        IDENTIFIER, NUMBER, STRING;

        override val hasValue: Boolean
            get() = this == IDENTIFIER

        override fun length(value: String?): Int {
            return value?.length ?: 0
        }
    }

    class PositionMap(
        override val source: CharacterSource,
        override val lineSeparators: IntArray,
    ) : io.github.shakelang.shake.util.parseutils.characters.position.PositionMap

    @Test
    fun testConstruct() {
        val tokens = arrayOf(
            Token(TokenType.IDENTIFIER, "test", 0, 3),
            Token(TokenType.NUMBER, "123", 4, 6),
            Token(TokenType.STRING, "\"test\"", 7, 12)
        )

        val map = PositionMap(
            CharacterSource.from("test 123 \"test\"", "test"),
            intArrayOf()
        )

        val stream = TokenBasedTokenInputStream(tokens, map)
    }

    @Test
    fun testNext() {
        val tokens = arrayOf(
            Token(TokenType.IDENTIFIER, "test", 0, 3),
            Token(TokenType.NUMBER, "123", 4, 6),
            Token(TokenType.STRING, "\"test\"", 7, 12)
        )

        val map = PositionMap(
            CharacterSource.from("test 123 \"test\"", "test"),
            intArrayOf()
        )

        val stream = TokenBasedTokenInputStream(tokens, map)

        assertEquals(tokens[0], stream.next())
        assertEquals(tokens[1], stream.next())
        assertEquals(tokens[2], stream.next())

        assertFailsWith<Error>("Not enough tokens left") {
            stream.next()
        }
    }

    @Test
    fun testPeek() {
        val tokens = arrayOf(
            Token(TokenType.IDENTIFIER, "test", 0, 3),
            Token(TokenType.NUMBER, "123", 4, 6),
            Token(TokenType.STRING, "\"test\"", 7, 12)
        )

        val map = PositionMap(
            CharacterSource.from("test 123 \"test\"", "test"),
            intArrayOf()
        )

        val stream = TokenBasedTokenInputStream(tokens, map)

        assertEquals(tokens[0], stream.peek())
        assertEquals(tokens[0], stream.peek())

        stream.next()

        assertEquals(tokens[1], stream.peek())
        assertEquals(tokens[1], stream.peek())

        stream.next()

        assertEquals(tokens[2], stream.peek())
        assertEquals(tokens[2], stream.peek())

        stream.next()

        assertFailsWith<Error>("Not enough tokens left") {
            stream.peek()
        }
    }

    @Test
    fun testHas() {
        val tokens = arrayOf(
            Token(TokenType.IDENTIFIER, "test", 0, 3),
            Token(TokenType.NUMBER, "123", 4, 6),
            Token(TokenType.STRING, "\"test\"", 7, 12)
        )

        val map = PositionMap(
            CharacterSource.from("test 123 \"test\"", "test"),
            intArrayOf()
        )

        val stream = TokenBasedTokenInputStream(tokens, map)

        assertTrue (stream.has(1))
        assertTrue (stream.has(2))
        assertTrue (stream.has(3))
        assertFalse (stream.has(4))
        assertFalse (stream.has(5))

        stream.next()

        assertTrue (stream.has(1))
        assertTrue (stream.has(2))
        assertFalse (stream.has(3))
        assertFalse (stream.has(4))
        assertFalse (stream.has(5))

        stream.next()

        assertTrue (stream.has(1))
        assertFalse (stream.has(2))
        assertFalse (stream.has(3))
        assertFalse (stream.has(4))
        assertFalse (stream.has(5))

        stream.next()

        assertFalse (stream.has(1))
        assertFalse (stream.has(2))
        assertFalse (stream.has(3))
        assertFalse (stream.has(4))
        assertFalse (stream.has(5))
    }

    @Test
    fun testHasNext() {
        val tokens = arrayOf(
            Token(TokenType.IDENTIFIER, "test", 0, 3),
            Token(TokenType.NUMBER, "123", 4, 6),
            Token(TokenType.STRING, "\"test\"", 7, 12)
        )

        val map = PositionMap(
            CharacterSource.from("test 123 \"test\"", "test"),
            intArrayOf()
        )

        val stream = TokenBasedTokenInputStream(tokens, map)

        assertTrue (stream.hasNext())
        stream.next()
        assertTrue (stream.hasNext())
        stream.next()
        assertTrue (stream.hasNext())
        stream.next()
        assertFalse (stream.hasNext())
    }

   @Test
    fun testSource() {
        val tokens = arrayOf(
            Token(TokenType.IDENTIFIER, "test", 0, 3),
            Token(TokenType.NUMBER, "123", 4, 6),
            Token(TokenType.STRING, "\"test\"", 7, 12)
        )

        val map = PositionMap(
            CharacterSource.from("test 123 \"test\"", "test"),
            intArrayOf()
        )

        val stream = TokenBasedTokenInputStream(tokens, map)

        assertEquals(map.location, stream.source)
    }

    @Test
    fun testSize() {
        val tokens = arrayOf(
            Token(TokenType.IDENTIFIER, "test", 0, 3),
            Token(TokenType.NUMBER, "123", 4, 6),
            Token(TokenType.STRING, "\"test\"", 7, 12)
        )

        val map = PositionMap(
            CharacterSource.from("test 123 \"test\"", "test"),
            intArrayOf()
        )

        val stream = TokenBasedTokenInputStream(tokens, map)

        assertEquals(3, stream.size)
    }

    @Test
    fun testGet() {
        val tokens = arrayOf(
            Token(TokenType.IDENTIFIER, "test", 0, 3),
            Token(TokenType.NUMBER, "123", 4, 6),
            Token(TokenType.STRING, "\"test\"", 7, 12)
        )

        val map = PositionMap(
            CharacterSource.from("test 123 \"test\"", "test"),
            intArrayOf()
        )

        val stream = TokenBasedTokenInputStream(tokens, map)

        assertEquals(tokens[0], stream[0])
        assertEquals(tokens[1], stream[1])
        assertEquals(tokens[2], stream[2])

        assertFailsWith<Error>("Invalid index") {
            stream[3]
        }
        assertFailsWith<Error>("Invalid index") {
            stream[-1]
        }
    }

    @Test
    fun testGetType() {
        val tokens = arrayOf(
            Token(TokenType.IDENTIFIER, "test", 0, 3),
            Token(TokenType.NUMBER, "123", 4, 6),
            Token(TokenType.STRING, "\"test\"", 7, 12),
            Token(TokenType.STRING, "\"test\"", 7, 12)
        )

        val map = PositionMap(
            CharacterSource.from("test 123 \"test\" \"test\"", "test"),
            intArrayOf()
        )

        val stream = TokenBasedTokenInputStream(tokens, map)

        assertEquals(TokenType.IDENTIFIER, stream.getType(0))
        assertEquals(TokenType.NUMBER, stream.getType(1))
        assertEquals(TokenType.STRING, stream.getType(2))
        assertEquals(TokenType.STRING, stream.getType(3))

        assertFailsWith<Error>("Invalid index") {
            stream.getType(4)
        }
        assertFailsWith<Error>("Invalid index") {
            stream.getType(-1)
        }
    }

@Test
fun testGetValue() {
    val tokens = arrayOf(
        Token(TokenType.IDENTIFIER, "test", 0, 3),
        Token(TokenType.NUMBER, "123", 4, 6),
        Token(TokenType.STRING, "\"test\"", 7, 12),
        Token(TokenType.STRING, "\"test\"", 7, 12)
    )

    val map = PositionMap(
        CharacterSource.from("test 123 \"test\" \"test\"", "test"),
        intArrayOf()
    )

    val stream = TokenBasedTokenInputStream(tokens, map)

    assertEquals("test", stream.getValue(0))
    assertEquals("123", stream.getValue(1))
    assertEquals("\"test\"", stream.getValue(2))
    assertEquals("\"test\"", stream.getValue(3))

    assertFailsWith<Error>("Invalid index") {
        stream.getValue(4)
    }
    assertFailsWith<Error>("Invalid index") {
        stream.getValue(-1)
    }
}

    @Test
    fun testGetStart() {
        val tokens = arrayOf(
            Token(TokenType.IDENTIFIER, "test", 0, 3),
            Token(TokenType.NUMBER, "123", 4, 6),
            Token(TokenType.STRING, "\"test\"", 7, 12),
            Token(TokenType.STRING, "\"test\"", 7, 12)
        )

        val map = PositionMap(
            CharacterSource.from("test 123 \"test\" \"test\"", "test"),
            intArrayOf()
        )

        val stream = TokenBasedTokenInputStream(tokens, map)

        assertEquals(0, stream.getStart(0))
        assertEquals(4, stream.getStart(1))
        assertEquals(7, stream.getStart(2))

        assertFailsWith<Error>("Invalid index") {
            stream.getStart(4)
        }
        assertFailsWith<Error>("Invalid index") {
            stream.getStart(-1)
        }
    }

    @Test
    fun testGetEnd() {
        val tokens = arrayOf(
            Token(TokenType.IDENTIFIER, "test", 0, 3),
            Token(TokenType.NUMBER, "123", 4, 6),
            Token(TokenType.STRING, "\"test\"", 7, 12),
            Token(TokenType.STRING, "\"test\"", 7, 12)
        )

        val map = PositionMap(
            CharacterSource.from("test 123 \"test\" \"test\"", "test"),
            intArrayOf()
        )

        val stream = TokenBasedTokenInputStream(tokens, map)

        assertEquals(3, stream.getEnd(0))
        assertEquals(6, stream.getEnd(1))
        assertEquals(12, stream.getEnd(2))

        assertFailsWith<Error>("Invalid index") {
            stream.getEnd(4)
        }
        assertFailsWith<Error>("Invalid index") {
            stream.getEnd(-1)
        }
    }
}