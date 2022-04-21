package io.github.shakelang.shake.processor.program.code

import io.github.shakelang.shake.processor.program.ShakeFunction
import io.github.shakelang.shake.processor.program.ShakeType

class ShakeInvocation (
    val callable: ShakeInvokable,
    val arguments: List<ShakeValue>,
    val parent: ShakeValue? = null
) : ShakeValue, ShakeStatement {

    override val type: ShakeType
        get() = callable.returnType
    val name get() = if(callable is ShakeFunction) callable.name else "anonymous"
    val isAnonymous get() = callable !is ShakeFunction

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "invocation",
            "callable" to callable.qualifiedName,
            "arguments" to arguments.map { it.toJson() },
            "parent" to parent?.toJson()
        )
    }

}