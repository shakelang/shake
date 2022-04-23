package io.github.shakelang.shake.processor.program.types

import io.github.shakelang.shake.processor.program.types.code.ShakeCode
import io.github.shakelang.shake.processor.program.types.code.ShakeScope

interface ShakeConstructor {
    val clazz: ShakeClass
    val body: ShakeCode
    val isStrict: Boolean
    val isPrivate: Boolean
    val isProtected: Boolean
    val isPublic: Boolean
    val name: String?
    val parameters: List<ShakeParameter>
    val scope: ShakeScope

    fun toJson(): Map<String, Any?>
}