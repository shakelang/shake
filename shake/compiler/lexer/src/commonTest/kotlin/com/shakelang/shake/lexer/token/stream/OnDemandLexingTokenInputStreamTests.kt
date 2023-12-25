package com.shakelang.shake.lexer.token.stream

import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.util.parseutils.characters.source.CharacterSource
import com.shakelang.util.parseutils.characters.streaming.SourceCharacterInputStream
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

@Suppress("unused")
class OnDemandLexingTokenInputStreamTests : FreeSpec({

    "next type" {
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testNextType()")
            )
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
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testNextValue()")
            )
        )

        tis.nextValue() shouldBe null
        tis.actualEnd shouldBe 2
        tis.nextValue() shouldBe "i"
        tis.actualEnd shouldBe 4
        tis.nextValue() shouldBe null
        tis.actualEnd shouldBe 6
        tis.nextValue() shouldBe "10"
        tis.actualEnd shouldBe 9
        tis.nextValue() shouldBe null
        tis.actualEnd shouldBe 10
    }

    "next token" {
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testNextToken()")
            )
        )

        var next = tis.next()
        next.type shouldBe ShakeTokenType.KEYWORD_INT
        next.end shouldBe 2
        next.value shouldBe null
        next.start shouldBe 0
        next = tis.next()
        next.type shouldBe ShakeTokenType.IDENTIFIER
        next.end shouldBe 4
        next.value shouldBe "i"
        next.start shouldBe 4
        next = tis.next()
        next.type shouldBe ShakeTokenType.ASSIGN
        next.end shouldBe 6
        next.value shouldBe null
        next.start shouldBe 6
        next = tis.next()
        next.type shouldBe ShakeTokenType.INTEGER
        next.end shouldBe 9
        next.value shouldBe "10"
        next.start shouldBe 8
        next = tis.next()
        next.type shouldBe ShakeTokenType.SEMICOLON
        next.end shouldBe 10
        next.value shouldBe null
        next.start shouldBe 10
    }

    "skip" {
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testSkip()")
            )
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
        shouldThrowWithMessage<Error>("Input already finished") { tis.skip() }
    }

    "has" {
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testHas()")
            )
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
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testHasNext()")
            )
        )

        tis.hasNext() shouldBe true
        tis.skip(2)
        tis.hasNext() shouldBe true
        tis.skip(3)
        tis.hasNext() shouldBe false
    }

    "test next with empty and position" {
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("", "OnDemandLexingTokenInputStreamTests#testHasNextWithEmptyAndPosition()")
            )
        )

        tis.hasNext() shouldBe false
        tis.position shouldBe -1
    }

    "actual end" {
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testActualEnd()")
            )
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
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testActualStart()")
            )
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
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testActualType()")
            )
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
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testActualValue()")
            )
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
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testActual()")
            )
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
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testPeekType()")
            )
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
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testPeekEnd()")
            )
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
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testPeekStart()")
            )
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
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testPeekValue()")
            )
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
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testPeek()")
            )
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
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testPeekType2()")
            )
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
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testPeekEnd2()")
            )
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
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekEnd(5) }
        tis.skip()
        tis.peekEnd(1) shouldBe 6
        tis.peekEnd(2) shouldBe 9
        tis.peekEnd(3) shouldBe 10
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekEnd(4) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekEnd(5) }
        tis.skip()
        tis.peekEnd(1) shouldBe 9
        tis.peekEnd(2) shouldBe 10
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekEnd(3) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekEnd(4) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekEnd(5) }
        tis.skip()
        tis.peekEnd(1) shouldBe 10
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekEnd(2) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekEnd(3) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekEnd(4) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekEnd(5) }
    }

    "peek start 2" {
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testPeekStart2()")
            )
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
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekStart(5) }
        tis.skip()
        tis.peekStart(1) shouldBe 6
        tis.peekStart(2) shouldBe 8
        tis.peekStart(3) shouldBe 10
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekStart(4) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekStart(5) }
        tis.skip()
        tis.peekStart(1) shouldBe 8
        tis.peekStart(2) shouldBe 10
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekStart(3) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekStart(4) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekStart(5) }
        tis.skip()
        tis.peekStart(1) shouldBe 10
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekStart(2) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekStart(3) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekStart(4) }
        shouldThrowWithMessage<Error>("Not enough tokens left") { tis.peekStart(5) }
    }

    "peek value 2" {
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testPeekValue2()")
            )
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
    }

    "peek 2" {
        val tis = ShakeOnDemandLexingTokenInputStream(
            SourceCharacterInputStream(
                CharacterSource.from("int i = 10;", "OnDemandLexingTokenInputStreamTests#testPeek2()")
            )
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
