package io.github.shakelang.shake.parser.node

import io.github.shakelang.parseutils.characters.position.PositionMap

class ShakeTree(map: PositionMap, val children: Array<ShakeNode>) : ShakeNodeImpl(map) {

    constructor(map: PositionMap, children: List<ShakeNode>) : this(map, children.toTypedArray())

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "Tree",
            "children" to children.map { it.json }
        )
}