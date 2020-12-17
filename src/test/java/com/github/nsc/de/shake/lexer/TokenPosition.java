package com.github.nsc.de.shake.lexer;

import com.github.nsc.de.shake.lexer.characterinputstream.CharacterInputStream;
import com.github.nsc.de.shake.lexer.characterinputstream.SourceCharacterInputStream;
import com.github.nsc.de.shake.lexer.characterinputstream.position.Position;
import com.github.nsc.de.shake.lexer.token.TokenInputStream;
import com.github.nsc.de.shake.lexer.token.TokenType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TokenPosition {

    @Test
    public void testGetBasicPosition() {

        CharacterInputStream chars = new SourceCharacterInputStream("<tests>", "var test = 10;");
        Lexer lexer = new Lexer(chars);
        TokenInputStream in = lexer.makeTokens();

        assertSame(TokenType.KEYWORD_VAR, in.peek().getType());
        assertSame(null, in.peek().getValue());

        Position start = in.getMap().resolve(in.peek().getStart());
        Position end = in.getMap().resolve(in.peek().getEnd());

        assertSame(0, start.getIndex());
        assertSame(1, start.getColumn());
        assertSame(1, start.getLine());

        assertSame(2, end.getIndex());
        assertSame(3, end.getColumn());
        assertSame(1, end.getLine());

        in.skip();

        assertSame(TokenType.IDENTIFIER, in.peek().getType());
        assertEquals("test", in.peek().getValue());

        start = in.getMap().resolve(in.peek().getStart());
        end = in.getMap().resolve(in.peek().getEnd());

        assertSame(4, start.getIndex());
        assertSame(5, start.getColumn());
        assertSame(1, start.getLine());

        assertSame(7, end.getIndex());
        assertSame(8, end.getColumn());
        assertSame(1, end.getLine());

        in.skip();

        assertSame(TokenType.ASSIGN, in.peek().getType());
        assertSame(null, in.peek().getValue());

        start = in.getMap().resolve(in.peek().getStart());
        end = in.getMap().resolve(in.peek().getEnd());

        assertSame(9, start.getIndex());
        assertSame(10, start.getColumn());
        assertSame(1, start.getLine());

        assertSame(9, end.getIndex());
        assertSame(10, end.getColumn());
        assertSame(1, end.getLine());

        in.skip();

        assertSame(TokenType.INTEGER, in.peek().getType());
        assertEquals("10", in.peek().getValue());

        start = in.getMap().resolve(in.peek().getStart());
        end = in.getMap().resolve(in.peek().getEnd());

        assertSame(11, start.getIndex());
        assertSame(12, start.getColumn());
        assertSame(1, start.getLine());

        assertSame(12, end.getIndex());
        assertSame(13, end.getColumn());
        assertSame(1, end.getLine());

        in.skip();

        assertSame(TokenType.SEMICOLON, in.peek().getType());
        assertSame(null, in.peek().getValue());

        start = in.getMap().resolve(in.peek().getStart());
        end = in.getMap().resolve(in.peek().getEnd());

        assertSame(13, start.getIndex());
        assertSame(14, start.getColumn());
        assertSame(1, start.getLine());

        assertSame(13, end.getIndex());
        assertSame(14, end.getColumn());
        assertSame(1, end.getLine());

    }

    @Test
    public void testGetMultiLinePosition() {

        CharacterInputStream chars = new SourceCharacterInputStream("<tests>", "var test\n  = \n10;");
        Lexer lexer = new Lexer(chars);
        TokenInputStream in = lexer.makeTokens();

        assertSame(TokenType.KEYWORD_VAR, in.peek().getType());
        assertSame(null, in.peek().getValue());

        Position start = in.getMap().resolve(in.peek().getStart());
        Position end = in.getMap().resolve(in.peek().getEnd());

        assertSame(0, start.getIndex());
        assertSame(1, start.getColumn());
        assertSame(1, start.getLine());

        assertSame(2, end.getIndex());
        assertSame(3, end.getColumn());
        assertSame(1, end.getLine());

        in.skip();

        assertSame(TokenType.IDENTIFIER, in.peek().getType());
        assertEquals("test", in.peek().getValue());

        start = in.getMap().resolve(in.peek().getStart());
        end = in.getMap().resolve(in.peek().getEnd());

        assertSame(4, start.getIndex());
        assertSame(5, start.getColumn());
        assertSame(1, start.getLine());

        assertSame(7, end.getIndex());
        assertSame(8, end.getColumn());
        assertSame(1, end.getLine());

        in.skip();

        assertSame(TokenType.LINE_SEPARATOR, in.peek().getType());
        assertSame(null, in.peek().getValue());

        start = in.getMap().resolve(in.peek().getStart());
        end = in.getMap().resolve(in.peek().getEnd());

        assertSame(8, start.getIndex());
        assertSame(0, start.getColumn());
        assertSame(2, start.getLine());

        assertSame(8, end.getIndex());
        assertSame(0, end.getColumn());
        assertSame(2, end.getLine());

        in.skip();

        assertSame(TokenType.ASSIGN, in.peek().getType());
        assertSame(null, in.peek().getValue());

        start = in.getMap().resolve(in.peek().getStart());
        end = in.getMap().resolve(in.peek().getEnd());

        assertSame(11, start.getIndex());
        assertSame(3, start.getColumn());
        assertSame(2, start.getLine());

        assertSame(11, end.getIndex());
        assertSame(3, end.getColumn());
        assertSame(2, end.getLine());

        in.skip();

        assertSame(TokenType.LINE_SEPARATOR, in.peek().getType());
        assertSame(null, in.peek().getValue());

        start = in.getMap().resolve(in.peek().getStart());
        end = in.getMap().resolve(in.peek().getEnd());

        assertSame(13, start.getIndex());
        assertSame(1, start.getColumn());
        assertSame(3, start.getLine());

        assertSame(13, end.getIndex());
        assertSame(1, end.getColumn());
        assertSame(3, end.getLine());

        in.skip();

        assertSame(TokenType.INTEGER, in.peek().getType());
        assertEquals("10", in.peek().getValue());

        start = in.getMap().resolve(in.peek().getStart());
        end = in.getMap().resolve(in.peek().getEnd());

        assertSame(14, start.getIndex());
        assertSame(2, start.getColumn());
        assertSame(3, start.getLine());

        assertSame(15, end.getIndex());
        assertSame(3, end.getColumn());
        assertSame(3, end.getLine());

        in.skip();

        assertSame(TokenType.SEMICOLON, in.peek().getType());
        assertSame(null, in.peek().getValue());

        start = in.getMap().resolve(in.peek().getStart());
        end = in.getMap().resolve(in.peek().getEnd());

        assertSame(16, start.getIndex());
        assertSame(4, start.getColumn());
        assertSame(3, start.getLine());

        assertSame(16, end.getIndex());
        assertSame(4, end.getColumn());
        assertSame(3, end.getLine());

    }

}
