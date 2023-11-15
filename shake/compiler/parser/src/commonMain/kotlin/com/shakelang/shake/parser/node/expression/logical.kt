package com.shakelang.shake.parser.node.expression

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeLogicalAndNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String get() = "&&"
    override fun toJson(): Map<String, *> = mapOf("name" to "ShakeLogicalAndNode", "left" to left.json, "right" to right.json)
}

class ShakeLogicalOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "||"

    override fun toJson(): Map<String, *> = mapOf("name" to "ShakeLogicalOrNode", "left" to left.json, "right" to right.json)
}

class ShakeLogicalXOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "^^"

    override fun toJson(): Map<String, *> = mapOf("name" to "ShakeLogicalXOrNode", "left" to left.json, "right" to right.json)
}

class ShakeLogicalNotNode(map: PositionMap, node: ShakeValuedNode, operatorPosition: Int) :
    ShakeUnaryNode(map, node, operatorPosition) {
    override val operator: String
        get() = "!"

    override fun toJson(): Map<String, *> = mapOf("name" to "ShakeLogicalNotNode", "value" to value.json)
}