package io.github.shakelang.shake.processor.program.types

import io.github.shakelang.shake.processor.program.types.code.ShakeInvokable
import io.github.shakelang.shake.processor.program.types.code.ShakeScope


interface ShakeFunction : ShakeInvokable {
    val prj: ShakeProject
    val pkg: ShakePackage?
    val parentScope: ShakeScope
    val name: String
    val isStatic: Boolean
    val isFinal: Boolean
    val isAbstract: Boolean
    val isSynchronized: Boolean
    val isStrict: Boolean
    val isPrivate: Boolean
    val isProtected: Boolean
    val isPublic: Boolean

    override val qualifiedName: String
    override val returnType: ShakeType
    val scope : ShakeScope

    override fun toJson(): Map<String, Any?>
}