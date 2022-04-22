package io.github.shakelang.shake.parser.node.loops

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.parser.node.ShakeStatementNodeImpl
import io.github.shakelang.shake.parser.node.ShakeTree
import io.github.shakelang.shake.parser.node.ShakeValuedNode

class ShakeDoWhileNode(map: PositionMap, val body: ShakeTree, val condition: ShakeValuedNode) : ShakeStatementNodeImpl(map) {

    override fun toJson(): Map<String, *> =
        mapOf("name" to "DoWhileNode", "body" to body.json, "condition" to condition.json)

}