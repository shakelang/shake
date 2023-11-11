package com.shakelang.shake.util.parseutils

import com.shakelang.shake.util.parseutils.characters.Characters
import kotlin.test.*

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
        assertFailsWith(IllegalArgumentException::class, "Invalid unicode escape sequence") {
            Characters.parseString("\\u")
        }
        assertFailsWith(IllegalArgumentException::class, "Invalid unicode escape sequence") {
            Characters.parseString("\\u00")
        }
        assertFailsWith(IllegalArgumentException::class, "Invalid unicode escape sequence") {
            Characters.parseString("\\u00ga")
        }
        assertFailsWith(IllegalArgumentException::class, "Unknown escape sequence '\\g'") {
            Characters.parseString("\\g")
        }
    }

    @Test
    fun testEscapeCharacter() {
        assertEquals("\\\\", Characters.escapeCharacter('\\'))
        assertEquals("\\t", Characters.escapeCharacter('\t'))
        assertEquals("\\b", Characters.escapeCharacter('\b'))
        assertEquals("\\r", Characters.escapeCharacter('\r'))
        assertEquals("\\n", Characters.escapeCharacter('\n'))
        assertEquals("\\'", Characters.escapeCharacter('\''))
        assertEquals("\\\"", Characters.escapeCharacter('\"'))
        assertEquals("\\u0015", Characters.escapeCharacter('\u0015'))
        assertEquals("\\u0111", Characters.escapeCharacter('\u0111'))
        assertEquals("a", Characters.escapeCharacter('a'))
    }

    @Test
    fun testEscapeString() {
        assertEquals("\\\\\\t\\b\\r\\n\\'\\\"", Characters.escapeString("\\\t\b\r\n\'\""))
        assertEquals("\\u0015", Characters.escapeString("\u0015"))
        assertEquals("\\u0111", Characters.escapeString("\u0111"))
        assertEquals("a", Characters.escapeString("a"))
    }

    @Test
    fun testGetBase16Character() {
        assertEquals('0', Characters.getBase16Character(0))
        assertEquals('1', Characters.getBase16Character(1))
        assertEquals('2', Characters.getBase16Character(2))
        assertEquals('3', Characters.getBase16Character(3))
        assertEquals('4', Characters.getBase16Character(4))
        assertEquals('5', Characters.getBase16Character(5))
        assertEquals('6', Characters.getBase16Character(6))
        assertEquals('7', Characters.getBase16Character(7))
        assertEquals('8', Characters.getBase16Character(8))
        assertEquals('9', Characters.getBase16Character(9))
        assertEquals('a', Characters.getBase16Character(10))
        assertEquals('b', Characters.getBase16Character(11))
        assertEquals('c', Characters.getBase16Character(12))
        assertEquals('d', Characters.getBase16Character(13))
        assertEquals('e', Characters.getBase16Character(14))
        assertEquals('f', Characters.getBase16Character(15))
        assertFailsWith(IllegalArgumentException::class, "Input 16 should be in range 0..15") {
            Characters.getBase16Character(16)
        }
        assertFailsWith(IllegalArgumentException::class, "Input -1 should be in range 0..15") {
            Characters.getBase16Character(-1)
        }
    }

    @Test
    fun testToBase16() {
        assertEquals("0", Characters.toBase16(0))
        assertEquals("1", Characters.toBase16(1))
        assertEquals("2", Characters.toBase16(2))
        assertEquals("3", Characters.toBase16(3))
        assertEquals("4", Characters.toBase16(4))
        assertEquals("5", Characters.toBase16(5))
        assertEquals("6", Characters.toBase16(6))
        assertEquals("7", Characters.toBase16(7))
        assertEquals("8", Characters.toBase16(8))
        assertEquals("9", Characters.toBase16(9))
        assertEquals("a", Characters.toBase16(10))
        assertEquals("b", Characters.toBase16(11))
        assertEquals("c", Characters.toBase16(12))
        assertEquals("d", Characters.toBase16(13))
        assertEquals("e", Characters.toBase16(14))
        assertEquals("f", Characters.toBase16(15))
        assertEquals("10", Characters.toBase16(16))
        assertEquals("200", Characters.toBase16(0x200))
        assertEquals("ffff", Characters.toBase16(0xffff))
        assertEquals("00000000000000010000", Characters.toBase16(0x10000, 20))
        assertEquals("10000", Characters.toBase16(0x10000, 2))
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
        } != null
        ) {
            assertTrue("Function ${fn::class} should return true for input '$i'") { fn(i) }
        } else {
            assertFalse("Function ${fn::class} should not return true for input '$i'") { fn(i) }
        }
    }
}
