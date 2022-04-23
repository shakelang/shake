package io.github.shakelang.shake.processor.program.types

import io.github.shakelang.shake.processor.program.types.code.ShakeScope
import io.github.shakelang.shake.processor.program.types.code.values.ShakeValue

interface ShakeField : ShakeDeclaration, ShakeAssignable {
    val project: ShakeProject
    val pkg: ShakePackage?
    val parentScope: ShakeScope
    val isStatic: Boolean
    val isFinal: Boolean
    val isAbstract: Boolean
    val isPrivate: Boolean
    val isProtected: Boolean
    val isPublic: Boolean
    val initialValue: ShakeValue?
}