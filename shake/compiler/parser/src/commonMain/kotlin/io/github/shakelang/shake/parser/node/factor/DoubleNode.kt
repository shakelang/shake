package io.github.shakelang.shake.parser.node.factor

import io.github.shakelang.shake.parser.node.ValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class DoubleNode(map: PositionMap, val number: Double) : ValuedNode(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "DoubleNode", "value" to number)
}