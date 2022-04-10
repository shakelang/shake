package io.github.shakelang.shake.parser.node

import io.github.shakelang.shason.JSON
import kotlin.jvm.JvmField

@Suppress("unused")
class VariableType {
    val type: Type
    val subtype: IdentifierNode?

    constructor(subtype: IdentifierNode?) {
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
        val DYNAMIC = VariableType(Type.DYNAMIC)
        @JvmField
        val BYTE = VariableType(Type.BYTE)
        @JvmField
        val SHORT = VariableType(Type.SHORT)
        @JvmField
        val INTEGER = VariableType(Type.INTEGER)
        @JvmField
        val LONG = VariableType(Type.LONG)
        @JvmField
        val FLOAT = VariableType(Type.FLOAT)
        @JvmField
        val DOUBLE = VariableType(Type.DOUBLE)
        @JvmField
        val BOOLEAN = VariableType(Type.BOOLEAN)
        @JvmField
        val CHAR = VariableType(Type.CHAR)
        @JvmField
        val ARRAY = VariableType(Type.ARRAY)
        @JvmField
        val OBJECT = VariableType(Type.OBJECT)
        @JvmField
        val VOID = VariableType(Type.VOID)
    }
}