@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.spec.code

import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.NamespaceSpec

/**
 * A [ValuedStatementSpec] is a StatementSpec and a ValueSpec at the same time
 * @since 0.1.0
 */
interface ValuedStatementSpec : StatementSpec, ValueSpec

/**
 * An abstract assignment spec
 * Superclass for all assignment specs
 * --> [VariableAssignmentSpec]
 * --> [VariableAdditionAssignmentSpec]
 * --> [VariableSubtractionAssignmentSpec]
 * --> [VariableMultiplicationAssignmentSpec]
 * --> [VariableDivisionAssignmentSpec]
 * --> [VariableModuloAssignmentSpec]
 * --> [VariablePowerAssignmentSpec]
 * --> [VariableBitwiseAndAssignmentSpec]
 * --> [VariableBitwiseOrAssignmentSpec]
 * --> [VariableBitwiseXorAssignmentSpec]
 */
abstract class AbstractAssignmentSpec(
    open val name: NamespaceSpec,
    open val value: ValueSpec,
) : ValuedStatementSpec {

    /**
     * The builder for the [AbstractAssignmentSpec]
     */
    abstract class AbstractAssignmentSpecBuilder<THIS : AbstractAssignmentSpecBuilder<THIS>>
    internal constructor(

        /**
         * The name of the variable
         */
        var name: NamespaceSpec? = null,

        /**
         * The value to assign to the variable
         */
        var value: ValueSpec? = null,
    ) {

        /**
         * Gets the instance of the builder
         * @return this as THIS
         */
        abstract fun getThis(): THIS

        /**
         * Sets the name of the variable
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: NamespaceSpec): THIS {
            this.name = name
            return getThis()
        }

        /**
         * Sets the name of the variable
         * @param name The name of the variable
         * @return The builder
         */
        fun name(vararg name: String): THIS {
            this.name = NamespaceSpec(*name)
            return getThis()
        }

        /**
         * Sets the value to assign to the variable
         * @param value The value to assign to the variable
         * @return The builder
         */
        fun value(value: ValueSpec): THIS {
            this.value = value
            return getThis()
        }

        /**
         * Sets the value to assign to the variable
         * @param value The value to assign to the variable
         * @return The builder
         * @deprecated WARNING: This function interprets the value as code, not as String literal!
         */
        fun value(value: String): THIS {
            this.value = ValueSpec.of(value)
            return getThis()
        }

        /**
         * Sets the value to assign to the variable
         * @param value The value to assign to the variable
         * @return The builder
         */
        fun value(value: Boolean) = value(ValueSpec.literal(value))

        /**
         * Sets the value to assign to the variable
         * @param value The value to assign to the variable
         * @return The builder
         */
        fun value(value: Byte) = value(ValueSpec.literal(value))

        /**
         * Sets the value to assign to the variable
         * @param value The value to assign to the variable
         * @return The builder
         */
        fun value(value: Short) = value(ValueSpec.literal(value))

        /**
         * Sets the value to assign to the variable
         * @param value The value to assign to the variable
         * @return The builder
         */
        fun value(value: Int) = value(ValueSpec.literal(value))

        /**
         * Sets the value to assign to the variable
         * @param value The value to assign to the variable
         * @return The builder
         */
        fun value(value: Long) = value(ValueSpec.literal(value))

        /**
         * Sets the value to assign to the variable
         * @param value The value to assign to the variable
         * @return The builder
         */
        fun value(value: Float) = value(ValueSpec.literal(value))

        /**
         * Sets the value to assign to the variable
         * @param value The value to assign to the variable
         * @return The builder
         */
        fun value(value: Double) = value(ValueSpec.literal(value))

        /**
         * Sets the value to assign to the variable
         * @param value The value to assign to the variable
         * @return The builder
         */
        fun value(value: Char) = value(ValueSpec.literal(value))

        /**
         * Builds the [VariableAssignmentSpec]
         *
         * @return The [VariableAssignmentSpec]
         */
        abstract fun build(): AbstractAssignmentSpec
    }
}

/**
 * An abstract modification spec
 * Superclass for all modification specs
 * --> [VariableIncrementBeforeSpec]
 * --> [VariableIncrementAfterSpec]
 * --> [VariableDecrementBeforeSpec]
 * --> [VariableDecrementAfterSpec]
 */
abstract class AbstractModificationSpec(

    /**
     * The name of the variable
     */
    open val name: NamespaceSpec,
) : ValuedStatementSpec {

    /**
     * The builder for the [AbstractModificationSpec]
     *
     * @since 0.1.0
     * @constructor Creates a [AbstractModificationSpecBuilder]
     */
    abstract class AbstractModificationSpecBuilder<THIS : AbstractModificationSpecBuilder<THIS>>
    internal constructor(

        /**
         * The name of the variable
         */
        var name: NamespaceSpec? = null,
    ) {

        /**
         * Gets the instance of the builder
         * @return this as THIS
         */
        abstract fun getThis(): THIS

        /**
         * Sets the name of the variable
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: NamespaceSpec): THIS {
            this.name = name
            return getThis()
        }

        /**
         * Sets the name of the variable
         * @param name The name of the variable
         * @return The builder
         */
        fun name(vararg name: String): THIS {
            this.name = NamespaceSpec(*name)
            return getThis()
        }

        /**
         * Builds the [VariableAssignmentSpec]
         *
         * @return The [VariableAssignmentSpec]
         */
        abstract fun build(): AbstractModificationSpec
    }
}

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
    name: NamespaceSpec,
    value: ValueSpec,
) : AbstractAssignmentSpec(name, value) {

    /**
     * Generates the code for the [VariableAssignmentSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "${name.generate(ctx)} = ${value.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VariableAssignmentSpec) return false
        if (name != other.name) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return 31 * name.hashCode() + value.hashCode()
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
        name: NamespaceSpec? = null,
        value: ValueSpec? = null,
    ) : AbstractAssignmentSpecBuilder<VariableAssignmentSpecBuilder>(
        name,
        value,
    ) {

        override fun getThis(): VariableAssignmentSpecBuilder = this

        /**
         * Builds the [VariableAssignmentSpec]
         * @return The [VariableAssignmentSpec]
         */
        override fun build(): VariableAssignmentSpec {
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
    name: NamespaceSpec,
    value: ValueSpec,
) : AbstractAssignmentSpec(name, value) {

    /**
     * Generates the code for the [VariableAdditionAssignmentSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "${name.generate(ctx)} += ${value.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VariableAdditionAssignmentSpec) return false
        if (name != other.name) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return 31 * name.hashCode() + value.hashCode()
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
        name: NamespaceSpec? = null,
        value: ValueSpec? = null,
    ) : AbstractAssignmentSpecBuilder<VariableAdditionAssignmentSpecBuilder>(
        name,
        value,
    ) {

        override fun getThis(): VariableAdditionAssignmentSpecBuilder = this

        /**
         * Builds the [VariableAdditionAssignmentSpec]
         *
         * @return The [VariableAdditionAssignmentSpec]
         */
        override fun build(): VariableAdditionAssignmentSpec {
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
    name: NamespaceSpec,
    value: ValueSpec,
) : AbstractAssignmentSpec(name, value) {

    /**
     * Generates the code for the [VariableSubtractionAssignmentSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "${name.generate(ctx)} -= ${value.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VariableSubtractionAssignmentSpec) return false
        if (name != other.name) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return 31 * name.hashCode() + value.hashCode()
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
        name: NamespaceSpec? = null,
        value: ValueSpec? = null,
    ) : AbstractAssignmentSpecBuilder<VariableSubtractionAssignmentSpecBuilder>(
        name,
        value,
    ) {

        override fun getThis(): VariableSubtractionAssignmentSpecBuilder = this

        /**
         * Builds the [VariableSubtractionAssignmentSpec]
         *
         * @return The [VariableSubtractionAssignmentSpec]
         */
        override fun build(): VariableSubtractionAssignmentSpec {
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
    name: NamespaceSpec,
    value: ValueSpec,
) : AbstractAssignmentSpec(name, value) {

    /**
     * Generates the code for the [VariableMultiplicationAssignmentSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "${name.generate(ctx)} *= ${value.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VariableMultiplicationAssignmentSpec) return false
        if (name != other.name) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return 31 * name.hashCode() + value.hashCode()
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
        name: NamespaceSpec? = null,
        value: ValueSpec? = null,
    ) : AbstractAssignmentSpecBuilder<VariableMultiplicationAssignmentSpecBuilder>(
        name,
        value,
    ) {

        override fun getThis(): VariableMultiplicationAssignmentSpecBuilder = this

        /**
         * Builds the [VariableMultiplicationAssignmentSpec]
         *
         * @return The [VariableMultiplicationAssignmentSpec]
         */
        override fun build(): VariableMultiplicationAssignmentSpec {
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
    name: NamespaceSpec,
    value: ValueSpec,
) : AbstractAssignmentSpec(name, value) {

    /**
     * Generates the code for the [VariableDivisionAssignmentSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "${name.generate(ctx)} /= ${value.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VariableDivisionAssignmentSpec) return false
        if (name != other.name) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return 31 * name.hashCode() + value.hashCode()
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
        name: NamespaceSpec? = null,
        value: ValueSpec? = null,
    ) : AbstractAssignmentSpecBuilder<VariableDivisionAssignmentSpecBuilder>(
        name,
        value,
    ) {

        override fun getThis(): VariableDivisionAssignmentSpecBuilder = this

        /**
         * Builds the [VariableDivisionAssignmentSpec]
         *
         * @return The [VariableDivisionAssignmentSpec]
         */
        override fun build(): VariableDivisionAssignmentSpec {
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
    name: NamespaceSpec,
    value: ValueSpec,
) : AbstractAssignmentSpec(name, value) {

    /**
     * Generates the code for the [VariableModuloAssignmentSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "${name.generate(ctx)} %= ${value.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VariableModuloAssignmentSpec) return false
        if (name != other.name) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return 31 * name.hashCode() + value.hashCode()
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
        name: NamespaceSpec? = null,
        value: ValueSpec? = null,
    ) : AbstractAssignmentSpecBuilder<VariableModuloAssignmentSpecBuilder>(
        name,
        value,
    ) {

        override fun getThis(): VariableModuloAssignmentSpecBuilder = this

        /**
         * Builds the [VariableModuloAssignmentSpec]
         *
         * @return The [VariableModuloAssignmentSpec]
         */
        override fun build(): VariableModuloAssignmentSpec {
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
    name: NamespaceSpec,
    value: ValueSpec,
) : AbstractAssignmentSpec(name, value) {

    /**
     * Generates the code for the [VariablePowerAssignmentSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "${name.generate(ctx)} **= ${value.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VariablePowerAssignmentSpec) return false
        if (name != other.name) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return 31 * name.hashCode() + value.hashCode()
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
        name: NamespaceSpec? = null,
        value: ValueSpec? = null,
    ) : AbstractAssignmentSpecBuilder<VariablePowerAssignmentSpecBuilder>(name, value) {

        override fun getThis(): VariablePowerAssignmentSpecBuilder = this

        /**
         * Builds the [VariablePowerAssignmentSpec]
         *
         * @return The [VariablePowerAssignmentSpec]
         */
        override fun build(): VariablePowerAssignmentSpec {
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
    name: NamespaceSpec,
    value: ValueSpec,
) : AbstractAssignmentSpec(name, value) {

    /**
     * Generates the code for the [VariableBitwiseAndAssignmentSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "${name.generate(ctx)} &= ${value.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VariableBitwiseAndAssignmentSpec) return false
        if (name != other.name) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return 31 * name.hashCode() + value.hashCode()
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
        name: NamespaceSpec? = null,
        value: ValueSpec? = null,
    ) : AbstractAssignmentSpecBuilder<VariableBitwiseAndAssignmentSpecBuilder>(name, value) {

        override fun getThis(): VariableBitwiseAndAssignmentSpecBuilder = this

        /**
         * Builds the [VariableBitwiseAndAssignmentSpec]
         *
         * @return The [VariableBitwiseAndAssignmentSpec]
         */
        override fun build(): VariableBitwiseAndAssignmentSpec {
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
    name: NamespaceSpec,
    value: ValueSpec,
) : AbstractAssignmentSpec(name, value) {

    /**
     * Generates the code for the [VariableBitwiseOrAssignmentSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "${name.generate(ctx)} |= ${value.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VariableBitwiseOrAssignmentSpec) return false
        if (name != other.name) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return 31 * name.hashCode() + value.hashCode()
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
        name: NamespaceSpec? = null,
        value: ValueSpec? = null,
    ) : AbstractAssignmentSpecBuilder<VariableBitwiseOrAssignmentSpecBuilder>(name, value) {

        override fun getThis(): VariableBitwiseOrAssignmentSpecBuilder = this

        /**
         * Builds the [VariableBitwiseOrAssignmentSpec]
         *
         * @return The [VariableBitwiseOrAssignmentSpec]
         */
        override fun build(): VariableBitwiseOrAssignmentSpec {
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
    name: NamespaceSpec,
    value: ValueSpec,
) : AbstractAssignmentSpec(name, value) {

    /**
     * Generates the code for the [VariableBitwiseXorAssignmentSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "${name.generate(ctx)} ^= ${value.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VariableBitwiseXorAssignmentSpec) return false
        if (name != other.name) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return 31 * name.hashCode() + value.hashCode()
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
        name: NamespaceSpec? = null,
        value: ValueSpec? = null,
    ) : AbstractAssignmentSpecBuilder<VariableBitwiseXorAssignmentSpecBuilder>(name, value) {

        override fun getThis(): VariableBitwiseXorAssignmentSpecBuilder = this

        /**
         * Builds the [VariableBitwiseXorAssignmentSpec]
         *
         * @return The [VariableBitwiseXorAssignmentSpec]
         */
        override fun build(): VariableBitwiseXorAssignmentSpec {
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
    name: NamespaceSpec,
) : AbstractModificationSpec(name) {

    /**
     * Generates the code for the [VariableIncrementBeforeSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "++${name.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VariableIncrementBeforeSpec) return false
        if (name != other.name) return false
        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
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
        name: NamespaceSpec? = null,
    ) : AbstractModificationSpecBuilder<VariableIncrementBeforeSpecBuilder>(name) {

        override fun getThis(): VariableIncrementBeforeSpecBuilder = this

        /**
         * Builds the [VariableIncrementBeforeSpec]
         *
         * @return The [VariableIncrementBeforeSpec]
         */
        override fun build(): VariableIncrementBeforeSpec {
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
    name: NamespaceSpec,
) : AbstractModificationSpec(name) {

    /**
     * Generates the code for the [VariableIncrementAfterSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "${name.generate(ctx)}++"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VariableIncrementAfterSpec) return false
        if (name != other.name) return false
        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
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
        name: NamespaceSpec? = null,
    ) : AbstractModificationSpecBuilder<VariableIncrementAfterSpecBuilder>(name) {

        override fun getThis(): VariableIncrementAfterSpecBuilder = this

        /**
         * Builds the [VariableIncrementAfterSpec]
         *
         * @return The [VariableIncrementAfterSpec]
         */
        override fun build(): VariableIncrementAfterSpec {
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
    name: NamespaceSpec,
) : AbstractModificationSpec(name) {

    /**
     * Generates the code for the [VariableDecrementBeforeSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "--${name.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VariableDecrementBeforeSpec) return false
        if (name != other.name) return false
        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
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
        name: NamespaceSpec? = null,
    ) : AbstractModificationSpecBuilder<VariableDecrementBeforeSpecBuilder>(name) {

        override fun getThis(): VariableDecrementBeforeSpecBuilder = this

        /**
         * Builds the [VariableDecrementBeforeSpec]
         *
         * @return The [VariableDecrementBeforeSpec]
         */
        override fun build(): VariableDecrementBeforeSpec {
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
    name: NamespaceSpec,
) : AbstractModificationSpec(name) {

    /**
     * Generates the code for the [VariableDecrementAfterSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        return "${name.generate(ctx)}--"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VariableDecrementAfterSpec) return false
        if (name != other.name) return false
        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
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
        name: NamespaceSpec? = null,
    ) : AbstractModificationSpecBuilder<VariableDecrementAfterSpecBuilder>(name) {

        override fun getThis(): VariableDecrementAfterSpecBuilder = this

        /**
         * Builds the [VariableDecrementAfterSpec]
         *
         * @return The [VariableDecrementAfterSpec]
         */
        override fun build(): VariableDecrementAfterSpec {
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
    open val name: NamespaceSpec,
    open val arguments: List<ValueSpec>,
) : ValueSpec, StatementSpec {

    /**
     * Generates the code for the [FunctionCallSpec]
     *
     * @param ctx The context to generate the code in
     * @return The generated code
     */
    override fun generate(ctx: GenerationContext): String {
        val args = arguments.joinToString(", ") { it.generate(ctx) }
        return "${name.generate(ctx)}($args)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FunctionCallSpec) return false
        if (name != other.name) return false
        if (arguments != other.arguments) return false
        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + arguments.hashCode()
        return result
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
        var name: NamespaceSpec? = null,
        var arguments: MutableList<ValueSpec> = mutableListOf(),
    ) {

        /**
         * Sets the name of the function
         *
         * @param name The name of the function
         * @return The builder
         */
        fun name(name: NamespaceSpec): FunctionCallSpecBuilder {
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
            this.name = NamespaceSpec(name)
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
         * Adds multiple arguments to the function call
         *
         * @param arguments The arguments to add
         * @return The builder
         */
        fun arguments(arguments: List<ValueSpec>): FunctionCallSpecBuilder {
            this.arguments.addAll(arguments)
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
