package io.github.shakelang.shake.processor.program.types

import io.github.shakelang.shake.processor.ShakeSelect


interface ShakeType {

    val name: String
    val qualifiedName: String

    fun assignType(other: ShakeType): ShakeType? = null
    fun additionAssignType(other: ShakeType): ShakeType? = null
    fun subtractionAssignType(other: ShakeType): ShakeType? = null
    fun multiplicationAssignType(other: ShakeType): ShakeType? = null
    fun divisionAssignType(other: ShakeType): ShakeType? = null
    fun modulusAssignType(other: ShakeType): ShakeType? = null
    fun powerAssignType(other: ShakeType): ShakeType? = null
    fun incrementBeforeType(): ShakeType? = null
    fun incrementAfterType(): ShakeType? = null
    fun decrementBeforeType(): ShakeType? = null
    fun decrementAfterType(): ShakeType? = null

    fun additionType(other: ShakeType): ShakeType? = null
    fun subtractionType(other: ShakeType): ShakeType? = null
    fun multiplicationType(other: ShakeType): ShakeType? = null
    fun divisionType(other: ShakeType): ShakeType? = null
    fun modulusType(other: ShakeType): ShakeType? = null
    fun powerType(other: ShakeType): ShakeType? = null
    fun equalsType(other: ShakeType): ShakeType? = null
    fun notEqualsType(other: ShakeType): ShakeType? = null
    fun greaterThanType(other: ShakeType): ShakeType? = null
    fun greaterThanOrEqualType(other: ShakeType): ShakeType? = null
    fun lessThanType(other: ShakeType): ShakeType? = null
    fun lessThanOrEqualType(other: ShakeType): ShakeType? = null
    fun andType(other: ShakeType): ShakeType? = null
    fun orType(other: ShakeType): ShakeType? = null
    fun notType(): ShakeType? = null
    fun childType(name: String): ShakeType? = null
    fun childFunctions(name: String): List<ShakeMethod>? = null
    fun childInvokable(name: String): List<ShakeMethod>? = null

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
        DYNAMIC,
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
        override val kind: Kind get() = Kind.OBJECT

        override fun additionType(other: ShakeType): ShakeType? {
            val methods = clazz.methods.filter { it.name == "plus" && it.isOperator }
            val fn = ShakeSelect.selectFunction(methods, listOf(other))
            return fn?.returnType
        }
        override fun subtractionType(other: ShakeType): ShakeType? {
            val methods = clazz.methods.filter { it.name == "minus" && it.isOperator }
            val fn = ShakeSelect.selectFunction(methods, listOf(other))
            return fn?.returnType
        }
        override fun multiplicationType(other: ShakeType): ShakeType? {
            val methods = clazz.methods.filter { it.name == "multiply" && it.isOperator }
            val fn = ShakeSelect.selectFunction(methods, listOf(other))
            return fn?.returnType
        }
        override fun divisionType(other: ShakeType): ShakeType? {
            val methods = clazz.methods.filter { it.name == "divide" && it.isOperator }
            val fn = ShakeSelect.selectFunction(methods, listOf(other))
            return fn?.returnType
        }
        override fun modulusType(other: ShakeType): ShakeType? {
            val methods = clazz.methods.filter { it.name == "modulus" && it.isOperator }
            val fn = ShakeSelect.selectFunction(methods, listOf(other))
            return fn?.returnType
        }
        override fun powerType(other: ShakeType): ShakeType? {
            val methods = clazz.methods.filter { it.name == "power" && it.isOperator }
            val fn = ShakeSelect.selectFunction(methods, listOf(other))
            return fn?.returnType
        }
        override fun equalsType(other: ShakeType): ShakeType? {
            val methods = clazz.methods.filter { it.name == "equals" && it.isOperator }
            val fn = ShakeSelect.selectFunction(methods, listOf(other))
            return fn?.returnType
        }
        override fun notEqualsType(other: ShakeType): ShakeType? {
            val methods = clazz.methods.filter { it.name == "notEquals" && it.isOperator }
            val fn = ShakeSelect.selectFunction(methods, listOf(other))
            return fn?.returnType
        }
        override fun greaterThanType(other: ShakeType): ShakeType? {
            val methods = clazz.methods.filter { it.name == "greaterThan" && it.isOperator }
            val fn = ShakeSelect.selectFunction(methods, listOf(other))
            return fn?.returnType
        }
        override fun greaterThanOrEqualType(other: ShakeType): ShakeType? {
            val methods = clazz.methods.filter { it.name == "greaterThanOrEqual" && it.isOperator }
            val fn = ShakeSelect.selectFunction(methods, listOf(other))
            return fn?.returnType
        }
        override fun lessThanType(other: ShakeType): ShakeType? {
            val methods = clazz.methods.filter { it.name == "lessThan" && it.isOperator }
            val fn = ShakeSelect.selectFunction(methods, listOf(other))
            return fn?.returnType
        }
        override fun lessThanOrEqualType(other: ShakeType): ShakeType? {
            val methods = clazz.methods.filter { it.name == "lessThanOrEqual" && it.isOperator }
            val fn = ShakeSelect.selectFunction(methods, listOf(other))
            return fn?.returnType
        }
        override fun andType(other: ShakeType): ShakeType? {
            val methods = clazz.methods.filter { it.name == "and" && it.isOperator }
            val fn = ShakeSelect.selectFunction(methods, listOf(other))
            return fn?.returnType
        }
        override fun orType(other: ShakeType): ShakeType? {
            val methods = clazz.methods.filter { it.name == "or" && it.isOperator }
            val fn = ShakeSelect.selectFunction(methods, listOf(other))
            return fn?.returnType
        }
        override fun notType(): ShakeType? {
            val methods = clazz.methods.filter { it.name == "not" && it.isOperator }
            val fn = ShakeSelect.selectFunction(methods, emptyList())
            return fn?.returnType
        }

        override fun childType(name: String): ShakeType? = clazz.fields.find { it.name == name }?.type
        override fun childFunctions(name: String): List<ShakeMethod> {
            return clazz.methods.filter { it.name == name }
        }

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
            get() = "L${clazz.qualifiedName}"
    }

    interface Array : ShakeType {
        val elementType: ShakeType
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

    interface Lambda : ShakeType {
        val parameters: List<ShakeParameter>
        val returnType: ShakeType

        override fun toJson(): Map<String, Any?> {
            return mapOf("type" to "lambda", "parameters" to parameters.map { it.toJson() }, "returnType" to returnType.toJson())
        }
    }
}