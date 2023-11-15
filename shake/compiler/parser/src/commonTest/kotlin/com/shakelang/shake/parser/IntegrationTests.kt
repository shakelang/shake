package com.shakelang.shake.parser

import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

// This file holds many tests that test the parser and the lexer together
// It should test every possible syntax of the language
// We just test the correctness of the parsed AST, by exporting it as json object
class IntegrationTests {

    // Literals
    @Test
    fun testIntegerLiteral() = testCodeSnippetValue("42", mapOf("name" to "IntegerNode", "value" to 42))

    @Test
    fun testDoubleLiteral() = testCodeSnippetValue("42.0", mapOf("name" to "DoubleNode", "value" to 42.0))

    @Test
    fun testStringLiteral() =
        testCodeSnippetValue("\"Hello World\"", mapOf("name" to "StringNode", "value" to "Hello World"))

    @Test
    fun testTrueLiteral() = testCodeSnippetValue("true", mapOf("name" to "LogicalTrueNode"))

    @Test
    fun testFalseLiteral() = testCodeSnippetValue("false", mapOf("name" to "LogicalFalseNode"))

    @Test
    @Ignore // TODO: Fix this
    fun testNullLiteral() = testCodeSnippetValue("null", mapOf("name" to "NullNode"))


    // Expressions
    @Test
    fun testAddExpression() = testCodeSnippetValue("1 + 2", mapOf(
        "name" to "AddNode",
        "left" to mapOf("name" to "IntegerNode", "value" to 1),
        "right" to mapOf("name" to "IntegerNode", "value" to 2)
    ))

    @Test
    fun testSubtractExpression() = testCodeSnippetValue("1 - 2", mapOf(
        "name" to "SubNode",
        "left" to mapOf("name" to "IntegerNode", "value" to 1),
        "right" to mapOf("name" to "IntegerNode", "value" to 2)
    ))

    @Test
    fun testMultiplyExpression() = testCodeSnippetValue("1 * 2", mapOf(
        "name" to "MulNode",
        "left" to mapOf("name" to "IntegerNode", "value" to 1),
        "right" to mapOf("name" to "IntegerNode", "value" to 2)
    ))

    @Test
    fun testDivideExpression() = testCodeSnippetValue("1 / 2", mapOf(
        "name" to "DivNode",
        "left" to mapOf("name" to "IntegerNode", "value" to 1),
        "right" to mapOf("name" to "IntegerNode", "value" to 2)
    ))

    @Test
    fun testModuloExpression() = testCodeSnippetValue("1 % 2", mapOf(
        "name" to "ModNode",
        "left" to mapOf("name" to "IntegerNode", "value" to 1),
        "right" to mapOf("name" to "IntegerNode", "value" to 2)
    ))

    @Test
    fun testPowerExpression() = testCodeSnippetValue("1 ** 2", mapOf(
        "name" to "PowNode",
        "left" to mapOf("name" to "IntegerNode", "value" to 1),
        "right" to mapOf("name" to "IntegerNode", "value" to 2)
    ))

    @Test
    fun testLogicalAndExpression() = testCodeSnippetValue("true && false", mapOf(
        "name" to "LogicalAndNode",
        "left" to mapOf("name" to "LogicalTrueNode"),
        "right" to mapOf("name" to "LogicalFalseNode")
    ))

    @Test
    fun testLogicalOrExpression() = testCodeSnippetValue("true || false", mapOf(
        "name" to "LogicalOrNode",
        "left" to mapOf("name" to "LogicalTrueNode"),
        "right" to mapOf("name" to "LogicalFalseNode")
    ))

    @Test
    fun testLogicalXOrExpression() = testCodeSnippetValue("true ^^ false", mapOf(
        "name" to "LogicalXOrNode",
        "left" to mapOf("name" to "LogicalTrueNode"),
        "right" to mapOf("name" to "LogicalFalseNode")
    ))

    @Test
    fun testEqualsExpression() = testCodeSnippetValue("1 == 2", mapOf(
        "name" to "ShakeEqualNode",
        "left" to mapOf("name" to "IntegerNode", "value" to 1),
        "right" to mapOf("name" to "IntegerNode", "value" to 2)
    ))

    @Test @Ignore // TODO: Fix this
    fun testNotEqualsExpression() = testCodeSnippetValue("1 != 2", mapOf(
        "name" to "NotEqualNode",
        "left" to mapOf("name" to "IntegerNode", "value" to 1),
        "right" to mapOf("name" to "IntegerNode", "value" to 2)
    ))

    @Test
    fun testLessThanExpression() = testCodeSnippetValue("1 < 2", mapOf(
        "name" to "ShakeLessThanNode",
        "left" to mapOf("name" to "IntegerNode", "value" to 1),
        "right" to mapOf("name" to "IntegerNode", "value" to 2)
    ))

    @Test
    fun testLessThanOrEqualsExpression() = testCodeSnippetValue("1 <= 2", mapOf(
        "name" to "ShakeLessThanOrEqualNode",
        "left" to mapOf("name" to "IntegerNode", "value" to 1),
        "right" to mapOf("name" to "IntegerNode", "value" to 2)
    ))

    @Test
    fun testGreaterThanExpression() = testCodeSnippetValue("1 > 2", mapOf(
        "name" to "ShakeGreaterThanNode",
        "left" to mapOf("name" to "IntegerNode", "value" to 1),
        "right" to mapOf("name" to "IntegerNode", "value" to 2)
    ))

    @Test
    fun testGreaterThanOrEqualsExpression() = testCodeSnippetValue("1 >= 2", mapOf(
        "name" to "ShakeGreaterThanOrEqualNode",
        "left" to mapOf("name" to "IntegerNode", "value" to 1),
        "right" to mapOf("name" to "IntegerNode", "value" to 2)
    ))

    @Test
    fun testUnaryMinusExpression() = testCodeSnippetValue("-1", mapOf(
        "name" to "ShakeUnaryMinusNode",
        "value" to mapOf("name" to "IntegerNode", "value" to 1)
    ))

    @Test
    fun testUnaryPlusExpression() = testCodeSnippetValue("+1", mapOf(
        "name" to "ShakeUnaryPlusNode",
        "value" to mapOf("name" to "IntegerNode", "value" to 1)
    ))

    @Test @Ignore // TODO: Fix this
    fun testNotExpression() = testCodeSnippetValue("!true", mapOf(
        "name" to "ShakeNotNode",
        "value" to mapOf("name" to "LogicalTrueNode")
    ))



    // Utils

    fun testCodeSnippetValue(code: String, expected: Any) {
        val sourceLocation = "TestInput"
        val parsed = ParserTestUtil.parseValue(sourceLocation, code)
        val json = parsed.json

        assertEquals(expected, json, "The parsed value is not correct")

    }

}


data class CallerInfo(
    val className: String,
    val functionName: String,
    val lineNumber: Int,
    val fileName: String
) {
    override fun toString(): String {
        return "CallerInfo(className='$className', functionName='$functionName', lineNumber=$lineNumber, fileName='$fileName')"
    }

}
