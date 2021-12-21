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

    override fun resolve(index: Int): Position {
        for (i in lineSeparatorsList.indices) {
            if (index < lineSeparators[i]) {
                return if (i == 0) Position(this, index, index + 1, 1)
                else Position(this, index, index - lineSeparators[i - 1], i + 1)
            }
        }
        return Position(
            this, index,
            index - (if (lineSeparators.size > 0) lineSeparators[lineSeparators.size - 1] else 0) + 1,
            lineSeparators.size + 1
        )
    }

    override fun getAfterInLine(p: Position): Int {
        return if (p.line - 1 == lineSeparators.size) source.length - p.column else lineSeparators[p.line - 1] - p.index
    }

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
    fun createPositionAtLocation(): Position = Position(createPositionMap(), index, column, line)
}