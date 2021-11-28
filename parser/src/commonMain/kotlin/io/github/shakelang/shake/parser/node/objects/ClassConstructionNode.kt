package io.github.shakelang.shake.parser.node.objects

import io.github.shakelang.shake.parser.node.ValuedNode
import io.github.shakelang.parseutils.characters.position.PositionMap

class ClassConstructionNode(
    map: PositionMap,
    val type: ValuedNode,
    val args: Array<ValuedNode>,
    val newKeywordPosition: Int
) : ValuedNode(map) {

    override fun toJson(): Map<String, *> =
        mapOf("name" to "ClassConstructionNode", "type" to type.json, "args" to args.map { it.json })

}