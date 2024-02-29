package com.shakelang.shake.processor.program.creation.code

import com.shakelang.shake.parser.node.statements.ShakeBlockNode
import com.shakelang.shake.processor.ShakeASTProcessor
import com.shakelang.shake.processor.ShakeProcessor
import com.shakelang.shake.processor.program.creation.*
import com.shakelang.shake.processor.program.creation.code.statements.CreationShakeStatement
import com.shakelang.shake.processor.program.creation.code.statements.CreationShakeVariableDeclaration
import com.shakelang.shake.processor.program.types.ShakeAssignable
import com.shakelang.shake.processor.program.types.code.ShakeCode

open class CreationShakeCode(
    override val statements: List<CreationShakeStatement>,
) : ShakeCode {

    open class ShakeLateProcessCode(
        open val tree: ShakeBlockNode,
    ) : CreationShakeCode(emptyList()) {

        override lateinit var statements: List<CreationShakeStatement>

        fun process(scope: CreationShakeScope) {
            if (this::statements.isInitialized) return
            val codeScope = LocalScope(scope)
            statements = tree.children.map {
                codeScope.processor.visitStatement(codeScope, it)
            }
        }
    }

    override fun toJson(): Map<String, Any> {
        return mapOf(
            "statements" to statements.map { it.toJson() },
        )
    }

    class LocalScope(
        override val parent: CreationShakeScope,
    ) : CreationShakeScope() {

        val locals = mutableListOf<CreationShakeVariableDeclaration>()

        override val project: CreationShakeProject
            get() = parent.project

        override fun getField(name: String): CreationShakeAssignable? {
            val local = locals.find { it.name == name }
            if (local != null) {
                debug("scope", "Found local $name in $this")
            } else {
                debug("scope", "Did not find local $name in $this")
            }
            return locals.find { it.name == name } ?: parent.getField(name)
        }

        override fun getFields(name: String): List<CreationShakeAssignable> {
            debug("scope", "Searching for fields $name in $this")
            return locals.filter { it.name == name } + parent.getFields(name)
        }

        override fun setField(value: CreationShakeDeclaration) {
            debug("scope", "Setting local ${value.name} in $this")
            if (value !is CreationShakeVariableDeclaration) throw IllegalArgumentException("Only variable declarations can be set in a method scope")
            if (locals.any { it.name == value.name }) throw IllegalArgumentException("Variable ${value.name} already exists in this scope")
            locals.add(value)
        }

        override fun getFunctions(name: String): List<CreationShakeMethod> {
            debug("scope", "Searching for method $name in $this (just redirecting to parent)")
            return parent.getFunctions(name)
        }

        override fun setFunctions(function: CreationShakeMethod) {
            throw IllegalArgumentException("Cannot set a function in a method scope")
        }

        override fun getClass(name: String): CreationShakeClass? {
            debug("scope", "Searching for class $name in $this (just redirecting to parent)")
            return parent.getClass(name)
        }

        override fun getClasses(name: String): List<CreationShakeClass> {
            debug("scope", "Searching for class $name in $this (just redirecting to parent)")
            return parent.getClasses(name)
        }

        override fun setClass(klass: CreationShakeClass) {
            throw IllegalArgumentException("Cannot set a class in a method scope")
        }

        override val processor: ShakeASTProcessor
            get() = parent.processor
        override val uniqueName: String
            get() = "local${hashCode()}"

        override fun getThis(): ShakeAssignable? {
            return parent.getThis()
        }

        override fun getThis(name: String): ShakeAssignable? {
            return parent.getThis(name)
        }

        override fun getSuper(): ShakeAssignable? {
            return parent.getSuper()
        }

        override fun getSuper(name: String): ShakeAssignable? {
            return parent.getSuper(name)
        }
    }

    companion object {

        val debug = ShakeProcessor.debug.child("creation", "code")

        fun fromTree(tree: ShakeBlockNode): CreationShakeCode {
            return ShakeLateProcessCode(tree)
        }
    }
}
