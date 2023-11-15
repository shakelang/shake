package com.shakelang.shake.parser.node.expression

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeAddNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {

    override val operator: String
        get() = "+"

    override fun toJson(): Map<String, *> = mapOf("name" to "ShakeAddNode", "left" to left.json, "right" to right.json)
}

class ShakeSubNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {

    override val operator: String
        get() = "-"

    override fun toJson(): Map<String, *> = mapOf("name" to "ShakeSubNode", "left" to left.json, "right" to right.json)
}

class ShakeMulNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "*"

    override fun toJson(): Map<String, *> = mapOf("name" to "ShakeMulNode", "left" to left.json, "right" to right.json)
}

class ShakeDivNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "/"

    override fun toJson(): Map<String, *> = mapOf("name" to "ShakeDivNode", "left" to left.json, "right" to right.json)
}

class ShakeModNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "%"

    override fun toJson(): Map<String, *> = mapOf("name" to "ShakeModNode", "left" to left.json, "right" to right.json)
}

class ShakePowNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "^"

    override fun toJson(): Map<String, *> = mapOf("name" to "ShakePowNode", "left" to left.json, "right" to right.json)
}

class ShakeUnaryPlusNode(
    map: PositionMap,
    contents: ShakeValuedNode,
    operatorPosition: Int
) : ShakeUnaryNode(map, contents, operatorPosition) {
    override val operator: String get() = "+"
    override fun toJson(): Map<String, *> = mapOf("name" to "ShakeUnaryPlusNode", "value" to value.json)
}

class ShakeUnaryMinusNode(
    map: PositionMap,
    contents: ShakeValuedNode,
    operatorPosition: Int
) : ShakeUnaryNode(map, contents, operatorPosition) {
    override val operator: String get() = "-"
    override fun toJson(): Map<String, *> = mapOf("name" to "ShakeUnaryMinusNode", "value" to value.json)
}
