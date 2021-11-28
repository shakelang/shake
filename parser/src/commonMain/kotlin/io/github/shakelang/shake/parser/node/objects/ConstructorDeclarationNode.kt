package io.github.shakelang.shake.parser.node.objects

import io.github.shakelang.shake.parser.node.ValuedNode
import io.github.shakelang.shake.parser.node.AccessDescriber
import io.github.shakelang.shake.parser.node.Tree
import io.github.shakelang.shake.parser.node.functions.FunctionArgumentNode
import io.github.shakelang.parseutils.characters.position.PositionMap
import kotlin.jvm.JvmOverloads

@Suppress("unused")
class ConstructorDeclarationNode
@JvmOverloads constructor(

    map: PositionMap,
    val name: String?,
    val body: Tree,
    val args: Array<FunctionArgumentNode>,
    val access: AccessDescriber? = AccessDescriber.PACKAGE

) : ValuedNode(map) {

    @JvmOverloads
    constructor(
        map: PositionMap,
        body: Tree,
        args: Array<FunctionArgumentNode>,
        access: AccessDescriber? = AccessDescriber.PACKAGE
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