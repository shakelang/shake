package com.shakelang.util.parseutils.characters.source

/**
 * A [CharArraySource] is an implementation of [CharacterSource] that provides the characters based
 * on a [CharArray]
 * @constructor creates a [CharArraySource] from a [CharArray] and a location
 * @param all the contents of the [CharacterSource] (implementation of [CharacterSource.all])
 * @param location the location the characters are from (e.g. file location) (implementation of [CharacterSource.location])
 *
 * @see CharacterSource
 *
 * @since 0.1.0
 * @version 0.2.1
 */
internal class CharArraySource(

    /**
     * The contents of the [CharacterSource] (implementation of [CharacterSource.all])
     * @see CharacterSource.all
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override val all: CharArray,

    /**
     * The location the characters are from (e.g. file location) (implementation of [CharacterSource.location])
     * @see CharacterSource.location
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override val location: String

) : CharacterSource {

    /**
     * The length of the [CharacterSource] (implementation of [CharacterSource.length])
     * @see CharacterSource.length
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override val length: Int get() = all.size

    /**
     * Get a range of characters from the [CharacterSource] as a [CharArray]
     * (implementation of [CharacterSource.get])
     *
     * @param start the start index for the characters
     * @param end the end index for the characters
     *
     * @see CharacterSource.get
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override fun get(start: Int, end: Int): CharArray = all.copyOfRange(start, end)
}
