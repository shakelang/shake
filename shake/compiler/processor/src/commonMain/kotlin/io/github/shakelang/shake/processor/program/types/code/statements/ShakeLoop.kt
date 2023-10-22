package io.github.shakelang.shake.processor.program.types.code.statements

import io.github.shakelang.shake.processor.program.types.code.ShakeCode
import io.github.shakelang.shake.processor.program.types.code.values.ShakeValue

interface ShakeWhile : ShakeStatement {
    val condition: ShakeValue
    val body: ShakeCode
}

interface ShakeDoWhile : ShakeStatement {
    val body: ShakeCode
    val condition: ShakeValue
}

interface ShakeFor : ShakeStatement {
    val init: ShakeStatement
    val condition: ShakeValue
    val update: ShakeStatement
    val body: ShakeCode
}