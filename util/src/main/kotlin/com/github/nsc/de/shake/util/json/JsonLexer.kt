package com.github.nsc.de.shake.util.json

import com.github.nsc.de.shake.util.Characters.isHexCharacter
import com.github.nsc.de.shake.util.Characters.isIdentifierCharacter
import com.github.nsc.de.shake.util.Characters.isIdentifierStartCharacter
import com.github.nsc.de.shake.util.Characters.isNumberOrDotCharacter
import com.github.nsc.de.shake.util.CompilerError
import com.github.nsc.de.shake.util.characterinput.characterinputstream.CharacterInputStream
import com.github.nsc.de.shake.util.characterinput.position.Position

@Suppress("unused")
class JsonLexer(
    private val chars: CharacterInputStream
) {

    fun makeTokens(): NBTTokenInputStream {

        val tokens = mutableSetOf<NBTToken>()

        while (this.chars.hasNext()) {
            val next = this.chars.next()
            if(next == ' ' || next == '\t' || next == '\r' || next == '\n') continue
            if(next == '{') {
                tokens.add(NBTToken(NBTTokenType.LCURL, this.chars.position))
                continue
            }
            if(next == '}') {
                tokens.add(NBTToken(NBTTokenType.RCURL, this.chars.position))
                continue
            }
            if(next == '[') {
                tokens.add(NBTToken(NBTTokenType.LSQUARE, this.chars.position))
                continue
            }
            if(next == ']') {
                tokens.add(NBTToken(NBTTokenType.RSQUARE, this.chars.position))
                continue
            }
            if(next == ':') {
                tokens.add(NBTToken(NBTTokenType.COLON, this.chars.position))
                continue
            }
            if(next == ',') {
                tokens.add(NBTToken(NBTTokenType.COMMA, this.chars.position))
                continue
            }
            if(next == '"' || next == '\'') tokens.add(makeString())
            else if(isIdentifierStartCharacter(next)) tokens.add(makeIdentifier())
            else if(isNumberOrDotCharacter(next) || next == '-') tokens.add(makeNumber())
            else throw NBTTokenLexerError("Unknown symbol '$next'")
        }

        return NBTTokenInputStream(chars.source.location, tokens.toTypedArray(), chars.positionMaker.createPositionMap())

    }

    private fun makeIdentifier(): NBTToken {

        val start = this.chars.position
        var identifier = chars.actual().toString()
        while (chars.hasNext() && isIdentifierCharacter(chars.peek())) {
            identifier += chars.next()
        }

        return NBTToken(NBTTokenType.STRING, start, this.chars.position - 1, identifier)
    }

    private fun makeString(): NBTToken {

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
                            if (!isHexCharacter(c)) throw NBTTokenLexerError("Expecting hex char")
                            s += c
                        }
                        str += s.toInt(radix = 16).toChar()
                    }
                    else -> throw NBTTokenLexerError("Unknown escape sequence '\\" + this.chars.actual() + "'")
                }
            }
            else str += this.chars.actual()
        }
        
        if(this.chars.actual() != end) throw  NBTTokenLexerError("Unexpected End")
        
        return NBTToken(NBTTokenType.STRING, start, this.chars.position, str)

    }

    private fun makeNumber(): NBTToken {

        val start = this.chars.position
        var foundDot = false
        var number = this.chars.actual().toString()

        while (this.chars.hasNext() && isNumberOrDotCharacter(this.chars.peek())) {

            if (this.chars.next() == '.') {
                if (foundDot) throw NBTTokenLexerError("Number must not contain two dots")
                else foundDot = true
            }
            number += this.chars.actual()

        }

        return NBTToken(if(foundDot) NBTTokenType.DOUBLE else NBTTokenType.INT, start, this.chars.position, number)

    }

    private inner class NBTTokenLexerError(message: String, name: String, details: String, start: Position, end: Position) :
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