package io.github.shakelang.shake.processor.program

import io.github.shakelang.shake.parser.node.ShakeIdentifierNode
import io.github.shakelang.shake.parser.node.ShakeTree
import io.github.shakelang.shake.parser.node.ShakeValuedNodeImpl
import io.github.shakelang.shake.parser.node.ShakeVariableType
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode

open class ShakeProject(
    open val subpackages: MutableList<ShakePackage> = mutableListOf(),
    open val classes: MutableMap<String, ShakeClass> = mutableMapOf(),
    open val functions: MutableMap<String, ShakeFunction> = mutableMapOf(),
    open val fields: MutableMap<String, ShakeField> = mutableMapOf()
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

    open fun putFile(name: String, contents: ShakeTree) {
        contents.children.forEach {
            when (it) {
                is ShakeClassDeclarationNode -> {
                    if(classes.containsKey(it.name)) {
                        throw Exception("Class ${it.name} already exists")
                    }
                    classes[it.name] = ShakeClass.from(this, null, it)
                }
                is ShakeFunctionDeclarationNode -> {
                    if(functions.containsKey(it.name)) {
                        throw Exception("Function ${it.name} already exists")
                    }
                    functions[it.name] = ShakeFunction.from(this, it)
                }
                is ShakeVariableDeclarationNode -> {
                    if(fields.containsKey(it.name)) {
                        throw Exception("Field ${it.name} already exists")
                    }
                    fields[it.name] = ShakeField.from(this, it)
                }
            }
        }
    }

    open fun putFile(name: Array<String>, contents: ShakeTree) {
        val pkg = name.sliceArray(0 until name.size - 1)
        val file = name.last()
        getPackage(pkg).putFile(file, contents)
    }
    private val classRequirements = mutableListOf<ClassRequirement>()
    fun getClass(name: String, then: (ShakeClass) -> Unit) {
        this.classRequirements.add(ClassRequirement(name, then))
    }
    fun getClass(pkg: Array<String>, name: String): ShakeClass? {
        return this.getPackage(pkg).classes[name]
    }
    fun getClass(clz: String): ShakeClass? {
        val parts = clz.split(".")
        val name = parts.last()
        val pkg = parts.dropLast(1).toTypedArray()
        return if(pkg.isEmpty()) this.classes[name]
        else this.getPackage(pkg).classes[name]
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
                var identifier: ShakeValuedNodeImpl? = type.subtype!!
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