package com.shakelang.util.commander

typealias ValueTransformer<T> = (String) -> T
typealias NullableValueTransformer<T> = (String?) -> T
typealias ValueValidator = (String?) -> Unit

fun ValueValidator.accepts(value: String?): Boolean {
    return try {
        this(value)
        true
    } catch (e: ValueException) {
        false
    }
}

fun valueValidator(validator: ValueValidator): ValueValidator = validator

/**
 * A wrapper for a string-encoded value
 */
data class Value(
    val value: String?
) {
    fun <T> to(valueTransformer: ValueTransformer<T>) = valueTransformer(value ?: throw NullPointerException("Value is null"))
    fun toByte(): Byte = value?.toByteOrNull() ?: throw NumberFormatException("Value is null")
    fun toShort(): Short = value?.toShortOrNull() ?: throw NumberFormatException("Value is null")
    fun toInt(): Int = value?.toIntOrNull() ?: throw NumberFormatException("Value is null")
    fun toLong(): Long = value ?.toLongOrNull() ?: throw NumberFormatException("Value is null")
    fun toFloat(): Float = value?.toFloatOrNull() ?: throw NumberFormatException("Value is null")
    fun toDouble(): Double = value?.toDoubleOrNull() ?: throw NumberFormatException("Value is null")
    fun toBoolean(): Boolean = value?.toBooleanStrictOrNull() ?: throw NumberFormatException("Value is null")
    override fun toString(): String = value ?: throw NullPointerException("Value is null")

    fun <T> toOrNull(valueTransformer: NullableValueTransformer<T>) = valueTransformer(value)
    fun toByteOrNull(): Byte? = value?.toByteOrNull()
    fun toShortOrNull(): Short? = value?.toShortOrNull()
    fun toIntOrNull(): Int? = value?.toIntOrNull()
    fun toLongOrNull(): Long? = value?.toLongOrNull()
    fun toFloatOrNull(): Float? = value?.toFloatOrNull()
    fun toDoubleOrNull(): Double? = value?.toDoubleOrNull()
    fun toBooleanOrNull(): Boolean? = value?.toBooleanStrictOrNull()
    fun toStringOrNull(): String? = value

    fun validate(valueValidator: ValueValidator) = valueValidator.accepts(value)
    fun validateByte(): Boolean = value?.toByteOrNull() != null
    fun validateShort(): Boolean = value?.toShortOrNull() != null
    fun validateInt(): Boolean = value?.toIntOrNull() != null
    fun validateLong(): Boolean = value?.toLongOrNull() != null
    fun validateFloat(): Boolean = value?.toFloatOrNull() != null
    fun validateDouble(): Boolean = value?.toDoubleOrNull() != null
    fun validateBoolean(): Boolean = value?.toBooleanStrictOrNull() != null
    fun validateString(): Boolean = value != null

    fun isNull(): Boolean = value == null
    fun isNotNull(): Boolean = value != null
    fun isByte(): Boolean = value?.toByteOrNull() != null
    fun isShort(): Boolean = value?.toShortOrNull() != null
    fun isInt(): Boolean = value?.toIntOrNull() != null
    fun isLong(): Boolean = value?.toLongOrNull() != null
    fun isFloat(): Boolean = value?.toFloatOrNull() != null
    fun isDouble(): Boolean = value?.toDoubleOrNull() != null
    fun isBoolean(): Boolean = value?.toBooleanStrictOrNull() != null
    fun isString(): Boolean = value != null
}
