package com.github.nsc.de.shake.parser.node.factor

import com.github.nsc.de.shake.parser.node.ValuedNode
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class CharacterNode(map: PositionMap, val value: Char) : ValuedNode(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "CharacterNode", "value" to "$value")
}