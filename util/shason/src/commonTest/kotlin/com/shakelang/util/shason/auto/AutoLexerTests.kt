package com.shakelang.util.shason.auto

import com.shakelang.util.io.streaming.general.generatorStream
import com.shakelang.util.parseutils.characters.Characters
import com.shakelang.util.shason.processing.JsonLexer
import com.shakelang.util.shason.processing.JsonTokenType
import com.shakelang.util.testlib.lexer.LexerSpec
import com.shakelang.util.testlib.lexer.TokenSpecDefinition
import com.shakelang.util.testlib.lexer.ValuePair

class AutoLexerTests : LexerSpec(

    @Suppress("RedundantLambdaArrow") { it ->
        JsonLexer(it).stream()
    },
    JsonTokenType.entries,
    listOf(
        TokenSpecDefinition(
            JsonTokenType.STRING,
            "\"(.*?)\"\\s*:\\s*\"(.*?)\"".toRegex(),
            generatorStream {
                val str = (0..10).joinToString("") {
                    (0..255).random().toChar().toString()
                }
                ValuePair("\"${Characters.escapeString(str)}\"", str)
            },
            10,
        ),
        TokenSpecDefinition(
            JsonTokenType.INT,
            "[0-9]+".toRegex(),
            generatorStream {
                val str = (0..4).joinToString("") {
                    (0..9).random().toString()
                }
                ValuePair(str, str)
            },
            10,
        ),
        TokenSpecDefinition(
            JsonTokenType.DOUBLE,
            "[0-9]+\\.[0-9]+".toRegex(),
            generatorStream {
                val str = (0..4).joinToString("") {
                    (0..9).random().toString()
                } + "." + (0..4).joinToString("") {
                    (0..9).random().toString()
                }
                ValuePair(str, str)
            },
            10,
        ),
    ),

    ignored = listOf(JsonTokenType.EOF),
)
