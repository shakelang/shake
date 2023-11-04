package io.github.shakelang.shake.parser.node.factor

import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.shake.parser.node.ShakeValuedNodeImpl
import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakePriorityNode(map: PositionMap, val value: ShakeValuedNode) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "PriorityNode", "value" to value)
}