@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.spec

interface TypeSpec : AbstractSpec {
    override fun generate(ctx: GenerationContext): String

    companion object {

        fun byte() = PrimitiveTypeSpec.BYTE
        fun shorts() = PrimitiveTypeSpec.SHORT
        fun int() = PrimitiveTypeSpec.INT
        fun long() = PrimitiveTypeSpec.LONG
        fun unsignedByte() = PrimitiveTypeSpec.UNSIGNED_BYTE
        fun unsignedShort() = PrimitiveTypeSpec.UNSIGNED_SHORT
        fun unsignedInt() = PrimitiveTypeSpec.UNSIGNED_INT
        fun unsignedLong() = PrimitiveTypeSpec.UNSIGNED_LONG
        fun ubyte() = PrimitiveTypeSpec.UNSIGNED_BYTE
        fun ushort() = PrimitiveTypeSpec.UNSIGNED_SHORT
        fun uint() = PrimitiveTypeSpec.UNSIGNED_INT
        fun ulong() = PrimitiveTypeSpec.UNSIGNED_LONG
        fun float() = PrimitiveTypeSpec.FLOAT
        fun double() = PrimitiveTypeSpec.DOUBLE
        fun char() = PrimitiveTypeSpec.CHAR
        fun boolean() = PrimitiveTypeSpec.BOOLEAN
        fun string() = ObjectTypeSpec("String")

        fun of(type: String): TypeSpec {
            return when (type) {
                "byte" -> PrimitiveTypeSpec.BYTE
                "shorts" -> PrimitiveTypeSpec.SHORT
                "int" -> PrimitiveTypeSpec.INT
                "long" -> PrimitiveTypeSpec.LONG
                "ubyte" -> PrimitiveTypeSpec.UNSIGNED_BYTE
                "ushort" -> PrimitiveTypeSpec.UNSIGNED_SHORT
                "uint" -> PrimitiveTypeSpec.UNSIGNED_INT
                "ulong" -> PrimitiveTypeSpec.UNSIGNED_LONG
                "float" -> PrimitiveTypeSpec.FLOAT
                "double" -> PrimitiveTypeSpec.DOUBLE
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
    SHORT("short"),
    INT("int"),
    LONG("long"),
    UNSIGNED_BYTE("ubyte"),
    UNSIGNED_SHORT("ushort"),
    UNSIGNED_INT("uint"),
    UNSIGNED_LONG("ulong"),
    FLOAT("float"),
    DOUBLE("double"),
    CHAR("char"),
    BOOLEAN("boolean"),
    ;

    override fun generate(ctx: GenerationContext): String {
        return type
    }
}
