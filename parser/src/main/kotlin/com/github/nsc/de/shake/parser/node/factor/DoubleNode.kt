package com.github.nsc.de.shake.parser.node.factor

import com.github.nsc.de.shake.parser.node.ValuedNode
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class DoubleNode(map: PositionMap, val number: Double) : ValuedNode(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "DoubleNode", "value" to number)
}