package io.github.shakelang.shake.parser.node.functions

import io.github.shakelang.shake.parser.node.ShakeNodeImpl
import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.shake.parser.node.ShakeVariableType
import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap
import kotlin.jvm.JvmOverloads

class ShakeFunctionArgumentNode @JvmOverloads constructor(
    map: PositionMap,
    val name: String,
    val type: ShakeVariableType = ShakeVariableType.DYNAMIC,
    val defaultValue: ShakeValuedNode? = null
) : ShakeNodeImpl(map) {

    override fun toJson(): Map<String, *> =
        mapOf("name" to "FunctionArgumentNode", "argument_name" to name, "type" to type.toString())
}
