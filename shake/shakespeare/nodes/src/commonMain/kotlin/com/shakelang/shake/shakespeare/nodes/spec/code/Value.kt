@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.nodes.spec.code

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.shakespeare.nodes.spec.AbstractNodeSpec
import com.shakelang.shake.shakespeare.nodes.spec.NodeContext
import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.NamespaceSpec
import com.shakelang.shake.shakespeare.spec.code.*

interface ValueNodeSpec : AbstractNodeSpec, ValueSpec {
    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeValuedNode
}

open class StringLiteralNodeSpec(value: String) : StringLiteralSpec(value), ValueNodeSpec {
    companion object {
        fun of(spec: StringLiteralSpec): StringLiteralNodeSpec = StringLiteralNodeSpec(spec.value)
    }
}

open class IntLiteralNodeSpec(value: Long) : IntLiteralSpec(value), ValueNodeSpec {
    companion object {
        fun of(spec: IntLiteralSpec): IntLiteralNodeSpec = IntLiteralNodeSpec(spec.value)
    }
}

open class FloatLiteralNodeSpec(value: Double) : FloatLiteralSpec(value), ValueNodeSpec {
    companion object {
        fun of(spec: FloatLiteralSpec): FloatLiteralNodeSpec = FloatLiteralNodeSpec(spec.value)
    }
}
open class BooleanLiteralNodeSpec(value: Boolean) : BooleanLiteralSpec(value), ValueNodeSpec {
    companion object {
        fun of(spec: BooleanLiteralSpec): BooleanLiteralNodeSpec = BooleanLiteralNodeSpec(spec.value)
    }
}
open class NullLiteralNodeSpec : NullLiteralSpec(), ValueNodeSpec {
    companion object {
        fun of(spec: NullLiteralSpec): NullLiteralNodeSpec = NullLiteralNodeSpec()
    }
}

open class VariableReferenceNodeSpec(value: NamespaceSpec) : VariableReferenceSpec(value), ValueNodeSpec {
    companion object {
        fun of(spec: VariableReferenceSpec): VariableReferenceNodeSpec = VariableReferenceNodeSpec(spec.name)
    }
}
open class AdditionNodeSpec(left: ValueSpec, right: ValueSpec) : AdditionSpec(left, right), ValueNodeSpec {
    companion object {
        fun of(spec: AdditionSpec): AdditionNodeSpec = AdditionNodeSpec(spec.left, spec.right)
    }
}
open class SubtractionNodeSpec(left: ValueSpec, right: ValueSpec) : SubtractionSpec(left, right), ValueNodeSpec {
    companion object {
        fun of(spec: SubtractionSpec): SubtractionNodeSpec = SubtractionNodeSpec(spec.left, spec.right)
    }
}
open class MultiplicationNodeSpec(left: ValueSpec, right: ValueSpec) : MultiplicationSpec(left, right), ValueNodeSpec {
    companion object {
        fun of(spec: MultiplicationSpec): MultiplicationNodeSpec = MultiplicationNodeSpec(spec.left, spec.right)
    }
}
open class DivisionNodeSpec(left: ValueSpec, right: ValueSpec) : DivisionSpec(left, right), ValueNodeSpec {
    companion object {
        fun of(spec: DivisionSpec): DivisionNodeSpec = DivisionNodeSpec(spec.left, spec.right)
    }
}
open class ModuloNodeSpec(left: ValueSpec, right: ValueSpec) : ModuloSpec(left, right), ValueNodeSpec {
    companion object {
        fun of(spec: ModuloSpec): ModuloNodeSpec = ModuloNodeSpec(spec.left, spec.right)
    }
}
open class PowerNodeSpec(left: ValueSpec, right: ValueSpec) : PowerSpec(left, right), ValueNodeSpec {
    companion object {
        fun of(spec: PowerSpec): PowerNodeSpec = PowerNodeSpec(spec.left, spec.right)
    }
}
open class UnaryMinusNodeSpec(value: ValueSpec) : UnaryMinusSpec(value), ValueNodeSpec {
    companion object {
        fun of(spec: UnaryMinusSpec): UnaryMinusNodeSpec = UnaryMinusNodeSpec(spec.value)
    }
}

typealias NegationNodeSpec = UnaryMinusNodeSpec
open class UnaryPlusNodeSpec(value: ValueSpec) : UnaryPlusSpec(value), ValueNodeSpec {
    companion object {
        fun of(spec: UnaryPlusSpec): UnaryPlusNodeSpec = UnaryPlusNodeSpec(spec.value)
    }
}
open class LogicalAndNodeSpec(left: ValueSpec, right: ValueSpec) : LogicalAndSpec(left, right), ValueNodeSpec {
    companion object {
        fun of(spec: LogicalAndSpec): LogicalAndNodeSpec = LogicalAndNodeSpec(spec.left, spec.right)
    }
}
open class LogicalOrNodeSpec(left: ValueSpec, right: ValueSpec) : LogicalOrSpec(left, right), ValueNodeSpec {
    companion object {
        fun of(spec: LogicalOrSpec): LogicalOrNodeSpec = LogicalOrNodeSpec(spec.left, spec.right)
    }
}
open class LogicalNotNodeSpec(value: ValueSpec) : LogicalNotSpec(value), ValueNodeSpec {
    companion object {
        fun of(spec: LogicalNotSpec): LogicalNotNodeSpec = LogicalNotNodeSpec(spec.value)
    }
}
open class LogicalXorNodeSpec(left: ValueSpec, right: ValueSpec) : LogicalXorSpec(left, right), ValueNodeSpec {
    companion object {
        fun of(spec: LogicalXorSpec): LogicalXorNodeSpec = LogicalXorNodeSpec(spec.left, spec.right)
    }
}
open class EqualityNodeSpec(left: ValueSpec, right: ValueSpec) : EqualitySpec(left, right), ValueNodeSpec {
    companion object {
        fun of(spec: EqualitySpec): EqualityNodeSpec = EqualityNodeSpec(spec.left, spec.right)
    }
}
open class InequalityNodeSpec(left: ValueSpec, right: ValueSpec) : InequalitySpec(left, right), ValueNodeSpec {
    companion object {
        fun of(spec: InequalitySpec): InequalityNodeSpec = InequalityNodeSpec(spec.left, spec.right)
    }
}
open class GreaterThanNodeSpec(left: ValueSpec, right: ValueSpec) : GreaterThanSpec(left, right), ValueNodeSpec {
    companion object {
        fun of(spec: GreaterThanSpec): GreaterThanNodeSpec = GreaterThanNodeSpec(spec.left, spec.right)
    }
}
open class GreaterThanOrEqualNodeSpec(left: ValueSpec, right: ValueSpec) : GreaterThanOrEqualSpec(left, right), ValueNodeSpec {
    companion object {
        fun of(spec: GreaterThanOrEqualSpec): GreaterThanOrEqualNodeSpec = GreaterThanOrEqualNodeSpec(spec.left, spec.right)
    }
}
open class LessThanNodeSpec(left: ValueSpec, right: ValueSpec) : LessThanSpec(left, right), ValueNodeSpec {
    companion object {
        fun of(spec: LessThanSpec): LessThanNodeSpec = LessThanNodeSpec(spec.left, spec.right)
    }
}
open class LessThanOrEqualNodeSpec(left: ValueSpec, right: ValueSpec) : LessThanOrEqualSpec(left, right), ValueNodeSpec {
    companion object {
        fun of(spec: LessThanOrEqualSpec): LessThanOrEqualNodeSpec = LessThanOrEqualNodeSpec(spec.left, spec.right)
    }
}
