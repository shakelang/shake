package com.shakelang.util.commander

/**
 * A typealias that represents a cli command action
 * @since 0.1.0
 * @version 0.1.0
 */
typealias CommanderCommandAction = CommanderCommand.(ParseResult) -> Unit

/**
 * A class that represents a command line interface command
 * @param parent The parent command
 * @param name The name of the command
 * @param aliases The aliases of the command
 * @param description The description of the command
 * @param arguments The arguments of the command
 * @param options The options of the command
 * @param commands The subcommands of the command
 * @param action The action of the command
 * @since 0.1.0
 * @version 0.1.0
 */
class CommanderCommand(

    /**
     * The parent command (or null if this is the root command)
     * @since 0.1.0
     * @version 0.1.0
     */
    val parent: CommanderCommand? = null,

    /**
     * The name of the command
     * @since 0.1.0
     * @version 0.1.0
     */
    val name: String,

    /**
     * The aliases of the command
     * @since 0.1.0
     * @version 0.1.0
     */
    val aliases: Array<String> = arrayOf(),

    /**
     * The description of the command
     * @since 0.1.0
     * @version 0.1.0
     */
    val description: String? = null,

    /**
     * The arguments of the command
     * @since 0.1.0
     * @version 0.1.0
     */
    val arguments: MutableList<CommanderArgument> = mutableListOf(),

    /**
     * The options of the command
     * @since 0.1.0
     * @version 0.1.0
     */
    val options: MutableList<CommanderOption> = mutableListOf(),

    /**
     * The subcommands of the command
     * @since 0.1.0
     * @version 0.1.0
     */
    val commands: MutableList<CommanderCommand> = mutableListOf(),

    /**
     * The action of the command
     * @since 0.1.0
     * @version 0.1.0
     */
    var action: CommanderCommandAction? = null,
) {

    /**
     * Get the usage of the command
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getUsage(): String {
        val builder = StringBuilder()
        builder.append(name)
        if (arguments.isNotEmpty()) {
            for (argument in arguments) {
                builder.append(" ${argument.getUsage()}")
            }
        }
        if (options.isNotEmpty()) {
            for (option in options) {
                builder.append(" ${option.getUsage()}")
            }
        }
        if (commands.isNotEmpty()) {
            for (command in commands) {
                builder.append(" ${command.getUsage()}")
            }
        }
        return builder.toString()
    }

    /**
     * Get the full usage of the command
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getFullUsage(): String {
        val builder = StringBuilder()
        builder.append(getUsage())
        if (description != null) builder.append("\n$description")
        if (arguments.isNotEmpty()) {
            builder.append("\n\nArguments:")
            for (argument in arguments) {
                builder.append("\n  ${argument.getUsage()}")
                if (argument.description != null) builder.append("\n    ${argument.description}")
            }
        }
        if (options.isNotEmpty()) {
            builder.append("\n\nOptions:")
            for (option in options) {
                builder.append("\n  ${option.getUsage()}")
                if (option.description != null) builder.append("\n    ${option.description}")
            }
        }
        if (commands.isNotEmpty()) {
            builder.append("\n\nCommands:")
            for (command in commands) {
                builder.append("\n  ${command.getUsage()}")
                if (command.description != null) builder.append("\n    ${command.description}")
            }
        }
        return builder.toString()
    }

    /**
     * Get an option by its name (or alias)
     * @param name The name of the option
     * @return The option or null if the option does not exist
     */
    fun getOptionByName(name: String): CommanderOption? {
        for (option in options) {
            if (option.name == name) return option
            if (option.aliases.contains(name)) return option
        }
        return parent?.getOptionByName(name)
    }

    /**
     * Get an option by its short alias
     * @param name The short alias of the option
     * @return The option or null if the option does not exist
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getOptionByShortName(name: String): CommanderOption? {
        for (option in options) {
            if (option.shortAlias.contains(name)) return option
        }
        return parent?.getOptionByShortName(name)
    }

    /**
     * Get an argument by its name
     * @param name The name of the argument
     * @return The argument or null if the argument does not exist
     * @since 0.1.0
     * @version 0.1.0
     */
    fun option(
        name: String,
        aliases: Array<String> = arrayOf(),
        shortAliases: Array<String> = arrayOf(),
        description: String? = null,
        required: Boolean = false,
        hasValue: Boolean = false,
        defaultValue: String? = null,
        valueName: String? = null,
        valueDescription: String? = null,
        valueValidator: CommanderValueValidator? = null,
    ): CommanderCommand {
        options.add(
            CommanderOption(
                this,
                name,
                aliases,
                shortAliases,
                description,
                required,
                hasValue,
                defaultValue,
                valueName,
                valueDescription,
                valueValidator,
            ),
        )
        return this
    }

    /**
     * Create a new argument
     * @param name The name of the argument
     * @param description The description of the argument
     * @param required If the argument is required
     * @param defaultValue The default value of the argument
     * @param valueName The value name of the argument
     * @param valueDescription The value description of the argument
     * @param valueValidator The value validator of the argument
     * @return The created argument
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun argument(
        name: String,
        description: String? = null,
        required: Boolean = false,
        defaultValue: String? = null,
        valueName: String? = null,
        valueDescription: String? = null,
        valueValidator: CommanderValueValidator? = null,
    ): CommanderCommand {
        arguments.add(
            CommanderArgument(
                this,
                name,
                description,
                required,
                defaultValue,
                valueName,
                valueDescription,
                valueValidator,
            ),
        )
        return this
    }

    /**
     * Create a new subcommand
     * @param name The name of the subcommand
     * @param aliases The aliases of the subcommand
     * @param description The description of the subcommand
     * @param action The action of the subcommand
     * @param init The init function of the subcommand
     * @return The created subcommand
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun subcommand(
        name: String,
        aliases: Array<String> = arrayOf(),
        description: String? = null,
        action: CommanderCommandAction? = null,
        init: (CommanderCommand.() -> Unit)? = null,
    ): CommanderCommand {
        val command =
            CommanderCommand(this, name, aliases, description, mutableListOf(), mutableListOf(), mutableListOf(), action)
        commands.add(command)
        if (init != null) command.init()
        return this
    }

    /**
     * Parse an array of arguments
     *
     * @param args The arguments to parse
     * @param stack The stack of commands (already containing the parent commands)
     * @return The result of the parsing
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun parse(args: Array<String>, stack: Array<MutableCommandStackEntry>): ParseResult {
        val entry = MutableCommandStackEntry(name, this)

        val newStack = arrayOf(*stack, entry)

        var i = 0
        var argumentIndex = 0
        while (i < args.size) {
            val arg = args[i]
            when {
                arg.startsWith("--") -> {
                    val option = getOptionByName(arg.substring(2))
                        ?: throw CommanderUnknownOptionException("Unknown option: $name for (sub)command: ${entry.name}")

                    val name = option.name

                    // Find entry for option

                    val entry = newStack.find { it.command == option.command }
                        ?: throw IllegalArgumentException("Unknown command for option: $name")

                    val value = if (option.hasValue) {
                        if (i + 1 >= args.size) throw CommanderMissingOptionValueException("Missing value for option: $name")
                        args[++i]
                    } else {
                        if (entry.options.containsKey(name)) throw IllegalArgumentException("Option: $name already exists")
                        "true"
                    }

                    if (option.valueValidator != null) {
                        if (!option.valueValidator.accepts(value)) throw ValueValidationException("Invalid value for option: $name")
                    }

                    val values = entry.options[name] ?: arrayOf()
                    entry.options[name] = arrayOf(*values, CommanderValue(value))
                }

                arg.startsWith("-") -> {
                    val option = getOptionByShortName(arg.substring(1))
                        ?: throw IllegalArgumentException("Unknown option: $name for (sub)command: ${entry.name}")

                    val name = option.name

                    // Find entry for option

                    val argEntry = newStack.find { it.command == option.command }
                        ?: throw IllegalArgumentException("Unknown command for option: $name")

                    val value = if (option.hasValue) {
                        if (i + 1 >= args.size) throw IllegalArgumentException("Missing value for option: $name")
                        args[++i]
                    } else {
                        if (argEntry.options.containsKey(name)) throw IllegalArgumentException("Option: $name already exists")
                        "true"
                    }

                    if (option.valueValidator != null) {
                        if (!option.valueValidator.accepts(value)) throw ValueValidationException("Invalid value for option: $name")
                    }

                    val values = argEntry.options[name] ?: arrayOf()
                    argEntry.options[name] = arrayOf(*values, CommanderValue(value))
                }

                else -> {
                    // First all the arguments of this command, then we will check for subcommands
                    if (argumentIndex < arguments.size) {
                        val argument = arguments[argumentIndex++]
                        val name = argument.name

                        // Find entry for argument

                        val argEntry = newStack.find { it.command == argument.command }
                            ?: throw IllegalArgumentException("Unknown command for argument: $name")

                        if (argEntry.arguments.containsKey(name)) throw IllegalArgumentException("Argument: $name already exists")

                        if (argument.valueValidator != null && !argument.valueValidator.accepts(arg)) {
                            throw ValueValidationException("Invalid value for argument: $name")
                        }

                        argEntry.arguments[name] = CommanderValue(arg)
                    } else {
                        // Find subcommand
                        val command = commands.find { it.name == arg || it.aliases.contains(arg) }
                            ?: throw CommanderUnknownSubCommandException("Unknown command: $arg")

                        return command.parse(args.sliceArray((i + 1) until args.size), newStack)
                    }
                }
            }

            i++
        }

        return ParseResult(newStack.toMutableList())
    }

    /**
     * Parse an array of arguments
     *
     * @param args The arguments to parse
     * @return The result of the parsing
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun parse(args: Array<String>): ParseResult {
        val result = parse(args, arrayOf())
        result.verify()
        return result
    }

    /**
     * Execute the command
     *
     * @param args The arguments to parse
     * @return The result of the parsing
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun execute(args: Array<String>): ParseResult {
        val result = parse(args)
        result.stack.last().command.action?.invoke(result.stack.last().command, result)
        return result
    }

    /**
     * Verify a given command stack entry
     * (Checks if all required arguments and options are given, applies default values)
     *
     * @param commandStackEntry The command stack entry to verify
     * @since 0.1.0
     * @version 0.1.0
     */
    internal fun verify(commandStackEntry: MutableCommandStackEntry) {
        for (it in this.options) {
            if (it.hasValue && it.defaultValue != null && !commandStackEntry.options.containsKey(it.name)) {
                commandStackEntry.options[it.name] = arrayOf(CommanderValue(it.defaultValue))
            }
            if (it.required && !commandStackEntry.options.containsKey(it.name)) throw CommanderMissingOptionException("Missing required option: ${it.name}")
        }

        for (it in this.arguments) {
            if (it.defaultValue != null && !commandStackEntry.arguments.containsKey(it.name)) {
                commandStackEntry.arguments[it.name] = CommanderValue(it.defaultValue)
            }
            if (it.required && !commandStackEntry.arguments.containsKey(it.name)) throw CommanderMissingArgumentException("Missing required argument: ${it.name}")
        }
    }
}

/**
 * Context for creating a command
 * @since 0.1.0
 * @version 0.1.0
 */
class CommanderCommandCreationContext {

    /**
     * The name of the command
     * @since 0.1.0
     * @version 0.1.0
     */
    lateinit var name: String

    /**
     * The aliases of the command
     * @since 0.1.0
     * @version 0.1.0
     */
    val aliases: MutableList<String> = mutableListOf()

    /**
     * The description of the command
     * @since 0.1.0
     * @version 0.1.0
     */
    var description: String? = null

    /**
     * The action of the command
     * @since 0.1.0
     * @version 0.1.0
     */
    var action: CommanderCommandAction? = null

    /**
     * The arguments of the command
     * @since 0.1.0
     * @version 0.1.0
     */
    val arguments: MutableList<(CommanderCommand) -> CommanderArgument> = mutableListOf()

    /**
     * The options of the command
     * @since 0.1.0
     * @version 0.1.0
     */
    val options: MutableList<(CommanderCommand) -> CommanderOption> = mutableListOf()

    /**
     * The subcommands of the command
     * @since 0.1.0
     * @version 0.1.0
     */
    val commands: MutableList<(CommanderCommand) -> CommanderCommand> = mutableListOf()

    /**
     * Add an alias to the command
     * @param aliases The aliases to add
     * @since 0.1.0
     * @version 0.1.0
     */
    fun alias(vararg aliases: String) {
        this.aliases.addAll(aliases)
    }

    /**
     * Add description to the command
     * @param description The description to add
     * @since 0.1.0
     * @version 0.1.0
     */
    fun description(description: String) {
        this.description = (this.description?.plus("\n") ?: "") + description
    }

    /**
     * Add an action to the command
     * @param action The action to add
     * @since 0.1.0
     * @version 0.1.0
     */
    fun action(action: CommanderCommandAction) {
        this.action = action
    }

    /**
     * Add an argument to the command
     * @param cliArgument The argument to add
     * @since 0.1.0
     * @version 0.1.0
     */
    fun argument(cliArgument: CommanderArgument) {
        arguments.add { cliArgument }
    }

    /**
     * Add an argument to the command
     * @param init The init function to create the argument
     * @since 0.1.0
     * @version 0.1.0
     */
    fun argument(init: CliArgumentInit) {
        val context = CommanderArgumentCreationContext()
        context.init()
        arguments.add { context.generate(it) }
    }

    /**
     * Add an argument to the command
     * @param name The name of the argument
     * @param description The description of the argument
     * @param required If the argument is required
     * @param defaultValue The default value of the argument
     * @param valueName The value name of the argument
     * @param valueDescription The value description of the argument
     * @param valueValidator The value validator of the argument
     * @since 0.1.0
     * @version 0.1.0
     */
    fun argument(
        name: String,
        description: String? = null,
        required: Boolean = false,
        defaultValue: String? = null,
        valueName: String? = null,
        valueDescription: String? = null,
        valueValidator: CommanderValueValidator? = null,
    ) {
        arguments.add {
            CommanderArgument(
                it,
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
     * Add an option to the command
     * @param cliOption The option to add
     * @since 0.1.0
     * @version 0.1.0
     */
    fun option(cliOption: CommanderOption) {
        options.add { cliOption }
    }

    /**
     * Add an option to the command
     * @param init The init function to create the option
     * @since 0.1.0
     * @version 0.1.0
     */
    fun option(init: CommanderOptionInit) {
        val context = CommanderOptionCreationContext()
        context.init()
        options.add { context.generate(it) }
    }

    /**
     * Add an option to the command
     * @param name The name of the option
     * @param aliases The aliases of the option
     * @param shortAliases The short aliases of the option
     * @param description The description of the option
     * @param required If the option is required
     * @param hasValue If the option has a value
     * @param defaultValue The default value of the option
     * @param valueName The value name of the option
     * @param valueDescription The value description of the option
     * @param valueValidator The value validator of the option
     * @since 0.1.0
     * @version 0.1.0
     */
    fun option(
        name: String,
        aliases: Array<String> = arrayOf(),
        shortAliases: Array<String> = arrayOf(),
        description: String? = null,
        required: Boolean = false,
        hasValue: Boolean = false,
        defaultValue: String? = null,
        valueName: String? = null,
        valueDescription: String? = null,
        valueValidator: CommanderValueValidator? = null,
    ) {
        options.add {
            CommanderOption(
                it,
                name,
                aliases,
                shortAliases,
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
     * Add a subcommand to the command
     * @param cliCommand The subcommand to add
     * @since 0.1.0
     * @version 0.1.0
     */
    fun subcommand(cliCommand: CommanderCommand) {
        commands.add { cliCommand }
    }

    /**
     * Add a subcommand to the command
     * @param init The init function to create the subcommand
     * @since 0.1.0
     * @version 0.1.0
     */
    fun subcommand(init: CommanderCommandInit) {
        val context = CommanderCommandCreationContext()
        context.init()
        commands.add { context.generate(it) }
    }

    /**
     * Add a subcommand to the command
     * @param name The name of the subcommand
     * @param aliases The aliases of the subcommand
     * @param description The description of the subcommand
     * @param action The action of the subcommand
     * @param init The init function of the subcommand
     * @since 0.1.0
     * @version 0.1.0
     */
    fun subcommand(
        name: String,
        aliases: Array<String> = arrayOf(),
        description: String? = null,
        action: CommanderCommandAction? = null,
        init: (CommanderCommandInit)? = null,
    ) {
        val context = CommanderCommandCreationContext()
        context.name = name
        context.aliases.addAll(aliases)
        context.description = description
        context.action = action
        if (init != null) context.init()
        commands.add { context.generate(it) }
    }

    /**
     * Generate the command
     * @param parent The parent command
     * @return The generated command
     * @since 0.1.0
     * @version 0.1.0
     */
    fun generate(parent: CommanderCommand?): CommanderCommand {
        if (!::name.isInitialized) throw IllegalStateException("Name is not initialized")

        val cmd = CommanderCommand(
            parent,
            name,
            aliases.toTypedArray(),
            description,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            action,
        )

        for (argument in arguments) {
            cmd.arguments.add(argument(cmd))
        }

        for (option in options) {
            cmd.options.add(option(cmd))
        }

        for (command in commands) {
            cmd.commands.add(command(cmd))
        }

        return cmd
    }
}

/**
 * Create a new command
 * @param init The init function to create the command
 * @return The created command
 * @since 0.1.0
 * @version 0.1.0
 */
fun command(init: CommanderCommandInit): CommanderCommand {
    val context = CommanderCommandCreationContext()
    context.init()
    return context.generate(null)
}

/**
 * Init function for creating a command
 * @since 0.1.0
 * @version 0.1.0
 */
typealias CommanderCommandInit = CommanderCommandCreationContext.() -> Unit
