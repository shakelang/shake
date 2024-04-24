package com.shakelang.shake.parser.node.statements

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.ShakeValuedStatementNodeImpl
import com.shakelang.util.parseutils.characters.position.PositionMap

class ShakeIfNode(
    map: PositionMap,
    val thenBody: ShakeBlockNode,
    val elseBody: ShakeBlockNode?,
    val condition: ShakeValuedNode,
    val ifToken: ShakeToken,
    val lparenToken: ShakeToken,
    val rparenToken: ShakeToken,
    val elseToken: ShakeToken?,
) : ShakeValuedStatementNodeImpl(map) {

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "condition" to condition.json,
            "body" to thenBody.json,
        )

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeIfNode) return false
        if (thenBody != other.thenBody) return false
        if (elseBody != other.elseBody) return false
        return condition == other.condition
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeIfNode) return false
        if (thenBody != other.thenBody) return false
        if (elseBody != other.elseBody) return false
        if (condition != other.condition) return false
        if (map != other.map) return false
        return true
    }

    override fun hashCode(): Int {
        var result = thenBody.hashCode()
        result = 31 * result + (elseBody?.hashCode() ?: 0)
        result = 31 * result + condition.hashCode()
        result = 31 * result + map.hashCode()
        return result
    }
}
