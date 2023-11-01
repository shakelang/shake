package io.github.shakelang.shake.parser.node.logical

import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.parser.node.ShakeValuedNode

class ShakeLogicalAndNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode) :
    ShakeLogicalConcatenationNode(map, left, right) {
    override val operator: String
        get() = "&&"

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalAndNode", "left" to left, "right" to right)
}