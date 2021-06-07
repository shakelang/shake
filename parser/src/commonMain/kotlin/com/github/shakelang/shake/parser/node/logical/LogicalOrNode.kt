package com.github.shakelang.shake.parser.node.logical

import com.github.shakelang.shake.parser.node.ValuedNode
import com.github.shakelang.shake.util.characterinput.position.PositionMap

class LogicalOrNode(map: PositionMap, left: ValuedNode, right: ValuedNode) :
    LogicalConcatenationNode(map, left, right) {
    override val operator: String
        get() = "||"

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalOrNode", "left" to left, "right" to right)
}