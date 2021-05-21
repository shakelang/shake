package com.github.nsc.de.shake.util.characterinput.characterinputstream

import com.github.nsc.de.shake.util.characterinput.charactersource.CharacterSource
import com.github.nsc.de.shake.util.characterinput.position.PositionMaker

/**
 * A [CharacterInputStream] provides the characters for the [com.github.nsc.de.shake.lexer.Lexer]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 *
 * @see SourceCharacterInputStream
 */
interface CharacterInputStream {
    /**
     * Returns the source of the [CharacterInputStream]
     *
     * @return the source (mostly file) of the [CharacterInputStream]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    val source: CharacterSource

    /**
     * Returns the chars of the [CharacterInputStream]
     *
     * @return the chars of the [CharacterInputStream]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    val content: CharArray

    /**
     * Returns the actual position of the [CharacterInputStream]
     *
     * @return the actual position of the [CharacterInputStream]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    val position: Int

    /**
     * Returns the actual position-maker of the [CharacterInputStream]
     *
     * @return the actual position-maker of the [CharacterInputStream]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    val positionMaker: PositionMaker

    /**
     * Checks if the [CharacterInputStream] has a next character left
     *
     * @return if the [CharacterInputStream] has a next character
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    operator fun hasNext(): Boolean

    /**
     * Checks if the [CharacterInputStream] has a given number of characters left
     *
     * @param number the num of characters to check
     * @return if the [CharacterInputStream] has a given number of characters left
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun has(number: Int): Boolean

    /**
     * Returns the next character and continues to the next token
     *
     * @return the next character
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    operator fun next(): Char

    /**
     * Skips a given number of characters
     *
     * @param number the number of characters to skip
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun skip(number: Int)

    /**
     * Skips the next character
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun skip()

    /**
     * Returns the actual character (the same as returned by [.next] when used before)
     *
     * @return the actual character
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun actual(): Char

    /**
     * Gives back the next character without skipping
     *
     * @return the next character
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun peek(): Char {
        return peek(1)
    }

    /**
     * Gives back a character of the [CharacterInputStream]
     * (relative to the actual position)
     *
     * @param num the position to get
     * @return the character at the requested position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun peek(from: Int, to: Int): String
}