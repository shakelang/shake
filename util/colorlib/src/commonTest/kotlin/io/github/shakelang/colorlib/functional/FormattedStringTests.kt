package io.github.shakelang.colorlib.functional

import io.github.shakelang.colorlib.Formatting
import kotlin.test.Test
import kotlin.test.assertEquals

class FormattedStringTests {

    @Test
    fun testFormattedStringBold() {
        val formattedString = FormattedString(listOf(FormattedStringObject.wrap("Hello World")), isBold = true)
        assertEquals("${Formatting.BOLD}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testFormattedStringItalic() {
        val formattedString = FormattedString(listOf(FormattedStringObject.wrap("Hello World")), isItalic = true)
        assertEquals("${Formatting.ITALIC}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testFormattedStringUnderline() {
        val formattedString = FormattedString(listOf(FormattedStringObject.wrap("Hello World")), isUnderlined = true)
        assertEquals("${Formatting.UNDERLINE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testFormattedStringStrikethrough() {
        val formattedString = FormattedString(listOf(FormattedStringObject.wrap("Hello World")), isStrikethrough = true)
        assertEquals("${Formatting.STRIKETHROUGH}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testInverted() {
        val formattedString = FormattedString(listOf(FormattedStringObject.wrap("Hello World")), isInverted = true)
        assertEquals("${Formatting.INVERT}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testFormattedStringColor() {
        val formattedString = FormattedString(listOf(FormattedStringObject.wrap("Hello World")), color = Formatting.FGColor.RED)
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testFormattedStringBackgroundColor() {
        val formattedString = FormattedString(listOf(FormattedStringObject.wrap("Hello World")), backgroundColor = Formatting.BGColor.RED)
        assertEquals("${Formatting.BGColor.RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBold() {
        val formattedString = FormattedString.wrap("Hello World").bold()
        assertEquals("${Formatting.BOLD}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringItalic() {
        val formattedString = FormattedString.wrap("Hello World").italic()
        assertEquals("${Formatting.ITALIC}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringUnderline() {
        val formattedString = FormattedString.wrap("Hello World").underline()
        assertEquals("${Formatting.UNDERLINE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringStrikethrough() {
        val formattedString = FormattedString.wrap("Hello World").strikethrough()
        assertEquals("${Formatting.STRIKETHROUGH}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringInverted() {
        val formattedString = FormattedString.wrap("Hello World").invert()
        assertEquals("${Formatting.INVERT}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringColor1() {
        val formattedString = FormattedString.wrap("Hello World").color(Formatting.FGColor.RED)
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringColor2() {
        val formattedString = FormattedString.wrap("Hello World").fgColor(Formatting.FGColor.RED)
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringColor3() {
        val formattedString = FormattedString.wrap("Hello World").fg(Formatting.FGColor.RED)
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBackgroundColor1() {
        val formattedString = FormattedString.wrap("Hello World").backgroundColor(Formatting.BGColor.RED)
        assertEquals("${Formatting.BGColor.RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBackgroundColor2() {
        val formattedString = FormattedString.wrap("Hello World").bgColor(Formatting.BGColor.RED)
        assertEquals("${Formatting.BGColor.RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBackgroundColor3() {
        val formattedString = FormattedString.wrap("Hello World").bg(Formatting.BGColor.RED)
        assertEquals("${Formatting.BGColor.RED}Hello World${Formatting.RESET}", formattedString.toString())
    }


    @Test
    fun testMakeFormattedStringBlack() {
        val formattedString = FormattedString.wrap("Hello World").black()
        assertEquals("${Formatting.FGColor.BLACK}Hello World${Formatting.RESET}", formattedString.toString())
    }


    @Test
    fun testMakeFormattedStringRed() {
        val formattedString = FormattedString.wrap("Hello World").red()
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringGreen() {
        val formattedString = FormattedString.wrap("Hello World").green()
        assertEquals("${Formatting.FGColor.GREEN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringYellow() {
        val formattedString = FormattedString.wrap("Hello World").yellow()
        assertEquals("${Formatting.FGColor.YELLOW}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBlue() {
        val formattedString = FormattedString.wrap("Hello World").blue()
        assertEquals("${Formatting.FGColor.BLUE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringPurple() {
        val formattedString = FormattedString.wrap("Hello World").purple()
        assertEquals("${Formatting.FGColor.PURPLE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringCyan() {
        val formattedString = FormattedString.wrap("Hello World").cyan()
        assertEquals("${Formatting.FGColor.CYAN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringWhite() {
        val formattedString = FormattedString.wrap("Hello World").white()
        assertEquals("${Formatting.FGColor.WHITE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringGrey() {
        val formattedString = FormattedString.wrap("Hello World").grey()
        assertEquals("${Formatting.FGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBrightBlack() {
        val formattedString = FormattedString.wrap("Hello World").brightBlack()
        assertEquals("${Formatting.FGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBrightRed() {
        val formattedString = FormattedString.wrap("Hello World").brightRed()
        assertEquals("${Formatting.FGColor.BRIGHT_RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBrightGreen() {
        val formattedString = FormattedString.wrap("Hello World").brightGreen()
        assertEquals("${Formatting.FGColor.BRIGHT_GREEN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBrightYellow() {
        val formattedString = FormattedString.wrap("Hello World").brightYellow()
        assertEquals("${Formatting.FGColor.BRIGHT_YELLOW}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBrightBlue() {
        val formattedString = FormattedString.wrap("Hello World").brightBlue()
        assertEquals("${Formatting.FGColor.BRIGHT_BLUE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBrightPurple() {
        val formattedString = FormattedString.wrap("Hello World").brightPurple()
        assertEquals("${Formatting.FGColor.BRIGHT_PURPLE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBrightCyan() {
        val formattedString = FormattedString.wrap("Hello World").brightCyan()
        assertEquals("${Formatting.FGColor.BRIGHT_CYAN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBrightWhite() {
        val formattedString = FormattedString.wrap("Hello World").brightWhite()
        assertEquals("${Formatting.FGColor.BRIGHT_WHITE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringFgBlack() {
        val formattedString = FormattedString.wrap("Hello World").fgBlack()
        assertEquals("${Formatting.FGColor.BLACK}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringFgRed() {
        val formattedString = FormattedString.wrap("Hello World").fgRed()
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringFgGreen() {
        val formattedString = FormattedString.wrap("Hello World").fgGreen()
        assertEquals("${Formatting.FGColor.GREEN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringFgYellow() {
        val formattedString = FormattedString.wrap("Hello World").fgYellow()
        assertEquals("${Formatting.FGColor.YELLOW}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringFgBlue() {
        val formattedString = FormattedString.wrap("Hello World").fgBlue()
        assertEquals("${Formatting.FGColor.BLUE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringFgPurple() {
        val formattedString = FormattedString.wrap("Hello World").fgPurple()
        assertEquals("${Formatting.FGColor.PURPLE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringFgCyan() {
        val formattedString = FormattedString.wrap("Hello World").fgCyan()
        assertEquals("${Formatting.FGColor.CYAN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringFgWhite() {
        val formattedString = FormattedString.wrap("Hello World").fgWhite()
        assertEquals("${Formatting.FGColor.WHITE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringFgGrey() {
        val formattedString = FormattedString.wrap("Hello World").fgGrey()
        assertEquals("${Formatting.FGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringFgBrightBlack() {
        val formattedString = FormattedString.wrap("Hello World").fgBrightBlack()
        assertEquals("${Formatting.FGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringFgBrightRed() {
        val formattedString = FormattedString.wrap("Hello World").fgBrightRed()
        assertEquals("${Formatting.FGColor.BRIGHT_RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringFgBrightGreen() {
        val formattedString = FormattedString.wrap("Hello World").fgBrightGreen()
        assertEquals("${Formatting.FGColor.BRIGHT_GREEN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringFgBrightYellow() {
        val formattedString = FormattedString.wrap("Hello World").fgBrightYellow()
        assertEquals("${Formatting.FGColor.BRIGHT_YELLOW}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringFgBrightBlue() {
        val formattedString = FormattedString.wrap("Hello World").fgBrightBlue()
        assertEquals("${Formatting.FGColor.BRIGHT_BLUE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringFgBrightPurple() {
        val formattedString = FormattedString.wrap("Hello World").fgBrightPurple()
        assertEquals("${Formatting.FGColor.BRIGHT_PURPLE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringFgBrightCyan() {
        val formattedString = FormattedString.wrap("Hello World").fgBrightCyan()
        assertEquals("${Formatting.FGColor.BRIGHT_CYAN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringFgBrightWhite() {
        val formattedString = FormattedString.wrap("Hello World").fgBrightWhite()
        assertEquals("${Formatting.FGColor.BRIGHT_WHITE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBackgroundBlack() {
        val formattedString = FormattedString.wrap("Hello World").bgBlack()
        assertEquals("${Formatting.BGColor.BLACK}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBackgroundRed() {
        val formattedString = FormattedString.wrap("Hello World").bgRed()
        assertEquals("${Formatting.BGColor.RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBackgroundGreen() {
        val formattedString = FormattedString.wrap("Hello World").bgGreen()
        assertEquals("${Formatting.BGColor.GREEN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBackgroundYellow() {
        val formattedString = FormattedString.wrap("Hello World").bgYellow()
        assertEquals("${Formatting.BGColor.YELLOW}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBackgroundBlue() {
        val formattedString = FormattedString.wrap("Hello World").bgBlue()
        assertEquals("${Formatting.BGColor.BLUE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBackgroundPurple() {
        val formattedString = FormattedString.wrap("Hello World").bgPurple()
        assertEquals("${Formatting.BGColor.PURPLE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBackgroundCyan() {
        val formattedString = FormattedString.wrap("Hello World").bgCyan()
        assertEquals("${Formatting.BGColor.CYAN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBackgroundWhite() {
        val formattedString = FormattedString.wrap("Hello World").bgWhite()
        assertEquals("${Formatting.BGColor.WHITE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBackgroundGrey() {
        val formattedString = FormattedString.wrap("Hello World").bgGrey()
        assertEquals("${Formatting.BGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBackgroundBrightBlack() {
        val formattedString = FormattedString.wrap("Hello World").bgBrightBlack()
        assertEquals("${Formatting.BGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBackgroundBrightRed() {
        val formattedString = FormattedString.wrap("Hello World").bgBrightRed()
        assertEquals("${Formatting.BGColor.BRIGHT_RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBackgroundBrightGreen() {
        val formattedString = FormattedString.wrap("Hello World").bgBrightGreen()
        assertEquals("${Formatting.BGColor.BRIGHT_GREEN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBackgroundBrightYellow() {
        val formattedString = FormattedString.wrap("Hello World").bgBrightYellow()
        assertEquals("${Formatting.BGColor.BRIGHT_YELLOW}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBackgroundBrightBlue() {
        val formattedString = FormattedString.wrap("Hello World").bgBrightBlue()
        assertEquals("${Formatting.BGColor.BRIGHT_BLUE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBackgroundBrightPurple() {
        val formattedString = FormattedString.wrap("Hello World").bgBrightPurple()
        assertEquals("${Formatting.BGColor.BRIGHT_PURPLE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBackgroundBrightCyan() {
        val formattedString = FormattedString.wrap("Hello World").bgBrightCyan()
        assertEquals("${Formatting.BGColor.BRIGHT_CYAN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBackgroundBrightWhite() {
        val formattedString = FormattedString.wrap("Hello World").bgBrightWhite()
        assertEquals("${Formatting.BGColor.BRIGHT_WHITE}Hello World${Formatting.RESET}", formattedString.toString())
    }
}