@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.nodes.spec.code

import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.parser.node.ShakeValuedStatementNode
import com.shakelang.shake.parser.node.mixed.*
import com.shakelang.shake.shakespeare.nodes.spec.NamespaceNodeSpec
import com.shakelang.shake.shakespeare.nodes.spec.NodeContext
import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.code.*

/**
 * A [ValuedStatementNodeSpec] is a StatementSpec and a ValueNodeSpec at the same time
 * @since 0.1.0
 */
interface ValuedStatementNodeSpec : StatementNodeSpec, ValueNodeSpec, ValuedStatementSpec {
    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedStatementNode

    companion object {
        fun of(spec: ValuedStatementSpec): ValuedStatementNodeSpec {
            return when (spec) {
                is VariableAssignmentSpec -> VariableAssignmentNodeSpec.of(spec)
                is VariableAdditionAssignmentSpec -> VariableAdditionAssignmentNodeSpec.of(spec)
                is VariableSubtractionAssignmentSpec -> VariableSubtractionAssignmentNodeSpec.of(spec)
                is VariableMultiplicationAssignmentSpec -> VariableMultiplicationAssignmentNodeSpec.of(spec)
                is VariableDivisionAssignmentSpec -> VariableDivisionAssignmentNodeSpec.of(spec)
                is VariableModuloAssignmentSpec -> VariableModuloAssignmentNodeSpec.of(spec)
                is VariablePowerAssignmentSpec -> VariablePowerAssignmentNodeSpec.of(spec)
                is VariableIncrementBeforeSpec -> VariableIncrementBeforeNodeSpec.of(spec)
                is VariableIncrementAfterSpec -> VariableIncrementAfterNodeSpec.of(spec)
                is VariableDecrementBeforeSpec -> VariableDecrementBeforeNodeSpec.of(spec)
                is VariableDecrementAfterSpec -> VariableDecrementAfterNodeSpec.of(spec)
                else -> throw IllegalArgumentException("Unknown ValuedStatementSpec: $spec")
            }
        }
    }
}

open class VariableAssignmentNodeSpec(
    name: NamespaceNodeSpec,
    value: ValueNodeSpec,
) : VariableAssignmentSpec(
    name,
    value,
),
    ValuedStatementNodeSpec {

    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec
    override val value: ValueNodeSpec get() = super.value as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableAssignmentNode {
        val namespace = name.dump(ctx, nctx)
        nctx.space()
        val operator = nctx.createToken(ShakeTokenType.ASSIGN)
        nctx.space()
        val value = value.dump(ctx, nctx)
        return ShakeVariableAssignmentNode(nctx.map, namespace.toValue(), value, operator)
    }

    companion object {
        fun of(spec: VariableAssignmentSpec): VariableAssignmentNodeSpec {
            return VariableAssignmentNodeSpec(
                NamespaceNodeSpec.of(spec.name),
                ValueNodeSpec.of(spec.value),
            )
        }
    }
}

open class VariableAdditionAssignmentNodeSpec(
    name: NamespaceNodeSpec,
    value: ValueNodeSpec,
) : VariableAdditionAssignmentSpec(
    name,
    value,
),
    ValuedStatementNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec
    override val value: ValueNodeSpec get() = super.value as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableAddAssignmentNode {
        val namespace = name.dump(ctx, nctx)
        nctx.space()
        nctx.print("+=")
        val operator = nctx.createToken(ShakeTokenType.ADD_ASSIGN)
        nctx.space()
        val value = value.dump(ctx, nctx)
        return ShakeVariableAddAssignmentNode(nctx.map, namespace.toValue(), value, operator)
    }

    companion object {
        fun of(spec: VariableAdditionAssignmentSpec): VariableAdditionAssignmentNodeSpec {
            return VariableAdditionAssignmentNodeSpec(
                NamespaceNodeSpec.of(spec.name),
                ValueNodeSpec.of(spec.value),
            )
        }
    }
}

open class VariableSubtractionAssignmentNodeSpec(
    name: NamespaceNodeSpec,
    value: ValueNodeSpec,
) : VariableSubtractionAssignmentSpec(
    name,
    value,
),
    ValuedStatementNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec
    override val value: ValueNodeSpec get() = super.value as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableSubAssignmentNode {
        val namespace = name.dump(ctx, nctx)
        nctx.space()
        nctx.print("-=")
        val operator = nctx.createToken(ShakeTokenType.SUB_ASSIGN)
        nctx.space()
        val value = value.dump(ctx, nctx)
        return ShakeVariableSubAssignmentNode(nctx.map, namespace.toValue(), value, operator)
    }

    companion object {
        fun of(spec: VariableSubtractionAssignmentSpec): VariableSubtractionAssignmentNodeSpec {
            return VariableSubtractionAssignmentNodeSpec(
                NamespaceNodeSpec.of(spec.name),
                ValueNodeSpec.of(spec.value),
            )
        }
    }
}

open class VariableMultiplicationAssignmentNodeSpec(
    name: NamespaceNodeSpec,
    value: ValueNodeSpec,
) : VariableMultiplicationAssignmentSpec(
    name,
    value,
),
    ValuedStatementNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec
    override val value: ValueNodeSpec get() = super.value as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableMulAssignmentNode {
        val namespace = name.dump(ctx, nctx)
        nctx.space()
        nctx.print("*=")
        val operator = nctx.createToken(ShakeTokenType.MUL_ASSIGN)
        nctx.space()
        val value = value.dump(ctx, nctx)
        return ShakeVariableMulAssignmentNode(nctx.map, namespace.toValue(), value, operator)
    }

    companion object {
        fun of(spec: VariableMultiplicationAssignmentSpec): VariableMultiplicationAssignmentNodeSpec {
            return VariableMultiplicationAssignmentNodeSpec(
                NamespaceNodeSpec.of(spec.name),
                ValueNodeSpec.of(spec.value),
            )
        }
    }
}

open class VariableDivisionAssignmentNodeSpec(
    name: NamespaceNodeSpec,
    value: ValueNodeSpec,
) : VariableDivisionAssignmentSpec(
    name,
    value,
),
    ValuedStatementNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec
    override val value: ValueNodeSpec get() = super.value as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableDivAssignmentNode {
        val namespace = name.dump(ctx, nctx)
        nctx.space()
        nctx.print("/=")
        val operator = nctx.createToken(ShakeTokenType.DIV_ASSIGN)
        nctx.space()
        val value = value.dump(ctx, nctx)
        return ShakeVariableDivAssignmentNode(nctx.map, namespace.toValue(), value, operator)
    }

    companion object {
        fun of(spec: VariableDivisionAssignmentSpec): VariableDivisionAssignmentNodeSpec {
            return VariableDivisionAssignmentNodeSpec(
                NamespaceNodeSpec.of(spec.name),
                ValueNodeSpec.of(spec.value),
            )
        }
    }
}

open class VariableModuloAssignmentNodeSpec(
    name: NamespaceNodeSpec,
    value: ValueNodeSpec,
) : VariableModuloAssignmentSpec(
    name,
    value,
),
    ValuedStatementNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec
    override val value: ValueNodeSpec get() = super.value as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableModAssignmentNode {
        val namespace = name.dump(ctx, nctx)
        nctx.space()
        nctx.print("%=")
        val operator = nctx.createToken(ShakeTokenType.MOD_ASSIGN)
        nctx.space()
        val value = value.dump(ctx, nctx)
        return ShakeVariableModAssignmentNode(nctx.map, namespace.toValue(), value, operator)
    }

    companion object {
        fun of(spec: VariableModuloAssignmentSpec): VariableModuloAssignmentNodeSpec {
            return VariableModuloAssignmentNodeSpec(
                NamespaceNodeSpec.of(spec.name),
                ValueNodeSpec.of(spec.value),
            )
        }
    }
}

open class VariablePowerAssignmentNodeSpec(
    name: NamespaceNodeSpec,
    value: ValueNodeSpec,
) : VariablePowerAssignmentSpec(
    name,
    value,
),
    ValuedStatementNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec
    override val value: ValueNodeSpec get() = super.value as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariablePowAssignmentNode {
        val namespace = name.dump(ctx, nctx)
        nctx.space()
        nctx.print("**=")
        val operator = nctx.createToken(ShakeTokenType.POW_ASSIGN)
        nctx.space()
        val value = value.dump(ctx, nctx)
        return ShakeVariablePowAssignmentNode(nctx.map, namespace.toValue(), value, operator)
    }

    companion object {
        fun of(spec: VariablePowerAssignmentSpec): VariablePowerAssignmentNodeSpec {
            return VariablePowerAssignmentNodeSpec(
                NamespaceNodeSpec.of(spec.name),
                ValueNodeSpec.of(spec.value),
            )
        }
    }
}

open class VariableIncrementBeforeNodeSpec(
    name: NamespaceNodeSpec,
) : VariableIncrementBeforeSpec(
    name,
),
    ValuedStatementNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableIncrementBeforeNode {
        nctx.print("++")
        val operator = nctx.createToken(ShakeTokenType.INCR)
        nctx.space()
        val namespace = name.dump(ctx, nctx)
        return ShakeVariableIncrementBeforeNode(nctx.map, namespace.toValue(), operator)
    }

    companion object {
        fun of(spec: VariableIncrementBeforeSpec): VariableIncrementBeforeNodeSpec {
            return VariableIncrementBeforeNodeSpec(
                NamespaceNodeSpec.of(spec.name),
            )
        }
    }
}

open class VariableIncrementAfterNodeSpec(
    name: NamespaceNodeSpec,
) : VariableIncrementAfterSpec(
    name,
),
    ValuedStatementNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableIncrementAfterNode {
        val namespace = name.dump(ctx, nctx)
        nctx.space()
        nctx.print("++")
        val operator = nctx.createToken(ShakeTokenType.INCR)
        return ShakeVariableIncrementAfterNode(nctx.map, namespace.toValue(), operator)
    }

    companion object {
        fun of(spec: VariableIncrementAfterSpec): VariableIncrementAfterNodeSpec {
            return VariableIncrementAfterNodeSpec(
                NamespaceNodeSpec.of(spec.name),
            )
        }
    }
}

open class VariableDecrementBeforeNodeSpec(
    name: NamespaceNodeSpec,
) : VariableDecrementBeforeSpec(
    name,
),
    ValuedStatementNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableDecrementBeforeNode {
        nctx.print("--")
        val operator = nctx.createToken(ShakeTokenType.DECR)
        nctx.space()
        val namespace = name.dump(ctx, nctx)
        return ShakeVariableDecrementBeforeNode(nctx.map, namespace.toValue(), operator)
    }

    companion object {
        fun of(spec: VariableDecrementBeforeSpec): VariableDecrementBeforeNodeSpec {
            return VariableDecrementBeforeNodeSpec(
                NamespaceNodeSpec.of(spec.name),
            )
        }
    }
}

open class VariableDecrementAfterNodeSpec(
    name: NamespaceNodeSpec,
) : VariableDecrementAfterSpec(
    name,
),
    ValuedStatementNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableDecrementAfterNode {
        val namespace = name.dump(ctx, nctx)
        nctx.space()
        nctx.print("--")
        val operator = nctx.createToken(ShakeTokenType.DECR)
        return ShakeVariableDecrementAfterNode(nctx.map, namespace.toValue(), operator)
    }

    companion object {
        fun of(spec: VariableDecrementAfterSpec): VariableDecrementAfterNodeSpec {
            return VariableDecrementAfterNodeSpec(
                NamespaceNodeSpec.of(spec.name),
            )
        }
    }
}

open class FunctionCallNodeSpec(
    name: NamespaceNodeSpec,
    arguments: List<ValueNodeSpec>,
) : FunctionCallSpec(
    name,
    arguments,
),
    ValuedStatementNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec
    override val arguments: List<ValueNodeSpec> get() = super.arguments.map { it as ValueNodeSpec }

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeInvocationNode {
        val namespace = name.dump(ctx, nctx)
        nctx.print("(")
        val lp = nctx.createToken(ShakeTokenType.LPAREN)

        val args = arguments.mapIndexed { i, it ->
            val d = it.dump(ctx, nctx)
            if (i != arguments.size - 1) nctx.print(", ")
            d
        }.toTypedArray()
        nctx.print(")")
        val rp = nctx.createToken(ShakeTokenType.RPAREN)
        return ShakeInvocationNode(nctx.map, namespace.toValue(), args)
    }

    companion object {
        fun of(spec: FunctionCallSpec): FunctionCallNodeSpec {
            return FunctionCallNodeSpec(
                NamespaceNodeSpec.of(spec.name),
                spec.arguments.map { ValueNodeSpec.of(it) },
            )
        }
    }
}

fun ValuedStatementSpec.toNodeSpec() = ValuedStatementNodeSpec.of(this)
fun VariableAssignmentSpec.toNodeSpec() = VariableAssignmentNodeSpec.of(this)
fun VariableAdditionAssignmentSpec.toNodeSpec() = VariableAdditionAssignmentNodeSpec.of(this)
fun VariableSubtractionAssignmentSpec.toNodeSpec() = VariableSubtractionAssignmentNodeSpec.of(this)
fun VariableMultiplicationAssignmentSpec.toNodeSpec() = VariableMultiplicationAssignmentNodeSpec.of(this)
fun VariableDivisionAssignmentSpec.toNodeSpec() = VariableDivisionAssignmentNodeSpec.of(this)
fun VariableModuloAssignmentSpec.toNodeSpec() = VariableModuloAssignmentNodeSpec.of(this)
fun VariablePowerAssignmentSpec.toNodeSpec() = VariablePowerAssignmentNodeSpec.of(this)
fun VariableIncrementBeforeSpec.toNodeSpec() = VariableIncrementBeforeNodeSpec.of(this)
fun VariableIncrementAfterSpec.toNodeSpec() = VariableIncrementAfterNodeSpec.of(this)
fun VariableDecrementBeforeSpec.toNodeSpec() = VariableDecrementBeforeNodeSpec.of(this)
fun VariableDecrementAfterSpec.toNodeSpec() = VariableDecrementAfterNodeSpec.of(this)
fun FunctionCallSpec.toNodeSpec() = FunctionCallNodeSpec.of(this)
