package com.shakelang.util.commander

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
    val valueValidator: ValueValidator? = null
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

class CliOptionCreationContext {

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
        if (!::name.isInitialized) throw IllegalStateException("Name is not initialized")

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
