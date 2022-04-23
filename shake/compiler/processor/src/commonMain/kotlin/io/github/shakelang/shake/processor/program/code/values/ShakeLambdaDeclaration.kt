package io.github.shakelang.shake.processor.program.code.values

import io.github.shakelang.shake.processor.program.ShakeParameter
import io.github.shakelang.shake.processor.program.ShakeType
import io.github.shakelang.shake.processor.program.code.ShakeCode
import io.github.shakelang.shake.processor.program.code.ShakeInvokable

class ShakeLambdaDeclaration(
    parameters: List<ShakeParameter>,
    override val returnType: ShakeType,
    val content: ShakeCode,
) : ShakeInvokable(content, parameters), ShakeValue {

    override val type: ShakeType = ShakeType.Lambda("lambda${parameters.size}", parameters, returnType)
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