package io.github.shakelang.shake.interpreter

import io.github.shakelang.shake.util.parseutils.CompilerError
import io.github.shakelang.shake.util.parseutils.characters.position.Position
import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap

@Suppress("unused")
class InterpreterError : CompilerError {
    constructor(message: String?, details: String?, start: Position?, end: Position?, cause: Throwable?) :
            super(message!!, "InterpretationError", details!!, start!!, end!!, cause!!)

    constructor(message: String?, details: String?, map: PositionMap?, start: Int, end: Int, cause: Throwable?) :
            super(message!!, "InterpretationError", details!!, map!!, start, end, cause!!)

    constructor(message: String?, details: String?, map: PositionMap?, position: Int, cause: Throwable?) :
            this(message, details, map, position, position, cause)

    constructor(details: String?, map: PositionMap?, start: Int, end: Int, cause: Throwable?) :
            this("Error occurred in Interpreter", details, map, start, end, cause)

    constructor(details: String?, map: PositionMap?, position: Int, cause: Throwable?) :
            this("Error occurred in Interpreter", details, map, position, cause)

    constructor(message: String?, details: String?, start: Position?, end: Position?) :
            super(message!!, "InterpretationError", details!!, start!!, end!!)

    constructor(message: String?, details: String?, map: PositionMap?, start: Int, end: Int) :
            super(message!!, "InterpretationError", details!!, map!!, start, end)

    constructor(message: String?, details: String?, map: PositionMap?, position: Int) :
            this(message, details, map, position, position)

    constructor(details: String?, map: PositionMap?, start: Int, end: Int) :
            this("Error occurred in Interpreter", details, map, start, end)

    constructor(details: String?, map: PositionMap?, position: Int) :
            this("Error occurred in Interpreter", details, map, position)
}