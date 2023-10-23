package io.github.shakelang.parseutils.characters.streaming

import io.github.shakelang.parseutils.characters.position.PositionMaker
import io.github.shakelang.parseutils.characters.source.CharacterSource
import io.github.shakelang.parseutils.characters.source.CharacterSource.Companion.from

/**
 * An implementation of [CharacterInputStream] using just a string as argument
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
class SourceCharacterInputStream(

    /**
     * The source (mostly file) of the [SourceCharacterInputStream]
     */
    override val source: CharacterSource

) : CharacterInputStream {

    /**
     * The actual position of the [SourceCharacterInputStream]
     */
    override val positionMaker: PositionMaker = PositionMaker(source)

    /**
     * Returns the actual position of the [CharacterInputStream]
     *
     * @return the actual position of the [CharacterInputStream]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     *
     * @see CharacterInputStream.position
     * @see SourceCharacterInputStream.content
     * @see SourceCharacterInputStream.source
     */
    override val position: Int
        get() = positionMaker.index

    /**
     * Returns the chars of the [CharacterInputStream]
     *
     * @return the chars of the [CharacterInputStream]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     *
     * @see CharacterInputStream.content
     * @see SourceCharacterInputStream.source
     * @see SourceCharacterInputStream.position
     */
    override val content: CharArray
        get() = source.all

    /**
     * Constructor for [SourceCharacterInputStream] with given position
     *
     * @param content the characters
     * @param source the source of the characters
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    constructor(source: String, content: CharArray) : this(from(content, source))

    /**
     * Constructor for [SourceCharacterInputStream] with given position
     *
     * @param content the characters
     * @param source the source of the characters
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    constructor(source: String, content: String) : this(from(content, source))

    /**
     * Checks if the [CharacterInputStream] has a next character left
     *
     * @return if the [CharacterInputStream] has a next character
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     *
     * @see CharacterInputStream.hasNext
     * @see SourceCharacterInputStream.has
     */
    override fun hasNext(): Boolean {
        // We could also use has(1) here, but for performance reasons that should be better
        return positionMaker.index + 1 < source.length
    }

    /**
     * Checks if the [CharacterInputStream] has a given number of characters left
     *
     * @param number the num of characters to check
     * @return if the [CharacterInputStream] has a given number of characters left
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     *
     * @see CharacterInputStream.has
     * @see SourceCharacterInputStream.hasNext
     */
    override fun has(number: Int): Boolean {
        // throw an error, if the given number is smaller than 1
        if (number < 1) throw Error("The given number must be 1 or bigger")
        return positionMaker.index + number < source.length
    }

    /**
     * Returns the next character and continues to the next token
     *
     * @return the next character
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     *
     * @see CharacterInputStream.next
     */
    override fun next(): Char {
        // Skip and return the actual character
        skip()
        return actual()
    }

    /**
     * Skips a given number of characters
     *
     * @param number the number of characters to skip
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     *
     * @see CharacterInputStream.skip
     * @see SourceCharacterInputStream.skip
     */
    override fun skip(number: Int) {
        // Skip as many times, as required
        for (i in 0 until number) skip()
    }

    /**
     * Skips the next character
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     *
     * @see CharacterInputStream.skip
     * @see SourceCharacterInputStream.skip
     */
    override fun skip() {
        // if the actual position is a line-separator go to next line, if not then to next column
        if (this.peek() == '\n') positionMaker.nextLine() else positionMaker.nextColumn()
    }

    /**
     * Returns the actual character (the same as returned by [.next] when used before)
     *
     * @return the actual character
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     *
     * @see CharacterInputStream.actual
     */
    override fun actual(): Char {
        // return the character at the actual position
        return this.content[position]
    }

    /**
     * Gives back the next character without skipping
     *
     * @return the next character
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     *
     * @see CharacterInputStream.peek
     * @see SourceCharacterInputStream.peek
     * @see SourceCharacterInputStream.peek
     */
    override fun peek(): Char {
        // we could also use peek(1) here, but for performance reasons a direct implementation is better
        // throw an error if the StringCharacterInputStream has not enough tokens left
        if (!hasNext()) throw Error("Not enough characters left")

        // return the content at the required position
        return this.content[position + 1]
    }

    /**
     * Gives back the next character without skipping
     *
     * @return the next character
     *
     * @param num the position to get
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     *
     * @see CharacterInputStream.peek
     * @see SourceCharacterInputStream.peek
     * @see SourceCharacterInputStream.peek
     */
    override fun peek(num: Int): Char {
        // throw an error if the StringCharacterInputStream has not enough tokens left
        // (this will also automatically throw an error, if a number that is smaller than 1 is given as input, because
        // of the implementation in the has method)
        if (!has(num)) throw Error("Not enough characters left")

        // return the content at the required position
        return this.content[position + num]
    }

    /**
     * Gives back a part of the [CharacterInputStream] as string
     * (relative to the actual position)
     *
     * @param from the starting position of the string to get
     * @param to the end position of the string to get
     * @return the character at the requested position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     *
     * @see CharacterInputStream.peek
     * @see SourceCharacterInputStream.peek
     * @see SourceCharacterInputStream.peek
     */
    override fun peek(from: Int, to: Int): String {
        if (from < 0) throw Error("Peek argument must not be smaller than 0")
        if (to <= from) throw Error("To-argument must be bigger than from-argument")
        if(!this.has(to)) throw Error("Not enough characters left");
        return this.content.copyOfRange(position + from, position + to + 1).concatToString()
    }
}