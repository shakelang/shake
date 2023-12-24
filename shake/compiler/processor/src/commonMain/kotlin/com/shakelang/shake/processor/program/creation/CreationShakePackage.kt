package com.shakelang.shake.processor.program.creation

import com.shakelang.shake.parser.node.ShakeFileNode
import com.shakelang.shake.parser.node.ShakeImportNode
import com.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import com.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import com.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import com.shakelang.shake.processor.ShakeASTProcessor
import com.shakelang.shake.processor.program.types.ShakePackage

open class CreationShakePackage(
    override val baseProject: CreationShakeProject,
    override val name: String,
    override val parent: CreationShakePackage? = null,
    override val subpackages: MutableList<CreationShakePackage> = mutableListOf(),
    override val classes: MutableList<CreationShakeClass> = mutableListOf(),
    override val functions: MutableList<CreationShakeMethod> = mutableListOf(),
    override val fields: MutableList<CreationShakeField> = mutableListOf()
) : ShakePackage {

    override val qualifiedName: String get() = "${parent?.qualifiedName?.plus(".") ?: ""}$name"
    override val scope: CreationShakeScope = Scope()
    private val files: MutableList<FileEntry> = mutableListOf()

    fun requirePackage(name: String): CreationShakePackage {
        return subpackages.find { it.name == name } ?: CreationShakePackage(baseProject, name, this).let {
            subpackages.add(it)
            it
        }
    }

    fun requirePackage(name: Array<String>): CreationShakePackage {
        return name.fold(this) { acc, pkgName -> acc.requirePackage(pkgName) }
    }

    fun requirePackage(name: List<String>): CreationShakePackage {
        return name.fold(this) { acc, pkgName -> acc.requirePackage(pkgName) }
    }

    override fun getPackage(name: String): CreationShakePackage? {
        return subpackages.find { it.name == name }
    }

    override fun getPackage(name: Array<String>): CreationShakePackage? {
        if(name.isEmpty()) return this
        return getPackage(name.first())?.getPackage(name.drop(1))
    }

    override fun getPackage(name: List<String>): CreationShakePackage? {
        if(name.isEmpty()) return this
        return getPackage(name.first())?.getPackage(name.drop(1))
    }

    open fun putFile(name: String, contents: ShakeFileNode) {

        this.files.add(FileEntry(name, contents))

    }

    open fun putFile(name: Array<String>, contents: ShakeFileNode) {
        val pkg = name.sliceArray(0 until name.size - 1)
        val file = name.last()
        requirePackage(pkg).putFile(file, contents)
    }



    /**
     * Phase 1: Register all classes
     * [See in the Specification](https://specification.shakelang.com/compiler/processor/#phase-1)
     */
    override fun phase1() {
        println("Phase 1 of package $qualifiedName")
        this.files.forEach { file ->
            file.classes.forEach { clz ->
                if (classes.any { it.name == clz.name }) {
                    throw IllegalStateException("Class ${clz.name} already exists")
                }
                classes.add(CreationShakeClass.from(baseProject, this, file.scope, clz))
            }
        }
        this.subpackages.forEach { it.phase1() }
    }

    /**
     * Phase 2: Link Superclasses and Interfaces
     * [See in the Specification](https://specification.shakelang.com/compiler/processor/#phase-2)
     */
    override fun phase2() {
        println("Phase 2 of package $qualifiedName")
        this.classes.forEach { it.phase2() }
        this.subpackages.forEach { it.phase2() }
    }

    /**
     * Phase 3: Process all methods and fields (without code)
     * [See in the Specification](https://specification.shakelang.com/compiler/processor/#phase-3)
     */
    override fun phase3() {
        println("Phase 3 of package $qualifiedName")
        this.files.forEach { file ->
            file.functions.forEach {
                val method = CreationShakeMethod.from(baseProject, this, file.scope, it)
                functions.add(method)
            }
            file.fields.forEach {
                val field = CreationShakeField.from(baseProject, this, file.scope, it)
                fields.add(field)
            }
        }
        this.classes.forEach { it.phase3() }
        this.subpackages.forEach { it.phase3() }
    }

    /**
     * Phase 4: Process all code
     * [See in the Specification](https://specification.shakelang.com/compiler/processor/#phase-4)
     */
    override fun phase4() {
        println("Phase 4 of package $qualifiedName")
        classes.forEach { it.phase4() }
        functions.forEach { it.phase4() }
        fields.forEach { it.phase4() }
        subpackages.forEach { it.phase4() }
    }

    private inner class FileEntry (
        val name: String,
        contents: ShakeFileNode,
    ) {
        val imports: List<ShakeImportNode> = contents.children.filterIsInstance<ShakeImportNode>()
        val scope: CreationShakeScope = FileScope(name, imports)
        val classes: List<ShakeClassDeclarationNode> = contents.children.filterIsInstance<ShakeClassDeclarationNode>()
        val functions: List<ShakeFunctionDeclarationNode> = contents.children.filterIsInstance<ShakeFunctionDeclarationNode>()
        val fields: List<ShakeVariableDeclarationNode> = contents.children.filterIsInstance<ShakeVariableDeclarationNode>()
    }

    private inner class Scope : CreationShakeScope() {
        override val uniqueName: String
            get() = qualifiedName
        override val parent: CreationShakeScope get() = baseProject.projectScope
        override val project get() = baseProject

        override fun get(name: String): CreationShakeAssignable? {
            return fields.find { it.name == name } ?: parent.get(name)
        }

        override fun set(value: CreationShakeDeclaration) {
            throw IllegalStateException("Cannot set a value in a package scope")
        }

        override fun getFunctions(name: String): List<CreationShakeMethod> {
            return functions.filter { it.name == name } + parent.getFunctions(name)
        }

        override fun setFunctions(function: CreationShakeMethod) {
            throw IllegalStateException("Cannot set a function in a package scope")
        }

        override fun getClass(name: String): CreationShakeClass? {
            return classes.find { it.name == name } ?: parent.getClass(name)
        }

        override fun setClass(klass: CreationShakeClass) {
            throw IllegalStateException("Cannot set a class in a package scope")
        }

        override val processor: ShakeASTProcessor
            get() = parent.processor
    }

    private inner class FileScope(
        val name: String,
        val imports: List<ShakeImportNode>
    ) : CreationShakeScope() {

        override val uniqueName: String
            get() = "file:$name"

        private val explicitImports = imports.filter {
            it.import.last() != "*"
        }.map {
            it.import
        }

        private val wildcardImports = imports.filter {
            it.import.last() == "*"
        }.map {
            it.import.dropLast(1)
        }

        private lateinit var wildcardImportedPackages: List<CreationShakePackage>
        private lateinit var wildcardImportedClasses: List<CreationShakeClass>

        private lateinit var importedClasses: List<CreationShakeClass>
        private lateinit var importedFunctions: List<CreationShakeMethod>
        private lateinit var importedFields: List<CreationShakeField>

        override val parent: CreationShakeScope
            get() = baseProject.projectScope

        fun lazyLoadImportedWildcardPackages() {
            if(this::wildcardImportedPackages.isInitialized) return
            wildcardImportedPackages = wildcardImports.mapNotNull { baseProject.getPackage(it) }
        }

        fun lazyLoadImportedWildcardClasses() {
            if(this::wildcardImportedClasses.isInitialized) return
            wildcardImportedClasses = wildcardImports.mapNotNull { baseProject.getClass(it) }
        }

        fun lazyLoadWildcards() {
            lazyLoadImportedWildcardPackages()
            lazyLoadImportedWildcardClasses()
        }

        fun lazyLoadImportedClasses() {
            if(this::importedClasses.isInitialized) return
            lazyLoadWildcards()
            val imports = wildcardImportedPackages.flatMap { it.classes }.toMutableList()
            imports.addAll(wildcardImportedClasses.flatMap { it.classes })
            imports.addAll(explicitImports.mapNotNull { baseProject.getClass(it) })
            importedClasses = imports
        }

        fun lazyLoadImportedFunctions() {
            if(this::importedFunctions.isInitialized) return
            lazyLoadImportedWildcardPackages()
            val imports = wildcardImports.flatMap { baseProject.getPackage(it)?.functions ?: listOf() }.toMutableList()
            imports.addAll(explicitImports.flatMap { baseProject.getFunctions(it) })
            importedFunctions = imports
        }

        fun lazyLoadImportedFields() {
            if(this::importedFields.isInitialized) return
            lazyLoadImportedWildcardPackages()
            val imports = wildcardImports.flatMap { baseProject.getPackage(it)?.fields ?: listOf() }.toMutableList()
            imports.addAll(explicitImports.mapNotNull { baseProject.getField(it) })
            importedFields = imports
        }

        override val project: CreationShakeProject get() = baseProject
        override val processor: ShakeASTProcessor get() = parent.processor

        override fun get(name: String): CreationShakeAssignable? {
            lazyLoadImportedFields()
            return importedFields.find { it.name == name } ?: parent.get(name)
        }

        override fun set(value: CreationShakeDeclaration) {
            throw IllegalStateException("Cannot set a value in a package scope")
        }

        override fun getFunctions(name: String): List<CreationShakeMethod> {
            lazyLoadImportedFunctions()
            return importedFunctions?.filter { it.name == name }?.plus(parent.getFunctions(name)) ?: parent.getFunctions(name)
        }

        override fun setFunctions(function: CreationShakeMethod) {
            throw IllegalStateException("Cannot set a function in a package scope")
        }

        override fun getClass(name: String): CreationShakeClass? {
            lazyLoadImportedClasses()
            return importedClasses?.find { it.name == name } ?: parent.getClass(name)
        }

        override fun setClass(klass: CreationShakeClass) {
            throw IllegalStateException("Cannot set a class in a package scope")
        }
    }
}
