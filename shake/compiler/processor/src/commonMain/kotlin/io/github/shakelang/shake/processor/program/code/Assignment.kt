package io.github.shakelang.shake.processor.program.code

import io.github.shakelang.shake.processor.program.ShakeAssignable
import io.github.shakelang.shake.processor.program.ShakeType

open class ShakeAssignment(
    val variable: ShakeAssignable,
    val value: ShakeValue,
    override val type: ShakeType
) : ShakeValue, ShakeStatement

open class ShakeAddAssignment(
    val variable: ShakeAssignable,
    val value: ShakeValue,
    override val type: ShakeType
) : ShakeValue, ShakeStatement

open class ShakeSubAssignment(
    val variable: ShakeAssignable,
    val value: ShakeValue,
    override val type: ShakeType
) : ShakeValue, ShakeStatement

open class ShakeMulAssignment(
    val variable: ShakeAssignable,
    val value: ShakeValue,
    override val type: ShakeType
) : ShakeValue, ShakeStatement

open class ShakeDivAssignment(
    val variable: ShakeAssignable,
    val value: ShakeValue,
    override val type: ShakeType
) : ShakeValue, ShakeStatement

open class ShakeModAssignment(
    val variable: ShakeAssignable,
    val value: ShakeValue,
    override val type: ShakeType
) : ShakeValue, ShakeStatement

open class ShakePowerAssignment(
    val variable: ShakeAssignable,
    val value: ShakeValue,
    override val type: ShakeType
) : ShakeValue, ShakeStatement

open class ShakeIncrementBefore(
    val variable: ShakeAssignable,
    override val type: ShakeType
) : ShakeValue, ShakeStatement

open class ShakeIncrementAfter(
    val variable: ShakeAssignable,
    override val type: ShakeType
) : ShakeValue, ShakeStatement

open class ShakeDecrementBefore(
    val variable: ShakeAssignable,
    override val type: ShakeType
) : ShakeValue, ShakeStatement

open class ShakeDecrementAfter(
    val variable: ShakeAssignable,
    override val type: ShakeType
) : ShakeValue, ShakeStatement