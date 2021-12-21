package io.github.shakelang.shake.lexer

import io.github.shakelang.parseutils.CompilerError
import io.github.shakelang.parseutils.characters.Characters
import io.github.shakelang.parseutils.characters.position.Position
import io.github.shakelang.parseutils.characters.streaming.CharacterInputStream
import io.github.shakelang.shake.lexer.token.Token
import io.github.shakelang.shake.lexer.token.TokenType
import kotlin.jvm.JvmOverloads

abstract class LexingBase(
    protected val input: CharacterInputStream
) {

    open fun makeToken(): Token {
        var next = input.next()

        // Whitespace
        while (Characters.isWhitespaceCharacter(next)) next = input.next()

        val peek: Char = if (input.hasNext()) input.peek() else (0).toChar()
        val start = input.position

        // Linebreaks
        return if (next == '\n') Token(TokenType.LINE_SEPARATOR, start)
        else if (next == ';') Token(TokenType.SEMICOLON, start)
        else if (next == ',') Token(TokenType.COMMA, start)
        else if (next == '.') Token(TokenType.DOT, start)
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
        else if (input.has(2) && next == '*' && peek == '*' && input.peek(2) == '=') {
            input.skip(2)
             Token(TokenType.POW_ASSIGN, input.position)
        }
        else if (next == '%' && peek == '=') {
            input.skip()
            Token(TokenType.MOD_ASSIGN, input.position)
        }
        else if (next == '/' && peek == '=') {
            input.skip()
            Token(TokenType.DIV_ASSIGN, input.position)
        }
        else if (next == '*' && peek == '=') {
            input.skip()
            Token(TokenType.MUL_ASSIGN, input.position)
        }
        else if (next == '-' && peek == '=') {
            input.skip()
            Token(TokenType.SUB_ASSIGN, input.position)
        }
        else if (next == '+' && peek == '=') {
            input.skip()
            Token(TokenType.ADD_ASSIGN, input.position)
        }
        else if (next == '+' && peek == '+') {
            input.skip()
            Token(TokenType.INCR, input.position)
        }
        else if (next == '-' && peek == '-') {
            input.skip()
            Token(TokenType.DECR, input.position)
        }
        else if (next == '*' && peek == '*') {
            input.skip()
            Token(TokenType.POW, input.position)
        }
        else if (next == '%') Token(TokenType.MOD, input.position)
        else if (next == '/') Token(TokenType.DIV, input.position)
        else if (next == '*') Token(TokenType.MUL, input.position)
        else if (next == '-') Token(TokenType.SUB, input.position)
        else if (next == '+') Token(TokenType.ADD, input.position)
        else if (next == '^') Token(TokenType.LOGICAL_XOR, input.position)
        else if (next == '|' && peek == '|') {
            input.skip()
            Token(TokenType.LOGICAL_OR, input.position)
        }
        else if (next == '&' && peek == '&') {
            input.skip()
            Token(TokenType.LOGICAL_AND, input.position)
        }
        else if (next == '=' && peek == '=') {
            input.skip()
            Token(TokenType.EQ_EQUALS, input.position)
        }
        else if (next == '>' && peek == '=') {
            input.skip()
            Token(TokenType.BIGGER_EQUALS, input.position)
        }
        else if (next == '<' && peek == '=') {
            input.skip()
            Token(TokenType.SMALLER_EQUALS, input.position)
        }
        else if (next == '>') Token(TokenType.BIGGER, input.position)
        else if (next == '<') Token(TokenType.SMALLER, input.position)
        else if (next == '=') Token(TokenType.ASSIGN, input.position)
        else if (next == '(') Token(TokenType.LPAREN, input.position)
        else if (next == ')') Token(TokenType.RPAREN, input.position)
        else if (next == '{') Token(TokenType.LCURL, input.position)
        else if (next == '}') return Token(TokenType.RCURL, input.position)
        else throw LexerError("UnexpectedTokenError", "Unrecognised Token: '$next'")
    }

    fun createPrimitiveIntArray(list: List<Int>): IntArray {
        val size = list.size
        val arr = IntArray(size)
        for (i in 0 until size) arr[i] = list[i]
        return arr
    }

    fun createPrimitiveByteArray(list: List<Byte>): ByteArray {
        val size = list.size
        val arr = ByteArray(size)
        for (i in 0 until size) arr[i] = list[i]
        return arr
    }

    private fun makeNumber(): Token {
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
        return if (dot) Token(TokenType.DOUBLE, numStr.toString(), input.position) else Token(
            TokenType.INTEGER,
            numStr.toString(),
            input.position,
        )
    }

    private fun makeIdentifier(): Token {
        val identifier = StringBuilder()
        identifier.append(input.actual())
        while (input.hasNext() && Characters.isIdentifierCharacter(input.peek())) {
            identifier.append(input.next())
        }
        val result = identifier.toString()
        val end = input.position
        return Token(
            when (result) {
                "as" -> TokenType.KEYWORD_AS
                "boolean" -> TokenType.KEYWORD_BOOLEAN
                "byte" -> TokenType.KEYWORD_BYTE
                "char" -> TokenType.KEYWORD_CHAR
                "class" -> TokenType.KEYWORD_CLASS
                "const" -> TokenType.KEYWORD_CONST
                "constructor" -> TokenType.KEYWORD_CONSTRUCTOR
                "do" -> TokenType.KEYWORD_DO
                "double" -> TokenType.KEYWORD_DOUBLE
                "dynamic" -> TokenType.KEYWORD_DYNAMIC
                "else" -> TokenType.KEYWORD_ELSE
                "extends" -> TokenType.KEYWORD_EXTENDS
                "false" -> TokenType.KEYWORD_FALSE
                "final" -> TokenType.KEYWORD_FINAL
                "float" -> TokenType.KEYWORD_FLOAT
                "for" -> TokenType.KEYWORD_FOR
                "function" -> TokenType.KEYWORD_FUNCTION
                "if" -> TokenType.KEYWORD_IF
                "implements" -> TokenType.KEYWORD_IMPLEMENTS
                "import" -> TokenType.KEYWORD_IMPORT
                "int" -> TokenType.KEYWORD_INT
                "long" -> TokenType.KEYWORD_LONG
                "new" -> TokenType.KEYWORD_NEW
                "private" -> TokenType.KEYWORD_PRIVATE
                "protected" -> TokenType.KEYWORD_PROTECTED
                "public" -> TokenType.KEYWORD_PUBLIC
                "return" -> TokenType.KEYWORD_RETURN
                "short" -> TokenType.KEYWORD_SHORT
                "static" -> TokenType.KEYWORD_STATIC
                "true" -> TokenType.KEYWORD_TRUE
                "var", "let" -> TokenType.KEYWORD_VAR
                "void" -> TokenType.KEYWORD_VOID
                "while" -> TokenType.KEYWORD_WHILE
                else -> return Token(TokenType.IDENTIFIER, identifier.toString(), end)
            }, end
        )
    }

    private fun makeString(): Token {
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
        return Token(TokenType.STRING, string.toString(), input.position)
    }

    private fun makeCharacter(): Token {
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
        return Token(TokenType.CHARACTER, c, input.position)
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