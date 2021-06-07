package com.github.shakelang.shake.util

import kotlin.jvm.JvmStatic

object Characters {

    @JvmStatic
    fun isHexCharacter(c: Char): Boolean = c in '0'..'9' || c in 'A'..'F' || c in 'a'..'f'

    @JvmStatic
    fun isNumberCharacter(c: Char): Boolean = c in '0'..'9'

    @JvmStatic
    fun isNumberOrDotCharacter(c: Char): Boolean = c in '0'..'9' || c == '.'

    @JvmStatic
    fun isWhitespaceCharacter(c: Char): Boolean = c == '\r' || c == '\t' || c == ' '

    @JvmStatic
    fun isIdentifierCharacter(c: Char): Boolean = c in '0'..'9' || c in 'A'..'Z' || c in 'a'..'z' || c == '_'

    @JvmStatic
    fun isIdentifierStartCharacter(c: Char): Boolean = c in 'A'..'Z' || c in 'a'..'z' || c == '_'

    @JvmStatic
    fun parseString(s: String): String {
        val string = StringBuilder()
        val length = s.length
        var i = 0
        while (i < length) {
            var c = s[i]
            if (c == '\\') {
                when (s[++i]) {
                    't' -> string.append('\t')
                    'b' -> string.append('\b')
                    'n' -> string.append('\n')
                    'r' -> string.append('\r')
                    '\'' -> string.append('\'')
                    '"' -> string.append('\"')
                    '\\' -> string.append('\\')
                    'u' -> {
                        val unicode = StringBuilder()
                        val to = i + 5
                        while (i < to) {
                            c = s[i]
                            if (!isHexCharacter(c)) throw Error("Expecting hex char")
                            unicode.append(c)
                            ++i
                        }
                        string.append(unicode.toString().toInt(16))
                    }
                    else -> throw Error("Unknown escape sequence '\\${s[i]}'")
                }
            } else string.append(c)
            i++
        }
        return string.toString()
    }
}