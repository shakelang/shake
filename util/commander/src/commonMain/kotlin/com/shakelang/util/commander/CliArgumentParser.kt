package com.shakelang.util.commander

abstract class ValueException(message: String) : RuntimeException(message)

/**
 * Error thrown when an argument is not valid
 */
class ValueValidationException(message: String) : ValueException(message)

/**
 * Error thrown when a required argument is missing
 */
class MissingArgumentException(message: String) : ValueException(message)

/**
 * Error thrown when unknown arguments are passed
 */
class UnknownArgumentException(message: String) : ValueException(message)

class CliOption(
    val command: CliCommand,
    val name: String,
    val aliases: Array<String> = arrayOf(),
    val shortAlias: Array<String> = arrayOf(),
    val description: String? = null,
    val required: Boolean = false,
    val hasValue: Boolean = false,
    val defaultValue: String? = null,
    val valueName: String? = null,
    val valueDescription: String? = null,
    val valueValidator: ValueValidator? = null,
) {
    fun getUsage(): String {
        val builder = StringBuilder()
        builder.append("--$name")
        if (shortAlias.isNotEmpty() || aliases.isNotEmpty()) {
            val aliases = arrayOf(
                *shortAlias.map { "-$it" }.toTypedArray(),
                *aliases.map { "--$it" }.toTypedArray()
            )
            builder.append(" (", aliases.joinToString(", "), ")")
        }
        if (valueName != null) builder.append(" <$valueName>")
        return builder.toString()
    }
}

interface CliCommandAppendable

class CliArgument(
    val command: CliCommand,
    val name: String,
    val description: String? = null,
    val required: Boolean = false,
    val hasValue: Boolean = false,
    val defaultValue: String? = null,
    val valueName: String? = null,
    val valueDescription: String? = null,
    val valueValidator: ValueValidator? = null,
) {
    fun getUsage(): String {
        val builder = StringBuilder()
        builder.append("<$name>")
        if (valueName != null) builder.append(" <$valueName>")
        return builder.toString()
    }
}

class CliCommand(
    val parent: CliCommand? = null,
    val name: String,
    val aliases: Array<String> = arrayOf(),
    val description: String? = null,
    val arguments: MutableList<CliArgument> = mutableListOf(),
    val options: MutableList<CliOption> = mutableListOf(),
    val commands: MutableList<CliCommand> = mutableListOf(),
    var action: ((CliCommand, Array<String>) -> Unit)? = null
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
        valueValidator: ValueValidator? = null,
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
        valueValidator: ValueValidator? = null,
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
        action: ((CliCommand, Array<String>) -> Unit)? = null,
        init: (CliCommand.() -> Unit)? = null,
    ): CliCommand {
        val command = CliCommand(this, name, aliases, description, mutableListOf(), mutableListOf(), mutableListOf(), action)
        commands.add(command)
        if (init != null) command.init()
        return this
    }

    fun parse(args: Array<String>, stack: Array<CommandStackEntry>): ParseResult {

        val argsList = args.toMutableList()
        val entry = CommandStackEntry(name, this)

        val newStack = arrayOf(*stack, entry)

        var i = 0
        while (i < args.size) {

            val arg = args[i]
            when {
                arg.startsWith("--") -> {
                    val option = getOptionByName(arg.substring(2))
                        ?: throw IllegalArgumentException("Unknown option: $name for (sub)command: ${entry.name}")

                    val name = option.name

                    // Find entry for option

                    val entry = newStack.find { it.command == option.command }
                        ?: throw IllegalArgumentException("Unknown command for option: $name")

                    if(entry.options.containsKey(name)) throw IllegalArgumentException("Option: $name already exists")

                    val value = if (option.hasValue) {
                        if (i + 1 >= args.size) throw IllegalArgumentException("Missing value for option: $name")
                        args[++i]
                    } else "true"

                    if (option.valueValidator != null) {
                        if (!option.valueValidator.accepts(value)) throw ValueValidationException("Invalid value for option: $name")
                    }

                    val values = entry.options[name] ?: arrayOf()
                    entry.options[name] = arrayOf(*values, Value(name, value))
                }

                arg.startsWith("-") -> {
                    val option = getOptionByShortName(arg.substring(1))
                        ?: throw IllegalArgumentException("Unknown option: $name for (sub)command: ${entry.name}")

                    val name = option.name

                    // Find entry for option

                    val argEntry = newStack.find { it.command == option.command }
                        ?: throw IllegalArgumentException("Unknown command for option: $name")

                    if(argEntry.options.containsKey(name)) throw IllegalArgumentException("Option: $name already exists")

                    val value = if (option.hasValue) {
                        if (i + 1 >= args.size) throw IllegalArgumentException("Missing value for option: $name")
                        args[++i]
                    } else "true"

                    if (option.valueValidator != null) {
                        if (!option.valueValidator.accepts(value)) throw ValueValidationException("Invalid value for option: $name")
                    }

                    val values = argEntry.options[name] ?: arrayOf()
                    argEntry.options[name] = arrayOf(*values, Value(name, value))
                }

                // TODO
            }

            i++;
        }

        return ParseResult(newStack.toMutableList())
    }


}

class ParseResult(
    val stack : MutableList<CommandStackEntry>
) {
    fun getOptionValueByName(alias: String): Array<Value>? {
        for (entry in stack) {
            val value = entry.options[alias]
            if (value != null) return value
        }
        return null
    }

    fun getArgumentValueByName(alias: String): Value? {
        for (entry in stack) {
            val value = entry.arguments[alias]
            if (value != null) return value
        }
        return null
    }

    fun getValueByName(alias: String): List<Value>? {
        for (entry in stack) {
            if(entry.options.containsKey(alias)) return entry.options[alias]?.toList()
            if(entry.arguments.containsKey(alias)) return listOf(entry.arguments[alias]!!)
        }
        return null
    }
}

class CommandStackEntry(
    val alias: String,
    val command: CliCommand,
) {
    val name: String = command.name
    val arguments: MutableMap<String, Value> = mutableMapOf()
    val options: MutableMap<String, Array<Value>> = mutableMapOf()
}


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



data class Value(
    val name: String,
    val value: String?,
) {
    fun <T> to(valueTransformer: ValueTransformer<T>) = valueTransformer(value ?: throw NullPointerException("Value is null"))
    fun toByte(): Byte = value?.toByte() ?: throw NumberFormatException("Value is null")
    fun toShort(): Short = value?.toShort() ?: throw NumberFormatException("Value is null")
    fun toInt(): Int = value?.toInt() ?: throw NumberFormatException("Value is null")
    fun toLong(): Long = value ?.toLong() ?: throw NumberFormatException("Value is null")
    fun toFloat(): Float = value?.toFloat() ?: throw NumberFormatException("Value is null")
    fun toDouble(): Double = value?.toDouble() ?: throw NumberFormatException("Value is null")
    fun toBoolean(): Boolean = value?.toBoolean() ?: throw NumberFormatException("Value is null")
    override fun toString(): String = value ?: throw NullPointerException("Value is null")

    fun <T> toOrNull(valueTransformer: NullableValueTransformer<T>) = valueTransformer(value)
    fun toByteOrNull(): Byte? = value?.toByteOrNull()
    fun toShortOrNull(): Short? = value?.toShortOrNull()
    fun toIntOrNull(): Int? = value?.toIntOrNull()
    fun toLongOrNull(): Long? = value?.toLongOrNull()
    fun toFloatOrNull(): Float? = value?.toFloatOrNull()
    fun toDoubleOrNull(): Double? = value?.toDoubleOrNull()
    fun toBooleanOrNull(): Boolean? = value?.toBoolean()
    fun toStringOrNull(): String? = value

    fun validate(valueValidator: ValueValidator) = valueValidator(value)
    fun validate(valueValidator: NullableValueTransformer<Any>) = valueValidator(value) != null
    fun validateByte(): Boolean = value?.toByteOrNull() != null
    fun validateShort(): Boolean = value?.toShortOrNull() != null
    fun validateInt(): Boolean = value?.toIntOrNull() != null
    fun validateLong(): Boolean = value?.toLongOrNull() != null
    fun validateFloat(): Boolean = value?.toFloatOrNull() != null
    fun validateDouble(): Boolean = value?.toDoubleOrNull() != null
    fun validateBoolean(): Boolean = value?.toBoolean() != null
    fun validateString(): Boolean = value != null

    fun isNull(): Boolean = value == null
    fun isNotNull(): Boolean = value != null
    fun isByte(): Boolean = value?.toByteOrNull() != null
    fun isShort(): Boolean = value?.toShortOrNull() != null
    fun isInt(): Boolean = value?.toIntOrNull() != null
    fun isLong(): Boolean = value?.toLongOrNull() != null
    fun isFloat(): Boolean = value?.toFloatOrNull() != null
    fun isDouble(): Boolean = value?.toDoubleOrNull() != null
    fun isBoolean(): Boolean = value?.toBoolean() != null
    fun isString(): Boolean = value != null
}

class OptionCreationContext {

    lateinit var name: String
    val aliases: MutableList<String> = mutableListOf()
    val shortAliases: MutableList<String> = mutableListOf()
    var description: String? = null
    var required: Boolean = false
    var hasValue: Boolean = false
    var defaultValue: String? = null
    var valueName: String? = null
    var valueDescription: String? = null
    var valueValidator: ValueValidator? = null

    fun alias(vararg aliases: String) {
        this.aliases.addAll(aliases)
    }

    fun shortAlias(vararg aliases: String) {
        this.shortAliases.addAll(aliases)
    }

    fun description(description: String) {
        this.description = (this.description?.plus("\n") ?: "") + description
    }

    fun required(required: Boolean = true) {
        this.required = required
    }

    fun generate(command: CliCommand): CliOption {

        if(!::name.isInitialized) throw IllegalStateException("Name is not initialized")

        return CliOption(
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
            valueValidator
        )
    }
}

class ArgumentCreationContext {

    lateinit var name: String
    var description: String? = null
    var required: Boolean = false
    var hasValue: Boolean = false
    var defaultValue: String? = null
    var valueName: String? = null
    var valueDescription: String? = null
    var valueValidator: ValueValidator? = null

    fun description(description: String) {
        this.description = (this.description?.plus("\n") ?: "") + description
    }

    fun required(required: Boolean = true) {
        this.required = required
    }

    fun generate(command: CliCommand): CliArgument {

        if(!::name.isInitialized) throw IllegalStateException("Name is not initialized")

        return CliArgument(
            command,
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

class CommandCreationContext {

    lateinit var name: String
    val aliases: MutableList<String> = mutableListOf()
    var description: String? = null
    var action: ((CliCommand, Array<String>) -> Unit)? = null
    val arguments: MutableList<(CliCommand) -> CliArgument> = mutableListOf()
    val options: MutableList<(CliCommand) -> CliOption> = mutableListOf()
    val commands: MutableList<(CliCommand) -> CliCommand> = mutableListOf()

    fun alias(vararg aliases: String) {
        this.aliases.addAll(aliases)
    }

    fun description(description: String) {
        this.description = (this.description?.plus("\n") ?: "") + description
    }

    fun action(action: (CliCommand, Array<String>) -> Unit) {
        this.action = action
    }

    fun argument(cliArgument: CliArgument) {
        arguments.add { cliArgument }
    }

    fun argument(init: ArgumentCreationContext.() -> Unit) {
        val context = ArgumentCreationContext()
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
        valueValidator: ValueValidator? = null,
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

    fun option(init: OptionCreationContext.() -> Unit) {
        val context = OptionCreationContext()
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
        valueValidator: ValueValidator? = null,
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

    fun command(cliCommand: CliCommand) {
        commands.add { cliCommand }
    }

    fun command(init: CommandCreationContext.() -> Unit) {
        val context = CommandCreationContext()
        context.init()
        commands.add { context.generate(it) }
    }

    fun command(
        name: String,
        aliases: Array<String> = arrayOf(),
        description: String? = null,
        action: ((CliCommand, Array<String>) -> Unit)? = null,
        init: (CommandCreationContext.() -> Unit)? = null,
    ) {
        val context = CommandCreationContext()
        context.name = name
        context.aliases.addAll(aliases)
        context.description = description
        context.action = action
        if(init != null) context.init()
        commands.add { context.generate(it) }
    }

    fun generate(parent: CliCommand): CliCommand {

        if(!::name.isInitialized) throw IllegalStateException("Name is not initialized")

        return CliCommand(
            parent,
            name,
            aliases.toTypedArray(),
            description,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            action
        )
    }
}
