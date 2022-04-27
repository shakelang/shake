package io.github.shakelang.shake.processor.program.immutable

import io.github.shakelang.shake.processor.program.types.ShakeParameter
import io.github.shakelang.shake.processor.program.types.code.ShakeCode
import io.github.shakelang.shake.processor.program.types.code.ShakeInvokable

abstract class ImmutableShakeInvokable (
    override val body: ShakeCode
) : ShakeInvokable {

    override lateinit var parameters: List<ShakeParameter>
        protected set

    constructor(
        body: ShakeCode,
        parameters: List<ShakeParameter>
    ) : this(body) {
        this.parameters = parameters
    }
}