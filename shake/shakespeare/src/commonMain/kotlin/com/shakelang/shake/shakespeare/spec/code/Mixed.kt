@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.spec.code

import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.Identifier

interface ValuedAssignmentSpec : StatementSpec, ValueSpec

class VariableAssignmentSpec(
    val name: Identifier,
    val value: ValueSpec,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name = $value"
    }

    open class VariableAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: ValueSpec? = null,
    ) {

        fun name(name: Identifier): VariableAssignmentSpecBuilder {
            this.name = name
            return this
        }

        fun name(name: String): VariableAssignmentSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        fun value(value: ValueSpec): VariableAssignmentSpecBuilder {
            this.value = value
            return this
        }

        fun value(value: String): VariableAssignmentSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        fun build(): VariableAssignmentSpec {
            return VariableAssignmentSpec(
                name ?: throw IllegalStateException("Name not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = VariableAssignmentSpecBuilder()
    }
}

class VariableAdditionAssignmentSpec(
    val name: Identifier,
    val value: ValueSpec,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name += $value"
    }

    open class VariableAdditionAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: ValueSpec? = null,
    ) {

        fun name(name: Identifier): VariableAdditionAssignmentSpecBuilder {
            this.name = name
            return this
        }

        fun name(name: String): VariableAdditionAssignmentSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        fun value(value: ValueSpec): VariableAdditionAssignmentSpecBuilder {
            this.value = value
            return this
        }

        fun value(value: String): VariableAdditionAssignmentSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        fun build(): VariableAdditionAssignmentSpec {
            return VariableAdditionAssignmentSpec(
                name ?: throw IllegalStateException("Name not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = VariableAdditionAssignmentSpecBuilder()
    }
}

class VariableSubtractionAssignmentSpec(
    val name: Identifier,
    val value: ValueSpec,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name -= $value"
    }

    open class VariableSubtractionAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: ValueSpec? = null,
    ) {

        fun name(name: Identifier): VariableSubtractionAssignmentSpecBuilder {
            this.name = name
            return this
        }

        fun name(name: String): VariableSubtractionAssignmentSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        fun value(value: ValueSpec): VariableSubtractionAssignmentSpecBuilder {
            this.value = value
            return this
        }

        fun value(value: String): VariableSubtractionAssignmentSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        fun build(): VariableSubtractionAssignmentSpec {
            return VariableSubtractionAssignmentSpec(
                name ?: throw IllegalStateException("Name not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = VariableSubtractionAssignmentSpecBuilder()
    }
}

class VariableMultiplicationAssignmentSpec(
    val name: Identifier,
    val value: ValueSpec,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name *= $value"
    }

    open class VariableMultiplicationAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: ValueSpec? = null,
    ) {

        fun name(name: Identifier): VariableMultiplicationAssignmentSpecBuilder {
            this.name = name
            return this
        }

        fun name(name: String): VariableMultiplicationAssignmentSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        fun value(value: ValueSpec): VariableMultiplicationAssignmentSpecBuilder {
            this.value = value
            return this
        }

        fun value(value: String): VariableMultiplicationAssignmentSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        fun build(): VariableMultiplicationAssignmentSpec {
            return VariableMultiplicationAssignmentSpec(
                name ?: throw IllegalStateException("Name not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = VariableMultiplicationAssignmentSpecBuilder()
    }
}

class VariableDivisionAssignmentSpec(
    val name: Identifier,
    val value: ValueSpec,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name /= $value"
    }

    open class VariableDivisionAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: ValueSpec? = null,
    ) {

        fun name(name: Identifier): VariableDivisionAssignmentSpecBuilder {
            this.name = name
            return this
        }

        fun name(name: String): VariableDivisionAssignmentSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        fun value(value: ValueSpec): VariableDivisionAssignmentSpecBuilder {
            this.value = value
            return this
        }

        fun value(value: String): VariableDivisionAssignmentSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        fun build(): VariableDivisionAssignmentSpec {
            return VariableDivisionAssignmentSpec(
                name ?: throw IllegalStateException("Name not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = VariableDivisionAssignmentSpecBuilder()
    }
}

class VariableModuloAssignmentSpec(
    val name: Identifier,
    val value: ValueSpec,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name %= $value"
    }

    open class VariableModuloAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: ValueSpec? = null,
    ) {

        fun name(name: Identifier): VariableModuloAssignmentSpecBuilder {
            this.name = name
            return this
        }

        fun name(name: String): VariableModuloAssignmentSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        fun value(value: ValueSpec): VariableModuloAssignmentSpecBuilder {
            this.value = value
            return this
        }

        fun value(value: String): VariableModuloAssignmentSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        fun build(): VariableModuloAssignmentSpec {
            return VariableModuloAssignmentSpec(
                name ?: throw IllegalStateException("Name not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = VariableModuloAssignmentSpecBuilder()
    }
}

class VariablePowerAssignmentSpec(
    val name: Identifier,
    val value: ValueSpec,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name **= $value"
    }

    class VariablePowerAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: ValueSpec? = null,
    ) {

        fun name(name: Identifier): VariablePowerAssignmentSpecBuilder {
            this.name = name
            return this
        }

        fun name(name: String): VariablePowerAssignmentSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        fun value(value: ValueSpec): VariablePowerAssignmentSpecBuilder {
            this.value = value
            return this
        }

        fun value(value: String): VariablePowerAssignmentSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        fun build(): VariablePowerAssignmentSpec {
            return VariablePowerAssignmentSpec(
                name ?: throw IllegalStateException("Name not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = VariablePowerAssignmentSpecBuilder()
    }
}

class VariableBitwiseAndAssignmentSpec(
    val name: Identifier,
    val value: ValueSpec,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name &= $value"
    }

    class VariableBitwiseAndAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: ValueSpec? = null,
    ) {

        fun name(name: Identifier): VariableBitwiseAndAssignmentSpecBuilder {
            this.name = name
            return this
        }

        fun name(name: String): VariableBitwiseAndAssignmentSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        fun value(value: ValueSpec): VariableBitwiseAndAssignmentSpecBuilder {
            this.value = value
            return this
        }

        fun value(value: String): VariableBitwiseAndAssignmentSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        fun build(): VariableBitwiseAndAssignmentSpec {
            return VariableBitwiseAndAssignmentSpec(
                name ?: throw IllegalStateException("Name not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = VariableBitwiseAndAssignmentSpecBuilder()
    }
}

class VariableBitwiseOrAssignmentSpec(
    val name: Identifier,
    val value: ValueSpec,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name |= $value"
    }

    class VariableBitwiseOrAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: ValueSpec? = null,
    ) {

        fun name(name: Identifier): VariableBitwiseOrAssignmentSpecBuilder {
            this.name = name
            return this
        }

        fun name(name: String): VariableBitwiseOrAssignmentSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        fun value(value: ValueSpec): VariableBitwiseOrAssignmentSpecBuilder {
            this.value = value
            return this
        }

        fun value(value: String): VariableBitwiseOrAssignmentSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        fun build(): VariableBitwiseOrAssignmentSpec {
            return VariableBitwiseOrAssignmentSpec(
                name ?: throw IllegalStateException("Name not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = VariableBitwiseOrAssignmentSpecBuilder()
    }
}

class VariableBitwiseXorAssignmentSpec(
    val name: Identifier,
    val value: ValueSpec,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name ^= $value"
    }

    class VariableBitwiseXorAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: ValueSpec? = null,
    ) {

        fun name(name: Identifier): VariableBitwiseXorAssignmentSpecBuilder {
            this.name = name
            return this
        }

        fun name(name: String): VariableBitwiseXorAssignmentSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        fun value(value: ValueSpec): VariableBitwiseXorAssignmentSpecBuilder {
            this.value = value
            return this
        }

        fun value(value: String): VariableBitwiseXorAssignmentSpecBuilder {
            this.value = ValueSpec.of(value)
            return this
        }

        fun build(): VariableBitwiseXorAssignmentSpec {
            return VariableBitwiseXorAssignmentSpec(
                name ?: throw IllegalStateException("Name not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = VariableBitwiseXorAssignmentSpecBuilder()
    }
}

class VariableIncrementBeforeSpec(
    val name: Identifier,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "++$name"
    }

    open class VariableIncrementBeforeSpecBuilder
    internal constructor(
        var name: Identifier? = null,
    ) {

        fun name(name: Identifier): VariableIncrementBeforeSpecBuilder {
            this.name = name
            return this
        }

        fun name(name: String): VariableIncrementBeforeSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        fun build(): VariableIncrementBeforeSpec {
            return VariableIncrementBeforeSpec(
                name ?: throw IllegalStateException("Name not set"),
            )
        }
    }

    companion object {
        fun builder() = VariableIncrementBeforeSpecBuilder()
    }
}

class VariableIncrementAfterSpec(
    val name: Identifier,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name++"
    }

    open class VariableIncrementAfterSpecBuilder
    internal constructor(
        var name: Identifier? = null,
    ) {

        fun name(name: Identifier): VariableIncrementAfterSpecBuilder {
            this.name = name
            return this
        }

        fun name(name: String): VariableIncrementAfterSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        fun build(): VariableIncrementAfterSpec {
            return VariableIncrementAfterSpec(
                name ?: throw IllegalStateException("Name not set"),
            )
        }
    }

    companion object {
        fun builder() = VariableIncrementAfterSpecBuilder()
    }
}

class VariableDecrementBeforeSpec(
    val name: Identifier,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "--$name"
    }

    open class VariableDecrementBeforeSpecBuilder
    internal constructor(
        var name: Identifier? = null,
    ) {

        fun name(name: Identifier): VariableDecrementBeforeSpecBuilder {
            this.name = name
            return this
        }

        fun name(name: String): VariableDecrementBeforeSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        fun build(): VariableDecrementBeforeSpec {
            return VariableDecrementBeforeSpec(
                name ?: throw IllegalStateException("Name not set"),
            )
        }
    }

    companion object {
        fun builder() = VariableDecrementBeforeSpecBuilder()
    }
}

class VariableDecrementAfterSpec(
    val name: Identifier,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name--"
    }

    open class VariableDecrementAfterSpecBuilder
    internal constructor(
        var name: Identifier? = null,
    ) {

        fun name(name: Identifier): VariableDecrementAfterSpecBuilder {
            this.name = name
            return this
        }

        fun name(name: String): VariableDecrementAfterSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        fun build(): VariableDecrementAfterSpec {
            return VariableDecrementAfterSpec(
                name ?: throw IllegalStateException("Name not set"),
            )
        }
    }

    companion object {
        fun builder() = VariableDecrementAfterSpecBuilder()
    }
}

class FunctionCallSpec(
    val name: Identifier,
    val arguments: List<ValueSpec>,
) : ValueSpec, StatementSpec {
    override fun generate(ctx: GenerationContext): String {
        val args = arguments.joinToString(", ") { it.generate(ctx) }
        return "$name($args)"
    }

    open class FunctionCallSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var arguments: MutableList<ValueSpec> = mutableListOf(),
    ) {

        fun name(name: Identifier): FunctionCallSpecBuilder {
            this.name = name
            return this
        }

        fun name(name: String): FunctionCallSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        fun argument(argument: ValueSpec): FunctionCallSpecBuilder {
            arguments.add(argument)
            return this
        }

        fun build(): FunctionCallSpec {
            return FunctionCallSpec(
                name ?: throw IllegalStateException("Name not set"),
                arguments,
            )
        }
    }

    companion object {
        fun builder() = FunctionCallSpecBuilder()
    }
}
