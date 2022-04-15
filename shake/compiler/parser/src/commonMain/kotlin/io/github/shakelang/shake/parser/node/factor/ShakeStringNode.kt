package io.github.shakelang.shake.parser.node.factor

import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class ShakeStringNode(map: PositionMap, val value: String) : ShakeValuedNode(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "StringNode", "number" to value)
}