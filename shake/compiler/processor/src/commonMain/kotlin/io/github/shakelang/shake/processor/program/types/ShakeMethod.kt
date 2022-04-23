package io.github.shakelang.shake.processor.program.types

import io.github.shakelang.shake.processor.program.types.code.ShakeScope

interface ShakeMethod : ShakeFunction {
    val clazz: ShakeClass
    override val scope : ShakeScope
}