package io.github.shakelang.shake.processor.program.creation

import io.github.shakelang.shake.processor.program.creation.code.CreationShakeScope
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeUsage

interface CreationShakeDeclaration {
    val name: String
    val type: CreationShakeType
    val qualifiedName: String
    fun use(scope: CreationShakeScope): CreationShakeUsage
    fun toJson(): Map<String, Any?>
}