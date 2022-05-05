package io.github.shakelang.shake.processor.program.creation.code.values

import io.github.shakelang.shake.processor.program.creation.CreationShakeParameter
import io.github.shakelang.shake.processor.program.creation.CreationShakeType
import io.github.shakelang.shake.processor.program.creation.code.CreationShakeCode
import io.github.shakelang.shake.processor.program.creation.code.CreationShakeInvokable

class CreationShakeLambdaDeclaration(
    parameters: List<CreationShakeParameter>,
    override val returnType: CreationShakeType,
    val content: CreationShakeCode,
) : CreationShakeInvokable(content, parameters), CreationShakeValue {

    override val type: CreationShakeType = CreationShakeType.Lambda("lambda${parameters.size}", parameters, returnType)
    override val qualifiedName: String
        get() = "anonymous${parameters.size}"

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "lambda",
            "parameters" to parameters.map { it.toJson() },
            "returnType" to returnType.toJson(),
            "content" to content.toJson(),
        )
    }
}