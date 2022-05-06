package io.github.shakelang.shake.processor.program.creation

import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.creation.code.CreationShakeCode
import io.github.shakelang.shake.processor.program.creation.code.statements.CreationShakeVariableDeclaration
import io.github.shakelang.shake.processor.program.types.ShakeMethod

class CreationShakeMethod (
    override val clazz: CreationShakeClass,
    parentScope: CreationShakeScope,
    name: String,
    body: CreationShakeCode,
    isStatic: Boolean,
    isFinal: Boolean,
    isAbstract: Boolean,
    isSynchronized: Boolean,
    isStrict: Boolean,
    isPrivate: Boolean,
    isProtected: Boolean,
    isPublic: Boolean,
) : CreationShakeFunction(
    clazz.prj,
    clazz.pkg,
    parentScope,
    name,
    body,
    isStatic,
    isFinal,
    isAbstract,
    isSynchronized,
    isStrict,
    isPrivate,
    isProtected,
    isPublic
), ShakeMethod {

    override val scope = ShakeMethodScope()

    override fun processCode() {
        if(body is CreationShakeCode.ShakeLateProcessCode) body.process(scope)
    }

    override fun toJson(): Map<String, Any?> {
        return super.toJson() + mapOf(
            "type" to "method",
            "class" to clazz.name,
            "body" to body.toJson()
        )
    }

    inner class ShakeMethodScope : CreationShakeScope {
        override val parent: CreationShakeScope = if(isStatic) clazz.staticScope else clazz.instanceScope

        val variables = mutableListOf<CreationShakeVariableDeclaration>()

        override fun get(name: String): CreationShakeAssignable? {
            return variables.find { it.name == name } ?: parent.get(name)
        }

        override fun set(value: CreationShakeDeclaration) {
            if(value !is CreationShakeVariableDeclaration) throw IllegalArgumentException("Only variable declarations can be set in a method scope")
            if(variables.any { it.name == value.name }) throw IllegalArgumentException("Variable ${value.name} already exists in this scope")
            variables.add(value)
        }

        override fun getFunctions(name: String): List<CreationShakeFunction> {
            return parent.getFunctions(name)
        }

        override fun setFunctions(function: CreationShakeFunction) {
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