package io.github.shakelang.shake.parser.node.functions

import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.shake.parser.node.ShakeNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class ShakeReturnNode(map: PositionMap, val value: ShakeValuedNode) : ShakeNode(map) {

    override fun toJson(): Map<String, *> = mapOf("name" to "ReturnNode", "value" to value.json)

}