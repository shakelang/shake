package io.github.shakelang.shake.processor.program.immutable

import io.github.shakelang.shake.processor.program.types.*
import io.github.shakelang.shake.processor.program.types.code.ShakeScope
import io.github.shakelang.shake.processor.util.Pointer
import io.github.shakelang.shake.processor.util.latePoint
import io.github.shakelang.shake.processor.util.point
import io.github.shakelang.shason.json

open class ImmutableShakeProject : ShakeProject {
    final override val subpackages: List<ImmutableShakePackage>
    final override val classes: List<ImmutableShakeClass>
    final override val functions: List<ImmutableShakeFunction>
    final override val fields: List<ShakeField>

    override val projectScope: ImmutableShakeScope
    var initFinished: Boolean private set
    private var classPointers: MutableMap<String, Pointer<ImmutableShakeClass>> = mutableMapOf()
    private var packagePointers: MutableMap<String, Pointer<ImmutableShakePackage>> = mutableMapOf()

    constructor(
        subpackages: List<ImmutableShakePackage>,
        classes: List<ImmutableShakeClass>,
        functions: List<ImmutableShakeFunction>,
        fields: List<ShakeField>
    ) {
        this.initFinished = false
        this.subpackages = subpackages
        this.classes = classes
        this.functions = functions
        this.fields = fields

        projectScope = object : ImmutableShakeScope {
            override val parent: ImmutableShakeScope? = null

            override fun get(name: String): ShakeAssignable? {
                return fields.find { it.name == name }
            }

            override fun getFunctions(name: String): List<ImmutableShakeFunction> {
                return functions.filter { it.name == name }
            }

            override fun getClass(name: String): ImmutableShakeClass? {
                return classes.find { it.name == name }
            }
        }

        this.initFinished = true
    }

    override fun getPackage(name: String): ImmutableShakePackage {
        if(name.contains(".")) return getPackage(name.split(".").toTypedArray())
        return subpackages.find { it.name == name } ?: throw Error("Package $name not found")
    }

    override fun getPackage(name: Array<String>): ImmutableShakePackage {
        if(name.isEmpty()) throw IllegalArgumentException("Cannot get package from empty name")
        return getPackage(name.first()).getPackage(name.drop(1).toTypedArray())
   }

    fun getShakePackagePointer(name: String): Pointer<ImmutableShakePackage> {
        return packagePointers.getOrPut(name) {
            if(initFinished) point(getPackage(name)) else latePoint()
        }
    }

    override fun getClass(pkg: Array<String>, name: String): ShakeClass? {
        return this.getPackage(pkg).classes.find { it.name == name }
    }

    override fun getClass(clz: String): ImmutableShakeClass? {
        val parts = clz.split(".")
        val name = parts.last()
        val pkg = parts.dropLast(1).toTypedArray()
        return if(pkg.isEmpty()) this.classes.find { it.name == name }
        else this.getPackage(pkg).classes.find { it.name == name }
    }

    fun getClassPointer(clz: String): Pointer<ImmutableShakeClass> {
        return classPointers.getOrPut(clz) {
            if(initFinished) point(getClass(clz) ?: throw Error("Class $clz not found"))
            else latePoint()
        }
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