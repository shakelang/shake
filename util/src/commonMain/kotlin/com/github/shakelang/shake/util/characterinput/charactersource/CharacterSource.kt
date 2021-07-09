package com.github.shakelang.shake.util.characterinput.charactersource

import com.github.shakelang.shake.util.File
import kotlin.jvm.JvmStatic


/**
 * A CharacterSource provides characters
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    operator fun get(start: Int, end: Int): CharArray

    companion object {

        /**
         * Create a [CharacterSource] from a source String
         *
         * @param contents the contents of the [CharacterSource]
         * @param source the source of the characters (e.g. file name)
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        @JvmStatic
        fun from(contents: String, source: String): CharacterSource = from(contents.toCharArray(), source)

        /**
         * Create a [CharacterSource] from a source [CharArray]
         *
         * @param chars the contained chars of the [CharacterSource]
         * @param source the source of the characters (e.g. file name)
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        @JvmStatic
        fun from(chars: CharArray, source: String): CharacterSource = CharArraySource(chars, source)

        /**
         * Create a [CharacterSource] from a file
         *
         * @param file the file to create the [CharacterSource] from
         * @param source the source of the characters (e.g. file name)
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        @JvmStatic
        fun from(file: File, source: String): CharacterSource = from(file.contents, source)

        /**
         * Create a [CharacterSource] from a file
         *
         * @param file the file to create the [CharacterSource] from
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        @JvmStatic
        fun from(file: File): CharacterSource = from(file, file.path)

    }
}