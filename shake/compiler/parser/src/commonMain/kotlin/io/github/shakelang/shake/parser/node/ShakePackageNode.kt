package io.github.shakelang.shake.parser.node

import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakePackageNode(map: PositionMap, val pkg: Array<String>) : ShakeFileChildNodeImpl(map) {

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "ImportNode",
            "package" to pkg
        )
}