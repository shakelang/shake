package io.github.shakelang.shake.parser.node

import io.github.shakelang.shason.JSON
import kotlin.jvm.JvmField

@Suppress("unused")
open class ShakeVariableType {
    val type: Type

    constructor(subtype: ShakeNamespaceNode?) {
        type = Type.OBJECT
    }

    constructor(type: Type) {
        this.type = type
    }

    override fun toString(): String = JSON.stringify(this.json)

    val json: Map<String, *>
        get() = toJson()

    fun toJson(): Map<String, *> =
        mapOf(
            "name" to "VariableType",
            "type" to type,
        )

    class Object (val namespace: ShakeNamespaceNode?) : ShakeVariableType(Type.OBJECT)
    class Array (val subtype: ShakeVariableType) : ShakeVariableType(Type.ARRAY)

    enum class Type {
        DYNAMIC, BYTE, SHORT, INTEGER, LONG, FLOAT, DOUBLE, BOOLEAN, CHAR, ARRAY, OBJECT, VOID;

        override fun toString(): String {
            return name.lowercase()
        }
    }

    companion object {
        @JvmField
        val DYNAMIC = ShakeVariableType(Type.DYNAMIC)
        @JvmField
        val BYTE = ShakeVariableType(Type.BYTE)
        @JvmField
        val SHORT = ShakeVariableType(Type.SHORT)
        @JvmField
        val INTEGER = ShakeVariableType(Type.INTEGER)
        @JvmField
        val LONG = ShakeVariableType(Type.LONG)
        @JvmField
        val FLOAT = ShakeVariableType(Type.FLOAT)
        @JvmField
        val DOUBLE = ShakeVariableType(Type.DOUBLE)
        @JvmField
        val BOOLEAN = ShakeVariableType(Type.BOOLEAN)
        @JvmField
        val CHAR = ShakeVariableType(Type.CHAR)
        @JvmField
        val VOID = ShakeVariableType(Type.VOID)

        fun objectType(subtype: ShakeNamespaceNode): ShakeVariableType = Object(subtype)

        fun objectType(subtype: ShakeIdentifierNode): ShakeVariableType {
            val namespace = mutableListOf<String>()
            var node: ShakeValuedNode? = subtype
            while (node is ShakeIdentifierNode) {
                namespace += node.name
                node = node.parent
            }
            if(node != null) {
                throw IllegalArgumentException("Invalid subtype for OBJECT type")
            }
            return objectType(ShakeNamespaceNode(subtype.map, namespace.reversed().toTypedArray()))
        }

        fun arrayType(subtype: ShakeVariableType): ShakeVariableType = Array(subtype)
    }
}