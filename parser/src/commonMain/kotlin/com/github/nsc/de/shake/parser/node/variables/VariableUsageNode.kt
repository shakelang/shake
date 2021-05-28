package com.github.nsc.de.shake.parser.node.variables

import com.github.nsc.de.shake.parser.node.ValuedNode
import com.github.nsc.de.shake.parser.node.IdentifierNode
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class VariableUsageNode(map: PositionMap, val variable: IdentifierNode) : ValuedNode(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "VariableUsageNode", "variable" to variable.json)
}