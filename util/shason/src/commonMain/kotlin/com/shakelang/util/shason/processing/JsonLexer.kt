package com.shakelang.util.shason.processing

import com.shakelang.util.parseutils.CompilerError
import com.shakelang.util.parseutils.characters.Characters.isHexCharacter
import com.shakelang.util.parseutils.characters.Characters.isIdentifierCharacter
import com.shakelang.util.parseutils.characters.Characters.isIdentifierStartCharacter
import com.shakelang.util.parseutils.characters.Characters.isNumberOrDotCharacter
import com.shakelang.util.parseutils.characters.position.Position
import com.shakelang.util.parseutils.characters.streaming.CharacterInputStream
import com.shakelang.util.parseutils.lexer.AbstractLexer
import com.shakelang.util.parseutils.lexer.token.TokenCreationContext
import com.shakelang.util.parseutils.lexer.token.TokenFactory
import kotlin.jvm.JvmOverloads

/**
 * A [JsonLexer] creates a [JsonTokenInputStream] from a [CharacterInputStream]
 * @since 0.1.0
 * @version 0.1.0
 */
@Suppress("unused")
class JsonLexer(

    input: CharacterInputStream,

) : AbstractLexer<JsonTokenType, JsonToken, JsonTokenInputStream, JsonTokenContext>(input, JsonTokenContext()) {

    override fun tokenFactory(ctx: TokenCreationContext<JsonTokenType, JsonToken, JsonTokenInputStream, JsonTokenContext>): JsonToken {
        return JsonToken(ctx.type, ctx.startIndex, ctx.endIndex, ctx.value, ctx.context)
    }

    /**
     * Make a [JsonTokenInputStream] from the [input] of the [JsonLexer]
     * This function executes the [JsonLexer]
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun createStream(factory: TokenFactory<JsonToken>) = JsonTokenInputStreamImpl(
        factory,
        input.positionMaker,
    )

    private val eof = factory.create(JsonTokenType.EOF, this.input.source.length)

    override fun makeToken(): JsonToken {
        if (!input.hasNext()) return eof

        // Store the next character (Store it in a variable to not get it every time to save performance)
        var next = this.input.next()

        // If the next character is a whitespace character, we will just ignore it
        while (next == ' ' || next == '\t' || next == '\r' || next == '\n') {
            if (!this.input.hasNext()) return eof
            next = this.input.next()
        }

        // If it is one of the simple tokens, we just return a new Token
        if (next == '{') {
            return factory.create(JsonTokenType.LCURL)
        }
        if (next == '}') {
            return factory.create(JsonTokenType.RCURL)
        }
        if (next == '[') {
            return factory.create(JsonTokenType.LSQUARE)
        }
        if (next == ']') {
            return factory.create(JsonTokenType.RSQUARE)
        }
        if (next == ':') {
            return factory.create(JsonTokenType.COLON)
        }
        if (next == ',') {
            return factory.create(JsonTokenType.COMMA)
        }

        // If the next token is a '"' or ''' call makeString()
        if (next == '"' || next == '\'') {
            return makeString()
        } // If the next token is an identifierStartCharacter (a-zA-Z_) call makeIdentifier() to generate a String
        else if (isIdentifierStartCharacter(next)) {
            return makeIdentifier()
        } // If the next character is 0-9 or '.' or '-' make a number
        else if (isNumberOrDotCharacter(next) || next == '-') {
            return makeNumber()
        } // If we can't parse the character throw an error
        else {
            throw JsonTokenLexerError("Unknown symbol '$next'")
        }
    }

    /**
     * Parses an identifier token (Returns a string token, because there are no identifiers in Json)
     *
     * _(Only called from [makeTokens]() workflow, because the condition for the first letter is checked
     * there and not here to improve performance. The condition **must** be checked before the call of this
     * function!)_
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun makeIdentifier(): JsonToken {
        // Loop over the characters that are contained in the Identifier
        var identifier = input.actual().toString()
        while (input.hasNext() && isIdentifierCharacter(input.peek())) {
            identifier += input.next()
        }

        // return a new string token out of the identifier
        return when (identifier) {
            "false" -> factory.create(JsonTokenType.FALSE)
            "true" -> factory.create(JsonTokenType.TRUE)
            "null" -> factory.create(JsonTokenType.NULL)
            else -> throw JsonTokenLexerError("Unknown identifier '$identifier'")
        }
    }

    /**
     * Parses a string token
     *
     * _(Only called from [makeTokens]() workflow, because the condition for the first letter is checked
     * there and not here to improve performance. The condition **must** be checked before the call of this
     * function!)_
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun makeString(): JsonToken {
        // Store the start position
        val start = this.input.position

        // Store the string end ('"' or ''')
        // We will then run the loop till we find this character again
        val end = this.input.actual()

        // String for storing the
        val str = StringBuilder()

        // Loop until we find the end character
        while (this.input.hasNext() && this.input.next() != end) {
            // If the character is an escape-character
            if (this.input.actual() == '\\') {
                when (this.input.next()) {
                    '"' -> str.append('"')
                    '\'' -> str.append('\'')
                    '\\' -> str.append('\\')
                    'b' -> str.append('\b')
                    'n' -> str.append('\n')
                    'r' -> str.append('\r')
                    't' -> str.append('\t')
                    'u' -> {
                        var s = ""
                        for (i in 0..3) {
                            val c: Char = this.input.next()
                            if (!isHexCharacter(c)) throw JsonTokenLexerError("Expecting hex char")
                            s += c
                        }
                        str.append(s.toInt(radix = 16).toChar())
                    }

                    else -> throw JsonTokenLexerError("Unknown escape sequence '\\" + this.input.actual() + "'")
                }
            } else {
                str.append(this.input.actual())
            }
        }

        // If we have not found the end throw an Error
        if (this.input.actual() != end) throw JsonTokenLexerError("Unexpected End")

        // Return a string JsonToken
        return factory.create(JsonTokenType.STRING, start, this.input.position, str.toString())
    }

    /**
     * Parse a number token
     *
     * _(Only called from [makeTokens]() workflow, because the condition for the first letter is checked
     * there and not here to improve performance. The condition **must** be checked before the call of this
     * function!)_
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun makeNumber(): JsonToken {
        // Store the start position
        val start = this.input.position

        // Boolean if we found a dot. This is important for checking
        // if we parsed a Integer or a doubles.
        var foundDot = false

        // The number string
        val number = StringBuilder().append(this.input.actual())
        if (this.input.actual() == '.') foundDot = true

        // Loop as long we find a '.' or '0-9' char
        while (this.input.hasNext() && isNumberOrDotCharacter(this.input.peek())) {
            if (this.input.next() == '.') {
                // Prevent two dots in a number
                if (foundDot) {
                    throw JsonTokenLexerError("Number must not contain two dots")
                } else {
                    foundDot = true
                }
            }

            // Append the character to the number
            number.append(this.input.actual())
        }

        // Create a new doubles-token or integers-token
        return factory.create(
            if (foundDot) {
                JsonTokenType.DOUBLE
            } else {
                JsonTokenType.INT
            },
            start,
            this.input.position,
            number.toString(),
        )
    }

    /**
     * An [CompilerError] thrown by the [JsonLexer]
     * @since 0.1.0
     * @version 0.3.0
     */
    private inner class JsonTokenLexerError(
        message: String,
        name: String,
        details: String,
        start: Position,
        end: Position,
    ) : CompilerError(message, name, details, start, end) {

        /**
         * Constructor for [JsonTokenLexerError]
         *
         * @param name the name of the [CompilerError]
         * @param details the details of the [CompilerError]
         * @param start the start position of the [CompilerError]
         * @param end the end position of the [CompilerError]
         * @since 0.1.0
         * @version 0.3.0
         */
        @JvmOverloads
        constructor(
            name: String,
            details: String,
            start: Position = input.positionMaker.createPositionAtLocation(),
            end: Position = start,
        ) : this(
            "Error occurred in lexer: $details in ${start.source}:${start.line}:${start.column}",
            name,
            details,
            start,
            end,
        )

        /**
         * Constructor for [JsonTokenLexerError]
         *
         * @param details the details of the [CompilerError]
         * @param start the start position of the [CompilerError]
         * @param end the end position of the [CompilerError]
         * @since 0.1.0
         * @version 0.3.0
         */
        @JvmOverloads
        constructor(
            details: String,
            start: Position = input.positionMaker.createPositionAtLocation(),
            end: Position = start,
        ) : this(
            "Error occurred in lexer: $details in ${start.source}:${start.line}:${start.column}",
            "LexerError",
            details,
            start,
            end,
        )
    }
}
