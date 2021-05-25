package com.github.nsc.de.shake.util.json

import com.github.nsc.de.shake.util.Characters

object JsonGenerator {
    private val base16chars = "0123456789abcdef".toCharArray()

    const val LINE_SEPARATOR = "\n"

    private fun generate(o: Array<*>, indent: String? = null, indentAmount: Int = 0): String {
        var ret = "["
        val iterator = o.iterator()
        while (iterator.hasNext()) {
            val value = iterator.next()
            if (indent != null) ret += LINE_SEPARATOR + indent.repeat(indentAmount + 1)
            ret += generate(value!!, indent, indentAmount + 1)
            if (iterator.hasNext()) ret += ','
        }
        if (o.isNotEmpty() && indent != null) ret += LINE_SEPARATOR + indent.repeat(indentAmount)
        return "$ret]"
    }

    private fun generate(o: Map<*, *>, indent: String? = null, indentAmount: Int = 0): String {
        var ret = "{"
        val iterator = o.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            val key = next.key.toString()
            val value = next.value
            if (indent != null) ret += LINE_SEPARATOR + indent.repeat(indentAmount + 1)
            ret += (if (isNamespaceAble(key)) key else "\"${escape(key)}\"") + ": " + generate(
                value,
                indent,
                indentAmount + 1
            )
            if (iterator.hasNext()) ret += ','
        }
        if (o.isNotEmpty() && indent != null) ret += LINE_SEPARATOR + indent.repeat(indentAmount)
        return "$ret}"
    }

    private fun generate(o: Set<*>, indent: String? = null, indentAmount: Int = 0): String =
        generate(o.toTypedArray(), indent, indentAmount)

    fun generate(o: Any?, indent: String? = null, indentAmount: Int = 0): String {
        return when (o) {
            null -> return "null"
            is Boolean, Byte, Short, Int, Long, Float, Double -> o.toString()
            is Map<*, *> -> generate(o, indent = indent, indentAmount = indentAmount)
            is Array<*> -> generate(o, indent = indent, indentAmount = indentAmount)
            is Set<*> -> generate(o, indent = indent, indentAmount = indentAmount)
            else -> throw Error("Can't generate ${o::class.qualifiedName}")
        }
    }

    private fun isNamespaceAble(str: String): Boolean {
        if (str.isEmpty()) return false
        val chars = str.toCharArray()
        if (!Characters.isIdentifierStartCharacter(chars[0])) return false
        chars.drop(1)
        for (c in chars) if (!Characters.isIdentifierCharacter(c)) return false
        return true
    }

    private fun escape(str: String): String {
        var escaped = ""
        for (c in str.toCharArray()) escaped += escape(c)
        return escaped
    }

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

    private fun toUnicode(char: Char) = "\\u${toBase16(char.code)}"

    private fun getBase16Character(number: Int) =
        if (number !in 0..15) throw Error("Input $number should be in range 1..15") else base16chars[number]

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