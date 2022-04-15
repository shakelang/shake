package io.github.shakelang.shake.parser.node.logical

import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

abstract class ShakeLogicalNode(map: PositionMap) : ShakeValuedNode(map) {
    abstract val operator: String?
}