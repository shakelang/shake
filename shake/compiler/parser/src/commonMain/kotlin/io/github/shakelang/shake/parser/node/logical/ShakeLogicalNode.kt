package io.github.shakelang.shake.parser.node.logical

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.parser.node.ShakeValuedNodeImpl

abstract class ShakeLogicalNode(map: PositionMap) : ShakeValuedNodeImpl(map) {
    abstract val operator: String?
}