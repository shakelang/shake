package io.github.shakelang.shake.parser

import io.github.shakelang.parseutils.characters.streaming.CharacterInputStream
import io.github.shakelang.parseutils.characters.streaming.SourceCharacterInputStream
import io.github.shakelang.shake.assertType
import io.github.shakelang.shake.lexer.ShakeLexer
import io.github.shakelang.shake.parser.node.*
import kotlin.reflect.KClass
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

object ParserTestUtil {
    fun parse(source: String, input: String): ShakeFile {
        val inp: CharacterInputStream = SourceCharacterInputStream(source, input)
        val lexer = ShakeLexer(inp)
        val tokens = lexer.makeTokens()
        val parser = ShakeParser.from(tokens)
        return parser.parse()
    }

    fun parseStatement(source: String, input: String): ShakeTree {
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
        assertEquals(1, nodes.size)

        // result is expected class
        assertNotNull(nodes[0])
        assertType(type, nodes[0])
        return nodes[0] as T
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : ShakeStatementNode> parseStatement(source: String, input: String, type: KClass<T>): T {
        val tree = parseStatement(source, input)
        val nodes = tree.children

        // Result should be 1 node
        assertEquals(1, nodes.size)

        // result is expected class
        assertNotNull(nodes[0])
        assertType(type, nodes[0])
        return nodes[0] as T
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : ShakeValuedNode> parseValue(source: String, input: String, type: KClass<T>): T {
        val value = parseValue(source, input)
        assertType(type, value)
        return value as T
    }


}