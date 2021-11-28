package io.github.shakelang.shake.parser.node.variables

import io.github.shakelang.shake.parser.node.ValuedNode
import io.github.shakelang.shake.parser.node.IdentifierNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class VariableUsageNode(map: PositionMap, val variable: IdentifierNode) : ValuedNode(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "VariableUsageNode", "variable" to variable.json)
}