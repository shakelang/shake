package com.github.shakelang.shake.util.json

import com.github.shakelang.shake.util.Characters.isHexCharacter
import com.github.shakelang.shake.util.Characters.isIdentifierCharacter
import com.github.shakelang.shake.util.Characters.isIdentifierStartCharacter
import com.github.shakelang.shake.util.Characters.isNumberOrDotCharacter
import com.github.shakelang.shake.util.CompilerError
import com.github.shakelang.shake.util.characterinput.characterinputstream.CharacterInputStream
import com.github.shakelang.shake.util.characterinput.position.Position
import kotlin.jvm.JvmOverloads

@Suppress("unused")
class JsonLexer(
    private val chars: CharacterInputStream
) {

    fun makeTokens(): JsonTokenInputStream {

        val tokens = mutableSetOf<JsonToken>()

        while (this.chars.hasNext()) {
            val next = this.chars.next()
            if(next == ' ' || next == '\t' || next == '\r' || next == '\n') continue
            if(next == '{') {
                tokens.add(JsonToken(JsonTokenType.LCURL, this.chars.position))
                continue
            }
            if(next == '}') {
                tokens.add(JsonToken(JsonTokenType.RCURL, this.chars.position))
                continue
            }
            if(next == '[') {
                tokens.add(JsonToken(JsonTokenType.LSQUARE, this.chars.position))
                continue
            }
            if(next == ']') {
                tokens.add(JsonToken(JsonTokenType.RSQUARE, this.chars.position))
                continue
            }
            if(next == ':') {
                tokens.add(JsonToken(JsonTokenType.COLON, this.chars.position))
                continue
            }
            if(next == ',') {
                tokens.add(JsonToken(JsonTokenType.COMMA, this.chars.position))
                continue
            }
            if(next == '"' || next == '\'') tokens.add(makeString())
            else if(isIdentifierStartCharacter(next)) tokens.add(makeIdentifier())
            else if(isNumberOrDotCharacter(next) || next == '-') tokens.add(makeNumber())
            else throw JSONTokenLexerError("Unknown symbol '$next'")
        }

        return JsonTokenInputStream(chars.source.location, tokens.toTypedArray(), chars.positionMaker.createPositionMap())

    }

    private fun makeIdentifier(): JsonToken {

        val start = this.chars.position
        var identifier = chars.actual().toString()
        while (chars.hasNext() && isIdentifierCharacter(chars.peek())) {
            identifier += chars.next()
        }

        return JsonToken(JsonTokenType.STRING, start, this.chars.position - 1, identifier)
    }

    private fun makeString(): JsonToken {

        val start = this.chars.position
        val end = this.chars.actual()
        var str = ""

        while(this.chars.hasNext() && this.chars.next() != end) {
            if(this.chars.actual() == '\\') {
                when (this.chars.next()) {
                    '"' -> str += "\""
                    '\'' -> str += "\'"
                    '\\' -> str += "\\"
                    'b' -> str += "\b"
                    'n' -> str += "\n"
                    'r' -> str += "\r"
                    't' -> str += "\t"
                    'u' -> {
                        var s = ""
                        for (i in 0..3) {
                            val c: Char = this.chars.next()
                            if (!isHexCharacter(c)) throw JSONTokenLexerError("Expecting hex char")
                            s += c
                        }
                        str += s.toInt(radix = 16).toChar()
                    }
                    else -> throw JSONTokenLexerError("Unknown escape sequence '\\" + this.chars.actual() + "'")
                }
            }
            else str += this.chars.actual()
        }
        
        if(this.chars.actual() != end) throw  JSONTokenLexerError("Unexpected End")
        
        return JsonToken(JsonTokenType.STRING, start, this.chars.position, str)

    }

    private fun makeNumber(): JsonToken {

        val start = this.chars.position
        var foundDot = false
        var number = this.chars.actual().toString()

        while (this.chars.hasNext() && isNumberOrDotCharacter(this.chars.peek())) {

            if (this.chars.next() == '.') {
                if (foundDot) throw JSONTokenLexerError("Number must not contain two dots")
                else foundDot = true
            }
            number += this.chars.actual()

        }

        return JsonToken(if(foundDot) JsonTokenType.DOUBLE else JsonTokenType.INT, start, this.chars.position, number)

    }

    private inner class JSONTokenLexerError(message: String, name: String, details: String, start: Position, end: Position) :
        CompilerError(message, name, details, start, end) {

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