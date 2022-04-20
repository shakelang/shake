package io.github.shakelang.shake.processor.program

import io.github.shakelang.shake.parser.node.ShakeFile
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.code.ShakeScope

open class ShakePackage (
    open val baseProject: ShakeProject,
    open val name: String,
    open val parent: ShakePackage? = null,
    open val subpackages: MutableList<ShakePackage> = mutableListOf(),
    open val classes: MutableList<ShakeClass> = mutableListOf(),
    open val functions: MutableList<ShakeFunction> = mutableListOf(),
    open val fields: MutableList<ShakeField> = mutableListOf(),
) {

    val qualifiedName: String get() = (parent?.qualifiedName?.plus(".") ?: "") + (name)

    open fun getPackage(name: String): ShakePackage {
        return subpackages.find { it.name == name } ?: ShakePackage(baseProject, name, this).let {
            subpackages.add(it)
            it
        }
    }

    open fun getPackage(name: Array<String>): ShakePackage {
        return name.fold(this) { acc, pkgName -> acc.getPackage(pkgName) }
    }

    open fun putFile(name: String, contents: ShakeFile) {
        contents.children.forEach {
            when (it) {
                is ShakeClassDeclarationNode -> {
                    if(classes.any { clz -> clz.name == it.name })
                        throw IllegalStateException("Class ${it.name} already exists")
                    classes.add(ShakeClass.from(baseProject, this, it))
                }
                is ShakeFunctionDeclarationNode -> {
                    if(functions.any { func -> func.name == it.name })
                        throw IllegalStateException("Function ${it.name} already exists") // TODO Functions with different signatures
                    functions.add(ShakeFunction.from(baseProject, it))
                }
                is ShakeVariableDeclarationNode -> {
                    if(fields.any { field -> field.name == it.name })
                        throw IllegalStateException("Field ${it.name} already exists")
                    fields.add(ShakeField.from(baseProject, it))
                }
                else -> throw IllegalStateException("Unknown node type ${it::class}")
            }
        }
    }

    open fun putFile(name: Array<String>, contents: ShakeFile) {
        val pkg = name.sliceArray(0 until name.size - 1)
        val file = name.last()
        getPackage(pkg).putFile(file, contents)
    }

    private inner class Scope(
        override val parent: ShakeScope,
    ) : ShakeScope {

        override fun get(name: String): ShakeAssignable? {
            return fields.find { it.name == name }
        }

        override fun set(name: String, value: ShakeDeclaration) {
            throw IllegalStateException("Cannot set a value in a package scope")
        }

        override fun getFunctions(name: String): List<ShakeFunction> {
            return functions.filter { it.name == name }
        }

        override fun setFunctions(name: String, function: ShakeFunction) {
            throw IllegalStateException("Cannot set a function in a package scope")
        }

        override fun getClass(name: String): ShakeClass? {
            return classes.find { it.name == name }
        }

        override fun setClass(name: String, klass: ShakeClass) {
            throw IllegalStateException("Cannot set a class in a package scope")
        }

        override val processor: ShakeCodeProcessor
            get() = TODO("Not yet implemented")
    }
}