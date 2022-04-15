package io.github.shakelang.shake.parser.node.loops

import io.github.shakelang.shake.parser.node.ShakeNode
import io.github.shakelang.shake.parser.node.ShakeTree
import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class ShakeWhileNode(map: PositionMap, val body: ShakeTree, val condition: ShakeValuedNode) : ShakeNode(map) {

    override fun toJson(): Map<String, *> =
        mapOf("name" to "DoWhileNode", "body" to body.json, "condition" to condition.json)

}