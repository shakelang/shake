package com.shakelang.shake.parser.node.loops

import com.shakelang.shake.parser.node.ShakeBlockNode
import com.shakelang.shake.parser.node.ShakeStatementNode
import com.shakelang.shake.parser.node.ShakeStatementNodeImpl
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.util.parseutils.characters.position.PositionMap

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
            "name" to nodeName,
            "body" to body.json,
            "declaration" to declaration.json,
            "condition" to condition.json,
            "round" to round.json
        )
}
