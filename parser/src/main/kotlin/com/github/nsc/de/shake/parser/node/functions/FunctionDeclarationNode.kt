package com.github.nsc.de.shake.parser.node.functions

import com.github.nsc.de.shake.parser.node.VariableType
import com.github.nsc.de.shake.parser.node.ValuedNode
import com.github.nsc.de.shake.parser.node.AccessDescriber
import com.github.nsc.de.shake.parser.node.Tree
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class FunctionDeclarationNode @JvmOverloads constructor(
    map: PositionMap,
    val name: String,
    val body: Tree,
    val args: Array<FunctionArgumentNode>,
    val type: VariableType = VariableType.DYNAMIC,
    val access: AccessDescriber? = AccessDescriber.PACKAGE,
    val isInClass: Boolean = false,
    val isStatic: Boolean = false,
    val isFinal: Boolean = false
) : ValuedNode(map) {

    constructor(
        map: PositionMap, name: String, body: Tree, args: Array<FunctionArgumentNode>,
        access: AccessDescriber?, isInClass: Boolean, isStatic: Boolean, isFinal: Boolean
    ) : this(map, name, body, args, VariableType.DYNAMIC, access, isInClass, isStatic, isFinal)

    override fun toString(): String {
        return "FunctionDeclarationNode{name='$name', body=$body, args=${args.contentToString()}, type=$type"
    }
}