package io.github.shakelang.shake.util.parseutils.characters.position

import kotlin.jvm.JvmOverloads


/**
 * The [Position] marks a position in the source-code.
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
class Position

/**
 * Constructor for the position
 *
 * @param source The [source] (mostly file) of the content
 * @param index The [index] of the position
 * @param column The [column] of the position
 * @param line The [line] of the position
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@JvmOverloads constructor(

    /**
     * The source
     */
    val source: PositionMap,

    /**
     * The index of the position
     */
    override val index: Int,

    /**
     * The column of the position
     */
    override val column: Int,

    /**
     * The line of the position
     */
    override val line: Int,

) : PositionMarker {

    /**
     * Copies the position
     *
     * @return a copy of the position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun copy(): Position {
        return Position(source, index, column, line)
    }

    /**
     * Creates a string-representation of the string
     *
     * @return A string-representation of the string
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
}