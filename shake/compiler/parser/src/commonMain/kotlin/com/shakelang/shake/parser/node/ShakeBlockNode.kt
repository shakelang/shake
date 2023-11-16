package com.shakelang.shake.parser.node

import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeBlockNode(map: PositionMap, val children: Array<ShakeStatementNode>) : ShakeNodeImpl(map) {

    constructor(map: PositionMap, children: List<ShakeStatementNode>) : this(map, children.toTypedArray())

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "children" to children.map { it.json }
        )
}
