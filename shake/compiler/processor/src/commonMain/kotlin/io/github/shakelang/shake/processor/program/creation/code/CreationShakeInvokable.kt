package io.github.shakelang.shake.processor.program.creation.code

import io.github.shakelang.shake.processor.program.creation.CreationShakeParameter
import io.github.shakelang.shake.processor.program.creation.CreationShakeType

abstract class CreationShakeInvokable (
    open val body: CreationShakeCode
) {

    abstract val qualifiedName: String

    lateinit var parameters: List<CreationShakeParameter>
        protected set
    abstract val returnType: CreationShakeType

    constructor(
        body: CreationShakeCode,
        parameters: List<CreationShakeParameter>
    ) : this(body) {
        this.parameters = parameters
    }

    abstract fun toJson(): Map<String, Any?>
}