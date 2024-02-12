@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.spec.code

import com.shakelang.shake.shakespeare.spec.GenerationContext

class CodeSpec(

    val statements: List<StatementSpec>,

) {

    fun generate(context: GenerationContext): String {
        val builder = StringBuilder("{")

        if (statements.isNotEmpty()) builder.append("\n")

        val indented = context.indent()

        for (statement in statements) {
            builder.append((1..indented.indentLevel).joinToString("") { indented.indentType })
            builder.append(statement.generate(indented))
        }
        return builder.append("}").toString()
    }

    open class CodeSpecBuilder(
        val statements: MutableList<StatementSpec> = mutableListOf(),
    ) {
        fun addStatement(vararg statement: StatementSpec) = apply { statements.addAll(statement) }

        fun build() = CodeSpec(statements)
    }

    companion object {
        fun builder() = CodeSpecBuilder()
        fun empty() = CodeSpec(emptyList())
    }
}
