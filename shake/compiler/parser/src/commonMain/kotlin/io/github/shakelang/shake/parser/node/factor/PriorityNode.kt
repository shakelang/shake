package io.github.shakelang.shake.parser.node.factor

import io.github.shakelang.shake.parser.node.ValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class PriorityNode(map: PositionMap, val value: ValuedNode) : ValuedNode(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "PriorityNode", "value" to value)
}