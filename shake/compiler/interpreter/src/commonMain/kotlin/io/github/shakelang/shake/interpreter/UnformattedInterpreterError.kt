package io.github.shakelang.shake.interpreter

@Suppress("unused")
class UnformattedInterpreterError : Error {
    constructor() : super()
    constructor(error: String?, cause: Throwable?) : super(error, cause)
    constructor(error: String?) : super(error)
    constructor(cause: Throwable?) : super(cause)
}