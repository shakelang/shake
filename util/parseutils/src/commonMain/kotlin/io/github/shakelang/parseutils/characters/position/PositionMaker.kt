package io.github.shakelang.parseutils.characters.position

import io.github.shakelang.parseutils.characters.source.CharacterSource

/**
 * A modifiable [PositionMarker] that stores the positions
 * of the line-separators
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
class PositionMaker : PositionMarker {

    /**
     * The index of the position
     */
    override var index: Int
        private set

    /**
     * The column of the position
     */
    override var column: Int
        private set

    /**
     * Getter for [PositionMaker.line]
     *
     * @return The [PositionMaker.line] of the [PositionMaker]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override var line: Int
        private set

    /**
     * Stores the locations of the line-separators
     */
    private val lineSeparators: MutableList<Int> = ArrayList()

    /**
     * The source of the [PositionMaker]
     */
    val source: CharacterSource

    /**
     * Constructor for the [PositionMaker]
     *
     * @param index The [PositionMaker.index] of the position
     * @param column The [PositionMaker.column] of the position
     * @param line The [PositionMaker.line] of the position
     * @param source the [CharacterSource] the chars come from
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     * @param source the [CharacterSource] the chars come from
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    constructor(source: CharacterSource) {
        this.source = source
        index = -1
        column = 0
        line = 1
    }

    // ***********************************************
    // Getters
    fun getLineSeparators(): IntArray {
        // Convert map to int[]
        val arr = IntArray(lineSeparators.size)
        for (i in arr.indices) arr[i] = lineSeparators[i]
        return arr
    }

    // ***********************************************
    // Others
    /**
     * Increases the [PositionMaker.index] and [PositionMaker.column]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun nextColumn() {
        index++
        column++
    }

    /**
     * Increases the [PositionMaker.index] and [PositionMaker.line] and sets the
     * [PositionMaker.column] to 1
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun nextLine() {
        lineSeparators.add(++index)
        line++
        column = 1
    }

    /**
     * Creates a [PositionMap] from the [PositionMaker]. This function only works after
     * the position is looped through
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun createPositionMap(): PositionMap = PositionMap(source, getLineSeparators())

    /**
     * Creates a [Position] from the [PositionMaker] at the actual location.
     * The [PositionMap] is only working to the actual position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun createPositionAtLocation(): Position = Position(createPositionMap(), index, column, line)
}