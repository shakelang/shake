package io.github.shakelang.shake.parser.node.expression

import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.shake.parser.node.ShakeValuedNodeImpl
import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap

abstract class ShakeExpressionNode(
    map: PositionMap,
    val left: ShakeValuedNode,
    val right: ShakeValuedNode,
    val operatorPosition: Int
) : ShakeValuedNodeImpl(map) {
    abstract val operator: String
}

abstract class ShakeUnaryNode(
    map: PositionMap,
    val node: ShakeValuedNode,
    val operatorPosition: Int
) : ShakeValuedNodeImpl(map) {
    abstract val operator: String
}