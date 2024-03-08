package com.shakelang.util.commander

/**
 * A transformer lambda for a string-encoded value
 * @param T the type of the value
 * @return the transformed value
 * @throws CommanderValueException if the value could not be transformed
 * @see CommanderValue
 * @since 0.1.0
 * @version 0.1.0
 */
typealias CommanderValueTransformerLambda<T> = (String?) -> T

/**
 * A validator lambda for a string-encoded value
 * @throws CommanderValueException if the value could not be validated
 * @see CommanderValue
 * @since 0.1.0
 * @version 0.1.0
 */
typealias CommanderValueValidatorLambda = (String?) -> Unit

/**
 * A transformer for a string-encoded value
 * @param T the type of the value
 * @return the transformed value
 * @throws CommanderValueException if the value could not be transformed
 * @see CommanderValue
 * @since 0.1.0
 * @version 0.1.0
 */
interface CommanderValueTransformer<T> {
    /**
     * Transform a string-encoded value
     * @param value the value to transform
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValue
     * @since 0.1.0
     * @version 0.1.0
     */
    fun transform(value: String?): T

    /**
     * Transform a string-encoded value (return null if the value could not be transformed)
     * @param value the value to transform
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValue
     * @since 0.1.0
     * @version 0.1.0
     */
    fun transformOrNull(value: String?): T? = try {
        transform(value)
    } catch (e: CommanderValueException) {
        null
    }

    /**
     * Transform a string-encoded value
     * @param value the value to transform
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValue
     * @since 0.1.0
     * @version 0.1.0
     */
    operator fun invoke(value: String?): T = transform(value)

    companion object {
        fun <T> of(transformer: CommanderValueTransformerLambda<T>): CommanderValueTransformer<T> =
            object : CommanderValueTransformer<T> {
                override fun transform(value: String?) = transformer(value)
            }
    }
}

/**
 * A validator for a string-encoded value
 * @throws CommanderValueException if the value could not be validated
 * @see CommanderValue
 * @since 0.1.0
 * @version 0.1.0
 */
interface CommanderValueValidator {
    /**
     * Validate a string-encoded value
     * @param value the value to validate
     * @throws CommanderValueException if the value could not be validated
     * @see CommanderValue
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validate(value: String?)

    /**
     * Validate a string-encoded value
     * @param value the value to validate
     * @throws CommanderValueException if the value could not be validated
     * @see CommanderValue
     * @since 0.1.0
     * @version 0.1.0
     */
    operator fun invoke(value: String?) = validate(value)

    /**
     * Check if a CommanderValueValidator accepts a value
     * (Catches the CommanderValueException and returns a boolean)
     * @param value the value to check
     * @return true if the value is accepted, false otherwise
     * @see CommanderValueValidator
     * @since 0.1.0
     * @version 0.1.0
     */
    fun accepts(value: String?): Boolean {
        return try {
            this(value)
            true
        } catch (e: CommanderValueException) {
            false
        }
    }

    companion object {
        fun of(validator: CommanderValueValidatorLambda): CommanderValueValidator = object : CommanderValueValidator {
            override fun validate(value: String?) = validator(value)
        }
    }
}

/**
 * Create a new [CommanderValueTransformer] from a lambda
 * @param transformer the lambda
 * @return the [CommanderValueTransformer]
 * @see CommanderValueTransformer
 * @since 0.1.0
 * @version 0.1.0
 */
fun <T> valueTransformer(transformer: CommanderValueTransformerLambda<T>) = CommanderValueTransformer.of(transformer)

/**
 * Create a new [CommanderValueValidator] from a lambda
 * @param validator the lambda
 * @return the [CommanderValueValidator]
 * @see CommanderValueValidator
 * @since 0.1.0
 * @version 0.1.0
 */
fun valueValidator(validator: CommanderValueValidatorLambda) = CommanderValueValidator.of(validator)

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
     * @see CommanderValueTransformer
     * @since 0.1.0
     * @version 0.1.0
     */
    fun <T> to(valueTransformer: CommanderValueTransformer<T>): T = valueTransformer(value ?: throw CommanderValueException("CommanderValue is null"))

    /**
     * Convert the value to a specific type (using a [CommanderValueTransformerLambda])
     * @param T the type of the value
     * @param valueTransformer the [CommanderValueTransformerLambda] to use
     * @return the transformed value
     * @see CommanderValueTransformer
     * @since 0.1.0
     * @version 0.1.0
     */
    fun <T> to(valueTransformer: CommanderValueTransformerLambda<T>): T = to(CommanderValueTransformer.of(valueTransformer))

    /**
     * Convert the value to a specific type (using a [CommanderValueTransformer]) or null
     * @param T the type of the value
     * @param valueTransformer the [CommanderValueTransformer] to use
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @since 0.1.0
     * @version 0.1.0
     */
    fun <T> toOrNull(valueTransformer: CommanderValueTransformer<T>): T? = valueTransformer.transformOrNull(value)

    /**
     * Convert the value to a specific type (using a [CommanderValueTransformerLambda]) or null
     * @param T the type of the value
     * @param valueTransformer the [CommanderValueTransformerLambda] to use
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @since 0.1.0
     * @version 0.1.0
     */
    fun <T> toOrNull(valueTransformer: CommanderValueTransformerLambda<T>): T? = CommanderValueTransformer.of(valueTransformer).transformOrNull(value)

    /**
     * Convert the value to a specific type (using a [CommanderValueTransformer]) or a default value
     * @param T the type of the value
     * @param valueTransformer the [CommanderValueTransformer] to use
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @since 0.1.0
     * @version 0.1.0
     */
    fun <T> toOrDefault(valueTransformer: CommanderValueTransformer<T>, default: T): T = valueTransformer.transformOrNull(value) ?: default

    /**
     * Convert the value to a specific type (using a [CommanderValueTransformerLambda]) or a default value
     * @param T the type of the value
     * @param valueTransformer the [CommanderValueTransformerLambda] to use
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @since 0.1.0
     * @version 0.1.0
     */
    fun <T> toOrDefault(valueTransformer: CommanderValueTransformerLambda<T>, default: T): T = CommanderValueTransformer.of(valueTransformer).transformOrNull(value) ?: default

    /**
     * Convert the value to a byte
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toByte() = to(CommanderValueTransformers.toByte)

    /**
     * Convert the value to a byte or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toByteOrNull() = toOrNull(CommanderValueTransformers.toByte)

    /**
     * Convert the value to a byte or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toByteOrDefault(default: Byte) = toOrDefault(CommanderValueTransformers.toByte, default)

    /**
     * Convert the value to a shorts
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toShort() = to(CommanderValueTransformers.toShort)

    /**
     * Convert the value to a shorts or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toShortOrNull() = toOrNull(CommanderValueTransformers.toShortOrNull)

    /**
     * Convert the value to a shorts or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toShortOrDefault(default: Short) = toOrDefault(CommanderValueTransformers.toShortOrNull, default)

    /**
     * Convert the value to an int
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toInt() = to(CommanderValueTransformers.toInt)

    /**
     * Convert the value to an int or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toIntOrNull() = toOrNull(CommanderValueTransformers.toIntOrNull)

    /**
     * Convert the value to an int or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toIntOrDefault(default: Int) = toOrDefault(CommanderValueTransformers.toIntOrNull, default)

    /**
     * Convert the value to a long
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toLong() = to(CommanderValueTransformers.toLong)

    /**
     * Convert the value to a long or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toLongOrNull() = toOrNull(CommanderValueTransformers.toLongOrNull)

    /**
     * Convert the value to a long or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toLongOrDefault(default: Long) = toOrDefault(CommanderValueTransformers.toLongOrNull, default)

    /**
     * Convert the value to a float
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toFloat() = to(CommanderValueTransformers.toFloat)

    /**
     * Convert the value to a float or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toFloatOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toFloatOrNull() = toOrNull(CommanderValueTransformers.toFloatOrNull)

    /**
     * Convert the value to a float or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toFloatOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toFloatOrDefault(default: Float) = toOrDefault(CommanderValueTransformers.toFloatOrNull, default)

    /**
     * Convert the value to a doubles
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toDouble
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toDouble() = to(CommanderValueTransformers.toDouble)

    /**
     * Convert the value to a doubles or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toDoubleOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toDoubleOrNull() = toOrNull(CommanderValueTransformers.toDoubleOrNull)

    /**
     * Convert the value to a doubles or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toDoubleOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toDoubleOrDefault(default: Double) = toOrDefault(CommanderValueTransformers.toDoubleOrNull, default)

    /**
     * Convert the value to an unsigned byte
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toUnsignedByte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toUnsignedByte() = to(CommanderValueTransformers.toUnsignedByte)

    /**
     * Convert the value to an unsigned byte or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toUnsignedByteOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toUnsignedByteOrNull() = toOrNull(CommanderValueTransformers.toUnsignedByteOrNull)

    /**
     * Convert the value to an unsigned byte or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toUnsignedByteOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toUnsignedByteOrDefault(default: UByte) = toOrDefault(CommanderValueTransformers.toUnsignedByteOrNull, default)

    /**
     * Convert the value to an unsigned shorts
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toUnsignedShort
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toUnsignedShort() = to(CommanderValueTransformers.toUnsignedShort)

    /**
     * Convert the value to an unsigned shorts or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toUnsignedShortOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toUnsignedShortOrNull() = toOrNull(CommanderValueTransformers.toUnsignedShortOrNull)

    /**
     * Convert the value to an unsigned shorts or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toUnsignedShortOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toUnsignedShortOrDefault(default: UShort) = toOrDefault(CommanderValueTransformers.toUnsignedShortOrNull, default)

    /**
     * Convert the value to an unsigned int
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toUnsignedInt
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toUnsignedInt() = to(CommanderValueTransformers.toUnsignedInt)

    /**
     * Convert the value to an unsigned int or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toUnsignedIntOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toUnsignedIntOrNull() = toOrNull(CommanderValueTransformers.toUnsignedIntOrNull)

    /**
     * Convert the value to an unsigned int or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toUnsignedIntOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toUnsignedIntOrDefault(default: UInt) = toOrDefault(CommanderValueTransformers.toUnsignedIntOrNull, default)

    /**
     * Convert the value to an unsigned long
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toUnsignedLong
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toUnsignedLong() = to(CommanderValueTransformers.toUnsignedLong)

    /**
     * Convert the value to an unsigned long or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toUnsignedLongOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toUnsignedLongOrNull() = toOrNull(CommanderValueTransformers.toUnsignedLongOrNull)

    /**
     * Convert the value to an unsigned long or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toUnsignedLongOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toUnsignedLongOrDefault(default: ULong) = toOrDefault(CommanderValueTransformers.toUnsignedLongOrNull, default)

    /**
     * Convert the value to a boolean
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toBoolean
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toBoolean() = to(CommanderValueTransformers.toBoolean)

    /**
     * Convert the value to a boolean or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toBooleanOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toBooleanOrNull() = toOrNull(CommanderValueTransformers.toBooleanOrNull)

    /**
     * Convert the value to a boolean or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toBooleanOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toBooleanOrDefault(default: Boolean) = toOrDefault(CommanderValueTransformers.toBooleanOrNull, default)

    /**
     * Convert the value to a char
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toChar
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toChar() = to(CommanderValueTransformers.toChar)

    /**
     * Convert the value to a char or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toCharOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toCharOrNull() = toOrNull(CommanderValueTransformers.toCharOrNull)

    /**
     * Convert the value to a char or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toCharOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toCharOrDefault(default: Char) = toOrDefault(CommanderValueTransformers.toCharOrNull, default)

    /**
     * Convert the value to a string
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toString
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun toString() = to(CommanderValueTransformers.toString)

    /**
     * Convert the value to a string or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toStringOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toStringOrNull() = toOrNull(CommanderValueTransformers.toStringOrNull)

    /**
     * Convert the value to a string or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toStringOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toStringOrDefault(default: String) = toOrDefault(CommanderValueTransformers.toStringOrNull, default)

    /**
     * Convert the value to a positive byte
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toPositiveByte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveByte() = to(CommanderValueTransformers.toPositiveByte)

    /**
     * Convert the value to a positive byte or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toPositiveByteOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveByteOrNull() = toOrNull(CommanderValueTransformers.toPositiveByteOrNull)

    /**
     * Convert the value to a positive byte or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toPositiveByteOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveByteOrDefault(default: Byte) = toOrDefault(CommanderValueTransformers.toPositiveByteOrNull, default)

    /**
     * Convert the value to a positive shorts
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toPositiveShort
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveShort() = to(CommanderValueTransformers.toPositiveShort)

    /**
     * Convert the value to a positive shorts or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toPositiveShortOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveShortOrNull() = toOrNull(CommanderValueTransformers.toPositiveShortOrNull)

    /**
     * Convert the value to a positive shorts or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toPositiveShortOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveShortOrDefault(default: Short) = toOrDefault(CommanderValueTransformers.toPositiveShortOrNull, default)

    /**
     * Convert the value to a positive int
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toPositiveInt
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveInt() = to(CommanderValueTransformers.toPositiveInt)

    /**
     * Convert the value to a positive int or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toPositiveIntOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveIntOrNull() = toOrNull(CommanderValueTransformers.toPositiveIntOrNull)

    /**
     * Convert the value to a positive int or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toPositiveIntOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveIntOrDefault(default: Int) = toOrDefault(CommanderValueTransformers.toPositiveIntOrNull, default)

    /**
     * Convert the value to a positive long
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toPositiveLong
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveLong() = to(CommanderValueTransformers.toPositiveLong)

    /**
     * Convert the value to a positive long or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toPositiveLongOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveLongOrNull() = toOrNull(CommanderValueTransformers.toPositiveLongOrNull)

    /**
     * Convert the value to a positive long or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toPositiveLongOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveLongOrDefault(default: Long) = toOrDefault(CommanderValueTransformers.toPositiveLongOrNull, default)

    /**
     * Convert the value to a positive float
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toPositiveFloat
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveFloat() = to(CommanderValueTransformers.toPositiveFloat)

    /**
     * Convert the value to a positive float or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toPositiveFloatOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveFloatOrNull() = toOrNull(CommanderValueTransformers.toPositiveFloatOrNull)

    /**
     * Convert the value to a positive float or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toPositiveFloatOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveFloatOrDefault(default: Float) = toOrDefault(CommanderValueTransformers.toPositiveFloatOrNull, default)

    /**
     * Convert the value to a positive doubles
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toPositiveDouble
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveDouble() = to(CommanderValueTransformers.toPositiveDouble)

    /**
     * Convert the value to a positive doubles or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toPositiveDoubleOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveDoubleOrNull() = toOrNull(CommanderValueTransformers.toPositiveDoubleOrNull)

    /**
     * Convert the value to a positive doubles or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toPositiveDoubleOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveDoubleOrDefault(default: Double) = toOrDefault(CommanderValueTransformers.toPositiveDoubleOrNull, default)

    /**
     * Convert the value to a negative byte
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toNegativeByte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeByte() = to(CommanderValueTransformers.toNegativeByte)

    /**
     * Convert the value to a negative byte or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toNegativeByteOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeByteOrNull() = toOrNull(CommanderValueTransformers.toNegativeByteOrNull)

    /**
     * Convert the value to a negative byte or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toNegativeByteOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeByteOrDefault(default: Byte) = toOrDefault(CommanderValueTransformers.toNegativeByteOrNull, default)

    /**
     * Convert the value to a negative shorts
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toNegativeShort
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeShort() = to(CommanderValueTransformers.toNegativeShort)

    /**
     * Convert the value to a negative shorts or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toNegativeShortOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeShortOrNull() = toOrNull(CommanderValueTransformers.toNegativeShortOrNull)

    /**
     * Convert the value to a negative shorts or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toNegativeShortOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeShortOrDefault(default: Short) = toOrDefault(CommanderValueTransformers.toNegativeShortOrNull, default)

    /**
     * Convert the value to a negative int
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toNegativeInt
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeInt() = to(CommanderValueTransformers.toNegativeInt)

    /**
     * Convert the value to a negative int or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toNegativeIntOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeIntOrNull() = toOrNull(CommanderValueTransformers.toNegativeIntOrNull)

    /**
     * Convert the value to a negative int or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toNegativeIntOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeIntOrDefault(default: Int) = toOrDefault(CommanderValueTransformers.toNegativeIntOrNull, default)

    /**
     * Convert the value to a negative long
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toNegativeLong
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeLong() = to(CommanderValueTransformers.toNegativeLong)

    /**
     * Convert the value to a negative long or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toNegativeLongOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeLongOrNull() = toOrNull(CommanderValueTransformers.toNegativeLongOrNull)

    /**
     * Convert the value to a negative long or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toNegativeLongOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeLongOrDefault(default: Long) = toOrDefault(CommanderValueTransformers.toNegativeLongOrNull, default)

    /**
     * Convert the value to a negative float
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toNegativeFloat
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeFloat() = to(CommanderValueTransformers.toNegativeFloat)

    /**
     * Convert the value to a negative float or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toNegativeFloatOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeFloatOrNull() = toOrNull(CommanderValueTransformers.toNegativeFloatOrNull)

    /**
     * Convert the value to a negative float or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toNegativeFloatOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeFloatOrDefault(default: Float) = toOrDefault(CommanderValueTransformers.toNegativeFloatOrNull, default)

    /**
     * Convert the value to a negative doubles
     * @return the transformed value
     * @throws CommanderValueException if the value could not be transformed
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toNegativeDouble
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeDouble() = to(CommanderValueTransformers.toNegativeDouble)

    /**
     * Convert the value to a negative doubles or null
     * @return the transformed value or null
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toNegativeDoubleOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeDoubleOrNull() = toOrNull(CommanderValueTransformers.toNegativeDoubleOrNull)

    /**
     * Convert the value to a negative doubles or a default value
     * @param default the default value
     * @return the transformed value or the default value
     * @see CommanderValueTransformer
     * @see CommanderValueTransformers.toNegativeDoubleOrNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeDoubleOrDefault(default: Double) = toOrDefault(CommanderValueTransformers.toNegativeDoubleOrNull, default)

    /**
     * Validate the value using a [CommanderValueValidator]
     * @param validator the [CommanderValueValidator] to use
     * @throws CommanderValueException if the value is not valid
     * @see CommanderValueValidator
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validate(validator: CommanderValueValidator) = validator.validate(value)

    /**
     * Validate the value using a [CommanderValueValidatorLambda]
     * @param validator the [CommanderValueValidatorLambda] to use
     * @throws CommanderValueException if the value is not valid
     * @see CommanderValueValidator
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validate(validator: CommanderValueValidatorLambda) = CommanderValueValidator.of(validator).validate(value)

    /**
     * Validate the value using a [CommanderValueValidator] to a boolean
     * @param validator the [CommanderValueValidator] to use
     * @return true if the value is valid, false otherwise
     * @see CommanderValueValidator
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isValid(validator: CommanderValueValidator) = validator.accepts(value)

    /**
     * Validate the value using a [CommanderValueValidatorLambda] to a boolean
     * @param validator the [CommanderValueValidatorLambda] to use
     * @return true if the value is valid, false otherwise
     * @see CommanderValueValidator
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isValid(validator: CommanderValueValidatorLambda) = CommanderValueValidator.of(validator).accepts(value)

    /**
     * Validate the value using a [CommanderValueValidator] to a boolean
     * @param validator the [CommanderValueValidator] to use
     * @return true if the value is valid, false otherwise
     * @see CommanderValueValidator
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isInvalid(validator: CommanderValueValidator) = !isValid(validator)

    /**
     * Validate the value using a [CommanderValueValidatorLambda] to a boolean
     * @param validator the [CommanderValueValidatorLambda] to use
     * @return true if the value is valid, false otherwise
     * @see CommanderValueValidator
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isInvalid(validator: CommanderValueValidatorLambda) = !isValid(validator)

    /**
     * Validate the value to be a byte
     * @throws CommanderValueException if the value is not a byte
     * @see CommanderValueValidators.isByte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateByte() = validate(CommanderValueValidators.isByte)

    /**
     * Is the value a byte?
     * @return true if the value is a byte, false otherwise
     * @see CommanderValueValidators.isByte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isByte() = isValid(CommanderValueValidators.isByte)

    /**
     * Is the value not a byte?
     * @return true if the value is not a byte, false otherwise
     * @see CommanderValueValidators.isByte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotByte() = isInvalid(CommanderValueValidators.isByte)

    /**
     * Validate the value to be a shorts
     * @throws CommanderValueException if the value is not a shorts
     * @see CommanderValueValidators.isShort
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateShort() = validate(CommanderValueValidators.isShort)

    /**
     * Is the value a shorts?
     * @return true if the value is a shorts, false otherwise
     * @see CommanderValueValidators.isShort
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isShort() = isValid(CommanderValueValidators.isShort)

    /**
     * Is the value not a shorts?
     * @return true if the value is not a shorts, false otherwise
     * @see CommanderValueValidators.isShort
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotShort() = isInvalid(CommanderValueValidators.isShort)

    /**
     * Validate the value to be an int
     * @throws CommanderValueException if the value is not an int
     * @see CommanderValueValidators.isInt
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateInt() = validate(CommanderValueValidators.isInt)

    /**
     * Is the value an int?
     * @return true if the value is an int, false otherwise
     * @see CommanderValueValidators.isInt
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isInt() = isValid(CommanderValueValidators.isInt)

    /**
     * Is the value not an int?
     * @return true if the value is not an int, false otherwise
     * @see CommanderValueValidators.isInt
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotInt() = isInvalid(CommanderValueValidators.isInt)

    /**
     * Validate the value to be a long
     * @throws CommanderValueException if the value is not a long
     * @see CommanderValueValidators.isLong
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateLong() = validate(CommanderValueValidators.isLong)

    /**
     * Is the value a long?
     * @return true if the value is a long, false otherwise
     * @see CommanderValueValidators.isLong
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isLong() = isValid(CommanderValueValidators.isLong)

    /**
     * Is the value not a long?
     * @return true if the value is not a long, false otherwise
     * @see CommanderValueValidators.isLong
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotLong() = isInvalid(CommanderValueValidators.isLong)

    /**
     * Validate the value to be a float
     * @throws CommanderValueException if the value is not a float
     * @see CommanderValueValidators.isFloat
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateFloat() = validate(CommanderValueValidators.isFloat)

    /**
     * Is the value a float?
     * @return true if the value is a float, false otherwise
     * @see CommanderValueValidators.isFloat
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isFloat() = isValid(CommanderValueValidators.isFloat)

    /**
     * Is the value not a float?
     * @return true if the value is not a float, false otherwise
     * @see CommanderValueValidators.isFloat
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotFloat() = isInvalid(CommanderValueValidators.isFloat)

    /**
     * Validate the value to be a doubles
     * @throws CommanderValueException if the value is not a doubles
     * @see CommanderValueValidators.isDouble
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateDouble() = validate(CommanderValueValidators.isDouble)

    /**
     * Is the value a doubles?
     * @return true if the value is a doubles, false otherwise
     * @see CommanderValueValidators.isDouble
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isDouble() = isValid(CommanderValueValidators.isDouble)

    /**
     * Is the value not a doubles?
     * @return true if the value is not a doubles, false otherwise
     * @see CommanderValueValidators.isDouble
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotDouble() = isInvalid(CommanderValueValidators.isDouble)

    /**
     * Validate the value to be an unsigned byte
     * @throws CommanderValueException if the value is not an unsigned byte
     * @see CommanderValueValidators.isUnsignedByte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateUnsignedByte() = validate(CommanderValueValidators.isUnsignedByte)

    /**
     * Is the value an unsigned byte?
     * @return true if the value is an unsigned byte, false otherwise
     * @see CommanderValueValidators.isUnsignedByte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isUnsignedByte() = isValid(CommanderValueValidators.isUnsignedByte)

    /**
     * Is the value not an unsigned byte?
     * @return true if the value is not an unsigned byte, false otherwise
     * @see CommanderValueValidators.isUnsignedByte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotUnsignedByte() = isInvalid(CommanderValueValidators.isUnsignedByte)

    /**
     * Validate the value to be an unsigned shorts
     * @throws CommanderValueException if the value is not an unsigned shorts
     * @see CommanderValueValidators.isUnsignedShort
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateUnsignedShort() = validate(CommanderValueValidators.isUnsignedShort)

    /**
     * Is the value an unsigned shorts?
     * @return true if the value is an unsigned shorts, false otherwise
     * @see CommanderValueValidators.isUnsignedShort
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isUnsignedShort() = isValid(CommanderValueValidators.isUnsignedShort)

    /**
     * Is the value not an unsigned shorts?
     * @return true if the value is not an unsigned shorts, false otherwise
     * @see CommanderValueValidators.isUnsignedShort
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotUnsignedShort() = isInvalid(CommanderValueValidators.isUnsignedShort)

    /**
     * Validate the value to be an unsigned int
     * @throws CommanderValueException if the value is not an unsigned int
     * @see CommanderValueValidators.isUnsignedInt
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateUnsignedInt() = validate(CommanderValueValidators.isUnsignedInt)

    /**
     * Is the value an unsigned int?
     * @return true if the value is an unsigned int, false otherwise
     * @see CommanderValueValidators.isUnsignedInt
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isUnsignedInt() = isValid(CommanderValueValidators.isUnsignedInt)

    /**
     * Is the value not an unsigned int?
     * @return true if the value is not an unsigned int, false otherwise
     * @see CommanderValueValidators.isUnsignedInt
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotUnsignedInt() = isInvalid(CommanderValueValidators.isUnsignedInt)

    /**
     * Validate the value to be an unsigned long
     * @throws CommanderValueException if the value is not an unsigned long
     * @see CommanderValueValidators.isUnsignedLong
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateUnsignedLong() = validate(CommanderValueValidators.isUnsignedLong)

    /**
     * Is the value an unsigned long?
     * @return true if the value is an unsigned long, false otherwise
     * @see CommanderValueValidators.isUnsignedLong
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isUnsignedLong() = isValid(CommanderValueValidators.isUnsignedLong)

    /**
     * Is the value not an unsigned long?
     * @return true if the value is not an unsigned long, false otherwise
     * @see CommanderValueValidators.isUnsignedLong
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotUnsignedLong() = isInvalid(CommanderValueValidators.isUnsignedLong)

    /**
     * Validate the value to be a boolean
     * @throws CommanderValueException if the value is not a boolean
     * @see CommanderValueValidators.isBoolean
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateBoolean() = validate(CommanderValueValidators.isBoolean)

    /**
     * Is the value a boolean?
     * @return true if the value is a boolean, false otherwise
     * @see CommanderValueValidators.isBoolean
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isBoolean() = isValid(CommanderValueValidators.isBoolean)

    /**
     * Is the value not a boolean?
     * @return true if the value is not a boolean, false otherwise
     * @see CommanderValueValidators.isBoolean
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotBoolean() = isInvalid(CommanderValueValidators.isBoolean)

    /**
     * Validate the value to be a char
     * @throws CommanderValueException if the value is not a char
     * @see CommanderValueValidators.isChar
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateChar() = validate(CommanderValueValidators.isChar)

    /**
     * Is the value a char?
     * @return true if the value is a char, false otherwise
     * @see CommanderValueValidators.isChar
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isChar() = isValid(CommanderValueValidators.isChar)

    /**
     * Is the value not a char?
     * @return true if the value is not a char, false otherwise
     * @see CommanderValueValidators.isChar
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotChar() = isInvalid(CommanderValueValidators.isChar)

    /**
     * Validate the value to be a string
     * @throws CommanderValueException if the value is not a string
     * @see CommanderValueValidators.isString
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateString() = validate(CommanderValueValidators.isString)

    /**
     * Is the value a string?
     * @return true if the value is a string, false otherwise
     * @see CommanderValueValidators.isString
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isString() = isValid(CommanderValueValidators.isString)

    /**
     * Is the value not a string?
     * @return true if the value is not a string, false otherwise
     * @see CommanderValueValidators.isString
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotString() = isInvalid(CommanderValueValidators.isString)

    /**
     * Validate the value to be a positive byte
     * @throws CommanderValueException if the value is not a positive byte
     * @see CommanderValueValidators.isPositiveByte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validatePositiveByte() = validate(CommanderValueValidators.isPositiveByte)

    /**
     * Is the value a positive byte?
     * @return true if the value is a positive byte, false otherwise
     * @see CommanderValueValidators.isPositiveByte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isPositiveByte() = isValid(CommanderValueValidators.isPositiveByte)

    /**
     * Is the value not a positive byte?
     * @return true if the value is not a positive byte, false otherwise
     * @see CommanderValueValidators.isPositiveByte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotPositiveByte() = isInvalid(CommanderValueValidators.isPositiveByte)

    /**
     * Validate the value to be a positive shorts
     * @throws CommanderValueException if the value is not a positive shorts
     * @see CommanderValueValidators.isPositiveShort
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validatePositiveShort() = validate(CommanderValueValidators.isPositiveShort)

    /**
     * Is the value a positive shorts?
     * @return true if the value is a positive shorts, false otherwise
     * @see CommanderValueValidators.isPositiveShort
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isPositiveShort() = isValid(CommanderValueValidators.isPositiveShort)

    /**
     * Is the value not a positive shorts?
     * @return true if the value is not a positive shorts, false otherwise
     * @see CommanderValueValidators.isPositiveShort
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotPositiveShort() = isInvalid(CommanderValueValidators.isPositiveShort)

    /**
     * Validate the value to be a positive int
     * @throws CommanderValueException if the value is not a positive int
     * @see CommanderValueValidators.isPositiveInt
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validatePositiveInt() = validate(CommanderValueValidators.isPositiveInt)

    /**
     * Is the value a positive int?
     * @return true if the value is a positive int, false otherwise
     * @see CommanderValueValidators.isPositiveInt
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isPositiveInt() = isValid(CommanderValueValidators.isPositiveInt)

    /**
     * Is the value not a positive int?
     * @return true if the value is not a positive int, false otherwise
     * @see CommanderValueValidators.isPositiveInt
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotPositiveInt() = isInvalid(CommanderValueValidators.isPositiveInt)

    /**
     * Validate the value to be a positive long
     * @throws CommanderValueException if the value is not a positive long
     * @see CommanderValueValidators.isPositiveLong
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validatePositiveLong() = validate(CommanderValueValidators.isPositiveLong)

    /**
     * Is the value a positive long?
     * @return true if the value is a positive long, false otherwise
     * @see CommanderValueValidators.isPositiveLong
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isPositiveLong() = isValid(CommanderValueValidators.isPositiveLong)

    /**
     * Is the value not a positive long?
     * @return true if the value is not a positive long, false otherwise
     * @see CommanderValueValidators.isPositiveLong
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotPositiveLong() = isInvalid(CommanderValueValidators.isPositiveLong)

    /**
     * Validate the value to be a positive float
     * @throws CommanderValueException if the value is not a positive float
     * @see CommanderValueValidators.isPositiveFloat
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validatePositiveFloat() = validate(CommanderValueValidators.isPositiveFloat)

    /**
     * Is the value a positive float?
     * @return true if the value is a positive float, false otherwise
     * @see CommanderValueValidators.isPositiveFloat
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isPositiveFloat() = isValid(CommanderValueValidators.isPositiveFloat)

    /**
     * Is the value not a positive float?
     * @return true if the value is not a positive float, false otherwise
     * @see CommanderValueValidators.isPositiveFloat
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotPositiveFloat() = isInvalid(CommanderValueValidators.isPositiveFloat)

    /**
     * Validate the value to be a positive doubles
     * @throws CommanderValueException if the value is not a positive doubles
     * @see CommanderValueValidators.isPositiveDouble
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validatePositiveDouble() = validate(CommanderValueValidators.isPositiveDouble)

    /**
     * Is the value a positive doubles?
     * @return true if the value is a positive doubles, false otherwise
     * @see CommanderValueValidators.isPositiveDouble
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isPositiveDouble() = isValid(CommanderValueValidators.isPositiveDouble)

    /**
     * Is the value not a positive doubles?
     * @return true if the value is not a positive doubles, false otherwise
     * @see CommanderValueValidators.isPositiveDouble
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotPositiveDouble() = isInvalid(CommanderValueValidators.isPositiveDouble)

    /**
     * Validate the value to be a negative byte
     * @throws CommanderValueException if the value is not a negative byte
     * @see CommanderValueValidators.isNegativeByte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateNegativeByte() = validate(CommanderValueValidators.isNegativeByte)

    /**
     * Is the value a negative byte?
     * @return true if the value is a negative byte, false otherwise
     * @see CommanderValueValidators.isNegativeByte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNegativeByte() = isValid(CommanderValueValidators.isNegativeByte)

    /**
     * Is the value not a negative byte?
     * @return true if the value is not a negative byte, false otherwise
     * @see CommanderValueValidators.isNegativeByte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotNegativeByte() = isInvalid(CommanderValueValidators.isNegativeByte)

    /**
     * Validate the value to be a negative shorts
     * @throws CommanderValueException if the value is not a negative shorts
     * @see CommanderValueValidators.isNegativeShort
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateNegativeShort() = validate(CommanderValueValidators.isNegativeShort)

    /**
     * Is the value a negative shorts?
     * @return true if the value is a negative shorts, false otherwise
     * @see CommanderValueValidators.isNegativeShort
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNegativeShort() = isValid(CommanderValueValidators.isNegativeShort)

    /**
     * Is the value not a negative shorts?
     * @return true if the value is not a negative shorts, false otherwise
     * @see CommanderValueValidators.isNegativeShort
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotNegativeShort() = isInvalid(CommanderValueValidators.isNegativeShort)

    /**
     * Validate the value to be a negative int
     * @throws CommanderValueException if the value is not a negative int
     * @see CommanderValueValidators.isNegativeInt
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateNegativeInt() = validate(CommanderValueValidators.isNegativeInt)

    /**
     * Is the value a negative int?
     * @return true if the value is a negative int, false otherwise
     * @see CommanderValueValidators.isNegativeInt
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNegativeInt() = isValid(CommanderValueValidators.isNegativeInt)

    /**
     * Is the value not a negative int?
     * @return true if the value is not a negative int, false otherwise
     * @see CommanderValueValidators.isNegativeInt
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotNegativeInt() = isInvalid(CommanderValueValidators.isNegativeInt)

    /**
     * Validate the value to be a negative long
     * @throws CommanderValueException if the value is not a negative long
     * @see CommanderValueValidators.isNegativeLong
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateNegativeLong() = validate(CommanderValueValidators.isNegativeLong)

    /**
     * Is the value a negative long?
     * @return true if the value is a negative long, false otherwise
     * @see CommanderValueValidators.isNegativeLong
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNegativeLong() = isValid(CommanderValueValidators.isNegativeLong)

    /**
     * Is the value not a negative long?
     * @return true if the value is not a negative long, false otherwise
     * @see CommanderValueValidators.isNegativeLong
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotNegativeLong() = isInvalid(CommanderValueValidators.isNegativeLong)

    /**
     * Validate the value to be a negative float
     * @throws CommanderValueException if the value is not a negative float
     * @see CommanderValueValidators.isNegativeFloat
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateNegativeFloat() = validate(CommanderValueValidators.isNegativeFloat)

    /**
     * Is the value a negative float?
     * @return true if the value is a negative float, false otherwise
     * @see CommanderValueValidators.isNegativeFloat
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNegativeFloat() = isValid(CommanderValueValidators.isNegativeFloat)

    /**
     * Is the value not a negative float?
     * @return true if the value is not a negative float, false otherwise
     * @see CommanderValueValidators.isNegativeFloat
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotNegativeFloat() = isInvalid(CommanderValueValidators.isNegativeFloat)

    /**
     * Validate the value to be a negative doubles
     * @throws CommanderValueException if the value is not a negative doubles
     * @see CommanderValueValidators.isNegativeDouble
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateNegativeDouble() = validate(CommanderValueValidators.isNegativeDouble)

    /**
     * Is the value a negative doubles?
     * @return true if the value is a negative doubles, false otherwise
     * @see CommanderValueValidators.isNegativeDouble
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNegativeDouble() = isValid(CommanderValueValidators.isNegativeDouble)

    /**
     * Is the value not a negative doubles?
     * @return true if the value is not a negative doubles, false otherwise
     * @see CommanderValueValidators.isNegativeDouble
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotNegativeDouble() = isInvalid(CommanderValueValidators.isNegativeDouble)

    /**
     * Validate the value to be null
     * @throws CommanderValueException if the value is not null
     * @see CommanderValueValidators.isNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateNull() = validate(CommanderValueValidators.isNull)

    /**
     * Validate the value not to be null
     * @throws CommanderValueException if the value is null
     * @see CommanderValueValidators.isNotNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun validateNotNull() = validate(CommanderValueValidators.isNotNull)

    /**
     * Is the value null?
     * @return true if the value is null, false otherwise
     * @see CommanderValueValidators.isNotNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNull() = isValid(CommanderValueValidators.isNull)

    /**
     * Is the value not null?
     * @return true if the value is not null, false otherwise
     * @see CommanderValueValidators.isNotNull
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotNull() = isValid(CommanderValueValidators.isNotNull)
}
