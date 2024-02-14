package com.shakelang.shake.lexer.token

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

@Suppress("unused")
class TokenTypeTests : FreeSpec(
    {

        "token length function" {
            ShakeTokenType.IDENTIFIER.length("hello") shouldBe 5
            ShakeTokenType.INTEGER.length("10") shouldBe 2
            ShakeTokenType.DOUBLE.length("10.0") shouldBe 4
            ShakeTokenType.CHARACTER.length("'a'") shouldBe 3
            ShakeTokenType.STRING.length("\"hello\"") shouldBe 7
            ShakeTokenType.COMMA.length("") shouldBe 1
            ShakeTokenType.COLON.length("") shouldBe 1
            ShakeTokenType.DOT.length("") shouldBe 1
            ShakeTokenType.SEMICOLON.length("") shouldBe 1
            ShakeTokenType.ADD.length("") shouldBe 1
            ShakeTokenType.SUB.length("") shouldBe 1
            ShakeTokenType.MUL.length("") shouldBe 1
            ShakeTokenType.DIV.length("") shouldBe 1
            ShakeTokenType.MOD.length("") shouldBe 1
            ShakeTokenType.POW.length("") shouldBe 2
            ShakeTokenType.BITWISE_SHL.length("") shouldBe 2
            ShakeTokenType.BITWISE_SHR.length("") shouldBe 2
            ShakeTokenType.BITWISE_USHR.length("") shouldBe 3
            ShakeTokenType.EQ_EQUALS.length("") shouldBe 2
            ShakeTokenType.BIGGER_EQUALS.length("") shouldBe 2
            ShakeTokenType.SMALLER_EQUALS.length("") shouldBe 2
            ShakeTokenType.BIGGER.length("") shouldBe 1
            ShakeTokenType.SMALLER.length("") shouldBe 1
            ShakeTokenType.LOGICAL_OR.length("") shouldBe 2
            ShakeTokenType.LOGICAL_XOR.length("") shouldBe 1
            ShakeTokenType.LOGICAL_AND.length("") shouldBe 2
            ShakeTokenType.LINE_SEPARATOR.length("") shouldBe 1
            ShakeTokenType.ASSIGN.length("") shouldBe 1
            ShakeTokenType.INCR.length("") shouldBe 2
            ShakeTokenType.DECR.length("") shouldBe 2
            ShakeTokenType.ADD_ASSIGN.length("") shouldBe 2
            ShakeTokenType.SUB_ASSIGN.length("") shouldBe 2
            ShakeTokenType.MUL_ASSIGN.length("") shouldBe 2
            ShakeTokenType.DIV_ASSIGN.length("") shouldBe 2
            ShakeTokenType.MOD_ASSIGN.length("") shouldBe 2
            ShakeTokenType.POW_ASSIGN.length("") shouldBe 3
            ShakeTokenType.LPAREN.length("") shouldBe 1
            ShakeTokenType.RPAREN.length("") shouldBe 1
            ShakeTokenType.LCURL.length("") shouldBe 1
            ShakeTokenType.RCURL.length("") shouldBe 1
            ShakeTokenType.KEYWORD_DO.length("") shouldBe 2
            ShakeTokenType.KEYWORD_WHILE.length("") shouldBe 5
            ShakeTokenType.KEYWORD_FOR.length("") shouldBe 3
            ShakeTokenType.KEYWORD_IF.length("") shouldBe 2
            ShakeTokenType.KEYWORD_ELSE.length("") shouldBe 4
            ShakeTokenType.KEYWORD_TRUE.length("") shouldBe 4
            ShakeTokenType.KEYWORD_FALSE.length("") shouldBe 5
            ShakeTokenType.KEYWORD_CLASS.length("") shouldBe 5
            ShakeTokenType.KEYWORD_STATIC.length("") shouldBe 6
            ShakeTokenType.KEYWORD_FINAL.length("") shouldBe 5
            ShakeTokenType.KEYWORD_PUBLIC.length("") shouldBe 6
            ShakeTokenType.KEYWORD_PROTECTED.length("") shouldBe 9
            ShakeTokenType.KEYWORD_PRIVATE.length("") shouldBe 7
            ShakeTokenType.KEYWORD_NEW.length("") shouldBe 3
            ShakeTokenType.KEYWORD_FUNCTION.length("") shouldBe 8
            ShakeTokenType.KEYWORD_RETURN.length("") shouldBe 6
            ShakeTokenType.KEYWORD_INT.length("") shouldBe 3
            ShakeTokenType.KEYWORD_CONST.length("") shouldBe 5
            ShakeTokenType.KEYWORD_DYNAMIC.length("") shouldBe 7
            ShakeTokenType.KEYWORD_BYTE.length("") shouldBe 4
            ShakeTokenType.KEYWORD_SHORT.length("") shouldBe 5
            ShakeTokenType.KEYWORD_INT.length("") shouldBe 3
            ShakeTokenType.KEYWORD_LONG.length("") shouldBe 4
            ShakeTokenType.KEYWORD_FLOAT.length("") shouldBe 5
            ShakeTokenType.KEYWORD_DOUBLE.length("") shouldBe 6
            ShakeTokenType.KEYWORD_CHAR.length("") shouldBe 4
            ShakeTokenType.KEYWORD_BOOLEAN.length("") shouldBe 7
            ShakeTokenType.KEYWORD_IMPORT.length("") shouldBe 6
            ShakeTokenType.KEYWORD_VOID.length("") shouldBe 4
            ShakeTokenType.KEYWORD_CONSTRUCTOR.length("") shouldBe 11
            ShakeTokenType.KEYWORD_AS.length("") shouldBe 2
        }
    },
)
