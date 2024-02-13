@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.spec

class GenerationContext(
    val indentType: String = "    ",
    val newline: String = "\n",
    val indentLevel: Int = 0,
) {

    fun indent(): GenerationContext {
        return GenerationContext(indentType, newline, indentLevel + 1)
    }

    fun dedent(): GenerationContext {
        return GenerationContext(indentType, newline, indentLevel - 1)
    }

    fun generateIndent(): String {
        val indent = StringBuilder()
        for (i in 0 until indentLevel) {
            indent.append(indentType)
        }
        return indent.toString()
    }
}
