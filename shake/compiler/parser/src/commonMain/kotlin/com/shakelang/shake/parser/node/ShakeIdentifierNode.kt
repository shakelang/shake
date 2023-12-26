package com.shakelang.shake.parser.node

import com.shakelang.util.parseutils.characters.position.PositionMap

class ShakeIdentifierNode : ShakeNodeImpl {
    val parent: ShakeValuedNode?
    val name: String
    val position: Int

    constructor(map: PositionMap, parent: ShakeValuedNode?, name: String, position: Int) : super(map) {
        this.parent = parent
        this.name = name
        this.position = position
    }

    constructor(map: PositionMap, name: String, position: Int) : super(map) {
        this.position = position
        parent = null
        this.name = name
    }

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "parent" to (parent?.json),
            "name" to name
        )

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeIdentifierNode) return false
        if (parent != other.parent) return false
        if (name != other.name) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeIdentifierNode) return false
        if (parent != other.parent) return false
        if (name != other.name) return false
        if (map != other.map) return false
        return true
    }

    override fun hashCode(): Int {
        var result = parent?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + map.hashCode()
        return result
    }
}
