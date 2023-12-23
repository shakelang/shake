package com.shakelang.shake.processor.program.creation.code.values

import com.shakelang.shake.processor.program.creation.CreationShakeParameter
import com.shakelang.shake.processor.program.creation.CreationShakeProject
import com.shakelang.shake.processor.program.creation.CreationShakeType
import com.shakelang.shake.processor.program.creation.code.CreationShakeCode
import com.shakelang.shake.processor.program.creation.code.CreationShakeInvokable
import com.shakelang.shake.processor.program.types.code.values.ShakeLambdaDeclaration

class CreationShakeLambdaDeclaration(
    override val project: CreationShakeProject,
    parameters: List<CreationShakeParameter>,
    override val returnType: CreationShakeType,
    override val content: CreationShakeCode
) : CreationShakeInvokable(content, parameters, returnType), CreationShakeValue, ShakeLambdaDeclaration {

    override val type: CreationShakeType = CreationShakeType.Lambda("lambda${parameters.size}", parameters, returnType)
    override val qualifiedName: String
        get() = "anonymous${parameters.size}"

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "lambda",
            "parameters" to parameters.map { it.toJson() },
            "returnType" to returnType.toJson(),
            "content" to content.toJson()
        )
    }
}
