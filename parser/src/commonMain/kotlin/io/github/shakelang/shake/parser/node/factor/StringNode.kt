package io.github.shakelang.shake.parser.node.factor

import io.github.shakelang.shake.parser.node.ValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class StringNode(map: PositionMap, val value: String) : ValuedNode(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "StringNode", "number" to value)
}