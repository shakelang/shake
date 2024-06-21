package com.shakelang.shake.parser.node.misc

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeNodeImpl
import com.shakelang.shake.parser.node.values.ShakeVariableUsageNode
import com.shakelang.util.parseutils.characters.position.PositionMap

class ShakeNamespaceNode(
    map: PositionMap,
    val nameToken: ShakeToken,
    val parent: ShakeNamespaceNode?,
    val dotToken: ShakeToken?,
) : ShakeNodeImpl(map) {

    val name: String
        get() = nameToken.value

    fun toType(): ShakeVariableType = ShakeVariableType(this, null, null, null, null)

    fun toIdentifier(): ShakeIdentifierNode {
        if (parent == null) return ShakeIdentifierNode(map, null, nameToken, null)
        return ShakeIdentifierNode(map, parent.toValue(), nameToken, dotToken)
    }

    fun toValue(): ShakeVariableUsageNode = ShakeVariableUsageNode(map, toIdentifier())

    fun toArray(): Array<String> = parent?.toArray()?.plus(name) ?: arrayOf(name)

    fun stringify(): String = toArray().joinToString(".")

    override fun toJson(): Map<String, *> = mapOf(
        "type" to nodeName,
        "name" to nameToken.value,
        "parent" to parent?.json,
    )

    override fun toString(): String = (parent?.toString()?.plus(".") ?: "") + nameToken.value

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
