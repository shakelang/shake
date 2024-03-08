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
import com.shakelang.shake.shakespeare.nodes.spec.AbstractNodeSpec
import com.shakelang.shake.shakespeare.nodes.spec.NamespaceNodeSpec
import com.shakelang.shake.shakespeare.nodes.spec.NodeContext
import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.code.*

/**
 * A [ValuedAssignmentSpec] is a StatementSpec and a ValueNodeSpec at the same time
 * @since 0.1.0
 */
interface ValuedAssignmentSpec : StatementSpec, ValueNodeSpec {
    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedStatementNode
}

open class VariableAssignmentNodeSpec(
    name: NamespaceNodeSpec,
    value: ValueNodeSpec,
) : VariableAssignmentSpec(
    name,
    value,
),
    AbstractNodeSpec {

    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec
    override val value: ValueNodeSpec get() = super.value as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableAssignmentNode {
        val namespace = name.dump(ctx, nctx)
        nctx.print(" ")
        val operator = nctx.createToken(ShakeTokenType.ASSIGN)
        nctx.print(" ")
        val value = value.dump(ctx, nctx)
        return ShakeVariableAssignmentNode(nctx.map, namespace.toValue(), value, operator)
    }
}

open class VariableAdditionAssignmentNodeSpec(
    name: NamespaceNodeSpec,
    value: ValueNodeSpec,
) : VariableAdditionAssignmentSpec(
    name,
    value,
),
    AbstractNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec
    override val value: ValueNodeSpec get() = super.value as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableAddAssignmentNode {
        val namespace = name.dump(ctx, nctx)
        nctx.print(" ")
        nctx.print("+=")
        val operator = nctx.createToken(ShakeTokenType.ADD_ASSIGN)
        nctx.print(" ")
        val value = value.dump(ctx, nctx)
        return ShakeVariableAddAssignmentNode(nctx.map, namespace.toValue(), value, operator)
    }
}

open class VariableSubtractionAssignmentNodeSpec(
    name: NamespaceNodeSpec,
    value: ValueNodeSpec,
) : VariableSubtractionAssignmentSpec(
    name,
    value,
),
    AbstractNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec
    override val value: ValueNodeSpec get() = super.value as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableSubAssignmentNode {
        val namespace = name.dump(ctx, nctx)
        nctx.print(" ")
        nctx.print("-=")
        val operator = nctx.createToken(ShakeTokenType.SUB_ASSIGN)
        nctx.print(" ")
        val value = value.dump(ctx, nctx)
        return ShakeVariableSubAssignmentNode(nctx.map, namespace.toValue(), value, operator)
    }
}

open class VariableMultiplicationAssignmentNodeSpec(
    name: NamespaceNodeSpec,
    value: ValueNodeSpec,
) : VariableMultiplicationAssignmentSpec(
    name,
    value,
),
    AbstractNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec
    override val value: ValueNodeSpec get() = super.value as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableMulAssignmentNode {
        val namespace = name.dump(ctx, nctx)
        nctx.print(" ")
        nctx.print("*=")
        val operator = nctx.createToken(ShakeTokenType.MUL_ASSIGN)
        nctx.print(" ")
        val value = value.dump(ctx, nctx)
        return ShakeVariableMulAssignmentNode(nctx.map, namespace.toValue(), value, operator)
    }
}

open class VariableDivisionAssignmentNodeSpec(
    name: NamespaceNodeSpec,
    value: ValueNodeSpec,
) : VariableDivisionAssignmentSpec(
    name,
    value,
),
    AbstractNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec
    override val value: ValueNodeSpec get() = super.value as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableDivAssignmentNode {
        val namespace = name.dump(ctx, nctx)
        nctx.print(" ")
        nctx.print("/=")
        val operator = nctx.createToken(ShakeTokenType.DIV_ASSIGN)
        nctx.print(" ")
        val value = value.dump(ctx, nctx)
        return ShakeVariableDivAssignmentNode(nctx.map, namespace.toValue(), value, operator)
    }
}

open class VariableModuloAssignmentNodeSpec(
    name: NamespaceNodeSpec,
    value: ValueNodeSpec,
) : VariableModuloAssignmentSpec(
    name,
    value,
),
    AbstractNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec
    override val value: ValueNodeSpec get() = super.value as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableModAssignmentNode {
        val namespace = name.dump(ctx, nctx)
        nctx.print(" ")
        nctx.print("%=")
        val operator = nctx.createToken(ShakeTokenType.MOD_ASSIGN)
        nctx.print(" ")
        val value = value.dump(ctx, nctx)
        return ShakeVariableModAssignmentNode(nctx.map, namespace.toValue(), value, operator)
    }
}

open class VariablePowerAssignmentNodeSpec(
    name: NamespaceNodeSpec,
    value: ValueNodeSpec,
) : VariablePowerAssignmentSpec(
    name,
    value,
),
    AbstractNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec
    override val value: ValueNodeSpec get() = super.value as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariablePowAssignmentNode {
        val namespace = name.dump(ctx, nctx)
        nctx.print(" ")
        nctx.print("**=")
        val operator = nctx.createToken(ShakeTokenType.POW_ASSIGN)
        nctx.print(" ")
        val value = value.dump(ctx, nctx)
        return ShakeVariablePowAssignmentNode(nctx.map, namespace.toValue(), value, operator)
    }
}

open class VariableIncrementBeforeNodeSpec(
    name: NamespaceNodeSpec,
) : VariableIncrementBeforeSpec(
    name,
),
    AbstractNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableIncrementBeforeNode {
        nctx.print("++")
        val operator = nctx.createToken(ShakeTokenType.INCR)
        nctx.print(" ")
        val namespace = name.dump(ctx, nctx)
        return ShakeVariableIncrementBeforeNode(nctx.map, namespace.toValue(), operator)
    }
}

open class VariableIncrementAfterNodeSpec(
    name: NamespaceNodeSpec,
) : VariableIncrementAfterSpec(
    name,
),
    AbstractNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableIncrementAfterNode {
        val namespace = name.dump(ctx, nctx)
        nctx.print(" ")
        nctx.print("++")
        val operator = nctx.createToken(ShakeTokenType.INCR)
        return ShakeVariableIncrementAfterNode(nctx.map, namespace.toValue(), operator)
    }
}

open class VariableDecrementBeforeNodeSpec(
    name: NamespaceNodeSpec,
) : VariableDecrementBeforeSpec(
    name,
),
    AbstractNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableDecrementBeforeNode {
        nctx.print("--")
        val operator = nctx.createToken(ShakeTokenType.DECR)
        nctx.print(" ")
        val namespace = name.dump(ctx, nctx)
        return ShakeVariableDecrementBeforeNode(nctx.map, namespace.toValue(), operator)
    }
}

open class VariableDecrementAfterNodeSpec(
    name: NamespaceNodeSpec,
) : VariableDecrementAfterSpec(
    name,
),
    AbstractNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableDecrementAfterNode {
        val namespace = name.dump(ctx, nctx)
        nctx.print(" ")
        nctx.print("--")
        val operator = nctx.createToken(ShakeTokenType.DECR)
        return ShakeVariableDecrementAfterNode(nctx.map, namespace.toValue(), operator)
    }
}

open class FunctionCallNodeSpec(
    name: NamespaceNodeSpec,
    arguments: List<ValueNodeSpec>,
) : FunctionCallSpec(
    name,
    arguments,
),
    AbstractNodeSpec {
    override val name: NamespaceNodeSpec get() = super.name as NamespaceNodeSpec
    override val arguments: List<ValueNodeSpec> get() = super.arguments.map { it as ValueNodeSpec }

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeInvocationNode {
        val namespace = name.dump(ctx, nctx)
        nctx.print("(")
        val open = nctx.createToken(ShakeTokenType.LPAREN)

        val args = arguments.mapIndexed { i, it ->
            val d = it.dump(ctx, nctx)
            if (i != arguments.size - 1) nctx.print(", ")
            d
        }.toTypedArray()
        nctx.print(")")
        val close = nctx.createToken(ShakeTokenType.RPAREN)
        return ShakeInvocationNode(nctx.map, namespace.toValue(), args)
    }
}
