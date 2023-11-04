package io.github.shakelang.shake.parser.node.logical

import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeLogicalSmallerNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode) :
    ShakeLogicalCompareNode(map, left, right) {
    override val operator: String
        get() = "<"

    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalSmallerNode", "left" to left, "right" to right)
}