package com.github.nsc.de.shake.lexer.token;

import java.util.Arrays;
import java.util.List;


/**
 * A {@link TokenInputStream} provides the {@link Token}s for a {@link com.github.nsc.de.shake.parser.Parser}. It is
 * created by the {@link com.github.nsc.de.shake.lexer.Lexer}
 *
 * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
 */
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public TokenInputStream(String source, Token[] tokens, int position) {
        // set all the fields
        this.source = source;
        this.tokens = tokens;
        this.position = position;

        if(this.position >= this.tokens.length) throw new Error("The position mustn't be out of the given tokens");
        if(position < -1) throw new Error("The position must not be smaller than -1");
    }

    /**
     * Create a {@link TokenInputStream} giving the {@link TokenInputStream#source} and {@link TokenInputStream#tokens}
     *
     * @param source value for field {@link TokenInputStream#source} (The source (mostly file) of the tokens)
     * @param tokens value for field {@link TokenInputStream#tokens} (The tokens that the {@link TokenInputStream} should give)
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public TokenInputStream(String source, Token[] tokens) {
        // set all the fields (position default value: -1)
        this.source = source;
        this.tokens = tokens;
        this.position = -1;
    }

    /**
     * Create a {@link TokenInputStream} giving the {@link TokenInputStream#source}, {@link TokenInputStream#tokens}
     * and {@link TokenInputStream#position}
     *
     * @param source value for field {@link TokenInputStream#source} (The source (mostly file) of the tokens)
     * @param tokens value for field {@link TokenInputStream#tokens} (The tokens that the {@link TokenInputStream} should give)
     * @param position value for field {@link TokenInputStream#position} (The starting position of the {@link TokenInputStream})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public TokenInputStream(String source, List<Token> tokens, int position) {
        // call other constructor with converted list
        this(source, tokens.toArray(new Token[0]), position);
    }

    /**
     * Create a {@link TokenInputStream} giving the {@link TokenInputStream#source} and {@link TokenInputStream#tokens}
     *
     * @param source value for field {@link TokenInputStream#source} (The source (mostly file) of the tokens)
     * @param tokens value for field {@link TokenInputStream#tokens} (The tokens that the {@link TokenInputStream} should give)
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public TokenInputStream(String source, List<Token> tokens) {
        // call other constructor with converted list
        this(source, tokens.toArray(new Token[0]));
    }

    /**
     * Getter for {@link TokenInputStream#source} (The source (mostly file) of the tokens)
     *
     * @return the source (mostly file) of the {@link TokenInputStream}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public String getSource() {
        // just return the source
        return source;
    }

    /**
     * Getter for {@link TokenInputStream#position} (The actual position of the {@link TokenInputStream})
     *
     * @return the actual position of the {@link TokenInputStream}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public int getPosition() {
        // just return the position
        return position;
    }

    /**
     * Getter for {@link TokenInputStream#tokens} (Gives back an array of {@link Token}s)
     *
     * @return The {@link Token}s of the {@link TokenInputStream}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Token[] getTokens() {
        // just return the tokens
        return tokens;
    }

    /**
     * Set the position of the {@link TokenInputStream}
     *
     * @param position the new position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public void setPosition(int position) {
        // test the position (throw error if a wrong position is provided)
        // and set the position if no error is thrown
        testPosition(position);
        this.position = position;
    }

    /**
     * Get a specific token from the {@link TokenInputStream}
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Token get(int position) {
        // test the position (throw error if a wrong position is provided)
        // and return the token at the position if no error is thrown
        testPosition(position);
        return this.tokens[position];
    }

    /**
     * Checks if the {@link TokenInputStream} has left a specific number of tokens
     *
     * @param num the number of tokens to check
     * @return has the {@link TokenInputStream} left the given amount of {@link Token}s?
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public boolean has(int num) {
        // When the number to check is smaller than 0 throw an error
        // in other case just check if the required tokens are left
        if(num < 1) throw new Error("You should only give positive numbers to this function");
        return this.position + num < this.tokens.length;
    }

    /**
     * Checks if the {@link TokenInputStream} has another token left
     *
     * @return has the {@link TokenInputStream} another {@link Token} left?
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Token next() {
        // skip to next token and then return the actual token
        skip();
        return actual();
    }

    /**
     * Skips the next token of the {@link TokenInputStream}
     *
     * @return The {@link TokenInputStream} itself so you can do an operation directly after the call
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public TokenInputStream skip() {
        // Check if the input has a next token. If so then increase the position. If not throw an error
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public TokenInputStream skipIgnorable() {
        // As long as the next token is a line-separator execute skip
        while(this.hasNext() && this.peek().getType() == TokenType.LINE_SEPARATOR) {

            // We could also use skip here, but for performance-reasons
            // that here should be better
            // This is possible because i already checked if there is a next
            // token before in the while statement.
            this.position++;
        }
        return this;
    }

    /**
     * Returns the actual token of the {@link TokenInputStream} without skipping
     *
     * @return The actual {@link Token}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Token actual() {
        // Just return the actual token
        // That is possible, because the position should never get
        // bigger than the token length.
        return this.tokens[this.position];
    }

    /**
     * Returns the next token of the {@link TokenInputStream} without skipping
     *
     * @return The next {@link Token}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Token peek() {
        // We could also use peek(1) here, but for performance-reasons
        // that here should be better
        if (this.position + 1 < this.tokens.length) return this.tokens[position + 1];
        else throw new Error("Not enough tokens left");
    }

    /**
     * Returns one of the following tokens (described by the num argument) of the {@link TokenInputStream} without skipping
     *
     * @return The expected {@link Token}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Token peek(int num) {
        // Throw an error, if the number is smaller than 1
        // Return the asked position if it exists, if not throw an error
        if(num < 1) throw new Error("The argument for the peek function should be a number that is bigger than 0.");
        if (this.position + num < this.tokens.length) return this.tokens[position + num];
        else throw new Error("Not enough tokens left");
    }

    /**
     * Returns a string-representation of the {@link TokenInputStream}
     *
     * @return the string-representation of the {@link TokenInputStream}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public String toString() {
        // Return a string-representation of the input just showing all the sub-elements
        return "TokenInputStream{" +
                "source='" + source + '\'' +
                ", tokens=" + Arrays.toString(tokens) +
                ", position=" + position +
                '}';
    }

    /**
     * This function checks if the given position is a valid position for the {@link TokenInputStream#tokens}-array
     * throws an error if the position is a wrong one
     *
     * @param position the position to check
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    private void testPosition(int position) {
        // If the position is out of range of the tokens array throw an error
        if(position < 0) throw new Error("Position mustn't be smaller than 0.");
        if(position >= this.getTokens().length)
            throw new Error(String.format("The given position is to high. The maximum value is %d, but given was %d", this.getTokens().length - 1, position));
    }
}
