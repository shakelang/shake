package com.shakelang.shake.processor.program.creation

import com.shakelang.shake.processor.program.types.ShakeType
import com.shakelang.shake.processor.program.types.code.ShakeScope

abstract class CreationShakeType(
    override val name: String,
) : ShakeType {

    abstract override val kind: ShakeType.Kind

    abstract override fun castableTo(other: ShakeType): Boolean
    override fun compatibleTo(other: ShakeType): Boolean = compatibilityDistance(other) >= 0
    abstract override fun compatibilityDistance(other: ShakeType): Int

    abstract override fun toJson(): Map<String, Any?>

    override fun equals(other: Any?): Boolean {
        if (other !is CreationShakeType) return false
        return qualifiedName == other.qualifiedName
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + kind.hashCode() + qualifiedName.hashCode()
        return result
    }

    override fun toString(): String {
        return name
    }

    abstract class Primitive(
        name: String,
        override val type: ShakeType.PrimitiveType,
    ) : CreationShakeType(name), ShakeType.Primitive {
        override val kind: ShakeType.Kind
            get() = ShakeType.Kind.PRIMITIVE

        override fun castableTo(other: ShakeType): Boolean =
            other is Primitive &&
                (
                    other.type == ShakeType.PrimitiveType.BYTE ||
                        other.type == ShakeType.PrimitiveType.SHORT ||
                        other.type == ShakeType.PrimitiveType.INT ||
                        other.type == ShakeType.PrimitiveType.LONG ||
                        other.type == ShakeType.PrimitiveType.FLOAT ||
                        other.type == ShakeType.PrimitiveType.DOUBLE ||
                        other.type == ShakeType.PrimitiveType.UNSIGNED_BYTE ||
                        other.type == ShakeType.PrimitiveType.UNSIGNED_SHORT ||
                        other.type == ShakeType.PrimitiveType.UNSIGNED_INT ||
                        other.type == ShakeType.PrimitiveType.UNSIGNED_LONG ||
                        other.type == ShakeType.PrimitiveType.CHAR
                    )

        companion object {

            private fun bool(): Primitive {
                return BOOLEAN
            }

            private fun byte(): Primitive {
                return BYTE
            }

            private fun short(): Primitive {
                return SHORT
            }

            private fun int(): Primitive {
                return INT
            }

            private fun long(): Primitive {
                return LONG
            }

            private fun float(): Primitive {
                return FLOAT
            }

            private fun double(): Primitive {
                return DOUBLE
            }

            private fun unsignedByte(): Primitive {
                return UNSIGNED_BYTE
            }

            private fun unsignedShort(): Primitive {
                return UNSIGNED_SHORT
            }

            private fun unsignedInt(): Primitive {
                return UNSIGNED_INT
            }

            private fun unsignedLong(): Primitive {
                return UNSIGNED_LONG
            }

            val BOOLEAN: Primitive = object : Primitive("boolean", ShakeType.PrimitiveType.BOOLEAN) {
                override fun additionType(other: ShakeType, scope: ShakeScope): CreationShakeType? = null
                override fun subtractionType(other: ShakeType, scope: ShakeScope): CreationShakeType? = null
                override fun multiplicationType(other: ShakeType, scope: ShakeScope): CreationShakeType? = null
                override fun divisionType(other: ShakeType, scope: ShakeScope): CreationShakeType? = null
                override fun modulusType(other: ShakeType, scope: ShakeScope): CreationShakeType? = null
                override fun powerType(other: ShakeType, scope: ShakeScope): CreationShakeType? = null

                override fun greaterThanType(other: ShakeType, scope: ShakeScope): CreationShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType, scope: ShakeScope): CreationShakeType = bool()
                override fun lessThanType(other: ShakeType, scope: ShakeScope): CreationShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType, scope: ShakeScope): CreationShakeType = bool()
                override fun logicalAndType(other: ShakeType, scope: ShakeScope): CreationShakeType = bool()
                override fun logicalOrType(other: ShakeType, scope: ShakeScope): CreationShakeType = bool()
                override fun logicalNotType(scope: ShakeScope): CreationShakeType = bool()

                override fun castableTo(other: ShakeType): Boolean =
                    other is Primitive && other.type == ShakeType.PrimitiveType.BOOLEAN

                override fun compatibilityDistance(other: ShakeType): Int =
                    if (other is Primitive && other.type == ShakeType.PrimitiveType.BOOLEAN) 0 else -1

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "boolean")
                }

                override val qualifiedName: String get() = "Z"
            }

            val NULL: Primitive = object : Primitive("null", ShakeType.PrimitiveType.NULL) {
                override fun compatibilityDistance(other: ShakeType): Int {
                    TODO("Not yet implemented")
                }

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "null")
                }

                override val qualifiedName: String get() = "N"
            }

            val BYTE: Primitive = object : Primitive("byte", ShakeType.PrimitiveType.BYTE) {
                override fun compatibilityDistance(other: ShakeType): Int =
                    if (other !is Primitive) {
                        -1
                    } else {
                        when (other.type) {
                            ShakeType.PrimitiveType.BYTE -> 0
                            ShakeType.PrimitiveType.SHORT -> 1
                            ShakeType.PrimitiveType.INT -> 2
                            ShakeType.PrimitiveType.LONG -> 3
                            ShakeType.PrimitiveType.FLOAT -> 4
                            ShakeType.PrimitiveType.DOUBLE -> 4
                            else -> -1
                        }
                    }

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "byte")
                }

                override val qualifiedName: String get() = "B"
            }

            val SHORT: Primitive = object : Primitive("short", ShakeType.PrimitiveType.SHORT) {
                override fun compatibilityDistance(other: ShakeType): Int =
                    if (other !is Primitive) {
                        -1
                    } else {
                        when (other.type) {
                            ShakeType.PrimitiveType.SHORT -> 0
                            ShakeType.PrimitiveType.INT -> 1
                            ShakeType.PrimitiveType.LONG -> 2
                            ShakeType.PrimitiveType.FLOAT -> 3
                            ShakeType.PrimitiveType.DOUBLE -> 4
                            else -> -1
                        }
                    }

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "short")
                }

                override val qualifiedName: String get() = "S"
            }

            val INT: Primitive = object : Primitive("int", ShakeType.PrimitiveType.INT) {
                override fun compatibilityDistance(other: ShakeType): Int =
                    if (other !is Primitive) {
                        -1
                    } else {
                        when (other.type) {
                            ShakeType.PrimitiveType.INT -> 0
                            ShakeType.PrimitiveType.LONG -> 1
                            ShakeType.PrimitiveType.FLOAT -> 2
                            ShakeType.PrimitiveType.DOUBLE -> 3
                            else -> -1
                        }
                    }

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "int")
                }

                override val qualifiedName: String get() = "I"
            }

            val LONG: Primitive = object : Primitive("long", ShakeType.PrimitiveType.LONG) {
                override fun compatibilityDistance(other: ShakeType): Int =
                    if (other !is Primitive) {
                        -1
                    } else {
                        when (other.type) {
                            ShakeType.PrimitiveType.LONG -> 0
                            ShakeType.PrimitiveType.FLOAT -> 1
                            ShakeType.PrimitiveType.DOUBLE -> 2
                            else -> -1
                        }
                    }

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "long")
                }

                override val qualifiedName: String get() = "J"
            }

            val FLOAT: Primitive = object : Primitive("float", ShakeType.PrimitiveType.FLOAT) {
                override fun compatibilityDistance(other: ShakeType): Int =
                    if (other !is Primitive) {
                        -1
                    } else {
                        when (other.type) {
                            ShakeType.PrimitiveType.FLOAT -> 0
                            ShakeType.PrimitiveType.DOUBLE -> 1
                            else -> -1
                        }
                    }

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "float")
                }

                override val qualifiedName: String get() = "F"
            }

            val DOUBLE: Primitive = object : Primitive("double", ShakeType.PrimitiveType.DOUBLE) {
                override fun compatibilityDistance(other: ShakeType): Int =
                    if (other !is Primitive) {
                        -1
                    } else {
                        when (other.type) {
                            ShakeType.PrimitiveType.DOUBLE -> 0
                            else -> -1
                        }
                    }

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "double")
                }

                override val qualifiedName: String get() = "D"
            }

            val UNSIGNED_BYTE: Primitive = object : Primitive("unsigned_byte", ShakeType.PrimitiveType.UNSIGNED_BYTE) {
                override fun compatibilityDistance(other: ShakeType): Int =
                    if (other !is Primitive) {
                        -1
                    } else {
                        when (other.type) {
                            ShakeType.PrimitiveType.UNSIGNED_BYTE -> 0
                            ShakeType.PrimitiveType.UNSIGNED_SHORT -> 1
                            ShakeType.PrimitiveType.UNSIGNED_INT -> 2
                            ShakeType.PrimitiveType.UNSIGNED_LONG -> 3
                            ShakeType.PrimitiveType.SHORT -> 4
                            ShakeType.PrimitiveType.INT -> 5
                            ShakeType.PrimitiveType.LONG -> 6
                            ShakeType.PrimitiveType.FLOAT -> 7
                            ShakeType.PrimitiveType.DOUBLE -> 8
                            else -> -1
                        }
                    }

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "unsigned_byte")
                }

                override val qualifiedName: String get() = "b"
            }

            val UNSIGNED_SHORT: Primitive =
                object : Primitive("unsigned_short", ShakeType.PrimitiveType.UNSIGNED_SHORT) {
                    override fun compatibilityDistance(other: ShakeType): Int =
                        if (other !is Primitive) {
                            -1
                        } else {
                            when (other.type) {
                                ShakeType.PrimitiveType.UNSIGNED_SHORT -> 0
                                ShakeType.PrimitiveType.UNSIGNED_INT -> 1
                                ShakeType.PrimitiveType.UNSIGNED_LONG -> 2
                                ShakeType.PrimitiveType.BYTE -> 3
                                ShakeType.PrimitiveType.SHORT -> 4
                                ShakeType.PrimitiveType.INT -> 5
                                ShakeType.PrimitiveType.LONG -> 6
                                ShakeType.PrimitiveType.FLOAT -> 7
                                ShakeType.PrimitiveType.DOUBLE -> 8
                                else -> -1
                            }
                        }

                    override fun toJson(): Map<String, Any?> {
                        return mapOf("type" to "unsigned_short")
                    }

                    override val qualifiedName: String get() = "s"
                }

            val UNSIGNED_INT: Primitive = object : Primitive("unsigned_int", ShakeType.PrimitiveType.UNSIGNED_INT) {
                override fun compatibilityDistance(other: ShakeType): Int =
                    if (other !is Primitive) {
                        -1
                    } else {
                        when (other.type) {
                            ShakeType.PrimitiveType.UNSIGNED_INT -> 0
                            ShakeType.PrimitiveType.UNSIGNED_LONG -> 1
                            ShakeType.PrimitiveType.BYTE -> 2
                            ShakeType.PrimitiveType.SHORT -> 3
                            ShakeType.PrimitiveType.INT -> 4
                            ShakeType.PrimitiveType.LONG -> 5
                            ShakeType.PrimitiveType.FLOAT -> 6
                            ShakeType.PrimitiveType.DOUBLE -> 7
                            else -> -1
                        }
                    }

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "unsigned_int")
                }

                override val qualifiedName: String get() = "i"
            }

            val UNSIGNED_LONG: Primitive = object : Primitive("unsigned_long", ShakeType.PrimitiveType.UNSIGNED_LONG) {
                override fun compatibilityDistance(other: ShakeType): Int =
                    if (other !is Primitive) {
                        -1
                    } else {
                        when (other.type) {
                            ShakeType.PrimitiveType.UNSIGNED_LONG -> 0
                            ShakeType.PrimitiveType.BYTE -> 1
                            ShakeType.PrimitiveType.SHORT -> 2
                            ShakeType.PrimitiveType.INT -> 3
                            ShakeType.PrimitiveType.LONG -> 4
                            ShakeType.PrimitiveType.FLOAT -> 5
                            ShakeType.PrimitiveType.DOUBLE -> 6
                            else -> -1
                        }
                    }

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "unsigned_long")
                }

                override val qualifiedName: String get() = "j"
            }

            val CHAR = object : Primitive("char", ShakeType.PrimitiveType.CHAR) {
                override fun compatibilityDistance(other: ShakeType): Int =
                    if (other !is Primitive) {
                        -1
                    } else {
                        when (other.type) {
                            ShakeType.PrimitiveType.CHAR -> 0
                            ShakeType.PrimitiveType.SHORT -> 1
                            ShakeType.PrimitiveType.UNSIGNED_SHORT -> 1
                            ShakeType.PrimitiveType.INT -> 2
                            ShakeType.PrimitiveType.UNSIGNED_INT -> 2
                            ShakeType.PrimitiveType.LONG -> 3
                            ShakeType.PrimitiveType.UNSIGNED_LONG -> 3
                            ShakeType.PrimitiveType.FLOAT -> 5
                            ShakeType.PrimitiveType.DOUBLE -> 6
                            else -> -1
                        }
                    }

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "char")
                }

                override val qualifiedName: String get() = "C"
            }

            val VOID = object : Primitive("void", ShakeType.PrimitiveType.VOID) {
                override fun compatibilityDistance(other: ShakeType): Int =
                    if (other !is Primitive) {
                        -1
                    } else {
                        when (other.type) {
                            ShakeType.PrimitiveType.VOID -> 0
                            else -> -1
                        }
                    }

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "void")
                }

                override val qualifiedName: String get() = "V"
            }

            val DYNAMIC = object : Primitive("dynamic", ShakeType.PrimitiveType.DYNAMIC) {
                override fun compatibilityDistance(other: ShakeType): Int =
                    if (other !is Primitive) {
                        -1
                    } else {
                        when (other.type) {
                            ShakeType.PrimitiveType.DYNAMIC -> 0
                            else -> -1
                        }
                    }

                override fun compatibleTo(other: ShakeType): Boolean {
                    return other == this
                }

                override fun castableTo(other: ShakeType): Boolean {
                    return true
                }

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "dynamic")
                }

                override val qualifiedName: String get() = "?"
            }
        }
    }

    class Object(
        override val clazz: CreationShakeClass,
    ) : CreationShakeType(clazz.qualifiedName), ShakeType.Object {

        override val kind: ShakeType.Kind
            get() = ShakeType.Kind.OBJECT

        override fun castableTo(other: ShakeType): Boolean {
            return other is Object && other.clazz.compatibleTo(clazz)
        }

        override fun compatibleTo(other: ShakeType): Boolean {
            return other is Object && clazz.compatibleTo(other.clazz)
        }

        override fun compatibilityDistance(other: ShakeType): Int {
            return if (other is Object) clazz.compatibilityDistance(other.clazz) else -1
        }

        override fun toJson(): Map<String, Any?> {
            return mapOf("type" to "object", "class" to clazz.qualifiedName)
        }
    }

    class Array(
        name: String,
        val elementType: CreationShakeType,
    ) : CreationShakeType(name) {

        override val kind: ShakeType.Kind
            get() = ShakeType.Kind.ARRAY

        override fun castableTo(other: ShakeType): Boolean {
            return other is Array && other.elementType.castableTo(elementType)
        }

        override fun compatibleTo(other: ShakeType): Boolean {
            return other is Array && elementType.compatibleTo(other.elementType)
        }

        override fun compatibilityDistance(other: ShakeType): Int {
            return if (other is Array) elementType.compatibilityDistance(other.elementType) else -1
        }

        override fun toJson(): Map<String, Any?> {
            return mapOf("type" to "array", "elementType" to elementType.toJson())
        }

        override val qualifiedName: String
            get() = "[${elementType.qualifiedName}"
    }

    class Lambda(
        name: String,
        val parameters: List<CreationShakeParameter>,
        val returnType: CreationShakeType,
    ) : CreationShakeType(name) {

        override val kind: ShakeType.Kind
            get() = ShakeType.Kind.LAMBDA

        override fun castableTo(other: ShakeType): Boolean {
            // TODO: Check parameters
            return other is Lambda && other.parameters.size == parameters.size && other.returnType.castableTo(returnType)
        }

        override fun compatibleTo(other: ShakeType): Boolean {
            // TODO: Check parameters
            return other is Lambda && returnType.compatibleTo(other.returnType)
        }

        override fun compatibilityDistance(other: ShakeType): Int {
            // TODO: Check parameters
            return if (other is Lambda) returnType.compatibilityDistance(other.returnType) else -1
        }

        override fun toJson(): Map<String, Any?> {
            return mapOf(
                "type" to "lambda",
                "parameters" to parameters.map { it.toJson() },
                "returnType" to returnType.toJson(),
            )
        }

        override val qualifiedName: String
            get() = "(${parameters.joinToString(", ") { it.type.qualifiedName }} -> ${returnType.qualifiedName})"
    }

    object Primitives {
        val BOOLEAN = Primitive.BOOLEAN
        val NULL = Primitive.NULL
        val BYTE = Primitive.BYTE
        val SHORT = Primitive.SHORT
        val INT = Primitive.INT
        val LONG = Primitive.LONG
        val FLOAT = Primitive.FLOAT
        val DOUBLE = Primitive.DOUBLE
        val UNSIGNED_BYTE = Primitive.UNSIGNED_BYTE
        val UNSIGNED_SHORT = Primitive.UNSIGNED_SHORT
        val UNSIGNED_INT = Primitive.UNSIGNED_INT
        val UNSIGNED_LONG = Primitive.UNSIGNED_LONG
        val UBYTE = UNSIGNED_BYTE
        val USHORT = UNSIGNED_SHORT
        val UINT = UNSIGNED_INT
        val ULONG = UNSIGNED_LONG
        val CHAR = Primitive.CHAR
        val VOID = Primitive.VOID
        val DYNAMIC = Primitive.DYNAMIC
        val UNKNOWN = Primitive.DYNAMIC // TODO: Change this
    }

    companion object {
        fun array(elementType: CreationShakeType): CreationShakeType {
            return Array("${elementType.name}[]", elementType)
        }

        fun objectType(clazz: CreationShakeClass): CreationShakeType {
            return Object(clazz)
        }
    }
}

fun ShakeType?.isCreation() = this is CreationShakeType
fun ShakeType?.asCreation() = this as CreationShakeType
