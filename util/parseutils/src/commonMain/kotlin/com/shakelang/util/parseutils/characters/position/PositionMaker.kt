package com.shakelang.util.parseutils.characters.position

import com.shakelang.util.parseutils.characters.source.CharacterSource

/**
 * A modifiable [PositionMarker] that stores the positions
 * of the line-separators
 *
 * @since 0.1.0
 * @version 0.2.1
 */
@Suppress("unused")
class PositionMaker : PositionMap, PositionMarker {

    /**
     * The index of the position
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override var index: Int
        private set

    /**
     * The column of the position
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override var column: Int
        private set

    /**
     * Getter for [PositionMaker.line]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override var line: Int
        private set

    /**
     * The list of line-separators
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    private val lineSeparatorsList: MutableList<Int> = ArrayList()

    /**
     * The list of line-separators
     */
    override val lineSeparators: IntArray get() = lineSeparatorsList.toIntArray()

    /**
     * The location of the [PositionMaker]
     */
    override val location: String
        get() = source.location

    /**
     * The source of the [PositionMaker]
     */
    override val source: CharacterSource

    /**
     * Constructor for the [PositionMaker]
     *
     * @param index The [PositionMaker.index] of the position
     * @param column The [PositionMaker.column] of the position
     * @param line The [PositionMaker.line] of the position
     * @param source the [CharacterSource] the chars come from
     */
    constructor(index: Int, column: Int, line: Int, source: CharacterSource) {
        this.index = index
        this.column = column
        this.line = line
        this.source = source
    }

    /**
     * Constructor for the [PositionMaker]
     *
     * @param index The [PositionMaker.index] of the position
     * @param column The [PositionMaker.column] of the position
     * @param line The [PositionMaker.line] of the position
     * @param lineSeparators The [PositionMaker.lineSeparators] of the position
     * @param source the [CharacterSource] the chars come from
     */
    constructor(index: Int, column: Int, line: Int, lineSeparators: IntArray, source: CharacterSource) {
        this.index = index
        this.column = column
        this.line = line
        this.source = source
        this.lineSeparatorsList.addAll(lineSeparators.toList())
    }

    /**
     * Constructor for the [PositionMaker]
     *
     * @param source the [CharacterSource] the chars come from
     */
    constructor(source: CharacterSource) {
        this.source = source
        index = -1
        column = 0
        line = 1
    }

    /**
     * Increases the [PositionMaker.index] and [PositionMaker.column]
     */
    fun nextColumn() {
        index++
        column++
    }

    /**
     * Increases the [PositionMaker.index] and [PositionMaker.line] and sets the
     * [PositionMaker.column] to 1
     */
    fun nextLine() {
        lineSeparatorsList.add(++index)
        line++
        column = 1
    }

    /**
     * Creates a [PositionMap] from the [PositionMaker]. This function only works after
     * the position is looped through
     */
    fun createPositionMap(): PositionMap = PositionMap.PositionMapImplementation(source, lineSeparators)

    /**
     * Creates a [Position] from the [PositionMaker] at the actual location.
     * The [PositionMap] is only working to the actual position
     */
    fun createPositionAtLocation(): Position = Position(this, index, column, line)

    /**
     * Returns a string-representation of the [PositionMaker]
     *
     * @return A string-representation of the [PositionMaker]
     */
    override fun toString(): String {
        return "PositionMaker(index=$index, column=$column, line=$line, lineSeparators=$lineSeparatorsList)"
    }
}
