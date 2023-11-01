package io.github.shakelang.shake.parser.node.expression

import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.parser.node.ShakeValuedNode

class ShakeModNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: Char
        get() = '%'

    override fun toJson(): Map<String, *> = mapOf("name" to "ModNode", "left" to left, "right" to right)
}