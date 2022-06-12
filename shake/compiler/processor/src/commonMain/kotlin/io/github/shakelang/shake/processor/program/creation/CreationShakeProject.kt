package io.github.shakelang.shake.processor.program.creation

import io.github.shakelang.shake.parser.node.*
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.types.ShakeProject

open class CreationShakeProject(
    processor: ShakeCodeProcessor,
    override val subpackages: MutableList<CreationShakePackage> = mutableListOf(),
    override val classes: MutableList<CreationShakeClass> = mutableListOf(),
    override val functions: MutableList<CreationShakeMethod> = mutableListOf(),
    override val fields: MutableList<CreationShakeField> = mutableListOf()
): ShakeProject {

    private val classRequirements = mutableListOf<ClassRequirement>()
    private val packageRequirements = mutableListOf<PackageRequirement>()

    override val projectScope = object : CreationShakeScope {
        override val parent: CreationShakeScope? = null

        override val processor: ShakeCodeProcessor = processor

        override fun get(name: String): CreationShakeAssignable? {
            return fields.find { it.name == name }
        }

        override fun set(value: CreationShakeDeclaration) {
            throw IllegalStateException("Cannot set a value in the project scope")
        }

        override fun getFunctions(name: String): List<CreationShakeMethod> {
            return functions.filter { it.name == name }
        }

        override fun setFunctions(function: CreationShakeMethod) {
            throw IllegalStateException("Cannot set a function in the project scope")
        }

        override fun getClass(name: String): CreationShakeClass? {
            return classes.find { it.name == name }
        }

        override fun setClass(klass: CreationShakeClass) {
            throw IllegalStateException("Cannot set a class in the project scope")
        }
    }

    override fun getPackage(name: String): CreationShakePackage {
        if(name.contains(".")) return getPackage(name.split(".").toTypedArray())
        return subpackages.find { it.name == name } ?: CreationShakePackage(this, name).let {
            subpackages.add(it)
            it
        }
    }

    override fun getPackage(name: Array<String>): CreationShakePackage {
        if(name.isEmpty()) throw IllegalArgumentException("Cannot get package from empty name")
        return getPackage(name.first()).getPackage(name.drop(1).toTypedArray())
    }

    override fun getPackage(name: List<String>): CreationShakePackage {
        if(name.isEmpty()) throw IllegalArgumentException("Cannot get package from empty name")
        return getPackage(name.first()).getPackage(name.drop(1).toTypedArray())
    }

    fun getPackage(name: String, then: (CreationShakePackage) -> Unit) {
        this.packageRequirements.add(PackageRequirement(name, then))
    }

    open fun putFile(name: String, contents: ShakeFileNode) {

        val imports = contents.children.filterIsInstance<ShakeImportNode>()
        val imported = arrayOfNulls<CreationShakePackage>(imports.size)

        imports.forEachIndexed { i, import ->
            getPackage(import.import.dropLast(1).joinToString(".")) {
                imported[i] = it
            }
        }

        val fileScope = object : CreationShakeScope {
            override val parent: CreationShakeScope = projectScope

            override fun get(name: String): CreationShakeAssignable? {
                println("Try to get $name")
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

            override fun getFunctions(name: String): List<CreationShakeMethod> {
                return imports.zip(imported).filter {
                    val last = it.first.import.last()
                    last == name || last == "*"
                }.flatMap {
                    it.second!!.functions.filter { f -> f.name == name }
                } + parent.getFunctions(name)
            }

            override fun setFunctions(function: CreationShakeMethod) {
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
                        throw IllegalArgumentException("Class ${it.name} already exists")
                    classes.add(CreationShakeClass.from(this, null, fileScope, it))
                }
                is ShakeFunctionDeclarationNode -> {
                    if(functions.any { func -> func.name == it.name })
                        throw IllegalArgumentException("Function ${it.name} already exists")
                    functions.add(CreationShakeMethod.from(this, null, fileScope, it))
                }
                is ShakeVariableDeclarationNode -> {
                    if(fields.any { field -> field.name == it.name })
                        throw IllegalArgumentException("Field ${it.name} already exists")
                    fields.add(CreationShakeField.from(this, null, fileScope, it))
                }
                is ShakeImportNode -> {}
                else -> throw IllegalArgumentException("Unknown node type ${it::class.simpleName}")
            }
        }
    }

    open fun putFile(name: Array<String>, contents: ShakeFileNode) {
        val pkg = name.sliceArray(0 until name.size - 1)
        val file = name.last()
        if(pkg.isEmpty()) putFile(file, contents)
        else getPackage(pkg).putFile(file, contents)
    }

    fun getClass(name: String, then: (CreationShakeClass) -> Unit) {
        this.classRequirements.add(ClassRequirement(name, then))
    }

    override fun getClass(pkg: Array<String>, name: String): CreationShakeClass? {
        return this.getPackage(pkg).classes.find { it.name == name }
    }

    override fun getClass(clz: String): CreationShakeClass? {
        val parts = clz.split(".")
        val name = parts.last()
        val pkg = parts.dropLast(1).toTypedArray()
        return if(pkg.isEmpty()) this.classes.find { it.name == name }
        else this.getPackage(pkg).classes.find { it.name == name }
    }

    fun getType(type: ShakeVariableType, then: (CreationShakeType) -> Unit) {
        when (type.type) {
            ShakeVariableType.Type.BYTE -> then(CreationShakeType.Primitives.BYTE)
            ShakeVariableType.Type.SHORT -> then(CreationShakeType.Primitives.SHORT)
            ShakeVariableType.Type.INTEGER -> then(CreationShakeType.Primitives.INT)
            ShakeVariableType.Type.LONG -> then(CreationShakeType.Primitives.LONG)
            ShakeVariableType.Type.FLOAT -> then(CreationShakeType.Primitives.FLOAT)
            ShakeVariableType.Type.DOUBLE -> then(CreationShakeType.Primitives.DOUBLE)
            ShakeVariableType.Type.BOOLEAN -> then(CreationShakeType.Primitives.BOOLEAN)
            ShakeVariableType.Type.CHAR -> then(CreationShakeType.Primitives.CHAR)
            ShakeVariableType.Type.OBJECT -> {
                val clz = mutableListOf<String>()
                var identifier: ShakeValuedNode? = type.subtype!!
                while(identifier != null) {
                    if(identifier !is ShakeIdentifierNode) throw IllegalArgumentException("Invalid type ${type.subtype}")
                    clz.add(identifier.name)
                    identifier = identifier.parent
                }
                val clzName = clz.reversed().joinToString(".")
                this.getClass(clzName) {
                    then(CreationShakeType.objectType(it))
                }
            }
        }
    }

    fun finish() {

        classRequirements.forEach {
            val cls = this.getClass(it.name)
            it.then(cls!!)
        }

        classRequirements.clear()

        packageRequirements.forEach {
            val pkg = this.getPackage(it.name)
            it.then(pkg)
        }

        packageRequirements.clear()

        this.classes.forEach { it.processCode() }
        this.functions.forEach { it.processCode() }
        this.fields.forEach { it.processCode() }
        this.subpackages.forEach { it.processCode() }

    }

    private class ClassRequirement(val name: String, val then: (CreationShakeClass) -> Unit)
    private class PackageRequirement(val name: String, val then: (CreationShakePackage) -> Unit)
}