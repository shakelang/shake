package com.shakelang.shake.parser.node.misc

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeNodeImpl
import com.shakelang.util.parseutils.characters.position.PositionMap

class ShakeNamespaceNode(
    map: PositionMap,
    val nameToken: ShakeToken,
    val parent: ShakeNamespaceNode?,
    val dotToken: ShakeToken?,
) : ShakeNodeImpl(map) {

    val name: String
        get() = nameToken.value ?: throw NullPointerException("Name token value is null")

    fun toType(): ShakeVariableType {
        return ShakeVariableType(this)
    }

    fun toArray(): Array<String> {
        return parent?.toArray()?.plus(name) ?: arrayOf(name)
    }

    override fun toJson(): Map<String, *> {
        return mapOf(
            "type" to nodeName,
            "name" to nameToken.value,
            "parent" to parent?.json,
        )
    }

    override fun toString(): String {
        return (parent?.toString()?.plus(".") ?: "") + nameToken.value
    }

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeNamespaceNode) return false
        if (!nameToken.value.contentEquals(other.nameToken.value)) return false
        if (parent?.equalsIgnorePosition(other.parent) == true) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeNamespaceNode) return false
        if (!nameToken.value.contentEquals(other.nameToken.value)) return false
        if (parent?.equals(other.parent) == true) return false
        return true
    }

    override fun hashCode(): Int {
        var result = nameToken.value.hashCode()
        result = 31 * result + (parent?.hashCode() ?: 0)
        return result
    }
}
