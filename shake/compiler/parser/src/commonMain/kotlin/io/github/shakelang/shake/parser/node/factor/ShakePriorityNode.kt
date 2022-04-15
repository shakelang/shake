package io.github.shakelang.shake.parser.node.factor

import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class ShakePriorityNode(map: PositionMap, val value: ShakeValuedNode) : ShakeValuedNode(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "PriorityNode", "value" to value)
}