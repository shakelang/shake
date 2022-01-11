package io.github.shakelang.shake.shasambly.shasp.lexer

import io.github.shakelang.parseutils.CompilerError
import io.github.shakelang.parseutils.characters.Characters
import io.github.shakelang.parseutils.characters.position.Position
import io.github.shakelang.parseutils.characters.streaming.CharacterInputStream
import io.github.shakelang.shake.lexer.token.ShasPToken
import io.github.shakelang.shake.shasambly.shasp.lexer.token.ShasPTokenType
import kotlin.jvm.JvmOverloads

abstract class ShasPLexingBase(
    protected val input: CharacterInputStream
) {

    open fun makeToken(): ShasPToken {
        var next = input.next()

        // Whitespace
        while (next.isWhitespace()) next = input.next()

        val peek: Char = if (input.hasNext()) input.peek() else (0).toChar()
        val start = input.position

        return if (next == ';') ShasPToken(ShasPTokenType.SEMICOLON, start)
        else if (next == ',') ShasPToken(ShasPTokenType.COMMA, start)
        else if (next == '.') ShasPToken(ShasPTokenType.DOT, start)
        else if (Characters.isNumberCharacter(next)) makeNumber()
        else if (Characters.isIdentifierStartCharacter(next)) makeIdentifier()
        else if (next == '"') makeString()
        else if (next == '\'') makeCharacter()
        else if (next == '/' && peek == '/') {
            singleLineComment()
            makeToken()
        }
        else if (next == '/' && peek == '*') {
            multiLineComment()
            makeToken()
        }
        else if (next == '%' && peek == '=') {
            input.skip()
            ShasPToken(ShasPTokenType.MOD_ASSIGN, input.position)
        }
        else if (next == '/' && peek == '=') {
            input.skip()
            ShasPToken(ShasPTokenType.DIV_ASSIGN, input.position)
        }
        else if (next == '*' && peek == '=') {
            input.skip()
            ShasPToken(ShasPTokenType.MUL_ASSIGN, input.position)
        }
        else if (next == '-' && peek == '=') {
            input.skip()
            ShasPToken(ShasPTokenType.SUB_ASSIGN, input.position)
        }
        else if (next == '+' && peek == '=') {
            input.skip()
            ShasPToken(ShasPTokenType.ADD_ASSIGN, input.position)
        }
        else if (next == '+' && peek == '+') {
            input.skip()
            ShasPToken(ShasPTokenType.INCR, input.position)
        }
        else if (next == '-' && peek == '-') {
            input.skip()
            ShasPToken(ShasPTokenType.DECR, input.position)
        }
        else if (next == '%') ShasPToken(ShasPTokenType.MOD, input.position)
        else if (next == '/') ShasPToken(ShasPTokenType.DIV, input.position)
        else if (next == '*') ShasPToken(ShasPTokenType.MUL, input.position)
        else if (next == '-') ShasPToken(ShasPTokenType.SUB, input.position)
        else if (next == '+') ShasPToken(ShasPTokenType.ADD, input.position)
        else if (next == '^') ShasPToken(ShasPTokenType.LOGICAL_XOR, input.position)
        else if (next == '|' && peek == '|') {
            input.skip()
            ShasPToken(ShasPTokenType.LOGICAL_OR, input.position)
        }
        else if (next == '&' && peek == '&') {
            input.skip()
            ShasPToken(ShasPTokenType.LOGICAL_AND, input.position)
        }
        else if (next == '=' && peek == '=') {
            input.skip()
            ShasPToken(ShasPTokenType.EQ_EQUALS, input.position)
        }
        else if (next == '!' && peek == '=') {
            input.skip()
            ShasPToken(ShasPTokenType.NOT_EQUALS, input.position)
        }
        else if (next == '>' && peek == '=') {
            input.skip()
            ShasPToken(ShasPTokenType.BIGGER_EQUALS, input.position)
        }
        else if (next == '<' && peek == '=') {
            input.skip()
            ShasPToken(ShasPTokenType.SMALLER_EQUALS, input.position)
        }
        else if (next == '>') ShasPToken(ShasPTokenType.BIGGER, input.position)
        else if (next == '<') ShasPToken(ShasPTokenType.SMALLER, input.position)
        else if (next == '=') ShasPToken(ShasPTokenType.ASSIGN, input.position)
        else if (next == '(') ShasPToken(ShasPTokenType.LPAREN, input.position)
        else if (next == ')') ShasPToken(ShasPTokenType.RPAREN, input.position)
        else if (next == '{') ShasPToken(ShasPTokenType.LCURL, input.position)
        else if (next == '}') return ShasPToken(ShasPTokenType.RCURL, input.position)
        else if (next == '[') return ShasPToken(ShasPTokenType.LBRACKET, input.position)
        else if (next == ']') return ShasPToken(ShasPTokenType.RBRACKET, input.position)
        else throw LexerError("UnexpectedTokenError", "Unrecognised Token: '$next'")
    }

    private fun makeNumber(): ShasPToken {
        val numStr = StringBuilder()
        var dot = false
        numStr.append(input.actual())
        while (input.hasNext() && Characters.isNumberOrDotCharacter(input.peek())) {
            if (input.peek() == '.') {
                if (dot) break
                dot = true
            }
            numStr.append(input.next())
        }
        return if (dot) ShasPToken(ShasPTokenType.DOUBLE, numStr.toString(), input.position) else ShasPToken(
            ShasPTokenType.INTEGER,
            numStr.toString(),
            input.position,
        )
    }

    private fun makeIdentifier(): ShasPToken {
        val identifier = StringBuilder()
        identifier.append(input.actual())
        while (input.hasNext() && Characters.isIdentifierCharacter(input.peek())) {
            identifier.append(input.next())
        }
        val result = identifier.toString()
        val end = input.position
        return ShasPToken(
            when (result) {
                "as" -> ShasPTokenType.KEYWORD_AS
                "boolean" -> ShasPTokenType.KEYWORD_BOOLEAN
                "byte" -> ShasPTokenType.KEYWORD_BYTE
                "char" -> ShasPTokenType.KEYWORD_CHAR
                "do" -> ShasPTokenType.KEYWORD_DO
                "double" -> ShasPTokenType.KEYWORD_DOUBLE
                "else" -> ShasPTokenType.KEYWORD_ELSE
                "false" -> ShasPTokenType.KEYWORD_FALSE
                "float" -> ShasPTokenType.KEYWORD_FLOAT
                "for" -> ShasPTokenType.KEYWORD_FOR
                "if" -> ShasPTokenType.KEYWORD_IF
                "int" -> ShasPTokenType.KEYWORD_INT
                "long" -> ShasPTokenType.KEYWORD_LONG
                "return" -> ShasPTokenType.KEYWORD_RETURN
                "short" -> ShasPTokenType.KEYWORD_SHORT
                "true" -> ShasPTokenType.KEYWORD_TRUE
                "void" -> ShasPTokenType.KEYWORD_VOID
                "while" -> ShasPTokenType.KEYWORD_WHILE
                "unsigned" -> ShasPTokenType.KEYWORD_UNSIGNED
                else -> return ShasPToken(ShasPTokenType.IDENTIFIER, identifier.toString(), end)
            }, end
        )
    }

    private fun makeString(): ShasPToken {
        val string = StringBuilder()
        if (input.actual() == '"') {
            while (input.hasNext() && input.next() != '"') {
                if (input.actual() == '\\') {
                    when (input.next()) {
                        't' -> string.append("\\t")
                        'b' -> string.append("\\b")
                        'n' -> string.append("\\n")
                        'r' -> string.append("\\r")
                        'f' -> string.append("\\f")
                        '\'' -> string.append("\\'")
                        '"' -> string.append("\\\"")
                        '\\' -> string.append("\\\\")
                        'u' -> {
                            string.append("\\u")
                            var i = 0
                            while (i < 4) {
                                val c = input.next()
                                if (!Characters.isHexCharacter(c)) throw LexerError("Expecting hex char")
                                string.append(c)
                                i++
                            }
                        }
                        else -> throw LexerError("Unknown escape sequence '\\" + input.actual() + "'")
                    }
                } else string.append(input.actual())
            }
            if (input.actual() != '"') throw LexerError("String must end with a '\"'")
        }
        return ShasPToken(ShasPTokenType.STRING, string.toString(), input.position)
    }

    private fun makeCharacter(): ShasPToken {
        val c: String = if (input.next() == '\\') {
            when (input.next()) {
                't' -> "\\t"
                'b' -> "\\b"
                'n' -> "\\n"
                'r' -> "\\r"
                'f' -> "\\f"
                '\'' -> "\\'"
                '"' -> "\\\""
                '\\' -> "\\\\"
                'u' -> {
                    val s = StringBuilder().append("\\u")
                    var i = 0
                    while (i < 4) {
                        val ch = input.next()
                        if (!Characters.isHexCharacter(ch)) throw LexerError("Expecting hex char")
                        s.append(ch)
                        i++
                    }
                    s.toString()
                }
                else -> throw LexerError("Unknown escape sequence '\\" + input.actual() + "'")
            }
        } else input.actual().toString()
        if (input.next() != '\'') throw LexerError("Char must end with a \"'\"")
        return ShasPToken(ShasPTokenType.CHARACTER, c, input.position)
    }

    private fun singleLineComment() {
        input.skip(2)
        while (input.hasNext() && input.peek() != '\n') input.skip()
    }

    private fun multiLineComment() {
        input.skip()
        while (input.has(2) && !(input.peek() == '*' && input.peek(2) == '/')) {
            input.skip()
        }
        if (!input.has(2)) throw LexerError("Multi-Line-Comment did not end")
        input.skip(2)
    }

    inner class LexerError(message: String, name: String, details: String, start: Position, end: Position) :
        CompilerError(message, name, details, start, end) {
        @JvmOverloads
        constructor(
            name: String,
            details: String,
            start: Position = input.positionMaker.createPositionAtLocation(),
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
            start: Position = input.positionMaker.createPositionAtLocation(),
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