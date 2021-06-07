package com.github.shakelang.shake.parser

import com.github.shakelang.shake.assertType
import com.github.shakelang.shake.lexer.Lexer
import com.github.shakelang.shake.parser.node.Node
import com.github.shakelang.shake.parser.node.Tree
import com.github.shakelang.shake.util.characterinput.characterinputstream.CharacterInputStream
import com.github.shakelang.shake.util.characterinput.characterinputstream.SourceCharacterInputStream
import kotlin.reflect.KClass
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

object ParserTestUtil {
    fun parse(source: String, input: String): Tree {
        val input: CharacterInputStream = SourceCharacterInputStream(source, input)
        val lexer = Lexer(input)
        val tokens = lexer.makeTokens()
        val parser = Parser(tokens)
        return parser.parse()
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Node> parseSingle(source: String, input: String, type: KClass<T>): T {
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