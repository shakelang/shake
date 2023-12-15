package com.shakelang.shake.bytecode.interpreter.format.descriptor

import com.shakelang.shake.util.io.streaming.input.BufferedInputStream
import com.shakelang.shake.util.io.streaming.input.InputStream
import com.shakelang.shake.util.io.streaming.input.bufferedStream
import com.shakelang.shake.util.io.streaming.input.byteStream

class MethodDescriptor (
    val name: String,
    val parameters: Array<TypeDescriptor>,
    val returnType: TypeDescriptor
) {
    init {
        if (name.isEmpty()) throw IllegalArgumentException("Method name cannot be empty")
    }

    val parameterCount: Int
        get() = parameters.size

    val descriptor: String
        get() = "$name(${parameters.joinToString(",") { it.descriptor }})${returnType.descriptor}"

    override fun toString(): String = descriptor

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MethodDescriptor) return false

        if (name != other.name) return false
        if (!parameters.contentEquals(other.parameters)) return false
        if (returnType != other.returnType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + parameters.contentHashCode()
        result = 31 * result + returnType.hashCode()
        return result
    }

    companion object {

        fun parse(descriptor: BufferedInputStream): MethodDescriptor {
            val nameBuilder = buildString {
                var char = descriptor.read().toChar()
                while(char != '(') {
                    append(char)
                    char = descriptor.read().toChar()
                }
            }


            val parameters = mutableListOf<TypeDescriptor>()
            if(descriptor.peek().toChar() != ')') {

                do {
                    parameters.add(TypeDescriptor.parse(descriptor))
                } while (descriptor.read().toChar() == ',')

            } else descriptor.read()

            val returnType = TypeDescriptor.parse(descriptor)

            return MethodDescriptor(nameBuilder, parameters.toTypedArray(), returnType)
        }

        fun parse(descriptor: InputStream): MethodDescriptor = parse(descriptor.bufferedStream)

        fun parse(descriptor: String): MethodDescriptor = parse(BufferedInputStream(descriptor.byteStream()))
    }
}