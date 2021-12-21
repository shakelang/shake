package io.github.shakelang.shake.lexer.token.stream

import io.github.shakelang.parseutils.characters.source.CharacterSource
import io.github.shakelang.parseutils.characters.streaming.SourceCharacterInputStream
import io.github.shakelang.shake.lexer.token.TokenType
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class OnDemandLexingTokenInputStreamTests {

    @Test
    fun testNextType() {

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testNextType()")
            )
        )

        assertEquals(TokenType.KEYWORD_INT, dbtis.nextType())
        assertEquals(2, dbtis.actualEnd)
        assertEquals(TokenType.IDENTIFIER, dbtis.nextType())
        assertEquals(4, dbtis.actualEnd)
        assertEquals(TokenType.ASSIGN, dbtis.nextType())
        assertEquals(6, dbtis.actualEnd)
        assertEquals(TokenType.INTEGER, dbtis.nextType())
        assertEquals(9, dbtis.actualEnd)
        assertEquals(TokenType.SEMICOLON, dbtis.nextType())
        assertEquals(10, dbtis.actualEnd)

    }

    @Test
    fun testNextValue() {

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testNextValue()")
            )
        )

        assertEquals(null, dbtis.nextValue())
        assertEquals(2, dbtis.actualEnd)
        assertEquals("i", dbtis.nextValue())
        assertEquals(4, dbtis.actualEnd)
        assertEquals(null, dbtis.nextValue())
        assertEquals(6, dbtis.actualEnd)
        assertEquals("10", dbtis.nextValue())
        assertEquals(9, dbtis.actualEnd)
        assertEquals(null, dbtis.nextValue())
        assertEquals(10, dbtis.actualEnd)

    }

    @Test
    fun testNextToken() {

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testNextToken()")
            )
        )

        var next = dbtis.next()
        assertEquals(TokenType.KEYWORD_INT, next.type)
        assertEquals(2, next.end)
        assertEquals(null, next.value)
        assertEquals(0, next.start)
        next = dbtis.next()
        assertEquals(TokenType.IDENTIFIER, next.type)
        assertEquals(4, next.end)
        assertEquals("i", next.value)
        assertEquals(4, next.start)
        next = dbtis.next()
        assertEquals(TokenType.ASSIGN, next.type)
        assertEquals(6, next.end)
        assertEquals(null, next.value)
        assertEquals(6, next.start)
        next = dbtis.next()
        assertEquals(TokenType.INTEGER, next.type)
        assertEquals(9, next.end)
        assertEquals("10", next.value)
        assertEquals(8, next.start)
        next = dbtis.next()
        assertEquals(TokenType.SEMICOLON, next.type)
        assertEquals(10, next.end)
        assertEquals(null, next.value)
        assertEquals(10, next.start)
    }

    @Test
    fun testSkip() {

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testSkip()")
            )
        )

        assertEquals(-1, dbtis.position)
        dbtis.skip()
        assertEquals(0, dbtis.position)
        dbtis.skip(2)
        assertEquals(2, dbtis.position)
        dbtis.skip()
        assertEquals(3, dbtis.position)
        dbtis.skip()
        assertEquals(4, dbtis.position)
        assertEquals("Input already finished", assertFailsWith<Error> { dbtis.skip() }.message)

    }

    @Test
    fun testHas() {

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testHas()")
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

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testHasNext()")
            )
        )

        assertEquals(true, dbtis.hasNext())
        dbtis.skip(2)
        assertEquals(true, dbtis.hasNext())
        dbtis.skip(3)
        assertEquals(false, dbtis.hasNext())

    }

    @Test
    fun testHasNextWithEmptyAndPosition() {

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("", "OnDemandLexingTokenInputStreamTests#testHasNextWithEmptyAndPosition()")
            )
        )

        assertEquals(false, dbtis.hasNext())
        assertEquals(-1, dbtis.position)

    }

    @Test
    fun testActualEnd() {

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testActualEnd()")
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

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testActualStart()")
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

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testActualType()")
            )
        )

        dbtis.skip()
        assertEquals(TokenType.KEYWORD_INT, dbtis.actualType)
        dbtis.skip()
        assertEquals(TokenType.IDENTIFIER, dbtis.actualType)
        dbtis.skip()
        assertEquals(TokenType.ASSIGN, dbtis.actualType)
        dbtis.skip()
        assertEquals(TokenType.INTEGER, dbtis.actualType)
        dbtis.skip()
        assertEquals(TokenType.SEMICOLON, dbtis.actualType)

    }

    @Test
    fun testActualValue() {

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testActualValue()")
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

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testActual()")
            )
        )

        dbtis.skip()
        var actual = dbtis.actual
        assertEquals(TokenType.KEYWORD_INT, actual.type)
        assertEquals(null, actual.value)
        assertEquals(2, actual.end)
        assertEquals(0, actual.start)
        dbtis.skip()
        actual = dbtis.actual
        assertEquals(TokenType.IDENTIFIER, actual.type)
        assertEquals("i", actual.value)
        assertEquals(4, actual.end)
        assertEquals(4, actual.start)
        dbtis.skip()
        actual = dbtis.actual
        assertEquals(TokenType.ASSIGN, actual.type)
        assertEquals(null, actual.value)
        assertEquals(6, actual.end)
        assertEquals(6, actual.start)
        dbtis.skip()
        actual = dbtis.actual
        assertEquals(TokenType.INTEGER, actual.type)
        assertEquals("10", actual.value)
        assertEquals(9, actual.end)
        assertEquals(8, actual.start)
        dbtis.skip()
        actual = dbtis.actual
        assertEquals(TokenType.SEMICOLON, actual.type)
        assertEquals(null, actual.value)
        assertEquals(10, actual.end)
        assertEquals(10, actual.start)

    }

    @Test
    fun testPeekType() {

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testPeekType()")
            )
        )

        assertEquals(TokenType.KEYWORD_INT, dbtis.peekType())
        dbtis.skip()
        assertEquals(TokenType.IDENTIFIER, dbtis.peekType())
        dbtis.skip()
        assertEquals(TokenType.ASSIGN, dbtis.peekType())
        dbtis.skip()
        assertEquals(TokenType.INTEGER, dbtis.peekType())
        dbtis.skip()
        assertEquals(TokenType.SEMICOLON, dbtis.peekType())

    }

    @Test
    fun testPeekEnd() {

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testPeekEnd()")
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

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testPeekStart()")
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

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testPeekValue()")
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

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testPeek()")
            )
        )

        var token = dbtis.peek()
        assertEquals(TokenType.KEYWORD_INT, token.type)
        assertEquals(null, token.value)
        assertEquals(0, token.start)
        assertEquals(2, token.end)
        dbtis.skip()
        token = dbtis.peek()
        assertEquals(TokenType.IDENTIFIER, token.type)
        assertEquals("i", token.value)
        assertEquals(4, token.start)
        assertEquals(4, token.end)
        dbtis.skip()
        token = dbtis.peek()
        assertEquals(TokenType.ASSIGN, token.type)
        assertEquals(null, token.value)
        assertEquals(6, token.start)
        assertEquals(6, token.end)
        dbtis.skip()
        token = dbtis.peek()
        assertEquals(TokenType.INTEGER, token.type)
        assertEquals("10", token.value)
        assertEquals(8, token.start)
        assertEquals(9, token.end)
        dbtis.skip()
        token = dbtis.peek()
        assertEquals(TokenType.SEMICOLON, token.type)
        assertEquals(null, token.value)
        assertEquals(10, token.start)
        assertEquals(10, token.end)

    }

    @Test
    fun testPeekType2() {

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testPeekType2()")
            )
        )

        assertEquals(TokenType.KEYWORD_INT, dbtis.peekType(1))
        assertEquals(TokenType.IDENTIFIER, dbtis.peekType(2))
        assertEquals(TokenType.ASSIGN, dbtis.peekType(3))
        assertEquals(TokenType.INTEGER, dbtis.peekType(4))
        assertEquals(TokenType.SEMICOLON, dbtis.peekType(5))
        dbtis.skip()
        assertEquals(TokenType.IDENTIFIER, dbtis.peekType(1))
        assertEquals(TokenType.ASSIGN, dbtis.peekType(2))
        assertEquals(TokenType.INTEGER, dbtis.peekType(3))
        assertEquals(TokenType.SEMICOLON, dbtis.peekType(4))
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(5) }.message)
        dbtis.skip()
        assertEquals(TokenType.ASSIGN, dbtis.peekType(1))
        assertEquals(TokenType.INTEGER, dbtis.peekType(2))
        assertEquals(TokenType.SEMICOLON, dbtis.peekType(3))
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(5) }.message)
        dbtis.skip()
        assertEquals(TokenType.INTEGER, dbtis.peekType(1))
        assertEquals(TokenType.SEMICOLON, dbtis.peekType(2))
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(3) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(5) }.message)
        dbtis.skip()
        assertEquals(TokenType.SEMICOLON, dbtis.peekType(1))
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(2) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(3) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(5) }.message)

    }

    @Test
    fun testPeekEnd2() {

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testPeekEnd2()")
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

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testPeekStart2()")
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

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testPeekValue2()")
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

        val dbtis = OnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testPeek2()")
            )
        )

        var token = dbtis.peek(1)
        assertEquals(TokenType.KEYWORD_INT, token.type)
        assertEquals(null, token.value)
        assertEquals(0, token.start)
        assertEquals(2, token.end)
        token = dbtis.peek(2)
        assertEquals(TokenType.IDENTIFIER, token.type)
        assertEquals("i", token.value)
        assertEquals(4, token.start)
        assertEquals(4, token.end)
        token = dbtis.peek(3)
        assertEquals(TokenType.ASSIGN, token.type)
        assertEquals(null, token.value)
        assertEquals(6, token.start)
        assertEquals(6, token.end)
        token = dbtis.peek(4)
        assertEquals(TokenType.INTEGER, token.type)
        assertEquals("10", token.value)
        assertEquals(8, token.start)
        assertEquals(9, token.end)
        token = dbtis.peek(5)
        assertEquals(TokenType.SEMICOLON, token.type)
        assertEquals(null, token.value)
        assertEquals(10, token.start)
        assertEquals(10, token.end)

        dbtis.skip()

        token = dbtis.peek(1)
        assertEquals(TokenType.IDENTIFIER, token.type)
        assertEquals("i", token.value)
        assertEquals(4, token.start)
        assertEquals(4, token.end)
        token = dbtis.peek(2)
        assertEquals(TokenType.ASSIGN, token.type)
        assertEquals(null, token.value)
        assertEquals(6, token.start)
        assertEquals(6, token.end)
        token = dbtis.peek(3)
        assertEquals(TokenType.INTEGER, token.type)
        assertEquals("10", token.value)
        assertEquals(8, token.start)
        assertEquals(9, token.end)
        token = dbtis.peek(4)
        assertEquals(TokenType.SEMICOLON, token.type)
        assertEquals(null, token.value)
        assertEquals(10, token.start)
        assertEquals(10, token.end)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(5) }.message)

        dbtis.skip()

        token = dbtis.peek(1)
        assertEquals(TokenType.ASSIGN, token.type)
        assertEquals(null, token.value)
        assertEquals(6, token.start)
        assertEquals(6, token.end)
        token = dbtis.peek(2)
        assertEquals(TokenType.INTEGER, token.type)
        assertEquals("10", token.value)
        assertEquals(8, token.start)
        assertEquals(9, token.end)
        token = dbtis.peek(3)
        assertEquals(TokenType.SEMICOLON, token.type)
        assertEquals(null, token.value)
        assertEquals(10, token.start)
        assertEquals(10, token.end)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(5) }.message)

        dbtis.skip()

        token = dbtis.peek(1)
        assertEquals(TokenType.INTEGER, token.type)
        assertEquals("10", token.value)
        assertEquals(8, token.start)
        assertEquals(9, token.end)
        token = dbtis.peek(2)
        assertEquals(TokenType.SEMICOLON, token.type)
        assertEquals(null, token.value)
        assertEquals(10, token.start)
        assertEquals(10, token.end)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(3) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(5) }.message)

        dbtis.skip()

        token = dbtis.peek(1)
        assertEquals(TokenType.SEMICOLON, token.type)
        assertEquals(null, token.value)
        assertEquals(10, token.start)
        assertEquals(10, token.end)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(2) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(3) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(5) }.message)

    }

}