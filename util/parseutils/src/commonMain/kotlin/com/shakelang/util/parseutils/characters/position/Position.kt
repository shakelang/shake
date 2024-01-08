package com.shakelang.util.parseutils.characters.position

/**
 * The [Position] marks a position in the source-code.
 *
 * @param source The [source] (mostly file) of the content
 * @param index The [index] of the position
 * @param column The [column] of the position
 * @param line The [line] of the position
 *
 * @since 0.1.0
 * @version 0.2.1
 */
class Position(

    /**
     * The source
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val source: PositionMap,

    /**
     * The index of the position
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override val index: Int,

    /**
     * The column of the position
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override val column: Int,

    /**
     * The line of the position
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override val line: Int,

    ) : PositionMarker {

    /**
     * Copies the position
     * @return a copy of the position
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun copy(): Position {
        return Position(source, index, column, line)
    }

    /**
     * Creates a string-representation of the string
     * @return A string-representation of the string
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override fun toString(): String {
        return "$source:$line:$column"
    }

    /**
     * Compares the position to another position
     *
     * @param other The other position
     * @return true if the positions are equal
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Position) return false

        if (source != other.source) return false
        if (index != other.index) return false
        if (column != other.column) return false
        if (line != other.line) return false

        return true
    }

    /**
     * Creates a hashcode of the position
     * @return The hashcode of the position
     *
     * @since 0.2.1
     * @version 0.2.1
     */
    override fun hashCode(): Int {
        var result = source.hashCode()
        result = 31 * result + index
        result = 31 * result + column
        result = 31 * result + line
        return result
    }
}
