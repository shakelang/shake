package io.github.shakelang.shake.parser.node.logical

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.parser.node.ShakeValuedNode

class ShakeLogicalSmallerNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode) :
    ShakeLogicalCompareNode(map, left, right) {
    override val operator: String
        get() = "<"

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalSmallerNode", "left" to left, "right" to right)
}