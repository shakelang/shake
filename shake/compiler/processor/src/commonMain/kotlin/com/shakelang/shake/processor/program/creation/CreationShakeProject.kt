package com.shakelang.shake.processor.program.creation

import com.shakelang.shake.parser.node.ShakeFileNode
import com.shakelang.shake.parser.node.ShakeImportNode
import com.shakelang.shake.parser.node.ShakePackageNode
import com.shakelang.shake.parser.node.ShakeVariableType
import com.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import com.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import com.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import com.shakelang.shake.processor.ShakeASTProcessor
import com.shakelang.shake.processor.program.types.ShakeProject

class CreationShakeProject(
    processor: ShakeASTProcessor,
    override val subpackages: MutableList<CreationShakePackage> = mutableListOf(),
    override val classes: MutableList<CreationShakeClass> = mutableListOf(),
    override val functions: MutableList<CreationShakeMethod> = mutableListOf(),
    override val fields: MutableList<CreationShakeField> = mutableListOf()
) : ShakeProject {

    private val classRequirements = mutableListOf<ClassRequirement>()
    private val packageRequirements = mutableListOf<PackageRequirement>()
    private val scopes = mutableListOf<CreationShakeScope>()

    val cores = Cores()

    override val projectScope = object : CreationShakeScope() {
        override val parent: CreationShakeScope? = null
        override val project: CreationShakeProject get() = this@CreationShakeProject

        override val processor: ShakeASTProcessor = processor

        val imported: Array<CreationShakePackage> = arrayOf(
            getPackage("shake.lang"),
            getPackage("shake.js")
        )

        override fun get(name: String): CreationShakeAssignable? {
            val localField = fields.find { it.name == name }
            if (localField != null) return localField
            for (import in imported) {
                val field = import.fields.find { it.name == name }
                if (field != null) return field
            }
            return null
        }

        override fun set(value: CreationShakeDeclaration) {
            throw IllegalStateException("Cannot set a value in the project scope")
        }

        override fun getFunctions(name: String): List<CreationShakeMethod> {
            val functions = functions.filter { it.name == name }.toMutableList()
            for (import in imported) {
                functions += import.functions.filter { it.name == name }
            }
            return functions
        }

        override fun setFunctions(function: CreationShakeMethod) {
            throw IllegalStateException("Cannot set a function in the project scope")
        }

        override fun getClass(name: String): CreationShakeClass? {
            val localClass = classes.find { it.name == name }
            if (localClass != null) return localClass
            for (import in imported) {
                val class_ = import.classes.find { it.name == name }
                if (class_ != null) return class_
            }
            return this@CreationShakeProject.getClass(name)
        }

        override fun setClass(klass: CreationShakeClass) {
            throw IllegalStateException("Cannot set a class in the project scope")
        }
    }

    override fun getPackage(name: String): CreationShakePackage {
        if (name.contains(".")) return getPackage(name.split(".").toTypedArray())
        return subpackages.find { it.name == name } ?: CreationShakePackage(this, name).let {
            subpackages.add(it)
            it
        }
    }

    override fun getPackage(name: Array<String>): CreationShakePackage {
        if (name.isEmpty()) throw IllegalArgumentException("Cannot get package from empty name")
        return getPackage(name.first()).getPackage(name.drop(1).toTypedArray())
    }

    override fun getPackage(name: List<String>): CreationShakePackage {
        if (name.isEmpty()) throw IllegalArgumentException("Cannot get package from empty name")
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

        val fileScope = CreationFileScope(this, projectScope, imports, imported)

        contents.children.forEach {
            when (it) {
                is ShakeClassDeclarationNode -> {
                    if (classes.any { clz -> clz.name == it.name }) {
                        throw IllegalArgumentException("Class ${it.name} already exists")
                    }
                    classes.add(CreationShakeClass.from(this, null, fileScope, it))
                }

                is ShakeFunctionDeclarationNode -> {
                    if (functions.any { func -> func.name == it.name }) {
                        throw IllegalArgumentException("Function ${it.name} already exists")
                    }
                    functions.add(CreationShakeMethod.from(this, null, fileScope, it))
                }

                is ShakeVariableDeclarationNode -> {
                    if (fields.any { field -> field.name == it.name }) {
                        throw IllegalArgumentException("Field ${it.name} already exists")
                    }
                    fields.add(CreationShakeField.from(this, null, fileScope, it))
                }

                is ShakeImportNode -> {}
                else -> throw IllegalArgumentException("Unknown node type ${it::class.simpleName}")
            }
        }
    }

    fun putFile(name: Array<String>, contents: ShakeFileNode) {
        val pkg = (contents.children.find { it is ShakePackageNode } as? ShakePackageNode)?.pkg
            ?: name.sliceArray(0 until name.size - 1)
        val file = name.last()
        if (pkg.isEmpty()) {
            putFile(file, contents)
        } else {
            getPackage(pkg).putFile(file, contents)
        }
    }

    @Deprecated("Use Scope.getClass() instead!")
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
        return if (pkg.isEmpty()) {
            this.classes.find { it.name == name }
        } else {
            this.getPackage(pkg).classes.find { it.name == name }
        }
    }

    @Deprecated("Use Scope.getType() instead")
    fun getType(type: ShakeVariableType, then: (CreationShakeType) -> Unit) {
        when (type.type) {
            ShakeVariableType.Type.BYTE -> then(CreationShakeType.Primitives.BYTE)
            ShakeVariableType.Type.SHORT -> then(CreationShakeType.Primitives.SHORT)
            ShakeVariableType.Type.INTEGER -> then(CreationShakeType.Primitives.INT)
            ShakeVariableType.Type.LONG -> then(CreationShakeType.Primitives.LONG)
            ShakeVariableType.Type.FLOAT -> then(CreationShakeType.Primitives.FLOAT)
            ShakeVariableType.Type.DOUBLE -> then(CreationShakeType.Primitives.DOUBLE)
            ShakeVariableType.Type.UNSIGNED_BYTE -> then(CreationShakeType.Primitives.UBYTE)
            ShakeVariableType.Type.UNSIGNED_SHORT -> then(CreationShakeType.Primitives.USHORT)
            ShakeVariableType.Type.UNSIGNED_INTEGER -> then(CreationShakeType.Primitives.UINT)
            ShakeVariableType.Type.UNSIGNED_LONG -> then(CreationShakeType.Primitives.ULONG)
            ShakeVariableType.Type.BOOLEAN -> then(CreationShakeType.Primitives.BOOLEAN)
            ShakeVariableType.Type.CHAR -> then(CreationShakeType.Primitives.CHAR)
            ShakeVariableType.Type.OBJECT -> {
                val namespace = (type as ShakeVariableType.Object).namespace
                    ?: throw IllegalArgumentException("Object type must have subtype")
                val clzName = namespace.parts.joinToString(".")
                this.getClass(clzName) {
                    then(CreationShakeType.objectType(it))
                }
            }

            ShakeVariableType.Type.DYNAMIC -> TODO()
            ShakeVariableType.Type.ARRAY -> TODO()
            ShakeVariableType.Type.VOID -> TODO()
            ShakeVariableType.Type.UNKNOWN -> TODO()
        }
    }

    fun finish() {
        classRequirements.forEach {
            val cls = this.getClass(it.name)
            it.then(cls ?: throw IllegalArgumentException("Class ${it.name} not found"))
        }

        classRequirements.clear()

        packageRequirements.forEach {
            val pkg = this.getPackage(it.name)
            it.then(pkg)
        }

        packageRequirements.clear()

        this.scopes.forEach {
            it.finish()
        }
        this.scopes.clear()

        this.classes.forEach { it.processCode() }
        this.functions.forEach { it.phase4() }
        this.fields.forEach { it.phase4() }
        this.subpackages.forEach { it.phase4() }
    }

    fun addScope(creationShakeScope: CreationShakeScope) {
        this.scopes.add(creationShakeScope)
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
        TODO()
    }

    class ClassRequirement(val name: String, val then: (CreationShakeClass) -> Unit)
    private class PackageRequirement(val name: String, val then: (CreationShakePackage) -> Unit)

    inner class Cores {
        val ObjectClass get() = getClass("shake.lang.Object") ?: error("Object class not found")
        val StringClass get() = getClass("shake.lang.String") ?: error("String class not found")

        val Object: CreationShakeType get() = CreationShakeType.objectType(ObjectClass)
        val String: CreationShakeType get() = CreationShakeType.objectType(StringClass)

        fun pointString(init: (CreationShakeType) -> Unit) = projectScope.getType("shake.lang.String", init)
        fun pointObject(init: (CreationShakeType) -> Unit) = projectScope.getType("shake.lang.Object", init)

        fun pointStringClass(init: (CreationShakeClass) -> Unit) = projectScope.getClass("shake.lang.String", init)
        fun pointObjectClass(init: (CreationShakeClass) -> Unit) = projectScope.getClass("shake.lang.Object", init)
    }
}
