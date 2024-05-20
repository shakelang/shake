package com.shakelang.shake.parser.node.outer

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeNodeImpl
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.misc.ShakeVariableType
import com.shakelang.util.parseutils.characters.position.PositionMap

/**
 * A node representing a method parameter
 */
@Suppress("MemberVisibilityCanBePrivate")
class ShakeParameterNode(
    map: PositionMap,

    /**
     * The name (token) of the parameter
     */
    val nameToken: ShakeToken,

    /**
     * The colon token
     */
    val colonToken: ShakeToken,

    /**
     * The type of the parameter
     */
    val type: ShakeVariableType,

    /**
     * The default value of the parameter
     */
    val defaultValue: ShakeValuedNode? = null,

    /**
     * The assignment token
     */
    val assignmentToken: ShakeToken? = null,
) : ShakeNodeImpl(map) {

    /**
     * The name of the parameter
     */
    val name: String get() = nameToken.value ?: throw Exception("Name of parameter token is null")

    override fun toJson(): Map<String, *> =
        mapOf("name" to nodeName, "argument_name" to name, "type" to type.json)

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeParameterNode) return false

        if (name != other.name) return false
        if (type != other.type) return false
        if (defaultValue != other.defaultValue) return false

        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeParameterNode) return false

        if (name != other.name) return false
        if (type != other.type) return false
        if (defaultValue != other.defaultValue) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + (defaultValue?.hashCode() ?: 0)
        return result
    }
}
