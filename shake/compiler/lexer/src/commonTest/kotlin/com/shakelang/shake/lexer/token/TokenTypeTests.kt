package com.shakelang.shake.lexer.token

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

@Suppress("unused")
class TokenTypeTests : FreeSpec({

    "get name" {
        ShakeTokenType.entries.forEach {
            it.tokenName shouldBe it.name
        }
    }

    "token length field" {
        ShakeTokenType.IDENTIFIER.tokenLength shouldBe -1
        ShakeTokenType.INTEGER.tokenLength shouldBe -1
        ShakeTokenType.DOUBLE.tokenLength shouldBe -1
        ShakeTokenType.CHARACTER.tokenLength shouldBe -1
        ShakeTokenType.STRING.tokenLength shouldBe -1
        ShakeTokenType.COMMA.tokenLength shouldBe 1
        ShakeTokenType.DOT.tokenLength shouldBe 1
        ShakeTokenType.SEMICOLON.tokenLength shouldBe 1
        ShakeTokenType.ADD.tokenLength shouldBe 1
        ShakeTokenType.SUB.tokenLength shouldBe 1
        ShakeTokenType.MUL.tokenLength shouldBe 1
        ShakeTokenType.DIV.tokenLength shouldBe 1
        ShakeTokenType.MOD.tokenLength shouldBe 1
        ShakeTokenType.POW.tokenLength shouldBe 2
        ShakeTokenType.EQ_EQUALS.tokenLength shouldBe 2
        ShakeTokenType.BIGGER_EQUALS.tokenLength shouldBe 2
        ShakeTokenType.SMALLER_EQUALS.tokenLength shouldBe 2
        ShakeTokenType.BIGGER.tokenLength shouldBe 1
        ShakeTokenType.SMALLER.tokenLength shouldBe 1
        ShakeTokenType.LOGICAL_OR.tokenLength shouldBe 2
        ShakeTokenType.LOGICAL_XOR.tokenLength shouldBe 1
        ShakeTokenType.LOGICAL_AND.tokenLength shouldBe 2
        ShakeTokenType.LINE_SEPARATOR.tokenLength shouldBe 1
        ShakeTokenType.ASSIGN.tokenLength shouldBe 1
        ShakeTokenType.INCR.tokenLength shouldBe 2
        ShakeTokenType.DECR.tokenLength shouldBe 2
        ShakeTokenType.ADD_ASSIGN.tokenLength shouldBe 2
        ShakeTokenType.SUB_ASSIGN.tokenLength shouldBe 2
        ShakeTokenType.MUL_ASSIGN.tokenLength shouldBe 2
        ShakeTokenType.DIV_ASSIGN.tokenLength shouldBe 2
        ShakeTokenType.MOD_ASSIGN.tokenLength shouldBe 2
        ShakeTokenType.POW_ASSIGN.tokenLength shouldBe 3
        ShakeTokenType.LPAREN.tokenLength shouldBe 1
        ShakeTokenType.RPAREN.tokenLength shouldBe 1
        ShakeTokenType.LCURL.tokenLength shouldBe 1
        ShakeTokenType.RCURL.tokenLength shouldBe 1
        ShakeTokenType.KEYWORD_DO.tokenLength shouldBe 2
        ShakeTokenType.KEYWORD_WHILE.tokenLength shouldBe 5
        ShakeTokenType.KEYWORD_FOR.tokenLength shouldBe 3
        ShakeTokenType.KEYWORD_IF.tokenLength shouldBe 2
        ShakeTokenType.KEYWORD_ELSE.tokenLength shouldBe 4
        ShakeTokenType.KEYWORD_TRUE.tokenLength shouldBe 4
        ShakeTokenType.KEYWORD_FALSE.tokenLength shouldBe 5
        ShakeTokenType.KEYWORD_CLASS.tokenLength shouldBe 5
        ShakeTokenType.KEYWORD_EXTENDS.tokenLength shouldBe 7
        ShakeTokenType.KEYWORD_IMPLEMENTS.tokenLength shouldBe 10
        ShakeTokenType.KEYWORD_STATIC.tokenLength shouldBe 6
        ShakeTokenType.KEYWORD_FINAL.tokenLength shouldBe 5
        ShakeTokenType.KEYWORD_PUBLIC.tokenLength shouldBe 6
        ShakeTokenType.KEYWORD_PROTECTED.tokenLength shouldBe 9
        ShakeTokenType.KEYWORD_PRIVATE.tokenLength shouldBe 7
        ShakeTokenType.KEYWORD_NEW.tokenLength shouldBe 3
        ShakeTokenType.KEYWORD_FUNCTION.tokenLength shouldBe 8
        ShakeTokenType.KEYWORD_RETURN.tokenLength shouldBe 6
        ShakeTokenType.KEYWORD_INT.tokenLength shouldBe 3
        ShakeTokenType.KEYWORD_CONST.tokenLength shouldBe 5
        ShakeTokenType.KEYWORD_DYNAMIC.tokenLength shouldBe 7
        ShakeTokenType.KEYWORD_BYTE.tokenLength shouldBe 4
        ShakeTokenType.KEYWORD_SHORT.tokenLength shouldBe 5
        ShakeTokenType.KEYWORD_INT.tokenLength shouldBe 3
        ShakeTokenType.KEYWORD_LONG.tokenLength shouldBe 4
        ShakeTokenType.KEYWORD_FLOAT.tokenLength shouldBe 5
        ShakeTokenType.KEYWORD_DOUBLE.tokenLength shouldBe 6
        ShakeTokenType.KEYWORD_CHAR.tokenLength shouldBe 4
        ShakeTokenType.KEYWORD_BOOLEAN.tokenLength shouldBe 7
        ShakeTokenType.KEYWORD_IMPORT.tokenLength shouldBe 6
        ShakeTokenType.KEYWORD_VOID.tokenLength shouldBe 4
        ShakeTokenType.KEYWORD_CONSTRUCTOR.tokenLength shouldBe 11
        ShakeTokenType.KEYWORD_AS.tokenLength shouldBe 2
    }

    "token length function" {
        ShakeTokenType.IDENTIFIER.length("hello") shouldBe 5
        ShakeTokenType.INTEGER.length("10") shouldBe 2
        ShakeTokenType.DOUBLE.length("10.0") shouldBe 4
        ShakeTokenType.CHARACTER.length("'a'") shouldBe 3
        ShakeTokenType.STRING.length("\"hello\"") shouldBe 7
        ShakeTokenType.COMMA.length("") shouldBe 1
        ShakeTokenType.DOT.length("") shouldBe 1
        ShakeTokenType.SEMICOLON.length("") shouldBe 1
        ShakeTokenType.ADD.length("") shouldBe 1
        ShakeTokenType.SUB.length("") shouldBe 1
        ShakeTokenType.MUL.length("") shouldBe 1
        ShakeTokenType.DIV.length("") shouldBe 1
        ShakeTokenType.MOD.length("") shouldBe 1
        ShakeTokenType.POW.length("") shouldBe 2
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
        ShakeTokenType.KEYWORD_EXTENDS.length("") shouldBe 7
        ShakeTokenType.KEYWORD_IMPLEMENTS.length("") shouldBe 10
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
})
