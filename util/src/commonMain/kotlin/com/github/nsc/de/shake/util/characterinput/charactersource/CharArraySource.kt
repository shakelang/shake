package com.github.nsc.de.shake.util.characterinput.charactersource

internal class CharArraySource(

    override val all: CharArray,
    override val location: String

) : CharacterSource {

    override val length: Int
        get() = all.size

    override fun get(start: Int, end: Int): CharArray = all.copyOfRange(start, end)
}