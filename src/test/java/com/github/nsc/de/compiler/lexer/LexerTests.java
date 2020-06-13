package com.github.nsc.de.compiler.lexer;

import static org.junit.jupiter.api.Assertions.*;

import com.github.nsc.de.compiler.lexer.characterinputstream.CharacterInputStream;
import com.github.nsc.de.compiler.lexer.characterinputstream.StringCharacterInputStream;
import com.github.nsc.de.compiler.lexer.token.Token;
import com.github.nsc.de.compiler.lexer.token.TokenInputStream;
import com.github.nsc.de.compiler.lexer.token.TokenType;
import jdk.nashorn.internal.ir.Assignment;
import org.junit.jupiter.api.Test;


public class LexerTests {

    @Test
    public void testMakeTokens() {

        // assign
        generateToken("=", TokenType.ASSIGN); // '='

        // math operators
        generateToken("+", TokenType.ADD); // '+'
        generateToken("-", TokenType.SUB); // '-'
        generateToken("*", TokenType.MUL); // '*'
        generateToken("/", TokenType.DIV); // '/'
        generateToken("^", TokenType.POW); // '^'
        generateToken("**", TokenType.POW); // "**"

        // math assign operators
        generateToken("++", TokenType.INCR); // '++'
        generateToken("--", TokenType.DECR); // '--'

        generateToken("+=", TokenType.ADD_ASSIGN); // '+='
        generateToken("-=", TokenType.SUB_ASSIGN); // '-='
        generateToken("*=", TokenType.MUL_ASSIGN); // '*='
        generateToken("/=", TokenType.DIV_ASSIGN); // '/='
        generateToken("^=", TokenType.POW_ASSIGN); // '^='
        generateToken("**=", TokenType.POW_ASSIGN); // "**="


        // logical comparison
        generateToken("==", TokenType.EQ_EQUALS); // "=="
        generateToken(">=", TokenType.BIGGER_EQUALS); // ">="
        generateToken("<=", TokenType.SMALLER_EQUALS); // "<="
        generateToken(">", TokenType.BIGGER); // '>'
        generateToken("<", TokenType.SMALLER); // '<'

        // logical concatenation
        generateToken("||", TokenType.LOGICAL_OR); // "||"
        generateToken("&&", TokenType.LOGICAL_AND); // "&&"

        // brackets
        generateToken("(", TokenType.LPAREN); // '('
        generateToken(")", TokenType.RPAREN); // ')'
        generateToken("{", TokenType.LCURL); // '{'
        generateToken("}", TokenType.RCURL); // '}'

        // keywords
        generateToken("var", TokenType.KEYWORD_VAR); // "var"
        generateToken("while", TokenType.KEYWORD_WHILE); // "while"
        generateToken("if", TokenType.KEYWORD_IF); // "if"
        generateToken("else", TokenType.KEYWORD_ELSE); // "else"
        generateToken("true", TokenType.KEYWORD_TRUE);  // "true"
        generateToken("false", TokenType.KEYWORD_FALSE); // "false"


        // identifiers
        assertEquals("hello", generateToken("hello", TokenType.IDENTIFIER).getValue());
        assertEquals("world0A_", generateToken("world0A_", TokenType.IDENTIFIER).getValue());


        // numbers
        assertEquals(149, generateToken("149", TokenType.INTEGER).getValue());
        assertEquals(0.01, generateToken("0.01", TokenType.DOUBLE).getValue());
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
