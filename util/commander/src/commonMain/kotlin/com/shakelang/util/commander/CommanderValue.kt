package com.shakelang.util.commander

/**
 * A transformer for a string-encoded value
 * @param T the type of the value
 * @return the transformed value
 * @throws CommanderValueException if the value could not be transformed
 * @see CommanderValue
 * @since 0.1.0
 * @version 0.1.0
 */
typealias CommanderValueTransformer<T> = (String) -> T

/**
 * A transformer for a string-encoded value
 * @param T the type of the value
 * @return the transformed value
 * @see CommanderValue
 * @since 0.1.0
 * @version 0.1.0
 */
typealias CommanderNullableValueTransformer<T> = (String?) -> T

/**
 * A validator for a string-encoded value
 * @throws CommanderValueException if the value could not be validated
 * @see CommanderValue
 * @since 0.1.0
 * @version 0.1.0
 */
typealias CommanderValueValidator = (String?) -> Unit

/**
 * Check if a CommanderValueValidator accepts a value
 * (Catches the CommanderValueException and returns a boolean)
 * @param value the value to check
 * @return true if the value is accepted, false otherwise
 * @see CommanderValueValidator
 * @since 0.1.0
 * @version 0.1.0
 */
fun CommanderValueValidator.accepts(value: String?): Boolean {
    return try {
        this(value)
        true
    } catch (e: CommanderValueException) {
        false
    }
}

/**
 * Create a new [CommanderValueValidator] from a lambda
 * @param validator the lambda
 * @return the [CommanderValueValidator]
 * @see CommanderValueValidator
 * @since 0.1.0
 * @version 0.1.0
 */
fun valueValidator(validator: CommanderValueValidator): CommanderValueValidator = validator

/**
 * A wrapper for a string-encoded value
 * @property value the value
 * @constructor Creates a new [CommanderValue]
 * @see CommanderValueTransformer
 * @see CommanderValueValidator
 * @since 0.1.0
 * @version 0.1.0
 */
data class CommanderValue(
    val value: String?,
) {

    /**
     * Convert the value to a specific type (using a [CommanderValueTransformer])
     * @param T the type of the value
     * @param valueTransformer the [CommanderValueTransformer] to use
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @since 0.1.0
     * @version 0.1.0
     */
    fun <T> to(valueTransformer: CommanderValueTransformer<T>) = valueTransformer(value ?: throw NullPointerException("CommanderValue is null"))

    /**
     * Convert the value to a byte
     * @return the byte
     * @throws NumberFormatException if the value could not be converted
     * @see Byte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toByte(): Byte = value?.toByteOrNull() ?: throw NumberFormatException("CommanderValue is null")

    /**
     * Convert the value to a short
     * @return the short
     * @throws NumberFormatException if the value could not be converted
     * @see Short
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toShort(): Short = value?.toShortOrNull() ?: throw NumberFormatException("CommanderValue is null")

    /**
     * Convert the value to an int
     * @return the int
     * @throws NumberFormatException if the value could not be converted
     * @see Int
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toInt(): Int = value?.toIntOrNull() ?: throw NumberFormatException("CommanderValue is null")

    /**
     * Convert the value to a long
     * @return the long
     * @throws NumberFormatException if the value could not be converted
     * @see Long
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toLong(): Long = value?.toLongOrNull() ?: throw NumberFormatException("CommanderValue is null")

    /**
     * Convert the value to a float
     * @return the float
     * @throws NumberFormatException if the value could not be converted
     * @see Float
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toFloat(): Float = value?.toFloatOrNull() ?: throw NumberFormatException("CommanderValue is null")

    /**
     * Convert the value to a double
     * @return the double
     * @throws NumberFormatException if the value could not be converted
     * @see Double
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toDouble(): Double = value?.toDoubleOrNull() ?: throw NumberFormatException("CommanderValue is null")

    /**
     * Convert the value to a boolean
     * @return the boolean
     * @throws NumberFormatException if the value could not be converted
     * @see Boolean
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toBoolean(): Boolean = value?.toBooleanStrictOrNull() ?: throw NumberFormatException("CommanderValue is null")

    /**
     * Convert the value to a string
     * @return the string
     * @throws NullPointerException if the value is null
     * @see String
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun toString(): String = value ?: throw NullPointerException("CommanderValue is null")

    /**
     * Convert the value to a specific type (using a [CommanderNullableValueTransformer])
     * @param T the type of the value
     * @param valueTransformer the [CommanderNullableValueTransformer] to use
     * @return the transformed value
     * @see CommanderNullableValueTransformer
     * @since 0.1.0
     * @version 0.1.0
     */
    fun <T> toOrNull(valueTransformer: CommanderNullableValueTransformer<T>): T? {
        if (value == null) return null
        return try {
            valueTransformer(value)
        } catch (e: CommanderValueException) {
            null
        }
    }

    /**
     * Convert the value to a byte (or null if the value could not be converted)
     * @return the byte
     * @see Byte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toByteOrNull(): Byte? = value?.toByteOrNull()

    /**
     * Convert the value to a short (or null if the value could not be converted)
     * @return the short
     * @see Short
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toShortOrNull(): Short? = value?.toShortOrNull()

    /**
     * Convert the value to an int (or null if the value could not be converted)
     * @return the int
     * @see Int
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toIntOrNull(): Int? = value?.toIntOrNull()

    /**
     * Convert the value to a long (or null if the value could not be converted)
     * @return the long
     * @see Long
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toLongOrNull(): Long? = value?.toLongOrNull()

    /**
     * Convert the value to a float (or null if the value could not be converted)
     * @return the float
     * @see Float
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toFloatOrNull(): Float? = value?.toFloatOrNull()

    /**
     * Convert the value to a double (or null if the value could not be converted)
     * @return the double
     * @see Double
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toDoubleOrNull(): Double? = value?.toDoubleOrNull()

    /**
     * Convert the value to a boolean (or null if the value could not be converted)
     * @return the boolean
     * @see Boolean
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toBooleanOrNull(): Boolean? = value?.toBooleanStrictOrNull()

    /**
     * Convert the value to a string (or null if the value is null)
     * @return the string
     * @see String
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toStringOrNull(): String? = value

    /**
     * Check if the value is valid (using a [CommanderValueValidator])
     * @return true if the value is null, false otherwise
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validate(valueValidator: CommanderValueValidator) = valueValidator.accepts(value)

    /**
     * Check if the value is convertable to a byte
     * @return true if the value is convertable to a byte, false otherwise
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateByte(): Boolean = value?.toByteOrNull() != null

    /**
     * Check if the value is convertable to a short
     * @return true if the value is convertable to a short, false otherwise
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateShort(): Boolean = value?.toShortOrNull() != null

    /**
     * Check if the value is convertable to an int
     * @return true if the value is convertable to an int, false otherwise
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateInt(): Boolean = value?.toIntOrNull() != null

    /**
     * Check if the value is convertable to a long
     * @return true if the value is convertable to a long, false otherwise
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateLong(): Boolean = value?.toLongOrNull() != null

    /**
     * Check if the value is convertable to a float
     * @return true if the value is convertable to a float, false otherwise
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateFloat(): Boolean = value?.toFloatOrNull() != null

    /**
     * Check if the value is convertable to a double
     * @return true if the value is convertable to a double, false otherwise
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateDouble(): Boolean = value?.toDoubleOrNull() != null

    /**
     * Check if the value is convertable to a boolean
     * @return true if the value is convertable to a boolean, false otherwise
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateBoolean(): Boolean = value?.toBooleanStrictOrNull() != null

    /**
     * Check if the value is convertable to a string
     * @return true if the value is convertable to a string, false otherwise
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateString(): Boolean = value != null

    /**
     * Check if the value is null
     * @return true if the value is null, false otherwise
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNull(): Boolean = value == null

    /**
     * Check if the value is not null
     * @return true if the value is not null, false otherwise
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotNull(): Boolean = value != null

    /**
     * Check if the value is convertable to a byte
     * @return true if the value is convertable to a byte, false otherwise
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isByte(): Boolean = value?.toByteOrNull() != null

    /**
     * Check if the value is convertable to a short
     * @return true if the value is convertable to a short, false otherwise
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isShort(): Boolean = value?.toShortOrNull() != null

    /**
     * Check if the value is convertable to an int
     * @return true if the value is convertable to an int, false otherwise
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isInt(): Boolean = value?.toIntOrNull() != null

    /**
     * Check if the value is convertable to a long
     * @return true if the value is convertable to a long, false otherwise
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isLong(): Boolean = value?.toLongOrNull() != null

    /**
     * Check if the value is convertable to a float
     * @return true if the value is convertable to a float, false otherwise
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isFloat(): Boolean = value?.toFloatOrNull() != null

    /**
     * Check if the value is convertable to a double
     * @return true if the value is convertable to a double, false otherwise
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isDouble(): Boolean = value?.toDoubleOrNull() != null

    /**
     * Check if the value is convertable to a boolean
     * @return true if the value is convertable to a boolean, false otherwise
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isBoolean(): Boolean = value?.toBooleanStrictOrNull() != null

    /**
     * Check if the value is convertable to a string
     * @return true if the value is convertable to a string, false otherwise
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isString(): Boolean = value != null
}
