package com.shakelang.util.parseutils.lexer

import com.shakelang.util.parseutils.CompilerError
import com.shakelang.util.parseutils.LexerErrorFactory
import com.shakelang.util.parseutils.characters.position.Position
import com.shakelang.util.parseutils.characters.streaming.CharacterInputStream
import com.shakelang.util.parseutils.lexer.token.*
import com.shakelang.util.parseutils.lexer.token.stream.TokenInputStream

@Suppress("unused")
abstract class AbstractLexer<
    TT : TokenType,
    T : Token<T, TT, ST, CTX>,
    ST : TokenInputStream<ST, TT, T, CTX>,
    CTX : TokenContext<CTX, TT, T, ST>,
    >(
    val input: CharacterInputStream,
    val ctx: CTX,
) {

    val factory = TokenCreationFactory(input.positionMaker, this::tokenFactory, ctx)

    private fun toFactory() = TokenFactory.of(this::makeToken)
    private var streamCreated = false

    fun stream(): ST {
        if (streamCreated) throw IllegalStateException("Stream already created for this lexer")
        streamCreated = true
        val stream = createStream(toFactory())
        TokenContext.Tools.initStream(ctx, stream)
        return stream
    }

    protected abstract fun createStream(factory: TokenFactory<T>): ST

    abstract fun makeToken(): T

    abstract fun tokenFactory(ctx: TokenCreationContext<TT, T, ST, CTX>): T

    val errorFactory = LexerErrorFactory({
            message, start, end ->
        LexerError(message, start, end)
    }, input)

    /**
     * A [CompilerError] thrown by the [JsonLexer]
     * @since 0.1.0
     * @version 0.3.0
     */
    class LexerError(
        message: String,
        start: Position,
        end: Position,
    ) : CompilerError(message, start, end)
}
