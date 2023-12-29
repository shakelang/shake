package com.shakelang.util.logger

object GlobalDebugConfiguration {
    /**
     * The paths that will be shown in the debug output
     */
    val paths = mutableListOf<String>()
}

fun matchesTemplate(template: String, input: String): Boolean {
    return Regex(
        template
            .replace(Regex("[.^$|()\\[\\]{}?+\\\\]")) { "\\${it.value}" } // Escape special characters
            .replace("*", ".*")
    ) // Replace * with .*
        .matches(input)
}

class DebuggerDumpConfiguration(
    val out: Logger,
    val paths: List<String> = GlobalDebugConfiguration.paths
) {
    fun accepts(path: String) = paths.any { matchesTemplate(it, path) }
    fun put(path: String, out: String) {
        if (accepts(path)) this.out.debug("[$path]: $out")
    }

    fun put(path: String, out: LogMessageCreator) {
        if (accepts(path)) this.out.debug("[$path]: $out")
    }

    fun put(path: String, out: LogMessageLambda) = this.put(path, LogMessageCreator.from(out))
}

interface Debug {
    val pathPrefix: String
    val path: String?
    fun debug(message: String)
    fun debug(path: String, message: String)
    fun debug(creator: LogMessageCreator)
    fun debug(path: String, creator: LogMessageCreator)
    fun debug(creator: LogMessageLambda) = debug(LogMessageCreator.from(creator))
    fun debug(path: String, creator: LogMessageLambda) = debug(path, LogMessageCreator.from(creator))

    operator fun invoke(message: String) = debug(message)
    operator fun invoke(path: String, message: String) = debug(path, message)
    operator fun invoke(creator: LogMessageCreator) = debug(creator)
    operator fun invoke(path: String, creator: LogMessageCreator) = debug(path, creator)
    operator fun invoke(creator: LogMessageLambda) = debug(creator)
    operator fun invoke(path: String, creator: LogMessageLambda) = debug(path, creator)

    fun child(vararg name: String): Debug
}

private class DebugImpl(
    val out: MutableList<DebuggerDumpConfiguration>,
    override val path: String?
) : Debug {
    override val pathPrefix = this.path?.plus(":") ?: ""
    override fun debug(message: String) = out.forEach { it.put(path ?: "<root>", message) }
    override fun debug(path: String, message: String) = out.forEach { it.put("$pathPrefix$path", message) }

    override fun debug(creator: LogMessageCreator) = out.forEach { it.put(path ?: "<root>", creator) }
    override fun debug(path: String, creator: LogMessageCreator) = out.forEach { it.put("$pathPrefix$path", creator) }

    override fun child(vararg name: String) = DebugSubImpl(this, name.joinToString(":"))
}

private class DebugSubImpl(
    val parent: Debug,
    override val path: String?
) : Debug {
    override val pathPrefix = this.path?.plus(":") ?: ""
    override fun debug(message: String) = parent.debug(pathPrefix, message)
    override fun debug(path: String, message: String) = parent.debug("$pathPrefix$path", message)

    override fun debug(creator: LogMessageCreator) = parent.debug(pathPrefix, creator)
    override fun debug(path: String, creator: LogMessageCreator) = parent.debug("$pathPrefix$path", creator)


    override fun child(vararg name: String) = DebugSubImpl(this, name.joinToString(":"))
}

fun debug(
    name: String? = null,
    logger: Logger = com.shakelang.util.logger.logger
): Debug {
    return DebugImpl(mutableListOf(DebuggerDumpConfiguration(logger)), name)
}
