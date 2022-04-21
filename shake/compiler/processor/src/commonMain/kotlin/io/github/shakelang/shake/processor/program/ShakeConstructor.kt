package io.github.shakelang.shake.processor.program

import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.code.ShakeCode
import io.github.shakelang.shake.processor.program.code.ShakeScope
import io.github.shakelang.shake.processor.program.code.statements.ShakeVariableDeclaration

open class ShakeConstructor (
    val clazz: ShakeClass,
    val body: ShakeCode,
    val isStrict: Boolean,
    val isPrivate: Boolean,
    val isProtected: Boolean,
    val isPublic: Boolean,
    val name: String? = null
) {
    lateinit var parameters: List<ShakeParameter>
        private set

    constructor(
        clazz: ShakeClass,
        parameters: List<ShakeParameter>,
        body: ShakeCode,
        isStrict: Boolean,
        isPrivate: Boolean,
        isProtected: Boolean,
        isPublic: Boolean,
        name: String? = null
    ): this(clazz, body, isStrict, isPrivate, isProtected, isPublic, name) {
        this.parameters = parameters
    }

    val scope: ShakeScope = ShakeConstructorScope()

    fun lateinitParameterTypes(names: List<String>): List<(ShakeType) -> ShakeType> {
        this.parameters = names.map {
            ShakeParameter(it)
        }
        return this.parameters.map {
            it.lateinitType()
        }
    }

    fun processCode() {
        if(body is ShakeCode.ShakeLateProcessCode) body.process(scope)
    }

    open fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "constructor",
            "name" to name,
            "parameters" to parameters.map { it.toJson() },
            "body" to body.toJson(),
            "isStrict" to isStrict,
            "isPrivate" to isPrivate,
            "isProtected" to isProtected,
            "isPublic" to isPublic
        )
    }

    inner class ShakeConstructorScope : ShakeScope {
        override val parent: ShakeScope
            get() = clazz.instanceScope

        val variables = mutableListOf<ShakeVariableDeclaration>()

        override fun get(name: String): ShakeAssignable? {
            return variables.find { it.name == name } ?: parent.get(name)
        }

        override fun set(value: ShakeDeclaration) {
            if(value !is ShakeVariableDeclaration) throw IllegalArgumentException("Only variable declarations can be set in a method scope")
            if(variables.any { it.name == value.name }) throw IllegalArgumentException("Variable ${value.name} already exists in this scope")
            variables.add(value)
        }

        override fun getFunctions(name: String): List<ShakeFunction> {
            return parent.getFunctions(name)
        }

        override fun setFunctions(function: ShakeFunction) {
            throw IllegalStateException("Cannot set function in method scope")
        }

        override fun getClass(name: String): ShakeClass? {
            return parent.getClass(name)
        }

        override fun setClass(klass: ShakeClass) {
            throw IllegalStateException("Cannot set class in method scope")
        }

        override val processor: ShakeCodeProcessor
            get() = parent.processor

    }
}