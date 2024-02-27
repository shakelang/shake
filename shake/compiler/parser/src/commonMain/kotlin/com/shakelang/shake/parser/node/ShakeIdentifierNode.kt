package com.shakelang.shake.parser.node

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.util.parseutils.characters.position.PositionMap

@Suppress("unused", "MemberVisibilityCanBePrivate")
class ShakeIdentifierNode(
    map: PositionMap,
    val parent: ShakeValuedNode?,
    val nameToken: ShakeToken,
    val dotToken: ShakeToken?,
) : ShakeNodeImpl(map) {

    val name: String get() = nameToken.value ?: throw IllegalStateException("Name token has no value")

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "parent" to (parent?.json),
            "name" to name,
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
