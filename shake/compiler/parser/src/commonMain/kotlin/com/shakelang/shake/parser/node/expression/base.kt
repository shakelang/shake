package com.shakelang.shake.parser.node.expression

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.ShakeValuedNodeImpl
import com.shakelang.shake.util.parseutils.characters.position.PositionMap

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
    val value: ShakeValuedNode,
    val operatorPosition: Int
) : ShakeValuedNodeImpl(map) {
    abstract val operator: String
}
