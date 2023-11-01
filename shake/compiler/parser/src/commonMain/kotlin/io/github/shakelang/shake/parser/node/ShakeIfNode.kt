package io.github.shakelang.shake.parser.node

import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeIfNode(map: PositionMap, val body: ShakeBlockNode, val elseBody: ShakeBlockNode?, val condition: ShakeValuedNode) : ShakeValuedStatementNodeImpl(map) {

    constructor(map: PositionMap, body: ShakeBlockNode, condition: ShakeValuedNode) : this(map, body, null, condition)

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "IfNode",
            "condition" to condition.json,
            "body" to body.json
        )
}