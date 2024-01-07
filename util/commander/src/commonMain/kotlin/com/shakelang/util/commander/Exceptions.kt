package com.shakelang.util.commander

abstract class ValueException(message: String) : RuntimeException(message)

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