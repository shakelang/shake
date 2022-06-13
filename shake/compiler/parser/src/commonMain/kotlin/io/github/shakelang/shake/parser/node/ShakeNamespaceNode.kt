package io.github.shakelang.shake.parser.node

import io.github.shakelang.parseutils.characters.position.PositionMap

class ShakeNamespaceNode (
    map: PositionMap,
    val parts: Array<String>
) : ShakeNodeImpl(map) {
    override fun toJson(): Map<String, *> {
        return mapOf(
            "type" to "namespace",
            "parts" to parts
        )
    }
}