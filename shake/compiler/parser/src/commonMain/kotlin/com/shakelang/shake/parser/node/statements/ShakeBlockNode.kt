package com.shakelang.shake.parser.node.statements

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeNodeImpl
import com.shakelang.shake.parser.node.ShakeStatementNode
import com.shakelang.util.parseutils.characters.position.PositionMap

class ShakeBlockNode(
    map: PositionMap,
    val children: Array<ShakeStatementNode>,
    val lcurlyToken: ShakeToken?,
    val rcurlyToken: ShakeToken?,
) : ShakeNodeImpl(map) {

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "children" to children.map { it.json },
        )

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeBlockNode) return false
        if (!children.contentEquals(other.children)) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeBlockNode) return false
        if (!children.contentEquals(other.children)) return false
        if (map != other.map) return false
        return true
    }

    override fun hashCode(): Int {
        var result = children.contentHashCode()
        result = 31 * result + map.hashCode()
        return result
    }
}
