package com.shakelang.shake.processor.program.types

import com.shakelang.shake.processor.program.types.code.ShakeScope
import com.shakelang.util.shason.json

/**
 * Represents a project in the Shake language.
 * A project is a high-level structure that contains subpackages and provides methods to access various components like classes, methods, and fields.
 *
 * @since 0.1.0
 */
interface ShakeProject {
    /**
     * A list of subpackages contained within this project.
     */
    val subpackages: List<ShakePackage>

    /**
     * The scope of the project.
     */
    val projectScope: ShakeScope

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
        return if (name.isEmpty()) null else getPackage(name.first())?.getPackage(name.drop(1))
    }

    /**
     * Retrieves a subpackage by a path of names.
     *
     * @param name A list of names representing the path to the subpackage.
     * @return The found subpackage, or null if not found.
     */
    fun getPackage(name: List<String>): ShakePackage? {
        return if (name.isEmpty()) null else getPackage(name.first())?.getPackage(name.drop(1))
    }

    /**
     * Retrieves a class by a path of names.
     *
     * @param name A list of names representing the path to the class.
     * @return The found class, or null if not found.
     */
    fun getClass(name: List<String>): ShakeClass? = getPackage(name.first())?.getClass(name.drop(1))

    /**
     * Retrieves a class by an array of names.
     *
     * @param name An array of names representing the path to the class.
     * @return The found class, or null if not found.
     */
    fun getClass(name: Array<String>): ShakeClass? = getPackage(name.first())?.getClass(name.drop(1))

    /**
     * Retrieves a class by its full name.
     *
     * @param clz The full name of the class, separated by dots or slashes.
     * @return The found class, or null if not found.
     */
    fun getClass(clz: String): ShakeClass? = getClass(clz.split(".", "/"))

    /**
     * Retrieves functions by a path of names.
     *
     * @param name A list of names representing the path to the functions.
     * @return A list of matching functions, or an empty list if none are found.
     */
    fun getFunctions(name: List<String>): List<ShakeMethod> =
        getPackage(name.first())?.getFunctions(name.drop(1)) ?: emptyList()

    /**
     * Retrieves functions by an array of names.
     *
     * @param name An array of names representing the path to the functions.
     * @return A list of matching functions, or an empty list if none are found.
     */
    fun getFunctions(name: Array<String>): List<ShakeMethod> =
        getPackage(name.first())?.getFunctions(name.drop(1)) ?: emptyList()

    /**
     * Retrieves functions by their full name.
     *
     * @param name The full name of the functions, separated by dots or slashes.
     * @return A list of matching functions, or an empty list if none are found.
     */
    fun getFunctions(name: String): List<ShakeMethod> = getFunctions(name.split(".", "/"))

    /**
     * Retrieves a field by a path of names.
     *
     * @param name A list of names representing the path to the field.
     * @return The found field, or null if not found.
     */
    fun getField(name: List<String>): ShakeField? =
        getPackage(name.first())?.getField(name.drop(1))

    /**
     * Retrieves a field by an array of names.
     *
     * @param name An array of names representing the path to the field.
     * @return The found field, or null if not found.
     */
    fun getField(name: Array<String>): ShakeField? =
        getPackage(name.first())?.getField(name.drop(1))

    /**
     * Retrieves a field by its full name.
     *
     * @param name The full name of the field, separated by dots or slashes.
     * @return The found field, or null if not found.
     */
    fun getField(name: String): ShakeField? = getField(name.split(".", "/"))

    /**
     * Converts this project to a JSON representation.
     * Useful for serialization or debugging purposes.
     *
     * @return A map representing the JSON structure of this project.
     */
    fun toJson(): Map<String, Any?> {
        return mapOf(
            "subpackages" to subpackages.map { it.toJson() },
        )
    }

    /**
     * Converts this project to a JSON string representation.
     *
     * @param format Set to true to pretty print the JSON, false by default.
     * @return A JSON string representing this project.
     */
    fun toJsonString(format: Boolean = false): String {
        return json.stringify(toJson(), format)
    }

    /**
     * Executes the first phase of project processing.
     */
    fun phase1()

    /**
     * Executes the second phase of project processing.
     */
    fun phase2()

    /**
     * Executes the third phase of project processing.
     */
    fun phase3()

    /**
     * Executes the fourth phase of project processing.
     */
    fun phase4()
}
