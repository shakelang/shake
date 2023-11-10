package io.github.shakelang.shake.util.parseutils.characters.source

/**
 * A [CharArraySource] is an implementation of [CharacterSource] that provides the characters based
 * on a [CharArray]
 *
 *
 * @see CharacterSource
 */
internal class CharArraySource(

    /**
     * The contents of the [CharacterSource] (implementation of [CharacterSource.all])
     *
     * @see CharacterSource.all
     */
    override val all: CharArray,

    /**
     * The location the characters are from (e.g. file location) (implementation of [CharacterSource.location])
     *
     * @see CharacterSource.location
     */
    override val location: String

) : CharacterSource {

    /**
     * The length of the [CharacterSource] (implementation of [CharacterSource.length])
     *
     *
     * @see CharacterSource.length
     */
    override val length: Int get() = all.size

    /**
     * Get a range of characters from the [CharacterSource] as a [CharArray]
     * (implementation of [CharacterSource.get])
     *
     * @param start the start index for the characters
     * @param end the end index for the characters
     *
     *
     * @see CharacterSource.get
     */
    override fun get(start: Int, end: Int): CharArray = all.copyOfRange(start, end)
}
