package com.shakelang.shake.lexer

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.lexer.token.ShakeTokenContext
import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.lexer.token.stream.ShakeTokenInputStream
import com.shakelang.shake.lexer.token.stream.ShakeTokenInputStreamImpl
import com.shakelang.util.parseutils.CompilerError
import com.shakelang.util.parseutils.characters.Characters
import com.shakelang.util.parseutils.characters.position.Position
import com.shakelang.util.parseutils.characters.streaming.CharacterInputStream
import com.shakelang.util.parseutils.lexer.AbstractLexer
import com.shakelang.util.parseutils.lexer.token.TokenCreationContext
import com.shakelang.util.parseutils.lexer.token.TokenFactory
import kotlin.jvm.JvmOverloads

class ShakeLexer(
    input: CharacterInputStream,
) : AbstractLexer<
    ShakeTokenType,
    ShakeToken,
    ShakeTokenInputStream,
    ShakeTokenContext,
    >(input, ShakeTokenContext()) {

    private val eof = factory.create(ShakeTokenType.EOF, input.source.length)
    override fun tokenFactory(ctx: TokenCreationContext<ShakeTokenType, ShakeToken, ShakeTokenInputStream, ShakeTokenContext>): ShakeToken {
        return ShakeToken(
            ctx.type,
            ctx.value,
            ctx.startIndex,
            ctx.endIndex,
            ctx.context,
        )
    }

    override fun createStream(factory: TokenFactory<ShakeToken>) = ShakeTokenInputStreamImpl(factory, input.positionMaker)

    override fun makeToken(): ShakeToken {
        if (!input.hasNext()) {
            return eof
        }

        var next = input.next()

        // Whitespace
        while (Characters.isWhitespaceCharacter(next)) {
            if (!input.hasNext()) {
                return eof
            }

            next = input.next()
        }

        val peek: Char = if (input.hasNext()) input.peek() else (0).toChar()

        // Linebreaks
        return when {
            next == '\n' -> factory.create(ShakeTokenType.LINE_SEPARATOR)
            next == ';' -> factory.create(ShakeTokenType.SEMICOLON)
            next == ',' -> factory.create(ShakeTokenType.COMMA)
            next == ':' -> factory.create(ShakeTokenType.COLON)
            next == '.' -> factory.create(ShakeTokenType.DOT)
            Characters.isNumberCharacter(next) -> makeNumber()
            Characters.isIdentifierStartCharacter(next) -> makeIdentifier()
            next == '"' -> makeString()
            next == '`' -> makeIdentifier2()
            next == '\'' -> makeCharacter()
            next == '/' && peek == '/' -> {
                singleLineComment()
                makeToken()
            }

            next == '/' && peek == '*' -> {
                multiLineComment()
                makeToken()
            }

            input.has(2) && next == '*' && peek == '*' && input.peek(2) == '=' -> {
                input.skip(2)
                factory.create(ShakeTokenType.POW_ASSIGN)
            }

            next == '%' && peek == '=' -> {
                input.skip()
                factory.create(ShakeTokenType.MOD_ASSIGN)
            }

            next == '/' && peek == '=' -> {
                input.skip()
                factory.create(ShakeTokenType.DIV_ASSIGN)
            }

            next == '*' && peek == '=' -> {
                input.skip()
                factory.create(ShakeTokenType.MUL_ASSIGN)
            }

            next == '-' && peek == '=' -> {
                input.skip()
                factory.create(ShakeTokenType.SUB_ASSIGN)
            }

            next == '+' && peek == '=' -> {
                input.skip()
                factory.create(ShakeTokenType.ADD_ASSIGN)
            }

            next == '+' && peek == '+' -> {
                input.skip()
                factory.create(ShakeTokenType.INCR)
            }

            next == '-' && peek == '-' -> {
                input.skip()
                factory.create(ShakeTokenType.DECR)
            }

            next == '*' && peek == '*' -> {
                input.skip()
                factory.create(ShakeTokenType.POW)
            }

            next == '%' -> factory.create(ShakeTokenType.MOD)
            next == '/' -> factory.create(ShakeTokenType.DIV)
            next == '*' -> factory.create(ShakeTokenType.MUL)
            next == '-' -> factory.create(ShakeTokenType.SUB)
            next == '+' -> factory.create(ShakeTokenType.ADD)
            next == '<' && peek == '<' -> {
                input.skip()
                factory.create(ShakeTokenType.BITWISE_SHL)
            }

            next == '>' && peek == '>' -> {
                input.skip()
                if (input.hasNext() && input.peek() == '>') {
                    input.skip()
                    factory.create(ShakeTokenType.BITWISE_USHR)
                } else {
                    factory.create(ShakeTokenType.BITWISE_SHR)
                }
            }

            next == '|' && peek == '|' -> {
                input.skip()
                factory.create(ShakeTokenType.LOGICAL_OR)
            }

            next == '|' && peek == '|' -> {
                input.skip()
                factory.create(ShakeTokenType.LOGICAL_OR)
            }

            next == '&' && peek == '&' -> {
                input.skip()
                factory.create(ShakeTokenType.LOGICAL_AND)
            }

            next == '^' && peek == '^' -> {
                input.skip()
                factory.create(ShakeTokenType.LOGICAL_XOR)
            }

            next == '=' && peek == '=' -> {
                input.skip()
                factory.create(ShakeTokenType.EQ_EQUALS)
            }

            next == '>' && peek == '=' -> {
                input.skip()
                factory.create(ShakeTokenType.BIGGER_EQUALS)
            }

            next == '<' && peek == '=' -> {
                input.skip()
                factory.create(ShakeTokenType.SMALLER_EQUALS)
            }

            next == '!' && peek == '=' -> {
                input.skip()
                factory.create(ShakeTokenType.NOT_EQUALS)
            }

            next == '>' -> factory.create(ShakeTokenType.BIGGER)
            next == '<' -> factory.create(ShakeTokenType.SMALLER)
            next == '!' -> factory.create(ShakeTokenType.LOGICAL_NOT)
            next == '~' && peek == '&' -> {
                input.skip()
                factory.create(ShakeTokenType.BITWISE_NAND)
            }

            next == '~' && peek == '|' -> {
                input.skip()
                factory.create(ShakeTokenType.BITWISE_NOR)
            }

            next == '~' && peek == '^' -> {
                input.skip()
                factory.create(ShakeTokenType.BITWISE_XNOR)
            }

            next == '~' -> factory.create(ShakeTokenType.BITWISE_NOT)
            next == '&' -> factory.create(ShakeTokenType.BITWISE_AND)
            next == '|' -> factory.create(ShakeTokenType.BITWISE_OR)
            next == '^' -> factory.create(ShakeTokenType.BITWISE_XOR)
            next == '=' -> factory.create(ShakeTokenType.ASSIGN)
            next == '(' -> factory.create(ShakeTokenType.LPAREN)
            next == ')' -> factory.create(ShakeTokenType.RPAREN)
            next == '{' -> factory.create(ShakeTokenType.LCURL)
            next == '}' -> factory.create(ShakeTokenType.RCURL)
            else -> throw LexerError("UnexpectedTokenError", "Unrecognised Token: '$next'")
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
            factory.create(ShakeTokenType.FLOAT, start, input.position, numStr.toString())
        } else {
            factory.create(ShakeTokenType.INTEGER, start, input.position, numStr.toString())
        }
    }

    private fun makeIdentifier(): ShakeToken {
        val start = input.position
        val identifierBuilder = StringBuilder()
        identifierBuilder.append(input.actual())
        while (input.hasNext() && Characters.isIdentifierCharacter(input.peek())) {
            identifierBuilder.append(input.next())
        }
        val identifier = identifierBuilder.toString()
        val end = input.position
        return factory.create(
            when (identifier) {
                "abstract" -> ShakeTokenType.KEYWORD_ABSTRACT
                "as" -> ShakeTokenType.KEYWORD_AS
                "class" -> ShakeTokenType.KEYWORD_CLASS
                "const" -> ShakeTokenType.KEYWORD_CONST
                "constructor" -> ShakeTokenType.KEYWORD_CONSTRUCTOR
                "do" -> ShakeTokenType.KEYWORD_DO
                "else" -> ShakeTokenType.KEYWORD_ELSE
                "enum" -> ShakeTokenType.KEYWORD_ENUM
                "false" -> ShakeTokenType.KEYWORD_FALSE
                "final" -> ShakeTokenType.KEYWORD_FINAL
                "for" -> ShakeTokenType.KEYWORD_FOR
                "fun" -> ShakeTokenType.KEYWORD_FUN
                "if" -> ShakeTokenType.KEYWORD_IF
                "import" -> ShakeTokenType.KEYWORD_IMPORT
                "in" -> ShakeTokenType.KEYWORD_IN
                "inline" -> ShakeTokenType.KEYWORD_INLINE
                "instanceof" -> ShakeTokenType.KEYWORD_INSTANCEOF
                "interface" -> ShakeTokenType.KEYWORD_INTERFACE
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
                "static" -> ShakeTokenType.KEYWORD_STATIC
                "super" -> ShakeTokenType.KEYWORD_SUPER
                "synchronized" -> ShakeTokenType.KEYWORD_SYNCHRONIZED
                "this" -> ShakeTokenType.KEYWORD_THIS
                "true" -> ShakeTokenType.KEYWORD_TRUE
                "val" -> ShakeTokenType.KEYWORD_VAL
                "var" -> ShakeTokenType.KEYWORD_VAR
                "while" -> ShakeTokenType.KEYWORD_WHILE
                else -> ShakeTokenType.IDENTIFIER
            },
            start,
            end,
            identifier,
        )
    }

    private fun parseStringCharacter(builder: StringBuilder) {
        if (input.actual() == '\\') {
            when (input.next()) {
                't' -> builder.append("\\t")
                'b' -> builder.append("\\b")
                'n' -> builder.append("\\n")
                'r' -> builder.append("\\r")
                'f' -> builder.append("\\f")
                '\'' -> builder.append("\\'")
                '"' -> builder.append("\\\"")
                '`' -> builder.append("\\\"")
                '\\' -> builder.append("\\\\")
                'u' -> {
                    builder.append("\\u")
                    var i = 0
                    while (i < 4) {
                        val c = input.next()
                        if (!Characters.isHexCharacter(c)) throw LexerError("Expecting hex char")
                        builder.append(c)
                        i++
                    }
                }

                else -> throw LexerError("Unknown escape sequence '\\" + input.actual() + "'")
            }
        } else {
            builder.append(input.actual())
        }
    }

    private fun parseStringLike(builder: StringBuilder, terminator: Char, onlySingleChar: Boolean = false): Int {
        var len = 0
        while (input.hasNext() && input.next() != terminator) {
            if (onlySingleChar && len > 0) throw LexerError("Only single character allowed")
            len++
            parseStringCharacter(builder)
        }
        return len
    }

    private fun makeIdentifier2(): ShakeToken {
        val string = StringBuilder("`")
        val start = input.position
        parseStringLike(string, '`')
        if (input.actual() != '`') throw LexerError("Token name must be enclosed in '`'")
        string.append('`')
        return factory.create(ShakeTokenType.IDENTIFIER, start, input.position, string.toString())
    }

    private fun makeString(): ShakeToken {
        val string = StringBuilder("\"")
        val start = input.position
        parseStringLike(string, '"')
        if (input.actual() != '"') throw LexerError("String must end with a '\"'")
        string.append('"')
        return factory.create(ShakeTokenType.STRING, start, input.position, string.toString())
    }

    private fun makeCharacter(): ShakeToken {
        val start = input.position
        val builder = StringBuilder("'")
        parseStringLike(builder, '\'', true)
        if (input.actual() != '\'') throw LexerError("Char must end with a \"'\"")
        builder.append('\'')
        val token = factory.create(ShakeTokenType.CHARACTER, start, input.position, builder.toString())
        return token
    }

    private fun singleLineComment() {
        input.skip()
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
            end: Position = start,
        ) : this(
            "Error occurred in lexer: " + name + ", " + details + " in " + start.source + ":" + start.line + ":" + start.column,
            name,
            details,
            start,
            end,
        )

        @JvmOverloads
        constructor(
            details: String,
            start: Position = input.positionMaker.createPositionAtLocation(),
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
