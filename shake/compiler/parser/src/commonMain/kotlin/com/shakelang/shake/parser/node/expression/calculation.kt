package com.shakelang.shake.parser.node.expression

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.util.parseutils.characters.position.PositionMap

class ShakeAddNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {

    override val operator: String
        get() = "+"
}

class ShakeSubNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {

    override val operator: String
        get() = "-"
}

class ShakeMulNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "*"
}

class ShakeDivNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "/"
}

class ShakeModNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "%"
}

class ShakePowNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "^"
}

class ShakeUnaryPlusNode(
    map: PositionMap,
    contents: ShakeValuedNode,
    operatorPosition: Int,
) : ShakeUnaryNode(map, contents, operatorPosition) {
    override val operator: String get() = "+"
}

class ShakeUnaryMinusNode(
    map: PositionMap,
    contents: ShakeValuedNode,
    operatorPosition: Int,
) : ShakeUnaryNode(map, contents, operatorPosition) {
    override val operator: String get() = "-"
}

fun createSyntheticAddNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeAddNode(PositionMap.empty(), left, right, -1)

fun createSyntheticSubNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeSubNode(PositionMap.empty(), left, right, -1)

fun createSyntheticMulNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeMulNode(PositionMap.empty(), left, right, -1)

fun createSyntheticDivNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeDivNode(PositionMap.empty(), left, right, -1)

fun createSyntheticModNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeModNode(PositionMap.empty(), left, right, -1)

fun createSyntheticPowNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakePowNode(PositionMap.empty(), left, right, -1)
