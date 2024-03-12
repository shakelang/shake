@file:Suppress("unused")

package com.shakelang.util.parseutils.marker

import com.shakelang.util.colorlib.functional.red
import com.shakelang.util.parseutils.CompilerError
import com.shakelang.util.parseutils.characters.position.Position
import com.shakelang.util.parseutils.characters.position.PositionMap
import com.shakelang.util.parseutils.characters.source.CharacterSource
import kotlin.js.JsName
import kotlin.math.min

class PositionMarkerSettings(
    var maxLength: Int = PositionMarker.DEFAULT_MAX_LENGTH,
    var overflowBeforeFormat: (Int) -> String = { "(+$it)..." },
    var overflowAfterFormat: (Int) -> String = { "...(+$it)" },

) {
    fun copy(
        maxLength: Int = this.maxLength,
        overflowBeforeFormat: (Int) -> String = this.overflowBeforeFormat,
        overflowAfterFormat: (Int) -> String = this.overflowAfterFormat,
    ) = PositionMarkerSettings(
        maxLength,
        overflowBeforeFormat,
        overflowAfterFormat,
    )
}

class PositionMarkerFactory(
    val map: PositionMap,
    val settings: PositionMarkerSettings = PositionMarkerSettings(),
) {
    val source: CharacterSource = map.source
    fun create(start: Position, end: Position): PositionMarker {
        if (start.source != map) throw IllegalArgumentException("Start position is not in the same source as the source of the factory")
        if (end.source != map) throw IllegalArgumentException("End position is not in the same source as the source of the factory")
        if (start.line != end.line) throw IllegalArgumentException("Start and end position are not in the same line")
        val line = map.getFullLine(start.line)
        val lineStart = map.getLineStart(start.line)
        val startIndex = start.index - lineStart
        val endIndex = end.index - lineStart

        return PositionMarker(this, start.line, startIndex, endIndex, line)
    }
}

/**
 * A marker for the position of the [CompilerError]
 *
 * @since 0.1.0
 * @version 0.2.1
 */
class PositionMarker(

    /**
     * The factory of the [PositionMarker]
     *
     * @since 0.1.0
     * @version 0.2.1
     */
    @JsName("factory")
    val factory: PositionMarkerFactory,

    /**
     * The line of the [PositionMarker]
     */
    @JsName("line")
    val line: Int,

    /**
     * The start index of the [PositionMarker]
     */
    @JsName("startIndex")
    val startIndex: Int,

    /**
     * The end index of the [PositionMarker]
     */
    @JsName("endIndex")
    val endIndex: Int,

    /**
     * The line contents of the [PositionMarker]
     */
    @JsName("stringLine")
    val lineContents: String,

) {

    val source = "${factory.source.location}:$line:${startIndex + 1}"

    val settings = factory.settings.copy()

    private lateinit var _lineNumber: String
    private lateinit var _marked: String
    private lateinit var _before: String
    private lateinit var _after: String

    val lineNumber: String
        get() {
            if (!::_lineNumber.isInitialized) calculatePreview()
            return _lineNumber
        }

    val marked: String
        get() {
            if (!::_marked.isInitialized) calculatePreview()
            return _marked
        }

    val before: String
        get() {
            if (!::_before.isInitialized) calculatePreview()
            return _before
        }

    val after: String
        get() {
            if (!::_after.isInitialized) calculatePreview()
            return _after
        }

    val preview: String
        get() {
            return "$lineNumber$before$marked$after"
        }

    val coloredPreview: String
        get() {
            if (!::_marked.isInitialized) calculatePreview()
            return "$lineNumber$before${marked.red().invert()}$after"
        }

    val marker
        get() = " ".repeat(before.length + lineNumber.length) + "^".repeat(marked.length)

    val coloredMarker
        get() = " ".repeat(before.length + lineNumber.length) + "^".repeat(marked.length).red()

    private fun calculatePreview() {
        if (::_marked.isInitialized) return
        if (::_before.isInitialized) return
        if (::_after.isInitialized) return

        val lineNumber = "$line  "

        val marked = lineContents.substring(startIndex, endIndex + 1)
        val markedLength = marked.length

        // The available characters before and after the marked string
        val maxBefore = startIndex
        val maxAfter = lineContents.length - endIndex

        // The optimal padding before and after the marked string
        val optimalPadding = (settings.maxLength - markedLength - lineNumber.length) / 2

        // Calculate the padding
        val beforePadding = min(
            if (optimalPadding > maxAfter) {
                optimalPadding + optimalPadding - maxAfter
            } else {
                optimalPadding
            },
            maxBefore,
        )

        val afterPadding = min(
            if (optimalPadding > maxBefore) {
                optimalPadding + optimalPadding - maxBefore
            } else {
                optimalPadding
            },
            maxAfter,
        )

        // Calculate the before and after strings
        // there can also be (+29) (if the padding does not show the whole section)
        // these characters must be counted, which makes the calculation a bit more complex
        var beforePlusSection = ""
        var beforeSection = ""
        var beforeSkipped = maxBefore - beforePadding

        do {
            beforeSection = lineContents.substring(beforeSkipped, startIndex)
            if (beforeSkipped == 0) break
            beforePlusSection = settings.overflowBeforeFormat(beforeSkipped)
            beforeSkipped++
        } while (beforeSection.length + beforePlusSection.length > beforePadding)

        var afterPlusSection = ""
        var afterSection = ""
        var afterSkipped = maxAfter - afterPadding
        do {
            afterSection = lineContents.substring(endIndex + 1, lineContents.length - afterSkipped)
            if (afterSkipped == 0) break
            afterPlusSection = settings.overflowAfterFormat(afterSkipped)
            afterSkipped++
        } while (afterSection.length + afterPlusSection.length > afterPadding)

        this._lineNumber = lineNumber
        this._marked = marked
        this._before = beforePlusSection + beforeSection
        this._after = afterSection + afterPlusSection
    }

    companion object {
        const val DEFAULT_MAX_LENGTH = 80
    }
}

/**
 * Repeat a char multiple times
 * @param number the number of times the char should be repeated
 * @return the repeated char
 *
 * @since 0.1.0
 * @version 0.2.1
 */
private fun Char.repeat(number: Int): String {
    val string = StringBuilder()
    for (i in 0 until number) string.append(this)
    return string.toString()
}
