package io.github.shakelang.parseutils

import io.github.shakelang.parseutils.characters.Characters
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CharactersTests {

    @Test
    fun testIsHexCharacter() = testCharacterRange(Characters::isHexCharacter, '0'..'9', 'a'..'f', 'A'..'F')

    @Test
    fun testIsNumberCharacter() = testCharacterRange(Characters::isNumberCharacter, '0'..'9')

    @Test
    fun testIsNumberOrDotCharacter() = testCharacterRange(Characters::isNumberOrDotCharacter, '0'..'9', '.')

    @Test
    fun testIsWhitespaceCharacter() = testCharacterRange(Characters::isWhitespaceCharacter, '\r', '\t', ' ')

    @Test
    fun testIsIdentifierCharacter() =
        testCharacterRange(Characters::isIdentifierCharacter, 'a'..'z', 'A'..'Z', '0'..'9', '_')

    @Test
    fun testIsIdentifierStartCharacter() =
        testCharacterRange(Characters::isIdentifierStartCharacter, 'a'..'z', 'A'..'Z', '_')

    @Test
    fun testParseString() {
        assertEquals("afaefefe", Characters.parseString("afaefefe"), "Normal string parsing failed")
        assertEquals(
            "\\\t\b\r\n\'\"",
            Characters.parseString("\\\\\\t\\b\\r\\n\\'\\\""),
            "Escape string parsing failed"
        )
        assertEquals("\u0015", Characters.parseString("\\u0015"), "Escape string parsing failed")
    }

}

fun testCharacterRange(
    fn: (Char) -> Boolean,
    vararg allowedCharacters: Any,
    range: CharRange = 0.toChar()..255.toChar()
) {

    for (i in range) {

        // Test character is allowed
        if (allowedCharacters.find {

                when (it) {
                    is CharRange -> i in it
                    is Char -> i == it
                    else -> throw Error()
                }

            } != null)
            assertTrue("Function ${fn::class} should return true for input '$i'") { fn(i) }
        else
            assertFalse("Function ${fn::class} should not return true for input '$i'") { fn(i) }

    }

}