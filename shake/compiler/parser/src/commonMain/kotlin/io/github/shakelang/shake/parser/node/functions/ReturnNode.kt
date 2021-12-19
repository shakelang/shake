package io.github.shakelang.shake.parser.node.functions

import io.github.shakelang.shake.parser.node.ValuedNode
import io.github.shakelang.shake.parser.node.Node
import io.github.shakelang.parseutils.characters.position.PositionMap

class ReturnNode(map: PositionMap, val value: ValuedNode) : Node(map) {

    override fun toJson(): Map<String, *> = mapOf("name" to "ReturnNode", "value" to value.json)

}