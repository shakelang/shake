package com.github.nsc.de.shake.lexer;

import com.github.nsc.de.shake.lexer.characterinputstream.CharacterInputStream;
import com.github.nsc.de.shake.lexer.characterinputstream.StringCharacterInputStream;
import com.github.nsc.de.shake.lexer.token.TokenInputStream;
import com.github.nsc.de.shake.lexer.token.TokenType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TokenPosition {

    @Test
    public void testGetBasicPosition() {

        CharacterInputStream chars = new StringCharacterInputStream("<tests>", "var test = 10;");
        Lexer lexer = new Lexer(chars);
        TokenInputStream in = lexer.makeTokens();

        assertSame(TokenType.KEYWORD_VAR, in.peek().getType());
        assertSame(null, in.peek().getValue());

        assertSame(0, in.peek().getStart().getIndex());
        assertSame(1, in.peek().getStart().getColumn());
        assertSame(1, in.peek().getStart().getLine());

        assertSame(2, in.peek().getEnd().getIndex());
        assertSame(3, in.peek().getEnd().getColumn());
        assertSame(1, in.peek().getEnd().getLine());

        in.skip();

        assertSame(TokenType.IDENTIFIER, in.peek().getType());
        assertEquals("test", in.peek().getValue());

        assertSame(4, in.peek().getStart().getIndex());
        assertSame(5, in.peek().getStart().getColumn());
        assertSame(1, in.peek().getStart().getLine());

        assertSame(7, in.peek().getEnd().getIndex());
        assertSame(8, in.peek().getEnd().getColumn());
        assertSame(1, in.peek().getEnd().getLine());

        in.skip();

        assertSame(TokenType.ASSIGN, in.peek().getType());
        assertSame(null, in.peek().getValue());

        assertSame(9, in.peek().getStart().getIndex());
        assertSame(10, in.peek().getStart().getColumn());
        assertSame(1, in.peek().getStart().getLine());

        assertSame(9, in.peek().getEnd().getIndex());
        assertSame(10, in.peek().getEnd().getColumn());
        assertSame(1, in.peek().getEnd().getLine());

        in.skip();

        assertSame(TokenType.INTEGER, in.peek().getType());
        assertEquals("10", in.peek().getValue());

        assertSame(11, in.peek().getStart().getIndex());
        assertSame(12, in.peek().getStart().getColumn());
        assertSame(1, in.peek().getStart().getLine());

        assertSame(12, in.peek().getEnd().getIndex());
        assertSame(13, in.peek().getEnd().getColumn());
        assertSame(1, in.peek().getEnd().getLine());

        in.skip();

        assertSame(TokenType.SEMICOLON, in.peek().getType());
        assertSame(null, in.peek().getValue());

        assertSame(13, in.peek().getStart().getIndex());
        assertSame(14, in.peek().getStart().getColumn());
        assertSame(1, in.peek().getStart().getLine());

        assertSame(13, in.peek().getEnd().getIndex());
        assertSame(14, in.peek().getEnd().getColumn());
        assertSame(1, in.peek().getEnd().getLine());

    }

    @Test
    public void testGetMultiLinePosition() {

        CharacterInputStream chars = new StringCharacterInputStream("<tests>", "var test\n  = \n10;");
        Lexer lexer = new Lexer(chars);
        TokenInputStream in = lexer.makeTokens();

        assertSame(TokenType.KEYWORD_VAR, in.peek().getType());
        assertSame(null, in.peek().getValue());

        assertSame(0, in.peek().getStart().getIndex());
        assertSame(1, in.peek().getStart().getColumn());
        assertSame(1, in.peek().getStart().getLine());

        assertSame(2, in.peek().getEnd().getIndex());
        assertSame(3, in.peek().getEnd().getColumn());
        assertSame(1, in.peek().getEnd().getLine());

        in.skip();

        assertSame(TokenType.IDENTIFIER, in.peek().getType());
        assertEquals("test", in.peek().getValue());

        assertSame(4, in.peek().getStart().getIndex());
        assertSame(5, in.peek().getStart().getColumn());
        assertSame(1, in.peek().getStart().getLine());

        assertSame(7, in.peek().getEnd().getIndex());
        assertSame(8, in.peek().getEnd().getColumn());
        assertSame(1, in.peek().getEnd().getLine());

        in.skip();

        assertSame(TokenType.LINE_SEPARATOR, in.peek().getType());
        assertSame(null, in.peek().getValue());

        assertSame(8, in.peek().getStart().getIndex());
        assertSame(1, in.peek().getStart().getColumn());
        assertSame(2, in.peek().getStart().getLine());

        assertSame(8, in.peek().getEnd().getIndex());
        assertSame(1, in.peek().getEnd().getColumn());
        assertSame(2, in.peek().getEnd().getLine());

        in.skip();

        assertSame(TokenType.ASSIGN, in.peek().getType());
        assertSame(null, in.peek().getValue());

        assertSame(11, in.peek().getStart().getIndex());
        assertSame(4, in.peek().getStart().getColumn());
        assertSame(2, in.peek().getStart().getLine());

        assertSame(11, in.peek().getEnd().getIndex());
        assertSame(4, in.peek().getEnd().getColumn());
        assertSame(2, in.peek().getEnd().getLine());

        in.skip();

        assertSame(TokenType.LINE_SEPARATOR, in.peek().getType());
        assertSame(null, in.peek().getValue());

        assertSame(13, in.peek().getStart().getIndex());
        assertSame(1, in.peek().getStart().getColumn());
        assertSame(3, in.peek().getStart().getLine());

        assertSame(13, in.peek().getEnd().getIndex());
        assertSame(1, in.peek().getEnd().getColumn());
        assertSame(3, in.peek().getEnd().getLine());

        in.skip();

        assertSame(TokenType.INTEGER, in.peek().getType());
        assertEquals("10", in.peek().getValue());

        assertSame(14, in.peek().getStart().getIndex());
        assertSame(2, in.peek().getStart().getColumn());
        assertSame(3, in.peek().getStart().getLine());

        assertSame(15, in.peek().getEnd().getIndex());
        assertSame(3, in.peek().getEnd().getColumn());
        assertSame(3, in.peek().getEnd().getLine());

        in.skip();

        assertSame(TokenType.SEMICOLON, in.peek().getType());
        assertSame(null, in.peek().getValue());

        assertSame(16, in.peek().getStart().getIndex());
        assertSame(4, in.peek().getStart().getColumn());
        assertSame(3, in.peek().getStart().getLine());

        assertSame(16, in.peek().getEnd().getIndex());
        assertSame(4, in.peek().getEnd().getColumn());
        assertSame(3, in.peek().getEnd().getLine());

    }

}
