package com.shakelang.shake.parser.node.loops

import com.shakelang.shake.parser.node.ShakeBlockNode
import com.shakelang.shake.parser.node.ShakeStatementNodeImpl
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeDoWhileNode(map: PositionMap, val body: ShakeBlockNode, val condition: ShakeValuedNode) :
    ShakeStatementNodeImpl(map) {

    override fun toJson(): Map<String, *> =
        mapOf("name" to "DoWhileNode", "body" to body.json, "condition" to condition.json)
}
