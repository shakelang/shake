package com.shakelang.shake.lexer

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.util.parseutils.characters.streaming.CharacterInputStream
import com.shakelang.util.parseutils.characters.streaming.SourceCharacterInputStream
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

@Suppress("unused")
class LexerBaseTests : FreeSpec(
    {

        fun generateToken(input: String, tt: ShakeTokenType): ShakeToken {
            val i: CharacterInputStream = SourceCharacterInputStream("<tests>", input)
            val lexer = object : ShakeLexingBase(i) {}
            val t = lexer.makeToken()
            t.type shouldBe tt
            i.hasNext() shouldBe false
            return t
        }

        "string" {
            generateToken("\"\"", ShakeTokenType.STRING).value shouldBe ""
            generateToken("\"afvne9214 ro\"", ShakeTokenType.STRING).value shouldBe "afvne9214 ro"
            generateToken(
                "\"\\t\\b\\n\\r\\f\\'\\\"\\\\a\\u0000\"",
                ShakeTokenType.STRING,
            ).value shouldBe "\\t\\b\\n\\r\\f\\'\\\"\\\\a\\u0000"
        }

        "character" {

            listOf(
                " ", "a", "\\r", "\\n", "\\b", "\\t", "\\f", "\\'", "\\u0000",
            ).forEach {
                generateToken("'$it'", ShakeTokenType.CHARACTER).value shouldBe it
            }
        }

        "punctuation" {
            generateToken(";", ShakeTokenType.SEMICOLON)
            generateToken(",", ShakeTokenType.COMMA)
            generateToken(".", ShakeTokenType.DOT)
            generateToken(":", ShakeTokenType.COLON)
        }

        "line separator" {
            generateToken("\n", ShakeTokenType.LINE_SEPARATOR)
            generateToken("\r\n", ShakeTokenType.LINE_SEPARATOR)
        }

        "assign" {
            // assign
            generateToken("=", ShakeTokenType.ASSIGN)

            // math assign operators
            generateToken("+=", ShakeTokenType.ADD_ASSIGN)
            generateToken("-=", ShakeTokenType.SUB_ASSIGN)
            generateToken("*=", ShakeTokenType.MUL_ASSIGN)
            generateToken("/=", ShakeTokenType.DIV_ASSIGN)
            generateToken("%=", ShakeTokenType.MOD_ASSIGN)
            generateToken("**=", ShakeTokenType.POW_ASSIGN)
            generateToken("++", ShakeTokenType.INCR)
            generateToken("--", ShakeTokenType.DECR)
        }

        "math operators" {
            generateToken("+", ShakeTokenType.ADD)
            generateToken("-", ShakeTokenType.SUB)
            generateToken("*", ShakeTokenType.MUL)
            generateToken("/", ShakeTokenType.DIV)
            generateToken("%", ShakeTokenType.MOD)
            generateToken("**", ShakeTokenType.POW)
        }

        "logical comparison" {
            generateToken("==", ShakeTokenType.EQ_EQUALS)
            generateToken(">=", ShakeTokenType.BIGGER_EQUALS)
            generateToken("<=", ShakeTokenType.SMALLER_EQUALS)
            generateToken(">", ShakeTokenType.BIGGER)
            generateToken("<", ShakeTokenType.SMALLER)
            generateToken("!=", ShakeTokenType.NOT_EQUALS)
        }

        "logical concatenation" {
            generateToken("||", ShakeTokenType.LOGICAL_OR)
            generateToken("^^", ShakeTokenType.LOGICAL_XOR)
            generateToken("&&", ShakeTokenType.LOGICAL_AND)
            generateToken("!", ShakeTokenType.LOGICAL_NOT)
        }

        "bitwise concatenation" {
            generateToken("|", ShakeTokenType.BITWISE_OR)
            generateToken("^", ShakeTokenType.BITWISE_XOR)
            generateToken("&", ShakeTokenType.BITWISE_AND)
            generateToken("~", ShakeTokenType.BITWISE_NOT)

            generateToken("~|", ShakeTokenType.BITWISE_NOR)
            generateToken("~^", ShakeTokenType.BITWISE_XNOR)
            generateToken("~&", ShakeTokenType.BITWISE_NAND)
        }

        "bit shifts" {
            generateToken("<<", ShakeTokenType.BITWISE_SHL)
            generateToken(">>", ShakeTokenType.BITWISE_SHR)
            generateToken(">>>", ShakeTokenType.BITWISE_USHR)
        }

        "brackets" {
            generateToken("(", ShakeTokenType.LPAREN)
            generateToken(")", ShakeTokenType.RPAREN)
            generateToken("{", ShakeTokenType.LCURL)
            generateToken("}", ShakeTokenType.RCURL)
        }

        fun testKeyword(test: KeywordTest) {
            generateToken(test.input, test.output)
        }

        KeywordTest.entries.forEach {
            "${it.name} keyword" { testKeyword(it) }
        }

        "numbers" {
            generateToken("149", ShakeTokenType.INTEGER).value shouldBe "149"
            generateToken("0.01", ShakeTokenType.DOUBLE).value shouldBe "0.01"
        }

        "identifiers" {
            generateToken("hello", ShakeTokenType.IDENTIFIER).value shouldBe "hello"
            generateToken("world0A_", ShakeTokenType.IDENTIFIER).value shouldBe "world0A_"
        }

        "line comments" {
            generateToken("// test\n", ShakeTokenType.LINE_SEPARATOR)
        }

        "multi line comments" {
            generateToken("/* test */\n", ShakeTokenType.LINE_SEPARATOR)
            generateToken("/*\ntest\ntest2*/\n", ShakeTokenType.LINE_SEPARATOR)
            generateToken("/*\n * test\n *  test2\n */\n", ShakeTokenType.LINE_SEPARATOR)
        }
    },
) {

    @Suppress("unused")
    enum class KeywordTest(val input: String, val output: ShakeTokenType) {
        DYNAMIC("dynamic", ShakeTokenType.KEYWORD_DYNAMIC),
        BYTE("byte", ShakeTokenType.KEYWORD_BYTE),
        SHORT("short", ShakeTokenType.KEYWORD_SHORT),
        INT("int", ShakeTokenType.KEYWORD_INT),
        LONG("long", ShakeTokenType.KEYWORD_LONG),
        FLOAT("float", ShakeTokenType.KEYWORD_FLOAT),
        DOUBLE("double", ShakeTokenType.KEYWORD_DOUBLE),
        CHAR("char", ShakeTokenType.KEYWORD_CHAR),
        BOOLEAN("boolean", ShakeTokenType.KEYWORD_BOOLEAN),
        CONST("const", ShakeTokenType.KEYWORD_CONST),
        FUNCTION("function", ShakeTokenType.KEYWORD_FUNCTION),
        RETURN("return", ShakeTokenType.KEYWORD_RETURN),
        DO("do", ShakeTokenType.KEYWORD_DO),
        WHILE("while", ShakeTokenType.KEYWORD_WHILE),
        FOR("for", ShakeTokenType.KEYWORD_FOR),
        IF("if", ShakeTokenType.KEYWORD_IF),
        ELSE("else", ShakeTokenType.KEYWORD_ELSE),
        TRUE("true", ShakeTokenType.KEYWORD_TRUE),
        FALSE("false", ShakeTokenType.KEYWORD_FALSE),
        CLASS("class", ShakeTokenType.KEYWORD_CLASS),
        STATIC("static", ShakeTokenType.KEYWORD_STATIC),
        FINAL("final", ShakeTokenType.KEYWORD_FINAL),
        PUBLIC("public", ShakeTokenType.KEYWORD_PUBLIC),
        PROTECTED("protected", ShakeTokenType.KEYWORD_PROTECTED),
        PRIVATE("private", ShakeTokenType.KEYWORD_PRIVATE),
        NEW("new", ShakeTokenType.KEYWORD_NEW),
        IMPORT("import", ShakeTokenType.KEYWORD_IMPORT),
        VOID("void", ShakeTokenType.KEYWORD_VOID),
        VAR("var", ShakeTokenType.KEYWORD_VAR),
        CONSTRUCTOR("constructor", ShakeTokenType.KEYWORD_CONSTRUCTOR),
        AS("as", ShakeTokenType.KEYWORD_AS),
        ;

        override fun toString(): String {
            return "Keyword '$input'"
        }
    }
}
