package io.github.shakelang.shake.processor.program.code

import io.github.shakelang.shake.processor.program.ShakeParameter
import io.github.shakelang.shake.processor.program.ShakeType

abstract class ShakeInvokable (
    open val body: ShakeCode
) {
    lateinit var parameters: List<ShakeParameter>
        protected set
    abstract val returnType: ShakeType

    constructor(
        body: ShakeCode,
        parameters: List<ShakeParameter>
    ) : this(body) {
        this.parameters = parameters
    }
}