package io.github.shakelang.shake.parser.node.expression

import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

@Suppress("unused")
abstract class ShakeExpressionNode(
    map: PositionMap,
    val left: ShakeValuedNode,
    val right: ShakeValuedNode,
    val operatorPosition: Int
) : ShakeValuedNode(map) {
    abstract val operator: Char
}