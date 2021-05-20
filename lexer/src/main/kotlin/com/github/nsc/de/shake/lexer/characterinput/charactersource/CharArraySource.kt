package com.github.nsc.de.shake.lexer.characterinput.charactersource

internal class CharArraySource(

    override val all: CharArray,
    override val location: String?

) : CharacterSource {

    override val length: Int
        get() = all.size

    override fun get(start: Int, end: Int): CharArray {
        val chars = CharArray(end - start)
        val length = end - start
        if (length >= 0) System.arraycopy(all, start, chars, 0, length)
        return chars
    }
}