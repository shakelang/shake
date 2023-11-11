package com.shakelang.shake.lexer

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.util.parseutils.characters.streaming.CharacterInputStream
import com.shakelang.shake.util.parseutils.characters.streaming.SourceCharacterInputStream
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertSame

class LexerBaseTests {
    @Test
    fun testString() {
        // strings
        assertEquals("", generateToken("\"\"", ShakeTokenType.STRING).value)
        assertEquals("afvne9214 ro", generateToken("\"afvne9214 ro\"", ShakeTokenType.STRING).value)
        assertEquals(
            "\\t\\b\\n\\r\\f\\'\\\"\\\\a\\u0000",
            generateToken("\"\\t\\b\\n\\r\\f\\'\\\"\\\\a\\u0000\"", ShakeTokenType.STRING).value
        )
    }

    @Test
    fun testCharacter() {
        // characters
        assertEquals(" ", generateToken("' '", ShakeTokenType.CHARACTER).value)
        assertEquals("a", generateToken("'a'", ShakeTokenType.CHARACTER).value)
        assertEquals("\\r", generateToken("'\\r'", ShakeTokenType.CHARACTER).value)
        assertEquals("\\n", generateToken("'\\n'", ShakeTokenType.CHARACTER).value)
        assertEquals("\\b", generateToken("'\\b'", ShakeTokenType.CHARACTER).value)
        assertEquals("\\t", generateToken("'\\t'", ShakeTokenType.CHARACTER).value)
        assertEquals("\\f", generateToken("'\\f'", ShakeTokenType.CHARACTER).value)
        assertEquals("\\'", generateToken("'\\''", ShakeTokenType.CHARACTER).value)
        assertEquals("\\u0000", generateToken("'\\u0000'", ShakeTokenType.CHARACTER).value)
    }

    @Test
    fun testPunctuation() {
        // punctuation
        generateToken(";", ShakeTokenType.SEMICOLON) // ';'
        generateToken(",", ShakeTokenType.COMMA) // ','
        generateToken(".", ShakeTokenType.DOT) // '.'
    }

    @Test
    fun testLineSeparator() {
        // line separator
        generateToken("\n", ShakeTokenType.LINE_SEPARATOR) // '\n'
        generateToken("\r\n", ShakeTokenType.LINE_SEPARATOR) // '\r\n'
    }

    @Test
    fun testAssign() {
        // assign
        generateToken("=", ShakeTokenType.ASSIGN) // '='

        // math assign operators
        generateToken("+=", ShakeTokenType.ADD_ASSIGN) // '+='
        generateToken("-=", ShakeTokenType.SUB_ASSIGN) // '-='
        generateToken("*=", ShakeTokenType.MUL_ASSIGN) // '*='
        generateToken("/=", ShakeTokenType.DIV_ASSIGN) // '/='
        generateToken("%=", ShakeTokenType.MOD_ASSIGN) // '%='
        generateToken("**=", ShakeTokenType.POW_ASSIGN) // "**="
        generateToken("++", ShakeTokenType.INCR) // '++'
        generateToken("--", ShakeTokenType.DECR) // '--'
    }

    @Test
    fun testMathOperators() {
        // math operators
        generateToken("+", ShakeTokenType.ADD) // '+'
        generateToken("-", ShakeTokenType.SUB) // '-'
        generateToken("*", ShakeTokenType.MUL) // '*'
        generateToken("/", ShakeTokenType.DIV) // '/'
        generateToken("%", ShakeTokenType.MOD) // '%'
        generateToken("**", ShakeTokenType.POW) // "**"
    }

    @Test
    fun testLogicalComparison() {
        // logical comparison
        generateToken("==", ShakeTokenType.EQ_EQUALS) // "=="
        generateToken(">=", ShakeTokenType.BIGGER_EQUALS) // ">="
        generateToken("<=", ShakeTokenType.SMALLER_EQUALS) // "<="
        generateToken(">", ShakeTokenType.BIGGER) // '>'
        generateToken("<", ShakeTokenType.SMALLER) // '<'
    }

    @Test
    fun testLogicalConcatenation() {
        // logical concatenation
        generateToken("||", ShakeTokenType.LOGICAL_OR) // "||"
        generateToken("^^", ShakeTokenType.LOGICAL_XOR) // "^^"
        generateToken("&&", ShakeTokenType.LOGICAL_AND) // "&&"
        generateToken("!", ShakeTokenType.LOGICAL_NOT) // "!"
    }

    @Test
    fun testBitwiseConcatenation() {
        // bitwise concatenation
        generateToken("|", ShakeTokenType.BITWISE_OR) // "|"
        generateToken("^", ShakeTokenType.BITWISE_XOR) // "^"
        generateToken("&", ShakeTokenType.BITWISE_AND) // "&"
        generateToken("~", ShakeTokenType.BITWISE_NOT) // "~"

        generateToken("~|", ShakeTokenType.BITWISE_NOR) // "~|"
        generateToken("~^", ShakeTokenType.BITWISE_XNOR) // "~^"
        generateToken("~&", ShakeTokenType.BITWISE_NAND) // "~&"
    }

    @Test
    fun testBitShifts() {
        // bit shifts
        generateToken("<<", ShakeTokenType.BITWISE_SHL) // "<<"
        generateToken(">>", ShakeTokenType.BITWISE_SHR) // ">>"
    }

    @Test
    fun testBrackets() {
        // brackets
        generateToken("(", ShakeTokenType.LPAREN) // '('
        generateToken(")", ShakeTokenType.RPAREN) // ')'
        generateToken("{", ShakeTokenType.LCURL) // '{'
        generateToken("}", ShakeTokenType.RCURL) // '}'
    }

    fun testKeyword(test: KeywordTest) {
        generateToken(test.input, test.output)
    }

    @Test
    fun testDynamic() = testKeyword(KeywordTest.DYNAMIC)

    @Test
    fun testByte() = testKeyword(KeywordTest.BYTE)

    @Test
    fun testShort() = testKeyword(KeywordTest.SHORT)

    @Test
    fun testInt() = testKeyword(KeywordTest.INT)

    @Test
    fun testLong() = testKeyword(KeywordTest.LONG)

    @Test
    fun testFloat() = testKeyword(KeywordTest.FLOAT)

    @Test
    fun testDouble() = testKeyword(KeywordTest.DOUBLE)

    @Test
    fun testChar() = testKeyword(KeywordTest.CHAR)

    @Test
    fun testBoolean() = testKeyword(KeywordTest.BOOLEAN)

    @Test
    fun testConst() = testKeyword(KeywordTest.CONST)

    @Test
    fun testFunction() = testKeyword(KeywordTest.FUNCTION)

    @Test
    fun testReturn() = testKeyword(KeywordTest.RETURN)

    @Test
    fun testDo() = testKeyword(KeywordTest.DO)

    @Test
    fun testWhile() = testKeyword(KeywordTest.WHILE)

    @Test
    fun testFor() = testKeyword(KeywordTest.FOR)

    @Test
    fun testIf() = testKeyword(KeywordTest.IF)

    @Test
    fun testElse() = testKeyword(KeywordTest.ELSE)

    @Test
    fun testTrue() = testKeyword(KeywordTest.TRUE)

    @Test
    fun testFalse() = testKeyword(KeywordTest.FALSE)

    @Test
    fun testClass() = testKeyword(KeywordTest.CLASS)

    @Test
    fun testExtends() = testKeyword(KeywordTest.EXTENDS)

    @Test
    fun testImplements() = testKeyword(KeywordTest.IMPLEMENTS)

    @Test
    fun testStatic() = testKeyword(KeywordTest.STATIC)

    @Test
    fun testFinal() = testKeyword(KeywordTest.FINAL)

    @Test
    fun testPublic() = testKeyword(KeywordTest.PUBLIC)

    @Test
    fun testProtected() = testKeyword(KeywordTest.PROTECTED)

    @Test
    fun testPrivate() = testKeyword(KeywordTest.PRIVATE)

    @Test
    fun testNew() = testKeyword(KeywordTest.NEW)

    @Test
    fun testImport() = testKeyword(KeywordTest.IMPORT)

    @Test
    fun testVoid() = testKeyword(KeywordTest.VOID)

    @Test
    fun testConstructor() = testKeyword(KeywordTest.CONSTRUCTOR)

    @Test
    fun testAs() = testKeyword(KeywordTest.AS)

    @Test
    fun testNumbers() {
        // numbers
        assertEquals("149", generateToken("149", ShakeTokenType.INTEGER).value)
        assertEquals("0.01", generateToken("0.01", ShakeTokenType.DOUBLE).value)
    }

    @Test
    fun testIdentifiers() {
        // identifiers
        assertEquals("hello", generateToken("hello", ShakeTokenType.IDENTIFIER).value)
        assertEquals("world0A_", generateToken("world0A_", ShakeTokenType.IDENTIFIER).value)
    }

    @Test
    fun testSingleLineComments() {
        generateToken("// test\n", ShakeTokenType.LINE_SEPARATOR)
    }

    @Test
    fun testMultiLineComments() {
        generateToken("/* test */\n", ShakeTokenType.LINE_SEPARATOR)
        generateToken("/*\ntest\ntest2*/\n", ShakeTokenType.LINE_SEPARATOR)
        generateToken("/*\n * test\n *  test2\n */\n", ShakeTokenType.LINE_SEPARATOR)
    }

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
        EXTENDS("extends", ShakeTokenType.KEYWORD_EXTENDS),
        IMPLEMENTS("implements", ShakeTokenType.KEYWORD_IMPLEMENTS),
        STATIC("static", ShakeTokenType.KEYWORD_STATIC),
        FINAL("final", ShakeTokenType.KEYWORD_FINAL),
        PUBLIC("public", ShakeTokenType.KEYWORD_PUBLIC),
        PROTECTED("protected", ShakeTokenType.KEYWORD_PROTECTED),
        PRIVATE("private", ShakeTokenType.KEYWORD_PRIVATE),
        NEW("new", ShakeTokenType.KEYWORD_NEW),
        IMPORT("import", ShakeTokenType.KEYWORD_IMPORT),
        VOID("void", ShakeTokenType.KEYWORD_VOID),
        CONSTRUCTOR("constructor", ShakeTokenType.KEYWORD_CONSTRUCTOR),
        AS("as", ShakeTokenType.KEYWORD_AS);

        override fun toString(): String {
            return "Keyword '$input'"
        }
    }

    companion object {
        @Suppress("deprecation")
        fun generateToken(input: String, tt: ShakeTokenType): ShakeToken {
            val i: CharacterInputStream = SourceCharacterInputStream("<tests>", input)
            val lexer = object : ShakeLexingBase(i) {}
            val t = lexer.makeToken()
            assertSame(tt, t.type)
            assertFalse(i.hasNext())
            return t
        }
    }
}
