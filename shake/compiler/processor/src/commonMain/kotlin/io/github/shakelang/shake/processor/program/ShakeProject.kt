package io.github.shakelang.shake.processor.program

import io.github.shakelang.shake.parser.node.*
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.code.ShakeScope
import io.github.shakelang.shason.json

open class ShakeProject(
    processor: ShakeCodeProcessor,
    open val subpackages: MutableList<ShakePackage> = mutableListOf(),
    open val classes: MutableList<ShakeClass> = mutableListOf(),
    open val functions: MutableList<ShakeFunction> = mutableListOf(),
    open val fields: MutableList<ShakeField> = mutableListOf()
) {

    private val classRequirements = mutableListOf<ClassRequirement>()
    private val packageRequirements = mutableListOf<PackageRequirement>()

    val projectScope = object : ShakeScope {
        override val parent: ShakeScope?
            get() = null

        override val processor: ShakeCodeProcessor = processor

        override fun get(name: String): ShakeAssignable? {
            return fields.find { it.name == name }
        }

        override fun set(value: ShakeDeclaration) {
            throw IllegalStateException("Cannot set a value in the project scope")
        }

        override fun getFunctions(name: String): List<ShakeFunction> {
            return functions.filter { it.name == name }
        }

        override fun setFunctions(function: ShakeFunction) {
            throw IllegalStateException("Cannot set a function in the project scope")
        }

        override fun getClass(name: String): ShakeClass? {
            return classes.find { it.name == name }
        }

        override fun setClass(klass: ShakeClass) {
            throw IllegalStateException("Cannot set a class in the project scope")
        }
    }

    open fun getPackage(name: String): ShakePackage {
        return subpackages.find { it.name == name } ?: ShakePackage(this, name).let {
            subpackages.add(it)
            it
        }
    }

    open fun getPackage(name: Array<String>): ShakePackage {
        if(name.isEmpty()) throw IllegalArgumentException("Cannot get package from empty name")
        return getPackage(name.first()).getPackage(name.drop(1).toTypedArray())
    }

    fun getPackage(name: String, then: (ShakePackage) -> Unit) {
        this.packageRequirements.add(PackageRequirement(name, then))
    }

    open fun putFile(name: String, contents: ShakeFile) {

        val imports = contents.children.filterIsInstance<ShakeImportNode>()
        val imported = arrayOfNulls<ShakePackage>(imports.size)

        imports.forEachIndexed { i, import ->
            getPackage(import.import.dropLast(1).joinToString(".")) {
                imported[i] = it
            }
        }

        val fileScope = object : ShakeScope {
            override val parent: ShakeScope get() = projectScope

            override fun get(name: String): ShakeAssignable? {
                return imports.zip(imported).filter {
                    val last = it.first.import.last()
                    last == name || last == "*"
                }.firstNotNullOfOrNull {
                    it.second!!.fields.find { f -> f.name == name }
                }
            }

            override fun set(value: ShakeDeclaration) {
                throw IllegalStateException("Cannot set a value in a file scope")
            }

            override fun getFunctions(name: String): List<ShakeFunction> {
                return imports.zip(imported).filter {
                    val last = it.first.import.last()
                    last == name || last == "*"
                }.flatMap {
                    it.second!!.functions.filter { f -> f.name == name }
                }
            }

            override fun setFunctions(function: ShakeFunction) {
                throw IllegalStateException("Cannot set a function in a file scope")
            }

            override fun getClass(name: String): ShakeClass? {
                return imports.zip(imported).filter {
                    val last = it.first.import.last()
                    last == name || last == "*"
                }.firstNotNullOfOrNull {
                    it.second!!.classes.find { c -> c.name == name }
                }
            }

            override fun setClass(klass: ShakeClass) {
                throw IllegalStateException("Cannot set a class in a file scope")
            }

            override val processor: ShakeCodeProcessor
                get() = parent.processor

        }


        contents.children.forEach {
            when (it) {
                is ShakeClassDeclarationNode -> {
                    if(classes.any { clz -> clz.name == it.name })
                        throw IllegalArgumentException("Class ${it.name} already exists")
                    classes.add(ShakeClass.from(this, null, fileScope, it))
                }
                is ShakeFunctionDeclarationNode -> {
                    if(functions.any { func -> func.name == it.name })
                        throw IllegalArgumentException("Function ${it.name} already exists")
                    functions.add(ShakeFunction.from(this, null, fileScope, it))
                }
                is ShakeVariableDeclarationNode -> {
                    if(fields.any { field -> field.name == it.name })
                        throw IllegalArgumentException("Field ${it.name} already exists")
                    fields.add(ShakeField.from(this, null, fileScope, it))
                }
                else -> throw IllegalArgumentException("Unknown node type ${it::class.simpleName}")
            }
        }
    }

    open fun putFile(name: Array<String>, contents: ShakeFile) {
        val pkg = name.sliceArray(0 until name.size - 1)
        val file = name.last()
        if(pkg.isEmpty()) putFile(file, contents)
        else getPackage(pkg).putFile(file, contents)
    }

    fun getClass(name: String, then: (ShakeClass) -> Unit) {
        this.classRequirements.add(ClassRequirement(name, then))
    }

    fun getClass(pkg: Array<String>, name: String): ShakeClass? {
        return this.getPackage(pkg).classes.find { it.name == name }
    }

    fun getClass(clz: String): ShakeClass? {
        val parts = clz.split(".")
        val name = parts.last()
        val pkg = parts.dropLast(1).toTypedArray()
        return if(pkg.isEmpty()) this.classes.find { it.name == name }
        else this.getPackage(pkg).classes.find { it.name == name }
    }

    fun getType(type: ShakeVariableType, then: (ShakeType) -> Unit) {
        when (type.type) {
            ShakeVariableType.Type.BYTE -> then(ShakeType.Primitives.BYTE)
            ShakeVariableType.Type.SHORT -> then(ShakeType.Primitives.SHORT)
            ShakeVariableType.Type.INTEGER -> then(ShakeType.Primitives.INT)
            ShakeVariableType.Type.LONG -> then(ShakeType.Primitives.LONG)
            ShakeVariableType.Type.FLOAT -> then(ShakeType.Primitives.FLOAT)
            ShakeVariableType.Type.DOUBLE -> then(ShakeType.Primitives.DOUBLE)
            ShakeVariableType.Type.BOOLEAN -> then(ShakeType.Primitives.BOOLEAN)
            ShakeVariableType.Type.CHAR -> then(ShakeType.Primitives.CHAR)
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
                    then(ShakeType.objectType(it))
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

    fun toJson(): Map<String, Any?> {
        return mapOf(
            "classes" to classes.map { it.toJson() },
            "functions" to functions.map { it.toJson() },
            "fields" to fields.map { it.toJson() },
            "subpackages" to subpackages.map { it.toJson() }
        )
    }

    fun toJsonString(format: Boolean = false): String {
        return json.stringify(toJson(), format)
    }

    private class ClassRequirement(val name: String, val then: (ShakeClass) -> Unit)
    private class PackageRequirement(val name: String, val then: (ShakePackage) -> Unit)
}