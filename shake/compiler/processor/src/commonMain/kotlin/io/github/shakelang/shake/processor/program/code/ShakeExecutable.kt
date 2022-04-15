package io.github.shakelang.shake.processor.program.code

import io.github.shakelang.shake.processor.program.ShakeParameter

abstract class ShakeExecutable(
    open val body: ShakeCode
) {
    lateinit var parameters: List<ShakeParameter>
        protected set
}