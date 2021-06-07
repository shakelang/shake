package com.github.shakelang.shake.parser.node.logical

import com.github.shakelang.shake.parser.node.ValuedNode
import com.github.shakelang.shake.util.characterinput.position.PositionMap

abstract class LogicalCompareNode(map: PositionMap, val left: ValuedNode, val right: ValuedNode) : LogicalNode(map)