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
    class NumberLiteralSpecBuilder
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
        fun value(value: Long): NumberLiteralSpecBuilder {
            this.value = value
            return this
        }

        /**
         * Set the value of the integer
         * @param value The value of the integer
         * @return The builder
         */
        fun value(value: Int): NumberLiteralSpecBuilder {
            this.value = value.toLong()
            return this
        }

        /**
         * Set the value of the integer
         * @param value The value of the integer
         * @return The builder
         */
        fun value(value: Short): NumberLiteralSpecBuilder {
            this.value = value.toLong()
            return this
        }

        /**
         * Set the value of the integer
         * @param value The value of the integer
         * @return The builder
         */
        fun value(value: Byte): NumberLiteralSpecBuilder {
            this.value = value.toLong()
            return this
        }

        /**
         * Set the value of the integer
         * @param value The value of the integer
         * @return The builder
         */
        fun value(value: ULong): NumberLiteralSpecBuilder {
            this.value = value.toLong()
            return this
        }

        /**
         * Set the value of the integer
         * @param value The value of the integer
         * @return The builder
         */
        fun value(value: UInt): NumberLiteralSpecBuilder {
            this.value = value.toLong()
            return this
        }

        /**
         * Set the value of the integer
         * @param value The value of the integer
         * @return The builder
         */
        fun value(value: UShort): NumberLiteralSpecBuilder {
            this.value = value.toLong()
            return this
        }

        /**
         * Set the value of the integer
         * @param value The value of the integer
         * @return The builder
         */
        fun value(value: UByte): NumberLiteralSpecBuilder {
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
        fun builder() = NumberLiteralSpecBuilder()
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

/**
 * A ValueSpec that represents a calculation
 */
open class AdditionSpec(
    /**
     * The left value of the addition
     */
    open val left: ValueSpec,

    /**
     * The right value of the addition
     */
    open val right: ValueSpec,
) : ValueSpec {

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
        var left: ValueSpec? = null,

        /**
         * The right value of the addition
         */
        var right: ValueSpec? = null,
    ) {

        /**
         * Set the left value of the addition
         * @param left The left value of the addition
         * @return The builder
         */
        fun left(left: ValueSpec): AdditionSpecBuilder {
            this.left = left
            return this
        }

        /**
         * Set the left value of the addition
         * @param left The left value of the addition
         * @return The builder
         */
        fun left(left: String): AdditionSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        /**
         * Set the right value of the addition
         * @param right The right value of the addition
         * @return The builder
         */
        fun right(right: ValueSpec): AdditionSpecBuilder {
            this.right = right
            return this
        }

        /**
         * Set the right value of the addition
         * @param right The right value of the addition
         * @return The builder
         */
        fun right(right: String): AdditionSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        /**
         * Build the AdditionSpec
         * @return The created AdditionSpec
         */
        fun build(): AdditionSpec {
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
    open val left: ValueSpec,

/**
     * The right value of the subtraction
     */
    open val right: ValueSpec,
) : ValueSpec {

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
        var left: ValueSpec? = null,

        /**
         * The right value of the subtraction
         */
        var right: ValueSpec? = null,
    ) {

        /**
         * Set the left value of the subtraction
         * @param left The left value of the subtraction
         * @return The builder
         */
        fun left(left: ValueSpec): SubtractionSpecBuilder {
            this.left = left
            return this
        }

        /**
         * Set the left value of the subtraction
         * @param left The left value of the subtraction
         * @return The builder
         */
        fun left(left: String): SubtractionSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        /**
         * Set the right value of the subtraction
         * @param right The right value of the subtraction
         * @return The builder
         */
        fun right(right: ValueSpec): SubtractionSpecBuilder {
            this.right = right
            return this
        }

        /**
         * Set the right value of the subtraction
         * @param right The right value of the subtraction
         * @return The builder
         */
        fun right(right: String): SubtractionSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        /**
         * Build the SubtractionSpec
         * @return The created SubtractionSpec
         */
        fun build(): SubtractionSpec {
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
    open val left: ValueSpec,

    /**
     * The right value of the multiplication
     */
    open val right: ValueSpec,
) : ValueSpec {

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
        var left: ValueSpec? = null,

        /**
         * The right value of the multiplication
         */
        var right: ValueSpec? = null,
    ) {

        /**
         * Set the left value of the multiplication
         * @param left The left value of the multiplication
         * @return The builder
         */
        fun left(left: ValueSpec): MultiplicationSpecBuilder {
            this.left = left
            return this
        }

        /**
         * Set the left value of the multiplication
         * @param left The left value of the multiplication
         * @return The builder
         */
        fun left(left: String): MultiplicationSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        /**
         * Set the right value of the multiplication
         * @param right The right value of the multiplication
         * @return The builder
         */
        fun right(right: ValueSpec): MultiplicationSpecBuilder {
            this.right = right
            return this
        }

        /**
         * Set the right value of the multiplication
         * @param right The right value of the multiplication
         * @return The builder
         */
        fun right(right: String): MultiplicationSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        /**
         * Build the MultiplicationSpec
         * @return The created MultiplicationSpec
         */
        fun build(): MultiplicationSpec {
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
    open val left: ValueSpec,

    /**
     * The right value of the division
     */
    open val right: ValueSpec,
) : ValueSpec {

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
        var left: ValueSpec? = null,

        /**
         * The right value of the division
         */
        var right: ValueSpec? = null,
    ) {

        /**
         * Set the left value of the division
         * @param left The left value of the division
         * @return The builder
         */
        fun left(left: ValueSpec): DivisionSpecBuilder {
            this.left = left
            return this
        }

        /**
         * Set the left value of the division
         * @param left The left value of the division
         * @return The builder
         */
        fun left(left: String): DivisionSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        /**
         * Set the right value of the division
         * @param right The right value of the division
         * @return The builder
         */
        fun right(right: ValueSpec): DivisionSpecBuilder {
            this.right = right
            return this
        }

        /**
         * Set the right value of the division
         * @param right The right value of the division
         * @return The builder
         */
        fun right(right: String): DivisionSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        /**
         * Build the DivisionSpec
         * @return The created DivisionSpec
         */
        fun build(): DivisionSpec {
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
    open val left: ValueSpec,

    /**
     * The right value of the modulo
     */
    open val right: ValueSpec,
) : ValueSpec {

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
        var left: ValueSpec? = null,

        /**
         * The right value of the modulo
         */
        var right: ValueSpec? = null,
    ) {

        /**
         * Set the left value of the modulo
         * @param left The left value of the modulo
         * @return The builder
         */
        fun left(left: ValueSpec): ModuloSpecBuilder {
            this.left = left
            return this
        }

        /**
         * Set the left value of the modulo
         * @param left The left value of the modulo
         * @return The builder
         */
        fun left(left: String): ModuloSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        /**
         * Set the right value of the modulo
         * @param right The right value of the modulo
         * @return The builder
         */
        fun right(right: ValueSpec): ModuloSpecBuilder {
            this.right = right
            return this
        }

        /**
         * Set the right value of the modulo
         * @param right The right value of the modulo
         * @return The builder
         */
        fun right(right: String): ModuloSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        /**
         * Build the ModuloSpec
         * @return The created ModuloSpec
         */
        fun build(): ModuloSpec {
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
    open val left: ValueSpec,

    /**
     * The right value of the power
     */
    open val right: ValueSpec,
) : ValueSpec {

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
        var left: ValueSpec? = null,

        /**
         * The right value of the power
         */
        var right: ValueSpec? = null,
    ) {

        /**
         * Set the left value of the power
         * @param left The left value of the power
         * @return The builder
         */
        fun left(left: ValueSpec): PowerSpecBuilder {
            this.left = left
            return this
        }

        /**
         * Set the left value of the power
         * @param left The left value of the power
         * @return The builder
         */
        fun left(left: String): PowerSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        /**
         * Set the right value of the power
         * @param right The right value of the power
         * @return The builder
         */
        fun right(right: ValueSpec): PowerSpecBuilder {
            this.right = right
            return this
        }

        /**
         * Set the right value of the power
         * @param right The right value of the power
         * @return The builder
         */
        fun right(right: String): PowerSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        /**
         * Build the PowerSpec
         * @return The created PowerSpec
         */
        fun build(): PowerSpec {
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
    open val value: ValueSpec,
) : ValueSpec {

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
        var value: ValueSpec? = null,
    ) {

        /**
         * Set the value to negate
         * @param value The value to negate
         * @return The builder
         */
        fun value(value: ValueSpec): UnaryMinusSpecBuilder {
            this.value = value
            return this
        }

        /**
         * Set the value to negate
         * @param value The value to negate
         * @return The builder
         */
        fun value(value: String): UnaryMinusSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        /**
         * Build the UnaryMinusSpec
         * @return The created UnaryMinusSpec
         */
        fun build(): UnaryMinusSpec {
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
typealias NegationSpec = UnaryMinusSpec

/**
 * A ValueSpec that represents a unary plus
 */
open class UnaryPlusSpec(

    /**
     * The value to negate
     */
    open val value: ValueSpec,
) : ValueSpec {

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
        var value: ValueSpec? = null,
    ) {

        /**
         * Set the value to negate
         * @param value The value to negate
         * @return The builder
         */
        fun value(value: ValueSpec): UnaryPlusSpecBuilder {
            this.value = value
            return this
        }

        /**
         * Set the value to negate
         * @param value The value to negate
         * @return The builder
         */
        fun value(value: String): UnaryPlusSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        /**
         * Build the UnaryPlusSpec
         * @return The created UnaryPlusSpec
         */
        fun build(): UnaryPlusSpec {
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
    open val left: ValueSpec,

    /**
     * The right value of the logical and
     */
    open val right: ValueSpec,
) : ValueSpec {

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
        var left: ValueSpec? = null,

        /**
         * The right value of the logical and
         */
        var right: ValueSpec? = null,
    ) {

        /**
         * Set the left value of the logical and
         * @param left The left value of the logical and
         * @return The builder
         */
        fun left(left: ValueSpec): LogicalAndSpecBuilder {
            this.left = left
            return this
        }

        /**
         * Set the left value of the logical and
         * @param left The left value of the logical and
         * @return The builder
         */
        fun left(left: String): LogicalAndSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        /**
         * Set the right value of the logical and
         * @param right The right value of the logical and
         * @return The builder
         */
        fun right(right: ValueSpec): LogicalAndSpecBuilder {
            this.right = right
            return this
        }

        /**
         * Set the right value of the logical and
         * @param right The right value of the logical and
         * @return The builder
         */
        fun right(right: String): LogicalAndSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        /**
         * Build the LogicalAndSpec
         * @return The created LogicalAndSpec
         */
        fun build(): LogicalAndSpec {
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
    open val left: ValueSpec,

    /**
     * The right value of the logical or
     */
    open val right: ValueSpec,
) : ValueSpec {

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
        var left: ValueSpec? = null,

        /**
         * The right value of the logical or
         */
        var right: ValueSpec? = null,
    ) {

        /**
         * Set the left value of the logical or
         * @param left The left value of the logical or
         * @return The builder
         */
        fun left(left: ValueSpec): LogicalOrSpecBuilder {
            this.left = left
            return this
        }

        /**
         * Set the left value of the logical or
         * @param left The left value of the logical or
         * @return The builder
         */
        fun left(left: String): LogicalOrSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        /**
         * Set the right value of the logical or
         * @param right The right value of the logical or
         * @return The builder
         */
        fun right(right: ValueSpec): LogicalOrSpecBuilder {
            this.right = right
            return this
        }

        /**
         * Set the right value of the logical or
         * @param right The right value of the logical or
         * @return The builder
         */
        fun right(right: String): LogicalOrSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        /**
         * Build the LogicalOrSpec
         * @return The created LogicalOrSpec
         */
        fun build(): LogicalOrSpec {
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
    open val value: ValueSpec,
) : ValueSpec {

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
        var value: ValueSpec? = null,
    ) {

        /**
         * Set the value to negate
         * @param value The value to negate
         * @return The builder
         */
        fun value(value: ValueSpec): LogicalNotSpecBuilder {
            this.value = value
            return this
        }

        /**
         * Set the value to negate
         * @param value The value to negate
         * @return The builder
         */
        fun value(value: String): LogicalNotSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        /**
         * Build the LogicalNotSpec
         * @return The created LogicalNotSpec
         */
        fun build(): LogicalNotSpec {
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
    open val left: ValueSpec,

    /**
     * The right value of the logical xor
     */
    open val right: ValueSpec,
) : ValueSpec {

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
        var left: ValueSpec? = null,

        /**
         * The right value of the logical xor
         */
        var right: ValueSpec? = null,
    ) {

        /**
         * Set the left value of the logical xor
         * @param left The left value of the logical xor
         * @return The builder
         */
        fun left(left: ValueSpec): LogicalXorSpecBuilder {
            this.left = left
            return this
        }

        /**
         * Set the left value of the logical xor
         * @param left The left value of the logical xor
         * @return The builder
         */
        fun left(left: String): LogicalXorSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        /**
         * Set the right value of the logical xor
         * @param right The right value of the logical xor
         * @return The builder
         */
        fun right(right: ValueSpec): LogicalXorSpecBuilder {
            this.right = right
            return this
        }

        /**
         * Set the right value of the logical xor
         * @param right The right value of the logical xor
         * @return The builder
         */
        fun right(right: String): LogicalXorSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        /**
         * Build the LogicalXorSpec
         * @return The created LogicalXorSpec
         */
        fun build(): LogicalXorSpec {
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
    open val left: ValueSpec,

    /**
     * The right value of the equality comparison
     */
    open val right: ValueSpec,
) : ValueSpec {

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
        var left: ValueSpec? = null,

        /**
         * The right value of the equality comparison
         */
        var right: ValueSpec? = null,
    ) {

        /**
         * Set the left value of the equality comparison
         * @param left The left value of the equality comparison
         * @return The builder
         */
        fun left(left: ValueSpec): EqualitySpecBuilder {
            this.left = left
            return this
        }

        /**
         * Set the left value of the equality comparison
         * @param left The left value of the equality comparison
         * @return The builder
         */
        fun left(left: String): EqualitySpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        /**
         * Set the right value of the equality comparison
         * @param right The right value of the equality comparison
         * @return The builder
         */
        fun right(right: ValueSpec): EqualitySpecBuilder {
            this.right = right
            return this
        }

        /**
         * Set the right value of the equality comparison
         * @param right The right value of the equality comparison
         * @return The builder
         */
        fun right(right: String): EqualitySpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        /**
         * Build the EqualitySpec
         * @return The created EqualitySpec
         */
        fun build(): EqualitySpec {
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
    open val left: ValueSpec,

    /**
     * The right value of the inequality comparison
     */
    open val right: ValueSpec,
) : ValueSpec {

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
        var left: ValueSpec? = null,

        /**
         * The right value of the inequality comparison
         */
        var right: ValueSpec? = null,
    ) {

        /**
         * Set the left value of the inequality comparison
         * @param left The left value of the inequality comparison
         * @return The builder
         */
        fun left(left: ValueSpec): InequalitySpecBuilder {
            this.left = left
            return this
        }

        /**
         * Set the left value of the inequality comparison
         * @param left The left value of the inequality comparison
         * @return The builder
         */
        fun left(left: String): InequalitySpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        /**
         * Set the right value of the inequality comparison
         * @param right The right value of the inequality comparison
         * @return The builder
         */
        fun right(right: ValueSpec): InequalitySpecBuilder {
            this.right = right
            return this
        }

        /**
         * Set the right value of the inequality comparison
         * @param right The right value of the inequality comparison
         * @return The builder
         */
        fun right(right: String): InequalitySpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        /**
         * Build the InequalitySpec
         * @return The created InequalitySpec
         */
        fun build(): InequalitySpec {
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
    open val left: ValueSpec,

    /**
     * The right value of the greater than comparison
     */
    open val right: ValueSpec,
) : ValueSpec {

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
        var left: ValueSpec? = null,

        /**
         * The right value of the greater than comparison
         */
        var right: ValueSpec? = null,
    ) {

        /**
         * Set the left value of the greater than comparison
         * @param left The left value of the greater than comparison
         * @return The builder
         */
        fun left(left: ValueSpec): GreaterThanSpecBuilder {
            this.left = left
            return this
        }

        /**
         * Set the left value of the greater than comparison
         * @param left The left value of the greater than comparison
         * @return The builder
         */
        fun left(left: String): GreaterThanSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        /**
         * Set the right value of the greater than comparison
         * @param right The right value of the greater than comparison
         * @return The builder
         */
        fun right(right: ValueSpec): GreaterThanSpecBuilder {
            this.right = right
            return this
        }

        /**
         * Set the right value of the greater than comparison
         * @param right The right value of the greater than comparison
         * @return The builder
         */
        fun right(right: String): GreaterThanSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        /**
         * Build the GreaterThanSpec
         * @return The created GreaterThanSpec
         */
        fun build(): GreaterThanSpec {
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
    open val left: ValueSpec,

/**
     * The right value of the greater than or equal comparison
     */
    open val right: ValueSpec,
) : ValueSpec {

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
        var left: ValueSpec? = null,

/**
         * The right value of the greater than or equal comparison
         */
        var right: ValueSpec? = null,
    ) {

        /**
         * Set the left value of the greater than or equal comparison
         * @param left The left value of the greater than or equal comparison
         * @return The builder
         */
        fun left(left: ValueSpec): GreaterThanOrEqualSpecBuilder {
            this.left = left
            return this
        }

        /**
         * Set the left value of the greater than or equal comparison
         * @param left The left value of the greater than or equal comparison
         * @return The builder
         */
        fun left(left: String): GreaterThanOrEqualSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        /**
         * Set the right value of the greater than or equal comparison
         * @param right The right value of the greater than or equal comparison
         * @return The builder
         */
        fun right(right: ValueSpec): GreaterThanOrEqualSpecBuilder {
            this.right = right
            return this
        }

        /**
         * Set the right value of the greater than or equal comparison
         * @param right The right value of the greater than or equal comparison
         * @return The builder
         */
        fun right(right: String): GreaterThanOrEqualSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        /**
         * Build the GreaterThanOrEqualSpec
         * @return The created GreaterThanOrEqualSpec
         */
        fun build(): GreaterThanOrEqualSpec {
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
    open val left: ValueSpec,

    /**
     * The right value of the less than comparison
     */
    open val right: ValueSpec,
) : ValueSpec {

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
        var left: ValueSpec? = null,

        /**
         * The right value of the less than comparison
         */
        var right: ValueSpec? = null,
    ) {

        /**
         * Set the left value of the less than comparison
         * @param left The left value of the less than comparison
         * @return The builder
         */
        fun left(left: ValueSpec): LessThanSpecBuilder {
            this.left = left
            return this
        }

        /**
         * Set the left value of the less than comparison
         * @param left The left value of the less than comparison
         * @return The builder
         */
        fun left(left: String): LessThanSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        /**
         * Set the right value of the less than comparison
         * @param right The right value of the less than comparison
         * @return The builder
         */
        fun right(right: ValueSpec): LessThanSpecBuilder {
            this.right = right
            return this
        }

        /**
         * Set the right value of the less than comparison
         * @param right The right value of the less than comparison
         * @return The builder
         */
        fun right(right: String): LessThanSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        /**
         * Build the LessThanSpec
         * @return The created LessThanSpec
         */
        fun build(): LessThanSpec {
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
    open val left: ValueSpec,

    /**
     * The right value of the less than or equal comparison
     */
    open val right: ValueSpec,
) : ValueSpec {

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
        var left: ValueSpec? = null,

        /**
         * The right value of the less than or equal comparison
         */
        var right: ValueSpec? = null,
    ) {

        /**
         * Set the left value of the less than or equal comparison
         * @param left The left value of the less than or equal comparison
         * @return The builder
         */
        fun left(left: ValueSpec): LessThanOrEqualSpecBuilder {
            this.left = left
            return this
        }

        /**
         * Set the left value of the less than or equal comparison
         * @param left The left value of the less than or equal comparison
         * @return The builder
         */
        fun left(left: String): LessThanOrEqualSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        /**
         * Set the right value of the less than or equal comparison
         * @param right The right value of the less than or equal comparison
         * @return The builder
         */
        fun right(right: ValueSpec): LessThanOrEqualSpecBuilder {
            this.right = right
            return this
        }

        /**
         * Set the right value of the less than or equal comparison
         * @param right The right value of the less than or equal comparison
         * @return The builder
         */
        fun right(right: String): LessThanOrEqualSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        /**
         * Build the LessThanOrEqualSpec
         * @return The created LessThanOrEqualSpec
         */
        fun build(): LessThanOrEqualSpec {
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
