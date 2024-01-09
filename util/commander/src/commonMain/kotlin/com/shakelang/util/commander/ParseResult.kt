package com.shakelang.util.commander

class ParseResult(
    val stack: List<CommandStackEntry>,
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
            if (entry.options.containsKey(alias)) return entry.options[alias]?.toList()
            if (entry.arguments.containsKey(alias)) return listOf(entry.arguments[alias]!!)
        }
        return null
    }

    internal fun verify() {
        for (entry in stack) {
            entry.verify()
        }
    }
}

open class CommandStackEntry(
    val alias: String,
    val command: CliCommand,
    open val arguments: Map<String, Value>,
    open val options: Map<String, Array<Value>>
) {
    val name: String = command.name

    internal open fun verify() {
        if (this !is MutableCommandStackEntry) throw IllegalStateException("CommandStackEntry is not mutable")
        this.verify()
    }
}

class MutableCommandStackEntry(
    alias: String,
    command: CliCommand,
): CommandStackEntry(alias, command, mutableMapOf(), mutableMapOf()) {
    override val arguments: MutableMap<String, Value> get() = super.arguments as MutableMap<String, Value>
    override val options: MutableMap<String, Array<Value>> = super.options as MutableMap<String, Array<Value>>

    override fun verify() {
        command.verify(this)
    }
}
