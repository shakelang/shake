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
    fun testMakeFormattedStringColor() {
        val formattedString = FormattedString.wrap("Hello World").color(Formatting.FGColor.RED)
        assertEquals("${Formatting.FGColor.RED}Hello World${Formatting.RESET}", formattedString.toString())
    }

    @Test
    fun testMakeFormattedStringBackgroundColor() {
        val formattedString = FormattedString.wrap("Hello World").backgroundColor(Formatting.BGColor.RED)
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
}