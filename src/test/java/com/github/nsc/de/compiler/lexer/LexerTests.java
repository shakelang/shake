package com.github.nsc.de.compiler.lexer;

import com.github.nsc.de.compiler.lexer.characterinputstream.CharacterInputStream;
import com.github.nsc.de.compiler.lexer.characterinputstream.StringCharacterInputStream;
import com.github.nsc.de.compiler.lexer.token.Token;
import com.github.nsc.de.compiler.lexer.token.TokenInputStream;
import com.github.nsc.de.compiler.lexer.token.TokenType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class LexerTests {

    @Test
    public void testTokens() {

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
        Assertions.assertSame(TokenType.ASSIGN, tokens.next().getType()); // '='

        // math operators
        Assertions.assertSame(TokenType.ADD, tokens.next().getType()); // '+'
        Assertions.assertSame(TokenType.SUB, tokens.next().getType()); // '-'
        Assertions.assertSame(TokenType.MUL, tokens.next().getType()); // '*'
        Assertions.assertSame(TokenType.DIV, tokens.next().getType()); // '/'
        Assertions.assertSame(TokenType.POW, tokens.next().getType()); // '^'
        Assertions.assertSame(TokenType.POW, tokens.next().getType()); // "**"


        // logical comparison
        Assertions.assertSame(TokenType.EQ_EQUALS, tokens.next().getType());      // "=="
        Assertions.assertSame(TokenType.BIGGER_EQUALS, tokens.next().getType());  // ">="
        Assertions.assertSame(TokenType.SMALLER_EQUALS, tokens.next().getType()); // "<="
        Assertions.assertSame(TokenType.BIGGER, tokens.next().getType());         // '>'
        Assertions.assertSame(TokenType.SMALLER, tokens.next().getType());        // '<'

        // logical concatenation
        Assertions.assertSame(TokenType.LOGICAL_OR, tokens.next().getType());     // "||"
        Assertions.assertSame(TokenType.LOGICAL_AND, tokens.next().getType());    // "&&"

        // brackets
        Assertions.assertSame(TokenType.LPAREN, tokens.next().getType()); // '('
        Assertions.assertSame(TokenType.RPAREN, tokens.next().getType()); // ')'
        Assertions.assertSame(TokenType.LCURL, tokens.next().getType());  // '{'
        Assertions.assertSame(TokenType.RCURL, tokens.next().getType());  // '}'

        // keywords
        Assertions.assertSame(TokenType.KEYWORD_VAR, tokens.next().getType());   // "var"
        Assertions.assertSame(TokenType.KEYWORD_WHILE, tokens.next().getType()); // "while"
        Assertions.assertSame(TokenType.KEYWORD_TRUE, tokens.next().getType());  // "true"
        Assertions.assertSame(TokenType.KEYWORD_FALSE, tokens.next().getType()); // "false"


        // identifiers
        token = tokens.next(); // simple identifier
        Assertions.assertSame(TokenType.IDENTIFIER, token.getType());
        Assertions.assertEquals("hello", token.getValue());

        token = tokens.next(); // identifier using number, uppercase letters and '_'
        Assertions.assertSame(TokenType.IDENTIFIER, token.getType());
        Assertions.assertEquals("world0A_", token.getValue());


        // numbers
        token = tokens.next(); // integer number
        Assertions.assertSame(TokenType.INTEGER, token.getType());
        Assertions.assertEquals(149, token.getValue());

        token = tokens.next(); // double number
        Assertions.assertSame(TokenType.DOUBLE, token.getType());
        Assertions.assertEquals(0.01, token.getValue());
    }

}
