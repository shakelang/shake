package io.github.shakelang.shake.parser.node.functions

import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.ShakeBlockNode
import io.github.shakelang.shake.parser.node.ShakeFileChildNodeImpl
import io.github.shakelang.shake.parser.node.ShakeVariableType
import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap
import kotlin.jvm.JvmOverloads

class ShakeFunctionDeclarationNode @JvmOverloads constructor(
    map: PositionMap,
    val expandedType: ShakeVariableType?,
    val name: String,
    val body: ShakeBlockNode?,
    val args: Array<ShakeFunctionArgumentNode>,
    val type: ShakeVariableType,
    val access: ShakeAccessDescriber?,
    val isStatic: Boolean,
    val isFinal: Boolean,
    val isAbstract: Boolean,
    val isOverride: Boolean,
    val isSynchronized: Boolean,
    val isNative: Boolean,
    val isOperator: Boolean,
    val isInline: Boolean
) : ShakeFileChildNodeImpl(map) {

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "FunctionDeclarationNode",
            "function_name" to name,
            "args" to args.map { it.json },
            "body" to body?.json,
            "type" to type.toString(),
            "access" to access.toString(),
            "is_static" to isStatic,
            "is_final" to isFinal
        )
}
