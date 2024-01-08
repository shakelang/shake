package com.shakelang.util.shason.processing

import com.shakelang.util.shason.processing.TestUtilities.makeTokens
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class JsonLexerTests : FreeSpec({

    fun testTokens(src: String, content: String, vararg expectedTokens: JsonToken) {
        val input = makeTokens(src, content)
        input.size shouldBe expectedTokens.size
        for (t in expectedTokens) input.next() shouldBe t
    }

    "testLCURL" {
        testTokens(
            "JsonLexerTests#testLCURL()",
            "{",
            JsonToken(JsonTokenType.LCURL, 0),
        )
    }

    "testRCURL" {
        testTokens(
            "JsonLexerTests#testRCURL()",
            "}",
            JsonToken(JsonTokenType.RCURL, 0),
        )
    }

    "testLSQUARE" {
        testTokens(
            "JsonLexerTests#testLQUARE()",
            "[",
            JsonToken(JsonTokenType.LSQUARE, 0),
        )
    }

    "testRSQUARE" {
        testTokens(
            "JsonLexerTests#testRSQUARE()",
            "]",
            JsonToken(JsonTokenType.RSQUARE, 0),
        )
    }

    "testCOMMA" {
        testTokens(
            "JsonLexerTests#testCOMMA()",
            ",",
            JsonToken(JsonTokenType.COMMA, 0),
        )
    }

    "testCOLON" {
        testTokens(
            "JsonLexerTests#testCOLON()",
            ":",
            JsonToken(JsonTokenType.COLON, 0),
        )
    }

    "testSTRING" {
        testTokens(
            "JsonLexerTests#testSTRING()",
            "\"\"",
            JsonToken(JsonTokenType.STRING, 0, 1, ""),
        )
    }

    "testSTRINGContent" {
        testTokens(
            "JsonLexerTests#testSTRINGContent()",
            "\"a\"",
            JsonToken(JsonTokenType.STRING, 0, 2, "a"),
        )
    }

    "testSTRINGContents" {
        testTokens(
            "JsonLexerTests#testSTRINGContents()",
            "\"abc\"",
            JsonToken(JsonTokenType.STRING, 0, 4, "abc"),
        )
    }

    "testSTRINGContentEscapeN" {
        testTokens(
            "JsonLexerTests#testSTRINGContentEscapeN()",
            "\"\\n\"",
            JsonToken(JsonTokenType.STRING, 0, 3, "\n"),
        )
    }

    "testSTRINGContentEscapeT" {
        testTokens(
            "JsonLexerTests#testSTRINGContentEscapeT()",
            "\"\\t\"",
            JsonToken(JsonTokenType.STRING, 0, 3, "\t"),
        )
    }

    "testSTRINGContentEscapeB" {
        testTokens(
            "JsonLexerTests#testSTRINGContentEscapeB()",
            "\"\\b\"",
            JsonToken(JsonTokenType.STRING, 0, 3, "\b"),
        )
    }

    "testSTRINGContentEscapeR" {
        testTokens(
            "JsonLexerTests#testSTRINGContentEscapeB()",
            "\"\\r\"",
            JsonToken(JsonTokenType.STRING, 0, 3, "\r"),
        )
    }

    "testSTRINGContentEscapeBS" {
        testTokens(
            "JsonLexerTests#testSTRINGContentEscapeBS()",
            "\"\\\\\"",
            JsonToken(JsonTokenType.STRING, 0, 3, "\\"),
        )
    }

    "testSTRINGContentEscapeSQ" {
        testTokens(
            "JsonLexerTests#testSTRINGContentEscapeSQ()",
            "\"\\'\"",
            JsonToken(JsonTokenType.STRING, 0, 3, "'"),
        )
    }

    "testSTRINGContentEscapeDQ" {
        testTokens(
            "JsonLexerTests#testSTRINGContentEscapeDQ()",
            "\"\\\"\"",
            JsonToken(JsonTokenType.STRING, 0, 3, "\""),
        )
    }

    "testSTRINGContentUnicode" {
        testTokens(
            "JsonLexerTests#testSTRINGContentUnicode()",
            "\"\\ue0af\"",
            JsonToken(JsonTokenType.STRING, 0, 7, "e0af".toInt(radix = 16).toChar().toString()),
        )
    }

    "testDOUBLE" {
        testTokens(
            "JsonLexerTests#testDOUBLE()",
            "1.2",
            JsonToken(JsonTokenType.DOUBLE, 0, 2, "1.2"),
        )
    }

    "testDOUBLENoPrefix" {
        testTokens(
            "JsonLexerTests#testDOUBLENoPrefix()",
            ".2",
            JsonToken(JsonTokenType.DOUBLE, 0, 1, ".2"),
        )
    }

    "testDOUBLENoSuffix" {
        testTokens(
            "JsonLexerTests#testDOUBLENoPrefix()",
            "2.",
            JsonToken(JsonTokenType.DOUBLE, 0, 1, "2."),
        )
    }

    "testINT" {
        testTokens(
            "JsonLexerTests#testINT()",
            "42",
            JsonToken(JsonTokenType.INT, 0, 1, "42"),
        )
    }

    "testTRUE" {
        testTokens(
            "JsonLexerTests#testTRUE()",
            "true",
            JsonToken(JsonTokenType.TRUE, 0, 3),
        )
    }

    "testFALSE" {
        testTokens(
            "JsonLexerTests#testFALSE()",
            "false",
            JsonToken(JsonTokenType.FALSE, 0, 4),
        )
    }
})
