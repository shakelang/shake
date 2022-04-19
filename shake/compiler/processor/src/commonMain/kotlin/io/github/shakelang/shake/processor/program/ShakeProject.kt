package io.github.shakelang.shake.processor.program

import io.github.shakelang.shake.parser.node.*
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode

open class ShakeProject(
    open val subpackages: MutableList<ShakePackage> = mutableListOf(),
    open val classes: MutableList<ShakeClass> = mutableListOf(),
    open val functions: MutableList<ShakeFunction> = mutableListOf(),
    open val fields: MutableList<ShakeField> = mutableListOf()
) {

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

    open fun putFile(name: String, contents: ShakeFile) {
        contents.children.forEach {
            when (it) {
                is ShakeClassDeclarationNode -> {
                    if(classes.any { clz -> clz.name == it.name })
                        throw IllegalArgumentException("Class ${it.name} already exists")
                    classes.add(ShakeClass.from(this, null, it))
                }
                is ShakeFunctionDeclarationNode -> {
                    if(functions.any { func -> func.name == it.name })
                        throw IllegalArgumentException("Function ${it.name} already exists")
                    functions.add(ShakeFunction.from(this, it))
                }
                is ShakeVariableDeclarationNode -> {
                    if(fields.any { field -> field.name == it.name })
                        throw IllegalArgumentException("Field ${it.name} already exists")
                    fields.add(ShakeField.from(this, it))
                }
                else -> throw IllegalArgumentException("Unknown node type ${it::class.simpleName}")
            }
        }
    }

    open fun putFile(name: Array<String>, contents: ShakeFile) {
        val pkg = name.sliceArray(0 until name.size - 1)
        val file = name.last()
        getPackage(pkg).putFile(file, contents)
    }
    private val classRequirements = mutableListOf<ClassRequirement>()
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
    }
    private class ClassRequirement(val name: String, val then: (ShakeClass) -> Unit)
}