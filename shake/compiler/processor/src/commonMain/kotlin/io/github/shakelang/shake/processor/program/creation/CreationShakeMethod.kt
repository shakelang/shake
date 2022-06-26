package io.github.shakelang.shake.processor.program.creation

import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.creation.code.CreationShakeCode
import io.github.shakelang.shake.processor.program.creation.code.CreationShakeInvokable
import io.github.shakelang.shake.processor.program.creation.code.statements.CreationShakeVariableDeclaration
import io.github.shakelang.shake.processor.program.types.ShakeMethod

class CreationShakeMethod (
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
): CreationShakeInvokable(body), ShakeMethod {

    override val qualifiedName: String
        get() = (pkg?.qualifiedName?.plus(".") ?: "") + name

    override lateinit var returnType: CreationShakeType
        private set

    constructor(
        prj: CreationShakeProject,
        pkg: CreationShakePackage?,
        clazz: CreationShakeClass?,
        parentScope: CreationShakeScope,
        name: String,
        parameters: List<CreationShakeParameter>,
        returnType: CreationShakeType,
        body: CreationShakeCode,
        isStatic: Boolean,
        isFinal: Boolean,
        isAbstract: Boolean,
        isSynchronized: Boolean,
        isStrict: Boolean,
        isPrivate: Boolean,
        isProtected: Boolean,
        isPublic: Boolean,
        isNative: Boolean,
    ): this(prj, pkg, clazz, parentScope, name, body, isStatic, isFinal, isAbstract, isSynchronized, isStrict, isPrivate, isProtected, isPublic, isNative) {
        this.parameters = parameters
        this.returnType = returnType
    }

    override val scope : CreationShakeScope = ShakeFunctionScope()

    fun lateinitReturnType(): (CreationShakeType) -> CreationShakeType {
        return {
            returnType = it
            it
        }
    }

    fun lateinitParameterTypes(names: List<String>): List<(CreationShakeType) -> CreationShakeType> {
        this.parameters = names.map {
            CreationShakeParameter(it)
        }
        return this.parameters.map {
            it.lateinitType()
        }
    }

    fun processCode() {
        if(body is CreationShakeCode.ShakeLateProcessCode) body.process(scope)
    }

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "isStatic" to isStatic,
            "isFinal" to isFinal,
            "isAbstract" to isAbstract,
            "isSynchronized" to isSynchronized,
            "isStrict" to isStrict,
            "isPrivate" to isPrivate,
            "isProtected" to isProtected,
            "isPublic" to isPublic,
            "returnType" to returnType.toJson(),
            "parameters" to parameters.map { it.toJson() },
            "body" to body?.toJson()
        )
    }
    inner class ShakeFunctionScope: CreationShakeScope() {

        val variables = mutableListOf<CreationShakeVariableDeclaration>()

        override val parent: CreationShakeScope = parentScope
        override val project get() = prj

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
            throw IllegalArgumentException("Cannot set a function in a method scope")
        }

        override fun getClass(name: String): CreationShakeClass? {
            return parent.getClass(name)
        }

        override fun setClass(klass: CreationShakeClass) {
            throw IllegalArgumentException("Cannot set a class in a method scope")
        }

        override val processor: ShakeCodeProcessor
            get() = prj.projectScope.processor

    }

    companion object {
        fun from(baseProject: CreationShakeProject, pkg: CreationShakePackage?, parentScope: CreationShakeScope, node: ShakeFunctionDeclarationNode): CreationShakeMethod {
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
            ).let {
                it.lateinitReturnType().let { run -> parentScope.getType(node.type) { t -> run(t) } }
                it.lateinitParameterTypes(node.args.map { p -> p.name })
                    .forEachIndexed { i, run -> parentScope.getType(node.args[i].type) { t -> run(t) } }
                it
            }
        }
        fun from(clazz: CreationShakeClass, parentScope: CreationShakeScope, node: ShakeFunctionDeclarationNode): CreationShakeMethod {
            val baseProject = clazz.prj
            return CreationShakeMethod(
                clazz.prj,
                clazz.pkg,
                clazz,
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
            ).let {
                it.lateinitReturnType().let { run -> parentScope.getType(node.type) { t -> run(t) } }
                it.lateinitParameterTypes(node.args.map { p -> p.name })
                    .forEachIndexed { i, run -> parentScope.getType(node.args[i].type) { t -> run(t) } }
                it
            }
        }
    }
}