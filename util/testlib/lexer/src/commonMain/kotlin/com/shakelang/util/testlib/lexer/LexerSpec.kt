package com.shakelang.util.testlib.lexer

import com.shakelang.util.io.streaming.general.Stream
import com.shakelang.util.parseutils.characters.source.CharacterSource
import com.shakelang.util.parseutils.characters.streaming.CharacterInputStream
import com.shakelang.util.parseutils.characters.streaming.SourceCharacterInputStream
import com.shakelang.util.parseutils.lexer.token.TokenType
import com.shakelang.util.parseutils.lexer.token.stream.TokenInputStream
import io.kotest.assertions.failure
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

typealias AnyTokenStream = TokenInputStream<*, *, *, *>

abstract class LexerSpec(
    lexer: (file: String, src: String) -> AnyTokenStream,
    tokenTypes: List<TokenType>,
    tokenList: List<TokenSpecDefinition>,
    ignored: List<TokenType> = emptyList(),
) : FunSpec(
    {
        val tokenListMap = tokenList.associateBy { it.tt }
        val definitions = tokenTypes
            .filter { it !in ignored }
            .mapNotNull {
                try {
                    tokenListMap[it] ?: TokenSpecDefinition.of(it)
                } catch (e: IllegalArgumentException) {
                    test("token type ${it.name} value definition") {
                        throw failure("Token type ${it.name} value definition failed", e)
                    }
                    null
                }
            }

        definitions.forEach { def ->

            val tt = def.tt

            def.tests.forEach { test ->

                val src = test.src
                val dist = test.dist

                test("lex ${tt.name} with value '$src'") {
                    val stream = lexer("<${tt.name}>", src)
                    stream.hasNext() shouldBe true
                    val token = stream.next()
                    stream.hasNext() shouldBe false
                    token.type shouldBe tt
                    token.value shouldBe dist
                }
            }
        }
    },
) {
    constructor(
        lexer: (CharacterInputStream) -> AnyTokenStream,
        tokenTypes: List<TokenType>,
        tokenList: List<TokenSpecDefinition>,
        ignored: List<TokenType> = emptyList(),
    ) : this(
        { file, src ->
            lexer(
                SourceCharacterInputStream(
                    CharacterSource.Companion.from(
                        src,
                        file,
                    ),
                ),
            )
        },
        tokenTypes,
        tokenList,
        ignored,
    )
}

class ValuePair(
    val src: String,
    val dist: String,
)

class TokenSpecDefinition(
    val tt: TokenType,
    val matches: Regex,
    val tests: List<ValuePair>,
) {

    constructor(
        tt: TokenType,
        matches: Regex,
        provider: Stream<ValuePair>,
        maxTests: Int = 100,
    ) : this(
        tt,
        matches,
        provider.run {
            val tests = mutableListOf<ValuePair>()
            var i = 0
            while (i < maxTests && provider.hasNext()) {
                tests.add(provider.read())
                i++
            }
            tests.toList()
        },
    )

    companion object {
        fun of(
            tt: TokenType,
        ): TokenSpecDefinition {
            val tokenValue = tt.value ?: throw IllegalArgumentException("Token type ${tt.name} without a value requires manual definition!")
            return TokenSpecDefinition(
                tt,
                Regex(Regex.escape(tokenValue)),
                listOf(
                    ValuePair(
                        tokenValue,
                        tokenValue,
                    ),
                ),
            )
        }
    }
}
