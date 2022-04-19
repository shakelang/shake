package io.github.shakelang.shake.processor.program

import io.github.shakelang.shake.processor.program.code.ShakeInvokable

abstract class ShakeType (
    val name: String,
) {

    open fun assignType(other: ShakeType): ShakeType? = null
    open fun additionAssignType(other: ShakeType): ShakeType? = null
    open fun subtractionAssignType(other: ShakeType): ShakeType? = null
    open fun multiplicationAssignType(other: ShakeType): ShakeType? = null
    open fun divisionAssignType(other: ShakeType): ShakeType? = null
    open fun modulusAssignType(other: ShakeType): ShakeType? = null
    open fun powerAssignType(other: ShakeType): ShakeType? = null
    open fun incrementBeforeType(): ShakeType? = null
    open fun incrementAfterType(): ShakeType? = null
    open fun decrementBeforeType(): ShakeType? = null
    open fun decrementAfterType(): ShakeType? = null

    abstract fun additionType(other: ShakeType): ShakeType?
    abstract fun subtractionType(other: ShakeType): ShakeType?
    abstract fun multiplicationType(other: ShakeType): ShakeType?
    abstract fun divisionType(other: ShakeType): ShakeType?
    abstract fun modulusType(other: ShakeType): ShakeType?
    abstract fun powerType(other: ShakeType): ShakeType?
    open fun equalsType(other: ShakeType): ShakeType? = Primitives.BOOLEAN
    open fun notEqualsType(other: ShakeType): ShakeType? = Primitives.BOOLEAN
    abstract fun greaterThanType(other: ShakeType): ShakeType?
    abstract fun greaterThanOrEqualType(other: ShakeType): ShakeType?
    abstract fun lessThanType(other: ShakeType): ShakeType?
    abstract fun lessThanOrEqualType(other: ShakeType): ShakeType?
    abstract fun andType(other: ShakeType): ShakeType?
    abstract fun orType(other: ShakeType): ShakeType?
    abstract fun notType(): ShakeType?
    open fun childType(name: String): ShakeType? = null
    open fun childFunctions(name: String): List<ShakeFunction>? = null
    open fun childInvokable(name: String): List<ShakeFunction>? = childFunctions(name)

    abstract val kind: Kind

    abstract fun compatibleTo(other: ShakeType): Boolean

    enum class Kind {
        PRIMITIVE,
        OBJECT,
        ARRAY,
        LAMBDA,
    }
    enum class PrimitiveType {
        BOOLEAN,
        BYTE,
        CHAR,
        SHORT,
        INT,
        LONG,
        FLOAT,
        DOUBLE,
        UNSIGNED_BYTE,
        UNSIGNED_SHORT,
        UNSIGNED_INT,
        UNSIGNED_LONG,
        VOID,
    }
    abstract class Primitive (
        name: String,
        val type: PrimitiveType,
    ) : ShakeType(name) {
        override val kind: Kind
            get() = Kind.PRIMITIVE

        override fun compatibleTo(other: ShakeType): Boolean {
            return when (other) {
                is Primitive -> type == other.type
                else -> false
            }
        }

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

            val BOOLEAN: Primitive = object : Primitive("boolean", PrimitiveType.BOOLEAN) {
                override fun additionType(other: ShakeType): ShakeType? = null
                override fun subtractionType(other: ShakeType): ShakeType? = null
                override fun multiplicationType(other: ShakeType): ShakeType? = null
                override fun divisionType(other: ShakeType): ShakeType? = null
                override fun modulusType(other: ShakeType): ShakeType? = null
                override fun powerType(other: ShakeType): ShakeType? = null

                override fun greaterThanType(other: ShakeType): ShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun lessThanType(other: ShakeType): ShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun andType(other: ShakeType): ShakeType = bool()
                override fun orType(other: ShakeType): ShakeType = bool()
                override fun notType(): ShakeType = bool()
            }

            val BYTE: Primitive = object : Primitive("byte", PrimitiveType.BYTE) {
                private fun gType(other: ShakeType): ShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            PrimitiveType.BYTE -> byte()
                            PrimitiveType.SHORT -> short()
                            PrimitiveType.INT -> int()
                            PrimitiveType.LONG -> long()
                            PrimitiveType.FLOAT -> float()
                            PrimitiveType.DOUBLE -> double()
                            PrimitiveType.UNSIGNED_BYTE -> short()
                            PrimitiveType.UNSIGNED_SHORT -> int()
                            PrimitiveType.UNSIGNED_INT,
                            PrimitiveType.UNSIGNED_LONG -> long()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): ShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): ShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): ShakeType? = gType(other)
                override fun divisionType(other: ShakeType): ShakeType? = gType(other)
                override fun modulusType(other: ShakeType): ShakeType? = gType(other)
                override fun powerType(other: ShakeType): ShakeType = double()

                override fun greaterThanType(other: ShakeType): ShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun lessThanType(other: ShakeType): ShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun andType(other: ShakeType): ShakeType = bool()
                override fun orType(other: ShakeType): ShakeType = bool()
                override fun notType(): ShakeType = bool()
            }

            val SHORT: Primitive = object : Primitive("short", PrimitiveType.SHORT) {
                private fun gType(other: ShakeType): ShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            PrimitiveType.BYTE,
                            PrimitiveType.SHORT -> short()
                            PrimitiveType.INT -> int()
                            PrimitiveType.LONG -> long()
                            PrimitiveType.FLOAT -> float()
                            PrimitiveType.DOUBLE -> double()
                            PrimitiveType.UNSIGNED_BYTE -> short()
                            PrimitiveType.UNSIGNED_SHORT -> int()
                            PrimitiveType.UNSIGNED_INT,
                            PrimitiveType.UNSIGNED_LONG -> long()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): ShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): ShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): ShakeType? = gType(other)
                override fun divisionType(other: ShakeType): ShakeType? = gType(other)
                override fun modulusType(other: ShakeType): ShakeType? = gType(other)
                override fun powerType(other: ShakeType): ShakeType = double()

                override fun greaterThanType(other: ShakeType): ShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun lessThanType(other: ShakeType): ShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun andType(other: ShakeType): ShakeType = bool()
                override fun orType(other: ShakeType): ShakeType = bool()
                override fun notType(): ShakeType = bool()
            }

            val INT: Primitive = object : Primitive("int", PrimitiveType.INT) {
                private fun gType(other: ShakeType): ShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            PrimitiveType.BYTE,
                            PrimitiveType.SHORT,
                            PrimitiveType.INT -> int()
                            PrimitiveType.LONG -> long()
                            PrimitiveType.FLOAT -> float()
                            PrimitiveType.DOUBLE -> double()
                            PrimitiveType.UNSIGNED_BYTE -> int()
                            PrimitiveType.UNSIGNED_SHORT -> int()
                            PrimitiveType.UNSIGNED_INT,
                            PrimitiveType.UNSIGNED_LONG -> long()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): ShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): ShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): ShakeType? = gType(other)
                override fun divisionType(other: ShakeType): ShakeType? = gType(other)
                override fun modulusType(other: ShakeType): ShakeType? = gType(other)
                override fun powerType(other: ShakeType): ShakeType = double()

                override fun greaterThanType(other: ShakeType): ShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun lessThanType(other: ShakeType): ShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun andType(other: ShakeType): ShakeType = bool()
                override fun orType(other: ShakeType): ShakeType = bool()
                override fun notType(): ShakeType = bool()
            }

            val LONG: Primitive = object : Primitive("long", PrimitiveType.LONG) {
                private fun gType(other: ShakeType): ShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            PrimitiveType.BYTE,
                            PrimitiveType.SHORT,
                            PrimitiveType.INT,
                            PrimitiveType.LONG -> long()
                            PrimitiveType.FLOAT -> float()
                            PrimitiveType.DOUBLE -> double()
                            PrimitiveType.UNSIGNED_BYTE,
                            PrimitiveType.UNSIGNED_SHORT,
                            PrimitiveType.UNSIGNED_INT,
                            PrimitiveType.UNSIGNED_LONG -> long()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): ShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): ShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): ShakeType? = gType(other)
                override fun divisionType(other: ShakeType): ShakeType? = gType(other)
                override fun modulusType(other: ShakeType): ShakeType? = gType(other)
                override fun powerType(other: ShakeType): ShakeType = double()

                override fun greaterThanType(other: ShakeType): ShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun lessThanType(other: ShakeType): ShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun andType(other: ShakeType): ShakeType = bool()
                override fun orType(other: ShakeType): ShakeType = bool()
                override fun notType(): ShakeType = bool()
            }

            val FLOAT: Primitive = object : Primitive("float", PrimitiveType.FLOAT) {
                private fun gType(other: ShakeType): ShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            PrimitiveType.UNSIGNED_BYTE,
                            PrimitiveType.UNSIGNED_SHORT,
                            PrimitiveType.UNSIGNED_INT,
                            PrimitiveType.UNSIGNED_LONG,
                            PrimitiveType.BYTE,
                            PrimitiveType.SHORT,
                            PrimitiveType.INT,
                            PrimitiveType.LONG,
                            PrimitiveType.FLOAT -> float()
                            PrimitiveType.DOUBLE -> double()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): ShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): ShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): ShakeType? = gType(other)
                override fun divisionType(other: ShakeType): ShakeType? = gType(other)
                override fun modulusType(other: ShakeType): ShakeType? = gType(other)
                override fun powerType(other: ShakeType): ShakeType = double()

                override fun greaterThanType(other: ShakeType): ShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun lessThanType(other: ShakeType): ShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun andType(other: ShakeType): ShakeType = bool()
                override fun orType(other: ShakeType): ShakeType = bool()
                override fun notType(): ShakeType = bool()
            }

            val DOUBLE: Primitive = object : Primitive("double", PrimitiveType.DOUBLE) {
                private fun gType(other: ShakeType): ShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            PrimitiveType.BYTE -> double()
                            PrimitiveType.SHORT -> double()
                            PrimitiveType.INT -> double()
                            PrimitiveType.LONG -> double()
                            PrimitiveType.FLOAT -> double()
                            PrimitiveType.DOUBLE -> double()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): ShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): ShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): ShakeType? = gType(other)
                override fun divisionType(other: ShakeType): ShakeType? = gType(other)
                override fun modulusType(other: ShakeType): ShakeType? = gType(other)
                override fun powerType(other: ShakeType): ShakeType = double()

                override fun greaterThanType(other: ShakeType): ShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun lessThanType(other: ShakeType): ShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun andType(other: ShakeType): ShakeType = bool()
                override fun orType(other: ShakeType): ShakeType = bool()
                override fun notType(): ShakeType = bool()
            }

            val UNSIGNED_BYTE: Primitive = object : Primitive("unsigned_byte", PrimitiveType.UNSIGNED_BYTE) {
                private fun gType(other: ShakeType): ShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            PrimitiveType.BYTE -> short()
                            PrimitiveType.SHORT -> int()
                            PrimitiveType.INT -> long()
                            PrimitiveType.LONG -> long()
                            PrimitiveType.FLOAT -> float()
                            PrimitiveType.DOUBLE -> double()
                            PrimitiveType.UNSIGNED_BYTE -> unsignedByte()
                            PrimitiveType.UNSIGNED_SHORT -> unsignedShort()
                            PrimitiveType.UNSIGNED_INT -> unsignedInt()
                            PrimitiveType.UNSIGNED_LONG -> unsignedLong()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): ShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): ShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): ShakeType? = gType(other)
                override fun divisionType(other: ShakeType): ShakeType? = gType(other)
                override fun modulusType(other: ShakeType): ShakeType? = gType(other)
                override fun powerType(other: ShakeType): ShakeType = double()

                override fun greaterThanType(other: ShakeType): ShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun lessThanType(other: ShakeType): ShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun andType(other: ShakeType): ShakeType = bool()
                override fun orType(other: ShakeType): ShakeType = bool()
                override fun notType(): ShakeType = bool()
            }

            val UNSIGNED_SHORT: Primitive = object : Primitive("unsigned_short", PrimitiveType.UNSIGNED_SHORT) {
                private fun gType(other: ShakeType): ShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            PrimitiveType.BYTE -> short()
                            PrimitiveType.SHORT -> int()
                            PrimitiveType.INT -> long()
                            PrimitiveType.LONG -> long()
                            PrimitiveType.FLOAT -> float()
                            PrimitiveType.DOUBLE -> double()
                            PrimitiveType.UNSIGNED_BYTE -> unsignedByte()
                            PrimitiveType.UNSIGNED_SHORT -> unsignedShort()
                            PrimitiveType.UNSIGNED_INT -> unsignedInt()
                            PrimitiveType.UNSIGNED_LONG -> unsignedLong()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): ShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): ShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): ShakeType? = gType(other)
                override fun divisionType(other: ShakeType): ShakeType? = gType(other)
                override fun modulusType(other: ShakeType): ShakeType? = gType(other)
                override fun powerType(other: ShakeType): ShakeType = double()

                override fun greaterThanType(other: ShakeType): ShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun lessThanType(other: ShakeType): ShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun andType(other: ShakeType): ShakeType = bool()
                override fun orType(other: ShakeType): ShakeType = bool()
                override fun notType(): ShakeType = bool()
            }

            val UNSIGNED_INT: Primitive = object : Primitive("unsigned_int", PrimitiveType.UNSIGNED_INT) {
                private fun gType(other: ShakeType): ShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            PrimitiveType.BYTE -> int()
                            PrimitiveType.SHORT -> long()
                            PrimitiveType.INT -> long()
                            PrimitiveType.LONG -> long()
                            PrimitiveType.FLOAT -> float()
                            PrimitiveType.DOUBLE -> double()
                            PrimitiveType.UNSIGNED_BYTE -> unsignedByte()
                            PrimitiveType.UNSIGNED_SHORT -> unsignedShort()
                            PrimitiveType.UNSIGNED_INT -> unsignedInt()
                            PrimitiveType.UNSIGNED_LONG -> unsignedLong()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): ShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): ShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): ShakeType? = gType(other)
                override fun divisionType(other: ShakeType): ShakeType? = gType(other)
                override fun modulusType(other: ShakeType): ShakeType? = gType(other)
                override fun powerType(other: ShakeType): ShakeType = double()

                override fun greaterThanType(other: ShakeType): ShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun lessThanType(other: ShakeType): ShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun andType(other: ShakeType): ShakeType = bool()
                override fun orType(other: ShakeType): ShakeType = bool()
                override fun notType(): ShakeType = bool()
            }

            val UNSIGNED_LONG: Primitive = object : Primitive("unsigned_long", PrimitiveType.UNSIGNED_LONG) {
                private fun gType(other: ShakeType): ShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            PrimitiveType.BYTE -> long()
                            PrimitiveType.SHORT -> long()
                            PrimitiveType.INT -> long()
                            PrimitiveType.LONG -> long()
                            PrimitiveType.FLOAT -> float()
                            PrimitiveType.DOUBLE -> double()
                            PrimitiveType.UNSIGNED_BYTE -> unsignedByte()
                            PrimitiveType.UNSIGNED_SHORT -> unsignedShort()
                            PrimitiveType.UNSIGNED_INT -> unsignedInt()
                            PrimitiveType.UNSIGNED_LONG -> unsignedLong()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): ShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): ShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): ShakeType? = gType(other)
                override fun divisionType(other: ShakeType): ShakeType? = gType(other)
                override fun modulusType(other: ShakeType): ShakeType? = gType(other)
                override fun powerType(other: ShakeType): ShakeType = double()

                override fun greaterThanType(other: ShakeType): ShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun lessThanType(other: ShakeType): ShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun andType(other: ShakeType): ShakeType = bool()
                override fun orType(other: ShakeType): ShakeType = bool()
                override fun notType(): ShakeType = bool()
            }

            val CHAR = object : Primitive("char", PrimitiveType.CHAR) {
                private fun gType(other: ShakeType): ShakeType? {
                    if (other is Primitive) {
                        return when (other.type) {
                            PrimitiveType.BYTE,
                            PrimitiveType.SHORT,
                            PrimitiveType.INT -> int()
                            PrimitiveType.LONG -> long()
                            PrimitiveType.FLOAT -> float()
                            PrimitiveType.DOUBLE -> double()
                            PrimitiveType.UNSIGNED_BYTE,
                            PrimitiveType.UNSIGNED_SHORT,
                            PrimitiveType.UNSIGNED_INT -> unsignedInt()
                            PrimitiveType.UNSIGNED_LONG -> unsignedLong()
                            else -> null
                        }
                    }
                    return null
                }

                override fun additionType(other: ShakeType): ShakeType? = gType(other)
                override fun subtractionType(other: ShakeType): ShakeType? = gType(other)
                override fun multiplicationType(other: ShakeType): ShakeType? = gType(other)
                override fun divisionType(other: ShakeType): ShakeType? = gType(other)
                override fun modulusType(other: ShakeType): ShakeType? = gType(other)
                override fun powerType(other: ShakeType): ShakeType = double()

                override fun greaterThanType(other: ShakeType): ShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun lessThanType(other: ShakeType): ShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun andType(other: ShakeType): ShakeType = bool()
                override fun orType(other: ShakeType): ShakeType = bool()
                override fun notType(): ShakeType = bool()
            }

            val VOID = object : Primitive("void", PrimitiveType.VOID) {
                override fun additionType(other: ShakeType): ShakeType? = null
                override fun subtractionType(other: ShakeType): ShakeType? = null
                override fun multiplicationType(other: ShakeType): ShakeType? = null
                override fun divisionType(other: ShakeType): ShakeType? = null
                override fun modulusType(other: ShakeType): ShakeType? = null
                override fun powerType(other: ShakeType): ShakeType = double()

                override fun greaterThanType(other: ShakeType): ShakeType = bool()
                override fun greaterThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun lessThanType(other: ShakeType): ShakeType = bool()
                override fun lessThanOrEqualType(other: ShakeType): ShakeType = bool()
                override fun andType(other: ShakeType): ShakeType = bool()
                override fun orType(other: ShakeType): ShakeType = bool()
                override fun notType(): ShakeType = bool()
            }
        }
    }

    class Object (
        name: String,
        val clazz: ShakeClass
    ) : ShakeType(name) {
        override fun additionType(other: ShakeType): ShakeType? = null
        override fun subtractionType(other: ShakeType): ShakeType? = null
        override fun multiplicationType(other: ShakeType): ShakeType? = null
        override fun divisionType(other: ShakeType): ShakeType? = null
        override fun modulusType(other: ShakeType): ShakeType? = null
        override fun powerType(other: ShakeType): ShakeType? = null
        override fun equalsType(other: ShakeType): ShakeType? = null
        override fun notEqualsType(other: ShakeType): ShakeType? = null
        override fun greaterThanType(other: ShakeType): ShakeType? = null
        override fun greaterThanOrEqualType(other: ShakeType): ShakeType? = null
        override fun lessThanType(other: ShakeType): ShakeType? = null
        override fun lessThanOrEqualType(other: ShakeType): ShakeType? = null
        override fun andType(other: ShakeType): ShakeType? = null
        override fun orType(other: ShakeType): ShakeType? = null
        override fun notType(): ShakeType? = null

        override fun childType(name: String): ShakeType? = clazz.fields.find { it.name == name }?.type
        override fun childFunctions(name: String): List<ShakeFunction> {
            return clazz.methods.filter { it.name == name }
        }

        override val kind: Kind
            get() = Kind.OBJECT

        override fun compatibleTo(other: ShakeType): Boolean {
            return other is Object && clazz.compatibleTo(other.clazz)
        }
    }

    class Array (
        name: String,
        val elementType: ShakeType
    ) : ShakeType(name) {
        override fun additionType(other: ShakeType): ShakeType? = null
        override fun subtractionType(other: ShakeType): ShakeType? = null
        override fun multiplicationType(other: ShakeType): ShakeType? = null
        override fun divisionType(other: ShakeType): ShakeType? = null
        override fun modulusType(other: ShakeType): ShakeType? = null
        override fun powerType(other: ShakeType): ShakeType? = null
        override fun equalsType(other: ShakeType): ShakeType? = null
        override fun notEqualsType(other: ShakeType): ShakeType? = null
        override fun greaterThanType(other: ShakeType): ShakeType? = null
        override fun greaterThanOrEqualType(other: ShakeType): ShakeType? = null
        override fun lessThanType(other: ShakeType): ShakeType? = null
        override fun lessThanOrEqualType(other: ShakeType): ShakeType? = null
        override fun andType(other: ShakeType): ShakeType? = null
        override fun orType(other: ShakeType): ShakeType? = null
        override fun notType(): ShakeType? = null

        override val kind: Kind
            get() = Kind.ARRAY
        override fun compatibleTo(other: ShakeType): Boolean {
            return other is Array && elementType.compatibleTo(other.elementType)
        }
    }

    class Lambda (
        name: String,
        val parameters: List<ShakeParameter>,
        val returnType: ShakeType
    ) : ShakeType(name) {
        override fun additionType(other: ShakeType): ShakeType? = null
        override fun subtractionType(other: ShakeType): ShakeType? = null
        override fun multiplicationType(other: ShakeType): ShakeType? = null
        override fun divisionType(other: ShakeType): ShakeType? = null
        override fun modulusType(other: ShakeType): ShakeType? = null
        override fun powerType(other: ShakeType): ShakeType? = null
        override fun equalsType(other: ShakeType): ShakeType? = null
        override fun notEqualsType(other: ShakeType): ShakeType? = null
        override fun greaterThanType(other: ShakeType): ShakeType? = null
        override fun greaterThanOrEqualType(other: ShakeType): ShakeType? = null
        override fun lessThanType(other: ShakeType): ShakeType? = null
        override fun lessThanOrEqualType(other: ShakeType): ShakeType? = null
        override fun andType(other: ShakeType): ShakeType? = null
        override fun orType(other: ShakeType): ShakeType? = null
        override fun notType(): ShakeType? = null

        override val kind: Kind
            get() = Kind.LAMBDA
        override fun compatibleTo(other: ShakeType): Boolean {
            // TODO: Check parameters
            return other is Lambda && returnType.compatibleTo(other.returnType)
        }
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
        fun array(elementType: ShakeType): ShakeType {
            return Array("${elementType.name}[]", elementType)
        }
        fun objectType(clazz: ShakeClass): ShakeType {
            return Object(clazz.name, clazz)
        }
    }
}