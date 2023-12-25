package com.shakelang.util.parseutils.characters

import kotlin.js.JsName

/**
 * Utilities for working with characters
 *
 * @since 0.1.0
 * @version 0.2.1
 */
object Characters {

    /**
     * All characters that are used to encode base16 integers (for encoding and decoding unicodes)
     * The index of the character is the value of the base16 integer
     * This array only contains lowercase characters
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    private val base16chars = "0123456789abcdef".toCharArray()

    /**
     * Is the given character a hex character (0-f)
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    @JsName("isHexCharacter")
    fun isHexCharacter(c: Char): Boolean = c in '0'..'9' || c in 'A'..'F' || c in 'a'..'f'

    /**
     * Is the given character a number character (0-9)
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    @JsName("isNumberCharacter")
    fun isNumberCharacter(c: Char): Boolean = c in '0'..'9'

    /**
     * Is the given character a number character (0-9) or a dot character
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    @JsName("isNumberOrDotCharacter")
    fun isNumberOrDotCharacter(c: Char): Boolean = c in '0'..'9' || c == '.'

    /**
     * Is the given character an ignored whitespace character ('\r', '\t' or ' ', **not '\n'**)
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    @JsName("isWhitespaceCharacter")
    fun isWhitespaceCharacter(c: Char): Boolean = c == '\r' || c == '\t' || c == ' '

    /**
     * Is the given character a part of an identifier (A-Z, a-z, 0-9 and '_')
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    @JsName("isIdentifierCharacter")
    fun isIdentifierCharacter(c: Char): Boolean = c in '0'..'9' || c in 'A'..'Z' || c in 'a'..'z' || c == '_'

    /**
     * Is the given character the start of a identifier (A-Z, a-z and '_', **not 0-9**)
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    @JsName("isIdentifierStartCharacter")
    fun isIdentifierStartCharacter(c: Char): Boolean = c in 'A'..'Z' || c in 'a'..'z' || c == '_'

    /**
     * Parses string contents. Not including string start & end
     * e.g.
     *
     * `"hello world"` → `"hello world"`
     *
     * `"hello\tworld"` → `"hello world"`
     *
     * `"\u0021"` → `"!"`
     *
     * @since 0.2.1
     * @version 0.1.0
     */
    @JsName("parseString")
    fun parseString(s: String): String {
        val string = StringBuilder()

        // We use a while loop because kotlin has no normal for loop
        var i = 0
        while (i < s.length) {
            var c = s[i]

            // Escape sequences
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
                        // parse unicode

                        // create string for storing the unicode
                        val unicode = StringBuilder()

                        // Get the next 4 hex characters
                        val to = i + 4
                        while (i < to) {
                            if (s.length <= ++i) throw IllegalArgumentException("Invalid unicode escape sequence")
                            c = s[i]

                            // Throw an error if the character is not a hex character
                            if (!isHexCharacter(c)) throw IllegalArgumentException("Expecting hex char, got $c")

                            unicode.append(c)
                        }

                        // Parse the unicode and append it to the string
                        string.append(unicode.toString().toInt(16).toChar())
                    }

                    // When we don't know the escape sequence we throw an error
                    else -> throw IllegalArgumentException("Unknown escape sequence '\\${s[i]}'")
                }
            }

            // If the next character is not a escape sequence we
            // just append it to the string result
            else {
                string.append(c)
            }

            // Increase the counting variable
            i++
        }

        // Return the generated string
        return string.toString()
    }

    /**
     * Escape a string
     */
    fun escapeString(str: String): String = str.toCharArray().joinToString("") { escapeCharacter(it) }

    /**
     * Escape a character
     */
    fun escapeCharacter(c: Char): String {
        return when {
            c == '\\' -> "\\\\"
            c == '\t' -> "\\t"
            c == '\b' -> "\\b"
            c == '\n' -> "\\n"
            c == '\r' -> "\\r"
            c == '\'' -> "\\'"
            c == '\"' -> "\\\""
            c.code < 0x20 || c.code > 0x7e -> toUnicode(c)
            else -> c.toString()
        }
    }

    /**
     * Escape a character to a unicode
     *
     * @since 0.2.1
     * @version 0.1.0
     */
    fun toUnicode(char: Char) = "\\u${toBase16(char.code, 4)}"

    /**
     * Get a base 16 char equivalent of an integer
     *
     * @since 0.2.1
     * @version 0.1.0
     */
    fun getBase16Character(number: Int) =
        if (number !in 0..15) throw IllegalArgumentException("Input $number should be in range 0..15") else base16chars[number]

    /**
     * Generate number to base 16
     *
     * @since 0.2.1
     * @version 0.1.0
     */
    fun toBase16(number: Int, digits: Int = 1): String {
        val sb = StringBuilder()
        var n = number
        while (n > 0) {
            sb.append(getBase16Character(n % 16))
            n /= 16
        }
        while (sb.length < digits) sb.append('0')
        return sb.reverse().toString()
    }
}
