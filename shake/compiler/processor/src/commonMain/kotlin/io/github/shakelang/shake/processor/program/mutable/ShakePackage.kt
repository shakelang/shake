package io.github.shakelang.shake.processor.program.mutable

import io.github.shakelang.shake.parser.node.ShakeFile
import io.github.shakelang.shake.parser.node.ShakeImportNode
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.mutable.code.ShakeScope

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
    val scope: ShakeScope = Scope()

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

        val imports = contents.children.filterIsInstance<ShakeImportNode>()
        val imported = arrayOfNulls<ShakePackage>(imports.size)

        imports.forEachIndexed { i, import ->
            baseProject.getPackage(import.import.dropLast(1).joinToString(".")) {
                imported[i] = it
            }
        }

        val fileScope = object : ShakeScope {
            override val parent: ShakeScope = scope

            override fun get(name: String): ShakeAssignable? {
                return imports.zip(imported).filter {
                    val last = it.first.import.last()
                    last == name || last == "*"
                }.firstNotNullOfOrNull {
                    it.second!!.fields.find { f -> f.name == name }
                } ?: parent.get(name)
            }

            override fun set(value: ShakeDeclaration) {
                parent.set(value)
            }

            override fun getFunctions(name: String): List<ShakeFunction> {
                return imports.zip(imported).filter {
                    val last = it.first.import.last()
                    last == name || last == "*"
                }.flatMap {
                    it.second!!.functions.filter { f -> f.name == name }
                } + parent.getFunctions(name)
            }

            override fun setFunctions(function: ShakeFunction) {
                parent.setFunctions(function)
            }

            override fun getClass(name: String): ShakeClass? {
                return imports.zip(imported).filter {
                    val last = it.first.import.last()
                    last == name || last == "*"
                }.firstNotNullOfOrNull {
                    it.second!!.classes.find { c -> c.name == name }
                } ?: parent.getClass(name)
            }

            override fun setClass(klass: ShakeClass) {
                parent.setClass(klass)
            }

            override val processor: ShakeCodeProcessor
                get() = parent.processor

        }

        contents.children.forEach {
            when (it) {
                is ShakeClassDeclarationNode -> {
                    if(classes.any { clz -> clz.name == it.name })
                        throw IllegalStateException("Class ${it.name} already exists")
                    classes.add(ShakeClass.from(baseProject, this, fileScope, it))
                }
                is ShakeFunctionDeclarationNode -> {
                    if(functions.any { func -> func.name == it.name })
                        throw IllegalStateException("Function ${it.name} already exists") // TODO Functions with different signatures
                    functions.add(ShakeFunction.from(baseProject, this, fileScope, it))
                }
                is ShakeVariableDeclarationNode -> {
                    if(fields.any { field -> field.name == it.name })
                        throw IllegalStateException("Field ${it.name} already exists")
                    fields.add(ShakeField.from(baseProject, this, fileScope, it))
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

    fun toJson(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "parent" to parent?.name,
            "subpackages" to subpackages.map { it.toJson() },
            "classes" to classes.map { it.toJson() },
            "functions" to functions.map { it.toJson() },
            "fields" to fields.map { it.toJson() },
        )
    }

    private inner class Scope : ShakeScope {
        override val parent: ShakeScope get() = baseProject.projectScope

        override fun get(name: String): ShakeAssignable? {
            return fields.find { it.name == name } ?: parent.get(name)
        }

        override fun set(value: ShakeDeclaration) {
            throw IllegalStateException("Cannot set a value in a package scope")
        }

        override fun getFunctions(name: String): List<ShakeFunction> {
            return functions.filter { it.name == name } + parent.getFunctions(name)
        }

        override fun setFunctions(function: ShakeFunction) {
            throw IllegalStateException("Cannot set a function in a package scope")
        }

        override fun getClass(name: String): ShakeClass? {
            return classes.find { it.name == name } ?: parent.getClass(name)
        }

        override fun setClass(klass: ShakeClass) {
            throw IllegalStateException("Cannot set a class in a package scope")
        }

        override val processor: ShakeCodeProcessor
            get() = parent.processor
    }

    fun processCode() {
        classes.forEach { it.processCode() }
        functions.forEach { it.processCode() }
        fields.forEach { it.processCode() }
        subpackages.forEach { it.processCode() }
    }
}