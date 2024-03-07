package com.shakelang.util.parseutils

import com.shakelang.util.parseutils.characters.position.Position
import com.shakelang.util.parseutils.characters.position.PositionMap
import com.shakelang.util.parseutils.characters.streaming.CharacterInputStream
import com.shakelang.util.parseutils.lexer.token.Token
import com.shakelang.util.parseutils.lexer.token.stream.TokenInputStream

open class ErrorFactory<E : CompilerError>(
    val factory: (message: String, start: Position, end: Position) -> E,
    val map: PositionMap,
) {
    fun createError(message: String, start: Position, end: Position) = factory(message, start, end)
    fun createError(message: String, start: Int, end: Int) = createError(message, map.resolve(start), map.resolve(end))
    fun createError(message: String, token: Token<*, *, *, *>) = createError(message, token.start, token.end)
}

open class LexerErrorFactory<E : CompilerError>(
    factory: (message: String, start: Position, end: Position) -> E,
    val input: CharacterInputStream,
) : ErrorFactory<E>(factory, input.positionMaker) {
    fun createErrorAtCurrent(message: String) = createError(message, input.positionMaker.createPositionAtLocation(), input.positionMaker.createPositionAtLocation())
}

open class ParserErrorFactory<E : CompilerError>(
    factory: (message: String, start: Position, end: Position) -> E,
    val input: TokenInputStream<*, *, *, *>,
) : ErrorFactory<E>(factory, input.map) {
    fun createErrorAtCurrent(message: String) = input.actual?.let {
        createError(message, it)
    } ?: input.map.resolve(0).let {
        createError(message, it, it)
    }
}
