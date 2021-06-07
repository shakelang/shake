package com.github.shakelang.shake.parser.node.variables

import com.github.shakelang.shake.parser.node.*
import com.github.shakelang.shake.util.characterinput.position.PositionMap

class VariableDivAssignmentNode(
    map: PositionMap,
    val variable: ValuedNode,
    val value: Node,
    val operatorPosition: Int
) : ValuedNode(map) {

    override fun toJson(): Map<String, *> = mapOf(
        "name" to "VariableDivAssignmentNode",
        "variable" to variable.json,
        "value" to value.json
    )

}