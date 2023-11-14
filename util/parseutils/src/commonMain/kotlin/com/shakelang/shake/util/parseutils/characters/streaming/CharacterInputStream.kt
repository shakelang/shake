package com.shakelang.shake.util.parseutils.characters.streaming

import com.shakelang.shake.util.parseutils.characters.position.PositionMaker
import com.shakelang.shake.util.parseutils.characters.source.CharacterSource

/**
 * A [CharacterInputStream] provides the characters for a Lexer
 * @see SourceCharacterInputStream
 *
 * @since 0.1.0
 * @version 0.2.1
 */
interface CharacterInputStream {
    /**
     * Returns the source of the [CharacterInputStream]
     * @return the source (mostly file) of the [CharacterInputStream]
     *
     * @see CharacterSource
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val source: CharacterSource

    /**
     * Returns the chars of the [CharacterInputStream]
     * @return the chars of the [CharacterInputStream]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val content: CharArray

    /**
     * Returns the actual position of the [CharacterInputStream]
     * @return the actual position of the [CharacterInputStream]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val position: Int

    /**
     * Returns the actual position-maker of the [CharacterInputStream]
     * @return the actual position-maker of the [CharacterInputStream]
     *
     * @see PositionMaker
     * @since 0.1.0
     * @version 0.2.1
     */
    val positionMaker: PositionMaker

    /**
     * Checks if the [CharacterInputStream] has a next character left
     * @return if the [CharacterInputStream] has a next character
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    operator fun hasNext(): Boolean

    /**
     * Checks if the [CharacterInputStream] has a given number of characters left
     * @param number the num of characters to check
     * @return if the [CharacterInputStream] has a given number of characters left
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun has(number: Int): Boolean

    /**
     * Returns the next character and continues to the next token
     * @return the next character
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    operator fun next(): Char

    /**
     * Skips a given number of characters
     * @param number the number of characters to skip
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun skip(number: Int)

    /**
     * Skips the next character
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun skip()

    /**
     * Returns the actual character (the same as returned by [.next] when used before)
     * @return the actual character
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun actual(): Char

    /**
     * Gives back the next character without skipping
     * @return the next character
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun peek(): Char

    /**
     * Gives back a character of the [CharacterInputStream]
     * (relative to the actual position)
     * @param num the position to get
     * @return the character at the requested position
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun peek(num: Int): Char

    /**
     * Gives back a part of the [CharacterInputStream] as string
     * (relative to the actual position)
     *
     * @param from the starting position of the string to get
     * @param to the end position of the string to get
     * @return the character at the requested position
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun peek(from: Int, to: Int): String
}
