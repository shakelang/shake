package com.github.nsc.de.shake.lexer

import com.github.nsc.de.shake.lexer.token.Token
import com.github.nsc.de.shake.lexer.token.TokenType
import com.github.nsc.de.shake.util.characterinput.characterinputstream.CharacterInputStream
import com.github.nsc.de.shake.util.characterinput.characterinputstream.SourceCharacterInputStream
import kotlin.test.*

class LexerTests {
    @Test
    fun testString() {

        // strings
        assertEquals("", generateToken("\"\"", TokenType.STRING).value)
        assertEquals("afvne9214 ro", generateToken("\"afvne9214 ro\"", TokenType.STRING).value)
        assertEquals(
            "\\t\\b\\n\\r\\f\\'\\\"\\\\a\\u0000",
            generateToken("\"\\t\\b\\n\\r\\f\\'\\\"\\\\a\\u0000\"", TokenType.STRING).value
        )
    }

    @Test
    fun testCharacter() {

        // characters
        assertEquals(" ", generateToken("' '", TokenType.CHARACTER).value)
        assertEquals("a", generateToken("'a'", TokenType.CHARACTER).value)
        assertEquals("\\r", generateToken("'\\r'", TokenType.CHARACTER).value)
        assertEquals("\\n", generateToken("'\\n'", TokenType.CHARACTER).value)
        assertEquals("\\b", generateToken("'\\b'", TokenType.CHARACTER).value)
        assertEquals("\\t", generateToken("'\\t'", TokenType.CHARACTER).value)
        assertEquals("\\f", generateToken("'\\f'", TokenType.CHARACTER).value)
        assertEquals("\\'", generateToken("'\\''", TokenType.CHARACTER).value)
        assertEquals("\\u0000", generateToken("'\\u0000'", TokenType.CHARACTER).value)
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
    fun testKeyword(test: KeywordTest) {
        generateToken(test.input, test.output)
    }

    @Test fun testDynamic() = testKeyword(KeywordTest.DYNAMIC)
    @Test fun testByte() = testKeyword(KeywordTest.BYTE)
    @Test fun testShort() = testKeyword(KeywordTest.SHORT)
    @Test fun testInt() = testKeyword(KeywordTest.INT)
    @Test fun testLong() = testKeyword(KeywordTest.LONG)
    @Test fun testFloat() = testKeyword(KeywordTest.FLOAT)
    @Test fun testDouble() = testKeyword(KeywordTest.DOUBLE)
    @Test fun testChar() = testKeyword(KeywordTest.CHAR)
    @Test fun testBoolean() = testKeyword(KeywordTest.BOOLEAN)
    @Test fun testVar() = testKeyword(KeywordTest.VAR)
    @Test fun testLet() = testKeyword(KeywordTest.LET)
    @Test fun testConst() = testKeyword(KeywordTest.CONST)
    @Test fun testFunction() = testKeyword(KeywordTest.FUNCTION)
    @Test fun testReturn() = testKeyword(KeywordTest.RETURN)
    @Test fun testDo() = testKeyword(KeywordTest.DO)
    @Test fun testWhile() = testKeyword(KeywordTest.WHILE)
    @Test fun testFor() = testKeyword(KeywordTest.FOR)
    @Test fun testIf() = testKeyword(KeywordTest.IF)
    @Test fun testElse() = testKeyword(KeywordTest.ELSE)
    @Test fun testTrue() = testKeyword(KeywordTest.TRUE)
    @Test fun testFalse() = testKeyword(KeywordTest.FALSE)
    @Test fun testClass() = testKeyword(KeywordTest.CLASS)
    @Test fun testExtends() = testKeyword(KeywordTest.EXTENDS)
    @Test fun testImplements() = testKeyword(KeywordTest.IMPLEMENTS)
    @Test fun testStatic() = testKeyword(KeywordTest.STATIC)
    @Test fun testFinal() = testKeyword(KeywordTest.FINAL)
    @Test fun testPublic() = testKeyword(KeywordTest.PUBLIC)
    @Test fun testProtected() = testKeyword(KeywordTest.PROTECTED)
    @Test fun testPrivate() = testKeyword(KeywordTest.PRIVATE)
    @Test fun testNew() = testKeyword(KeywordTest.NEW)
    @Test fun testImport() = testKeyword(KeywordTest.IMPORT)
    @Test fun testVoid() = testKeyword(KeywordTest.VOID)
    @Test fun testConstructor() = testKeyword(KeywordTest.CONSTRUCTOR)
    @Test fun testAs() = testKeyword(KeywordTest.AS)

    @Test
    fun testNumbers() {

        // numbers
        assertEquals("149", generateToken("149", TokenType.INTEGER).value)
        assertEquals("0.01", generateToken("0.01", TokenType.DOUBLE).value)
    }

    @Test
    fun testIdentifiers() {

        // identifiers
        assertEquals("hello", generateToken("hello", TokenType.IDENTIFIER).value)
        assertEquals("world0A_", generateToken("world0A_", TokenType.IDENTIFIER).value)
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
        DYNAMIC("dynamic", TokenType.KEYWORD_DYNAMIC),
        BYTE("byte", TokenType.KEYWORD_BYTE),
        SHORT("short", TokenType.KEYWORD_SHORT),
        INT("int", TokenType.KEYWORD_INT),
        LONG("long", TokenType.KEYWORD_LONG),
        FLOAT("float", TokenType.KEYWORD_FLOAT),
        DOUBLE("double", TokenType.KEYWORD_DOUBLE),
        CHAR("char", TokenType.KEYWORD_CHAR),
        BOOLEAN("boolean", TokenType.KEYWORD_BOOLEAN),
        VAR("var", TokenType.KEYWORD_VAR),
        LET("let", TokenType.KEYWORD_VAR),
        CONST("const", TokenType.KEYWORD_CONST),
        FUNCTION("function", TokenType.KEYWORD_FUNCTION),
        RETURN("return", TokenType.KEYWORD_RETURN),
        DO("do", TokenType.KEYWORD_DO),
        WHILE("while", TokenType.KEYWORD_WHILE),
        FOR("for", TokenType.KEYWORD_FOR),
        IF("if", TokenType.KEYWORD_IF),
        ELSE("else", TokenType.KEYWORD_ELSE),
        TRUE("true", TokenType.KEYWORD_TRUE),
        FALSE("false", TokenType.KEYWORD_FALSE),
        CLASS("class", TokenType.KEYWORD_CLASS),
        EXTENDS("extends", TokenType.KEYWORD_EXTENDS),
        IMPLEMENTS("implements", TokenType.KEYWORD_IMPLEMENTS),
        STATIC("static", TokenType.KEYWORD_STATIC),
        FINAL("final", TokenType.KEYWORD_FINAL),
        PUBLIC("public", TokenType.KEYWORD_PUBLIC),
        PROTECTED("protected", TokenType.KEYWORD_PROTECTED),
        PRIVATE("private", TokenType.KEYWORD_PRIVATE),
        NEW("new", TokenType.KEYWORD_NEW),
        IMPORT("import", TokenType.KEYWORD_IMPORT),
        VOID("void", TokenType.KEYWORD_VOID),
        CONSTRUCTOR("constructor", TokenType.KEYWORD_CONSTRUCTOR),
        AS("as", TokenType.KEYWORD_AS);

        override fun toString(): String {
            return "Keyword '$input'"
        }
    }

    companion object {
        @Suppress("deprecation")
        fun generateToken(input: String, tt: Byte): Token {
            val i: CharacterInputStream = SourceCharacterInputStream("<tests>", input)
            val lexer = Lexer(i)
            val t = lexer.makeTokens().next()
            assertSame(tt, t.type)
            assertFalse(i.hasNext())
            return t
        }
    }
}