package com.shakelang.util.commander

/**
 * Base class for all exceptions thrown by the commander
 * @param message The message of the exception
 * @since 0.1.0
 * @version 0.1.0
 */
open class CommanderException(message: String) : RuntimeException(message)

/**
 * Base class for exceptions thrown when a value is not valid
 * @since 0.1.0
 * @version 0.1.0
 */
open class CommanderValueException(message: String) : CommanderException(message)

/**
 * Error thrown when an argument is not valid
 * @since 0.1.0
 * @version 0.1.0
 */
class ValueValidationException(message: String) : CommanderValueException(message)

/**
 * Error thrown in runtime when the syntax of the command is not valid
 * @since 0.1.0
 * @version 0.1.0
 */
open class CommanderSyntaxException(message: String) : CommanderException(message)

/**
 * Error thrown when a required argument is missing
 * @since 0.1.0
 * @version 0.1.0
 */
class CommanderMissingArgumentException(message: String) : CommanderException(message)

/**
 * Error thrown when a required option is missing
 * @since 0.1.0
 * @version 0.1.0
 */
class CommanderMissingOptionException(message: String) : CommanderException(message)

/**
 * Error thrown when an option is missing a value
 * @since 0.1.0
 * @version 0.1.0
 */
class CommanderMissingOptionValueException(message: String) : CommanderException(message)

/**
 * Error thrown when a non-existing option is passed
 * @since 0.1.0
 * @version 0.1.0
 */
class CommanderUnknownOptionException(message: String) : CommanderException(message)

/**
 * Error thrown when an argument is passed, but not expected
 * @since 0.1.0
 * @version 0.1.0
 */
class CommanderUnknownArgumentException(message: String) : CommanderException(message)

/**
 * Error thrown when a sub-command is passed, but not expected
 * @since 0.1.0
 * @version 0.1.0
 */
class CommanderUnknownSubCommandException(message: String) : CommanderException(message)
