package com.github.nsc.de.shake.lexer

import com.github.nsc.de.shake.lexer.token.TokenInputStream
import com.github.nsc.de.shake.lexer.token.TokenType
import com.github.nsc.de.shake.util.Characters
import com.github.nsc.de.shake.util.CompilerError
import com.github.nsc.de.shake.util.characterinput.characterinputstream.CharacterInputStream
import com.github.nsc.de.shake.util.characterinput.position.Position

class Lexer(
    private val input: CharacterInputStream
) {
    private val tokens: MutableList<Byte> = ArrayList()
    private val positions: MutableList<Int> = ArrayList()
    private val values: MutableList<String> = ArrayList()
    fun makeTokens(): TokenInputStream {
        while (input.hasNext()) {
            val next = input.next()
            val peek: Char = if (input.hasNext()) input.peek() else (0).toChar()
            val start = input.position

            // Whitespace
            if (Characters.isWhitespaceCharacter(next)) continue

            // Linebreaks
            if (next == '\n') addPosition(TokenType.LINE_SEPARATOR,start)
            else if (next == ';') addPosition(TokenType.SEMICOLON,start)
            else if (next == ',') addPosition(TokenType.COMMA, start)
            else if (next == '.') addPosition(TokenType.DOT,start)
            else if (Characters.isNumberCharacter(next)) makeNumber()
            else if (Characters.isIdentifierStartCharacter(next)) makeIdentifier()
            else if (next == '"') makeString()
            else if (next == '\'') makeCharacter()
            else if (next == '/' && peek == '/') singleLineComment()
            else if (next == '/' && peek == '*') multiLineComment()
            else if (input.has(2) && next == '*' && peek == '*' && input.peek(2) == '=') {
                input.skip(2)
                addPosition(TokenType.POW_ASSIGN, input.position)
            } else if (next == '%' && peek == '=') {
                input.skip()
                addPosition(TokenType.MOD_ASSIGN, input.position)
            } else if (next == '/' && peek == '=') {
                input.skip()
                addPosition(TokenType.DIV_ASSIGN, input.position)
            } else if (next == '*' && peek == '=') {
                input.skip()
                addPosition(TokenType.MUL_ASSIGN, input.position)
            } else if (next == '-' && peek == '=') {
                input.skip()
                addPosition(TokenType.SUB_ASSIGN, input.position)
            } else if (next == '+' && peek == '=') {
                input.skip()
                addPosition(TokenType.ADD_ASSIGN, input.position)
            } else if (next == '+' && peek == '+') {
                input.skip()
                addPosition(TokenType.INCR, input.position)
            } else if (next == '-' && peek == '-') {
                input.skip()
                addPosition(TokenType.DECR, input.position)
            } else if (next == '*' && peek == '*') {
                input.skip()
                addPosition(TokenType.POW, input.position)
            } else if (next == '%') addPosition(TokenType.MOD, input.position)
            else if (next == '/') addPosition(TokenType.DIV,input.position)
            else if (next == '*') addPosition(TokenType.MUL, input.position)
            else if (next == '-') addPosition(TokenType.SUB,input.position)
            else if (next == '+') addPosition(TokenType.ADD, input.position)
            else if (next == '^') addPosition(TokenType.LOGICAL_XOR, input.position)
            else if (next == '|' && peek == '|') {
                input.skip()
                addPosition(TokenType.LOGICAL_OR, input.position)
            } else if (next == '&' && peek == '&') {
                input.skip()
                addPosition(TokenType.LOGICAL_AND, input.position)
            } else if (next == '=' && peek == '=') {
                input.skip()
                addPosition(TokenType.EQ_EQUALS, input.position)
            } else if (next == '>' && peek == '=') {
                input.skip()
                addPosition(TokenType.BIGGER_EQUALS, input.position)
            } else if (next == '<' && peek == '=') {
                input.skip()
                addPosition(TokenType.SMALLER_EQUALS, input.position)
            } else if (next == '>') addPosition(TokenType.BIGGER, input.position) else if (next == '<') addPosition(
                TokenType.SMALLER,
                input.position
            ) else if (next == '=') addPosition(TokenType.ASSIGN, input.position) else if (next == '(') addPosition(
                TokenType.LPAREN,
                input.position
            ) else if (next == ')') addPosition(TokenType.RPAREN, input.position) else if (next == '{') addPosition(
                TokenType.LCURL,
                input.position
            ) else if (next == '}') addPosition(
                TokenType.RCURL,
                input.position
            ) else throw LexerError("UnexpectedTokenError", "Unrecognised Token: '$next'")
        }
        return TokenInputStream(
            input.source.location!!,
            createPrimitiveByteArray(tokens),
            values.toTypedArray(),
            createPrimitiveIntArray(positions),
            input.positionMaker.createPositionMap()
        )
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

    private fun addPosition(type: Byte, end: Int) {
        tokens.add(type)
        positions.add(end)
    }

    private fun addPosition(type: Byte, end: Int, value: String) {
        tokens.add(type)
        positions.add(end)
        values.add(value)
    }

    private fun makeNumber() {
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
        if (dot) addPosition(TokenType.DOUBLE, input.position, numStr.toString()) else addPosition(
            TokenType.INTEGER,
            input.position,
            numStr.toString()
        )
    }

    private fun makeIdentifier() {
        val identifier = StringBuilder()
        identifier.append(input.actual())
        while (input.hasNext() && Characters.isIdentifierCharacter(input.peek())) {
            identifier.append(input.next())
        }
        val result = identifier.toString()
        val end = input.position
        val chars = result.toCharArray()
        when (result.length) {
            2 -> {
                // do keyword
                if (chars[0] == 'd') {
                    if (chars[1] == 'o') {
                        addPosition(TokenType.KEYWORD_DO, end)
                        return
                    }
                }
                if (chars[0] == 'a') {
                    if (chars[1] == 's') {
                        addPosition(TokenType.KEYWORD_AS, end)
                        return
                    }
                }
                // if keyword
                if (chars[0] == 'i' && chars[1] == 'f') {
                    addPosition(TokenType.KEYWORD_IF, end)
                    return
                }
            }
            3 -> {
                if (chars[0] == 'v') {
                    if (chars[1] == 'a' && chars[2] == 'r') {
                        addPosition(TokenType.KEYWORD_VAR, end)
                        return
                    }
                }
                if (chars[0] == 'l') {
                    if (chars[1] == 'e' && chars[2] == 't') {
                        addPosition(TokenType.KEYWORD_VAR, end)
                        return
                    }
                }
                if (chars[0] == 'i') {
                    if (chars[1] == 'n' && chars[2] == 't') {
                        addPosition(TokenType.KEYWORD_INT, end)
                        return
                    }
                }
                if (chars[0] == 'n') {
                    if (chars[1] == 'e' && chars[2] == 'w') {
                        addPosition(TokenType.KEYWORD_NEW, end)
                        return
                    }
                }
                if (chars[0] == 'f' && chars[1] == 'o' && chars[2] == 'r') {
                    addPosition(TokenType.KEYWORD_FOR, end)
                    return
                }
            }
            4 -> {
                if (chars[0] == 'b') {
                    if (chars[1] == 'y' && chars[2] == 't' && chars[3] == 'e') {
                        addPosition(TokenType.KEYWORD_BYTE, end)
                        return
                    }
                }
                if (chars[0] == 'v') {
                    if (chars[1] == 'o' && chars[2] == 'i' && chars[3] == 'd') {
                        addPosition(TokenType.KEYWORD_VOID, end)
                        return
                    }
                }
                if (chars[0] == 'l') {
                    if (chars[1] == 'o' && chars[2] == 'n' && chars[3] == 'g') {
                        addPosition(TokenType.KEYWORD_LONG, end)
                        return
                    }
                }
                if (chars[0] == 'c') {
                    if (chars[1] == 'h' && chars[2] == 'a' && chars[3] == 'r') {
                        addPosition(TokenType.KEYWORD_CHAR, end)
                        return
                    }
                }
                if (chars[0] == 't') {
                    if (chars[1] == 'r' && chars[2] == 'u' && chars[3] == 'e') {
                        addPosition(TokenType.KEYWORD_TRUE, end)
                        return
                    }
                }
                if (chars[0] == 'e' && chars[1] == 'l' && chars[2] == 's' && chars[3] == 'e') {
                    addPosition(TokenType.KEYWORD_ELSE, end)
                    return
                }
            }
            5 -> {
                if (chars[0] == 'f') {
                    if (chars[1] == 'a' && chars[2] == 'l' && chars[3] == 's' && chars[4] == 'e') {
                        addPosition(TokenType.KEYWORD_FALSE, end)
                        return
                    }
                    if (chars[1] == 'l' && chars[2] == 'o' && chars[3] == 'a' && chars[4] == 't') {
                        addPosition(TokenType.KEYWORD_FLOAT, end)
                        return
                    }
                    if (chars[1] == 'i' && chars[2] == 'n' && chars[3] == 'a' && chars[4] == 'l') {
                        addPosition(TokenType.KEYWORD_FINAL, end)
                        return
                    }
                }
                if (chars[0] == 'c') {
                    if (chars[1] == 'o' && chars[2] == 'n' && chars[3] == 's' && chars[4] == 't') {
                        addPosition(TokenType.KEYWORD_CONST, end)
                        return
                    }
                    if (chars[1] == 'l' && chars[2] == 'a' && chars[3] == 's' && chars[4] == 's') {
                        addPosition(TokenType.KEYWORD_CLASS, end)
                        return
                    }
                }
                if (chars[0] == 's') {
                    if (chars[1] == 'h' && chars[2] == 'o' && chars[3] == 'r' && chars[4] == 't') {
                        addPosition(TokenType.KEYWORD_SHORT, end)
                        return
                    }
                }
                if (chars[0] == 'w' && chars[1] == 'h' && chars[2] == 'i' && chars[3] == 'l' && chars[4] == 'e') {
                    addPosition(TokenType.KEYWORD_WHILE, end)
                    return
                }
            }
            6 -> {
                if (chars[0] == 'd') {
                    if (chars[1] == 'o' && chars[2] == 'u' && chars[3] == 'b' && chars[4] == 'l' && chars[5] == 'e') {
                        addPosition(TokenType.KEYWORD_DOUBLE, end)
                        return
                    }
                }
                if (chars[0] == 'r') {
                    if (chars[1] == 'e' && chars[2] == 't' && chars[3] == 'u' && chars[4] == 'r' && chars[5] == 'n') {
                        addPosition(TokenType.KEYWORD_RETURN, end)
                        return
                    }
                }
                if (chars[0] == 's') {
                    if (chars[1] == 't' && chars[2] == 'a' && chars[3] == 't' && chars[4] == 'i' && chars[5] == 'c') {
                        addPosition(TokenType.KEYWORD_STATIC, end)
                        return
                    }
                }
                if (chars[0] == 'i') {
                    if (chars[1] == 'm' && chars[2] == 'p' && chars[3] == 'o' && chars[4] == 'r' && chars[5] == 't') {
                        addPosition(TokenType.KEYWORD_IMPORT, end)
                        return
                    }
                }
                if (chars[0] == 'p' && chars[1] == 'u' && chars[2] == 'b' && chars[3] == 'l' && chars[4] == 'i' && chars[5] == 'c') {
                    addPosition(TokenType.KEYWORD_PUBLIC, end)
                    return
                }
            }
            7 -> {
                if (chars[0] == 'd') {
                    if (chars[1] == 'y' && chars[2] == 'n' && chars[3] == 'a' && chars[4] == 'm' && chars[5] == 'i' && chars[6] == 'c') {
                        addPosition(TokenType.KEYWORD_DYNAMIC, end)
                        return
                    }
                }
                if (chars[0] == 'b') {
                    if (chars[1] == 'o' && chars[2] == 'o' && chars[3] == 'l' && chars[4] == 'e' && chars[5] == 'a' && chars[6] == 'n') {
                        addPosition(TokenType.KEYWORD_BOOLEAN, end)
                        return
                    }
                }
                if (chars[0] == 'e') {
                    if (chars[1] == 'x' && chars[2] == 't' && chars[3] == 'e' && chars[4] == 'n' && chars[5] == 'd' && chars[6] == 's') {
                        addPosition(TokenType.KEYWORD_EXTENDS, end)
                        return
                    }
                }
                if (chars[0] == 'p' && chars[1] == 'r' && chars[2] == 'i' && chars[3] == 'v' && chars[4] == 'a' && chars[5] == 't' && chars[6] == 'e') {
                    addPosition(TokenType.KEYWORD_PRIVATE, end)
                    return
                }
            }
            8 -> if (chars[0] == 'f') {
                if (chars[1] == 'u' && chars[2] == 'n' && chars[3] == 'c' && chars[4] == 't' && chars[5] == 'i' && chars[6] == 'o' && chars[7] == 'n') {
                    addPosition(TokenType.KEYWORD_FUNCTION, end)
                    return
                }
            }
            9 -> if (chars[0] == 'p' && chars[1] == 'r' && chars[2] == 'o' && chars[3] == 't' && chars[4] == 'e' && chars[5] == 'c' && chars[6] == 't' && chars[7] == 'e' && chars[8] == 'd') {
                addPosition(TokenType.KEYWORD_PROTECTED, end)
                return
            }
            10 -> if (chars[0] == 'i' && chars[1] == 'm' && chars[2] == 'p' && chars[3] == 'l' && chars[4] == 'e' && chars[5] == 'm' && chars[6] == 'e' && chars[7] == 'n' && chars[8] == 't' && chars[9] == 's') {
                addPosition(TokenType.KEYWORD_IMPLEMENTS, end)
                return
            }
            11 -> if (chars[0] == 'c' && chars[1] == 'o' && chars[2] == 'n' && chars[3] == 's' && chars[4] == 't' && chars[5] == 'r' && chars[6] == 'u' && chars[7] == 'c' && chars[8] == 't' && chars[9] == 'o' && chars[10] == 'r') {
                addPosition(TokenType.KEYWORD_CONSTRUCTOR, end)
                return
            }
        }
        addPosition(TokenType.IDENTIFIER, end, identifier.toString())
    }

    private fun makeString() {
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
        addPosition(TokenType.STRING, input.position, string.toString())
    }

    private fun makeCharacter() {
        val c: String
        c = if (input.next() == '\\') {
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
        addPosition(TokenType.CHARACTER, input.position, c)
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
        ) {
        }

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
        ) {
        }
    }
}