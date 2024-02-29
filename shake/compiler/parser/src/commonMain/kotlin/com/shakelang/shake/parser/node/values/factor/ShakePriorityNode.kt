package com.shakelang.shake.parser.node.values.factor

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.ShakeValuedNodeImpl
import com.shakelang.util.parseutils.characters.position.PositionMap

/**
 * Node for a priority node
 */
class ShakePriorityNode(
    map: PositionMap,

    /**
     * The value of the priority node
     */
    val value: ShakeValuedNode,

    /**
     * The token for the left parenthesis
     */
    val lparenToken: ShakeToken,

    /**
     * The token for the right parenthesis
     */
    val rparenToken: ShakeToken,
) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to nodeName, "value" to value.json)

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakePriorityNode) return false
        if (value != other.value) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakePriorityNode) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}
