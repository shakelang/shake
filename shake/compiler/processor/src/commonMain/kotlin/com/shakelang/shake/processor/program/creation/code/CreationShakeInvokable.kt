package com.shakelang.shake.processor.program.creation.code

import com.shakelang.shake.processor.program.creation.CreationShakeParameter
import com.shakelang.shake.processor.program.creation.CreationShakeType
import com.shakelang.shake.processor.program.types.code.ShakeInvokable

abstract class CreationShakeInvokable(
    override val body: CreationShakeCode?,
    override val parameters: List<CreationShakeParameter>,
    override val returnType: CreationShakeType,
) : ShakeInvokable {

    abstract override val qualifiedName: String

    abstract override fun toJson(): Map<String, Any?>
}
