package com.github.shakelang.shake.parser.node.variables

import com.github.shakelang.shake.parser.node.ValuedNode
import com.github.shakelang.shake.util.characterinput.position.PositionMap

class VariableIncreaseNode(map: PositionMap, val variable: ValuedNode, val operatorPosition: Int) : ValuedNode(map) {

    override fun toJson(): Map<String, *> = mapOf("name" to "VariableModAssignmentNode", "variable" to variable.json)

}