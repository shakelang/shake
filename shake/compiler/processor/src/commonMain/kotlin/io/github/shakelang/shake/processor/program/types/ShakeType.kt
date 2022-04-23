package io.github.shakelang.shake.processor.program.types

interface ShakeType {

    val name: String

    fun assignType(other: ShakeType): ShakeType?
    fun additionAssignType(other: ShakeType): ShakeType?
    fun subtractionAssignType(other: ShakeType): ShakeType?
    fun multiplicationAssignType(other: ShakeType): ShakeType?
    fun divisionAssignType(other: ShakeType): ShakeType?
    fun modulusAssignType(other: ShakeType): ShakeType?
    fun powerAssignType(other: ShakeType): ShakeType?
    fun incrementBeforeType(): ShakeType?
    fun incrementAfterType(): ShakeType?
    fun decrementBeforeType(): ShakeType?
    fun decrementAfterType(): ShakeType?

    fun additionType(other: ShakeType): ShakeType?
    fun subtractionType(other: ShakeType): ShakeType?
    fun multiplicationType(other: ShakeType): ShakeType?
    fun divisionType(other: ShakeType): ShakeType?
    fun modulusType(other: ShakeType): ShakeType?
    fun powerType(other: ShakeType): ShakeType?
    fun equalsType(other: ShakeType): ShakeType?
    fun notEqualsType(other: ShakeType): ShakeType?
    fun greaterThanType(other: ShakeType): ShakeType?
    fun greaterThanOrEqualType(other: ShakeType): ShakeType?
    fun lessThanType(other: ShakeType): ShakeType?
    fun lessThanOrEqualType(other: ShakeType): ShakeType?
    fun andType(other: ShakeType): ShakeType?
    fun orType(other: ShakeType): ShakeType?
    fun notType(): ShakeType?
    fun childType(name: String): ShakeType?
    fun childFunctions(name: String): List<ShakeFunction>?
    fun childInvokable(name: String): List<ShakeFunction>?

    val kind: Kind

    fun castableTo(other: ShakeType): Boolean
    fun compatibleTo(other: ShakeType): Boolean
    fun compatibilityDistance(other: ShakeType): Int

    fun toJson(): Map<String, Any?>

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

    interface Primitive : ShakeType {

        val type: PrimitiveType
        override val kind: Kind get() = Kind.PRIMITIVE

        override fun castableTo(other: ShakeType): Boolean =
            other is Primitive &&
                    (other.type == PrimitiveType.BYTE
                            || other.type == PrimitiveType.SHORT
                            || other.type == PrimitiveType.INT
                            || other.type == PrimitiveType.LONG
                            || other.type == PrimitiveType.FLOAT
                            || other.type == PrimitiveType.DOUBLE
                            || other.type == PrimitiveType.UNSIGNED_BYTE
                            || other.type == PrimitiveType.UNSIGNED_SHORT
                            || other.type == PrimitiveType.UNSIGNED_INT
                            || other.type == PrimitiveType.UNSIGNED_LONG
                            || other.type == PrimitiveType.CHAR)
    }

    interface Object : ShakeType {
        val clazz: ShakeClass
    }

    interface Array : ShakeType {
        val elementType: ShakeType
    }

    interface Lambda : ShakeType {
        val parameters: List<ShakeParameter>
        val returnType: ShakeType

        override fun toJson(): Map<String, Any?> {
            return mapOf("type" to "lambda", "parameters" to parameters.map { it.toJson() }, "returnType" to returnType.toJson())
        }
    }
}