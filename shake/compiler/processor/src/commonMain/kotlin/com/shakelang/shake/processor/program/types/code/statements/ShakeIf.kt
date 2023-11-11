package com.shakelang.shake.processor.program.types.code.statements

import com.shakelang.shake.processor.program.types.code.ShakeCode
import com.shakelang.shake.processor.program.types.code.values.ShakeValue

interface ShakeIf : ShakeStatement {

    val condition: ShakeValue
    val body: ShakeCode
    val elseBody: ShakeCode?
}
