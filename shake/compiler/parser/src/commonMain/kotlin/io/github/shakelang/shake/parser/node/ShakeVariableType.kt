package io.github.shakelang.shake.parser.node

import io.github.shakelang.shason.JSON
import kotlin.jvm.JvmField

@Suppress("unused")
class ShakeVariableType {
    val type: Type
    val subtype: ShakeNamespaceNode?

    constructor(subtype: ShakeNamespaceNode?) {
        type = Type.OBJECT
        this.subtype = subtype
    }

    constructor(type: Type) {
        this.type = type
        subtype = null
    }

    override fun toString(): String = JSON.stringify(this.json)

    val json: Map<String, *>
        get() = toJson()

    fun toJson(): Map<String, *> =
        mapOf(
            "name" to "VariableType",
            "type" to type,
            "subtype" to subtype,
        )

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
        val ARRAY = ShakeVariableType(Type.ARRAY)
        @JvmField
        val VOID = ShakeVariableType(Type.VOID)

        fun OBJECT(subtype: ShakeNamespaceNode): ShakeVariableType =
            ShakeVariableType(subtype)

        fun OBJECT(subtype: ShakeIdentifierNode): ShakeVariableType {
            val namespace = mutableListOf<String>()
            var node: ShakeValuedNode? = subtype
            while (node is ShakeIdentifierNode) {
                namespace += node.name
                node = node.parent
            }
            if(node != null) {
                throw IllegalArgumentException("Invalid subtype for OBJECT type")
            }
            return OBJECT(ShakeNamespaceNode(subtype.map, namespace.reversed().toTypedArray()))

        }
    }
}