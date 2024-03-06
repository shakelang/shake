package com.shakelang.util.parseutils.lexer.token

interface TokenFactory<T : Token<*, out TokenType, *>> {
    fun createToken(): T
    companion object {
        fun <T : Token<*, out TokenType, *>> of(factory: () -> T) = object : TokenFactory<T> {
            override fun createToken() = factory()
        }

        fun <T : Token<*, out TokenType, *>> of(list: List<T>) = object : TokenFactory<T> {
            private val list = list.toMutableList()
            override fun createToken() = this.list.removeAt(0)
        }

        fun <T : Token<*, out TokenType, *>> of(vararg tokens: T) = of(tokens.toList())
    }
}
