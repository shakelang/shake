package com.shakelang.shake.bytecode.interpreter.format.descriptor

/**
 * Path Signature
 *
 * The path signature is the "path" of a method, class or field. It contains the package name, (optinally the parent class name) and the class/method/field name.
 *
 * The package path is separated by the `/` character. When we have a parent class, we seperate it with the `:` character.
 *
 * For example `shake.lang.String` would be represented as `shake/lang/String`. The method `shake.lang.String::length` would be represented as `shake/lang/String:length`.
 *
 * If we have a class like this:
 *
 * ```shake
 * package shake.lang;
 *
 * class String {
 *     public int length() {
 *         return 0;
 *     }
 *
 *     class Builder {
 *         public String build() {
 *             return "";
 *         }
 *     }
 * }
 * ```
 *
 * The method `shake.lang.String.Builder::build` would be represented as `shake/lang/String:Builder:build`.
 *
 * See [specification](https://spec.shakelang.com/bytecode/storage-format#path-signature)
 *
 * @param packageEntities The package entities
 * @param classEntities The class entities
 * @param entity The entity
 * @constructor Creates a new [PathDescriptor]
 *
 * @since 0.1.0
 * @version 0.1.0
 */
class PathDescriptor(

    /**
     * The package entities
     * @return The package entities
     * @see packagePath
     * @since 0.1.0
     * @version 0.1.0
     */
    val packageEntities: Array<String>,

    /**
     * The class entities
     * @return The class entities
     * @see classPath
     * @since 0.1.0
     * @version 0.1.0
     */
    val classEntities: Array<String>,

    /**
     * The entity
     * @return The entity
     * @since 0.1.0
     * @version 0.1.0
     */
    val entity: String,
) {

    /**
     * String representation of the [PathDescriptor]
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#path-signature)
     *
     * @return The string representation of the [PathDescriptor]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    val descriptor
        get() = buildString {
            packageEntities.forEach { append("$it/") }
            classEntities.forEach { append("$it:") }
            append(entity)
        }

    /**
     * The package path
     * @return The package path
     * @since 0.1.0
     * @version 0.1.0
     * @see packageEntities
     */
    val packagePath get() = packageEntities.joinToString("/")

    /**
     * The class path
     * @return The class path
     * @since 0.1.0
     * @version 0.1.0
     * @see classEntities
     */
    val classPath get() = classEntities.joinToString(":")

    /**
     * The full path
     * @return The full path
     * @since 0.1.0
     * @version 0.1.0
     * @see packagePath
     * @see classPath
     */
    val path get() = "$packagePath/$classPath"

    companion object {

        /**
         * Parse a [PathDescriptor] from a [String]
         *
         * See [specification](https://spec.shakelang.com/bytecode/storage-format#path-signature)
         *
         * @param descriptor The descriptor to parse
         * @return The parsed [PathDescriptor]
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun parse(descriptor: String): PathDescriptor {
            val packageEntities = mutableListOf<String>()
            val classEntities = mutableListOf<String>()

            var current = StringBuilder()

            var finished = false

            for (it in descriptor) {
                if (finished) {
                    current.append(it)
                    continue
                }
                when (it) {
                    '/' -> {
                        packageEntities.add(current.toString())
                        current = StringBuilder()
                    }

                    ':' -> {
                        classEntities.add(current.toString())
                        current = StringBuilder()
                    }

                    '(' -> {
                        current.append(it)
                        finished = true
                    }

                    else -> {
                        current.append(it)
                    }
                }
            }

            return PathDescriptor(
                packageEntities.toTypedArray(),
                classEntities.toTypedArray(),
                current.toString(),
            )
        }
    }
}
