package com.shakelang.shake.util.shason.processing

import com.shakelang.shake.util.parseutils.CompilerError
import io.github.shakelang.shake.util.parseutils.characters.Characters.isHexCharacter
import io.github.shakelang.shake.util.parseutils.characters.Characters.isIdentifierCharacter
import io.github.shakelang.shake.util.parseutils.characters.Characters.isIdentifierStartCharacter
import io.github.shakelang.shake.util.parseutils.characters.Characters.isNumberOrDotCharacter
import io.github.shakelang.shake.util.parseutils.characters.position.Position
import io.github.shakelang.shake.util.parseutils.characters.streaming.CharacterInputStream
import kotlin.jvm.JvmOverloads

/**
 * A [JsonLexer] creates a [JsonTokenInputStream] from a [CharacterInputStream]
 */
@Suppress("unused")
class JsonLexer(

    /**
     * The [CharacterInputStream] to create the [JsonTokenInputStream] from
     */
    private val chars: CharacterInputStream

) {

    /**
     * Make a [JsonTokenInputStream] from the [chars] of the [JsonLexer]
     * This function executes the [JsonLexer]
     */
    fun makeTokens(): JsonTokenInputStream {
        // Set for storing the generated tokens
        val tokens = mutableListOf<JsonToken>()

        // Loop over all the characters in the characterInputStream
        while (this.chars.hasNext()) {
            // Store the next character (Store it in a variable to not get it every time to save performance)
            val next = this.chars.next()

            // If the next character is a whitespace character we will just ignore it
            if (next == ' ' || next == '\t' || next == '\r' || next == '\n') continue

            // If it is one of the simple tokens we just return a new Token
            if (next == '{') {
                tokens.add(JsonToken(JsonTokenType.LCURL, this.chars.position))
                continue
            }
            if (next == '}') {
                tokens.add(JsonToken(JsonTokenType.RCURL, this.chars.position))
                continue
            }
            if (next == '[') {
                tokens.add(JsonToken(JsonTokenType.LSQUARE, this.chars.position))
                continue
            }
            if (next == ']') {
                tokens.add(JsonToken(JsonTokenType.RSQUARE, this.chars.position))
                continue
            }
            if (next == ':') {
                tokens.add(JsonToken(JsonTokenType.COLON, this.chars.position))
                continue
            }
            if (next == ',') {
                tokens.add(JsonToken(JsonTokenType.COMMA, this.chars.position))
                continue
            }

            // If the next token is a '"' or ''' call makeString()
            if (next == '"' || next == '\'') {
                tokens.add(makeString())
            } // If the next token is an identifierStartCharacter (a-zA-Z_) call makeIdentifier() to generate a String
            else if (isIdentifierStartCharacter(next)) {
                tokens.add(makeIdentifier())
            } // If the next character is 0-9 or '.' or '-' make a number
            else if (isNumberOrDotCharacter(next) || next == '-') {
                tokens.add(makeNumber())
            } // If we can't parse the character throw an error
            else {
                throw JsonTokenLexerError("Unknown symbol '$next'")
            }
        }

        // Create a new JsonTokenInputStream out of the tokens
        return JsonTokenInputStreamImpl(
            tokens.toTypedArray(),
            chars.positionMaker.createPositionMap()
        )
    }

    /**
     * Parses an identifier token (Returns a string token, because there are no identifiers in Json)
     *
     * _(Only called from [makeTokens]() workflow, because the condition for the first letter is checked
     * there and not here to improve performance. The condition **must** be checked before the call of this
     * function!)_
     */
    private fun makeIdentifier(): JsonToken {
        // Store the start position
        val start = this.chars.position

        // Loop over the characters that are contained in the Identifier
        var identifier = chars.actual().toString()
        while (chars.hasNext() && isIdentifierCharacter(chars.peek())) {
            identifier += chars.next()
        }

        // return a new string token out of the identifier
        return when (identifier) {
            "false" -> JsonToken(JsonTokenType.FALSE, start, this.chars.position)
            "true" -> JsonToken(JsonTokenType.TRUE, start, this.chars.position)
            else -> JsonToken(JsonTokenType.STRING, start, this.chars.position, identifier)
        }
    }

    /**
     * Parses a string token
     *
     * _(Only called from [makeTokens]() workflow, because the condition for the first letter is checked
     * there and not here to improve performance. The condition **must** be checked before the call of this
     * function!)_
     */
    private fun makeString(): JsonToken {
        // Store the start position
        val start = this.chars.position

        // Store the string end ('"' or ''')
        // We will then run the loop till we find this character again
        val end = this.chars.actual()

        // String for storing the
        val str = StringBuilder()

        // Loop until we find the end character
        while (this.chars.hasNext() && this.chars.next() != end) {
            // If the character is an escape-character
            if (this.chars.actual() == '\\') {
                when (this.chars.next()) {
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
                            val c: Char = this.chars.next()
                            if (!isHexCharacter(c)) throw JsonTokenLexerError("Expecting hex char")
                            s += c
                        }
                        str.append(s.toInt(radix = 16).toChar())
                    }

                    else -> throw JsonTokenLexerError("Unknown escape sequence '\\" + this.chars.actual() + "'")
                }
            } else {
                str.append(this.chars.actual())
            }
        }

        // If we have not found the end throw an Error
        if (this.chars.actual() != end) throw JsonTokenLexerError("Unexpected End")

        // Return a string JsonToken
        return JsonToken(JsonTokenType.STRING, start, this.chars.position, str.toString())
    }

    /**
     * Parse a number token
     *
     * _(Only called from [makeTokens]() workflow, because the condition for the first letter is checked
     * there and not here to improve performance. The condition **must** be checked before the call of this
     * function!)_
     */
    private fun makeNumber(): JsonToken {
        // Store the start position
        val start = this.chars.position

        // Boolean if we found a dot. This is important for checking
        // if we parsed a Integer or a double.
        var foundDot = false

        // The number string
        val number = StringBuilder().append(this.chars.actual())
        if (this.chars.actual() == '.') foundDot = true

        // Loop as long we find a '.' or '0-9' char
        while (this.chars.hasNext() && isNumberOrDotCharacter(this.chars.peek())) {
            if (this.chars.next() == '.') {
                // Prevent two dots in a number
                if (foundDot) {
                    throw JsonTokenLexerError("Number must not contain two dots")
                } else {
                    foundDot = true
                }
            }

            // Append the character to the number
            number.append(this.chars.actual())
        }

        // Create a new double-token or integer-token
        return JsonToken(
            if (foundDot) {
                JsonTokenType.DOUBLE
            } else {
                JsonTokenType.INT
            },
            start,
            this.chars.position,
            number.toString()
        )
    }

    /**
     * An [CompilerError] thrown by the [JsonLexer]
     */
    private inner class JsonTokenLexerError(
        message: String,
        name: String,
        details: String,
        start: Position,
        end: Position
    ) : com.shakelang.shake.util.parseutils.CompilerError(message, name, details, start, end) {

        /**
         * Constructor for [JsonTokenLexerError]
         *
         * @param name the name of the [CompilerError]
         * @param details the details of the [CompilerError]
         * @param start the start position of the [CompilerError]
         * @param end the end position of the [CompilerError]
         *
         */
        @JvmOverloads
        constructor(
            name: String,
            details: String,
            start: Position = chars.positionMaker.createPositionAtLocation(),
            end: Position = start
        ) : this(
            "Error occurred in lexer: " + name + ", " + details + " in " + start.source + ":" + start.line + ":" + start.column,
            name,
            details,
            start,
            end
        )

        /**
         * Constructor for [JsonTokenLexerError]
         *
         * @param details the details of the [CompilerError]
         * @param start the start position of the [CompilerError]
         * @param end the end position of the [CompilerError]
         *
         */
        @JvmOverloads
        constructor(
            details: String,
            start: Position = chars.positionMaker.createPositionAtLocation(),
            end: Position = start
        ) : this(
            "Error occurred in lexer: " + details + " in " + start.source + ":" + start.line + ":" + start.column,
            "LexerError",
            details,
            start,
            end
        )
    }
}
