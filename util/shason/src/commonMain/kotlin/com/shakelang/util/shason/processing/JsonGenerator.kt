package com.shakelang.util.shason.processing

import com.shakelang.util.parseutils.characters.Characters
import com.shakelang.util.shason.elements.JsonArray
import com.shakelang.util.shason.elements.JsonObject
import com.shakelang.util.shason.elements.JsonPrimitive

/**
 * API for generating json
 * @since 0.1.0
 * @version 0.1.0
 */
object JsonGenerator {

    /**
     * The line-separator to generate when generating beautified json
     * @since 0.1.0
     * @version 0.1.0
     */
    private const val LINE_SEPARATOR = "\n"

    /**
     * Generate an Array
     * @since 0.1.0
     * @version 0.1.0
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
                    },

                ) {
                    generate(
                        it,
                        indent,
                        indentAmount + 1,
                    )
                }
                ) + (if (o.isNotEmpty() && indent != null) LINE_SEPARATOR + indent.repeat(indentAmount) else "") + "]"
    }

    /**
     * Generate an Object
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun generate(o: Map<*, *>, indent: String? = null, indentAmount: Int = 0): String {
        var ret = "{"
        val iterator = o.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            val key = next.key.toString()
            val value = next.value
            if (indent != null) ret += LINE_SEPARATOR + indent.repeat(indentAmount + 1)
            ret += "\"${Characters.escapeStringForString(key)}\"" + ":"
            if (indent != null) ret += " "
            ret += generate(
                value,
                indent,
                indentAmount + 1,
            )
            if (iterator.hasNext()) ret += ','
        }
        if (o.isNotEmpty() && indent != null) ret += LINE_SEPARATOR + indent.repeat(indentAmount)
        return "$ret}"
    }

    /**
     * Generate a String
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun generate(s: String) = "\"${Characters.escapeStringForString(s)}\""

    /**
     * Generate something
     * @since 0.1.0
     * @version 0.1.0
     */
    fun generate(o: Any?, indent: String? = null, indentAmount: Int = 0): String {
        return when (o) {
            null -> return "null"
            is Boolean, is Byte, is Short, is Int, is Long -> o.toString()
            is Float -> generate(o)
            is Double -> generate(o)
            is Map<*, *> -> generate(o, indent = indent, indentAmount = indentAmount)
            is Array<*> -> generate(o, indent = indent, indentAmount = indentAmount)
            is Set<*> -> generate(o.toTypedArray(), indent = indent, indentAmount = indentAmount)
            is List<*> -> generate(o.toTypedArray(), indent = indent, indentAmount = indentAmount)
            is JsonPrimitive -> generate(o.value)
            is JsonObject -> generate(o.value, indent = indent, indentAmount = indentAmount)
            is JsonArray -> generate(o.value, indent = indent, indentAmount = indentAmount)
            else -> generate(o.toString())
        }
    }

    fun generate(d: Double): String {
        if (d.isInfinite() || d.isNaN()) return "null"
        // don't generate 10.0, but 10
        if (d == d.toLong().toDouble()) return d.toLong().toString()
        return d.toString()
    }

    fun generate(f: Float): String {
        if (f.isInfinite() || f.isNaN()) return "null"
        // don't generate 10.0, but 10
        if (f == f.toLong().toFloat()) return f.toLong().toString()
        return f.toString()
    }
}
