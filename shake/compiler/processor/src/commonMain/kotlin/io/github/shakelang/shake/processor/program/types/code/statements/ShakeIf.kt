package io.github.shakelang.shake.processor.program.types.code.statements

import io.github.shakelang.shake.processor.program.types.code.ShakeCode
import io.github.shakelang.shake.processor.program.types.code.values.ShakeValue

interface ShakeIf : ShakeStatement {

    val condition: ShakeValue
    val body: ShakeCode
    val elseBody: ShakeCode?
}
