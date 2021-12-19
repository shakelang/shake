package io.github.shakelang.shake.parser.node.factor

import io.github.shakelang.shake.parser.node.ValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class IntegerNode(map: PositionMap, val number: Int) : ValuedNode(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "IntegerNode", "value" to number)
}