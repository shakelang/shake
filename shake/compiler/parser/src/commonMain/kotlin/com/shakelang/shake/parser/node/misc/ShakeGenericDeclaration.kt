package com.shakelang.shake.parser.node.misc

import com.shakelang.shake.lexer.token.ShakeToken

class ShakeGenericDeclaration(
    val nameToken: ShakeToken,
    val colonToken: ShakeToken?,
    val type: ShakeVariableType?,
) {

    val name: String
        get() = nameToken.value

    val json: Any
        get() = toJson()

    fun toJson(): Map<String, *> = mapOf(
        "name" to name,
        "type" to type?.json,
    )

    override fun equals(other: Any?): Boolean {
        if (other !is ShakeGenericDeclaration) return false
        if (other.nameToken != nameToken) return false
        if (other.colonToken != colonToken) return false
        if (other.type != type) return false
        return true
    }

    fun equalsIgnorePosition(other: Any?): Boolean {
        if (other !is ShakeGenericDeclaration) return false
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

class ShakeGenericsDeclaration(
    val openToken: ShakeToken,
    val generics: Array<ShakeGenericDeclaration>,
    val commaTokens: Array<ShakeToken>,
    val closeToken: ShakeToken,
) {

    override fun equals(other: Any?): Boolean {
        if (other !is ShakeGenericsDeclaration) return false
        if (other.openToken != openToken) return false
        if (!other.generics.contentEquals(generics)) return false
        if (!other.commaTokens.contentEquals(commaTokens)) return false
        if (other.closeToken != closeToken) return false
        return true
    }

    fun equalsIgnorePosition(other: Any?): Boolean {
        if (other !is ShakeGenericsDeclaration) return false
        if (other.openToken != openToken) return false
        if (!other.generics.contentEquals(generics)) return false
        if (!other.commaTokens.contentEquals(commaTokens)) return false
        if (other.closeToken != closeToken) return false
        return true
    }

    val json: Any
        get() = toJson()

    fun toJson(): Map<String, *> = mapOf(
        "open" to openToken.value,
        "generics" to generics.map { it.json },
        "comma" to commaTokens.map { it.value },
        "close" to closeToken.value,
    )

    override fun hashCode(): Int {
        var result = openToken.hashCode()
        result = 31 * result + generics.contentHashCode()
        result = 31 * result + commaTokens.contentHashCode()
        result = 31 * result + closeToken.hashCode()
        return result
    }
}
