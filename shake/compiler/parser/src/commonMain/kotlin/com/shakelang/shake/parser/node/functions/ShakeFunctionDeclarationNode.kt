package com.shakelang.shake.parser.node.functions

import com.shakelang.shake.parser.node.*
import com.shakelang.shake.util.parseutils.characters.position.PositionMap
import kotlin.jvm.JvmOverloads


class ShakeFunctionParameterNode @JvmOverloads constructor(
    map: PositionMap,
    val name: String,
    val type: ShakeVariableType = ShakeVariableType.DYNAMIC,
    val defaultValue: ShakeValuedNode? = null
) : ShakeNodeImpl(map) {

    override fun toJson(): Map<String, *> =
        mapOf("name" to nodeName, "argument_name" to name, "type" to type.toString())
}

class ShakeFunctionDeclarationNode(
    map: PositionMap,
    val expandedType: ShakeVariableType?,
    val name: String,
    val body: ShakeBlockNode?,
    val args: Array<ShakeFunctionParameterNode>,
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
            "name" to nodeName,
            "function_name" to name,
            "args" to args.map { it.json },
            "body" to body?.json,
            "type" to type.toString(),
            "access" to access.toString(),
            "is_static" to isStatic,
            "is_final" to isFinal
        )
}
