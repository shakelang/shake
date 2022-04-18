package io.github.shakelang.shake.parser.node.logical

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.parser.node.ShakeValuedNode

abstract class ShakeLogicalCompareNode(map: PositionMap, val left: ShakeValuedNode, val right: ShakeValuedNode) : ShakeLogicalNode(map)