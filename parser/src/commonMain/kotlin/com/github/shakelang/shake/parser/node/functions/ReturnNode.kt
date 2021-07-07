package com.github.shakelang.shake.parser.node.functions

import com.github.shakelang.shake.parser.node.ValuedNode
import com.github.shakelang.shake.parser.node.Node
import com.github.shakelang.shake.util.characterinput.position.PositionMap

class ReturnNode(map: PositionMap, val value: ValuedNode) : Node(map) {

    override fun toJson(): Map<String, *> = mapOf("name" to "ReturnNode", "value" to value.json)

}