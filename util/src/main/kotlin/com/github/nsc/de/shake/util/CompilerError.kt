package com.github.nsc.de.shake.util

import com.github.nsc.de.shake.lexer.characterinput.position.Position
import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap
import java.lang.StringBuilder
import com.github.nsc.de.shake.util.Formatting.FGColor
import java.lang.Error

@Suppress("unused")
open class CompilerError : Error {
    val name: String
    val details: String
    val start: Position
    val end: Position
    val marker: ErrorMarker

    private constructor(
        message: String, marker: ErrorMarker, name: String, details: String, start: Position, end: Position,
        cause: Throwable
    ) : super(String.format("%s: %s%n%n%s%n", message, details, marker), cause) {
        this.name = name
        this.details = details
        this.start = start
        this.end = end
        this.marker = marker
    }

    private constructor(
        message: String, marker: ErrorMarker, name: String, details: String, map: PositionMap, start: Int, end: Int,
        cause: Throwable
    ) : super(String.format("%s: %s%n%n%s%n", message, details, marker), cause) {
        this.name = name
        this.details = details
        this.start = map.resolve(start)
        this.end = map.resolve(end)
        this.marker = marker
    }

    constructor(
        message: String, name: String, details: String, start: Position, end: Position,
        cause: Throwable
    ) : this(message, createPositionMarker(maxLength, start, end), name, details, start, end, cause)

    constructor(
        message: String, name: String, details: String, map: PositionMap, start: Int, end: Int,
        cause: Throwable
    ) : this(message, createPositionMarker(maxLength, map.resolve(start).also { start_zw = it },
        map.resolve(end).also {
            end_zw = it
        }), name, details, start_zw!!, end_zw!!, cause
    ) {
        end_zw = null
        start_zw = end_zw
    }

    private constructor(
        message: String,
        marker: ErrorMarker,
        name: String,
        details: String,
        start: Position,
        end: Position
    ) : super(
        String.format("%s: %s%n%n%s%n", message, details, marker)
    ) {
        this.name = name
        this.details = details
        this.start = start
        this.end = end
        this.marker = marker
    }

    private constructor(
        message: String,
        marker: ErrorMarker,
        name: String,
        details: String,
        map: PositionMap,
        start: Int,
        end: Int
    ) : super(
        String.format("%s: %s%n%n%s%n", message, details, marker)
    ) {
        this.name = name
        this.details = details
        this.start = map.resolve(start)
        this.end = map.resolve(end)
        this.marker = marker
    }

    constructor(message: String, name: String, details: String, start: Position, end: Position) : this(
        message, createPositionMarker(
            maxLength, start, end
        ), name, details, start, end
    ) {
    }

    constructor(message: String, name: String, details: String, map: PositionMap, start: Int, end: Int) : this(
        message, createPositionMarker(maxLength, map.resolve(start).also { start_zw = it },
            map.resolve(end).also {
                end_zw = it
            }), name, details, start_zw!!, end_zw!!
    ) {
        end_zw = null
        start_zw = end_zw
    }

    override fun toString(): String {
        return message!!
    }

    override fun printStackTrace() {
        super.printStackTrace()
    }

    class ErrorMarker(val source: String, val colorPreview: String, val preview: String, val marker: String) {
        override fun toString(): String {
            return "at " + source + System.lineSeparator() + colorPreview + System.lineSeparator() + marker
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
            val line_str = pos1.line.toString() + "  "

            // Length of the highlighted section
            val highlighted = pos2.column - pos1.column + 1

            // The maximum amount of characters that will be shown around the highlighted section
            val maxAround = maxLength - highlighted - line_str.length
            val before = maxAround / 2 + maxAround % 2
            val after = maxAround / 2


            // The available tokens before the highlighted section
            val before2 = pos1.column - 1

            // The available tokens after the highlighted section
            val after2 = pos2.source.getAfterInLine(pos2)

            // Take the smallest value and use it
            var real_before = smallest(before, before2)
            var real_after = smallest(after, after2 + 1)

            // Get the differences (to display if there are tokens that can't be displayed)
            var before_dif = before2 - real_before
            var after_dif = after2 - real_after

            // Resolve numbers if there is a non-displayed part
            if (before_dif > 0) {
                val baseLen = before_dif.toString().length
                var len = baseLen + 4
                real_before -= if (len.toString().length != baseLen) ++len else len
                before_dif += len
            }
            if (after_dif > 0) {
                val baseLen = after_dif.toString().length
                var len = baseLen + 4
                real_after -= if (len.toString().length != baseLen) ++len else len
                after_dif += len
            }


            // The start of the line
            val start = (line_str
                    + (if (before_dif > 0) "+$before_dif..." else "")
                    + String(pos1.source.source[pos1.index - real_before, pos1.index]).replace("\t".toRegex(), " "))

            // The end of the line
            val end = (String(pos1.source.source[pos2.index + 1, pos2.index + real_after]).replace("\t".toRegex(), " ")
                .replace("\n".toRegex(), " ")
                    + if (after_dif > 0) "...+$after_dif" else "")

            // Generate end-string
            return ErrorMarker(
                pos1.source.location + ':' + pos1.line + ':' + pos1.column,
                start + Formatting.INVERT + FGColor.RED + String(pos1.source.source[pos1.index, pos2.index + 1]) + Formatting.RESET + end,
                start + String(pos1.source.source[pos1.index, pos2.index + 1]) + end,
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