package com.shakelang.shake.util.parseutils.characters.source

import kotlin.jvm.JvmStatic

/**
 * A CharacterSource provides characters
 */
interface CharacterSource {

    /**
     * The contents of the [CharacterSource]
     */
    val all: CharArray

    /**
     * The length of the [CharacterSource]
     */
    val length: Int

    /**
     * The location the characters are from (e.g. file location)
     */
    val location: String

    /**
     * Get a range of characters from the [CharacterSource] as a [CharArray]
     *
     * @param start the start index for the characters
     * @param end the end index for the characters
     */
    operator fun get(start: Int, end: Int): CharArray

    companion object {

        /**
         * Create a [CharacterSource] from a source String
         *
         * @param contents the contents of the [CharacterSource]
         * @param source the source of the characters (e.g. file name)
         *
         */
        @JvmStatic
        fun from(contents: String, source: String): CharacterSource = from(contents.toCharArray(), source)

        /**
         * Create a [CharacterSource] from a source [CharArray]
         *
         * @param chars the contained chars of the [CharacterSource]
         * @param source the source of the characters (e.g. file name)
         *
         */
        @JvmStatic
        fun from(chars: CharArray, source: String): CharacterSource = CharArraySource(chars, source)
    }
}
