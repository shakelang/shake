package com.github.nsc.de.shake.parser.node

import com.github.nsc.de.shake.util.characterinput.position.PositionMap

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