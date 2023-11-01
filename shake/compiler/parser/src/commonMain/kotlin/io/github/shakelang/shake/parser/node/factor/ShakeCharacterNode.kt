package io.github.shakelang.shake.parser.node.factor

import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.parser.node.ShakeValuedNodeImpl

class ShakeCharacterNode(map: PositionMap, val value: Char) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "CharacterNode", "value" to "$value")
}