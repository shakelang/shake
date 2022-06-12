package io.github.shakelang.shake.processor.program.types

import io.github.shakelang.shake.processor.program.types.code.ShakeScope
import io.github.shakelang.shake.processor.program.types.code.values.ShakeUsage

interface ShakeDeclaration {
    val name: String
    val type: ShakeType
    val qualifiedName: String
    //fun use(scope: ShakeScope): ShakeUsage
    fun toJson(): Map<String, Any?>
}