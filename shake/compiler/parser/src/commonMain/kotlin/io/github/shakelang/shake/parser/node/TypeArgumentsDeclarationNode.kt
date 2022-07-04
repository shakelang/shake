package io.github.shakelang.shake.parser.node

import io.github.shakelang.parseutils.characters.position.PositionMap

class TypeArgumentsDeclarationNode (

    map: PositionMap,
    val typeArguments: List<TypeArgumentDeclarationNode>

) : ShakeNodeImpl(map) {
    override fun toJson(): Map<String, *> {
        return mapOf(
            "type" to "TypeArgumentsDeclarationNode",
            "typeArguments" to typeArguments.map { it.toJson() }
        )
    }

}

class TypeArgumentDeclarationNode (


    map: PositionMap,
    val name: String,
    val extends: ShakeVariableType?,

) : ShakeNodeImpl(map) {

    override fun toJson(): Map<String, *> {
        return mapOf(
            "type" to "TypeArgumentDeclarationNode",
            "name" to name,
            "extends" to extends?.toJson()
        )
    }


}