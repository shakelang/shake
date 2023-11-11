package com.shakelang.shake.util.shason.processing

import com.shakelang.shake.util.parseutils.characters.Characters

/**
 * API for generating json
 */
object JsonGenerator {

    /**
     * The line-separator to generate when generating beautified json
     */
    const val LINE_SEPARATOR = "\n"

    /**
     * Generate an Array
     */
    private fun generate(o: Array<*>, indent: String? = null, indentAmount: Int = 0): String {
        return "[" +

            // Indention of first line
            (if (o.isNotEmpty() && indent != null) LINE_SEPARATOR + indent.repeat(indentAmount + 1) else "") +

            // Join the children
            (
                o.joinToString(

                    // Indention of all lines (except first) and Comma between elements
                    if (o.isNotEmpty() && indent != null) {
                        "," + LINE_SEPARATOR + indent.repeat(indentAmount + 1)
                    } else {
                        ","
                    }

                ) {
                    generate(
                        it!!,
                        indent,
                        indentAmount + 1
                    )
                }
                ) + (if (o.isNotEmpty() && indent != null) LINE_SEPARATOR + indent.repeat(indentAmount) else "") + "]"
    }

    /**
     * Generate an Object
     */
    private fun generate(o: Map<*, *>, indent: String? = null, indentAmount: Int = 0): String {
        var ret = "{"
        val iterator = o.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            val key = next.key.toString()
            val value = next.value
            if (indent != null) ret += LINE_SEPARATOR + indent.repeat(indentAmount + 1)
            ret += "\"${Characters.escapeString(key)}\"" + ":"
            if (indent != null) ret += " "
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
     */
    private fun generate(s: String) = "\"${Characters.escapeString(s)}\""

    /**
     * Generate something
     */
    fun generate(o: Any?, indent: String? = null, indentAmount: Int = 0): String {
        return when (o) {
            null -> return "null"
            is Boolean, is Byte, is Short, is Int, is Long, is Float, is Double -> o.toString()
            is Map<*, *> -> generate(o, indent = indent, indentAmount = indentAmount)
            is Array<*> -> generate(o, indent = indent, indentAmount = indentAmount)
            is Set<*> -> generate(o.toTypedArray(), indent = indent, indentAmount = indentAmount)
            is List<*> -> generate(o.toTypedArray(), indent = indent, indentAmount = indentAmount)
            else -> generate(o.toString())
        }
    }
}
