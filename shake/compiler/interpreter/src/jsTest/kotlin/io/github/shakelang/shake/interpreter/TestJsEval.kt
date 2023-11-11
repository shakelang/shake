package com.shakelang.shake.interpreter

import com.shakelang.shake.util.parseutils.characters.position.PositionMap
import com.shakelang.shake.util.parseutils.characters.streaming.CharacterInputStream
import com.shakelang.shake.util.parseutils.characters.streaming.SourceCharacterInputStream
import com.shakelang.shake.lexer.ShakeLexer
import com.shakelang.shake.parser.ShakeParser
import com.shakelang.shake.parser.node.ShakeBlockNode
import kotlin.test.Ignore
import kotlin.test.Test

class TestJsEval {

    @Ignore
    @Test
    fun testJsEval() {
        // FIXME: This test is ignored right now. It crashes in the browser for some reason
        val interpreter = Interpreter()
        interpreter.visit(parse("var i;for(i = 0; i < 1000; i++) {}").tree)
        jsEvalCode("console.log(variable('i'))", interpreter.global)
    }

}

private fun parse(input: String): ParseResult = parse(SourceCharacterInputStream("<Console>", input))

private fun parse(input: CharacterInputStream): ParseResult {

    // Create a new Lexer from the CharacterInputStream
    val lexer = ShakeLexer(input)

    // Generate the tokens using the lexer
    val tokens = lexer.makeTokens()

    // Create a new Parser from the tokens
    val parser = ShakeParser(tokens)

    // Let the parser parse the tokens into a Tree
    val tree = parser.parse()

    // return the Tree
    return ParseResult(tree, tokens.map)
}

private class ParseResult(val tree: ShakeBlockNode, val map: PositionMap)