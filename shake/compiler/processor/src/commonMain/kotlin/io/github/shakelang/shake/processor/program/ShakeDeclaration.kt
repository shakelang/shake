package io.github.shakelang.shake.processor.program

import io.github.shakelang.shake.processor.program.code.ShakeScope
import io.github.shakelang.shake.processor.program.code.ShakeValue
import io.github.shakelang.shake.processor.program.code.values.ShakeUsage

interface ShakeDeclaration {
    val name: String
    val type: ShakeType
    fun use(scope: ShakeScope): ShakeUsage
}