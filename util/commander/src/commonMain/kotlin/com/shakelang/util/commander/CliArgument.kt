package com.shakelang.util.commander

/**
 * A class representing an argument of a [CliCommand]
 * @param command The [CliCommand] this argument belongs to
 * @param name The name of the argument
 * @param description The description of the argument
 * @param required If the argument is required
 * @param defaultValue The default value of the argument
 * @param valueName The name of the argument's value
 * @param valueDescription The description of the argument's value
 * @param valueValidator The [ValueValidator] of the value of the argument
 * @see CliCommand
 * @see ValueValidator
 *
 * @since 0.1.0
 * @version 0.1.0
 */
class CliArgument(

    /**
     * The [CliCommand] this argument belongs to
     * @since 0.1.0
     * @version 0.1.0
     */
    val command: CliCommand,

    /**
     * The name of the argument
     * @since 0.1.0
     * @version 0.1.0
     */
    val name: String,

    /**
     * The description of the argument
     * @since 0.1.0
     * @version 0.1.0
     */
    val description: String? = null,

    /**
     * If the argument is required
     * @since 0.1.0
     * @version 0.1.0
     */
    val required: Boolean = false,

    /**
     * The default value of the argument
     * @since 0.1.0
     * @version 0.1.0
     */
    val defaultValue: String? = null,

    /**
     * The name of the argument's value
     * @since 0.1.0
     * @version 0.1.0
     */
    val valueName: String? = null,

    /**
     * The description of the argument's value
     * @since 0.1.0
     * @version 0.1.0
     */
    val valueDescription: String? = null,

    /**
     * The [ValueValidator] of the value of the argument
     * @since 0.1.0
     * @version 0.1.0
     */
    val valueValidator: ValueValidator? = null,
) {

    /**
     * Get the usage of the argument
     * @return The usage of the argument
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getUsage(): String {
        val builder = StringBuilder()
        builder.append("<$name>")
        if (valueName != null) builder.append(" <$valueName>")
        return builder.toString()
    }
}

/**
 * A class representing a context for creating a [CliArgument]
 * @since 0.1.0
 * @version 0.1.0
 */
class CliArgumentCreationContext {

    /**
     * The name of the argument
     * @since 0.1.0
     * @version 0.1.0
     */
    lateinit var name: String

    /**
     * The description of the argument
     * @since 0.1.0
     * @version 0.1.0
     */
    var description: String? = null

    /**
     * If the argument is required
     * @since 0.1.0
     * @version 0.1.0
     */
    var required: Boolean = false

    /**
     * The default value of the argument
     * @since 0.1.0
     * @version 0.1.0
     */
    var defaultValue: String? = null

    /**
     * The name of the argument's value
     * @since 0.1.0
     * @version 0.1.0
     */
    var valueName: String? = null

    /**
     * The description of the argument's value
     * @since 0.1.0
     * @version 0.1.0
     */
    var valueDescription: String? = null

    /**
     * The [ValueValidator] of the value of the argument
     * @since 0.1.0
     * @version 0.1.0
     */
    var valueValidator: ValueValidator? = null

    /**
     * Add description to the argument
     * @param description The description to add
     * @since 0.1.0
     * @version 0.1.0
     */
    fun description(description: String) {
        this.description = (this.description?.plus("\n") ?: "") + description
    }

    /**
     * Set the default value of the argument
     * @param required If the argument is required
     * @since 0.1.0
     * @version 0.1.0
     */
    fun required(required: Boolean = true) {
        this.required = required
    }

    /**
     * Generate the [CliArgument] from the context
     * @param command The [CliCommand] this argument belongs to
     * @return The generated [CliArgument]
     * @since 0.1.0
     * @version 0.1.0
     */
    fun generate(command: CliCommand): CliArgument {
        if (!::name.isInitialized) throw IllegalStateException("Name is not initialized")

        return CliArgument(
            command,
            name,
            description,
            required,
            defaultValue,
            valueName,
            valueDescription,
            valueValidator,
        )
    }
}

/**
 * Init function for [CliArgument]
 * @since 0.1.0
 * @version 0.1.0
 */
typealias CliArgumentInit = CliArgumentCreationContext.() -> Unit
