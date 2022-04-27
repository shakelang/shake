package io.github.shakelang.shake.processor.program.mutable.code

import io.github.shakelang.shake.processor.program.mutable.ShakeParameter
import io.github.shakelang.shake.processor.program.mutable.ShakeType

abstract class ShakeInvokable (
    open val body: ShakeCode
) {

    abstract val qualifiedName: String

    lateinit var parameters: List<ShakeParameter>
        protected set
    abstract val returnType: ShakeType

    constructor(
        body: ShakeCode,
        parameters: List<ShakeParameter>
    ) : this(body) {
        this.parameters = parameters
    }

    abstract fun toJson(): Map<String, Any?>
}