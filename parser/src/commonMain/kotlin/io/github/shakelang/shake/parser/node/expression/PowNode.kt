package io.github.shakelang.shake.parser.node.expression

import io.github.shakelang.shake.parser.node.ValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class PowNode(map: PositionMap, left: ValuedNode, right: ValuedNode, operatorPosition: Int) :
    ExpressionNode(map, left, right, operatorPosition) {
    override val operator: Char
        get() = '^'

    override fun toJson(): Map<String, *> = mapOf("name" to "PowNode", "left" to left, "right" to right)
}