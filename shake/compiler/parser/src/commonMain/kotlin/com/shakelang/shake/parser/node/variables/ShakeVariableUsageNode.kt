package com.shakelang.shake.parser.node.variables

import com.shakelang.shake.parser.node.ShakeIdentifierNode
import com.shakelang.shake.parser.node.ShakeValuedNodeImpl
import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeVariableUsageNode(map: PositionMap, val identifier: ShakeIdentifierNode) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to nodeName, "variable" to identifier.json)
}
