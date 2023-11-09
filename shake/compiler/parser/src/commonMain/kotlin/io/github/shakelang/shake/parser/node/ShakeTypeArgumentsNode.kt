package io.github.shakelang.shake.parser.node

import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeTypeArgumentsNode(

    map: PositionMap,
    val typeArguments: List<ShakeTypeArgumentNode>

) : ShakeNodeImpl(map) {

    override fun toJson(): Map<String, *> {
        return mapOf(
            "type" to "TypeArguments",
            "typeArguments" to typeArguments.map { it.toJson() }
        )
    }

}

class ShakeTypeArgumentNode(


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