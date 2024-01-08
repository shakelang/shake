package com.shakelang.shake.lexer.token.stream

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.util.parseutils.characters.position.PositionMap
import com.shakelang.util.parseutils.characters.source.CharacterSource
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

@Suppress("unused")
class TokenBasedTokenInputStreamTests : FreeSpec({

    "next type" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testNextType()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testNextType()"),
                intArrayOf(),
            ),
        )

        tis.nextType() shouldBe ShakeTokenType.KEYWORD_INT
        tis.actualEnd shouldBe 2
        tis.nextType() shouldBe ShakeTokenType.IDENTIFIER
        tis.actualEnd shouldBe 4
        tis.nextType() shouldBe ShakeTokenType.ASSIGN
        tis.actualEnd shouldBe 6
        tis.nextType() shouldBe ShakeTokenType.INTEGER
        tis.actualEnd shouldBe 9
        tis.nextType() shouldBe ShakeTokenType.SEMICOLON
        tis.actualEnd shouldBe 10
    }

    "next value" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testNextValue()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testNextValue()"),
                intArrayOf(),
            ),
        )

        tis.nextValue() shouldBe null
        tis.nextValue() shouldBe "i"
        tis.nextValue() shouldBe null
        tis.nextValue() shouldBe "10"
        tis.nextValue() shouldBe null
    }

    "next token" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testNextToken()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testNextToken()"),
                intArrayOf(),
            ),
        )

        var token = tis.next()
        token.type shouldBe ShakeTokenType.KEYWORD_INT
        token.value shouldBe null
        token.end shouldBe 2
        token.start shouldBe 0

        token = tis.next()

        token.type shouldBe ShakeTokenType.IDENTIFIER
        token.value shouldBe "i"
        token.end shouldBe 4
        token.start shouldBe 4

        token = tis.next()

        token.type shouldBe ShakeTokenType.ASSIGN
        token.value shouldBe null
        token.end shouldBe 6
        token.start shouldBe 6

        token = tis.next()

        token.type shouldBe ShakeTokenType.INTEGER
        token.value shouldBe "10"
        token.end shouldBe 9
        token.start shouldBe 8

        token = tis.next()

        token.type shouldBe ShakeTokenType.SEMICOLON
        token.value shouldBe null
        token.end shouldBe 10
        token.start shouldBe 10
    }

    "get type" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testGetType()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 9, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testGetType()"),
                intArrayOf(),
            ),
        )

        tis.getType(0) shouldBe ShakeTokenType.KEYWORD_INT
        tis.getType(1) shouldBe ShakeTokenType.IDENTIFIER
        tis.getType(2) shouldBe ShakeTokenType.ASSIGN
        tis.getType(3) shouldBe ShakeTokenType.INTEGER
        tis.getType(4) shouldBe ShakeTokenType.SEMICOLON
    }

    "get end" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testGetEnd()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testGetEnd()"),
                intArrayOf(),
            ),
        )

        tis.getEnd(0) shouldBe 2
        tis.getEnd(1) shouldBe 4
        tis.getEnd(2) shouldBe 6
        tis.getEnd(3) shouldBe 9
        tis.getEnd(4) shouldBe 10
    }

    "get start" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testGetStart()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testGetStart()"),
                intArrayOf(),
            ),
        )

        tis.getStart(0) shouldBe 0
        tis.getStart(1) shouldBe 4
        tis.getStart(2) shouldBe 6
        tis.getStart(3) shouldBe 8
        tis.getStart(4) shouldBe 10
    }

    "get value" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testGetValue()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testGetValue()"),
                intArrayOf(),
            ),
        )

        tis.getValue(0) shouldBe null
        tis.getValue(1) shouldBe "i"
        tis.getValue(2) shouldBe null
        tis.getValue(3) shouldBe "10"
        tis.getValue(4) shouldBe null
    }

    "get has value" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testGetHasValue()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testGetHasValue()"),
                intArrayOf(),
            ),
        )

        tis.getHasValue(0) shouldBe false
        tis.getHasValue(1) shouldBe true
        tis.getHasValue(2) shouldBe false
        tis.getHasValue(3) shouldBe true
        tis.getHasValue(4) shouldBe false
    }

    "skip" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testSkip()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testSkip()"),
                intArrayOf(),
            ),
        )

        tis.position shouldBe -1
        tis.skip()
        tis.position shouldBe 0
        tis.skip(2)
        tis.position shouldBe 2
        tis.skip()
        tis.position shouldBe 3
        tis.skip()
        tis.position shouldBe 4
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.skip() }
    }

    "set position" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testSetPosition()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testSetPosition()"),
                intArrayOf(),
            ),
        )

        tis.position shouldBe -1
        tis.position = 0
        tis.position shouldBe 0
        tis.actualEnd shouldBe 2
        tis.actualValue shouldBe null
        tis.position = 1
        tis.position shouldBe 1
        tis.actualEnd shouldBe 4
        tis.actualValue shouldBe "i"
        tis.position = 2
        tis.position shouldBe 2
        tis.actualEnd shouldBe 6
        tis.actualValue shouldBe null
        tis.position = 3
        tis.position shouldBe 3
        tis.actualEnd shouldBe 9
        tis.actualValue shouldBe "10"
        tis.position = 4
        tis.position shouldBe 4
        tis.actualEnd shouldBe 10
        tis.actualValue shouldBe null
        tis.position = 3
        tis.position shouldBe 3
        tis.actualEnd shouldBe 9
        tis.actualValue shouldBe "10"
    }

    "has" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testHas()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testHas()"),
                intArrayOf(),
            ),
        )

        tis.has(1) shouldBe true
        tis.has(2) shouldBe true
        tis.has(3) shouldBe true
        tis.has(4) shouldBe true
        tis.has(5) shouldBe true
        tis.has(6) shouldBe false
        tis.skip(2)
        tis.has(1) shouldBe true
        tis.has(2) shouldBe true
        tis.has(3) shouldBe true
        tis.has(4) shouldBe false
    }

    "has next" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testHasNext()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testHasNext()"),
                intArrayOf(),
            ),
        )

        tis.hasNext() shouldBe true
        tis.skip(2)
        tis.hasNext() shouldBe true
        tis.skip(3)
        tis.hasNext() shouldBe false
    }

    "has next with empty" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testHasNextWithEmpty()",
            arrayOf(),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("", "TokenBasedTokenInputStreamTests#testHasNextWithEmpty()"),
                intArrayOf(),
            ),
        )

        tis.hasNext() shouldBe false
    }

    "has next with empty and position" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testHasNextWithEmptyAndPosition()",
            arrayOf(),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("", "TokenBasedTokenInputStreamTests#testHasNextWithEmptyAndPosition()"),
                intArrayOf(),
            ),
        )

        tis.hasNext() shouldBe false
        tis.position shouldBe -1
    }

    "actual end" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testActualEnd()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testActualEnd()"),
                intArrayOf(),
            ),
        )

        tis.skip()
        tis.actualEnd shouldBe 2
        tis.skip()
        tis.actualEnd shouldBe 4
        tis.skip()
        tis.actualEnd shouldBe 6
        tis.skip()
        tis.actualEnd shouldBe 9
        tis.skip()
        tis.actualEnd shouldBe 10
    }

    "actual start" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testActualStart()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testActualStart()"),
                intArrayOf(),
            ),
        )

        tis.skip()
        tis.actualStart shouldBe 0
        tis.skip()
        tis.actualStart shouldBe 4
        tis.skip()
        tis.actualStart shouldBe 6
        tis.skip()
        tis.actualStart shouldBe 8
        tis.skip()
        tis.actualStart shouldBe 10
    }

    "actual type" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testActualType()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testActualType()"),
                intArrayOf(),
            ),
        )

        tis.skip()
        tis.actualType shouldBe ShakeTokenType.KEYWORD_INT
        tis.skip()
        tis.actualType shouldBe ShakeTokenType.IDENTIFIER
        tis.skip()
        tis.actualType shouldBe ShakeTokenType.ASSIGN
        tis.skip()
        tis.actualType shouldBe ShakeTokenType.INTEGER
        tis.skip()
        tis.actualType shouldBe ShakeTokenType.SEMICOLON
    }

    "actual value" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testActualValue()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testActualValue()"),
                intArrayOf(),
            ),
        )

        tis.skip()
        tis.actualValue shouldBe null
        tis.skip()
        tis.actualValue shouldBe "i"
        tis.skip()
        tis.actualValue shouldBe null
        tis.skip()
        tis.actualValue shouldBe "10"
        tis.skip()
        tis.actualValue shouldBe null
    }

    "actual" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testActual()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testActual()"),
                intArrayOf(),
            ),
        )

        tis.skip()
        var actual = tis.actual
        actual.type shouldBe ShakeTokenType.KEYWORD_INT
        actual.value shouldBe null
        actual.end shouldBe 2
        actual.start shouldBe 0
        tis.skip()
        actual = tis.actual
        actual.type shouldBe ShakeTokenType.IDENTIFIER
        actual.value shouldBe "i"
        actual.end shouldBe 4
        actual.start shouldBe 4
        tis.skip()
        actual = tis.actual
        actual.type shouldBe ShakeTokenType.ASSIGN
        actual.value shouldBe null
        actual.end shouldBe 6
        actual.start shouldBe 6
        tis.skip()
        actual = tis.actual
        actual.type shouldBe ShakeTokenType.INTEGER
        actual.value shouldBe "10"
        actual.end shouldBe 9
        actual.start shouldBe 8
        tis.skip()
        actual = tis.actual
        actual.type shouldBe ShakeTokenType.SEMICOLON
        actual.value shouldBe null
        actual.end shouldBe 10
        actual.start shouldBe 10
    }

    "peek type" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testPeekType()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 9, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testPeekType()"),
                intArrayOf(),
            ),
        )

        tis.peekType() shouldBe ShakeTokenType.KEYWORD_INT
        tis.skip()
        tis.peekType() shouldBe ShakeTokenType.IDENTIFIER
        tis.skip()
        tis.peekType() shouldBe ShakeTokenType.ASSIGN
        tis.skip()
        tis.peekType() shouldBe ShakeTokenType.INTEGER
        tis.skip()
        tis.peekType() shouldBe ShakeTokenType.SEMICOLON
    }

    "peek end" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testPeekEnd()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testPeekEnd()"),
                intArrayOf(),
            ),
        )

        tis.peekEnd() shouldBe 2
        tis.skip()
        tis.peekEnd() shouldBe 4
        tis.skip()
        tis.peekEnd() shouldBe 6
        tis.skip()
        tis.peekEnd() shouldBe 9
        tis.skip()
        tis.peekEnd() shouldBe 10
    }

    "peek start" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testPeekStart()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testPeekStart()"),
                intArrayOf(),
            ),
        )

        tis.peekStart() shouldBe 0
        tis.skip()
        tis.peekStart() shouldBe 4
        tis.skip()
        tis.peekStart() shouldBe 6
        tis.skip()
        tis.peekStart() shouldBe 8
        tis.skip()
        tis.peekStart() shouldBe 10
    }

    "peek value" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testPeekValue()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testPeekValue()"),
                intArrayOf(),
            ),
        )

        tis.peekValue() shouldBe null
        tis.skip()
        tis.peekValue() shouldBe "i"
        tis.skip()
        tis.peekValue() shouldBe null
        tis.skip()
        tis.peekValue() shouldBe "10"
        tis.skip()
        tis.peekValue() shouldBe null
    }

    "peek" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testPeek()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testPeek()"),
                intArrayOf(),
            ),
        )

        var token = tis.peek()
        token.type shouldBe ShakeTokenType.KEYWORD_INT
        token.value shouldBe null
        token.start shouldBe 0
        token.end shouldBe 2
        tis.skip()
        token = tis.peek()
        token.type shouldBe ShakeTokenType.IDENTIFIER
        token.value shouldBe "i"
        token.start shouldBe 4
        token.end shouldBe 4
        tis.skip()
        token = tis.peek()
        token.type shouldBe ShakeTokenType.ASSIGN
        token.value shouldBe null
        token.start shouldBe 6
        token.end shouldBe 6
        tis.skip()
        token = tis.peek()
        token.type shouldBe ShakeTokenType.INTEGER
        token.value shouldBe "10"
        token.start shouldBe 8
        token.end shouldBe 9
        tis.skip()
        token = tis.peek()
        token.type shouldBe ShakeTokenType.SEMICOLON
        token.value shouldBe null
        token.start shouldBe 10
        token.end shouldBe 10
    }

    "peek type 2" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testPeekType2()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testPeekType2()"),
                intArrayOf(),
            ),
        )

        tis.peekType(1) shouldBe ShakeTokenType.KEYWORD_INT
        tis.peekType(2) shouldBe ShakeTokenType.IDENTIFIER
        tis.peekType(3) shouldBe ShakeTokenType.ASSIGN
        tis.peekType(4) shouldBe ShakeTokenType.INTEGER
        tis.peekType(5) shouldBe ShakeTokenType.SEMICOLON
        tis.skip()
        tis.peekType(1) shouldBe ShakeTokenType.IDENTIFIER
        tis.peekType(2) shouldBe ShakeTokenType.ASSIGN
        tis.peekType(3) shouldBe ShakeTokenType.INTEGER
        tis.peekType(4) shouldBe ShakeTokenType.SEMICOLON
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekType(5) }
        tis.skip()
        tis.peekType(1) shouldBe ShakeTokenType.ASSIGN
        tis.peekType(2) shouldBe ShakeTokenType.INTEGER
        tis.peekType(3) shouldBe ShakeTokenType.SEMICOLON
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekType(4) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekType(5) }
        tis.skip()
        tis.peekType(1) shouldBe ShakeTokenType.INTEGER
        tis.peekType(2) shouldBe ShakeTokenType.SEMICOLON
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekType(3) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekType(4) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekType(5) }
        tis.skip()
        tis.peekType(1) shouldBe ShakeTokenType.SEMICOLON
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekType(2) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekType(3) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekType(4) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekType(5) }
    }

    "peek end 2" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testPeekEnd2()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testPeekEnd2()"),
                intArrayOf(),
            ),
        )

        tis.peekEnd(1) shouldBe 2
        tis.peekEnd(2) shouldBe 4
        tis.peekEnd(3) shouldBe 6
        tis.peekEnd(4) shouldBe 9
        tis.peekEnd(5) shouldBe 10
        tis.skip()
        tis.peekEnd(1) shouldBe 4
        tis.peekEnd(2) shouldBe 6
        tis.peekEnd(3) shouldBe 9
        tis.peekEnd(4) shouldBe 10
        shouldThrow<Error> { tis.peekEnd(5) }
        tis.skip()
        tis.peekEnd(1) shouldBe 6
        tis.peekEnd(2) shouldBe 9
        tis.peekEnd(3) shouldBe 10
        shouldThrow<Error> { tis.peekEnd(4) }
        shouldThrow<Error> { tis.peekEnd(5) }
        tis.skip()
        tis.peekEnd(1) shouldBe 9
        tis.peekEnd(2) shouldBe 10
        shouldThrow<Error> { tis.peekEnd(3) }
        shouldThrow<Error> { tis.peekEnd(4) }
        shouldThrow<Error> { tis.peekEnd(5) }
        tis.skip()
        tis.peekEnd(1) shouldBe 10
        shouldThrow<Error> { tis.peekEnd(2) }
        shouldThrow<Error> { tis.peekEnd(3) }
        shouldThrow<Error> { tis.peekEnd(4) }
        shouldThrow<Error> { tis.peekEnd(5) }
    }

    "peek start 2" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testPeekStart2()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testPeekStart2()"),
                intArrayOf(),
            ),
        )

        tis.peekStart(1) shouldBe 0
        tis.peekStart(2) shouldBe 4
        tis.peekStart(3) shouldBe 6
        tis.peekStart(4) shouldBe 8
        tis.peekStart(5) shouldBe 10
        tis.skip()
        tis.peekStart(1) shouldBe 4
        tis.peekStart(2) shouldBe 6
        tis.peekStart(3) shouldBe 8
        tis.peekStart(4) shouldBe 10
        shouldThrow<Error> { tis.peekStart(5) }
        tis.skip()
        tis.peekStart(1) shouldBe 6
        tis.peekStart(2) shouldBe 8
        tis.peekStart(3) shouldBe 10
        shouldThrow<Error> { tis.peekStart(4) }
        shouldThrow<Error> { tis.peekStart(5) }
        tis.skip()
        tis.peekStart(1) shouldBe 8
        tis.peekStart(2) shouldBe 10
        shouldThrow<Error> { tis.peekStart(3) }
        shouldThrow<Error> { tis.peekStart(4) }
        shouldThrow<Error> { tis.peekStart(5) }
        tis.skip()
        tis.peekStart(1) shouldBe 10
        shouldThrow<Error> { tis.peekStart(2) }
        shouldThrow<Error> { tis.peekStart(3) }
        shouldThrow<Error> { tis.peekStart(4) }
        shouldThrow<Error> { tis.peekStart(5) }
        tis.skip()
        shouldThrow<Error> { tis.peekStart(1) }
        shouldThrow<Error> { tis.peekStart(2) }
        shouldThrow<Error> { tis.peekStart(3) }
        shouldThrow<Error> { tis.peekStart(4) }
        shouldThrow<Error> { tis.peekStart(5) }
    }

    "peek value 2" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testPeekValue2()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testPeekValue2()"),
                intArrayOf(),
            ),
        )

        tis.peekValue(1) shouldBe null
        tis.peekValue(2) shouldBe "i"
        tis.peekValue(3) shouldBe null
        tis.peekValue(4) shouldBe "10"
        tis.peekValue(5) shouldBe null
        tis.skip()
        tis.peekValue(1) shouldBe "i"
        tis.peekValue(2) shouldBe null
        tis.peekValue(3) shouldBe "10"
        tis.peekValue(4) shouldBe null
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekValue(5) }
        tis.skip()
        tis.peekValue(1) shouldBe null
        tis.peekValue(2) shouldBe "10"
        tis.peekValue(3) shouldBe null
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekValue(4) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekValue(5) }
        tis.skip()
        tis.peekValue(1) shouldBe "10"
        tis.peekValue(2) shouldBe null
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekValue(3) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekValue(4) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekValue(5) }
        tis.skip()
        tis.peekValue(1) shouldBe null
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekValue(2) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekValue(3) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekValue(4) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekValue(5) }
        tis.skip()
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekValue(1) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekValue(2) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekValue(3) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekValue(4) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekValue(5) }
    }

    "peek 2" {
        val tis = ShakeTokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testPeek2()",
            arrayOf(
                ShakeToken(ShakeTokenType.KEYWORD_INT, 0, 2),
                ShakeToken(ShakeTokenType.IDENTIFIER, "i", 4, 4),
                ShakeToken(ShakeTokenType.ASSIGN, 6, 6),
                ShakeToken(ShakeTokenType.INTEGER, "10", 8, 9),
                ShakeToken(ShakeTokenType.SEMICOLON, 10, 10),
            ),
            PositionMap.PositionMapImplementation(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testPeek2()"),
                intArrayOf(),
            ),
        )

        var token = tis.peek(1)
        token.type shouldBe ShakeTokenType.KEYWORD_INT
        token.value shouldBe null
        token.start shouldBe 0
        token.end shouldBe 2
        token = tis.peek(2)
        token.type shouldBe ShakeTokenType.IDENTIFIER
        token.value shouldBe "i"
        token.start shouldBe 4
        token.end shouldBe 4
        token = tis.peek(3)
        token.type shouldBe ShakeTokenType.ASSIGN
        token.value shouldBe null
        token.start shouldBe 6
        token.end shouldBe 6
        token = tis.peek(4)
        token.type shouldBe ShakeTokenType.INTEGER
        token.value shouldBe "10"
        token.start shouldBe 8
        token.end shouldBe 9
        token = tis.peek(5)
        token.type shouldBe ShakeTokenType.SEMICOLON
        token.value shouldBe null
        token.start shouldBe 10
        token.end shouldBe 10

        tis.skip()

        token = tis.peek(1)
        token.type shouldBe ShakeTokenType.IDENTIFIER
        token.value shouldBe "i"
        token.start shouldBe 4
        token.end shouldBe 4
        token = tis.peek(2)
        token.type shouldBe ShakeTokenType.ASSIGN
        token.value shouldBe null
        token.start shouldBe 6
        token.end shouldBe 6
        token = tis.peek(3)
        token.type shouldBe ShakeTokenType.INTEGER
        token.value shouldBe "10"
        token.start shouldBe 8
        token.end shouldBe 9
        token = tis.peek(4)
        token.type shouldBe ShakeTokenType.SEMICOLON
        token.value shouldBe null
        token.start shouldBe 10
        token.end shouldBe 10
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peek(5) }

        tis.skip()

        token = tis.peek(1)
        token.type shouldBe ShakeTokenType.ASSIGN
        token.value shouldBe null
        token.start shouldBe 6
        token.end shouldBe 6
        token = tis.peek(2)
        token.type shouldBe ShakeTokenType.INTEGER
        token.value shouldBe "10"
        token.start shouldBe 8
        token.end shouldBe 9
        token = tis.peek(3)
        token.type shouldBe ShakeTokenType.SEMICOLON
        token.value shouldBe null
        token.start shouldBe 10
        token.end shouldBe 10
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peek(4) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peek(5) }

        tis.skip()

        token = tis.peek(1)
        token.type shouldBe ShakeTokenType.INTEGER
        token.value shouldBe "10"
        token.start shouldBe 8
        token.end shouldBe 9
        token = tis.peek(2)
        token.type shouldBe ShakeTokenType.SEMICOLON
        token.value shouldBe null
        token.start shouldBe 10
        token.end shouldBe 10
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peek(3) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peek(4) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peek(5) }

        tis.skip()

        token = tis.peek(1)
        token.type shouldBe ShakeTokenType.SEMICOLON
        token.value shouldBe null
        token.start shouldBe 10
        token.end shouldBe 10
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peek(2) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peek(3) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peek(4) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peek(5) }
    }
})
