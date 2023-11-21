package com.shakelang.shake.parser.node.expression

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeBitwiseAndNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String get() = "&"
}

class ShakeBitwiseOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "|"
}

class ShakeBitwiseXOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "^"
}

class ShakeBitwiseNAndNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "~&"
}

class ShakeBitwiseNOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "~|"
}

class ShakeBitwiseXNOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "~^"
}

class ShakeBitwiseNotNode(map: PositionMap, node: ShakeValuedNode, operatorPosition: Int) :
    ShakeUnaryNode(map, node, operatorPosition) {
    override val operator: String
        get() = "~"
}

class ShakeBitwiseShiftLeftNode(
    map: PositionMap,
    left: ShakeValuedNode,
    right: ShakeValuedNode,
    operatorPosition: Int
) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "<<"
}

class ShakeBitwiseShiftRightNode(
    map: PositionMap,
    left: ShakeValuedNode,
    right: ShakeValuedNode,
    operatorPosition: Int
) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = ">>"
}

fun createSyntheticBitwiseAndNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeBitwiseAndNode(PositionMap.empty(), left, right, -1)

fun createSyntheticBitwiseOrNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeBitwiseOrNode(PositionMap.empty(), left, right, -1)

fun createSyntheticBitwiseXOrNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeBitwiseXOrNode(PositionMap.empty(), left, right, -1)

fun createSyntheticBitwiseNAndNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeBitwiseNAndNode(PositionMap.empty(), left, right, -1)

fun createSyntheticBitwiseNOrNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeBitwiseNOrNode(PositionMap.empty(), left, right, -1)

fun createSyntheticBitwiseXNOrNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeBitwiseXNOrNode(PositionMap.empty(), left, right, -1)

fun createSyntheticBitwiseNotNode(node: ShakeValuedNode) =
    ShakeBitwiseNotNode(PositionMap.empty(), node, -1)

fun createSyntheticBitwiseShiftLeftNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeBitwiseShiftLeftNode(PositionMap.empty(), left, right, -1)

fun createSyntheticBitwiseShiftRightNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeBitwiseShiftRightNode(PositionMap.empty(), left, right, -1)
