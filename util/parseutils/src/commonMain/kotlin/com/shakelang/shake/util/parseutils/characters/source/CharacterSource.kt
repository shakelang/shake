package com.shakelang.shake.util.parseutils.characters.source

import kotlin.jvm.JvmStatic

/**
 * A CharacterSource provides characters
 *
 * @since 0.1.0
 * @version 0.2.1
 */
interface CharacterSource {

    /**
     * The contents of the [CharacterSource]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val all: CharArray

    /**
     * The length of the [CharacterSource]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val length: Int

    /**
     * The location the characters are from (e.g. file location)
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val location: String

    /**
     * Get a range of characters from the [CharacterSource] as a [CharArray]
     *
     * @param start the start index for the characters
     * @param end the end index for the characters
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    operator fun get(start: Int, end: Int): CharArray

    companion object {

        /**
         * Create a [CharacterSource] from a source String
         *
         * @param contents the contents of the [CharacterSource]
         * @param source the source of the characters (e.g. file name)
         *
         * @since 0.1.0
         * @version 0.2.1
         */
        @JvmStatic
        fun from(contents: String, source: String): CharacterSource = from(contents.toCharArray(), source)

        /**
         * Create a [CharacterSource] from a source [CharArray]
         *
         * @param chars the contained chars of the [CharacterSource]
         * @param source the source of the characters (e.g. file name)
         *
         * @since 0.1.0
         * @version 0.2.1
         */
        @JvmStatic
        fun from(chars: CharArray, source: String): CharacterSource = CharArraySource(chars, source)
    }
}
