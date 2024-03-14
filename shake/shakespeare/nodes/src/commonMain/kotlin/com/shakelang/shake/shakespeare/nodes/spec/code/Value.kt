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
import com.shakelang.shake.parser.node.values.ShakeVariableUsageNode
import com.shakelang.shake.parser.node.values.expression.*
import com.shakelang.shake.parser.node.values.factor.*
import com.shakelang.shake.shakespeare.nodes.spec.AbstractNodeSpec
import com.shakelang.shake.shakespeare.nodes.spec.NamespaceNodeSpec
import com.shakelang.shake.shakespeare.nodes.spec.NodeContext
import com.shakelang.shake.shakespeare.spec.GenerationContext
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
}

open class StringLiteralNodeSpec(value: String) : StringLiteralSpec(value), ValueNodeSpec {

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeStringLiteralNode {
        return ShakeStringLiteralNode(nctx.map, nctx.createToken(ShakeTokenType.STRING, "\"$escaped\""))
    }

    companion object {
        fun of(spec: StringLiteralSpec): StringLiteralNodeSpec = StringLiteralNodeSpec(spec.value)
    }
}

open class CharacterLiteralNodeSpec(value: Char) : CharacterLiteralSpec(value), ValueNodeSpec {

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeCharacterLiteralNode {
        return ShakeCharacterLiteralNode(nctx.map, nctx.createToken(ShakeTokenType.CHARACTER, "'$escaped'"))
    }

    companion object {
        fun of(spec: CharacterLiteralSpec): CharacterLiteralNodeSpec = CharacterLiteralNodeSpec(spec.value)
    }
}

open class IntLiteralNodeSpec(value: Long) : IntLiteralSpec(value), ValueNodeSpec {

    constructor(value: Int) : this(value.toLong())

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
        if (value < 0) {
            val minus = nctx.createToken(ShakeTokenType.SUB)
            return ShakeUnaryMinusNode(
                nctx.map,
                ShakeIntegerLiteralNode(
                    nctx.map,
                    nctx.createToken(ShakeTokenType.INTEGER, (-value).toString()),
                ),
                minus,
            )
        }
        return ShakeIntegerLiteralNode(nctx.map, nctx.createToken(ShakeTokenType.INTEGER, value.toString()))
    }

    companion object {
        fun of(spec: IntLiteralSpec): IntLiteralNodeSpec = IntLiteralNodeSpec(spec.value)
    }
}

open class FloatLiteralNodeSpec(value: Double) : FloatLiteralSpec(value), ValueNodeSpec {

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode {
        if (value < 0) {
            val minus = nctx.createToken(ShakeTokenType.SUB)
            return ShakeUnaryMinusNode(
                nctx.map,
                ShakeFloatLiteralNode(
                    nctx.map,
                    nctx.createToken(ShakeTokenType.FLOAT, (-value).toString()),
                ),
                minus,
            )
        }
        return ShakeFloatLiteralNode(nctx.map, nctx.createToken(ShakeTokenType.FLOAT, (value).toString()))
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

open class VariableReferenceNodeSpec(value: NamespaceNodeSpec) : VariableReferenceSpec(value), ValueNodeSpec {

    constructor(vararg value: String) : this(NamespaceNodeSpec(*value))

    override val name: NamespaceNodeSpec
        get() = super.name as NamespaceNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableUsageNode {
        return name.dump(ctx, nctx).toValue()
    }

    companion object {
        fun of(spec: VariableReferenceSpec): VariableReferenceNodeSpec = VariableReferenceNodeSpec(NamespaceNodeSpec.of(spec.name))
    }
}

open class AdditionNodeSpec(left: ValueNodeSpec, right: ValueNodeSpec) : AdditionSpec(left, right), ValueNodeSpec {

    override val left: ValueNodeSpec
        get() = super.left as ValueNodeSpec

    override val right: ValueNodeSpec
        get() = super.right as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeAddNode {
        val left = this.left.dump(ctx, nctx)
        nctx.space()
        val operator = nctx.createToken(ShakeTokenType.ADD)
        nctx.space()
        val right = this.right.dump(ctx, nctx)
        return ShakeAddNode(
            nctx.map,
            left,
            right,
            operator,
        )
    }

    companion object {
        fun of(spec: AdditionSpec): AdditionNodeSpec = AdditionNodeSpec(ValueNodeSpec.of(spec.left), ValueNodeSpec.of(spec.right))
    }
}

open class SubtractionNodeSpec(left: ValueNodeSpec, right: ValueNodeSpec) : SubtractionSpec(left, right), ValueNodeSpec {

    override val left: ValueNodeSpec
        get() = super.left as ValueNodeSpec

    override val right: ValueNodeSpec
        get() = super.right as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeSubNode {
        val left = this.left.dump(ctx, nctx)
        nctx.space()
        val operator = nctx.createToken(ShakeTokenType.SUB)
        nctx.space()
        val right = this.right.dump(ctx, nctx)
        return ShakeSubNode(
            nctx.map,
            left,
            right,
            operator,
        )
    }

    companion object {
        fun of(spec: SubtractionSpec): SubtractionNodeSpec = SubtractionNodeSpec(ValueNodeSpec.of(spec.left), ValueNodeSpec.of(spec.right))
    }
}

open class MultiplicationNodeSpec(left: ValueNodeSpec, right: ValueNodeSpec) : MultiplicationSpec(left, right), ValueNodeSpec {

    override val left: ValueNodeSpec
        get() = super.left as ValueNodeSpec

    override val right: ValueNodeSpec
        get() = super.right as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeMulNode {
        val left = this.left.dump(ctx, nctx)
        nctx.space()
        val operator = nctx.createToken(ShakeTokenType.MUL)
        nctx.space()
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

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeDivNode {
        val left = this.left.dump(ctx, nctx)
        nctx.space()
        val operator = nctx.createToken(ShakeTokenType.DIV)
        nctx.space()
        val right = this.right.dump(ctx, nctx)
        return ShakeDivNode(
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

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeModNode {
        val left = this.left.dump(ctx, nctx)
        nctx.space()
        val operator = nctx.createToken(ShakeTokenType.MOD)
        nctx.space()
        val right = this.right.dump(ctx, nctx)
        return ShakeModNode(
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

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakePowNode {
        val left = this.left.dump(ctx, nctx)
        nctx.space()
        val operator = nctx.createToken(ShakeTokenType.POW)
        nctx.space()
        val right = this.right.dump(ctx, nctx)
        return ShakePowNode(
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

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeUnaryMinusNode {
        val minus = nctx.createToken(ShakeTokenType.SUB)
        return ShakeUnaryMinusNode(nctx.map, value.dump(ctx, nctx), minus)
    }

    companion object {
        fun of(spec: UnaryMinusSpec): UnaryMinusNodeSpec = UnaryMinusNodeSpec(ValueNodeSpec.of(spec.value))
    }
}

typealias NegationNodeSpec = UnaryMinusNodeSpec

open class UnaryPlusNodeSpec(value: ValueNodeSpec) : UnaryPlusSpec(value), ValueNodeSpec {

    override val value: ValueNodeSpec
        get() = super.value as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeUnaryPlusNode {
        val plus = nctx.createToken(ShakeTokenType.ADD)
        return ShakeUnaryPlusNode(nctx.map, value.dump(ctx, nctx), plus)
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

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeLogicalAndNode {
        val left = this.left.dump(ctx, nctx)
        nctx.space()
        val operator = nctx.createToken(ShakeTokenType.LOGICAL_AND)
        nctx.space()
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

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeLogicalOrNode {
        val left = this.left.dump(ctx, nctx)
        nctx.space()
        val operator = nctx.createToken(ShakeTokenType.LOGICAL_OR)
        nctx.space()
        val right = this.right.dump(ctx, nctx)
        return ShakeLogicalOrNode(
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

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeLogicalNotNode {
        val operator = nctx.createToken(ShakeTokenType.LOGICAL_NOT)
        val value = this.value.dump(ctx, nctx)
        return ShakeLogicalNotNode(nctx.map, value, operator)
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

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeLogicalXOrNode {
        val left = this.left.dump(ctx, nctx)
        nctx.space()
        val operator = nctx.createToken(ShakeTokenType.LOGICAL_XOR)
        nctx.space()
        val right = this.right.dump(ctx, nctx)
        return ShakeLogicalXOrNode(
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

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeEqualNode {
        val left = this.left.dump(ctx, nctx)
        nctx.space()
        val operator = nctx.createToken(ShakeTokenType.EQ_EQUALS)
        nctx.space()
        val right = this.right.dump(ctx, nctx)
        return ShakeEqualNode(
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

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeNotEqualNode {
        val left = this.left.dump(ctx, nctx)
        nctx.space()
        val operator = nctx.createToken(ShakeTokenType.NOT_EQUALS)
        nctx.space()
        val right = this.right.dump(ctx, nctx)
        return ShakeNotEqualNode(
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

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeGreaterThanNode {
        val left = this.left.dump(ctx, nctx)
        nctx.space()
        val operator = nctx.createToken(ShakeTokenType.BIGGER)
        nctx.space()
        val right = this.right.dump(ctx, nctx)
        return ShakeGreaterThanNode(
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

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeGreaterThanOrEqualNode {
        val left = this.left.dump(ctx, nctx)
        nctx.space()
        val operator = nctx.createToken(ShakeTokenType.BIGGER_EQUALS)
        nctx.space()
        val right = this.right.dump(ctx, nctx)
        return ShakeGreaterThanOrEqualNode(
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

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeLessThanNode {
        val left = this.left.dump(ctx, nctx)
        nctx.space()
        val operator = nctx.createToken(ShakeTokenType.SMALLER)
        nctx.space()
        val right = this.right.dump(ctx, nctx)
        return ShakeLessThanNode(
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

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeLessThanOrEqualNode {
        val left = this.left.dump(ctx, nctx)
        nctx.space()
        val operator = nctx.createToken(ShakeTokenType.SMALLER_EQUALS)
        nctx.space()
        val right = this.right.dump(ctx, nctx)
        return ShakeLessThanOrEqualNode(
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

fun ValueSpec.toNodeSpec(): ValueNodeSpec = ValueNodeSpec.of(this)
fun StringLiteralSpec.toNodeSpec(): StringLiteralNodeSpec = StringLiteralNodeSpec.of(this)
fun CharacterLiteralSpec.toNodeSpec(): CharacterLiteralNodeSpec = CharacterLiteralNodeSpec.of(this)
fun IntLiteralSpec.toNodeSpec(): IntLiteralNodeSpec = IntLiteralNodeSpec.of(this)
fun FloatLiteralSpec.toNodeSpec(): FloatLiteralNodeSpec = FloatLiteralNodeSpec.of(this)
fun BooleanLiteralSpec.toNodeSpec(): BooleanLiteralNodeSpec = BooleanLiteralNodeSpec.of(this)
fun NullLiteralSpec.toNodeSpec(): NullLiteralNodeSpec = NullLiteralNodeSpec.of(this)
fun VariableReferenceSpec.toNodeSpec(): VariableReferenceNodeSpec = VariableReferenceNodeSpec.of(this)
fun AdditionSpec.toNodeSpec(): AdditionNodeSpec = AdditionNodeSpec.of(this)
fun SubtractionSpec.toNodeSpec(): SubtractionNodeSpec = SubtractionNodeSpec.of(this)
fun MultiplicationSpec.toNodeSpec(): MultiplicationNodeSpec = MultiplicationNodeSpec.of(this)
fun DivisionSpec.toNodeSpec(): DivisionNodeSpec = DivisionNodeSpec.of(this)
fun ModuloSpec.toNodeSpec(): ModuloNodeSpec = ModuloNodeSpec.of(this)
fun PowerSpec.toNodeSpec(): PowerNodeSpec = PowerNodeSpec.of(this)
fun UnaryMinusSpec.toNodeSpec(): UnaryMinusNodeSpec = UnaryMinusNodeSpec.of(this)
fun UnaryPlusSpec.toNodeSpec(): UnaryPlusNodeSpec = UnaryPlusNodeSpec.of(this)
fun LogicalAndSpec.toNodeSpec(): LogicalAndNodeSpec = LogicalAndNodeSpec.of(this)
fun LogicalOrSpec.toNodeSpec(): LogicalOrNodeSpec = LogicalOrNodeSpec.of(this)
fun LogicalNotSpec.toNodeSpec(): LogicalNotNodeSpec = LogicalNotNodeSpec.of(this)
fun LogicalXorSpec.toNodeSpec(): LogicalXorNodeSpec = LogicalXorNodeSpec.of(this)
fun EqualitySpec.toNodeSpec(): EqualityNodeSpec = EqualityNodeSpec.of(this)
fun InequalitySpec.toNodeSpec(): InequalityNodeSpec = InequalityNodeSpec.of(this)
fun GreaterThanSpec.toNodeSpec(): GreaterThanNodeSpec = GreaterThanNodeSpec.of(this)
fun GreaterThanOrEqualSpec.toNodeSpec(): GreaterThanOrEqualNodeSpec = GreaterThanOrEqualNodeSpec.of(this)
fun LessThanSpec.toNodeSpec(): LessThanNodeSpec = LessThanNodeSpec.of(this)
fun LessThanOrEqualSpec.toNodeSpec(): LessThanOrEqualNodeSpec = LessThanOrEqualNodeSpec.of(this)
