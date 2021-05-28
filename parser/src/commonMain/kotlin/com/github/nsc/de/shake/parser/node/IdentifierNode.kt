package com.github.nsc.de.shake.parser.node

import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class IdentifierNode : ValuedNode {
    val parent: ValuedNode?
    val name: String
    val position: Int

    constructor(map: PositionMap, parent: ValuedNode?, name: String, position: Int) : super(map) {
        this.parent = parent
        this.name = name
        this.position = position
    }

    constructor(map: PositionMap, name: String, position: Int) : super(map) {
        this.position = position
        parent = null
        this.name = name
    }

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "IdentifierNode",
            "parent" to (parent?.json),
            "name" to name
        )
}