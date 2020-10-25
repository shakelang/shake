package com.github.nsc.de.compiler.lexer.characterinputstream;

import com.github.nsc.de.compiler.lexer.Position;

public interface CharacterInputStream {

    /**
     * Returns the source (mostly file) of the {@link CharacterInputStream}
     *
     * @return the source (mostly file) of the {@link CharacterInputStream}
     *
     * @author Nicolas Schmidt
     */
    String getSource();

    /**
     * Returns the chars of the {@link CharacterInputStream}
     *
     * @return the chars of the {@link CharacterInputStream}
     *
     * @author Nicolas Schmidt
     */
    char[] getContent();

    /**
     * Returns the actual position of the {@link CharacterInputStream}
     *
     * @return the actual position of the {@link CharacterInputStream}
     *
     * @author Nicolas Schmidt
     */
    Position getPosition();

    /**
     * Checks if the {@link CharacterInputStream} has a next character left
     *
     * @return if the {@link CharacterInputStream} has a next character
     *
     * @author Nicolas Schmidt
     */
    boolean hasNext();

    /**
     * Checks if the {@link CharacterInputStream} has a given number of characters left
     *
     * @param number the num of characters to check
     * @return if the {@link CharacterInputStream} has a given number of characters left
     *
     * @author Nicolas Schmidt
     */
    boolean has(int number);

    /**
     * Returns the next character and continues to the next token
     *
     * @return the next character
     *
     * @author Nicolas Schmidt
     */
    char next();

    /**
     * Skips a given number of characters
     *
     * @param number the number of characters to skip
     *
     * @author Nicolas Schmidt
     */
    void skip(int number);

    /**
     * Skips the next character
     *
     * @author Nicolas Schmidt
     */
    void skip();

    /**
     * Returns the actual character (the same as returned by {@link #next()} when used before)
     *
     * @return the actual character
     *
     * @author Nicolas Schmidt
     */
    char actual();

    /**
     * Gives back the next character without skipping
     *
     * @return the next character
     *
     * @author Nicolas Schmidt
     */
    default char peek() { return peek(1); }

    /**
     * Gives back a character of the {@link CharacterInputStream}
     * (relative to the actual position)
     *
     * @param num the position to get
     * @return the character at the requested position
     *
     * @author Nicolas Schmidt
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
     * @author Nicolas Schmidt
     */
    String peek(int from, int to);

}