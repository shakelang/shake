package io.github.shakelang.shake.parser.node.factor

import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class ShakeDoubleNode(map: PositionMap, val number: Double) : ShakeValuedNode(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "DoubleNode", "value" to number)
}