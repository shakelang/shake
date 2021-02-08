package com.github.nsc.de.shake.lexer;

import com.github.nsc.de.shake.lexer.characterinput.characterinputstream.CharacterInputStream;
import com.github.nsc.de.shake.lexer.characterinput.characterinputstream.SourceCharacterInputStream;
import com.github.nsc.de.shake.lexer.token.Token;
import com.github.nsc.de.shake.lexer.token.TokenType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;


public class LexerTests {

    @Test
    public void testString() {

        // strings
        assertEquals("", generateToken("\"\"", TokenType.STRING).getValue());
        assertEquals("afvne9214 ro", generateToken("\"afvne9214 ro\"", TokenType.STRING).getValue());
        assertEquals("\\t\\b\\n\\r\\f\\'\\\"\\\\a\\u0000", generateToken("\"\\t\\b\\n\\r\\f\\'\\\"\\\\a\\u0000\"", TokenType.STRING).getValue());

    }

    @Test
    public void testCharacter() {

        // characters
        assertEquals(" ", generateToken("' '", TokenType.CHARACTER).getValue());
        assertEquals("a", generateToken("'a'", TokenType.CHARACTER).getValue());
        assertEquals("\\r", generateToken("'\\r'", TokenType.CHARACTER).getValue());
        assertEquals("\\n", generateToken("'\\n'", TokenType.CHARACTER).getValue());
        assertEquals("\\b", generateToken("'\\b'", TokenType.CHARACTER).getValue());
        assertEquals("\\t", generateToken("'\\t'", TokenType.CHARACTER).getValue());
        assertEquals("\\f", generateToken("'\\f'", TokenType.CHARACTER).getValue());
        assertEquals("\\'", generateToken("'\\''", TokenType.CHARACTER).getValue());
        assertEquals("\\u0000", generateToken("'\\u0000'", TokenType.CHARACTER).getValue());

    }

    @Test
    public void testPunctuation() {

        // punctuation
        generateToken(";", TokenType.SEMICOLON); // ';'
        generateToken(",", TokenType.COMMA); // ','
        generateToken(".", TokenType.DOT); // '.'

    }

    @Test
    public void testLineSeparator() {

        // line separator
        generateToken("\n", TokenType.LINE_SEPARATOR); // '\n'
        generateToken("\r\n", TokenType.LINE_SEPARATOR); // '\r\n'

    }

    @Test
    public void testAssign() {

        // assign
        generateToken("=", TokenType.ASSIGN); // '='

        // math assign operators
        generateToken("+=", TokenType.ADD_ASSIGN); // '+='
        generateToken("-=", TokenType.SUB_ASSIGN); // '-='
        generateToken("*=", TokenType.MUL_ASSIGN); // '*='
        generateToken("/=", TokenType.DIV_ASSIGN); // '/='
        generateToken("%=", TokenType.MOD_ASSIGN); // '%='
        generateToken("**=", TokenType.POW_ASSIGN); // "**="

        generateToken("++", TokenType.INCR); // '++'
        generateToken("--", TokenType.DECR); // '--'

    }

    @Test
    public void testMathOperators() {

        // math operators
        generateToken("+", TokenType.ADD); // '+'
        generateToken("-", TokenType.SUB); // '-'
        generateToken("*", TokenType.MUL); // '*'
        generateToken("/", TokenType.DIV); // '/'
        generateToken("%", TokenType.MOD); // '%'
        generateToken("**", TokenType.POW); // "**"

    }

    @Test
    public void testLogicalComparison() {

        // logical comparison
        generateToken("==", TokenType.EQ_EQUALS); // "=="
        generateToken(">=", TokenType.BIGGER_EQUALS); // ">="
        generateToken("<=", TokenType.SMALLER_EQUALS); // "<="
        generateToken(">", TokenType.BIGGER); // '>'
        generateToken("<", TokenType.SMALLER); // '<'

    }

    @Test
    public void testLogicalConcatenation() {

        // logical concatenation
        generateToken("||", TokenType.LOGICAL_OR); // "||"
        generateToken("&&", TokenType.LOGICAL_AND); // "&&"

    }

    @Test
    public void testBrackets() {

        // brackets
        generateToken("(", TokenType.LPAREN); // '('
        generateToken(")", TokenType.RPAREN); // ')'
        generateToken("{", TokenType.LCURL); // '{'
        generateToken("}", TokenType.RCURL); // '}'

    }

    @ParameterizedTest
    @EnumSource(KeywordTest.class)
    public void testKeyword(KeywordTest test) {
        generateToken(test.input, test.output);
    }

    @Test
    public void testNumbers() {

        // numbers
        assertEquals("149", generateToken("149", TokenType.INTEGER).getValue());
        assertEquals("0.01", generateToken("0.01", TokenType.DOUBLE).getValue());

    }

    @Test
    public void testIdentifiers() {

        // identifiers
        assertEquals("hello", generateToken("hello", TokenType.IDENTIFIER).getValue());
        assertEquals("world0A_", generateToken("world0A_", TokenType.IDENTIFIER).getValue());

    }

    @Test
    public void testSingleLineComments() {

        generateToken("// test\n", TokenType.LINE_SEPARATOR);

    }

    @Test
    public void testMultiLineComments() {

        generateToken("/* test */\n", TokenType.LINE_SEPARATOR);
        generateToken("/*\ntest\ntest2*/\n", TokenType.LINE_SEPARATOR);
        generateToken("/*\n * test\n *  test2\n */\n", TokenType.LINE_SEPARATOR);

    }

    public static Token generateToken(String input, byte tt) {
        CharacterInputStream in = new SourceCharacterInputStream("<tests>", input);
        Lexer lexer = new Lexer(in);
        Token t = lexer.makeTokens().next();
        assertSame(tt, t.getType());
        assertFalse(in.hasNext());
        return t;
    }

    private enum KeywordTest {

        DYNAMIC ("dynamic", TokenType.KEYWORD_DYNAMIC),
        BYTE ("byte", TokenType.KEYWORD_BYTE),
        SHORT ("short", TokenType.KEYWORD_SHORT),
        INT ("int", TokenType.KEYWORD_INT),
        LONG ("long", TokenType.KEYWORD_LONG),
        FLOAT ("float", TokenType.KEYWORD_FLOAT),
        DOUBLE ("double", TokenType.KEYWORD_DOUBLE),
        CHAR ("char", TokenType.KEYWORD_CHAR),
        BOOLEAN ("boolean", TokenType.KEYWORD_BOOLEAN),
        VAR ("var", TokenType.KEYWORD_VAR),
        LET ("let", TokenType.KEYWORD_VAR),
        CONST ("const", TokenType.KEYWORD_CONST),

        FUNCTION ("function", TokenType.KEYWORD_FUNCTION),
        RETURN ("return", TokenType.KEYWORD_RETURN),
        DO ("do", TokenType.KEYWORD_DO),
        WHILE ("while", TokenType.KEYWORD_WHILE),
        FOR ("for", TokenType.KEYWORD_FOR),
        IF ("if", TokenType.KEYWORD_IF),
        ELSE ("else", TokenType.KEYWORD_ELSE),
        TRUE ("true", TokenType.KEYWORD_TRUE),
        FALSE ("false", TokenType.KEYWORD_FALSE),

        CLASS ("class", TokenType.KEYWORD_CLASS),
        EXTENDS ("extends", TokenType.KEYWORD_EXTENDS),
        IMPLEMENTS ("implements", TokenType.KEYWORD_IMPLEMENTS),
        STATIC ("static", TokenType.KEYWORD_STATIC),
        FINAL ("final", TokenType.KEYWORD_FINAL),

        PUBLIC ("public", TokenType.KEYWORD_PUBLIC),
        PROTECTED("protected", TokenType.KEYWORD_PROTECTED),
        PRIVATE ("private", TokenType.KEYWORD_PRIVATE),
        NEW ("new", TokenType.KEYWORD_NEW),

        IMPORT ("import", TokenType.KEYWORD_IMPORT),
        VOID ("void", TokenType.KEYWORD_VOID),
        CONSTRUCTOR ("constructor", TokenType.KEYWORD_CONSTRUCTOR),
        ;
        private final String input;
        private final byte output;

        KeywordTest(String input, byte output) {
            this.input = input;
            this.output = output;
        }

        @Override
        public String toString() {
            return "Keyword '" + input + '\'';
        }
    }

}
