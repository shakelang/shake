package com.shakelang.shake.util.logger

fun matchesTemplate(template: String, input: String): Boolean {
    return Regex(
        template
            .replace(Regex("[.^$|()\\[\\]{}?+\\\\]")) { "\\${it.value}" } // Escape special characters
            .replace("*", ".*")) // Replace * with .*
        .matches(input)
}
class DebuggerDumpConfiguration(
    val paths: List<String>,
    val out: Logger,
) {
    fun accepts(path: String) = paths.any { matchesTemplate(it, path) }
    fun put(path: String, out: String) {
        if (accepts(path)) this.out.debug(out)
    }
}

interface Debug {
    fun debug(message: String)
    fun debug(path: String, message: String)
    operator fun invoke(message: String) = debug(message)
    fun child(name: String): Debug
}

private class DebugImpl (
    val out: MutableList<DebuggerDumpConfiguration>,
    val name: String?
) : Debug {
    private val pathPrefix = this.name?.plus(":")?:""
    override fun debug(message: String) = out.forEach { it.put(name?:"<root>", message) }
    override fun debug(path: String, message: String) = out.forEach { it.put("$pathPrefix$name", message) }
    override fun child(name: String) = DebugSubImpl(this, name)
}

private class DebugSubImpl(
    val parent: Debug,
    val name: String?
) : Debug {
    private val pathPrefix = this.name?.plus(":")?:""
    override fun debug(message: String) = parent.debug("$pathPrefix$message")
    override fun debug(path: String, message: String) = parent.debug("$pathPrefix$path", message)
    override fun child(name: String) = DebugSubImpl(this, name)
}

fun debug(
    name: String? = null,
    logger: Logger = com.shakelang.shake.util.logger.logger): Debug {
    return DebugImpl(logger, name)
}