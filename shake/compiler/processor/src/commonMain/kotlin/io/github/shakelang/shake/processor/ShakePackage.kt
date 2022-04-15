package io.github.shakelang.shake.processor

import io.github.shakelang.shake.parser.node.ShakeTree
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode

open class ShakePackage (
    open val baseProject: ShakeProject,
    open val name: String,
    open val subpackages: MutableList<ShakePackage> = mutableListOf(),
    open val classes: MutableMap<String, ShakeClass> = mutableMapOf(),
    open val functions: MutableMap<String, ShakeMethod> = mutableMapOf(),
    open val fields: MutableMap<String, ShakeField> = mutableMapOf()
) {

    open fun getPackage(name: String): ShakePackage {
        return subpackages.find { it.name == name } ?: ShakePackage(baseProject, name).let {
            subpackages.add(it)
            it
        }
    }

    open fun getPackage(name: Array<String>): ShakePackage {
        return name.fold(this) { acc, pkgName -> acc.getPackage(pkgName) }
    }

    open fun putFile(name: String, contents: ShakeTree) {
        contents.children.forEach {
            when (it) {
                is ShakeClassDeclarationNode -> {
                    if(classes.containsKey(it.name)) {
                        throw Exception("Class ${it.name} already exists")
                    }
                    classes[it.name] = ShakeClass.from(baseProject, it)
                }
                is ShakeFunctionDeclarationNode -> {
                    if(functions.containsKey(it.name)) {
                        throw Exception("Function ${it.name} already exists")
                    }
                    functions[it.name] = ShakeMethod.from(baseProject, it)
                }
                is ShakeVariableDeclarationNode -> {
                    if(fields.containsKey(it.name)) {
                        throw Exception("Field ${it.name} already exists")
                    }
                    fields[it.name] = ShakeField.from(baseProject, it)
                }
            }
        }
    }

    open fun putFile(name: Array<String>, contents: ShakeTree) {
        val pkg = name.sliceArray(0 until name.size - 1)
        val file = name.last()
        getPackage(pkg).putFile(file, contents)
    }
}