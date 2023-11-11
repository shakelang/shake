@file:Suppress("NOTHING_TO_INLINE")

package io.github.shakelang.shake.util.parseutils.lexer.token

/**
 * These are the different types of tokens, that the lexer creates
 */
interface TokenType {
    fun length(value: String?): Int
    val hasValue: Boolean
    val name: String
}
