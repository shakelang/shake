package com.shakelang.shake.shakespeare.spec.code

import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.Identifier

interface ValueSpec {
    fun generate(ctx: GenerationContext): String
}

class StringLiteralSpec(val value: String) : ValueSpec {
    override fun generate(context: GenerationContext): String {
        return "\"$value\""
    }

    class StringLiteralSpecBuilder
    internal constructor(
        var value: String? = null,
    ) {
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

class NumberLiteralSpec(val value: Number) : ValueSpec {
    override fun generate(context: GenerationContext): String {
        return value.toString()
    }

    class NumberLiteralSpecBuilder
    internal constructor(
        var value: Number? = null,
    ) {
        fun build(): NumberLiteralSpec {
            return NumberLiteralSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = NumberLiteralSpecBuilder()
    }
}

class BooleanLiteralSpec(val value: Boolean) : ValueSpec {
    override fun generate(context: GenerationContext): String {
        return value.toString()
    }

    class BooleanLiteralSpecBuilder
    internal constructor(
        var value: Boolean? = null,
    ) {
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

class VariableReferenceSpec(val name: Identifier) : ValueSpec {
    override fun generate(context: GenerationContext): String {
        return name.name
    }

    class VariableReferenceSpecBuilder
    internal constructor(
        var name: Identifier? = null,
    ) {
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
