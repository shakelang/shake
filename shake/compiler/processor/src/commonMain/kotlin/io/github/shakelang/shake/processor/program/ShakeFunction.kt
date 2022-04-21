package io.github.shakelang.shake.processor.program

import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.code.ShakeCode
import io.github.shakelang.shake.processor.program.code.ShakeInvokable
import io.github.shakelang.shake.processor.program.code.ShakeScope
import io.github.shakelang.shake.processor.program.code.statements.ShakeVariableDeclaration

open class ShakeFunction (
    val prj: ShakeProject,
    val pkg: ShakePackage?,
    val name: String,
    body: ShakeCode,
    val isStatic: Boolean,
    val isFinal: Boolean,
    val isAbstract: Boolean,
    val isSynchronized: Boolean,
    val isStrict: Boolean,
    val isPrivate: Boolean,
    val isProtected: Boolean,
    val isPublic: Boolean,
): ShakeInvokable(body) {

    override val qualifiedName: String
        get() = (pkg?.qualifiedName?.plus(".") ?: "") + name

    final override lateinit var returnType: ShakeType
        private set

    constructor(
        prj: ShakeProject,
        pkg: ShakePackage?,
        name: String,
        parameters: List<ShakeParameter>,
        returnType: ShakeType,
        body: ShakeCode,
        isStatic: Boolean,
        isFinal: Boolean,
        isAbstract: Boolean,
        isSynchronized: Boolean,
        isStrict: Boolean,
        isPrivate: Boolean,
        isProtected: Boolean,
        isPublic: Boolean
    ): this(prj, pkg, name, body, isStatic, isFinal, isAbstract, isSynchronized, isStrict, isPrivate, isProtected, isPublic) {
        this.parameters = parameters
        this.returnType = returnType
    }

    open val scope : ShakeScope = ShakeFunctionScope()

    fun lateinitReturnType(): (ShakeType) -> ShakeType {
        return {
            returnType = it
            it
        }
    }

    fun lateinitParameterTypes(names: List<String>): List<(ShakeType) -> ShakeType> {
        this.parameters = names.map {
            ShakeParameter(it)
        }
        return this.parameters.map {
            it.lateinitType()
        }
    }

    open fun processCode() {
        if(body is ShakeCode.ShakeLateProcessCode) (body as ShakeCode.ShakeLateProcessCode).process(scope)
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

    inner class ShakeFunctionScope: ShakeScope {

        val variables = mutableListOf<ShakeVariableDeclaration>()

        override val parent: ShakeScope
            get() = pkg?.scope ?: prj.projectScope

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
            throw IllegalArgumentException("Cannot set a function in a method scope")
        }

        override fun getClass(name: String): ShakeClass? {
            return parent.getClass(name)
        }

        override fun setClass(klass: ShakeClass) {
            throw IllegalArgumentException("Cannot set a class in a method scope")
        }

        override val processor: ShakeCodeProcessor
            get() = prj.projectScope.processor

    }

    companion object {
        fun from(baseProject: ShakeProject, pkg: ShakePackage?, node: ShakeFunctionDeclarationNode): ShakeFunction {
            return ShakeFunction(
                baseProject,
                pkg,
                node.name,
                ShakeCode.fromTree(node.body),
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