package com.shakelang.util.commander

typealias CommanderValueTransformer<T> = (String) -> T
typealias CommanderNullableValueTransformer<T> = (String?) -> T
typealias CommanderValueValidator = (String?) -> Unit

fun CommanderValueValidator.accepts(value: String?): Boolean {
    return try {
        this(value)
        true
    } catch (e: CommanderValueException) {
        false
    }
}

fun valueValidator(validator: CommanderValueValidator): CommanderValueValidator = validator

/**
 * A wrapper for a string-encoded value
 */
data class CommanderValue(
    val value: String?,
) {
    fun <T> to(valueTransformer: CommanderValueTransformer<T>) = valueTransformer(value ?: throw NullPointerException("CommanderValue is null"))
    fun toByte(): Byte = value?.toByteOrNull() ?: throw NumberFormatException("CommanderValue is null")
    fun toShort(): Short = value?.toShortOrNull() ?: throw NumberFormatException("CommanderValue is null")
    fun toInt(): Int = value?.toIntOrNull() ?: throw NumberFormatException("CommanderValue is null")
    fun toLong(): Long = value?.toLongOrNull() ?: throw NumberFormatException("CommanderValue is null")
    fun toFloat(): Float = value?.toFloatOrNull() ?: throw NumberFormatException("CommanderValue is null")
    fun toDouble(): Double = value?.toDoubleOrNull() ?: throw NumberFormatException("CommanderValue is null")
    fun toBoolean(): Boolean = value?.toBooleanStrictOrNull() ?: throw NumberFormatException("CommanderValue is null")
    override fun toString(): String = value ?: throw NullPointerException("CommanderValue is null")

    fun <T> toOrNull(valueTransformer: CommanderNullableValueTransformer<T>) = valueTransformer(value)
    fun toByteOrNull(): Byte? = value?.toByteOrNull()
    fun toShortOrNull(): Short? = value?.toShortOrNull()
    fun toIntOrNull(): Int? = value?.toIntOrNull()
    fun toLongOrNull(): Long? = value?.toLongOrNull()
    fun toFloatOrNull(): Float? = value?.toFloatOrNull()
    fun toDoubleOrNull(): Double? = value?.toDoubleOrNull()
    fun toBooleanOrNull(): Boolean? = value?.toBooleanStrictOrNull()
    fun toStringOrNull(): String? = value

    fun validate(valueValidator: CommanderValueValidator) = valueValidator.accepts(value)
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
