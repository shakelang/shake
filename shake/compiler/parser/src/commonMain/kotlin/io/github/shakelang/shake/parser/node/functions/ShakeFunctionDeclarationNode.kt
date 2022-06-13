package io.github.shakelang.shake.parser.node.functions

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.ShakeBlockNode
import io.github.shakelang.shake.parser.node.ShakeFileChildNodeImpl
import io.github.shakelang.shake.parser.node.ShakeVariableType
import kotlin.jvm.JvmOverloads

class ShakeFunctionDeclarationNode @JvmOverloads constructor(
    map: PositionMap,
    val name: String,
    val body: ShakeBlockNode,
    val args: Array<ShakeFunctionArgumentNode>,
    val type: ShakeVariableType = ShakeVariableType.DYNAMIC,
    val access: ShakeAccessDescriber? = ShakeAccessDescriber.PACKAGE,
    val isStatic: Boolean = false,
    val isFinal: Boolean = false,
    val isAbstract: Boolean = false,
) : ShakeFileChildNodeImpl(map) {

    constructor(
        map: PositionMap,
        name: String,
        body: ShakeBlockNode,
        args: Array<ShakeFunctionArgumentNode>,
        access: ShakeAccessDescriber?,
        isInClass: Boolean,
        isStatic: Boolean,
        isFinal: Boolean,
        isNative: Boolean,
    ) : this(map, name, body, args, ShakeVariableType.DYNAMIC, access, isInClass, isStatic, isFinal)

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "FunctionDeclarationNode",
            "function_name" to name,
            "args" to args.map { it.json },
            "body" to body.json,
            "type" to type.toString(),
            "access" to access.toString(),
            "is_static" to isStatic,
            "is_final" to isFinal
        )
}