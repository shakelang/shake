@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.spec.code

import com.shakelang.shake.shakespeare.AbstractSpec
import com.shakelang.shake.shakespeare.spec.GenerationContext

/**
 * Represents a specification of code in the Shake programming language.
 *
 * @property statements A list of statements included in this code specification.
 * @since 0.1.0
 */
open class CodeSpec(

    open val statements: List<StatementSpec>,

) : AbstractSpec {

    /**
     * Generates a string representation of the code based on the given context.
     *
     * @param ctx The generation context to be used for code generation.
     * @return A string representation of the generated code.
     * @since 0.1.0
     */
    override fun generate(ctx: GenerationContext): String {
        val builder = StringBuilder("{")

        if (statements.isNotEmpty()) builder.append("\n")

        val indented = ctx.indent()

        for (statement in statements) {
            builder.append((1..indented.indentLevel).joinToString("") { indented.indentType })
            builder.append(statement.generate(indented))
        }
        return builder.append("}").toString()
    }

    /**
     * Builder class for [CodeSpec].
     *
     * @property statements Mutable list of [StatementSpec] to be added to the [CodeSpec].
     * @since 0.1.0
     */
    open class CodeSpecBuilder(
        val statements: MutableList<StatementSpec> = mutableListOf(),
    ) {
        /**
         * Adds one or more statements to the code specification.
         *
         * @param statement Vararg of [StatementSpec] to be added.
         * @return The current instance of [CodeSpecBuilder].
         * @since 0.1.0
         */
        fun addStatement(vararg statement: StatementSpec) = apply { statements.addAll(statement) }

        /**
         * Builds a new [CodeSpec] instance with the current configuration of statements.
         *
         * @return A new instance of [CodeSpec].
         * @since 0.1.0
         */
        fun build() = CodeSpec(statements)
    }

    companion object {
        /**
         * Creates a new [CodeSpecBuilder] instance.
         *
         * @return A new instance of [CodeSpecBuilder].
         * @since 0.1.0
         */
        fun builder() = CodeSpecBuilder()

        /**
         * Creates a new [CodeSpec] instance with no statements.
         *
         * @return A new instance of [CodeSpec] with an empty list of statements.
         * @since 0.1.0
         */
        fun empty() = CodeSpec(emptyList())
    }
}
