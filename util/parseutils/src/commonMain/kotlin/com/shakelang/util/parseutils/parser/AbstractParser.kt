package com.shakelang.util.parseutils.parser

import com.shakelang.util.parseutils.CompilerError
import com.shakelang.util.parseutils.ParserErrorFactory
import com.shakelang.util.parseutils.characters.position.Position
import com.shakelang.util.parseutils.lexer.token.stream.TokenInputStream

/**
 * An abstract parser class
 * @param ST The type of the token stream
 * @param ROOT The type of the root node
 * @since 0.5.0
 * @version 0.5.0
 */
@Suppress("unused")
abstract class AbstractParser<
    ST : TokenInputStream<*, *, *, *>,
    ROOT,
    >(
    /**
     * The input of the parser
     * @since 0.5.0
     * @version 0.5.0
     */
    val input: ST,
) {
    abstract fun parse(): ROOT

    /**
     * The error factory for the parser
     * @since 0.5.0
     * @version 0.5.0
     */
    open val errorFactory = ParserErrorFactory({
            message, start, end ->
        ParserError(message, start, end)
    }, input)

    /**
     * A [CompilerError] thrown by the parser
     * @since 0.5.0
     * @version 0.5.0
     */
    class ParserError(
        message: String,
        start: Position,
        end: Position,
    ) : CompilerError(message, start, end)
}
