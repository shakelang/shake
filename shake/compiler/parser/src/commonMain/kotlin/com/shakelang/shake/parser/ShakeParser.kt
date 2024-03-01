package com.shakelang.shake.parser

import com.shakelang.shake.lexer.token.stream.ShakeTokenInputStream
import com.shakelang.shake.parser.impl.ShakeParserImpl
import com.shakelang.shake.parser.node.ShakeNode
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.outer.ShakeFileNode
import com.shakelang.shake.parser.node.statements.ShakeBlockNode
import com.shakelang.util.parseutils.characters.position.Position
import com.shakelang.util.parseutils.characters.position.PositionMap

/**
 * An abstract Parser for the Shake Programming language. An instance is created for each file in the compilation process.
 * The Default implementation of this class is [ShakeParserImpl]. Create a ShakeParser using [ShakeParser.from]
 *
 * @see ShakeParserImpl
 * @see ShakeParser.from
 */
abstract class ShakeParser {

    /**
     * The [ShakeTokenInputStream] to be parsed.
     */
    abstract val input: ShakeTokenInputStream

    /**
     * The [PositionMap] of the [input].
     * A position map is a mapping of the index of characters in the input and its corresponding [Position] in the file
     * (line, column, etc.).
     */
    abstract val map: PositionMap

    /**
     * Parses the [input] and returns the root [ShakeNode] of the parsed tree (A [ShakeFileNode])
     */
    abstract fun parse(): ShakeFileNode

    /**
     * Starts the parsing process, but directly jumps into the statement parsing phase (statements can normally be found
     * in methods, constructors, etc.).
     */
    abstract fun parseAsStatements(): ShakeBlockNode

    /**
     * Parses a single value.
     */
    abstract fun expectValue(): ShakeValuedNode

    companion object {

        /**
         * Creates a new [ShakeParser] from the given [ShakeTokenInputStream].
         *
         * @param input The [ShakeTokenInputStream] to be parsed.
         * @return A new [ShakeParser] instance.
         */
        fun from(input: ShakeTokenInputStream) = ShakeParserImpl(input)
    }
}
