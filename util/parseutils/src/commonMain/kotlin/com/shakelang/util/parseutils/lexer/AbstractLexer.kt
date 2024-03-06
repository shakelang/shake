package com.shakelang.util.parseutils.lexer

import com.shakelang.util.parseutils.CompilerError
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

    open fun createError(
        message: String,
        name: String,
        details: String,
        start: Position,
        end: Position,
    ) = LexerError(message, name, details, start, end)

    open fun createError(
        name: String,
        details: String,
        start: Position = input.positionMaker.createPositionAtLocation(),
        end: Position = start,
    ) = LexerError(name, details, start, end)

    open fun createError(
        details: String,
        start: Position = input.positionMaker.createPositionAtLocation(),
        end: Position = start,
    ) = LexerError(details, start, end)

    open class LexerError(
        message: String,
        name: String,
        details: String,
        start: Position,
        end: Position,
    ) :
        CompilerError(message, name, details, start, end) {

        constructor(
            base: AbstractLexer<*, *, *, *>,
            name: String,
            details: String,
            start: Position = base.input.positionMaker.createPositionAtLocation(),
            end: Position = start,
        ) : this(
            "Error occurred in lexer: " + name + ", " + details + " in " + start.source + ":" + start.line + ":" + start.column,
            name,
            details,
            start,
            end,
        )

        constructor(
            base: AbstractLexer<*, *, *, *>,
            details: String,
            start: Position = base.input.positionMaker.createPositionAtLocation(),
            end: Position = start,
        ) : this(
            "Error occurred in lexer: " + details + " in " + start.source + ":" + start.line + ":" + start.column,
            "LexerError",
            details,
            start,
            end,
        )

        constructor(
            name: String,
            details: String,
            start: Position,
            end: Position = start,
        ) : this(
            "Error occurred in lexer: " + name + ", " + details + " in " + start.source + ":" + start.line + ":" + start.column,
            name,
            details,
            start,
            end,
        )

        constructor(
            details: String,
            start: Position,
            end: Position = start,
        ) : this(
            "Error occurred in lexer: " + details + " in " + start.source + ":" + start.line + ":" + start.column,
            "LexerError",
            details,
            start,
            end,
        )
    }
}
