package com.shakelang.util.parseutils.lexer.token

interface TokenFactory<T : Token<out TokenType>> {
    fun createToken(): T
    fun hasMoreTokens(): Boolean = true

    companion object {
        fun <T : Token<TokenType>> of(factory: () -> T) = object : TokenFactory<T> {
            override fun createToken() = factory()
        }

        fun <T : Token<TokenType>> of(list: List<T>) = object : TokenFactory<T> {
            private val list = list.toMutableList()
            override fun createToken() = this.list.removeAt(0)
        }

        fun <T : Token<TokenType>> of(vararg tokens: T) = of(tokens.toList())
    }
}
