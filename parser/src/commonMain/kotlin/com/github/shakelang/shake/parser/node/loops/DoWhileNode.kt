package com.github.shakelang.shake.parser.node.loops

import com.github.shakelang.shake.parser.node.Node
import com.github.shakelang.shake.parser.node.Tree
import com.github.shakelang.shake.parser.node.ValuedNode
import com.github.shakelang.shake.util.characterinput.position.PositionMap

class DoWhileNode(map: PositionMap, val body: Tree, val condition: ValuedNode) : Node(map) {

    override fun toJson(): Map<String, *> =
        mapOf("name" to "DoWhileNode", "body" to body.json, "condition" to condition.json)

}