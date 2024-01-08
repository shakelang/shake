package com.shakelang.util.commander

typealias CommandAction = CliCommand.(ParseResult) -> Unit

class CliCommand(
    val parent: CliCommand? = null,
    val name: String,
    val aliases: Array<String> = arrayOf(),
    val description: String? = null,
    val arguments: MutableList<CliArgument> = mutableListOf(),
    val options: MutableList<CliOption> = mutableListOf(),
    val commands: MutableList<CliCommand> = mutableListOf(),
    var action: CommandAction? = null
) {
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

    fun getOptionByName(name: String): CliOption? {
        for (option in options) {
            if (option.name == name) return option
            if (option.aliases.contains(name)) return option
        }
        return parent?.getOptionByName(name)
    }

    fun getOptionByShortName(name: String): CliOption? {
        for (option in options) {
            if (option.shortAlias.contains(name)) return option
        }
        return parent?.getOptionByShortName(name)
    }

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
        valueValidator: ValueValidator? = null
    ): CliCommand {
        options.add(
            CliOption(
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
                valueValidator
            )
        )
        return this
    }

    fun argument(
        name: String,
        description: String? = null,
        required: Boolean = false,
        hasValue: Boolean = false,
        defaultValue: String? = null,
        valueName: String? = null,
        valueDescription: String? = null,
        valueValidator: ValueValidator? = null
    ): CliCommand {
        arguments.add(
            CliArgument(
                this,
                name,
                description,
                required,
                hasValue,
                defaultValue,
                valueName,
                valueDescription,
                valueValidator
            )
        )
        return this
    }

    fun command(
        name: String,
        aliases: Array<String> = arrayOf(),
        description: String? = null,
        action: CommandAction? = null,
        init: (CliCommand.() -> Unit)? = null
    ): CliCommand {
        val command =
            CliCommand(this, name, aliases, description, mutableListOf(), mutableListOf(), mutableListOf(), action)
        commands.add(command)
        if (init != null) command.init()
        return this
    }

    fun parse(args: Array<String>, stack: Array<CommandStackEntry>): ParseResult {
        val entry = CommandStackEntry(name, this)

        val newStack = arrayOf(*stack, entry)

        var i = 0
        var argumentIndex = 0
        while (i < args.size) {
            val arg = args[i]
            when {
                arg.startsWith("--") -> {
                    val option = getOptionByName(arg.substring(2))
                        ?: throw CliUnknownOptionException("Unknown option: $name for (sub)command: ${entry.name}")

                    val name = option.name

                    // Find entry for option

                    val entry = newStack.find { it.command == option.command }
                        ?: throw IllegalArgumentException("Unknown command for option: $name")

                    if (entry.options.containsKey(name)) throw IllegalArgumentException("Option: $name already exists")

                    val value = if (option.hasValue) {
                        if (i + 1 >= args.size) throw CliMissingOptionValueException("Missing value for option: $name")
                        args[++i]
                    } else {
                        "true"
                    }

                    if (option.valueValidator != null) {
                        if (!option.valueValidator.accepts(value)) throw ValueValidationException("Invalid value for option: $name")
                    }

                    val values = entry.options[name] ?: arrayOf()
                    entry.options[name] = arrayOf(*values, Value(value))
                }

                arg.startsWith("-") -> {
                    val option = getOptionByShortName(arg.substring(1))
                        ?: throw IllegalArgumentException("Unknown option: $name for (sub)command: ${entry.name}")

                    val name = option.name

                    // Find entry for option

                    val argEntry = newStack.find { it.command == option.command }
                        ?: throw IllegalArgumentException("Unknown command for option: $name")

                    if (argEntry.options.containsKey(name)) throw IllegalArgumentException("Option: $name already exists")

                    val value = if (option.hasValue) {
                        if (i + 1 >= args.size) throw IllegalArgumentException("Missing value for option: $name")
                        args[++i]
                    } else {
                        "true"
                    }

                    if (option.valueValidator != null) {
                        if (!option.valueValidator.accepts(value)) throw ValueValidationException("Invalid value for option: $name")
                    }

                    val values = argEntry.options[name] ?: arrayOf()
                    argEntry.options[name] = arrayOf(*values, Value(value))
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

                        argEntry.arguments[name] = Value(arg)
                    } else {
                        // Find subcommand
                        val command = commands.find { it.name == arg || it.aliases.contains(arg) }
                            ?: throw CliUnknownSubCommandException("Unknown command: $arg")

                        return command.parse(args.sliceArray((i + 1) until args.size), newStack)
                    }
                }
            }

            i++
        }

        return ParseResult(newStack.toMutableList())
    }

    fun parse(args: Array<String>): ParseResult {
        return parse(args, arrayOf())
    }

    fun execute(args: Array<String>): ParseResult {
        val result = parse(args)
        result.stack.last().command.action?.invoke(result.stack.last().command, result)
        return result
    }
}

class CliCommandCreationContext {

    lateinit var name: String
    val aliases: MutableList<String> = mutableListOf()
    var description: String? = null
    var action: CommandAction? = null
    val arguments: MutableList<(CliCommand) -> CliArgument> = mutableListOf()
    val options: MutableList<(CliCommand) -> CliOption> = mutableListOf()
    val commands: MutableList<(CliCommand) -> CliCommand> = mutableListOf()

    fun alias(vararg aliases: String) {
        this.aliases.addAll(aliases)
    }

    fun description(description: String) {
        this.description = (this.description?.plus("\n") ?: "") + description
    }

    fun action(action: CommandAction) {
        this.action = action
    }

    fun argument(cliArgument: CliArgument) {
        arguments.add { cliArgument }
    }

    fun argument(init: CliArgumentCreationContext.() -> Unit) {
        val context = CliArgumentCreationContext()
        context.init()
        arguments.add { context.generate(it) }
    }

    fun argument(
        name: String,
        description: String? = null,
        required: Boolean = false,
        hasValue: Boolean = false,
        defaultValue: String? = null,
        valueName: String? = null,
        valueDescription: String? = null,
        valueValidator: ValueValidator? = null
    ) {
        arguments.add {
            CliArgument(
                it,
                name,
                description,
                required,
                hasValue,
                defaultValue,
                valueName,
                valueDescription,
                valueValidator
            )
        }
    }

    fun option(cliOption: CliOption) {
        options.add { cliOption }
    }

    fun option(init: CliOptionCreationContext.() -> Unit) {
        val context = CliOptionCreationContext()
        context.init()
        options.add { context.generate(it) }
    }

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
        valueValidator: ValueValidator? = null
    ) {
        options.add {
            CliOption(
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
                valueValidator
            )
        }
    }

    fun subcommand(cliCommand: CliCommand) {
        commands.add { cliCommand }
    }

    fun subcommand(init: CliCommandCreationContext.() -> Unit) {
        val context = CliCommandCreationContext()
        context.init()
        commands.add { context.generate(it) }
    }

    fun subcommand(
        name: String,
        aliases: Array<String> = arrayOf(),
        description: String? = null,
        action: CommandAction? = null,
        init: (CliCommandCreationContext.() -> Unit)? = null
    ) {
        val context = CliCommandCreationContext()
        context.name = name
        context.aliases.addAll(aliases)
        context.description = description
        context.action = action
        if (init != null) context.init()
        commands.add { context.generate(it) }
    }

    fun generate(parent: CliCommand?): CliCommand {
        if (!::name.isInitialized) throw IllegalStateException("Name is not initialized")

        val cmd = CliCommand(
            parent,
            name,
            aliases.toTypedArray(),
            description,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            action
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

fun command(init: CliCommandCreationContext.() -> Unit): CliCommand {
    val context = CliCommandCreationContext()
    context.init()
    return context.generate(null)
}
