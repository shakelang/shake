package com.github.nsc.de.shake.parser.node.objects

import com.github.nsc.de.shake.parser.node.ValuedNode
import com.github.nsc.de.shake.parser.node.AccessDescriber
import com.github.nsc.de.shake.parser.node.Tree
import com.github.nsc.de.shake.parser.node.functions.FunctionArgumentNode
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

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