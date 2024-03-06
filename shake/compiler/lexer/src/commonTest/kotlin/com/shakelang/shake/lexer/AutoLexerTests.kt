package com.shakelang.shake.lexer

import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.util.io.streaming.general.generatorStream
import com.shakelang.util.parseutils.characters.Characters
import com.shakelang.util.testlib.lexer.LexerSpec
import com.shakelang.util.testlib.lexer.TokenSpecDefinition
import com.shakelang.util.testlib.lexer.ValuePair

class AutoLexerTests : LexerSpec(

    @Suppress("RedundantLambdaArrow") { it ->
        ShakeLexer(it).stream()
    },
    ShakeTokenType.entries,
    listOf(
        TokenSpecDefinition(
            ShakeTokenType.STRING,
            "\"(.*?)\"\\s*:\\s*\"(.*?)\"".toRegex(),
            generatorStream {
                val str = (0..10).joinToString("") {
                    (0..255).random().toChar().toString()
                }
                ValuePair("\"${Characters.escapeString(str)}\"", "\"${Characters.escapeString(str)}\"")
            },
        ),
        TokenSpecDefinition(
            ShakeTokenType.INTEGER,
            "[0-9]+".toRegex(),
            generatorStream {
                val str = (0..4).joinToString("") {
                    (0..9).random().toString()
                }
                ValuePair(str, str)
            },
        ),
        TokenSpecDefinition(
            ShakeTokenType.FLOAT,
            "[0-9]+\\.[0-9]+".toRegex(),
            generatorStream {
                val str = (0..4).joinToString("") {
                    (0..9).random().toString()
                } + "." + (0..4).joinToString("") {
                    (0..9).random().toString()
                }
                ValuePair(str, str)
            },
        ),
        TokenSpecDefinition(
            ShakeTokenType.CHARACTER,
            "'([^\\\\']|\\\\[nrt\\\\'\"]|\\\\u[a-fA-F]{4})'".toRegex(),
            generatorStream {
                val str = Characters.escapeCharacter((0..255).random().toChar())
                ValuePair("'$str'", "'$str'")
            },
        ),
        TokenSpecDefinition(
            ShakeTokenType.IDENTIFIER,
            "[a-zA-Z_][a-zA-Z0-9_]*".toRegex(),
            generatorStream {
                val first = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_".random().toString()
                val str = first + (0..9).joinToString("") {
                    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_".random().toString()
                }
                ValuePair(str, str)
            },
        ),
    ),

    ignored = listOf(ShakeTokenType.EOF),
)
