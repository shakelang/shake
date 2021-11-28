package io.github.shakelang.shake.parser.node.functions

import io.github.shakelang.shake.parser.node.VariableType
import io.github.shakelang.shake.parser.node.Node
import io.github.shakelang.parseutils.characters.position.PositionMap
import kotlin.jvm.JvmOverloads

class FunctionArgumentNode @JvmOverloads constructor(
    map: PositionMap,
    val name: String,
    private val type: VariableType = VariableType.DYNAMIC
) : Node(map) {

    override fun toJson(): Map<String, *> =
        mapOf("name" to "FunctionArgumentNode", "argument_name" to name, "type" to type.toString())

}