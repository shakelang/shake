package com.github.shakelang.shake.parser.node.logical

import com.github.shakelang.shake.parser.node.ValuedNode
import com.github.shakelang.shake.util.characterinput.position.PositionMap

class LogicalBiggerEqualsNode(map: PositionMap, left: ValuedNode, right: ValuedNode) :
    LogicalCompareNode(map, left, right) {
    override val operator: String
        get() = ">="

    override fun toJson(): Map<String, *> =
        mapOf("name" to "LogicalBiggerEqualsNode", "left" to left, "right" to right)
}