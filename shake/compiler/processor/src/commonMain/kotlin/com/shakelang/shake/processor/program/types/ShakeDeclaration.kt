package com.shakelang.shake.processor.program.types

interface ShakeDeclaration : ShakeAssignable {
    val name: String
    val uniqueName: String
    val qualifiedName: String

    // fun use(scope: ShakeScope): ShakeUsage
    fun toJson(): Map<String, Any?>
}
