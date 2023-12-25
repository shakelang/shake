package com.shakelang.shake.parser.node.loops

import com.shakelang.shake.parser.node.ShakeBlockNode
import com.shakelang.shake.parser.node.ShakeStatementNodeImpl
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.util.parseutils.characters.position.PositionMap

class ShakeDoWhileNode(map: PositionMap, val body: ShakeBlockNode, val condition: ShakeValuedNode) :
    ShakeStatementNodeImpl(map) {

    override fun toJson(): Map<String, *> =
        mapOf("name" to nodeName, "body" to body.json, "condition" to condition.json)

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeDoWhileNode) return false
        if (body != other.body) return false
        return condition == other.condition
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeDoWhileNode) return false
        if (body != other.body) return false
        if (condition != other.condition) return false
        if (map != other.map) return false
        return true
    }

    override fun hashCode(): Int {
        var result = body.hashCode()
        result = 31 * result + condition.hashCode()
        result = 31 * result + map.hashCode()
        return result
    }
}
