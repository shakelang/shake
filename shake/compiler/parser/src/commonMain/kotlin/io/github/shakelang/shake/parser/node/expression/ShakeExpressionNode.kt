package io.github.shakelang.shake.parser.node.expression

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.shake.parser.node.ShakeValuedNodeImpl

@Suppress("unused")
abstract class ShakeExpressionNode(
    map: PositionMap,
    val left: ShakeValuedNode,
    val right: ShakeValuedNode,
    val operatorPosition: Int
) : ShakeValuedNodeImpl(map) {
    abstract val operator: Char
}