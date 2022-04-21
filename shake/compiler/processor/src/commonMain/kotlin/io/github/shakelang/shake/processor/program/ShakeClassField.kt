package io.github.shakelang.shake.processor.program

import io.github.shakelang.shake.processor.program.code.values.ShakeFieldUsage
import io.github.shakelang.shake.processor.program.code.ShakeScope
import io.github.shakelang.shake.processor.program.code.ShakeValue
import io.github.shakelang.shake.processor.program.code.values.ShakeUsage

class ShakeClassField (
    val clazz: ShakeClass,
    parentScope: ShakeScope,
    name: String,
    isStatic: Boolean,
    isFinal: Boolean,
    isAbstract: Boolean,
    isPrivate: Boolean,
    isProtected: Boolean,
    isPublic: Boolean,
    initialValue: ShakeValue? = null
): ShakeField(
    clazz.prj,
    clazz.pkg,
    parentScope,
    name,
    isStatic,
    isFinal,
    isAbstract,
    isPrivate,
    isProtected,
    isPublic,
    initialValue
) {
    override val qualifiedName: String
        get() = "${clazz.qualifiedName}.$name"


    override fun use(scope: ShakeScope): ShakeUsage {
        return ShakeFieldUsage(scope, this)
    }

    override fun processCode() {
        //TODO: process value
    }
}