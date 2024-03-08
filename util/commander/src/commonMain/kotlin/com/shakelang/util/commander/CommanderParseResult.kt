package com.shakelang.util.commander

/**
 * The result of a parse operation
 * @property stack The stack of commands
 * @constructor Creates a [CommanderParseResult]
 * @since 0.1.0
 * @version 0.1.0
 */
class CommanderParseResult(

    /**
     * The stack of commands
     * @since 0.1.0
     * @version 0.1.0
     */
    val stack: List<CommanderCommandStackEntry>,

) {

    /**
     * Get the result for a command
     * @param command The command to get the result for
     * @return The result or null if the command was not found
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getCommandResult(command: CommanderCommand): CommanderCommandStackEntry? {
        for (entry in stack) {
            if (entry.command == command) return entry
        }
        return null
    }

    /**
     * Get an option by its name (or alias)
     * @param alias The name or alias of the option
     * @return The option or null if the option does not exist
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getOptionByName(alias: String): CommanderOption? {
        for (entry in stack.reversed()) {
            entry.command.getOptionByName(alias)?.let { return it }
        }
        return null
    }

    /**
     * Get an option by its shorts name (or alias)
     * @param alias The shorts name or alias of the option
     * @return The option or null if the option does not exist
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getOptionByShortName(alias: String): CommanderOption? {
        for (entry in stack.reversed()) {
            entry.command.getOptionByShortName(alias)?.let { return it }
        }
        return null
    }

    /**
     * Get the value of an option
     * @param option The option to get the value for
     * @return The value or null if the option was not found
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getOptionValue(option: CommanderOption): Array<CommanderValue>? {
        val result = getCommandResult(option.command) ?: return null
        return result.options[option.name]
    }

    /**
     * Get an option value by its name (or alias)
     * @param name The name or alias of the option
     * @return The value or null if the option was not found
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getOptionValueByName(name: String): Array<CommanderValue>? {
        val command = getOptionByName(name) ?: return null
        return getOptionValue(command)
    }

    /**
     * Get an option value by its shorts name (or alias)
     * @param name The shorts name or alias of the option
     * @return The value or null if the option was not found
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getOptionValueByShortName(name: String): Array<CommanderValue>? {
        val command = getOptionByShortName(name) ?: return null
        return getOptionValue(command)
    }

    /**
     * Get an argument by its name (or alias)
     * @param name The name or alias of the argument
     * @return The argument or null if the argument does not exist
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getArgumentByName(name: String): CommanderArgument? {
        for (entry in stack.reversed()) {
            entry.command.getArgumentByName(name)?.let { return it }
        }
        return null
    }

    /**
     * Get the value of an argument
     * @param argument The argument to get the value for
     * @return The value or null if the argument was not found
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getArgumentValue(argument: CommanderArgument): CommanderValue? {
        val result = getCommandResult(argument.command) ?: return null
        return result.arguments[argument.name]
    }

    /**
     * Get an argument value by its name (or alias)
     * @param name The name or alias of the argument
     * @return The value or null if the argument was not found
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getArgumentValueByName(name: String): CommanderValue? {
        val command = getArgumentByName(name) ?: return null
        return getArgumentValue(command)
    }

    /**
     * Get the value of an argument or option by its name (or alias)
     * @param name The name or alias of the argument or option
     * @return The value or null if the argument or option was not found
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getValueByName(name: String): List<CommanderValue>? {
        for (entry in stack.reversed()) {
            entry.command.getOptionByName(name)?.let {
                return getOptionValue(it)?.toList()
            }
            entry.command.getArgumentByName(name)?.let {
                return listOf(getArgumentValue(it)!!)
            }
        }
        return null
    }

    /**
     * Verify the result by checking if all required arguments and options are set
     * (also applies default values)
     * Only works with mutable [CommanderCommandStackEntry]s
     * @since 0.1.0
     * @version 0.1.0
     */
    internal fun verify() {
        for (entry in stack) {
            entry.verify()
        }
    }
}

/**
 * A stack entry
 * @property alias The alias of the command
 * @property command The command
 * @property arguments The arguments
 * @property options The options
 * @constructor Creates a [CommanderCommandStackEntry]
 * @since 0.1.0
 * @version 0.1.0
 */
open class CommanderCommandStackEntry(

    /**
     * The alias of the command
     * @since 0.1.0
     * @version 0.1.0
     */
    val alias: String,

    /**
     * The command
     * @since 0.1.0
     * @version 0.1.0
     */
    val command: CommanderCommand,

    /**
     * The arguments
     * @since 0.1.0
     * @version 0.1.0
     */
    open val arguments: Map<String, CommanderValue>,

    /**
     * The options
     * @since 0.1.0
     * @version 0.1.0
     */
    open val options: Map<String, Array<CommanderValue>>,

) {

    /**
     * The name of the command
     * @since 0.1.0
     * @version 0.1.0
     */
    val name: String = command.name

    /**
     * Verify the result by checking if all required arguments and options are set
     * (also applies default values)
     * Only works with mutable [CommanderCommandStackEntry]s
     * @throws IllegalStateException If the [CommanderCommandStackEntry] is not mutable
     * @since 0.1.0
     * @version 0.1.0
     */
    internal open fun verify() {
        if (this !is CommanderMutableCommandStackEntry) throw IllegalStateException("CommanderCommandStackEntry is not mutable")
        this.verify()
    }
}

/**
 * A mutable stack entry
 * @property alias The alias of the command
 * @property command The command
 * @property arguments The arguments
 * @property options The options
 * @constructor Creates a [CommanderMutableCommandStackEntry]
 * @since 0.1.0
 * @version 0.1.0
 */
class CommanderMutableCommandStackEntry(

    /**
     * The alias of the command
     * @since 0.1.0
     * @version 0.1.0
     */
    alias: String,

    /**
     * The command
     * @since 0.1.0
     * @version 0.1.0
     */
    command: CommanderCommand,

) : CommanderCommandStackEntry(alias, command, mutableMapOf(), mutableMapOf()) {

    /**
     * The arguments
     * @since 0.1.0
     * @version 0.1.0
     */
    override val arguments: MutableMap<String, CommanderValue> get() = super.arguments as MutableMap<String, CommanderValue>

    /**
     * The options
     * @since 0.1.0
     * @version 0.1.0
     */
    override val options: MutableMap<String, Array<CommanderValue>> = super.options as MutableMap<String, Array<CommanderValue>>

    /**
     * Verify the result by checking if all required arguments and options are set
     * (also applies default values)
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun verify() {
        command.verify(this)
    }
}
