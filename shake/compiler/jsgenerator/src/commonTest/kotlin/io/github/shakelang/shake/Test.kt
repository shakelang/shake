package io.github.shakelang.shake

import io.github.shakelang.parseutils.characters.source.CharacterSource
import io.github.shakelang.parseutils.characters.streaming.SourceCharacterInputStream
import io.github.shakelang.shake.js.ShakeJsGenerator
import io.github.shakelang.shake.lexer.ShakeLexer
import io.github.shakelang.shake.parser.Parser

fun main() {
    val inputStream =  SourceCharacterInputStream(CharacterSource.from(
            "class Test { int i = 0; static int x = 42; void test() { int i = 0; } static void xxx() {} } void test(int i) { println(i); }",
            "test.shake"))
    val lexer = ShakeLexer(inputStream)
    val tokens = lexer.makeTokens()
    val parser = Parser(tokens)
    val program = parser.parse()
    val generator = ShakeJsGenerator()
    println(generator.visit(program).generate())
}