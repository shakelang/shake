package com.shakelang.util.commander

/**
 * A class representing a command line option
 * @param command The command this option belongs to
 * @param name The name of the option
 * @param aliases The aliases of the option
 * @param shortAlias The shorts aliases of the option
 * @param description The description of the option
 * @param required If the option is required
 * @param hasValue If the option has a value
 * @param defaultValue The default value of the option
 * @param valueName The name of the value
 * @param valueDescription The description of the value
 * @param valueValidator The validator of the value
 * @constructor Creates a new [CommanderOption]
 * @see CommanderCommand
 * @see CommanderArgument
 */
class CommanderOption(

    /**
     * The command this option belongs to
     * @since 0.1.0
     * @version 0.1.0
     */
    val command: CommanderCommand,

    /**
     * The name of the option
     * @since 0.1.0
     * @version 0.1.0
     */
    val name: String,

    /**
     * The aliases of the option
     * @since 0.1.0
     * @version 0.1.0
     */
    val aliases: Array<String> = arrayOf(),

    /**
     * The shorts aliases of the option
     * @since 0.1.0
     * @version 0.1.0
     */
    val shortAlias: Array<String> = arrayOf(),

    /**
     * The description of the option
     * @since 0.1.0
     * @version 0.1.0
     */
    val description: String? = null,

    /**
     * If the option is required
     * @since 0.1.0
     * @version 0.1.0
     */
    val required: Boolean = false,

    /**
     * If the option has a value
     * @since 0.1.0
     * @version 0.1.0
     */
    val hasValue: Boolean = false,

    /**
     * The default value of the option
     * @since 0.1.0
     * @version 0.1.0
     */
    val defaultValue: String? = null,

    /**
     * The name of the value
     * @since 0.1.0
     * @version 0.1.0
     */
    val valueName: String? = null,

    /**
     * The description of the value
     * @since 0.1.0
     * @version 0.1.0
     */
    val valueDescription: String? = null,

    /**
     * The validator of the value
     * @since 0.1.0
     * @version 0.1.0
     */
    val valueValidator: CommanderValueValidator? = null,
) {

    /**
     * Get the usage of the option
     * @return The usage of the option
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getUsage(): String {
        val builder = StringBuilder()
        builder.append("--$name")
        if (shortAlias.isNotEmpty() || aliases.isNotEmpty()) {
            val aliases = arrayOf(
                *shortAlias.map { "-$it" }.toTypedArray(),
                *aliases.map { "--$it" }.toTypedArray(),
            )
            builder.append(" (", aliases.joinToString(", "), ")")
        }
        if (valueName != null) builder.append(" <$valueName>")
        return builder.toString()
    }
}

/**
 * A class representing a context for creating a [CommanderOption]
 * @constructor Creates a new [CommanderOptionCreationContext]
 * @since 0.1.0
 * @version 0.1.0
 */
class CommanderOptionCreationContext {

    /**
     * The name of the option
     * @since 0.1.0
     * @version 0.1.0
     */
    lateinit var name: String

    /**
     * The aliases of the option
     * @since 0.1.0
     * @version 0.1.0
     */
    val aliases: MutableList<String> = mutableListOf()

    /**
     * The shorts aliases of the option
     * @since 0.1.0
     * @version 0.1.0
     */
    val shortAliases: MutableList<String> = mutableListOf()

    /**
     * The description of the option
     * @since 0.1.0
     * @version 0.1.0
     */
    var description: String? = null

    /**
     * If the option is required
     * @since 0.1.0
     * @version 0.1.0
     */
    var required: Boolean = false

    /**
     * If the option has a value
     * @since 0.1.0
     * @version 0.1.0
     */
    var hasValue: Boolean = false

    /**
     * The default value of the option
     * @since 0.1.0
     * @version 0.1.0
     */
    var defaultValue: String? = null

    /**
     * The name of the value
     * @since 0.1.0
     * @version 0.1.0
     */
    var valueName: String? = null

    /**
     * The description of the value
     * @since 0.1.0
     * @version 0.1.0
     */
    var valueDescription: String? = null

    /**
     * The validator of the value
     * @since 0.1.0
     * @version 0.1.0
     */
    var valueValidator: CommanderValueValidator? = null

    /**
     * Add aliases to the option
     * @param aliases The aliases to add
     * @since 0.1.0
     * @version 0.1.0
     */
    fun alias(vararg aliases: String) {
        this.aliases.addAll(aliases)
    }

    /**
     * Add shorts aliases to the option
     * @param aliases The shorts aliases to add
     * @since 0.1.0
     * @version 0.1.0
     */
    fun shortAlias(vararg aliases: String) {
        this.shortAliases.addAll(aliases)
    }

    /**
     * Set the description of the option
     * @param description The description to set
     * @since 0.1.0
     * @version 0.1.0
     */
    fun description(description: String) {
        this.description = (this.description?.plus("\n") ?: "") + description
    }

    /**
     * Set the default value of the option
     * @param defaultValue The default value to set
     * @since 0.1.0
     * @version 0.1.0
     */
    fun required(required: Boolean = true) {
        this.required = required
    }

    /**
     * Set the default value of the option
     * @param defaultValue The default value to set
     * @since 0.1.0
     * @version 0.1.0
     */
    fun generate(command: CommanderCommand): CommanderOption {
        if (!::name.isInitialized) throw IllegalStateException("Name is not initialized")

        return CommanderOption(
            command,
            name,
            aliases.toTypedArray(),
            shortAliases.toTypedArray(),
            description,
            required,
            hasValue,
            defaultValue,
            valueName,
            valueDescription,
            valueValidator,
        )
    }
}

/**
 * Init function for [CommanderOption]
 * @since 0.1.0
 * @version 0.1.0
 */
typealias CommanderOptionInit = CommanderOptionCreationContext.() -> Unit
