package com.shakelang.util.commander

class CliArgument(
    val command: CliCommand,
    val name: String,
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
        builder.append("<$name>")
        if (valueName != null) builder.append(" <$valueName>")
        return builder.toString()
    }
}

class CliArgumentCreationContext {

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
        if (!::name.isInitialized) throw IllegalStateException("Name is not initialized")

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
