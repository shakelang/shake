package io.github.shakelang.shake.processor.program.types.code

import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.types.ShakeAssignable
import io.github.shakelang.shake.processor.program.types.ShakeClass
import io.github.shakelang.shake.processor.program.types.ShakeDeclaration
import io.github.shakelang.shake.processor.program.types.ShakeFunction

interface ShakeScope {
    val parent: ShakeScope?
    fun get(name: String): ShakeAssignable?
    fun set(value: ShakeDeclaration)
    fun getFunctions(name: String): List<ShakeFunction>
    fun setFunctions(function: ShakeFunction)
    fun getClass(name: String): ShakeClass?
    fun setClass(klass: ShakeClass)
    fun getInvokable(name: String): List<ShakeInvokable>
    fun use(name: String) 
}