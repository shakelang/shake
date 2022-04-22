package io.github.shakelang.shake.parser.node.functions

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.ShakeFileChildNodeImpl
import io.github.shakelang.shake.parser.node.ShakeTree
import io.github.shakelang.shake.parser.node.ShakeVariableType
import kotlin.jvm.JvmOverloads

class ShakeFunctionDeclarationNode @JvmOverloads constructor(
    map: PositionMap,
    val name: String,
    val body: ShakeTree,
    val args: Array<ShakeFunctionArgumentNode>,
    val type: ShakeVariableType = ShakeVariableType.DYNAMIC,
    val access: ShakeAccessDescriber? = ShakeAccessDescriber.PACKAGE,
    val isInClass: Boolean = false,
    val isStatic: Boolean = false,
    val isFinal: Boolean = false
) : ShakeFileChildNodeImpl(map) {

    constructor(
        map: PositionMap, name: String, body: ShakeTree, args: Array<ShakeFunctionArgumentNode>,
        access: ShakeAccessDescriber?, isInClass: Boolean, isStatic: Boolean, isFinal: Boolean
    ) : this(map, name, body, args, ShakeVariableType.DYNAMIC, access, isInClass, isStatic, isFinal)

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "FunctionDeclarationNode",
            "function_name" to name,
            "args" to args.map { it.json },
            "body" to body.json,
            "type" to type.toString(),
            "access" to access.toString(),
            "is_in_class" to isInClass,
            "is_static" to isStatic,
            "is_final" to isFinal
        )
}