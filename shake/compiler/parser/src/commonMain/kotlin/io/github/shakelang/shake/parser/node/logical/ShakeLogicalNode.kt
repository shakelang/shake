package io.github.shakelang.shake.parser.node.logical

import io.github.shakelang.shake.parser.node.ShakeValuedNodeImpl
import io.github.shakelang.parseutils.characters.position.PositionMap

abstract class ShakeLogicalNode(map: PositionMap) : ShakeValuedNodeImpl(map) {
    abstract val operator: String?
}