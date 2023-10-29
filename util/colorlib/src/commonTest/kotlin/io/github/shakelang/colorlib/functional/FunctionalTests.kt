package io.github.shakelang.colorlib.functional

import io.github.shakelang.colorlib.Formatting
import kotlin.test.Test
import kotlin.test.assertEquals

class FunctionalTests {
    @Test
    fun testFgBlack() =
        assertEquals<String>("${Formatting.FGColor.BLACK}hello world${Formatting.RESET}", fgBlack("hello world"))

    @Test
    fun testFgRed() =
        assertEquals<String>("${Formatting.FGColor.RED}hello world${Formatting.RESET}", fgRed("hello world"))

    @Test
    fun testFgGreen() =
        assertEquals<String>("${Formatting.FGColor.GREEN}hello world${Formatting.RESET}", fgGreen("hello world"))

    @Test
    fun testFgYellow() =
        assertEquals<String>("${Formatting.FGColor.YELLOW}hello world${Formatting.RESET}", fgYellow("hello world"))

    @Test
    fun testFgBlue() =
        assertEquals<String>("${Formatting.FGColor.BLUE}hello world${Formatting.RESET}", fgBlue("hello world"))

    @Test
    fun testFgPurple() =
        assertEquals<String>("${Formatting.FGColor.PURPLE}hello world${Formatting.RESET}", fgPurple("hello world"))

    @Test
    fun testFgCyan() =
        assertEquals<String>("${Formatting.FGColor.CYAN}hello world${Formatting.RESET}", fgCyan("hello world"))

    @Test
    fun testFgWhite() =
        assertEquals<String>("${Formatting.FGColor.WHITE}hello world${Formatting.RESET}", fgWhite("hello world"))

    @Test
    fun testFgBrightBlack() =
        assertEquals<String>(
            "${Formatting.FGColor.BRIGHT_BLACK}hello world${Formatting.RESET}",
            fgBrightBlack("hello world")
        )

    @Test
    fun testFgGray() =
        assertEquals<String>("${Formatting.FGColor.BRIGHT_BLACK}hello world${Formatting.RESET}", fgGray("hello world"))

    @Test
    fun testFgBrightRed() =
        assertEquals<String>(
            "${Formatting.FGColor.BRIGHT_RED}hello world${Formatting.RESET}",
            fgBrightRed("hello world")
        )

    @Test
    fun testFgBrightGreen() =
        assertEquals<String>(
            "${Formatting.FGColor.BRIGHT_GREEN}hello world${Formatting.RESET}",
            fgBrightGreen("hello world")
        )

    @Test
    fun testFgBrightYellow() =
        assertEquals<String>(
            "${Formatting.FGColor.BRIGHT_YELLOW}hello world${Formatting.RESET}",
            fgBrightYellow("hello world")
        )

    @Test
    fun testFgBrightBlue() =
        assertEquals<String>(
            "${Formatting.FGColor.BRIGHT_BLUE}hello world${Formatting.RESET}",
            fgBrightBlue("hello world")
        )

    @Test
    fun testFgBrightPurple() =
        assertEquals<String>(
            "${Formatting.FGColor.BRIGHT_PURPLE}hello world${Formatting.RESET}",
            fgBrightPurple("hello world")
        )

    @Test
    fun testFgBrightCyan() =
        assertEquals<String>(
            "${Formatting.FGColor.BRIGHT_CYAN}hello world${Formatting.RESET}",
            fgBrightCyan("hello world")
        )

    @Test
    fun testFgBrightWhite() =
        assertEquals<String>(
            "${Formatting.FGColor.BRIGHT_WHITE}hello world${Formatting.RESET}",
            fgBrightWhite("hello world")
        )

    @Test
    fun testBlack() =
        assertEquals<String>("${Formatting.FGColor.BLACK}hello world${Formatting.RESET}", black("hello world"))

    @Test
    fun testRed() =
        assertEquals<String>("${Formatting.FGColor.RED}hello world${Formatting.RESET}", red("hello world"))

    @Test
    fun testGreen() =
        assertEquals<String>("${Formatting.FGColor.GREEN}hello world${Formatting.RESET}", green("hello world"))

    @Test
    fun testYellow() =
        assertEquals<String>("${Formatting.FGColor.YELLOW}hello world${Formatting.RESET}", yellow("hello world"))

    @Test
    fun testBlue() =
        assertEquals<String>("${Formatting.FGColor.BLUE}hello world${Formatting.RESET}", blue("hello world"))

    @Test
    fun testPurple() =
        assertEquals<String>("${Formatting.FGColor.PURPLE}hello world${Formatting.RESET}", purple("hello world"))

    @Test
    fun testCyan() =
        assertEquals<String>("${Formatting.FGColor.CYAN}hello world${Formatting.RESET}", cyan("hello world"))

    @Test
    fun testWhite() =
        assertEquals<String>("${Formatting.FGColor.WHITE}hello world${Formatting.RESET}", white("hello world"))

    @Test
    fun testGray() =
        assertEquals<String>("${Formatting.FGColor.BRIGHT_BLACK}hello world${Formatting.RESET}", gray("hello world"))

    @Test
    fun testBrightRed() =
        assertEquals<String>(
            "${Formatting.FGColor.BRIGHT_RED}hello world${Formatting.RESET}",
            brightRed("hello world")
        )

    @Test
    fun testBrightGreen() =
        assertEquals<String>(
            "${Formatting.FGColor.BRIGHT_GREEN}hello world${Formatting.RESET}",
            brightGreen("hello world")
        )

    @Test
    fun testBrightYellow() =
        assertEquals<String>(
            "${Formatting.FGColor.BRIGHT_YELLOW}hello world${Formatting.RESET}",
            brightYellow("hello world")
        )

    @Test
    fun testBrightBlue() =
        assertEquals<String>(
            "${Formatting.FGColor.BRIGHT_BLUE}hello world${Formatting.RESET}",
            brightBlue("hello world")
        )

    @Test
    fun testBrightPurple() =
        assertEquals<String>(
            "${Formatting.FGColor.BRIGHT_PURPLE}hello world${Formatting.RESET}",
            brightPurple("hello world")
        )

    @Test
    fun testBrightCyan() =
        assertEquals<String>(
            "${Formatting.FGColor.BRIGHT_CYAN}hello world${Formatting.RESET}",
            brightCyan("hello world")
        )

    @Test
    fun testBrightWhite() =
        assertEquals<String>(
            "${Formatting.FGColor.BRIGHT_WHITE}hello world${Formatting.RESET}",
            brightWhite("hello world")
        )

    @Test
    fun testBgBlack() =
        assertEquals<String>("${Formatting.BGColor.BLACK}hello world${Formatting.RESET}", bgBlack("hello world"))

    @Test
    fun testBgRed() =
        assertEquals<String>("${Formatting.BGColor.RED}hello world${Formatting.RESET}", bgRed("hello world"))

    @Test
    fun testBgGreen() =
        assertEquals<String>("${Formatting.BGColor.GREEN}hello world${Formatting.RESET}", bgGreen("hello world"))

    @Test
    fun testBgYellow() =
        assertEquals<String>("${Formatting.BGColor.YELLOW}hello world${Formatting.RESET}", bgYellow("hello world"))

    @Test
    fun testBgBlue() =
        assertEquals<String>("${Formatting.BGColor.BLUE}hello world${Formatting.RESET}", bgBlue("hello world"))

    @Test
    fun testBgPurple() =
        assertEquals<String>("${Formatting.BGColor.PURPLE}hello world${Formatting.RESET}", bgPurple("hello world"))

    @Test
    fun testBgCyan() =
        assertEquals<String>("${Formatting.BGColor.CYAN}hello world${Formatting.RESET}", bgCyan("hello world"))

    @Test
    fun testBgWhite() =
        assertEquals<String>("${Formatting.BGColor.WHITE}hello world${Formatting.RESET}", bgWhite("hello world"))

    @Test
    fun testBgBrightRed() =
        assertEquals<String>(
            "${Formatting.BGColor.BRIGHT_RED}hello world${Formatting.RESET}",
            bgBrightRed("hello world")
        )

    @Test
    fun testBgBrightGreen() =
        assertEquals<String>(
            "${Formatting.BGColor.BRIGHT_GREEN}hello world${Formatting.RESET}",
            bgBrightGreen("hello world")
        )

    @Test
    fun testBgBrightYellow() =
        assertEquals<String>(
            "${Formatting.BGColor.BRIGHT_YELLOW}hello world${Formatting.RESET}",
            bgBrightYellow("hello world")
        )

    @Test
    fun testBgBrightBlue() =
        assertEquals<String>(
            "${Formatting.BGColor.BRIGHT_BLUE}hello world${Formatting.RESET}",
            bgBrightBlue("hello world")
        )

    @Test
    fun testBgBrightPurple() =
        assertEquals<String>(
            "${Formatting.BGColor.BRIGHT_PURPLE}hello world${Formatting.RESET}",
            bgBrightPurple("hello world")
        )

    @Test
    fun testBgBrightCyan() =
        assertEquals<String>(
            "${Formatting.BGColor.BRIGHT_CYAN}hello world${Formatting.RESET}",
            bgBrightCyan("hello world")
        )

    @Test
    fun testBgBrightWhite() =
        assertEquals<String>(
            "${Formatting.BGColor.BRIGHT_WHITE}hello world${Formatting.RESET}",
            bgBrightWhite("hello world")
        )

    @Test
    fun testBold() =
        assertEquals<String>("${Formatting.BOLD}hello world${Formatting.RESET}", bold("hello world"))

    @Test
    fun testItalic() =
        assertEquals<String>("${Formatting.ITALIC}hello world${Formatting.RESET}", italic("hello world"))

    @Test
    fun testUnderline() =
        assertEquals<String>("${Formatting.UNDERLINE}hello world${Formatting.RESET}", underline("hello world"))

    @Test
    fun testReverse() =
        assertEquals<String>("hello world".reversed(), reverse("hello world"))

    @Test
    fun testInvert() =
        assertEquals<String>("${Formatting.INVERT}hello world${Formatting.RESET}", invert("hello world"))

    @Test
    fun testJoin() =
        assertEquals<String>(arrayOf("hello", "world").joinToString(""), join("hello", "world"))

}