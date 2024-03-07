@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.nodes.spec.code

import com.shakelang.shake.shakespeare.nodes.spec.AbstractNodeSpec
import com.shakelang.shake.shakespeare.spec.Identifier
import com.shakelang.shake.shakespeare.spec.code.*

interface ValueNodeSpec : AbstractNodeSpec, ValueSpec

open class StringLiteralNodeSpec(value: String) : StringLiteralSpec(value), ValueNodeSpec
open class IntLiteralNodeSpec(value: Long) : IntLiteralSpec(value), ValueNodeSpec

open class FloatLiteralNodeSpec(value: Double) : FloatLiteralSpec(value), ValueNodeSpec
open class BooleanLiteralNodeSpec(value: Boolean) : BooleanLiteralSpec(value), ValueNodeSpec
open class NullLiteralNodeSpec : NullLiteralSpec(), ValueNodeSpec

open class VariableReferenceNodeSpec(value: Identifier) : VariableReferenceSpec(value), ValueNodeSpec
open class AdditionNodeSpec(left: ValueSpec, right: ValueSpec) : AdditionSpec(left, right), ValueNodeSpec
open class SubtractionNodeSpec(left: ValueSpec, right: ValueSpec) : SubtractionSpec(left, right), ValueNodeSpec
open class MultiplicationNodeSpec(left: ValueSpec, right: ValueSpec) : MultiplicationSpec(left, right), ValueNodeSpec
open class DivisionNodeSpec(left: ValueSpec, right: ValueSpec) : DivisionSpec(left, right), ValueNodeSpec
open class ModuloNodeSpec(left: ValueSpec, right: ValueSpec) : ModuloSpec(left, right), ValueNodeSpec
open class PowerNodeSpec(left: ValueSpec, right: ValueSpec) : PowerSpec(left, right), ValueNodeSpec
open class UnaryMinusNodeSpec(value: ValueSpec) : UnaryMinusSpec(value), ValueNodeSpec

typealias NegationNodeSpec = UnaryMinusNodeSpec
open class UnaryPlusNodeSpec(value: ValueSpec) : UnaryPlusSpec(value), ValueNodeSpec
open class LogicalAndNodeSpec(left: ValueSpec, right: ValueSpec) : LogicalAndSpec(left, right), ValueNodeSpec
open class LogicalOrNodeSpec(left: ValueSpec, right: ValueSpec) : LogicalOrSpec(left, right), ValueNodeSpec
open class LogicalNotNodeSpec(value: ValueSpec) : LogicalNotSpec(value), ValueNodeSpec
open class LogicalXorNodeSpec(left: ValueSpec, right: ValueSpec) : LogicalXorSpec(left, right), ValueNodeSpec
open class EqualityNodeSpec(left: ValueSpec, right: ValueSpec) : EqualitySpec(left, right), ValueNodeSpec
open class InequalityNodeSpec(left: ValueSpec, right: ValueSpec) : InequalitySpec(left, right), ValueNodeSpec
open class GreaterThanNodeSpec(left: ValueSpec, right: ValueSpec) : GreaterThanSpec(left, right), ValueNodeSpec
open class GreaterThanOrEqualNodeSpec(left: ValueSpec, right: ValueSpec) : GreaterThanOrEqualSpec(left, right), ValueNodeSpec
open class LessThanNodeSpec(left: ValueSpec, right: ValueSpec) : LessThanSpec(left, right), ValueNodeSpec
open class LessThanOrEqualNodeSpec(left: ValueSpec, right: ValueSpec) : LessThanOrEqualSpec(left, right), ValueNodeSpec
