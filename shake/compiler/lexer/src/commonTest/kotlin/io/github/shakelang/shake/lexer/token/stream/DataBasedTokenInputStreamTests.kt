@file:Suppress("deprecation")
package io.github.shakelang.shake.lexer.token.stream

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.parseutils.characters.source.CharacterSource
import io.github.shakelang.shake.lexer.token.ShakeTokenType
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DataBasedTokenInputStreamTests {

    @Test
    fun testNextType() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testNextType()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 7, 8),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testNextType()"),
                intArrayOf()
            )
        )

        assertEquals(ShakeTokenType.KEYWORD_INT, dbtis.nextType())
        assertEquals(2, dbtis.actualEnd)
        assertEquals(ShakeTokenType.IDENTIFIER, dbtis.nextType())
        assertEquals(4, dbtis.actualEnd)
        assertEquals(ShakeTokenType.ASSIGN, dbtis.nextType())
        assertEquals(6, dbtis.actualEnd)
        assertEquals(ShakeTokenType.INTEGER, dbtis.nextType())
        assertEquals(7, dbtis.actualEnd)
        assertEquals(ShakeTokenType.SEMICOLON, dbtis.nextType())
        assertEquals(8, dbtis.actualEnd)

    }

    @Test
    fun testNextValue() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testNextValue()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 7, 8),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testNextValue()"),
                intArrayOf()
            )
        )

        assertEquals(null, dbtis.nextValue())
        assertEquals(2, dbtis.actualEnd)
        assertEquals("i", dbtis.nextValue())
        assertEquals(4, dbtis.actualEnd)
        assertEquals(null, dbtis.nextValue())
        assertEquals(6, dbtis.actualEnd)
        assertEquals("10", dbtis.nextValue())
        assertEquals(7, dbtis.actualEnd)
        assertEquals(null, dbtis.nextValue())
        assertEquals(8, dbtis.actualEnd)

    }

    @Test
    fun testNextToken() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testNextToken()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testNextToken()"),
                intArrayOf()
            )
        )

        var next = dbtis.next()
        assertEquals(ShakeTokenType.KEYWORD_INT, next.type)
        assertEquals(2, next.end)
        assertEquals(null, next.value)
        assertEquals(0, next.start)
        next = dbtis.next()
        assertEquals(ShakeTokenType.IDENTIFIER, next.type)
        assertEquals(4, next.end)
        assertEquals("i", next.value)
        assertEquals(4, next.start)
        next = dbtis.next()
        assertEquals(ShakeTokenType.ASSIGN, next.type)
        assertEquals(6, next.end)
        assertEquals(null, next.value)
        assertEquals(6, next.start)
        next = dbtis.next()
        assertEquals(ShakeTokenType.INTEGER, next.type)
        assertEquals(9, next.end)
        assertEquals("10", next.value)
        assertEquals(8, next.start)
        next = dbtis.next()
        assertEquals(ShakeTokenType.SEMICOLON, next.type)
        assertEquals(10, next.end)
        assertEquals(null, next.value)
        assertEquals(10, next.start)
    }

    @Test
    fun testGetType() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testGetType()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testGetType()"),
                intArrayOf()
            )
        )

        assertEquals(ShakeTokenType.KEYWORD_INT, dbtis.getType(0))
        assertEquals(ShakeTokenType.IDENTIFIER, dbtis.getType(1))
        assertEquals(ShakeTokenType.ASSIGN, dbtis.getType(2))
        assertEquals(ShakeTokenType.INTEGER, dbtis.getType(3))
        assertEquals(ShakeTokenType.SEMICOLON, dbtis.getType(4))

    }

    @Test
    fun testGetEnd() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testGetEnd()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testGetEnd()"),
                intArrayOf()
            )
        )

        assertEquals(2, dbtis.getEnd(0))
        assertEquals(4, dbtis.getEnd(1))
        assertEquals(6, dbtis.getEnd(2))
        assertEquals(9, dbtis.getEnd(3))
        assertEquals(10, dbtis.getEnd(4))

    }

    @Test
    fun testGetStart() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testGetStart()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testGetStart()"),
                intArrayOf()
            )
        )

        assertEquals(0, dbtis.getStart(0))
        assertEquals(4, dbtis.getStart(1))
        assertEquals(6, dbtis.getStart(2))
        assertEquals(8, dbtis.getStart(3))
        assertEquals(10, dbtis.getStart(4))

    }

    @Test
    fun testGetValue() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testGetValue()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testGetValue()"),
                intArrayOf()
            )
        )

        assertEquals(null, dbtis.getValue(0))
        assertEquals("i", dbtis.getValue(1))
        assertEquals(null, dbtis.getValue(2))
        assertEquals("10", dbtis.getValue(3))
        assertEquals(null, dbtis.getValue(4))

    }

    @Test
    fun testGetHasValue() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testGetHasValue()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testGetHasValue()"),
                intArrayOf()
            )
        )

        assertEquals(false, dbtis.getHasValue(0))
        assertEquals(true, dbtis.getHasValue(1))
        assertEquals(false, dbtis.getHasValue(2))
        assertEquals(true, dbtis.getHasValue(3))
        assertEquals(false, dbtis.getHasValue(4))

    }

    @Test
    fun testSkip() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testSkip()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testSkip()"),
                intArrayOf()
            )
        )

        assertEquals(dbtis.position, -1)
        dbtis.skip()
        assertEquals(dbtis.position, 0)
        dbtis.skip(2)
        assertEquals(dbtis.position, 2)
        dbtis.skip()
        assertEquals(dbtis.position, 3)
        dbtis.skip()
        assertEquals(dbtis.position, 4)
        assertEquals("Input already finished", assertFailsWith<Error> { dbtis.skip() }.message)

    }

    @Test
    fun testSetPosition() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testSetPosition()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testSetPosition()"),
                intArrayOf()
            )
        )

        assertEquals(dbtis.position, -1)
        dbtis.position = 0
        assertEquals(dbtis.position, 0)
        assertEquals(2, dbtis.actualEnd)
        assertEquals(null, dbtis.actualValue)
        dbtis.position = 1
        assertEquals(dbtis.position, 1)
        assertEquals(4, dbtis.actualEnd)
        assertEquals("i", dbtis.actualValue)
        dbtis.position = 2
        assertEquals(dbtis.position, 2)
        assertEquals(6, dbtis.actualEnd)
        assertEquals(null, dbtis.actualValue)
        dbtis.position = 3
        assertEquals(dbtis.position, 3)
        assertEquals(9, dbtis.actualEnd)
        assertEquals("10", dbtis.actualValue)
        dbtis.position = 4
        assertEquals(dbtis.position, 4)
        assertEquals(10, dbtis.actualEnd)
        assertEquals(null, dbtis.actualValue)
        dbtis.position = 3
        assertEquals(dbtis.position, 3)
        assertEquals(9, dbtis.actualEnd)
        assertEquals("10", dbtis.actualValue)

    }

    @Test
    fun testHas() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testHas()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testHas()"),
                intArrayOf()
            )
        )

        assertEquals(true, dbtis.has(1))
        assertEquals(true, dbtis.has(2))
        assertEquals(true, dbtis.has(3))
        assertEquals(true, dbtis.has(4))
        assertEquals(true, dbtis.has(5))
        assertEquals(false, dbtis.has(6))
        dbtis.skip(2)
        assertEquals(true, dbtis.has(1))
        assertEquals(true, dbtis.has(2))
        assertEquals(true, dbtis.has(3))
        assertEquals(false, dbtis.has(4))

    }

    @Test
    fun testHasNext() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testHasNext()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testHasNext()"),
                intArrayOf()
            )
        )

        assertEquals(true, dbtis.hasNext())
        dbtis.skip(2)
        assertEquals(true, dbtis.hasNext())
        dbtis.skip(3)
        assertEquals(false, dbtis.hasNext())

    }

    @Test
    fun testHasNextWithEmpty() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testHasNextWithEmpty()",
            arrayOf(),
            arrayOf(),
            intArrayOf(),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testHasNextWithEmpty()"),
                intArrayOf()
            )
        )

        assertEquals(false, dbtis.hasNext())

    }

    @Test
    fun testHasNextWithEmptyAndPosition() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testHasNextWithEmptyAndPosition()",
            arrayOf(),
            arrayOf(),
            intArrayOf(),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testHasNextWithEmptyAndPosition()"),
                intArrayOf()
            )
        )

        assertEquals(false, dbtis.hasNext())
        assertEquals(-1, dbtis.position)

    }

    @Test
    fun testActualEnd() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testActualEnd()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testActualEnd()"),
                intArrayOf()
            )
        )

        dbtis.skip()
        assertEquals(2, dbtis.actualEnd)
        dbtis.skip()
        assertEquals(4, dbtis.actualEnd)
        dbtis.skip()
        assertEquals(6, dbtis.actualEnd)
        dbtis.skip()
        assertEquals(9, dbtis.actualEnd)
        dbtis.skip()
        assertEquals(10, dbtis.actualEnd)

    }

    @Test
    fun testActualStart() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testActualStart()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testActualStart()"),
                intArrayOf()
            )
        )

        dbtis.skip()
        assertEquals(0, dbtis.actualStart)
        dbtis.skip()
        assertEquals(4, dbtis.actualStart)
        dbtis.skip()
        assertEquals(6, dbtis.actualStart)
        dbtis.skip()
        assertEquals(8, dbtis.actualStart)
        dbtis.skip()
        assertEquals(10, dbtis.actualStart)

    }

    @Test
    fun testActualType() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testActualType()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testActualType()"),
                intArrayOf()
            )
        )

        dbtis.skip()
        assertEquals(ShakeTokenType.KEYWORD_INT, dbtis.actualType)
        dbtis.skip()
        assertEquals(ShakeTokenType.IDENTIFIER, dbtis.actualType)
        dbtis.skip()
        assertEquals(ShakeTokenType.ASSIGN, dbtis.actualType)
        dbtis.skip()
        assertEquals(ShakeTokenType.INTEGER, dbtis.actualType)
        dbtis.skip()
        assertEquals(ShakeTokenType.SEMICOLON, dbtis.actualType)

    }

    @Test
    fun testActualValue() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testActualValue()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testActualValue()"),
                intArrayOf()
            )
        )

        dbtis.skip()
        assertEquals(null, dbtis.actualValue)
        dbtis.skip()
        assertEquals("i", dbtis.actualValue)
        dbtis.skip()
        assertEquals(null, dbtis.actualValue)
        dbtis.skip()
        assertEquals("10", dbtis.actualValue)
        dbtis.skip()
        assertEquals(null, dbtis.actualValue)

    }

    @Test
    fun testActual() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testActual()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testActual()"),
                intArrayOf()
            )
        )

        dbtis.skip()
        var actual = dbtis.actual
        assertEquals(ShakeTokenType.KEYWORD_INT, actual.type)
        assertEquals(null, actual.value)
        assertEquals(2, actual.end)
        assertEquals(0, actual.start)
        dbtis.skip()
        actual = dbtis.actual
        assertEquals(ShakeTokenType.IDENTIFIER, actual.type)
        assertEquals("i", actual.value)
        assertEquals(4, actual.end)
        assertEquals(4, actual.start)
        dbtis.skip()
        actual = dbtis.actual
        assertEquals(ShakeTokenType.ASSIGN, actual.type)
        assertEquals(null, actual.value)
        assertEquals(6, actual.end)
        assertEquals(6, actual.start)
        dbtis.skip()
        actual = dbtis.actual
        assertEquals(ShakeTokenType.INTEGER, actual.type)
        assertEquals("10", actual.value)
        assertEquals(9, actual.end)
        assertEquals(8, actual.start)
        dbtis.skip()
        actual = dbtis.actual
        assertEquals(ShakeTokenType.SEMICOLON, actual.type)
        assertEquals(null, actual.value)
        assertEquals(10, actual.end)
        assertEquals(10, actual.start)

    }

    @Test
    fun testPeekType() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testPeekType()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testPeekType()"),
                intArrayOf()
            )
        )

        assertEquals(ShakeTokenType.KEYWORD_INT, dbtis.peekType())
        dbtis.skip()
        assertEquals(ShakeTokenType.IDENTIFIER, dbtis.peekType())
        dbtis.skip()
        assertEquals(ShakeTokenType.ASSIGN, dbtis.peekType())
        dbtis.skip()
        assertEquals(ShakeTokenType.INTEGER, dbtis.peekType())
        dbtis.skip()
        assertEquals(ShakeTokenType.SEMICOLON, dbtis.peekType())

    }

    @Test
    fun testPeekEnd() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testPeekEnd()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testPeekEnd()"),
                intArrayOf()
            )
        )

        assertEquals(2, dbtis.peekEnd())
        dbtis.skip()
        assertEquals(4, dbtis.peekEnd())
        dbtis.skip()
        assertEquals(6, dbtis.peekEnd())
        dbtis.skip()
        assertEquals(9, dbtis.peekEnd())
        dbtis.skip()
        assertEquals(10, dbtis.peekEnd())

    }

    @Test
    fun testPeekStart() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testPeekStart()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testPeekStart()"),
                intArrayOf()
            )
        )

        assertEquals(0, dbtis.peekStart())
        dbtis.skip()
        assertEquals(4, dbtis.peekStart())
        dbtis.skip()
        assertEquals(6, dbtis.peekStart())
        dbtis.skip()
        assertEquals(8, dbtis.peekStart())
        dbtis.skip()
        assertEquals(10, dbtis.peekStart())

    }

    @Test
    fun testPeekValue() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testPeekValue()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testPeekValue()"),
                intArrayOf()
            )
        )

        assertEquals(null, dbtis.peekValue())
        dbtis.skip()
        assertEquals("i", dbtis.peekValue())
        dbtis.skip()
        assertEquals(null, dbtis.peekValue())
        dbtis.skip()
        assertEquals("10", dbtis.peekValue())
        dbtis.skip()
        assertEquals(null, dbtis.peekValue())

    }

    @Test
    fun testPeek() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testPeek()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testPeek()"),
                intArrayOf()
            )
        )

        var token = dbtis.peek()
        assertEquals(ShakeTokenType.KEYWORD_INT, token.type)
        assertEquals(null, token.value)
        assertEquals(0, token.start)
        assertEquals(2, token.end)
        dbtis.skip()
        token = dbtis.peek()
        assertEquals(ShakeTokenType.IDENTIFIER, token.type)
        assertEquals("i", token.value)
        assertEquals(4, token.start)
        assertEquals(4, token.end)
        dbtis.skip()
        token = dbtis.peek()
        assertEquals(ShakeTokenType.ASSIGN, token.type)
        assertEquals(null, token.value)
        assertEquals(6, token.start)
        assertEquals(6, token.end)
        dbtis.skip()
        token = dbtis.peek()
        assertEquals(ShakeTokenType.INTEGER, token.type)
        assertEquals("10", token.value)
        assertEquals(8, token.start)
        assertEquals(9, token.end)
        dbtis.skip()
        token = dbtis.peek()
        assertEquals(ShakeTokenType.SEMICOLON, token.type)
        assertEquals(null, token.value)
        assertEquals(10, token.start)
        assertEquals(10, token.end)

    }

    @Test
    fun testPeekType2() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testPeekType2()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testPeekType2()"),
                intArrayOf()
            )
        )

        assertEquals(ShakeTokenType.KEYWORD_INT, dbtis.peekType(1))
        assertEquals(ShakeTokenType.IDENTIFIER, dbtis.peekType(2))
        assertEquals(ShakeTokenType.ASSIGN, dbtis.peekType(3))
        assertEquals(ShakeTokenType.INTEGER, dbtis.peekType(4))
        assertEquals(ShakeTokenType.SEMICOLON, dbtis.peekType(5))
        dbtis.skip()
        assertEquals(ShakeTokenType.IDENTIFIER, dbtis.peekType(1))
        assertEquals(ShakeTokenType.ASSIGN, dbtis.peekType(2))
        assertEquals(ShakeTokenType.INTEGER, dbtis.peekType(3))
        assertEquals(ShakeTokenType.SEMICOLON, dbtis.peekType(4))
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(5) }.message)
        dbtis.skip()
        assertEquals(ShakeTokenType.ASSIGN, dbtis.peekType(1))
        assertEquals(ShakeTokenType.INTEGER, dbtis.peekType(2))
        assertEquals(ShakeTokenType.SEMICOLON, dbtis.peekType(3))
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(5) }.message)
        dbtis.skip()
        assertEquals(ShakeTokenType.INTEGER, dbtis.peekType(1))
        assertEquals(ShakeTokenType.SEMICOLON, dbtis.peekType(2))
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(3) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(5) }.message)
        dbtis.skip()
        assertEquals(ShakeTokenType.SEMICOLON, dbtis.peekType(1))
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(2) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(3) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(5) }.message)

    }

    @Test
    fun testPeekEnd2() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testPeekEnd2()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testPeekEnd2()"),
                intArrayOf()
            )
        )

        assertEquals(2, dbtis.peekEnd(1))
        assertEquals(4, dbtis.peekEnd(2))
        assertEquals(6, dbtis.peekEnd(3))
        assertEquals(9, dbtis.peekEnd(4))
        assertEquals(10, dbtis.peekEnd(5))
        dbtis.skip()
        assertEquals(4, dbtis.peekEnd(1))
        assertEquals(6, dbtis.peekEnd(2))
        assertEquals(9, dbtis.peekEnd(3))
        assertEquals(10, dbtis.peekEnd(4))
        assertFailsWith<Error> { dbtis.peekEnd(5) }
        dbtis.skip()
        assertEquals(6, dbtis.peekEnd(1))
        assertEquals(9, dbtis.peekEnd(2))
        assertEquals(10, dbtis.peekEnd(3))
        assertFailsWith<Error> { dbtis.peekEnd(4) }
        assertFailsWith<Error> { dbtis.peekEnd(5) }
        dbtis.skip()
        assertEquals(9, dbtis.peekEnd(1))
        assertEquals(10, dbtis.peekEnd(2))
        assertFailsWith<Error> { dbtis.peekEnd(3) }
        assertFailsWith<Error> { dbtis.peekEnd(4) }
        assertFailsWith<Error> { dbtis.peekEnd(5) }
        dbtis.skip()
        assertEquals(10, dbtis.peekEnd(1))
        assertFailsWith<Error> { dbtis.peekEnd(2) }
        assertFailsWith<Error> { dbtis.peekEnd(3) }
        assertFailsWith<Error> { dbtis.peekEnd(4) }
        assertFailsWith<Error> { dbtis.peekEnd(5) }

    }

    @Test
    fun testPeekStart2() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testPeekStart2()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testPeekStart2()"),
                intArrayOf()
            )
        )

        assertEquals(0, dbtis.peekStart(1))
        assertEquals(4, dbtis.peekStart(2))
        assertEquals(6, dbtis.peekStart(3))
        assertEquals(8, dbtis.peekStart(4))
        assertEquals(10, dbtis.peekStart(5))
        dbtis.skip()
        assertEquals(4, dbtis.peekStart(1))
        assertEquals(6, dbtis.peekStart(2))
        assertEquals(8, dbtis.peekStart(3))
        assertEquals(10, dbtis.peekStart(4))
        assertFailsWith<Error> { dbtis.peekStart(5) }
        dbtis.skip()
        assertEquals(6, dbtis.peekStart(1))
        assertEquals(8, dbtis.peekStart(2))
        assertEquals(10, dbtis.peekStart(3))
        assertFailsWith<Error> { dbtis.peekStart(4) }
        assertFailsWith<Error> { dbtis.peekStart(5) }
        dbtis.skip()
        assertEquals(8, dbtis.peekStart(1))
        assertEquals(10, dbtis.peekStart(2))
        assertFailsWith<Error> { dbtis.peekStart(3) }
        assertFailsWith<Error> { dbtis.peekStart(4) }
        assertFailsWith<Error> { dbtis.peekStart(5) }
        dbtis.skip()
        assertEquals(10, dbtis.peekStart(1))
        assertFailsWith<Error> { dbtis.peekStart(2) }
        assertFailsWith<Error> { dbtis.peekStart(3) }
        assertFailsWith<Error> { dbtis.peekStart(4) }
        assertFailsWith<Error> { dbtis.peekStart(5) }
        dbtis.skip()
        assertFailsWith<Error> { dbtis.peekStart(1) }
        assertFailsWith<Error> { dbtis.peekStart(2) }
        assertFailsWith<Error> { dbtis.peekStart(3) }
        assertFailsWith<Error> { dbtis.peekStart(4) }
        assertFailsWith<Error> { dbtis.peekStart(5) }

    }

    @Test
    fun testPeekValue2() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testPeekValue2()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testPeekValue2()"),
                intArrayOf()
            )
        )

        assertEquals(null, dbtis.peekValue(1))
        assertEquals("i", dbtis.peekValue(2))
        assertEquals(null, dbtis.peekValue(3))
        assertEquals("10", dbtis.peekValue(4))
        assertEquals(null, dbtis.peekValue(5))
        dbtis.skip()
        assertEquals("i", dbtis.peekValue(1))
        assertEquals(null, dbtis.peekValue(2))
        assertEquals("10", dbtis.peekValue(3))
        assertEquals(null, dbtis.peekValue(4))
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(5) }.message)
        dbtis.skip()
        assertEquals(null, dbtis.peekValue(1))
        assertEquals("10", dbtis.peekValue(2))
        assertEquals(null, dbtis.peekValue(3))
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(5) }.message)
        dbtis.skip()
        assertEquals("10", dbtis.peekValue(1))
        assertEquals(null, dbtis.peekValue(2))
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(3) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(5) }.message)
        dbtis.skip()
        assertEquals(null, dbtis.peekValue(1))
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(2) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(3) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(5) }.message)
        dbtis.skip()
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(1) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(2) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(3) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(5) }.message)

    }

    @Test
    fun testPeek2() {

        val dbtis = ShakeDataBasedTokenInputStream(
            "DataBasedTokenInputStreamTests#testPeek2()",
            arrayOf(
                ShakeTokenType.KEYWORD_INT,
                ShakeTokenType.IDENTIFIER,
                ShakeTokenType.ASSIGN,
                ShakeTokenType.INTEGER,
                ShakeTokenType.SEMICOLON,
            ),
            arrayOf("i", "10"),
            intArrayOf(2, 4, 6, 9, 10),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "DataBasedTokenInputStreamTests#testPeek2()"),
                intArrayOf()
            )
        )

        var token = dbtis.peek(1)
        assertEquals(ShakeTokenType.KEYWORD_INT, token.type)
        assertEquals(null, token.value)
        assertEquals(0, token.start)
        assertEquals(2, token.end)
        token = dbtis.peek(2)
        assertEquals(ShakeTokenType.IDENTIFIER, token.type)
        assertEquals("i", token.value)
        assertEquals(4, token.start)
        assertEquals(4, token.end)
        token = dbtis.peek(3)
        assertEquals(ShakeTokenType.ASSIGN, token.type)
        assertEquals(null, token.value)
        assertEquals(6, token.start)
        assertEquals(6, token.end)
        token = dbtis.peek(4)
        assertEquals(ShakeTokenType.INTEGER, token.type)
        assertEquals("10", token.value)
        assertEquals(8, token.start)
        assertEquals(9, token.end)
        token = dbtis.peek(5)
        assertEquals(ShakeTokenType.SEMICOLON, token.type)
        assertEquals(null, token.value)
        assertEquals(10, token.start)
        assertEquals(10, token.end)

        dbtis.skip()

        token = dbtis.peek(1)
        assertEquals(ShakeTokenType.IDENTIFIER, token.type)
        assertEquals("i", token.value)
        assertEquals(4, token.start)
        assertEquals(4, token.end)
        token = dbtis.peek(2)
        assertEquals(ShakeTokenType.ASSIGN, token.type)
        assertEquals(null, token.value)
        assertEquals(6, token.start)
        assertEquals(6, token.end)
        token = dbtis.peek(3)
        assertEquals(ShakeTokenType.INTEGER, token.type)
        assertEquals("10", token.value)
        assertEquals(8, token.start)
        assertEquals(9, token.end)
        token = dbtis.peek(4)
        assertEquals(ShakeTokenType.SEMICOLON, token.type)
        assertEquals(null, token.value)
        assertEquals(10, token.start)
        assertEquals(10, token.end)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(5) }.message)

        dbtis.skip()

        token = dbtis.peek(1)
        assertEquals(ShakeTokenType.ASSIGN, token.type)
        assertEquals(null, token.value)
        assertEquals(6, token.start)
        assertEquals(6, token.end)
        token = dbtis.peek(2)
        assertEquals(ShakeTokenType.INTEGER, token.type)
        assertEquals("10", token.value)
        assertEquals(8, token.start)
        assertEquals(9, token.end)
        token = dbtis.peek(3)
        assertEquals(ShakeTokenType.SEMICOLON, token.type)
        assertEquals(null, token.value)
        assertEquals(10, token.start)
        assertEquals(10, token.end)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(5) }.message)

        dbtis.skip()

        token = dbtis.peek(1)
        assertEquals(ShakeTokenType.INTEGER, token.type)
        assertEquals("10", token.value)
        assertEquals(8, token.start)
        assertEquals(9, token.end)
        token = dbtis.peek(2)
        assertEquals(ShakeTokenType.SEMICOLON, token.type)
        assertEquals(null, token.value)
        assertEquals(10, token.start)
        assertEquals(10, token.end)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(3) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(5) }.message)

        dbtis.skip()

        token = dbtis.peek(1)
        assertEquals(ShakeTokenType.SEMICOLON, token.type)
        assertEquals(null, token.value)
        assertEquals(10, token.start)
        assertEquals(10, token.end)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(2) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(3) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(5) }.message)

    }

}