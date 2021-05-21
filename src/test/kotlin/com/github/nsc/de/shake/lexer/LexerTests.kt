package com.github.nsc.de.shake.lexer

import com.github.nsc.de.shake.lexer.token.Token
import com.github.nsc.de.shake.lexer.token.TokenType
import com.github.nsc.de.shake.util.characterinput.characterinputstream.CharacterInputStream
import com.github.nsc.de.shake.util.characterinput.characterinputstream.SourceCharacterInputStream
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class LexerTests {
    @Test
    fun testString() {

        // strings
        Assertions.assertEquals("", generateToken("\"\"", TokenType.STRING).value)
        Assertions.assertEquals("afvne9214 ro", generateToken("\"afvne9214 ro\"", TokenType.STRING).value)
        Assertions.assertEquals(
            "\\t\\b\\n\\r\\f\\'\\\"\\\\a\\u0000",
            generateToken("\"\\t\\b\\n\\r\\f\\'\\\"\\\\a\\u0000\"", TokenType.STRING).value
        )
    }

    @Test
    fun testCharacter() {

        // characters
        Assertions.assertEquals(" ", generateToken("' '", TokenType.CHARACTER).value)
        Assertions.assertEquals("a", generateToken("'a'", TokenType.CHARACTER).value)
        Assertions.assertEquals("\\r", generateToken("'\\r'", TokenType.CHARACTER).value)
        Assertions.assertEquals("\\n", generateToken("'\\n'", TokenType.CHARACTER).value)
        Assertions.assertEquals("\\b", generateToken("'\\b'", TokenType.CHARACTER).value)
        Assertions.assertEquals("\\t", generateToken("'\\t'", TokenType.CHARACTER).value)
        Assertions.assertEquals("\\f", generateToken("'\\f'", TokenType.CHARACTER).value)
        Assertions.assertEquals("\\'", generateToken("'\\''", TokenType.CHARACTER).value)
        Assertions.assertEquals("\\u0000", generateToken("'\\u0000'", TokenType.CHARACTER).value)
    }

    @Test
    fun testPunctuation() {

        // punctuation
        generateToken(";", TokenType.SEMICOLON) // ';'
        generateToken(",", TokenType.COMMA) // ','
        generateToken(".", TokenType.DOT) // '.'
    }

    @Test
    fun testLineSeparator() {

        // line separator
        generateToken("\n", TokenType.LINE_SEPARATOR) // '\n'
        generateToken("\r\n", TokenType.LINE_SEPARATOR) // '\r\n'
    }

    @Test
    fun testAssign() {

        // assign
        generateToken("=", TokenType.ASSIGN) // '='

        // math assign operators
        generateToken("+=", TokenType.ADD_ASSIGN) // '+='
        generateToken("-=", TokenType.SUB_ASSIGN) // '-='
        generateToken("*=", TokenType.MUL_ASSIGN) // '*='
        generateToken("/=", TokenType.DIV_ASSIGN) // '/='
        generateToken("%=", TokenType.MOD_ASSIGN) // '%='
        generateToken("**=", TokenType.POW_ASSIGN) // "**="
        generateToken("++", TokenType.INCR) // '++'
        generateToken("--", TokenType.DECR) // '--'
    }

    @Test
    fun testMathOperators() {

        // math operators
        generateToken("+", TokenType.ADD) // '+'
        generateToken("-", TokenType.SUB) // '-'
        generateToken("*", TokenType.MUL) // '*'
        generateToken("/", TokenType.DIV) // '/'
        generateToken("%", TokenType.MOD) // '%'
        generateToken("**", TokenType.POW) // "**"
    }

    @Test
    fun testLogicalComparison() {

        // logical comparison
        generateToken("==", TokenType.EQ_EQUALS) // "=="
        generateToken(">=", TokenType.BIGGER_EQUALS) // ">="
        generateToken("<=", TokenType.SMALLER_EQUALS) // "<="
        generateToken(">", TokenType.BIGGER) // '>'
        generateToken("<", TokenType.SMALLER) // '<'
    }

    @Test
    fun testLogicalConcatenation() {

        // logical concatenation
        generateToken("||", TokenType.LOGICAL_OR) // "||"
        generateToken("^", TokenType.LOGICAL_XOR) // "^"
        generateToken("&&", TokenType.LOGICAL_AND) // "&&"
    }

    @Test
    fun testBrackets() {

        // brackets
        generateToken("(", TokenType.LPAREN) // '('
        generateToken(")", TokenType.RPAREN) // ')'
        generateToken("{", TokenType.LCURL) // '{'
        generateToken("}", TokenType.RCURL) // '}'
    }

    @ParameterizedTest
    @EnumSource(KeywordTest::class)
    fun testKeyword(test: KeywordTest) {
        generateToken(test.input, test.output)
    }

    @Test
    fun testNumbers() {

        // numbers
        Assertions.assertEquals("149", generateToken("149", TokenType.INTEGER).value)
        Assertions.assertEquals("0.01", generateToken("0.01", TokenType.DOUBLE).value)
    }

    @Test
    fun testIdentifiers() {

        // identifiers
        Assertions.assertEquals("hello", generateToken("hello", TokenType.IDENTIFIER).value)
        Assertions.assertEquals("world0A_", generateToken("world0A_", TokenType.IDENTIFIER).value)
    }

    @Test
    fun testSingleLineComments() {
        generateToken("// test\n", TokenType.LINE_SEPARATOR)
    }

    @Test
    fun testMultiLineComments() {
        generateToken("/* test */\n", TokenType.LINE_SEPARATOR)
        generateToken("/*\ntest\ntest2*/\n", TokenType.LINE_SEPARATOR)
        generateToken("/*\n * test\n *  test2\n */\n", TokenType.LINE_SEPARATOR)
    }

    @Suppress("unused")
    enum class KeywordTest(val input: String, val output: Byte) {
        DYNAMIC("dynamic", TokenType.KEYWORD_DYNAMIC), BYTE("byte", TokenType.KEYWORD_BYTE), SHORT(
            "short",
            TokenType.KEYWORD_SHORT
        ),
        INT("int", TokenType.KEYWORD_INT), LONG("long", TokenType.KEYWORD_LONG), FLOAT(
            "float",
            TokenType.KEYWORD_FLOAT
        ),
        DOUBLE("double", TokenType.KEYWORD_DOUBLE), CHAR("char", TokenType.KEYWORD_CHAR), BOOLEAN(
            "boolean",
            TokenType.KEYWORD_BOOLEAN
        ),
        VAR("var", TokenType.KEYWORD_VAR), LET("let", TokenType.KEYWORD_VAR), CONST(
            "const",
            TokenType.KEYWORD_CONST
        ),
        FUNCTION("function", TokenType.KEYWORD_FUNCTION), RETURN("return", TokenType.KEYWORD_RETURN), DO(
            "do",
            TokenType.KEYWORD_DO
        ),
        WHILE("while", TokenType.KEYWORD_WHILE), FOR("for", TokenType.KEYWORD_FOR), IF(
            "if",
            TokenType.KEYWORD_IF
        ),
        ELSE("else", TokenType.KEYWORD_ELSE), TRUE("true", TokenType.KEYWORD_TRUE), FALSE(
            "false",
            TokenType.KEYWORD_FALSE
        ),
        CLASS("class", TokenType.KEYWORD_CLASS), EXTENDS("extends", TokenType.KEYWORD_EXTENDS), IMPLEMENTS(
            "implements",
            TokenType.KEYWORD_IMPLEMENTS
        ),
        STATIC("static", TokenType.KEYWORD_STATIC), FINAL("final", TokenType.KEYWORD_FINAL), PUBLIC(
            "public",
            TokenType.KEYWORD_PUBLIC
        ),
        PROTECTED("protected", TokenType.KEYWORD_PROTECTED), PRIVATE("private", TokenType.KEYWORD_PRIVATE), NEW(
            "new",
            TokenType.KEYWORD_NEW
        ),
        IMPORT("import", TokenType.KEYWORD_IMPORT), VOID("void", TokenType.KEYWORD_VOID), CONSTRUCTOR(
            "constructor",
            TokenType.KEYWORD_CONSTRUCTOR
        ),
        AS("as", TokenType.KEYWORD_AS);

        override fun toString(): String {
            return "Keyword '$input'"
        }
    }

    companion object {
        @Suppress("deprecation")
        fun generateToken(input: String?, tt: Byte): Token {
            val chars: CharacterInputStream = SourceCharacterInputStream("<tests>", input)
            val lexer = Lexer(chars)
            val t = lexer.makeTokens().next()
            Assertions.assertSame(tt, t.type)
            Assertions.assertFalse(chars.hasNext())
            return t
        }
    }
}