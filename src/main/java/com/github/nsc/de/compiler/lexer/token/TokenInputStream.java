package com.github.nsc.de.compiler.lexer.token;

import java.util.Arrays;
import java.util.List;

public class TokenInputStream {

    /**
     * The source (mostly filename) of the {@link TokenInputStream}
     */
    private final String source;

    /**
     * The tokens that are contained in the {@link TokenInputStream}
     */
    private final Token[] tokens;

    /**
     * The position that the TokenInputStream is actually at
     */
    private int position;

    /**
     * Create a {@link TokenInputStream} giving the {@link TokenInputStream#source}, {@link TokenInputStream#tokens}
     * and {@link TokenInputStream#position}
     *
     * @param source value for field {@link TokenInputStream#source} (The source (mostly file) of the tokens)
     * @param tokens value for field {@link TokenInputStream#tokens} (The tokens that the {@link TokenInputStream} should give)
     * @param position value for field {@link TokenInputStream#position} (The starting position of the {@link TokenInputStream})
     *
     * @author Nicolas Schmidt
     */
    public TokenInputStream(String source, Token[] tokens, int position) {
        this.source = source;
        this.tokens = tokens;
        this.position = position;
    }

    /**
     * Create a {@link TokenInputStream} giving the {@link TokenInputStream#source} and {@link TokenInputStream#tokens}
     *
     * @param source value for field {@link TokenInputStream#source} (The source (mostly file) of the tokens)
     * @param tokens value for field {@link TokenInputStream#tokens} (The tokens that the {@link TokenInputStream} should give)
     *
     * @author Nicolas Schmidt
     */
    public TokenInputStream(String source, Token[] tokens) {
        this(source, tokens, -1);
    }

    /**
     * Create a {@link TokenInputStream} giving the {@link TokenInputStream#source}, {@link TokenInputStream#tokens}
     * and {@link TokenInputStream#position}
     *
     * @param source value for field {@link TokenInputStream#source} (The source (mostly file) of the tokens)
     * @param tokens value for field {@link TokenInputStream#tokens} (The tokens that the {@link TokenInputStream} should give)
     * @param position value for field {@link TokenInputStream#position} (The starting position of the {@link TokenInputStream})
     *
     * @author Nicolas Schmidt
     */
    public TokenInputStream(String source, List<Token> tokens, int position) {
        this(source, tokens.toArray(new Token[0]), position);
    }

    /**
     * Create a {@link TokenInputStream} giving the {@link TokenInputStream#source} and {@link TokenInputStream#tokens}
     *
     * @param source value for field {@link TokenInputStream#source} (The source (mostly file) of the tokens)
     * @param tokens value for field {@link TokenInputStream#tokens} (The tokens that the {@link TokenInputStream} should give)
     *
     * @author Nicolas Schmidt
     */
    public TokenInputStream(String source, List<Token> tokens) {
        this(source, tokens.toArray(new Token[0]));
    }

    /**
     * Getter for {@link TokenInputStream#source} (The source (mostly file) of the tokens)
     *
     * @return the source (mostly file) of the {@link TokenInputStream}
     *
     * @author Nicolas Schmidt
     */
    public String getSource() {
        return source;
    }

    /**
     * Getter for {@link TokenInputStream#position} (The actual position of the {@link TokenInputStream})
     *
     * @return the actual position of the {@link TokenInputStream}
     *
     * @author Nicolas Schmidt
     */
    public int getPosition() {
        return position;
    }

    /**
     * Getter for {@link TokenInputStream#tokens} (Gives back an array of {@link Token}s)
     *
     * @return The {@link Token}s of the {@link TokenInputStream}
     *
     * @author Nicolas Schmidt
     */
    public Token[] getTokens() {
        return tokens;
    }

    /**
     * Set the position of the {@link TokenInputStream}
     *
     * @param position the new position
     *
     * @author Nicolas Schmidt
     */
    public void setPosition(int position) {
        testPosition(position);
        this.position = position;
    }

    /**
     * Get a specific token from the {@link TokenInputStream}
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author Nicolas Schmidt
     */
    public Token get(int position) {
        testPosition(position);
        return this.tokens[position];
    }

    /**
     * Checks if the {@link TokenInputStream} has left a specific number of tokens
     *
     * @param num the number of tokens to check
     * @return has the {@link TokenInputStream} left the given amount of {@link Token}s?
     *
     * @author Nicolas Schmidt
     */
    public boolean has(int num) {
        if(num < 1) throw new Error("You should only give positive numbers to this function");
        return this.position + num < this.tokens.length;
    }

    /**
     * Checks if the {@link TokenInputStream} has another token left
     *
     * @return has the {@link TokenInputStream} another {@link Token} left?
     *
     * @author Nicolas Schmidt
     */
    public boolean hasNext() {
        // We could also use has(1) here, but for performance-reasons
        // that here should be better
        return this.position + 1 < this.tokens.length;
    }

    /**
     * Returns the next token of the {@link TokenInputStream}
     *
     * @return the next token
     *
     * @author Nicolas Schmidt
     */
    public Token next() {
        skip();
        return actual();
    }

    /**
     * Skips the next token of the {@link TokenInputStream}
     *
     * @return The {@link TokenInputStream} itself so you can do an operation directly after the call
     *
     * @author Nicolas Schmidt
     */
    public TokenInputStream skip() {
        if (hasNext()) this.position++;
        else throw new Error("Input already finished");
        return this;
    }

    /**
     * Skips all ignorable tokens of the {@link TokenInputStream}
     * _(ignorable tokens are tokens that can have a function in the parser, but can also be ignored (Line-Separators))_
     *
     * @return The {@link TokenInputStream} itself so you can do an operation directly after the call
     *
     * @author Nicolas Schmidt
     */
    public TokenInputStream skipIgnorable() {
        while(this.hasNext() && this.peek().getType() == TokenType.LINE_SEPARATOR) this.skip();
        return this;
    }

    /**
     * Returns the actual token of the {@link TokenInputStream} without skipping
     *
     * @return The actual {@link Token}
     *
     * @author Nicolas Schmidt
     */
    public Token actual() {
        return this.tokens[this.position];
    }

    /**
     * Returns the next token of the {@link TokenInputStream} without skipping
     *
     * @return The next {@link Token}
     *
     * @author Nicolas Schmidt
     */
    public Token peek() {
        if (this.position + 1 < this.tokens.length) return this.tokens[position + 1];
        else throw new Error("Not enough tokens left");
    }

    /**
     * Returns one of the following tokens (described by the num argument) of the {@link TokenInputStream} without skipping
     *
     * @return The expected {@link Token}
     *
     * @author Nicolas Schmidt
     */
    public Token peek(int num) {
        if(num < 1) throw new Error("The argument for the peek function should be a number that is bigger than 0.");
        if (this.position + num < this.tokens.length) return this.tokens[position + num];
        else throw new Error("Not enough tokens left");
    }

    @Override
    public String toString() {
        return "TokenInputStream{" +
                "source='" + source + '\'' +
                ", tokens=" + Arrays.toString(tokens) +
                ", position=" + position +
                '}';
    }

    private void testPosition(int position) {
        if(position < 0) throw new Error("Position mustn't be smaller than 0.");
        if(position >= this.getTokens().length)
            throw new Error(String.format("The given position is to high. The maximum value is %d, but given was %d", this.getTokens().length - 1, position));
    }
}
