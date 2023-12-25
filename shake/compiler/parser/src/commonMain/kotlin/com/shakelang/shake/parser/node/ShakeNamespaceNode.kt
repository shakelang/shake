package com.shakelang.shake.parser.node

import com.shakelang.util.parseutils.characters.position.PositionMap

class ShakeNamespaceNode(
    map: PositionMap,
    val parts: Array<String>
) : ShakeNodeImpl(map) {
    override fun toJson(): Map<String, *> {
        return mapOf(
            "type" to nodeName,
            "parts" to parts
        )
    }

    override fun toString(): String {
        return parts.joinToString(".")
    }

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeNamespaceNode) return false
        if (!parts.contentEquals(other.parts)) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeNamespaceNode) return false
        if (!parts.contentEquals(other.parts)) return false
        if (map != other.map) return false
        return true
    }

    override fun hashCode(): Int {
        var result = parts.contentHashCode()
        result = 31 * result + map.hashCode()
        return result
    }
}
