package com.github.nsc.de.shake.parser.node.logical

import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class LogicalTrueNode(map: PositionMap) : LogicalNode(map) {
    override val operator: String?
        get() = null

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalTrueNode")
}