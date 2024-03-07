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

/**
 * A [ValuedAssignmentSpec] is a StatementSpec and a ValueNodeSpec at the same time
 * @since 0.1.0
 */
interface ValuedAssignmentSpec : StatementSpec, ValueNodeSpec

open class VariableAssignmentNodeSpec(
    name: Identifier,
    value: ValueNodeSpec,
) : VariableAssignmentSpec(
    name,
    value,
),
    AbstractNodeSpec

open class VariableAdditionAssignmentNodeSpec(
    name: Identifier,
    value: ValueNodeSpec,
) : VariableAdditionAssignmentSpec(
    name,
    value,
),
    AbstractNodeSpec

open class VariableSubtractionAssignmentNodeSpec(
    name: Identifier,
    value: ValueNodeSpec,
) : VariableSubtractionAssignmentSpec(
    name,
    value,
),
    AbstractNodeSpec

open class VariableMultiplicationAssignmentNodeSpec(
    name: Identifier,
    value: ValueNodeSpec,
) : VariableMultiplicationAssignmentSpec(
    name,
    value,
),
    AbstractNodeSpec

open class VariableDivisionAssignmentNodeSpec(
    name: Identifier,
    value: ValueNodeSpec,
) : VariableDivisionAssignmentSpec(
    name,
    value,
),
    AbstractNodeSpec

open class VariableModuloAssignmentNodeSpec(
    name: Identifier,
    value: ValueNodeSpec,
) : VariableModuloAssignmentSpec(
    name,
    value,
),
    AbstractNodeSpec

open class VariablePowerAssignmentNodeSpec(
    name: Identifier,
    value: ValueNodeSpec,
) : VariablePowerAssignmentSpec(
    name,
    value,
),
    AbstractNodeSpec

open class VariableBitwiseAndAssignmentNodeSpec(
    name: Identifier,
    value: ValueNodeSpec,
) : VariableBitwiseAndAssignmentSpec(
    name,
    value,
),
    AbstractNodeSpec

open class VariableBitwiseOrAssignmentNodeSpec(
    name: Identifier,
    value: ValueNodeSpec,
) : VariableBitwiseOrAssignmentSpec(
    name,
    value,
),
    AbstractNodeSpec

open class VariableBitwiseXorAssignmentNodeSpec(
    name: Identifier,
    value: ValueNodeSpec,
) : VariableBitwiseXorAssignmentSpec(
    name,
    value,
),
    AbstractNodeSpec

open class VariableIncrementBeforeNodeSpec(
    name: Identifier,
) : VariableIncrementBeforeSpec(
    name,
),
    AbstractNodeSpec

open class VariableIncrementAfterNodeSpec(
    name: Identifier,
) : VariableIncrementAfterSpec(
    name,
),
    AbstractNodeSpec

open class VariableDecrementBeforeNodeSpec(
    name: Identifier,
) : VariableDecrementBeforeSpec(
    name,
),
    AbstractNodeSpec

open class VariableDecrementAfterNodeSpec(
    name: Identifier,
) : VariableDecrementAfterSpec(
    name,
),
    AbstractNodeSpec

open class FunctionCallNodeSpec(
    name: Identifier,
    arguments: List<ValueNodeSpec>,
) : FunctionCallSpec(
    name,
    arguments,
),
    AbstractNodeSpec
