package com.github.shakelang.shake.parser.node.logical

import com.github.shakelang.shake.util.characterinput.position.PositionMap

class LogicalTrueNode(map: PositionMap) : LogicalNode(map) {
    override val operator: String?
        get() = null

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalTrueNode")
}