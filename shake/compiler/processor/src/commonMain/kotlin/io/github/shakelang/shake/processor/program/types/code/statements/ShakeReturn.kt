package io.github.shakelang.shake.processor.program.types.code.statements

import io.github.shakelang.shake.processor.program.types.code.values.ShakeValue

interface ShakeReturn {
    val value: ShakeValue?
}