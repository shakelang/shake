package io.github.shakelang.shake.parser.node.objects

import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.ShakeTree
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionArgumentNode
import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.parser.node.ShakeValuedNodeImpl
import kotlin.jvm.JvmOverloads

@Suppress("unused")
class ShakeConstructorDeclarationNode
@JvmOverloads constructor(

    map: PositionMap,
    val name: String?,
    val body: ShakeTree,
    val args: Array<ShakeFunctionArgumentNode>,
    val access: ShakeAccessDescriber? = ShakeAccessDescriber.PACKAGE

) : ShakeValuedNodeImpl(map) {

    @JvmOverloads
    constructor(
        map: PositionMap,
        body: ShakeTree,
        args: Array<ShakeFunctionArgumentNode>,
        access: ShakeAccessDescriber? = ShakeAccessDescriber.PACKAGE
    ) : this(map, null, body, args, access)

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "ConstructorDeclarationNode",
            "function_name" to name,
            "args" to args.map { it.json },
            "body" to body.json,
            "access" to access.toString(),
        )
}