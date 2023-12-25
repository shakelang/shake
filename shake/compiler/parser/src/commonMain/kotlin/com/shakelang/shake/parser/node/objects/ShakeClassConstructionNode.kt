package com.shakelang.shake.parser.node.objects

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.ShakeValuedStatementNodeImpl
import com.shakelang.util.parseutils.characters.position.PositionMap

class ShakeClassConstructionNode(
    map: PositionMap,
    val type: ShakeValuedNode,
    val args: Array<ShakeValuedNode>,
    val newKeywordPosition: Int
) : ShakeValuedStatementNodeImpl(map) {

    override fun toJson(): Map<String, *> =
        mapOf("name" to nodeName, "type" to type.json, "args" to args.map { it.json })

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeClassConstructionNode) return false
        if (type != other.type) return false
        if (!args.contentEquals(other.args)) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeClassConstructionNode) return false
        if (type != other.type) return false
        if (!args.contentEquals(other.args)) return false
        if (map != other.map) return false
        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + args.contentHashCode()
        result = 31 * result + map.hashCode()
        return result
    }
}
