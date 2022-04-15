package io.github.shakelang.shake.parser.node.factor

import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class ShakeCharacterNode(map: PositionMap, val value: Char) : ShakeValuedNode(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "CharacterNode", "value" to "$value")
}