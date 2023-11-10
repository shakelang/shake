package io.github.shakelang.shake.processor.program.creation

import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeUsage
import io.github.shakelang.shake.processor.program.types.ShakeDeclaration
import io.github.shakelang.shake.processor.program.types.ShakeType

interface CreationShakeDeclaration : ShakeDeclaration {
    override val name: String
    override val type: ShakeType
    override val qualifiedName: String
    fun use(scope: CreationShakeScope): CreationShakeUsage
    override fun toJson(): Map<String, Any?>
}
