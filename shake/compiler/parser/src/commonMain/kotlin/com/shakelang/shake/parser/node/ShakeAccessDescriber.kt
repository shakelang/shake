package com.shakelang.shake.parser.node

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.lexer.token.ShakeTokenType

class ShakeAccessDescriber(
    val token: ShakeToken?,
) {

    val type: ShakeAccessDescriberType get() = when (token?.type) {
        null -> ShakeAccessDescriberType.PACKAGE
        else -> when (token.type) {
            ShakeTokenType.KEYWORD_PUBLIC -> ShakeAccessDescriberType.PUBLIC
            ShakeTokenType.KEYWORD_PROTECTED -> ShakeAccessDescriberType.PROTECTED
            ShakeTokenType.KEYWORD_PRIVATE -> ShakeAccessDescriberType.PRIVATE
            else -> throw IllegalArgumentException("Invalid access token: ${token.type}")
        }
    }

    override fun toString(): String = type.toString()

    enum class ShakeAccessDescriberType(
        val prefix: String?,
    ) {
        PUBLIC("public"),
        PROTECTED("protected"),
        PACKAGE(null),
        PRIVATE("private"),
        ;

        override fun toString(): String = name.lowercase()
        val realPrefix: String get() = prefix?.plus(" ") ?: ""
    }

    companion object {
        fun of(token: ShakeToken?): ShakeAccessDescriber = ShakeAccessDescriber(token)
        val PACKAGE: ShakeAccessDescriber = ShakeAccessDescriber(null)
        val types = ShakeAccessDescriberType.entries
    }
}
