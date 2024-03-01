package com.shakelang.shake.parser.node.outer

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeFileChildNodeImpl
import com.shakelang.shake.parser.node.misc.ShakeNamespaceNode
import com.shakelang.util.parseutils.characters.position.PositionMap

class ShakePackageNode(map: PositionMap, val namespace: ShakeNamespaceNode, val packageToken: ShakeToken) : ShakeFileChildNodeImpl(map) {

    val pkg: Array<String> = namespace.toArray()

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "package" to pkg,
        )

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakePackageNode) return false
        if (!pkg.contentEquals(other.pkg)) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakePackageNode) return false
        if (!pkg.contentEquals(other.pkg)) return false
        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + pkg.contentHashCode()
        return result
    }
}
