package com.github.shakelang.shake.parser.node

import com.github.shakelang.shake.util.characterinput.position.PositionMap

class Tree(map: PositionMap, val children: Array<Node>) : Node(map) {

    constructor(map: PositionMap, children: List<Node>) : this(map, children.toTypedArray())

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "Tree",
            "children" to children.map { it.json }
        )
}