package com.github.nsc.de.compiler.lexer;

import static org.junit.jupiter.api.Assertions.*;

import com.github.nsc.de.compiler.lexer.characterinputstream.CharacterInputStream;
import com.github.nsc.de.compiler.lexer.characterinputstream.StringCharacterInputStream;
import com.github.nsc.de.compiler.lexer.token.Token;
import com.github.nsc.de.compiler.lexer.token.TokenInputStream;
import com.github.nsc.de.compiler.lexer.token.TokenType;
import org.junit.jupiter.api.Test;


public class LexerTests {

    @Test
    public void testMakeTokens() {

        String input;
        Token token;

        // assign
        input = "= ";

        // math operators
        input += "+ - * / ^ ** ";

        // logical comparison
        input += "== >= <= > < ";

        // logical concatenation
        input += "|| && ";

        // brackets
        input += "(){} ";

        // keywords
        input += "var while true false ";

        // identifiers
        input += "hello world0A_ ";

        // numbers
        input += "149 0.01 ";

        CharacterInputStream in = new StringCharacterInputStream("<tests>", input);
        Lexer lexer = new Lexer(in);
        TokenInputStream tokens = lexer.makeTokens();

        // assign
        assertSame(TokenType.ASSIGN, tokens.next().getType()); // '='

        // math operators
        assertSame(TokenType.ADD, tokens.next().getType()); // '+'
        assertSame(TokenType.SUB, tokens.next().getType()); // '-'
        assertSame(TokenType.MUL, tokens.next().getType()); // '*'
        assertSame(TokenType.DIV, tokens.next().getType()); // '/'
        assertSame(TokenType.POW, tokens.next().getType()); // '^'
        assertSame(TokenType.POW, tokens.next().getType()); // "**"


        // logical comparison
        assertSame(TokenType.EQ_EQUALS, tokens.next().getType());      // "=="
        assertSame(TokenType.BIGGER_EQUALS, tokens.next().getType());  // ">="
        assertSame(TokenType.SMALLER_EQUALS, tokens.next().getType()); // "<="
        assertSame(TokenType.BIGGER, tokens.next().getType());         // '>'
        assertSame(TokenType.SMALLER, tokens.next().getType());        // '<'

        // logical concatenation
        assertSame(TokenType.LOGICAL_OR, tokens.next().getType());     // "||"
        assertSame(TokenType.LOGICAL_AND, tokens.next().getType());    // "&&"

        // brackets
        assertSame(TokenType.LPAREN, tokens.next().getType()); // '('
        assertSame(TokenType.RPAREN, tokens.next().getType()); // ')'
        assertSame(TokenType.LCURL, tokens.next().getType());  // '{'
        assertSame(TokenType.RCURL, tokens.next().getType());  // '}'

        // keywords
        assertSame(TokenType.KEYWORD_VAR, tokens.next().getType());   // "var"
        assertSame(TokenType.KEYWORD_WHILE, tokens.next().getType()); // "while"
        assertSame(TokenType.KEYWORD_TRUE, tokens.next().getType());  // "true"
        assertSame(TokenType.KEYWORD_FALSE, tokens.next().getType()); // "false"


        // identifiers
        token = tokens.next(); // simple identifier
        assertSame(TokenType.IDENTIFIER, token.getType());
        assertEquals("hello", token.getValue());

        token = tokens.next(); // identifier using number, uppercase letters and '_'
        assertSame(TokenType.IDENTIFIER, token.getType());
        assertEquals("world0A_", token.getValue());


        // numbers
        token = tokens.next(); // integer number
        assertSame(TokenType.INTEGER, token.getType());
        assertEquals(149, token.getValue());

        token = tokens.next(); // double number
        assertSame(TokenType.DOUBLE, token.getType());
        assertEquals(0.01, token.getValue());
    }

}
