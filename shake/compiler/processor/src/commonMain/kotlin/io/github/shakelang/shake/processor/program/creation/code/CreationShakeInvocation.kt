package io.github.shakelang.shake.processor.program.creation.code

import io.github.shakelang.shake.processor.program.creation.CreationShakeMethod
import io.github.shakelang.shake.processor.program.creation.CreationShakeType
import io.github.shakelang.shake.processor.program.creation.code.statements.CreationShakeStatement
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeValue
import io.github.shakelang.shake.processor.program.types.code.ShakeInvocation

class CreationShakeInvocation (
    override val callable: CreationShakeInvokable,
    override val arguments: List<CreationShakeValue>,
    override val parent: CreationShakeValue? = null
) : CreationShakeValue, CreationShakeStatement, ShakeInvocation {

    override val type: CreationShakeType
        get() = callable.returnType
    override val name get() = if(callable is CreationShakeMethod) callable.name else "anonymous"
    override val isAnonymous get() = callable !is CreationShakeMethod

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "invocation",
            "callable" to callable.qualifiedName,
            "arguments" to arguments.map { it.toJson() },
            "parent" to parent?.toJson()
        )
    }

}