package com.shakelang.util.parseutils.characters.position

/**
 * A [PositionMarker] marks a position in a file
 * @see Position
 *
 * @since 0.1.0
 * @version 0.2.1
 */
interface PositionMarker {

    /**
     * The index of the position
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val index: Int

    /**
     * The column of the position
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val column: Int

    /**
     * The line of the position
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val line: Int
}
