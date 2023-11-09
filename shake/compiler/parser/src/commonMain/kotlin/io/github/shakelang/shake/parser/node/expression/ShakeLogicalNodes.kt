package io.github.shakelang.shake.parser.node.expression

import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.shake.parser.node.ShakeValuedNodeImpl
import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeLogicalAndNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String get() = "&&"
    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalAndNode", "left" to left, "right" to right)
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

class ShakeLogicalEqEqualsNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "=="

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalEqEqualsNode", "left" to left, "right" to right)
}


class ShakeLogicalFalseNode(map: PositionMap) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalFalseNode")
}


class ShakeLogicalOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "||"

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalOrNode", "left" to left, "right" to right)
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


class ShakeLogicalTrueNode(map: PositionMap) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalTrueNode")
}

class ShakeLogicalXOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "^^"

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalXOrNode", "left" to left, "right" to right)
}