package com.github.shakelang.shake.util.characterinput.position

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
    override val index: Int = -1,

    /**
     * The column of the position
     */
    override val column: Int = 0,

    /**
     * The line of the position
     */
    override val line: Int = 1

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
}