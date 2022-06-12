package io.github.shakelang.shake.parser.node.factor

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.parser.node.ShakeValuedNodeImpl

class ShakeDoubleNode(map: PositionMap, val number: Double) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "DoubleNode", "value" to number)
}