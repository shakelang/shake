package com.shakelang.shake.processor.program.types

import com.shakelang.shake.processor.ShakeProcessor
import com.shakelang.shake.processor.program.types.code.ShakeScope

/**
 * Represents a package in the Shake language.
 * A package is a namespace that organizes classes, methods, fields, and other packages.
 *
 * @since 0.1.0
 */
interface ShakePackage {
    /**
     * The base project to which this package belongs.
     */
    val baseProject: ShakeProject

    /**
     * The name of the package.
     */
    val name: String

    /**
     * The parent package, if any.
     */
    val parent: ShakePackage?

    /**
     * A list of subpackages contained within this package.
     */
    val subpackages: List<ShakePackage>

    /**
     * A list of classes contained within this package.
     */
    val classes: List<ShakeClass>

    /**
     * A list of functions defined in this package.
     */
    val functions: List<ShakeMethod>

    /**
     * A list of fields declared in this package.
     */
    val fields: List<ShakeField>

    /**
     * The prefix used for qualifying names in this package.
     */
    val qualifierPrefix: String
        get() = (parent?.qualifierPrefix ?: "") + name + "/"

    /**
     * The fully qualified name of this package.
     */
    val qualifiedName: String
        get() = (parent?.qualifierPrefix ?: "") + name

    /**
     * The scope of this package.
     */
    val scope: ShakeScope

    /**
     * Retrieves a subpackage by name.
     *
     * @param name The name of the subpackage to retrieve.
     * @return The found subpackage, or null if not found.
     */
    fun getPackage(name: String): ShakePackage? {
        return subpackages.find { it.name == name }
    }

    /**
     * Retrieves a subpackage by a path of names.
     *
     * @param name An array of names representing the path to the subpackage.
     * @return The found subpackage, or null if not found.
     */
    fun getPackage(name: Array<String>): ShakePackage? {
        return if (name.isEmpty()) this else getPackage(name.first())?.getPackage(name.drop(1))
    }

    /**
     * Retrieves a subpackage by a path of names.
     *
     * @param name A list of names representing the path to the subpackage.
     * @return The found subpackage, or null if not found.
     */
    fun getPackage(name: List<String>): ShakePackage? {
        return if (name.isEmpty()) this else getPackage(name.first())?.getPackage(name.drop(1))
    }

    /**
     * Retrieves a class by a path of names.
     *
     * @param name A list of names representing the path to the class.
     * @return The found class, or null if not found.
     */
    fun getClass(name: List<String>): ShakeClass? {
        debug("Searching for class: ${name.joinToString(".")} in package: $qualifiedName")

        if (name.isEmpty()) return null
        if (name.size == 1) return getClass(name.first())

        val subClass = getClass(name.first())
        val searched0 = subClass?.getClass(name.drop(1))
        if (searched0 != null) return searched0

        val searched1 = getPackage(name.first())
        if (searched1 != null) return searched1.getClass(name.drop(1))

        return null
    }

    /**
     * Retrieves a class by an array of names.
     *
     * @param name An array of names representing the path to the class.
     * @return The found class, or null if not found.
     */
    fun getClass(name: Array<String>) = getClass(name.toList())

    /**
     * Retrieves a class by its name.
     *
     * @param name The name of the class to retrieve.
     * @return The found class, or null if not found.
     */
    fun getClass(name: String): ShakeClass? {
        return classes.find { it.name == name }
    }

    /**
     * Retrieves functions by a path of names.
     *
     * @param name A list of names representing the path to the functions.
     * @return A list of matching functions, or an empty list if none are found.
     */
    fun getFunctions(name: List<String>): List<ShakeMethod>? {
        if (name.isEmpty()) return emptyList()
        if (name.size == 1) return getFunctions(name.first())
        val subClass = getClass(name.first())
        val subClassResults = subClass?.getMethods(name.drop(1)) ?: emptyList()
        val subPackageResults = getPackage(name.first())?.getFunctions(name.drop(1)) ?: emptyList()
        return subClassResults + subPackageResults
    }

    /**
     * Retrieves functions by an array of names.
     *
     * @param name An array of names representing the path to the functions.
     * @return A list of matching functions, or an empty list if none are found.
     */
    fun getFunctions(name: Array<String>) = getFunctions(name.toList())

    /**
     * Retrieves functions by their name.
     *
     * @param name The name of the functions to retrieve.
     * @return A list of matching functions, or an empty list if none are found.
     */
    fun getFunctions(name: String): List<ShakeMethod> {
        return functions.filter { it.name == name }
    }

    /**
     * Retrieves a field by a path of names.
     *
     * @param name A list of names representing the path to the field.
     * @return The found field, or null if not found.
     */
    fun getField(name: List<String>): ShakeField? {
        if (name.isEmpty()) return null
        if (name.size == 1) return getField(name.first())
        val subClass = getClass(name.first())
        val searched0 = subClass?.getField(name.drop(1))
        if (searched0 != null) return searched0
        val searched1 = getPackage(name.first())
        if (searched1 != null) return searched1.getField(name.drop(1))
        return null
    }

    /**
     * Retrieves a field by an array of names.
     *
     * @param name An array of names representing the path to the field.
     * @return The found field, or null if not found.
     */
    fun getField(name: Array<String>) = getField(name.toList())

    /**
     * Retrieves a field by its name.
     *
     * @param name The name of the field to retrieve.
     * @return The found field, or null if not found.
     */
    fun getField(name: String): ShakeField? {
        return fields.find { it.name == name }
    }

    /**
     * Converts this package to a JSON representation.
     * Useful for serialization or debugging purposes.
     *
     * @return A map representing the JSON structure of this package.
     */
    fun toJson(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "parent" to parent?.name,
            "subpackages" to subpackages.map { it.toJson() },
            "classes" to classes.map { it.toJson() },
            "functions" to functions.map { it.toJson() },
            "fields" to fields.map { it.toJson() },
        )
    }

    /**
     * Executes the first phase of the package processing.
     */
    fun phase1()

    /**
     * Executes the second phase of the package processing.
     */
    fun phase2()

    /**
     * Executes the third phase of the package processing.
     */
    fun phase3()

    /**
     * Executes the fourth phase of the package processing.
     */
    fun phase4()

    companion object {
        /**
         * Debugging utility for this package.
         */
        val debug = ShakeProcessor.debug.child("package")
    }
}
