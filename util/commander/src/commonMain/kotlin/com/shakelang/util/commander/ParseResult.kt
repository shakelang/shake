package com.shakelang.util.commander

class ParseResult(
    val stack: List<CommandStackEntry>,
) {
    fun getOptionValueByName(alias: String): Array<CommanderValue>? {
        for (entry in stack) {
            val value = entry.options[alias]
            if (value != null) return value
        }
        return null
    }

    fun getArgumentValueByName(alias: String): CommanderValue? {
        for (entry in stack) {
            val value = entry.arguments[alias]
            if (value != null) return value
        }
        return null
    }

    fun getValueByName(alias: String): List<CommanderValue>? {
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
    val command: CommanderCommand,
    open val arguments: Map<String, CommanderValue>,
    open val options: Map<String, Array<CommanderValue>>,
) {
    val name: String = command.name

    internal open fun verify() {
        if (this !is MutableCommandStackEntry) throw IllegalStateException("CommandStackEntry is not mutable")
        this.verify()
    }
}

class MutableCommandStackEntry(
    alias: String,
    command: CommanderCommand,
) : CommandStackEntry(alias, command, mutableMapOf(), mutableMapOf()) {
    override val arguments: MutableMap<String, CommanderValue> get() = super.arguments as MutableMap<String, CommanderValue>
    override val options: MutableMap<String, Array<CommanderValue>> = super.options as MutableMap<String, Array<CommanderValue>>

    override fun verify() {
        command.verify(this)
    }
}
