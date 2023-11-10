package io.github.shakelang.shake.parser.node.logical

import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeLogicalFalseNode(map: PositionMap) : ShakeLogicalNode(map) {
    override val operator: String?
        get() = null

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalFalseNode")
}
