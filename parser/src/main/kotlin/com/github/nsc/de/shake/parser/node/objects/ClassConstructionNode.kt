package com.github.nsc.de.shake.parser.node.objects

import com.github.nsc.de.shake.parser.node.ValuedNode
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class ClassConstructionNode(
    map: PositionMap,
    val type: ValuedNode,
    val args: Array<ValuedNode>,
    val newKeywordPosition: Int
) : ValuedNode(map) {

    override fun toJson(): Map<String, *> =
        mapOf("name" to "ClassConstructionNode", "type" to type.json, "args" to args.map { it.json })

}