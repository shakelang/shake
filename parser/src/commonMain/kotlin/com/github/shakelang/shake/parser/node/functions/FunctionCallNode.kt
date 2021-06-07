package com.github.shakelang.shake.parser.node.functions

import com.github.shakelang.shake.parser.node.ValuedNode
import com.github.shakelang.shake.util.characterinput.position.PositionMap

class FunctionCallNode(map: PositionMap, val function: ValuedNode, val args: Array<ValuedNode>) : ValuedNode(map) {

    override fun toJson(): Map<String, *> =
        mapOf("name" to "FunctionCallNode", "function" to function.json, "args" to args.map { it.json })

}