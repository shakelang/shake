package io.github.shakelang.shake.processor.program.creation.code

import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.creation.CreationShakeAssignable
import io.github.shakelang.shake.processor.program.creation.CreationShakeClass
import io.github.shakelang.shake.processor.program.creation.CreationShakeDeclaration
import io.github.shakelang.shake.processor.program.creation.CreationShakeFunction

interface CreationShakeScope {
    val parent: CreationShakeScope?
    fun get(name: String): CreationShakeAssignable?
    fun set(value: CreationShakeDeclaration)
    fun getFunctions(name: String): List<CreationShakeFunction>
    fun setFunctions(function: CreationShakeFunction)
    fun getClass(name: String): CreationShakeClass?
    fun setClass(klass: CreationShakeClass)
    fun getInvokable(name: String): List<CreationShakeInvokable> {
        val functions = getFunctions(name)
        val variable = get(name)
        if(variable != null && variable is CreationShakeInvokable) {
            return listOf(variable, *functions.toTypedArray())
        }
        return functions
    }
    fun use(name: String) {
        val declaration = get(name)
    }
    val processor : ShakeCodeProcessor
}