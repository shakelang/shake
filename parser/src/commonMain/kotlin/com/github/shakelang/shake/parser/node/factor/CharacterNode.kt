package com.github.shakelang.shake.parser.node.factor

import com.github.shakelang.shake.parser.node.ValuedNode
import com.github.shakelang.shake.util.characterinput.position.PositionMap

class CharacterNode(map: PositionMap, val value: Char) : ValuedNode(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "CharacterNode", "value" to "$value")
}