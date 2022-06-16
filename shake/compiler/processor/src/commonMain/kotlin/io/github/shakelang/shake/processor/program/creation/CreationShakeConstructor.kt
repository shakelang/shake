package io.github.shakelang.shake.processor.program.creation

import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.creation.code.CreationShakeCode
import io.github.shakelang.shake.processor.program.creation.code.statements.CreationShakeVariableDeclaration
import io.github.shakelang.shake.processor.program.types.ShakeConstructor
import io.github.shakelang.shake.processor.program.types.ShakeProject

open class CreationShakeConstructor (
    override val clazz: CreationShakeClass,
    override val body: CreationShakeCode,
    override val isStrict: Boolean,
    override val isPrivate: Boolean,
    override val isProtected: Boolean,
    override val isPublic: Boolean,
    override val name: String? = null
): ShakeConstructor {
    final override lateinit var parameters: List<CreationShakeParameter>
        private set

    constructor(
        clazz: CreationShakeClass,
        parameters: List<CreationShakeParameter>,
        body: CreationShakeCode,
        isStrict: Boolean,
        isPrivate: Boolean,
        isProtected: Boolean,
        isPublic: Boolean,
        name: String? = null
    ): this(clazz, body, isStrict, isPrivate, isProtected, isPublic, name) {
        this.parameters = parameters
    }

    override val scope: CreationShakeScope = ShakeConstructorScope()

    fun lateinitParameterTypes(names: List<String>): List<(CreationShakeType) -> CreationShakeType> {
        this.parameters = names.map {
            CreationShakeParameter(it)
        }
        return this.parameters.map {
            it.lateinitType()
        }
    }

    fun processCode() {
        if(body is CreationShakeCode.ShakeLateProcessCode) (body as CreationShakeCode.ShakeLateProcessCode).process(scope)
    }

    inner class ShakeConstructorScope : CreationShakeScope() {
        override val parent: CreationShakeScope = clazz.instanceScope
        override val project get() = clazz.prj
        val variables = mutableListOf<CreationShakeVariableDeclaration>()

        override fun get(name: String): CreationShakeAssignable? {
            return variables.find { it.name == name } ?: parent.get(name)
        }

        override fun set(value: CreationShakeDeclaration) {
            if(value !is CreationShakeVariableDeclaration) throw IllegalArgumentException("Only variable declarations can be set in a method scope")
            if(variables.any { it.name == value.name }) throw IllegalArgumentException("Variable ${value.name} already exists in this scope")
            variables.add(value)
        }

        override fun getFunctions(name: String): List<CreationShakeMethod> {
            return parent.getFunctions(name)
        }

        override fun setFunctions(function: CreationShakeMethod) {
            throw IllegalStateException("Cannot set function in method scope")
        }

        override fun getClass(name: String): CreationShakeClass? {
            return parent.getClass(name)
        }

        override fun setClass(klass: CreationShakeClass) {
            throw IllegalStateException("Cannot set class in method scope")
        }

        override val processor: ShakeCodeProcessor
            get() = parent.processor

    }
}