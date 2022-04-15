package io.github.shakelang.shake.parser.node.factor

import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class ShakeIntegerNode(map: PositionMap, val number: Int) : ShakeValuedNode(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "IntegerNode", "value" to number)
}