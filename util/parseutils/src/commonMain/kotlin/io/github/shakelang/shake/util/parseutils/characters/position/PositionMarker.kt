package io.github.shakelang.shake.util.parseutils.characters.position

interface PositionMarker {
    // ***********************************************
    // Getters
    /**
     * The index of the position
     *
     * @return The index of the position
     */
    val index: Int

    /**
     * The column of the position
     *
     * @return The column of the position
     */
    val column: Int

    /**
     * The line of the position
     *
     * @return The line of the position
     */
    val line: Int
}
