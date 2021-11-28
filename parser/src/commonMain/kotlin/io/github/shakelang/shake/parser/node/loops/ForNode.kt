package io.github.shakelang.shake.parser.node.loops

import io.github.shakelang.shake.parser.node.Node
import io.github.shakelang.shake.parser.node.Tree
import io.github.shakelang.shake.parser.node.ValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class ForNode(map: PositionMap, val body: Tree, val declaration: Node, val condition: ValuedNode, val round: Node) :
    Node(map) {

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "ForNode",
            "body" to body.json,
            "declaration" to declaration.json,
            "condition" to condition.json,
            "round" to round.json
        )
}