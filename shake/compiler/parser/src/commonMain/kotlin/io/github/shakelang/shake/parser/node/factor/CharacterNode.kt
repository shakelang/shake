package io.github.shakelang.shake.parser.node.factor

import io.github.shakelang.shake.parser.node.ValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class CharacterNode(map: PositionMap, val value: Char) : ValuedNode(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "CharacterNode", "value" to "$value")
}