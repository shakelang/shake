package io.github.shakelang.shake.parser.node

import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeIdentifierNode : ShakeValuedNodeImpl {
    val parent: ShakeValuedNode?
    val name: String
    val position: Int

    constructor(map: PositionMap, parent: ShakeValuedNode?, name: String, position: Int) : super(map) {
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
