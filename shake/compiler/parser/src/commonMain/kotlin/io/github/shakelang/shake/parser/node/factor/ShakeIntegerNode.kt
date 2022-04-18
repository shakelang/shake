package io.github.shakelang.shake.parser.node.factor

import io.github.shakelang.shake.parser.node.ShakeValuedNodeImpl
import io.github.shakelang.parseutils.characters.position.PositionMap

class ShakeIntegerNode(map: PositionMap, val number: Int) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "IntegerNode", "value" to number)
}