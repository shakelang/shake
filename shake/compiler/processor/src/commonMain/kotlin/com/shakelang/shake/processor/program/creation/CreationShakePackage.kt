package com.shakelang.shake.processor.program.creation

import com.shakelang.shake.parser.node.ShakeFileNode
import com.shakelang.shake.parser.node.ShakeImportNode
import com.shakelang.shake.parser.node.ShakePackageNode
import com.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import com.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import com.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import com.shakelang.shake.processor.ShakeASTProcessor
import com.shakelang.shake.processor.program.types.ShakePackage

open class CreationShakePackage(
    override val baseProject: CreationShakeProject,
    override val name: String,
    override val parent: CreationShakePackage? = null,
    override val subpackages: MutableList<CreationShakePackage> = mutableListOf(),
    override val classes: MutableList<CreationShakeClass> = mutableListOf(),
    override val functions: MutableList<CreationShakeMethod> = mutableListOf(),
    override val fields: MutableList<CreationShakeField> = mutableListOf()
) : ShakePackage {

    override val qualifiedName: String get() = "${parent?.qualifiedName?.plus(".") ?: ""}$name"
    override val scope: CreationShakeScope = Scope()

    override fun getPackage(name: String): CreationShakePackage {
        return subpackages.find { it.name == name } ?: CreationShakePackage(baseProject, name, this).let {
            subpackages.add(it)
            it
        }
    }

    override fun getPackage(name: Array<String>): CreationShakePackage {
        return name.fold(this) { acc, pkgName -> acc.getPackage(pkgName) }
    }

    override fun getPackage(name: List<String>): ShakePackage? {
        return name.fold(this) { acc, pkgName -> acc.getPackage(pkgName) }
    }

    open fun putFile(name: String, contents: ShakeFileNode) {
        val imports = contents.children.filterIsInstance<ShakeImportNode>()
        val imported = arrayOfNulls<CreationShakePackage>(imports.size)

        imports.forEachIndexed { i, import ->
            baseProject.getPackage(import.import.dropLast(1).joinToString(".")) {
                imported[i] = it
            }
        }

        val fileScope = CreationFileScope(baseProject, scope, imports, imported)

        contents.children.forEach {
            when (it) {
                is ShakeClassDeclarationNode -> {
                    if (classes.any { clz -> clz.name == it.name }) {
                        throw IllegalStateException("Class ${it.name} already exists")
                    }
                    classes.add(CreationShakeClass.from(baseProject, this, fileScope, it))
                }

                is ShakeFunctionDeclarationNode -> {
                    val method = CreationShakeMethod.from(baseProject, this, fileScope, it)
                    /* TODO: check if method already exists
                    if(functions.any { func -> func.signature == method.signature })
                        throw IllegalStateException("Method ${method.signature} already exists")
                    */
                    functions.add(method)
                }

                is ShakeVariableDeclarationNode -> {
                    if (fields.any { field -> field.name == it.name }) {
                        throw IllegalStateException("Field ${it.name} already exists")
                    }
                    fields.add(CreationShakeField.from(baseProject, this, fileScope, it))
                }

                is ShakePackageNode -> {}
                else -> throw IllegalStateException("Unknown node type ${it::class.simpleName}")
            }
        }
    }

    open fun putFile(name: Array<String>, contents: ShakeFileNode) {
        val pkg = name.sliceArray(0 until name.size - 1)
        val file = name.last()
        getPackage(pkg).putFile(file, contents)
    }

    private inner class Scope : CreationShakeScope() {
        override val parent: CreationShakeScope get() = baseProject.projectScope
        override val project get() = baseProject

        override fun get(name: String): CreationShakeAssignable? {
            return fields.find { it.name == name } ?: parent.get(name)
        }

        override fun set(value: CreationShakeDeclaration) {
            throw IllegalStateException("Cannot set a value in a package scope")
        }

        override fun getFunctions(name: String): List<CreationShakeMethod> {
            return functions.filter { it.name == name } + parent.getFunctions(name)
        }

        override fun setFunctions(function: CreationShakeMethod) {
            throw IllegalStateException("Cannot set a function in a package scope")
        }

        override fun getClass(name: String): CreationShakeClass? {
            return classes.find { it.name == name } ?: parent.getClass(name)
        }

        override fun setClass(klass: CreationShakeClass) {
            throw IllegalStateException("Cannot set a class in a package scope")
        }

        override val processor: ShakeASTProcessor
            get() = parent.processor
    }

    override fun phase1() {
        TODO()
    }

    override fun phase2() {
        TODO()
    }

    override fun phase3() {
        TODO()
    }


    override fun phase4() {
        classes.forEach { it.processCode() }
        functions.forEach { it.phase4() }
        fields.forEach { it.phase4() }
        subpackages.forEach { it.phase4() }
    }
}
