package io.github.shakelang.shake.parser.node.expression

import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeLogicalAndNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String get() = "&&"
    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalAndNode", "left" to left, "right" to right)
}


class ShakeLogicalOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "||"

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalOrNode", "left" to left, "right" to right)
}

class ShakeLogicalXOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "^^"

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalXOrNode", "left" to left, "right" to right)
}
