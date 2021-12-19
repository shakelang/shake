package io.github.shakelang.shake.parser.node.logical

import io.github.shakelang.shake.parser.node.ValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class LogicalBiggerNode(map: PositionMap, left: ValuedNode, right: ValuedNode) : LogicalCompareNode(map, left, right) {
    override val operator: String
        get() = ">"

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalBiggerNode", "left" to left, "right" to right)
}