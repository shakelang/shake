package com.github.shakelang.shake.util

import com.github.shakelang.shake.util.json.json
import kotlin.js.JsName
import kotlin.jvm.JvmStatic


/**
 * Utilities for wirking with characters
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
object Characters {

    /**
     * Is the given character a hex character (0-f)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    @JvmStatic
    @JsName("isHexCharacter")
    fun isHexCharacter(c: Char): Boolean = c in '0'..'9' || c in 'A'..'F' || c in 'a'..'f'

    /**
     * Is the given character a number character (0-9)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    @JvmStatic
    @JsName("isNumberCharacter")
    fun isNumberCharacter(c: Char): Boolean = c in '0'..'9'

    /**
     * Is the given character a number character (0-9) or a dot character
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    @JvmStatic
    @JsName("isNumberOrDotCharacter")
    fun isNumberOrDotCharacter(c: Char): Boolean = c in '0'..'9' || c == '.'

    /**
     * Is the given character a ignored whitespace character ('\r', '\t' or ' ', **not '\n'**)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    @JvmStatic
    @JsName("isWhitespaceCharacter")
    fun isWhitespaceCharacter(c: Char): Boolean = c == '\r' || c == '\t' || c == ' '

    /**
     * Is the given character a part of an identifier (A-Z, a-z, 0-9 and '_')
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    @JvmStatic
    @JsName("isIdentifierCharacter")
    fun isIdentifierCharacter(c: Char): Boolean = c in '0'..'9' || c in 'A'..'Z' || c in 'a'..'z' || c == '_'

    /**
     * Is the given character the start of a identifier (A-Z, a-z and '_', **not 0-9**)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    @JvmStatic
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
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    @JvmStatic
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
                            c = s[++i]

                            // Throw an error if the character is not a hex character
                            if (!isHexCharacter(c)) throw Error("Expecting hex char, got $c")

                            unicode.append(c)

                        }

                        // Parse the unicode and append it to the string
                        string.append(unicode.toString().toInt(16).toChar())
                    }

                    // When we don't know the escape sequence we throw an error
                    else -> throw Error("Unknown escape sequence '\\${s[i]}'")
                }


            }

            // If the next character is not a escape sequence we
            // just append it to the string result
            else string.append(c)

            // Increase the counting variable
            i++
        }

        // Return the generated string
        return string.toString()
    }
}