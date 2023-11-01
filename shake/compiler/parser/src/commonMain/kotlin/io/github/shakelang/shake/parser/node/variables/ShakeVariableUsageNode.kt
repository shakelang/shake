package io.github.shakelang.shake.parser.node.variables

import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.parser.node.ShakeIdentifierNode
import io.github.shakelang.shake.parser.node.ShakeValuedNodeImpl

class ShakeVariableUsageNode(map: PositionMap, val variable: ShakeIdentifierNode) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "VariableUsageNode", "variable" to variable.json)
}