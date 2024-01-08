package com.shakelang.shake.parser.node

import com.shakelang.util.parseutils.characters.position.PositionMap

class ShakeTypeArgumentsNode(

    map: PositionMap,
    val typeArguments: List<ShakeTypeArgumentNode>,

) : ShakeNodeImpl(map) {

    override fun toJson(): Map<String, *> {
        return mapOf(
            "type" to nodeName,
            "typeArguments" to typeArguments.map { it.toJson() },
        )
    }

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeTypeArgumentsNode) return false
        if (!typeArguments.map { it.equalsIgnorePosition(other) }.all { it }) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeTypeArgumentsNode) return false
        if (!typeArguments.map { it.equals(other) }.all { it }) return false
        return true
    }

    override fun hashCode(): Int {
        return typeArguments.hashCode()
    }
}

class ShakeTypeArgumentNode(

    map: PositionMap,
    val type: ShakeVariableType,

) : ShakeNodeImpl(map) {

    override fun toJson(): Map<String, *> {
        return mapOf(
            "type" to "TypeArgument",
            "type" to type.toJson(),
        )
    }

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeTypeArgumentNode) return false
        if (!type.equalsIgnorePosition(other.type)) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeTypeArgumentNode) return false
        if (!type.equals(other.type)) return false
        return true
    }
}
