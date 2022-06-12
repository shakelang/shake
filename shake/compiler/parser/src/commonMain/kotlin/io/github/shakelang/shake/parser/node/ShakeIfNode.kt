package io.github.shakelang.shake.parser.node

import io.github.shakelang.parseutils.characters.position.PositionMap

class ShakeIfNode(map: PositionMap, val body: ShakeTree, val elseBody: ShakeTree?, val condition: ShakeValuedNode) : ShakeValuedStatementNodeImpl(map) {

    constructor(map: PositionMap, body: ShakeTree, condition: ShakeValuedNode) : this(map, body, null, condition)

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "IfNode",
            "condition" to condition.json,
            "body" to body.json
        )
}