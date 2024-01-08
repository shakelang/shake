package com.shakelang.shake.processor.program.creation

import com.shakelang.shake.parser.node.ShakeFileNode
import com.shakelang.shake.parser.node.ShakePackageNode
import com.shakelang.shake.processor.ShakeASTProcessor
import com.shakelang.shake.processor.ShakeProcessor
import com.shakelang.shake.processor.program.types.ShakeProject

class CreationShakeProject(
    processor: ShakeASTProcessor,
    override val subpackages: MutableList<CreationShakePackage> = mutableListOf(),
) : ShakeProject {
    private val scopes = mutableListOf<CreationShakeScope>()

    val cores = Cores()

    override val projectScope = object : CreationShakeScope() {
        override val uniqueName: String
            get() = "project"
        override val parent: CreationShakeScope? = null
        override val project: CreationShakeProject get() = this@CreationShakeProject

        override val processor: ShakeASTProcessor = processor

        lateinit var imported: Array<CreationShakePackage>

        fun lazyLoadImportedPackages() {
            if (this::imported.isInitialized) return
            imported = arrayOf(
                getPackage("shake/lang"),
                getPackage("shake/js"),
            ).filterNotNull().toTypedArray()
        }

        override fun get(name: String): CreationShakeAssignable? {
            debug("scope", "Searching for field $name in project scope")

            lazyLoadImportedPackages()
            for (import in imported) {
                val field = import.fields.find { it.name == name }
                if (field != null) return field
            }
            return getField(name)
        }

        override fun set(value: CreationShakeDeclaration) {
            throw IllegalStateException("Cannot set a value in the project scope")
        }

        override fun getFunctions(name: String): List<CreationShakeMethod> {
            debug("scope", "Searching for function $name in project scope")

            lazyLoadImportedPackages()
            val functions = mutableListOf<CreationShakeMethod>()
            for (import in imported) {
                functions += import.functions.filter { it.name == name }
            }
            return functions
        }

        override fun setFunctions(function: CreationShakeMethod) {
            throw IllegalStateException("Cannot set a function in the project scope")
        }

        override fun getClass(name: String): CreationShakeClass? {
            debug("scope", "Searching for class $name in project scope")

            lazyLoadImportedPackages()
            for (import in imported) {
                val clazz = import.classes.find { it.name == name }
                if (clazz != null) return clazz
            }
            return this@CreationShakeProject.getClass(name)
        }

        override fun setClass(klass: CreationShakeClass) {
            throw IllegalStateException("Cannot set a class in the project scope")
        }
    }

    fun putFile(name: String, content: ShakeFileNode) {
        val pkgNode =
            (content.children.find { it is ShakePackageNode } ?: error("No package node found")) as ShakePackageNode
        val pkgName = pkgNode.pkg
        requirePackage(pkgName).putFile(name, content)
    }

    override fun getPackage(name: String): CreationShakePackage? {
        if (name.contains(".") || name.contains("/")) {
            return getPackage(
                name.split(".", "/")
                    .toTypedArray(),
            )
        }
        return subpackages.find { it.name == name }
    }

    override fun getPackage(name: Array<String>): CreationShakePackage? {
        if (name.isEmpty()) throw IllegalArgumentException("Cannot get package from empty name")
        return getPackage(name.first())?.getPackage(name.drop(1).toTypedArray())
    }

    override fun getPackage(name: List<String>): CreationShakePackage? {
        if (name.isEmpty()) throw IllegalArgumentException("Cannot get package from empty name")
        return getPackage(name.first())?.getPackage(name.drop(1).toTypedArray())
    }

    fun requirePackage(name: String): CreationShakePackage {
        if (name.contains(".")) return requirePackage(name.split(".").toTypedArray())
        return subpackages.find { it.name == name } ?: CreationShakePackage(this, name).let {
            subpackages.add(it)
            it
        }
    }

    fun requirePackage(name: Array<String>): CreationShakePackage {
        if (name.isEmpty()) throw IllegalArgumentException("Cannot get package from empty name")
        return requirePackage(name.first()).requirePackage(name.drop(1).toTypedArray())
    }

    fun requirePackage(name: List<String>): CreationShakePackage {
        if (name.isEmpty()) throw IllegalArgumentException("Cannot get package from empty name")
        return requirePackage(name.first()).requirePackage(name.drop(1).toTypedArray())
    }

    override fun getClass(name: List<String>): CreationShakeClass? = super.getClass(name) as CreationShakeClass?
    override fun getClass(name: Array<String>): CreationShakeClass? = super.getClass(name) as CreationShakeClass?
    override fun getClass(clz: String): CreationShakeClass? = super.getClass(clz) as CreationShakeClass?

    @Suppress("UNCHECKED_CAST")
    override fun getFunctions(name: List<String>): List<CreationShakeMethod> =
        super.getFunctions(name) as List<CreationShakeMethod>

    @Suppress("UNCHECKED_CAST")
    override fun getFunctions(name: Array<String>): List<CreationShakeMethod> =
        super.getFunctions(name) as List<CreationShakeMethod>

    @Suppress("UNCHECKED_CAST")
    override fun getFunctions(name: String): List<CreationShakeMethod> =
        super.getFunctions(name) as List<CreationShakeMethod>

    override fun getField(name: Array<String>): CreationShakeField? = super.getField(name) as CreationShakeField?

    override fun getField(name: List<String>): CreationShakeField? = super.getField(name) as CreationShakeField?

    override fun getField(name: String): CreationShakeField? = super.getField(name) as CreationShakeField?

    fun finish() {
        this.phase1()
        this.phase2()
        this.phase3()
        this.phase4()
    }

    fun addScope(creationShakeScope: CreationShakeScope) {
        this.scopes.add(creationShakeScope)
    }

    override fun phase1() {
        debug("phases", "Starting phase 1 of project")
        this.subpackages.forEach { it.phase1() }
        debug("phases", "Finished phase 1 of project")
    }

    override fun phase2() {
        debug("phases", "Starting phase 2 of project")
        this.subpackages.forEach { it.phase2() }
        debug("phases", "Finished phase 2 of project")
    }

    override fun phase3() {
        debug("phases", "Starting phase 3 of project")
        this.subpackages.forEach { it.phase3() }
        debug("phases", "Finished phase 3 of project")
    }

    override fun phase4() {
        debug("phases", "Starting phase 4 of project")
        this.subpackages.forEach { it.phase4() }
        debug("phases", "Finished phase 4 of project")
    }

    class ClassRequirement(val name: String, val then: (CreationShakeClass) -> Unit)
    private class PackageRequirement(val name: String, val then: (CreationShakePackage) -> Unit)

    inner class Cores {
        val ObjectClass get() = getClass("shake.lang.Object") ?: error("Object class not found")
        val StringClass get() = getClass("shake.lang.String") ?: error("String class not found")

        val Object: CreationShakeType get() = CreationShakeType.objectType(ObjectClass)
        val String: CreationShakeType get() = CreationShakeType.objectType(StringClass)
    }

    companion object {
        val debug = ShakeProcessor.debug.child("creation", "project")
    }
}
