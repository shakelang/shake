package com.github.shakelang.shake.util

import com.github.shakelang.shake.util.Formatting.FGColor
import com.github.shakelang.shake.util.characterinput.position.Position
import com.github.shakelang.shake.util.characterinput.position.PositionMap

@Suppress("unused")
open class CompilerError : Error {
    val exceptionName: String
    val details: String
    val start: Position
    val end: Position
    val marker: ErrorMarker

    private constructor(
        message: String,
        marker: ErrorMarker,
        exceptionName: String,
        details: String,
        start: Position,
        end: Position,
        cause: Throwable
    ) : super("$message: $details\n\n$marker\n", cause) {
        this.exceptionName = exceptionName
        this.details = details
        this.start = start
        this.end = end
        this.marker = marker
    }

    private constructor(
        message: String, marker: ErrorMarker, exceptionName: String, details: String, map: PositionMap, start: Int, end: Int,
        cause: Throwable
    ) : super("$message: $details\n\n$marker\n", cause) {
        this.exceptionName = exceptionName
        this.details = details
        this.start = map.resolve(start)
        this.end = map.resolve(end)
        this.marker = marker
    }

    constructor(
        message: String,
        exceptionName: String,
        details: String,
        start: Position,
        end: Position,
        cause: Throwable
    ) : this(message, createPositionMarker(maxLength, start, end), exceptionName, details, start, end, cause)

    constructor(
        message: String,
        exceptionName: String,
        details: String,
        map: PositionMap,
        start: Int,
        end: Int,
        cause: Throwable
    ) : this(message, createPositionMarker(maxLength, map.resolve(start).also { start_zw = it },
        map.resolve(end).also {
            end_zw = it
        }), exceptionName, details, start_zw!!, end_zw!!, cause
    ) {
        end_zw = null
        start_zw = end_zw
    }

    private constructor(
        message: String,
        marker: ErrorMarker,
        exceptionName: String,
        details: String,
        start: Position,
        end: Position
    ) : super("$message: $details\n\n$marker\n") {
        this.exceptionName = exceptionName
        this.details = details
        this.start = start
        this.end = end
        this.marker = marker
    }

    private constructor(
        message: String,
        marker: ErrorMarker,
        exceptionName: String,
        details: String,
        map: PositionMap,
        start: Int,
        end: Int
    ) : super("$message: $details\n\n$marker\n") {
        this.exceptionName = exceptionName
        this.details = details
        this.start = map.resolve(start)
        this.end = map.resolve(end)
        this.marker = marker
    }

    constructor(message: String, exceptionName: String, details: String, start: Position, end: Position) : this(
        message, createPositionMarker(
            maxLength, start, end
        ), exceptionName, details, start, end
    )

    constructor(message: String, exceptionName: String, details: String, map: PositionMap, start: Int, end: Int) : this(
        message, createPositionMarker(maxLength, map.resolve(start).also { start_zw = it },
            map.resolve(end).also {
                end_zw = it
            }), exceptionName, details, start_zw!!, end_zw!!
    ) {
        end_zw = null
        start_zw = end_zw
    }

    override fun toString(): String {
        return message!!
    }

    class ErrorMarker(val source: String, val colorPreview: String, val preview: String, val marker: String) {
        override fun toString(): String {
            return "at $source\n$colorPreview\n$marker"
        }
    }

    companion object {
        var maxLength = 60
        private var start_zw: Position? = null
        private var end_zw: Position? = null
        private fun createPositionMarker(maxLength: Int, pos1: Position, pos2: Position): ErrorMarker {

            // Check requirements
            if (pos1.source != pos2.source) throw Error("The two have to be located in the same source")
            if (pos1.line != pos2.line) throw Error("The two positions that should be marked have to be in the same line")
            if (pos1.column > pos2.column) throw Error("Position 1 must be before Position 2")


            // Line start (linenumber + 2 spaces)
            val lineStr = pos1.line.toString() + "  "

            // Length of the highlighted section
            val highlighted = pos2.column - pos1.column + 1

            // The maximum amount of characters that will be shown around the highlighted section
            val maxAround = maxLength - highlighted - lineStr.length
            val before = maxAround / 2 + maxAround % 2
            val after = maxAround / 2


            // The available tokens before the highlighted section
            val before2 = pos1.column - 1

            // The available tokens after the highlighted section
            val after2 = pos2.source.getAfterInLine(pos2)

            // Take the smallest value and use it
            var realBefore = smallest(before, before2)
            var realAfter = smallest(after, after2 + 1)

            // Get the differences (to display if there are tokens that can't be displayed)
            var beforeDif = before2 - realBefore
            var afterDif = after2 - realAfter

            // Resolve numbers if there is a non-displayed part
            if (beforeDif > 0) {
                val baseLen = beforeDif.toString().length
                var len = baseLen + 4
                realBefore -= if (len.toString().length != baseLen) ++len else len
                beforeDif += len
            }
            if (afterDif > 0) {
                val baseLen = afterDif.toString().length
                var len = baseLen + 4
                realAfter -= if (len.toString().length != baseLen) ++len else len
                afterDif += len
            }


            // The start of the line
            val start = (lineStr
                    + (if (beforeDif > 0) "+$beforeDif..." else "")
                    + pos1.source.source[pos1.index - realBefore, pos1.index].concatToString()
                .replace("\t".toRegex(), " "))

            // The end of the line
            val end = (pos1.source.source[pos2.index + 1, pos2.index + realAfter].concatToString()
                .replace("\t".toRegex(), " ")
                .replace("\n".toRegex(), " ")
                    + if (afterDif > 0) "...+$afterDif" else "")

            // Generate end-string
            return ErrorMarker(
                pos1.source.location + ':' + pos1.line + ':' + pos1.column,
                start + Formatting.INVERT + FGColor.RED
                        + pos1.source.source[pos1.index, pos2.index + 1].concatToString() + Formatting.RESET + end,
                start + pos1.source.source[pos1.index, pos2.index + 1].concatToString() + end,
                strRepeat(' ', start.length) + strRepeat('^', highlighted)
            )
        }

        private fun strRepeat(c: Char, number: Int): String {
            val string = StringBuilder()
            for (i in 0 until number) string.append(c)
            return string.toString()
        }

        private fun smallest(vararg arr: Int): Int {
            var smallest = arr[0]
            for (i in 1 until arr.size) if (arr[i] < smallest) smallest = arr[i]
            return smallest
        }
    }
}