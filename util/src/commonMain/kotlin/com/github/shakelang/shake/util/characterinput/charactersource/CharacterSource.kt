package com.github.shakelang.shake.util.characterinput.charactersource

import com.github.shakelang.shake.util.File
import kotlin.jvm.JvmStatic

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
        fun from(f: File, source: String): CharacterSource {
            return from(f.contents, source)
        }

    }
}