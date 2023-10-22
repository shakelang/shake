package io.github.shakelang.shake.parser.node

import io.github.shakelang.parseutils.characters.position.PositionMap

class ShakeBlockNode(map: PositionMap, val children: Array<ShakeStatementNode>) : ShakeNodeImpl(map) {

    constructor(map: PositionMap, children: List<ShakeStatementNode>) : this(map, children.toTypedArray())

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "Tree",
            "children" to children.map { it.json }
        )
}