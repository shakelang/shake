package com.shakelang.shake.processor.program.types.code

import io.github.shakelang.shake.processor.program.types.ShakeAssignable
import io.github.shakelang.shake.processor.program.types.code.statements.ShakeStatement
import io.github.shakelang.shake.processor.program.types.code.values.ShakeValue

interface ShakeAssignmentType : ShakeValue, ShakeStatement {
    val variable: ShakeAssignable
    val value: ShakeValue
}

interface ShakeAssignment : ShakeAssignmentType
interface ShakeAddAssignment : ShakeAssignmentType
interface ShakeSubAssignment : ShakeAssignmentType
interface ShakeMulAssignment : ShakeAssignmentType
interface ShakeDivAssignment : ShakeAssignmentType
interface ShakeModAssignment : ShakeAssignmentType
interface ShakePowAssignment : ShakeAssignmentType

interface ShakeMutateType : ShakeValue, ShakeStatement {
    val variable: ShakeAssignable
}

interface ShakeIncrementBefore : ShakeMutateType
interface ShakeIncrementAfter : ShakeMutateType
interface ShakeDecrementBefore : ShakeMutateType
interface ShakeDecrementAfter : ShakeMutateType
