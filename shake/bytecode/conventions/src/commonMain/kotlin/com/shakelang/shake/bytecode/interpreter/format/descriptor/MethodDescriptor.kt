package com.shakelang.shake.bytecode.interpreter.format.descriptor

import com.shakelang.util.io.streaming.input.BufferedInputStream
import com.shakelang.util.io.streaming.input.InputStream
import com.shakelang.util.io.streaming.input.bufferedStream
import com.shakelang.util.io.streaming.input.byteStream

/**
 * A class to represent a method descriptor
 * @param name The name of the method
 * @param parameters The parameters of the method
 * @param returnType The return type of the method
 * @constructor Creates a [MethodDescriptor] with the given [name], [parameters] and [returnType]
 *
 * @throws IllegalArgumentException If the [name] is empty
 *
 * @since 0.1.0
 * @version 0.1.0
 */
class MethodDescriptor(

    /**
     * The name of the method
     * @since 0.1.0
     * @version 0.1.0
     */
    val name: String,

    /**
     * The parameters of the method
     * @since 0.1.0
     * @version 0.1.0
     */
    val parameters: Array<TypeDescriptor>,

    /**
     * The return type of the method
     * @since 0.1.0
     * @version 0.1.0
     */
    val returnType: TypeDescriptor,
) {
    init {
        if (name.isEmpty()) throw IllegalArgumentException("Method name cannot be empty")
    }

    /**
     * The parameter count of the method
     * (The size of the [parameters] list)
     * @since 0.1.0
     * @version 0.1.0
     */
    val parameterCount: Int
        get() = parameters.size

    /**
     * The descriptor of the method
     * @since 0.1.0
     * @version 0.1.0
     */
    val descriptor: String
        get() = "$name(${parameters.joinToString(",") { it.descriptor }})${returnType.descriptor}"

    /**
     * Get the string representation of the [MethodDescriptor]
     * @return The string representation of the [MethodDescriptor]
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun toString(): String = descriptor

    /**
     * Check if the given [MethodDescriptor] is equal to this [MethodDescriptor]
     * @param other The [MethodDescriptor] to check
     * @return If the given [MethodDescriptor] is equal to this [MethodDescriptor]
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MethodDescriptor) return false

        if (name != other.name) return false
        if (!parameters.contentEquals(other.parameters)) return false
        if (returnType != other.returnType) return false

        return true
    }

    /**
     * Get the hash code of this [MethodDescriptor]
     * @return The hash code of this [MethodDescriptor]
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + parameters.contentHashCode()
        result = 31 * result + returnType.hashCode()
        return result
    }

    companion object {

        /**
         * Parse a [MethodDescriptor] from the given [BufferedInputStream]
         * @param stream The [BufferedInputStream] to parse the [MethodDescriptor] from
         * @return The parsed [MethodDescriptor]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun parse(stream: BufferedInputStream): MethodDescriptor {
            val nameBuilder = buildString {
                var char = stream.read().toChar()
                while (char != '(') {
                    append(char)
                    char = stream.read().toChar()
                }
            }

            val parameters = mutableListOf<TypeDescriptor>()
            if (stream.peek().toChar() != ')') {
                do {
                    parameters.add(TypeDescriptor.parse(stream))
                } while (stream.read().toChar() == ',')
            } else {
                stream.read()
            }

            val returnType = TypeDescriptor.parse(stream)

            return MethodDescriptor(nameBuilder, parameters.toTypedArray(), returnType)
        }

        /**
         * Parse a [MethodDescriptor] from the given [InputStream]
         * @param descriptor The [InputStream] to parse the [MethodDescriptor] from
         * @return The parsed [MethodDescriptor]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun parse(descriptor: InputStream): MethodDescriptor = parse(descriptor.bufferedStream)

        /**
         * Parse a [MethodDescriptor] from the given [String]
         * @param descriptor The [String] to parse the [MethodDescriptor] from
         * @return The parsed [MethodDescriptor]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun parse(descriptor: String): MethodDescriptor = parse(BufferedInputStream(descriptor.byteStream()))
    }
}
