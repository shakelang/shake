package com.shakelang.util.commander

class ParseResult(
    val stack: MutableList<CommandStackEntry>
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
}

class CommandStackEntry(
    val alias: String,
    val command: CliCommand
) {
    val name: String = command.name
    val arguments: MutableMap<String, Value> = mutableMapOf()
    val options: MutableMap<String, Array<Value>> = mutableMapOf()
}
