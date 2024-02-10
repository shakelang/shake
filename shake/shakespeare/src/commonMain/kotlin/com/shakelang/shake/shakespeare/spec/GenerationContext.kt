package com.shakelang.shake.shakespeare.spec

class GenerationContext (
    val indentType: String = "    ",
    val newline: String = "\n",
    val indentLevel: Int = 0
) {

    fun indent(): GenerationContext {
        return GenerationContext(indentType, newline, indentLevel + 1)
    }

}
