package io.github.shakelang.shake.processor.program.creation

import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.creation.code.CreationShakeInvokable
import io.github.shakelang.shake.processor.program.types.code.ShakeScope

interface CreationShakeScope : ShakeScope {
    override val parent: CreationShakeScope?
    override fun get(name: String): CreationShakeAssignable?
    fun set(value: CreationShakeDeclaration)
    override fun getFunctions(name: String): List<CreationShakeFunction>
    fun setFunctions(function: CreationShakeFunction)
    override fun getClass(name: String): CreationShakeClass?
    fun setClass(klass: CreationShakeClass)
    override fun getInvokable(name: String): List<CreationShakeInvokable> {
        val functions = getFunctions(name)
        val variable = get(name)
        if(variable != null && variable is CreationShakeInvokable) {
            return listOf(variable, *functions.toTypedArray())
        }
        return functions
    }
    override fun use(name: String) {
        val declaration = get(name)
    }
    val processor : ShakeCodeProcessor
}