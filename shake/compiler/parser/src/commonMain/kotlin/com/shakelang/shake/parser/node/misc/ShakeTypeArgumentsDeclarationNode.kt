package com.shakelang.shake.parser.node.misc

import com.shakelang.shake.parser.node.ShakeNodeImpl
import com.shakelang.util.parseutils.characters.position.PositionMap

class ShakeTypeArgumentsDeclarationNode(

    map: PositionMap,
    val typeArguments: Array<ShakeTypeArgumentDeclarationNode>,

) : ShakeNodeImpl(map) {
    override fun toJson(): Map<String, *> {
        return mapOf(
            "type" to "TypeArgumentsDeclarationNode",
            "typeArguments" to typeArguments.map { it.toJson() },
        )
    }

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeTypeArgumentsDeclarationNode) return false
        if (!typeArguments.contentEquals(other.typeArguments)) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeTypeArgumentsDeclarationNode) return false
        if (!typeArguments.contentEquals(other.typeArguments)) return false
        return true
    }

    override fun hashCode(): Int {
        return typeArguments.contentHashCode()
    }
}

class ShakeTypeArgumentDeclarationNode(

    map: PositionMap,
    val name: String,
    val extends: ShakeVariableType?,

) : ShakeNodeImpl(map) {

    override fun toJson(): Map<String, *> {
        return mapOf(
            "type" to nodeName,
            "name" to name,
            "extends" to extends?.toJson(),
        )
    }

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeTypeArgumentDeclarationNode) return false
        if (name != other.name) return false
        if (extends != other.extends) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeTypeArgumentDeclarationNode) return false
        if (name != other.name) return false
        if (extends != other.extends) return false
        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + (extends?.hashCode() ?: 0)
        return result
    }
}
