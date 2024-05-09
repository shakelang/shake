package com.shakelang.util.parseutils.lexer

import com.shakelang.util.parseutils.CompilerError
import com.shakelang.util.parseutils.LexerErrorFactory
import com.shakelang.util.parseutils.characters.position.Position
import com.shakelang.util.parseutils.characters.streaming.CharacterInputStream
import com.shakelang.util.parseutils.lexer.token.*
import com.shakelang.util.parseutils.lexer.token.stream.TokenInputStream

/**
 * An abstract lexer class
 * @param TT The type of the token type
 * @param T The type of the token
 * @param ST The type of the token stream
 * @param CTX The type of the token context
 * @since 0.5.0
 * @version 0.5.0
 */
@Suppress("unused")
abstract class AbstractLexer<
    TT : TokenType,
    T : Token<T, TT, ST, CTX>,
    ST : TokenInputStream<ST, TT, T, CTX>,
    CTX : TokenContext<CTX, TT, T, ST>,
    >(

    /**
     * The input stream
     * @since 0.5.0
     * @version 0.5.0
     */
    val input: CharacterInputStream,

    /**
     * The token context
     * @since 0.5.0
     * @version 0.5.0
     */
    val ctx: CTX,
) {

    /**
     * The token creation factory
     * @since 0.5.0
     * @version 0.5.0
     */
    val factory = TokenCreationFactory(input.positionMaker, this::tokenFactory, ctx)

    private fun toFactory() = TokenFactory.of(this::makeToken)
    private var streamCreated = false

    /**
     * Create a token stream
     * @since 0.5.0
     * @version 0.5.0
     */
    fun stream(): ST {
        if (streamCreated) throw IllegalStateException("Stream already created for this lexer")
        streamCreated = true
        val stream = createStream(toFactory())
        TokenContext.Tools.initStream(ctx, stream)
        return stream
    }

    /**
     * Create a token stream
     * @since 0.5.0
     * @version 0.5.0
     */
    protected abstract fun createStream(factory: TokenFactory<T>): ST

    /**
     * Factory method for creating tokens
     * @since 0.5.0
     * @version 0.5.0
     */
    abstract fun makeToken(): T

    /**
     * Factory method for creating tokens
     * @since 0.5.0
     * @version 0.5.0
     */
    abstract fun tokenFactory(ctx: TokenCreationContext<TT, T, ST, CTX>): T

    /**
     * The error factory for the lexer
     * @since 0.5.0
     * @version 0.5.0
     */
    open val errorFactory = LexerErrorFactory(
        { message, start, end ->
            LexerError(message, start, end)
        },
        input,
    )

    /**
     * A [CompilerError] thrown by the lexer
     * @since 0.1.0
     * @version 0.3.0
     */
    class LexerError(
        message: String,
        start: Position,
        end: Position,
    ) : CompilerError(message, start, end)
}
