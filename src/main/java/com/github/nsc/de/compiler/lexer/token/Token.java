package com.github.nsc.de.compiler.lexer.token;

import com.github.nsc.de.compiler.lexer.Position;

import java.util.Objects;

public class Token {

    private final TokenType type;
    private final String value;
    private final Position start;
    private final Position end;

    public Token(TokenType type, String value, Position start, Position end) {
        this.type = type;
        this.value = value;
        this.start = start;
        this.end = end;
    }

    public Token(TokenType type, String value, Position start) { this(type, value, start, start); }
    public Token(TokenType type, Position start, Position end) { this(type, null, start, end); }
    public Token(TokenType type, Position start) { this(type, null, start, start); }

    public TokenType getType() { return type; }
    public String getValue() { return value; }
    public Position getStart() { return start; }
    public Position getEnd() { return end; }
    public int getBefore() { return start.getIndex(); }
    public int getAfter() { return end.getIndex() + 1; }

    @Override
    public String toString() {
        return start == end ? (value != null ? "" +
                "Token{" + "type=" + type + ", value=" + value + ", position=" + start + '}' :
                "Token{" + "type=" + type +  ", position=" + start + '}')
              : (value != null ? "" +
                "Token{" + "type=" + type + ", value=" + value + ", start=" + start + ", end=" + end + '}' :
                "Token{" + "type=" + type +  ", position=" + start + ", end=" + end + '}');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return type == token.type &&
                Objects.equals(value, token.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }
}
