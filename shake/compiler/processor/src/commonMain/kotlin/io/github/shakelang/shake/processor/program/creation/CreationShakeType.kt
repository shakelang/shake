package io.github.shakelang.shake.processor.program.creation

import io.github.shakelang.shake.processor.program.types.ShakeType

abstract class CreationShakeType (
    override val name: String,
): ShakeType {

    override fun assignType(other: ShakeType): CreationShakeType? = null
    override fun additionAssignType(other: ShakeType): CreationShakeType? = null
    override fun subtractionAssignType(other: ShakeType): CreationShakeType? = null
    override fun multiplicationAssignType(other: ShakeType): CreationShakeType? = null
    override fun divisionAssignType(other: ShakeType): CreationShakeType? = null
    override fun modulusAssignType(other: ShakeType): CreationShakeType? = null
    override fun powerAssignType(other: ShakeType): CreationShakeType? = null
    override fun incrementBeforeType(): CreationShakeType? = null
    override fun incrementAfterType(): CreationShakeType? = null
    override fun decrementBeforeType(): CreationShakeType? = null
    override fun decrementAfterType(): CreationShakeType? = null

    abstract override fun additionType(other: ShakeType): CreationShakeType?
    abstract override fun subtractionType(other: ShakeType): CreationShakeType?
    abstract override fun multiplicationType(other: ShakeType): CreationShakeType?
    abstract override fun divisionType(other: ShakeType): CreationShakeType?
    abstract override fun modulusType(other: ShakeType): CreationShakeType?
    abstract override fun powerType(other: ShakeType): CreationShakeType?
    override fun equalsType(other: ShakeType): CreationShakeType? = Primitives.BOOLEAN
    override fun notEqualsType(other: ShakeType): CreationShakeType? = Primitives.BOOLEAN
    abstract override fun greaterThanType(other: ShakeType): CreationShakeType?
    abstract override fun greaterThanOrEqualType(other: ShakeType): CreationShakeType?
    abstract override fun lessThanType(other: ShakeType): CreationShakeType?
    abstract override fun lessThanOrEqualType(other: ShakeType): CreationShakeType?
    abstract override fun andType(other: ShakeType): CreationShakeType?
    abstract override fun orType(other: ShakeType): CreationShakeType?
    abstract override fun notType(): CreationShakeType?
    override fun childType(name: String): CreationShakeType? = null
    override fun childFunctions(name: String): List<CreationShakeFunction>? = null
    override fun childInvokable(name: String): List<CreationShakeFunction>? = childFunctions(name)

    abstract override val kind: ShakeType.Kind

    abstract override fun castableTo(other: ShakeType): Boolean
    override fun compatibleTo(other: ShakeType): Boolean = compatibilityDistance(other) >= 0
    abstract override fun compatibilityDistance(other: ShakeType): Int

    abstract override fun toJson(): Map<String, Any?>

    abstract class Primitive (
        name: String,
        val type: ShakeType.PrimitiveType,
    ) : CreationShakeType(name) {
        override val kind: ShakeType.Kind
            get() = ShakeType.Kind.PRIMITIVE

        override fun castableTo(other: ShakeType): Boolean =
            other is Primitive &&
                    (other.type == ShakeType.PrimitiveType.BYTE
                            || other.type == ShakeType.PrimitiveType.SHORT
                            || other.type == ShakeType.PrimitiveType.INT
                            || other.type == ShakeType.PrimitiveType.LONG
                            || other.type == ShakeType.PrimitiveType.FLOAT
                            || other.type == ShakeType.PrimitiveType.DOUBLE
                            || other.type == ShakeType.PrimitiveType.UNSIGNED_BYTE
                            || other.type == ShakeType.PrimitiveType.UNSIGNED_SHORT
                            || other.type == ShakeType.PrimitiveType.UNSIGNED_INT
                            || other.type == ShakeType.PrimitiveType.UNSIGNED_LONG
                            || other.type == ShakeType.PrimitiveType.CHAR)

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
                override fun additionType(other: ShakeType): CreationShakeType? = null
                override fun subtractionType(other: ShakeType): CreationShakeType? = null
                override fun multiplicationType(other: ShakeType): CreationShakeType? = null
                override fun divisionType(other: ShakeType): CreationShakeType? = null
                override fun modulusType(other: ShakeType): CreationShakeType? = null
                override fun powerType(other: ShakeType): CreationShakeType? = null

                override fun greaterThanType(other: ShakeType): CreationShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun andType(other: ShakeType): CreationShakeType = bool()
                override fun orType(other: ShakeType): CreationShakeType = bool()
                override fun notType(): CreationShakeType = bool()

                override fun castableTo(other: ShakeType): Boolean =
                    other is Primitive && other.type == ShakeType.PrimitiveType.BOOLEAN
                override fun compatibilityDistance(other: ShakeType): Int =
                    if(other is Primitive && other.type == ShakeType.PrimitiveType.BOOLEAN) 0 else -1

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "boolean")
                }

                override val qualifiedName: String get() = "b"
            }

            val BYTE: Primitive = object : Primitive("byte", ShakeType.PrimitiveType.BYTE) {
                private fun gType(other: ShakeType): CreationShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            ShakeType.PrimitiveType.BYTE -> byte()
                            ShakeType.PrimitiveType.SHORT -> short()
                            ShakeType.PrimitiveType.INT -> int()
                            ShakeType.PrimitiveType.LONG -> long()
                            ShakeType.PrimitiveType.FLOAT -> float()
                            ShakeType.PrimitiveType.DOUBLE -> double()
                            ShakeType.PrimitiveType.UNSIGNED_BYTE -> short()
                            ShakeType.PrimitiveType.UNSIGNED_SHORT -> int()
                            ShakeType.PrimitiveType.UNSIGNED_INT,
                            ShakeType.PrimitiveType.UNSIGNED_LONG -> long()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): CreationShakeType? = gType(other)
                override fun divisionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun modulusType(other: ShakeType): CreationShakeType? = gType(other)
                override fun powerType(other: ShakeType): CreationShakeType = double()

                override fun greaterThanType(other: ShakeType): CreationShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun andType(other: ShakeType): CreationShakeType = bool()
                override fun orType(other: ShakeType): CreationShakeType = bool()
                override fun notType(): CreationShakeType = bool()

                override fun compatibilityDistance(other: ShakeType): Int =
                    if(other !is Primitive) -1 else when(other.type) {
                        ShakeType.PrimitiveType.BYTE -> 0
                        ShakeType.PrimitiveType.SHORT -> 1
                        ShakeType.PrimitiveType.INT -> 2
                        ShakeType.PrimitiveType.LONG -> 3
                        ShakeType.PrimitiveType.FLOAT -> 4
                        ShakeType.PrimitiveType.DOUBLE -> 4
                        else -> -1
                    }

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "byte")
                }

                override val qualifiedName: String get() = "B"
            }

            val SHORT: Primitive = object : Primitive("short", ShakeType.PrimitiveType.SHORT) {
                private fun gType(other: ShakeType): CreationShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            ShakeType.PrimitiveType.BYTE,
                            ShakeType.PrimitiveType.SHORT -> short()
                            ShakeType.PrimitiveType.INT -> int()
                            ShakeType.PrimitiveType.LONG -> long()
                            ShakeType.PrimitiveType.FLOAT -> float()
                            ShakeType.PrimitiveType.DOUBLE -> double()
                            ShakeType.PrimitiveType.UNSIGNED_BYTE -> short()
                            ShakeType.PrimitiveType.UNSIGNED_SHORT -> int()
                            ShakeType.PrimitiveType.UNSIGNED_INT,
                            ShakeType.PrimitiveType.UNSIGNED_LONG -> long()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): CreationShakeType? = gType(other)
                override fun divisionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun modulusType(other: ShakeType): CreationShakeType? = gType(other)
                override fun powerType(other: ShakeType): CreationShakeType = double()

                override fun greaterThanType(other: ShakeType): CreationShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun andType(other: ShakeType): CreationShakeType = bool()
                override fun orType(other: ShakeType): CreationShakeType = bool()
                override fun notType(): CreationShakeType = bool()

                override fun compatibilityDistance(other: ShakeType): Int =
                    if(other !is Primitive) -1 else when(other.type) {
                        ShakeType.PrimitiveType.SHORT -> 0
                        ShakeType.PrimitiveType.INT -> 1
                        ShakeType.PrimitiveType.LONG -> 2
                        ShakeType.PrimitiveType.FLOAT -> 3
                        ShakeType.PrimitiveType.DOUBLE -> 4
                        else -> -1
                    }

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "short")
                }

                override val qualifiedName: String get() = "S"
            }

            val INT: Primitive = object : Primitive("int", ShakeType.PrimitiveType.INT) {
                private fun gType(other: ShakeType): CreationShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            ShakeType.PrimitiveType.BYTE,
                            ShakeType.PrimitiveType.SHORT,
                            ShakeType.PrimitiveType.INT -> int()
                            ShakeType.PrimitiveType.LONG -> long()
                            ShakeType.PrimitiveType.FLOAT -> float()
                            ShakeType.PrimitiveType.DOUBLE -> double()
                            ShakeType.PrimitiveType.UNSIGNED_BYTE -> int()
                            ShakeType.PrimitiveType.UNSIGNED_SHORT -> int()
                            ShakeType.PrimitiveType.UNSIGNED_INT,
                            ShakeType.PrimitiveType.UNSIGNED_LONG -> long()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): CreationShakeType? = gType(other)
                override fun divisionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun modulusType(other: ShakeType): CreationShakeType? = gType(other)
                override fun powerType(other: ShakeType): CreationShakeType = double()

                override fun greaterThanType(other: ShakeType): CreationShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun andType(other: ShakeType): CreationShakeType = bool()
                override fun orType(other: ShakeType): CreationShakeType = bool()
                override fun notType(): CreationShakeType = bool()

                override fun compatibilityDistance(other: ShakeType): Int =
                    if(other !is Primitive) -1 else when(other.type) {
                        ShakeType.PrimitiveType.INT -> 0
                        ShakeType.PrimitiveType.LONG -> 1
                        ShakeType.PrimitiveType.FLOAT -> 2
                        ShakeType.PrimitiveType.DOUBLE -> 3
                        else -> -1
                    }

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "int")
                }

                override val qualifiedName: String get() = "I"
            }

            val LONG: Primitive = object : Primitive("long", ShakeType.PrimitiveType.LONG) {
                private fun gType(other: ShakeType): CreationShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            ShakeType.PrimitiveType.BYTE,
                            ShakeType.PrimitiveType.SHORT,
                            ShakeType.PrimitiveType.INT,
                            ShakeType.PrimitiveType.LONG -> long()
                            ShakeType.PrimitiveType.FLOAT -> float()
                            ShakeType.PrimitiveType.DOUBLE -> double()
                            ShakeType.PrimitiveType.UNSIGNED_BYTE,
                            ShakeType.PrimitiveType.UNSIGNED_SHORT,
                            ShakeType.PrimitiveType.UNSIGNED_INT,
                            ShakeType.PrimitiveType.UNSIGNED_LONG -> long()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): CreationShakeType? = gType(other)
                override fun divisionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun modulusType(other: ShakeType): CreationShakeType? = gType(other)
                override fun powerType(other: ShakeType): CreationShakeType = double()

                override fun greaterThanType(other: ShakeType): CreationShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun andType(other: ShakeType): CreationShakeType = bool()
                override fun orType(other: ShakeType): CreationShakeType = bool()
                override fun notType(): CreationShakeType = bool()

                override fun compatibilityDistance(other: ShakeType): Int =
                    if(other !is Primitive) -1 else when(other.type) {
                        ShakeType.PrimitiveType.LONG -> 0
                        ShakeType.PrimitiveType.FLOAT -> 1
                        ShakeType.PrimitiveType.DOUBLE -> 2
                        else -> -1
                    }

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "long")
                }

                override val qualifiedName: String get() = "L"
            }

            val FLOAT: Primitive = object : Primitive("float", ShakeType.PrimitiveType.FLOAT) {
                private fun gType(other: ShakeType): CreationShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            ShakeType.PrimitiveType.UNSIGNED_BYTE,
                            ShakeType.PrimitiveType.UNSIGNED_SHORT,
                            ShakeType.PrimitiveType.UNSIGNED_INT,
                            ShakeType.PrimitiveType.UNSIGNED_LONG,
                            ShakeType.PrimitiveType.BYTE,
                            ShakeType.PrimitiveType.SHORT,
                            ShakeType.PrimitiveType.INT,
                            ShakeType.PrimitiveType.LONG,
                            ShakeType.PrimitiveType.FLOAT -> float()
                            ShakeType.PrimitiveType.DOUBLE -> double()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): CreationShakeType? = gType(other)
                override fun divisionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun modulusType(other: ShakeType): CreationShakeType? = gType(other)
                override fun powerType(other: ShakeType): CreationShakeType = double()

                override fun greaterThanType(other: ShakeType): CreationShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun andType(other: ShakeType): CreationShakeType = bool()
                override fun orType(other: ShakeType): CreationShakeType = bool()
                override fun notType(): CreationShakeType = bool()

                override fun compatibilityDistance(other: ShakeType): Int =
                    if(other !is Primitive) -1 else when(other.type) {
                        ShakeType.PrimitiveType.FLOAT -> 0
                        ShakeType.PrimitiveType.DOUBLE -> 1
                        else -> -1
                    }

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "float")
                }

                override val qualifiedName: String get() = "F"
            }

            val DOUBLE: Primitive = object : Primitive("double", ShakeType.PrimitiveType.DOUBLE) {
                private fun gType(other: ShakeType): CreationShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            ShakeType.PrimitiveType.BYTE -> double()
                            ShakeType.PrimitiveType.SHORT -> double()
                            ShakeType.PrimitiveType.INT -> double()
                            ShakeType.PrimitiveType.LONG -> double()
                            ShakeType.PrimitiveType.FLOAT -> double()
                            ShakeType.PrimitiveType.DOUBLE -> double()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): CreationShakeType? = gType(other)
                override fun divisionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun modulusType(other: ShakeType): CreationShakeType? = gType(other)
                override fun powerType(other: ShakeType): CreationShakeType = double()

                override fun greaterThanType(other: ShakeType): CreationShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun andType(other: ShakeType): CreationShakeType = bool()
                override fun orType(other: ShakeType): CreationShakeType = bool()
                override fun notType(): CreationShakeType = bool()

                override fun compatibilityDistance(other: ShakeType): Int =
                    if(other !is Primitive) -1 else when(other.type) {
                        ShakeType.PrimitiveType.DOUBLE -> 0
                        else -> -1
                    }

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "double")
                }

                override val qualifiedName: String get() = "D"
            }

            val UNSIGNED_BYTE: Primitive = object : Primitive("unsigned_byte", ShakeType.PrimitiveType.UNSIGNED_BYTE) {
                private fun gType(other: ShakeType): CreationShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            ShakeType.PrimitiveType.BYTE -> short()
                            ShakeType.PrimitiveType.SHORT -> int()
                            ShakeType.PrimitiveType.INT -> long()
                            ShakeType.PrimitiveType.LONG -> long()
                            ShakeType.PrimitiveType.FLOAT -> float()
                            ShakeType.PrimitiveType.DOUBLE -> double()
                            ShakeType.PrimitiveType.UNSIGNED_BYTE -> unsignedByte()
                            ShakeType.PrimitiveType.UNSIGNED_SHORT -> unsignedShort()
                            ShakeType.PrimitiveType.UNSIGNED_INT -> unsignedInt()
                            ShakeType.PrimitiveType.UNSIGNED_LONG -> unsignedLong()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): CreationShakeType? = gType(other)
                override fun divisionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun modulusType(other: ShakeType): CreationShakeType? = gType(other)
                override fun powerType(other: ShakeType): CreationShakeType = double()

                override fun greaterThanType(other: ShakeType): CreationShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun andType(other: ShakeType): CreationShakeType = bool()
                override fun orType(other: ShakeType): CreationShakeType = bool()
                override fun notType(): CreationShakeType = bool()

                override fun compatibilityDistance(other: ShakeType): Int =
                    if(other !is Primitive) -1 else when(other.type) {
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

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "unsigned_byte")
                }

                override val qualifiedName: String get() = "UB"
            }

            val UNSIGNED_SHORT: Primitive = object : Primitive("unsigned_short", ShakeType.PrimitiveType.UNSIGNED_SHORT) {
                private fun gType(other: ShakeType): CreationShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            ShakeType.PrimitiveType.BYTE -> short()
                            ShakeType.PrimitiveType.SHORT -> int()
                            ShakeType.PrimitiveType.INT -> long()
                            ShakeType.PrimitiveType.LONG -> long()
                            ShakeType.PrimitiveType.FLOAT -> float()
                            ShakeType.PrimitiveType.DOUBLE -> double()
                            ShakeType.PrimitiveType.UNSIGNED_BYTE -> unsignedByte()
                            ShakeType.PrimitiveType.UNSIGNED_SHORT -> unsignedShort()
                            ShakeType.PrimitiveType.UNSIGNED_INT -> unsignedInt()
                            ShakeType.PrimitiveType.UNSIGNED_LONG -> unsignedLong()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): CreationShakeType? = gType(other)
                override fun divisionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun modulusType(other: ShakeType): CreationShakeType? = gType(other)
                override fun powerType(other: ShakeType): CreationShakeType = double()

                override fun greaterThanType(other: ShakeType): CreationShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun andType(other: ShakeType): CreationShakeType = bool()
                override fun orType(other: ShakeType): CreationShakeType = bool()
                override fun notType(): CreationShakeType = bool()

                override fun compatibilityDistance(other: ShakeType): Int =
                    if(other !is Primitive) -1 else when(other.type) {
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

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "unsigned_short")
                }

                override val qualifiedName: String get() = "US"
            }

            val UNSIGNED_INT: Primitive = object : Primitive("unsigned_int", ShakeType.PrimitiveType.UNSIGNED_INT) {
                private fun gType(other: ShakeType): CreationShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            ShakeType.PrimitiveType.BYTE -> int()
                            ShakeType.PrimitiveType.SHORT -> long()
                            ShakeType.PrimitiveType.INT -> long()
                            ShakeType.PrimitiveType.LONG -> long()
                            ShakeType.PrimitiveType.FLOAT -> float()
                            ShakeType.PrimitiveType.DOUBLE -> double()
                            ShakeType.PrimitiveType.UNSIGNED_BYTE -> unsignedByte()
                            ShakeType.PrimitiveType.UNSIGNED_SHORT -> unsignedShort()
                            ShakeType.PrimitiveType.UNSIGNED_INT -> unsignedInt()
                            ShakeType.PrimitiveType.UNSIGNED_LONG -> unsignedLong()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): CreationShakeType? = gType(other)
                override fun divisionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun modulusType(other: ShakeType): CreationShakeType? = gType(other)
                override fun powerType(other: ShakeType): CreationShakeType = double()

                override fun greaterThanType(other: ShakeType): CreationShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun andType(other: ShakeType): CreationShakeType = bool()
                override fun orType(other: ShakeType): CreationShakeType = bool()
                override fun notType(): CreationShakeType = bool()

                override fun compatibilityDistance(other: ShakeType): Int =
                    if(other !is Primitive) -1 else when(other.type) {
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

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "unsigned_int")
                }

                override val qualifiedName: String get() = "UI"
            }

            val UNSIGNED_LONG: Primitive = object : Primitive("unsigned_long", ShakeType.PrimitiveType.UNSIGNED_LONG) {
                private fun gType(other: ShakeType): CreationShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            ShakeType.PrimitiveType.BYTE -> long()
                            ShakeType.PrimitiveType.SHORT -> long()
                            ShakeType.PrimitiveType.INT -> long()
                            ShakeType.PrimitiveType.LONG -> long()
                            ShakeType.PrimitiveType.FLOAT -> float()
                            ShakeType.PrimitiveType.DOUBLE -> double()
                            ShakeType.PrimitiveType.UNSIGNED_BYTE -> unsignedByte()
                            ShakeType.PrimitiveType.UNSIGNED_SHORT -> unsignedShort()
                            ShakeType.PrimitiveType.UNSIGNED_INT -> unsignedInt()
                            ShakeType.PrimitiveType.UNSIGNED_LONG -> unsignedLong()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): CreationShakeType? = gType(other)
                override fun divisionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun modulusType(other: ShakeType): CreationShakeType? = gType(other)
                override fun powerType(other: ShakeType): CreationShakeType = double()

                override fun greaterThanType(other: ShakeType): CreationShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun andType(other: ShakeType): CreationShakeType = bool()
                override fun orType(other: ShakeType): CreationShakeType = bool()
                override fun notType(): CreationShakeType = bool()

                override fun compatibilityDistance(other: ShakeType): Int =
                    if(other !is Primitive) -1 else when(other.type) {
                        ShakeType.PrimitiveType.UNSIGNED_LONG -> 0
                        ShakeType.PrimitiveType.BYTE -> 1
                        ShakeType.PrimitiveType.SHORT -> 2
                        ShakeType.PrimitiveType.INT -> 3
                        ShakeType.PrimitiveType.LONG -> 4
                        ShakeType.PrimitiveType.FLOAT -> 5
                        ShakeType.PrimitiveType.DOUBLE -> 6
                        else -> -1
                    }

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "unsigned_long")
                }

                override val qualifiedName: String get() = "UL"
            }

            val CHAR = object : Primitive("char", ShakeType.PrimitiveType.CHAR) {
                private fun gType(other: ShakeType): CreationShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            ShakeType.PrimitiveType.BYTE,
                            ShakeType.PrimitiveType.SHORT,
                            ShakeType.PrimitiveType.INT -> int()
                            ShakeType.PrimitiveType.LONG -> long()
                            ShakeType.PrimitiveType.FLOAT -> float()
                            ShakeType.PrimitiveType.DOUBLE -> double()
                            ShakeType.PrimitiveType.UNSIGNED_BYTE,
                            ShakeType.PrimitiveType.UNSIGNED_SHORT,
                            ShakeType.PrimitiveType.UNSIGNED_INT -> unsignedInt()
                            ShakeType.PrimitiveType.UNSIGNED_LONG -> unsignedLong()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): CreationShakeType? = gType(other)
                override fun divisionType(other: ShakeType): CreationShakeType? = gType(other)
                override fun modulusType(other: ShakeType): CreationShakeType? = gType(other)
                override fun powerType(other: ShakeType): CreationShakeType = double()

                override fun greaterThanType(other: ShakeType): CreationShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun andType(other: ShakeType): CreationShakeType = bool()
                override fun orType(other: ShakeType): CreationShakeType = bool()
                override fun notType(): CreationShakeType = bool()

                override fun compatibilityDistance(other: ShakeType): Int =
                    if(other !is Primitive) -1 else when(other.type) {
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

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "char")
                }

                override val qualifiedName: String get() = "C"
            }

            val VOID = object : Primitive("void", ShakeType.PrimitiveType.VOID) {
                override fun additionType(other: ShakeType): CreationShakeType? = null
                override fun subtractionType(other: ShakeType): CreationShakeType? = null
                override fun multiplicationType(other: ShakeType): CreationShakeType? = null
                override fun divisionType(other: ShakeType): CreationShakeType? = null
                override fun modulusType(other: ShakeType): CreationShakeType? = null
                override fun powerType(other: ShakeType): CreationShakeType = double()

                override fun greaterThanType(other: ShakeType): CreationShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanType(other: ShakeType): CreationShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): CreationShakeType = bool()
                override fun andType(other: ShakeType): CreationShakeType = bool()
                override fun orType(other: ShakeType): CreationShakeType = bool()
                override fun notType(): CreationShakeType = bool()

                override fun compatibilityDistance(other: ShakeType): Int =
                    if(other !is Primitive) -1 else when(other.type) {
                        ShakeType.PrimitiveType.VOID -> 0
                        else -> -1
                    }

                override fun toJson(): Map<String, Any?> {
                    return mapOf("type" to "void")
                }

                override val qualifiedName: String get() = "V"
            }
        }
    }

    class Object (
        val clazz: CreationShakeClass,
        name: String = clazz.qualifiedName,
    ) : CreationShakeType(name) {
        override fun additionType(other: ShakeType): CreationShakeType? = null
        override fun subtractionType(other: ShakeType): CreationShakeType? = null
        override fun multiplicationType(other: ShakeType): CreationShakeType? = null
        override fun divisionType(other: ShakeType): CreationShakeType? = null
        override fun modulusType(other: ShakeType): CreationShakeType? = null
        override fun powerType(other: ShakeType): CreationShakeType? = null
        override fun equalsType(other: ShakeType): CreationShakeType? = null
        override fun notEqualsType(other: ShakeType): CreationShakeType? = null
        override fun greaterThanType(other: ShakeType): CreationShakeType? = null
        override fun greaterThanOrEqualType(other: ShakeType): CreationShakeType? = null
        override fun lessThanType(other: ShakeType): CreationShakeType? = null
        override fun lessThanOrEqualType(other: ShakeType): CreationShakeType? = null
        override fun andType(other: ShakeType): CreationShakeType? = null
        override fun orType(other: ShakeType): CreationShakeType? = null
        override fun notType(): CreationShakeType? = null

        override fun childType(name: String): CreationShakeType? = clazz.fields.find { it.name == name }?.type
        override fun childFunctions(name: String): List<CreationShakeFunction> {
            return clazz.methods.filter { it.name == name }
        }

        override val kind: ShakeType.Kind
            get() = ShakeType.Kind.OBJECT

        override fun castableTo(other: ShakeType): Boolean {
            return other is Object && other.clazz.compatibleTo(clazz)
        }

        override fun compatibleTo(other: ShakeType): Boolean {
            return other is Object && clazz.compatibleTo(other.clazz)
        }

        override fun compatibilityDistance(other: ShakeType): Int {
            return if(other is Object) clazz.compatibilityDistance(other.clazz) else -1
        }

        override fun toJson(): Map<String, Any?> {
            return mapOf("type" to "object", "class" to clazz.qualifiedName)
        }

        override val qualifiedName: String
            get() = "L${clazz.qualifiedName};"
    }

    class Array (
        name: String,
        val elementType: CreationShakeType
    ) : CreationShakeType(name) {
        override fun additionType(other: ShakeType): CreationShakeType? = null
        override fun subtractionType(other: ShakeType): CreationShakeType? = null
        override fun multiplicationType(other: ShakeType): CreationShakeType? = null
        override fun divisionType(other: ShakeType): CreationShakeType? = null
        override fun modulusType(other: ShakeType): CreationShakeType? = null
        override fun powerType(other: ShakeType): CreationShakeType? = null
        override fun equalsType(other: ShakeType): CreationShakeType? = null
        override fun notEqualsType(other: ShakeType): CreationShakeType? = null
        override fun greaterThanType(other: ShakeType): CreationShakeType? = null
        override fun greaterThanOrEqualType(other: ShakeType): CreationShakeType? = null
        override fun lessThanType(other: ShakeType): CreationShakeType? = null
        override fun lessThanOrEqualType(other: ShakeType): CreationShakeType? = null
        override fun andType(other: ShakeType): CreationShakeType? = null
        override fun orType(other: ShakeType): CreationShakeType? = null
        override fun notType(): CreationShakeType? = null

        override val kind: ShakeType.Kind
            get() = ShakeType.Kind.ARRAY

        override fun castableTo(other: ShakeType): Boolean {
            return other is Array && other.elementType.castableTo(elementType)
        }

        override fun compatibleTo(other: ShakeType): Boolean {
            return other is Array && elementType.compatibleTo(other.elementType)
        }

        override fun compatibilityDistance(other: ShakeType): Int {
            return if(other is Array) elementType.compatibilityDistance(other.elementType) else -1
        }

        override fun toJson(): Map<String, Any?> {
            return mapOf("type" to "array", "elementType" to elementType.toJson())
        }

        override val qualifiedName: String
            get() = "[${elementType.qualifiedName}"
    }

    class Lambda (
        name: String,
        val parameters: List<CreationShakeParameter>,
        val returnType: CreationShakeType
    ) : CreationShakeType(name) {
        override fun additionType(other: ShakeType): CreationShakeType? = null
        override fun subtractionType(other: ShakeType): CreationShakeType? = null
        override fun multiplicationType(other: ShakeType): CreationShakeType? = null
        override fun divisionType(other: ShakeType): CreationShakeType? = null
        override fun modulusType(other: ShakeType): CreationShakeType? = null
        override fun powerType(other: ShakeType): CreationShakeType? = null
        override fun equalsType(other: ShakeType): CreationShakeType? = null
        override fun notEqualsType(other: ShakeType): CreationShakeType? = null
        override fun greaterThanType(other: ShakeType): CreationShakeType? = null
        override fun greaterThanOrEqualType(other: ShakeType): CreationShakeType? = null
        override fun lessThanType(other: ShakeType): CreationShakeType? = null
        override fun lessThanOrEqualType(other: ShakeType): CreationShakeType? = null
        override fun andType(other: ShakeType): CreationShakeType? = null
        override fun orType(other: ShakeType): CreationShakeType? = null
        override fun notType(): CreationShakeType? = null

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
            return if(other is Lambda) returnType.compatibilityDistance(other.returnType) else -1
        }

        override fun toJson(): Map<String, Any?> {
            return mapOf("type" to "lambda", "parameters" to parameters.map { it.toJson() }, "returnType" to returnType.toJson())
        }

        override val qualifiedName: String
            get() = "(${parameters.joinToString(", ") { it.type.qualifiedName }} -> ${returnType.qualifiedName})"
    }

    object Primitives {
        val BOOLEAN = Primitive.BYTE
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
    }

    companion object {
        fun array(elementType: CreationShakeType): CreationShakeType {
            return Array("${elementType.name}[]", elementType)
        }
        fun objectType(clazz: CreationShakeClass): CreationShakeType {
            return Object(clazz, clazz.name)
        }
    }
}