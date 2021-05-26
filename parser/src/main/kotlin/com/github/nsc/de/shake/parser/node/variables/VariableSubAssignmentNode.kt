package com.github.nsc.de.shake.parser.node.variables

import com.github.nsc.de.shake.parser.node.*
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class VariableSubAssignmentNode(
    map: PositionMap,
    val variable: ValuedNode,
    val value: Node,
    val operatorPosition: Int
) : ValuedNode(map) {

    override fun toJson(): Map<String, *> = mapOf(
        "name" to "VariableSubAssignmentNode",
        "variable" to variable.json,
        "value" to value.json
    )

}