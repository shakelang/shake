package com.github.nsc.de.shake.parser.node.variables

import com.github.nsc.de.shake.parser.node.ValuedNode
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class VariableDecreaseNode(map: PositionMap, val variable: ValuedNode, val operatorPosition: Int) : ValuedNode(map) {

    override fun toJson(): Map<String, *> = mapOf("name" to "VariableDecreaseNode", "variable" to variable.json)

}