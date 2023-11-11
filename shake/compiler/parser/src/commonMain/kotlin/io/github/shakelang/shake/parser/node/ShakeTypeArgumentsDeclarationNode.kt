package io.github.shakelang.shake.parser.node

import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeTypeArgumentsDeclarationNode(

    map: PositionMap,
    val typeArguments: Array<ShakeTypeArgumentDeclarationNode>

) : ShakeNodeImpl(map) {
    override fun toJson(): Map<String, *> {
        return mapOf(
            "type" to "TypeArgumentsDeclarationNode",
            "typeArguments" to typeArguments.map { it.toJson() }
        )
    }
}

class ShakeTypeArgumentDeclarationNode(

    map: PositionMap,
    val name: String,
    val extends: ShakeVariableType?

) : ShakeNodeImpl(map) {

    override fun toJson(): Map<String, *> {
        return mapOf(
            "type" to "TypeArgumentDeclarationNode",
            "name" to name,
            "extends" to extends?.toJson()
        )
    }
}
