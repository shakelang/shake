package com.shakelang.util.parseutils.lexer.token

/**
 * These are the different types of tokens, that the lexer creates
 * @since 0.1.0
 * @version 0.2.1
 */
interface TokenType {
    /**
     * Get the length of the token
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    fun length(value: String?): Int

    /**
     * Check if the token has a value
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val hasValue: Boolean

    /**
     * Get the name of the token
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val name: String
}
