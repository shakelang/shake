package com.shakelang.shake.processor.program.creation

import com.shakelang.shake.parser.node.ShakeAccessDescriber
import com.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import com.shakelang.shake.processor.ShakeASTProcessor
import com.shakelang.shake.processor.ShakeProcessor
import com.shakelang.shake.processor.program.creation.code.CreationShakeCode
import com.shakelang.shake.processor.program.creation.code.CreationShakeInvokable
import com.shakelang.shake.processor.program.creation.code.statements.CreationShakeVariableDeclaration
import com.shakelang.shake.processor.program.types.ShakeMethod
import com.shakelang.shake.processor.program.types.ShakeType

class CreationShakeMethod(
    override val prj: CreationShakeProject,
    override val pkg: CreationShakePackage?,
    override val clazz: CreationShakeClass?,
    override val parentScope: CreationShakeScope,
    override val name: String,
    body: CreationShakeCode?,
    override val isStatic: Boolean,
    override val isFinal: Boolean,
    override val isAbstract: Boolean,
    override val isSynchronized: Boolean,
    override val isStrict: Boolean,
    override val isPrivate: Boolean,
    override val isProtected: Boolean,
    override val isPublic: Boolean,
    override val isNative: Boolean,
    override val isOperator: Boolean,
    returnType: CreationShakeType,
    parameters: List<CreationShakeParameter>,
    override val expanding: ShakeType?

) : CreationShakeInvokable(
    body,
    parameters,
    returnType
),
    ShakeMethod {

    override val qualifiedName: String
        get() = super.qualifiedName

    override val scope: CreationShakeScope = ShakeFunctionScope()

    override fun phase3() {
        debug("phases", "Phase 3 of method $qualifiedSignature")
        // TODO: Implement
    }

    override fun phase4() {
        debug("phases", "Phase 4 of method $qualifiedSignature")
        if (body is CreationShakeCode.ShakeLateProcessCode) body.process(scope)
    }

    override fun toJson(): Map<String, Any?> = super.toJson()

    inner class ShakeFunctionScope : CreationShakeScope() {

        override val uniqueName: String get() = qualifiedSignature

        val variables = mutableListOf<CreationShakeVariableDeclaration>()

        override val parent: CreationShakeScope = parentScope
        override val project get() = prj

        override fun get(name: String): CreationShakeAssignable? {
            return variables.find { it.name == name } ?: parent.get(name)
        }

        override fun set(value: CreationShakeDeclaration) {
            if (value !is CreationShakeVariableDeclaration) throw IllegalArgumentException("Only variable declarations can be set in a method scope")
            if (variables.any { it.name == value.name }) throw IllegalArgumentException("Variable ${value.name} already exists in this scope")
            variables.add(value)
        }

        override fun getFunctions(name: String): List<CreationShakeMethod> {
            return parent.getFunctions(name)
        }

        override fun setFunctions(function: CreationShakeMethod) {
            throw IllegalArgumentException("Cannot set a function in a method scope")
        }

        override fun getClass(name: String): CreationShakeClass? {
            return parent.getClass(name)
        }

        override fun setClass(klass: CreationShakeClass) {
            throw IllegalArgumentException("Cannot set a class in a method scope")
        }

        override val processor: ShakeASTProcessor
            get() = prj.projectScope.processor
    }

    companion object {

        val debug = ShakeProcessor.debug.child("creation", "method")

        fun from(
            baseProject: CreationShakeProject,
            pkg: CreationShakePackage?,
            parentScope: CreationShakeScope,
            node: ShakeFunctionDeclarationNode
        ): CreationShakeMethod {
            return CreationShakeMethod(
                baseProject,
                pkg,
                null,
                parentScope,
                node.name,
                node.body?.let { CreationShakeCode.fromTree(it) },
                node.isStatic,
                node.isFinal,
                false,
                false,
                false,
                node.access == ShakeAccessDescriber.PRIVATE,
                node.access == ShakeAccessDescriber.PROTECTED,
                node.access == ShakeAccessDescriber.PUBLIC,
                node.isNative,
                node.isOperator,
                parentScope.getType(node.type),
                node.args.map {
                    CreationShakeParameter(
                        it.name,
                        parentScope.getType(it.type)
                    )
                },
                node.expandedType?.let { parentScope.getType(it) }
            )
        }

        fun from(
            clazz: CreationShakeClass,
            parentScope: CreationShakeScope,
            node: ShakeFunctionDeclarationNode
        ): CreationShakeMethod {
            return CreationShakeMethod(
                clazz.prj,
                clazz.pkg,
                clazz,
                parentScope,
                node.name,
                node.body?.let { CreationShakeCode.fromTree(it) },
                node.isStatic,
                node.isFinal,
                node.isAbstract,
                node.isSynchronized,
                false,
                node.access == ShakeAccessDescriber.PRIVATE,
                node.access == ShakeAccessDescriber.PROTECTED,
                node.access == ShakeAccessDescriber.PUBLIC,
                node.isNative,
                node.isOperator,
                parentScope.getType(node.type),
                node.args.map {
                    CreationShakeParameter(
                        it.name,
                        parentScope.getType(it.type)
                    )
                },
                node.expandedType?.let { parentScope.getType(it) }
            )
        }
    }
}
