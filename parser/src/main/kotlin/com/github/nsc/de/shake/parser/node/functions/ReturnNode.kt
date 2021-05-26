package com.github.nsc.de.shake.parser.node.functions

import com.github.nsc.de.shake.parser.node.ValuedNode
import com.github.nsc.de.shake.parser.node.Node
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class ReturnNode(map: PositionMap, val value: ValuedNode) : Node(map) {

    override fun toJson(): Map<String, *> = mapOf("name" to "ReturnNode", "value" to value.json)

}