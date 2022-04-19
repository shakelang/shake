package io.github.shakelang.shake.processor.program.code.statements

import io.github.shakelang.shake.processor.program.code.ShakeCode
import io.github.shakelang.shake.processor.program.code.ShakeStatement
import io.github.shakelang.shake.processor.program.code.ShakeValue

class ShakeWhile (
    val condition: ShakeValue,
    val body: ShakeCode
) : ShakeStatement

class ShakeDoWhile (
    val condition: ShakeValue,
    val body: ShakeCode
) : ShakeStatement

class ShakeFor(
    val init: ShakeStatement,
    val condition: ShakeValue,
    val update: ShakeStatement,
    val body: ShakeCode
) : ShakeStatement