package io.github.shakelang.shake.parser.node.loops

import io.github.shakelang.shake.parser.node.ShakeBlockNode
import io.github.shakelang.shake.parser.node.ShakeStatementNode
import io.github.shakelang.shake.parser.node.ShakeStatementNodeImpl
import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeForNode(
    map: PositionMap,
    val body: ShakeBlockNode,
    val declaration: ShakeStatementNode,
    val condition: ShakeValuedNode,
    val round: ShakeStatementNode
) :
    ShakeStatementNodeImpl(map) {

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "ForNode",
            "body" to body.json,
            "declaration" to declaration.json,
            "condition" to condition.json,
            "round" to round.json
        )
}