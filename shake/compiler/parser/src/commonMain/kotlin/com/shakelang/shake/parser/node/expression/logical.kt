package com.shakelang.shake.parser.node.expression

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeLogicalAndNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String get() = "&&"
    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalAndNode", "left" to left.json, "right" to right.json)
}

class ShakeLogicalOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "||"

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalOrNode", "left" to left.json, "right" to right.json)
}

class ShakeLogicalXOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "^^"

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalXOrNode", "left" to left.json, "right" to right.json)
}
