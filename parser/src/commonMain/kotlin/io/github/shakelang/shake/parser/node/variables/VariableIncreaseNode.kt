package io.github.shakelang.shake.parser.node.variables

import io.github.shakelang.shake.parser.node.ValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class VariableIncreaseNode(map: PositionMap, val variable: ValuedNode, val operatorPosition: Int) : ValuedNode(map) {

    override fun toJson(): Map<String, *> = mapOf("name" to "VariableModAssignmentNode", "variable" to variable.json)

}