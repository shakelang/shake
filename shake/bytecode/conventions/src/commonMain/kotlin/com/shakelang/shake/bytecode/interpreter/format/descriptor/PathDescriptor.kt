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
 * @param packageEntities The package entities
 * @param classEntities The class entities
 * @param entity The entity
 * @constructor Creates a new [PathDescriptor]
 * @see [the docs](https://specification.shakelang.com/bytecode/storage-format#path-signature) for more information
 */
class PathDescriptor(
    val packageEntities: Array<String>,
    val classEntities: Array<String>,
    val entity: String,
) {

    val descriptor
        get() = buildString {
            packageEntities.forEach { append("$it/") }
            classEntities.forEach { append("$it:") }
            append(entity)
        }

    val packagePath get() = packageEntities.joinToString("/")
    val classPath get() = classEntities.joinToString(":")

    companion object {

        /**
         * Parse a [PathDescriptor] from a [String]
         *
         * @param descriptor The descriptor to parse
         * @return The parsed [PathDescriptor]
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
                current.toString()
            )
        }
    }
}