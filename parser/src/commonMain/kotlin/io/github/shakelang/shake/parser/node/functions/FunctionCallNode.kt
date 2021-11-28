package io.github.shakelang.shake.parser.node.functions

import io.github.shakelang.shake.parser.node.ValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class FunctionCallNode(map: PositionMap, val function: ValuedNode, val args: Array<ValuedNode>) : ValuedNode(map) {

    override fun toJson(): Map<String, *> =
        mapOf("name" to "FunctionCallNode", "function" to function.json, "args" to args.map { it.json })

}