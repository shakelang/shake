package com.github.nsc.de.shake.parser.node.factor

import com.github.nsc.de.shake.parser.node.ValuedNode
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class PriorityNode(map: PositionMap, val value: ValuedNode) : ValuedNode(map) {
    override fun toString(): String = "PriorityNode{value=$value}"
}