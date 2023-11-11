package com.shakelang.shake.parser.node.functions

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.ShakeValuedStatementNodeImpl
import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeFunctionCallNode(map: PositionMap, val function: ShakeValuedNode, val args: Array<ShakeValuedNode>) :
    ShakeValuedStatementNodeImpl(map) {

    override fun toJson(): Map<String, *> =
        mapOf("name" to "FunctionCallNode", "function" to function.json, "args" to args.map { it.json })
}
