package io.github.shakelang.shake.processor.program.mutable.code

import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.mutable.ShakeAssignable
import io.github.shakelang.shake.processor.program.mutable.ShakeClass
import io.github.shakelang.shake.processor.program.mutable.ShakeDeclaration
import io.github.shakelang.shake.processor.program.mutable.ShakeFunction

interface ShakeScope {
    val parent: ShakeScope?
    fun get(name: String): ShakeAssignable?
    fun set(value: ShakeDeclaration)
    fun getFunctions(name: String): List<ShakeFunction>
    fun setFunctions(function: ShakeFunction)
    fun getClass(name: String): ShakeClass?
    fun setClass(klass: ShakeClass)
    fun getInvokable(name: String): List<ShakeInvokable> {
        val functions = getFunctions(name)
        val variable = get(name)
        if(variable != null && variable is ShakeInvokable) {
            return listOf(variable, *functions.toTypedArray())
        }
        return functions
    }
    fun use(name: String) {
        val declaration = get(name)
    }
    val processor : ShakeCodeProcessor
}