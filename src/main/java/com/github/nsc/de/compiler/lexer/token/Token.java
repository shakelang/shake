package com.github.nsc.de.compiler.lexer.token;

public class Token {

    private final TokenType type;
    private final Object value;

    public Token(TokenType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Token(TokenType type) {
        this.type = type;
        this.value = null;
    }

    public TokenType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value != null ? "Token{" + "type=" + type + ", value=" + value + '}' : "Token{" + "type=" + type + '}';
    }
}
