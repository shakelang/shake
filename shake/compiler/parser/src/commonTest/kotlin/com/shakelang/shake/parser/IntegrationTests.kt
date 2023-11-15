package com.shakelang.shake.parser

import kotlin.test.Test
import kotlin.test.assertEquals

// This file holds many tests that test the parser and the lexer together
// It should test every possible syntax of the language
// We just test the correctness of the parsed AST, by exporting it as json object
class IntegrationTests {

    // Literals
    @Test
    fun testIntegerLiteral() = testCodeSnippetValue("42", mapOf("name" to "ShakeIntegerNode", "value" to 42))

    @Test
    fun testDoubleLiteral() = testCodeSnippetValue("42.0", mapOf("name" to "ShakeDoubleNode", "value" to 42.0))

    @Test
    fun testStringLiteral() =
        testCodeSnippetValue("\"Hello World\"", mapOf("name" to "ShakeStringNode", "value" to "Hello World"))

    @Test
    fun testTrueLiteral() = testCodeSnippetValue("true", mapOf("name" to "ShakeLogicalTrueNode"))

    @Test
    fun testFalseLiteral() = testCodeSnippetValue("false", mapOf("name" to "ShakeLogicalFalseNode"))

    @Test
    fun testNullLiteral() = testCodeSnippetValue("null", mapOf("name" to "ShakeNullNode"))


    // Expressions
    @Test
    fun testAddExpression() = testCodeSnippetValue("1 + 2", mapOf(
        "name" to "ShakeAddNode",
        "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
        "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
    ))

    @Test
    fun testSubtractExpression() = testCodeSnippetValue("1 - 2", mapOf(
        "name" to "ShakeSubNode",
        "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
        "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
    ))

    @Test
    fun testMultiplyExpression() = testCodeSnippetValue("1 * 2", mapOf(
        "name" to "ShakeMulNode",
        "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
        "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
    ))

    @Test
    fun testDivideExpression() = testCodeSnippetValue("1 / 2", mapOf(
        "name" to "ShakeDivNode",
        "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
        "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
    ))

    @Test
    fun testModuloExpression() = testCodeSnippetValue("1 % 2", mapOf(
        "name" to "ShakeModNode",
        "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
        "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
    ))

    @Test
    fun testPowerExpression() = testCodeSnippetValue("1 ** 2", mapOf(
        "name" to "ShakePowNode",
        "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
        "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
    ))

    @Test
    fun testLogicalAndExpression() = testCodeSnippetValue("true && false", mapOf(
        "name" to "ShakeLogicalAndNode",
        "left" to mapOf("name" to "ShakeLogicalTrueNode"),
        "right" to mapOf("name" to "ShakeLogicalFalseNode")
    ))

    @Test
    fun testLogicalOrExpression() = testCodeSnippetValue("true || false", mapOf(
        "name" to "ShakeLogicalOrNode",
        "left" to mapOf("name" to "ShakeLogicalTrueNode"),
        "right" to mapOf("name" to "ShakeLogicalFalseNode")
    ))

    @Test
    fun testLogicalXOrExpression() = testCodeSnippetValue("true ^^ false", mapOf(
        "name" to "ShakeLogicalXOrNode",
        "left" to mapOf("name" to "ShakeLogicalTrueNode"),
        "right" to mapOf("name" to "ShakeLogicalFalseNode")
    ))

    @Test
    fun testEqualsExpression() = testCodeSnippetValue("1 == 2", mapOf(
        "name" to "ShakeEqualNode",
        "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
        "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
    ))

    @Test
    fun testNotEqualsExpression() = testCodeSnippetValue("1 != 2", mapOf(
        "name" to "ShakeNotEqualNode",
        "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
        "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
    ))

    @Test
    fun testLessThanExpression() = testCodeSnippetValue("1 < 2", mapOf(
        "name" to "ShakeLessThanNode",
        "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
        "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
    ))

    @Test
    fun testLessThanOrEqualsExpression() = testCodeSnippetValue("1 <= 2", mapOf(
        "name" to "ShakeLessThanOrEqualNode",
        "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
        "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
    ))

    @Test
    fun testGreaterThanExpression() = testCodeSnippetValue("1 > 2", mapOf(
        "name" to "ShakeGreaterThanNode",
        "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
        "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
    ))

    @Test
    fun testGreaterThanOrEqualsExpression() = testCodeSnippetValue("1 >= 2", mapOf(
        "name" to "ShakeGreaterThanOrEqualNode",
        "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
        "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
    ))

    @Test
    fun testUnaryMinusExpression() = testCodeSnippetValue("-1", mapOf(
        "name" to "ShakeUnaryMinusNode",
        "value" to mapOf("name" to "ShakeIntegerNode", "value" to 1)
    ))

    @Test
    fun testUnaryPlusExpression() = testCodeSnippetValue("+1", mapOf(
        "name" to "ShakeUnaryPlusNode",
        "value" to mapOf("name" to "ShakeIntegerNode", "value" to 1)
    ))

    @Test
    fun testNotExpression() = testCodeSnippetValue("!true", mapOf(
        "name" to "ShakeLogicalNotNode",
        "value" to mapOf("name" to "ShakeLogicalTrueNode")
    ))



    // Utils

    private fun testCodeSnippetValue(code: String, expected: Any) {
        val sourceLocation = "TestInput"
        val parsed = ParserTestUtil.parseValue(sourceLocation, code)
        val json = parsed.json

        assertEquals(expected, json, "The parsed value is not correct")

    }

}