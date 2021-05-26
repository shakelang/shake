package com.github.nsc.de.shake.parser.node.factor

import com.github.nsc.de.shake.parser.node.ValuedNode
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class StringNode(map: PositionMap, val value: String) : ValuedNode(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "StringNode", "number" to value)
}