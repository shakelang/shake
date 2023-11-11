package com.shakelang.shake.processor.program.creation.code

import com.shakelang.shake.processor.program.creation.CreationShakeParameter
import com.shakelang.shake.processor.program.creation.CreationShakeType
import com.shakelang.shake.processor.program.types.code.ShakeInvokable

abstract class CreationShakeInvokable(
    override val body: CreationShakeCode?
) : ShakeInvokable {

    abstract override val qualifiedName: String

    override lateinit var parameters: List<CreationShakeParameter>
        protected set
    abstract override val returnType: CreationShakeType

    constructor(
        body: CreationShakeCode,
        parameters: List<CreationShakeParameter>
    ) : this(body) {
        this.parameters = parameters
    }

    abstract override fun toJson(): Map<String, Any?>
}
