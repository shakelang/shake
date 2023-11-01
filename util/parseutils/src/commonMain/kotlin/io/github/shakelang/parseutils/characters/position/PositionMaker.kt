package io.github.shakelang.parseutils.characters.position

import io.github.shakelang.parseutils.characters.source.CharacterSource

/**
 * A modifiable [PositionMarker] that stores the positions
 * of the line-separators
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
class PositionMaker : PositionMap, PositionMarker {

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

    private val lineSeparatorsList: MutableList<Int> = ArrayList()
    override val lineSeparators: IntArray get() = lineSeparatorsList.toIntArray()

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
     * @param index The [PositionMaker.index] of the position
     * @param column The [PositionMaker.column] of the position
     * @param line The [PositionMaker.line] of the position
     * @param source the [CharacterSource] the chars come from
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    constructor(source: CharacterSource) {
        this.source = source
        index = -1
        column = 0
        line = 1
    }

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
        lineSeparatorsList.add(++index)
        line++
        column = 1
    }

    /**
     * Creates a [PositionMap] from the [PositionMaker]. This function only works after
     * the position is looped through
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun createPositionMap(): PositionMap = PositionMap.PositionMapImpl(source, lineSeparators)

    /**
     * Creates a [Position] from the [PositionMaker] at the actual location.
     * The [PositionMap] is only working to the actual position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun createPositionAtLocation(): Position = Position(this, index, column, line)

    override fun toString(): String {
        return "PositionMaker(index=$index, column=$column, line=$line, lineSeparators=$lineSeparatorsList)"
    }
}