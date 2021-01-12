package com.github.nsc.de.shake.lexer.token;

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;
import com.github.nsc.de.shake.util.ArrayUtil;

import java.util.Arrays;


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
    private final byte[] tokens;

    /**
     * The values for the tokens that have values in the {@link TokenInputStream}
     */
    private final String[] values;

    /**
     * The positions of the tokens of the {@link TokenInputStream}
     * (We just store the end-indexes of each token. We can calculate the start-index out of the token type &amp; value)
     */
    private final int[] positions;

    /**
     * The position that the TokenInputStream is actually at
     */
    private int position;

    /**
     * The value-position of the {@link TokenInputStream}
     * (This variable we just need for performance-reasons, because don't have to search all the tokens again to get the
     * value-index of the actual position)
     */
    private int valuePosition = -1;

    /**
     * The map for the token-positions
     * We have this map to resolve the column / line of an index. This is useful for error-generation.
     */
    private final PositionMap map;

    /**
     * Create a {@link TokenInputStream} giving the {@link TokenInputStream#source} and {@link TokenInputStream#tokens}
     *
     * @param source value for field {@link TokenInputStream#source} (The source (mostly file) of the tokens)
     * @param tokens value for field {@link TokenInputStream#tokens} (The tokens that the {@link TokenInputStream} should give)
     * @param values The values for the tokens that have values in the {@link TokenInputStream}
     * @param positions The positions of the tokens of the {@link TokenInputStream}
     *                  (We just store the end-indexes of each token. We can calculate the start-index out of the token type &amp; value)
     * @param map  value for field {@link TokenInputStream#map} (The position map of the {@link TokenInputStream})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public TokenInputStream(String source, byte[] tokens, String[] values, int[] positions, PositionMap map) {
        // set all the fields (position default value: -1)
        this.source = source;
        this.tokens = tokens;
        this.values = values;
        this.positions = positions;
        this.map = map;
        this.position = -1;
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

        // Create an array of tokens for all the tokens
        Token[] tokens = new Token[this.tokens.length];

        // The positions
        int valuePosition = -1;

        // loop over the tokens array and fill it.
        for(int i = 0; i < tokens.length; i++) {
            if(TokenType.hasValue(this.tokens[i])) {
                tokens[i] = new Token(this.tokens[i],
                        this.values[++valuePosition],
                        this.positions[i] - TokenType.getTokenLength(this.tokens[i], this.values[valuePosition]),
                        this.positions[i]);
            }
            else tokens[i] = new Token(this.tokens[i],
                    this.positions[i] - TokenType.getTokenLength(this.tokens[i]),
                    this.positions[i]);
        }
        return tokens;
    }

    /**
     * Getter for {@link TokenInputStream#map} (Gives back the map for the positions)
     *
     * @return The {@link PositionMap} of the {@link TokenInputStream} (for resolving the token-positions
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public PositionMap getMap() {
        // just return the position-map
        return map;
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

        // resolve value-position
        this.valuePosition = -1;
        for(int i = 0; i <= position; i++) if(TokenType.hasValue(this.tokens[i])) this.valuePosition++;
    }

    /**
     * Get a specific token from the {@link TokenInputStream}
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Deprecated
    public Token get(int position) {
        // test the position (throw error if a wrong position is provided)
        // and return the token at the position if no error is thrown
        testPosition(position);
        return new Token(getType(position), getValue(position), getStart(position), getEnd(position));
    }

    /**
     * Get the type of specific token from the {@link TokenInputStream} by it's position
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public byte getType(int position) {
        // test the position (throw error if a wrong position is provided)
        // and return the token-type at the position if no error is thrown
        testPosition(position);
        return this.tokens[position];
    }

    /**
     * Get the start of specific token from the {@link TokenInputStream} by it's position
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @deprecated Avoid using this method and use {@link #peekStart()}, {@link #actualStart()} instead. This method
     *             is fully functional, but it uses {@link #getValue(int)}, which has to loop over the complete
     *             {@link #tokens} array again up to the given token position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Deprecated
    public int getStart(int position) {
        // test the position (throw error if a wrong position is provided)
        // and return the token-start at the position if no error is thrown
        testPosition(position);
        return this.getEnd(position) + 1 - (this.getHasValue(position) ?
                TokenType.getTokenLength(getType(position), getValue(position)) :
                TokenType.getTokenLength(getType(position)));
    }

    /**
     * Get the end of specific token from the {@link TokenInputStream} by it's position
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public int getEnd(int position) {
        // test the position (throw error if a wrong position is provided)
        // and return the token-end at the position if no error is thrown
        testPosition(position);
        return this.positions[position];
    }

    /**
     * Get the value of specific token from the {@link TokenInputStream} by it's position
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @deprecated This method is fully working, but it will decrease the performance of the program, because it has to
     *             loop over all the {@link #tokens} again to find the value's index that should be returned
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Deprecated
    public String getValue(int position) {

        // We start the valueIndex at 0
        int valueIndex = 0;

        /// loop up to the given position and count the valued tokens before (increase the valueIndex)
        for(int i = 0; i < position; i++) if(TokenType.hasValue(this.tokens[i])) valueIndex++;

        // return the value at the given position
        return this.values[valueIndex];

    }

    /**
     * Check if specific token from the {@link TokenInputStream} has a value (by it's position)
     *
     * @param position the position to get
     * @return the token at the given position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public boolean getHasValue(int position) {

        // Check, if the token at the given position has a value
        return TokenType.hasValue(this.tokens[position]);

    }

    /**
     * Checks if the {@link TokenInputStream} has left a given number of tokens
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
     * Checks if the {@link TokenInputStream} has a token left
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
     * Returns the next token of the {@link TokenInputStream} (and skips)
     *
     * @return the next token
     * @deprecated If you don't need a {@link Token}, it is advised to use {@link #nextType()} &amp; {@link #nextValue()}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Deprecated
    public Token next() {
        // skip to next token and then return the actual token
        skip();
        return actual();
    }

    /**
     * Returns the type of the next token of the {@link TokenInputStream} (and skips)
     *
     * @return the next token
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public byte nextType() {
        // skip to next token and then return the actual token
        skip();
        return actualType();
    }

    /**
     * Returns the next token of the {@link TokenInputStream}
     *
     * @return the next token
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public String nextValue() {
        // skip to next token and then return the actual token
        skip();
        return actualValue();
    }

    /**
     * Skips the next token of the {@link TokenInputStream}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public void skip() {
        // Check if the input has a next token. If so then increase the position. If not throw an error
        // also increase the valuePosition if the next token has a value
        if (hasNext()) {
            if (TokenType.hasValue(tokens[++this.position])) valuePosition++;
        }
        else throw new Error("Input already finished");
    }

    /**
     * Skips all ignorable tokens of the {@link TokenInputStream}
     *_(ignorable tokens are tokens that can have a function in the parser, but can also be ignored
     * ({@link TokenType#LINE_SEPARATOR}))
     *
     * @return The {@link TokenInputStream} itself so you can do an operation directly after the call
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public TokenInputStream skipIgnorable() {
        // As long as the next token is a line-separator execute skip
        while(this.hasNext() && this.peekType() == TokenType.LINE_SEPARATOR) {

            // We could also use skip here, but for performance-reasons
            // that here should be better
            // This is possible because i already checked if there is a next
            // token before in the while statement and a line-separator token
            // has no value.
            this.position++;
        }
        return this;
    }

    /**
     * Returns the actual {@link Token} of the {@link TokenInputStream}
     *
     * @return The actual {@link Token}
     * @deprecated Only use, if you need a complete {@link Token}. Use {@link #actualType()}, {@link #actualStart()},
     * {@link #actualEnd()}, {@link #actualValue()} &amp; {@link #actualHasValue()} instead, if you just need one of these!
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Deprecated
    public Token actual() {
        // Just return the actual token
        // That is possible, because the position should never get
        // bigger than the token length.
        return new Token(this.actualType(), this.actualValue(), this.actualStart(), this.actualEnd());
    }

    /**
     * Returns the type of the actual token of the {@link TokenInputStream}
     *
     * @return The actual token-type
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public byte actualType() {
        // Just return the actual token-type
        // That is possible, because the position should never get
        // bigger than the token length.
        return this.tokens[this.position];
    }

    /**
     * Returns the start of the actual token of the {@link TokenInputStream}
     *
     * @return The actual token-start
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public int actualStart() {
        // Just return the actual token-start
        // That is possible, because the position should never get
        // bigger than the token length.
        return this.actualEnd() + 1 - (this.actualHasValue() ?
                TokenType.getTokenLength(actualType(), actualValue()) :
                TokenType.getTokenLength(actualType()));
    }

    /**
     * Returns the end of the actual token of the {@link TokenInputStream}
     *
     * @return The actual token-end
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public int actualEnd() {
        // Just return the actual token-end
        // That is possible, because the position should never get
        // bigger than the token length.
        return this.positions[this.position];
    }

    /**
     * Returns the value of the actual token of the {@link TokenInputStream}
     *
     * @return The actual token-value
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public String actualValue() {
        // Just return the actual token-value
        // That is possible, because the position should never get
        // bigger than the token length.
        return TokenType.hasValue(this.tokens[this.position]) ? this.values[this.valuePosition] : null;
    }

    /**
     * Checks if the actual token of the {@link TokenInputStream} has a value
     *
     * @return Has the actual token a value?
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public boolean actualHasValue() {
        // just return if the actual token-type
        return TokenType.hasValue(this.tokens[this.position]);
    }

    /**
     * Returns the next {@link Token} of the {@link TokenInputStream} without skipping
     *
     * @return The next {@link Token}
     * @deprecated Only use, if you need a complete {@link Token}. Use {@link #peekType()}, {@link #peekStart()},
     * {@link #peekEnd()}, {@link #peekValue()} &amp; {@link #peekHasValue()} instead, if you just need one of these!
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Deprecated
    public Token peek() {
        if (this.position + 1 < this.tokens.length) return new Token(
                this.peekType(),
                this.peekValue(),
                this.peekStart(),
                this.peekEnd());
        else throw new Error("Not enough tokens left");
    }

    /**
     * Returns the type of the next token of the {@link TokenInputStream} without skipping
     *
     * @return The next token-type
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public byte peekType() {
        if (this.position + 1 < this.tokens.length) return this.tokens[position + 1];
        else throw new Error("Not enough tokens left");
    }

    /**
     * Returns the start of the next token of the {@link TokenInputStream} without skipping
     *
     * @return The next token-start
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public int peekStart() {
        if (this.position + 1 < this.tokens.length) return this.peekEnd() + 1 - (this.peekHasValue() ?
                        TokenType.getTokenLength(peekType(), peekValue()) :
                        TokenType.getTokenLength(peekType()));
        else throw new Error("Not enough tokens left");
    }

    /**
     * Returns the end of the next token of the {@link TokenInputStream} without skipping
     *
     * @return The next token-end
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public int peekEnd() {
        if (this.position + 1 < this.tokens.length) return this.positions[position + 1];
        else throw new Error("Not enough tokens left");
    }

    /**
     * Returns the value of the next token of the {@link TokenInputStream} without skipping
     *
     * @return The next token-value
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public String peekValue() {
        return peekHasValue() ?
                this.values[TokenType.hasValue(this.actualType()) ? this.valuePosition + 2 : this.valuePosition + 1] :
                null;
    }


    /**
     * Checks if the next token of the {@link TokenInputStream} has a value without skipping
     *
     * @return The next {@link Token}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public boolean peekHasValue() {
        if (this.position + 1 < this.tokens.length) return TokenType.hasValue(this.peekType());
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
                ", tokens=" + Arrays.toString(ArrayUtil.map(tokens, new String[tokens.length], TokenType::getName)) +
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
