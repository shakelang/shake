package com.shakelang.util.logger

/**
 * The global debug configuration
 * @since 0.3.0
 * @version 0.3.0
 */
object GlobalDebugConfiguration {
    /**
     * The paths that will be shown in the debug output
     * @since 0.3.0
     * @version 0.3.0
     */
    val paths = mutableListOf<String>()
}

/**
 * Check if a path matches a template
 *
 * A template can contain * as a wildcard
 *
 * @param pattern The template
 * @param input The input
 * @return if the input matches the template
 * @since 0.5.0
 * @version 0.5.0
 */
fun matchesPattern(pattern: String, input: String): Boolean {
    return Regex(
        pattern
            .replace(Regex("[.^$|()\\[\\]{}?+\\\\]")) { "\\${it.value}" } // Escape special characters
            .replace("*", ".*")
    ) // Replace * with .*
        .matches(input)
}

/**
 * A [DebuggerDumpConfiguration] is a configuration for the [debug] function
 * It contains the [Logger] to use and the paths that will be shown in the debug output
 * @param out The [Logger] to use
 * @param paths The paths that will be shown in the debug output
 * @since 0.3.0
 * @version 0.3.0
 */
class DebuggerDumpConfiguration(

    /**
     * The [Logger] to write the debug output to
     * @since 0.3.0
     * @version 0.3.0
     */
    val out: Logger,

    /**
     * The paths that will be shown in the debug output
     * @since 0.3.0
     * @version 0.3.0
     */
    val paths: List<String> = GlobalDebugConfiguration.paths
) {

    /**
     * Check if a path is accepted by this configuration
     * @param path The path to check
     * @return if the path is accepted by this configuration
     * @since 0.3.0
     * @version 0.3.0
     */
    fun accepts(path: String) = paths.any { matchesPattern(it, path) }

    /**
     * Put a message to the debug output
     * @param path The path of the message
     * @param out The message
     * @since 0.3.0
     * @version 0.3.0
     */
    fun put(path: String, out: String) {
        if (accepts(path)) this.out.debug("[$path]: $out")
    }

    /**
     * Put a message to the debug output
     * @param path The path of the message
     * @param out The message
     * @since 0.3.0
     * @version 0.3.0
     */
    fun put(path: String, out: LogMessageCreator) {
        if (accepts(path)) this.out.debug("[$path]: $out")
    }

    /**
     * Put a message to the debug output
     * @param path The path of the message
     * @param out The message
     * @since 0.3.0
     * @version 0.3.0
     */
    fun put(path: String, out: LogMessageLambda) = this.put(path, LogMessageCreator.from(out))
}

/**
 * A debugger function
 * @since 0.3.0
 * @version 0.3.0
 */
interface Debug {

    /**
     * The path prefix of the debug output
     * (If we are in a child debug context, this will be added to the front of the path)
     * @since 0.3.0
     * @version 0.3.0
     */
    val pathPrefix: String

    /**
     * The path of the debug output
     * @since 0.3.0
     * @version 0.3.0
     */
    val path: String?

    /**
     * Put a message to the debug output
     * @param message The message
     * @since 0.3.0
     * @version 0.3.0
     */
    fun debug(message: String)

    /**
     * Put a message to the debug output (with a sub-path)
     * @param path The path of the message
     * @param message The message
     * @since 0.3.0
     * @version 0.3.0
     */
    fun debug(path: String, message: String)

    /**
     * Put a message to the debug output (using lazy-generation of the message)
     * @param creator The message
     * @since 0.3.0
     * @version 0.3.0
     */
    fun debug(creator: LogMessageCreator)

    /**
     * Put a message to the debug output (using lazy-generation of the message) (with a sub-path)
     * @param path The path of the message
     * @param creator The message
     * @since 0.3.0
     * @version 0.3.0
     */
    fun debug(path: String, creator: LogMessageCreator)

    /**
     * Put a message to the debug output (using lazy-generation of the message)
     * @param creator The message
     * @since 0.3.0
     * @version 0.3.0
     */
    fun debug(creator: LogMessageLambda) = debug(LogMessageCreator.from(creator))

    /**
     * Put a message to the debug output (using lazy-generation of the message) (with a sub-path)
     * @param path The path of the message
     * @param creator The message
     * @since 0.3.0
     * @version 0.3.0
     */
    fun debug(path: String, creator: LogMessageLambda) = debug(path, LogMessageCreator.from(creator))


    /**
     * Put a message to the debug output
     * @param message The message
     * @return The child debug context
     * @since 0.3.0
     * @version 0.3.0
     */
    operator fun invoke(message: String) = debug(message)

    /**
     * Put a message to the debug output (with a sub-path)
     * @param path The path of the message
     * @param message The message
     * @return The child debug context
     * @since 0.3.0
     * @version 0.3.0
     */
    operator fun invoke(path: String, message: String) = debug(path, message)

    /**
     * Put a message to the debug output (using lazy-generation of the message)
     * @param creator The message
     * @return The child debug context
     * @since 0.3.0
     * @version 0.3.0
     */
    operator fun invoke(creator: LogMessageCreator) = debug(creator)

    /**
     * Put a message to the debug output (using lazy-generation of the message) (with a sub-path)
     * @param path The path of the message
     * @param creator The message
     * @return The child debug context
     * @since 0.3.0
     * @version 0.3.0
     */
    operator fun invoke(path: String, creator: LogMessageCreator) = debug(path, creator)

    /**
     * Put a message to the debug output (using lazy-generation of the message)
     * @param creator The message
     * @return The child debug context
     * @since 0.3.0
     * @version 0.3.0
     */
    operator fun invoke(creator: LogMessageLambda) = debug(creator)

    /**
     * Put a message to the debug output (using lazy-generation of the message) (with a sub-path)
     * @param path The path of the message
     * @param creator The message
     * @return The child debug context
     * @since 0.3.0
     * @version 0.3.0
     */
    operator fun invoke(path: String, creator: LogMessageLambda) = debug(path, creator)


    /**
     * Create a child debug context
     * @param name The name of the child debug context
     * @return The child debug context
     * @since 0.3.0
     * @version 0.3.0
     */
    fun child(vararg name: String): Debug
}

/**
 * A debug implementation
 * @param out The [Logger] to write the debug output to
 * @param path The path of the debug output
 * @since 0.3.0
 * @version 0.3.0
 */
private class DebugImpl(

    /**
     * The dump configurations for the debug output
     * @since 0.3.0
     * @version 0.3.0
     */
    val out: MutableList<DebuggerDumpConfiguration>,

    /**
     * The path of the debug output
     * @since 0.3.0
     * @version 0.3.0
     */
    override val path: String?

) : Debug {

    /**
     * The path prefix of the debug output
     * (If we are in a child debug context, this will be added to the front of the path)
     * @since 0.3.0
     * @version 0.3.0
     */
    override val pathPrefix = this.path?.plus(":") ?: ""

    /**
     * Put a message to the debug output
     * @param message The message
     * @since 0.3.0
     * @version 0.3.0
     */
    override fun debug(message: String) = out.forEach { it.put(path ?: "<root>", message) }

    /**
     * Put a message to the debug output (with a sub-path)
     * @param path The path of the message
     * @param message The message
     * @since 0.3.0
     * @version 0.3.0
     */
    override fun debug(path: String, message: String) = out.forEach { it.put("$pathPrefix$path", message) }


    /**
     * Put a message to the debug output (using lazy-generation of the message)
     * @param creator The message
     * @since 0.3.0
     * @version 0.3.0
     */
    override fun debug(creator: LogMessageCreator) = out.forEach { it.put(path ?: "<root>", creator) }

    /**
     * Put a message to the debug output (using lazy-generation of the message) (with a sub-path)
     * @param path The path of the message
     * @param creator The message
     * @since 0.3.0
     * @version 0.3.0
     */
    override fun debug(path: String, creator: LogMessageCreator) = out.forEach { it.put("$pathPrefix$path", creator) }


    /**
     * Create a child debug context
     * @param name The name of the child debug context
     * @return The child debug context
     * @since 0.3.0
     * @version 0.3.0
     */
    override fun child(vararg name: String) = DebugSubImpl(this, name.joinToString(":"))
}


/**
 * A debug implementation for a child debug context
 * @param parent The parent debug context
 * @param path The path of the debug output
 * @since 0.3.0
 * @version 0.3.0
 */
private class DebugSubImpl(

    /**
     * The parent debug context
     * @since 0.3.0
     * @version 0.3.0
     */
    val parent: Debug,

    /**
     * The path of the debug output
     * @since 0.3.0
     * @version 0.3.0
     */
    override val path: String?
) : Debug {

    /**
     * The path prefix of the debug output
     * (If we are in a child debug context, this will be added to the front of the path)
     * @since 0.3.0
     * @version 0.3.0
     */
    override val pathPrefix = this.path?.plus(":") ?: ""

    /**
     * Put a message to the debug output
     * @param message The message
     * @since 0.3.0
     * @version 0.3.0
     */
    override fun debug(message: String) = parent.debug(pathPrefix, message)

    /**
     * Put a message to the debug output (with a sub-path)
     * @param path The path of the message
     * @param message The message
     * @since 0.3.0
     * @version 0.3.0
     */
    override fun debug(path: String, message: String) = parent.debug("$pathPrefix$path", message)


    /**
     * Put a message to the debug output (using lazy-generation of the message)
     * @param creator The message
     * @since 0.3.0
     * @version 0.3.0
     */
    override fun debug(creator: LogMessageCreator) = parent.debug(pathPrefix, creator)

    /**
     * Put a message to the debug output (using lazy-generation of the message) (with a sub-path)
     * @param path The path of the message
     * @param creator The message
     * @since 0.3.0
     * @version 0.3.0
     */
    override fun debug(path: String, creator: LogMessageCreator) = parent.debug("$pathPrefix$path", creator)


    /**
     * Create a child debug context
     * @param name The name of the child debug context
     * @return The child debug context
     * @since 0.3.0
     * @version 0.3.0
     */
    override fun child(vararg name: String) = DebugSubImpl(this, name.joinToString(":"))
}

/**
 * Create a debug context
 * @param name The name of the debug context
 * @param logger The [Logger] to write the debug output to
 * @return The debug context
 * @since 0.3.0
 * @version 0.3.0
 */
fun debug(
    name: String? = null,
    logger: Logger = com.shakelang.util.logger.logger
): Debug {
    return DebugImpl(mutableListOf(DebuggerDumpConfiguration(logger)), name)
}
