package io.github.shakelang.shake.processor.program.code.values

import io.github.shakelang.shake.processor.program.ShakeType
import io.github.shakelang.shake.processor.program.code.ShakeValue


open class ShakeAddition(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue

open class ShakeSubtraction(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue

open class ShakeMultiplication(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue

open class ShakeDivision(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue

open class ShakeModulo(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue

open class ShakePower(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue