package io.github.shakelang.shake.parser.node.objects

import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.ShakeBlockNode
import io.github.shakelang.shake.parser.node.ShakeValuedNodeImpl
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionArgumentNode
import kotlin.jvm.JvmOverloads

@Suppress("unused")
class ShakeConstructorDeclarationNode
@JvmOverloads constructor(

    map: PositionMap,
    val name: String?,
    val body: ShakeBlockNode,
    val args: Array<ShakeFunctionArgumentNode>,
    val access: ShakeAccessDescriber? = ShakeAccessDescriber.PACKAGE,
    val isNative: Boolean,
    val isSynchronized: Boolean

) : ShakeValuedNodeImpl(map) {

    @JvmOverloads
    constructor(
        map: PositionMap,
        body: ShakeBlockNode,
        args: Array<ShakeFunctionArgumentNode>,
        access: ShakeAccessDescriber? = ShakeAccessDescriber.PACKAGE,
        isNative: Boolean,
        isSynchronized: Boolean
    ) : this(map, null, body, args, access, isNative, isSynchronized)

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "ConstructorDeclarationNode",
            "function_name" to name,
            "args" to args.map { it.json },
            "body" to body.json,
            "access" to access.toString(),
        )
}