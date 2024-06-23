package com.shakelang.shake.parser.node.misc

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeNodeImpl
import com.shakelang.util.parseutils.characters.position.PositionMap

class ShakeTypeArgumentDeclaration(
    map: PositionMap,
    val nameToken: ShakeToken,
    val colonToken: ShakeToken?,
    val type: ShakeVariableType?,
) : ShakeNodeImpl(map = map) {

    val name: String
        get() = nameToken.value

    override fun equals(other: Any?): Boolean {
        if (other !is ShakeTypeArgumentDeclaration) return false
        if (other.nameToken != nameToken) return false
        if (other.colonToken != colonToken) return false
        if (other.type != type) return false
        return true
    }

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (other !is ShakeTypeArgumentDeclaration) return false
        if (other.nameToken != nameToken) return false
        if (other.colonToken != colonToken) return false
        if (other.type != type) return false
        return true
    }

    override fun hashCode(): Int {
        var result = nameToken.hashCode()
        result = 31 * result + (colonToken?.hashCode() ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        return result
    }
}

class ShakeTypeArgumentsDeclaration(
    map: PositionMap,
    val openToken: ShakeToken,
    val generics: Array<ShakeTypeArgumentDeclaration>,
    val commaTokens: Array<ShakeToken>,
    val closeToken: ShakeToken,
) : ShakeNodeImpl(map) {

    override fun equals(other: Any?): Boolean {
        if (other !is ShakeTypeArgumentsDeclaration) return false
        if (other.openToken != openToken) return false
        if (!other.generics.contentEquals(generics)) return false
        if (!other.commaTokens.contentEquals(commaTokens)) return false
        if (other.closeToken != closeToken) return false
        return true
    }

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (other !is ShakeTypeArgumentsDeclaration) return false
        if (other.openToken != openToken) return false
        if (!other.generics.contentEquals(generics)) return false
        if (!other.commaTokens.contentEquals(commaTokens)) return false
        if (other.closeToken != closeToken) return false
        return true
    }

    override fun hashCode(): Int {
        var result = openToken.hashCode()
        result = 31 * result + generics.contentHashCode()
        result = 31 * result + commaTokens.contentHashCode()
        result = 31 * result + closeToken.hashCode()
        return result
    }
}
