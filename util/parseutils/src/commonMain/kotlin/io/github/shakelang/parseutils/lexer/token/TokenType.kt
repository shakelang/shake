@file:Suppress("NOTHING_TO_INLINE")
package io.github.shakelang.parseutils.lexer.token

/**
 * These are the different types of tokens, that the lexer creates
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
interface TokenType {
    fun length(value: String?): Int
    val hasValue: Boolean
    val name: String
}