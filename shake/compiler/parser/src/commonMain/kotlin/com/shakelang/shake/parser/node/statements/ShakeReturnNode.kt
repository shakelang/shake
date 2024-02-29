package com.shakelang.shake.parser.node.statements

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeStatementNodeImpl
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.util.parseutils.characters.position.PositionMap

class ShakeReturnNode(
    map: PositionMap,
    val value: ShakeValuedNode,
    val returnToken: ShakeToken,
) : ShakeStatementNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to nodeName, "value" to value.json)

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeReturnNode) return false

        if (value != other.value) return false

        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeReturnNode) return false

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}
