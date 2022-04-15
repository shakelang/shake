package io.github.shakelang.shake.parser.node.variables

import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.shake.parser.node.ShakeIdentifierNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class ShakeVariableUsageNode(map: PositionMap, val variable: ShakeIdentifierNode) : ShakeValuedNode(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "VariableUsageNode", "variable" to variable.json)
}