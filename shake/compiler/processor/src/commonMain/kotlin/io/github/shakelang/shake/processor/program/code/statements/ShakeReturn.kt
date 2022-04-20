package io.github.shakelang.shake.processor.program.code.statements

import io.github.shakelang.shake.processor.program.code.ShakeStatement
import io.github.shakelang.shake.processor.program.code.ShakeValue

class ShakeReturn (
    val value: ShakeValue?
) : ShakeStatement