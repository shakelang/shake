package com.shakelang.shake.parser.node.misc

import com.shakelang.shake.lexer.token.ShakeToken

class ShakeTypeArguments(
    val openToken: ShakeToken,
    val arguments: Array<ShakeVariableType>,
    val commaTokens: Array<ShakeToken>,
    val closeToken: ShakeToken,
) {

    override fun equals(other: Any?): Boolean {
        if (other !is ShakeTypeArguments) return false
        if (other.openToken != openToken) return false
        if (!other.arguments.contentEquals(arguments)) return false
        if (!other.commaTokens.contentEquals(commaTokens)) return false
        if (other.closeToken != closeToken) return false
        return true
    }

    fun equalsIgnorePosition(other: Any?): Boolean {
        if (other !is ShakeTypeArguments) return false
        if (other.openToken != openToken) return false
        if (!other.arguments.contentEquals(arguments)) return false
        if (!other.commaTokens.contentEquals(commaTokens)) return false
        if (other.closeToken != closeToken) return false
        return true
    }
}
