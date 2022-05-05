package io.github.shakelang.shake.processor.program.creation

import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.creation.code.CreationShakeCode
import io.github.shakelang.shake.processor.program.creation.code.CreationShakeInvokable
import io.github.shakelang.shake.processor.program.creation.code.CreationShakeScope
import io.github.shakelang.shake.processor.program.creation.code.statements.CreationShakeVariableDeclaration

open class CreationShakeFunction (
    val prj: CreationShakeProject,
    val pkg: CreationShakePackage?,
    val parentScope: CreationShakeScope,
    val name: String,
    body: CreationShakeCode,
    val isStatic: Boolean,
    val isFinal: Boolean,
    val isAbstract: Boolean,
    val isSynchronized: Boolean,
    val isStrict: Boolean,
    val isPrivate: Boolean,
    val isProtected: Boolean,
    val isPublic: Boolean,
): CreationShakeInvokable(body) {

    override val qualifiedName: String
        get() = (pkg?.qualifiedName?.plus(".") ?: "") + name

    final override lateinit var returnType: CreationShakeType
        private set

    constructor(
        prj: CreationShakeProject,
        pkg: CreationShakePackage?,
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
        isPublic: Boolean
    ): this(prj, pkg, parentScope, name, body, isStatic, isFinal, isAbstract, isSynchronized, isStrict, isPrivate, isProtected, isPublic) {
        this.parameters = parameters
        this.returnType = returnType
    }

    open val scope : CreationShakeScope = ShakeFunctionScope()

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

    open fun processCode() {
        if(body is CreationShakeCode.ShakeLateProcessCode) (body as CreationShakeCode.ShakeLateProcessCode).process(scope)
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
            "body" to body.toJson()
        )
    }

    inner class ShakeFunctionScope: CreationShakeScope {

        val variables = mutableListOf<CreationShakeVariableDeclaration>()

        override val parent: CreationShakeScope = parentScope

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
        fun from(baseProject: CreationShakeProject, pkg: CreationShakePackage?, parentScope: CreationShakeScope, node: ShakeFunctionDeclarationNode): CreationShakeFunction {
            return CreationShakeFunction(
                baseProject,
                pkg,
                parentScope,
                node.name,
                CreationShakeCode.fromTree(node.body),
                node.isStatic,
                node.isFinal,
                false,
                false,
                false,
                node.access == ShakeAccessDescriber.PRIVATE,
                node.access == ShakeAccessDescriber.PROTECTED,
                node.access == ShakeAccessDescriber.PUBLIC
            ).let {
                it.lateinitReturnType().let { run -> baseProject.getType(node.type) { t -> run(t) } }
                it.lateinitParameterTypes(node.args.map { p -> p.name })
                    .forEachIndexed { i, run -> baseProject.getType(node.args[i].type) { t -> run(t) } }
                it
            }
        }
    }
}