package io.github.shakelang.shake.parser.node.logical

import io.github.shakelang.shake.parser.node.ValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class LogicalXOrNode(map: PositionMap, left: ValuedNode, right: ValuedNode) :
    LogicalConcatenationNode(map, left, right) {
    override val operator: String
        get() = "^"

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalXOrNode", "left" to left, "right" to right)
}