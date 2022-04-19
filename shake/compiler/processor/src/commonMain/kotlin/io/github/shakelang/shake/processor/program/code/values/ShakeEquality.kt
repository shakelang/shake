package io.github.shakelang.shake.processor.program.code.values

import io.github.shakelang.shake.processor.program.ShakeType
import io.github.shakelang.shake.processor.program.code.ShakeValue


open class ShakeEquals(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue

open class ShakeNotEquals(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue

open class ShakeLessThan(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue

open class ShakeLessThanOrEqual(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue

open class ShakeGreaterThan(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue

open class ShakeGreaterThanOrEqual(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue

open class ShakeAnd(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue

open class ShakeOr(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue

open class ShakeXor(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue

open class ShakeNot(
    val value: ShakeValue,
    override val type: ShakeType
) : ShakeValue