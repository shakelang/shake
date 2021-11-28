package io.github.shakelang.shake.parser.node.loops

import io.github.shakelang.shake.parser.node.Node
import io.github.shakelang.shake.parser.node.Tree
import io.github.shakelang.shake.parser.node.ValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class DoWhileNode(map: PositionMap, val body: Tree, val condition: ValuedNode) : Node(map) {

    override fun toJson(): Map<String, *> =
        mapOf("name" to "DoWhileNode", "body" to body.json, "condition" to condition.json)

}