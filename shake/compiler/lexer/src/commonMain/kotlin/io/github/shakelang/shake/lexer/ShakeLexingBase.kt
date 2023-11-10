package io.github.shakelang.shake.lexer

import io.github.shakelang.shake.lexer.token.ShakeToken
import io.github.shakelang.shake.lexer.token.ShakeTokenType
import io.github.shakelang.shake.util.parseutils.characters.Characters
import io.github.shakelang.shake.util.parseutils.characters.position.Position
import io.github.shakelang.shake.util.parseutils.characters.streaming.CharacterInputStream
import io.github.shakelang.shake.util.parseutils.lexer.LexingBase
import kotlin.jvm.JvmOverloads

abstract class ShakeLexingBase(
    input: CharacterInputStream
) : LexingBase<ShakeTokenType, ShakeToken>(input) {

    override fun makeToken(): ShakeToken {
        var next = input.next()

        // Whitespace
        while (Characters.isWhitespaceCharacter(next)) next = input.next()

        val peek: Char = if (input.hasNext()) input.peek() else (0).toChar()
        val start = input.position

        // Linebreaks
        return if (next == '\n') {
            ShakeToken(ShakeTokenType.LINE_SEPARATOR, start, start)
        } else if (next == ';') {
            ShakeToken(ShakeTokenType.SEMICOLON, start, start)
        } else if (next == ',') {
            ShakeToken(ShakeTokenType.COMMA, start, start)
        } else if (next == '.') {
            ShakeToken(ShakeTokenType.DOT, start, start)
        } else if (Characters.isNumberCharacter(next)) {
            makeNumber()
        } else if (Characters.isIdentifierStartCharacter(next)) {
            makeIdentifier()
        } else if (next == '"') {
            makeString()
        } else if (next == '`') {
            makeIdentifier2()
        } else if (next == '\'') {
            makeCharacter()
        } else if (next == '/' && peek == '/') {
            singleLineComment()
            makeToken()
        } else if (next == '/' && peek == '*') {
            multiLineComment()
            makeToken()
        } else if (input.has(2) && next == '*' && peek == '*' && input.peek(2) == '=') {
            input.skip(2)
            ShakeToken(ShakeTokenType.POW_ASSIGN, input.position, input.position)
        } else if (next == '%' && peek == '=') {
            input.skip()
            ShakeToken(ShakeTokenType.MOD_ASSIGN, input.position, input.position)
        } else if (next == '/' && peek == '=') {
            input.skip()
            ShakeToken(ShakeTokenType.DIV_ASSIGN, input.position, input.position)
        } else if (next == '*' && peek == '=') {
            input.skip()
            ShakeToken(ShakeTokenType.MUL_ASSIGN, input.position, input.position)
        } else if (next == '-' && peek == '=') {
            input.skip()
            ShakeToken(ShakeTokenType.SUB_ASSIGN, input.position, input.position)
        } else if (next == '+' && peek == '=') {
            input.skip()
            ShakeToken(ShakeTokenType.ADD_ASSIGN, input.position, input.position)
        } else if (next == '+' && peek == '+') {
            input.skip()
            ShakeToken(ShakeTokenType.INCR, input.position, input.position)
        } else if (next == '-' && peek == '-') {
            input.skip()
            ShakeToken(ShakeTokenType.DECR, input.position, input.position)
        } else if (next == '*' && peek == '*') {
            input.skip()
            ShakeToken(ShakeTokenType.POW, input.position, input.position)
        } else if (next == '%') {
            ShakeToken(ShakeTokenType.MOD, input.position, input.position)
        } else if (next == '/') {
            ShakeToken(ShakeTokenType.DIV, input.position, input.position)
        } else if (next == '*') {
            ShakeToken(ShakeTokenType.MUL, input.position, input.position)
        } else if (next == '-') {
            ShakeToken(ShakeTokenType.SUB, input.position, input.position)
        } else if (next == '+') {
            ShakeToken(ShakeTokenType.ADD, input.position, input.position)
        } else if (next == '<' && peek == '<') {
            input.skip()
            ShakeToken(ShakeTokenType.BITWISE_SHL, input.position, input.position)
        } else if (next == '>' && peek == '>') {
            input.skip()
            ShakeToken(ShakeTokenType.BITWISE_SHR, input.position, input.position)
        } else if (next == '|' && peek == '|') {
            input.skip()
            ShakeToken(ShakeTokenType.LOGICAL_OR, input.position, input.position)
        } else if (next == '|' && peek == '|') {
            input.skip()
            ShakeToken(ShakeTokenType.LOGICAL_OR, input.position, input.position)
        } else if (next == '&' && peek == '&') {
            input.skip()
            ShakeToken(ShakeTokenType.LOGICAL_AND, input.position, input.position)
        } else if (next == '^' && peek == '^') {
            input.skip()
            ShakeToken(ShakeTokenType.LOGICAL_XOR, input.position, input.position)
        } else if (next == '=' && peek == '=') {
            input.skip()
            ShakeToken(ShakeTokenType.EQ_EQUALS, input.position, input.position)
        } else if (next == '>' && peek == '=') {
            input.skip()
            ShakeToken(ShakeTokenType.BIGGER_EQUALS, input.position, input.position)
        } else if (next == '<' && peek == '=') {
            input.skip()
            ShakeToken(ShakeTokenType.SMALLER_EQUALS, input.position, input.position)
        } else if (next == '>') {
            ShakeToken(ShakeTokenType.BIGGER, input.position, input.position)
        } else if (next == '<') {
            ShakeToken(ShakeTokenType.SMALLER, input.position, input.position)
        } else if (next == '!') {
            ShakeToken(ShakeTokenType.LOGICAL_NOT, input.position, input.position)
        } else if (next == '~' && peek == '&') {
            input.skip()
            ShakeToken(ShakeTokenType.BITWISE_NAND, input.position, input.position)
        } else if (next == '~' && peek == '|') {
            input.skip()
            ShakeToken(ShakeTokenType.BITWISE_NOR, input.position, input.position)
        } else if (next == '~' && peek == '^') {
            input.skip()
            ShakeToken(ShakeTokenType.BITWISE_XNOR, input.position, input.position)
        } else if (next == '~') {
            ShakeToken(ShakeTokenType.BITWISE_NOT, input.position, input.position)
        } else if (next == '&') {
            ShakeToken(ShakeTokenType.BITWISE_AND, input.position, input.position)
        } else if (next == '|') {
            ShakeToken(ShakeTokenType.BITWISE_OR, input.position, input.position)
        } else if (next == '^') {
            ShakeToken(ShakeTokenType.BITWISE_XOR, input.position, input.position)
        } else if (next == '=') {
            ShakeToken(ShakeTokenType.ASSIGN, input.position, input.position)
        } else if (next == '(') {
            ShakeToken(ShakeTokenType.LPAREN, input.position, input.position)
        } else if (next == ')') {
            ShakeToken(ShakeTokenType.RPAREN, input.position, input.position)
        } else if (next == '{') {
            ShakeToken(ShakeTokenType.LCURL, input.position, input.position)
        } else if (next == '}') {
            return ShakeToken(ShakeTokenType.RCURL, input.position, input.position)
        } else if (next == '[') {
            ShakeToken(ShakeTokenType.LSQBR, input.position, input.position)
        } else if (next == ']') {
            ShakeToken(ShakeTokenType.RSQBR, input.position, input.position)
        } else {
            throw LexerError("UnexpectedTokenError", "Unrecognised Token: '$next'")
        }
    }

    private fun makeNumber(): ShakeToken {
        val numStr = StringBuilder()
        var dot = false
        val start = input.position
        numStr.append(input.actual())
        while (input.hasNext() && Characters.isNumberOrDotCharacter(input.peek())) {
            if (input.peek() == '.') {
                if (dot) break
                dot = true
            }
            numStr.append(input.next())
        }
        return if (dot) {
            ShakeToken(ShakeTokenType.DOUBLE, numStr.toString(), start, input.position)
        } else {
            ShakeToken(ShakeTokenType.INTEGER, numStr.toString(), start, input.position)
        }
    }

    private fun makeIdentifier(): ShakeToken {
        val start = input.position
        val identifier = StringBuilder()
        identifier.append(input.actual())
        while (input.hasNext() && Characters.isIdentifierCharacter(input.peek())) {
            identifier.append(input.next())
        }
        val result = identifier.toString()
        val end = input.position
        return ShakeToken(
            when (result) {
                "abstract" -> ShakeTokenType.KEYWORD_ABSTRACT
                "as" -> ShakeTokenType.KEYWORD_AS
                "boolean" -> ShakeTokenType.KEYWORD_BOOLEAN
                "byte" -> ShakeTokenType.KEYWORD_BYTE
                "char" -> ShakeTokenType.KEYWORD_CHAR
                "class" -> ShakeTokenType.KEYWORD_CLASS
                "const" -> ShakeTokenType.KEYWORD_CONST
                "constructor" -> ShakeTokenType.KEYWORD_CONSTRUCTOR
                "do" -> ShakeTokenType.KEYWORD_DO
                "double" -> ShakeTokenType.KEYWORD_DOUBLE
                "dynamic" -> ShakeTokenType.KEYWORD_DYNAMIC
                "else" -> ShakeTokenType.KEYWORD_ELSE
                "enum" -> ShakeTokenType.KEYWORD_ENUM
                "extends" -> ShakeTokenType.KEYWORD_EXTENDS
                "false" -> ShakeTokenType.KEYWORD_FALSE
                "final" -> ShakeTokenType.KEYWORD_FINAL
                "float" -> ShakeTokenType.KEYWORD_FLOAT
                "for" -> ShakeTokenType.KEYWORD_FOR
                "function" -> ShakeTokenType.KEYWORD_FUNCTION
                "if" -> ShakeTokenType.KEYWORD_IF
                "implements" -> ShakeTokenType.KEYWORD_IMPLEMENTS
                "import" -> ShakeTokenType.KEYWORD_IMPORT
                "in" -> ShakeTokenType.KEYWORD_IN
                "inline" -> ShakeTokenType.KEYWORD_INLINE
                "instanceof" -> ShakeTokenType.KEYWORD_INSTANCEOF
                "int" -> ShakeTokenType.KEYWORD_INT
                "interface" -> ShakeTokenType.KEYWORD_INTERFACE
                "long" -> ShakeTokenType.KEYWORD_LONG
                "native" -> ShakeTokenType.KEYWORD_NATIVE
                "new" -> ShakeTokenType.KEYWORD_NEW
                "null" -> ShakeTokenType.KEYWORD_NULL
                "object" -> ShakeTokenType.KEYWORD_OBJECT
                "operator" -> ShakeTokenType.KEYWORD_OPERATOR
                "override" -> ShakeTokenType.KEYWORD_OVERRIDE
                "package" -> ShakeTokenType.KEYWORD_PACKAGE
                "private" -> ShakeTokenType.KEYWORD_PRIVATE
                "protected" -> ShakeTokenType.KEYWORD_PROTECTED
                "public" -> ShakeTokenType.KEYWORD_PUBLIC
                "return" -> ShakeTokenType.KEYWORD_RETURN
                "short" -> ShakeTokenType.KEYWORD_SHORT
                "static" -> ShakeTokenType.KEYWORD_STATIC
                "super" -> ShakeTokenType.KEYWORD_SUPER
                "synchronized" -> ShakeTokenType.KEYWORD_SYNCHRONIZED
                "this" -> ShakeTokenType.KEYWORD_THIS
                "true" -> ShakeTokenType.KEYWORD_TRUE
                "unsigned" -> ShakeTokenType.KEYWORD_UNSIGNED
                "void" -> ShakeTokenType.KEYWORD_VOID
                "while" -> ShakeTokenType.KEYWORD_WHILE
                else -> return ShakeToken(ShakeTokenType.IDENTIFIER, identifier.toString(), start, end)
            },
            start,
            end
        )
    }

    private fun makeIdentifier2(): ShakeToken {
        val string = StringBuilder()
        val start = input.position
        if (input.actual() == '`') {
            while (input.hasNext() && input.next() != '`') {
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
                } else {
                    string.append(input.actual())
                }
            }
            if (input.actual() != '`') throw LexerError("Token name must be enclosed in '`'")
        }
        return ShakeToken(ShakeTokenType.IDENTIFIER, string.toString(), start, input.position)
    }

    private fun makeString(): ShakeToken {
        val string = StringBuilder()
        val start = input.position
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
                } else {
                    string.append(input.actual())
                }
            }
            if (input.actual() != '"') throw LexerError("String must end with a '\"'")
        }
        return ShakeToken(ShakeTokenType.STRING, string.toString(), start, input.position)
    }

    private fun makeCharacter(): ShakeToken {
        val start = input.position
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
        } else {
            input.actual().toString()
        }
        if (input.next() != '\'') throw LexerError("Char must end with a \"'\"")
        return ShakeToken(ShakeTokenType.CHARACTER, c, start, input.position)
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
        io.github.shakelang.shake.util.parseutils.CompilerError(message, name, details, start, end) {
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
