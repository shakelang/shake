package com.shakelang.shake.shakespeare.spec.code

import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.Identifier

interface ValuedAssignmentSpec : StatementSpec, ValueSpec

class VariableAssignmentSpec(
    val name: Identifier,
    val value: String,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name = $value"
    }

    class VariableAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: String? = null,
    ) {
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
    val value: String,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name += $value"
    }

    class VariableAdditionAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: String? = null,
    ) {
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
    val value: String,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name -= $value"
    }

    class VariableSubtractionAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: String? = null,
    ) {
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
    val value: String,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name *= $value"
    }

    class VariableMultiplicationAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: String? = null,
    ) {
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
    val value: String,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name /= $value"
    }

    class VariableDivisionAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: String? = null,
    ) {
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
    val value: String,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name %= $value"
    }

    class VariableModuloAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: String? = null,
    ) {
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
    val value: String,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name **= $value"
    }

    class VariablePowerAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: String? = null,
    ) {
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
    val value: String,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name &= $value"
    }

    class VariableBitwiseAndAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: String? = null,
    ) {
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
    val value: String,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name |= $value"
    }

    class VariableBitwiseOrAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: String? = null,
    ) {
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
    val value: String,
) : ValuedAssignmentSpec {
    override fun generate(ctx: GenerationContext): String {
        return "$name ^= $value"
    }

    class VariableBitwiseXorAssignmentSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var value: String? = null,
    ) {
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

    class VariableIncrementBeforeSpecBuilder
    internal constructor(
        var name: Identifier? = null,
    ) {
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

    class VariableIncrementAfterSpecBuilder
    internal constructor(
        var name: Identifier? = null,
    ) {
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

    class VariableDecrementBeforeSpecBuilder
    internal constructor(
        var name: Identifier? = null,
    ) {
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

    class VariableDecrementAfterSpecBuilder
    internal constructor(
        var name: Identifier? = null,
    ) {
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
) : ValueSpec {
    override fun generate(ctx: GenerationContext): String {
        val args = arguments.joinToString(", ") { it.generate(ctx) }
        return "$name($args)"
    }

    class FunctionCallSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var arguments: MutableList<ValueSpec> = mutableListOf(),
    ) {
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
