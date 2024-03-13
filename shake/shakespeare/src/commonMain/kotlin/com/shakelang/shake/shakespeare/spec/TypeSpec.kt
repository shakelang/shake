@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.spec

import com.shakelang.shake.shakespeare.AbstractSpec

interface TypeSpec : AbstractSpec {
    override fun generate(ctx: GenerationContext): String

    companion object {
        fun of(type: String): TypeSpec {
            return when (type) {
                "byte" -> PrimitiveTypeSpec.BYTE
                "shorts" -> PrimitiveTypeSpec.SHORT
                "int" -> PrimitiveTypeSpec.INT
                "long" -> PrimitiveTypeSpec.LONG
                "ubyte" -> PrimitiveTypeSpec.UNSIGNED_BYTE
                "ushorts" -> PrimitiveTypeSpec.UNSIGNED_SHORT
                "uint" -> PrimitiveTypeSpec.UNSIGNED_INT
                "ulong" -> PrimitiveTypeSpec.UNSIGNED_LONG
                "float" -> PrimitiveTypeSpec.FLOAT
                "doubles" -> PrimitiveTypeSpec.DOUBLE
                "char" -> PrimitiveTypeSpec.CHAR
                "boolean" -> PrimitiveTypeSpec.BOOLEAN
                else -> ObjectTypeSpec(NamespaceSpec(*type.split(".").toTypedArray()))
            }
        }
    }
}

open class ObjectTypeSpec(open val namespace: NamespaceSpec) : TypeSpec {

    constructor(vararg namespace: String) : this(NamespaceSpec(*namespace))

    override fun generate(ctx: GenerationContext): String {
        return namespace.generate(ctx)
    }

    override fun toString(): String {
        return namespace.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ObjectTypeSpec) return false
        if (namespace != other.namespace) return false
        return true
    }

    override fun hashCode(): Int {
        return namespace.hashCode()
    }
}

enum class PrimitiveTypeSpec(val type: String) : TypeSpec {
    BYTE("byte"),
    SHORT("shorts"),
    INT("int"),
    LONG("long"),
    UNSIGNED_BYTE("unsigned byte"),
    UNSIGNED_SHORT("unsigned shorts"),
    UNSIGNED_INT("unsigned int"),
    UNSIGNED_LONG("unsigned long"),
    FLOAT("float"),
    DOUBLE("doubles"),
    CHAR("char"),
    BOOLEAN("boolean"),
    ;

    override fun generate(ctx: GenerationContext): String {
        return type
    }
}
