package io.github.shakelang.colorlib.functional

import io.github.shakelang.colorlib.Formatting

/**
 * A FormattedStringObject is an object that can be used in a [FormattedString]
 * It can wrap a [String] or another [FormattedString]
 * Its used to implement the [FormattedStringObject.extends] function
 *
 * @since 0.1.1
 * @version 0.1.1
 */
interface FormattedStringObject {

    /**
     * Get the [String] representation of this [FormattedStringObject]
     * This should be overridden by all implementations!
     *
     * @return the [String] representation of this [FormattedStringObject]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    override fun toString(): String

    /**
     * Get the [String] representation of this [FormattedStringObject]
     * (Basically a shortcut for [toString])
     * Not needed to be overridden
     *
     * @return the [String] representation of this [FormattedStringObject]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun string() = toString()

    /**
     * Extend this [FormattedStringObject] with the given [format]
     *
     * @param format the [FormattedString] to extend this [FormattedStringObject] with
     * @return the resulting [FormattedStringObject]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun extends(format: FormattedString): FormattedStringObject


    companion object {

        /**
         * Wrap a [String] in a [FormattedStringObject]
         *
         * @param string the [String] to wrap
         * @return the resulting [FormattedStringObject]
         *
         * @since 0.1.1
         * @version 0.1.1
         */
        fun wrap(string: String) = FormattedStringObjectString(string)
    }
}

/**
 * A [FormattedStringObject] that wraps a [String]
 *
 * @property value the [String] that is wrapped
 *
 * @since 0.1.1
 * @version 0.1.1
 */
class FormattedStringObjectString (

    /**
     * The [String] that is wrapped
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    val value: String
) : FormattedStringObject {

    /**
     * Get the [String] representation of this [FormattedStringObject]
     *
     * @return the [String] representation of this [FormattedStringObject]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    override fun toString(): String {
        return this.value
    }

    /**
     * The generated formatted [String] will extend the given [format] and return itself
     *
     * @param format the [FormattedString] to extend this [FormattedStringObject] with
     * @return the resulting [FormattedStringObject]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    override fun extends(format: FormattedString) = this
}


/**
 * A formatted [String] that can be used to format [String]s with colors and styles
 *
 * @param contents the [FormattedStringObject]s that are wrapped in this [FormattedString]
 * @param isBold if the [FormattedString] is bold
 * @param isItalic if the [FormattedString] is italic
 * @param isUnderlined if the [FormattedString] is underlined
 * @param isStrikethrough if the [FormattedString] is strikethrough
 * @param isInverted if the [FormattedString] is inverted
 * @param color the [Formatting.FGColor] of the [FormattedString]
 * @param backgroundColor the [Formatting.BGColor] of the [FormattedString]
 * @constructor Creates a [FormattedString] with the given [contents], [isBold], [isItalic], [isUnderlined],
 *   [isStrikethrough], [isInverted], [color] and [backgroundColor]
 *
 * @since 0.1.1
 * @version 0.1.1
 */
class FormattedString(

    /**
     * The [FormattedStringObject]s that are wrapped in this [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    val contents: List<FormattedStringObject>,

    /**
     * If the [FormattedString] is bold
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    isBold: Boolean? = null,

    /**
     * If the [FormattedString] is italic
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    isItalic: Boolean? = null,

    /**
     * If the [FormattedString] is underlined
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    isUnderlined: Boolean? = null,

    /**
     * If the [FormattedString] is strikethrough
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    isStrikethrough: Boolean? = null,

    /**
     * If the [FormattedString] is inverted
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    isInverted: Boolean? = null,

    /**
     * The [Formatting.FGColor] of the [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    val color: Formatting.FGColor? = null,

    /**
     * The [Formatting.BGColor] of the [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    val backgroundColor: Formatting.BGColor? = null

) : FormattedStringObject {

    /**
     * If the [FormattedString] is bold
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    private val _isBold: Boolean? = isBold

    /**
     * If the [FormattedString] is italic
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    private val _isItalic: Boolean? = isItalic

    /**
     * If the [FormattedString] is underlined
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    private val _isUnderlined: Boolean? = isUnderlined

    /**
     * If the [FormattedString] is strikethrough
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    private val _isStrikethrough: Boolean? = isStrikethrough

    /**
     * If the [FormattedString] is inverted
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    private val _isInverted: Boolean? = isInverted

    /**
     * If the [FormattedString] is bold
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    val isBold get() = _isBold ?: false

    /**
     * If the [FormattedString] is italic
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    val isItalic get() = _isItalic ?: false

    /**
     * If the [FormattedString] is underlined
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    val isUnderlined get() = _isUnderlined ?: false

    /**
     * If the [FormattedString] is strikethrough
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    val isStrikethrough get() = _isStrikethrough ?: false

    /**
     * If the [FormattedString] is inverted
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    val isInverted get() = _isInverted ?: false


    /**
     * Construct a [FormattedString] from the given [contents] [FormattedString]
     *
     * @constructor Creates a [FormattedString] from the given [contents] [FormattedString]
     *
     * @param contents the [FormattedString] to copy
     * @param strings the [FormattedStringObject]s that are wrapped in this [FormattedString]
     * @param isBold if the [FormattedString] is bold
     * @param isItalic if the [FormattedString] is italic
     * @param isUnderlined if the [FormattedString] is underlined
     * @param isStrikethrough if the [FormattedString] is strikethrough
     * @param isInverted if the [FormattedString] is inverted
     * @param color the [Formatting.FGColor] of the [FormattedString]
     * @param backgroundColor the [Formatting.BGColor] of the [FormattedString]
     */
    constructor(
        contents: FormattedString,
        strings: List<FormattedStringObject> = contents.contents,
        isBold: Boolean? = contents.isBold,
        isItalic: Boolean? = contents.isItalic,
        isUnderlined: Boolean? = contents.isUnderlined,
        isStrikethrough: Boolean? = contents.isStrikethrough,
        isInverted: Boolean? = contents.isInverted,
        color: Formatting.FGColor? = contents.color,
        backgroundColor: Formatting.BGColor? = contents.backgroundColor
    ) : this(strings, isBold, isItalic, isUnderlined, isStrikethrough, isInverted, color, backgroundColor)

    /**
     * Is the FormattedString formatted in any way?
     * (Used to determine if the generated [String] should be formatted and if the [Formatting.RESET] should be added)
     *
     * @return if the FormattedString is formatted in any way
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    val isFormatted: Boolean
        get() = isBold
                || isItalic
                || isUnderlined
                || isStrikethrough
                || isInverted
                || color != null
                || backgroundColor != null

    /**
     * Get the [String] representation of this [FormattedString]
     *
     * @return the [String] representation of this [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    override fun toString(): String {
        var str = contents.map { it.string() }

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

    /**
     * Extend this [FormattedString] with the given [format]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
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

    /**
     * Make this [FormattedString] bold
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun bold() = FormattedString(this, isBold = true)

    /**
     * Make this [FormattedString] italic
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun italic() = FormattedString(this, isItalic = true)

    /**
     * Make this [FormattedString] underlined
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun underline() = FormattedString(this, isUnderlined = true)

    /**
     * Make this [FormattedString] strikethrough
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun strikethrough() = FormattedString(this, isStrikethrough = true)

    /**
     * Make this [FormattedString] inverted
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun invert() = FormattedString(this, isInverted = true)

    /**
     * Set the [Formatting.FGColor] of this [FormattedString]
     *
     * @param color the [Formatting.FGColor] to set
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun color(color: Formatting.FGColor) = FormattedString(this, color = color)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString]
     *
     * @param color the [Formatting.BGColor] to set
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun fg(color: Formatting.FGColor) = color(color)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString]
     *
     * @param color the [Formatting.BGColor] to set
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun fgColor(color: Formatting.FGColor) = color(color)


    /**
     * Set the [Formatting.BGColor] of this [FormattedString]
     *
     * @param color the [Formatting.BGColor] to set
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun backgroundColor(color: Formatting.BGColor) = FormattedString(this, backgroundColor = color)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString]
     *
     * @param color the [Formatting.BGColor] to set
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun bg(color: Formatting.BGColor) = backgroundColor(color)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString]
     *
     * @param color the [Formatting.BGColor] to set
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun bgColor(color: Formatting.BGColor) = backgroundColor(color)


    /**
     * Set the [Formatting.FGColor] of this [FormattedString] to [Formatting.FGColor.BLACK]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun black() = color(Formatting.FGColor.BLACK)

    /**
     * Set the [Formatting.FGColor] of this [FormattedString] to [Formatting.FGColor.RED]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun red() = color(Formatting.FGColor.RED)

    /**
     * Set the [Formatting.FGColor] of this [FormattedString] to [Formatting.FGColor.GREEN]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun green() = color(Formatting.FGColor.GREEN)

    /**
     * Set the [Formatting.FGColor] of this [FormattedString] to [Formatting.FGColor.YELLOW]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun yellow() = color(Formatting.FGColor.YELLOW)

    /**
     * Set the [Formatting.FGColor] of this [FormattedString] to [Formatting.FGColor.BLUE]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun blue() = color(Formatting.FGColor.BLUE)

    /**
     * Set the [Formatting.FGColor] of this [FormattedString] to [Formatting.FGColor.PURPLE]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun purple() = color(Formatting.FGColor.PURPLE)

    /**
     * Set the [Formatting.FGColor] of this [FormattedString] to [Formatting.FGColor.CYAN]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun cyan() = color(Formatting.FGColor.CYAN)

    /**
     * Set the [Formatting.FGColor] of this [FormattedString] to [Formatting.FGColor.WHITE]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun white() = color(Formatting.FGColor.WHITE)

    /**
     * Set the [Formatting.FGColor] of this [FormattedString] to [Formatting.FGColor.BRIGHT_BLACK]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun grey() = color(Formatting.FGColor.BRIGHT_BLACK)

    /**
     * Set the [Formatting.FGColor] of this [FormattedString] to [Formatting.FGColor.BRIGHT_BLACK]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun brightBlack() = color(Formatting.FGColor.BRIGHT_BLACK)

    /**
     * Set the [Formatting.FGColor] of this [FormattedString] to [Formatting.FGColor.BRIGHT_RED]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun brightRed() = color(Formatting.FGColor.BRIGHT_RED)

    /**
     * Set the [Formatting.FGColor] of this [FormattedString] to [Formatting.FGColor.BRIGHT_GREEN]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun brightGreen() = color(Formatting.FGColor.BRIGHT_GREEN)

    /**
     * Set the [Formatting.FGColor] of this [FormattedString] to [Formatting.FGColor.BRIGHT_YELLOW]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun brightYellow() = color(Formatting.FGColor.BRIGHT_YELLOW)

    /**
     * Set the [Formatting.FGColor] of this [FormattedString] to [Formatting.FGColor.BRIGHT_BLUE]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun brightBlue() = color(Formatting.FGColor.BRIGHT_BLUE)

    /**
     * Set the [Formatting.FGColor] of this [FormattedString] to [Formatting.FGColor.BRIGHT_PURPLE]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun brightPurple() = color(Formatting.FGColor.BRIGHT_PURPLE)

    /**
     * Set the [Formatting.FGColor] of this [FormattedString] to [Formatting.FGColor.BRIGHT_CYAN]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun brightCyan() = color(Formatting.FGColor.BRIGHT_CYAN)

    /**
     * Set the [Formatting.FGColor] of this [FormattedString] to [Formatting.FGColor.BRIGHT_WHITE]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun brightWhite() = color(Formatting.FGColor.BRIGHT_WHITE)


    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.BLACK]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun fgBlack() = color(Formatting.FGColor.BLACK)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.RED]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun fgRed() = color(Formatting.FGColor.RED)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.GREEN]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun fgGreen() = color(Formatting.FGColor.GREEN)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.YELLOW]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun fgYellow() = color(Formatting.FGColor.YELLOW)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.BLUE]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun fgBlue() = color(Formatting.FGColor.BLUE)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.PURPLE]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun fgPurple() = color(Formatting.FGColor.PURPLE)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.CYAN]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun fgCyan() = color(Formatting.FGColor.CYAN)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.WHITE]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun fgWhite() = color(Formatting.FGColor.WHITE)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.BRIGHT_BLACK]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun fgGrey() = color(Formatting.FGColor.BRIGHT_BLACK)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.BRIGHT_BLACK]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun fgBrightBlack() = color(Formatting.FGColor.BRIGHT_BLACK)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.BRIGHT_RED]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun fgBrightRed() = color(Formatting.FGColor.BRIGHT_RED)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.BRIGHT_GREEN]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun fgBrightGreen() = color(Formatting.FGColor.BRIGHT_GREEN)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.BRIGHT_YELLOW]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun fgBrightYellow() = color(Formatting.FGColor.BRIGHT_YELLOW)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.BRIGHT_BLUE]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun fgBrightBlue() = color(Formatting.FGColor.BRIGHT_BLUE)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.BRIGHT_PURPLE]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun fgBrightPurple() = color(Formatting.FGColor.BRIGHT_PURPLE)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.BRIGHT_CYAN]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun fgBrightCyan() = color(Formatting.FGColor.BRIGHT_CYAN)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.BRIGHT_WHITE]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun fgBrightWhite() = color(Formatting.FGColor.BRIGHT_WHITE)


    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.BLACK]
     *
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun bgBlack() = backgroundColor(Formatting.BGColor.BLACK)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.RED]
     *
     * @return the resulting [Formatting]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun bgRed() = backgroundColor(Formatting.BGColor.RED)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.GREEN]
     *
     * @return the resulting [Formatting]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun bgGreen() = backgroundColor(Formatting.BGColor.GREEN)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.YELLOW]
     *
     * @return the resulting [Formatting]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun bgYellow() = backgroundColor(Formatting.BGColor.YELLOW)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.BLUE]
     *
     * @return the resulting [Formatting]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun bgBlue() = backgroundColor(Formatting.BGColor.BLUE)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.PURPLE]
     *
     * @return the resulting [Formatting]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun bgPurple() = backgroundColor(Formatting.BGColor.PURPLE)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.CYAN]
     *
     * @return the resulting [Formatting]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun bgCyan() = backgroundColor(Formatting.BGColor.CYAN)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.WHITE]
     *
     * @return the resulting [Formatting]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun bgWhite() = backgroundColor(Formatting.BGColor.WHITE)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.BRIGHT_BLACK]
     *
     * @return the resulting [Formatting]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun bgGrey() = backgroundColor(Formatting.BGColor.BRIGHT_BLACK)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.BRIGHT_BLACK]
     *
     * @return the resulting [Formatting]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun bgBrightBlack() = backgroundColor(Formatting.BGColor.BRIGHT_BLACK)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.BRIGHT_RED]
     *
     * @return the resulting [Formatting]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun bgBrightRed() = backgroundColor(Formatting.BGColor.BRIGHT_RED)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.BRIGHT_GREEN]
     *
     * @return the resulting [Formatting]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun bgBrightGreen() = backgroundColor(Formatting.BGColor.BRIGHT_GREEN)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.BRIGHT_YELLOW]
     *
     * @return the resulting [Formatting]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun bgBrightYellow() = backgroundColor(Formatting.BGColor.BRIGHT_YELLOW)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.BRIGHT_BLUE]
     *
     * @return the resulting [Formatting]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun bgBrightBlue() = backgroundColor(Formatting.BGColor.BRIGHT_BLUE)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.BRIGHT_PURPLE]
     *
     * @return the resulting [Formatting]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun bgBrightPurple() = backgroundColor(Formatting.BGColor.BRIGHT_PURPLE)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.BRIGHT_CYAN]
     *
     * @return the resulting [Formatting]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun bgBrightCyan() = backgroundColor(Formatting.BGColor.BRIGHT_CYAN)

    /**
     * Set the [Formatting.BGColor] of this [FormattedString] to [Formatting.BGColor.WHITE]
     *
     * @return the resulting [Formatting]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    fun bgBrightWhite() = backgroundColor(Formatting.BGColor.BRIGHT_WHITE)

    /**
     * Extend this [FormattedString] with the given [format]
     *
     * @param format the [FormattedString] to extend this [FormattedString] with
     * @return the resulting [FormattedString]
     *
     * @since 0.1.1
     * @version 0.1.1
     */
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

        /**
         * Wrap a string into a formatted string with no style.
         *
         * @param string the string to wrap
         * @returns the resulting [FormattedString]
         *
         * @since 0.1.1
         * @version 0.1.1
         */
        fun wrap(string: String) = FormattedString(listOf(FormattedStringObject.wrap(string)))

        /**
         * Wrap strings into a formatted string with no style.
         *
         * @param strings the strings to wrap
         * @returns the resulting [FormattedString]
         *
         * @since 0.1.1
         * @version 0.1.1
         */
        fun wrap(vararg strings: String) = FormattedString(strings.map { FormattedStringObject.wrap(it) })


        /**
         * Wrap a string into a formatted string with no style.
         *
         * @param string the string to wrap
         * @returns the resulting [FormattedString]
         *
         * @since 0.1.1
         * @version 0.1.1
         */
        fun from(string: String) = wrap(string)

        /**
         * Wrap strings into a formatted string with no style.
         *
         * @param strings the strings to wrap
         * @returns the resulting [FormattedString]
         *
         * @since 0.1.1
         * @version 0.1.1
         */
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
