package com.github.shakelang.shake.parser.node

import com.github.shakelang.shake.util.characterinput.position.PositionMap

class IfNode(map: PositionMap, val body: Tree, val elseBody: Tree?, val condition: ValuedNode) : Node(map) {

    constructor(map: PositionMap, body: Tree, condition: ValuedNode) : this(map, body, null, condition)

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "IfNode",
            "condition" to condition.json,
            "body" to body.json
        )
}