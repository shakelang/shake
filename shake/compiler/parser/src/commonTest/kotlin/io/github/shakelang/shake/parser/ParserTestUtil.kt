package io.github.shakelang.shake.parser

import io.github.shakelang.shake.assertType
import io.github.shakelang.shake.lexer.ShakeLexer
import io.github.shakelang.shake.parser.node.ShakeNode
import io.github.shakelang.shake.parser.node.ShakeTree
import io.github.shakelang.parseutils.characters.streaming.CharacterInputStream
import io.github.shakelang.parseutils.characters.streaming.SourceCharacterInputStream
import kotlin.reflect.KClass
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

object ParserTestUtil {
    fun parse(source: String, input: String): ShakeTree {
        val inp: CharacterInputStream = SourceCharacterInputStream(source, input)
        val lexer = ShakeLexer(inp)
        val tokens = lexer.makeTokens()
        val parser = ShakeParser(tokens)
        return parser.parse()
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : ShakeNode> parseSingle(source: String, input: String, type: KClass<T>): T {
        val tree = parse(source, input)
        val nodes = tree.children

        // Result should be 1 node
        assertEquals(1, nodes.size)

        // result is expected class
        assertNotNull(nodes[0])
        assertType(type, nodes[0])
        return nodes[0] as T
    }
}