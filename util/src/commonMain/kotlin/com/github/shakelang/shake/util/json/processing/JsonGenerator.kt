package com.github.shakelang.shake.util.json.processing

import com.github.shakelang.shake.util.Characters


/**
 * API for generating json
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
object JsonGenerator {

    /**
     * All characters that are used to encode base16 integers (for encoding and decoding unicodes)
     */
    private val base16chars = "0123456789abcdef".toCharArray()

    /**
     * The line-separator to generate when generating beautified json
     */
    const val LINE_SEPARATOR = "\n"

    /**
     * Generate an Array
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    private fun generate(o: Array<*>, indent: String? = null, indentAmount: Int = 0): String {
        return "[" +

                // Indention of first line
                (if(o.isNotEmpty() && indent != null) LINE_SEPARATOR + indent.repeat(indentAmount) else "") +

                // Join the children
                (o.joinToString(

                    // Indention of all lines (except first) and Comma between elements
                    if (o.isNotEmpty() && indent != null) "," + LINE_SEPARATOR + indent.repeat(indentAmount)
                    else ","

                ) { generate(it!!, indent, indentAmount + 1) }) + "]"
    }

    /**
     * Generate an Object
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    private fun generate(o: Map<*, *>, indent: String? = null, indentAmount: Int = 0): String {
        var ret = "{"
        val iterator = o.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            val key = next.key.toString()
            val value = next.value
            if (indent != null) ret += LINE_SEPARATOR + indent.repeat(indentAmount + 1)
            ret += (if (isNamespaceAble(key)) key else "\"${escape(key)}\"") + ":"
            if(indent != null) ret += " "
            ret += generate(
                value,
                indent,
                indentAmount + 1
            )
            if (iterator.hasNext()) ret += ','
        }
        if (o.isNotEmpty() && indent != null) ret += LINE_SEPARATOR + indent.repeat(indentAmount)
        return "$ret}"
    }

    /**
     * Generate a String
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    private fun generate(s: String) = "\"${escape(s)}\""

    /**
     * Generate something
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun generate(o: Any?, indent: String? = null, indentAmount: Int = 0): String {
        val ret = when (o) {
            null -> return "null"
            is Boolean, Byte, Short, Int, Long, Float, Double -> o.toString()
            is Map<*, *> -> generate(o, indent = indent, indentAmount = indentAmount)
            is Array<*> -> generate(o, indent = indent, indentAmount = indentAmount)
            is Set<*> -> generate(o.toTypedArray(), indent = indent, indentAmount = indentAmount)
            is List<*> -> generate(o.toTypedArray(), indent = indent, indentAmount = indentAmount)
            else -> generate(o.toString())
        }
        return ret
    }

    /**
     * Is a function namespaceable? (Only contains a-zA-Z0-9_ and starts with a-zA-Z_)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    private fun isNamespaceAble(str: String): Boolean {
        if (str.isEmpty()) return false
        val chars = str.toCharArray()
        if (!Characters.isIdentifierStartCharacter(chars[0])) return false
        chars.drop(1)
        for (c in chars) if (!Characters.isIdentifierCharacter(c)) return false
        return true
    }

    /**
     * Escape a string
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    private fun escape(str: String): String = str.toCharArray().joinToString { escape(it) }

    /**
     * Escape a character
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    private fun escape(c: Char): String {
        return when {
            c == '\\' -> "\\\\"
            c == '\t' -> "\\t"
            c == '\b' -> "\\b"
            c == '\n' -> "\\n"
            c == '\r' -> "\\r"
            c == '\'' -> "\\'"
            c == '\"' -> "\\\""
            c.code > 255 -> toUnicode(c)
            else -> c.toString()
        }
    }

    /**
     * Escape a character to an unicode
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    private fun toUnicode(char: Char) = "\\u${toBase16(char.code)}"

    /**
     * Get a base 16 char equivalent of an integer
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;]
     */
    private fun getBase16Character(number: Int) =
        if (number !in 0..15) throw Error("Input $number should be in range 1..15") else base16chars[number]

    /**
     * Generate number to base 16
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    private fun toBase16(number: Int): String {
        var i = number
        var x = 65536
        var base16chars = ""
        while (x > 1) {
            base16chars += getBase16Character(i / x)
            i %= x
            x /= 16
        }
        return base16chars
    }
}