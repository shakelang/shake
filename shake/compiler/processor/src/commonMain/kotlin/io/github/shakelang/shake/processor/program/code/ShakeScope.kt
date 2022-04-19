package io.github.shakelang.shake.processor.program.code

import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.ShakeAssignable
import io.github.shakelang.shake.processor.program.ShakeClass
import io.github.shakelang.shake.processor.program.ShakeDeclaration
import io.github.shakelang.shake.processor.program.ShakeFunction

interface ShakeScope {
    val parent: ShakeScope?
    fun get(name: String): ShakeAssignable?
    fun set(name: String, value: ShakeDeclaration)
    fun getFunctions(name: String): List<ShakeFunction>
    fun setFunctions(name: String, function: ShakeFunction)
    fun getClass(name: String): ShakeClass?
    fun setClass(name: String, klass: ShakeClass)
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