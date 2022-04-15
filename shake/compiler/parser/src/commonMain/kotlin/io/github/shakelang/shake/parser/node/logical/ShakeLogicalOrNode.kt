package io.github.shakelang.shake.parser.node.logical

import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class ShakeLogicalOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode) :
    ShakeLogicalConcatenationNode(map, left, right) {
    override val operator: String
        get() = "||"

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalOrNode", "left" to left, "right" to right)
}