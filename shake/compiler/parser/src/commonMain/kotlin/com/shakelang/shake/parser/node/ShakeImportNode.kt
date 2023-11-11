package com.shakelang.shake.parser.node

import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeImportNode(map: PositionMap, val import: Array<String>) : ShakeFileChildNodeImpl(map) {

    companion object {
        const val EVERYTHING = "*"
    }

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "ImportNode",
            "import" to import
        )
}