package com.shakelang.shake.parser.node

import com.shakelang.shake.parser.node.variables.ShakeVariableUsageNode
import com.shakelang.util.shason.JSON

@Suppress("unused")
open class ShakeVariableType : ShakeNode {
    val type: Type

    constructor(subtype: ShakeNamespaceNode?) {
        type = Type.OBJECT
    }

    constructor(type: Type) {
        this.type = type
    }

    override fun toString(): String = JSON.stringify(this.json)

    override val json: Map<String, *>
        get() = toJson()

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "type" to type.name.lowercase(),
        )

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeVariableType) return false
        if (type != other.type) return false

        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeVariableType) return false
        if (type != other.type) return false
        return true
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }

    class Object(val namespace: ShakeNamespaceNode?) : ShakeVariableType(Type.OBJECT)
    class Array(val subtype: ShakeVariableType) : ShakeVariableType(Type.ARRAY)

    enum class Type {
        UNKNOWN,
        DYNAMIC,
        BYTE,
        SHORT,
        INTEGER,
        LONG,
        FLOAT,
        DOUBLE,
        UNSIGNED_BYTE,
        UNSIGNED_SHORT,
        UNSIGNED_INTEGER,
        UNSIGNED_LONG,
        BOOLEAN,
        CHAR,
        ARRAY,
        OBJECT,
        VOID,
        ;

        override fun toString(): String {
            return name.lowercase()
        }
    }

    companion object {

        val DYNAMIC = ShakeVariableType(Type.DYNAMIC)
        val UNKNOWN = ShakeVariableType(Type.UNKNOWN)
        val BYTE = ShakeVariableType(Type.BYTE)
        val SHORT = ShakeVariableType(Type.SHORT)
        val INTEGER = ShakeVariableType(Type.INTEGER)
        val LONG = ShakeVariableType(Type.LONG)
        val FLOAT = ShakeVariableType(Type.FLOAT)
        val DOUBLE = ShakeVariableType(Type.DOUBLE)
        val UNSIGNED_BYTE = ShakeVariableType(Type.UNSIGNED_BYTE)
        val UNSIGNED_SHORT = ShakeVariableType(Type.UNSIGNED_SHORT)
        val UNSIGNED_INTEGER = ShakeVariableType(Type.UNSIGNED_INTEGER)
        val UNSIGNED_LONG = ShakeVariableType(Type.UNSIGNED_LONG)
        val BOOLEAN = ShakeVariableType(Type.BOOLEAN)
        val CHAR = ShakeVariableType(Type.CHAR)
        val VOID = ShakeVariableType(Type.VOID)

        fun objectType(subtype: ShakeNamespaceNode): ShakeVariableType = Object(subtype)

        fun objectType(subtype: ShakeVariableUsageNode): ShakeVariableType {
            val namespace = mutableListOf<String>()
            var node: ShakeValuedNode? = subtype
            while (node is ShakeVariableUsageNode) {
                namespace += node.identifier.name
                node = node.identifier.parent
            }
            if (node != null) {
                throw IllegalArgumentException("Invalid subtype for OBJECT type")
            }
            return objectType(ShakeNamespaceNode(subtype.map, namespace.reversed().toTypedArray()))
        }

        fun arrayType(subtype: ShakeVariableType): ShakeVariableType = Array(subtype)
    }
}
