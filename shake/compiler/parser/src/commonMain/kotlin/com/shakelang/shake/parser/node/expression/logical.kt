package com.shakelang.shake.parser.node.expression

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeLogicalAndNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String get() = "&&"
}

class ShakeLogicalOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "||"
}

class ShakeLogicalXOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "^^"
}

class ShakeLogicalNotNode(map: PositionMap, node: ShakeValuedNode, operatorPosition: Int) :
    ShakeUnaryNode(map, node, operatorPosition) {
    override val operator: String
        get() = "!"
}

fun createSyntheticLogicalAndNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeLogicalAndNode(PositionMap.empty(), left, right, -1)

fun createSyntheticLogicalOrNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeLogicalOrNode(PositionMap.empty(), left, right, -1)

fun createSyntheticLogicalXOrNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeLogicalXOrNode(PositionMap.empty(), left, right, -1)

fun createSyntheticLogicalNotNode(node: ShakeValuedNode) =
    ShakeLogicalNotNode(PositionMap.empty(), node, -1)
