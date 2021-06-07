package com.github.shakelang.shake.parser.node

import com.github.shakelang.shake.util.characterinput.position.PositionMap

class ImportNode(map: PositionMap, val import: Array<String>) : Node(map) {

    companion object {
        const val EVERYTHING = "*"
    }

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "ImportNode",
            "import" to import
        )
}