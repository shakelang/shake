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

   @Test
    fun testMakeFormattedStringReset() {
        val formattedString = FormattedString.wrap("Hello World").reset()
        assertEquals("Hello World", formattedString.toString())
    }

    @Test
    fun testMakeStringBold() {
        val formattedString = "Hello World".bold()
        assertEquals("${Formatting.BOLD}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringItalic() {
        val formattedString = "Hello World".italic()
        assertEquals("${Formatting.ITALIC}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringUnderline() {
        val formattedString = "Hello World".underline()
        assertEquals("${Formatting.UNDERLINE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringStrikethrough() {
        val formattedString = "Hello World".strikethrough()
        assertEquals("${Formatting.STRIKETHROUGH}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringInverted() {
        val formattedString = "Hello World".invert()
        assertEquals("${Formatting.INVERT}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringColor1() {
        val formattedString = "Hello World".color(Formatting.FGColor.RED)
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringColor2() {
        val formattedString = "Hello World".fgColor(Formatting.FGColor.RED)
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringColor3() {
        val formattedString = "Hello World".fg(Formatting.FGColor.RED)
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBackgroundColor1() {
        val formattedString = "Hello World".backgroundColor(Formatting.BGColor.RED)
        assertEquals("${Formatting.BGColor.RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBackgroundColor2() {
        val formattedString = "Hello World".bgColor(Formatting.BGColor.RED)
        assertEquals("${Formatting.BGColor.RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBackgroundColor3() {
        val formattedString = "Hello World".bg(Formatting.BGColor.RED)
        assertEquals("${Formatting.BGColor.RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBlack() {
        val formattedString = "Hello World".black()
        assertEquals("${Formatting.FGColor.BLACK}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringRed() {
        val formattedString = "Hello World".red()
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringGreen() {
        val formattedString = "Hello World".green()
        assertEquals("${Formatting.FGColor.GREEN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringYellow() {
        val formattedString = "Hello World".yellow()
        assertEquals("${Formatting.FGColor.YELLOW}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBlue() {
        val formattedString = "Hello World".blue()
        assertEquals("${Formatting.FGColor.BLUE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringPurple() {
        val formattedString = "Hello World".purple()
        assertEquals("${Formatting.FGColor.PURPLE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringCyan() {
        val formattedString = "Hello World".cyan()
        assertEquals("${Formatting.FGColor.CYAN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringWhite() {
        val formattedString = "Hello World".white()
        assertEquals("${Formatting.FGColor.WHITE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringGrey() {
        val formattedString = "Hello World".grey()
        assertEquals("${Formatting.FGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBrightBlack() {
        val formattedString = "Hello World".brightBlack()
        assertEquals("${Formatting.FGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBrightRed() {
        val formattedString = "Hello World".brightRed()
        assertEquals("${Formatting.FGColor.BRIGHT_RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBrightGreen() {
        val formattedString = "Hello World".brightGreen()
        assertEquals("${Formatting.FGColor.BRIGHT_GREEN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBrightYellow() {
        val formattedString = "Hello World".brightYellow()
        assertEquals("${Formatting.FGColor.BRIGHT_YELLOW}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBrightBlue() {
        val formattedString = "Hello World".brightBlue()
        assertEquals("${Formatting.FGColor.BRIGHT_BLUE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBrightPurple() {
        val formattedString = "Hello World".brightPurple()
        assertEquals("${Formatting.FGColor.BRIGHT_PURPLE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBrightCyan() {
        val formattedString = "Hello World".brightCyan()
        assertEquals("${Formatting.FGColor.BRIGHT_CYAN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBrightWhite() {
        val formattedString = "Hello World".brightWhite()
        assertEquals("${Formatting.FGColor.BRIGHT_WHITE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringFgBlack() {
        val formattedString = "Hello World".fgBlack()
        assertEquals("${Formatting.FGColor.BLACK}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringFgRed() {
        val formattedString = "Hello World".fgRed()
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringFgGreen() {
        val formattedString = "Hello World".fgGreen()
        assertEquals("${Formatting.FGColor.GREEN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringFgYellow() {
        val formattedString = "Hello World".fgYellow()
        assertEquals("${Formatting.FGColor.YELLOW}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringFgBlue() {
        val formattedString = "Hello World".fgBlue()
        assertEquals("${Formatting.FGColor.BLUE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringFgPurple() {
        val formattedString = "Hello World".fgPurple()
        assertEquals("${Formatting.FGColor.PURPLE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringFgCyan() {
        val formattedString = "Hello World".fgCyan()
        assertEquals("${Formatting.FGColor.CYAN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringFgWhite() {
        val formattedString = "Hello World".fgWhite()
        assertEquals("${Formatting.FGColor.WHITE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringFgGrey() {
        val formattedString = "Hello World".fgGrey()
        assertEquals("${Formatting.FGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringFgBrightBlack() {
        val formattedString = "Hello World".fgBrightBlack()
        assertEquals("${Formatting.FGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringFgBrightRed() {
        val formattedString = "Hello World".fgBrightRed()
        assertEquals("${Formatting.FGColor.BRIGHT_RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringFgBrightGreen() {
        val formattedString = "Hello World".fgBrightGreen()
        assertEquals("${Formatting.FGColor.BRIGHT_GREEN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringFgBrightYellow() {
        val formattedString = "Hello World".fgBrightYellow()
        assertEquals("${Formatting.FGColor.BRIGHT_YELLOW}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringFgBrightBlue() {
        val formattedString = "Hello World".fgBrightBlue()
        assertEquals("${Formatting.FGColor.BRIGHT_BLUE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringFgBrightPurple() {
        val formattedString = "Hello World".fgBrightPurple()
        assertEquals("${Formatting.FGColor.BRIGHT_PURPLE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringFgBrightCyan() {
        val formattedString = "Hello World".fgBrightCyan()
        assertEquals("${Formatting.FGColor.BRIGHT_CYAN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringFgBrightWhite() {
        val formattedString = "Hello World".fgBrightWhite()
        assertEquals("${Formatting.FGColor.BRIGHT_WHITE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBackgroundBlack() {
        val formattedString = "Hello World".bgBlack()
        assertEquals("${Formatting.BGColor.BLACK}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBackgroundRed() {
        val formattedString = "Hello World".bgRed()
        assertEquals("${Formatting.BGColor.RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBackgroundGreen() {
        val formattedString = "Hello World".bgGreen()
        assertEquals("${Formatting.BGColor.GREEN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBackgroundYellow() {
        val formattedString = "Hello World".bgYellow()
        assertEquals("${Formatting.BGColor.YELLOW}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBackgroundBlue() {
        val formattedString = "Hello World".bgBlue()
        assertEquals("${Formatting.BGColor.BLUE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBackgroundPurple() {
        val formattedString = "Hello World".bgPurple()
        assertEquals("${Formatting.BGColor.PURPLE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBackgroundCyan() {
        val formattedString = "Hello World".bgCyan()
        assertEquals("${Formatting.BGColor.CYAN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBackgroundWhite() {
        val formattedString = "Hello World".bgWhite()
        assertEquals("${Formatting.BGColor.WHITE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBackgroundGrey() {
        val formattedString = "Hello World".bgGrey()
        assertEquals("${Formatting.BGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBackgroundBrightBlack() {
        val formattedString = "Hello World".bgBrightBlack()
        assertEquals("${Formatting.BGColor.BRIGHT_BLACK}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBackgroundBrightRed() {
        val formattedString = "Hello World".bgBrightRed()
        assertEquals("${Formatting.BGColor.BRIGHT_RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBackgroundBrightGreen() {
        val formattedString = "Hello World".bgBrightGreen()
        assertEquals("${Formatting.BGColor.BRIGHT_GREEN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBackgroundBrightYellow() {
        val formattedString = "Hello World".bgBrightYellow()
        assertEquals("${Formatting.BGColor.BRIGHT_YELLOW}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBackgroundBrightBlue() {
        val formattedString = "Hello World".bgBrightBlue()
        assertEquals("${Formatting.BGColor.BRIGHT_BLUE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBackgroundBrightPurple() {
        val formattedString = "Hello World".bgBrightPurple()
        assertEquals("${Formatting.BGColor.BRIGHT_PURPLE}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBackgroundBrightCyan() {
        val formattedString = "Hello World".bgBrightCyan()
        assertEquals("${Formatting.BGColor.BRIGHT_CYAN}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeStringBackgroundBrightWhite() {
        val formattedString = "Hello World".bgBrightWhite()
        assertEquals("${Formatting.BGColor.BRIGHT_WHITE}Hello World${Formatting.RESET}", formattedString.toString())
    }

}