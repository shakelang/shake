@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.spec

interface Type {
    fun generate(ctx: GenerationContext): String

    companion object {
        fun of(type: String): Type {
            return when (type) {
                "byte" -> PrimitiveType.BYTE
                "short" -> PrimitiveType.SHORT
                "int" -> PrimitiveType.INT
                "long" -> PrimitiveType.LONG
                "unsigned byte" -> PrimitiveType.UNSIGNED_BYTE
                "unsigned short" -> PrimitiveType.UNSIGNED_SHORT
                "unsigned int" -> PrimitiveType.UNSIGNED_INT
                "unsigned long" -> PrimitiveType.UNSIGNED_LONG
                "float" -> PrimitiveType.FLOAT
                "double" -> PrimitiveType.DOUBLE
                "char" -> PrimitiveType.CHAR
                "boolean" -> PrimitiveType.BOOLEAN
                else -> SimpleType(type)
            }
        }
    }
}

class SimpleType(val name: String) : Type {
    override fun generate(ctx: GenerationContext): String {
        return name
    }

    override fun toString(): String {
        return name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SimpleType) return false
        if (name != other.name) return false
        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}

enum class PrimitiveType(val type: String) : Type {
    BYTE("byte"),
    SHORT("short"),
    INT("int"),
    LONG("long"),
    UNSIGNED_BYTE("unsigned byte"),
    UNSIGNED_SHORT("unsigned short"),
    UNSIGNED_INT("unsigned int"),
    UNSIGNED_LONG("unsigned long"),
    FLOAT("float"),
    DOUBLE("double"),
    CHAR("char"),
    BOOLEAN("boolean"),
    ;

    override fun generate(ctx: GenerationContext): String {
        return type
    }
}

class ClassType(val name: String) : Type {
    override fun generate(ctx: GenerationContext): String {
        return name
    }
}
