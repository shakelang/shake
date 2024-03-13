@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.nodes.spec.code

import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.values.expression.ShakeAddNode
import com.shakelang.shake.parser.node.values.expression.ShakeLogicalAndNode
import com.shakelang.shake.parser.node.values.expression.ShakeMulNode
import com.shakelang.shake.parser.node.values.expression.ShakeSubNode
import com.shakelang.shake.parser.node.values.factor.*
import com.shakelang.shake.shakespeare.nodes.spec.AbstractNodeSpec
import com.shakelang.shake.shakespeare.nodes.spec.NamespaceNodeSpec
import com.shakelang.shake.shakespeare.nodes.spec.NodeContext
import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.NamespaceSpec
import com.shakelang.shake.shakespeare.spec.code.*

interface ValueNodeSpec : AbstractNodeSpec, ValueSpec {
    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode

    companion object {
        fun of(spec: ValueSpec): ValueNodeSpec = when (spec) {
            is ValuedStatementSpec -> ValuedStatementNodeSpec.of(spec)
            is StringLiteralSpec -> StringLiteralNodeSpec.of(spec)
            is CharacterLiteralSpec -> CharacterLiteralNodeSpec.of(spec)
            is IntLiteralSpec -> IntLiteralNodeSpec.of(spec)
            is FloatLiteralSpec -> FloatLiteralNodeSpec.of(spec)
            is BooleanLiteralSpec -> BooleanLiteralNodeSpec.of(spec)
            is NullLiteralSpec -> NullLiteralNodeSpec.of(spec)
            is VariableReferenceSpec -> VariableReferenceNodeSpec.of(spec)
            is AdditionSpec -> AdditionNodeSpec.of(spec)
            is SubtractionSpec -> SubtractionNodeSpec.of(spec)
            is MultiplicationSpec -> MultiplicationNodeSpec.of(spec)
            is DivisionSpec -> DivisionNodeSpec.of(spec)
            is ModuloSpec -> ModuloNodeSpec.of(spec)
            is PowerSpec -> PowerNodeSpec.of(spec)
            is UnaryMinusSpec -> UnaryMinusNodeSpec.of(spec)
            is UnaryPlusSpec -> UnaryPlusNodeSpec.of(spec)
            is LogicalAndSpec -> LogicalAndNodeSpec.of(spec)
            is LogicalOrSpec -> LogicalOrNodeSpec.of(spec)
            is LogicalNotSpec -> LogicalNotNodeSpec.of(spec)
            is LogicalXorSpec -> LogicalXorNodeSpec.of(spec)
            is EqualitySpec -> EqualityNodeSpec.of(spec)
            is InequalitySpec -> InequalityNodeSpec.of(spec)
            is GreaterThanSpec -> GreaterThanNodeSpec.of(spec)
            is GreaterThanOrEqualSpec -> GreaterThanOrEqualNodeSpec.of(spec)
            is LessThanSpec -> LessThanNodeSpec.of(spec)
            is LessThanOrEqualSpec -> LessThanOrEqualNodeSpec.of(spec)
            else -> throw IllegalArgumentException("Unknown value spec: $spec")
        }
    }

    open class StringLiteralNodeSpec(value: String) : StringLiteralSpec(value), ValueNodeSpec {

        override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
            return ShakeStringLiteralNode(nctx.map, nctx.createToken(ShakeTokenType.STRING, "\"$escaped\""))
        }

        companion object {
            fun of(spec: StringLiteralSpec): StringLiteralNodeSpec = StringLiteralNodeSpec(spec.value)
        }
    }

    open class CharacterLiteralNodeSpec(value: Char) : CharacterLiteralSpec(value), ValueNodeSpec {

        override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
            return ShakeCharacterLiteralNode(nctx.map, nctx.createToken(ShakeTokenType.CHARACTER, "'$escaped'"))
        }

        companion object {
            fun of(spec: CharacterLiteralSpec): CharacterLiteralNodeSpec = CharacterLiteralNodeSpec(spec.value)
        }
    }

    open class IntLiteralNodeSpec(value: Long) : IntLiteralSpec(value), ValueNodeSpec {

        override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
            return ShakeIntegerLiteralNode(nctx.map, nctx.createToken(ShakeTokenType.INTEGER, value.toString()))
        }

        companion object {
            fun of(spec: IntLiteralSpec): IntLiteralNodeSpec = IntLiteralNodeSpec(spec.value)
        }
    }

    open class FloatLiteralNodeSpec(value: Double) : FloatLiteralSpec(value), ValueNodeSpec {

        override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
            return ShakeDoubleLiteralNode(nctx.map, nctx.createToken(ShakeTokenType.FLOAT, value.toString()))
        }

        companion object {
            fun of(spec: FloatLiteralSpec): FloatLiteralNodeSpec = FloatLiteralNodeSpec(spec.value)
        }
    }

    open class BooleanLiteralNodeSpec(value: Boolean) : BooleanLiteralSpec(value), ValueNodeSpec {

        override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
            if (value) return ShakeTrueLiteralNode(nctx.map, nctx.createToken(ShakeTokenType.KEYWORD_TRUE))
            return ShakeFalseLiteralNode(nctx.map, nctx.createToken(ShakeTokenType.KEYWORD_FALSE))
        }

        companion object {
            fun of(spec: BooleanLiteralSpec): BooleanLiteralNodeSpec = BooleanLiteralNodeSpec(spec.value)
        }
    }

    open class NullLiteralNodeSpec : NullLiteralSpec(), ValueNodeSpec {

        override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeNullLiteralNode {
            return ShakeNullLiteralNode(nctx.map, nctx.createToken(ShakeTokenType.KEYWORD_NULL))
        }

        companion object {
            @Suppress("unused_parameter")
            fun of(spec: NullLiteralSpec): NullLiteralNodeSpec = NullLiteralNodeSpec()
        }
    }

    open class VariableReferenceNodeSpec(value: NamespaceSpec) : VariableReferenceSpec(value), ValueNodeSpec {

        override val name: NamespaceNodeSpec
            get() = super.name as NamespaceNodeSpec

        override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
            return name.dump(ctx, nctx).toValue()
        }

        companion object {
            fun of(spec: VariableReferenceSpec): VariableReferenceNodeSpec = VariableReferenceNodeSpec(spec.name)
        }
    }

    open class AdditionNodeSpec(left: ValueNodeSpec, right: ValueNodeSpec) : AdditionSpec(left, right), ValueNodeSpec {

        override val left: ValueNodeSpec
            get() = super.left as ValueNodeSpec

        override val right: ValueNodeSpec
            get() = super.right as ValueNodeSpec

        override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
            val left = this.left.dump(ctx, nctx)
            nctx.print(" ")
            val operator = nctx.createToken(ShakeTokenType.ADD)
            nctx.print(" ")
            val right = this.right.dump(ctx, nctx)
            return ShakeAddNode(
                nctx.map,
                left,
                right,
                operator,
            )
        }

        companion object {
            fun of(spec: AdditionSpec): AdditionNodeSpec = AdditionNodeSpec(of(spec.left), of(spec.right))
        }
    }

    open class SubtractionNodeSpec(left: ValueNodeSpec, right: ValueNodeSpec) : SubtractionSpec(left, right), ValueNodeSpec {

        override val left: ValueNodeSpec
            get() = super.left as ValueNodeSpec

        override val right: ValueNodeSpec
            get() = super.right as ValueNodeSpec

        override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
            val left = this.left.dump(ctx, nctx)
            nctx.print(" ")
            val operator = nctx.createToken(ShakeTokenType.SUB)
            nctx.print(" ")
            val right = this.right.dump(ctx, nctx)
            return ShakeSubNode(
                nctx.map,
                left,
                right,
                operator,
            )
        }

        companion object {
            fun of(spec: SubtractionSpec): SubtractionNodeSpec = SubtractionNodeSpec(of(spec.left), of(spec.right))
        }
    }
}

open class MultiplicationNodeSpec(left: ValueNodeSpec, right: ValueNodeSpec) : MultiplicationSpec(left, right), ValueNodeSpec {

    override val left: ValueNodeSpec
        get() = super.left as ValueNodeSpec

    override val right: ValueNodeSpec
        get() = super.right as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
        val left = this.left.dump(ctx, nctx)
        nctx.print(" ")
        val operator = nctx.createToken(ShakeTokenType.MUL)
        nctx.print(" ")
        val right = this.right.dump(ctx, nctx)
        return ShakeMulNode(
            nctx.map,
            left,
            right,
            operator,
        )
    }

    companion object {
        fun of(spec: MultiplicationSpec): MultiplicationNodeSpec = MultiplicationNodeSpec(
            ValueNodeSpec.of(spec.left),
            ValueNodeSpec.of(spec.right),
        )
    }
}

open class DivisionNodeSpec(left: ValueNodeSpec, right: ValueNodeSpec) : DivisionSpec(left, right), ValueNodeSpec {

    override val left: ValueNodeSpec
        get() = super.left as ValueNodeSpec

    override val right: ValueNodeSpec
        get() = super.right as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
        val left = this.left.dump(ctx, nctx)
        nctx.print(" ")
        val operator = nctx.createToken(ShakeTokenType.DIV)
        nctx.print(" ")
        val right = this.right.dump(ctx, nctx)
        return ShakeMulNode(
            nctx.map,
            left,
            right,
            operator,
        )
    }

    companion object {
        fun of(spec: DivisionSpec): DivisionNodeSpec = DivisionNodeSpec(
            ValueNodeSpec.of(spec.left),
            ValueNodeSpec.of(spec.right),
        )
    }
}

open class ModuloNodeSpec(left: ValueNodeSpec, right: ValueNodeSpec) : ModuloSpec(left, right), ValueNodeSpec {

    override val left: ValueNodeSpec
        get() = super.left as ValueNodeSpec

    override val right: ValueNodeSpec
        get() = super.right as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
        val left = this.left.dump(ctx, nctx)
        nctx.print(" ")
        val operator = nctx.createToken(ShakeTokenType.MOD)
        nctx.print(" ")
        val right = this.right.dump(ctx, nctx)
        return ShakeMulNode(
            nctx.map,
            left,
            right,
            operator,
        )
    }

    companion object {
        fun of(spec: ModuloSpec): ModuloNodeSpec = ModuloNodeSpec(
            ValueNodeSpec.of(spec.left),
            ValueNodeSpec.of(spec.right),
        )
    }
}

open class PowerNodeSpec(left: ValueNodeSpec, right: ValueNodeSpec) : PowerSpec(left, right), ValueNodeSpec {

    override val left: ValueNodeSpec
        get() = super.left as ValueNodeSpec

    override val right: ValueNodeSpec
        get() = super.right as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
        val left = this.left.dump(ctx, nctx)
        nctx.print(" ")
        val operator = nctx.createToken(ShakeTokenType.POW)
        nctx.print(" ")
        val right = this.right.dump(ctx, nctx)
        return ShakeMulNode(
            nctx.map,
            left,
            right,
            operator,
        )
    }

    companion object {
        fun of(spec: PowerSpec): PowerNodeSpec = PowerNodeSpec(
            ValueNodeSpec.of(spec.left),
            ValueNodeSpec.of(spec.right),
        )
    }
}

open class UnaryMinusNodeSpec(value: ValueNodeSpec) : UnaryMinusSpec(value), ValueNodeSpec {

    override val value: ValueNodeSpec
        get() = super.value as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
        nctx.print("-")
        return value.dump(ctx, nctx)
    }

    companion object {
        fun of(spec: UnaryMinusSpec): UnaryMinusNodeSpec = UnaryMinusNodeSpec(ValueNodeSpec.of(spec.value))
    }
}

typealias NegationNodeSpec = UnaryMinusNodeSpec

open class UnaryPlusNodeSpec(value: ValueNodeSpec) : UnaryPlusSpec(value), ValueNodeSpec {

    override val value: ValueNodeSpec
        get() = super.value as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
        nctx.print("+")
        return value.dump(ctx, nctx)
    }

    companion object {
        fun of(spec: UnaryPlusSpec): UnaryPlusNodeSpec = UnaryPlusNodeSpec(ValueNodeSpec.of(spec.value))
    }
}

open class LogicalAndNodeSpec(left: ValueNodeSpec, right: ValueNodeSpec) : LogicalAndSpec(left, right), ValueNodeSpec {

    override val left: ValueNodeSpec
        get() = super.left as ValueNodeSpec

    override val right: ValueNodeSpec
        get() = super.right as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
        val left = this.left.dump(ctx, nctx)
        nctx.print(" ")
        val operator = nctx.createToken(ShakeTokenType.LOGICAL_AND)
        nctx.print(" ")
        val right = this.right.dump(ctx, nctx)
        return ShakeLogicalAndNode(
            nctx.map,
            left,
            right,
            operator,
        )
    }

    companion object {
        fun of(spec: LogicalAndSpec): LogicalAndNodeSpec = LogicalAndNodeSpec(
            ValueNodeSpec.of(spec.left),
            ValueNodeSpec.of(spec.right),
        )
    }
}

open class LogicalOrNodeSpec(left: ValueNodeSpec, right: ValueNodeSpec) : LogicalOrSpec(left, right), ValueNodeSpec {

    override val left: ValueNodeSpec
        get() = super.left as ValueNodeSpec

    override val right: ValueNodeSpec
        get() = super.right as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
        val left = this.left.dump(ctx, nctx)
        nctx.print(" ")
        val operator = nctx.createToken(ShakeTokenType.LOGICAL_OR)
        nctx.print(" ")
        val right = this.right.dump(ctx, nctx)
        return ShakeLogicalAndNode(
            nctx.map,
            left,
            right,
            operator,
        )
    }

    companion object {
        fun of(spec: LogicalOrSpec): LogicalOrNodeSpec = LogicalOrNodeSpec(
            ValueNodeSpec.of(spec.left),
            ValueNodeSpec.of(spec.right),
        )
    }
}

open class LogicalNotNodeSpec(value: ValueNodeSpec) : LogicalNotSpec(value), ValueNodeSpec {

    override val value: ValueNodeSpec
        get() = super.value as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
        nctx.print("!")
        return value.dump(ctx, nctx)
    }

    companion object {
        fun of(spec: LogicalNotSpec): LogicalNotNodeSpec = LogicalNotNodeSpec(ValueNodeSpec.of(spec.value))
    }
}

open class LogicalXorNodeSpec(left: ValueNodeSpec, right: ValueNodeSpec) : LogicalXorSpec(left, right), ValueNodeSpec {

    override val left: ValueNodeSpec
        get() = super.left as ValueNodeSpec

    override val right: ValueNodeSpec
        get() = super.right as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
        val left = this.left.dump(ctx, nctx)
        nctx.print(" ")
        val operator = nctx.createToken(ShakeTokenType.LOGICAL_XOR)
        nctx.print(" ")
        val right = this.right.dump(ctx, nctx)
        return ShakeLogicalAndNode(
            nctx.map,
            left,
            right,
            operator,
        )
    }

    companion object {
        fun of(spec: LogicalXorSpec): LogicalXorNodeSpec = LogicalXorNodeSpec(
            ValueNodeSpec.of(spec.left),
            ValueNodeSpec.of(spec.right),
        )
    }
}

open class EqualityNodeSpec(left: ValueNodeSpec, right: ValueNodeSpec) : EqualitySpec(left, right), ValueNodeSpec {

    override val left: ValueNodeSpec
        get() = super.left as ValueNodeSpec

    override val right: ValueNodeSpec
        get() = super.right as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
        val left = this.left.dump(ctx, nctx)
        nctx.print(" ")
        val operator = nctx.createToken(ShakeTokenType.EQ_EQUALS)
        nctx.print(" ")
        val right = this.right.dump(ctx, nctx)
        return ShakeLogicalAndNode(
            nctx.map,
            left,
            right,
            operator,
        )
    }

    companion object {
        fun of(spec: EqualitySpec): EqualityNodeSpec = EqualityNodeSpec(
            ValueNodeSpec.of(spec.left),
            ValueNodeSpec.of(spec.right),
        )
    }
}

open class InequalityNodeSpec(left: ValueNodeSpec, right: ValueNodeSpec) : InequalitySpec(left, right), ValueNodeSpec {

    override val left: ValueNodeSpec
        get() = super.left as ValueNodeSpec

    override val right: ValueNodeSpec
        get() = super.right as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
        val left = this.left.dump(ctx, nctx)
        nctx.print(" ")
        val operator = nctx.createToken(ShakeTokenType.NOT_EQUALS)
        nctx.print(" ")
        val right = this.right.dump(ctx, nctx)
        return ShakeLogicalAndNode(
            nctx.map,
            left,
            right,
            operator,
        )
    }

    companion object {
        fun of(spec: InequalitySpec): InequalityNodeSpec = InequalityNodeSpec(
            ValueNodeSpec.of(spec.left),
            ValueNodeSpec.of(spec.right),
        )
    }
}

open class GreaterThanNodeSpec(left: ValueNodeSpec, right: ValueNodeSpec) : GreaterThanSpec(left, right), ValueNodeSpec {

    override val left: ValueNodeSpec
        get() = super.left as ValueNodeSpec

    override val right: ValueNodeSpec
        get() = super.right as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
        val left = this.left.dump(ctx, nctx)
        nctx.print(" ")
        val operator = nctx.createToken(ShakeTokenType.BIGGER)
        nctx.print(" ")
        val right = this.right.dump(ctx, nctx)
        return ShakeLogicalAndNode(
            nctx.map,
            left,
            right,
            operator,
        )
    }

    companion object {
        fun of(spec: GreaterThanSpec): GreaterThanNodeSpec = GreaterThanNodeSpec(
            ValueNodeSpec.of(spec.left),
            ValueNodeSpec.of(spec.right),
        )
    }
}

open class GreaterThanOrEqualNodeSpec(left: ValueNodeSpec, right: ValueNodeSpec) : GreaterThanOrEqualSpec(left, right), ValueNodeSpec {

    override val left: ValueNodeSpec
        get() = super.left as ValueNodeSpec

    override val right: ValueNodeSpec
        get() = super.right as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
        val left = this.left.dump(ctx, nctx)
        nctx.print(" ")
        val operator = nctx.createToken(ShakeTokenType.BIGGER_EQUALS)
        nctx.print(" ")
        val right = this.right.dump(ctx, nctx)
        return ShakeLogicalAndNode(
            nctx.map,
            left,
            right,
            operator,
        )
    }

    companion object {
        fun of(spec: GreaterThanOrEqualSpec): GreaterThanOrEqualNodeSpec = GreaterThanOrEqualNodeSpec(
            ValueNodeSpec.of(spec.left),
            ValueNodeSpec.of(spec.right),
        )
    }
}

open class LessThanNodeSpec(left: ValueNodeSpec, right: ValueNodeSpec) : LessThanSpec(left, right), ValueNodeSpec {

    override val left: ValueNodeSpec
        get() = super.left as ValueNodeSpec

    override val right: ValueNodeSpec
        get() = super.right as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
        val left = this.left.dump(ctx, nctx)
        nctx.print(" ")
        val operator = nctx.createToken(ShakeTokenType.SMALLER)
        nctx.print(" ")
        val right = this.right.dump(ctx, nctx)
        return ShakeLogicalAndNode(
            nctx.map,
            left,
            right,
            operator,
        )
    }

    companion object {
        fun of(spec: LessThanSpec): LessThanNodeSpec = LessThanNodeSpec(
            ValueNodeSpec.of(spec.left),
            ValueNodeSpec.of(spec.right),
        )
    }
}

open class LessThanOrEqualNodeSpec(left: ValueNodeSpec, right: ValueNodeSpec) : LessThanOrEqualSpec(left, right), ValueNodeSpec {

    override val left: ValueNodeSpec
        get() = super.left as ValueNodeSpec

    override val right: ValueNodeSpec
        get() = super.right as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
        val left = this.left.dump(ctx, nctx)
        nctx.print(" ")
        val operator = nctx.createToken(ShakeTokenType.SMALLER_EQUALS)
        nctx.print(" ")
        val right = this.right.dump(ctx, nctx)
        return ShakeLogicalAndNode(
            nctx.map,
            left,
            right,
            operator,
        )
    }

    companion object {
        fun of(spec: LessThanOrEqualSpec): LessThanOrEqualNodeSpec = LessThanOrEqualNodeSpec(
            ValueNodeSpec.of(spec.left),
            ValueNodeSpec.of(spec.right),
        )
    }
}
