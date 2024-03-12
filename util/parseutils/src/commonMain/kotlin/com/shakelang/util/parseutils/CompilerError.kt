package com.shakelang.util.parseutils

import com.shakelang.util.parseutils.characters.position.Position
import com.shakelang.util.parseutils.marker.PositionMarkerFactory
import kotlin.js.JsName

/**
 * A [CompilerError] is an error thrown by a Compiler. It has functionality for
 * marking source code locations
 *
 * @since 0.1.0
 * @version 0.2.1
 */
@Suppress("unused")
open class CompilerError
constructor(
    message: String,
    /**
     * The start position of the [CompilerError]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    @JsName("start") val start: Position,
    /**
     * The end position of the [CompilerError]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    @JsName("end") val end: Position,
    cause: Throwable? = null,
) : Error(message, cause) {

    /**
     * Stringify the [CompilerError]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    override fun toString() = message!!

    private val markerFactory = PositionMarkerFactory(start.source)
    val marker = markerFactory.create(start, end)
}
