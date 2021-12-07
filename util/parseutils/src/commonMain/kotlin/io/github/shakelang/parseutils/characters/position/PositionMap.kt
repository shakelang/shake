package io.github.shakelang.parseutils.characters.position

import io.github.shakelang.parseutils.characters.source.CharacterSource

class PositionMap(val source: CharacterSource, val lineSeparators: IntArray) {
    val location: String?
        get() = source.location

    fun resolve(index: Int): Position {
        for (i in lineSeparators.indices) {
            if (index < lineSeparators[i]) {
                return if (i == 0) Position(
                    this,
                    index,
                    index + 1,
                    1
                ) else Position(
                    this,
                    index,
                    index - lineSeparators[i - 1],
                    i + 1
                )
            }
        }
        return Position(
            this, index,
            index - (if (lineSeparators.size > 0) lineSeparators[lineSeparators.size - 1] else 0) + 1,
            lineSeparators.size + 1
        )
    }

    fun getAfterInLine(p: Position): Int {
        return if (p.line - 1 == lineSeparators.size) source.length - p.column else lineSeparators[p.line - 1] - p.index
    }

    override fun toString(): String {
        return location!!
    }
}