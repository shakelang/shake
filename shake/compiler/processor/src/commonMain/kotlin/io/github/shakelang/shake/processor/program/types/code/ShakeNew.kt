package io.github.shakelang.shake.processor.program.types.code

import io.github.shakelang.shake.processor.program.types.ShakeConstructor
import io.github.shakelang.shake.processor.program.types.code.statements.ShakeStatement
import io.github.shakelang.shake.processor.program.types.code.values.ShakeValue

interface ShakeNew : ShakeValue, ShakeStatement {

    val reference: ShakeConstructor
    val arguments: List<ShakeValue>
    val parent: ShakeValue?
    val name: String

}