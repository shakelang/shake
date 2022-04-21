package io.github.shakelang.shake.processor.program

import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.code.ShakeCode
import io.github.shakelang.shake.processor.program.code.ShakeScope
import io.github.shakelang.shake.processor.program.code.statements.ShakeVariableDeclaration

class ShakeMethod (
    val clazz: ShakeClass,
    parentScope: ShakeScope,
    name: String,
    body: ShakeCode,
    isStatic: Boolean,
    isFinal: Boolean,
    isAbstract: Boolean,
    isSynchronized: Boolean,
    isStrict: Boolean,
    isPrivate: Boolean,
    isProtected: Boolean,
    isPublic: Boolean,
) : ShakeFunction(
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
) {

    override val scope = ShakeMethodScope()

    override fun processCode() {
        if(body is ShakeCode.ShakeLateProcessCode) body.process(scope)
    }

    override fun toJson(): Map<String, Any?> {
        return super.toJson() + mapOf(
            "type" to "method",
            "class" to clazz.name,
            "body" to body.toJson()
        )
    }

    inner class ShakeMethodScope : ShakeScope {
        override val parent: ShakeScope
            get() = if(isStatic) clazz.staticScope else clazz.instanceScope

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