package io.github.shakelang.shake.parser.node.logical

import io.github.shakelang.parseutils.characters.position.PositionMap

class LogicalFalseNode(map: PositionMap) : LogicalNode(map) {
    override val operator: String?
        get() = null

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalFalseNode")
}