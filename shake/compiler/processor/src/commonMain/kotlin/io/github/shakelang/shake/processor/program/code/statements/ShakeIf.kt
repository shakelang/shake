package io.github.shakelang.shake.processor.program.code.statements

import io.github.shakelang.shake.processor.program.code.ShakeCode
import io.github.shakelang.shake.processor.program.code.ShakeStatement
import io.github.shakelang.shake.processor.program.code.ShakeValue

class ShakeIf (

    val condition: ShakeValue,
    val body: ShakeCode,
    val elseBody: ShakeCode? = null

) : ShakeStatement