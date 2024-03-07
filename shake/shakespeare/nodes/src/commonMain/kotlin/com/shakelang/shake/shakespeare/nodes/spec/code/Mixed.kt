@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.nodes.spec.code

import com.shakelang.shake.shakespeare.nodes.spec.GenerationContext
import com.shakelang.shake.shakespeare.nodes.spec.Identifier

/**
 * A [ValuedAssignmentSpec] is a StatementSpec and a ValueSpec at the same time
 * @since 0.1.0
 */
interface ValuedAssignmentSpec : StatementSpec, ValueSpec

/**
 * A variable assignment assigns a value to a variable
 *
 * @param name The name of the variable
 * @param value The value to assign to the variable
 *
 * @since 0.1.0
 * @property name The name of the variable
 * @property value The value to assign to the variable
 * @constructor Creates a [VariableAssignmentSpec]
 */
open class VariableAssignmentSpec(
    val name: Identifier,
    val value: ValueSpec,
) : ValuedAssignmentSpec {

    /**
     * Generates the code for the [VariableAssignmentSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "$name = $value"
    }

    /**
     * The builder for the [VariableAssignmentSpec]
     *
     * @since 0.1.0
     * @constructor Creates a [VariableAssignmentSpecBuilder]
     *
     * @property name The name of the variable
     * @property value The value to assign to the variable
     */
    open class VariableAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: ValueSpec? = null,
    ) {

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: Identifier): VariableAssignmentSpecBuilder {
            this.name = name
            return this
        }

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: String): VariableAssignmentSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        /**
         * Sets the value to assign to the variable
         *
         * @param value The value to assign to the variable
         * @return The builder
         */
        fun value(value: ValueSpec): VariableAssignmentSpecBuilder {
            this.value = value
            return this
        }

        /**
         * Sets the value to assign to the variable
         *
         * @param value The value to assign to the variable
         * @return The builder
         */
        fun value(value: String): VariableAssignmentSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        /**
         * Builds the [VariableAssignmentSpec]
         *
         * @return The [VariableAssignmentSpec]
         */
        fun build(): VariableAssignmentSpec {
            return VariableAssignmentSpec(
                name ?: throw IllegalStateException("Name not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {

        /**
         * Creates a new [VariableAssignmentSpecBuilder]
         *
         * @return The [VariableAssignmentSpecBuilder]
         */
        fun builder() = VariableAssignmentSpecBuilder()
    }
}

/**
 * A variable addition assignment adds a value to a variable
 *
 * @param name The name of the variable
 * @param value The value to add to the variable
 *
 * @since 0.1.0
 * @property name The name of the variable
 * @property value The value to add to the variable
 * @constructor Creates a [VariableAdditionAssignmentSpec]
 */
open class VariableAdditionAssignmentSpec(
    val name: Identifier,
    val value: ValueSpec,
) : ValuedAssignmentSpec {

    /**
     * Generates the code for the [VariableAdditionAssignmentSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "$name += $value"
    }

    /**
     * The builder for the [VariableAdditionAssignmentSpec]
     *
     * @since 0.1.0
     * @constructor Creates a [VariableAdditionAssignmentSpecBuilder]
     *
     * @property name The name of the variable
     * @property value The value to add to the variable
     */
    open class VariableAdditionAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: ValueSpec? = null,
    ) {

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: Identifier): VariableAdditionAssignmentSpecBuilder {
            this.name = name
            return this
        }

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: String): VariableAdditionAssignmentSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        /**
         * Sets the value to add to the variable
         *
         * @param value The value to add to the variable
         * @return The builder
         */
        fun value(value: ValueSpec): VariableAdditionAssignmentSpecBuilder {
            this.value = value
            return this
        }

        /**
         * Sets the value to add to the variable
         *
         * @param value The value to add to the variable
         * @return The builder
         */
        fun value(value: String): VariableAdditionAssignmentSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        /**
         * Builds the [VariableAdditionAssignmentSpec]
         *
         * @return The [VariableAdditionAssignmentSpec]
         */
        fun build(): VariableAdditionAssignmentSpec {
            return VariableAdditionAssignmentSpec(
                name ?: throw IllegalStateException("Name not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {

        /**
         * Creates a new [VariableAdditionAssignmentSpecBuilder]
         *
         * @return The [VariableAdditionAssignmentSpecBuilder]
         */
        fun builder() = VariableAdditionAssignmentSpecBuilder()
    }
}

/**
 * A variable subtraction assignment subtracts a value from a variable
 *
 * @param name The name of the variable
 * @param value The value to subtract from the variable
 *
 * @since 0.1.0
 * @property name The name of the variable
 * @property value The value to subtract from the variable
 * @constructor Creates a [VariableSubtractionAssignmentSpec]
 */
open class VariableSubtractionAssignmentSpec(
    val name: Identifier,
    val value: ValueSpec,
) : ValuedAssignmentSpec {

    /**
     * Generates the code for the [VariableSubtractionAssignmentSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "$name -= $value"
    }

    /**
     * The builder for the [VariableSubtractionAssignmentSpec]
     *
     * @since 0.1.0
     * @constructor Creates a [VariableSubtractionAssignmentSpecBuilder]
     *
     * @property name The name of the variable
     * @property value The value to subtract from the variable
     */
    open class VariableSubtractionAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: ValueSpec? = null,
    ) {

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: Identifier): VariableSubtractionAssignmentSpecBuilder {
            this.name = name
            return this
        }

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: String): VariableSubtractionAssignmentSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        /**
         * Sets the value to subtract from the variable
         *
         * @param value The value to subtract from the variable
         * @return The builder
         */
        fun value(value: ValueSpec): VariableSubtractionAssignmentSpecBuilder {
            this.value = value
            return this
        }

        /**
         * Sets the value to subtract from the variable
         *
         * @param value The value to subtract from the variable
         * @return The builder
         */
        fun value(value: String): VariableSubtractionAssignmentSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        /**
         * Builds the [VariableSubtractionAssignmentSpec]
         *
         * @return The [VariableSubtractionAssignmentSpec]
         */
        fun build(): VariableSubtractionAssignmentSpec {
            return VariableSubtractionAssignmentSpec(
                name ?: throw IllegalStateException("Name not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {

        /**
         * Creates a new [VariableSubtractionAssignmentSpecBuilder]
         *
         * @return The [VariableSubtractionAssignmentSpecBuilder]
         */
        fun builder() = VariableSubtractionAssignmentSpecBuilder()
    }
}

/**
 * A variable multiplication assignment multiplies a variable with a value
 *
 * @param name The name of the variable
 * @param value The value to multiply the variable with
 *
 * @since 0.1.0
 * @property name The name of the variable
 * @property value The value to multiply the variable with
 * @constructor Creates a [VariableMultiplicationAssignmentSpec]
 */
open class VariableMultiplicationAssignmentSpec(
    val name: Identifier,
    val value: ValueSpec,
) : ValuedAssignmentSpec {

    /**
     * Generates the code for the [VariableMultiplicationAssignmentSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "$name *= $value"
    }

    /**
     * The builder for the [VariableMultiplicationAssignmentSpec]
     *
     * @since 0.1.0
     * @constructor Creates a [VariableMultiplicationAssignmentSpecBuilder]
     *
     * @property name The name of the variable
     * @property value The value to multiply the variable with
     */
    open class VariableMultiplicationAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: ValueSpec? = null,
    ) {

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: Identifier): VariableMultiplicationAssignmentSpecBuilder {
            this.name = name
            return this
        }

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: String): VariableMultiplicationAssignmentSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        /**
         * Sets the value to multiply the variable with
         *
         * @param value The value to multiply the variable with
         * @return The builder
         */
        fun value(value: ValueSpec): VariableMultiplicationAssignmentSpecBuilder {
            this.value = value
            return this
        }

        /**
         * Sets the value to multiply the variable with
         *
         * @param value The value to multiply the variable with
         * @return The builder
         */
        fun value(value: String): VariableMultiplicationAssignmentSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        /**
         * Builds the [VariableMultiplicationAssignmentSpec]
         *
         * @return The [VariableMultiplicationAssignmentSpec]
         */
        fun build(): VariableMultiplicationAssignmentSpec {
            return VariableMultiplicationAssignmentSpec(
                name ?: throw IllegalStateException("Name not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {

        /**
         * Creates a new [VariableMultiplicationAssignmentSpecBuilder]
         *
         * @return The [VariableMultiplicationAssignmentSpecBuilder]
         */
        fun builder() = VariableMultiplicationAssignmentSpecBuilder()
    }
}

/**
 * A variable division assignment divides a variable by a value
 *
 * @param name The name of the variable
 * @param value The value to divide the variable by
 *
 * @since 0.1.0
 * @property name The name of the variable
 * @property value The value to divide the variable by
 * @constructor Creates a [VariableDivisionAssignmentSpec]
 */
open class VariableDivisionAssignmentSpec(
    val name: Identifier,
    val value: ValueSpec,
) : ValuedAssignmentSpec {

    /**
     * Generates the code for the [VariableDivisionAssignmentSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "$name /= $value"
    }

    /**
     * The builder for the [VariableDivisionAssignmentSpec]
     *
     * @since 0.1.0
     * @constructor Creates a [VariableDivisionAssignmentSpecBuilder]
     *
     * @property name The name of the variable
     * @property value The value to divide the variable by
     */
    open class VariableDivisionAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: ValueSpec? = null,
    ) {

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: Identifier): VariableDivisionAssignmentSpecBuilder {
            this.name = name
            return this
        }

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: String): VariableDivisionAssignmentSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        /**
         * Sets the value to divide the variable by
         *
         * @param value The value to divide the variable by
         * @return The builder
         */
        fun value(value: ValueSpec): VariableDivisionAssignmentSpecBuilder {
            this.value = value
            return this
        }

        /**
         * Sets the value to divide the variable by
         *
         * @param value The value to divide the variable by
         * @return The builder
         */
        fun value(value: String): VariableDivisionAssignmentSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        /**
         * Builds the [VariableDivisionAssignmentSpec]
         *
         * @return The [VariableDivisionAssignmentSpec]
         */
        fun build(): VariableDivisionAssignmentSpec {
            return VariableDivisionAssignmentSpec(
                name ?: throw IllegalStateException("Name not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {

        /**
         * Creates a new [VariableDivisionAssignmentSpecBuilder]
         *
         * @return The [VariableDivisionAssignmentSpecBuilder]
         */
        fun builder() = VariableDivisionAssignmentSpecBuilder()
    }
}

/**
 * A variable modulo assignment calculates the modulo of a variable and a value
 *
 * @param name The name of the variable
 * @param value The value to calculate the modulo with
 *
 * @since 0.1.0
 * @property name The name of the variable
 * @property value The value to calculate the modulo with
 * @constructor Creates a [VariableModuloAssignmentSpec]
 */
open class VariableModuloAssignmentSpec(
    val name: Identifier,
    val value: ValueSpec,
) : ValuedAssignmentSpec {

    /**
     * Generates the code for the [VariableModuloAssignmentSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "$name %= $value"
    }

    /**
     * The builder for the [VariableModuloAssignmentSpec]
     *
     * @since 0.1.0
     * @constructor Creates a [VariableModuloAssignmentSpecBuilder]
     *
     * @property name The name of the variable
     * @property value The value to calculate the modulo with
     */
    open class VariableModuloAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: ValueSpec? = null,
    ) {

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: Identifier): VariableModuloAssignmentSpecBuilder {
            this.name = name
            return this
        }

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: String): VariableModuloAssignmentSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        /**
         * Sets the value to calculate the modulo with
         *
         * @param value The value to calculate the modulo with
         * @return The builder
         */
        fun value(value: ValueSpec): VariableModuloAssignmentSpecBuilder {
            this.value = value
            return this
        }

        /**
         * Sets the value to calculate the modulo with
         *
         * @param value The value to calculate the modulo with
         * @return The builder
         */
        fun value(value: String): VariableModuloAssignmentSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        /**
         * Builds the [VariableModuloAssignmentSpec]
         *
         * @return The [VariableModuloAssignmentSpec]
         */
        fun build(): VariableModuloAssignmentSpec {
            return VariableModuloAssignmentSpec(
                name ?: throw IllegalStateException("Name not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {

        /**
         * Creates a new [VariableModuloAssignmentSpecBuilder]
         *
         * @return The [VariableModuloAssignmentSpecBuilder]
         */
        fun builder() = VariableModuloAssignmentSpecBuilder()
    }
}

/**
 * A variable power assignment calculates the power of a variable and a value
 *
 * @param name The name of the variable
 * @param value The value to calculate the power with
 *
 * @since 0.1.0
 * @property name The name of the variable
 * @property value The value to calculate the power with
 * @constructor Creates a [VariablePowerAssignmentSpec]
 */
open class VariablePowerAssignmentSpec(
    val name: Identifier,
    val value: ValueSpec,
) : ValuedAssignmentSpec {

    /**
     * Generates the code for the [VariablePowerAssignmentSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "$name **= $value"
    }

    /**
     * The builder for the [VariablePowerAssignmentSpec]
     *
     * @since 0.1.0
     * @constructor Creates a [VariablePowerAssignmentSpecBuilder]
     *
     * @property name The name of the variable
     * @property value The value to calculate the power with
     */
    class VariablePowerAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: ValueSpec? = null,
    ) {

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: Identifier): VariablePowerAssignmentSpecBuilder {
            this.name = name
            return this
        }

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: String): VariablePowerAssignmentSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        /**
         * Sets the value to calculate the power with
         *
         * @param value The value to calculate the power with
         * @return The builder
         */
        fun value(value: ValueSpec): VariablePowerAssignmentSpecBuilder {
            this.value = value
            return this
        }

        /**
         * Sets the value to calculate the power with
         *
         * @param value The value to calculate the power with
         * @return The builder
         */
        fun value(value: String): VariablePowerAssignmentSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        /**
         * Builds the [VariablePowerAssignmentSpec]
         *
         * @return The [VariablePowerAssignmentSpec]
         */
        fun build(): VariablePowerAssignmentSpec {
            return VariablePowerAssignmentSpec(
                name ?: throw IllegalStateException("Name not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {

        /**
         * Creates a new [VariablePowerAssignmentSpecBuilder]
         *
         * @return The [VariablePowerAssignmentSpecBuilder]
         */
        fun builder() = VariablePowerAssignmentSpecBuilder()
    }
}

/**
 * A variable bitwise left shift assignment shifts the bits of a variable to the left
 *
 * @param name The name of the variable
 * @param value The value to shift the bits to the left
 *
 * @since 0.1.0
 * @property name The name of the variable
 * @property value The value to shift the bits to the left
 * @constructor Creates a [VariableBitwiseAndAssignmentSpec]
 */
open class VariableBitwiseAndAssignmentSpec(
    val name: Identifier,
    val value: ValueSpec,
) : ValuedAssignmentSpec {

    /**
     * Generates the code for the [VariableBitwiseAndAssignmentSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "$name &= $value"
    }

    /**
     * The builder for the [VariableBitwiseAndAssignmentSpec]
     *
     * @since 0.1.0
     * @constructor Creates a [VariableBitwiseAndAssignmentSpecBuilder]
     *
     * @property name The name of the variable
     * @property value The value to shift the bits to the left
     */
    class VariableBitwiseAndAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: ValueSpec? = null,
    ) {

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: Identifier): VariableBitwiseAndAssignmentSpecBuilder {
            this.name = name
            return this
        }

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: String): VariableBitwiseAndAssignmentSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        /**
         * Sets the value to shift the bits to the left
         *
         * @param value The value to shift the bits to the left
         * @return The builder
         */
        fun value(value: ValueSpec): VariableBitwiseAndAssignmentSpecBuilder {
            this.value = value
            return this
        }

        /**
         * Sets the value to shift the bits to the left
         *
         * @param value The value to shift the bits to the left
         * @return The builder
         */
        fun value(value: String): VariableBitwiseAndAssignmentSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        /**
         * Builds the [VariableBitwiseAndAssignmentSpec]
         *
         * @return The [VariableBitwiseAndAssignmentSpec]
         */
        fun build(): VariableBitwiseAndAssignmentSpec {
            return VariableBitwiseAndAssignmentSpec(
                name ?: throw IllegalStateException("Name not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {

        /**
         * Creates a new [VariableBitwiseAndAssignmentSpecBuilder]
         *
         * @return The [VariableBitwiseAndAssignmentSpecBuilder]
         */
        fun builder() = VariableBitwiseAndAssignmentSpecBuilder()
    }
}

/**
 * A variable bitwise left shift assignment shifts the bits of a variable to the left
 *
 * @param name The name of the variable
 * @param value The value to shift the bits to the left
 *
 * @since 0.1.0
 * @property name The name of the variable
 * @property value The value to shift the bits to the left
 * @constructor Creates a [VariableBitwiseOrAssignmentSpec]
 */
open class VariableBitwiseOrAssignmentSpec(
    val name: Identifier,
    val value: ValueSpec,
) : ValuedAssignmentSpec {

    /**
     * Generates the code for the [VariableBitwiseOrAssignmentSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "$name |= $value"
    }

    /**
     * The builder for the [VariableBitwiseOrAssignmentSpec]
     *
     * @since 0.1.0
     * @constructor Creates a [VariableBitwiseOrAssignmentSpecBuilder]
     *
     * @property name The name of the variable
     * @property value The value to shift the bits to the left
     */
    class VariableBitwiseOrAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: ValueSpec? = null,
    ) {

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: Identifier): VariableBitwiseOrAssignmentSpecBuilder {
            this.name = name
            return this
        }

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: String): VariableBitwiseOrAssignmentSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        /**
         * Sets the value to shift the bits to the left
         *
         * @param value The value to shift the bits to the left
         * @return The builder
         */
        fun value(value: ValueSpec): VariableBitwiseOrAssignmentSpecBuilder {
            this.value = value
            return this
        }

        /**
         * Sets the value to shift the bits to the left
         *
         * @param value The value to shift the bits to the left
         * @return The builder
         */
        fun value(value: String): VariableBitwiseOrAssignmentSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        /**
         * Builds the [VariableBitwiseOrAssignmentSpec]
         *
         * @return The [VariableBitwiseOrAssignmentSpec]
         */
        fun build(): VariableBitwiseOrAssignmentSpec {
            return VariableBitwiseOrAssignmentSpec(
                name ?: throw IllegalStateException("Name not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {

        /**
         * Creates a new [VariableBitwiseOrAssignmentSpecBuilder]
         *
         * @return The [VariableBitwiseOrAssignmentSpecBuilder]
         */
        fun builder() = VariableBitwiseOrAssignmentSpecBuilder()
    }
}

/**
 * A variable bitwise left shift assignment shifts the bits of a variable to the left
 *
 * @param name The name of the variable
 * @param value The value to shift the bits to the left
 *
 * @since 0.1.0
 * @property name The name of the variable
 * @property value The value to shift the bits to the left
 * @constructor Creates a [VariableBitwiseXorAssignmentSpec]
 */
open class VariableBitwiseXorAssignmentSpec(
    val name: Identifier,
    val value: ValueSpec,
) : ValuedAssignmentSpec {

    /**
     * Generates the code for the [VariableBitwiseXorAssignmentSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "$name ^= $value"
    }

    /**
     * The builder for the [VariableBitwiseXorAssignmentSpec]
     *
     * @since 0.1.0
     * @constructor Creates a [VariableBitwiseXorAssignmentSpecBuilder]
     *
     * @property name The name of the variable
     * @property value The value to shift the bits to the left
     */
    class VariableBitwiseXorAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: ValueSpec? = null,
    ) {

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: Identifier): VariableBitwiseXorAssignmentSpecBuilder {
            this.name = name
            return this
        }

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: String): VariableBitwiseXorAssignmentSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        /**
         * Sets the value to shift the bits to the left
         *
         * @param value The value to shift the bits to the left
         * @return The builder
         */
        fun value(value: ValueSpec): VariableBitwiseXorAssignmentSpecBuilder {
            this.value = value
            return this
        }

        /**
         * Sets the value to shift the bits to the left
         *
         * @param value The value to shift the bits to the left
         * @return The builder
         */
        fun value(value: String): VariableBitwiseXorAssignmentSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        /**
         * Builds the [VariableBitwiseXorAssignmentSpec]
         *
         * @return The [VariableBitwiseXorAssignmentSpec]
         */
        fun build(): VariableBitwiseXorAssignmentSpec {
            return VariableBitwiseXorAssignmentSpec(
                name ?: throw IllegalStateException("Name not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {

        /**
         * Creates a new [VariableBitwiseXorAssignmentSpecBuilder]
         *
         * @return The [VariableBitwiseXorAssignmentSpecBuilder]
         */
        fun builder() = VariableBitwiseXorAssignmentSpecBuilder()
    }
}

/**
 * A variable increment before assignment increments a variable before using it
 *
 * @param name The name of the variable
 *
 * @since 0.1.0
 * @property name The name of the variable
 * @constructor Creates a [VariableIncrementBeforeSpec]
 */
open class VariableIncrementBeforeSpec(
    val name: Identifier,
) : ValuedAssignmentSpec {

    /**
     * Generates the code for the [VariableIncrementBeforeSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "++$name"
    }

    /**
     * The builder for the [VariableIncrementBeforeSpec]
     *
     * @since 0.1.0
     * @constructor Creates a [VariableIncrementBeforeSpecBuilder]
     *
     * @property name The name of the variable
     */
    open class VariableIncrementBeforeSpecBuilder
    internal constructor(
        var name: Identifier? = null,
    ) {

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: Identifier): VariableIncrementBeforeSpecBuilder {
            this.name = name
            return this
        }

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: String): VariableIncrementBeforeSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        /**
         * Builds the [VariableIncrementBeforeSpec]
         *
         * @return The [VariableIncrementBeforeSpec]
         */
        fun build(): VariableIncrementBeforeSpec {
            return VariableIncrementBeforeSpec(
                name ?: throw IllegalStateException("Name not set"),
            )
        }
    }

    companion object {

        /**
         * Creates a new [VariableIncrementBeforeSpecBuilder]
         *
         * @return The [VariableIncrementBeforeSpecBuilder]
         */
        fun builder() = VariableIncrementBeforeSpecBuilder()
    }
}

/**
 * A variable increment after assignment increments a variable after using it
 *
 * @param name The name of the variable
 *
 * @since 0.1.0
 * @property name The name of the variable
 * @constructor Creates a [VariableIncrementAfterSpec]
 */
open class VariableIncrementAfterSpec(
    val name: Identifier,
) : ValuedAssignmentSpec {

    /**
     * Generates the code for the [VariableIncrementAfterSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "$name++"
    }

    /**
     * The builder for the [VariableIncrementAfterSpec]
     *
     * @since 0.1.0
     * @constructor Creates a [VariableIncrementAfterSpecBuilder]
     *
     * @property name The name of the variable
     */
    open class VariableIncrementAfterSpecBuilder
    internal constructor(
        var name: Identifier? = null,
    ) {

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: Identifier): VariableIncrementAfterSpecBuilder {
            this.name = name
            return this
        }

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: String): VariableIncrementAfterSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        /**
         * Builds the [VariableIncrementAfterSpec]
         *
         * @return The [VariableIncrementAfterSpec]
         */
        fun build(): VariableIncrementAfterSpec {
            return VariableIncrementAfterSpec(
                name ?: throw IllegalStateException("Name not set"),
            )
        }
    }

    companion object {

        /**
         * Creates a new [VariableIncrementAfterSpecBuilder]
         *
         * @return The [VariableIncrementAfterSpecBuilder]
         */
        fun builder() = VariableIncrementAfterSpecBuilder()
    }
}

/**
 * A variable decrement before assignment decrements a variable before using it
 *
 * @param name The name of the variable
 *
 * @since 0.1.0
 * @property name The name of the variable
 * @constructor Creates a [VariableDecrementBeforeSpec]
 */
open class VariableDecrementBeforeSpec(
    val name: Identifier,
) : ValuedAssignmentSpec {

    /**
     * Generates the code for the [VariableDecrementBeforeSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "--$name"
    }

    /**
     * The builder for the [VariableDecrementBeforeSpec]
     *
     * @since 0.1.0
     * @constructor Creates a [VariableDecrementBeforeSpecBuilder]
     *
     * @property name The name of the variable
     */
    open class VariableDecrementBeforeSpecBuilder
    internal constructor(
        var name: Identifier? = null,
    ) {

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: Identifier): VariableDecrementBeforeSpecBuilder {
            this.name = name
            return this
        }

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: String): VariableDecrementBeforeSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        /**
         * Builds the [VariableDecrementBeforeSpec]
         *
         * @return The [VariableDecrementBeforeSpec]
         */
        fun build(): VariableDecrementBeforeSpec {
            return VariableDecrementBeforeSpec(
                name ?: throw IllegalStateException("Name not set"),
            )
        }
    }

    companion object {

        /**
         * Creates a new [VariableDecrementBeforeSpecBuilder]
         *
         * @return The [VariableDecrementBeforeSpecBuilder]
         */
        fun builder() = VariableDecrementBeforeSpecBuilder()
    }
}

/**
 * A variable decrement after assignment decrements a variable after using it
 *
 * @param name The name of the variable
 *
 * @since 0.1.0
 * @property name The name of the variable
 * @constructor Creates a [VariableDecrementAfterSpec]
 */
open class VariableDecrementAfterSpec(
    val name: Identifier,
) : ValuedAssignmentSpec {

    /**
     * Generates the code for the [VariableDecrementAfterSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "$name--"
    }

    /**
     * The builder for the [VariableDecrementAfterSpec]
     *
     * @since 0.1.0
     * @constructor Creates a [VariableDecrementAfterSpecBuilder]
     *
     * @property name The name of the variable
     */
    open class VariableDecrementAfterSpecBuilder
    internal constructor(
        var name: Identifier? = null,
    ) {

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: Identifier): VariableDecrementAfterSpecBuilder {
            this.name = name
            return this
        }

        /**
         * Sets the name of the variable
         *
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: String): VariableDecrementAfterSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        /**
         * Builds the [VariableDecrementAfterSpec]
         *
         * @return The [VariableDecrementAfterSpec]
         */
        fun build(): VariableDecrementAfterSpec {
            return VariableDecrementAfterSpec(
                name ?: throw IllegalStateException("Name not set"),
            )
        }
    }

    companion object {

        /**
         * Creates a new [VariableDecrementAfterSpecBuilder]
         *
         * @return The [VariableDecrementAfterSpecBuilder]
         */
        fun builder() = VariableDecrementAfterSpecBuilder()
    }
}

/**
 * A function call
 *
 * @param name The name of the function
 * @param arguments The arguments to pass to the function
 *
 * @since 0.1.0
 * @property name The name of the function
 * @property arguments The arguments to pass to the function
 * @constructor Creates a [FunctionCallSpec]
 */
open class FunctionCallSpec(
    val name: Identifier,
    val arguments: List<ValueSpec>,
) : ValueSpec, StatementSpec {

    /**
     * Generates the code for the [FunctionCallSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        val args = arguments.joinToString(", ") { it.generate(ctx) }
        return "$name($args)"
    }

    /**
     * The builder for the [FunctionCallSpec]
     *
     * @since 0.1.0
     * @constructor Creates a [FunctionCallSpecBuilder]
     *
     * @property name The name of the function
     * @property arguments The arguments to pass to the function
     */
    open class FunctionCallSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var arguments: MutableList<ValueSpec> = mutableListOf(),
    ) {

        /**
         * Sets the name of the function
         *
         * @param name The name of the function
         * @return The builder
         */
        fun name(name: Identifier): FunctionCallSpecBuilder {
            this.name = name
            return this
        }

        /**
         * Sets the name of the function
         *
         * @param name The name of the function
         * @return The builder
         */
        fun name(name: String): FunctionCallSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        /**
         * Adds an argument to the function call
         *
         * @param argument The argument to add
         * @return The builder
         */
        fun argument(argument: ValueSpec): FunctionCallSpecBuilder {
            arguments.add(argument)
            return this
        }

        /**
         * Adds an argument to the function call
         *
         * @param argument The argument to add
         * @return The builder
         */
        fun argument(argument: String): FunctionCallSpecBuilder {
            arguments.add(ValueSpec.of(argument))
            return this
        }

        /**
         * Builds the [FunctionCallSpec]
         * @return The [FunctionCallSpec]
         */
        fun build(): FunctionCallSpec {
            return FunctionCallSpec(
                name ?: throw IllegalStateException("Name not set"),
                arguments,
            )
        }
    }

    companion object {

        /**
         * Creates a new [FunctionCallSpecBuilder]
         *
         * @return The [FunctionCallSpecBuilder]
         */
        fun builder() = FunctionCallSpecBuilder()
    }
}
