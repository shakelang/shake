package io.github.shakelang.shake.parser.node.factor

import io.github.shakelang.shake.parser.node.ShakeValuedNodeImpl
import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeDoubleNode(map: PositionMap, val number: Double) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "DoubleNode", "value" to number)
}