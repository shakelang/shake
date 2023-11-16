package com.shakelang.shake.parser.node.functions

import com.shakelang.shake.parser.node.ShakeStatementNodeImpl
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeReturnNode(map: PositionMap, val value: ShakeValuedNode) : ShakeStatementNodeImpl(map) {

    override fun toJson(): Map<String, *> = mapOf("name" to nodeName, "value" to value.json)
}
