package com.shakelang.shake.processor.program.types.code

import io.github.shakelang.shake.processor.program.types.code.statements.ShakeStatement

interface ShakeCode {
    val statements: List<ShakeStatement>
    fun toJson(): Map<String, Any>
}
