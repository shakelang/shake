package io.github.shakelang.shake.parser.node.logical

import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class ShakeLogicalAndNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode) :
    ShakeLogicalConcatenationNode(map, left, right) {
    override val operator: String
        get() = "&&"

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalAndNode", "left" to left, "right" to right)
}