package io.github.shakelang.colorlib.functional

import io.github.shakelang.colorlib.Formatting

interface FormattedStringObject {
    override fun toString(): String
    fun string() = toString()
    fun extends(format: FormattedString): FormattedStringObject


    companion object {
        fun wrap(string: String) = FormattedStringObjectString(string)
    }
}

class FormattedStringObjectString (
    val value: String
) : FormattedStringObject {
    override fun toString(): String {
        return this.value
    }

    override fun extends(format: FormattedString) = this
}

class FormattedString(

    contents: List<FormattedStringObject>,
    isBold: Boolean? = null,
    isItalic: Boolean? = null,
    isUnderlined: Boolean? = null,
    isStrikethrough: Boolean? = null,
    isInverted: Boolean? = null,
    val color: Formatting.FGColor? = null,
    val backgroundColor: Formatting.BGColor? = null

) : FormattedStringObject {

    val strings = contents.map { it.extends(this) }
    private val _isBold: Boolean? = isBold
    private val _isItalic: Boolean? = isItalic
    private val _isUnderlined: Boolean? = isUnderlined
    private val _isStrikethrough: Boolean? = isStrikethrough
    private val _isInverted: Boolean? = isInverted

    val isBold get() = _isBold ?: false
    val isItalic get() = _isItalic ?: false
    val isUnderlined get() = _isUnderlined ?: false
    val isStrikethrough get() = _isStrikethrough ?: false
    val isInverted get() = _isInverted ?: false


    constructor(
        from: FormattedString,
        isBold: Boolean? = from.isBold,
        isItalic: Boolean? = from.isItalic,
        isUnderlined: Boolean? = from.isUnderlined,
        isStrikethrough: Boolean? = from.isStrikethrough,
        isInverted: Boolean? = from.isInverted,
        color: Formatting.FGColor? = from.color,
        backgroundColor: Formatting.BGColor? = from.backgroundColor
    ) : this(from.strings, isBold, isItalic, isUnderlined, isStrikethrough, isInverted, color, backgroundColor)

    val isFormatted: Boolean
        get() = isBold
                || isItalic
                || isUnderlined
                || isStrikethrough
                || isInverted
                || color != null
                || backgroundColor != null

    override fun toString(): String {
        var str = strings.map { it.string() }

        if (isBold) str = str.map { "${Formatting.BOLD}$it" }
        if (isItalic) str = str.map { "${Formatting.ITALIC}$it" }
        if (isUnderlined) str = str.map { "${Formatting.UNDERLINE}$it" }
        if (isStrikethrough) str = str.map { "${Formatting.STRIKETHROUGH}$it" }
        if (isInverted) str = str.map { "${Formatting.INVERT}$it" }
        if (color != null) str = str.map { "${color}$it" }
        if (backgroundColor != null) str = str.map { "${backgroundColor}$it" }
        if (isFormatted) str = str.map { "$it${Formatting.RESET}" }

        return str.joinToString()
    }

    fun reset() = FormattedString(
        this,
        isBold = false,
        isItalic = false,
        isUnderlined = false,
        isStrikethrough = false,
        isInverted = false,
        color = null,
        backgroundColor = null
    )

    fun bold() = FormattedString(this, isBold = true)
    fun italic() = FormattedString(this, isItalic = true)
    fun underline() = FormattedString(this, isUnderlined = true)
    fun strikethrough() = FormattedString(this, isStrikethrough = true)
    fun invert() = FormattedString(this, isInverted = true)

    fun color(color: Formatting.FGColor) = FormattedString(this, color = color)
    fun fg(color: Formatting.FGColor) = color(color)
    fun fgColor(color: Formatting.FGColor) = color(color)

    fun backgroundColor(color: Formatting.BGColor) = FormattedString(this, backgroundColor = color)
    fun bg(color: Formatting.BGColor) = backgroundColor(color)
    fun bgColor(color: Formatting.BGColor) = backgroundColor(color)

    fun black() = color(Formatting.FGColor.BLACK)
    fun red() = color(Formatting.FGColor.RED)
    fun green() = color(Formatting.FGColor.GREEN)
    fun yellow() = color(Formatting.FGColor.YELLOW)
    fun blue() = color(Formatting.FGColor.BLUE)
    fun purple() = color(Formatting.FGColor.PURPLE)
    fun cyan() = color(Formatting.FGColor.CYAN)
    fun white() = color(Formatting.FGColor.WHITE)
    fun grey() = color(Formatting.FGColor.BRIGHT_BLACK)
    fun brightBlack() = color(Formatting.FGColor.BRIGHT_BLACK)
    fun brightRed() = color(Formatting.FGColor.BRIGHT_RED)
    fun brightGreen() = color(Formatting.FGColor.BRIGHT_GREEN)
    fun brightYellow() = color(Formatting.FGColor.BRIGHT_YELLOW)
    fun brightBlue() = color(Formatting.FGColor.BRIGHT_BLUE)
    fun brightPurple() = color(Formatting.FGColor.BRIGHT_PURPLE)
    fun brightCyan() = color(Formatting.FGColor.BRIGHT_CYAN)
    fun brightWhite() = color(Formatting.FGColor.BRIGHT_WHITE)

    fun fgBlack() = color(Formatting.FGColor.BLACK)
    fun fgRed() = color(Formatting.FGColor.RED)
    fun fgGreen() = color(Formatting.FGColor.GREEN)
    fun fgYellow() = color(Formatting.FGColor.YELLOW)
    fun fgBlue() = color(Formatting.FGColor.BLUE)
    fun fgPurple() = color(Formatting.FGColor.PURPLE)
    fun fgCyan() = color(Formatting.FGColor.CYAN)
    fun fgWhite() = color(Formatting.FGColor.WHITE)
    fun fgGrey() = color(Formatting.FGColor.BRIGHT_BLACK)
    fun fgBrightBlack() = color(Formatting.FGColor.BRIGHT_BLACK)
    fun fgBrightRed() = color(Formatting.FGColor.BRIGHT_RED)
    fun fgBrightGreen() = color(Formatting.FGColor.BRIGHT_GREEN)
    fun fgBrightYellow() = color(Formatting.FGColor.BRIGHT_YELLOW)
    fun fgBrightBlue() = color(Formatting.FGColor.BRIGHT_BLUE)
    fun fgBrightPurple() = color(Formatting.FGColor.BRIGHT_PURPLE)
    fun fgBrightCyan() = color(Formatting.FGColor.BRIGHT_CYAN)
    fun fgBrightWhite() = color(Formatting.FGColor.BRIGHT_WHITE)

    fun bgBlack() = backgroundColor(Formatting.BGColor.BLACK)
    fun bgRed() = backgroundColor(Formatting.BGColor.RED)
    fun bgGreen() = backgroundColor(Formatting.BGColor.GREEN)
    fun bgYellow() = backgroundColor(Formatting.BGColor.YELLOW)
    fun bgBlue() = backgroundColor(Formatting.BGColor.BLUE)
    fun bgPurple() = backgroundColor(Formatting.BGColor.PURPLE)
    fun bgCyan() = backgroundColor(Formatting.BGColor.CYAN)
    fun bgWhite() = backgroundColor(Formatting.BGColor.WHITE)
    fun bgGrey() = backgroundColor(Formatting.BGColor.BRIGHT_BLACK)
    fun bgBrightBlack() = backgroundColor(Formatting.BGColor.BRIGHT_BLACK)
    fun bgBrightRed() = backgroundColor(Formatting.BGColor.BRIGHT_RED)
    fun bgBrightGreen() = backgroundColor(Formatting.BGColor.BRIGHT_GREEN)
    fun bgBrightYellow() = backgroundColor(Formatting.BGColor.BRIGHT_YELLOW)
    fun bgBrightBlue() = backgroundColor(Formatting.BGColor.BRIGHT_BLUE)
    fun bgBrightPurple() = backgroundColor(Formatting.BGColor.BRIGHT_PURPLE)
    fun bgBrightCyan() = backgroundColor(Formatting.BGColor.BRIGHT_CYAN)
    fun bgBrightWhite() = backgroundColor(Formatting.BGColor.BRIGHT_WHITE)

    override fun extends(format: FormattedString) = FormattedString(
        this,
        isBold = _isBold ?: format._isBold,
        isItalic = _isItalic ?: format._isItalic,
        isUnderlined = _isUnderlined ?: format._isUnderlined,
        isStrikethrough = _isStrikethrough ?: format._isStrikethrough,
        isInverted = _isInverted ?: format._isInverted,
        color = color ?: format.color,
        backgroundColor = backgroundColor ?: format.backgroundColor
    )

    companion object {
        fun wrap(string: String) = FormattedString(listOf(FormattedStringObject.wrap(string)))
        fun wrap(vararg strings: String) = FormattedString(strings.map { FormattedStringObject.wrap(it) })

        fun from(string: String) = wrap(string)
        fun from(vararg strings: String) = wrap(*strings)

    }
}

fun String.format(
    isBold: Boolean? = null,
    isItalic: Boolean? = null,
    isUnderlined: Boolean? = null,
    isStrikethrough: Boolean? = null,
    isInverted: Boolean? = null,
    color: Formatting.FGColor? = null,
    backgroundColor: Formatting.BGColor? = null
) = FormattedString(
    listOf(FormattedStringObject.wrap(this)),
    isBold,
    isItalic,
    isUnderlined,
    isStrikethrough,
    isInverted,
    color,
    backgroundColor
)

fun String.color(color: Formatting.FGColor) = format(color = color)
fun String.fg(color: Formatting.FGColor) = format(color = color)
fun String.fgColor(color: Formatting.FGColor) = format(color = color)

fun String.bg(color: Formatting.BGColor) = format(backgroundColor = color)
fun String.bgColor(color: Formatting.BGColor) = format(backgroundColor = color)
fun String.backgroundColor(color: Formatting.BGColor) = format(backgroundColor = color)

fun String.bold() = format(isBold = true)
fun String.italic() = format(isItalic = true)
fun String.underline() = format(isUnderlined = true)
fun String.strikethrough() = format(isStrikethrough = true)
fun String.invert() = format(isInverted = true)

fun String.black() = format(color = Formatting.FGColor.BLACK)
fun String.red() = format(color = Formatting.FGColor.RED)
fun String.green() = format(color = Formatting.FGColor.GREEN)
fun String.yellow() = format(color = Formatting.FGColor.YELLOW)
fun String.blue() = format(color = Formatting.FGColor.BLUE)
fun String.purple() = format(color = Formatting.FGColor.PURPLE)
fun String.cyan() = format(color = Formatting.FGColor.CYAN)
fun String.white() = format(color = Formatting.FGColor.WHITE)
fun String.grey() = format(color = Formatting.FGColor.BRIGHT_BLACK)
fun String.brightBlack() = format(color = Formatting.FGColor.BRIGHT_BLACK)
fun String.brightRed() = format(color = Formatting.FGColor.BRIGHT_RED)
fun String.brightGreen() = format(color = Formatting.FGColor.BRIGHT_GREEN)
fun String.brightYellow() = format(color = Formatting.FGColor.BRIGHT_YELLOW)
fun String.brightBlue() = format(color = Formatting.FGColor.BRIGHT_BLUE)
fun String.brightPurple() = format(color = Formatting.FGColor.BRIGHT_PURPLE)
fun String.brightCyan() = format(color = Formatting.FGColor.BRIGHT_CYAN)
fun String.brightWhite() = format(color = Formatting.FGColor.BRIGHT_WHITE)

fun String.fgBlack() = format(color = Formatting.FGColor.BLACK)
fun String.fgRed() = format(color = Formatting.FGColor.RED)
fun String.fgGreen() = format(color = Formatting.FGColor.GREEN)
fun String.fgYellow() = format(color = Formatting.FGColor.YELLOW)
fun String.fgBlue() = format(color = Formatting.FGColor.BLUE)
fun String.fgPurple() = format(color = Formatting.FGColor.PURPLE)
fun String.fgCyan() = format(color = Formatting.FGColor.CYAN)
fun String.fgWhite() = format(color = Formatting.FGColor.WHITE)
fun String.fgGrey() = format(color = Formatting.FGColor.BRIGHT_BLACK)
fun String.fgBrightBlack() = format(color = Formatting.FGColor.BRIGHT_BLACK)
fun String.fgBrightRed() = format(color = Formatting.FGColor.BRIGHT_RED)
fun String.fgBrightGreen() = format(color = Formatting.FGColor.BRIGHT_GREEN)
fun String.fgBrightYellow() = format(color = Formatting.FGColor.BRIGHT_YELLOW)
fun String.fgBrightBlue() = format(color = Formatting.FGColor.BRIGHT_BLUE)
fun String.fgBrightPurple() = format(color = Formatting.FGColor.BRIGHT_PURPLE)
fun String.fgBrightCyan() = format(color = Formatting.FGColor.BRIGHT_CYAN)
fun String.fgBrightWhite() = format(color = Formatting.FGColor.BRIGHT_WHITE)

fun String.bgBlack() = format(backgroundColor = Formatting.BGColor.BLACK)
fun String.bgRed() = format(backgroundColor = Formatting.BGColor.RED)
fun String.bgGreen() = format(backgroundColor = Formatting.BGColor.GREEN)
fun String.bgYellow() = format(backgroundColor = Formatting.BGColor.YELLOW)
fun String.bgBlue() = format(backgroundColor = Formatting.BGColor.BLUE)
fun String.bgPurple() = format(backgroundColor = Formatting.BGColor.PURPLE)
fun String.bgCyan() = format(backgroundColor = Formatting.BGColor.CYAN)
fun String.bgWhite() = format(backgroundColor = Formatting.BGColor.WHITE)
fun String.bgGrey() = format(backgroundColor = Formatting.BGColor.BRIGHT_BLACK)
fun String.bgBrightBlack() = format(backgroundColor = Formatting.BGColor.BRIGHT_BLACK)
fun String.bgBrightRed() = format(backgroundColor = Formatting.BGColor.BRIGHT_RED)
fun String.bgBrightGreen() = format(backgroundColor = Formatting.BGColor.BRIGHT_GREEN)
fun String.bgBrightYellow() = format(backgroundColor = Formatting.BGColor.BRIGHT_YELLOW)
fun String.bgBrightBlue() = format(backgroundColor = Formatting.BGColor.BRIGHT_BLUE)
fun String.bgBrightPurple() = format(backgroundColor = Formatting.BGColor.BRIGHT_PURPLE)
fun String.bgBrightCyan() = format(backgroundColor = Formatting.BGColor.BRIGHT_CYAN)
fun String.bgBrightWhite() = format(backgroundColor = Formatting.BGColor.BRIGHT_WHITE)
