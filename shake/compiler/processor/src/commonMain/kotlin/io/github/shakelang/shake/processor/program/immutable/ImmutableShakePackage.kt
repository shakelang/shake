package io.github.shakelang.shake.processor.program.immutable

import io.github.shakelang.shake.processor.program.types.*
import io.github.shakelang.shake.processor.program.types.code.ShakeScope

open class ImmutableShakePackage : ShakePackage {
    override val baseProject: ImmutableShakeProject
    override val name: String
    override val parent: ImmutableShakePackage?
    override val subpackages: List<ImmutableShakePackage>
    override val classes: List<ImmutableShakeClass>
    override val functions: List<ImmutableShakeFunction>
    override val fields: List<ShakeField>

    override val qualifiedName: String get() = (parent?.qualifiedName?.plus(".") ?: "") + (name)
    override val scope: ShakeScope = Scope()

    constructor(
        baseProject: ImmutableShakeProject,
        name: String,
        parent: ImmutableShakePackage?,
        subpackages: List<ImmutableShakePackage>,
        classes: List<ImmutableShakeClass>,
        functions: List<ImmutableShakeFunction>,
        fields: List<ShakeField>
    ) {
        this.baseProject = baseProject
        this.name = name
        this.parent = parent
        this.subpackages = subpackages
        this.classes = classes
        this.functions = functions
        this.fields = fields
    }

    override fun getPackage(name: String): ImmutableShakePackage {
        return subpackages.find { it.name == name } ?: throw IllegalArgumentException("No such package $name")
    }

    override fun getPackage(name: Array<String>): ImmutableShakePackage {
        return name.fold(this) { acc, pkgName -> acc.getPackage(pkgName) }
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

    private inner class Scope : ImmutableShakeScope {
        override val parent: ImmutableShakeScope get() = baseProject.projectScope

        override fun get(name: String): ShakeAssignable? {
            return fields.find { it.name == name } ?: parent.get(name)
        }

        override fun set(value: ShakeDeclaration) {
            throw IllegalStateException("Cannot set a value in a package scope")
        }

        override fun getFunctions(name: String): List<ImmutableShakeFunction> {
            return functions.filter { it.name == name } + parent.getFunctions(name)
        }

        override fun setFunctions(function: ShakeFunction) {
            throw IllegalStateException("Cannot set a function in a package scope")
        }

        override fun getClass(name: String): ImmutableShakeClass? {
            return classes.find { it.name == name } ?: parent.getClass(name)
        }

        override fun setClass(klass: ShakeClass) {
            throw IllegalStateException("Cannot set a class in a package scope")
        }
    }
}