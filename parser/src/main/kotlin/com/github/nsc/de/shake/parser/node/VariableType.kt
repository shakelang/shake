package com.github.nsc.de.shake.parser.node

import com.github.nsc.de.shake.util.json.JSON
import java.util.*

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
            return name.lowercase(Locale.getDefault())
        }
    }

    companion object {
        @JvmField
        var DYNAMIC = VariableType(Type.DYNAMIC)
        @JvmField
        var BYTE = VariableType(Type.BYTE)
        @JvmField
        var SHORT = VariableType(Type.SHORT)
        @JvmField
        var INTEGER = VariableType(Type.INTEGER)
        @JvmField
        var LONG = VariableType(Type.LONG)
        @JvmField
        var FLOAT = VariableType(Type.FLOAT)
        @JvmField
        var DOUBLE = VariableType(Type.DOUBLE)
        @JvmField
        var BOOLEAN = VariableType(Type.BOOLEAN)
        @JvmField
        var CHAR = VariableType(Type.CHAR)
        @JvmField
        var ARRAY = VariableType(Type.ARRAY)
        @JvmField
        var OBJECT = VariableType(Type.OBJECT)
        @JvmField
        var VOID = VariableType(Type.VOID)
    }
}