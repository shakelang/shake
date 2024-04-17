@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.spec.code

import com.shakelang.shake.shakespeare.spec.AbstractSpec
import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.TypeSpec

/**
 * A [StatementSpec] is a specification for a statement in the code
 */
interface StatementSpec : AbstractSpec {

    /**
     * Generate the statement
     * @param ctx The [GenerationContext] to use
     * @return The generated statement
     */
    override fun generate(ctx: GenerationContext): String

    companion object {

        /**
         * Create a [StatementSpec] from a string
         * @param value The string to create the [StatementSpec] from
         * @return The [StatementSpec] created from the string
         */
        fun of(value: String): StatementSpec {
            return object : StatementSpec {
                override fun generate(ctx: GenerationContext): String {
                    return value
                }

                override fun equals(other: Any?): Boolean {
                    if (this === other) return true
                    if (other !is StatementSpec) return false
                    return value == other.generate(GenerationContext())
                }

                override fun hashCode(): Int {
                    return value.hashCode()
                }
            }
        }
    }
}

/**
 * A [VariableDeclarationSpec] is a specification for a variable declaration in the code
 * @param name The name of the variable
 * @param type The type of the variable
 * @param value The value of the variable
 */
open class VariableDeclarationSpec(

    /**
     * The name of the variable
     */
    val name: String,

    /**
     * The type of the variable
     */
    open val type: TypeSpec?,

    /**
     * The value of the variable
     */
    open val value: ValueSpec?,

    /**
     * If the variable is a val or a var
     */
    val isVal: Boolean = true,
) : StatementSpec {

    /**
     * Generate the variable declaration
     * @param ctx The [GenerationContext] to use
     * @return The generated variable declaration
     */
    override fun generate(ctx: GenerationContext): String {
        val builder = StringBuilder()
        if (isVal) builder.append("val ") else builder.append("var ")
        builder.append(name)
        if (type != null) builder.append(": ").append(type!!.generate(ctx))
        if (value != null) builder.append(" = ").append(value!!.generate(ctx))
        return builder.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VariableDeclarationSpec) return false
        if (name != other.name) return false
        if (type != other.type) return false
        if (value != other.value) return false
        if (isVal != other.isVal) return false
        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (value?.hashCode() ?: 0)
        result = 31 * result + isVal.hashCode()
        return result
    }

    /**
     * A builder for [VariableDeclarationSpec]
     */
    open class VariableDeclarationSpecBuilder
    internal constructor(

        /**
         * The name of the variable
         */
        var name: String? = null,

        /**
         * The type of the variable
         */
        var type: TypeSpec? = null,

        /**
         * The value of the variable
         */
        var value: ValueSpec? = null,

        /**
         * If the variable is a val or a var
         */
        var isVal: Boolean = true,
    ) {

        /**
         * Set the name of the variable
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: String): VariableDeclarationSpecBuilder {
            this.name = name
            return this
        }

        /**
         * Set the type of the variable
         * @param type The type of the variable
         * @return The builder
         */
        fun type(type: TypeSpec): VariableDeclarationSpecBuilder {
            this.type = type
            return this
        }

        /**
         * Set the type of the variable
         * @param type The type of the variable
         * @return The builder
         */
        fun type(type: String): VariableDeclarationSpecBuilder {
            this.type = TypeSpec.of(type)
            return this
        }

        /**
         * Set the value of the variable
         * @param value The value of the variable
         * @return The builder
         */
        fun value(value: ValueSpec): VariableDeclarationSpecBuilder {
            this.value = value
            return this
        }

        /**
         * Set the value of the variable
         * @param value The value of the variable
         * @return The builder
         */
        fun value(value: String): VariableDeclarationSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        /**
         * Set if the variable is a val or a var
         * @param isVal If the variable is a val or a var
         * @return The builder
         */
        fun isVal(isVal: Boolean = true): VariableDeclarationSpecBuilder {
            this.isVal = isVal
            return this
        }

        /**
         * Build the [VariableDeclarationSpec]
         * @return The built [VariableDeclarationSpec]
         */
        fun build(): VariableDeclarationSpec {
            return VariableDeclarationSpec(
                name ?: throw IllegalStateException("Name not set"),
                type ?: throw IllegalStateException("Type not set"),
                value ?: throw IllegalStateException("Value not set"),
                isVal,
            )
        }
    }

    companion object {

        /**
         * Create a new [VariableDeclarationSpecBuilder]
         * @return The created [VariableDeclarationSpecBuilder]
         */
        fun builder() = VariableDeclarationSpecBuilder()
    }
}

/**
 * A [WhileSpec] is a specification for a while loop in the code
 * @param condition The condition of the while loop
 * @param body The body of the while loop
 */
open class WhileSpec(

    /**
     * The condition of the while loop
     */
    open val condition: ValueSpec,

    /**
     * The body of the while loop
     */
    open val body: CodeSpec,
) : StatementSpec {

    /**
     * Generate the while loop
     * @param ctx The [GenerationContext] to use
     * @return The generated while loop
     */
    override fun generate(ctx: GenerationContext): String {
        return "while (${condition.generate(ctx)}) ${body.generate(ctx.indent())}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is WhileSpec) return false
        if (condition != other.condition) return false
        if (body != other.body) return false
        return true
    }

    override fun hashCode(): Int {
        var result = condition.hashCode()
        result = 31 * result + body.hashCode()
        return result
    }

    /**
     * A builder for [WhileSpec]
     */
    open class WhileSpecBuilder
    internal constructor(

        /**
         * The condition of the while loop
         */
        var condition: ValueSpec? = null,

        /**
         * The body of the while loop
         */
        var body: CodeSpec? = null,
    ) {

        /**
         * Set the condition of the while loop
         * @param condition The condition of the while loop
         * @return The builder
         */
        fun condition(condition: ValueSpec): WhileSpecBuilder {
            this.condition = condition
            return this
        }

        /**
         * Set the condition of the while loop
         * @param condition The condition of the while loop
         * @return The builder
         */
        fun condition(condition: String): WhileSpecBuilder {
            this.condition = ValueSpec.of(condition)
            return this
        }

        /**
         * Set the body of the while loop
         * @param body The body of the while loop
         * @return The builder
         */
        fun body(body: CodeSpec): WhileSpecBuilder {
            this.body = body
            return this
        }

        /**
         * Build the [WhileSpec]
         * @return The built [WhileSpec]
         */
        fun build(): WhileSpec {
            return WhileSpec(
                condition ?: throw IllegalStateException("Condition not set"),
                body ?: throw IllegalStateException("Body not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new [WhileSpecBuilder]
         * @return The created [WhileSpecBuilder]
         */
        fun builder() = WhileSpecBuilder()
    }
}

/**
 * A [DoWhileSpec] is a specification for a do while loop in the code
 * @param body The body of the do while loop
 * @param condition The condition of the do while loop
 */
open class DoWhileSpec(

    /**
     * The body of the do while loop
     */
    open val body: CodeSpec,

    /**
     * The condition of the do while loop
     */
    open val condition: ValueSpec,
) : StatementSpec {

    /**
     * Generate the do while loop
     * @param ctx The [GenerationContext] to use
     * @return The generated do while loop
     */
    override fun generate(ctx: GenerationContext): String {
        return "do ${body.generate(ctx.indent())} while (${condition.generate(ctx)})"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DoWhileSpec) return false
        if (body != other.body) return false
        if (condition != other.condition) return false
        return true
    }

    override fun hashCode(): Int {
        var result = body.hashCode()
        result = 31 * result + condition.hashCode()
        return result
    }

    /**
     * A builder for [DoWhileSpec]
     */
    open class DoWhileSpecBuilder
    internal constructor(

        /**
         * The body of the do while loop
         */
        var body: CodeSpec? = null,

        /**
         * The condition of the do while loop
         */
        var condition: ValueSpec? = null,
    ) {

        /**
         * Set the body of the do while loop
         * @param body The body of the do while loop
         * @return The builder
         */
        fun body(body: CodeSpec): DoWhileSpecBuilder {
            this.body = body
            return this
        }

        /**
         * Set the condition of the do while loop
         * @param condition The condition of the do while loop
         * @return The builder
         */
        fun condition(condition: ValueSpec): DoWhileSpecBuilder {
            this.condition = condition
            return this
        }

        /**
         * Set the condition of the do while loop
         * @param condition The condition of the do while loop
         * @return The builder
         */
        fun condition(condition: String): DoWhileSpecBuilder {
            this.condition = ValueSpec.of(condition)
            return this
        }

        /**
         * Build the [DoWhileSpec]
         * @return The built [DoWhileSpec]
         */
        fun build(): DoWhileSpec {
            return DoWhileSpec(
                body ?: throw IllegalStateException("Body not set"),
                condition ?: throw IllegalStateException("Condition not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new [DoWhileSpecBuilder]
         * @return The created [DoWhileSpecBuilder]
         */
        fun builder() = DoWhileSpecBuilder()
    }
}

/**
 * A [ForSpec] is a specification for a for loop in the code
 * @param init The init statement of the for loop
 * @param condition The condition of the for loop
 * @param update The update statement of the for loop
 * @param body The body of the for loop
 */
open class ForSpec(

    /**
     * The init statement of the for loop
     */
    open val init: StatementSpec,

    /**
     * The condition of the for loop
     */
    open val condition: ValueSpec,

    /**
     * The update statement of the for loop
     */
    open val update: StatementSpec,

    /**
     * The body of the for loop
     */
    open val body: CodeSpec,
) : StatementSpec {

    /**
     * Generate the for loop
     * @param ctx The [GenerationContext] to use
     * @return The generated for loop
     */
    override fun generate(ctx: GenerationContext): String {
        return "for (${init.generate(ctx)}; ${condition.generate(ctx)}; ${update.generate(ctx)}) ${body.generate(ctx.indent())}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ForSpec) return false
        if (init != other.init) return false
        if (condition != other.condition) return false
        if (update != other.update) return false
        if (body != other.body) return false
        return true
    }

    override fun hashCode(): Int {
        var result = init.hashCode()
        result = 31 * result + condition.hashCode()
        result = 31 * result + update.hashCode()
        result = 31 * result + body.hashCode()
        return result
    }

    /**
     * A builder for [ForSpec]
     */
    open class ForSpecBuilder
    internal constructor(

        /**
         * The init statement of the for loop
         */
        var init: StatementSpec? = null,

        /**
         * The condition of the for loop
         */
        var condition: ValueSpec? = null,

        /**
         * The update statement of the for loop
         */
        var update: StatementSpec? = null,

        /**
         * The body of the for loop
         */
        var body: CodeSpec? = null,
    ) {

        /**
         * Set the init statement of the for loop
         * @param init The init statement of the for loop
         * @return The builder
         */
        fun init(init: StatementSpec): ForSpecBuilder {
            this.init = init
            return this
        }

        /**
         * Set the init statement of the for loop
         * @param init The init statement of the for loop
         * @return The builder
         */
        fun init(init: String): ForSpecBuilder {
            this.init = StatementSpec.of(init)
            return this
        }

        /**
         * Set the condition of the for loop
         * @param condition The condition of the for loop
         * @return The builder
         */
        fun condition(condition: ValueSpec): ForSpecBuilder {
            this.condition = condition
            return this
        }

        /**
         * Set the condition of the for loop
         * @param condition The condition of the for loop
         * @return The builder
         */
        fun condition(condition: String): ForSpecBuilder {
            this.condition = ValueSpec.of(condition)
            return this
        }

        /**
         * Set the update statement of the for loop
         * @param update The update statement of the for loop
         * @return The builder
         */
        fun update(update: StatementSpec): ForSpecBuilder {
            this.update = update
            return this
        }

        /**
         * Set the update statement of the for loop
         * @param update The update statement of the for loop
         * @return The builder
         */
        fun update(update: String): ForSpecBuilder {
            this.update = StatementSpec.of(update)
            return this
        }

        /**
         * Set the body of the for loop
         * @param body The body of the for loop
         * @return The builder
         */
        fun body(body: CodeSpec): ForSpecBuilder {
            this.body = body
            return this
        }

        /**
         * Build the [ForSpec]
         * @return The built [ForSpec]
         */
        fun build(): ForSpec {
            return ForSpec(
                init ?: throw IllegalStateException("Init not set"),
                condition ?: throw IllegalStateException("Condition not set"),
                update ?: throw IllegalStateException("Update not set"),
                body ?: throw IllegalStateException("Body not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new [ForSpecBuilder]
         * @return The created [ForSpecBuilder]
         */
        fun builder() = ForSpecBuilder()
    }
}

/**
 * A [IfSpec] is a specification for an if statement in the code
 * @param condition The condition of the if statement
 * @param body The body of the if statement
 * @param elseBody The else body of the if statement
 */
open class IfSpec(

    /**
     * The condition of the if statement
     */
    open val condition: ValueSpec,

    /**
     * The body of the if statement
     */
    open val body: CodeSpec,

    /**
     * The else body of the if statement
     */
    open val elseBody: CodeSpec?,
) : StatementSpec {

    /**
     * Generate the if statement
     * @param ctx The [GenerationContext] to use
     * @return The generated if statement
     */
    override fun generate(ctx: GenerationContext): String {
        val elsePart = if (elseBody != null) " else ${elseBody!!.generate(ctx.indent())}" else ""
        return "if (${condition.generate(ctx)}) ${body.generate(ctx.indent())}$elsePart"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is IfSpec) return false
        if (condition != other.condition) return false
        if (body != other.body) return false
        if (elseBody != other.elseBody) return false
        return true
    }

    override fun hashCode(): Int {
        var result = condition.hashCode()
        result = 31 * result + body.hashCode()
        result = 31 * result + (elseBody?.hashCode() ?: 0)
        return result
    }

    /**
     * A builder for [IfSpec]
     */
    open class IfSpecBuilder
    internal constructor(

        /**
         * The condition of the if statement
         */
        var condition: ValueSpec? = null,

        /**
         * The body of the if statement
         */
        var body: CodeSpec? = null,

        /**
         * The else body of the if statement
         */
        var elseBody: CodeSpec? = null,
    ) {

        /**
         * Set the condition of the if statement
         * @param condition The condition of the if statement
         * @return The builder
         */
        fun condition(condition: ValueSpec): IfSpecBuilder {
            this.condition = condition
            return this
        }

        /**
         * Set the condition of the if statement
         * @param condition The condition of the if statement
         * @return The builder
         */
        fun condition(condition: String): IfSpecBuilder {
            this.condition = ValueSpec.of(condition)
            return this
        }

        /**
         * Set the body of the if statement
         * @param body The body of the if statement
         * @return The builder
         */
        fun body(body: CodeSpec): IfSpecBuilder {
            this.body = body
            return this
        }

        /**
         * Set the else body of the if statement
         * @param elseBody The else body of the if statement
         * @return The builder
         */
        fun elseBody(elseBody: CodeSpec): IfSpecBuilder {
            this.elseBody = elseBody
            return this
        }

        /**
         * Build the [IfSpec]
         * @return The built [IfSpec]
         */
        fun build(): IfSpec {
            return IfSpec(
                condition ?: throw IllegalStateException("Condition not set"),
                body ?: throw IllegalStateException("Body not set"),
                elseBody,
            )
        }
    }

    companion object {

        /**
         * Create a new [IfSpecBuilder]
         * @return The created [IfSpecBuilder]
         */
        fun builder() = IfSpecBuilder()
    }
}

/**
 * A [ReturnSpec] is a specification for a return statement in the code
 * @param value The value to return
 */
open class ReturnSpec(

    /**
     * The value to return
     */
    open val value: ValueSpec?,
) : StatementSpec {

    /**
     * Generate the return statement
     * @param ctx The [GenerationContext] to use
     * @return The generated return statement
     */
    override fun generate(ctx: GenerationContext): String {
        return "return${if (value != null) " ${value!!.generate(ctx)}" else ""}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ReturnSpec) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return value?.hashCode() ?: this::class.hashCode()
    }

    /**
     * A builder for [ReturnSpec]
     */
    open class ReturnSpecBuilder
    internal constructor(

        /**
         * The value to return
         */
        var value: ValueSpec? = null,
    ) {

        /**
         * Set the value to return
         * @param value The value to return
         * @return The builder
         */
        fun value(value: ValueSpec?): ReturnSpecBuilder {
            this.value = value
            return this
        }

        /**
         * Set the value to return
         * @param value The value to return
         * @return The builder
         */
        fun value(value: String): ReturnSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        /**
         * Build the [ReturnSpec]
         * @return The built [ReturnSpec]
         */
        fun build(): ReturnSpec {
            return ReturnSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new [ReturnSpecBuilder]
         * @return The created [ReturnSpecBuilder]
         */
        fun builder() = ReturnSpecBuilder()
    }
}
