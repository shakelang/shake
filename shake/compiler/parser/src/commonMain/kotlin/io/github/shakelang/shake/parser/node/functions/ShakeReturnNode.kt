package io.github.shakelang.shake.parser.node.functions

import io.github.shakelang.shake.parser.node.ShakeStatementNodeImpl
import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeReturnNode(map: PositionMap, val value: ShakeValuedNode) : ShakeStatementNodeImpl(map) {

    override fun toJson(): Map<String, *> = mapOf("name" to "ReturnNode", "value" to value.json)

}