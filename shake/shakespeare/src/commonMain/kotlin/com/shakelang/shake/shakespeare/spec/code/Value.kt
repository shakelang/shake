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
import com.shakelang.shake.shakespeare.spec.NamespaceSpec
import com.shakelang.util.parseutils.characters.Characters

interface ValueSpec : AbstractSpec {
    override fun generate(ctx: GenerationContext): String

    companion object {
        fun of(value: String): ValueSpec {
            return object : ValueSpec {
                override fun generate(ctx: GenerationContext): String {
                    return value
                }
            }
        }
    }
}

open class StringLiteralSpec(val value: String) : ValueSpec {

    val escaped = Characters.escapeString(value)

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

    class StringLiteralSpecBuilder
    internal constructor(
        var value: String? = null,
    ) {
        fun value(value: String): StringLiteralSpecBuilder {
            this.value = value
            return this
        }

        fun build(): StringLiteralSpec {
            return StringLiteralSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = StringLiteralSpecBuilder()
    }
}

open class CharacterLiteralSpec(val value: Char) : ValueSpec {

    val escaped = Characters.escapeCharacter(value)

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

    class CharacterLiteralSpecBuilder
    internal constructor(
        var value: Char? = null,
    ) {
        fun value(value: Char): CharacterLiteralSpecBuilder {
            this.value = value
            return this
        }

        fun build(): CharacterLiteralSpec {
            return CharacterLiteralSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = CharacterLiteralSpecBuilder()
    }
}

open class IntLiteralSpec(val value: Long) : ValueSpec {

    constructor(value: Int) : this(value.toLong())

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

    class NumberLiteralSpecBuilder
    internal constructor(
        var value: Long? = null,
    ) {

        fun value(value: Long): NumberLiteralSpecBuilder {
            this.value = value
            return this
        }

        fun value(value: Int): NumberLiteralSpecBuilder {
            this.value = value.toLong()
            return this
        }

        fun value(value: Short): NumberLiteralSpecBuilder {
            this.value = value.toLong()
            return this
        }

        fun value(value: Byte): NumberLiteralSpecBuilder {
            this.value = value.toLong()
            return this
        }

        fun build(): IntLiteralSpec {
            return IntLiteralSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = NumberLiteralSpecBuilder()
    }
}

open class FloatLiteralSpec(val value: Double) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        return value.toString()
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

    class FloatLiteralSpecBuilder
    internal constructor(
        var value: Double? = null,
    ) {

        fun value(value: Float): FloatLiteralSpecBuilder {
            this.value = value.toDouble()
            return this
        }

        fun value(value: Double): FloatLiteralSpecBuilder {
            this.value = value
            return this
        }

        fun build(): FloatLiteralSpec {
            return FloatLiteralSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = FloatLiteralSpecBuilder()
    }
}

open class BooleanLiteralSpec(val value: Boolean) : ValueSpec {
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

    class BooleanLiteralSpecBuilder
    internal constructor(
        var value: Boolean? = null,
    ) {

        fun value(value: Boolean): BooleanLiteralSpecBuilder {
            this.value = value
            return this
        }

        fun build(): BooleanLiteralSpec {
            return BooleanLiteralSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = BooleanLiteralSpecBuilder()
    }
}

open class NullLiteralSpec : ValueSpec {
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
        val INSTANCE = NullLiteralSpec()
    }
}

open class VariableReferenceSpec(open val name: NamespaceSpec) : ValueSpec {

    constructor(vararg name: String) : this(NamespaceSpec(*name))

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

    class VariableReferenceSpecBuilder
    internal constructor(
        var name: NamespaceSpec? = null,
    ) {

        fun name(name: NamespaceSpec): VariableReferenceSpecBuilder {
            this.name = name
            return this
        }

        fun name(name: String): VariableReferenceSpecBuilder {
            if (this.name != null) {
                this.name = NamespaceSpec(*this.name!!.name, name)
            } else {
                this.name = NamespaceSpec(name)
            }
            return this
        }

        fun build(): VariableReferenceSpec {
            return VariableReferenceSpec(
                name ?: throw IllegalStateException("Name not set"),
            )
        }
    }

    companion object {
        fun builder() = VariableReferenceSpecBuilder()
    }
}

open class AdditionSpec(open val left: ValueSpec, open val right: ValueSpec) : ValueSpec {
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

    class AdditionSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): AdditionSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): AdditionSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): AdditionSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): AdditionSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): AdditionSpec {
            return AdditionSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = AdditionSpecBuilder()
    }
}

open class SubtractionSpec(open val left: ValueSpec, open val right: ValueSpec) : ValueSpec {
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

    class SubtractionSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): SubtractionSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): SubtractionSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): SubtractionSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): SubtractionSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): SubtractionSpec {
            return SubtractionSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = SubtractionSpecBuilder()
    }
}

open class MultiplicationSpec(open val left: ValueSpec, open val right: ValueSpec) : ValueSpec {
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

    class MultiplicationSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): MultiplicationSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): MultiplicationSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): MultiplicationSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): MultiplicationSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): MultiplicationSpec {
            return MultiplicationSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = MultiplicationSpecBuilder()
    }
}

open class DivisionSpec(open val left: ValueSpec, open val right: ValueSpec) : ValueSpec {
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

    class DivisionSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): DivisionSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): DivisionSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): DivisionSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): DivisionSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): DivisionSpec {
            return DivisionSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = DivisionSpecBuilder()
    }
}

open class ModuloSpec(open val left: ValueSpec, open val right: ValueSpec) : ValueSpec {
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

    class ModuloSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): ModuloSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): ModuloSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): ModuloSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): ModuloSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): ModuloSpec {
            return ModuloSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = ModuloSpecBuilder()
    }
}

open class PowerSpec(open val left: ValueSpec, open val right: ValueSpec) : ValueSpec {
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

    class PowerSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): PowerSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): PowerSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): PowerSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): PowerSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): PowerSpec {
            return PowerSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = PowerSpecBuilder()
    }
}

open class UnaryMinusSpec(open val value: ValueSpec) : ValueSpec {
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

    class UnaryMinusSpecBuilder
    internal constructor(
        var value: ValueSpec? = null,
    ) {

        fun value(value: ValueSpec): UnaryMinusSpecBuilder {
            this.value = value
            return this
        }

        fun value(value: String): UnaryMinusSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        fun build(): UnaryMinusSpec {
            return UnaryMinusSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = UnaryMinusSpecBuilder()
    }
}

typealias NegationSpec = UnaryMinusSpec

open class UnaryPlusSpec(open val value: ValueSpec) : ValueSpec {
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

    class UnaryPlusSpecBuilder
    internal constructor(
        var value: ValueSpec? = null,
    ) {

        fun value(value: ValueSpec): UnaryPlusSpecBuilder {
            this.value = value
            return this
        }

        fun value(value: String): UnaryPlusSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        fun build(): UnaryPlusSpec {
            return UnaryPlusSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = UnaryPlusSpecBuilder()
    }
}

open class LogicalAndSpec(open val left: ValueSpec, open val right: ValueSpec) : ValueSpec {
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

    class LogicalAndSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): LogicalAndSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): LogicalAndSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): LogicalAndSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): LogicalAndSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): LogicalAndSpec {
            return LogicalAndSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = LogicalAndSpecBuilder()
    }
}

open class LogicalOrSpec(open val left: ValueSpec, open val right: ValueSpec) : ValueSpec {
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

    class LogicalOrSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): LogicalOrSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): LogicalOrSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): LogicalOrSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): LogicalOrSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): LogicalOrSpec {
            return LogicalOrSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = LogicalOrSpecBuilder()
    }
}

open class LogicalNotSpec(open val value: ValueSpec) : ValueSpec {
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

    class LogicalNotSpecBuilder
    internal constructor(
        var value: ValueSpec? = null,
    ) {

        fun value(value: ValueSpec): LogicalNotSpecBuilder {
            this.value = value
            return this
        }

        fun value(value: String): LogicalNotSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        fun build(): LogicalNotSpec {
            return LogicalNotSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = LogicalNotSpecBuilder()
    }
}

open class LogicalXorSpec(open val left: ValueSpec, open val right: ValueSpec) : ValueSpec {
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

    class LogicalXorSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): LogicalXorSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): LogicalXorSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): LogicalXorSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): LogicalXorSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): LogicalXorSpec {
            return LogicalXorSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = LogicalXorSpecBuilder()
    }
}

open class EqualitySpec(open val left: ValueSpec, open val right: ValueSpec) : ValueSpec {
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

    class EqualitySpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): EqualitySpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): EqualitySpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): EqualitySpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): EqualitySpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): EqualitySpec {
            return EqualitySpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = EqualitySpecBuilder()
    }
}

open class InequalitySpec(open val left: ValueSpec, open val right: ValueSpec) : ValueSpec {
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

    class InequalitySpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): InequalitySpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): InequalitySpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): InequalitySpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): InequalitySpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): InequalitySpec {
            return InequalitySpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = InequalitySpecBuilder()
    }
}

open class GreaterThanSpec(open val left: ValueSpec, open val right: ValueSpec) : ValueSpec {
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

    class GreaterThanSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): GreaterThanSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): GreaterThanSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): GreaterThanSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): GreaterThanSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): GreaterThanSpec {
            return GreaterThanSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = GreaterThanSpecBuilder()
    }
}

open class GreaterThanOrEqualSpec(open val left: ValueSpec, open val right: ValueSpec) : ValueSpec {
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

    class GreaterThanOrEqualSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): GreaterThanOrEqualSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): GreaterThanOrEqualSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): GreaterThanOrEqualSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): GreaterThanOrEqualSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): GreaterThanOrEqualSpec {
            return GreaterThanOrEqualSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = GreaterThanOrEqualSpecBuilder()
    }
}

open class LessThanSpec(open val left: ValueSpec, open val right: ValueSpec) : ValueSpec {
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

    class LessThanSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): LessThanSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): LessThanSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): LessThanSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): LessThanSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): LessThanSpec {
            return LessThanSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = LessThanSpecBuilder()
    }
}

open class LessThanOrEqualSpec(open val left: ValueSpec, open val right: ValueSpec) : ValueSpec {
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

    class LessThanOrEqualSpecBuilder
    internal constructor(
        var left: ValueSpec? = null,
        var right: ValueSpec? = null,
    ) {

        fun left(left: ValueSpec): LessThanOrEqualSpecBuilder {
            this.left = left
            return this
        }

        fun left(left: String): LessThanOrEqualSpecBuilder {
            this.left = ValueSpec.of(left)
            return this
        }

        fun right(right: ValueSpec): LessThanOrEqualSpecBuilder {
            this.right = right
            return this
        }

        fun right(right: String): LessThanOrEqualSpecBuilder {
            this.right = ValueSpec.of(right)
            return this
        }

        fun build(): LessThanOrEqualSpec {
            return LessThanOrEqualSpec(
                left ?: throw IllegalStateException("Left not set"),
                right ?: throw IllegalStateException("Right not set"),
            )
        }
    }

    companion object {
        fun builder() = LessThanOrEqualSpecBuilder()
    }
}
