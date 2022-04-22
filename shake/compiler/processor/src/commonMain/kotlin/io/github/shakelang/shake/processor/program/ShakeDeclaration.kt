package io.github.shakelang.shake.processor.program

import io.github.shakelang.shake.processor.program.code.ShakeScope
import io.github.shakelang.shake.processor.program.code.values.ShakeUsage

interface ShakeDeclaration {
    val name: String
    val type: ShakeType
    val qualifiedName: String
    fun use(scope: ShakeScope): ShakeUsage
    fun toJson(): Map<String, Any?>
}