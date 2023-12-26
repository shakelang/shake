package com.shakelang.util.parseutils.characters.position

import com.shakelang.util.parseutils.characters.source.CharacterSource

/**
 * A [PositionMap] is a utility that stores the line separators to be able to
 * resolve the [Position] of a character from its index to save memory
 *
 * @since 0.1.0
 * @version 0.2.1
 */
interface PositionMap {

    /**
     * The [CharacterSource] of the [PositionMap]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val source: CharacterSource

    /**
     * The line separators of the [PositionMap]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val lineSeparators: IntArray

    /**
     * The location of the [PositionMap]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val location: String get() = source.location

    /**
     * Resolve the [Position] of a character from its index
     * @param index the index of the character
     * @return the [Position] of the character
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun resolve(index: Int): Position {
        for (i in lineSeparators.indices) {
            if (index < lineSeparators[i] + 1) {
                return if (i == 0) {
                    Position(
                        this,
                        index,
                        index + 1,
                        1
                    )
                } else {
                    Position(
                        this,
                        index,
                        index - lineSeparators[i - 1],
                        i + 1
                    )
                }
            }
        }
        return Position(
            this,
            index,
            index - (if (lineSeparators.isNotEmpty()) lineSeparators[lineSeparators.size - 1] + 1 else 0) + 1,
            lineSeparators.size + 1
        )
    }

    /**
     * Get amount of characters after the [Position] in the line
     * @param p the [Position] to get the amount of characters after
     * @return the amount of characters after the [Position] in the line
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun getAfterInLine(p: Position): Int {
        return if (p.line - 1 == lineSeparators.size) source.length - p.column else lineSeparators[p.line - 1] - p.index
    }

    /**
     * Get amount of characters after the [Position] in the line
     * Similar to [getAfterInLine] but with the index of the [Position] instead of the [Position] itself
     * @param index the index of the [Position] to get the amount of characters after
     * @return the amount of characters after the [Position] in the line
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun getAfterInLine(index: Int): Int = getAfterInLine(resolve(index))

    /**
     * Simple implementation of [PositionMap]
     *
     * @param source the [CharacterSource] of the [PositionMap]
     * @param lineSeparators the line separators of the [PositionMap]
     *
     * @since 0.2.1
     * @version 0.2.1
     * @internal
     */
    open class PositionMapImplementation(
        override val source: CharacterSource,
        override val lineSeparators: IntArray
    ) : PositionMap {

        /**
         * The location of the [PositionMap]
         *
         * @since 0.1.0
         * @version 0.2.1
         */
        override val location: String
            get() = source.location

        /**
         * Get amount of characters after the [Position] in the line
         *
         * @param p the [Position] to get the amount of characters after
         * @return the amount of characters after the [Position] in the line
         *
         * @since 0.1.0
         * @version 0.2.1
         */
        override fun getAfterInLine(p: Position): Int {
            return if (p.line - 1 == lineSeparators.size) source.length - p.column else lineSeparators[p.line - 1] - p.index
        }

        /**
         * Return the String representation of the [PositionMap]
         * @return the String representation of the [PositionMap]
         */
        override fun toString(): String {
            return source.location
        }
    }

    companion object {
        fun empty(source: CharacterSource): PositionMap = PositionMapImplementation(source, intArrayOf())
        fun empty(): PositionMap = empty(CharacterSource.from("empty", "<PositionMap#empty()>"))
    }
}
