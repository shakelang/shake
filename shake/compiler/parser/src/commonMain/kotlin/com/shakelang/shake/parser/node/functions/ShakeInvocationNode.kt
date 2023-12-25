package com.shakelang.shake.parser.node.functions

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.ShakeValuedStatementNodeImpl
import com.shakelang.util.parseutils.characters.position.PositionMap

class ShakeInvocationNode(map: PositionMap, val function: ShakeValuedNode, val args: Array<ShakeValuedNode>) :
    ShakeValuedStatementNodeImpl(map) {

    override fun toJson(): Map<String, *> =
        mapOf("name" to nodeName, "function" to function.json, "args" to args.map { it.json })

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeInvocationNode) return false

        if (function != other.function) return false
        if (!args.mapIndexed { index, node -> node.equalsIgnorePosition(other.args[index]) }.all { it }) return false

        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeInvocationNode) return false

        if (function != other.function) return false
        if (!args.contentEquals(other.args)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + function.hashCode()
        result = 31 * result + args.contentHashCode()
        return result
    }
}
