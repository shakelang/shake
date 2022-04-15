package io.github.shakelang.shake.parser.node.loops

import io.github.shakelang.shake.parser.node.ShakeNode
import io.github.shakelang.shake.parser.node.ShakeTree
import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class ShakeForNode(map: PositionMap, val body: ShakeTree, val declaration: ShakeNode, val condition: ShakeValuedNode, val round: ShakeNode) :
    ShakeNode(map) {

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "ForNode",
            "body" to body.json,
            "declaration" to declaration.json,
            "condition" to condition.json,
            "round" to round.json
        )
}