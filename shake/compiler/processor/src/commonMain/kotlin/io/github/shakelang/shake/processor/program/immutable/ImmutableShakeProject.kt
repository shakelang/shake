package io.github.shakelang.shake.processor.program.immutable

import io.github.shakelang.shake.parser.node.*
import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.types.*
import io.github.shakelang.shake.processor.program.types.ShakeDeclaration
import io.github.shakelang.shake.processor.program.types.code.ShakeInvokable
import io.github.shakelang.shake.processor.program.types.code.ShakeScope
import io.github.shakelang.shason.json

open class ImmutableShakeProject : ShakeProject {
    final override val subpackages: List<ShakePackage>
    final override val classes: List<ShakeClass>
    final override val functions: List<ShakeFunction>
    final override val fields: List<ShakeField>

    override val projectScope: ShakeScope

    constructor(
        subpackages: List<ShakePackage>,
        classes: List<ShakeClass>,
        functions: List<ShakeFunction>,
        fields: List<ShakeField>
    ) {
        this.subpackages = subpackages
        this.classes = classes
        this.functions = functions
        this.fields = fields

        projectScope = object : ImmutableShakeScope {
            override val parent: ShakeScope? = null

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
    }

    override fun getPackage(name: String): ShakePackage {
        if(name.contains(".")) return getPackage(name.split(".").toTypedArray())
        return subpackages.find { it.name == name } ?: throw Error("Package $name not found")
    }

    override fun getPackage(name: Array<String>): ShakePackage {
        if(name.isEmpty()) throw IllegalArgumentException("Cannot get package from empty name")
        return getPackage(name.first()).getPackage(name.drop(1).toTypedArray())
    }

    override fun getClass(pkg: Array<String>, name: String): ShakeClass? {
        return this.getPackage(pkg).classes.find { it.name == name }
    }

    override fun getClass(clz: String): ShakeClass? {
        val parts = clz.split(".")
        val name = parts.last()
        val pkg = parts.dropLast(1).toTypedArray()
        return if(pkg.isEmpty()) this.classes.find { it.name == name }
        else this.getPackage(pkg).classes.find { it.name == name }
    }

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "classes" to classes.map { it.toJson() },
            "functions" to functions.map { it.toJson() },
            "fields" to fields.map { it.toJson() },
            "subpackages" to subpackages.map { it.toJson() }
        )
    }

    override fun toJsonString(format: Boolean): String {
        return json.stringify(toJson(), format)
    }

}