package io.github.shakelang.shake.processor.program.creation

import io.github.shakelang.shake.parser.node.ShakeFile
import io.github.shakelang.shake.parser.node.ShakeImportNode
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.types.ShakePackage

open class CreationShakePackage (
    override val baseProject: CreationShakeProject,
    override val name: String,
    override val parent: CreationShakePackage? = null,
    override val subpackages: MutableList<CreationShakePackage> = mutableListOf(),
    override val classes: MutableList<CreationShakeClass> = mutableListOf(),
    override val functions: MutableList<CreationShakeFunction> = mutableListOf(),
    override val fields: MutableList<CreationShakeField> = mutableListOf(),
): ShakePackage {

    override val qualifiedName: String get() = (parent?.qualifiedName?.plus(".") ?: "") + (name)
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

    open fun putFile(name: String, contents: ShakeFile) {

        val imports = contents.children.filterIsInstance<ShakeImportNode>()
        val imported = arrayOfNulls<CreationShakePackage>(imports.size)

        imports.forEachIndexed { i, import ->
            baseProject.getPackage(import.import.dropLast(1).joinToString(".")) {
                imported[i] = it
            }
        }

        val fileScope = object : CreationShakeScope {
            override val parent: CreationShakeScope = scope

            override fun get(name: String): CreationShakeAssignable? {
                return imports.zip(imported).filter {
                    val last = it.first.import.last()
                    last == name || last == "*"
                }.firstNotNullOfOrNull {
                    it.second!!.fields.find { f -> f.name == name }
                } ?: parent.get(name)
            }

            override fun set(value: CreationShakeDeclaration) {
                parent.set(value)
            }

            override fun getFunctions(name: String): List<CreationShakeFunction> {
                return imports.zip(imported).filter {
                    val last = it.first.import.last()
                    last == name || last == "*"
                }.flatMap {
                    it.second!!.functions.filter { f -> f.name == name }
                } + parent.getFunctions(name)
            }

            override fun setFunctions(function: CreationShakeFunction) {
                parent.setFunctions(function)
            }

            override fun getClass(name: String): CreationShakeClass? {
                return imports.zip(imported).filter {
                    val last = it.first.import.last()
                    last == name || last == "*"
                }.firstNotNullOfOrNull {
                    it.second!!.classes.find { c -> c.name == name }
                } ?: parent.getClass(name)
            }

            override fun setClass(klass: CreationShakeClass) {
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
                    classes.add(CreationShakeClass.from(baseProject, this, fileScope, it))
                }
                is ShakeFunctionDeclarationNode -> {
                    if(functions.any { func -> func.name == it.name })
                        throw IllegalStateException("Function ${it.name} already exists") // TODO Functions with different signatures
                    functions.add(CreationShakeFunction.from(baseProject, this, fileScope, it))
                }
                is ShakeVariableDeclarationNode -> {
                    if(fields.any { field -> field.name == it.name })
                        throw IllegalStateException("Field ${it.name} already exists")
                    fields.add(CreationShakeField.from(baseProject, this, fileScope, it))
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

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "parent" to parent?.name,
            "subpackages" to subpackages.map { it.toJson() },
            "classes" to classes.map { it.toJson() },
            "functions" to functions.map { it.toJson() },
            "fields" to fields.map { it.toJson() },
        )
    }

    private inner class Scope : CreationShakeScope {
        override val parent: CreationShakeScope get() = baseProject.projectScope

        override fun get(name: String): CreationShakeAssignable? {
            return fields.find { it.name == name } ?: parent.get(name)
        }

        override fun set(value: CreationShakeDeclaration) {
            throw IllegalStateException("Cannot set a value in a package scope")
        }

        override fun getFunctions(name: String): List<CreationShakeFunction> {
            return functions.filter { it.name == name } + parent.getFunctions(name)
        }

        override fun setFunctions(function: CreationShakeFunction) {
            throw IllegalStateException("Cannot set a function in a package scope")
        }

        override fun getClass(name: String): CreationShakeClass? {
            return classes.find { it.name == name } ?: parent.getClass(name)
        }

        override fun setClass(klass: CreationShakeClass) {
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