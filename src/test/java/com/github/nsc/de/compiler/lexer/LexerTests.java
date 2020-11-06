package com.github.nsc.de.compiler.lexer;

import com.github.nsc.de.compiler.lexer.characterinputstream.CharacterInputStream;
import com.github.nsc.de.compiler.lexer.characterinputstream.StringCharacterInputStream;
import com.github.nsc.de.compiler.lexer.token.Token;
import com.github.nsc.de.compiler.lexer.token.TokenType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class LexerTests {

    @Test
    public void testString() {

        // strings
        assertEquals("", generateToken("\"\"", TokenType.STRING).getValue());
        assertEquals("afvne9214 ro", generateToken("\"afvne9214 ro\"", TokenType.STRING).getValue());
        generateToken("\"\\t\\b\\n\\r\\f\\'\\\"\\\\a\"", TokenType.STRING);

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
        generateToken("^=", TokenType.POW_ASSIGN); // '^='
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
        generateToken("^", TokenType.POW); // '^'
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

    @Test
    public void testKeywords() {

        // keywords
        generateToken("dynamic", TokenType.KEYWORD_DYNAMIC); // "byte"
        generateToken("byte", TokenType.KEYWORD_BYTE); // "byte"
        generateToken("short", TokenType.KEYWORD_SHORT); // "short"
        generateToken("int", TokenType.KEYWORD_INT); // "int"
        generateToken("long", TokenType.KEYWORD_LONG); // "long"
        generateToken("float", TokenType.KEYWORD_FLOAT); // "float"
        generateToken("double", TokenType.KEYWORD_DOUBLE); // "double"
        generateToken("char", TokenType.KEYWORD_CHAR); // "char"
        generateToken("boolean", TokenType.KEYWORD_BOOLEAN); // "boolean"
        generateToken("var", TokenType.KEYWORD_VAR); // "var"
        generateToken("let", TokenType.KEYWORD_VAR); // "let"
        generateToken("const", TokenType.KEYWORD_CONST); // "const"

        generateToken("function", TokenType.KEYWORD_FUNCTION); // "function"
        generateToken("do", TokenType.KEYWORD_DO); // "do"
        generateToken("while", TokenType.KEYWORD_WHILE); // "while"
        generateToken("for", TokenType.KEYWORD_FOR); // "for"
        generateToken("if", TokenType.KEYWORD_IF); // "if"
        generateToken("else", TokenType.KEYWORD_ELSE); // "else"
        generateToken("true", TokenType.KEYWORD_TRUE);  // "true"
        generateToken("false", TokenType.KEYWORD_FALSE); // "false"

        generateToken("class", TokenType.KEYWORD_CLASS); // "class"
        generateToken("extends", TokenType.KEYWORD_EXTENDS); // "extends"
        generateToken("implements", TokenType.KEYWORD_IMPLEMENTS); // "implements"
        generateToken("static", TokenType.KEYWORD_STATIC); // "static"
        generateToken("final", TokenType.KEYWORD_FINAL); // "final"

        generateToken("public", TokenType.KEYWORD_PUBLIC); // "public"
        generateToken("protected", TokenType.KEYWORD_PROTECTED); // "protected"
        generateToken("private", TokenType.KEYWORD_PRIVATE); // "private"
        generateToken("new", TokenType.KEYWORD_NEW); // "new"


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

    }

    private Token generateToken(String input, TokenType tt) {
        CharacterInputStream in = new StringCharacterInputStream("<tests>", input);
        Lexer lexer = new Lexer(in);
        Token t = lexer.makeTokens().next();
        assertSame(tt, t.getType());
        assertFalse(in.hasNext());
        return t;
    }

}
