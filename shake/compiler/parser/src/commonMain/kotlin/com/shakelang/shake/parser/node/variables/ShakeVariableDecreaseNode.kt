package com.shakelang.shake.parser.node.variables

import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.shake.parser.node.ShakeValuedStatementNodeImpl
import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeVariableDecreaseNode(map: PositionMap, val variable: ShakeValuedNode, val operatorPosition: Int) :
    ShakeValuedStatementNodeImpl(map) {

    override fun toJson(): Map<String, *> = mapOf("name" to "VariableDecreaseNode", "variable" to variable.json)
}
