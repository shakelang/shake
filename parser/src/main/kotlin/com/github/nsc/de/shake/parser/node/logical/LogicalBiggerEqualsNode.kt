package com.github.nsc.de.shake.parser.node.logical

import com.github.nsc.de.shake.parser.node.ValuedNode
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class LogicalBiggerEqualsNode(map: PositionMap, left: ValuedNode, right: ValuedNode) :
    LogicalCompareNode(map, left, right) {
    override val operator: String
        get() = ">="

    override fun toJson(): Map<String, *> =
        mapOf("name" to "LogicalBiggerEqualsNode", "left" to left, "right" to right)
}