package io.github.shakelang.shake.parser.node.objects

import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.shake.parser.node.ShakeValuedStatementNodeImpl
import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeClassConstructionNode(
    map: PositionMap,
    val type: ShakeValuedNode,
    val args: Array<ShakeValuedNode>,
    val newKeywordPosition: Int
) : ShakeValuedStatementNodeImpl(map) {

    override fun toJson(): Map<String, *> =
        mapOf("name" to "ClassConstructionNode", "type" to type.json, "args" to args.map { it.json })

}