package com.github.nsc.de.shake.parser.node.factor

import com.github.nsc.de.shake.parser.node.ValuedNode
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class IntegerNode(map: PositionMap, val number: Int) : ValuedNode(map) {
    override fun toString(): String = number.toString()
}