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
     * Get the name of the token
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    val name: String

    /**
     * Get the value of the token (or null if allowed)
     *
     * @since 0.5.0
     * @version 0.5.0
     */
    val value: String?
}
