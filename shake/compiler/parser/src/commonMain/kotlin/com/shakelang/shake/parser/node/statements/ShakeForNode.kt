package com.shakelang.shake.parser.node.statements

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeStatementNode
import com.shakelang.shake.parser.node.ShakeStatementNodeImpl
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.util.parseutils.characters.position.PositionMap

class ShakeForNode(
    map: PositionMap,
    val body: ShakeBlockNode,
    val init: ShakeStatementNode,
    val condition: ShakeValuedNode,
    val update: ShakeStatementNode,
    val forToken: ShakeToken,
    val lparenToken: ShakeToken,
    val semicolon1Token: ShakeToken,
    val semicolon2Token: ShakeToken,
    val rparenToken: ShakeToken,
) :
    ShakeStatementNodeImpl(map) {

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "body" to body.json,
            "declaration" to init.json,
            "condition" to condition.json,
            "round" to update.json,
        )

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeForNode) return false
        if (body != other.body) return false
        if (init != other.init) return false
        if (condition != other.condition) return false
        return update == other.update
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeForNode) return false
        if (body != other.body) return false
        if (init != other.init) return false
        if (condition != other.condition) return false
        if (update != other.update) return false
        if (map != other.map) return false
        return true
    }

    override fun hashCode(): Int {
        var result = body.hashCode()
        result = 31 * result + init.hashCode()
        result = 31 * result + condition.hashCode()
        result = 31 * result + update.hashCode()
        result = 31 * result + map.hashCode()
        return result
    }
}
