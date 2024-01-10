package com.shakelang.util.commander

/**
 * A class representing an argument of a [CommanderCommand]
 * @param command The [CommanderCommand] this argument belongs to
 * @param name The name of the argument
 * @param description The description of the argument
 * @param required If the argument is required
 * @param defaultValue The default value of the argument
 * @param valueName The name of the argument's value
 * @param valueDescription The description of the argument's value
 * @param valueValidator The [CommanderValueValidator] of the value of the argument
 * @see CommanderCommand
 * @see CommanderValueValidator
 *
 * @since 0.1.0
 * @version 0.1.0
 */
class CommanderArgument(

    /**
     * The [CommanderCommand] this argument belongs to
     * @since 0.1.0
     * @version 0.1.0
     */
    val command: CommanderCommand,

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
     * The [CommanderValueValidator] of the value of the argument
     * @since 0.1.0
     * @version 0.1.0
     */
    val valueValidator: CommanderValueValidator? = null,
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
 * A class representing a context for creating a [CommanderArgument]
 * @since 0.1.0
 * @version 0.1.0
 */
class CommanderArgumentCreationContext {

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
     * The [CommanderValueValidator] of the value of the argument
     * @since 0.1.0
     * @version 0.1.0
     */
    var valueValidator: CommanderValueValidator? = null

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
     * Generate the [CommanderArgument] from the context
     * @param command The [CommanderCommand] this argument belongs to
     * @return The generated [CommanderArgument]
     * @since 0.1.0
     * @version 0.1.0
     */
    fun generate(command: CommanderCommand): CommanderArgument {
        if (!::name.isInitialized) throw IllegalStateException("Name is not initialized")

        return CommanderArgument(
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
 * Init function for [CommanderArgument]
 * @since 0.1.0
 * @version 0.1.0
 */
typealias CliArgumentInit = CommanderArgumentCreationContext.() -> Unit
