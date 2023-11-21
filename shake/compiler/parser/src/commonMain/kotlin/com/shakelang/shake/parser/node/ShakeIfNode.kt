package com.shakelang.shake.parser.node

import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeIfNode(
    map: PositionMap,
    val body: ShakeBlockNode,
    val elseBody: ShakeBlockNode?,
    val condition: ShakeValuedNode
) : ShakeValuedStatementNodeImpl(map) {

    constructor(map: PositionMap, body: ShakeBlockNode, condition: ShakeValuedNode) : this(map, body, null, condition)

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "condition" to condition.json,
            "body" to body.json
        )

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeIfNode) return false
        if (body != other.body) return false
        if (elseBody != other.elseBody) return false
        return condition == other.condition
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeIfNode) return false
        if (body != other.body) return false
        if (elseBody != other.elseBody) return false
        if (condition != other.condition) return false
        if (map != other.map) return false
        return true
    }

    override fun hashCode(): Int {
        var result = body.hashCode()
        result = 31 * result + (elseBody?.hashCode() ?: 0)
        result = 31 * result + condition.hashCode()
        result = 31 * result + map.hashCode()
        return result
    }
}
