package io.github.shakelang.shake.parser.node.variables

import io.github.shakelang.shake.parser.node.*
import io.github.shakelang.parseutils.characters.position.PositionMap

class VariableAssignmentNode(map: PositionMap, val variable: ValuedNode, val value: ValuedNode, val operatorPosition: Int) :
    ValuedNode(map) {

    override fun toJson(): Map<String, *> = mapOf(
        "name" to "VariableAssignmentNode",
        "variable" to variable.json,
        "value" to value.json
    )
}