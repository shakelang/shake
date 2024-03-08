package com.shakelang.util.parseutils.lexer.token

import com.shakelang.util.parseutils.characters.position.Position
import com.shakelang.util.parseutils.characters.position.PositionMaker
import com.shakelang.util.parseutils.characters.position.PositionMap
import com.shakelang.util.parseutils.lexer.token.stream.TokenInputStream

typealias TokenCreationLambda<TT, T, ST, CTX> =
    (TokenCreationContext<TT, T, ST, CTX>) -> T

class TokenCreationFactory<
    TT : TokenType,
    T : Token<T, TT, ST, CTX>,
    ST : TokenInputStream<ST, TT, T, CTX>,
    CTX : TokenContext<CTX, TT, T, ST>,
    >(
    private val positionMaker: PositionMaker,
    private val creationLambda: TokenCreationLambda<TT, T, ST, CTX>,
    private val context: CTX,
) {

    fun create(type: TT, start: Int, end: Int, value: String): T {
        return creationLambda(
            TokenCreationContext(
                context,
                type,
                start,
                end,
                value,
            ),
        )
    }

    fun create(type: TT, start: Position, end: Position, value: String): T {
        return create(
            type,
            start.index,
            end.index,
            value,
        )
    }

    fun create(type: TT, end: Int, value: String): T {
        return create(
            type,
            end - value.length + 1,
            end,
            value,
        )
    }

    fun create(type: TT, start: Int, end: Int): T {
        return create(
            type,
            start,
            end,
            type.value ?: throw IllegalArgumentException("Implicit value is not allowed for $type"),
        )
    }

    fun create(type: TT, start: Position, end: Position): T {
        return create(
            type,
            start.index,
            end.index,
            type.value ?: throw IllegalArgumentException("Implicit value is not allowed for $type"),
        )
    }

    fun create(type: TT, end: Int): T {
        return create(
            type,
            end,
            type.value ?: throw IllegalArgumentException("Implicit value is not allowed for $type"),
        )
    }

    fun create(type: TT): T {
        return create(
            type,
            this.positionMaker.index,
        )
    }
}

open class TokenContext<
    Self : TokenContext<Self, TT, T, ST>,
    TT : TokenType,
    T : Token<T, TT, ST, Self>,
    ST : TokenInputStream<ST, TT, T, Self>,
    > {
    lateinit var stream: ST
        private set
    lateinit var positionMap: PositionMap
        private set

    internal object Tools {
        fun <
            Self : TokenContext<Self, TT, T, ST>,
            TT : TokenType,
            T : Token<T, TT, ST, Self>,
            ST : TokenInputStream<ST, TT, T, CTX>,
            CTX : TokenContext<CTX, TT, T, ST>,
            > initStream(
            context: Self,
            stream: ST,
        ) {
            context.stream = stream
        }

        fun <
            Self : TokenContext<Self, TT, T, ST>,
            TT : TokenType,
            T : Token<T, TT, ST, Self>,
            ST : TokenInputStream<ST, TT, T, CTX>,
            CTX : TokenContext<CTX, TT, T, ST>,
            > initPositionMap(
            context: Self,
            positionMap: PositionMap,
        ) {
            context.positionMap = positionMap
        }
    }
}

data class TokenCreationContext<
    TT : TokenType,
    T : Token<T, TT, ST, CTX>,
    ST : TokenInputStream<ST, TT, T, CTX>,
    CTX : TokenContext<CTX, TT, T, ST>,
    >(
    val context: CTX,
    val type: TT,
    val startIndex: Int,
    val endIndex: Int,
    val value: String,
) {
    val start: Position
        get() = context.positionMap.resolve(startIndex)
    val end: Position
        get() = context.positionMap.resolve(endIndex)
}
