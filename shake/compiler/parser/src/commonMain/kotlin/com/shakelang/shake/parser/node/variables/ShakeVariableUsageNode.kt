package com.shakelang.shake.parser.node.variables

import com.shakelang.shake.parser.node.ShakeIdentifierNode
import com.shakelang.shake.parser.node.ShakeValuedNodeImpl
import com.shakelang.util.parseutils.characters.position.PositionMap

class ShakeVariableUsageNode(map: PositionMap, val identifier: ShakeIdentifierNode) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to nodeName, "variable" to identifier.json)

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeVariableUsageNode) return false
        return identifier == other.identifier
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeVariableUsageNode) return false
        if (identifier != other.identifier) return false
        if (map != other.map) return false
        return true
    }

    override fun hashCode(): Int {
        var result = identifier.hashCode()
        result = 31 * result + map.hashCode()
        return result
    }
}
