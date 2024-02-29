package com.shakelang.shake.parser.node.values

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.ShakeValuedNodeImpl
import com.shakelang.shake.parser.node.misc.ShakeVariableType
import com.shakelang.util.parseutils.characters.position.PositionMap

@Suppress("unused")
class ShakeCastNode(map: PositionMap, val value: ShakeValuedNode, val castTarget: ShakeVariableType) :
    ShakeValuedNodeImpl(map) {

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "value" to value.json,
            "cast_target" to castTarget.json,
        )

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeCastNode) return false
        if (value != other.value) return false
        return castTarget == other.castTarget
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeCastNode) return false
        if (value != other.value) return false
        if (castTarget != other.castTarget) return false
        if (map != other.map) return false
        return true
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + castTarget.hashCode()
        result = 31 * result + map.hashCode()
        return result
    }
}
