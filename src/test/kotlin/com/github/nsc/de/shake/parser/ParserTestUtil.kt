package com.github.nsc.de.shake.parser

import com.github.nsc.de.shake.assertType
import com.github.nsc.de.shake.lexer.Lexer
import com.github.nsc.de.shake.lexer.characterinput.characterinputstream.CharacterInputStream
import com.github.nsc.de.shake.lexer.characterinput.characterinputstream.SourceCharacterInputStream
import com.github.nsc.de.shake.parser.node.*
import org.junit.jupiter.api.Assertions

object ParserTestUtil {
    fun parse(source: String?, input: String?): Tree {
        val `in`: CharacterInputStream = SourceCharacterInputStream(source, input)
        val lexer = Lexer(`in`)
        val tokens = lexer.makeTokens()
        val parser = Parser(tokens)
        return parser.parse()
    }

    @Suppress("unchecked")
    fun <T : Node?> parseSingle(source: String?, input: String?, type: Class<T>?): T {
        val tree = parse(source, input)
        val nodes = tree.children

        // Result should be 1 node
        Assertions.assertEquals(1, nodes.size)

        // result is expected class
        Assertions.assertNotNull(nodes[0])
        assertType(type, nodes[0])
        return nodes[0] as T
    }
}