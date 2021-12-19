package io.github.shakelang.shake.parser.node.logical

import io.github.shakelang.shake.parser.node.ValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

abstract class LogicalConcatenationNode(map: PositionMap, val left: ValuedNode, val right: ValuedNode) :
    LogicalNode(map)