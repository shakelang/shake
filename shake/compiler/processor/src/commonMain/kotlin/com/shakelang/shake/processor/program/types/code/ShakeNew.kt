package com.shakelang.shake.processor.program.types.code

import com.shakelang.shake.processor.program.types.ShakeConstructor
import com.shakelang.shake.processor.program.types.code.statements.ShakeStatement
import com.shakelang.shake.processor.program.types.code.values.ShakeValue

interface ShakeNew : ShakeValue, ShakeStatement {

    val reference: ShakeConstructor
    val arguments: List<ShakeValue>
    val parent: ShakeValue?
    val name: String
}
