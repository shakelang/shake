package com.shakelang.shake.parser.node

import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakePackageNode(map: PositionMap, val pkg: Array<String>) : ShakeFileChildNodeImpl(map) {

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "package" to pkg
        )
}
