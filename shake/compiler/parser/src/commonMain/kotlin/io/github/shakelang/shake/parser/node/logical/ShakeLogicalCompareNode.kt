package io.github.shakelang.shake.parser.node.logical

import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap

abstract class ShakeLogicalCompareNode(map: PositionMap, val left: ShakeValuedNode, val right: ShakeValuedNode) :
    ShakeLogicalNode(map)