package io.github.shakelang.shake.processor.program

import io.github.shakelang.shake.processor.program.code.values.ShakeFieldUsage
import io.github.shakelang.shake.processor.program.code.ShakeScope
import io.github.shakelang.shake.processor.program.code.values.ShakeUsage

class ShakeClassField (
    name: String,
    isStatic: Boolean,
    isFinal: Boolean,
    isAbstract: Boolean,
    isPrivate: Boolean,
    isProtected: Boolean,
    isPublic: Boolean,
): ShakeField(
    name,
    isStatic,
    isFinal,
    isAbstract,
    isPrivate,
    isProtected,
    isPublic
) {
    lateinit var clazz: ShakeClass
        private set

    override fun use(scope: ShakeScope): ShakeUsage {
        return ShakeFieldUsage(scope, this)
    }

    override fun processCode() {
        //TODO: process value
    }
}