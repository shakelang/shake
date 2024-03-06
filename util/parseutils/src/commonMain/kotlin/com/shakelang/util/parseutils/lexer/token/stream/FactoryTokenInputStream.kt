package com.shakelang.util.parseutils.lexer.token.stream

import com.shakelang.util.io.streaming.general.PeekableStreamImpl
import com.shakelang.util.io.streaming.general.Stream
import com.shakelang.util.parseutils.characters.position.PositionMap
import com.shakelang.util.parseutils.lexer.token.Token
import com.shakelang.util.parseutils.lexer.token.TokenFactory
import com.shakelang.util.parseutils.lexer.token.TokenType

open class FactoryTokenInputStream<
    Self : TokenInputStream<Self, TT, T>,
    TT : TokenType,
    T : Token<*, TT, Self>,
    >(
    val factory: TokenFactory<T>,
    override val map: PositionMap,
) :
    PeekableStreamImpl<T>(object : Stream<T> {
        override fun read(): T {
            return factory.createToken()
        }

        override fun hasNext(): Boolean {
            return factory.hasMoreTokens()
        }
    }),
    TokenInputStream<Self, TT, T> {

    override val source: String
        get() = map.location

    override fun skip() {
        super.read()
    }

    override fun skip(amount: Int) {
        for (i in 0 until amount) super.read()
    }
}
