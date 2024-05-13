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
import com.shakelang.shake.shakespeare.spec.NamespaceSpec
import com.shakelang.util.parseutils.characters.Characters

/**
 * A ValueSpec is a spec that represents a value
 * This can be a literal, a variable reference or a calculation
 */
interface ValueSpec : AbstractSpec {

    /**
     * Generate the value
     * @param ctx The context to generate the value in
     * @return The generated value
     */
    override fun generate(ctx: GenerationContext): String

    companion object {

        /**
         * Create a ValueSpec from a string
         * @param value The value to create the ValueSpec from
         * @return The created ValueSpec
         */
        fun of(value: String): ValueSpec {
            return object : ValueSpec {
                override fun generate(ctx: GenerationContext): String {
                    return value
                }

                override fun equals(other: Any?): Boolean {
                    if (this === other) return true
                    if (other !is ValueSpec) return false
                    return value == other.generate(GenerationContext())
                }
            }
        }

        fun literal(value: Boolean): ValueSpec {
            return BooleanLiteralSpec(value)
        }

        fun literal(value: Byte): ValueSpec {
            return IntLiteralSpec(value)
        }

        fun literal(value: Short): ValueSpec {
            return IntLiteralSpec(value)
        }

        fun literal(value: Int): ValueSpec {
            return IntLiteralSpec(value)
        }

        fun literal(value: Long): ValueSpec {
            return IntLiteralSpec(value)
        }

        fun literal(value: Float): ValueSpec {
            return FloatLiteralSpec(value)
        }

        fun literal(value: Double): ValueSpec {
            return FloatLiteralSpec(value)
        }

        fun literal(value: String): ValueSpec {
            return StringLiteralSpec(value)
        }

        fun literal(value: Char): ValueSpec {
            return CharacterLiteralSpec(value)
        }

        fun literal(value: UByte): ValueSpec {
            return IntLiteralSpec(value.toLong())
        }

        fun literal(value: UShort): ValueSpec {
            return IntLiteralSpec(value.toLong())
        }

        fun literal(value: UInt): ValueSpec {
            return IntLiteralSpec(value.toLong())
        }

        fun literal(value: ULong): ValueSpec {
            return IntLiteralSpec(value.toLong())
        }

        fun literal(value: Number): ValueSpec {
            return when (value) {
                is Byte -> IntLiteralSpec(value)
                is Short -> IntLiteralSpec(value)
                is Int -> IntLiteralSpec(value)
                is Long -> IntLiteralSpec(value)
                is Float -> FloatLiteralSpec(value)
                is Double -> FloatLiteralSpec(value)
                else -> throw IllegalArgumentException("Unsupported number type")
            }
        }
    }
}

/**
 * A ValueSpec that represents a string literal
 *
 * @param value The value of the string
 */
open class StringLiteralSpec(val value: String) : ValueSpec {

    /**
     * The escaped value of the string
     */
    val escaped = Characters.escapeString(value)

    /**
     * Generate the string
     * @param ctx The context to generate the string in
     * @return The generated string
     */
    override fun generate(ctx: GenerationContext): String {
        return "\"$escaped\""
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StringLiteralSpec) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    /**
     * Builder for StringLiteralSpec
     */
    class StringLiteralSpecBuilder
    internal constructor(

        /**
         * The value of the string
         */
        var value: String? = null,
    ) {

        /**
         * Set the value of the string
         * @param value The value of the string
         * @return The builder
         */
        fun value(value: String): StringLiteralSpecBuilder {
            this.value = value
            return this
        }

        /**
         * Build the StringLiteralSpec
         * @return The created StringLiteralSpec
         */
        fun build(): StringLiteralSpec {
            return StringLiteralSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new StringLiteralSpecBuilder
         * @return The created StringLiteralSpecBuilder
         */
        fun builder() = StringLiteralSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a character literal
 *
 * @param value The value of the character
 */
open class CharacterLiteralSpec(val value: Char) : ValueSpec {

    /**
     * The escaped value of the character
     */
    val escaped = Characters.escapeCharacter(value)

    /**
     * Generate the character
     * @param ctx The context to generate the character in
     * @return The generated character
     */
    override fun generate(ctx: GenerationContext): String {
        return "'${Characters.escapeCharacter(value)}'"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CharacterLiteralSpec) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    /**
     * Builder for CharacterLiteralSpec
     */
    class CharacterLiteralSpecBuilder
    internal constructor(

        /**
         * The value of the character
         */
        var value: Char? = null,
    ) {

        /**
         * Set the value of the character
         * @param value The value of the character
         * @return The builder
         */
        fun value(value: Char): CharacterLiteralSpecBuilder {
            this.value = value
            return this
        }

        /**
         * Build the CharacterLiteralSpec
         * @return The created CharacterLiteralSpec
         */
        fun build(): CharacterLiteralSpec {
            return CharacterLiteralSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new CharacterLiteralSpecBuilder
         * @return The created CharacterLiteralSpecBuilder
         */
        fun builder() = CharacterLiteralSpecBuilder()
    }
}

/**
 * A ValueSpec that represents an integer literal
 * @param value The value of the integer
 */
open class IntLiteralSpec(

    /**
     * The value of the integer
     */
    val value: Long,
) : ValueSpec {

    /**
     * Constructor for IntLiteralSpec
     * @param value The value of the integer
     */
    constructor(value: Byte) : this(value.toLong())

    /**
     * Constructor for IntLiteralSpec
     * @param value The value of the integer
     */
    constructor(value: Short) : this(value.toLong())

    /**
     * Constructor for IntLiteralSpec
     * @param value The value of the integer
     */
    constructor(value: Int) : this(value.toLong())

    /**
     * Constructor for IntLiteralSpec
     * @param ctx The value of the integer
     * @return The generated integer
     */
    override fun generate(ctx: GenerationContext): String {
        return value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is IntLiteralSpec) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    /**
     * Builder for IntLiteralSpec
     */
    class IntLiteralSpecBuilder
    internal constructor(

        /**
         * The value of the integer
         */
        var value: Long? = null,
    ) {

        /**
         * Set the value of the integer
         * @param value The value of the integer
         * @return The builder
         */
        fun value(value: Long): IntLiteralSpecBuilder {
            this.value = value
            return this
        }

        /**
         * Set the value of the integer
         * @param value The value of the integer
         * @return The builder
         */
        fun value(value: Int): IntLiteralSpecBuilder {
            this.value = value.toLong()
            return this
        }

        /**
         * Set the value of the integer
         * @param value The value of the integer
         * @return The builder
         */
        fun value(value: Short): IntLiteralSpecBuilder {
            this.value = value.toLong()
            return this
        }

        /**
         * Set the value of the integer
         * @param value The value of the integer
         * @return The builder
         */
        fun value(value: Byte): IntLiteralSpecBuilder {
            this.value = value.toLong()
            return this
        }

        /**
         * Set the value of the integer
         * @param value The value of the integer
         * @return The builder
         */
        fun value(value: ULong): IntLiteralSpecBuilder {
            this.value = value.toLong()
            return this
        }

        /**
         * Set the value of the integer
         * @param value The value of the integer
         * @return The builder
         */
        fun value(value: UInt): IntLiteralSpecBuilder {
            this.value = value.toLong()
            return this
        }

        /**
         * Set the value of the integer
         * @param value The value of the integer
         * @return The builder
         */
        fun value(value: UShort): IntLiteralSpecBuilder {
            this.value = value.toLong()
            return this
        }

        /**
         * Set the value of the integer
         * @param value The value of the integer
         * @return The builder
         */
        fun value(value: UByte): IntLiteralSpecBuilder {
            this.value = value.toLong()
            return this
        }

        /**
         * Build the IntLiteralSpec
         * @return The created IntLiteralSpec
         */
        fun build(): IntLiteralSpec {
            return IntLiteralSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new NumberLiteralSpecBuilder
         * @return The created NumberLiteralSpecBuilder
         */
        fun builder() = IntLiteralSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a float literal
 *
 * @param value The value of the float
 */
open class FloatLiteralSpec(

    /**
     * The value of the float
     */
    val value: Double,
) : ValueSpec {

    constructor(value: Float) : this(value.toDouble())

    /**
     * Generate the float
     * @param ctx The context to generate the float in
     * @return The generated float
     */
    override fun generate(ctx: GenerationContext): String {
        return value.stringifyIncludeComma()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FloatLiteralSpec) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    private fun Double.stringifyIncludeComma(): String {
        val str = this.toString()
        return if (str.contains(".")) str else "$str.0"
    }

    /**
     * Builder for FloatLiteralSpec
     */
    class FloatLiteralSpecBuilder
    internal constructor(

        /**
         * The value of the float
         */
        var value: Double? = null,
    ) {

        /**
         * Set the value of the float
         * @param value The value of the float
         * @return The builder
         */
        fun value(value: Float): FloatLiteralSpecBuilder {
            this.value = value.toDouble()
            return this
        }

        /**
         * Set the value of the float
         * @param value The value of the float
         * @return The builder
         */
        fun value(value: Double): FloatLiteralSpecBuilder {
            this.value = value
            return this
        }

        /**
         * Build the FloatLiteralSpec
         * @return The created FloatLiteralSpec
         */
        fun build(): FloatLiteralSpec {
            return FloatLiteralSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new FloatLiteralSpecBuilder
         * @return The created FloatLiteralSpecBuilder
         */
        fun builder() = FloatLiteralSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a boolean literal
 *
 * @param value The value of the boolean
 */
open class BooleanLiteralSpec(

    /**
     * The value of the boolean
     */
    val value: Boolean,
) : ValueSpec {

    /**
     * Generate the boolean
     * @param ctx The context to generate the boolean in
     * @return The generated boolean
     */
    override fun generate(ctx: GenerationContext): String {
        return value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BooleanLiteralSpec) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return if (value) 1 else 0
    }

    /**
     * Builder for BooleanLiteralSpec
     */
    class BooleanLiteralSpecBuilder
    internal constructor(

        /**
         * The value of the boolean
         */
        var value: Boolean? = null,
    ) {

        /**
         * Set the value of the boolean
         * @param value The value of the boolean
         * @return The builder
         */
        fun value(value: Boolean): BooleanLiteralSpecBuilder {
            this.value = value
            return this
        }

        /**
         * Build the BooleanLiteralSpec
         * @return The created BooleanLiteralSpec
         */
        fun build(): BooleanLiteralSpec {
            return BooleanLiteralSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new BooleanLiteralSpecBuilder
         * @return The created BooleanLiteralSpecBuilder
         */
        fun builder() = BooleanLiteralSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a null literal
 */
open class NullLiteralSpec : ValueSpec {

    /**
     * Generate the null literal
     * @param ctx The context to generate the null literal in
     * @return The generated null literal
     */
    override fun generate(ctx: GenerationContext): String {
        return "null"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NullLiteralSpec) return false
        return true
    }

    override fun hashCode(): Int {
        return this::class.hashCode()
    }

    companion object {

        /**
         * The instance of the NullLiteralSpec
         */
        val INSTANCE = NullLiteralSpec()
    }
}

/**
 * A ValueSpec that represents a variable reference
 *
 * @param name The name of the variable
 */
open class VariableReferenceSpec(open val name: NamespaceSpec) : ValueSpec {

    /**
     * Constructor for VariableReferenceSpec
     * @param name The name of the variable
     * @return The generated variable reference
     */
    constructor(vararg name: String) : this(NamespaceSpec(*name))

    /**
     * Generate the variable reference
     * @param ctx The context to generate the variable reference in
     * @return The generated variable reference
     */
    override fun generate(ctx: GenerationContext): String {
        return name.generate(ctx)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VariableReferenceSpec) return false
        if (name != other.name) return false
        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    /**
     * Builder for VariableReferenceSpec
     */
    class VariableReferenceSpecBuilder
    internal constructor(

        /**
         * The name of the variable
         */
        var name: NamespaceSpec? = null,
    ) {

        /**
         * Set the name of the variable
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: NamespaceSpec): VariableReferenceSpecBuilder {
            this.name = name
            return this
        }

        /**
         * Set the name of the variable
         * @param name The name of the variable
         * @return The builder
         */
        fun name(name: String): VariableReferenceSpecBuilder {
            if (this.name != null) {
                this.name = NamespaceSpec(*this.name!!.name, name)
            } else {
                this.name = NamespaceSpec(name)
            }
            return this
        }

        /**
         * Build the VariableReferenceSpec
         * @return The created VariableReferenceSpec
         */
        fun build(): VariableReferenceSpec {
            return VariableReferenceSpec(
                name ?: throw IllegalStateException("Name not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new VariableReferenceSpecBuilder
         * @return The created VariableReferenceSpecBuilder
         */
        fun builder() = VariableReferenceSpecBuilder()
    }
}

abstract class AbstractDualOperatorSpec(
    open val left: ValueSpec,
    open val right: ValueSpec,
) : ValueSpec {
    abstract class AbstractDualOperatorSpecBuilder<THIS : AbstractDualOperatorSpecBuilder<THIS>>
    internal constructor(

        /**
         * The left value of the addition
         */
        var left: ValueSpec? = null,

        /**
         * The right value of the addition
         */
        var right: ValueSpec? = null,
    ) {

        abstract fun getThis(): THIS

        /**
         * Set the left value of the addition
         * @param left The left value of the addition
         * @return The builder
         */
        fun left(left: ValueSpec): THIS {
            this.left = left
            return getThis()
        }

        /**
         * Set the left value of the addition
         * @param left The left value of the addition
         * @return The builder
         */
        fun left(left: String): THIS {
            this.left = ValueSpec.of(left)
            return getThis()
        }

        /**
         * Set the left value of the addition
         * @param left The left value of the addition
         * @return The builder
         */
        fun left(left: NamespaceSpec): THIS {
            this.left = VariableReferenceSpec(left)
            return getThis()
        }

        /**
         * Set the left value of the addition
         * @param left The left value of the addition
         * @return The builder
         */
        fun left(left: Boolean): THIS {
            this.left = BooleanLiteralSpec(left)
            return getThis()
        }

        /**
         * Set the left value of the addition
         * @param left The left value of the addition
         * @return The builder
         */
        fun left(left: Byte): THIS {
            this.left = IntLiteralSpec(left)
            return getThis()
        }

        /**
         * Set the left value of the addition
         * @param left The left value of the addition
         * @return The builder
         */
        fun left(left: Short): THIS {
            this.left = IntLiteralSpec(left)
            return getThis()
        }

        /**
         * Set the left value of the addition
         * @param left The left value of the addition
         * @return The builder
         */
        fun left(left: Int): THIS {
            this.left = IntLiteralSpec(left)
            return getThis()
        }

        /**
         * Set the left value of the addition
         * @param left The left value of the addition
         * @return The builder
         */
        fun left(left: Long): THIS {
            this.left = IntLiteralSpec(left)
            return getThis()
        }

        /**
         * Set the left value of the addition
         * @param left The left value of the addition
         * @return The builder
         */
        fun left(left: Float): THIS {
            this.left = FloatLiteralSpec(left)
            return getThis()
        }

        /**
         * Set the left value of the addition
         * @param left The left value of the addition
         * @return The builder
         */
        fun left(left: Double): THIS {
            this.left = FloatLiteralSpec(left)
            return getThis()
        }

        /**
         * Set the right value of the addition
         * @param right The right value of the addition
         * @return The builder
         */
        fun right(right: ValueSpec): THIS {
            this.right = right
            return getThis()
        }

        /**
         * Set the right value of the addition
         * @param right The right value of the addition
         * @return The builder
         */
        fun right(right: String): THIS {
            this.right = ValueSpec.of(right)
            return getThis()
        }

        /**
         * Set the right value of the addition
         * @param right The right value of the addition
         * @return The builder
         */
        fun right(right: NamespaceSpec): THIS {
            this.right = VariableReferenceSpec(right)
            return getThis()
        }

        /**
         * Set the right value of the addition
         * @param right The right value of the addition
         * @return The builder
         */
        fun right(right: Boolean): THIS {
            this.right = BooleanLiteralSpec(right)
            return getThis()
        }

        /**
         * Set the right value of the addition
         * @param right The right value of the addition
         * @return The builder
         */
        fun right(right: Byte): THIS {
            this.right = IntLiteralSpec(right)
            return getThis()
        }

        /**
         * Set the right value of the addition
         * @param right The right value of the addition
         * @return The builder
         */
        fun right(right: Short): THIS {
            this.right = IntLiteralSpec(right)
            return getThis()
        }

        /**
         * Set the right value of the addition
         * @param right The right value of the addition
         * @return The builder
         */
        fun right(right: Int): THIS {
            this.right = IntLiteralSpec(right)
            return getThis()
        }

        /**
         * Set the right value of the addition
         * @param right The right value of the addition
         * @return The builder
         */
        fun right(right: Long): THIS {
            this.right = IntLiteralSpec(right)
            return getThis()
        }

        /**
         * Set the right value of the addition
         * @param right The right value of the addition
         * @return The builder
         */
        fun right(right: Float): THIS {
            this.right = FloatLiteralSpec(right)
            return getThis()
        }

        /**
         * Set the right value of the addition
         * @param right The right value of the addition
         * @return The builder
         */
        fun right(right: Double): THIS {
            this.right = FloatLiteralSpec(right)
            return getThis()
        }

        /**
         * Build the AdditionSpec
         * @return The created AdditionSpec
         */
        abstract fun build(): AbstractDualOperatorSpec
    }
}

/**
 * A ValueSpec that represents an unary operation
 */
abstract class AbstractUnaryOperatorSpec(
    open val value: ValueSpec,
) : ValueSpec {

    abstract class AbstractUnaryOperatorSpecBuilder<THIS : AbstractUnaryOperatorSpecBuilder<THIS>>
    internal constructor(

        /**
         * The value
         */
        var value: ValueSpec? = null,
    ) {

        abstract fun getThis(): THIS

        /**
         * Set the value
         * @param value The value
         * @return The builder
         */
        fun value(value: ValueSpec): THIS {
            this.value = value
            return getThis()
        }

        /**
         * Set the value
         * @param value The value
         * @return The builder
         */
        fun value(value: String): THIS {
            this.value = ValueSpec.of(value)
            return getThis()
        }

        /**
         * Set the value
         * @param value The value
         * @return The builder
         */
        fun value(value: NamespaceSpec): THIS {
            this.value = VariableReferenceSpec(value)
            return getThis()
        }

        /**
         * Set the value
         * @param value The value
         * @return The builder
         */
        fun value(value: Boolean): THIS {
            this.value = BooleanLiteralSpec(value)
            return getThis()
        }

        /**
         * Set the value
         * @param value The value
         * @return The builder
         */
        fun value(value: Byte): THIS {
            this.value = IntLiteralSpec(value)
            return getThis()
        }

        /**
         * Set the value
         * @param value The value
         * @return The builder
         */
        fun value(value: Short): THIS {
            this.value = IntLiteralSpec(value)
            return getThis()
        }

        /**
         * Set the value
         * @param value The value
         * @return The builder
         */
        fun value(value: Int): THIS {
            this.value = IntLiteralSpec(value)
            return getThis()
        }

        /**
         * Set the value
         * @param value The value
         * @return The builder
         */
        fun value(value: Long): THIS {
            this.value = IntLiteralSpec(value)
            return getThis()
        }

        /**
         * Set the value
         * @param value The value
         * @return The builder
         */
        fun value(value: Float): THIS {
            this.value = FloatLiteralSpec(value)
            return getThis()
        }

        /**
         * Set the value
         * @param value The value
         * @return The builder
         */
        fun value(value: Double): THIS {
            this.value = FloatLiteralSpec(value)
            return getThis()
        }

        /**
         * Build the [AbstractUnaryOperatorSpec]
         * @return The created [AbstractUnaryOperatorSpec]
         */
        abstract fun build(): AbstractUnaryOperatorSpec
    }
}

/**
 * A ValueSpec that represents a calculation
 */
open class AdditionSpec(
    /**
     * The left value of the addition
     */
    left: ValueSpec,

    /**
     * The right value of the addition
     */
    right: ValueSpec,
) : AbstractDualOperatorSpec(left, right) {

    /**
     * Generate the addition
     * @param ctx The context to generate the addition in
     * @return The generated addition
     */
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} + ${right.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AdditionSpec) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    /**
     * Builder for AdditionSpec
     */
    class AdditionSpecBuilder
    internal constructor(

        /**
         * The left value of the addition
         */
        left: ValueSpec? = null,

        /**
         * The right value of the addition
         */
        right: ValueSpec? = null,
    ) : AbstractDualOperatorSpecBuilder<AdditionSpecBuilder>(left, right) {

        override fun getThis() = this

        /**
         * Build the AdditionSpec
         * @return The created AdditionSpec
         */
        override fun build(): AdditionSpec {
            return AdditionSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new AdditionSpecBuilder
         * @return The created AdditionSpecBuilder
         */
        fun builder() = AdditionSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a subtraction
 */
open class SubtractionSpec(

    /**
     * The left value of the subtraction
     */
    left: ValueSpec,

    /**
     * The right value of the subtraction
     */
    right: ValueSpec,
) : AbstractDualOperatorSpec(left, right) {

    /**
     * Generate the subtraction
     * @param ctx The context to generate the subtraction in
     * @return The generated subtraction
     */
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} - ${right.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SubtractionSpec) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    /**
     * Builder for SubtractionSpec
     */
    class SubtractionSpecBuilder
    internal constructor(

        /**
         * The left value of the subtraction
         */
        left: ValueSpec? = null,

        /**
         * The right value of the subtraction
         */
        right: ValueSpec? = null,
    ) : AbstractDualOperatorSpecBuilder<SubtractionSpecBuilder>(left, right) {

        override fun getThis() = this

        /**
         * Build the SubtractionSpec
         * @return The created SubtractionSpec
         */
        override fun build(): SubtractionSpec {
            return SubtractionSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new SubtractionSpecBuilder
         * @return The created SubtractionSpecBuilder
         */
        fun builder() = SubtractionSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a multiplication
 */
open class MultiplicationSpec(

    /**
     * The left value of the multiplication
     */
    left: ValueSpec,

    /**
     * The right value of the multiplication
     */
    right: ValueSpec,
) : AbstractDualOperatorSpec(left, right) {

    /**
     * Generate the multiplication
     * @param ctx The context to generate the multiplication in
     * @return The generated multiplication
     */
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} * ${right.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MultiplicationSpec) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    /**
     * Builder for MultiplicationSpec
     */
    class MultiplicationSpecBuilder
    internal constructor(

        /**
         * The left value of the multiplication
         */
        left: ValueSpec? = null,

        /**
         * The right value of the multiplication
         */
        right: ValueSpec? = null,
    ) : AbstractDualOperatorSpecBuilder<MultiplicationSpecBuilder>(left, right) {

        override fun getThis() = this

        /**
         * Build the MultiplicationSpec
         * @return The created MultiplicationSpec
         */
        override fun build(): MultiplicationSpec {
            return MultiplicationSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new MultiplicationSpecBuilder
         * @return The created MultiplicationSpecBuilder
         */
        fun builder() = MultiplicationSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a division
 */
open class DivisionSpec(

    /**
     * The left value of the division
     */
    left: ValueSpec,

    /**
     * The right value of the division
     */
    right: ValueSpec,
) : AbstractDualOperatorSpec(left, right) {

    /**
     * Generate the division
     * @param ctx The context to generate the division in
     * @return The generated division
     */
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} / ${right.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DivisionSpec) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    /**
     * Builder for DivisionSpec
     */
    class DivisionSpecBuilder
    internal constructor(

        /**
         * The left value of the division
         */
        left: ValueSpec? = null,

        /**
         * The right value of the division
         */
        right: ValueSpec? = null,
    ) : AbstractDualOperatorSpecBuilder<DivisionSpecBuilder>(left, right) {

        override fun getThis() = this

        /**
         * Build the DivisionSpec
         * @return The created DivisionSpec
         */
        override fun build(): DivisionSpec {
            return DivisionSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new DivisionSpecBuilder
         * @return The created DivisionSpecBuilder
         */
        fun builder() = DivisionSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a modulo
 */
open class ModuloSpec(

    /**
     * The left value of the modulo
     */
    left: ValueSpec,

    /**
     * The right value of the modulo
     */
    right: ValueSpec,
) : AbstractDualOperatorSpec(left, right) {

    /**
     * Generate the modulo
     * @param ctx The context to generate the modulo in
     * @return The generated modulo
     */
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} % ${right.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ModuloSpec) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    /**
     * Builder for ModuloSpec
     */
    class ModuloSpecBuilder
    internal constructor(

        /**
         * The left value of the modulo
         */
        left: ValueSpec? = null,

        /**
         * The right value of the modulo
         */
        right: ValueSpec? = null,
    ) : AbstractDualOperatorSpecBuilder<ModuloSpecBuilder>(left, right) {

        override fun getThis() = this

        /**
         * Build the ModuloSpec
         * @return The created ModuloSpec
         */
        override fun build(): ModuloSpec {
            return ModuloSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new ModuloSpecBuilder
         * @return The created ModuloSpecBuilder
         */
        fun builder() = ModuloSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a power
 */
open class PowerSpec(

    /**
     * The left value of the power
     */
    left: ValueSpec,

    /**
     * The right value of the power
     */
    right: ValueSpec,
) : AbstractDualOperatorSpec(left, right) {

    /**
     * Generate the power
     * @param ctx The context to generate the power in
     * @return The generated power
     */
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} ** ${right.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PowerSpec) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    /**
     * Builder for PowerSpec
     */
    class PowerSpecBuilder
    internal constructor(

        /**
         * The left value of the power
         */
        left: ValueSpec? = null,

        /**
         * The right value of the power
         */
        right: ValueSpec? = null,
    ) : AbstractDualOperatorSpecBuilder<PowerSpecBuilder>(left, right) {

        override fun getThis() = this

        /**
         * Build the PowerSpec
         * @return The created PowerSpec
         */
        override fun build(): PowerSpec {
            return PowerSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new PowerSpecBuilder
         * @return The created PowerSpecBuilder
         */
        fun builder() = PowerSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a unary minus
 */
open class UnaryMinusSpec(

    /**
     * The value to negate
     */
    value: ValueSpec,
) : AbstractUnaryOperatorSpec(value) {

    /**
     * Generate the unary minus
     * @param ctx The context to generate the unary minus in
     * @return The generated unary minus
     */
    override fun generate(ctx: GenerationContext): String {
        return "-${value.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UnaryMinusSpec) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    /**
     * Builder for UnaryMinusSpec
     */
    class UnaryMinusSpecBuilder
    internal constructor(

        /**
         * The value to negate
         */
        value: ValueSpec? = null,
    ) : AbstractUnaryOperatorSpecBuilder<UnaryMinusSpecBuilder>(value) {

        override fun getThis() = this

        /**
         * Build the UnaryMinusSpec
         * @return The created UnaryMinusSpec
         */
        override fun build(): UnaryMinusSpec {
            return UnaryMinusSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new UnaryMinusSpecBuilder
         * @return The created UnaryMinusSpecBuilder
         */
        fun builder() = UnaryMinusSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a unary plus
 */
open class UnaryPlusSpec(

    /**
     * The value to negate
     */
    value: ValueSpec,
) : AbstractUnaryOperatorSpec(value) {

    /**
     * Generate the unary plus
     * @param ctx The context to generate the unary plus in
     * @return The generated unary plus
     */
    override fun generate(ctx: GenerationContext): String {
        return "+${value.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UnaryPlusSpec) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    /**
     * Builder for UnaryPlusSpec
     */
    class UnaryPlusSpecBuilder
    internal constructor(

        /**
         * The value to negate
         */
        value: ValueSpec? = null,
    ) : AbstractUnaryOperatorSpecBuilder<UnaryPlusSpecBuilder>(value) {

        override fun getThis() = this

        /**
         * Build the UnaryPlusSpec
         * @return The created UnaryPlusSpec
         */
        override fun build(): UnaryPlusSpec {
            return UnaryPlusSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new UnaryPlusSpecBuilder
         * @return The created UnaryPlusSpecBuilder
         */
        fun builder() = UnaryPlusSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a logical and
 */
open class LogicalAndSpec(

    /**
     * The left value of the logical and
     */
    left: ValueSpec,

    /**
     * The right value of the logical and
     */
    right: ValueSpec,
) : AbstractDualOperatorSpec(left, right) {

    /**
     * Generate the logical and
     * @param ctx The context to generate the logical and in
     * @return The generated logical and
     */
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} && ${right.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LogicalAndSpec) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    /**
     * Builder for LogicalAndSpec
     */
    class LogicalAndSpecBuilder
    internal constructor(

        /**
         * The left value of the logical and
         */
        left: ValueSpec? = null,

        /**
         * The right value of the logical and
         */
        right: ValueSpec? = null,
    ) : AbstractDualOperatorSpecBuilder<LogicalAndSpecBuilder>(left, right) {

        override fun getThis() = this

        /**
         * Build the LogicalAndSpec
         * @return The created LogicalAndSpec
         */
        override fun build(): LogicalAndSpec {
            return LogicalAndSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new LogicalAndSpecBuilder
         * @return The created LogicalAndSpecBuilder
         */
        fun builder() = LogicalAndSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a logical or
 */
open class LogicalOrSpec(

    /**
     * The left value of the logical or
     */
    left: ValueSpec,

    /**
     * The right value of the logical or
     */
    right: ValueSpec,
) : AbstractDualOperatorSpec(left, right) {

    /**
     * Generate the logical or
     * @param ctx The context to generate the logical or in
     * @return The generated logical or
     */
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} || ${right.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LogicalOrSpec) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    /**
     * Builder for LogicalOrSpec
     */
    class LogicalOrSpecBuilder
    internal constructor(

        /**
         * The left value of the logical or
         */
        left: ValueSpec? = null,

        /**
         * The right value of the logical or
         */
        right: ValueSpec? = null,
    ) : AbstractDualOperatorSpecBuilder<LogicalOrSpecBuilder>(left, right) {

        override fun getThis() = this

        /**
         * Build the LogicalOrSpec
         * @return The created LogicalOrSpec
         */
        override fun build(): LogicalOrSpec {
            return LogicalOrSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new LogicalOrSpecBuilder
         * @return The created LogicalOrSpecBuilder
         */
        fun builder() = LogicalOrSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a logical not
 */
open class LogicalNotSpec(

    /**
     * The value to negate
     */
    value: ValueSpec,
) : AbstractUnaryOperatorSpec(value) {

    /**
     * Generate the logical not
     * @param ctx The context to generate the logical not in
     * @return The generated logical not
     */
    override fun generate(ctx: GenerationContext): String {
        return "!${value.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LogicalNotSpec) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    /**
     * Builder for LogicalNotSpec
     */
    class LogicalNotSpecBuilder
    internal constructor(

        /**
         * The value to negate
         */
        value: ValueSpec? = null,
    ) : AbstractUnaryOperatorSpecBuilder<LogicalNotSpecBuilder>(value) {

        override fun getThis() = this

        /**
         * Build the LogicalNotSpec
         * @return The created LogicalNotSpec
         */
        override fun build(): LogicalNotSpec {
            return LogicalNotSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new LogicalNotSpecBuilder
         * @return The created LogicalNotSpecBuilder
         */
        fun builder() = LogicalNotSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a logical xor
 */
open class LogicalXorSpec(

    /**
     * The left value of the logical xor
     */
    left: ValueSpec,

    /**
     * The right value of the logical xor
     */
    right: ValueSpec,
) : AbstractDualOperatorSpec(left, right) {

    /**
     * Generate the logical xor
     * @param ctx The context to generate the logical xor in
     * @return The generated logical xor
     */
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} ^^ ${right.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LogicalXorSpec) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    /**
     * Builder for LogicalXorSpec
     */
    class LogicalXorSpecBuilder
    internal constructor(

        /**
         * The left value of the logical xor
         */
        left: ValueSpec? = null,

        /**
         * The right value of the logical xor
         */
        right: ValueSpec? = null,
    ) : AbstractDualOperatorSpecBuilder<LogicalXorSpecBuilder>(left, right) {

        override fun getThis() = this

        /**
         * Build the LogicalXorSpec
         * @return The created LogicalXorSpec
         */
        override fun build(): LogicalXorSpec {
            return LogicalXorSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new LogicalXorSpecBuilder
         * @return The created LogicalXorSpecBuilder
         */
        fun builder() = LogicalXorSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a equality comparison
 */
open class EqualitySpec(

    /**
     * The left value of the equality comparison
     */
    left: ValueSpec,

    /**
     * The right value of the equality comparison
     */
    right: ValueSpec,
) : AbstractDualOperatorSpec(left, right) {

    /**
     * Generate the equality comparison
     * @param ctx The context to generate the equality comparison in
     * @return The generated equality comparison
     */
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} == ${right.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is EqualitySpec) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    /**
     * Builder for EqualitySpec
     */
    class EqualitySpecBuilder
    internal constructor(

        /**
         * The left value of the equality comparison
         */
        left: ValueSpec? = null,

        /**
         * The right value of the equality comparison
         */
        right: ValueSpec? = null,
    ) : AbstractDualOperatorSpecBuilder<EqualitySpecBuilder>(left, right) {

        override fun getThis() = this

        /**
         * Build the EqualitySpec
         * @return The created EqualitySpec
         */
        override fun build(): EqualitySpec {
            return EqualitySpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new EqualitySpecBuilder
         * @return The created EqualitySpecBuilder
         */
        fun builder() = EqualitySpecBuilder()
    }
}

/**
 * A ValueSpec that represents an inequality comparison
 */
open class InequalitySpec(

    /**
     * The left value of the inequality comparison
     */
    left: ValueSpec,

    /**
     * The right value of the inequality comparison
     */
    right: ValueSpec,
) : AbstractDualOperatorSpec(left, right) {

    /**
     * Generate the inequality comparison
     * @param ctx The context to generate the inequality comparison in
     * @return The generated inequality comparison
     */
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} != ${right.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is InequalitySpec) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    /**
     * Builder for InequalitySpec
     */
    class InequalitySpecBuilder
    internal constructor(

        /**
         * The left value of the inequality comparison
         */
        left: ValueSpec? = null,

        /**
         * The right value of the inequality comparison
         */
        right: ValueSpec? = null,
    ) : AbstractDualOperatorSpecBuilder<InequalitySpecBuilder>(left, right) {

        override fun getThis() = this

        /**
         * Build the InequalitySpec
         * @return The created InequalitySpec
         */
        override fun build(): InequalitySpec {
            return InequalitySpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new InequalitySpecBuilder
         * @return The created InequalitySpecBuilder
         */
        fun builder() = InequalitySpecBuilder()
    }
}

/**
 * A ValueSpec that represents a greater than comparison
 */
open class GreaterThanSpec(

    /**
     * The left value of the greater than comparison
     */
    left: ValueSpec,

    /**
     * The right value of the greater than comparison
     */
    right: ValueSpec,
) : AbstractDualOperatorSpec(left, right) {

    /**
     * Generate the greater than comparison
     * @param ctx The context to generate the greater than comparison in
     * @return The generated greater than comparison
     */
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} > ${right.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GreaterThanSpec) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    /**
     * Builder for GreaterThanSpec
     */
    class GreaterThanSpecBuilder
    internal constructor(

        /**
         * The left value of the greater than comparison
         */
        left: ValueSpec? = null,

        /**
         * The right value of the greater than comparison
         */
        right: ValueSpec? = null,
    ) : AbstractDualOperatorSpecBuilder<GreaterThanSpecBuilder>(left, right) {

        override fun getThis() = this

        /**
         * Build the GreaterThanSpec
         * @return The created GreaterThanSpec
         */
        override fun build(): GreaterThanSpec {
            return GreaterThanSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new GreaterThanSpecBuilder
         * @return The created GreaterThanSpecBuilder
         */
        fun builder() = GreaterThanSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a greater than or equal comparison
 */
open class GreaterThanOrEqualSpec(

    /**
     * The left value of the greater than or equal comparison
     */
    left: ValueSpec,

    /**
     * The right value of the greater than or equal comparison
     */
    right: ValueSpec,
) : AbstractDualOperatorSpec(left, right) {

    /**
     * Generate the greater than or equal comparison
     * @param ctx The context to generate the greater than or equal comparison in
     * @return The generated greater than or equal comparison
     */
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} >= ${right.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GreaterThanOrEqualSpec) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    /**
     * Builder for GreaterThanOrEqualSpec
     */
    class GreaterThanOrEqualSpecBuilder
    internal constructor(

        /**
         * The left value of the greater than or equal comparison
         */
        left: ValueSpec? = null,

        /**
         * The right value of the greater than or equal comparison
         */
        right: ValueSpec? = null,
    ) : AbstractDualOperatorSpecBuilder<GreaterThanOrEqualSpecBuilder>(left, right) {

        override fun getThis() = this

        /**
         * Build the GreaterThanOrEqualSpec
         * @return The created GreaterThanOrEqualSpec
         */
        override fun build(): GreaterThanOrEqualSpec {
            return GreaterThanOrEqualSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new GreaterThanOrEqualSpecBuilder
         * @return The created GreaterThanOrEqualSpecBuilder
         */
        fun builder() = GreaterThanOrEqualSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a less than comparison
 */
open class LessThanSpec(

    /**
     * The left value of the less than comparison
     */
    left: ValueSpec,

    /**
     * The right value of the less than comparison
     */
    right: ValueSpec,
) : AbstractDualOperatorSpec(left, right) {

    /**
     * Generate the less than comparison
     * @param ctx The context to generate the less than comparison in
     * @return The generated less than comparison
     */
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} < ${right.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LessThanSpec) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    /**
     * Builder for LessThanSpec
     */
    class LessThanSpecBuilder
    internal constructor(

        /**
         * The left value of the less than comparison
         */
        left: ValueSpec? = null,

        /**
         * The right value of the less than comparison
         */
        right: ValueSpec? = null,
    ) : AbstractDualOperatorSpecBuilder<LessThanSpecBuilder>(left, right) {

        override fun getThis() = this

        /**
         * Build the LessThanSpec
         * @return The created LessThanSpec
         */
        override fun build(): LessThanSpec {
            return LessThanSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new LessThanSpecBuilder
         * @return The created LessThanSpecBuilder
         */
        fun builder() = LessThanSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a less than or equal comparison
 */
open class LessThanOrEqualSpec(

    /**
     * The left value of the less than or equal comparison
     */
    left: ValueSpec,

    /**
     * The right value of the less than or equal comparison
     */
    right: ValueSpec,
) : AbstractDualOperatorSpec(left, right) {

    /**
     * Generate the less than or equal comparison
     * @param ctx The context to generate the less than or equal comparison in
     * @return The generated less than or equal comparison
     */
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} <= ${right.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LessThanOrEqualSpec) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    /**
     * Builder for LessThanOrEqualSpec
     */
    class LessThanOrEqualSpecBuilder
    internal constructor(

        /**
         * The left value of the less than or equal comparison
         */
        left: ValueSpec? = null,

        /**
         * The right value of the less than or equal comparison
         */
        right: ValueSpec? = null,
    ) : AbstractDualOperatorSpecBuilder<LessThanOrEqualSpecBuilder>(left, right) {

        override fun getThis() = this

        /**
         * Build the LessThanOrEqualSpec
         * @return The created LessThanOrEqualSpec
         */
        override fun build(): LessThanOrEqualSpec {
            return LessThanOrEqualSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new LessThanOrEqualSpecBuilder
         * @return The created LessThanOrEqualSpecBuilder
         */
        fun builder() = LessThanOrEqualSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a bitwise and
 */
open class BitwiseAndSpec(

    /**
     * The left value of the bitwise and
     */
    left: ValueSpec,

    /**
     * The right value of the bitwise and
     */
    right: ValueSpec,
) : AbstractDualOperatorSpec(left, right) {

    /**
     * Generate the bitwise and
     * @param ctx The context to generate the bitwise and in
     * @return The generated bitwise and
     */
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} & ${right.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BitwiseAndSpec) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    /**
     * Builder for BitwiseAndSpec
     */
    class BitwiseAndSpecBuilder
    internal constructor(

        /**
         * The left value of the bitwise and
         */
        left: ValueSpec? = null,

        /**
         * The right value of the bitwise and
         */
        right: ValueSpec? = null,
    ) : AbstractDualOperatorSpecBuilder<BitwiseAndSpecBuilder>(left, right) {

        override fun getThis() = this

        /**
         * Build the BitwiseAndSpec
         * @return The created BitwiseAndSpec
         */
        override fun build(): BitwiseAndSpec {
            return BitwiseAndSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new BitwiseAndSpecBuilder
         * @return The created BitwiseAndSpecBuilder
         */
        fun builder() = BitwiseAndSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a bitwise or
 */
open class BitwiseOrSpec(

    /**
     * The left value of the bitwise or
     */
    left: ValueSpec,

    /**
     * The right value of the bitwise or
     */
    right: ValueSpec,
) : AbstractDualOperatorSpec(left, right) {

    /**
     * Generate the bitwise or
     * @param ctx The context to generate the bitwise or in
     * @return The generated bitwise or
     */
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} | ${right.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BitwiseOrSpec) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    /**
     * Builder for BitwiseOrSpec
     */
    class BitwiseOrSpecBuilder
    internal constructor(

        /**
         * The left value of the bitwise or
         */
        left: ValueSpec? = null,

        /**
         * The right value of the bitwise or
         */
        right: ValueSpec? = null,
    ) : AbstractDualOperatorSpecBuilder<BitwiseOrSpecBuilder>(left, right) {

        override fun getThis() = this

        /**
         * Build the BitwiseOrSpec
         * @return The created BitwiseOrSpec
         */
        override fun build(): BitwiseOrSpec {
            return BitwiseOrSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new BitwiseOrSpecBuilder
         * @return The created BitwiseOrSpecBuilder
         */
        fun builder() = BitwiseOrSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a bitwise xor
 */
open class BitwiseXorSpec(

    /**
     * The left value of the bitwise xor
     */
    left: ValueSpec,

    /**
     * The right value of the bitwise xor
     */
    right: ValueSpec,
) : AbstractDualOperatorSpec(left, right) {

    /**
     * Generate the bitwise xor
     * @param ctx The context to generate the bitwise xor in
     * @return The generated bitwise xor
     */
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} ^ ${right.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BitwiseXorSpec) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    /**
     * Builder for BitwiseXorSpec
     */
    class BitwiseXorSpecBuilder
    internal constructor(

        /**
         * The left value of the bitwise xor
         */
        left: ValueSpec? = null,

        /**
         * The right value of the bitwise xor
         */
        right: ValueSpec? = null,
    ) : AbstractDualOperatorSpecBuilder<BitwiseXorSpecBuilder>(left, right) {

        override fun getThis() = this

        /**
         * Build the BitwiseXorSpec
         * @return The created BitwiseXorSpec
         */
        override fun build(): BitwiseXorSpec {
            return BitwiseXorSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new BitwiseXorSpecBuilder
         * @return The created BitwiseXorSpecBuilder
         */
        fun builder() = BitwiseXorSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a bitwise not
 */
open class BitwiseNotSpec(

    /**
     * The value to negate
     */
    value: ValueSpec,

) : AbstractUnaryOperatorSpec(value) {

    /**
     * Generate the bitwise not
     * @param ctx The context to generate the bitwise not in
     * @return The generated bitwise not
     */
    override fun generate(ctx: GenerationContext): String {
        return "~${value.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BitwiseNotSpec) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    /**
     * Builder for BitwiseNotSpec
     */
    class BitwiseNotSpecBuilder
    internal constructor(

        /**
         * The value to negate
         */
        value: ValueSpec? = null,
    ) : AbstractUnaryOperatorSpecBuilder<BitwiseNotSpecBuilder>(value) {

        override fun getThis() = this

        /**
         * Build the BitwiseNotSpec
         * @return The created BitwiseNotSpec
         */
        override fun build(): BitwiseNotSpec {
            return BitwiseNotSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new BitwiseNotSpecBuilder
         * @return The created BitwiseNotSpecBuilder
         */
        fun builder() = BitwiseNotSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a left shift
 */
open class LeftShiftSpec(

    /**
     * The left value of the left shift
     */
    left: ValueSpec,

    /**
     * The right value of the left shift
     */
    right: ValueSpec,

) : AbstractDualOperatorSpec(left, right) {

    /**
     * Generate the left shift
     * @param ctx The context to generate the left shift in
     * @return The generated left shift
     */
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} << ${right.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LeftShiftSpec) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    /**
     * Builder for LeftShiftSpec
     */
    class LeftShiftSpecBuilder
    internal constructor(

        /**
         * The left value of the left shift
         */
        left: ValueSpec? = null,

        /**
         * The right value of the left shift
         */
        right: ValueSpec? = null,
    ) : AbstractDualOperatorSpecBuilder<LeftShiftSpecBuilder>(left, right) {

        override fun getThis() = this

        /**
         * Build the LeftShiftSpec
         * @return The created LeftShiftSpec
         */
        override fun build(): LeftShiftSpec {
            return LeftShiftSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new LeftShiftSpecBuilder
         * @return The created LeftShiftSpecBuilder
         */
        fun builder() = LeftShiftSpecBuilder()
    }
}

/**
 * A ValueSpec that represents a right shift
 */
open class RightShiftSpec(

    /**
     * The left value of the right shift
     */
    left: ValueSpec,

    /**
     * The right value of the right shift
     */
    right: ValueSpec,

) : AbstractDualOperatorSpec(left, right) {

    /**
     * Generate the right shift
     * @param ctx The context to generate the right shift in
     * @return The generated right shift
     */
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} >> ${right.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RightShiftSpec) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    /**
     * Builder for RightShiftSpec
     */
    class RightShiftSpecBuilder
    internal constructor(

        /**
         * The left value of the right shift
         */
        left: ValueSpec? = null,

        /**
         * The right value of the right shift
         */
        right: ValueSpec? = null,
    ) : AbstractDualOperatorSpecBuilder<RightShiftSpecBuilder>(left, right) {

        override fun getThis() = this

        /**
         * Build the RightShiftSpec
         * @return The created RightShiftSpec
         */
        override fun build(): RightShiftSpec {
            return RightShiftSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new RightShiftSpecBuilder
         * @return The created RightShiftSpecBuilder
         */
        fun builder() = RightShiftSpecBuilder()
    }
}

/**
 * A ValueSpec that represents an unsigned right shift
 */
open class UnsignedRightShiftSpec(

    /**
     * The left value of the unsigned right shift
     */
    left: ValueSpec,

    /**
     * The right value of the unsigned right shift
     */
    right: ValueSpec,

) : AbstractDualOperatorSpec(left, right) {

    /**
     * Generate the unsigned right shift
     * @param ctx The context to generate the unsigned right shift in
     * @return The generated unsigned right shift
     */
    override fun generate(ctx: GenerationContext): String {
        return "${left.generate(ctx)} >>> ${right.generate(ctx)}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UnsignedRightShiftSpec) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    /**
     * Builder for UnsignedRightShiftSpec
     */
    class UnsignedRightShiftSpecBuilder
    internal constructor(

        /**
         * The left value of the unsigned right shift
         */
        left: ValueSpec? = null,

        /**
         * The right value of the unsigned right shift
         */
        right: ValueSpec? = null,
    ) : AbstractDualOperatorSpecBuilder<UnsignedRightShiftSpecBuilder>(left, right) {

        override fun getThis() = this

        /**
         * Build the UnsignedRightShiftSpec
         * @return The created UnsignedRightShiftSpec
         */
        override fun build(): UnsignedRightShiftSpec {
            return UnsignedRightShiftSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {

        /**
         * Create a new UnsignedRightShiftSpecBuilder
         * @return The created UnsignedRightShiftSpecBuilder
         */
        fun builder() = UnsignedRightShiftSpecBuilder()
    }
}
