package com.shakelang.util.commander

abstract class ValueException(message: String) : CliException(message)

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

open class CliException(message: String) : RuntimeException(message)

class CliMissingArgumentException(message: String) : CliException(message)
class CliMissingOptionException(message: String) : CliException(message)
class CliMissingOptionValueException(message: String) : CliException(message)
class CliUnknownOptionException(message: String) : CliException(message)
class CliUnknownArgumentException(message: String) : CliException(message)
class CliUnknownSubCommandException(message: String) : CliException(message)
