package io.github.shakelang.shake.processor.program.creation.code

import io.github.shakelang.shake.processor.program.creation.CreationShakeMethod
import io.github.shakelang.shake.processor.program.creation.code.statements.CreationShakeStatement
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeValue
import io.github.shakelang.shake.processor.program.types.ShakeProject
import io.github.shakelang.shake.processor.program.types.ShakeType
import io.github.shakelang.shake.processor.program.types.code.ShakeInvocation
import io.github.shakelang.shake.processor.program.types.code.ShakeInvokable

class CreationShakeInvocation (
    override val project: ShakeProject,
    override val callable: ShakeInvokable,
    override val arguments: List<CreationShakeValue>,
    override val parent: CreationShakeValue? = null
) : CreationShakeValue, CreationShakeStatement, ShakeInvocation {

    override val type: ShakeType
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