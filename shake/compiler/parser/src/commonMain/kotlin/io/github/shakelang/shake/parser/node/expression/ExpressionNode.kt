package io.github.shakelang.shake.parser.node.expression

import io.github.shakelang.shake.parser.node.ValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

@Suppress("unused")
abstract class ExpressionNode(
    map: PositionMap,
    val left: ValuedNode,
    val right: ValuedNode,
    val operatorPosition: Int
) : ValuedNode(map) {
    abstract val operator: Char
}