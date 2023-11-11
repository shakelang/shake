package com.shakelang.shake.parser.node.expression

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeLogicalEqEqualsNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "=="

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalEqEqualsNode", "left" to left, "right" to right)
}

class ShakeLogicalNotEqualsNode(
    map: PositionMap,
    left: ShakeValuedNode,
    right: ShakeValuedNode,
    operatorPosition: Int
) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "!="

    override fun toJson(): Map<String, *> =
        mapOf("name" to "LogicalNotEqualsNode", "left" to left, "right" to right)
}

class ShakeLogicalBiggerEqualsNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = ">="

    override fun toJson(): Map<String, *> =
        mapOf("name" to "LogicalBiggerEqualsNode", "left" to left, "right" to right)
}

class ShakeLogicalBiggerNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = ">"

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalBiggerNode", "left" to left, "right" to right)
}

class ShakeLogicalSmallerEqualsNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "<="

    override fun toJson(): Map<String, *> =
        mapOf("name" to "LogicalSmallerEqualsNode", "left" to left, "right" to right)
}

class ShakeLogicalSmallerNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "<"

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalSmallerNode", "left" to left, "right" to right)
}
