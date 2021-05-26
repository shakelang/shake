package com.github.nsc.de.shake.util.characterinput.charactersource

import java.io.*

interface CharacterSource {

    operator fun get(start: Int, end: Int): CharArray
    val all: CharArray
    val length: Int
    val location: String

    companion object {

        @JvmStatic
        fun from(s: String, source: String): CharacterSource {
            return from(s.toCharArray(), source)
        }

        @JvmStatic
        fun from(chars: CharArray, source: String): CharacterSource {
            return CharArraySource(chars, source)
        }

        @JvmStatic
        @Throws(IOException::class)
        fun from(f: File, source: String): CharacterSource {
            val reader = BufferedReader(FileReader(f))
            val chars = CharArray(f.length().toInt()) // FIXME files that are longer than 2^31 (integer limit)
            for (i in chars.indices) chars[i] = reader.read().toChar()
            return from(chars, source)
        }

        @JvmStatic
        @Throws(IOException::class)
        fun from(s: InputStream, source: String): CharacterSource {
            val reader = BufferedReader(InputStreamReader(s))
            val chars = CharArray(s.available()) // FIXME files that are longer than 2^31 (integer limit)
            for (i in chars.indices) chars[i] = reader.read().toChar()
            return from(chars, source)
        }

    }
}