package com.github.shakelang.shake.util.characterinput.position

import com.github.shakelang.shake.util.characterinput.charactersource.CharacterSource

/**
 * A modifiable [PositionMarker] that stores the positions
 * of the line-separators
 */
@Suppress("unused")
class PositionMaker : PositionMarker {
    /**
     * Getter for [PositionMaker.index]
     *
     * @return The [PositionMaker.index] of the [PositionMaker]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    /**
     * The index of the position
     */
    override var index: Int
        private set
    /**
     * Getter for [PositionMaker.column]
     *
     * @return The [PositionMaker.column] of the [PositionMaker]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
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
    /**
     * The line of the position
     */
    override var line: Int
        private set

    /**
     * Stores the locations of the line-separators
     */
    private val lineSeparators: MutableList<Int> = ArrayList()
    /**
     * Getter for [PositionMaker.source]
     *
     * @return the [PositionMaker.source] of the [PositionMaker]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
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

    fun createPositionMap(): PositionMap {
        return PositionMap(source, getLineSeparators())
    }

    fun createPositionAtLocation(): Position {
        return Position(createPositionMap(), index, column, line)
    }
}