package com.shakelang.shake.parser.node.expression

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.util.parseutils.characters.position.PositionMap

class ShakeEqualNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "=="

    override fun toJson(): Map<String, *> =
        mapOf("name" to "ShakeEqualNode", "left" to left.json, "right" to right.json)
}

class ShakeNotEqualNode(
    map: PositionMap,
    left: ShakeValuedNode,
    right: ShakeValuedNode,
    operatorPosition: Int,
) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "!="

    override fun toJson(): Map<String, *> =
        mapOf("name" to "ShakeNotEqualNode", "left" to left.json, "right" to right.json)
}

class ShakeGreaterThanOrEqualNode(
    map: PositionMap,
    left: ShakeValuedNode,
    right: ShakeValuedNode,
    operatorPosition: Int,
) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = ">="
}

class ShakeGreaterThanNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = ">"
}

class ShakeLessThanOrEqualNode(
    map: PositionMap,
    left: ShakeValuedNode,
    right: ShakeValuedNode,
    operatorPosition: Int,
) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "<="
}

class ShakeLessThanNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "<"
}

fun createSyntheticEqualNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeEqualNode(PositionMap.empty(), left, right, -1)

fun createSyntheticNotEqualNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeNotEqualNode(PositionMap.empty(), left, right, -1)

fun createSyntheticGreaterThanOrEqualNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeGreaterThanOrEqualNode(PositionMap.empty(), left, right, -1)

fun createSyntheticGreaterThanNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeGreaterThanNode(PositionMap.empty(), left, right, -1)

fun createSyntheticLessThanOrEqualNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeLessThanOrEqualNode(PositionMap.empty(), left, right, -1)

fun createSyntheticLessThanNode(left: ShakeValuedNode, right: ShakeValuedNode) =
    ShakeLessThanNode(PositionMap.empty(), left, right, -1)
