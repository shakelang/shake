package com.shakelang.shake.parser.node.variables

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.ShakeValuedStatementNodeImpl
import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeVariableIncreaseNode(map: PositionMap, val variable: ShakeValuedNode, val operatorPosition: Int) :
    ShakeValuedStatementNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to nodeName, "variable" to variable.json)

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeVariableIncreaseNode) return false
        if (variable != other.variable) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeVariableIncreaseNode) return false
        if (variable != other.variable) return false
        if (operatorPosition != other.operatorPosition) return false
        if (map != other.map) return false
        return true
    }

    override fun hashCode(): Int {
        var result = variable.hashCode()
        result = 31 * result + operatorPosition
        result = 31 * result + map.hashCode()
        return result
    }
}
