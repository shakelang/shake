package com.shakelang.shake.parser

import com.shakelang.shake.lexer.ShakeLexer
import com.shakelang.shake.parser.node.ShakeFileChildNode
import com.shakelang.shake.parser.node.ShakeStatementNode
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.outer.ShakeFileNode
import com.shakelang.shake.parser.node.statements.ShakeBlockNode
import com.shakelang.util.parseutils.characters.streaming.CharacterInputStream
import com.shakelang.util.parseutils.characters.streaming.SourceCharacterInputStream
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlin.reflect.KClass

object ParserTestUtil {
    fun parse(source: String, input: String): ShakeFileNode {
        val inp: CharacterInputStream = SourceCharacterInputStream(source, input)
        val lexer = ShakeLexer(inp)
        val tokens = lexer.makeTokens()
        val parser = ShakeParser.from(tokens)
        return parser.parse()
    }

    fun parseStatement(source: String, input: String): ShakeBlockNode {
        val inp: CharacterInputStream = SourceCharacterInputStream(source, input)
        val lexer = ShakeLexer(inp)
        val tokens = lexer.makeTokens()
        val parser = ShakeParser.from(tokens)
        return parser.parseAsStatements()
    }

    fun parseValue(source: String, input: String): ShakeValuedNode {
        val inp: CharacterInputStream = SourceCharacterInputStream(source, input)
        val lexer = ShakeLexer(inp)
        val tokens = lexer.makeTokens()
        val parser = ShakeParser.from(tokens)
        return parser.expectValue()
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : ShakeFileChildNode> parseSingle(source: String, input: String, type: KClass<T>): T {
        val file = parse(source, input)
        val nodes = file.children

        // Result should be 1 node
        nodes.size shouldBe 1

        // result is expected class
        nodes[0] shouldNotBe null
        nodes[0] shouldBeOfType type
        return nodes[0] as T
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : ShakeStatementNode> parseStatement(source: String, input: String, type: KClass<T>): T {
        val tree = parseStatement(source, input)
        val nodes = tree.children

        // Result should be 1 node
        nodes.size shouldBe 1

        // result is expected class
        nodes[0] shouldNotBe null
        nodes[0] shouldBeOfType type
        return nodes[0] as T
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : ShakeValuedNode> parseValue(source: String, input: String, type: KClass<T>): T {
        val value = parseValue(source, input)
        value shouldNotBe null
        return value as T
    }
}
