package com.github.nsc.de.compiler.lexer.characterinputstream;

import com.github.nsc.de.compiler.lexer.Position;

/**
 * A {@link CharacterInputStream} provides the characters for the {@link com.github.nsc.de.compiler.lexer.Lexer}
 *
 * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
 *
 * @see StringCharacterInputStream
 */
public interface CharacterInputStream {

    /**
     * Returns the source (mostly file) of the {@link CharacterInputStream}
     *
     * @return the source (mostly file) of the {@link CharacterInputStream}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    String getSource();

    /**
     * Returns the chars of the {@link CharacterInputStream}
     *
     * @return the chars of the {@link CharacterInputStream}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    char[] getContent();

    /**
     * Returns the actual position of the {@link CharacterInputStream}
     *
     * @return the actual position of the {@link CharacterInputStream}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    Position getPosition();

    /**
     * Checks if the {@link CharacterInputStream} has a next character left
     *
     * @return if the {@link CharacterInputStream} has a next character
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    boolean hasNext();

    /**
     * Checks if the {@link CharacterInputStream} has a given number of characters left
     *
     * @param number the num of characters to check
     * @return if the {@link CharacterInputStream} has a given number of characters left
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    boolean has(int number);

    /**
     * Returns the next character and continues to the next token
     *
     * @return the next character
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    char next();

    /**
     * Skips a given number of characters
     *
     * @param number the number of characters to skip
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    void skip(int number);

    /**
     * Skips the next character
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    void skip();

    /**
     * Returns the actual character (the same as returned by {@link #next()} when used before)
     *
     * @return the actual character
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    char actual();

    /**
     * Gives back the next character without skipping
     *
     * @return the next character
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    default char peek() { return peek(1); }

    /**
     * Gives back a character of the {@link CharacterInputStream}
     * (relative to the actual position)
     *
     * @param num the position to get
     * @return the character at the requested position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    char peek(int num);

    /**
     * Gives back a part of the {@link CharacterInputStream} as string
     * (relative to the actual position)
     *
     * @param from the starting position of the string to get
     * @param to the end position of the string to get
     * @return the character at the requested position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    String peek(int from, int to);

}