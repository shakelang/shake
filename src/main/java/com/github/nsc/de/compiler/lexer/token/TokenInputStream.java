package com.github.nsc.de.compiler.lexer.token;

import java.util.Arrays;
import java.util.List;

public class TokenInputStream {

    private final String source;
    private final Token[] tokens;
    private int position;

    public TokenInputStream(String source, Token[] tokens, int position) {
        this.source = source;
        this.tokens = tokens;
        this.position = position;
    }

    public TokenInputStream(String source, Token[] tokens) {
        this(source, tokens, -1);
    }

    public TokenInputStream(String source, List<Token> tokens, int position) {
        this(source, tokens.toArray(new Token[0]), position);
    }

    public TokenInputStream(String source, List<Token> tokens) {
        this(source, tokens.toArray(new Token[0]));
    }

    public String getSource() {
        return source;
    }

    public int getPosition() {
        return position;
    }

    public Token[] getTokens() {
        return tokens;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Token get(int position) { return this.tokens[position]; }

    public boolean hasNext() {
        return this.peek() != null;
    }

    public Token next() {
        skip();
        return actual();
    }

    public TokenInputStream skip() {
        if (hasNext()) this.position++;
        else throw new Error("Input already finished");
        return this;
    }

    public Token actual() {
        return this.tokens[this.position];
    }

    public Token peek() {
        return peek(1);
    }

    public Token peek(int num) {
        if (this.position + num < this.tokens.length) return this.tokens[position + num];
        else return null;
    }

    @Override
    public String toString() {
        return "TokenInputStream{" +
                "source='" + source + '\'' +
                ", tokens=" + Arrays.toString(tokens) +
                ", position=" + position +
                '}';
    }
}
