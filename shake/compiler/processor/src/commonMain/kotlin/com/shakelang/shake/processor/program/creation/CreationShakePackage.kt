package com.shakelang.shake.processor.program.creation

import com.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import com.shakelang.shake.parser.node.outer.ShakeFieldDeclarationNode
import com.shakelang.shake.parser.node.outer.ShakeFileNode
import com.shakelang.shake.parser.node.outer.ShakeImportNode
import com.shakelang.shake.parser.node.outer.ShakeMethodDeclarationNode
import com.shakelang.shake.processor.ShakeASTProcessor
import com.shakelang.shake.processor.ShakeProcessor
import com.shakelang.shake.processor.program.types.ShakeAssignable
import com.shakelang.shake.processor.program.types.ShakePackage

open class CreationShakePackage(
    override val baseProject: CreationShakeProject,
    override val name: String,
    override val parent: CreationShakePackage? = null,
    override val subpackages: MutableList<CreationShakePackage> = mutableListOf(),
    override val classes: MutableList<CreationShakeClass> = mutableListOf(),
    override val functions: MutableList<CreationShakeMethod> = mutableListOf(),
    override val fields: MutableList<CreationShakeField> = mutableListOf(),
) : ShakePackage {

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
        if (name.isEmpty()) return this
        return getPackage(name.first())?.getPackage(name.drop(1))
    }

    override fun getPackage(name: List<String>): CreationShakePackage? {
        if (name.isEmpty()) return this
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
     * [See in the Specification](https://spec.shakelang.com/compiler/processor/#phase-1)
     */
    override fun phase1() {
        debug("phases", "Phase 1 of package $qualifiedName")
        this.files.forEach { file ->
            val classes = mutableListOf<CreationShakeClass>()
            file.classes.forEach { clz ->
                if (classes.any { it.name == clz.name }) {
                    throw IllegalStateException("Class ${clz.name} already exists")
                }
                val clazz = CreationShakeClass.from(baseProject, this, file.scope, clz)
                classes.add(clazz)
            }
            file.scope.initClasses(classes)
            this.classes.addAll(classes)
            classes.forEach { it.phase1() }
        }
        this.subpackages.forEach { it.phase1() }
    }

    /**
     * Phase 2: Link Superclasses and Interfaces
     * [See in the Specification](https://spec.shakelang.com/compiler/processor/#phase-2)
     */
    override fun phase2() {
        debug("phases", "Phase 2 of package $qualifiedName")
        this.classes.forEach { it.phase2() }
        this.subpackages.forEach { it.phase2() }
    }

    /**
     * Phase 3: Process all methods and fields (without code)
     * [See in the Specification](https://spec.shakelang.com/compiler/processor/#phase-3)
     */
    override fun phase3() {
        debug("phases", "Phase 3 of package $qualifiedName")
        this.files.forEach { file ->
            val functions = mutableListOf<CreationShakeMethod>()
            val fields = mutableListOf<CreationShakeField>()
            file.functions.forEach {
                val method = CreationShakeMethod.from(baseProject, this, file.scope, it)
                functions.add(method)
            }
            file.fields.forEach {
                val field = CreationShakeField.from(baseProject, this, file.scope, it)
                fields.add(field)
            }

            file.scope.initFunctions(functions)
            file.scope.initFields(fields)

            this.functions.addAll(functions)
            this.fields.addAll(fields)
        }
        this.classes.forEach { it.phase3() }
        this.subpackages.forEach { it.phase3() }
    }

    /**
     * Phase 4: Process all code
     * [See in the Specification](https://spec.shakelang.com/compiler/processor/#phase-4)
     */
    override fun phase4() {
        debug("phases", "Phase 4 of package $qualifiedName")
        classes.forEach { it.phase4() }
        functions.forEach { it.phase4() }
        fields.forEach { it.phase4() }
        subpackages.forEach { it.phase4() }
    }

    private inner class FileEntry(
        val name: String,
        contents: ShakeFileNode,
    ) {
        val imports: List<ShakeImportNode> = contents.children.filterIsInstance<ShakeImportNode>()
        val scope = FileScope(name, imports)
        val classes: List<ShakeClassDeclarationNode> = contents.children.filterIsInstance<ShakeClassDeclarationNode>()
        val functions: List<ShakeMethodDeclarationNode> = contents.children.filterIsInstance<ShakeMethodDeclarationNode>()
        val fields: List<ShakeFieldDeclarationNode> = contents.children.filterIsInstance<ShakeFieldDeclarationNode>()
    }

    private inner class Scope : CreationShakeScope() {
        override val uniqueName: String
            get() = qualifiedName
        override val parent: CreationShakeScope get() = baseProject.projectScope
        override val project get() = baseProject

        override fun getField(name: String): CreationShakeAssignable? {
            val field = fields.find { it.name == name }
            if (field != null) {
                debug("scope", "Searching for field $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for field $name in $uniqueName had no result")
            }
            return field ?: parent.getField(name)
        }

        override fun getFields(name: String): List<CreationShakeAssignable> {
            val fields = fields.filter { it.name == name }
            if (fields.isNotEmpty()) {
                debug("scope", "Searching for field $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for field $name in $uniqueName had no result")
            }
            return fields + parent.getFields(name)
        }

        override fun setField(value: CreationShakeDeclaration) {
            throw IllegalStateException("Cannot set a value in a package scope")
        }

        override fun getFunctions(name: String): List<CreationShakeMethod> {
            val functions = functions.filter { it.name == name }
            if (functions.isNotEmpty()) {
                debug("scope", "Searching for function $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for function $name in $uniqueName had no result")
            }
            return functions + parent.getFunctions(name)
        }

        override fun getClass(name: String): CreationShakeClass? {
            val clazz = classes.find { it.name == name }
            if (clazz != null) {
                debug("scope", "Searching for class $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for class $name in $uniqueName had no result")
            }
            return clazz ?: parent.getClass(name)
        }

        override fun getClasses(name: String): List<CreationShakeClass> {
            val classes = classes.filter { it.name == name }
            if (classes.isNotEmpty()) {
                debug("scope", "Searching for class $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for class $name in $uniqueName had no result")
            }
            return classes + parent.getClasses(name)
        }

        override fun getThis(): ShakeAssignable? {
            throw Error("Cannot get this in a package scope")
        }

        override fun getThis(name: String): ShakeAssignable? {
            throw Error("Cannot get this in a package scope")
        }

        override fun getSuper(): ShakeAssignable? {
            throw Error("Cannot get super in a package scope")
        }

        override fun getSuper(name: String): ShakeAssignable? {
            throw Error("Cannot get super in a package scope")
        }

        override val processor: ShakeASTProcessor
            get() = parent.processor
    }

    private inner class FileScope(
        val name: String,
        val imports: List<ShakeImportNode>,
    ) : CreationShakeScope() {

        override val uniqueName: String
            get() = "file:$name"

        override val project: CreationShakeProject get() = baseProject
        override val processor: ShakeASTProcessor get() = parent.processor

        private val explicitImports = imports.filter {
            it.importStrings.last() != "*"
        }.map {
            it.importStrings
        }

        private val wildcardImports = imports.filter {
            it.importStrings.last() == "*"
        }.map {
            it.importStrings.dropLast(1)
        }

        private lateinit var wildcardImportedPackages: List<CreationShakePackage>
        private lateinit var wildcardImportedClasses: List<CreationShakeClass>

        private lateinit var importedClasses: List<CreationShakeClass>
        private lateinit var importedFunctions: List<CreationShakeMethod>
        private lateinit var importedFields: List<CreationShakeField>

        private lateinit var fileFields: List<CreationShakeField>
        private lateinit var fileFunctions: List<CreationShakeMethod>
        private lateinit var fileClasses: List<CreationShakeClass>

        override val parent: CreationShakeScope
            get() = scope

        fun initFields(fields: List<CreationShakeField>) {
            if (this::fileFields.isInitialized) throw IllegalStateException("Cannot initialize fields twice")
            fileFields = fields
        }

        fun initFunctions(functions: List<CreationShakeMethod>) {
            if (this::fileFunctions.isInitialized) throw IllegalStateException("Cannot initialize functions twice")
            fileFunctions = functions
        }

        fun initClasses(classes: List<CreationShakeClass>) {
            if (this::fileClasses.isInitialized) throw IllegalStateException("Cannot initialize classes twice")
            fileClasses = classes
        }

        private fun lazyLoadImportedWildcardPackages() {
            if (this::wildcardImportedPackages.isInitialized) return
            wildcardImportedPackages = wildcardImports.mapNotNull { baseProject.getPackage(it) }
        }

        private fun lazyLoadImportedWildcardClasses() {
            if (this::wildcardImportedClasses.isInitialized) return
            wildcardImportedClasses = wildcardImports.mapNotNull { baseProject.getClass(it) }
        }

        private fun lazyLoadWildcards() {
            lazyLoadImportedWildcardPackages()
            lazyLoadImportedWildcardClasses()
        }

        private fun lazyLoadImportedClasses() {
            if (this::importedClasses.isInitialized) return
            lazyLoadWildcards()
            val imports = wildcardImportedPackages.flatMap { it.classes }.toMutableList()
            imports.addAll(wildcardImportedClasses.flatMap { it.classes })
            imports.addAll(explicitImports.mapNotNull { baseProject.getClass(it) })
            importedClasses = imports
        }

        private fun lazyLoadImportedFunctions() {
            if (this::importedFunctions.isInitialized) return
            lazyLoadImportedWildcardPackages()
            val imports = wildcardImports.flatMap { baseProject.getPackage(it)?.functions ?: listOf() }.toMutableList()
            imports.addAll(explicitImports.flatMap { baseProject.getFunctions(it) })
            importedFunctions = imports
        }

        private fun lazyLoadImportedFields() {
            if (this::importedFields.isInitialized) return
            lazyLoadImportedWildcardPackages()
            val imports = wildcardImports.flatMap { baseProject.getPackage(it)?.fields ?: listOf() }.toMutableList()
            imports.addAll(explicitImports.mapNotNull { baseProject.getField(it) })
            importedFields = imports
        }

        override fun getField(name: String): CreationShakeAssignable? {
            lazyLoadImportedFields()
            val field = importedFields.find { it.name == name }
            if (field != null) {
                debug("scope", "Searching for field $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for field $name in $uniqueName had no result")
            }
            return field ?: parent.getField(name)
        }

        override fun getFields(name: String): List<CreationShakeAssignable> {
            lazyLoadImportedFields()
            val fields = importedFields.filter { it.name == name }
            if (fields.isNotEmpty()) {
                debug("scope", "Searching for field $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for field $name in $uniqueName had no result")
            }
            return fields + parent.getFields(name)
        }

        override fun setField(value: CreationShakeDeclaration) {
            throw IllegalStateException("Cannot set a value in a package scope")
        }

        override fun getFunctions(name: String): List<CreationShakeMethod> {
            lazyLoadImportedFunctions()
            val functions = importedFunctions.filter { it.name == name }
            if (functions.isNotEmpty()) {
                debug("scope", "Searching for function $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for function $name in $uniqueName had no result")
            }
            return functions + parent.getFunctions(name)
        }

        override fun getClass(name: String): CreationShakeClass? {
            lazyLoadImportedClasses()
            val clazz = importedClasses.find { it.name == name }
            if (clazz != null) {
                debug("scope", "Searching for class $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for class $name in $uniqueName had no result")
            }
            return clazz ?: parent.getClass(name)
        }

        override fun getClasses(name: String): List<CreationShakeClass> {
            lazyLoadImportedClasses()
            val classes = importedClasses.filter { it.name == name }
            if (classes.isNotEmpty()) {
                debug("scope", "Searching for class $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for class $name in $uniqueName had no result")
            }
            return classes + parent.getClasses(name)
        }

        override fun getThis(): ShakeAssignable? {
            throw Error("Cannot get this in a package scope")
        }

        override fun getThis(name: String): ShakeAssignable? {
            throw Error("Cannot get this in a package scope")
        }

        override fun getSuper(): ShakeAssignable? {
            throw Error("Cannot get super in a package scope")
        }

        override fun getSuper(name: String): ShakeAssignable? {
            throw Error("Cannot get super in a package scope")
        }
    }

    companion object {
        val debug = ShakeProcessor.debug.child("creation", "package")
    }
}
