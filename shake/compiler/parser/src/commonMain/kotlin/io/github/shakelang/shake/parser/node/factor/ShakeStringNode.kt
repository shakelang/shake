package io.github.shakelang.shake.parser.node.factor

import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.parser.node.ShakeValuedNodeImpl

class ShakeStringNode(map: PositionMap, val value: String) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "StringNode", "number" to value)
}