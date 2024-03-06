package com.shakelang.util.shason.processing

import com.shakelang.util.shason.processing.TestUtilities.makeTokens
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class JsonLexerTests : FreeSpec(
    {

        fun testTokens(src: String, content: String, vararg expectedTokens: JsonTokenType) {
            val input = makeTokens(src, content)
            for (t in expectedTokens) input.next().type shouldBe t
        }

        "testLCURL" {
            testTokens(
                "JsonLexerTests#testLCURL()",
                "{",
                JsonTokenType.LCURL,
            )
        }

        "testRCURL" {
            testTokens(
                "JsonLexerTests#testRCURL()",
                "}",
                JsonTokenType.RCURL,
            )
        }

        "testLSQUARE" {
            testTokens(
                "JsonLexerTests#testLQUARE()",
                "[",
                JsonTokenType.LSQUARE,
            )
        }

        "testRSQUARE" {
            testTokens(
                "JsonLexerTests#testRSQUARE()",
                "]",
                JsonTokenType.RSQUARE,
            )
        }

        "testCOMMA" {
            testTokens(
                "JsonLexerTests#testCOMMA()",
                ",",
                JsonTokenType.COMMA,
            )
        }

        "testCOLON" {
            testTokens(
                "JsonLexerTests#testCOLON()",
                ":",
                JsonTokenType.COLON,
            )
        }

        "testSTRING" {
            testTokens(
                "JsonLexerTests#testSTRING()",
                "\"\"",
                JsonTokenType.STRING,
            )
        }

        "testSTRINGContent" {
            testTokens(
                "JsonLexerTests#testSTRINGContent()",
                "\"a\"",
                JsonTokenType.STRING,
            )
        }

        "testSTRINGContents" {
            testTokens(
                "JsonLexerTests#testSTRINGContents()",
                "\"abc\"",
                JsonTokenType.STRING,
            )
        }

        "testSTRINGContentEscapeN" {
            testTokens(
                "JsonLexerTests#testSTRINGContentEscapeN()",
                "\"\\n\"",
                JsonTokenType.STRING,
            )
        }

        "testSTRINGContentEscapeT" {
            testTokens(
                "JsonLexerTests#testSTRINGContentEscapeT()",
                "\"\\t\"",
                JsonTokenType.STRING,
            )
        }

        "testSTRINGContentEscapeB" {
            testTokens(
                "JsonLexerTests#testSTRINGContentEscapeB()",
                "\"\\b\"",
                JsonTokenType.STRING,
            )
        }

        "testSTRINGContentEscapeR" {
            testTokens(
                "JsonLexerTests#testSTRINGContentEscapeB()",
                "\"\\r\"",
                JsonTokenType.STRING,
            )
        }

        "testSTRINGContentEscapeBS" {
            testTokens(
                "JsonLexerTests#testSTRINGContentEscapeBS()",
                "\"\\\\\"",
                JsonTokenType.STRING,
            )
        }

        "testSTRINGContentEscapeSQ" {
            testTokens(
                "JsonLexerTests#testSTRINGContentEscapeSQ()",
                "\"\\'\"",
                JsonTokenType.STRING,
            )
        }

        "testSTRINGContentEscapeDQ" {
            testTokens(
                "JsonLexerTests#testSTRINGContentEscapeDQ()",
                "\"\\\"\"",
                JsonTokenType.STRING,
            )
        }

        "testSTRINGContentUnicode" {
            testTokens(
                "JsonLexerTests#testSTRINGContentUnicode()",
                "\"\\ue0af\"",
                JsonTokenType.STRING,
            )
        }

        "testDOUBLE" {
            testTokens(
                "JsonLexerTests#testDOUBLE()",
                "1.2",
                JsonTokenType.DOUBLE,
            )
        }

        "testDOUBLENoPrefix" {
            testTokens(
                "JsonLexerTests#testDOUBLENoPrefix()",
                ".2",
                JsonTokenType.DOUBLE,
            )
        }

        "testDOUBLENoSuffix" {
            testTokens(
                "JsonLexerTests#testDOUBLENoPrefix()",
                "2.",
                JsonTokenType.DOUBLE,
            )
        }

        "testINT" {
            testTokens(
                "JsonLexerTests#testINT()",
                "42",
                JsonTokenType.INT,
            )
        }

        "testTRUE" {
            testTokens(
                "JsonLexerTests#testTRUE()",
                "true",
                JsonTokenType.TRUE,
            )
        }

        "testFALSE" {
            testTokens(
                "JsonLexerTests#testFALSE()",
                "false",
                JsonTokenType.FALSE,
            )
        }
    },
)
