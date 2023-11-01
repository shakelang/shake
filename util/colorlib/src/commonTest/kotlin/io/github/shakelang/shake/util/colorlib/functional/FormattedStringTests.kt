package io.github.shakelang.shake.util.colorlib.functional

import io.github.shakelang.shake.util.colorlib.Formatting
import kotlin.test.Test
import kotlin.test.assertEquals

class FormattedStringTests {

    @Test
    fun testFormattedStringBold() {
        val formattedString = FormattedString(listOf(FormattedStringObject.wrap("Hello World")), isBold = true)
        assertEquals("${Formatting.BOLD}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testFormattedStringItalic() {
        val formattedString = FormattedString(listOf(FormattedStringObject.wrap("Hello World")), isItalic = true)
        assertEquals("${Formatting.ITALIC}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testFormattedStringUnderline() {
        val formattedString = FormattedString(listOf(FormattedStringObject.wrap("Hello World")), isUnderlined = true)
        assertEquals("${Formatting.UNDERLINE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testFormattedStringStrikethrough() {
        val formattedString = FormattedString(listOf(FormattedStringObject.wrap("Hello World")), isStrikethrough = true)
        assertEquals("${Formatting.STRIKETHROUGH}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testInverted() {
        val formattedString = FormattedString(listOf(FormattedStringObject.wrap("Hello World")), isInverted = true)
        assertEquals("${Formatting.INVERT}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testFormattedStringColor() {
        val formattedString = FormattedString(listOf(FormattedStringObject.wrap("Hello World")), color = Formatting.FGColor.RED)
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testFormattedStringBackgroundColor() {
        val formattedString = FormattedString(listOf(FormattedStringObject.wrap("Hello World")), backgroundColor = Formatting.BGColor.RED)
        assertEquals("${Formatting.BGColor.RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBold() {
        val formattedString = FormattedString.wrap("Hello World").bold()
        assertEquals("${Formatting.BOLD}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringItalic() {
        val formattedString = FormattedString.wrap("Hello World").italic()
        assertEquals("${Formatting.ITALIC}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringUnderline() {
        val formattedString = FormattedString.wrap("Hello World").underline()
        assertEquals("${Formatting.UNDERLINE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringStrikethrough() {
        val formattedString = FormattedString.wrap("Hello World").strikethrough()
        assertEquals("${Formatting.STRIKETHROUGH}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringInverted() {
        val formattedString = FormattedString.wrap("Hello World").invert()
        assertEquals("${Formatting.INVERT}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringColor1() {
        val formattedString = FormattedString.wrap("Hello World").color(Formatting.FGColor.RED)
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringColor2() {
        val formattedString = FormattedString.wrap("Hello World").fgColor(Formatting.FGColor.RED)
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringColor3() {
        val formattedString = FormattedString.wrap("Hello World").fg(Formatting.FGColor.RED)
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBackgroundColor1() {
        val formattedString = FormattedString.wrap("Hello World").backgroundColor(Formatting.BGColor.RED)
        assertEquals("${Formatting.BGColor.RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBackgroundColor2() {
        val formattedString = FormattedString.wrap("Hello World").bgColor(Formatting.BGColor.RED)
        assertEquals("${Formatting.BGColor.RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBackgroundColor3() {
        val formattedString = FormattedString.wrap("Hello World").bg(Formatting.BGColor.RED)
        assertEquals("${Formatting.BGColor.RED}Hello World${Formatting.RESET}", formattedString.string())
    }


    @Test
    fun testMakeFormattedStringBlack() {
        val formattedString = FormattedString.wrap("Hello World").black()
        assertEquals("${Formatting.FGColor.BLACK}Hello World${Formatting.RESET}", formattedString.string())
    }


    @Test
    fun testMakeFormattedStringRed() {
        val formattedString = FormattedString.wrap("Hello World").red()
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringGreen() {
        val formattedString = FormattedString.wrap("Hello World").green()
        assertEquals("${Formatting.FGColor.GREEN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringYellow() {
        val formattedString = FormattedString.wrap("Hello World").yellow()
        assertEquals("${Formatting.FGColor.YELLOW}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBlue() {
        val formattedString = FormattedString.wrap("Hello World").blue()
        assertEquals("${Formatting.FGColor.BLUE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringPurple() {
        val formattedString = FormattedString.wrap("Hello World").purple()
        assertEquals("${Formatting.FGColor.PURPLE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringCyan() {
        val formattedString = FormattedString.wrap("Hello World").cyan()
        assertEquals("${Formatting.FGColor.CYAN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringWhite() {
        val formattedString = FormattedString.wrap("Hello World").white()
        assertEquals("${Formatting.FGColor.WHITE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringGrey() {
        val formattedString = FormattedString.wrap("Hello World").grey()
        assertEquals("${Formatting.FGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBrightBlack() {
        val formattedString = FormattedString.wrap("Hello World").brightBlack()
        assertEquals("${Formatting.FGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBrightRed() {
        val formattedString = FormattedString.wrap("Hello World").brightRed()
        assertEquals("${Formatting.FGColor.BRIGHT_RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBrightGreen() {
        val formattedString = FormattedString.wrap("Hello World").brightGreen()
        assertEquals("${Formatting.FGColor.BRIGHT_GREEN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBrightYellow() {
        val formattedString = FormattedString.wrap("Hello World").brightYellow()
        assertEquals("${Formatting.FGColor.BRIGHT_YELLOW}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBrightBlue() {
        val formattedString = FormattedString.wrap("Hello World").brightBlue()
        assertEquals("${Formatting.FGColor.BRIGHT_BLUE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBrightPurple() {
        val formattedString = FormattedString.wrap("Hello World").brightPurple()
        assertEquals("${Formatting.FGColor.BRIGHT_PURPLE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBrightCyan() {
        val formattedString = FormattedString.wrap("Hello World").brightCyan()
        assertEquals("${Formatting.FGColor.BRIGHT_CYAN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBrightWhite() {
        val formattedString = FormattedString.wrap("Hello World").brightWhite()
        assertEquals("${Formatting.FGColor.BRIGHT_WHITE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringFgBlack() {
        val formattedString = FormattedString.wrap("Hello World").fgBlack()
        assertEquals("${Formatting.FGColor.BLACK}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringFgRed() {
        val formattedString = FormattedString.wrap("Hello World").fgRed()
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringFgGreen() {
        val formattedString = FormattedString.wrap("Hello World").fgGreen()
        assertEquals("${Formatting.FGColor.GREEN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringFgYellow() {
        val formattedString = FormattedString.wrap("Hello World").fgYellow()
        assertEquals("${Formatting.FGColor.YELLOW}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringFgBlue() {
        val formattedString = FormattedString.wrap("Hello World").fgBlue()
        assertEquals("${Formatting.FGColor.BLUE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringFgPurple() {
        val formattedString = FormattedString.wrap("Hello World").fgPurple()
        assertEquals("${Formatting.FGColor.PURPLE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringFgCyan() {
        val formattedString = FormattedString.wrap("Hello World").fgCyan()
        assertEquals("${Formatting.FGColor.CYAN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringFgWhite() {
        val formattedString = FormattedString.wrap("Hello World").fgWhite()
        assertEquals("${Formatting.FGColor.WHITE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringFgGrey() {
        val formattedString = FormattedString.wrap("Hello World").fgGrey()
        assertEquals("${Formatting.FGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringFgBrightBlack() {
        val formattedString = FormattedString.wrap("Hello World").fgBrightBlack()
        assertEquals("${Formatting.FGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringFgBrightRed() {
        val formattedString = FormattedString.wrap("Hello World").fgBrightRed()
        assertEquals("${Formatting.FGColor.BRIGHT_RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringFgBrightGreen() {
        val formattedString = FormattedString.wrap("Hello World").fgBrightGreen()
        assertEquals("${Formatting.FGColor.BRIGHT_GREEN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringFgBrightYellow() {
        val formattedString = FormattedString.wrap("Hello World").fgBrightYellow()
        assertEquals("${Formatting.FGColor.BRIGHT_YELLOW}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringFgBrightBlue() {
        val formattedString = FormattedString.wrap("Hello World").fgBrightBlue()
        assertEquals("${Formatting.FGColor.BRIGHT_BLUE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringFgBrightPurple() {
        val formattedString = FormattedString.wrap("Hello World").fgBrightPurple()
        assertEquals("${Formatting.FGColor.BRIGHT_PURPLE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringFgBrightCyan() {
        val formattedString = FormattedString.wrap("Hello World").fgBrightCyan()
        assertEquals("${Formatting.FGColor.BRIGHT_CYAN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringFgBrightWhite() {
        val formattedString = FormattedString.wrap("Hello World").fgBrightWhite()
        assertEquals("${Formatting.FGColor.BRIGHT_WHITE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBackgroundBlack() {
        val formattedString = FormattedString.wrap("Hello World").bgBlack()
        assertEquals("${Formatting.BGColor.BLACK}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBackgroundRed() {
        val formattedString = FormattedString.wrap("Hello World").bgRed()
        assertEquals("${Formatting.BGColor.RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBackgroundGreen() {
        val formattedString = FormattedString.wrap("Hello World").bgGreen()
        assertEquals("${Formatting.BGColor.GREEN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBackgroundYellow() {
        val formattedString = FormattedString.wrap("Hello World").bgYellow()
        assertEquals("${Formatting.BGColor.YELLOW}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBackgroundBlue() {
        val formattedString = FormattedString.wrap("Hello World").bgBlue()
        assertEquals("${Formatting.BGColor.BLUE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBackgroundPurple() {
        val formattedString = FormattedString.wrap("Hello World").bgPurple()
        assertEquals("${Formatting.BGColor.PURPLE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBackgroundCyan() {
        val formattedString = FormattedString.wrap("Hello World").bgCyan()
        assertEquals("${Formatting.BGColor.CYAN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBackgroundWhite() {
        val formattedString = FormattedString.wrap("Hello World").bgWhite()
        assertEquals("${Formatting.BGColor.WHITE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBackgroundGrey() {
        val formattedString = FormattedString.wrap("Hello World").bgGrey()
        assertEquals("${Formatting.BGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBackgroundBrightBlack() {
        val formattedString = FormattedString.wrap("Hello World").bgBrightBlack()
        assertEquals("${Formatting.BGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBackgroundBrightRed() {
        val formattedString = FormattedString.wrap("Hello World").bgBrightRed()
        assertEquals("${Formatting.BGColor.BRIGHT_RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBackgroundBrightGreen() {
        val formattedString = FormattedString.wrap("Hello World").bgBrightGreen()
        assertEquals("${Formatting.BGColor.BRIGHT_GREEN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBackgroundBrightYellow() {
        val formattedString = FormattedString.wrap("Hello World").bgBrightYellow()
        assertEquals("${Formatting.BGColor.BRIGHT_YELLOW}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBackgroundBrightBlue() {
        val formattedString = FormattedString.wrap("Hello World").bgBrightBlue()
        assertEquals("${Formatting.BGColor.BRIGHT_BLUE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBackgroundBrightPurple() {
        val formattedString = FormattedString.wrap("Hello World").bgBrightPurple()
        assertEquals("${Formatting.BGColor.BRIGHT_PURPLE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBackgroundBrightCyan() {
        val formattedString = FormattedString.wrap("Hello World").bgBrightCyan()
        assertEquals("${Formatting.BGColor.BRIGHT_CYAN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeFormattedStringBackgroundBrightWhite() {
        val formattedString = FormattedString.wrap("Hello World").bgBrightWhite()
        assertEquals("${Formatting.BGColor.BRIGHT_WHITE}Hello World${Formatting.RESET}", formattedString.string())
    }

   @Test
    fun testMakeFormattedStringReset() {
        val formattedString = FormattedString.wrap("Hello World").reset()
        assertEquals("Hello World", formattedString.string())
    }

    @Test
    fun testMakeStringBold() {
        val formattedString = "Hello World".bold()
        assertEquals("${Formatting.BOLD}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringItalic() {
        val formattedString = "Hello World".italic()
        assertEquals("${Formatting.ITALIC}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringUnderline() {
        val formattedString = "Hello World".underline()
        assertEquals("${Formatting.UNDERLINE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringStrikethrough() {
        val formattedString = "Hello World".strikethrough()
        assertEquals("${Formatting.STRIKETHROUGH}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringInverted() {
        val formattedString = "Hello World".invert()
        assertEquals("${Formatting.INVERT}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringColor1() {
        val formattedString = "Hello World".color(Formatting.FGColor.RED)
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringColor2() {
        val formattedString = "Hello World".fgColor(Formatting.FGColor.RED)
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringColor3() {
        val formattedString = "Hello World".fg(Formatting.FGColor.RED)
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBackgroundColor1() {
        val formattedString = "Hello World".backgroundColor(Formatting.BGColor.RED)
        assertEquals("${Formatting.BGColor.RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBackgroundColor2() {
        val formattedString = "Hello World".bgColor(Formatting.BGColor.RED)
        assertEquals("${Formatting.BGColor.RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBackgroundColor3() {
        val formattedString = "Hello World".bg(Formatting.BGColor.RED)
        assertEquals("${Formatting.BGColor.RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBlack() {
        val formattedString = "Hello World".black()
        assertEquals("${Formatting.FGColor.BLACK}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringRed() {
        val formattedString = "Hello World".red()
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringGreen() {
        val formattedString = "Hello World".green()
        assertEquals("${Formatting.FGColor.GREEN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringYellow() {
        val formattedString = "Hello World".yellow()
        assertEquals("${Formatting.FGColor.YELLOW}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBlue() {
        val formattedString = "Hello World".blue()
        assertEquals("${Formatting.FGColor.BLUE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringPurple() {
        val formattedString = "Hello World".purple()
        assertEquals("${Formatting.FGColor.PURPLE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringCyan() {
        val formattedString = "Hello World".cyan()
        assertEquals("${Formatting.FGColor.CYAN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringWhite() {
        val formattedString = "Hello World".white()
        assertEquals("${Formatting.FGColor.WHITE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringGrey() {
        val formattedString = "Hello World".grey()
        assertEquals("${Formatting.FGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBrightBlack() {
        val formattedString = "Hello World".brightBlack()
        assertEquals("${Formatting.FGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBrightRed() {
        val formattedString = "Hello World".brightRed()
        assertEquals("${Formatting.FGColor.BRIGHT_RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBrightGreen() {
        val formattedString = "Hello World".brightGreen()
        assertEquals("${Formatting.FGColor.BRIGHT_GREEN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBrightYellow() {
        val formattedString = "Hello World".brightYellow()
        assertEquals("${Formatting.FGColor.BRIGHT_YELLOW}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBrightBlue() {
        val formattedString = "Hello World".brightBlue()
        assertEquals("${Formatting.FGColor.BRIGHT_BLUE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBrightPurple() {
        val formattedString = "Hello World".brightPurple()
        assertEquals("${Formatting.FGColor.BRIGHT_PURPLE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBrightCyan() {
        val formattedString = "Hello World".brightCyan()
        assertEquals("${Formatting.FGColor.BRIGHT_CYAN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBrightWhite() {
        val formattedString = "Hello World".brightWhite()
        assertEquals("${Formatting.FGColor.BRIGHT_WHITE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringFgBlack() {
        val formattedString = "Hello World".fgBlack()
        assertEquals("${Formatting.FGColor.BLACK}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringFgRed() {
        val formattedString = "Hello World".fgRed()
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringFgGreen() {
        val formattedString = "Hello World".fgGreen()
        assertEquals("${Formatting.FGColor.GREEN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringFgYellow() {
        val formattedString = "Hello World".fgYellow()
        assertEquals("${Formatting.FGColor.YELLOW}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringFgBlue() {
        val formattedString = "Hello World".fgBlue()
        assertEquals("${Formatting.FGColor.BLUE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringFgPurple() {
        val formattedString = "Hello World".fgPurple()
        assertEquals("${Formatting.FGColor.PURPLE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringFgCyan() {
        val formattedString = "Hello World".fgCyan()
        assertEquals("${Formatting.FGColor.CYAN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringFgWhite() {
        val formattedString = "Hello World".fgWhite()
        assertEquals("${Formatting.FGColor.WHITE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringFgGrey() {
        val formattedString = "Hello World".fgGrey()
        assertEquals("${Formatting.FGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringFgBrightBlack() {
        val formattedString = "Hello World".fgBrightBlack()
        assertEquals("${Formatting.FGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringFgBrightRed() {
        val formattedString = "Hello World".fgBrightRed()
        assertEquals("${Formatting.FGColor.BRIGHT_RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringFgBrightGreen() {
        val formattedString = "Hello World".fgBrightGreen()
        assertEquals("${Formatting.FGColor.BRIGHT_GREEN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringFgBrightYellow() {
        val formattedString = "Hello World".fgBrightYellow()
        assertEquals("${Formatting.FGColor.BRIGHT_YELLOW}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringFgBrightBlue() {
        val formattedString = "Hello World".fgBrightBlue()
        assertEquals("${Formatting.FGColor.BRIGHT_BLUE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringFgBrightPurple() {
        val formattedString = "Hello World".fgBrightPurple()
        assertEquals("${Formatting.FGColor.BRIGHT_PURPLE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringFgBrightCyan() {
        val formattedString = "Hello World".fgBrightCyan()
        assertEquals("${Formatting.FGColor.BRIGHT_CYAN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringFgBrightWhite() {
        val formattedString = "Hello World".fgBrightWhite()
        assertEquals("${Formatting.FGColor.BRIGHT_WHITE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBackgroundBlack() {
        val formattedString = "Hello World".bgBlack()
        assertEquals("${Formatting.BGColor.BLACK}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBackgroundRed() {
        val formattedString = "Hello World".bgRed()
        assertEquals("${Formatting.BGColor.RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBackgroundGreen() {
        val formattedString = "Hello World".bgGreen()
        assertEquals("${Formatting.BGColor.GREEN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBackgroundYellow() {
        val formattedString = "Hello World".bgYellow()
        assertEquals("${Formatting.BGColor.YELLOW}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBackgroundBlue() {
        val formattedString = "Hello World".bgBlue()
        assertEquals("${Formatting.BGColor.BLUE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBackgroundPurple() {
        val formattedString = "Hello World".bgPurple()
        assertEquals("${Formatting.BGColor.PURPLE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBackgroundCyan() {
        val formattedString = "Hello World".bgCyan()
        assertEquals("${Formatting.BGColor.CYAN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBackgroundWhite() {
        val formattedString = "Hello World".bgWhite()
        assertEquals("${Formatting.BGColor.WHITE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBackgroundGrey() {
        val formattedString = "Hello World".bgGrey()
        assertEquals("${Formatting.BGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBackgroundBrightBlack() {
        val formattedString = "Hello World".bgBrightBlack()
        assertEquals("${Formatting.BGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBackgroundBrightRed() {
        val formattedString = "Hello World".bgBrightRed()
        assertEquals("${Formatting.BGColor.BRIGHT_RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBackgroundBrightGreen() {
        val formattedString = "Hello World".bgBrightGreen()
        assertEquals("${Formatting.BGColor.BRIGHT_GREEN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBackgroundBrightYellow() {
        val formattedString = "Hello World".bgBrightYellow()
        assertEquals("${Formatting.BGColor.BRIGHT_YELLOW}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBackgroundBrightBlue() {
        val formattedString = "Hello World".bgBrightBlue()
        assertEquals("${Formatting.BGColor.BRIGHT_BLUE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBackgroundBrightPurple() {
        val formattedString = "Hello World".bgBrightPurple()
        assertEquals("${Formatting.BGColor.BRIGHT_PURPLE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBackgroundBrightCyan() {
        val formattedString = "Hello World".bgBrightCyan()
        assertEquals("${Formatting.BGColor.BRIGHT_CYAN}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testMakeStringBackgroundBrightWhite() {
        val formattedString = "Hello World".bgBrightWhite()
        assertEquals("${Formatting.BGColor.BRIGHT_WHITE}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testPlusOperator() {
        val formattedString = "Hello".red() + FormattedString.wrap(" World")
        assertEquals("${Formatting.FGColor.RED}Hello${Formatting.RESET} World", formattedString.string())
    }

    @Test
    fun testExtendsWithBold() {
        val formattedString = ("Hello".red() + " World").bold()
        assertEquals("${Formatting.BOLD}${Formatting.FGColor.RED}Hello${Formatting.RESET}${Formatting.BOLD} World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testExtendsWithItalic() {
        val formattedString = ("Hello".red() + " World").italic()
        assertEquals("${Formatting.ITALIC}${Formatting.FGColor.RED}Hello${Formatting.RESET}${Formatting.ITALIC} World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testExtendsWithUnderline() {
        val formattedString = ("Hello".red() + " World").underline()
        assertEquals("${Formatting.UNDERLINE}${Formatting.FGColor.RED}Hello${Formatting.RESET}${Formatting.UNDERLINE} World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testExtendsWithStrikethrough() {
        val formattedString = ("Hello".red() + " World").strikethrough()
        assertEquals("${Formatting.STRIKETHROUGH}${Formatting.FGColor.RED}Hello${Formatting.RESET}${Formatting.STRIKETHROUGH} World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testExtendsWithInvert() {
        val formattedString = ("Hello".red() + " World").invert()
        assertEquals("${Formatting.INVERT}${Formatting.FGColor.RED}Hello${Formatting.RESET}${Formatting.INVERT} World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testExtendsWithColor() {
        val formattedString = ("Hello".bold() + " World").color(Formatting.FGColor.BLUE)
        assertEquals("${Formatting.BOLD}${Formatting.FGColor.BLUE}Hello${Formatting.RESET}${Formatting.FGColor.BLUE} World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testWithBackground() {
        val formattedString = ("Hello".bold() + " World").backgroundColor(Formatting.BGColor.BLUE)
        assertEquals("${Formatting.BOLD}${Formatting.BGColor.BLUE}Hello${Formatting.RESET}${Formatting.BGColor.BLUE} World${Formatting.RESET}", formattedString.string())
    }
    @Test
    fun testWrapOfString() {
        val formattedString = FormattedString.wrap("Hello World")
        assertEquals("Hello World", formattedString.string())
    }

    @Test
    fun testWrapOfStrings() {
        val formattedString = FormattedString.wrap("Hello World", "Hello World")
        assertEquals("Hello WorldHello World", formattedString.string())
    }

    @Test
    fun testWrapOfFormattedString() {
        val formattedString = FormattedString.wrap("Hello World".red())
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.string())
    }

    @Test
    fun testWrapOfFormattedStrings() {
        val formattedString = FormattedString.wrap("Hello World".red(), "Hello World".blue())
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}${Formatting.FGColor.BLUE}Hello World${Formatting.RESET}", formattedString.string())
    }

}