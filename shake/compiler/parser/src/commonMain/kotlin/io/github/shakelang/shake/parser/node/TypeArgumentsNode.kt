package io.github.shakelang.shake.parser.node

import io.github.shakelang.parseutils.characters.position.PositionMap

class TypeArgumentsNode (

    map: PositionMap,
    val typeArguments: List<TypeArgumentNode>

) : ShakeNodeImpl(map) {

    override fun toJson(): Map<String, *> {
        return mapOf(
            "type" to "TypeArguments",
            "typeArguments" to typeArguments.map { it.toJson() }
        )
    }

}

class TypeArgumentNode (


    map: PositionMap,
    val type: ShakeVariableType,

) : ShakeNodeImpl(map) {

    override fun toJson(): Map<String, *> {
        return mapOf(
            "type" to "TypeArgument",
            "type" to type.toJson()
        )
    }

}