package com.github.shakelang.shake.parser.node.factor

import com.github.shakelang.shake.parser.node.ValuedNode
import com.github.shakelang.shake.util.characterinput.position.PositionMap

class PriorityNode(map: PositionMap, val value: ValuedNode) : ValuedNode(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "PriorityNode", "value" to value)
}