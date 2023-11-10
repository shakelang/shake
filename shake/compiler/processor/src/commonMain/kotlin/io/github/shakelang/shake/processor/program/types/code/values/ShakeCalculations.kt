package io.github.shakelang.shake.processor.program.types.code.values

interface ShakeConcatenation : ShakeValue {
    val left: ShakeValue
    val right: ShakeValue
}

interface ShakeCalculation : ShakeConcatenation

interface ShakeAddition : ShakeCalculation
interface ShakeSubtraction : ShakeCalculation
interface ShakeMultiplication : ShakeCalculation
interface ShakeDivision : ShakeCalculation
interface ShakeModulus : ShakeCalculation
interface ShakePower : ShakeCalculation

interface ShakeComparison : ShakeConcatenation

interface ShakeEquals : ShakeComparison
interface ShakeNotEquals : ShakeComparison
interface ShakeLessThan : ShakeComparison
interface ShakeLessThanOrEqual : ShakeComparison
interface ShakeGreaterThan : ShakeComparison
interface ShakeGreaterThanOrEqual : ShakeComparison

interface ShakeLogical
interface ShakeLogicalConcatenation : ShakeConcatenation, ShakeLogical

interface ShakeAnd : ShakeLogicalConcatenation
interface ShakeOr : ShakeLogicalConcatenation
interface ShakeXor : ShakeLogicalConcatenation
interface ShakeNot : ShakeLogical {
    val value: ShakeValue
}
