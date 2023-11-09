package io.github.shakelang.shake.util.shason.processing

import io.github.shakelang.shake.util.shason.processing.TestUtilities.makeTokens
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class JsonLexerTests {

    @Test
    fun testLCURL() =
        testTokens(
            "JsonLexerTests#testLCURL()",
            "{",
            JsonToken(JsonTokenType.LCURL, 0)
        )

    @Test
    fun testRCURL() =
        testTokens(
            "JsonLexerTests#testRCURL()",
            "}",
            JsonToken(JsonTokenType.RCURL, 0)
        )

    @Test
    fun testLSQUARE() =
        testTokens(
            "JsonLexerTests#testLQUARE()",
            "[",
            JsonToken(JsonTokenType.LSQUARE, 0)
        )

    @Test
    fun testRSQUARE() =
        testTokens(
            "JsonLexerTests#testRSQUARE()",
            "]",
            JsonToken(JsonTokenType.RSQUARE, 0)
        )

    @Test
    fun testCOMMA() =
        testTokens(
            "JsonLexerTests#testCOMMA()",
            ",",
            JsonToken(JsonTokenType.COMMA, 0)
        )

    @Test
    fun testCOLON() =
        testTokens(
            "JsonLexerTests#testCOLON()",
            ":",
            JsonToken(JsonTokenType.COLON, 0)
        )

    @Test
    fun testSTRING() =
        testTokens(
            "JsonLexerTests#testSTRING()",
            "\"\"",
            JsonToken(JsonTokenType.STRING, 0, 1, "")
        )

    @Test
    fun testSTRINGContent() =
        testTokens(
            "JsonLexerTests#testSTRINGContent()",
            "\"a\"",
            JsonToken(JsonTokenType.STRING, 0, 2, "a")
        )

    @Test
    fun testSTRINGContents() =
        testTokens(
            "JsonLexerTests#testSTRINGContents()",
            "\"abc\"",
            JsonToken(JsonTokenType.STRING, 0, 4, "abc")
        )

    @Test
    fun testSTRINGContentEscapeN() =
        testTokens(
            "JsonLexerTests#testSTRINGContentEscapeN()",
            "\"\\n\"",
            JsonToken(JsonTokenType.STRING, 0, 3, "\n")
        )

    @Test
    fun testSTRINGContentEscapeT() =
        testTokens(
            "JsonLexerTests#testSTRINGContentEscapeT()",
            "\"\\t\"",
            JsonToken(JsonTokenType.STRING, 0, 3, "\t")
        )

    @Test
    fun testSTRINGContentEscapeB() =
        testTokens(
            "JsonLexerTests#testSTRINGContentEscapeB()",
            "\"\\b\"",
            JsonToken(JsonTokenType.STRING, 0, 3, "\b")
        )

    @Test
    fun testSTRINGContentEscapeR() =
        testTokens(
            "JsonLexerTests#testSTRINGContentEscapeB()",
            "\"\\r\"",
            JsonToken(JsonTokenType.STRING, 0, 3, "\r")
        )

    @Test
    fun testSTRINGContentEscapeBS() =
        testTokens(
            "JsonLexerTests#testSTRINGContentEscapeBS()",
            "\"\\\\\"",
            JsonToken(JsonTokenType.STRING, 0, 3, "\\")
        )

    @Test
    fun testSTRINGContentEscapeSQ() =
        testTokens(
            "JsonLexerTests#testSTRINGContentEscapeSQ()",
            "\"\\'\"",
            JsonToken(JsonTokenType.STRING, 0, 3, "'")
        )

    @Test
    fun testSTRINGContentEscapeDQ() =
        testTokens(
            "JsonLexerTests#testSTRINGContentEscapeDQ()",
            "\"\\\"\"",
            JsonToken(JsonTokenType.STRING, 0, 3, "\"")
        )

    @Test
    fun testSTRINGContentUnicode() =
        testTokens(
            "JsonLexerTests#testSTRINGContentUnicode()",
            "\"\\ue0af\"",
            JsonToken(JsonTokenType.STRING, 0, 7, "e0af".toInt(radix = 16).toChar().toString())
        )

    @Test
    fun testDOUBLE() =
        testTokens(
            "JsonLexerTests#testDOUBLE()",
            "1.2",
            JsonToken(JsonTokenType.DOUBLE, 0, 2, "1.2")
        )

    @Test
    fun testDOUBLENoPrefix() =
        testTokens(
            "JsonLexerTests#testDOUBLENoPrefix()",
            ".2",
            JsonToken(JsonTokenType.DOUBLE, 0, 1, ".2")
        )

    @Test
    fun testDOUBLENoSuffix() =
        testTokens(
            "JsonLexerTests#testDOUBLENoPrefix()",
            "2.",
            JsonToken(JsonTokenType.DOUBLE, 0, 1, "2.")
        )

    @Test
    fun testINT() =
        testTokens(
            "JsonLexerTests#testINT()",
            "42",
            JsonToken(JsonTokenType.INT, 0, 1, "42")
        )

    @Test
    fun testTRUE() =
        testTokens(
            "JsonLexerTests#testTRUE()",
            "true",
            JsonToken(JsonTokenType.TRUE, 0, 3)
        )

    @Test
    fun testFALSE() =
        testTokens(
            "JsonLexerTests#testFALSE()",
            "false",
            JsonToken(JsonTokenType.FALSE, 0, 4)
        )

    fun testTokens(src: String, content: String, vararg expectedTokens: JsonToken) {
        val input = makeTokens(src, content)
        assertSame(expectedTokens.size, input.size, "Expected ${expectedTokens.size} tokens, but got ${input.size}")
        for (t in expectedTokens) assertEquals(t, input.next())
    }
}