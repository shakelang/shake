package com.github.nsc.de.shake.parser.node.variables

import com.github.nsc.de.shake.parser.node.*
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class VariableAssignmentNode(map: PositionMap, val variable: ValuedNode, val value: Node, val operatorPosition: Int) :
    ValuedNode(map) {

    override fun toJson(): Map<String, *> = mapOf(
        "name" to "VariableAssignmentNode",
        "variable" to variable.json,
        "value" to value.json
    )
}